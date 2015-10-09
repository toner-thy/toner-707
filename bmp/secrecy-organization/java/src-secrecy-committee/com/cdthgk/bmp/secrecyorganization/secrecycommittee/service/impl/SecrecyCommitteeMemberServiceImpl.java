package com.cdthgk.bmp.secrecyorganization.secrecycommittee.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.bmp.secrecyorganization.secrecycommittee.service.SecrecyCommitteeMemberModuleService;
import com.cdthgk.bmp.secrecyorganization.secrecycommittee.vo.SecrecyCommittee;
import com.cdthgk.bmp.secrecyorganization.secrecycommittee.vo.SecrecyCommitteeMember;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;

/**
 * <p>
 * 保密委成员 Service 实现类
 * </p>
 * <p>
 * 汪 钟 2012-12-18 13:44
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
public class SecrecyCommitteeMemberServiceImpl extends BmpServiceImpl<SecrecyCommitteeMember, String> implements SecrecyCommitteeMemberModuleService {
	// 构造器
	/** 默认构造器 */
	SecrecyCommitteeMemberServiceImpl() {
	}

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
	public List<SecrecyCommitteeMember> getPageList(PageSortModel<SecrecyCommitteeMember> psm, SecrecyCommittee secrecyCommittee, Integer personChangeHistoryNow){
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("from SecrecyCommitteeMember scm where 1=1 ");

		// 查询保密委
		hql.append(" and scm.secrecyCommittee.secrecyCommitteeId = :secrecyCommitteeId");
		params.put("secrecyCommitteeId", secrecyCommittee.getSecrecyCommitteeId());

		//过滤已变更人员信息
		hql.append(" and ( scm.secrecyStatus = :personChangeNowFlag or scm.secrecyStatus is NULL ) ");
		params.put("personChangeNowFlag", personChangeHistoryNow);

		// 按保密委员会职务升序排列
		hql.append(" order by scm.sort asc");

//		return (List<SecrecyCommitteeMember>) this.getList(hql.toString(), psm, params);
		return (List<SecrecyCommitteeMember>) this.findList(hql.toString(), params, psm);
	}

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

	public List<SecrecyCommitteeMember> getPageHistoryList(PageSortModel<SecrecyCommitteeMember> psm, SecrecyCommittee secrecyCommittee, Integer personChangeHitory){
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("from SecrecyCommitteeMember scm where 1=1 ");

		// 查询保密委
		hql.append(" and scm.secrecyCommittee.secrecyCommitteeId = :secrecyCommitteeId");
		params.put("secrecyCommitteeId", secrecyCommittee.getSecrecyCommitteeId());

		//已变更人员信息
		hql.append(" and scm.secrecyStatus = :personChangeFlag ");
		params.put("personChangeFlag", personChangeHitory);

		// 按保密委员会职务升序排列
		hql.append(" order by scm.sort asc");

//		return (List<SecrecyCommitteeMember>) this.getList(hql.toString(), psm, params);
		return (List<SecrecyCommitteeMember>) this.findList(hql.toString(), params, psm);
	}

	/**
	 *
	 * <p>
	 * 方法名：getList
	 * </p>
	 * <p>
	 * 宋亚非 2013-5-21 上午9:14:07
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
	public List<SecrecyCommitteeMember> getAllList(SecrecyCommittee secrecyCommittee, Integer secrecyStatusFlag){
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("from SecrecyCommitteeMember scm where 1=1 ");

		// 查询保密委
		hql.append(" and scm.secrecyCommittee.secrecyCommitteeId = :secrecyCommitteeId");
		params.put("secrecyCommitteeId", secrecyCommittee.getSecrecyCommitteeId());

		hql.append(" and (scm.secrecyStatus = :secrecyStatusFlag or scm.secrecyStatus is null)");
		params.put("secrecyStatusFlag", secrecyStatusFlag);

		// 按保密委员会职务升序排列
		hql.append(" order by scm.sort asc");

//		return (List<SecrecyCommitteeMember>) this.getList(hql.toString(), params);
		return (List<SecrecyCommitteeMember>) this.findList(hql.toString(), params);
	}


	public List<SecrecyCommitteeMember> getSecrecyCommitteeMemberList(String secrecyCommitteeId){
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("from SecrecyCommitteeMember scm where scm.secrecyCommittee.secrecyCommitteeId = :secrecyCommitteeId");
		params.put("secrecyCommitteeId", secrecyCommitteeId);
		return (List<SecrecyCommitteeMember>) this.findList(hql.toString(), params);
	}

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
	public SecrecyCommitteeMember getLeader(Organ organ){
//		List<SecrecyCommitteeMember> leaderList = getList("from SecrecyCommitteeMember scm where 1=1 and scm.person.organ.organId='" + organ.getOrganId() + "' and (scm.personGroupPosition.personGroupPosition='1' or scm.personGroupPosition.personGroupPosition='4')", null);
		List<SecrecyCommitteeMember> leaderList = findList("from SecrecyCommitteeMember scm where 1=1 and scm.person.organ.organId='" + organ.getOrganId() + "' and (scm.personGroupPosition.personGroupPosition='1' or scm.personGroupPosition.personGroupPosition='4')", null);
		if(leaderList!=null && leaderList.size()!=0){
			return leaderList.get(0);
		}else{
			return null;
		}
	}
}