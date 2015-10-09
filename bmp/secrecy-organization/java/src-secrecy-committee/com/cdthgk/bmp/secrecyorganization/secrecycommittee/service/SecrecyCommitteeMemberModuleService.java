package com.cdthgk.bmp.secrecyorganization.secrecycommittee.service;

import java.util.List;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.bmp.secrecyorganization.secrecycommittee.vo.SecrecyCommittee;
import com.cdthgk.bmp.secrecyorganization.secrecycommittee.vo.SecrecyCommitteeMember;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;

/**
 * <p>
 * 保密委成员 Service 抽象类(对外)
 * </p>
 * <p>
 * 汪 钟 2012-12-18 13:42
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
public interface SecrecyCommitteeMemberModuleService extends SecrecyCommitteeMemberService, BmpServiceTemplate<SecrecyCommitteeMember, String> {

	/**
	 * <p>
	 * 分页
	 * </p>
	 * <p>
	 * 汪 钟 2012-12-18 19:33
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
	public List<SecrecyCommitteeMember> getPageList(PageSortModel<SecrecyCommitteeMember> psm, SecrecyCommittee secrecyCommittee, Integer personChangeNow);

	/**
	 *
	 * <p>
	 * 方法名：getPageHistoryList 获取保密委成员变更人员信息列表
	 * </p>
	 * <p>
	 * 宋亚非 2013-7-16 下午5:29:10
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
	 *@param secrecyCommittee
	 *@return
	 */
	public List<SecrecyCommitteeMember> getPageHistoryList(PageSortModel<SecrecyCommitteeMember> psm, SecrecyCommittee secrecyCommittee, Integer personChangeHistory);

	/**
	 *
	 * <p>
	 * 方法名：getPageList
	 * </p>
	 * <p>
	 * 宋亚非 2013-5-21 上午9:15:29
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@param secrecyCommittee
	 *@return
	 */
	public List<SecrecyCommitteeMember> getAllList(SecrecyCommittee secrecyCommittee, Integer secrecyStatusFlag);
	/**
	 *
	 * <p>
	 * 获取保密委成员
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-4-2 - 下午4:23:00
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param secrecyCommittee
	 * @return
	 */
	public List<SecrecyCommitteeMember> getSecrecyCommitteeMemberList(String secrecyCommitteeId);

	/**
	 * <p>
	 * 得到指定单位的保密工作机构领导
	 * </p>
	 * <p>
	 * 汪 钟 2012-12-18 16:18
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
	public SecrecyCommitteeMember getLeader(Organ organ);
}