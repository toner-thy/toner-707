/**
 *
 */
package com.cdthgk.bmp.secrecysystem.secrecyotherdevice.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.action.BmpAction;
import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.bmp.secrecycountryitem.qo.ZongHeTongJiStatDto;
import com.cdthgk.bmp.secrecycountryitem.qo.ZongHeTongJiUtil;
import com.cdthgk.bmp.secrecysystem.secrecyotherdevice.service.SecrecyOthersService;
import com.cdthgk.bmp.secrecysystem.secrecyotherdevice.vo.SecrecyOthers;
import com.cdthgk.platform.dictionary.domain.DictionaryOption;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.core.OrganizationContext;
import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

/**
 * <p>
 * 类的说明放这里 2013-7-31 下午1:55:34
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 *
 * @author 宋亚非
 */
public class SecrecyOthersServiceImpl extends
		BmpServiceImpl<SecrecyOthers, String> implements SecrecyOthersService {

	/* (non-Javadoc)
	 * @see com.cdthgk.bmp.secrecysystem.secrecyotherdevice.service.SecrecyOthersService#findPageAllList(ec.common.PageSortModel, com.cdthgk.bmp.secrecysystem.secrecyotherdevice.vo.SecrecyOthers, com.cdthgk.platform.organization.organ.domain.Organ, java.lang.Integer)
	 */
	@Override
	public List<SecrecyOthers> findPageAllList(
			PageSortModel<SecrecyOthers> psm, SecrecyOthers secrecyOthers,
			Organ organ, District district, String checkLower, Integer secrecyStatusFlag) {
		Map<String,Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM SecrecyOthers so where 1=1");

		if( organ!=null ){
			hql.append(" AND so.createOrgan.organId = :organId ");
			params.put("organId", organ.getOrganId());
		}

		if( district!=null ){
			if( checkLower!=null && "1".equals(checkLower) ){
				hql.append(" AND so.createOrgan.district.layer like :layer ");
				params.put("layer", district.getLayer()+"%");
			}else{
				hql.append(" AND so.createOrgan.district.layer = :layer ");
				params.put("layer", district.getLayer());
			}
		}

		hql.append(" AND so.secrecyStatus = :secrecyStatusFlag");
		params.put("secrecyStatusFlag", secrecyStatusFlag);

		//查询条件
		if(secrecyOthers!=null ){
			//介质类型
			if(	secrecyOthers.getSecrecyothersType()!=null ){
				hql.append(" AND so.secrecyothersType = :secrecyothersType");
				params.put("secrecyothersType", secrecyOthers.getSecrecyothersType());
			}
			//涉密等级
			if(	secrecyOthers.getSecrecyLevel() !=null ){
				hql.append(" AND so.secrecyLevel = :secrecyLevel");
				params.put("secrecyLevel", secrecyOthers.getSecrecyLevel());
			}
			//责任人
			if(secrecyOthers.getDutyPerson()!=null && secrecyOthers.getDutyPerson().getName() !=null && !"".equals(secrecyOthers.getSecrecyLevel() ) ){
				hql.append(" AND so.dutyPerson.name like :dutyPerson");
				params.put("dutyPerson", "%"+secrecyOthers.getDutyPerson().getName()+"%");
			}
			//是否属于要害部门部位
			if( secrecyOthers.getIsbelongKeydepartment()!=null ){
				hql.append(" AND so.isbelongKeydepartment like :isbelongKeydepartment");
				params.put("isbelongKeydepartment", secrecyOthers.getIsbelongKeydepartment());
			}
		}
		List<SecrecyOthers> secrecyOthersList = new ArrayList<SecrecyOthers>();
		if( psm!=null ){
			secrecyOthersList = this.findList(hql.toString(), params, psm);
		}else{
			secrecyOthersList = this.findList(hql.toString(), params);
		}
		return secrecyOthersList;
	}


	/*@Override
	public List<SecrecyOthers> findPageAllList(
			PageSortModel<SecrecyOthers> psm, SecrecyOthers secrecyOthers,
			District district) {
		Map<String,Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM SecrecyOthers so where 1=1");

		if( district!=null ){
			hql.append(" AND so.createOrgan.district.layer = :layer ");
			params.put("layer", district.getLayer());
		}
		hql.append(" AND so.secrecyStatus = :secrecyStatusFlag");
		params.put("secrecyStatusFlag", BmpAction.SECRECY_STATUS_NOW);

		//查询条件
		if(secrecyOthers!=null ){
			//介质类型
			if(	secrecyOthers.getSecrecyothersType()!=null ){
				hql.append(" AND so.secrecyothersType = :secrecyothersType");
				params.put("secrecyothersType", secrecyOthers.getSecrecyothersType());
			}
			//涉密等级
			if(	secrecyOthers.getSecrecyLevel() !=null ){
				hql.append(" AND so.secrecyLevel = :secrecyLevel");
				params.put("secrecyLevel", secrecyOthers.getSecrecyLevel());
			}
			//责任人
			if(secrecyOthers.getDutyPerson()!=null && secrecyOthers.getDutyPerson().getName() !=null && !"".equals(secrecyOthers.getSecrecyLevel() ) ){
				hql.append(" AND so.dutyPerson.name like :dutyPerson");
				params.put("dutyPerson", "%"+secrecyOthers.getDutyPerson().getName()+"%");
			}
			//是否属于要害部门部位
			if( secrecyOthers.getIsbelongKeydepartment()!=null ){
				hql.append(" AND so.isbelongKeydepartment like :isbelongKeydepartment");
				params.put("isbelongKeydepartment", secrecyOthers.getIsbelongKeydepartment());
			}
		}
		List<SecrecyOthers> secrecyOthersList = new ArrayList<SecrecyOthers>();
		if( psm!=null ){
			secrecyOthersList = this.findList(hql.toString(), params, psm);
		}else{
			secrecyOthersList = this.findList(hql.toString(), params);
		}
		return secrecyOthersList;
	}*/

	/* (non-Javadoc)
	 * @see com.cdthgk.bmp.secrecysystem.secrecyotherdevice.service.SecrecyOthersService#countOthersData(java.util.List, java.util.List, com.cdthgk.platform.organization.organ.domain.Organ, java.lang.String)
	 */
	@Override
	public Map<Integer, Map<Integer, Long>> countOthersData(
			List<DictionaryOption> optionList, List<DictionaryOption> typeList,
			Organ currentOrgan, String countType) {
		Map<Integer, Map<Integer, Long>> returnMap = new HashMap<Integer, Map<Integer,Long>>();
		// 统计
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("select new map(so.secrecyothersType as secrecyothersType, count(so) as num) from SecrecyOthers so " +
				" where 1=1 and (so.secrecyStatus is null or so.secrecyStatus = :statusNow) ");
		params.put("statusNow", BmpAction.SECRECY_STATUS_NOW);

		if( "organ".equals(countType) ){
			hql.append(" and so.createOrgan.organId = :organId ");
			params.put("organId", currentOrgan.getOrganId());
		}else if( "layer".equals(countType) ){
			hql.append(" and so.createOrgan.district.layer like :layer");
			params.put("layer", currentOrgan.getDistrict().getLayer() + "%");
		}
		hql.append(" and so.secrecyLevel = :secrecyLevel");

		hql.append(" group by so.secrecyothersType ");

		for( DictionaryOption dictionaryOption : optionList ){
			params.put("secrecyLevel", dictionaryOption.getOptionValue());
			List<Map<String, Object>> aResultList = this.getPersistProxy().getOrmPersistence().findList(hql.toString(), params);
			Map<Integer,Long> tmpResult = new HashMap<Integer, Long>();
			if( aResultList!=null && aResultList.size()>0 ){
				for( Map<String,Object> tmpDbResult : aResultList ){
					tmpResult.put(Integer.parseInt(tmpDbResult.get("secrecyothersType").toString()), Long.parseLong(tmpDbResult.get("num").toString()));
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
	 * @see com.cdthgk.bmp.secrecysystem.secrecyotherdevice.service.SecrecyOthersService#findDataList(ec.common.PageSortModel, com.cdthgk.platform.organization.organ.domain.Organ, java.lang.String, com.cdthgk.bmp.secrecysystem.secrecyotherdevice.vo.SecrecyOthers)
	 */
	@Override
	public List<SecrecyOthers> findDataList(PageSortModel<SecrecyOthers> psm,
			Organ currentOrgan, String countType, SecrecyOthers secrecyOthers) {
		Map<String,Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM SecrecyOthers so where 1=1");

		if( "organ".equals(countType) ){
			hql.append(" and so.createOrgan.organId = :organId ");
			params.put("organId", currentOrgan.getOrganId());
		}else if( "layer".equals(countType) ){
			hql.append(" and so.createOrgan.district.layer like :layer");
			params.put("layer", currentOrgan.getDistrict().getLayer() + "%");
		}
		hql.append(" AND so.secrecyStatus = :secrecyStatusFlag");
		params.put("secrecyStatusFlag", BmpAction.SECRECY_STATUS_NOW);

		//查询条件
		if(secrecyOthers!=null ){
			//介质类型
			if(	secrecyOthers.getSecrecyothersType()!=null ){
				hql.append(" AND so.secrecyothersType = :secrecyothersType");
				params.put("secrecyothersType", secrecyOthers.getSecrecyothersType());
			}
			//涉密等级
			if(	secrecyOthers.getSecrecyLevel() !=null ){
				hql.append(" AND so.secrecyLevel = :secrecyLevel");
				params.put("secrecyLevel", secrecyOthers.getSecrecyLevel());
			}
			//责任人
			if(secrecyOthers.getDutyPerson()!=null && secrecyOthers.getDutyPerson().getName() !=null && !"".equals(secrecyOthers.getSecrecyLevel() ) ){
				hql.append(" AND so.dutyPerson.name like :dutyPerson");
				params.put("dutyPerson", "%"+secrecyOthers.getDutyPerson().getName()+"%");
			}
			//是否属于要害部门部位
			if( secrecyOthers.getIsbelongKeydepartment()!=null ){
				hql.append(" AND so.isbelongKeydepartment like :isbelongKeydepartment");
				params.put("isbelongKeydepartment", secrecyOthers.getIsbelongKeydepartment());
			}
		}
		List<SecrecyOthers> secrecyOthersList = new ArrayList<SecrecyOthers>();
		if( psm!=null ){
			secrecyOthersList = this.findList(hql.toString(), params, psm);
		}else{
			secrecyOthersList = this.findList(hql.toString(), params);
		}
		return secrecyOthersList;
	}


	/* (non-Javadoc)
	 * @see com.cdthgk.bmp.secrecysystem.secrecyotherdevice.service.SecrecyOthersService#addDepartment(java.lang.String, com.cdthgk.platform.permission.user.domain.User)
	 */
	@Override
	public Department addDepartment(String departmentName, User currentUser) {
		Department dept = new Department();
		//当前单位不存在该部门，新增系统部门
		Department existDepartment  = OrganizationContext.getInstance().getDepartmentService()
				.getByName(departmentName, currentUser.getOrgan());
		if(existDepartment == null) {
			dept.setDepartmentName(departmentName);
			dept.setOrgan(currentUser.getOrgan());
			dept.setParent(currentUser.getOrgan().getDepartment());
			dept.setCreatePerson(currentUser);
			dept.setModifyPerson(currentUser);
			dept.save();
		} else {
			dept = existDepartment;
		}
		return dept;

	}


	/* (non-Javadoc)
	 * @see com.cdthgk.bmp.secrecysystem.secrecyotherdevice.service.SecrecyOthersService#addUserinfo(java.lang.String, com.cdthgk.platform.permission.user.domain.User)
	 */
	@Override
	public UserInfo addUserinfo(String userName, User currentUser) {
		UserInfo userInfo = new UserInfo();
		//当前单位不存在该人员，新增系统人员
		List<UserInfo> userInfoList = (List<UserInfo>) OrganizationContext.getInstance().getMemberService()
				.getByName(userName);
		if(userInfoList != null && userInfoList.size() > 0) {
			userInfo = userInfoList.get(0);
		} else {
			userInfo.setName(userName);
			userInfo.setOrgan(currentUser.getOrgan());
			userInfo.setDepartment(currentUser.getOrgan().getDepartment());
			userInfo.setCreatePerson(currentUser);
			userInfo.setModifyPerson(currentUser);
			userInfo.save();
		}
		return userInfo;
	}

	/**
	 * <p>
	 * 方法名称  getSecrecyComputer_Total_District
	 * </p>
	 * <p>
	 * 宋亚非 2013-9-5 上午11:14:12
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@param district
	 *@param reflag
	 *@return
	 */
	public Map<String, Object> getSecrecyOthers_Total_District(District district, int reflag) {
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
		sb.append(" SELECT secrecy_level, 1 AS fcount FROM bm_secrecy_others WHERE (secrecy_status is null or secrecy_status = "+ BmpAction.SECRECY_STATUS_NOW +" ) AND create_organ IN(SELECT organ_id FROM sys_organization  ");
		sb.append(" WHERE district_code IN (SELECT district_code FROM sys_district WHERE "+sql+")) ");
		sb.append(" )AS A GROUP BY secrecy_level)AS B)AS C ");
		cmap = this.getPersistProxy().getJdbcPersistence().find(sb.toString(), params);

		return cmap;
	}

	/**
	 * 综合统计
	 */
	public List<ZongHeTongJiStatDto> count_SecrecyOthers_District(List<District> districtList, boolean isGroup) {

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
			StringBuffer sql = ZongHeTongJiUtil.getDistrictSql_ZongHeTongJi_secrecySystem(district.getLayer(),"bm_secrecy_others","create_organ");
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
	public List<ZongHeTongJiStatDto> count_SecrecyOthers_Organ(District district, Organ organ, boolean needTotal) {

		//返回值
		List<ZongHeTongJiStatDto> list = new ArrayList<ZongHeTongJiStatDto>();
		ZongHeTongJiStatDto totalMap = new ZongHeTongJiStatDto();//合计数

		//获取sql语句
		StringBuffer sb = ZongHeTongJiUtil.getOrganSql_ZongHeTongJi_secrecySystem(district, organ, "bm_secrecy_others", "create_organ");
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
	 * 综合统计  查询 行政区划涉密人员统计  通过行政区划编码
	 * @param district  行政区划对象 , 每个行政区划必须包含  districtCode
	 * @param isGroup  是否合计
	 * @param organ   单位对象   ，这个对象可以为空，只是在模糊查询的时候我们会用到，单位的名字
	 *
	 * @return
	 */
	public List<ZongHeTongJiStatDto> count_SecrecyOthers_District(District district, boolean isGroup,Organ organ){

		//返回值
		List<ZongHeTongJiStatDto> list = new ArrayList<ZongHeTongJiStatDto>();

		if(district!=null && district.getDistrictCode()!=null) {
			ZongHeTongJiStatDto totalMap = new ZongHeTongJiStatDto();//合计数

			//获取sql语句
			StringBuffer sb = ZongHeTongJiUtil.getOrganSqlByDistrict_ZongHeTongJi_secrecySystem(district, "bm_secrecy_others", "create_organ",organ);
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
