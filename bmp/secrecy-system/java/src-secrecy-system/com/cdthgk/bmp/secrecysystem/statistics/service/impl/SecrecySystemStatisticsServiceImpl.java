/**
 *
 */
package com.cdthgk.bmp.secrecysystem.statistics.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.action.BmpAction;
import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.bmp.secrecysystem.secrecycomputer.vo.SecrecyComputer;
import com.cdthgk.bmp.secrecysystem.statistics.service.SecrecySystemStatisticsService;
import com.cdthgk.platform.dictionary.context.DictionaryContext;
import com.cdthgk.platform.dictionary.domain.DictionaryOption;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;

/**
 * <p>
 * 类的说明放这里  2013-8-9 上午11:20:32
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 *
 * @author 宋亚非
 */
public class SecrecySystemStatisticsServiceImpl extends BmpServiceImpl<Object, String>
		implements SecrecySystemStatisticsService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Map<String, String>> getComputerCount(District district, Organ organ, boolean flag) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("from SecrecyComputer d  where 1=1" );
		// false ： 查看本单位； true ：查看行政区
		if (flag) {
			hql.append(" and d.createOrgan.district.layer like :layer");
			params.put("layer", district.getLayer() + "%");
		} else {
			hql.append(" and d.createOrgan.organId = :organId");
			params.put("organId", organ.getOrganId());
		}

		//计算机解除标记
		hql.append(" and ( d.secrecyStatus = " + BmpAction.SECRECY_STATUS_NOW + " or  d.secrecyStatus = " + BmpAction.SECRECY_STATUS_CHANGE + ")");

		List<SecrecyComputer> secrecyComputerList = this.getPersistProxy().getOrmPersistence().findList(hql.toString(), params);
		List<DictionaryOption> optionList = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "computer_type");
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for (DictionaryOption dictionaryOption : optionList) {
			Map<String, String> typeMap = new HashMap<String, String>();
			int level1 = 0;
			int level2 = 0;
			int level3 = 0;
			int count = 0;
			for (SecrecyComputer secrecyComputer : secrecyComputerList) {
				if(secrecyComputer.getComputerType().equals(dictionaryOption.getOptionValue())){
					count ++;
					// 1绝密2机密3秘密
					if (secrecyComputer.getSecrecyLevel() == 1) {
						level1 ++;
					} else if (secrecyComputer.getSecrecyLevel() == 2) {
						level2 ++;
					} else if (secrecyComputer.getSecrecyLevel() == 3) {
						level3 ++;
					}
				}
			}
			typeMap.put("name", dictionaryOption.getOptionText());
			typeMap.put("type", dictionaryOption.getOptionValue()+"");
			typeMap.put("level1", level1 +"");
			typeMap.put("level2", level2+"");
			typeMap.put("level3", level3+"");
			typeMap.put("count", count+"");
			list.add(typeMap);
		}
		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Map<String, String>> getComputerThreeCount(District district, Organ organ, boolean flag) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder sql = new StringBuilder("from SecrecyComputer d  where 1=1" );
		// false ： 查看本单位； true ：查看行政区
		if (flag) {
			sql.append(" and d.createOrgan.district.layer like :layer");
			params.put("layer", district.getLayer() + "%");
		} else {
			sql.append(" and d.createOrgan.organId = :organId");
			params.put("organId", organ.getOrganId());
		}

		//计算机解除标记
		sql.append(" and d.secrecyStatus = :secrecyStatus");
		params.put("secrecyStatus", BmpAction.SECRECY_STATUS_NOW);

		List<SecrecyComputer> secrecyComputerList = this.getPersistProxy().getOrmPersistence().findList(sql.toString(), params);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		// 是否3和1
		for (int i = 0; i < 2; i++) {
			Map<String, String> typeMap = new HashMap<String, String>();
			int count = 0;
			int level1 = 0;
			int level2 = 0;
			int level3 = 0;
			for (SecrecyComputer secrecyComputer : secrecyComputerList) {
				if(secrecyComputer.getIsFanghu() == i){
					count ++;
					// 1绝密2机密3秘密
					if (secrecyComputer.getSecrecyLevel() == 1) {
						level1 ++;
					} else if (secrecyComputer.getSecrecyLevel() == 2) {
						level2 ++;
					} else if (secrecyComputer.getSecrecyLevel() == 3) {
						level3 ++;
					}
				}
			}
			typeMap.put("name", i == 0 ? "否" : "是");
			typeMap.put("level1", level1 +"");
			typeMap.put("level2", level2+"");
			typeMap.put("level3", level3+"");
			typeMap.put("count", count+"");
			list.add(typeMap);
		}
		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Map<String, String>> getComputerWaiLianCount(District district, Organ organ, boolean flag) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder sql = new StringBuilder("from SecrecyComputer d  where 1=1" );
		// false ： 查看本单位； true ：查看行政区
		if (flag) {
			sql.append(" and d.createOrgan.district.layer like :layer");
			params.put("layer", district.getLayer() + "%");
		} else {
			sql.append(" and d.createOrgan.organId = :organId");
			params.put("organId", organ.getOrganId());
		}

		//计算机解除标记
		sql.append(" and d.secrecyStatus = :secrecyStatus");
		params.put("secrecyStatus", BmpAction.SECRECY_STATUS_NOW);

		List<SecrecyComputer> secrecyComputerList = this.getPersistProxy().getOrmPersistence().findList(sql.toString(), params);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		// 是否3和1
		for (int i = 0; i < 2; i++) {
			Map<String, String> typeMap = new HashMap<String, String>();
			int count = 0;
			int level1 = 0;
			int level2 = 0;
			int level3 = 0;
			for (SecrecyComputer secrecyComputer : secrecyComputerList) {
				if(secrecyComputer.getIsWailian() == i){
					count ++;
					// 1绝密2机密3秘密
					if (secrecyComputer.getSecrecyLevel() == 1) {
						level1 ++;
					} else if (secrecyComputer.getSecrecyLevel() == 2) {
						level2 ++;
					} else if (secrecyComputer.getSecrecyLevel() == 3) {
						level3 ++;
					}
				}
			}
			typeMap.put("name", i == 0 ? "否" : "是");
			typeMap.put("level1", level1 +"");
			typeMap.put("level2", level2+"");
			typeMap.put("level3", level3+"");
			typeMap.put("count", count+"");
			list.add(typeMap);
		}
		return list;
	}

	/**单位明细
	 * 涉密计算机总数统计
	 * @param district
	 * @return
	 */
	public List<Map<String,Object>> getSecrecyComputer_OrganDetail(District district) {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();

		StringBuffer sb = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();

		sb.append(" SELECT organ_id,organ_name FROM sys_organization WHERE district_code IN(SELECT district_code FROM sys_district WHERE layer = :layer) ");
		params.put("layer", district.getLayer());
		list = this.getPersistProxy().getJdbcPersistence().findList(sb.toString(), params);

		return list;
	}


	/*****************************************************************移动存储介质统计**************BEGIN**********************************************************/
	/**（本单位）
	 * 本单位统计
	 * @param organ
	 * @return
	 */
	public List<Map<String,Object>> getMobileStorageMedia_CurrentOrgan(Organ organ){

		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		if(organ==null) {
			return list;
		}
		StringBuffer sb = new StringBuffer(" AND create_organ = :organ ");
		Map<String, Object> params = new HashMap<String, Object>();
		//得到sql语句
		StringBuffer hql = getSQL_MobileStorageMedia(sb);
		params.put("organ", organ.getOrganId());
		//统计出结果集
		list = this.getPersistProxy().getJdbcPersistence().findList(hql.toString(), params);
		//查询总数
		Map<String, Object> cmp = getTotal_MobileStorageMedia(sb, params);
		//将总数加入到统计的结果集中
		Map<String, Object> objMap = new HashMap<String, Object>();
		if(cmp!=null && cmp.size()!=0) {
			objMap.put("media_name", "合计");
			objMap.put("a1", cmp.get("a1"));
			objMap.put("a2", cmp.get("a2"));
			objMap.put("a3", cmp.get("a3"));
			objMap.put("total", cmp.get("total"));
			list.add(objMap);
		}
		return list;
	}

	/**(行政区划)
	 * 行政区划统计
	 * @param district
	 * @return
	 */
	public List<Map<String,Object>> getMobileStorageMedia_District(District district){

		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		if(district==null) {
			return list;
		}
		StringBuffer sb = new StringBuffer(" AND create_organ IN(SELECT organ_id FROM sys_organization WHERE district_code IN (SELECT district_code FROM sys_district WHERE layer like :layer)) ");
		Map<String, Object> params = new HashMap<String, Object>();
		//得到sql语句
		StringBuffer hql = getSQL_MobileStorageMedia(sb);
		params.put("layer", district.getLayer()+"%");
		//统计出结果集
		list = this.getPersistProxy().getJdbcPersistence().findList(hql.toString(), params);
		//查询总数
		Map<String, Object> cmp = getTotal_MobileStorageMedia(sb, params);
		//将总数加入到统计的结果集中
		Map<String, Object> objMap = new HashMap<String, Object>();
		if(cmp!=null && cmp.size()!=0) {
			objMap.put("media_name", "合计");
			objMap.put("a1", cmp.get("a1"));
			objMap.put("a2", cmp.get("a2"));
			objMap.put("a3", cmp.get("a3"));
			objMap.put("total", cmp.get("total"));
			list.add(objMap);
		}
		return list;
	}

	/**(直辖单位)
	 * 直辖单位统计
	 * @param district
	 * @return
	 */
	public List<Map<String,Object>> getMobileStorageMedia_Layer(District district){

		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		if(district==null) {
			return list;
		}
		StringBuffer sb = new StringBuffer(" AND create_organ IN(SELECT organ_id FROM sys_organization WHERE district_code IN (SELECT district_code FROM sys_district WHERE layer = :layer)) ");
		Map<String, Object> params = new HashMap<String, Object>();
		//得到sql语句
		StringBuffer hql = getSQL_MobileStorageMedia(sb);
		params.put("layer", district.getLayer());
		//统计出结果集
		list = this.getPersistProxy().getJdbcPersistence().findList(hql.toString(), params);
		//查询总数
		Map<String, Object> cmp = getTotal_MobileStorageMedia(sb, params);
		//将总数加入到统计的结果集中
		Map<String, Object> objMap = new HashMap<String, Object>();
		if(cmp!=null && cmp.size()!=0) {
			objMap.put("media_name", "合计");
			objMap.put("a1", cmp.get("a1"));
			objMap.put("a2", cmp.get("a2"));
			objMap.put("a3", cmp.get("a3"));
			objMap.put("total", cmp.get("total"));
			list.add(objMap);
		}
		return list;
	}

	/**
	 * 得到移动存储介质的sql语句
	 * @param strSQL
	 * @return  sql语句
	 */
	private StringBuffer getSQL_MobileStorageMedia(StringBuffer strSQL) {
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT media_name, a1, ROUND(a1/IF(a1+a2+a3=0,1,a1+a2+a3)*100,2)AS b1, a2, ROUND(a2/IF(a1+a2+a3=0,1,a1+a2+a3)*100,2)AS b2, a3, ROUND(a3/IF(a1+a2+a3=0,1,a1+a2+a3)*100,2)AS b3, (a1+a2+a3) AS total FROM( ");
		sb.append(" SELECT media_name, IFNULL(SUM(CASE WHEN secrecy_level=1 THEN fcount END),0)AS a1,IFNULL(SUM(CASE WHEN secrecy_level=2 THEN fcount END),0)AS a2, IFNULL(SUM(CASE WHEN secrecy_level=3 THEN fcount END),0)AS a3 FROM( ");
		sb.append(" SELECT media_name ,secrecy_level, SUM(fcount) AS fcount FROM( ");
		sb.append(" SELECT a.media_name, IFNULL(secrecy_level,0)AS secrecy_level, IFNULL(fcount,0)AS fcount FROM ");
		sb.append(" (SELECT '移动硬盘' AS media_name FROM dual UNION ALL SELECT '优盘' AS media_name FROM dual UNION ALL SELECT '其他' AS media_name FROM dual)AS a ");
		sb.append(" LEFT JOIN ");
		sb.append(" (SELECT '移动硬盘' AS media_name,  secrecy_level, 1 AS fcount FROM bm_Secrecy_MobileStorageMedia WHERE (secrecy_status=11 or secrecy_status=12) AND media_type=1 ").append(strSQL);
		sb.append(" UNION ALL ");
		sb.append(" SELECT '优盘' AS media_name,  secrecy_level, 1 AS fcount FROM bm_Secrecy_MobileStorageMedia WHERE (secrecy_status=11 or secrecy_status=12) AND media_type=2 ").append(strSQL);
		sb.append(" UNION ALL ");
		sb.append(" SELECT '其他' AS media_name,  secrecy_level, 1 AS fcount FROM bm_Secrecy_MobileStorageMedia WHERE (secrecy_status=11 or secrecy_status=12) AND media_type=3 ").append(strSQL);
		sb.append(" )AS b ON a.media_name=b.media_name)AS C GROUP BY media_name ,secrecy_level) AS D GROUP BY media_name)AS E ");
		return sb;
	}

	/**
	 * 查询总数
	 * @param strSQL
	 * @param params
	 * @return
	 */
	private Map<String, Object> getTotal_MobileStorageMedia(StringBuffer strSQL, Map<String, Object> params){
		Map<String, Object> cmp = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();

		sb.append(" SELECT a1,a2,a3,a1+a2+a3 AS total FROM(");
		sb.append(" SELECT IFNULL(SUM(CASE WHEN secrecy_level=1 THEN fcount END),0)AS a1,IFNULL(SUM(CASE WHEN secrecy_level=2 THEN fcount END),0)AS a2, IFNULL(SUM(CASE WHEN secrecy_level=3 THEN fcount END),0)AS a3 FROM( ");
		sb.append(" SELECT secrecy_level, SUM(fcount)AS fcount FROM(SELECT secrecy_level, 1 AS fcount FROM bm_Secrecy_MobileStorageMedia WHERE (secrecy_status=11 or secrecy_status=12) ").append(strSQL);
		sb.append( " )a GROUP BY secrecy_level)b )c ");
		cmp = this.getPersistProxy().getJdbcPersistence().find(sb.toString(), params);
		return cmp;
	}
	/*****************************************************************移动存储介质统计**************END***********************************************************/



	/*****************************************************************其他涉密设备统计*************BEGIN************************************************************/
	/**（本单位）
	 * 本单位统计
	 * @param organ
	 * @return
	 */
	public List<Map<String,Object>> getOthers_CurrentOrgan(Organ organ){

		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		if(organ==null) {
			return list;
		}
		StringBuffer sb = new StringBuffer(" AND create_organ = :organ ");
		Map<String, Object> params = new HashMap<String, Object>();
		//得到sql语句
		StringBuffer hql = getSQL_Others(sb);
		params.put("organ", organ.getOrganId());
		//统计出结果集
		list = this.getPersistProxy().getJdbcPersistence().findList(hql.toString(), params);
		//查询总数
		Map<String, Object> cmp = getTotal_Others(sb, params);
		//将总数加入到统计的结果集中
		Map<String, Object> objMap = new HashMap<String, Object>();
		if(cmp!=null && cmp.size()!=0) {
			objMap.put("others_name", "合计");
			objMap.put("a1", cmp.get("a1"));
			objMap.put("a2", cmp.get("a2"));
			objMap.put("a3", cmp.get("a3"));
			objMap.put("total", cmp.get("total"));
			list.add(objMap);
		}
		return list;
	}

	/**(行政区划)
	 * 行政区划统计
	 * @param district
	 * @return
	 */
	public List<Map<String,Object>> getOthers_District(District district){

		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		if(district==null) {
			return list;
		}
		StringBuffer sb = new StringBuffer(" AND create_organ IN(SELECT organ_id FROM sys_organization WHERE district_code IN (SELECT district_code FROM sys_district WHERE layer like :layer)) ");
		Map<String, Object> params = new HashMap<String, Object>();
		//得到sql语句
		StringBuffer hql = getSQL_Others(sb);
		params.put("layer", district.getLayer() + "%");
		//统计出结果集
		list = this.getPersistProxy().getJdbcPersistence().findList(hql.toString(), params);
		//查询总数
		Map<String, Object> cmp = getTotal_Others(sb, params);
		//将总数加入到统计的结果集中
		Map<String, Object> objMap = new HashMap<String, Object>();
		if(cmp!=null && cmp.size()!=0) {
			objMap.put("others_name", "合计");
			objMap.put("a1", cmp.get("a1"));
			objMap.put("a2", cmp.get("a2"));
			objMap.put("a3", cmp.get("a3"));
			objMap.put("total", cmp.get("total"));
			list.add(objMap);
		}
		return list;
	}

	/**(直辖单位)
	 * 直辖单位统计
	 * @param district
	 * @return
	 */
	public List<Map<String,Object>> getOthers_Layer(District district){

		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		if(district==null) {
			return list;
		}
		StringBuffer sb = new StringBuffer(" AND create_organ IN(SELECT organ_id FROM sys_organization WHERE district_code IN (SELECT district_code FROM sys_district WHERE layer = :layer)) ");
		Map<String, Object> params = new HashMap<String, Object>();
		//得到sql语句
		StringBuffer hql = getSQL_Others(sb);
		params.put("layer", district.getLayer());
		//统计出结果集
		list = this.getPersistProxy().getJdbcPersistence().findList(hql.toString(), params);
		//查询总数
		Map<String, Object> cmp = getTotal_Others(sb, params);
		//将总数加入到统计的结果集中
		Map<String, Object> objMap = new HashMap<String, Object>();
		if(cmp!=null && cmp.size()!=0) {
			objMap.put("others_name", "合计");
			objMap.put("a1", cmp.get("a1"));
			objMap.put("a2", cmp.get("a2"));
			objMap.put("a3", cmp.get("a3"));
			objMap.put("total", cmp.get("total"));
			list.add(objMap);
		}
		return list;
	}

	/**
	 * 得到其他涉密设备的sql语句
	 * @param strSQL
	 * @return  sql语句
	 */
	private StringBuffer getSQL_Others(StringBuffer strSQL) {
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT others_name, a1, ROUND(a1/IF(a1+a2+a3=0,1,a1+a2+a3)*100,2)AS b1, a2, ROUND(a2/IF(a1+a2+a3=0,1,a1+a2+a3)*100,2)AS b2, a3, ROUND(a3/IF(a1+a2+a3=0,1,a1+a2+a3)*100,2)AS b3, (a1+a2+a3) AS total FROM( ");
		sb.append(" SELECT others_name, IFNULL(SUM(CASE WHEN secrecy_level=1 THEN fcount END),0)AS a1,IFNULL(SUM(CASE WHEN secrecy_level=2 THEN fcount END),0)AS a2, IFNULL(SUM(CASE WHEN secrecy_level=3 THEN fcount END),0)AS a3 FROM( ");
		sb.append(" SELECT others_name ,secrecy_level, SUM(fcount) AS fcount FROM( ");
		sb.append(" SELECT a.others_name, IFNULL(secrecy_level,0)AS secrecy_level, IFNULL(fcount,0)AS fcount FROM ");
		sb.append(" (SELECT '打印机' AS others_name FROM dual UNION ALL SELECT '传真机' AS others_name FROM dual UNION ALL SELECT '复印机' AS others_name FROM dual UNION ALL SELECT '扫描仪' AS others_name FROM dual UNION ALL SELECT '其他' AS others_name FROM dual )AS a ");
		sb.append(" LEFT JOIN ");
		sb.append(" (SELECT '打印机' AS others_name, secrecy_level, 1 AS fcount FROM bm_Secrecy_Others WHERE (secrecy_status=11 or secrecy_status=12) AND secrecyothers_type=1 ").append(strSQL);
		sb.append(" UNION ALL ");
		sb.append(" SELECT '传真机' AS others_name, secrecy_level, 1 AS fcount FROM bm_Secrecy_Others WHERE (secrecy_status=11 or secrecy_status=12) AND secrecyothers_type=2 ").append(strSQL);
		sb.append(" UNION ALL ");
		sb.append(" SELECT '复印机' AS others_name, secrecy_level, 1 AS fcount FROM bm_Secrecy_Others WHERE (secrecy_status=11 or secrecy_status=12) AND secrecyothers_type=3 ").append(strSQL);
		sb.append(" UNION ALL ");
		sb.append(" SELECT '扫描仪' AS others_name, secrecy_level, 1 AS fcount FROM bm_Secrecy_Others WHERE (secrecy_status=11 or secrecy_status=12) AND secrecyothers_type=4 ").append(strSQL);
		sb.append(" UNION ALL ");
		sb.append(" SELECT '其他' AS others_name, secrecy_level, 1 AS fcount FROM bm_Secrecy_Others WHERE (secrecy_status=11 or secrecy_status=12) AND secrecyothers_type=5 ").append(strSQL);
		sb.append(" )AS b ON a.others_name=b.others_name)AS C GROUP BY others_name ,secrecy_level) AS D GROUP BY others_name)AS E ");
		return sb;
	}

	/**
	 * 查询总数
	 * @param strSQL
	 * @param params
	 * @return
	 */
	private Map<String, Object> getTotal_Others(StringBuffer strSQL, Map<String, Object> params){
		Map<String, Object> cmp = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();

		sb.append(" SELECT a1,a2,a3,a1+a2+a3 AS total FROM(");
		sb.append(" SELECT IFNULL(SUM(CASE WHEN secrecy_level=1 THEN fcount END),0)AS a1,IFNULL(SUM(CASE WHEN secrecy_level=2 THEN fcount END),0)AS a2, IFNULL(SUM(CASE WHEN secrecy_level=3 THEN fcount END),0)AS a3 FROM( ");
		sb.append(" SELECT secrecy_level, SUM(fcount)AS fcount FROM(SELECT secrecy_level, 1 AS fcount FROM bm_Secrecy_Others WHERE (secrecy_status=11 or secrecy_status=12) ").append(strSQL);
		sb.append( " )a GROUP BY secrecy_level)b)c ");
		cmp = this.getPersistProxy().getJdbcPersistence().find(sb.toString(), params);
		return cmp;
	}
	/*****************************************************************其他涉密设备统计***********END**************************************************************/

	/*************************************涉密网络统计 - start**************************************************/
	/* (non-Javadoc)
	 * @see com.cdthgk.bmp.secrecysystem.statistics.service.SecrecySystemStatisticsService#getNetwork_CurrentOrgan(com.cdthgk.platform.organization.organ.domain.Organ, java.lang.Integer)
	 */
	@Override
	public List<Map<String, Map<Integer, Integer>>> getNetwork_CurrentOrgan(
			List<DictionaryOption> optionsList ,Organ organ, Integer secrecyStatusFlag) {
		// TODO Auto-generated method stub
		List<Map<String, Map<Integer, Integer>>> returnList = new ArrayList<Map<String,Map<Integer,Integer>>>();
		for( DictionaryOption dicoption : optionsList ){
			Map<String, Map<Integer, Integer>> aDictionaryCount = new HashMap<String, Map<Integer,Integer>>();
			List<Map<String, Object>> resultMap = new ArrayList<Map<String,Object>>();
			Map<String, Object> params = new HashMap<String, Object>();
			StringBuilder hql = new StringBuilder();
			hql.append("SELECT new map(snw.secrecyLevel as secrecyLevel, COUNT(snw) as num) FROM SecrecyNetwork snw WHERE secrecyStatus = :secrecyStatus ");
			params.put("secrecyStatus", secrecyStatusFlag);
			hql.append("AND snw.networkType = :networkType ");
			params.put("networkType", dicoption.getOptionValue());

			hql.append("AND snw.createOrgan.organId = :createOrgan ");
			params.put("createOrgan", organ.getOrganId());

			hql.append("GROUP BY snw.secrecyLevel");
			resultMap = this.getPersistProxy().getOrmPersistence().findList(hql.toString(), params);
			if( resultMap!=null && resultMap.size()>0 ){
				Map<Integer, Integer> aSecrecyLevelCount = new HashMap<Integer, Integer>();
				for( int i=0; i<resultMap.size(); i++ ){
					aSecrecyLevelCount.put(Integer.parseInt(resultMap.get(i).get("secrecyLevel").toString()), Integer.parseInt(resultMap.get(i).get("num").toString()));
				}
				aDictionaryCount.put(dicoption.getOptionText(), aSecrecyLevelCount);
			}else{
				aDictionaryCount.put(dicoption.getOptionText(), null);
			}
			returnList.add(aDictionaryCount);
		}
		return returnList;
	}

	/* (non-Javadoc)
	 * @see com.cdthgk.bmp.secrecysystem.statistics.service.SecrecySystemStatisticsService#getNetwork_District(com.cdthgk.platform.district.domain.District, java.lang.Integer)
	 */
	@Override
	public List<Map<String, Map<Integer, Integer>>> getNetwork_District(
			List<DictionaryOption> optionsList ,District district, Integer secrecyStatusFlag) {
		// TODO Auto-generated method stub
		List<Map<String, Map<Integer, Integer>>> returnList = new ArrayList<Map<String,Map<Integer,Integer>>>();
		for( DictionaryOption dicoption : optionsList ){
			Map<String, Map<Integer, Integer>> aDictionaryCount = new HashMap<String, Map<Integer,Integer>>();
			List<Map<String, Object>> resultMap = new ArrayList<Map<String,Object>>();
			Map<String, Object> params = new HashMap<String, Object>();
			StringBuffer hql = new StringBuffer();
			hql.append("SELECT new map(snw.secrecyLevel as secrecyLevel, COUNT(snw) as num) FROM SecrecyNetwork snw WHERE secrecyStatus = :secrecyStatus ");
			params.put("secrecyStatus", secrecyStatusFlag);
			hql.append("AND snw.networkType = :networkType ");
			params.put("networkType", dicoption.getOptionValue());

			hql.append(" and snw.createOrgan.district.districtCode = :districtCode");
			params.put("districtCode", district.getDistrictCode());

			hql.append("GROUP BY snw.secrecyLevel");
			resultMap = this.getPersistProxy().getOrmPersistence().findList(hql.toString(), params);
			if( resultMap!=null && resultMap.size()>0 ){
				Map<Integer, Integer> aSecrecyLevelCount = new HashMap<Integer, Integer>();
				for( int i=0; i<resultMap.size(); i++ ){
					aSecrecyLevelCount.put(Integer.parseInt(resultMap.get(i).get("secrecyLevel").toString()), Integer.parseInt(resultMap.get(i).get("num").toString()));
				}
				aDictionaryCount.put(dicoption.getOptionText(), aSecrecyLevelCount);
			}else{
				aDictionaryCount.put(dicoption.getOptionText(), null);
			}
			returnList.add(aDictionaryCount);
		}
		return returnList;
	}

	/* (non-Javadoc)
	 * @see com.cdthgk.bmp.secrecysystem.statistics.service.SecrecySystemStatisticsService#getNetwork_Layer(com.cdthgk.platform.district.domain.District, java.lang.Integer)
	 */
	@Override
	public List<Map<String, Map<Integer, Integer>>> getNetwork_Layer(
			List<DictionaryOption> optionsList ,District district, Integer secrecyStatusFlag) {
		// TODO Auto-generated method stub
		List<Map<String, Map<Integer, Integer>>> returnList = new ArrayList<Map<String,Map<Integer,Integer>>>();
		for( DictionaryOption dicoption : optionsList ){
			Map<String, Map<Integer, Integer>> aDictionaryCount = new HashMap<String, Map<Integer,Integer>>();
			List<Map<String, Object>> resultMap = new ArrayList<Map<String,Object>>();
			Map<String, Object> params = new HashMap<String, Object>();
			StringBuffer hql = new StringBuffer();
			hql.append("SELECT new map(snw.secrecyLevel as secrecyLevel, COUNT(snw) as num) FROM SecrecyNetwork snw WHERE secrecyStatus = :secrecyStatus ");
			params.put("secrecyStatus", secrecyStatusFlag);
			hql.append("AND snw.networkType = :networkType ");
			params.put("networkType", dicoption.getOptionValue());

			hql.append(" and snw.createOrgan.district.layer like :layer ");
			params.put("layer", district.getLayer()+"%");

			hql.append("GROUP BY snw.secrecyLevel");
			resultMap = this.getPersistProxy().getOrmPersistence().findList(hql.toString(), params);
			if( resultMap!=null && resultMap.size()>0 ){
				Map<Integer, Integer> aSecrecyLevelCount = new HashMap<Integer, Integer>();
				for( int i=0; i<resultMap.size(); i++ ){
					aSecrecyLevelCount.put(Integer.parseInt(resultMap.get(i).get("secrecyLevel").toString()), Integer.parseInt(resultMap.get(i).get("num").toString()));
				}
				aDictionaryCount.put(dicoption.getOptionText(), aSecrecyLevelCount);
			}else{
				aDictionaryCount.put(dicoption.getOptionText(), null);
			}
			returnList.add(aDictionaryCount);
		}
		return returnList;
	}

	/* (non-Javadoc)
	 * @see com.cdthgk.bmp.secrecysystem.statistics.service.SecrecySystemStatisticsService#getNetwork_DistrictOrgan(java.util.List, com.cdthgk.platform.district.domain.District, java.lang.Integer)
	 */
	@Override
	public List<Map<String, Map<Integer, Integer>>> getNetwork_DistrictOrgan(
			List<DictionaryOption> optionsList, District district,
			Integer secrecyStatusFlag) {
		// TODO Auto-generated method stub
		List<Map<String, Map<Integer, Integer>>> returnList = new ArrayList<Map<String,Map<Integer,Integer>>>();
		for( DictionaryOption dicoption : optionsList ){
			Map<String, Map<Integer, Integer>> aDictionaryCount = new HashMap<String, Map<Integer,Integer>>();
			List<Map<String, Object>> resultMap = new ArrayList<Map<String,Object>>();
			Map<String, Object> params = new HashMap<String, Object>();
			StringBuffer hql = new StringBuffer();
			hql.append("SELECT new map( snw.secrecyLevel as secrecyLevel, COUNT(snw) as num) FROM SecrecyNetwork snw WHERE secrecyStatus = :secrecyStatus ");
			params.put("secrecyStatus", secrecyStatusFlag);
			hql.append("AND snw.networkType = :networkType ");
			params.put("networkType", dicoption.getOptionValue());

			hql.append(" and snw.createOrgan.district.districtCode = :districtCode ");
			params.put("districtCode", district.getDistrictCode());

			hql.append("GROUP BY snw.secrecyLevel");
			resultMap = this.getPersistProxy().getOrmPersistence().findList(hql.toString(), params);
			if( resultMap!=null && resultMap.size()>0 ){
				Map<Integer, Integer> aSecrecyLevelCount = new HashMap<Integer, Integer>();
				for( int i=0; i<resultMap.size(); i++ ){
					aSecrecyLevelCount.put(Integer.parseInt(resultMap.get(i).get("secrecyLevel").toString()), Integer.parseInt(resultMap.get(i).get("num").toString()));
				}
				aDictionaryCount.put(dicoption.getOptionText(), aSecrecyLevelCount);
			}else{
				aDictionaryCount.put(dicoption.getOptionText(), null);
			}
			returnList.add(aDictionaryCount);
		}
		return returnList;
	}
	/**********************************涉密网络统计 - end******************************************************/

}
