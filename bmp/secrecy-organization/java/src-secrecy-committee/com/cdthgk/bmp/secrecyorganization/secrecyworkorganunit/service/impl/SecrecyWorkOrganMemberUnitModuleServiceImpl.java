package com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.service.SecrecyWorkOrganMemberUnitModuleService;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.vo.SecrecyWorkOrgan;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.vo.SecrecyWorkOrganMemberUnit;
import com.cdthgk.common.lang.LangUtils;
import com.cdthgk.platform.organization.core.OrganizationContext;
import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;
import com.cdthgk.platform.permission.user.domain.User;
import com.cdthgk.standard.asserts.AssertStandardApp;

import ec.common.PageSortModel;

/**
 * <p>
 * 保密办成员  Service 实现类
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
 * @author 刘椿成
 * @since 1.0
 * @version 1.0
 */
public class SecrecyWorkOrganMemberUnitModuleServiceImpl extends BmpServiceImpl<SecrecyWorkOrganMemberUnit, String> implements SecrecyWorkOrganMemberUnitModuleService {

	// 构造器
	/** 默认构造器 */
	SecrecyWorkOrganMemberUnitModuleServiceImpl() {
	}

	/**
	 * 保密办成员列表
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
	public List<SecrecyWorkOrganMemberUnit> getPageList(SecrecyWorkOrgan secrecyWorkOrgan,Organ organ, Integer personChageFlagNow) {

		List<SecrecyWorkOrganMemberUnit> list = new ArrayList<SecrecyWorkOrganMemberUnit>();

		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("from SecrecyWorkOrganMemberUnit su where 1=1 ");
		if(organ!=null){
			hql.append(" and su.secrecyWorkOrgan.organ.organId = :organId ");
			params.put("organId", organ.getOrganId());
		}
		if (secrecyWorkOrgan != null) {
			if (!StringUtils.isEmpty(secrecyWorkOrgan.getSecrecyWorkOrganId())) {
				// 查询保密组织机构
				hql.append(" and su.secrecyWorkOrgan.secrecyWorkOrganId = :secrecyWorkOrganId");
				params.put("secrecyWorkOrganId", secrecyWorkOrgan.getSecrecyWorkOrganId());
			}
		}
		//过滤已发生过人员变更的信息
		if( personChageFlagNow!=null ){
			hql.append(" and (su.secrecyStatus = :secrecyStatus or su.secrecyStatus is null )");
			params.put("secrecyStatus", personChageFlagNow);
		}
		hql.append(" order by su.sort asc");
		list =  this.findList(hql.toString(), params, 0,1000);

		return list;
	}


	/**
	 * 保存保密办成员信息
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
	public SecrecyWorkOrganMemberUnit saveSystemDataBySecrecyWorkOrgan(
			SecrecyWorkOrganMemberUnit secrecyWorkOrganMemberUnit,User currentUser, String deptFlag) {
		AssertStandardApp.isNotNull(secrecyWorkOrganMemberUnit.getPerson(), "输入的人员不能为空");
		AssertStandardApp.isNotNull(secrecyWorkOrganMemberUnit.getPerson().getDepartment(), "输入的部门不能为空");
		//当前单位下输入的部门如果有则保存，如果没有则通过输入的部门名称新增部门。
		Department department = new Department();
		if (LangUtils.isEmpty(secrecyWorkOrganMemberUnit.getPerson().getDepartment().getDepartmentId())) {
			//部门变更或者部门改名的标志：1：部门改名；2部门变更
			if(deptFlag != null && "1".equals(deptFlag)) {
				Department existDepartment  = this.get(secrecyWorkOrganMemberUnit.getOldDeptId(), Department.class);
				existDepartment.setDepartmentName(secrecyWorkOrganMemberUnit.getPerson().getDepartment()
						.getDepartmentName());
				department = existDepartment;
				department.update();
			} else {
				//变更时，如果存在该部门，则不理，把关系建立起。
				Department existDepartment = OrganizationContext.getInstance().getDepartmentService()
						.getByName(secrecyWorkOrganMemberUnit.getPerson().getDepartment()
						.getDepartmentName(), currentUser.getOrgan());
				if(existDepartment == null){
					//当前单位不存在该部门，新增系统部门
					department.setDepartmentName(secrecyWorkOrganMemberUnit.getPerson().getDepartment()
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
		} else {
			department = this.get(secrecyWorkOrganMemberUnit.getPerson().getDepartment()
					.getDepartmentId(), Department.class);
		}
		//当前单位下输入的人员如果有则保存，如果没有则通过输入的名字新增人员。
		//姓 名
		UserInfo userInfo = new UserInfo();
		if (LangUtils.isEmpty(secrecyWorkOrganMemberUnit.getPerson().getUserInfoId())) {
			//FIXME 先检查是否存在该人员(注意这里返回的是集合需要修改)

			List<UserInfo> userInfoList = (List<UserInfo>) OrganizationContext.getInstance().getMemberService()
					.getByName(secrecyWorkOrganMemberUnit.getPerson().getName());
			if(userInfoList != null && userInfoList.size() > 0) {
				userInfo = userInfoList.get(0);
				userInfo.setDuty(secrecyWorkOrganMemberUnit.getPerson().getDuty());
			} else {
				//当前单位不存在该人员，新增系统人员
				userInfo.setDuty(secrecyWorkOrganMemberUnit.getPerson().getDuty());
				userInfo.setPhone(secrecyWorkOrganMemberUnit.getPerson().getPhone());
				userInfo.setName(secrecyWorkOrganMemberUnit.getPerson().getName());
				userInfo.setOrgan(currentUser.getOrgan());
				userInfo.setDepartment(currentUser.getOrgan().getDepartment());

 				userInfo.setSex(secrecyWorkOrganMemberUnit.getPerson().getSex());
				userInfo.setBirthDate(secrecyWorkOrganMemberUnit.getPerson().getBirthDate());
				userInfo.setLearningLevel(secrecyWorkOrganMemberUnit.getPerson().getLearningLevel());
				userInfo.setPolity(secrecyWorkOrganMemberUnit.getPerson().getPolity());
				userInfo.setTechnicTitleLevel(secrecyWorkOrganMemberUnit.getPerson().getTechnicTitleLevel());
				userInfo.setSpecialtyCode(secrecyWorkOrganMemberUnit.getPerson().getSpecialtyCode());
				userInfo.setAdministrativeLevel(secrecyWorkOrganMemberUnit.getPerson().getAdministrativeLevel());

				userInfo.setCreatePerson(currentUser);
				userInfo.setModifyPerson(currentUser);
				userInfo.save();
			}
		} else {
			userInfo = this.get(secrecyWorkOrganMemberUnit
					.getPerson().getUserInfoId(), UserInfo.class);
			userInfo.setName(secrecyWorkOrganMemberUnit.getPerson().getName());
			userInfo.setDuty(secrecyWorkOrganMemberUnit.getPerson().getDuty());
			userInfo.setPhone(secrecyWorkOrganMemberUnit.getPerson().getPhone());

			userInfo.setSex(secrecyWorkOrganMemberUnit.getPerson().getSex());
			userInfo.setBirthDate(secrecyWorkOrganMemberUnit.getPerson().getBirthDate());
			userInfo.setLearningLevel(secrecyWorkOrganMemberUnit.getPerson().getLearningLevel());
			userInfo.setPolity(secrecyWorkOrganMemberUnit.getPerson().getPolity());
			userInfo.setTechnicTitleLevel(secrecyWorkOrganMemberUnit.getPerson().getTechnicTitleLevel());
			userInfo.setSpecialtyCode(secrecyWorkOrganMemberUnit.getPerson().getSpecialtyCode());
			userInfo.setAdministrativeLevel(secrecyWorkOrganMemberUnit.getPerson().getAdministrativeLevel());
		}
		userInfo.setDepartment(department);
		secrecyWorkOrganMemberUnit.setPerson(userInfo);
		return secrecyWorkOrganMemberUnit;
	}
}