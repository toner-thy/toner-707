package com.cdthgk.disclosesecrecy.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.disclosesecrecy.service.CaseCriticalviolationClearService;
import com.cdthgk.disclosesecrecy.vo.CaseCriticalviolationClear;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

public class CaseCriticalviolationClearServiceImpl extends BmpServiceImpl<CaseCriticalviolationClear, String> implements
CaseCriticalviolationClearService {
	@Override
	public List<CaseCriticalviolationClear> queryCaseCriticalviolationClearList(
			PageSortModel<CaseCriticalviolationClear> psm,
			CaseCriticalviolationClear caseCriticalviolationClear,User user) {
		List<CaseCriticalviolationClear> list = new ArrayList<CaseCriticalviolationClear>();
		if (psm == null) {
			return list;
		}
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("FROM CaseCriticalviolationClear dssc  where 1=1");
		hql.append(" and dssc.createPerson.organ.organId = :organId");
		params.put("organId", user.getOrgan().getOrganId());
		if(caseCriticalviolationClear!=null) {
			if(null!=caseCriticalviolationClear.getCaseCriticalviolation()&&caseCriticalviolationClear.getCaseCriticalviolation().getName()!= null) {
				hql.append(" and dssc.caseCriticalviolation.name like :name");
				params.put("name", "%" + caseCriticalviolationClear.getCaseCriticalviolation().getName() + "%");
				//解除前密级
				if(caseCriticalviolationClear.getCaseCriticalviolation().getSecrecyLevel()!=null) {
					hql.append(" and dssc.caseCriticalviolation.secrecyLevel = :secrecyLevel");
					params.put("secrecyLevel", caseCriticalviolationClear.getCaseCriticalviolation().getSecrecyLevel());
				}
			}
			//解密类型
			if(caseCriticalviolationClear.getClearType()!=null) {
				hql.append(" and dssc.clearType = :clearType");
				params.put("clearType", caseCriticalviolationClear.getClearType());
			}
			//解密时间
			if(caseCriticalviolationClear.getClearTime()!=null) {
				hql.append(" and dssc.clearTime = :clearTime");
				params.put("clearTime", caseCriticalviolationClear.getClearTime());
			}
		}

		hql.append(" order by dssc.createDate desc");//降序排列
		list = this.findList(hql.toString(), params, psm);


		return list;
	}

	@Override
	public List<CaseCriticalviolationClear> queryCaseCriticalviolationClearList(
			PageSortModel<CaseCriticalviolationClear> psm,
			CaseCriticalviolationClear caseCriticalviolationClear,
			User currentUser, String districtCode,String includeChild) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("FROM CaseCriticalviolationClear dssc  where 1=1");
		hql.append(" and dssc.createPerson.organ.organId in (");
		if (includeChild.equals("0")) {
			hql.append("select o.organId from Organ as o where o.district.districtCode =:districtCode) ");
			params.put("districtCode",  districtCode);
		}
		if (includeChild.equals("1")) {
			int layer=this.getPersistProxy().getJdbcPersistence().findForInt(
					"select layer from sys_district where district_code ='"+districtCode+"'", params);
			hql.append("select o.organId from Organ as o where o.district.districtCode in ");
			hql.append("(select district.districtCode from District as district where district.layer like :layer ))");
			params.put("layer",  layer+"%");
		}
		if(caseCriticalviolationClear!=null) {
			if(null!=caseCriticalviolationClear.getCaseCriticalviolation()&&caseCriticalviolationClear.getCaseCriticalviolation().getName()!= null) {
				hql.append(" and dssc.caseCriticalviolation.name like :name");
				params.put("name", "%" + caseCriticalviolationClear.getCaseCriticalviolation().getName() + "%");
				//解除前密级
				if(caseCriticalviolationClear.getCaseCriticalviolation().getSecrecyLevel()!=null) {
					hql.append(" and dssc.caseCriticalviolation.secrecyLevel = :secrecyLevel");
					params.put("secrecyLevel", caseCriticalviolationClear.getCaseCriticalviolation().getSecrecyLevel());
				}
			}
			//解密类型
			if(caseCriticalviolationClear.getClearType()!=null) {
				hql.append(" and dssc.clearType = :clearType");
				params.put("clearType", caseCriticalviolationClear.getClearType());
			}
			//解密时间
			if(caseCriticalviolationClear.getClearTime()!=null) {
				hql.append(" and dssc.clearTime = :clearTime");
				params.put("clearTime", caseCriticalviolationClear.getClearTime());
			}
		}

		hql.append(" order by dssc.createDate desc");//降序排列
		List<CaseCriticalviolationClear> list = this.findList(hql.toString(), params, psm);
		return list;
	}
}