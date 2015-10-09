package com.cdthgk.disclosesecrecy.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.common.lang.StringUtils;
import com.cdthgk.disclosesecrecy.service.CaseCriticalviolationService;
import com.cdthgk.disclosesecrecy.vo.CaseCriticalviolation;
import com.cdthgk.platform.base.service.GenericServiceTemplate;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

public class CaseCriticalviolationServiceImpl extends
GenericServiceTemplate<CaseCriticalviolation, String> implements
CaseCriticalviolationService {


	@Override
	public List<CaseCriticalviolation> listForEc(PageSortModel<CaseCriticalviolation> psm,
			CaseCriticalviolation caseCriticalviolation,User user,boolean baomijuOrbendanwei) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder(
				"from CaseCriticalviolation sl where 1=1 and sl.status!=1 ");
		if (caseCriticalviolation != null) {

			if(caseCriticalviolation.getSecrecyLevel() != null){
				hql.append(" and sl.secrecyLevel = :secrecyLevel ");
				params.put("secrecyLevel", caseCriticalviolation.getSecrecyLevel());
			}
			if(caseCriticalviolation.getDealResult() != null){
				hql.append(" and sl.dealResult = :dealResult ");
				params.put("dealResult", caseCriticalviolation.getDealResult());
			}
			if(StringUtils.isNotEmpty(caseCriticalviolation.getName())){
				hql.append(" and sl.name like :name ");
				params.put("name", "%" + caseCriticalviolation.getName() + "%");
			}
		}
		if (!baomijuOrbendanwei) {
			// 当前单位涉密人员
			hql.append(" and sl.createOrgan.organId = :organId ");
			params.put("organId", user.getOrgan().getOrganId());
		}
		else {

			//保密局
			hql.append(" and sl.createOrgan.organId in (");
			hql.append(" select o.organId from Organ as o where o.district.districtCode in ");
			hql.append(" (select district.districtCode from District as district where district.layer like :layer)) ");
			params.put("layer",  user.getOrgan().getDistrict().getLayer()+ "%");
		/*	hql.append(" and sl.createOrgan.organId in (select o.organId from Organ as o where o.layer like :layer) ");
			params.put("layer", user.getOrgan().getLayer()+"%");*/
		}
		return findList(hql.toString(), params, psm);
	}
	/**
	 * 通过泄密事件id  查询该泄密案件的id  和哪些表 有关联
	 * 1.泄密案件密级变更表
	 * 2.泄密案件密级解除表
	 *
	 * @param partId  泄密案件id
	 * @return
	 */
	@Override
	public int getCaseCriticalviolationIdRelationship(
			String caseCriticalviolationId) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if(caseCriticalviolationId==null || caseCriticalviolationId.equals("")) {
			return 0;
		}

		StringBuffer sb = new StringBuffer();
		Map<String ,Object> params = new HashMap<String, Object>();


		//1.密级变更表
		sb = new StringBuffer();
		params = new HashMap<String, Object>();
		sb.append("select p.caseCriticalviolation_change_id from bm_case_criticalviolation_change p where p.criticalviolationcase_id = :caseCriticalviolationId");
		params.put("caseCriticalviolationId", caseCriticalviolationId);
		list = this.getPersistProxy().getJdbcPersistence().findList(sb.toString(), params);
		if(list!=null && list.size()>0) {
			return 1;
		}

		//2.密级解除表
		sb = new StringBuffer();
		params = new HashMap<String, Object>();
		sb.append("select p.caseCriticalviolation_clear_id from bm_case_criticalviolation_clear p where p.criticalviolationcase_id = :caseCriticalviolationId ");
		params.put("caseCriticalviolationId", caseCriticalviolationId);
		list = this.getPersistProxy().getJdbcPersistence().findList(sb.toString(), params);
		if(list!=null && list.size()>0) {
			return 2;
		}

		return 0;
	}
	@Override
	public List<CaseCriticalviolation> listForAll(CaseCriticalviolation caseCriticalviolation,
			User user,String districtCode ,String includeChild) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder(
				"from CaseCriticalviolation sl where 1=1 and sl.status!=1");
		if (districtCode==null||districtCode.equals("")) {

			// 当前单位涉密人员
			hql.append(" and sl.createOrgan.organId = :organId");
			params.put("organId", user.getOrgan().getOrganId());
		}
		else {
			hql.append(" and sl.createOrgan.organId in (");
			if (includeChild.equals("1")) {
				//hql语句--保密局
				int layer=this.getPersistProxy().getJdbcPersistence().findForInt(
						"select layer from sys_district where district_code ='"+districtCode+"'", params);
				hql.append("select o.organId from Organ as o where o.district.districtCode in ");
				hql.append("(select district.districtCode from District as district where district.layer like :layer ))");
				params.put("layer",  layer+"%");
			}
			if (includeChild.equals("0")) {
				//hql语句--直辖单位
				hql.append("select o.organId from Organ as o where o.district.districtCode=:districtCode)");
				params.put("districtCode",  districtCode);
			}
		}
		// 当前单位涉密人员
		if (caseCriticalviolation != null) {

			if(caseCriticalviolation.getSecrecyLevel() != null){
				hql.append(" and sl.secrecyLevel = :secrecyLevel ");
				params.put("secrecyLevel", caseCriticalviolation.getSecrecyLevel());
			}
			if(caseCriticalviolation.getDealResult() != null){
				hql.append(" and sl.dealResult = :dealResult ");
				params.put("dealResult", caseCriticalviolation.getDealResult());
			}
			if(StringUtils.isNotEmpty(caseCriticalviolation.getName())){
				hql.append(" and sl.name like :name ");
				params.put("name", "%" + caseCriticalviolation.getName() + "%");
			}
		}
		return findList(hql.toString(), params);
	}
	@Override
	public List<CaseCriticalviolation> listForSelect(PageSortModel<CaseCriticalviolation> psm,
			CaseCriticalviolation caseCriticalviolation, String districtCode ,String includeChild) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder(
				"from CaseCriticalviolation sl where 1=1 and sl.status!=1");
		// 当前单位涉密人员
		if (caseCriticalviolation != null) {
			if(caseCriticalviolation.getSecrecyLevel() != null){
				hql.append(" and sl.secrecyLevel = :secrecyLevel");
				params.put("secrecyLevel", caseCriticalviolation.getSecrecyLevel());
			}
			if(caseCriticalviolation.getDealResult() != null){
				hql.append(" and sl.dealResult = :dealResult");
				params.put("dealResult", caseCriticalviolation.getDealResult());
			}
			if(StringUtils.isNotEmpty(caseCriticalviolation.getName())){
				hql.append(" and sl.name like :name");
				params.put("name", "%" + caseCriticalviolation.getName() + "%");
			}
			if(null!=caseCriticalviolation.getCreateOrgan()&&StringUtils.isNotEmpty(caseCriticalviolation.getCreateOrgan().getOrganId())){
				hql.append(" and sl.createOrgan.organId=:organId");
				params.put("organId",  caseCriticalviolation.getCreateOrgan().getOrganId());
			}
		}

		//hql语句
		hql.append(" and sl.createOrgan.organId in (");
		int layer=this.getPersistProxy().getJdbcPersistence().findForInt(
				"select layer from sys_district where district_code ='"+districtCode+"'", params);
		if (includeChild.equals("1")) {
			hql.append("select o.organId from Organ as o where o.district.districtCode in ");
			hql.append("(select district.districtCode from District as district where district.layer like :layer ))");
			params.put("layer",  layer+"%");
		}
		if (includeChild.equals("0")) {
			hql.append("select o.organId from Organ as o where o.district.districtCode =:districtCode) ");
			params.put("districtCode",  districtCode);
		}
		return findList(hql.toString(), params, psm);
	}


}