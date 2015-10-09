/**
 *
 */
package com.cdthgk.bmp.secrecysystem.secrecynetwork.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.action.BmpAction;
import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.bmp.secrecycountryitem.qo.ZongHeTongJiStatDto;
import com.cdthgk.bmp.secrecycountryitem.qo.ZongHeTongJiUtil;
import com.cdthgk.bmp.secrecysystem.secrecynetwork.service.SecrecyNetworkterminalService;
import com.cdthgk.bmp.secrecysystem.secrecynetwork.vo.SecrecyNetwork;
import com.cdthgk.bmp.secrecysystem.secrecynetwork.vo.SecrecyNetworkterminal;
import com.cdthgk.common.lang.StringUtils;
import com.cdthgk.platform.dictionary.domain.DictionaryOption;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

/**
 * <p>
 * 类的说明放这里 2013-8-7 下午3:35:28
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 *
 * @author 宋亚非
 */
public class SecrecyNetworkterminalServiceImpl extends
		BmpServiceImpl<SecrecyNetworkterminal, String> implements
		SecrecyNetworkterminalService {

	/* (non-Javadoc)
	 * @see com.cdthgk.bmp.secrecysystem.secrecynetwork.service.SecrecyNetworkterminalService#findListPage(ec.common.PageSortModel, com.cdthgk.bmp.secrecysystem.secrecynetwork.vo.SecrecyNetworkterminal, com.cdthgk.platform.organization.organ.domain.Organ)
	 */
	@Override
	public List<SecrecyNetworkterminal> findListPage(
			PageSortModel<SecrecyNetworkterminal> psm,
			SecrecyNetworkterminal secrecyNetworkterminal, Organ organ,
			District district, boolean flag, User currentUser, String checkLower) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("FROM SecrecyNetworkterminal snt WHERE 1 = 1");
		// false ： 查看本单位； true ：查看行政区
		if (flag) {
			// checkLower 是否查看下级 1：查看下级； 0：不查看
			if ("1".equals(checkLower)) {
				// 查看整个行政区
				hql.append(" and snt.createOrgan.district.layer like :layer");
				params.put("layer", district.getLayer() + "%");
			} else {
				// 查看直辖机关单位
				hql.append(" and snt.createOrgan.district.districtCode = :districtCode");
				params.put("districtCode", district.getDistrictCode());
			}
		} else {
			hql.append(" and snt.createOrgan.organId = :organId");
			params.put("organId", currentUser.getOrgan().getOrganId());
		}

		hql.append(" and snt.dataState = :dataState");
		params.put("dataState", 1);
		//hql.append(" and (snt.secrecyNetwork.secrecyStatus = :secrecyStatusFlag or snt.secrecyNetwork.secrecyStatus is null )");
		//params.put("secrecyStatusFlag", BmpAction.SECRECY_STATUS_NOW);

		if (secrecyNetworkterminal != null) {
			//网络名称
			if (secrecyNetworkterminal.getSecrecyNetwork() != null && StringUtils.isNotEmpty(secrecyNetworkterminal.getSecrecyNetwork().getName()) ) {
				hql.append(" and snt.secrecyNetwork.name like :name");
				params.put("name", "%" + secrecyNetworkterminal.getSecrecyNetwork().getName() + "%");
			}
			//责任人
			if (secrecyNetworkterminal.getDutyPerson() != null && StringUtils.isNotEmpty(secrecyNetworkterminal.getDutyPerson().getName())) {
				hql.append(" and snt.dutyPerson.name like :dutyPerson");
				params.put("dutyPerson", secrecyNetworkterminal.getDutyPerson().getName());
			}
			//网络密级
			if (secrecyNetworkterminal.getSecrecyLevel() != null) {
				hql.append(" and snt.secrecyLevel = :secrecyLevel");
				params.put("secrecyLevel", secrecyNetworkterminal.getSecrecyLevel());
			}
			//部门名称
			if( secrecyNetworkterminal.getDepartment()!=null && StringUtils.isNotEmpty(secrecyNetworkterminal.getDepartment().getDepartmentName()) ){
				hql.append(" and snt.department.departmentName = :departmentName");
				params.put("departmentName", secrecyNetworkterminal.getDepartment().getDepartmentName());
			}
		}
		hql.append(" order by snt.createTime desc");
		if( psm==null ){
			return this.findList(hql.toString(), params);
		}else{
			return this.findList(hql.toString(), params, psm);
		}

	}

	/* (non-Javadoc)
	 * @see com.cdthgk.bmp.secrecysystem.secrecynetwork.service.SecrecyNetworkterminalService#findTerminalListByNetwork(ec.common.PageSortModel, com.cdthgk.bmp.secrecysystem.secrecynetwork.vo.SecrecyNetwork, com.cdthgk.bmp.secrecysystem.secrecynetwork.vo.SecrecyNetworkterminal, java.lang.Integer)
	 */
	@Override
	public List<SecrecyNetworkterminal> findTerminalListByNetwork(
			PageSortModel<SecrecyNetworkterminal> psm,
			SecrecyNetwork secrecyNetwork,
			SecrecyNetworkterminal secrecyNetworkterminal,
			Integer secrecyStatusFlag) {
		// TODO Auto-generated method stub

		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("FROM SecrecyNetworkterminal snt WHERE 1 = 1");
		hql.append(" and snt.dataState = :dataState");
		params.put("dataState", 1);
		if( secrecyNetwork!=null ){
			hql.append(" and snt.secrecyNetwork.secrecyNetworkId = :secrecyNetworkId");
			params.put("secrecyNetworkId", secrecyNetwork.getSecrecyNetworkId());
		}
		//hql.append(" and snt.secrecyNetwork.secrecyStatus = :secrecyStatusFlag");
		//params.put("secrecyStatusFlag", secrecyStatusFlag);

		if (secrecyNetworkterminal != null && secrecyNetworkterminal.getSecrecyComputer()!=null) {
			//责任人
			if (secrecyNetworkterminal.getSecrecyComputer().getDutyPerson() != null && StringUtils.isNotEmpty(secrecyNetworkterminal.getSecrecyComputer().getDutyPerson().getName())) {
				hql.append(" and snt.secrecyComputer.dutyPerson.name like :dutyPerson");
				params.put("dutyPerson", secrecyNetworkterminal.getSecrecyComputer().getDutyPerson().getName());
			}
			//机型
			if( secrecyNetworkterminal.getSecrecyComputer().getComputerType()!=null ){
				hql.append(" and snt.secrecyComputer.computerType =:computerType");
				params.put("computerType", secrecyNetworkterminal.getSecrecyComputer().getComputerType());
			}

			//网络密级
			if (secrecyNetworkterminal.getSecrecyComputer().getSecrecyLevel() != null) {
				hql.append(" and snt.secrecyLevel = :secrecyLevel");
				params.put("secrecyLevel", secrecyNetworkterminal.getSecrecyComputer().getSecrecyLevel());
			}

			//是否属于要害部门部位
			if(secrecyNetworkterminal.getSecrecyComputer().getIsbelongKeydepartment()!=null ){
				hql.append(" and snt.secrecyComputer.isbelongKeydepartment = :isbelongKeydepartment");
				params.put("isbelongKeydepartment", secrecyNetworkterminal.getSecrecyComputer().getIsbelongKeydepartment());
			}
		}
		hql.append(" order by snt.createTime desc");
		if( psm==null ){
			return this.findList(hql.toString(), params);
		}else{
			return this.findList(hql.toString(), params, psm);
		}
	}

	/* (non-Javadoc)
	 * @see com.cdthgk.bmp.secrecysystem.secrecynetwork.service.SecrecyNetworkterminalService#countOthersData(java.util.List, java.util.List, com.cdthgk.platform.organization.organ.domain.Organ, java.lang.String)
	 */
	@Override
	public Map<Integer, Map<Integer, Long>> countData(
			List<DictionaryOption> optionList, List<DictionaryOption> typeList,
			Organ currentOrgan, String countType) {
		Map<Integer, Map<Integer, Long>> returnMap = new HashMap<Integer, Map<Integer,Long>>();
		// 统计
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("select new map(snt.secrecyComputer.computerType as computerType, count(snt) as num) from SecrecyNetworkterminal snt " +
				" where 1=1 and snt.dataState = :dataState ");
		params.put("dataState", 1);

		if( "organ".equals(countType) ){
			hql.append(" and snt.createOrgan.organId = :organId ");
			params.put("organId", currentOrgan.getOrganId());
		}else if( "layer".equals(countType) ){
			hql.append(" and snt.createOrgan.district.layer like :layer");
			params.put("layer", currentOrgan.getDistrict().getLayer() + "%");
		}
		hql.append(" and snt.secrecyLevel = :secrecyLevel");

		hql.append(" group by snt.secrecyComputer.computerType ");

		for( DictionaryOption dictionaryOption : optionList ){
			params.put("secrecyLevel", dictionaryOption.getOptionValue());
			List<Map<String, Object>> aResultList = this.getPersistProxy().getOrmPersistence().findList(hql.toString(), params);
			Map<Integer,Long> tmpResult = new HashMap<Integer, Long>();
			if( aResultList!=null && aResultList.size()>0 ){
				for( Map<String,Object> tmpDbResult : aResultList ){
					tmpResult.put(Integer.parseInt(tmpDbResult.get("computerType").toString()), Long.parseLong(tmpDbResult.get("num").toString()));
				}
			}
			Map<Integer, Long> aReturnResult = new HashMap<Integer, Long>();

			for( DictionaryOption typeOption : typeList ){
				if( tmpResult!=null && tmpResult.size()>0 ){
					Long tmpValue = tmpResult.get(typeOption.getOptionValue());
					if( tmpValue!=null ){
						aReturnResult.put(typeOption.getOptionValue(), tmpValue);
					}else{
						aReturnResult.put(typeOption.getOptionValue(), new Long(0));
					}
				}else{
					aReturnResult.put(typeOption.getOptionValue(), new Long(0));
				}
			}

			returnMap.put(dictionaryOption.getOptionValue(), aReturnResult);
		}
		return returnMap;
	}

	/* (non-Javadoc)
	 * @see com.cdthgk.bmp.secrecysystem.secrecynetwork.service.SecrecyNetworkterminalService#findDataList(ec.common.PageSortModel, com.cdthgk.platform.organization.organ.domain.Organ, java.lang.String, com.cdthgk.bmp.secrecysystem.secrecynetwork.vo.SecrecyNetworkterminal)
	 */
	@Override
	public List<SecrecyNetworkterminal> findDataList(
			PageSortModel<SecrecyNetworkterminal> psm, Organ currentOrgan,
			String countType, SecrecyNetworkterminal secrecyNetworkterminal) {
		Map<String,Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM SecrecyNetworkterminal snt where 1=1");

		if( "organ".equals(countType) ){
			hql.append(" and snt.department.organ.organId = :organId ");
			params.put("organId", currentOrgan.getOrganId());
		}else if( "layer".equals(countType) ){
			hql.append(" and snt.department.organ.district.layer like :layer");
			params.put("layer", currentOrgan.getDistrict().getLayer() + "%");
		}
		hql.append(" AND snt.dataState = :dataState");
		params.put("dataState", 1);

		//查询条件
		if (secrecyNetworkterminal != null && secrecyNetworkterminal.getSecrecyComputer()!=null) {
			//网络名称
			if (secrecyNetworkterminal.getSecrecyNetwork() != null && StringUtils.isNotEmpty(secrecyNetworkterminal.getSecrecyNetwork().getName()) ) {
				hql.append(" and snt.secrecyNetwork.name like :name");
				params.put("name", "%" + secrecyNetworkterminal.getSecrecyNetwork().getName() + "%");
			}
			//责任人
			if (secrecyNetworkterminal.getDutyPerson() != null && StringUtils.isNotEmpty(secrecyNetworkterminal.getDutyPerson().getName())) {
				hql.append(" and snt.dutyPerson.name like :dutyPerson");
				params.put("dutyPerson", secrecyNetworkterminal.getDutyPerson().getName());
			}
			//网络密级
			if (secrecyNetworkterminal.getSecrecyLevel() != null) {
				hql.append(" and snt.secrecyLevel = :secrecyLevel");
				params.put("secrecyLevel", secrecyNetworkterminal.getSecrecyLevel());
			}
			//部门名称
			if( secrecyNetworkterminal.getDepartment()!=null && StringUtils.isNotEmpty(secrecyNetworkterminal.getDepartment().getDepartmentName()) ){
				hql.append(" and snt.department.departmentName = :departmentName");
				params.put("departmentName", secrecyNetworkterminal.getDepartment().getDepartmentName());
			}
			//计算机类型
			if( secrecyNetworkterminal.getSecrecyComputer().getComputerType()!=null){
	                        hql.append(" and snt.secrecyComputer.computerType = :computerType");
			        params.put("computerType", secrecyNetworkterminal.getSecrecyComputer().getComputerType());
			}
		}
		List<SecrecyNetworkterminal> secrecyNetworkterminalList = new ArrayList<SecrecyNetworkterminal>();
		if( psm!=null ){
			secrecyNetworkterminalList = this.findList(hql.toString(), params, psm);
		}else{
			secrecyNetworkterminalList = this.findList(hql.toString(), params);
		}
		return secrecyNetworkterminalList;
	}

	/**
	 * 综合统计 总数
	 */
	public Map<String, Object> getSecrecyNetworkterminal_Total_District(District district, int reflag) {
		Map<String, Object> cmap = new HashMap<String,Object>();
		if(district==null) {
			return cmap;
		}

		StringBuffer sb = new StringBuffer();
		String sql = "";
		Map<String, Object> params = new HashMap<String, Object>();

		if(reflag==1) {//行政区划
			sql = " layer like :layer ";
			params.put("layer", district.getLayer() + "%");
		}else {//直辖单位
			sql = " layer = :layer ";
			params.put("layer", district.getLayer());
		}

		//查询总数
		sb = new StringBuffer();
		sb.append(" SELECT  a1,a2,a3,a1+a2+a3 AS total FROM(  ");
		sb.append(" SELECT IFNULL(SUM(CASE WHEN secrecy_level=1 THEN fcount END),0)AS a1,IFNULL(SUM(CASE WHEN secrecy_level=2 THEN fcount END),0)AS a2, ");
		sb.append(" IFNULL(SUM(CASE WHEN secrecy_level=3 THEN fcount END),0)AS a3 FROM ( ");
		sb.append(" SELECT secrecy_level, SUM(fcount)AS fcount FROM( ");
		sb.append(" SELECT secrecy_level, 1 AS fcount FROM bm_secrecy_networkterminal WHERE (secrecy_status is null or secrecy_status = "+ BmpAction.SECRECY_STATUS_NOW +" ) AND create_organ IN(SELECT organ_id FROM sys_organization  ");
		sb.append(" WHERE district_code IN (SELECT district_code FROM sys_district WHERE "+sql+")) ");
		sb.append(" )AS A GROUP BY secrecy_level)AS B)AS C ");
		cmap = this.getPersistProxy().getJdbcPersistence().find(sb.toString(), params);

		return cmap;
	}

	/**
	 * 综合统计
	 */
	public List<ZongHeTongJiStatDto> count_SecrecyNetworkterminal_District(List<District> districtList, boolean isGroup) {

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
			StringBuffer sql = ZongHeTongJiUtil.getDistrictSql_ZongHeTongJi_secrecySystem(district.getLayer(),"bm_secrecy_networkterminal","create_organ");
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

	/**
	 * 综合统计
	 */
	public List<ZongHeTongJiStatDto> count_SecrecyNetworkterminal_Organ(District district, Organ organ, boolean needTotal) {

		//返回值
		List<ZongHeTongJiStatDto> list = new ArrayList<ZongHeTongJiStatDto>();
		ZongHeTongJiStatDto totalMap = new ZongHeTongJiStatDto();//合计数

		//获取sql语句
		StringBuffer sb = ZongHeTongJiUtil.getOrganSql_ZongHeTongJi_secrecySystem(district, organ, "bm_secrecy_networkterminal", "create_organ");
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

	/**
	 * 综合统计
	 */
	/**通过行政区划对象统计   分单位显示行政区划下面的统计情况
	 * 综合统计  查询 行政区划涉密人员统计  通过行政区划编码
	 * @param district  行政区划对象 , 每个行政区划必须包含  districtCode
	 * @param isGroup  是否合计
	 * @param organ   单位对象   ，这个对象可以为空，只是在模糊查询的时候我们会用到，单位的名字
	 *
	 * @return
	 */
	public List<ZongHeTongJiStatDto> count_SecrecyNetworkterminal_District(District district, boolean isGroup,Organ organ){

		//返回值
		List<ZongHeTongJiStatDto> list = new ArrayList<ZongHeTongJiStatDto>();

		if(district!=null && district.getDistrictCode()!=null) {
			ZongHeTongJiStatDto totalMap = new ZongHeTongJiStatDto();//合计数

			//获取sql语句
			StringBuffer sb = ZongHeTongJiUtil.getOrganSqlByDistrict_ZongHeTongJi_secrecySystem(district, "bm_secrecy_networkterminal", "create_organ",organ);
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

}
