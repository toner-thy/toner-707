package com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.bmp.secrecyorganization.secrecycommittee.vo.PersonGroupPosition;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.service.SecrecyWorkOrganMemberUnitModuleService;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.service.SecrecyWorkOrganModuleService;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.service.SecrecyWorkOrganRelationMemberModuleService;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.vo.SecrecyWorkOrgan;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.vo.SecrecyWorkOrganMemberUnit;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.vo.SecrecyWorkOrganRelationMember;
import com.cdthgk.bmp.statinfo.dto.QueryDto;
import com.cdthgk.common.bean.BeanUtils;
import com.cdthgk.common.lang.LangUtils;
import com.cdthgk.common.lang.StringUtils;
import com.cdthgk.platform.dictionary.domain.DictionaryOption;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.core.OrganizationContext;
import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;
import com.cdthgk.platform.permission.user.domain.User;
import com.cdthgk.standard.asserts.AssertStandardApp;

/**
 * <p>
 * 保密工作机构  Service 实现类
 * </p>
 * <p>
 * 刘椿成 2012-12-14 10:43:03
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
 * @author liucc
 * @since 1.0
 * @version 1.0
 */
public class SecrecyWorkOrganModuleServiceImpl extends BmpServiceImpl<SecrecyWorkOrgan, String> implements SecrecyWorkOrganModuleService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(SecrecyWorkOrganModuleServiceImpl.class);

	private SecrecyWorkOrganRelationMemberModuleService secrecyWorkOrganRelationMemberModuleService;
	private SecrecyWorkOrganMemberUnitModuleService secrecyWorkOrganMemberUnitModuleService;

	/** 默认构造器 */
	SecrecyWorkOrganModuleServiceImpl() {
	}

	/**
	 * 根据当前登录用户所在单位获取保密工作机构
	 * 刘椿成 2012-12-14 10:43:03
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
	 * @author liucc
	 * @since 1.0
	 * @version 1.0
	 */
	@Override
	public SecrecyWorkOrgan getSecrecyWorkOrganByOrgan(Organ organ) {

		SecrecyWorkOrgan  secrecyWorkOrgan  = new SecrecyWorkOrgan();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("organId", organ.getOrganId());

		secrecyWorkOrgan = unique("from SecrecyWorkOrgan s where s.organ.organId = :organId", params);
		return secrecyWorkOrgan;
	}

	/**
	 * 新增/更新填写的部门，人员
	 * 刘椿成 2012-12-14 10:43:03
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
	 * @author liucc
	 * @since 1.0
	 * @version 1.0
	 */
	@Override
	public SecrecyWorkOrgan saveSystemDataBySecrecyWorkOrgan(SecrecyWorkOrgan secrecyWorkOrgan,User currentUser, String deptFlag) {
		AssertStandardApp.isNotNull(secrecyWorkOrgan.getOrganPrincipal(), "输入的人员不能为空");
		AssertStandardApp.isNotNull(secrecyWorkOrgan.getDepartment(), "输入的部门不能为空");
		//当前单位下输入的部门如果有则保存，如果没有则通过输入的部门名称新增部门。
		Department department = new Department();
		if (LangUtils.isEmpty(secrecyWorkOrgan.getDepartment().getDepartmentId())) {
			//部门变更或者部门改名的标志：1：部门改名；2部门变更
			if(deptFlag != null && "1".equals(deptFlag)) {
				Department existDepartment  = this.get(secrecyWorkOrgan.getOldDeptId(), Department.class);
				existDepartment.setDepartmentName(secrecyWorkOrgan.getDepartment()
					.getDepartmentName());
				department = existDepartment;
				department.update();
			}else {
				//变更时，如果存在该部门，则不理，把关系建立起。
				Department existDepartment = OrganizationContext.getInstance().getDepartmentService()
					.getByName(secrecyWorkOrgan.getDepartment()
					.getDepartmentName(), currentUser.getOrgan());
				if(existDepartment == null){
					//当前单位不存在该部门，新增系统部门
					department.setDepartmentName(secrecyWorkOrgan.getDepartment()
						.getDepartmentName());
					department.setOrgan(currentUser.getOrgan());
					department.setParent(currentUser.getOrgan().getDepartment());
					department.setCreatePerson(currentUser);
					department.setModifyPerson(currentUser);
					department.save();
				} else {
					department = existDepartment;
				}
			}
		}else {
			// 部门已经存在
			department = this.get(secrecyWorkOrgan.getDepartment().getDepartmentId(), Department.class);
		}

		//当前单位下输入的人员如果有则保存，如果没有则通过输入的名字新增人员。
		//姓 名
		UserInfo userInfo = new UserInfo();
		if (LangUtils.isEmpty(secrecyWorkOrgan.getOrganPrincipal().getUserInfoId())) {
			//FIXME 先检查是否存在该人员(注意这里返回的是集合需要修改)
			List<UserInfo> userInfoList = (List<UserInfo>) OrganizationContext.getInstance().getMemberService()
					.getByName(secrecyWorkOrgan.getOrganPrincipal().getName());
			if(userInfoList != null && userInfoList.size() > 0) {
				userInfo = userInfoList.get(0);
				//userInfo.setDuty(secrecyWorkOrgan.getOrganPrincipal().getDuty());
				userInfo.setDuty(secrecyWorkOrgan.getPrincipalDuty());
				userInfo.setPhone(secrecyWorkOrgan.getPrincipalPhone());
			}else {
				//当前单位不存在该人员，新增系统人员
				//userInfo.setDuty(secrecyWorkOrgan.getOrganPrincipal().getDuty());
				userInfo.setDuty(secrecyWorkOrgan.getPrincipalDuty());
				userInfo.setPhone(secrecyWorkOrgan.getPrincipalPhone());
				userInfo.setName(secrecyWorkOrgan.getOrganPrincipal().getName());
				userInfo.setOrgan(currentUser.getOrgan());
				userInfo.setDepartment(currentUser.getOrgan().getDepartment());
				userInfo.setCreatePerson(currentUser);
				userInfo.setModifyPerson(currentUser);
				userInfo.save();
			}
		}else {
			userInfo = this.get(secrecyWorkOrgan.getOrganPrincipal().getUserInfoId(), UserInfo.class);
			userInfo.setDuty(secrecyWorkOrgan.getPrincipalDuty());
			userInfo.setPhone(secrecyWorkOrgan.getPrincipalPhone());
			// userInfo.setDuty(part.getPerson().getDuty());
		}
		userInfo.setDepartment(department);
		secrecyWorkOrgan.setOrganPrincipal(userInfo);
		secrecyWorkOrgan.setDepartment(department);
		return secrecyWorkOrgan;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void saveRecSecrecyWorkOrgan(
			List<SecrecyWorkOrgan> secrecyWorkOrganList, String receiveOrganId) {
		if (secrecyWorkOrganList == null) {
			return;
		}
		for (SecrecyWorkOrgan secrecyWorkOrgan : secrecyWorkOrganList) {
			List<SecrecyWorkOrganRelationMember> oldMemberList = secrecyWorkOrganRelationMemberModuleService.getPageList(secrecyWorkOrgan, null, null);
			List<SecrecyWorkOrganMemberUnit> oldUnitList = secrecyWorkOrganMemberUnitModuleService.getPageList(secrecyWorkOrgan, null, null);
			SecrecyWorkOrgan swo = this.get(secrecyWorkOrgan.getSecrecyWorkOrganId());
			if (null != swo) { // null != eno 说明已经保存过，现在只需更新
				try {
					BeanUtils.copyProperties(swo, secrecyWorkOrgan);
				} catch(Exception e) {
					LOGGER.debug("属性转换异常:" + e.getMessage());
				}
				this.update(swo);
			} else { // 第一次保存
				this.save(secrecyWorkOrgan);
			}
			//删除保密组织机构成员
			secrecyWorkOrganRelationMemberModuleService.deleteBatch(oldMemberList);
			//添加
			secrecyWorkOrganRelationMemberModuleService.saveBatch(secrecyWorkOrgan.getPersonGroupRelations());
			//删除保密办成员
			secrecyWorkOrganMemberUnitModuleService.deleteBatch(oldUnitList);
			//添加
			secrecyWorkOrganMemberUnitModuleService.saveBatch(secrecyWorkOrgan.getSecrecyWorkOrganMemberUnitSet());
		}
	}

	/*****************************统计部分*******************************/

	@Override
	public Map<String, Map<Integer, Integer>> statisticsByOrganType(List<DictionaryOption> organCategoryDictionary,District district, String organId){
		Map<String,Map<Integer,Integer>> returnResult = new HashMap<String, Map<Integer,Integer>>();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT swo.name, swo.organ.organName, swo.organType, count(swo.organType) as num  FROM SecrecyWorkOrgan swo ");
		hql.append("where 1 = 1 ");

		if( StringUtils.isNotEmpty(organId) ){
			hql.append("AND swo.organ.organId = :organId ");
			params.put("organId", organId);
		}else if( district != null ){
			hql.append("AND swo.organ.district.districtCode= :districtCode ");
			params.put("districtCode", district.getDistrictCode());
		}
		hql.append("GROUP BY ");
		hql.append("swo.organType, swo.name, swo.organ.organName");
		List<Object[]> result = this.getPersistProxy().getOrmPersistence().findList(hql.toString(), params);
		for( Object[] aTmp : result ){
			Map<Integer,Integer> aResult = returnResult.get(aTmp[2]);
			if( aResult==null ){
				aResult = new HashMap<Integer,Integer>();
			}
			aResult.put(Integer.parseInt(aTmp[2]==null?"1000":aTmp[2].toString()), Integer.parseInt(aTmp[3].toString()));
			returnResult.put(aTmp[0]==null?aTmp[1].toString():aTmp[0].toString(), aResult);
		}
		return returnResult;
	}

	@Override
	public Map<String, Map<Integer, Integer>> statisticsByAreaOfOrganType(List<DictionaryOption> organCategoryDictionary,District district, String organId){
		Map<String,Map<Integer,Integer>> returnResult = new HashMap<String, Map<Integer,Integer>>();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT swo.organType, count(swo.organType) as num  FROM SecrecyWorkOrgan swo ");
		hql.append("where 1 = 1 ");

		if( StringUtils.isNotEmpty(organId) ){
			hql.append("AND swo.organ.organId = :organId ");
			params.put("organId", organId);
		}else if( district != null && StringUtils.isNotEmpty(district.getLayer()) ){
			hql.append("AND swo.organ.district.districtCode= :districtCode ");
			params.put("districtCode", district.getDistrictCode());
		}
		hql.append("GROUP BY ");
		hql.append("swo.organType");
		List<Object[]> result = this.getPersistProxy().getOrmPersistence().findList(hql.toString(), params);
		for( Object[] aTmp : result ){
			Map<Integer,Integer> aResult = returnResult.get(district.getDistrictName());
			if( aResult==null ){
				aResult = new HashMap<Integer,Integer>();
			}
			aResult.put(Integer.parseInt(aTmp[0]==null?"1000":aTmp[0].toString()), Integer.parseInt(aTmp[1].toString()));
			returnResult.put(district.getDistrictName(), aResult);
		}
		return returnResult;
	}

	@Override
	public Map<String, Map<Integer, Integer>> statisticsByLayerOfOrganType(List<DictionaryOption> organCategoryDictionary,District district, String organId){
		Map<String,Map<Integer,Integer>> returnResult = new HashMap<String, Map<Integer,Integer>>();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT swo.organType, count(swo.organType) as num  FROM SecrecyWorkOrgan swo ");
		hql.append("where 1 = 1 ");

		if( StringUtils.isNotEmpty(organId) ){
			hql.append("AND swo.organ.organId = :organId ");
			params.put("organId", organId);
		}else if( district != null && StringUtils.isNotEmpty(district.getLayer()) ){
			hql.append("AND swo.organ.district.layer LIKE :layer ");
			params.put("layer", district.getLayer()+"%");
		}
		hql.append("GROUP BY ");
		hql.append("swo.organType");
		List<Object[]> result = this.getPersistProxy().getOrmPersistence().findList(hql.toString(), params);
		for( Object[] aTmp : result ){
			Map<Integer,Integer> aResult = returnResult.get(district.getDistrictName());
			if( aResult==null ){
				aResult = new HashMap<Integer,Integer>();
			}
			aResult.put(Integer.parseInt(aTmp[0]==null?"1000":aTmp[0].toString()), Integer.parseInt(aTmp[1].toString()));
			returnResult.put(district.getDistrictName(), aResult);
		}
		return returnResult;
	}

	//内部调用--行政区划使用
	public Map<Integer, Integer> statisticsDistinctOfOrganType(List<DictionaryOption> organAdminLevelDictionary,String layer,String organId){
		Map<Integer,Integer> returnResult = new HashMap<Integer,Integer>();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT swo.organType, count(swo.organType) as num  FROM SecrecyWorkOrgan swo ");
		hql.append("where 1 = 1 ");

		if( StringUtils.isNotEmpty(organId) ){
			hql.append("AND swo.organ.organId = :organId ");
			params.put("organId", organId);
		}else if( StringUtils.isNotEmpty(layer)){
			hql.append("AND swo.organ.district.layer LIKE :layer ");
			params.put("layer", layer+"%");
		}
		hql.append("GROUP BY ");
		hql.append("swo.organType");
		List<Object[]> result = this.getPersistProxy().getOrmPersistence().findList(hql.toString(), params);
		for( Object[] aTmp : result ){
			returnResult.put(Integer.parseInt(aTmp[0]==null?"1000":aTmp[0].toString()), Integer.parseInt(aTmp[1].toString()));
		}
		return returnResult;
	}
	//内部调用--直辖机构使用
	public Map<Integer, Integer> statisticsOrganOfOrganType(List<DictionaryOption> organAdminLevelDictionary,String TmpOrganId,String organId){
		Map<Integer,Integer> returnResult = new HashMap<Integer,Integer>();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT swo.organType, count(swo.organType) as num  FROM SecrecyWorkOrgan swo ");
		hql.append("where 1 = 1 ");
		if( StringUtils.isNotEmpty(organId) ){
			hql.append("AND swo.organ.organId = :organId ");
			params.put("organId", organId);
		}else if( StringUtils.isNotEmpty(TmpOrganId)){
			hql.append("AND swo.organ.organId = :organId ");
			params.put("organId", TmpOrganId);
		}
		hql.append("GROUP BY ");
		hql.append("swo.organType");
		List<Object[]> result = this.getPersistProxy().getOrmPersistence().findList(hql.toString(), params);
		for( Object[] aTmp : result ){
			returnResult.put(Integer.parseInt(aTmp[0]==null?"1000":aTmp[0].toString()), Integer.parseInt(aTmp[1].toString()));
		}
		return returnResult;
	}

	//子行政区划
	public Map<String, Map<Integer, Integer>> statisticsByOrganTypeOfDistrict(List<DictionaryOption> organAdminLevelDictionary,District district,String organId){
		Map<String, Map<Integer, Integer>> returnResult = new HashMap<String, Map<Integer, Integer>>();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append("SELECT district2_.district_name,district2_.district_code, district2_.layer FROM SYS_DISTRICT district2_ ");
		sqlStr.append("WHERE district2_.pid = :districtCode ");
		params.put("districtCode", district.getDistrictCode());
		List<Map<String,Object>> subDistrict = this.getPersistProxy().getJdbcPersistence().findList(sqlStr.toString(), params);
		if( subDistrict!=null ){
			for( Map<String,Object> aDistrict : subDistrict ){
				returnResult.put(aDistrict.get("district_name").toString(),this.statisticsDistinctOfOrganType(organAdminLevelDictionary,aDistrict.get("layer").toString(),organId));
			}
		}
		return returnResult;
	}
	//子直属机构
	public Map<String, Map<Integer, Integer>> statisticsByOrganTypeOfOrgan(List<DictionaryOption> organAdminLevelDictionary,District district,String organId){
		Map<String, Map<Integer, Integer>> returnResult = new HashMap<String, Map<Integer, Integer>>();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT o FROM  Organ o WHERE 1=1 ");
		hql.append("AND o.district.districtCode = :districtCode ");
		params.put("districtCode", district.getDistrictCode());
		List<Organ> subOrgan = this.getPersistProxy().getOrmPersistence().findList(hql.toString(), params);
		if( subOrgan!=null ){
			for( Organ tmpOrgan : subOrgan ){
				returnResult.put(tmpOrgan.getName(),this.statisticsOrganOfOrganType(organAdminLevelDictionary,tmpOrgan.getOrganId(),organId));
			}
		}
		return returnResult;
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public Map<String, Map<Integer, Integer>> statisticsByAdministrativeLevel(List<DictionaryOption> organAdminLevelDictionary,District district, String organId){
		Map<String,Map<Integer,Integer>> returnResult = new HashMap<String, Map<Integer,Integer>>();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT swo.name, swo.organ.organName, swo.organAdminLevel, count(swo.organAdminLevel) as num  FROM SecrecyWorkOrgan swo ");
		hql.append("where 1 = 1 ");

		if( StringUtils.isNotEmpty(organId) ){
			hql.append("AND swo.organ.organId = :organId ");
			params.put("organId", organId);
		}else if( district != null ){
			hql.append("AND swo.organ.district.districtCode= :districtCode ");
			params.put("districtCode", district.getDistrictCode());
		}
		hql.append("GROUP BY ");
		hql.append("swo.organType, swo.name, swo.organ.organName");
		List<Object[]> result = this.getPersistProxy().getOrmPersistence().findList(hql.toString(), params);
		for( Object[] aTmp : result ){
			Map<Integer,Integer> aResult = returnResult.get(aTmp[2]);
			if( aResult==null ){
				aResult = new HashMap<Integer,Integer>();
			}
			aResult.put(Integer.parseInt(aTmp[2]==null?"1000":aTmp[2].toString()), Integer.parseInt(aTmp[3].toString()));
			returnResult.put(aTmp[0]==null?aTmp[1].toString():aTmp[0].toString(), aResult);
		}
		return returnResult;
	}

	public Map<String, Map<Integer, Integer>> statisticsByAreaOfAdministrativeLevel(List<DictionaryOption> organAdminLevelDictionary,District district, String organId){
		Map<String,Map<Integer,Integer>> returnResult = new HashMap<String, Map<Integer,Integer>>();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT swo.organAdminLevel, count(swo.organAdminLevel) as num  FROM SecrecyWorkOrgan swo ");
		hql.append("where 1 = 1 ");

		if( StringUtils.isNotEmpty(organId) ){
			hql.append("AND swo.organ.organId = :organId ");
			params.put("organId", organId);
		}else if( district != null && StringUtils.isNotEmpty(district.getLayer())){
			hql.append("AND swo.organ.district.districtCode= :districtCode ");
			params.put("districtCode", district.getDistrictCode());
		}
		hql.append("GROUP BY ");
		hql.append("swo.organAdminLevel");
		List<Object[]> result = this.getPersistProxy().getOrmPersistence().findList(hql.toString(), params);
		for( Object[] aTmp : result ){
			Map<Integer,Integer> aResult = returnResult.get(district.getDistrictName());
			if( aResult==null ){
				aResult = new HashMap<Integer,Integer>();
			}
			aResult.put(Integer.parseInt(aTmp[0]==null?"1000":aTmp[0].toString()), Integer.parseInt(aTmp[1].toString()));
			returnResult.put(district.getDistrictName(), aResult);
		}
		return returnResult;
	}

	public Map<String, Map<Integer, Integer>> statisticsByLayerOfAdministrativeLevel(List<DictionaryOption> organAdminLevelDictionary,District district, String organId){
		Map<String,Map<Integer,Integer>> returnResult = new HashMap<String, Map<Integer,Integer>>();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT swo.organAdminLevel, count(swo.organAdminLevel) as num  FROM SecrecyWorkOrgan swo ");
		hql.append("where 1 = 1 ");

		if( StringUtils.isNotEmpty(organId) ){
			hql.append("AND swo.organ.organId = :organId ");
			params.put("organId", organId);
		}else if( district != null && StringUtils.isNotEmpty(district.getLayer())){
			hql.append("AND swo.organ.district.layer LIKE :layer ");
			params.put("layer", district.getLayer()+"%");
		}
		hql.append("GROUP BY ");
		hql.append("swo.organAdminLevel");
		List<Object[]> result = this.getPersistProxy().getOrmPersistence().findList(hql.toString(), params);
		for( Object[] aTmp : result ){
			Map<Integer,Integer> aResult = returnResult.get(district.getDistrictName());
			if( aResult==null ){
				aResult = new HashMap<Integer,Integer>();
			}
			aResult.put(Integer.parseInt(aTmp[0]==null?"1000":aTmp[0].toString()), Integer.parseInt(aTmp[1].toString()));
			returnResult.put(district.getDistrictName(), aResult);
		}
		return returnResult;
	}

	//内部调用--行政区划使用
	public Map<Integer, Integer> statisticsDistinctOfAdmin(List<DictionaryOption> organAdminLevelDictionary,String layer,String organId){
		Map<Integer,Integer> returnResult = new HashMap<Integer,Integer>();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT swo.organAdminLevel, count(swo.organAdminLevel) as num  FROM SecrecyWorkOrgan swo ");
		hql.append("where 1 = 1 ");

		if( StringUtils.isNotEmpty(organId) ){
			hql.append("AND swo.organ.organId = :organId ");
			params.put("organId", organId);
		}else if( StringUtils.isNotEmpty(layer)){
			hql.append("AND swo.organ.district.layer LIKE :layer ");
			params.put("layer", layer+"%");
		}
		hql.append("GROUP BY ");
		hql.append("swo.organType");
		List<Object[]> result = this.getPersistProxy().getOrmPersistence().findList(hql.toString(), params);
		for( Object[] aTmp : result ){
			returnResult.put(Integer.parseInt(aTmp[0]==null?"1000":aTmp[0].toString()), Integer.parseInt(aTmp[1].toString()));
		}
		return returnResult;
	}
	//内部调用--直辖机构使用
	public Map<Integer, Integer> statisticsOrganOfAdmin(List<DictionaryOption> organAdminLevelDictionary,String TmpOrganId,String organId){
		Map<Integer,Integer> returnResult = new HashMap<Integer,Integer>();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT swo.organAdminLevel, count(swo.organAdminLevel) as num  FROM SecrecyWorkOrgan swo ");
		hql.append("where 1 = 1 ");

		if( StringUtils.isNotEmpty(organId) ){
			hql.append("AND swo.organ.organId = :organId ");
			params.put("organId", organId);
		}else if( StringUtils.isNotEmpty(TmpOrganId)){
			hql.append("AND swo.organ.organId = :organId ");
			params.put("organId", TmpOrganId);
		}
		hql.append("GROUP BY ");
		hql.append("swo.organType");
		List<Object[]> result = this.getPersistProxy().getOrmPersistence().findList(hql.toString(), params);
		for( Object[] aTmp : result ){
			returnResult.put(Integer.parseInt(aTmp[0]==null?"1000":aTmp[0].toString()), Integer.parseInt(aTmp[1].toString()));
		}
		return returnResult;
	}

	//子行政区划
	public Map<String, Map<Integer, Integer>> statisticsByAdministrativeLevelOfDistrict(List<DictionaryOption> organAdminLevelDictionary,District district,String organId){
		Map<String, Map<Integer, Integer>> returnResult = new HashMap<String, Map<Integer, Integer>>();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append("SELECT district2_.district_name,district2_.district_code, district2_.layer FROM SYS_DISTRICT district2_ ");
		sqlStr.append("WHERE district2_.pid = :districtCode ");
		params.put("districtCode", district.getDistrictCode());
		List<Map<String,Object>> subDistrict = this.getPersistProxy().getJdbcPersistence().findList(sqlStr.toString(), params);
		if( subDistrict!=null ){
			for( Map<String,Object> aDistrict : subDistrict ){
				returnResult.put(aDistrict.get("district_name").toString(),this.statisticsDistinctOfAdmin(organAdminLevelDictionary,aDistrict.get("layer").toString(),organId));
			}
		}
		return returnResult;
	}
	//子直属机构
	public Map<String, Map<Integer, Integer>> statisticsByAdministrativeLevelOfOrgan(List<DictionaryOption> organAdminLevelDictionary,District district,String organId){
		Map<String, Map<Integer, Integer>> returnResult = new HashMap<String, Map<Integer, Integer>>();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT o FROM  Organ o WHERE 1=1 ");
		hql.append("AND o.district.districtCode = :districtCode ");
		params.put("districtCode", district.getDistrictCode());
		List<Organ> subOrgan = this.getPersistProxy().getOrmPersistence().findList(hql.toString(), params);
		if( subOrgan!=null ){
			for( Organ tmpOrgan : subOrgan ){
				returnResult.put(tmpOrgan.getName(),this.statisticsDistinctOfAdmin(organAdminLevelDictionary,tmpOrgan.getOrganId(),organId));
			}
		}
		return returnResult;
	}
	///////////////////////////////////////////////////////////////////////////////////////////////

	public Map<String, Integer> statisticsByEstablishmentNum(List<Map<String, Integer>> numRange,District district,String organId, Integer hisFlag){
		Map<String, Integer> returnResult = new HashMap<String, Integer>();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT swo.name, swo.organ.organName, count(swo.secrecyWorkOrganId) as num  FROM SecrecyWorkOrgan as swo ");
		hql.append(" INNER JOIN swo.personGroupRelations as swor where ");
		hql.append(" 1 = 1 ");
		if( StringUtils.isNotEmpty(organId) ){
			hql.append("AND swo.organ.organId = :organId ");
			params.put("organId", organId);
		}else if( district != null ){
			hql.append("AND swo.organ.district.districtCode= :districtCode ");
			params.put("districtCode", district.getDistrictCode());
		}

		if( hisFlag != null  ){
			hql.append("AND (swor.secrecyStatus is null or swor.secrecyStatus =:secrecyStatusNow ) ");
			params.put("secrecyStatusNow", hisFlag);
		}

		hql.append("GROUP BY ");
		hql.append("swo.name, swo.organ.organName");
		List<Object[]> result = this.getPersistProxy().getOrmPersistence().findList(hql.toString(), params);

		for( Object[] aTmp : result ){
			returnResult.put(aTmp[0]==null?aTmp[1].toString():aTmp[0].toString(), Integer.parseInt(aTmp[2].toString()));
		}
		return returnResult;
	}

	public Map<String, Integer> statisticsByAreaOfEstablishmentNum(List<Map<String, Integer>> numRange,District district,String organId, Integer hisFlag){
		Map<String, Integer> returnResult = new HashMap<String, Integer>();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT swo.name, swo.organ.organName, swo.secrecyWorkOrganId, count(swo.secrecyWorkOrganId) as num  FROM SecrecyWorkOrgan swo ");
		hql.append(" JOIN swo.personGroupRelations swor where ");
		hql.append(" 1 = 1 ");
		if( StringUtils.isNotEmpty(organId) ){
			hql.append("AND swo.organ.organId = :organId ");
			params.put("organId", organId);
		}else if( district != null && StringUtils.isNotEmpty(district.getLayer()) ){
			hql.append("AND swo.organ.district.districtCode= :districtCode ");
			params.put("districtCode", district.getDistrictCode());
		}

		if( hisFlag != null  ){
			hql.append("AND (swor.secrecyStatus is null or swor.secrecyStatus =:secrecyStatusNow ) ");
			params.put("secrecyStatusNow", hisFlag);
		}

		hql.append("GROUP BY ");
		hql.append("swo.organAdminLevel, swo.name, swo.organ.organName");
		List<Object[]> result = this.getPersistProxy().getOrmPersistence().findList(hql.toString(), params);

		for( Object[] aTmp : result ){
			returnResult.put(aTmp[0]==null?aTmp[1].toString():aTmp[0].toString(), Integer.parseInt(aTmp[3].toString()));
		}
		return returnResult;
	}

	public Map<String, Integer> statisticsByLayerOfEstablishmentNum(List<Map<String, Integer>> numRange,District district,String organId, Integer hisFlag){
		Map<String, Integer> returnResult = new HashMap<String, Integer>();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT swo.name, swo.organ.organName, swo.secrecyWorkOrganId, count(swo.secrecyWorkOrganId) as num  FROM SecrecyWorkOrgan swo ");
		hql.append(" JOIN swo.personGroupRelations swor where ");
		hql.append(" 1 = 1 ");
		if( StringUtils.isNotEmpty(organId) ){
			hql.append("AND swo.organ.organId = :organId ");
			params.put("organId", organId);
		}else if( district != null && StringUtils.isNotEmpty(district.getLayer()) ){
			hql.append("AND swo.organ.district.layer LIKE :layer ");
			params.put("layer", district.getLayer()+"%");
		}

		if( hisFlag != null  ){
			hql.append("AND (swor.secrecyStatus is null or swor.secrecyStatus =:secrecyStatusNow ) ");
			params.put("secrecyStatusNow", hisFlag);
		}

		hql.append("GROUP BY ");
		hql.append("swo.organAdminLevel, swo.name, swo.organ.organName");
		List<Object[]> result = this.getPersistProxy().getOrmPersistence().findList(hql.toString(), params);

		for( Object[] aTmp : result ){
			returnResult.put(aTmp[0]==null?aTmp[1].toString():aTmp[0].toString(), Integer.parseInt(aTmp[3].toString()));
		}
		return returnResult;
	}

	//内部调用--行政区划使用
	public Map<String, Integer> statisticsDistinctOfEstablishmentNum(List<Map<String, Integer>> numRange,String layer,String organId, Integer hisFlag){
		Map<String,Integer> returnResult = new HashMap<String, Integer>();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT swo.secrecyWorkOrganId, count(swo.secrecyWorkOrganId) as num  FROM SecrecyWorkOrgan swo ");
		hql.append(" JOIN swo.personGroupRelations swor where ");
		hql.append(" 1 = 1 ");
		if( StringUtils.isNotEmpty(organId) ){
			hql.append("AND swo.organ.organId = :organId ");
			params.put("organId", organId);
		}else if( StringUtils.isNotEmpty(layer) ){
			hql.append("AND swo.organ.district.layer LIKE :layer ");
			params.put("layer", layer+"%");
		}

		if( hisFlag != null  ){
			hql.append("AND (swor.secrecyStatus is null or swor.secrecyStatus =:secrecyStatusNow ) ");
			params.put("secrecyStatusNow", hisFlag);
		}

		hql.append("GROUP BY ");
		hql.append("swo.organAdminLevel, swo.name, swo.organ.organName");
		List<Object[]> result = this.getPersistProxy().getOrmPersistence().findList(hql.toString(), params);
		if( result!=null && result.size()>0 ){
			for( Object[] tmp : result){
				//单位id   人数
				returnResult.put(tmp[0].toString(), Integer.parseInt(tmp[1].toString())) ;
			}
		}
		return returnResult;
	}
	//内部调用--直辖机构使用
	public Integer statisticsOrganOfEstablishmentNum(List<Map<String, Integer>> numRange,String TmpOrganId,String organId, Integer hisFlag){
		Integer returnResult = 0;
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT swo.secrecyWorkOrganId, count(swo.secrecyWorkOrganId) as num  FROM SecrecyWorkOrgan swo ");
		hql.append(" JOIN swo.personGroupRelations swor where ");
		hql.append(" 1 = 1 ");
		if( StringUtils.isNotEmpty(organId) ){
			hql.append("AND swo.organ.organId = :organId ");
			params.put("organId", organId);
		}else if( StringUtils.isNotEmpty(TmpOrganId) ){
			hql.append("AND swo.organ.organId = :organId ");
			params.put("organId", TmpOrganId);
		}

		if( hisFlag != null  ){
			hql.append("AND (swor.secrecyStatus is null or swor.secrecyStatus =:secrecyStatusNow ) ");
			params.put("secrecyStatusNow", hisFlag);
		}

		hql.append("GROUP BY ");
		hql.append("swo.organAdminLevel, swo.name, swo.organ.organName");
		List<Object[]> result = this.getPersistProxy().getOrmPersistence().findList(hql.toString(), params);
		if( result!=null && result.size()>0 && result.get(0)!=null && result.get(0)[1]!=null ){
			returnResult = Integer.parseInt(result.get(0)[1].toString());
		}
		return returnResult;
	}

	//子行政区划
	public Map<String,Map<String, Integer>> statisticsByEstablishmentNumOfDistrict(List<Map<String, Integer>> numRange,District district,String organId, Integer hisFlag){
		Map<String,Map<String, Integer>> returnResult = new HashMap<String,Map<String, Integer>>();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append("SELECT district2_.district_name,district2_.district_code, district2_.layer FROM SYS_DISTRICT district2_ ");
		sqlStr.append("WHERE district2_.pid = :districtCode ");
		params.put("districtCode", district.getDistrictCode());
		List<Map<String,Object>> subDistrict = this.getPersistProxy().getJdbcPersistence().findList(sqlStr.toString(), params);
		if( subDistrict!=null ){
			for( Map<String,Object> aDistrict : subDistrict ){
				returnResult.put(aDistrict.get("district_name").toString(),this.statisticsDistinctOfEstablishmentNum(numRange,aDistrict.get("layer").toString(),organId, hisFlag));
			}
		}
		return returnResult;
	}
	//子直属机构
	public Map<String, Integer> statisticsByEstablishmentNumOfOrgan(List<Map<String, Integer>> numRange,District district,String organId, Integer hisFlag){
		Map<String, Integer> returnResult = new HashMap<String, Integer>();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT o FROM  Organ o WHERE 1=1 ");
		hql.append("AND o.district.districtCode = :districtCode ");
		params.put("districtCode", district.getDistrictCode());
		List<Organ> subOrgan = this.getPersistProxy().getOrmPersistence().findList(hql.toString(), params);
		if( subOrgan!=null ){
			for( Organ tmpOrgan : subOrgan ){
				returnResult.put(tmpOrgan.getName(),this.statisticsOrganOfEstablishmentNum(numRange,tmpOrgan.getOrganId(),organId, hisFlag));
			}
		}
		return returnResult;
	}
	/**
	 * @param secrecyWorkOrganRelationMemberModuleService 设置secrecyWorkOrganRelationMemberModuleService
	 */
	public void setSecrecyWorkOrganRelationMemberModuleService(
			SecrecyWorkOrganRelationMemberModuleService secrecyWorkOrganRelationMemberModuleService) {
		this.secrecyWorkOrganRelationMemberModuleService = secrecyWorkOrganRelationMemberModuleService;
	}

	/**
	 * @param secrecyWorkOrganMemberUnitModuleService 设置secrecyWorkOrganMemberUnitModuleService
	 */
	public void setSecrecyWorkOrganMemberUnitModuleService(
			SecrecyWorkOrganMemberUnitModuleService secrecyWorkOrganMemberUnitModuleService) {
		this.secrecyWorkOrganMemberUnitModuleService = secrecyWorkOrganMemberUnitModuleService;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SecrecyWorkOrgan queryOrganIndex(Organ organ, QueryDto queryDto) {
		SecrecyWorkOrgan  secrecyWorkOrgan  = new SecrecyWorkOrgan();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("organId", organ.getOrganId());
		secrecyWorkOrgan = unique("from SecrecyWorkOrgan s where s.organ.organId = :organId", params);
		return secrecyWorkOrgan;
	}
}