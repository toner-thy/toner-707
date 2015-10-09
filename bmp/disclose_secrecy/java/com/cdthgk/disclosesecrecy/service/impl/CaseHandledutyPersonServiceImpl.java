package com.cdthgk.disclosesecrecy.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.common.lang.LangUtils;
import com.cdthgk.disclosesecrecy.service.CaseHandledutyPersonService;
import com.cdthgk.disclosesecrecy.vo.CaseCriticalviolation;
import com.cdthgk.disclosesecrecy.vo.CaseHandledutyPerson;
import com.cdthgk.disclosesecrecy.vo.DiscloseSecrecy;
import com.cdthgk.platform.organization.core.OrganizationContext;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;
public class CaseHandledutyPersonServiceImpl extends BmpServiceImpl<CaseHandledutyPerson, String> implements
CaseHandledutyPersonService{
	/**
	 * 泄密案件处理责任人 分页查询
	 * @param psm   分页对象
	 * @param caseHandledutyPerson 泄密案件处理责任人
	 * @return
	 */
	@Override
	public List<CaseHandledutyPerson> queryCaseHandledutyPersonList(
			PageSortModel<CaseHandledutyPerson> psm,
			DiscloseSecrecy discloseSecrecy,CaseCriticalviolation caseCriticalviolation) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("from CaseHandledutyPerson chp where 1=1  ");
		if (null!=discloseSecrecy&&null!=discloseSecrecy.getDisclosesecrecycaseId()) {
			hql.append("and chp.disclosesecrecycase.disclosesecrecycaseId='"+discloseSecrecy.getDisclosesecrecycaseId()+"'");
		}
		if(null!=caseCriticalviolation&&null!=caseCriticalviolation.getCaseCriticalviolationId())
		{
			hql.append("and chp.caseCriticalviolation.caseCriticalviolationId='"+caseCriticalviolation.getCaseCriticalviolationId()+"'");

		}
		hql.append(" order by chp.createTime desc");
		return findList(hql.toString(), params, psm);
	}




	@Override
	public CaseHandledutyPerson saveCaseHandledutyPerson(CaseHandledutyPerson e,User currentUser) {
		//当前单位下输入的人员如果有则保存，如果没有则通过输入的名字新增人员。
		//姓 名
		UserInfo userInfo = new UserInfo();
		if (LangUtils.isEmpty(e.getUserInfo().getUserInfoId())) {
			//FIXME 先检查是否存在该人员(注意这里返回的是集合需要修改)
			List<UserInfo> userInfoList = (List<UserInfo>) OrganizationContext.getInstance().getMemberService()
					.getByName(e.getUserInfo().getName());
			if(userInfoList != null && userInfoList.size() > 0) {
				userInfo = userInfoList.get(0);
				userInfo.setDuty(e.getUserInfo().getDuty());
			}else {
				//当前单位不存在该人员，新增系统人员
				userInfo.setDuty(e.getUserInfo().getDuty());
				userInfo.setName(e.getUserInfo().getName());
				userInfo.setOrgan(currentUser.getOrgan());
				userInfo.setDepartment(currentUser.getOrgan().getDepartment());
				userInfo.setCreatePerson(currentUser);
				userInfo.setModifyPerson(currentUser);
				userInfo.save();
			}
			e.setUserInfo(userInfo);
		}
		return this.save(e);
	}




	@Override
	public List<Map<String, Object>> countCaseHandledutyPerson(String organId,
			String handle_type, String groupBy,String className,String distrust_code,boolean zhixia) {

		Integer total = getCaseHandledutyPersonTotal(organId,className,handle_type,distrust_code,zhixia);//得到本单位  要害部门总数
		Map<String ,Object> params = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT A.option_text, ROUND(IFNULL(B.fcount,0),0)AS fcount");
		sb.append(" FROM ");
		sb.append(" ( ");//A表  查询基础字典表，查询出保密要害部门的密级
		sb.append("  SELECT option_text,option_value ");
		sb.append("  FROM sys_dictionary_option ");
		sb.append("  WHERE status=1 AND field_id=(SELECT field_id FROM sys_dictionary_field ");
		sb.append("  WHERE table_id=(SELECT table_id FROM sys_dictionary_table  ");
		if(groupBy.equals("political_landscape"))
		{
			sb.append(" WHERE table_code='person')  AND field_code='polity') ");
		}
		if(groupBy.equals("manage_level"))
		{
			sb.append(" WHERE table_code='bmp')  AND field_code='organ_admin_level') ");
		}

		sb.append(" )AS A ");
		sb.append(" LEFT JOIN");
		sb.append(" ( SELECT handledutyperson.");//B表   按照密级统计泄密事件的个数
		sb.append(groupBy);//B表   按照密级统计泄密事件的个数
		sb.append(", SUM(handledutyperson.fcount) AS fcount ");
		sb.append("FROM (SELECT handledutyperson_id,");
		sb.append(groupBy);
		int layer=0;
		if (null!=distrust_code) {
			layer=this.getPersistProxy().getJdbcPersistence().findForInt("select layer from sys_district where district_code ='"+distrust_code+"'", params);
		}
		if (className.equals(DiscloseSecrecy.class.getName())) {
			sb.append(", 1 AS fcount FROM bm_case_handledutyperson WHERE disclosesecrecycase_id in");//orgid过滤
			if (null!=distrust_code) {

				sb.append("( SELECT disclosesecrecycase_id FROM bm_case_disclosesecrecy WHERE create_organ_id in(");//orgid过滤
				if (zhixia) {
					sb.append(" select ORGAN_ID from sys_organization where DISTRICT_CODE in " +
							"(select district_code from sys_district where layer='"+layer);
					sb.append("') ) AND secrecy_status=0)");

				}
				else {
					sb.append(" select ORGAN_ID from sys_organization where DISTRICT_CODE in " +
							"(select district_code from sys_district where layer like '"+layer);
					sb.append("%') ) AND secrecy_status=0)");

				}

			}
			else if(null!=organId) {

				sb.append("(SELECT disclosesecrecycase_id FROM bm_case_disclosesecrecy WHERE create_organ_id = :organId AND secrecy_status=0)");//orgid过滤
			}
		}

		if (className.equals(CaseCriticalviolation.class.getName())) {

			sb.append(", 1 AS fcount FROM bm_case_handledutyperson WHERE criticalviolationcase_id in");//orgid过滤
			if (null!=distrust_code) {
				if (zhixia) {
					sb.append("( SELECT criticalviolationcase_id FROM bm_case_criticalviolation WHERE create_organ_id in(");//orgid过滤
					sb.append(" select ORGAN_ID from sys_organization where DISTRICT_CODE in " +
							"(select district_code from sys_district where layer ='"+layer);
					sb.append("') ) AND secrecy_status=0)");

				}else {

					sb.append("( SELECT criticalviolationcase_id FROM bm_case_criticalviolation WHERE create_organ_id in(");//orgid过滤
					sb.append(" select ORGAN_ID from sys_organization where DISTRICT_CODE in " +
							"(select district_code from sys_district where layer like '"+layer);
					sb.append("%') ) AND secrecy_status=0)");
				}

			}
			else if(null!=organId) {

				sb.append("(SELECT criticalviolationcase_id FROM bm_case_criticalviolation WHERE create_organ_id = :organId AND secrecy_status=0)");//orgid过滤
			}
		}
		if (null!=handle_type) {
			sb.append(" and handle_type="+Integer.parseInt(handle_type));
		}
		sb.append(")AS handledutyperson GROUP BY ");
		sb.append(groupBy);
		sb.append(" )AS B");
		sb.append(" ON A.option_value = B.");
		sb.append(groupBy);
		params.put("organId", organId);
		List<Map<String, Object>> list = this.getPersistProxy().getJdbcPersistence().findList(sb.toString(), params);

		//设置合计列
		Map<String, Object> grp = new  HashMap<String, Object>();
		grp.put("option_text", "合计");
		grp.put("fcount", total);
		list.add(grp);

		return list;
	}
	public Integer getCaseHandledutyPersonTotal(String organId,String classname,String handle_type,String distrust_code,boolean zhixia) {
		Integer i = new Integer(0);
		Map<String ,Object> params = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT SUM(handledutyperson.fcount) AS total ");
		sb.append(" FROM ( ");
		int layer=0;
		if (null!=distrust_code) {
			layer=this.getPersistProxy().getJdbcPersistence().findForInt("select layer from sys_district where district_code ='"+distrust_code+"'", params);
		}
		if (classname.equals(DiscloseSecrecy.class.getName())) {
			sb.append(" SELECT handledutyperson_id, 1 AS fcount FROM bm_case_handledutyperson WHERE  disclosesecrecycase_id in");

			if (null!=distrust_code) {
				sb.append(" (SELECT disclosesecrecycase_id FROM bm_case_disclosesecrecy WHERE create_organ_id in(");//orgid过滤
				if (zhixia) {
					sb.append(" select ORGAN_ID from sys_organization where DISTRICT_CODE in " +
							"(select district_code from sys_district where layer = '"+layer+"')) AND secrecy_status=0 )");

				}else {

					sb.append(" select ORGAN_ID from sys_organization where DISTRICT_CODE in " +
							"(select district_code from sys_district where layer like '"+layer+"%')) AND secrecy_status=0 )");
				}
			}
			if(null!=organId)
			{
				sb.append("(SELECT disclosesecrecycase_id FROM bm_case_disclosesecrecy WHERE create_organ_id = :organId AND secrecy_status=0)");
			}
		}
		else if (classname.equals(CaseCriticalviolation.class.getName())) {
			sb.append("  SELECT handledutyperson_id,1 AS fcount FROM bm_case_handledutyperson WHERE criticalviolationcase_id in");
			if (null!=distrust_code) {

				sb.append(" (SELECT criticalviolationcase_id FROM bm_case_criticalviolation WHERE create_organ_id in(");//orgid过滤
				if (zhixia) {
					sb.append(" select ORGAN_ID from sys_organization where DISTRICT_CODE in " +
							"(select district_code from sys_district where layer = '"+layer+"')) AND secrecy_status=0 )");

				}else {

					sb.append(" select ORGAN_ID from sys_organization where DISTRICT_CODE in " +
							"(select district_code from sys_district where layer like '"+layer+"%')) AND secrecy_status=0 )");
				}
			}
			if(null!=organId)
			{
				sb.append("(SELECT criticalviolationcase_id FROM bm_case_criticalviolation WHERE create_organ_id = :organId AND secrecy_status=0)");//orgid过滤
			}
		}
		if (null!=handle_type) {
			sb.append(" and handle_type="+Integer.parseInt(handle_type));
		}
		sb.append(" )AS handledutyperson");
		params.put("organId", organId);

		Map<String ,Object> map = this.getPersistProxy().getJdbcPersistence().find(sb.toString(), params);
		Object o = map.get("total");
		if(o!=null) {
			i = new Integer(Integer.parseInt(o.toString()));
		}
		return i;
	}

	@Override
	public List<Map<String, Object>> getHandleType() {
		String sql = "SELECT option_text,option_value FROM sys_dictionary_option   " +
				"WHERE status=:status AND field_id=(SELECT field_id FROM sys_dictionary_field   " +
				"WHERE table_id=(SELECT table_id FROM sys_dictionary_table WHERE table_code=:tableCode) " +
				" AND field_code=:field_code) ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("status", "1");
		params.put("field_code", "bmp_handleType");
		params.put("tableCode", "bmp");
		return this.getPersistProxy().getJdbcPersistence().findList(sql, params);
	}
	@Override
	public List<Map<String, Object>> getPolitical() {
		String sql = "SELECT option_text,option_value FROM sys_dictionary_option   " +
				"WHERE status=:status AND field_id=(SELECT field_id FROM sys_dictionary_field   " +
				"WHERE table_id=(SELECT table_id FROM sys_dictionary_table WHERE table_code=:tableCode) " +
				" AND field_code=:field_code) ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("status", "1");
		params.put("field_code", "polity");
		params.put("tableCode", "person");
		return this.getPersistProxy().getJdbcPersistence().findList(sql, params);
	}




	@Override
	public CaseHandledutyPerson updateCaseHandledutyPerson(
			CaseHandledutyPerson e, User currentUser) {
		//当前单位下输入的人员如果有则保存，如果没有则通过输入的名字新增人员。
				//姓 名
				UserInfo userInfo = new UserInfo();
				if (LangUtils.isEmpty(e.getUserInfo().getUserInfoId())) {
					//FIXME 先检查是否存在该人员(注意这里返回的是集合需要修改)
					List<UserInfo> userInfoList = (List<UserInfo>) OrganizationContext.getInstance().getMemberService()
							.getByName(e.getUserInfo().getName());
					if(userInfoList != null && userInfoList.size() > 0) {
						userInfo = userInfoList.get(0);
						userInfo.setDuty(e.getUserInfo().getDuty());
					}else {
						//当前单位不存在该人员，新增系统人员
						userInfo.setDuty(e.getUserInfo().getDuty());
						userInfo.setName(e.getUserInfo().getName());
						userInfo.setOrgan(currentUser.getOrgan());
						userInfo.setDepartment(currentUser.getOrgan().getDepartment());
						userInfo.setCreatePerson(currentUser);
						userInfo.setModifyPerson(currentUser);
						userInfo.save();
					}
					e.setUserInfo(userInfo);
				}
				return this.update(e);
	}
}