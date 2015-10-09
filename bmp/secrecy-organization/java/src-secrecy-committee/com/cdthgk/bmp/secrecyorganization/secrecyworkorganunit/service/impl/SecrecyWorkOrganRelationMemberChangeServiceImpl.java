/**
 *
 */
package com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.bmp.secrecyorganization.secrecycommittee.vo.PersonGroupPosition;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.service.SecrecyWorkOrganRelationMemberChangeService;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.vo.PersonGroupRelationChange;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.vo.SecrecyWorkOrgan;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;

/**
 * <p>
 * 类的说明放这里 2013-7-15 上午11:51:24
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 *
 * @author 宋亚非
 */
public class SecrecyWorkOrganRelationMemberChangeServiceImpl extends
		BmpServiceImpl<PersonGroupRelationChange, String> implements
		SecrecyWorkOrganRelationMemberChangeService {

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
	@Override
	public List<PersonGroupRelationChange> getPersonGroupRelationChangeList(
			PageSortModel<PersonGroupRelationChange> psm,
			String relationMemberId) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("from PersonGroupRelationChange pgrc where 1=1 ");

		hql.append(" and pgrc.bmPersonGroupRelationId.secrecyWorkOrganRelationId = :relationMemberId");
		params.put("relationMemberId", relationMemberId);

		hql.append(" order by pgrc.changeDate desc");
		List<PersonGroupRelationChange> list = this.findList(hql.toString(), params, psm);
		return list;
	}


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
	@Override
	public List<PersonGroupRelationChange> getHistoryPageList(PageSortModel<PersonGroupRelationChange> psm,
			SecrecyWorkOrgan secrecyWorkOrgan, Organ oragn, Integer personChangeHistoryFlag) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("from PersonGroupRelationChange PRC where 1=1 ");
		if (secrecyWorkOrgan != null) {
			if (!StringUtils.isEmpty(secrecyWorkOrgan.getSecrecyWorkOrganId())) {
				// 查询保密工作机构成员
				hql.append(" and PRC.bmPersonGroupRelationId.secrecyWorkOrgan.secrecyWorkOrganId = :secrecyWorkOrganId");
				params.put("secrecyWorkOrganId", secrecyWorkOrgan.getSecrecyWorkOrganId());

				//已变更人员信息
				hql.append(" and PRC.bmPersonGroupRelationId.secrecyStatus = :secrecyStatus");
				params.put("secrecyStatus", personChangeHistoryFlag);
			}
		}
		hql.append(" order by PRC.bmPersonGroupRelationId.sort asc");
		List<PersonGroupRelationChange> list = this.findList(hql.toString(), params, psm) ;
		for( PersonGroupRelationChange sworm : list ){
			if( sworm.getBmPersonGroupRelationId().getPersonGroupPosition()!=null ){
				PersonGroupPosition pgp = this.get(sworm.getBmPersonGroupRelationId().getPersonGroupPosition().getPersonGroupPosition(), PersonGroupPosition.class);
				sworm.getBmPersonGroupRelationId().setPersonGroupPosition(pgp);
			}

		}
		 return list;
	}

}
