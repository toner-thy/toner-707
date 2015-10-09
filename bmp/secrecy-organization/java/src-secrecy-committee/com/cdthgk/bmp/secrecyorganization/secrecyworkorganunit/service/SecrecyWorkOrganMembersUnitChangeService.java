/**
 *
 */
package com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.service;

import java.util.List;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.vo.PersonGroupRelationChange;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.vo.SecrecyWorkOrgan;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.vo.SecrecyWorkOrganMemberUnit;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.vo.SecrecyWorkOrganMembersUnitChange;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;

/**
 * <p>
 * 类的说明放这里  2013-7-20 下午3:26:00
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 *
 * @author 宋亚非
 */
public interface SecrecyWorkOrganMembersUnitChangeService extends
		BmpServiceTemplate<SecrecyWorkOrganMembersUnitChange, String> {
	public List<SecrecyWorkOrganMembersUnitChange> getMemberUnitChangeList(PageSortModel<SecrecyWorkOrganMembersUnitChange> psm, String memberUnitId);


	/**
	 *
	 * <p>
	 * 方法名：getHistoryPageList  获得保密组织机构  办公室成员变动历史数据
	 * </p>
	 * <p>
	 * 宋亚非 2013-7-20 下午3:38:15
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
	 *@param secrecyWorkOrgan
	 *@param organ
	 *@param personChangeHistoryFlag
	 *@return
	 */
	public List<SecrecyWorkOrganMembersUnitChange> getHistoryPageList(PageSortModel<SecrecyWorkOrganMembersUnitChange> psm,SecrecyWorkOrgan secrecyWorkOrgan,Organ organ,Integer personChangeHistoryFlag);


}
