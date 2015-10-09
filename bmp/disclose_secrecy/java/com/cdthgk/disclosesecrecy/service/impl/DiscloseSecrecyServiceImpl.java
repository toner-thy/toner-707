package com.cdthgk.disclosesecrecy.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.common.lang.StringUtils;
import com.cdthgk.disclosesecrecy.dto.DiscloseSecrecyStatDto;
import com.cdthgk.disclosesecrecy.service.DiscloseSecrecyService;
import com.cdthgk.disclosesecrecy.vo.CaseCriticalviolation;
import com.cdthgk.disclosesecrecy.vo.DiscloseSecrecy;
import com.cdthgk.platform.base.service.GenericServiceTemplate;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

/**
 * <p>
 * 保密范围Serviceimpl接口实现类
 * </p>
 * <p>
 * 王欢 2009-10-28 12:34:56
 * </p>
 * <blockquote>
 * <h4>历史修改记录</h4>
 * <ul>
 * <li>
 * 修改人wangpb 2013 1 7 12:00:02 修改注释格式
 * </ul>
 * </blockquote>
 *
 * @author 王欢
 * @since 1.0
 * @version 1.0
 */
public class DiscloseSecrecyServiceImpl extends
GenericServiceTemplate<DiscloseSecrecy, String> implements
DiscloseSecrecyService {


	@Override
	public List<DiscloseSecrecy> listForEc(PageSortModel<DiscloseSecrecy> psm,
			DiscloseSecrecy discloseSecrecy,User user,boolean baomijuObendanwei) {

		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder(
				"from DiscloseSecrecy sl where 1=1 and sl.status!=1");
		// 当前单位涉密人员
		if (discloseSecrecy != null) {
			if(discloseSecrecy.getSecrecyLevel() != null){
				hql.append(" and sl.secrecyLevel = :secrecyLevel");
				params.put("secrecyLevel", discloseSecrecy.getSecrecyLevel());
			}
			if(discloseSecrecy.getDealResult() != null){
				hql.append(" and sl.dealResult = :dealResult");
				params.put("dealResult", discloseSecrecy.getDealResult());
			}
			if(StringUtils.isNotEmpty(discloseSecrecy.getName())){
				hql.append(" and sl.name like :name");
				params.put("name", "%" + discloseSecrecy.getName() + "%");
			}
		}
		if(!baomijuObendanwei){
			hql.append(" and sl.createOrgan.organId = :organId");
			params.put("organId", user.getOrgan().getOrganId());

		}
		else {
			/*sql语句查询的。
			 * hql.append(" and sl.createOrgan.organId in (");
			int layer=this.getPersistProxy().getJdbcPersistence().findForInt("select layer from sys_district where district_code ='"+user.getOrgan().getDistrict().getDistrictCode()+"'", params);
			String sql=" select ORGAN_ID from sys_organization where DISTRICT_CODE in " +
			"(select district_code from sys_district where layer like '"+layer+"%')";
			List<Map<String, Object>> mapList = this.getPersistProxy().getJdbcPersistence().findList(sql, params);
			if (mapList.size()>0) {
				for (int i = 0; i < mapList.size(); i++) {
					if (i!=mapList.size()-1) {

						hql.append("'"+mapList.get(i).get("ORGAN_ID")+"',");
					}
					else{
						hql.append("'"+mapList.get(i).get("ORGAN_ID")+"')");
					}
				}
			}

			if (discloseSecrecy != null) {
				if(discloseSecrecy.getSecrecyLevel() != null){
					hql.append(" and sl.secrecyLevel = :secrecyLevel");
					params.put("secrecyLevel", discloseSecrecy.getSecrecyLevel());
				}
				if(discloseSecrecy.getDealResult() != null){
					hql.append(" and sl.dealResult = :dealResult");
					params.put("dealResult", discloseSecrecy.getDealResult());
				}
				if(StringUtils.isNotEmpty(discloseSecrecy.getName())){
					hql.append(" and sl.name like :name");
					params.put("name", "%" + discloseSecrecy.getName() + "%");
				}*/
			//hql语句
			hql.append(" and sl.createOrgan.organId in (");
			hql.append("select o.organId from Organ as o where o.district.districtCode in ");
			hql.append("(select district.districtCode from District as district where district.layer like :layer))");
			params.put("layer",  user.getOrgan().getDistrict().getLayer()+ "%");

		}
		return findList(hql.toString(), params, psm);
	}
	@Override
	public List<DiscloseSecrecy> listForSelect(
			PageSortModel<DiscloseSecrecy> psm,
			DiscloseSecrecy discloseSecrecy, String districtCode,String includeChild) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder(
				"from DiscloseSecrecy sl where 1=1 and sl.status!=1");
		// 当前单位涉密人员
		if (discloseSecrecy != null) {
			if(discloseSecrecy.getSecrecyLevel() != null){
				hql.append(" and sl.secrecyLevel = :secrecyLevel");
				params.put("secrecyLevel", discloseSecrecy.getSecrecyLevel());
			}
			if(discloseSecrecy.getDealResult() != null){
				hql.append(" and sl.dealResult = :dealResult");
				params.put("dealResult", discloseSecrecy.getDealResult());
			}
			if(StringUtils.isNotEmpty(discloseSecrecy.getName())){
				hql.append(" and sl.name like :name");
				params.put("name", "%" + discloseSecrecy.getName() + "%");
			}
			if(null!=discloseSecrecy.getCreateOrgan()&&StringUtils.isNotEmpty(discloseSecrecy.getCreateOrgan().getOrganId())){
				hql.append(" and sl.createOrgan.organId=:organId");
				params.put("organId",  discloseSecrecy.getCreateOrgan().getOrganId());
			}
		}
		if (includeChild.equals("1")) {
			//hql语句--保密局
			int layer=this.getPersistProxy().getJdbcPersistence().findForInt(
					"select layer from sys_district where district_code ='"+districtCode+"'", params);
			hql.append(" and sl.createOrgan.organId in (");
			hql.append("select o.organId from Organ as o where o.district.districtCode in ");
			hql.append("(select district.districtCode from District as district where district.layer like :layer ))");
			params.put("layer",  layer+"%");
		}
		if (includeChild.equals("0")) {
			//hql语句--直辖单位
			hql.append(" and sl.createOrgan.organId in (");
			hql.append("select o.organId from Organ as o where o.district.districtCode=:districtCode)");
			params.put("districtCode",  districtCode);
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
	public int getDisclosesecrecycaseIdRelationship(String disclosesecrecycaseId){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if(disclosesecrecycaseId==null || disclosesecrecycaseId.equals("")) {
			return 0;
		}

		StringBuffer sb = new StringBuffer();
		Map<String ,Object> params = new HashMap<String, Object>();


		//1.密级变更表
		sb = new StringBuffer();
		params = new HashMap<String, Object>();
		sb.append("select p.case_disclosesecrecy_change_id from bm_case_disclosesecrecy_change p where p.disclosesecrecycase_id = :disclosesecrecycaseId");
		params.put("disclosesecrecycaseId", disclosesecrecycaseId);
		list = this.getPersistProxy().getJdbcPersistence().findList(sb.toString(), params);
		if(list!=null && list.size()>0) {
			return 1;
		}

		//2.密级解除表
		sb = new StringBuffer();
		params = new HashMap<String, Object>();
		sb.append("select p.case_disclosesecrecy_clear_id from bm_case_disclosesecrecy_clear p where p.disclosesecrecycase_id = :disclosesecrecycaseId ");
		params.put("disclosesecrecycaseId", disclosesecrecycaseId);
		list = this.getPersistProxy().getJdbcPersistence().findList(sb.toString(), params);
		if(list!=null && list.size()>0) {
			return 2;
		}

		return 0;
	}
	@Override
	public List<DiscloseSecrecy> listForAll(DiscloseSecrecy discloseSecrecy,
			User user,String districtCode,String includeChild) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder(
				"from DiscloseSecrecy sl where 1=1 and sl.status!=1");
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
		if (discloseSecrecy != null) {
			if(discloseSecrecy.getSecrecyLevel() != null){
				hql.append(" and sl.secrecyLevel = :secrecyLevel");
				params.put("secrecyLevel", discloseSecrecy.getSecrecyLevel());
			}
			if(discloseSecrecy.getDealResult() != null){
				hql.append(" and sl.dealResult = :dealResult");
				params.put("dealResult", discloseSecrecy.getDealResult());
			}
			if(StringUtils.isNotEmpty(discloseSecrecy.getName())){
				hql.append(" and sl.name like :name");
				params.put("name", "%" + discloseSecrecy.getName() + "%");
			}
		}
		return findList(hql.toString(), params);
	}
	@Override
	public List<Map<String, Object>> countDiscloseSecrecyBySecrecy_Level(
			String organId,String dealResult,String className) {
		Integer total = getDiscloseSecrecyTotal(organId,className,dealResult,null,false);

		Map<String ,Object> params = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();


		sb.append(" SELECT A.option_text, ROUND(IFNULL(B.fcount,0),0)AS fcount");


		sb.append(" FROM ");
		sb.append(" ( ");//A表  查询基础字典表，查询出保密要害部门的密级
		sb.append("  SELECT option_text,option_value ");
		sb.append("  FROM sys_dictionary_option ");
		sb.append("  WHERE status=1 AND field_id=(SELECT field_id FROM sys_dictionary_field ");
		sb.append("  WHERE table_id=(SELECT table_id FROM sys_dictionary_table WHERE table_code='bmp') ");
		sb.append("  AND field_code='secrecy_level_thing') ");
		sb.append(" )AS A ");
		sb.append(" LEFT JOIN");
		sb.append(" ( ");//B表   按照密级统计泄密事件的个数
		sb.append("  SELECT disclosesecrecy.secrecy_level, SUM(disclosesecrecy.fcount) AS fcount ");

		sb.append("  FROM (SELECT disclosesecrecycase_id,secrecy_level, 1 AS fcount FROM bm_case_disclosesecrecy WHERE create_organ_id = :organId AND secrecy_status=0");//orgid过滤
		if (dealResult!=null) {
			sb.append("  and deal_result="+Integer.parseInt(dealResult));
		}
		sb.append(")AS disclosesecrecy");
		sb.append("  GROUP BY secrecy_level");
		sb.append(" )AS B");
		sb.append(" ON A.option_value = B.secrecy_level ");


		params.put("organId", organId);
		List<Map<String, Object>> list = this.getPersistProxy().getJdbcPersistence().findList(sb.toString(), params);

		//设置合计列
		Map<String, Object> grp = new  HashMap<String, Object>();
		grp.put("option_text", "合计");
		if(total==0) {
			grp.put("total", "0.00%");
		}else {
			grp.put("total", "100.00%");
		}
		grp.put("fcount", total);
		list.add(grp);

		return list;
	}

	/**
	 * 返回 （本单位,保密局，直辖单位）的泄密事件总个数
	 * 1.通过单位id过滤    2.通过密级是否启用过滤
	 *
	 * @param organId
	 * @return
	 */
	@Override
	public Integer getDiscloseSecrecyTotal(String organId,String classname,String dealResult,String district,boolean zhixiadanwei) {
		Integer i = new Integer(0);
		Map<String ,Object> params = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		if (classname.equals(DiscloseSecrecy.class.getName())) {
			sb.append(" SELECT SUM(disclosesecrecycase.fcount) AS total ");
			sb.append(" FROM ( ");
			if (null!=district) {
				int layer=this.getPersistProxy().getJdbcPersistence().findForInt("select layer from sys_district where district_code ='"+district+"'", params);

				sb.append("  SELECT disclosesecrecycase_id,secrecy_level, 1 AS fcount FROM bm_case_disclosesecrecy WHERE create_organ_id in(");//orgid过滤
				if (zhixiadanwei) {
					sb.append(" select ORGAN_ID from sys_organization where DISTRICT_CODE in " +
							"(select district_code from sys_district where layer ='"+layer+"')) AND secrecy_status=0 ");

				}else {

					sb.append(" select ORGAN_ID from sys_organization where DISTRICT_CODE in " +
							"(select district_code from sys_district where layer like '"+layer+"%')) AND secrecy_status=0 ");
				}
			}
			if(null!=organId)
			{
				sb.append("  SELECT disclosesecrecycase_id,secrecy_level, 1 AS fcount FROM bm_case_disclosesecrecy WHERE create_organ_id = :organId AND secrecy_status=0 ");
			}
			if (dealResult!=null) {

				sb.append("  and deal_result="+Integer.parseInt(dealResult));
			}
			sb.append("  AND secrecy_level IN (SELECT option_value FROM sys_dictionary_option  ");
			sb.append("  WHERE status=1 AND field_id=(SELECT field_id FROM sys_dictionary_field  ");
			sb.append("  WHERE table_id=(SELECT table_id FROM sys_dictionary_table WHERE table_code='bmp') ");
			sb.append("  AND field_code='secrecy_level_thing')) ");
			sb.append(" )AS disclosesecrecycase");
		}
		else if (classname.equals(CaseCriticalviolation.class.getName())) {

			sb.append(" SELECT SUM(criticalviolationcase.fcount) AS total ");
			sb.append(" FROM ( ");
			if (null!=district) {
				int layer=this.getPersistProxy().getJdbcPersistence().findForInt("select layer from sys_district where district_code ='"+district+"'", params);

				sb.append("  SELECT criticalviolationcase_id,secrecy_level, 1 AS fcount FROM bm_case_criticalviolation WHERE   secrecy_status=0 AND create_organ_id in(");//orgid过滤
				if (zhixiadanwei) {
					sb.append(" select ORGAN_ID from sys_organization where DISTRICT_CODE in " +
							"(select district_code from sys_district where layer = '"+layer+"')) ");

				}
				else {

					sb.append(" select ORGAN_ID from sys_organization where DISTRICT_CODE in " +
							"(select district_code from sys_district where layer like '"+layer+"%')) ");
				}
			}
			if(null!=organId)
			{
				sb.append("  SELECT criticalviolationcase_id,secrecy_level, 1 AS fcount FROM bm_case_criticalviolation WHERE create_organ_id = :organId AND secrecy_status=0 ");
			}
			if (dealResult!=null) {

				sb.append("  and deal_result="+Integer.parseInt(dealResult));
			}

			sb.append("  AND secrecy_level IN (SELECT option_value FROM sys_dictionary_option  ");
			sb.append("  WHERE status=1 AND field_id=(SELECT field_id FROM sys_dictionary_field  ");
			sb.append("  WHERE table_id=(SELECT table_id FROM sys_dictionary_table WHERE table_code='bmp') ");
			sb.append("  AND field_code='secrecy_level_thing')) ");
			sb.append(" )AS criticalviolationcase");
		}
		params.put("organId", organId);

		Map<String ,Object> map = this.getPersistProxy().getJdbcPersistence().find(sb.toString(), params);
		Object o = map.get("total");
		if(o!=null) {
			i = new Integer(Integer.parseInt(o.toString()));
		}
		return i;
	}
	@Override
	public List<Map<String, Object>> countCaseCriticalviolationBySecrecyLevel(
			String organId,String className) {
		Integer total = getDiscloseSecrecyTotal(organId,className, "2",null ,false);//得到本单位  要害部门总数

		Map<String ,Object> params = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();

		if(total==0) {
			sb.append(" SELECT A.option_text, 0 AS fcount, '0.00%' AS total ");
		}else {
			sb.append(" SELECT A.option_text, ROUND(IFNULL(B.fcount,0),0)AS fcount, CONCAT(ROUND(IFNULL((fcount/"+total+")*100,0),2),'%') AS total ");
		}

		sb.append(" FROM ");
		sb.append(" ( ");//A表  查询基础字典表，查询出保密要害部门的密级
		sb.append("  SELECT option_text,option_value ");
		sb.append("  FROM sys_dictionary_option ");
		sb.append("  WHERE status=1 AND field_id=(SELECT field_id FROM sys_dictionary_field ");
		sb.append("  WHERE table_id=(SELECT table_id FROM sys_dictionary_table WHERE table_code='bmp') ");
		sb.append("  AND field_code='secrecy_level_thing') ");
		sb.append(" )AS A ");
		sb.append(" LEFT JOIN");
		sb.append(" ( ");//B表   按照密级统计泄密事件的个数
		sb.append("  SELECT criticalviolation.secrecy_level, SUM(criticalviolation.fcount) AS fcount ");
		sb.append("  FROM (SELECT criticalviolationcase_id,secrecy_level, 1 AS fcount FROM bm_case_criticalviolation WHERE create_organ_id = :organId AND secrecy_status=0)AS criticalviolation");//orgid过滤
		sb.append("  GROUP BY secrecy_level");
		sb.append(" )AS B");
		sb.append(" ON A.option_value = B.secrecy_level ");


		params.put("organId", organId);
		List<Map<String, Object>> list = this.getPersistProxy().getJdbcPersistence().findList(sb.toString(), params);

		//设置合计列
		Map<String, Object> grp = new  HashMap<String, Object>();
		grp.put("option_text", "合计");
		if(total==0) {
			grp.put("total", "0.00%");
		}else {
			grp.put("total", "100.00%");
		}
		grp.put("fcount", total);
		list.add(grp);

		return list;
	}
	@Override
	public List<Map<String, Object>> countDiscloseSecrecy(String organId,
			String dealResult, String groupBy,String className,String district,boolean zhixiadanwei) {

		Integer total = getDiscloseSecrecyTotal(organId,className,dealResult,district,zhixiadanwei);

		Map<String ,Object> params = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT A.option_text, ROUND(IFNULL(B.fcount,0),0)AS fcount");
		sb.append(" FROM (  SELECT option_text,option_value FROM sys_dictionary_option ");
		if (groupBy.equals("case_type")) {

			if (className.equals(DiscloseSecrecy.class.getName().toString())) {

				sb.append("  WHERE status=1 AND option_value>0 and option_value<18 and field_id=(SELECT field_id FROM sys_dictionary_field ");
			}
			if (className.equals(CaseCriticalviolation.class.getName().toString())) {

				sb.append("  WHERE status=1 AND option_value>17  and  field_id=(SELECT field_id FROM sys_dictionary_field ");

			}
		}
		else {
			sb.append("  WHERE status=1 AND  field_id=(SELECT field_id FROM sys_dictionary_field ");
		}
		sb.append("  WHERE table_id=(SELECT table_id FROM sys_dictionary_table WHERE table_code='bmp') ");
		if (groupBy.equals("secrecy_level")) {
			sb.append("  AND field_code='secrecy_level_thing') ");
		}else {
			sb.append("  AND field_code='"+groupBy+"') ");

		}
		sb.append(" )AS A ");
		sb.append(" LEFT JOIN");
		if (className.equals(DiscloseSecrecy.class.getName())) {

			sb.append(" (  SELECT disclosesecrecy."+groupBy+", SUM(disclosesecrecy.fcount) AS fcount ");
			if (null!=district) {

				sb.append("  FROM (SELECT disclosesecrecycase_id,"+groupBy+", 1 AS fcount FROM bm_case_disclosesecrecy WHERE create_organ_id in(");//orgid过滤
				if (zhixiadanwei) {
					sb.append(" select ORGAN_ID from sys_organization where DISTRICT_CODE=:district  ");
					sb.append(" )AND secrecy_status=0");
					params.put("district", district);

				}else{
					int layer=this.getPersistProxy().getJdbcPersistence().findForInt("select layer from sys_district where district_code ='"+district+"'", params);
					sb.append(" select ORGAN_ID from sys_organization where DISTRICT_CODE in " +
							"(select district_code from sys_district where layer like '"+layer);
					sb.append("%') )AND secrecy_status=0");
				}
				if (dealResult!=null) {
					sb.append("  and deal_result="+Integer.parseInt(dealResult));
				}
				sb.append(")AS disclosesecrecy");
				sb.append("  GROUP BY " +groupBy);
				sb.append(" )AS B");

			}
			else if(null!=organId) {

				sb.append("  FROM (SELECT disclosesecrecycase_id,"+groupBy+", 1 AS fcount FROM bm_case_disclosesecrecy WHERE create_organ_id = :organId AND secrecy_status=0");//orgid过滤
				if (dealResult!=null) {
					sb.append("  and deal_result="+Integer.parseInt(dealResult));
				}
				sb.append(")AS disclosesecrecy");
				sb.append("  GROUP BY " +groupBy);
				sb.append(" )AS B");
			}

		}
		if (className.equals(CaseCriticalviolation.class.getName())) {

			sb.append(" (  SELECT criticalviolation."+groupBy+", SUM(criticalviolation.fcount) AS fcount ");
			if (null!=district) {

				int layer=this.getPersistProxy().getJdbcPersistence().findForInt("select layer from sys_district where district_code ='"+district+"'", params);
				sb.append("  FROM (SELECT criticalviolationcase_id,"+groupBy+", 1 AS fcount FROM bm_case_criticalviolation WHERE create_organ_id in(");//orgid过滤

				if (zhixiadanwei) {
					sb.append(" select ORGAN_ID from sys_organization where DISTRICT_CODE in " +
							"(select district_code from sys_district where layer=:layer");
					sb.append(") )AND secrecy_status=0");
					params.put("layer", layer);

				}else{
					sb.append(" select ORGAN_ID from sys_organization where DISTRICT_CODE in " +
							"(select district_code from sys_district where layer like '"+layer);
					sb.append("%') )AND secrecy_status=0");
				}
				if (dealResult!=null) {
					sb.append("  and deal_result="+Integer.parseInt(dealResult));
				}
				sb.append(")AS criticalviolation");
				sb.append("  GROUP BY " +groupBy);
				sb.append(" )AS B");

			}
			else if(null!=organId) {
				sb.append("  FROM (SELECT criticalviolationcase_id,"+groupBy+", 1 AS fcount FROM bm_case_criticalviolation WHERE create_organ_id = :organId AND secrecy_status=0");//orgid过滤
				if (dealResult!=null) {
					sb.append("  and deal_result="+Integer.parseInt(dealResult));
				}
				sb.append(")AS criticalviolation");
				sb.append("  GROUP BY "+groupBy);
				sb.append(" )AS B");
			}

		}
		sb.append(" ON A.option_value = B."+groupBy);


		params.put("organId", organId);
		List<Map<String, Object>> list = this.getPersistProxy().getJdbcPersistence().findList(sb.toString(), params);

		//设置合计列
		Map<String, Object> grp = new  HashMap<String, Object>();
		grp.put("option_text", "合计");
		grp.put("fcount", total);
		list.add(grp);

		return list;
	}
	@SuppressWarnings("all")
	@Override
	public List<List<String>> countDiscloseSecrecy_zhixiadanwei(
			String dealResult, String groupBy, String className, String district) {
		String sqlString ="SELECT ORGAN_NAME,ORGAN_ID from sys_organization where DISTRICT_CODE=:district_code";
		String sqlTitle="SELECT option_text,option_value FROM sys_dictionary_option   WHERE status=1 AND field_id=( "+
				"SELECT field_id FROM sys_dictionary_field   WHERE table_id=( "+
				"SELECT table_id FROM sys_dictionary_table WHERE table_code='bmp')   AND field_code=:field_code)";
		Map<String ,Object> params = new HashMap<String, Object>();
		params.put("district_code", district);
		if (groupBy.equals("secrecy_level")) {
			params.put("field_code", "secrecy_level_thing");
		}else {
			params.put("field_code", groupBy);
		}
		//查出有好多个单位
		List<Map<String, Object>> list = this.getPersistProxy().getJdbcPersistence().findList(sqlString, params);
		//表头
		List<Map<String, Object>> listTiltle = this.getPersistProxy().getJdbcPersistence().findList(sqlTitle, params);

		List<List<String>> listReturn=new ArrayList<List<String>>();
		for (int i = 0; i < list.size(); i++) {
			String sqlString2="";
			if (className.equals(DiscloseSecrecy.class.getName())) {

				//循环单位
				sqlString2="SELECT a.option_text,b.number from "+
						"(SELECT option_text,option_value   FROM sys_dictionary_option   WHERE status=1 AND field_id=( "+
						"SELECT field_id FROM sys_dictionary_field   WHERE table_id=( "+
						"SELECT table_id FROM sys_dictionary_table WHERE table_code='bmp')   AND field_code=:field_code)  )AS A ,(SELECT  "+
						groupBy+
						", count("+groupBy+") as number FROM bm_case_disclosesecrecy where create_organ_id=:create_organ_id AND secrecy_status=0 ";
				if (null!=dealResult) {
					sqlString2+=" and deal_result=:dealResult";
					params.put("dealResult", dealResult);
				}
				sqlString2+=" GROUP BY "+groupBy+" ) as B  where  A.option_value =B."+groupBy;

			}
			if (className.equals(CaseCriticalviolation.class.getName())) {
				sqlString2="SELECT a.option_text,b.number from "+
						"(SELECT option_text,option_value   FROM sys_dictionary_option   WHERE status=1 AND field_id=( "+
						"SELECT field_id FROM sys_dictionary_field   WHERE table_id=( "+
						"SELECT table_id FROM sys_dictionary_table WHERE table_code='bmp')   AND field_code=:field_code)  )AS A , "+
						"(SELECT "+groupBy+", count("+groupBy+") as number FROM bm_case_criticalviolation where create_organ_id=:create_organ_id "+
						" AND secrecy_status=0 ";
				if (null!=dealResult) {
					sqlString2+=" and deal_result=:dealResult";
					params.put("dealResult", dealResult);
				}
				sqlString2+=" GROUP BY "+groupBy+" ) as B  where  A.option_value =B."+groupBy;

			}
			if (groupBy.equals("secrecy_level")) {
				params.put("field_code", "secrecy_level_thing");
			}else {
				params.put("field_code", groupBy);
			}
			params.put("create_organ_id", list.get(i).get("ORGAN_ID").toString());
			List<Map<String, Object>> listOrgen = this.getPersistProxy().getJdbcPersistence().findList(sqlString2, params);
			if (listOrgen.size()>0) {
				if (i==0) {

					listReturn.addAll(deal_list(listOrgen, list.get(i).get("ORGAN_NAME").toString(),"title",listTiltle));
				}else {
					listReturn.addAll(deal_list(listOrgen, list.get(i).get("ORGAN_NAME").toString(),null,listTiltle));
				}
			}
		}

		return listReturn;
	}
	@SuppressWarnings("all")
	public List deal_list(List list,String districtName,String tilte,List listTitles)
	{
		List<List<String>> listCount=new ArrayList<List<String>>();
		List listTitle=new ArrayList();//标题
		List listRowCount=new ArrayList();//行
		if (tilte!=null) {

			listTitle.add("泄密案件");
		}
		if(null!=districtName)
		{

			listRowCount.add(districtName);
		}
		if (listTitles.size()==list.size()) {

			for (int i = 0; i < list.size(); i++) {
				Map map=(Map) list.get(i);
				if (tilte!=null) {

					listTitle.add(map.get("option_text"));
				}
				if (map.get("number")==null) {

					listRowCount.add(0);
				}else{
					listRowCount.add(map.get("number"));

				}

			}
		}
		else if (listTitles.size()>list.size()) {

			for (int i = 0; i < listTitles.size(); i++) {
				if (list.size()>=i+1) {

					Map map=(Map) list.get(i);
					if (tilte!=null) {

						listTitle.add(map.get("option_text"));
					}
					if (map.get("number")==null) {

						listRowCount.add(0);
					}else{
						listRowCount.add(map.get("number"));

					}
				}
				else {
					Map map=(Map) listTitles.get(i);
					if (tilte!=null) {

						listTitle.add(map.get("option_text"));
					}

					listRowCount.add(0);
				}

			}
		}
		else if (list.size()==0) {

			for (int i = 0; i < listTitles.size(); i++) {
				Map map=(Map) listTitles.get(i);
				if (tilte!=null) {

					listTitle.add(map.get("option_text"));
				}


				listRowCount.add(0);


			}
		}
		listCount.add(listTitle);
		listCount.add(listRowCount);
		return listCount;
	}
	@Override
	public List<DiscloseSecrecyStatDto> queryDiscloseSecrecyByDistrict(
			List<District> districtList, boolean needTotal, Organ organ,
			String className) {
		List<DiscloseSecrecyStatDto> keySectionStatDtoList = new ArrayList<DiscloseSecrecyStatDto>();
		DiscloseSecrecyStatDto total = new DiscloseSecrecyStatDto();
		for (District d : districtList) {
			//Map<String ,Object> params = new HashMap<String, Object>();
			//统计  行政区划  和直辖单位的  保密要害部门--有多少个子区域，就查询多少次，每次查询统计表格一行数据
			List<Object[]> objectList = getPersistProxy().getOrmPersistence().findByNativeQuery(getDiscloseSecrecyDistrictSql(d.getLayer(),className).toString());
			//处理数据
			for (Object[] object : objectList) {
				//秘密 	3
				//机密     2
				//绝密     1
				DiscloseSecrecyStatDto keySectionStatDto = new DiscloseSecrecyStatDto();
				keySectionStatDto.setName(object[0].toString());
				keySectionStatDto.setNum1(Integer.parseInt(object[3].toString()));
				keySectionStatDto.setNum2(Integer.parseInt(object[2].toString()));
				keySectionStatDto.setNum3(Integer.parseInt(object[1].toString()));
				keySectionStatDto.setSelfTotal(Integer.parseInt(object[1].toString())
						+ Integer.parseInt(object[2].toString())
						+ Integer.parseInt(object[3].toString()));
				keySectionStatDto.setNum4(Integer.parseInt(object[6].toString()));
				keySectionStatDto.setNum5(Integer.parseInt(object[5].toString()));
				keySectionStatDto.setNum6(Integer.parseInt(object[4].toString()));
				keySectionStatDto.setAllTotal(Integer.parseInt(object[4].toString())
						+ Integer.parseInt(object[5].toString())
						+ Integer.parseInt(object[6].toString()));
				keySectionStatDto.setDistrictCode(object[7].toString());
				keySectionStatDtoList.add(keySectionStatDto);
				if (needTotal) {
					// TODO 以后从配置读出来
					total.setName("合计");
					total.setNum1(total.getNum1() + keySectionStatDto.getNum1());
					total.setNum2(total.getNum2() + keySectionStatDto.getNum2());
					total.setNum3(total.getNum3() + keySectionStatDto.getNum3());
					total.setNum4(total.getNum4() + keySectionStatDto.getNum4());
					total.setNum5(total.getNum5() + keySectionStatDto.getNum5());
					total.setNum6(total.getNum6() + keySectionStatDto.getNum6());
					total.setSelfTotal(total.getSelfTotal() + keySectionStatDto.getSelfTotal());
					total.setAllTotal(total.getAllTotal() + keySectionStatDto.getAllTotal());
				}
			}
		}
		if (needTotal && !keySectionStatDtoList.isEmpty()) {
			keySectionStatDtoList.add(total);
		}
		return keySectionStatDtoList;
	}
	@Override
	public List<DiscloseSecrecyStatDto> queryDiscloseSecrecyByOrgan(
			District district, boolean needTotal, Organ organ, String className) {
		List<DiscloseSecrecyStatDto> keySectionStatDtoList = new ArrayList<DiscloseSecrecyStatDto>();
		String tableName="";
		if (className.equals(DiscloseSecrecy.class.getName())) {
			tableName="bm_case_disclosesecrecy";
		}else{
			tableName="bm_case_criticalviolation";
		}
		DiscloseSecrecyStatDto total = new DiscloseSecrecyStatDto();
			List<Object[]> objectList = getPersistProxy().getOrmPersistence().findByNativeQuery(getOrganSqlByDistrict(district,tableName,"create_organ_id",organ).toString());
			//处理数据
			for (Object[] object : objectList) {
				//秘密 	3
				//机密     2
				//绝密     1
				DiscloseSecrecyStatDto keySectionStatDto = new DiscloseSecrecyStatDto();
				keySectionStatDto.setName(object[0].toString());
				keySectionStatDto.setNum1(Integer.parseInt(object[3].toString()));
				keySectionStatDto.setNum2(Integer.parseInt(object[2].toString()));
				keySectionStatDto.setNum3(Integer.parseInt(object[1].toString()));
				keySectionStatDto.setId(object[4].toString());
				keySectionStatDto.setSelfTotal(Integer.parseInt(object[1].toString())
						+ Integer.parseInt(object[2].toString())
						+ Integer.parseInt(object[3].toString()));
				keySectionStatDtoList.add(keySectionStatDto);
				if (needTotal) {
					// TODO 以后从配置读出来
					total.setName("合计");
					total.setNum1(total.getNum1() + keySectionStatDto.getNum1());
					total.setNum2(total.getNum2() + keySectionStatDto.getNum2());
					total.setNum3(total.getNum3() + keySectionStatDto.getNum3());
					total.setSelfTotal(total.getSelfTotal() + keySectionStatDto.getSelfTotal());
					total.setAllTotal(total.getAllTotal() + keySectionStatDto.getAllTotal());
				}
			}
		if (needTotal && !keySectionStatDtoList.isEmpty()) {
			keySectionStatDtoList.add(total);
		}
		return keySectionStatDtoList;
	}

	/**
	 * 直辖单位的sql语句
	 * @param district
	 * @param tableName
	 * @param colnumName_organ
	 * @param organ
	 * @return
	 */
	public  StringBuffer getOrganSqlByDistrict(District district, String tableName, String colnumName_organ,Organ organ) {
		StringBuffer sb = new StringBuffer();
		String receiveStr = "";

		// 查询条件-单位名称
		if(organ!=null && organ.getOrganName()!= null && !"".equals(organ.getOrganName())){
			receiveStr = " and o.organ_name like '%" + organ.getOrganName() +"%' ";
		}

		sb.append(" SELECT o.organ_name AS organName,IFNULL(b.num1,0)AS c1, IFNULL(b.num2,0)AS c2, IFNULL(b.num3,0)AS c3, o.organ_id AS organId FROM sys_organization o ");
		sb.append(" LEFT JOIN ");
		//b表开始
		sb.append(" (SELECT ").append(colnumName_organ).append(",IFNULL(SUM(CASE WHEN secrecy_level=1 THEN fcount END),0)AS num1,IFNULL(SUM(CASE WHEN secrecy_level=2 THEN fcount END),0)AS num2,SUM(CASE WHEN secrecy_level=3 THEN fcount END)AS num3 FROM( ");
		//查询1开始
		sb.append(" SELECT ").append(colnumName_organ).append(", secrecy_level,count(secrecy_level)AS fcount FROM ").append(tableName);
		sb.append(" WHERE secrecy_status!=1 AND create_organ_id IN(SELECT organ_id FROM sys_organization WHERE district_code='").append(district.getDistrictCode()).append("') ");
		sb.append(" GROUP BY ").append(colnumName_organ).append(", secrecy_level ");
		//查询1结束
		sb.append(" )AS a GROUP BY ").append(colnumName_organ).append(")AS b  ");
		//b表结束
		sb.append(" ON b.create_organ_id = o.organ_id ");
		sb.append(" WHERE o.organ_id IN (SELECT organ_id FROM sys_organization WHERE district_code='").append(district.getDistrictCode()).append("') ").append(receiveStr).append(" ORDER BY o.organ_id DESC ");

		return sb;
	}

	/**
	 * 统计  行政区划 和直辖单位 的泄密事件  的sql语句
	 * @param layer
	 * @return
	 */
	private StringBuffer getDiscloseSecrecyDistrictSql(String layer,String className){
		//秘密 	3
		//机密     2
		//绝密     1
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT c.district_name,IFNULL(b.num4,0)AS num4, IFNULL(b.num5,0)AS num5, IFNULL(b.num6,0)AS num6, IFNULL(a.num1,0)AS num1, IFNULL(a.num2,0)AS num2, IFNULL(a.num3,0)AS num3, IFNULL(c.district_code,0)AS district_code FROM ");
		//行政区划名字
		sb.append(" (SELECT d.district_name,d.district_code FROM sys_district d where d.layer = '"+layer+"')AS c ");
		sb.append(" LEFT JOIN ");
		//行政区划统计
		sb.append(" (SELECT IFNULL(SUM(CASE WHEN secrecy_level=1 THEN fcount END),0)AS num1,IFNULL(SUM(CASE WHEN secrecy_level=2 THEN fcount END),0)AS num2,SUM(CASE WHEN secrecy_level=3 THEN fcount END)AS num3 FROM( ");
		if (className.equals(DiscloseSecrecy.class.getName())) {

			sb.append(" SELECT secrecy_level, count(secrecy_level)AS  fcount FROM bm_case_disclosesecrecy WHERE secrecy_status!=1 AND ");
		}
		else {
			sb.append(" SELECT secrecy_level, count(secrecy_level)AS  fcount FROM bm_case_criticalviolation WHERE secrecy_status!=1 AND ");
		}
		sb.append(" create_organ_id IN (SELECT organ_id FROM sys_organization WHERE district_code IN (SELECT district_code FROM sys_district WHERE layer LIKE '"+layer+"%'))group by secrecy_level)AS aa )a  ");
		sb.append(" on 1=1  LEFT JOIN ");
		//直辖单位统计
		sb.append(" (SELECT IFNULL(SUM(CASE WHEN secrecy_level=1 THEN fcount END),0)AS num4,IFNULL(SUM(CASE WHEN secrecy_level=2 THEN fcount END),0)AS num5,SUM(CASE WHEN secrecy_level=3 THEN fcount END)AS num6 FROM( ");
		if (className.equals(DiscloseSecrecy.class.getName())) {
			sb.append(" SELECT secrecy_level, count(secrecy_level)AS  fcount FROM bm_case_disclosesecrecy WHERE secrecy_status!=1 AND ");
		}
		else {
			sb.append(" SELECT secrecy_level, count(secrecy_level)AS  fcount FROM bm_case_criticalviolation WHERE secrecy_status!=1 AND ");

		}
		sb.append(" create_organ_id IN (SELECT organ_id FROM sys_organization WHERE district_code IN (SELECT district_code FROM sys_district WHERE layer = '"+layer+"'))group by secrecy_level)AS bb)b  ");
		sb.append(" ON 1=1 ");
		return sb;
	}
	@Override
	/**
	 *
	 * <p>
	 * 获取单位密级情况
	 * </p>
	 * <p>
	 * 梁文杰 2013-08-22 14:04:56
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @return
	 */
	public List<Object[]> getOrganKeySection(Organ organ,String tableName) {
		StringBuffer sb = new StringBuffer();
		sb.append("	SELECT o.ORGAN_ID,o.ORGAN_NAME,");
		sb.append(" (select count(*) from  "+tableName+" sp where secrecy_status!=1 AND o.ORGAN_ID=sp.create_organ_id and  sp.SECRECY_LEVEL=1) as num1,");

		sb.append("	(select count(*) from  "+tableName+" sp where secrecy_status!=1 AND o.ORGAN_ID=sp.create_organ_id and  sp.SECRECY_LEVEL=2) as num2,");

		sb.append("	(select count(*) from  "+tableName+" sp where secrecy_status!=1 AND o.ORGAN_ID=sp.create_organ_id and  sp.SECRECY_LEVEL=3) as num3");

		sb.append("	 FROM  sys_organization o WHERE o.organ_id = '"+organ.getOrganId()+"'");

		List<Object[]> objectList = getPersistProxy().getOrmPersistence().findByNativeQuery(sb.toString());
		return objectList;
	}


}