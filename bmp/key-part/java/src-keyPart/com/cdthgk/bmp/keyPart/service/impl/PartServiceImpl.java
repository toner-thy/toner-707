package com.cdthgk.bmp.keyPart.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.bmp.keyPart.service.PartService;
import com.cdthgk.bmp.keyPart.vo.Part;
import com.cdthgk.bmp.secrecycountryitem.qo.ZongHeTongJiStatDto;
import com.cdthgk.bmp.secrecycountryitem.qo.ZongHeTongJiUtil;
import com.cdthgk.common.lang.LangUtils;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;

/**
 * <p>
 * PartServiceImpl
 * 类的说明放这里
 * </p>
 * 刘椿成 2012-12-15 13:26:59
 * </p>
 * <blockquote>
 * <h4>历史修改记录</h4>
 * <ul>
 * <li>修改人 修改时间 修改描述
 * </ul>
 * </blockquote>
 * <p>
 * copyright liucc 2012, all rights reserved.
 * </p>
 * @author 刘椿成
 * @since 1.0
 * @version 1.0
 */
public class PartServiceImpl extends BmpServiceImpl<Part, String> implements PartService {

	// 构造器
	/** 默认构造器 */
	PartServiceImpl() {
	}


	/**(本单位)
	 * 本单位统计  保密要害部位    按照要害部位的密级
	 * 梁文杰 2013-07-18
	 * @param organId 单位id
	 * @return
	 */
	public List<Map<String, Object>> count_KeyPart_By_Secrecy_Level(String organId) {

		Integer total = this.getOrganPartTotal(organId);//返回 本单位的要害部位总个数

		Map<String ,Object> params = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();

		if(total==0) {
			sb.append(" SELECT A.option_text, A.option_value, 0 AS fcount, '0.00' AS total ");
		}else {
			sb.append(" SELECT A.option_text, A.option_value, IFNULL(B.fcount,0)AS fcount, ROUND(IFNULL((fcount/"+total+")*100,0),2) AS total ");
		}

		sb.append(" FROM ");
		sb.append(" ( ");//A表  查询基础字典表，查询出保密要害部位的密级
		sb.append("  SELECT option_text,option_value ");
		sb.append("  FROM sys_dictionary_option ");
		sb.append("  WHERE status=1 AND field_id=(SELECT field_id FROM sys_dictionary_field ");
		sb.append("  WHERE table_id=(SELECT table_id FROM sys_dictionary_table WHERE table_code='bmp') ");
		sb.append("  AND field_code='secrecy_level_section') ");
		sb.append(" )AS A ");
		sb.append(" LEFT JOIN");
		sb.append(" ( ");//B表   按照密级统计保密要害部位的个数
		sb.append("  SELECT part.secrecy_level, SUM(part.fcount) AS fcount ");
		sb.append("  FROM (SELECT key_part_id,secrecy_level, 1 AS fcount FROM bm_key_part WHERE organ_id=:organId AND secrecy_status=0)AS part");//orgid过滤
		sb.append("  GROUP BY part.secrecy_level");
		sb.append(" )AS B");
		sb.append(" ON A.option_value = B.secrecy_level ");

		/******新的查询方式    下个版本升级*****/
//		sb.append(" SELECT department_id, department_name, a1, CONCAT(ROUND(a1/IF(a1+a2+a3=0,1,a1+a2+a3)*100,2),'%')AS b1, ");
//		sb.append(" a2, CONCAT(ROUND(a2/IF(a1+a2+a3=0,1,a1+a2+a3)*100,2),'%')AS b2 ,a3, CONCAT(ROUND(a3/IF(a1+a2+a3=0,1,a1+a2+a3)*100,2),'%')AS b3 , (a1+a2+a3) AS total FROM( ");
//		sb.append(" SELECT department_id, department_name, IFNULL(SUM(CASE WHEN secrecy_level='1' THEN fcount END),0)AS a1, ");
//		sb.append(" IFNULL(SUM(CASE WHEN secrecy_level='2' THEN fcount END),0)AS a2, IFNULL(SUM(CASE WHEN secrecy_level='3' THEN fcount END),0)AS a3 FROM( ");
//		sb.append(" SELECT depart.department_id, depart.department_name, part.secrecy_level, part.fcount FROM( ");
//		sb.append(" SELECT department_id, secrecy_level, SUM(fcount)as fcount FROM( ");
//		sb.append(" SELECT DISTINCT key_part_id, department_id, secrecy_level, 1 AS fcount FROM bm_key_part ");
//		sb.append(" WHERE secrecy_status=0 AND department_id IS NOT NULL AND secrecy_level IN (SELECT option_value FROM sys_dictionary_option ");
//		sb.append(" WHERE status=1 AND field_id=(SELECT field_id FROM sys_dictionary_field WHERE table_id=(SELECT table_id FROM sys_dictionary_table WHERE table_code='bmp') ");
//		sb.append(" AND field_code='secrecy_level_section')) AND organ_id = :organId)AS part GROUP BY department_id, secrecy_level)AS part ");
//		sb.append(" RIGHT JOIN (SELECT department_id,department_name FROM sys_department WHERE organ_id= :organId)AS depart ");
//		sb.append(" ON part.department_id = depart.department_id)AS a GROUP BY department_id, department_name)AS b ");


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

	/**(本单位)
	 * 返回 本单位的要害部位总个数
	 * 1.通过单位id过滤    2.通过密级是否启用过滤
	 * 梁文杰  2013-07-22
	 * @param organId  单位
	 * @return
	 */
	public Integer getOrganPartTotal(String organId) {

		Integer i = new Integer(0);
		Map<String ,Object> params = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();

		sb.append(" SELECT SUM(part.fcount) AS total ");
		sb.append(" FROM ( ");
		sb.append("  SELECT key_part_id, secrecy_level, 1 AS fcount FROM bm_key_part WHERE organ_id = :organId AND secrecy_status=0 ");
		sb.append("  AND secrecy_level IN (SELECT option_value FROM sys_dictionary_option  ");
		sb.append("  WHERE status=1 AND field_id=(SELECT field_id FROM sys_dictionary_field  ");
		sb.append("  WHERE table_id=(SELECT table_id FROM sys_dictionary_table WHERE table_code='bmp') ");
		sb.append("  AND field_code='secrecy_level_section')) ");
		sb.append(" )AS part  ");
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
//		sb.append(" SELECT DISTINCT key_part_id, department_id, secrecy_level, 1 AS fcount FROM bm_key_part ");
//		sb.append(" WHERE secrecy_status=0 AND department_id IS NOT NULL AND secrecy_level IN (SELECT option_value FROM sys_dictionary_option  ");
//		sb.append(" WHERE status=1 AND field_id=(SELECT field_id FROM sys_dictionary_field ");
//		sb.append(" WHERE table_id=(SELECT table_id FROM sys_dictionary_table WHERE table_code='bmp') ");
//		sb.append(" AND field_code='secrecy_level_section')) AND organ_id = :organId)AS A ");
//		params.put("organId", organId);
//		Map<String ,Object> map = this.getPersistProxy().getJdbcPersistence().find(sb.toString(), params);
	}


	/**(行政区划)
	 * 保密局  统计 保密要害部位的信息
	 * 梁文杰 2013-07-23
	 * @param district  行政区划
	 * @return
	 */
	public List<Map<String, Object>> count_KeyPart_By_Secrecy_Level_District(District district){
		return count_KeyPart_By_Secrecy_Level_Basic(district, 1);
	}


	/**(直辖单位)
	 * 保密局  统计 保密要害部位的信息
	 * 梁文杰 2013-07-23
	 * @param district  行政区划
	 * @return
	 */
	public List<Map<String, Object>> count_KeyPart_By_Secrecy_Level_Layer(District district){
		return count_KeyPart_By_Secrecy_Level_Basic(district, 2);
	}



	/**
	 * 保密局  统计 保密要害部位的信息
	 * @param district  行政区划
	 * @param reflag 标志信息  2表示直辖单位  1行政区划
	 * @return
	 */
	private List<Map<String, Object>> count_KeyPart_By_Secrecy_Level_Basic(District district, int reflag) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if(district==null) { //如果行政区划参数为空  那么直接返回空list
			return list;
		}
		Map<String ,Object> params = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();

		int total = this.getBasicPartTotal(district,reflag);//总数
		if(total==0) { //总数为0
			sb.append(" SELECT A.option_text, 0 AS fcount,'0.00' AS total FROM ");
		}else {
			sb.append(" SELECT A.option_text, ROUND(IFNULL(B.fcount,0),0)AS fcount, ROUND(IFNULL((fcount/"+total+")*100,0),2) AS total FROM ");
		}
		sb.append("	(SELECT option_text,option_value ");//查询出密级信息
		sb.append("	 FROM sys_dictionary_option ");
		sb.append("  WHERE status=1 AND field_id=(SELECT field_id FROM sys_dictionary_field ");
		sb.append("  WHERE table_id=(SELECT table_id FROM sys_dictionary_table WHERE table_code='bmp') ");
		sb.append("  AND field_code='secrecy_level_section') ");
		sb.append(" )AS A ");
		sb.append(" LEFT JOIN ");
		sb.append(" ( ");
		sb.append("  SELECT secrecy_level, COUNT(fcount)AS fcount FROM( ");//按照密级和行政区统计要害部位的个数
		sb.append("	 SELECT key_part_id,secrecy_level, 1 AS fcount FROM bm_key_part ");

		if(reflag==1) {//1行政区划
			sb.append("	 WHERE organ_id IN(SELECT organ_id FROM sys_organization WHERE district_code IN (SELECT district_code FROM sys_district WHERE layer like :layer)) ");
		}else {//  2表示直辖单位
			sb.append("	 WHERE organ_id IN(SELECT organ_id FROM sys_organization WHERE district_code IN (SELECT district_code FROM sys_district WHERE layer = :layer)) ");
		}

		sb.append("	 AND secrecy_status=0)AS C GROUP BY secrecy_level ");
		sb.append("	)AS B ");
		sb.append("  ON A.option_value = B.secrecy_level ");

		if(reflag==1) {//1行政区划
			params.put("layer", district.getLayer() + "%");
		}else {//  2表示直辖单位
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


	/**
	 * 保密局     获取要害部位总数
	 * @param district  行政区划
	 * @param reflag 标志信息  2表示直辖单位  1行政区划
	 * @return
	 */
	public Integer getBasicPartTotal(District district,  int reflag) {

		Integer i = new Integer(0);
		Map<String ,Object> params = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();

		sb.append(" SELECT COUNT(A.fcount)AS total FROM ( ");
		sb.append(" SELECT key_part_id,secrecy_level, 1 AS fcount FROM bm_key_part  ");

		if(reflag==1) {//1行政区划
			sb.append(" WHERE organ_id IN(SELECT organ_id FROM sys_organization WHERE district_code IN (SELECT district_code FROM sys_district WHERE layer like :layer) ) ");
		}else {//2表示直辖单位
			sb.append(" WHERE organ_id IN(SELECT organ_id FROM sys_organization WHERE district_code IN (SELECT district_code FROM sys_district WHERE layer = :layer) ) ");
		}

		sb.append(" AND secrecy_status=0  ");
		sb.append(" AND secrecy_level IN ( ");
		sb.append(" SELECT option_value FROM sys_dictionary_option WHERE status=1 ");
		sb.append(" AND field_id=(SELECT field_id FROM sys_dictionary_field WHERE ");
		sb.append(" table_id=(SELECT table_id FROM sys_dictionary_table WHERE table_code='bmp') AND field_code='secrecy_level_section') ");
		sb.append(" ) ");
		sb.append(" )A ");

		if(reflag==1) {//1行政区划
			params.put("layer", district.getLayer() + "%");
		}else {//2表示直辖单位
			params.put("layer", district.getLayer());
		}

		Map<String ,Object> map = this.getPersistProxy().getJdbcPersistence().find(sb.toString(), params);
		Object o = map.get("total");
		if(o!=null) {
			i = new Integer(Integer.parseInt(o.toString()));
		}

		return i;
	}


	/**(单位明细)
	 * 保密局  统计  各个单位明细 保密要害部位的信息
	 * @param district  行政区划对象
	 * @return
	 */
	public List<Map<String, Object>> count_KeyPart_By_Secrecy_Level_OrganDetail(District district) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if(district==null) { //如果行政区划参数为空  那么直接返回空list
			return list;
		}
		Map<String ,Object> params = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();

		sb.append(" SELECT organ_id, organ_name, a1, ROUND((a1/IF(total=0,1,total)*100),2) AS b1, a2,ROUND((a2/IF(total=0,1,total)*100),2) AS b2, ");
		sb.append(" a3, ROUND((a3/IF(total=0,1,total)*100),2) AS b3, total FROM( ");
		sb.append(" SELECT organ_id, organ_name, a1, a2, a3, (a1+a2+a3)AS total FROM( ");//计算总数  如果总数total为0  那么就证明 a1,a2,a3也是0,这里让total为1 避免分母为0的情况
		sb.append(" SELECT lel.organ_id, lel.organ_name, ");
		sb.append(" IFNULL(SUM(CASE WHEN lel.secrecy_level=3 THEN lel.fcount END),0) AS a3 , ");
		sb.append(" IFNULL(SUM(CASE WHEN lel.secrecy_level=2 THEN lel.fcount END),0) AS a2 , ");
		sb.append(" IFNULL(SUM(CASE WHEN lel.secrecy_level=1 THEN lel.fcount END),0) AS a1 ");
		sb.append(" FROM( ");
		sb.append(" SELECT base.organ_id, base.organ_name, base.secrecy_level, COUNT(base.fcount)AS fcount FROM( ");
		//查询出相关单位下的  要害部位的统计  按照密级  开始
		sb.append(" SELECT org.organ_id, org.organ_name, sec.key_part_id, sec.secrecy_level, 1 AS fcount FROM ");
		sb.append(" (SELECT organ_id,organ_name FROM sys_organization WHERE district_code IN(SELECT district_code FROM sys_district WHERE layer = :layer))AS org ");
		sb.append(" LEFT JOIN ");
		sb.append(" (SELECT key_part_id, secrecy_level, organ_id FROM bm_key_part WHERE secrecy_status=0 ");
		sb.append(" AND organ_id IN(SELECT organ_id FROM sys_organization WHERE district_code IN(SELECT district_code FROM sys_district WHERE layer = :layer)) ");
		sb.append(" AND secrecy_level IN (SELECT option_value FROM sys_dictionary_option WHERE status=1 AND field_id=(SELECT field_id FROM sys_dictionary_field WHERE ");
		sb.append(" table_id=(SELECT table_id FROM sys_dictionary_table WHERE table_code='bmp') AND field_code='secrecy_level_section')) )AS sec ");
		sb.append(" ON org.organ_id = sec.organ_id ");
		//查询出相关单位下的  要害部位的统计  按照密级  结束
		sb.append(" )AS base GROUP BY base.organ_id,base.organ_name,base.secrecy_level ");
		sb.append(" )AS lel GROUP BY organ_id,organ_name ");
		sb.append(" )AS res ");
	    sb.append(" )AS res1 ");
	    params.put("layer", district.getLayer());

		list = this.getPersistProxy().getJdbcPersistence().findList(sb.toString(), params);


		//设置合计列
		Map<String, Object> groupColunm = new  HashMap<String, Object>();
		//保密局    获取   直辖单位 汇总   要害部位总数
		List<Map<String, Object>> groupList = this.getPartTotal_OrganDetail(district);
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


	/**(单位明细)
	 * 保密局    获取 各个单位明细  要害部位总数
	 * @param district  行政区划 对象
	 * @return
	 */
	public List<Map<String, Object>> getPartTotal_OrganDetail(District district) {

		List<Map<String, Object>> map = new ArrayList<Map<String, Object>>();
		if (district == null || district.getLayer() == null) {
			return map;
		}
		Map<String ,Object> params = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();

		sb.append(" SELECT a1,a2,a3,a1+a2+a3 AS total FROM( ");
		sb.append(" SELECT IFNULL(SUM(CASE WHEN lel.secrecy_level=3 THEN lel.fcount END),0) AS a3 , ");
		sb.append(" IFNULL(SUM(CASE WHEN lel.secrecy_level=2 THEN lel.fcount END),0) AS a2 , ");
		sb.append(" IFNULL(SUM(CASE WHEN lel.secrecy_level=1 THEN lel.fcount END),0) AS a1 FROM ");
		sb.append(" (SELECT secrecy_level, COUNT(A.fcount)AS fcount FROM ( ");
		sb.append(" SELECT key_part_id,secrecy_level, 1 AS fcount FROM bm_key_part ");
		sb.append(" WHERE organ_id IN(SELECT organ_id FROM sys_organization WHERE district_code IN (SELECT district_code FROM sys_district WHERE layer = :layer) ) ");
		sb.append(" AND secrecy_status=0 AND secrecy_level IN (SELECT option_value FROM sys_dictionary_option WHERE status=1 ");
		sb.append(" AND field_id=(SELECT field_id FROM sys_dictionary_field WHERE ");
		sb.append(" table_id=(SELECT table_id FROM sys_dictionary_table WHERE table_code='bmp') AND field_code='secrecy_level_section') ");
		sb.append(" ) ");
		sb.append(" )A GROUP BY secrecy_level)AS lel)AS C");
		params.put("layer", district.getLayer());

		map = this.getPersistProxy().getJdbcPersistence().findList(sb.toString(), params);
		return map;
	}

	/**
	 * 通过要害部位id  查询该要害部位的id  和哪些表 有关联
	 * 1.要害部 位密级变更表
	 * 2.要害部位密级解除表
	 *
	 * @param partId  要害部位id
	 * @return
	 */
	public int getKeyPartRelationship(String partId){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if(partId==null || partId.equals("")) {
			return 0;
		}

		StringBuffer sb = new StringBuffer();
		Map<String ,Object> params = new HashMap<String, Object>();


		//1.要害部 位密级变更表
		sb = new StringBuffer();
		params = new HashMap<String, Object>();
		sb.append("select p.key_part_secrecy_change_id from bm_Key_part_Change p where p.key_part_id = :partId ");
		params.put("partId", partId);
		list = this.getPersistProxy().getJdbcPersistence().findList(sb.toString(), params);
		if(list!=null && list.size()>0) {
			return 1;
		}

		//2.要害部位密级解除表
		sb = new StringBuffer();
		params = new HashMap<String, Object>();
		sb.append("select p.key_part_secrecy_clear_id from bm_key_part_secrecy_clear p where p.key_part_id = :partId ");
		params.put("partId", partId);
		list = this.getPersistProxy().getJdbcPersistence().findList(sb.toString(), params);
		if(list!=null && list.size()>0) {
			return 2;
		}

		return 0;
	}


	/***************************************************************************综合统计要害部位******start********************************************************/
	/**(综合统计)
	 * 通过行政区划对象集合
	 * 综合统计  统计要害部位的  个数.
	 * 包含了   行政区划和行政区内的  要害部位 个数    都需要分密级统计出来
	 *
	 * @param districtList    行政区划对象集合    这里每个行政区划对象必须包含layer对象
	 * @param isGroup         是否需要合计数据
	 * @return 查询出来的列
	 *  district_name(行政区划名字)
	 *  district_code(行政区划编码)
	 *  a1 a2 a3 (按照行政区划统计出的个数信息)
	 *  b1 b2 b3 (按照直辖单位统计出的个数信息)
	 *
	 */
	public List<ZongHeTongJiStatDto> count_District(List<District> districtList, boolean isGroup) {

		//返回list
		List<ZongHeTongJiStatDto> list = new ArrayList<ZongHeTongJiStatDto>();
		if(districtList==null) {//如果行政区集合为空  直接返回
			return list;
		}
		ZongHeTongJiStatDto totalMap = new ZongHeTongJiStatDto();//合计数

		//轮循
		Iterator<District> it = districtList.iterator();
		while(it.hasNext()) {
			District district =  it.next();
			//获取sql语句
			StringBuffer sql = ZongHeTongJiUtil.getDistrictSql_ZongHeTongJi(district.getLayer(),"bm_key_part","organ_id");
			//查询
			Map<String, Object> cmsp = this.getPersistProxy().getJdbcPersistence().find(sql.toString(), new HashMap<String, Object>());
			//类型转换
			ZongHeTongJiStatDto stat = ZongHeTongJiUtil.map2Dto(cmsp);//类型转换;
			list.add(stat);

			if(isGroup) {//是否合计  如果是  ，这里将计算合计列
				totalMap = ZongHeTongJiUtil.setTotalRow(totalMap, stat);
			}
		}

		//如果需要合计，list又不为空的情况
		if(isGroup && !list.isEmpty()){
			list.add(totalMap);
		}

		return list;
	}

	/** (综合统计)
	 *  通过单位对象   查询出一个单位下的要害部位个数  都要按照密级统计出来
	 * @param organ  单位   这个的单位对象必须要包含organId信息
	 * @param district 行政区划对象
	 * @param needTotal 是否合计
	 * @return
	 *  查询出来的列 :
	 *  organName(本单位名字)
	 *  organId(本单位编码)
	 *  a1 a2 a3 (按照本单位统计出的个数信息)
	 */
	public List<ZongHeTongJiStatDto> count_Organ(District district, Organ organ, boolean needTotal) {

		//返回值
		List<ZongHeTongJiStatDto> list = new ArrayList<ZongHeTongJiStatDto>();
		ZongHeTongJiStatDto totalMap = new ZongHeTongJiStatDto();//合计数

		//获取sql语句
		StringBuffer sb = ZongHeTongJiUtil.getOrganSql_ZongHeTongJi(district, organ, "bm_key_part", "organ_id");
		List<Map<String, Object>> cmpList = this.getPersistProxy().getJdbcPersistence().findList(sb.toString(), new HashMap<String, Object>());//查询
		if(cmpList!=null) {
			Iterator<Map<String, Object>> it = cmpList.iterator();
			while(it.hasNext()) {
				Map<String, Object> cmp = it.next();
				//类型转换
				ZongHeTongJiStatDto stat = ZongHeTongJiUtil.map2Dto(cmp);
				list.add(stat);

				if(needTotal) {//是否合计  如果是  ，这里将计算合计列
					totalMap = ZongHeTongJiUtil.setTotalRow(totalMap, stat);
				}
			}
		}

		if(needTotal && !list.isEmpty()) {//是否合计  如果是  ，这里将计算合计列
			list.add(totalMap);
		}

		return list;
	}

	/**通过行政区划对象统计   分单位显示行政区划下面的统计情况
	 * 综合统计  查询 行政区划要害部位统计  通过行政区划编码
	 * @param district  行政区划对象 , 每个行政区划必须包含  districtCode
	 * @param isGroup  是否合计
	 * @param organ   单位对象   ，这个对象可以为空，只是在模糊查询的时候我们会用到，单位的名字
	 *
	 * @return
	 */
	public List<ZongHeTongJiStatDto> count_District(District district, boolean isGroup,Organ organ){

		//返回值
		List<ZongHeTongJiStatDto> list = new ArrayList<ZongHeTongJiStatDto>();

		if(district!=null && district.getDistrictCode()!=null) {
			ZongHeTongJiStatDto totalMap = new ZongHeTongJiStatDto();//合计数

			//获取sql语句
			StringBuffer sb = ZongHeTongJiUtil.getOrganSqlByDistrict_ZongHeTongJi(district, "bm_key_part", "organ_id",organ);
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
	 * 本单位查询  要害部位 返回list   这里只有行政区划对象，没有单位对象
	 * @param psm
	 * @param part
	 * @param district    行政区划对象
	 * @return
	 */
	public List<Part> getListPage(PageSortModel<Part> psm,Part part, District district) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("FROM Part pt WHERE 1 = 1 and pt.secrecyStatus!=1 ");

		if(district!=null && district.getLayer()!=null){
			hql.append(" and pt.organ.district.districtCode = :districtCode");
			params.put("districtCode", district.getDistrictCode());
		}

		// 按部位名称搜索
		if(part !=null && part.getPartName() !=null
			&& !"".equals(part.getPartName())){
			hql.append(" and pt.partName like :partName");
			params.put("partName", "%"+part.getPartName()+"%");
		}
		// 按涉密程度搜索
		if(part !=null && part.getSecrecyLevel() !=null && part.getSecrecyLevel()!= 4){
			hql.append(" and pt.secrecyLevel = :secrecyLevel");
			params.put("secrecyLevel", part.getSecrecyLevel());
		}
		// 按联系电话搜索
		if(part !=null && part.getPhone() !=null && !"".equals(part.getPhone())){
			hql.append(" and pt.phone like :phone");
			params.put("phone", "%"+part.getPhone()+"%");
		}
		// 按责任人搜索
		if(part !=null && part.getPerson() !=null
			&& !"".equals(part.getPerson())){
			hql.append(" and pt.person.name like :name");
			params.put("name", "%"+part.getPerson().getName()+"%");
		}
		if (part != null && part.getDepartment() != null && LangUtils.isNotEmpty(part.getDepartment().getDepartmentId())) {
			hql.append(" and pt.department.departmentId = :departmentId");
			params.put("departmentId", part.getDepartment().getDepartmentId());
		}

		// 按创建时间降序排列
		hql.append(" order by pt.createTime desc");
		List<Part> list = null;
		if(psm!=null) {
			list =(List<Part>) this.findList(hql.toString(), params,psm);
		}else {
			list =(List<Part>) this.findList(hql.toString(), params);
		}
		return list;
	}

	/***************************************************************************综合统计要害部位******end*********************************************************/


}