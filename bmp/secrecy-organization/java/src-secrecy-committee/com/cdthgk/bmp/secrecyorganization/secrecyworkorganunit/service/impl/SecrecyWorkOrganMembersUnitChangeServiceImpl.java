/**
 *
 */
package com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.service.SecrecyWorkOrganMembersUnitChangeService;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.vo.PersonGroupRelationChange;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.vo.SecrecyWorkOrgan;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.vo.SecrecyWorkOrganMemberUnit;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.vo.SecrecyWorkOrganMembersUnitChange;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;

/**
 * <p>
 * 类的说明放这里  2013-7-20 下午3:30:03
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 *
 * @author 宋亚非
 */
public class SecrecyWorkOrganMembersUnitChangeServiceImpl extends
		BmpServiceImpl<SecrecyWorkOrganMembersUnitChange, String> implements
		SecrecyWorkOrganMembersUnitChangeService {

	/* (non-Javadoc)
	 * @see com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.service.SecrecyWorkOrganMembersUnitChangeService#getMemberUnitChangeList(ec.common.PageSortModel, java.lang.String)
	 */
	@Override
	public List<SecrecyWorkOrganMembersUnitChange> getMemberUnitChangeList(
			PageSortModel<SecrecyWorkOrganMembersUnitChange> psm,
			String memberUnitId) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("from SecrecyWorkOrganMembersUnitChange swomuc where 1=1 ");

		hql.append(" and swomuc.secrecyWorkOrganMembersId.secrecyWorkUnitMemberId = :unitMemberId");
		params.put("unitMemberId", memberUnitId);

		hql.append(" order by swomuc.changeDate desc");
		List<SecrecyWorkOrganMembersUnitChange> list = this.findList(hql.toString(), params, psm);
		return list;

	}

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
	public List<SecrecyWorkOrganMembersUnitChange> getHistoryPageList(PageSortModel<SecrecyWorkOrganMembersUnitChange> psm,
				SecrecyWorkOrgan secrecyWorkOrgan,Organ organ,Integer personChangeHistoryFlag){
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("from SecrecyWorkOrganMembersUnitChange swomuc where 1=1 ");
		if(organ!=null){
			hql.append(" and swomuc.secrecyWorkOrganMembersId.secrecyWorkOrgan.organ.organId = :organId ");
			params.put("organId", organ.getOrganId());
		}
		if (secrecyWorkOrgan != null) {
			if (!StringUtils.isEmpty(secrecyWorkOrgan.getSecrecyWorkOrganId())) {
				// 查询保密组织机构
				hql.append(" and swomuc.secrecyWorkOrganMembersId.secrecyWorkOrgan.secrecyWorkOrganId = :secrecyWorkOrganId");
				params.put("secrecyWorkOrganId", secrecyWorkOrgan.getSecrecyWorkOrganId());
			}
		}
		//过滤已变更人员信息
		if( personChangeHistoryFlag != null ){
			hql.append(" and ( swomuc.secrecyWorkOrganMembersId.secrecyStatus = :secrecyStatus ) ");
			params.put("secrecyStatus", personChangeHistoryFlag);
		}

		hql.append(" order by swomuc.secrecyWorkOrganMembersId.sort asc");
//		return this.getList(hql.toString(), psm, params);
		return this.findList(hql.toString(), params, psm);
	}


}
