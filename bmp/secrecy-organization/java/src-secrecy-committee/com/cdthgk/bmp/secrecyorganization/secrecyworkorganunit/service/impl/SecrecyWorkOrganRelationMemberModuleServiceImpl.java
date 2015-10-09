package com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.bmp.secrecyorganization.secrecycommittee.vo.PersonGroupPosition;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.service.SecrecyWorkOrganRelationMemberModuleService;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.vo.PersonGroupRelationChange;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.vo.SecrecyWorkOrgan;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.vo.SecrecyWorkOrganRelationMember;
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
 * 保密工作机构成员  Service 实现类
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
public class SecrecyWorkOrganRelationMemberModuleServiceImpl extends BmpServiceImpl<SecrecyWorkOrganRelationMember, String> implements SecrecyWorkOrganRelationMemberModuleService {

	// 构造器
	/** 默认构造器 */
	SecrecyWorkOrganRelationMemberModuleServiceImpl() {
	}

	/**
	 * <p>
	 * 保密工作机构成员列表
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
	@Override
	public List<SecrecyWorkOrganRelationMember> getPageList(SecrecyWorkOrgan secrecyWorkOrgan,Organ oragn, Integer personChangeNowFlag) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("from SecrecyWorkOrganRelationMember su where 1=1 ");
		if (secrecyWorkOrgan != null) {
			if (!StringUtils.isEmpty(secrecyWorkOrgan.getSecrecyWorkOrganId())) {
				// 查询保密工作机构成员
				hql.append(" and su.secrecyWorkOrgan.secrecyWorkOrganId = :secrecyWorkOrganId");
				params.put("secrecyWorkOrganId", secrecyWorkOrgan.getSecrecyWorkOrganId());

				//过滤已变更人员信息
				if( personChangeNowFlag != null ){
					hql.append(" and ( su.secrecyStatus = :secrecyStatus or su.secrecyStatus is null) ");
					params.put("secrecyStatus", personChangeNowFlag);
				}
			}
		} else {
			return new ArrayList<SecrecyWorkOrganRelationMember>();
		}
		hql.append(" order by su.sort asc");
		return (List<SecrecyWorkOrganRelationMember>) this.findList(hql.toString(), params, 0,1000);
	}


	/**
	 * <p>
	 * 根据情况填写新增/更新系统部门 人员
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
	@Override
	public SecrecyWorkOrganRelationMember saveSystemDataBySecrecyWorkOrgan(
			SecrecyWorkOrganRelationMember secrecyWorkOrganRelationMember,User currentUser, String deptFlag) {
		AssertStandardApp.isNotNull(secrecyWorkOrganRelationMember.getPerson(), "输入的人员不能为空");
		AssertStandardApp.isNotNull(secrecyWorkOrganRelationMember.getPerson().getDepartment(), "输入的部门不能为空");
		//当前单位下输入的部门如果有则保存，如果没有则通过输入的部门名称新增部门。
		Department department = new Department();
		if (LangUtils.isEmpty(secrecyWorkOrganRelationMember.getPerson().getDepartment().getDepartmentId())) {
			//部门变更或者部门改名的标志：1：部门改名；2部门变更
			if(deptFlag != null && "1".equals(deptFlag)) {
				Department existDepartment  = this.get(secrecyWorkOrganRelationMember.getOldDeptId(), Department.class);
				existDepartment.setDepartmentName(secrecyWorkOrganRelationMember.getPerson().getDepartment()
						.getDepartmentName());
				department = existDepartment;
				department.update();
			} else {
				//变更时，如果存在该部门，则不理，把关系建立起。
				Department existDepartment = OrganizationContext.getInstance().getDepartmentService()
						.getByName(secrecyWorkOrganRelationMember.getPerson().getDepartment()
						.getDepartmentName(), currentUser.getOrgan());
				if(existDepartment == null){
					//当前单位不存在该部门，新增系统部门
					department.setDepartmentName(secrecyWorkOrganRelationMember.getPerson().getDepartment()
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
			department = this.get(secrecyWorkOrganRelationMember.getPerson().getDepartment()
					.getDepartmentId(), Department.class);
		}
		//当前单位下输入的人员如果有则保存，如果没有则通过输入的名字新增人员。
		//姓 名
		UserInfo userInfo = new UserInfo();
		if (LangUtils.isEmpty(secrecyWorkOrganRelationMember.getPerson().getUserInfoId())) {
			//FIXME 先检查是否存在该人员(注意这里返回的是集合需要修改)

			List<UserInfo> userInfoList = (List<UserInfo>) OrganizationContext.getInstance().getMemberService()
					.getByName(secrecyWorkOrganRelationMember.getPerson().getName());
			if(userInfoList != null && userInfoList.size() > 0) {
				userInfo = userInfoList.get(0);
				userInfo.setDuty(secrecyWorkOrganRelationMember.getPerson().getDuty());
                                userInfo.setPhone(secrecyWorkOrganRelationMember.getPerson().getPhone());
                                userInfo.setName(secrecyWorkOrganRelationMember.getPerson().getName());

                                userInfo.setBirthDate(secrecyWorkOrganRelationMember.getPerson().getBirthDate());
                                userInfo.setSex(secrecyWorkOrganRelationMember.getPerson().getSex());
                                userInfo.setLearningLevel(secrecyWorkOrganRelationMember.getPerson().getLearningLevel());
                                userInfo.setPolity(secrecyWorkOrganRelationMember.getPerson().getPolity());
                                userInfo.setMobile(secrecyWorkOrganRelationMember.getPerson().getMobile());

                                userInfo.setSpecialtyCode(secrecyWorkOrganRelationMember.getPerson().getSpecialtyCode());
                                userInfo.setTechnicTitleLevel(secrecyWorkOrganRelationMember.getPerson().getTechnicTitleLevel());
                                userInfo.setAdministrativeLevel(secrecyWorkOrganRelationMember.getPerson().getAdministrativeLevel());

                                userInfo.setOrgan(currentUser.getOrgan());
                                userInfo.setDepartment(currentUser.getOrgan().getDepartment());
                                userInfo.setModifyPerson(currentUser);
                                userInfo.save();
			} else {
				//当前单位不存在该人员，新增系统人员
				userInfo.setDuty(secrecyWorkOrganRelationMember.getPerson().getDuty());
				userInfo.setPhone(secrecyWorkOrganRelationMember.getPerson().getPhone());
				userInfo.setName(secrecyWorkOrganRelationMember.getPerson().getName());

				userInfo.setBirthDate(secrecyWorkOrganRelationMember.getPerson().getBirthDate());
				userInfo.setSex(secrecyWorkOrganRelationMember.getPerson().getSex());
				userInfo.setLearningLevel(secrecyWorkOrganRelationMember.getPerson().getLearningLevel());
				userInfo.setPolity(secrecyWorkOrganRelationMember.getPerson().getPolity());
				userInfo.setMobile(secrecyWorkOrganRelationMember.getPerson().getMobile());

				userInfo.setSpecialtyCode(secrecyWorkOrganRelationMember.getPerson().getSpecialtyCode());
				userInfo.setTechnicTitleLevel(secrecyWorkOrganRelationMember.getPerson().getTechnicTitleLevel());
				userInfo.setAdministrativeLevel(secrecyWorkOrganRelationMember.getPerson().getAdministrativeLevel());

				userInfo.setOrgan(currentUser.getOrgan());
				userInfo.setDepartment(currentUser.getOrgan().getDepartment());
				userInfo.setCreatePerson(currentUser);
				userInfo.setModifyPerson(currentUser);
				userInfo.save();
			}
		} else {
			userInfo = this.get(secrecyWorkOrganRelationMember
				.getPerson().getUserInfoId(), UserInfo.class);
			userInfo.setDuty(secrecyWorkOrganRelationMember.getPerson().getDuty());
			userInfo.setPhone(secrecyWorkOrganRelationMember.getPerson().getPhone());

			userInfo.setBirthDate(secrecyWorkOrganRelationMember.getPerson().getBirthDate());
			userInfo.setSex(secrecyWorkOrganRelationMember.getPerson().getSex());
			userInfo.setLearningLevel(secrecyWorkOrganRelationMember.getPerson().getLearningLevel());
			userInfo.setPolity(secrecyWorkOrganRelationMember.getPerson().getPolity());
			userInfo.setMobile(secrecyWorkOrganRelationMember.getPerson().getMobile());

			userInfo.setSpecialtyCode(secrecyWorkOrganRelationMember.getPerson().getSpecialtyCode());
			userInfo.setTechnicTitleLevel(secrecyWorkOrganRelationMember.getPerson().getTechnicTitleLevel());
			userInfo.setAdministrativeLevel(secrecyWorkOrganRelationMember.getPerson().getAdministrativeLevel());

                        userInfo.setModifyPerson(currentUser);
                        userInfo.setModifyTime(new Date(System.currentTimeMillis()));
			userInfo.update();
		}
		userInfo.setDepartment(department);
		secrecyWorkOrganRelationMember.setPerson(userInfo);
		return secrecyWorkOrganRelationMember;
	}


}