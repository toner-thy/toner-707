package com.cdthgk.bmp.secrecynet.secrecytroops.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.cdthgk.bmp.secrecynet.secrecytroops.service.SecrecyTroopsService;
import com.cdthgk.bmp.secrecynet.secrecytroops.vo.SecrecyTroops;
import com.cdthgk.platform.base.service.GenericServiceTemplate;
import com.cdthgk.platform.organization.organ.domain.Organ;

public class SecrecyTroopsServiceImpl extends GenericServiceTemplate<SecrecyTroops, String> implements
	SecrecyTroopsService {

	@Override
	public SecrecyTroops getSecrecyTroopsByYear(Integer year, Organ organ) {
		String hql = "from SecrecyTroops s where s.year = :year and s.createOrgan.organId = :organId";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("year", year);
		params.put("organId", organ.getOrganId());
		return unique(hql, params);
	}


}
