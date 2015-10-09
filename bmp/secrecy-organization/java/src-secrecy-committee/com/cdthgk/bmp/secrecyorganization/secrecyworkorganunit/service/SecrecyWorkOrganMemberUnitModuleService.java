package com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.service;

import java.util.List;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.vo.SecrecyWorkOrgan;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.vo.SecrecyWorkOrganMemberUnit;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

/**
 * <p>
 * 保密办成员  Service 抽象类(对外接口)
 * </p>
 * <p>
 * 刘椿成  2012-12-29 16:43:03
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
public interface SecrecyWorkOrganMemberUnitModuleService extends BmpServiceTemplate<SecrecyWorkOrganMemberUnit, String> {

	/**
	 *
	 * <p>
	 * 保密办成员列表
	 * </p>
	 * @param psm
	 * @param secrecyOffice
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
	public List<SecrecyWorkOrganMemberUnit> getPageList(SecrecyWorkOrgan secrecyWorkOrgan,Organ organ, Integer personChageFlagNow);

	/**
	 * <p>
	 * 保存系统数据 部门 人员
	 * </p>
	 * @param secrecyWorkOrganMemberUnit
	 * @param currentUser
	 * @param object
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
	public SecrecyWorkOrganMemberUnit saveSystemDataBySecrecyWorkOrgan(
			SecrecyWorkOrganMemberUnit secrecyWorkOrganMemberUnit,
			User currentUser, String deptFlag);
}