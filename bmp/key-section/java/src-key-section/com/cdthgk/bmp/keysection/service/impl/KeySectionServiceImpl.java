package com.cdthgk.bmp.keysection.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.bmp.keysection.dto.KeySectionStatDto;
import com.cdthgk.bmp.keysection.qo.KeySectionQo;
import com.cdthgk.bmp.keysection.service.KeySectionService;
import com.cdthgk.bmp.keysection.vo.KeySection;
import com.cdthgk.bmp.secrecycountryitem.qo.ZongHeTongJiStatDto;
import com.cdthgk.bmp.secrecycountryitem.qo.ZongHeTongJiUtil;
import com.cdthgk.common.lang.LangUtils;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;

/**
 * <p>
 * 要害部门Service类（外部模块使用）
 * </p>
 * <p>
 * 王彭波 2012-12-14 18:01
 * </p>
 * <blockquote>
 * <h4>历史修改记录</h4>
 * <ul>
 * <li>修改人 修改时间 修改描述
 * </ul>
 * </blockquote>
 * <p>
 * copyright tianyu 2012, all rights reserved.
 * </p>
 *
 * @author wangpb
 * @since 1.0
 * @version 1.0
 */
public class KeySectionServiceImpl extends BmpServiceImpl<KeySection, Serializable> implements KeySectionService{

	// 构造器
	/** 默认构造器 */
	public KeySectionServiceImpl() {
	}

	/**（本单位）
	 * 本单位统计 保密要害部门    按照要害部门的密级
	 * 梁文杰 2013-07-18
	 * @param organId  单位id
	 * @return
	 */
	public List<Map<String, Object>> count_KeySection_By_Secrecy_Level(String organId) {

		Integer total = getOrganSectionTotal(organId);//得到本单位  要害部门总数

		Map<String ,Object> params = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();

		if(total==0) {
			sb.append(" SELECT A.option_text, 0 AS fcount, '0.00%' AS total ");
		}else {
			sb.append(" SELECT A.option_text, ROUND(IFNULL(B.fcount,0),0)AS fcount, ROUND(IFNULL((fcount/"+total+")*100,0),2) AS total ");
		}

		sb.append(" FROM ");
		sb.append(" ( ");//A表  查询基础字典表，查询出保密要害部门的密级
		sb.append("  SELECT option_text,option_value ");
		sb.append("  FROM sys_dictionary_option ");
		sb.append("  WHERE status=1 AND field_id=(SELECT field_id FROM sys_dictionary_field ");
		sb.append("  WHERE table_id=(SELECT table_id FROM sys_dictionary_table WHERE table_code='bmp') ");
		sb.append("  AND field_code='secrecy_level_section') ");
		sb.append(" )AS A ");
		sb.append(" LEFT JOIN");
		sb.append(" ( ");//B表   按照密级统计保密要害部门的个数
		sb.append("  SELECT section.secrecy_level, SUM(section.fcount) AS fcount ");
		sb.append("  FROM (SELECT key_section_id,secrecy_level, 1 AS fcount FROM bm_key_section WHERE organ_id = :organId AND secrecy_status=0)AS section");//orgid过滤
		sb.append("  GROUP BY secrecy_level");
		sb.append(" )AS B");
		sb.append(" ON A.option_value = B.secrecy_level ");

		/******新的查询方式    下个版本升级*****/
//		sb.append(" SELECT department_id, department_name, a1, CONCAT(ROUND(a1/IF(a1+a2+a3=0,1,a1+a2+a3)*100,2),'%')AS b1, ");
//		sb.append(" a2, CONCAT(ROUND(a2/IF(a1+a2+a3=0,1,a1+a2+a3)*100,2),'%')AS b2 , ");
//		sb.append(" a3, CONCAT(ROUND(a3/IF(a1+a2+a3=0,1,a1+a2+a3)*100,2),'%')AS b3 , (a1+a2+a3) AS total FROM( ");
//		sb.append(" SELECT department_id, department_name, IFNULL(SUM(CASE WHEN secrecy_level='1' THEN fcount END),0)AS a1, ");
//		sb.append(" IFNULL(SUM(CASE WHEN secrecy_level='2' THEN fcount END),0)AS a2, IFNULL(SUM(CASE WHEN secrecy_level='3' THEN fcount END),0)AS a3 FROM( ");
//		sb.append(" SELECT depart.department_id, depart.department_name, section.secrecy_level, section.fcount FROM( ");
//		sb.append(" SELECT department_id, secrecy_level, SUM(fcount)as fcount FROM( ");
//		sb.append(" SELECT DISTINCT key_section_id, department_id, secrecy_level, 1 AS fcount FROM bm_key_section ");
//		sb.append(" WHERE secrecy_status=0 AND secrecy_level IN (SELECT option_value FROM sys_dictionary_option ");
//		sb.append(" WHERE status=1 AND field_id=(SELECT field_id FROM sys_dictionary_field ");
//		sb.append(" WHERE table_id=(SELECT table_id FROM sys_dictionary_table WHERE table_code='bmp') ");
//		sb.append(" AND field_code='secrecy_level_section')) AND organ_id = :organId ");
//		sb.append(" )AS section GROUP BY department_id, secrecy_level)AS section ");
//		sb.append(" RIGHT JOIN (SELECT department_id,department_name FROM sys_department WHERE organ_id= :organId)AS depart ");
//		sb.append(" ON section.department_id = depart.department_id)AS a GROUP BY department_id, department_name)AS b ");

		params.put("organId", organId);
		List<Map<String, Object>> list = this.getPersistProxy().getJdbcPersistence().findList(sb.toString(), params);

		//设置合计列
		Map<String, Object> grp = new  HashMap<String, Object>();
		grp.put("option_text", "合计");
		if(total==0) {
			grp.put("total", "0.00");
		}else {
			grp.put("total", "100.00");
		}
		grp.put("fcount", total);
		list.add(grp);

		return list;
	}


	/**（本单位）
	 * 返回 本单位的要害部门总个数
	 * 1.通过单位id过滤    2.通过密级是否启用过滤
	 *
	 * @param organId
	 * @return
	 */
	public Integer getOrganSectionTotal(String organId) {

		Integer i = new Integer(0);
		Map<String ,Object> params = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();

		sb.append(" SELECT SUM(section.fcount) AS total ");
		sb.append(" FROM ( ");
		sb.append("  SELECT key_section_id,secrecy_level, 1 AS fcount FROM bm_key_section WHERE organ_id = :organId AND secrecy_status=0 ");
		sb.append("  AND secrecy_level IN (SELECT option_value FROM sys_dictionary_option  ");
		sb.append("  WHERE status=1 AND field_id=(SELECT field_id FROM sys_dictionary_field  ");
		sb.append("  WHERE table_id=(SELECT table_id FROM sys_dictionary_table WHERE table_code='bmp') ");
		sb.append("  AND field_code='secrecy_level_section')) ");
		sb.append(" )AS section  ");
		params.put("organId", organId);

		Map<String ,Object> map = this.getPersistProxy().getJdbcPersistence().find(sb.toString(), params);
		Object o = map.get("total");
		if(o!=null) {
			i = new Integer(Integer.parseInt(o.toString()));
		}
		return i;

		/****新的查询方式  下个版本升级 ***/
//		sb.append(" SELECT IFNULL(SUM(CASE WHEN secrecy_level='1' THEN fcount END),0)AS a1, ");
//		sb.append(" IFNULL(SUM(CASE WHEN secrecy_level='2' THEN fcount END),0)AS a2, IFNULL(SUM(CASE WHEN secrecy_level='3' THEN fcount END),0)AS a3 FROM( ");
//		sb.append(" SELECT DISTINCT key_section_id, department_id, secrecy_level, 1 AS fcount FROM bm_key_section ");
//		sb.append(" WHERE secrecy_status=0 AND  secrecy_level IN (SELECT option_value FROM sys_dictionary_option  ");
//		sb.append(" WHERE status=1 AND field_id=(SELECT field_id FROM sys_dictionary_field ");
//		sb.append(" WHERE table_id=(SELECT table_id FROM sys_dictionary_table WHERE table_code='bmp') ");
//		sb.append(" AND field_code='secrecy_level_section')) AND organ_id = :organId)AS A ");
//		params.put("organId", organId);
//		Map<String ,Object> map = this.getPersistProxy().getJdbcPersistence().find(sb.toString(), params);
	}


	/**（行政区划）
	 * 保密局  统计 行政区划  的保密要害部门的信息
	 * @param district  行政区划
	 * @return
	 */
	public List<Map<String, Object>> count_KeySection_By_Secrecy_Level_District(District district) {
		return this.count_KeySection_By_Secrecy_Level_basic(district, 1);
	}


	/**（ 直辖单位）
	 * 保密局  统计 行政区下面的   直辖单位 汇总的统计
	 * @param district  行政区划对象
	 * @return
	 */
	public List<Map<String, Object>> count_KeySection_By_Secrecy_Level_Layer(District district) {
		return count_KeySection_By_Secrecy_Level_basic(district, 2);
	}


	/**（行政区划）
	 * 保密局  统计 保密要害部门的信息
	 * @param district  行政区划
	 * @param reflag    标志信息       1：行政区划统计   2:行政区内的单位统计
	 * @return
	 */
	private List<Map<String, Object>> count_KeySection_By_Secrecy_Level_basic(District district, int reflag) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if(district==null) { //如果行政区划参数为空  那么直接返回空list
			return list;
		}
		Map<String ,Object> params = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();

		int total = this.getSectionTotal_basic(district, reflag);
		if(total==0) { //总数为0
			sb.append(" SELECT A.option_text, 0 AS fcount,'100.00%' AS total FROM ");
		}else {
			sb.append(" SELECT A.option_text, ROUND(IFNULL(B.fcount,0),0)AS fcount, ROUND(IFNULL((fcount/"+total+"),0)*100,2) AS total FROM ");
		}
		sb.append("	(SELECT option_text,option_value ");//查询出密级信息
		sb.append("	 FROM sys_dictionary_option ");
		sb.append("  WHERE status=1 AND field_id=(SELECT field_id FROM sys_dictionary_field ");
		sb.append("  WHERE table_id=(SELECT table_id FROM sys_dictionary_table WHERE table_code='bmp') ");
		sb.append("  AND field_code='secrecy_level_section') ");
		sb.append(" )AS A ");
		sb.append(" LEFT JOIN ");
		sb.append(" ( ");
		sb.append("  SELECT secrecy_level, COUNT(fcount)AS fcount FROM( ");//按照密级和行政区统计要害部门的个数
		sb.append("	 SELECT key_section_id,secrecy_level, 1 AS fcount FROM bm_key_section ");

		if(reflag==1) { //1：行政区划统计
			sb.append("	 WHERE organ_id IN (SELECT organ_id FROM sys_organization WHERE district_code IN (SELECT district_code FROM sys_district WHERE layer LIKE :layer)) ");
		}else {//2:行政区内的单位统计
			sb.append("	 WHERE organ_id IN (SELECT organ_id FROM sys_organization WHERE district_code IN (SELECT district_code FROM sys_district WHERE layer = :layer)) ");
		}

		sb.append("	 AND secrecy_status=0)AS C GROUP BY secrecy_level ");
		sb.append("	)AS B ");
		sb.append("  ON A.option_value = B.secrecy_level ");

		if(reflag==1) {// //1：行政区划统计
			params.put("layer", district.getLayer() + "%");
		}else {//2:行政区内的单位统计
			params.put("layer", district.getLayer() );
		}

		list = this.getPersistProxy().getJdbcPersistence().findList(sb.toString(), params);

		//设置合计列
		Map<String, Object> grp = new  HashMap<String, Object>();
		grp.put("option_text", "合计");
		if(total==0) {
			grp.put("total", "0.00");
		}else {
			grp.put("total", "100.00");
		}
		grp.put("fcount", total);
		list.add(grp);

		return list;
	}


	/**（行政区划）
	 * 保密局     获取要害部门总数
	 * @param district  行政区划
	 * @param reflag    标志信息       1：行政区划统计   2:行政区内的单位统计
	 * @return
	 */
	public Integer getSectionTotal_basic(District district, int reflag) {

		Integer i = new Integer(0);
		Map<String ,Object> params = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();

		sb.append(" SELECT COUNT(A.fcount)AS total FROM ( ");
		sb.append(" SELECT key_section_id,secrecy_level, 1 AS fcount FROM bm_key_section  ");
		if(reflag==1) {// //1：行政区划统计
			sb.append(" WHERE organ_id IN (SELECT organ_id FROM sys_organization WHERE district_code IN (SELECT district_code FROM sys_district WHERE layer LIKE :layer)) ");
		}else {//2:行政区内的单位统计
			sb.append(" WHERE organ_id IN (SELECT organ_id FROM sys_organization WHERE district_code IN (SELECT district_code FROM sys_district WHERE layer = :layer)) ");
		}

		sb.append(" AND secrecy_status=0  ");
		sb.append(" AND secrecy_level IN ( ");
		sb.append(" SELECT option_value FROM sys_dictionary_option WHERE status=1 ");
		sb.append(" AND field_id=(SELECT field_id FROM sys_dictionary_field WHERE ");
		sb.append(" table_id=(SELECT table_id FROM sys_dictionary_table WHERE table_code='bmp') AND field_code='secrecy_level_section') ");
		sb.append(" ) ");
		sb.append(" )A ");
		if(reflag==1) {// //1：行政区划统计
			params.put("layer", district.getLayer() + "%");
		}else {//2:行政区内的单位统计
			params.put("layer", district.getLayer() );
		}

		Map<String ,Object> map = this.getPersistProxy().getJdbcPersistence().find(sb.toString(), params);
		Object o = map.get("total");
		if(o!=null) {
			i = new Integer(Integer.parseInt(o.toString()));
		}

		return i;
	}



	/**(单位明细)
	 *  保密局    获取 行政区下面   各个单位的   保密要害部门的信息
	 * 梁文杰  2013-07-23
	 * @param district  行政区划对象
	 * @return
	 */
	public List<Map<String, Object>> count_KeySection_By_Secrecy_Level_OrganDetail(District district){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if(district==null) { //如果行政区划参数为空  那么直接返回空list
			return list;
		}
		Map<String ,Object> params = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();

		sb.append(" SELECT organ_id,organ_name, a1, ROUND((a1/IF(total=0,1,total)*100),2) AS b1, a2,ROUND((a2/IF(total=0,1,total)*100),2) AS b2, ");
		sb.append(" a3, ROUND((a3/IF(total=0,1,total)*100),2) AS b3, total FROM( ");
		sb.append(" SELECT organ_id, organ_name, a1, a2, a3, (a1+a2+a3)AS total FROM( ");//计算总数  如果总数total为0  那么就证明 a1,a2,a3也是0,这里让total为1 避免分母为0的情况
		sb.append(" SELECT lel.organ_id, lel.organ_name, ");
		sb.append(" IFNULL(SUM(CASE WHEN lel.secrecy_level=3 THEN lel.fcount END),0) AS a3 , ");
		sb.append(" IFNULL(SUM(CASE WHEN lel.secrecy_level=2 THEN lel.fcount END),0) AS a2 , ");
		sb.append(" IFNULL(SUM(CASE WHEN lel.secrecy_level=1 THEN lel.fcount END),0) AS a1 ");
		sb.append(" FROM( ");
		sb.append(" SELECT base.organ_id, base.organ_name, base.secrecy_level, COUNT(base.fcount)AS fcount FROM( ");
		//查询出相关单位下的  要害部门的统计  按照密级  开始
		sb.append(" SELECT org.organ_id, org.organ_name, sec.key_section_id, sec.secrecy_level, 1 AS fcount FROM ");
		sb.append(" (SELECT organ_id,organ_name FROM sys_organization WHERE district_code IN(SELECT district_code FROM sys_district WHERE layer = :layer))AS org ");
		sb.append(" LEFT JOIN ");
		sb.append(" (SELECT key_section_id, secrecy_level, organ_id FROM bm_key_section WHERE secrecy_status=0 ");
		sb.append(" AND organ_id IN(SELECT organ_id FROM sys_organization WHERE district_code IN(SELECT district_code FROM sys_district WHERE layer = :layer)) ");
		sb.append(" AND secrecy_level IN (SELECT option_value FROM sys_dictionary_option WHERE status=1 AND field_id=(SELECT field_id FROM sys_dictionary_field WHERE ");
		sb.append(" table_id=(SELECT table_id FROM sys_dictionary_table WHERE table_code='bmp') AND field_code='secrecy_level_section')) )AS sec ");
		sb.append(" ON org.organ_id = sec.organ_id ");
		//查询出相关单位下的  要害部门的统计  按照密级  结束
		sb.append(" )AS base GROUP BY base.organ_id,base.organ_name,base.secrecy_level ");
		sb.append(" )AS lel GROUP BY organ_id,organ_name ");
		sb.append(" )AS res ");
	    sb.append(" )AS res1 ");
	    params.put("layer", district.getLayer());

		list = this.getPersistProxy().getJdbcPersistence().findList(sb.toString(), params);


		//设置合计列
		Map<String, Object> groupColunm = new  HashMap<String, Object>();
		//保密局    获取   直辖单位 汇总   要害部门总数
		List<Map<String, Object>> groupList = this.getOrganDetailSectionTotal(district);
		Iterator<Map<String, Object>> it = groupList.iterator();
		while(it.hasNext()) {
			Map<String, Object> obj =  it.next();
			if(obj != null) {
				groupColunm.put("a1", obj.get("a1"));
				groupColunm.put("a2", obj.get("a2"));
				groupColunm.put("a3", obj.get("a3"));
				groupColunm.put("total", obj.get("total"));
			}
		}
		groupColunm.put("organ_name", "合计");
		list.add(groupColunm);

		return list;
	}


	/** (单位明细)
	 * 保密局    获取 行政区下面   各个单位的    要害部门总数
	 * 梁文杰  2013-07-23
	 * @param district  行政区划 对象
	 * @return
	 */
	public List<Map<String, Object>>  getOrganDetailSectionTotal(District district){
		List<Map<String, Object>> map = new ArrayList<Map<String, Object>>();
		if(district == null) {
			return map;
		}
		Map<String ,Object> params = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();

		sb.append(" SELECT a1,a2,a3 ,a1+a2+a3 AS total FROM( ");
		sb.append(" SELECT IFNULL(SUM(CASE WHEN lel.secrecy_level=3 THEN lel.fcount END),0) AS a3 , ");
		sb.append(" IFNULL(SUM(CASE WHEN lel.secrecy_level=2 THEN lel.fcount END),0) AS a2 , ");
		sb.append(" IFNULL(SUM(CASE WHEN lel.secrecy_level=1 THEN lel.fcount END),0) AS a1 FROM ");
		sb.append(" (SELECT secrecy_level, COUNT(A.fcount)AS fcount FROM ( ");
		sb.append(" SELECT key_section_id,secrecy_level, 1 AS fcount FROM bm_key_section  ");
		sb.append(" WHERE organ_id IN(SELECT organ_id FROM sys_organization WHERE district_code IN (SELECT district_code FROM sys_district WHERE layer = :layer) ) ");
		sb.append(" AND secrecy_status=0 AND secrecy_level IN (SELECT option_value FROM sys_dictionary_option WHERE status=1 ");
		sb.append(" AND field_id=(SELECT field_id FROM sys_dictionary_field WHERE ");
		sb.append(" table_id=(SELECT table_id FROM sys_dictionary_table WHERE table_code='bmp') AND field_code='secrecy_level_section') ");
		sb.append(" ) ");
		sb.append(" )A GROUP BY secrecy_level)lel)AS C");
		params.put("layer", district.getLayer());

		map = this.getPersistProxy().getJdbcPersistence().findList(sb.toString(), params);
		return map;
	}

	/**
	 * 通过要害部门id 查询  要害部门是否被其他的表引用
	 * 1.要害部位表
	 * 2.要害部门密级变更表
	 * 3.要害部门密级解除表
	 * 4.涉密人员表
	 *
	 * @param keySectionId  要害部门id
	 * @return  true有关联  false没有关联
	 */
	public int getKeySectionRelationship(String keySectionId){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if(keySectionId==null || keySectionId.equals("")) {
			return 0;
		}

		StringBuffer sb = new StringBuffer();
		Map<String ,Object> params = new HashMap<String, Object>();

		//1.要害部位表
		sb.append("select p.KEY_PART_ID from bm_key_part p where p.DEPARTMENT_ID = (select DEPARTMENT_ID from bm_key_section where key_section_id=:keySectionId) ");
		params.put("keySectionId", keySectionId);
		list = this.getPersistProxy().getJdbcPersistence().findList(sb.toString(), params);
		if(list!=null && list.size()>0) {
			return 1;
		}

		//2.要害部门密级变更表
		sb = new StringBuffer();
		params = new HashMap<String, Object>();
		sb.append("select p.section_change_id from bm_Key_Section_Change p where p.key_section_id = :keySectionId ");
		params.put("keySectionId", keySectionId);
		list = this.getPersistProxy().getJdbcPersistence().findList(sb.toString(), params);
		if(list!=null && list.size()>0) {
			return 2;
		}

		//3.要害部门密级解除表
		sb = new StringBuffer();
		params = new HashMap<String, Object>();
		sb.append("select p.key_section_id from bm_key_section_secrecy_clear p where p.key_section_id = :keySectionId ");
		params.put("keySectionId", keySectionId);
		list = this.getPersistProxy().getJdbcPersistence().findList(sb.toString(), params);
		if(list!=null && list.size()>0) {
			return 3;
		}

		//4.涉密人员表
		sb = new StringBuffer();
		params = new HashMap<String, Object>();
		sb.append("select p.person_id from bm_Secrecy_Person p where p.department_id = (select DEPARTMENT_ID from bm_key_section where key_section_id=:keySectionId) ");
		params.put("keySectionId", keySectionId);
		list = this.getPersistProxy().getJdbcPersistence().findList(sb.toString(), params);
		if(list!=null && list.size()>0) {
			return 4;
		}

		return 0;
	}


	/***********************************************综合统计**********************************************/
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
	public List<Object[]> getOrganKeySection(Organ organ) {

		StringBuffer sb = new StringBuffer();
		sb.append("	SELECT o.ORGAN_ID,o.ORGAN_NAME,");
		sb.append(" (select count(*) from bm_key_section sp where secrecy_status!=1 AND o.ORGAN_ID=sp.ORGAN_ID and  sp.SECRECY_LEVEL=1) as num1,");
		sb.append("	(select count(*) from bm_key_section sp where secrecy_status!=1 AND o.ORGAN_ID=sp.ORGAN_ID and  sp.SECRECY_LEVEL=2) as num2,");
		sb.append("	(select count(*) from bm_key_section sp where secrecy_status!=1 AND o.ORGAN_ID=sp.ORGAN_ID and  sp.SECRECY_LEVEL=3) as num3");
		sb.append("	 FROM  sys_organization o WHERE o.organ_id = '"+organ.getOrganId()+"'");

		List<Object[]> objectList = getPersistProxy().getOrmPersistence().findByNativeQuery(sb.toString());
		return objectList;
	}


	/**
	 * 统计  行政区划  和直辖单位的  保密要害部门
	 * @param districtList  行政区划 对象  集合
	 * @param needTotal   是否需要 总数
	 * @param organ  组织对象
	 * @return
	 */
	public List<KeySectionStatDto> queryKeySectionByDistrict(List<District> districtList, boolean needTotal, Organ organ) {

		List<KeySectionStatDto> keySectionStatDtoList = new ArrayList<KeySectionStatDto>();
		KeySectionStatDto total = new KeySectionStatDto();

		for (District d : districtList) {
			Map<String ,Object> params = new HashMap<String, Object>();
			//统计  行政区划  和直辖单位的  保密要害部门
			List<Object[]> objectList = getPersistProxy().getOrmPersistence().findByNativeQuery(getKeySectionDistrictSql(d.getLayer()).toString());

			//处理数据
			for (Object[] object : objectList) {
				KeySectionStatDto keySectionStatDto = new KeySectionStatDto();
				keySectionStatDto.setName(object[0].toString());
				keySectionStatDto.setNum1(Integer.parseInt(object[1].toString()));
				keySectionStatDto.setNum2(Integer.parseInt(object[2].toString()));
				keySectionStatDto.setNum3(Integer.parseInt(object[3].toString()));
				keySectionStatDto.setSelfTotal(Integer.parseInt(object[1].toString())
						+ Integer.parseInt(object[2].toString())
						+ Integer.parseInt(object[3].toString()));
				keySectionStatDto.setNum4(Integer.parseInt(object[4].toString()));
				keySectionStatDto.setNum5(Integer.parseInt(object[5].toString()));
				keySectionStatDto.setNum6(Integer.parseInt(object[6].toString()));
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

	/**
	 * 统计  行政区划 和直辖单位 的保密要害部门  的sql语句
	 * @param layer
	 * @return
	 */
	private StringBuffer getKeySectionDistrictSql(String layer){
		StringBuffer sb = new StringBuffer();

		sb.append(" SELECT c.district_name,IFNULL(b.num4,0)AS num4, IFNULL(b.num5,0)AS num5, IFNULL(b.num6,0)AS num6, IFNULL(a.num1,0)AS num1, IFNULL(a.num2,0)AS num2, IFNULL(a.num3,0)AS num3, IFNULL(c.district_code,0)AS district_code FROM ");
		//行政区划名字
		sb.append(" (SELECT d.district_name,d.district_code FROM sys_district d where d.layer = '"+layer+"')AS c ");
		sb.append(" LEFT JOIN ");
		//行政区划统计
		sb.append(" (SELECT IFNULL(SUM(CASE WHEN secrecy_level=1 THEN fcount END),0)AS num1,IFNULL(SUM(CASE WHEN secrecy_level=2 THEN fcount END),0)AS num2,SUM(CASE WHEN secrecy_level=3 THEN fcount END)AS num3 FROM( ");
		sb.append(" SELECT secrecy_level, count(secrecy_level)AS  fcount FROM bm_key_section WHERE secrecy_status!=1 AND ");
		sb.append(" organ_id IN (SELECT organ_id FROM sys_organization WHERE district_code IN (SELECT district_code FROM sys_district WHERE layer LIKE '"+layer+"%'))group by secrecy_level)AS aa )a  ");
		sb.append(" on 1=1  LEFT JOIN ");
		//直辖单位统计
		sb.append(" (SELECT IFNULL(SUM(CASE WHEN secrecy_level=1 THEN fcount END),0)AS num4,IFNULL(SUM(CASE WHEN secrecy_level=2 THEN fcount END),0)AS num5,SUM(CASE WHEN secrecy_level=3 THEN fcount END)AS num6 FROM( ");
		sb.append(" SELECT secrecy_level, count(secrecy_level)AS  fcount FROM bm_key_section WHERE secrecy_status!=1 AND ");
		sb.append(" organ_id IN (SELECT organ_id FROM sys_organization WHERE district_code IN (SELECT district_code FROM sys_district WHERE layer = '"+layer+"'))group by secrecy_level)AS bb)b  ");
		sb.append(" ON 1=1 ");

		return sb;
	}

	/**
	 * 统计  按照单位统计
	 * @param district
	 * @param needTotal
	 * @param organ
	 * @return
	 */
	public List<KeySectionStatDto> queryOrganByDistrict(District district, boolean needTotal, Organ organ) {

		List<KeySectionStatDto> keySectionStatDtoList = new ArrayList<KeySectionStatDto>();
		String receiveStr = "";
		StringBuffer sb = new StringBuffer();

		// 查询条件-单位名称
		if(organ!=null && organ.getOrganName()!= null && !"".equals(organ.getOrganName())){
			receiveStr = " and o.organ_name like '%" + organ.getOrganName() +"%'";
		}
		sb.append("SELECT o.organ_name,IFNULL(o3.num1,0)AS num1, IFNULL(o3.num2,0)AS num2, IFNULL(o3.num3,0)AS num3, o.organ_id FROM sys_organization o LEFT JOIN ");
		sb.append("(SELECT organ_id,IFNULL(SUM(CASE WHEN secrecy_level=1 THEN fcount END),0)AS num1,IFNULL(SUM(CASE WHEN secrecy_level=2 THEN fcount END),0)AS num2, ");
		sb.append("SUM(CASE WHEN secrecy_level=3 THEN fcount END)AS num3 FROM(SELECT organ_id, secrecy_level,count(secrecy_level)AS fcount FROM	bm_key_section  ");
		if(district!=null && district.getDistrictCode()!=null) {
			sb.append("WHERE secrecy_status!=1 AND organ_id IN (SELECT organ_id FROM sys_organization WHERE district_code='"+district.getDistrictCode()+"') GROUP BY organ_id,secrecy_level)AS a GROUP BY organ_id)AS o3 on o3.organ_id = o.organ_id  ");
			sb.append(" WHERE o.organ_id IN (SELECT organ_id FROM sys_organization WHERE district_code='"+district.getDistrictCode()+"') ").append(receiveStr);
		}else if(district==null && organ!=null && organ.getOrganId()!=null){
			sb.append("WHERE secrecy_status!=1 AND organ_id='"+organ.getOrganId()+"' GROUP BY organ_id,secrecy_level)AS a GROUP BY organ_id)AS o3 on o3.organ_id = o.organ_id  ");
			sb.append(" WHERE o.organ_id = '"+organ.getOrganId()+"' ").append(receiveStr);
		}

		sb.append("ORDER BY o.organ_id DESC ");
		List<Object[]> objectList = getPersistProxy().getOrmPersistence().findByNativeQuery(sb.toString());

		for (Object[] object : objectList) {//循环
			KeySectionStatDto keySectionStatDto = new KeySectionStatDto();
			keySectionStatDto.setName(object[0].toString());
			keySectionStatDto.setNum1(object[1] == null ? 0 : Integer.parseInt(object[1].toString()));
			keySectionStatDto.setNum2(object[2] == null ? 0 : Integer.parseInt(object[2].toString()));
			keySectionStatDto.setNum3(object[3] == null ? 0 : Integer.parseInt(object[3].toString()));
			keySectionStatDto.setId(object[4].toString());
			keySectionStatDto.setSelfTotal(keySectionStatDto.getNum1()
					+ keySectionStatDto.getNum2()
					+ keySectionStatDto.getNum3());
			keySectionStatDtoList.add(keySectionStatDto);

			if (needTotal) {
				KeySectionStatDto total = new KeySectionStatDto();//合计对象
				// TODO 以后从配置读出来
				total.setName("合计");
				total.setNum1(total.getNum1() + keySectionStatDto.getNum1());
				total.setNum2(total.getNum2() + keySectionStatDto.getNum2());
				total.setNum3(total.getNum3() + keySectionStatDto.getNum3());
				total.setSelfTotal(total.getSelfTotal() + keySectionStatDto.getSelfTotal());

				if (!keySectionStatDtoList.isEmpty()) {
					keySectionStatDtoList.add(total);
				}
			}
		}

		return keySectionStatDtoList;
	}


	/**通过行政区划对象统计   分单位显示行政区划下面的统计情况
	 * 综合统计  查询 行政区划要害部门统计  通过行政区划编码
	 * @param district  行政区划对象 , 每个行政区划必须包含  districtCode
	 * @param isGroup  是否合计
	 * @param organ   单位对象   ，这个对象可以为空，只是在模糊查询的时候我们会用到，单位的名字
	 *
	 * @return
	 */
	public List<ZongHeTongJiStatDto> count_KeySection_District(District district, boolean isGroup,Organ organ){

		//返回值
		List<ZongHeTongJiStatDto> list = new ArrayList<ZongHeTongJiStatDto>();

		if(district!=null && district.getDistrictCode()!=null) {
			ZongHeTongJiStatDto totalMap = new ZongHeTongJiStatDto();//合计数

			//获取sql语句
			StringBuffer sb = ZongHeTongJiUtil.getOrganSqlByDistrict_ZongHeTongJi(district, "bm_key_section", "organ_id",organ);
			//查询
			List<Map<String, Object>> cmpList = this.getPersistProxy().getJdbcPersistence().findList(sb.toString(), new HashMap<String, Object>());
			if(cmpList!=null) {
				Iterator<Map<String, Object>> it = cmpList.iterator();
				while(it.hasNext()) {
					Map<String, Object> cmp = it.next();
					//类型转换
					ZongHeTongJiStatDto stat = ZongHeTongJiUtil.map2Dto(cmp);
					list.add(stat);

					if(isGroup) {//是否合计  如果是  ，这里将计算合计列
						totalMap = ZongHeTongJiUtil.setTotalRow(totalMap, stat);
					}
				}
			}

			if(isGroup && !list.isEmpty()) {//是否合计  如果是  ，这里将计算合计列
				list.add(totalMap);
			}
		}

		return list;
	}


	/**
	 * @since 1.0
	 * @version 1.0
	 * @param psm PageSortModel
	 * @param organ 当前登录单位
	 * @param keySection keySection
	 * @return List<KeySection>
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<KeySection> queryList(PageSortModel psm, Organ organ,
			KeySection keySection) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("From KeySection k where 1=1 and k.secrecyStatus!=1 ");

		if(organ!=null && organ.getOrganId()!=null) {
			hql.append(" and k.organ.organId = :organId");
			params.put("organId", organ.getOrganId());
		}

		// 按创建时间降序排列
		if (keySection != null) {
			if (LangUtils.isNotEmpty(keySection.getPrincipal())) {
				hql.append(" and k.principal.name like :principal");
				params.put("principal", "%" + keySection.getPrincipal() + "%");
			}
			if (keySection.getDepartment()!=null && keySection.getDepartment().getDepartmentName()!=null) {
				hql.append(" and k.department.departmentName like :departmentName");
				params.put("departmentName", "%" + keySection.getDepartment().getDepartmentName() + "%");
			}
			if (LangUtils.isNotEmpty(keySection.getSecrecyLevel())) {
				hql.append(" and k.secrecyLevel = :secrecyLevel");
				params.put("secrecyLevel", keySection.getSecrecyLevel());
			}
			if (LangUtils.isNotEmpty(keySection.getPhone())) {
				hql.append(" and k.phone like :phone");
				params.put("phone", "%" + keySection.getPhone() + "%");
			}
		}
		hql.append(" order by k.createTime desc");

		if(psm == null){
			return (List<KeySection>) this.findList(hql.toString(), params);
		}else {
			return (List<KeySection>) this.findList(hql.toString(), params, psm);
		}

	}

	/**
	 * @param psm PageSortModel
	 * @param district 行政区划对象
	 * @param keySection keySection
	 * @return List<KeySection>
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<KeySection> queryList(PageSortModel psm, District district,
			KeySection keySection) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("From KeySection k where 1=1 and k.secrecyStatus!=1 ");

		if(district!=null && district.getDistrictCode()!=null) {
			hql.append(" and k.organ.district.districtCode = :districtCode");
			params.put("districtCode", district.getDistrictCode());
		}

		// 按创建时间降序排列
		if (keySection != null) {
			if (LangUtils.isNotEmpty(keySection.getPrincipal())) {
				hql.append(" and k.principal.name like :principal");
				params.put("principal", "%" + keySection.getPrincipal() + "%");
			}
			if (keySection.getDepartment()!=null && keySection.getDepartment().getDepartmentName()!=null) {
				hql.append(" and k.department.departmentName like :departmentName");
				params.put("departmentName", "%" + keySection.getDepartment().getDepartmentName() + "%");
			}
			if (LangUtils.isNotEmpty(keySection.getSecrecyLevel())) {
				hql.append(" and k.secrecyLevel = :secrecyLevel");
				params.put("secrecyLevel", keySection.getSecrecyLevel());
			}
			if (LangUtils.isNotEmpty(keySection.getPhone())) {
				hql.append(" and k.phone like :phone");
				params.put("phone", "%" + keySection.getPhone() + "%");
			}
		}
		hql.append(" order by k.createTime desc");

		if(psm == null){
			return (List<KeySection>) this.findList(hql.toString(), params);
		}else {
			return (List<KeySection>) this.findList(hql.toString(), params, psm);
		}

	}

}
