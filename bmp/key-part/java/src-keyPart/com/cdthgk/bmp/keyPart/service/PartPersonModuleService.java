package com.cdthgk.bmp.keyPart.service;

import java.util.List;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.bmp.keyPart.vo.Part;
import com.cdthgk.bmp.keyPart.vo.PartPerson;
import com.cdthgk.bmp.secrecyperson.vo.SecrecyPerson;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

/**
 * <p>
 * PartPersonModuleService.java 业务模块内需要用到的方法，不需要对外使用
 * </p>
 * <p>
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
public interface PartPersonModuleService extends BmpServiceTemplate<PartPerson, String>{

	/**
	 * @param organ 单位参数
	 * @param part 对象参数
	 * @return list
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
	public List<PartPerson> getPersonListPage(PageSortModel psm, Part part,Organ organ, Integer nowFlag);

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
	 * @param userInfo 人员信息对象
	 * @param user 人员
	 * @param department 部门
	 * @return userInfo
	 */
	UserInfo saveUserInfo(UserInfo userInfo, User user, Department department);

	/**
	 * <p>
	 * 保存涉密人员
	 * </p>
	 * @param name
	 * @param secrecyPerson
	 * @return
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
	SecrecyPerson saveSecrecyPerson(String name, SecrecyPerson secrecyPerson, User currentUser);

	/**
	 * <p>
	 * 保存要害部门
	 * </p>
	 * @param departmentName
	 * @return
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
	Department saveDepartment(String departmentName);

	/**
	 * createTime 2013.01.16
	 * @author wangpb
	 * @param organ
	 * @return
	 */
	public List<Long> countSectionData(Organ organ);

	/**按照行政区划
	 * 统计 保密要害部位
	 * @param district  行政区划对象
	 * @return
	 */
	public List<Long> countPartData(District district);
}