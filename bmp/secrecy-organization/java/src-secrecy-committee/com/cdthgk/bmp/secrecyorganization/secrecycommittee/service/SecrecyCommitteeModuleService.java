package com.cdthgk.bmp.secrecyorganization.secrecycommittee.service;

import java.util.List;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.bmp.secrecyorganization.secrecycommittee.vo.SecrecyCommittee;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

/**
 * <p>
 * 保密委员会 Service 抽象类
 * </p>
 * <p>
 * 汪 钟 2012-12-14 10:43:03
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
public interface SecrecyCommitteeModuleService extends SecrecyCommitteeService, BmpServiceTemplate<SecrecyCommittee, String> {
	/**
	 * <p>
	 * 查询列表方法
	 * </p>
	 * <p>
	 * 汪 钟 2012-12-14 10:43:03
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
	public List<SecrecyCommittee> getPageList(PageSortModel psm, SecrecyCommittee secrecyCommittee);

	/**
	 * <p>
	 * 通过行政区划查询保密委
	 * </p>
	 * <p>
	 * 汪 钟 2012-12-14 17:54
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
	public SecrecyCommittee getByDistrict(District district);

	/**
	 * <p>
	 * 通过人员得到所属行政区划的保密委名称（系统判定的名称，可以修改）
	 * </p>
	 * <p>
	 * 汪 钟 2012-12-17 16:25
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
	public String getNameByUser(User user);

	/**
	 * <p>
	 * 创建新人员
	 * </p>
	 * <p>
	 * 汪 钟 2013-01-05 20:52
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
	public UserInfo saveNewUserInfo(String userInfoName, Organ org, User currentUser, String duty);

	/**
	 * <p>
	 * 创建新单位
	 * </p>
	 * <p>
	 * 汪 钟 2013-01-05 20:52
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
	public Organ saveNewOrgan(String orgName, District district, User currentUser);

	/**
	 * <p>
	 * 上报保存保密委员会远端执行
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-3-22 - 上午10:52:11
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param secrecyCommitteeList
	 * @param receiveOrganId
	 */
	public void saveRecSecrecyCommittee(
			List<SecrecyCommittee> secrecyCommitteeList, String receiveOrganId);

}