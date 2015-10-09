package com.cdthgk.bmp.secrecyorganization.secrecyoffice.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.bmp.secrecyorganization.secrecyoffice.action.SecrecyOfficeAction;
import com.cdthgk.bmp.secrecyorganization.secrecyoffice.service.SecrecyOfficeMemberModuleService;
import com.cdthgk.bmp.secrecyorganization.secrecyoffice.vo.SecrecyOffice;
import com.cdthgk.bmp.secrecyorganization.secrecyoffice.vo.SecrecyOfficeMember;
import com.cdthgk.bmp.secrecyperson.service.SecrecyPersonModuleService;
import com.cdthgk.code.enums.Nationality;
import com.cdthgk.common.lang.LangUtils;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.core.OrganizationContext;
import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;
import com.cdthgk.platform.permission.user.domain.User;
import com.cdthgk.standard.asserts.AssertStandardApp;

import ec.common.PageSortModel;

/**
 * <p>
 * 保密办（保密局）成员 Service 实现类
 * </p>
 * <p>
 * 陶汇源  2013-01-06 12:21:43
 * </p>
 * <blockquote>
 * <h4>历史修改记录</h4>
 * <ul>
 * <li>修改人 修改时间 修改描述
 * </ul>
 * </blockquote>
 * <p>
 * copyright cdthgk 2010-2011, all rights reserved.
 * </p>
 *
 * @author 陶汇源
 * @author cdthgk r&d
 * @since 1.0
 * @version 1.0
 */
public class SecrecyOfficeMemberServiceImpl extends BmpServiceImpl<SecrecyOfficeMember, String>
	implements SecrecyOfficeMemberModuleService {

	private SecrecyPersonModuleService secrecyPersonModuleService;

	// 构造器
	/** 默认构造器 */
	SecrecyOfficeMemberServiceImpl() {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<SecrecyOfficeMember> getSecrecyOfficeMemberPageList(PageSortModel<SecrecyOfficeMember> psm
			, SecrecyOffice secrecyOffice, Integer secrecyStatus, District district) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("from SecrecyOfficeMember som where 1=1");
		if(secrecyStatus.equals(SecrecyOfficeAction.SECRECY_STATUS_NOW)){
			//现有人员
			hql.append(" and (som.secrecyStatus = " + SecrecyOfficeAction.SECRECY_STATUS_NOW);
			hql.append(" or som.secrecyStatus = " + SecrecyOfficeAction.SECRECY_STATUS_CHANGE + ")");
		}
		if(secrecyStatus.equals(SecrecyOfficeAction.SECRECY_STATUS_CHANGE)){
			//密级变更人员
			hql.append(" and som.secrecyStatus = " + SecrecyOfficeAction.SECRECY_STATUS_CHANGE);
		}
		if(secrecyStatus.equals(SecrecyOfficeAction.SECRECY_STATUS_DECRYPTION)){
			//脱密人员
			hql.append(" and som.secrecyStatus = " + SecrecyOfficeAction.SECRECY_STATUS_DECRYPTION);
		}
		if (secrecyOffice != null) {
			if (!StringUtils.isEmpty(secrecyOffice.getSecrecyOfficeId())) {
				// 查询保密办（保密局）
				hql.append(" and som.secrecyOffice.secrecyOfficeId = :secrecyOfficeId");
				params.put("secrecyOfficeId", secrecyOffice.getSecrecyOfficeId());
			}
		}
		if(district != null && !"".equals(district.getDistrictCode())){
			// 行政区查询时所带参数
			hql.append(" and som.secrecyOffice.createOrgan.district.districtCode = :districtCode");
			params.put("districtCode", district.getDistrictCode());
		}
		hql.append(" order by som.sort asc ");
		return (List<SecrecyOfficeMember>) this.findList(hql.toString(), params, psm);
	}

	/**
	 * 获取所有保密委成员列表
	 * 宋亚非
	 * 2013-05-21
	 */
	public List<SecrecyOfficeMember> getAllList(SecrecyOffice secrecyOffice) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("from SecrecyOfficeMember som where 1=1 ");
		//现有人员
		hql.append(" and (som.secrecyStatus = " + SecrecyOfficeAction.SECRECY_STATUS_NOW);
		hql.append(" or som.secrecyStatus = " + SecrecyOfficeAction.SECRECY_STATUS_CHANGE + ")");
		if (secrecyOffice != null) {
			if (!StringUtils.isEmpty(secrecyOffice.getSecrecyOfficeId())) {
				// 查询保密办（保密局）
				hql.append(" and som.secrecyOffice.secrecyOfficeId = :secrecyOfficeId");
				params.put("secrecyOfficeId", secrecyOffice.getSecrecyOfficeId());
			}
		}
		hql.append(" order by som.sort asc ");
		return (List<SecrecyOfficeMember>) this.findList(hql.toString(), params);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SecrecyOfficeMember saveSystemDataBySecrecyOffice(SecrecyOfficeMember secrecyOfficeMember,
			 User currentUser, String deptFlag) {
		AssertStandardApp.isNotNull(secrecyOfficeMember.getPerson(), "输入的人员不能为空");
		AssertStandardApp.isNotNull(secrecyOfficeMember.getPerson().getDepartment(), "输入的部门不能为空");
		//当前单位下输入的部门如果有则保存，如果没有则通过输入的部门名称新增部门。
		Department department = new Department();
		if (LangUtils.isEmpty(secrecyOfficeMember.getPerson().getDepartment().getDepartmentId())) {
			//部门变更或者部门改名的标志：1：部门改名；2部门变更
			if(deptFlag != null && "1".equals(deptFlag)) {
				Department existDepartment  = secrecyPersonModuleService
						.get(secrecyOfficeMember.getOldDeptId(), Department.class);
				existDepartment.setDepartmentName(secrecyOfficeMember.getPerson().getDepartment()
						.getDepartmentName());
				department = existDepartment;
				department.update();
			} else {
				//变更时，如果存在该部门，则不理，把关系建立起。
				Department existDepartment = OrganizationContext.getInstance().getDepartmentService()
						.getByName(secrecyOfficeMember.getPerson().getDepartment()
						.getDepartmentName(), currentUser.getOrgan());
				if(existDepartment == null){
					//当前单位不存在该部门，新增系统部门
					department.setDepartmentName(secrecyOfficeMember.getPerson().getDepartment()
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
			department = secrecyPersonModuleService
						.get(secrecyOfficeMember.getPerson().getDepartment()
						.getDepartmentId(), Department.class);
		}
		//当前单位下输入的人员如果有则保存，如果没有则通过输入的名字新增人员。
		//姓 名
		UserInfo userInfo = new UserInfo();
		if (LangUtils.isEmpty(secrecyOfficeMember.getPerson().getUserInfoId())) {
			//FIXME 先检查是否存在该人员(注意这里返回的是集合需要修改)

			List<UserInfo> userInfoList = (List<UserInfo>) OrganizationContext.getInstance().getMemberService()
					.getByName(secrecyOfficeMember.getPerson().getName());
			if(userInfoList != null && userInfoList.size() > 0) {
				userInfo = userInfoList.get(0);
				userInfo.setDuty(secrecyOfficeMember.getPerson().getDuty());
			} else {
				//当前单位不存在该人员，新增系统人员
				userInfo.setName(secrecyOfficeMember.getPerson().getName());
				userInfo.setSex(secrecyOfficeMember.getPerson().getSex());
				userInfo.setNation(secrecyOfficeMember.getPerson().getNation());
				userInfo.setBirthDate(secrecyOfficeMember.getPerson().getBirthDate());
				userInfo.setLearningLevel(secrecyOfficeMember.getPerson().getLearningLevel());
				userInfo.setIdentityCard(secrecyOfficeMember.getPerson().getIdentityCard());
				userInfo.setDepartment(department);
				userInfo.setDuty(secrecyOfficeMember.getPerson().getDuty());
				userInfo.setMobile(secrecyOfficeMember.getPerson().getMobile());
				userInfo.setStaff(secrecyOfficeMember.getPerson().getStaff());
				userInfo.setAdministrativeLevel(secrecyOfficeMember.getPerson().getAdministrativeLevel());
				userInfo.setSpecialtyCode(secrecyOfficeMember.getPerson().getSpecialtyCode());
				userInfo.setPolity(secrecyOfficeMember.getPerson().getPolity());
				userInfo.setTechnicTitleLevel(secrecyOfficeMember.getPerson().getTechnicTitleLevel());
				userInfo.setPostType(secrecyOfficeMember.getPerson().getPostType());
				// 通用字段
				userInfo.setRemark(new Date().toString() + "产生。创建者：" + currentUser.getUserName() + "(UserID:" + currentUser.getUserId()
						+ ")；创建环境：在【保密工作机构】-【保密办（保密局）】-【新增保密办（保密局）成员】时填写人员时创建。");
				userInfo.setOrgan(currentUser.getOrgan());
				userInfo.setCreatePerson(currentUser);
				userInfo.setModifyPerson(currentUser);
				userInfo.setCreateTime(new Date());
				userInfo.setModifyTime(new Date());
				userInfo.setNationality(Nationality.CN);
				userInfo.save();
			}
		} else {
			userInfo = secrecyPersonModuleService.get(secrecyOfficeMember
						.getPerson().getUserInfoId(), UserInfo.class);
			userInfo.setName(secrecyOfficeMember.getPerson().getName());
			userInfo.setSex(secrecyOfficeMember.getPerson().getSex());
			userInfo.setNation(secrecyOfficeMember.getPerson().getNation());
			userInfo.setBirthDate(secrecyOfficeMember.getPerson().getBirthDate());
			userInfo.setLearningLevel(secrecyOfficeMember.getPerson().getLearningLevel());
			userInfo.setIdentityCard(secrecyOfficeMember.getPerson().getIdentityCard());
			userInfo.setDepartment(department);
			userInfo.setDuty(secrecyOfficeMember.getPerson().getDuty());
			userInfo.setMobile(secrecyOfficeMember.getPerson().getMobile());
			userInfo.setStaff(secrecyOfficeMember.getPerson().getStaff());
			userInfo.setAdministrativeLevel(secrecyOfficeMember.getPerson().getAdministrativeLevel());
			userInfo.setSpecialtyCode(secrecyOfficeMember.getPerson().getSpecialtyCode());
			userInfo.setPolity(secrecyOfficeMember.getPerson().getPolity());
			userInfo.setTechnicTitleLevel(secrecyOfficeMember.getPerson().getTechnicTitleLevel());
			userInfo.setPostType(secrecyOfficeMember.getPerson().getPostType());
			// 通用字段
			userInfo.setOrgan(currentUser.getOrgan());
			userInfo.setModifyPerson(currentUser);
			userInfo.setModifyTime(new Date());

			userInfo.update();
		}

		secrecyOfficeMember.setPerson(userInfo);
		return secrecyOfficeMember;
	}

	@Override
	public List<SecrecyOfficeMember> getSecrecyOfficeMemberList(SecrecyOffice secrecyOffice) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("from SecrecyOfficeMember som where 1=1 ");
		//现有人员
		hql.append(" and (som.secrecyStatus = " + SecrecyOfficeAction.SECRECY_STATUS_NOW);
		hql.append(" or som.secrecyStatus = " + SecrecyOfficeAction.SECRECY_STATUS_CHANGE + ")");
		if (secrecyOffice != null) {
			if (!StringUtils.isEmpty(secrecyOffice.getSecrecyOfficeId())) {
				// 查询保密办（保密局）
				hql.append(" and som.secrecyOffice.secrecyOfficeId = :secrecyOfficeId");
				params.put("secrecyOfficeId", secrecyOffice.getSecrecyOfficeId());
			}
		}
		return (List<SecrecyOfficeMember>) this.findList(hql.toString(), params);
	}

	// ******************** Setter & Getter ********************
	public void setSecrecyPersonModuleService(
			SecrecyPersonModuleService secrecyPersonModuleService) {
		this.secrecyPersonModuleService = secrecyPersonModuleService;
	}
	public SecrecyPersonModuleService getSecrecyPersonModuleService() {
		return secrecyPersonModuleService;
	}


}