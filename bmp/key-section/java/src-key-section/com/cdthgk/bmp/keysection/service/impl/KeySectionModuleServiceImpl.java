package com.cdthgk.bmp.keysection.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.bmp.keysection.qo.KeySectionQo;
import com.cdthgk.bmp.keysection.service.KeySectionModuleService;
import com.cdthgk.bmp.keysection.vo.KeySection;
import com.cdthgk.code.enums.DataStatus;
import com.cdthgk.common.bean.BeanUtils;
import com.cdthgk.common.lang.CollectionUtils;
import com.cdthgk.common.lang.LangUtils;
import com.cdthgk.platform.dictionary.domain.DictionaryField;
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
 * 要害部门Service类（模块内使用）
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
public class KeySectionModuleServiceImpl extends BmpServiceImpl<KeySection, Serializable>
				implements KeySectionModuleService{

	private static final Logger LOGGER = LoggerFactory
			.getLogger(KeySectionModuleServiceImpl.class);

	// 构造器
	/** 默认构造器 */
	public KeySectionModuleServiceImpl() {
	}

	/**
	 * <p>
	 * 获取要害部门列表
	 * </p>
	 * <p>
	 * 牟远洋 2012-12-14 18:01
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
	 * @author mouyuanyang
	 * @since 1.0
	 * @version 1.0
	 * @param psm PageSortModel
	 * @param organ 单位
	 * @return List<KeySection>
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<KeySection> getPageList(PageSortModel psm, Organ organ) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("From KeySection k where 1=1");
		hql.append(" and k.organ.organId = :organId");
		params.put("organId", organ.getOrganId());
		// 按创建时间降序排列
		hql.append(" order by k.createTime desc");

		return (List<KeySection>) findList(hql.toString(), params, psm);
	}

	/**
	 * <p>
	 * 保存用户信息
	 * </p>
	 * <p>
	 * 牟远洋 2012-12-21 18:01
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
	 * @author mouyuanyang
	 * @since 1.0
	 * @version 1.0
	 * @param name 用户名称
	 * @param user 人员
	 * @return userInfo
	 */
	@Override
	public UserInfo saveUserInfo(UserInfo userInfo, User user) {
		//如果单位下的人员存在，直接返回该人员
		List<UserInfo> userInfoList = (List<UserInfo>) OrganizationContext.getInstance().getMemberService()
				.getByName(userInfo.getName(), user.getOrgan());
		if (CollectionUtils.isNotEmpty(userInfoList)) {
			return userInfoList.get(0);
		}
		Date d = new Date();

		userInfo.setOrgan(user.getOrgan());
		userInfo.setCreateTime(d);
		userInfo.setCreatePerson(user);
		userInfo.setModifyPerson(user);
		userInfo.setModifyTime(d);
		if (LangUtils.isEmpty(userInfo.getUserInfoId())) {
			userInfo.setUserInfoId(null);
		}
		userInfo.save();
		return userInfo;
	}

	/**
	 * <p>
	 * 保存部门信息
	 * </p>
	 * <p>
	 * 牟远洋 2012-12-21 18:01
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
	 * @author mouyuanyang
	 * @since 1.0
	 * @version 1.0
	 * @param departmentName 部门名称
	 * @param user 人员
	 * @return userInfo
	 */
	@Override
	public Department saveDepartment(String departmentName, User user) {
		Department deptDb = OrganizationContext.getInstance().getDepartmentService().getByName(departmentName, user.getOrgan());
		if (deptDb != null) {
			return deptDb;
		}
		Department department = new Department();
		Date d = new Date();
		department.setDepartmentName(departmentName);
		department.setOrgan(user.getOrgan());
		department.setParent(user.getOrgan().getDepartment());
		department.setStatus(DataStatus.USE);
		department.setCreateTime(d);
		department.setCreatePerson(user);
		department.setModifyPerson(user);
		department.setModifyTime(d);
		department.save();
		return department;
	}

	/**
	 * <p>
	 * 查询列表
	 * </p>
	 * <p>
	 * 牟远洋 2012-12-21 18:01
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
	 * @author mouyuanyang
	 * @since 1.0
	 * @version 1.0
	 * @param psm PageSortModel
	 * @param organ 当前登录单位
	 * @param keySectionQo keySectionQo
	 * @return List<KeySection>
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<KeySection> queryList(PageSortModel psm, Organ organ,
			KeySectionQo keySectionQo) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("From KeySection k where 1=1 and k.secrecyStatus!=1 ");
		hql.append(" and k.organ.organId = :organId");
		params.put("organId", organ.getOrganId());
		// 按创建时间降序排列
		if (keySectionQo != null) {
			if (LangUtils.isNotEmpty(keySectionQo.getPrincipal())) {
				hql.append(" and k.principal.name like :principal");
				params.put("principal", "%" + keySectionQo.getPrincipal() + "%");
			}
			if (LangUtils.isNotEmpty(keySectionQo.getDepartmentName())) {
				hql.append(" and k.department.departmentName like :departmentName");
				params.put("departmentName", "%" + keySectionQo.getDepartmentName() + "%");
			}
			if (LangUtils.isNotEmpty(keySectionQo.getSecrecyLevel())) {
				hql.append(" and k.secrecyLevel = :secrecyLevel");
				params.put("secrecyLevel", keySectionQo.getSecrecyLevel());
			}
			if (LangUtils.isNotEmpty(keySectionQo.getPhone())) {
				hql.append(" and k.phone like :phone");
				params.put("phone", "%" + keySectionQo.getPhone() + "%");
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
	 *
	 */
	public List<KeySection> queryList(Organ organ,KeySectionQo keySectionQo) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("From KeySection k where 1=1 AND k.secrecyStatus!=1 ");
		hql.append(" and k.organ.organId = :organId");
		params.put("organId", organ.getOrganId());
		// 按创建时间降序排列
		if (keySectionQo != null) {
			if (LangUtils.isNotEmpty(keySectionQo.getPrincipal())) {
				hql.append(" and k.principal.name like :principal");
				params.put("principal", "%" + keySectionQo.getPrincipal() + "%");
			}
			if (LangUtils.isNotEmpty(keySectionQo.getDepartmentName())) {
				hql.append(" and k.department.departmentName like :departmentName");
				params.put("departmentName", "%" + keySectionQo.getDepartmentName() + "%");
			}
			if (LangUtils.isNotEmpty(keySectionQo.getSecrecyLevel())) {
				hql.append(" and k.secrecyLevel = :secrecyLevel");
				params.put("secrecyLevel", keySectionQo.getSecrecyLevel());
			}
			if (LangUtils.isNotEmpty(keySectionQo.getPhone())) {
				hql.append(" and k.phone like :phone");
				params.put("phone", "%" + keySectionQo.getPhone() + "%");
			}
		}
		hql.append(" order by k.createTime desc");

		return (List<KeySection>) this.findList(hql.toString(), params);
	}

	public List<DictionaryOption> getDictionaryOptionList(String tableCode, String fieldCode){
		Map<String ,Object> params = new HashMap<String, Object>();
		String hql = "SELECT dop FROM DictionaryTable dt, DictionaryField df, DictionaryOption dop WHERE df.dictionaryTable.tableId = dt.tableId AND dop.dictionaryField.fieldId=df.fieldId AND" +
					" dt.tableCode=:tableCode AND df.fieldCode=:fieldCode";
		params.put("tableCode", tableCode);
		params.put("fieldCode", fieldCode);
		List<DictionaryOption> dictionaryOptionList = this.getPersistProxy().getOrmPersistence().findList(hql, params);
		return dictionaryOptionList;
	}

	/**
	 * <p>
	 * 查询列表(首页获取关键部位查询列表)
	 * </p>
	 * <p>
	 * 宋亚非 2013-04-10 09:24
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
	 * @author mouyuanyang
	 * @since 1.0
	 * @version 1.0
	 * @param psm PageSortModel
	 * @param organ 当前登录单位
	 * @param keySectionQo keySectionQo
	 * @return List<KeySection>
	 */
	@Override
	public List<KeySection> queryList(PageSortModel psm, Organ organ,
			KeySectionQo keySectionQo, String showType) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("From KeySection k where 1=1 and k.secrecyStatus!=1 ");
		if( organ!=null ){
			organ = get(organ.getOrganId(), Organ.class);
			String layer = organ.getDistrict().getLayer();
			if( showType!=null){
				if( "1".equals(showType) ){
					hql.append(" and k.organ.district.layer like :layer");
					params.put("layer", layer + "%");
				}else{
					hql.append(" and k.organ.organId = :organId");
					params.put("organId", organ.getOrganId());
				}
			}else{
				hql.append(" and k.organ.organId = :organId");
				params.put("organId", organ.getOrganId());
//				hql.append(" and k.organ.layer like :layer");
//				params.put("layer", layer + "%");
			}
		}
		// 按创建时间降序排列
		if (keySectionQo != null) {
			if (LangUtils.isNotEmpty(keySectionQo.getPrincipal())) {
				hql.append(" and k.principal.name like :principal");
				params.put("principal", "%" + keySectionQo.getPrincipal() + "%");
			}
			if (LangUtils.isNotEmpty(keySectionQo.getDepartmentName())) {
				hql.append(" and k.department.departmentName like :departmentName");
				params.put("departmentName", "%" + keySectionQo.getDepartmentName() + "%");
			}
			if (LangUtils.isNotEmpty(keySectionQo.getSecrecyLevel())) {
				hql.append(" and k.secrecyLevel = :secrecyLevel");
				params.put("secrecyLevel", keySectionQo.getSecrecyLevel());
			}
			if (LangUtils.isNotEmpty(keySectionQo.getPhone())) {
				hql.append(" and k.phone like :phone");
				params.put("phone", "%" + keySectionQo.getPhone() + "%");
			}
		}
		hql.append(" order by k.createTime desc");

		return (List<KeySection>) this.findList(hql.toString(), params, psm);
	}


	/**
	 * <p>
	 * 查询部门信息
	 * </p>
	 * <p>
	 * 牟远洋 2012-12-22 18:01
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
	 * @author mouyuanyang
	 * @since 1.0
	 * @version 1.0
	 * @param departmentName 部门名称
	 * @param organ 当前登录单位
	 * @return List<KeySection>
	 */
	@Override
	public int searchDepartment(String departmentName, Organ organ) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("select count(*)From KeySection k where 1=1 and k.secrecyStatus!=1");
		if (LangUtils.isNotEmpty(departmentName)) {
			hql.append(" and k.department.departmentName = :departmentName");
			params.put("departmentName", departmentName);
			hql.append(" and k.organ.organId = :organId");
			params.put("organId", organ.getOrganId());
			return count(hql.toString(), params).intValue();
		} else {
			return 0;
		}
	}

	public List<Long> countSectionData(Organ organ) {
		// 要害部门统计
		List<Long> keySectionArgs = new ArrayList<Long>();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("select count(s) from KeySection s where 1=1 and s.secrecyStatus!=1");
		hql.append(" and s.secrecyLevel = :secrecyLevel");
		hql.append(" and s.organ.organId = :organId");
		// 等级3修改为等级1 解决首页统计要害部门中核心与一般等级错位。
		params.put("secrecyLevel", 1);
		params.put("organId", organ.getOrganId());
		Long countNum =  this.unique(hql.toString(), params, Long.class);
		keySectionArgs.add(0, countNum);

		params.put("secrecyLevel", 2);
		countNum =  this.unique(hql.toString(), params, Long.class);
		keySectionArgs.add(1, countNum);

		// 等级1修改为等级3 解决首页统计要害部门中核心与一般等级错位。
		params.put("secrecyLevel", 3);
		countNum =  this.unique(hql.toString(), params, Long.class);
		keySectionArgs.add(2, countNum);
		return keySectionArgs;
	}

	/**
	 * 保密局统计  要害部门总数
	 * @param district
	 * @return
	 */
	public List<Long> countSectionData(District district) {
		// 要害部门统计
		List<Long> keySectionArgs = new ArrayList<Long>();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("select count(s) from KeySection s where 1=1 and s.secrecyStatus!=1");
		hql.append(" and s.secrecyLevel = :secrecyLevel");
		hql.append(" and s.organ.district.layer like :layer");
		// 等级3修改为等级1 解决首页统计要害部门中核心与一般等级错位。
		params.put("secrecyLevel", 1);
		params.put("layer", district.getLayer()+"%");
		Long countNum =  this.unique(hql.toString(), params, Long.class);
		keySectionArgs.add(0, countNum);

		params.put("secrecyLevel", 2);
		countNum =  this.unique(hql.toString(), params, Long.class);
		keySectionArgs.add(1, countNum);

		// 等级1修改为等级3 解决首页统计要害部门中核心与一般等级错位。
		params.put("secrecyLevel", 3);
		countNum =  this.unique(hql.toString(), params, Long.class);
		keySectionArgs.add(2, countNum);
		return keySectionArgs;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public void saveRecKeySection(List<KeySection> keySectionList,
			String receiveOrganId) {
		if (keySectionList == null) {
			return;
		}
		for (KeySection keySection : keySectionList) {
			KeySection section = this.get(keySection.getKeySectionId());
			if (null != section) { // null != eno 说明已经保存过，现在只需更新
				try {
					BeanUtils.copyProperties(section, keySection);
				} catch(Exception e) {
					LOGGER.debug("属性转换异常:" + e.getMessage());
				}
				this.update(section);
			} else { // 第一次保存
				this.save(keySection);
			}
		}

	}


	/**
	 * <p>
	 * 首页统计详情列表
	 * </p>
	 * <p>
	 * 刘椿成 2012-08-29 10:34:56
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>宋亚非  2013-04-09  从市平台移植到省平台
	 * </ul>
	 * </blockquote>
	 * <p>
	 * copyright tianyu 2012, all rights reserved.
	 * </p>
	 *
	 *  @param PageSortModel psm
	 *  @param Organ organ
	 *  @param Integer secrecyLevel
	 *  @param String districtCode
	 *  @return List<Section>
	 */
	@Override
	public List<KeySection> getIndexPage(PageSortModel psm,
			Organ organ,Integer secrecyLevel,String districtCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder(
				"FROM KeySection s where 1=1 and s.secrecyStatus!=1");
		if(organ !=null){
			hql.append("and s.organ.organId = :organId ");
			params.put("organId", organ.getOrganId());
		}
		hql.append("and s.secrecyLevel = :secrecyLevel ");
		params.put("secrecyLevel", secrecyLevel);
		hql.append(" and s.organ.district.districtCode = :districtCode");
		params.put("districtCode", districtCode);
		return (List<KeySection>) findList(hql.toString(),params, psm);
	}


	/**
	 * 按照行政区划统计  要害部门
	 * @param psm
	 * @param district
	 * @param keySectionQo
	 * @param isChildren 是否包含下级  1包含  0不包含
	 * @return
	 */
	public List<KeySection> queryList(PageSortModel psm, District district,Integer isChildren,
			KeySectionQo keySectionQo) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("From KeySection k where 1=1 and k.secrecyStatus!=1 ");
		//行政区划不为空
		if(district!=null && district.getLayer()!=null) {
			if(isChildren==1) {
				hql.append(" and k.organ.district.layer like :layer");
				params.put("layer", district.getLayer()+"%");
			}else {
				hql.append(" and k.organ.district.layer = :layer");
				params.put("layer", district.getLayer());
			}
		}

		// 按创建时间降序排列
		if (keySectionQo != null) {
			if (LangUtils.isNotEmpty(keySectionQo.getPrincipal())) {
				hql.append(" and k.principal.name like :principal");
				params.put("principal", "%" + keySectionQo.getPrincipal() + "%");
			}
			if (LangUtils.isNotEmpty(keySectionQo.getDepartmentName())) {
				hql.append(" and k.department.departmentName like :departmentName");
				params.put("departmentName", "%" + keySectionQo.getDepartmentName() + "%");
			}
			if (LangUtils.isNotEmpty(keySectionQo.getSecrecyLevel())) {
				hql.append(" and k.secrecyLevel = :secrecyLevel");
				params.put("secrecyLevel", keySectionQo.getSecrecyLevel());
			}
			if (LangUtils.isNotEmpty(keySectionQo.getPhone())) {
				hql.append(" and k.phone like :phone");
				params.put("phone", "%" + keySectionQo.getPhone() + "%");
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
