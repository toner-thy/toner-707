package com.cdthgk.bmp.secrecyorganization.secrecyoffice.service;

import java.util.List;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.bmp.secrecyorganization.secrecyoffice.vo.SecrecyOffice;
import com.cdthgk.bmp.secrecyorganization.secrecyoffice.vo.SecrecyOfficeMember;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

/**
 * <p>
 * 保密办（保密局）成员 Service 抽象类(对外)
 * </p>
 * <p>
 * 陶汇源 2012-12-18 13:42
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
 * @author FastCodeingTools
 * @author tianyu
 * @since 1.0
 * @version 1.0
 */
public interface SecrecyOfficeMemberModuleService extends SecrecyOfficeMemberService
	, BmpServiceTemplate<SecrecyOfficeMember, String> {

	/**
	 * <p>
	 * 分页获取保密办（保密局）成员
	 * </p>
	 * <p>
	 * 陶汇源 创建时间 2012-12-26 14:49:09
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
	 * @param secrecyStatus
	 */
	List<SecrecyOfficeMember> getSecrecyOfficeMemberPageList(PageSortModel<SecrecyOfficeMember> psm, SecrecyOffice secrecyOffice
			, Integer secrecyStatus, District district);

	/**
	 *
	 * <p>
	 * 方法名：getAllList 获取所有记录
	 * </p>
	 * <p>
	 * 宋亚非 2013-5-21 上午9:49:32
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@param psm
	 *@param secrecyOffice
	 *@return
	 */
	List<SecrecyOfficeMember> getAllList(SecrecyOffice secrecyOffice);

	/**
	 * <p>
	 * 根据当前办公室保存系统数据人员
	 * </p>
	 * <p>
	 * 陶汇源  2012-12-26 14:49:40
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
	SecrecyOfficeMember saveSystemDataBySecrecyOffice(
			SecrecyOfficeMember secrecyOfficeMember, User currentUser, String deptFlag);

	/**
	 *
	 * <p>
	 * 查询保密办下的成员
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-4-7 - 下午1:06:04
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param secrecyOffice
	 * @return
	 */
	public List<SecrecyOfficeMember> getSecrecyOfficeMemberList(SecrecyOffice secrecyOffice);
}