/**
 *
 */
package com.cdthgk.bmp.secrecyorganization.secrecycommittee.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpService;
import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.bmp.secrecyorganization.secrecycommittee.service.SecrecyCommitteeMembersChangeService;
import com.cdthgk.bmp.secrecyorganization.secrecycommittee.vo.SecrecyCommitteeMembersChange;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.vo.PersonGroupRelationChange;

import ec.common.PageSortModel;

/**
 * <p>
 * 类的说明放这里  2013-7-16 下午3:26:50
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 *
 * @author 宋亚非
 */
public class SecrecyCommitteeMembersChangeServiceImpl extends BmpServiceImpl<SecrecyCommitteeMembersChange, String> implements SecrecyCommitteeMembersChangeService {

	/* (non-Javadoc)
	 * @see com.cdthgk.bmp.secrecyorganization.secrecycommittee.service.SecrecyCommitteeMembersChangeService#getSecrecyCommitteeMemberChangeList(ec.common.PageSortModel, java.lang.String)
	 */
	@Override
	public List<SecrecyCommitteeMembersChange> getSecrecyCommitteeMemberChangeList(
			PageSortModel<SecrecyCommitteeMembersChange> psm,
			String secrecyCommitteeMemberId) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("from SecrecyCommitteeMembersChange scmc where 1=1 ");

		hql.append(" and scmc.secrecyCommitteeMemberId.secrecyCommitteeMemberId = :secrecyCommitteeMemberId");
		params.put("secrecyCommitteeMemberId", secrecyCommitteeMemberId);

		hql.append(" order by scmc.changeDate desc");
		List<SecrecyCommitteeMembersChange> list = this.findList(hql.toString(), params, psm);
		return list;
	}

}
