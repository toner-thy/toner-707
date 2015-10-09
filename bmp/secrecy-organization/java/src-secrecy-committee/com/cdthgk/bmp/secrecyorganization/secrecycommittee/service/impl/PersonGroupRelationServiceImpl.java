package com.cdthgk.bmp.secrecyorganization.secrecycommittee.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.action.BmpAction;
import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.bmp.secrecyorganization.secrecycommittee.service.PersonGroupRelationService;
import com.cdthgk.bmp.secrecyorganization.secrecycommittee.vo.PersonGroup;
import com.cdthgk.bmp.secrecyorganization.secrecycommittee.vo.PersonGroupRelation;

import ec.common.PageSortModel;

public class PersonGroupRelationServiceImpl extends BmpServiceImpl<PersonGroupRelation, String>
implements PersonGroupRelationService{

	public List<PersonGroupRelation> getForPage(PageSortModel psm,
			PersonGroup personGroup) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("FROM PersonGroupRelation p where 1=1 and (p.secrecyStatus is null or p.secrecyStatus=11) ");
		hql.append("and p.bmPersonGroup.personGroupId = :personGroupId ");
		params.put("personGroupId", personGroup.getPersonGroupId());

		return (List<PersonGroupRelation>) findList(hql.toString(), params, psm);
	}
}
