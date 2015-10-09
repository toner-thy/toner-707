package com.cdthgk.bmp.secrecyorganization.secrecycommittee.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.bmp.secrecyorganization.secrecycommittee.service.PersonGroupService;
import com.cdthgk.bmp.secrecyorganization.secrecycommittee.vo.PersonGroup;
import com.cdthgk.platform.organization.organ.domain.Organ;



public class PersonGroupServiceImpl extends BmpServiceImpl<PersonGroup, String> implements PersonGroupService{

	/**
	 * 查找组
	 * @param organ  单位对象
	 * @return
	 */
	public List<PersonGroup> findGroup(Organ organ) {
		Map<String, Object> params = new HashMap<String, Object>();

		StringBuilder hql = new StringBuilder("FROM  PersonGroup pg where 1=1 ");
		hql.append("and pg.organ.organId = :organId ");
		params.put("organId", organ.getOrganId());

		return (List<PersonGroup>) findList(hql.toString(), params);
	}



}
