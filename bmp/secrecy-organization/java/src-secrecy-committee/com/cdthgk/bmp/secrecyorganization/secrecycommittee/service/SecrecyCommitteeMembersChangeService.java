/**
 *
 */
package com.cdthgk.bmp.secrecyorganization.secrecycommittee.service;

import java.util.List;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.bmp.secrecyorganization.secrecycommittee.vo.SecrecyCommitteeMembersChange;

import ec.common.PageSortModel;

/**
 * <p>
 * 类的说明放这里  2013-7-16 下午3:24:19
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 *
 * @author 宋亚非
 */
public interface SecrecyCommitteeMembersChangeService extends BmpServiceTemplate<SecrecyCommitteeMembersChange, String> {
	/**
	 *
	 * <p>
	 * 方法名：getSecrecyCommitteeMemberChangeList 获取保密委 某个人员的变更历史明细列表
	 * </p>
	 * <p>
	 * 宋亚非 2013-7-16 下午5:44:14
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
	 *@param secrecyCommitteeMemberId
	 *@return
	 */
	public List<SecrecyCommitteeMembersChange> getSecrecyCommitteeMemberChangeList(PageSortModel<SecrecyCommitteeMembersChange> psm, String secrecyCommitteeMemberId);

}
