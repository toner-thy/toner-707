package com.cdthgk.disclosesecrecy.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.disclosesecrecy.service.CaseCriticalviolationChangeService;
import com.cdthgk.disclosesecrecy.vo.CaseCriticalviolationChange;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;
public class CaseCriticalviolationChangeServiceImpl extends BmpServiceImpl<CaseCriticalviolationChange, String> implements
CaseCriticalviolationChangeService {

	/**
	 * 泄密事件变更记录  查询
	 *
	 * @param psm   分页对象
	 * @param discloseSecrecyChange   泄密事件变更
	 * @return
	 */
	@Override
	public List<CaseCriticalviolationChange> queryCaseCriticalviolationChangelist(
			PageSortModel<CaseCriticalviolationChange> psm,
			CaseCriticalviolationChange caseCriticalviolationChange,User user) {
			List<CaseCriticalviolationChange> list = new ArrayList<CaseCriticalviolationChange>();//如果 分页对象为空
			if (psm == null) {
				return list;
			}
			Map<String, Object> params = new HashMap<String, Object>();
			StringBuilder hql = new StringBuilder("from CaseCriticalviolationChange dsc where 1=1 ");
			hql.append(" and dsc.createPerson.organ.organId = :organId");
			params.put("organId", user.getOrgan().getOrganId());
			if(caseCriticalviolationChange!=null) {

				if(caseCriticalviolationChange.getCaseCriticalviolation()!=null ) {

					if(caseCriticalviolationChange.getCaseCriticalviolation().getName()!=null) {
						hql.append(" and dsc.caseCriticalviolation.name like :name ");
						params.put("name", "%" + caseCriticalviolationChange.getCaseCriticalviolation().getName()+ "%");
					}
				}
				//原密级
				if(caseCriticalviolationChange.getBeforeLevel()!=null) {
					hql.append(" and dsc.beforeLevel = :beforeLevel ");
					params.put("beforeLevel", caseCriticalviolationChange.getBeforeLevel());
				}
				//变更后密级
				if(caseCriticalviolationChange.getAfterLevel()!=null) {
					hql.append(" and dsc.afterLevel = :afterLevel ");
					params.put("afterLevel", caseCriticalviolationChange.getAfterLevel());
				}
				//变更时间
				if(caseCriticalviolationChange.getChangeDate()!=null) {
					hql.append(" and dsc.changeDate = :changeDate ");
					params.put("changeDate", caseCriticalviolationChange.getChangeDate());
				}
				//保密期限变更
				if(caseCriticalviolationChange.getChangeTimeState()!=null) {
					hql.append(" and dsc.changeTimeState = :changeTimeState ");
					params.put("changeTimeState", caseCriticalviolationChange.getChangeTimeState());
				}
			}
			hql.append(" order by dsc.createDate desc");
			list = this.findList(hql.toString(), params);
			return list;
	}

	@Override
	public List<CaseCriticalviolationChange> queryCaseCriticalviolationChangelist(
			PageSortModel psm,
			CaseCriticalviolationChange caseCriticalviolationChange,
			User currentUser, String districtCode,String includeChild) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("from CaseCriticalviolationChange dsc where 1=1 ");
		hql.append(" and dsc.createPerson.organ.organId in (");
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
		if(caseCriticalviolationChange!=null) {

			if(caseCriticalviolationChange.getCaseCriticalviolation()!=null ) {

				if(caseCriticalviolationChange.getCaseCriticalviolation().getName()!=null) {
					hql.append(" and dsc.caseCriticalviolation.name like :name ");
					params.put("name", "%" + caseCriticalviolationChange.getCaseCriticalviolation().getName()+ "%");
				}
			}
			//原密级
			if(caseCriticalviolationChange.getBeforeLevel()!=null) {
				hql.append(" and dsc.beforeLevel = :beforeLevel ");
				params.put("beforeLevel", caseCriticalviolationChange.getBeforeLevel());
			}
			//变更后密级
			if(caseCriticalviolationChange.getAfterLevel()!=null) {
				hql.append(" and dsc.afterLevel = :afterLevel ");
				params.put("afterLevel", caseCriticalviolationChange.getAfterLevel());
			}
			//变更时间
			if(caseCriticalviolationChange.getChangeDate()!=null) {
				hql.append(" and dsc.changeDate = :changeDate ");
				params.put("changeDate", caseCriticalviolationChange.getChangeDate());
			}
			//保密期限变更
			if(caseCriticalviolationChange.getChangeTimeState()!=null) {
				hql.append(" and dsc.changeTimeState = :changeTimeState ");
				params.put("changeTimeState", caseCriticalviolationChange.getChangeTimeState());
			}
		}
		hql.append(" order by dsc.createDate desc");
		List<CaseCriticalviolationChange> list = this.findList(hql.toString(), params);
		return list;
	}



}