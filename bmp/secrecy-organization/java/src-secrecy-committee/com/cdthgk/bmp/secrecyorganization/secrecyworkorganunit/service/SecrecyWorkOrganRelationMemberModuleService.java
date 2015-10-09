package com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.service;

import java.util.List;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.vo.PersonGroupRelationChange;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.vo.SecrecyWorkOrgan;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.vo.SecrecyWorkOrganRelationMember;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

/**
 * <p>
 * 保密工作机构成员  Service 抽象类(对外接口)
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
 * @author liucc
 * @since 1.0
 * @version 1.0
 */
public interface SecrecyWorkOrganRelationMemberModuleService extends BmpServiceTemplate<SecrecyWorkOrganRelationMember, String> {

	/**
	 * <p>
	 * 保密工作机构成员 列表
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
	 * @author liucc
	 * @since 1.0
	 * @version 1.0
	 */
	public List<SecrecyWorkOrganRelationMember> getPageList(SecrecyWorkOrgan secrecyWorkOrgan,Organ oragn, Integer personChangeNowFlag);


	/**
	 * <p>
	 * 保存系统数据部门，人员
	 * </p>
	 * @param secrecyWorkOrganMemberUnit
	 * @param currentUser
	 * @param object
	 * @return
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
	 * @author liucc
	 * @since 1.0
	 * @version 1.0
	 */
	public SecrecyWorkOrganRelationMember saveSystemDataBySecrecyWorkOrgan(
			SecrecyWorkOrganRelationMember secrecyWorkOrganRelation,
			User currentUser, String deptFlag);
}