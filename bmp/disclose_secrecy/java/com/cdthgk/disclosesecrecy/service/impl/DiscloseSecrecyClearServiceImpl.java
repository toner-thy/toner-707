package com.cdthgk.disclosesecrecy.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.disclosesecrecy.service.DiscloseSecrecyClearService;
import com.cdthgk.disclosesecrecy.vo.DiscloseSecrecyClear;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

public class DiscloseSecrecyClearServiceImpl extends BmpServiceImpl<DiscloseSecrecyClear, String> implements
DiscloseSecrecyClearService {
	@Override
	public List<DiscloseSecrecyClear> queryDiscloseSecrecyClearList(
			PageSortModel<DiscloseSecrecyClear> psm,
			DiscloseSecrecyClear discloseSecrecyClear,User user) {
		List<DiscloseSecrecyClear> list = new ArrayList<DiscloseSecrecyClear>();
		if (psm == null) {
			return list;
		}
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("FROM DiscloseSecrecyClear dssc  where 1=1");
		hql.append(" and dssc.createPerson.organ.organId = :organId");
		params.put("organId", user.getOrgan().getOrganId());
		if(discloseSecrecyClear!=null) {
			if(null!=discloseSecrecyClear.getDisclosesecrecycaseId()&&discloseSecrecyClear.getDisclosesecrecycaseId().getName()!= null) {
				hql.append(" and dssc.disclosesecrecycaseId.name like :name");
				params.put("name", "%" + discloseSecrecyClear.getDisclosesecrecycaseId().getName() + "%");
				//解除前密级
				if(discloseSecrecyClear.getDisclosesecrecycaseId().getSecrecyLevel()!=null) {
					hql.append(" and dssc.disclosesecrecycaseId.secrecyLevel = :secrecyLevel");
					params.put("secrecyLevel", discloseSecrecyClear.getDisclosesecrecycaseId().getSecrecyLevel());
				}
			}
			//解密类型
			if(discloseSecrecyClear.getClearType()!=null) {
				hql.append(" and dssc.clearType = :clearType");
				params.put("clearType", discloseSecrecyClear.getClearType());
			}
			//解密时间
			if(discloseSecrecyClear.getClearTime()!=null) {
				hql.append(" and dssc.clearTime = :clearTime");
				params.put("clearTime", discloseSecrecyClear.getClearTime());
			}
		}

		hql.append(" order by dssc.createDate desc");//降序排列
		list = this.findList(hql.toString(), params, psm);


		return list;
	}

	@Override
	public List<DiscloseSecrecyClear> queryDiscloseSecrecyClearList(
			PageSortModel<DiscloseSecrecyClear> psm,
			DiscloseSecrecyClear discloseSecrecyClear, User currentUser,
			String districtCode ,String includeChild) {
		List<DiscloseSecrecyClear> list = new ArrayList<DiscloseSecrecyClear>();
		if (psm == null) {
			return list;
		}
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("FROM DiscloseSecrecyClear dssc  where 1=1");
		if (includeChild.equals("1")) {
			int layer=this.getPersistProxy().getJdbcPersistence().findForInt(
					"select layer from sys_district where district_code ='"+districtCode+"'", params);
			hql.append(" and dssc.createPerson.organ.organId in (");
			hql.append("select o.organId from Organ as o where o.district.districtCode in ");
			hql.append("(select district.districtCode from District as district where district.layer like :layer ))");
			params.put("layer",  layer+"%");
		}
		if (includeChild.equals("0")) {
			hql.append(" and dssc.createPerson.organ.organId in (");
			hql.append("select o.organId from Organ as o where o.district.districtCode=:districtCode )");
			params.put("districtCode",  districtCode);
		}
		if(discloseSecrecyClear!=null) {
			if(null!=discloseSecrecyClear.getDisclosesecrecycaseId()&&discloseSecrecyClear.getDisclosesecrecycaseId().getName()!= null) {
				hql.append(" and dssc.disclosesecrecycaseId.name like :name");
				params.put("name", "%" + discloseSecrecyClear.getDisclosesecrecycaseId().getName() + "%");
				//解除前密级
				if(discloseSecrecyClear.getDisclosesecrecycaseId().getSecrecyLevel()!=null) {
					hql.append(" and dssc.disclosesecrecycaseId.secrecyLevel = :secrecyLevel");
					params.put("secrecyLevel", discloseSecrecyClear.getDisclosesecrecycaseId().getSecrecyLevel());
				}
			}
			//解密类型
			if(discloseSecrecyClear.getClearType()!=null) {
				hql.append(" and dssc.clearType = :clearType");
				params.put("clearType", discloseSecrecyClear.getClearType());
			}
			//解密时间
			if(discloseSecrecyClear.getClearTime()!=null) {
				hql.append(" and dssc.clearTime = :clearTime");
				params.put("clearTime", discloseSecrecyClear.getClearTime());
			}
		}

		hql.append(" order by dssc.createDate desc");//降序排列
		list = this.findList(hql.toString(), params, psm);


		return list;
	}
}