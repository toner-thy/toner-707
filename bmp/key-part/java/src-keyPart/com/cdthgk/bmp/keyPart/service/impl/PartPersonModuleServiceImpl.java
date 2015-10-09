package com.cdthgk.bmp.keyPart.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.bmp.keyPart.service.PartPersonModuleService;
import com.cdthgk.bmp.keyPart.vo.Part;
import com.cdthgk.bmp.keyPart.vo.PartPerson;
import com.cdthgk.bmp.secrecyperson.vo.SecrecyPerson;
import com.cdthgk.code.enums.Nationality;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

/**
 * <p>
 * PartPersonModuleServiceImpl
 * 部位人员内部使用方法显示类
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
public class PartPersonModuleServiceImpl extends BmpServiceImpl<PartPerson, String> implements PartPersonModuleService {

	private PartPersonModuleService partPersonModuleService;

	// 构造器
	/** 默认构造器 */
	PartPersonModuleServiceImpl() {
	}

	/**
	 * 涉密人员列表
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
	@Override
	public List<PartPerson> getPersonListPage(PageSortModel psm,Part part, Organ organ, Integer nowFlag) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("FROM PartPerson pp WHERE 1 = 1 ");

		hql.append("and (pp.secrecyPerson.secrecyStatus is null or pp.secrecyPerson.secrecyStatus = :statusNow )");
		params.put("statusNow", nowFlag);
		// 按部位名称搜索
		if(part !=null){
			hql.append(" and pp.part.partId = :partId");
			params.put("partId", part.getPartId());
		}

		List<PartPerson> list = new ArrayList<PartPerson>();
		if(psm==null) {
			list =(List<PartPerson>) this.findList(hql.toString(), params);//不分页
		}else {
			list =(List<PartPerson>) this.findList(hql.toString(), params, psm);
		}
		return list;
	}

	/**
	 * <p>
	 * 保存人员信息
	 * </p>
	 * <p>
	 * @author 牟远洋 2013 1 11 14:00
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
	 * @version 1.0
	 * @param userInfo 人员对象
	 * @param currentUser 当前登录人员
	 * @param department 部门
	 * @return userInfo
	 */
	public UserInfo saveUserInfo(UserInfo userInfo, User currentUser, Department department) {

		// 该人员不存在，新增人员信息
		userInfo.setUserInfoId(null);
		userInfo.setDepartment(department);
		userInfo.setOrgan(currentUser.getOrgan());
		userInfo.setRemark(new Date().toString() + "产生。创建者：" + currentUser.getUserName()
				+ "(UserID:" + currentUser.getUserId() + ")；创建环境：在【保密业务】-【保密要害部位】-【机关涉密人员】-【新增涉密人员】时填写人员时创建。");
		userInfo.setCreatePerson(currentUser);
		userInfo.setModifyPerson(currentUser);
		userInfo.setCreateTime(new Date());
		userInfo.setModifyTime(new Date());
		userInfo.setNationality(Nationality.CN);

		userInfo.save();
		return userInfo;
	}

	/**
	 * 保存涉密人员业务字段
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
	@Override
	public SecrecyPerson saveSecrecyPerson(String name, SecrecyPerson secrecyPerson, User currentUser) {
		// 新增涉密人员
		SecrecyPerson secrecyPersonTemp = new SecrecyPerson();
		// 设置业务字段
		secrecyPersonTemp.setUserInfo(secrecyPerson.getUserInfo());
		secrecyPersonTemp.setOfficeDuty(secrecyPerson.getOfficeDuty());
		secrecyPersonTemp.setFirstWorkDate(secrecyPerson.getFirstWorkDate());
		secrecyPersonTemp.setPost(secrecyPerson.getPost());
		secrecyPersonTemp.setSecrecyPersonLevel(secrecyPerson.getSecrecyPersonLevel());
		secrecyPersonTemp.setSecSignBookTime(secrecyPerson.getSecSignBookTime());
		secrecyPersonTemp.setPoliticalStatus(secrecyPerson.getPoliticalStatus());
		secrecyPersonTemp.setSecUppostTime(secrecyPerson.getSecUppostTime());
		secrecyPersonTemp.setResume(secrecyPerson.getResume());
		secrecyPersonTemp.setOrganCheckOpinion(secrecyPerson.getOrganCheckOpinion());
		secrecyPersonTemp.setOfficePhone(secrecyPerson.getOfficePhone());
		// 设置通用字段
		secrecyPersonTemp.setCreatePerson(currentUser);
		secrecyPersonTemp.setCreateTime(new Date());
		secrecyPersonTemp.setOrgan(currentUser.getOrgan());

		this.getPersistProxy().getOrmPersistence().save(secrecyPersonTemp);
		return secrecyPersonTemp;
	}

	/**
	 * 保存部门
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
	@Override
	public Department saveDepartment(String departmentName) {
		Department department = new Department();
		department.setDepartmentName(departmentName);
		this.getPersistProxy().getOrmPersistence().save(department);
		return department;
	}

	// ******************** Setter & Getter ********************
	/**
	 * 返回partPersonModuleService
	 * @return partPersonModuleService
	 */
	public PartPersonModuleService getPartPersonModuleService() {
		return partPersonModuleService;
	}

	/**
	 * 设置partPersonModuleService
	 * @param partPersonModuleService partPersonModuleService
	 */
	public void setPartPersonModuleService(
			PartPersonModuleService partPersonModuleService) {
		this.partPersonModuleService = partPersonModuleService;
	}


	@Override
	public List<Long> countSectionData(Organ organ) {
		// 要害部位统计
		List<Long> keyPartArgs = new ArrayList<Long>();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("select count(p) from Part p where 1=1 and p.secrecyStatus!=1 ");
		hql.append(" and p.secrecyLevel = :secrecyLevel");
		hql.append(" and p.createperson.organ.organId = :organId");

		params.put("secrecyLevel", 1);
		params.put("organId", organ.getOrganId());
		Long countNum = this.unique(hql.toString(), params, Long.class);
		keyPartArgs.add(0,countNum);

		params.put("secrecyLevel", 2);
		countNum =  this.unique(hql.toString(), params, Long.class);
		keyPartArgs.add(1, countNum);

		params.put("secrecyLevel", 3);
		countNum = this.unique(hql.toString(), params, Long.class);
		keyPartArgs.add(2, countNum);
		return keyPartArgs;
	}

	/**按照行政区划
	 * 统计 保密要害部位
	 * @param district  行政区划对象
	 * @return
	 */
	public List<Long> countPartData(District district) {
		// 要害部位统计
		List<Long> keyPartArgs = new ArrayList<Long>();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("select count(p) from Part p where 1=1 and p.secrecyStatus!=1 ");
		hql.append(" and p.secrecyLevel = :secrecyLevel");
		hql.append(" and p.organ.district.layer like :layer");

		params.put("secrecyLevel", 1);
		params.put("layer", district.getLayer()+"%");
		Long countNum = this.unique(hql.toString(), params, Long.class);
		keyPartArgs.add(0,countNum);

		params.put("secrecyLevel", 2);
		countNum =  this.unique(hql.toString(), params, Long.class);
		keyPartArgs.add(1, countNum);

		params.put("secrecyLevel", 3);
		countNum = this.unique(hql.toString(), params, Long.class);
		keyPartArgs.add(2, countNum);
		return keyPartArgs;
	}
}