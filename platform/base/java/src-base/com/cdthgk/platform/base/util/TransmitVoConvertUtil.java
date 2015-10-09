
package com.cdthgk.platform.base.util;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdthgk.common.bean.BeanUtils;
import com.cdthgk.common.bean.rule.CopyRuleEnum;
import com.cdthgk.common.lang.LogUtils;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.industry.domain.Industry;
import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;
import com.cdthgk.platform.permission.user.domain.User;
import com.cdthgk.standard.exception.StandardAppException;

/**
 * <p>
 * 类的说明放这里
 * </p>
 * <p>
 * 创建时间 2013-3-21 - 下午1:48:59
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
public class TransmitVoConvertUtil {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(TransmitVoConvertUtil.class);

	public static Department accessionDeptProperty(Department secrecyPersonDepartment){
		Department dept = new Department();
		dept.setDepartmentId(secrecyPersonDepartment.getDepartmentId());
		dept.setCreateTime(secrecyPersonDepartment.getCreateTime());
		dept.setModifyTime(secrecyPersonDepartment.getModifyTime());
		dept.setDepartmentCode(secrecyPersonDepartment.getDepartmentCode());
		dept.setDepartmentName(secrecyPersonDepartment.getDepartmentName());
		dept.setDepartmentShortName(secrecyPersonDepartment.getDepartmentShortName());
		dept.setDescription(secrecyPersonDepartment.getDescription());
		dept.setLayer(secrecyPersonDepartment.getLayer());
		dept.setSort(secrecyPersonDepartment.getSort());
		dept.setStatus(secrecyPersonDepartment.getStatus());
		dept.setSysRegisterTime(secrecyPersonDepartment.getSysRegisterTime());
		if(secrecyPersonDepartment.getCreatePerson() != null){
			User deptCreatePerson = new User();
			deptCreatePerson.setUserId(secrecyPersonDepartment.getCreatePerson().getUserId());
			dept.setCreatePerson(deptCreatePerson);
		}
		if(secrecyPersonDepartment.getModifyPerson() != null){
			User deptModifyPerson = new User();
			deptModifyPerson.setUserId(secrecyPersonDepartment.getModifyPerson().getUserId());
			dept.setModifyPerson(deptModifyPerson);
		}
		if(secrecyPersonDepartment.getOrgan() != null){
			Organ deptOrgan = new Organ();
			deptOrgan.setOrganId(secrecyPersonDepartment.getOrgan().getOrganId());
			dept.setOrgan(deptOrgan);
		}
		if(secrecyPersonDepartment.getParent() != null){
			Department deptParent = new Department();
			deptParent.setDepartmentId(secrecyPersonDepartment.getParent().getDepartmentId());
			dept.setParent(deptParent);
		}
		return dept;
	}

	public static UserInfo accessionUserInfo(UserInfo userInfo){
		UserInfo uiNew = new UserInfo();
		userInfo.setJoinOrganDate(new Date());
		try {
			BeanUtils.copyProperties(uiNew, userInfo, CopyRuleEnum.ignoreCaseEmpty);
		} catch (Exception e) {
			LogUtils.error(e, LOGGER);
			throw new StandardAppException("属性转换异常：" + e.getMessage());
		}
		Department department = new Department();
		department.setDepartmentId(userInfo.getDepartment().getDepartmentId());
		uiNew.setDepartment(department);
		Organ oragn = new Organ();
		oragn.setOrganId(userInfo.getOrgan().getOrganId());
		uiNew.setOrgan(oragn);
		uiNew.setPhoto(null);
		uiNew.setPost(null);
		User newUser = new User();
		if (userInfo.getUser() != null) {
			newUser.setUserId(userInfo.getUser().getUserId());
		}
		uiNew.setUser(newUser);
		User newCreateUser = new User();
		if (userInfo.getCreatePerson() != null) {
			newCreateUser.setUserId(userInfo.getCreatePerson().getUserId());
		}
		uiNew.setCreatePerson(newCreateUser);
		User newModifyUser = new User();
		if (userInfo.getModifyPerson() != null) {
			newModifyUser.setUserId(userInfo.getModifyPerson().getUserId());
		}
		uiNew.setModifyPerson(newModifyUser);
		return uiNew;
	}

	public static User accessionUser(User user){
		User userNew = new User();
		userNew.setCreateTime(user.getCreateTime());
		userNew.setDataState(user.getDataState());
		userNew.setLoginTime(user.getLoginTime());
		userNew.setModifyTime(user.getModifyTime());
		userNew.setPassword(user.getPassword());
		userNew.setStatus(user.getStatus());
		userNew.setUserId(user.getUserId());
		userNew.setUserName(user.getUserName());
		Organ organ = new Organ();
		organ.setOrganId(user.getOrgan().getOrganId());
		userNew.setOrgan(organ);
		return userNew;
	}

	public static Organ accessionOrgan(Organ organ){
		Organ organNew = new Organ();
		organNew.setContactPhone(organ.getContactPhone());
		organNew.setCreateTime(organ.getCreateTime());
		organNew.setDescription(organ.getDescription());
		organNew.setLayer(organ.getLayer());
		organNew.setLogoutStatus(organ.getLogoutStatus());
		organNew.setModifyTime(organ.getModifyTime());
		organNew.setOrganAddress(organ.getOrganAddress());
		organNew.setOrganCategory(organ.getOrganCategory());
		organNew.setOrganCode(organ.getOrganCode());
		organNew.setOrganId(organ.getOrganId());
		organNew.setOrganName(organ.getOrganName());
		organNew.setOrganShortName(organ.getOrganShortName());
		organNew.setOrganType(organ.getOrganType());
		organNew.setPhoneAreaCode(organNew.getPhoneAreaCode());
		organNew.setSort(organ.getSort());
		organNew.setStatus(organ.getStatus());
		Organ parent = new Organ();
		parent.setOrganId(organ.getParent().getOrganId());
		organNew.setParent(parent);
		District d = new District();
		d.setDistrictCode(organ.getDistrict().getDistrictCode());

		organNew.setDistrict(d);
		if(organ.getAddressDistrict() != null){
			District addressDistrict = new District();
			addressDistrict.setDistrictCode(organ.getAddressDistrict().getDistrictCode());
			organNew.setAddressDistrict(addressDistrict);
		}
		if(organ.getIndustry() != null){
			Industry industry = new Industry();
			industry.setIndustryCode(organ.getIndustry().getIndustryCode());
			organNew.setIndustry(industry);
		}
		organNew.setCreatePerson(accessionUser(organ.getCreatePerson()));
		organNew.setModifyPerson(accessionUser(organ.getModifyPerson()));
		return organNew;
	}

	public static Department getAllDepartmentByOrgan(Organ organ){
		//根部门
		Department parentDepartment = organ.getDepartment();
		Department transmitParentDepartment = TransmitVoConvertUtil.accessionDeptProperty(parentDepartment);
		for (Department dept : parentDepartment.getChildren()) {
			Department transmitDepartment = TransmitVoConvertUtil.accessionDeptProperty(dept);
			// 将所有子部门设置到根部门下
			transmitParentDepartment.getChildren().add(transmitDepartment);
		}
		return transmitParentDepartment;
	}
}
