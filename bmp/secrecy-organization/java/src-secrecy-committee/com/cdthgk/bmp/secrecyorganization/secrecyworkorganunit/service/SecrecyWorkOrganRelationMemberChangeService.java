/**
 *
 */
package com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.service;

import java.util.List;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.vo.PersonGroupRelationChange;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.vo.SecrecyWorkOrgan;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;

/**
 * <p>
 * 类的说明放这里  2013-7-15 上午11:50:29
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 *
 * @author 宋亚非
 */
public interface SecrecyWorkOrganRelationMemberChangeService extends BmpServiceTemplate<PersonGroupRelationChange, String>{
	/**
	 *
	 * <p>
	 * 方法名：getPersonGroupRelationChangeList 获取某人历史变更记录列表
	 * </p>
	 * <p>
	 * 宋亚非 2013-7-15 下午5:27:55
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
	 *@param relationMemberId
	 *@return
	 */
	public List<PersonGroupRelationChange> getPersonGroupRelationChangeList(PageSortModel<PersonGroupRelationChange> psm, String relationMemberId);


	/**
	 *
	 * <p>
	 * 方法名：getHistoryPageList 保密工作机构成员变更人员信息列表
	 * </p>
	 * <p>
	 * 宋亚非 2013-7-15 下午2:39:51
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@param secrecyWorkOrgan
	 *@param oragn
	 *@return
	 */
	public List<PersonGroupRelationChange> getHistoryPageList(PageSortModel<PersonGroupRelationChange> psm ,SecrecyWorkOrgan secrecyWorkOrgan,Organ oragn, Integer personChangeHistoryFlag);

}
