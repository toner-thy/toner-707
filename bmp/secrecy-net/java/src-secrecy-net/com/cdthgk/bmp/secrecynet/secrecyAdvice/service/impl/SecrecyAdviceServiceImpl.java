
package com.cdthgk.bmp.secrecynet.secrecyAdvice.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.bmp.secrecynet.secrecyAdvice.service.SecrecyAdviceService;
import com.cdthgk.bmp.secrecynet.secrecyAdvice.vo.SecrecyAdvice;
import com.cdthgk.platform.organization.organ.domain.Organ;

/**
 * <p>
 * 类的说明放这里
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 *
 * @author 钟冀
 */
public class SecrecyAdviceServiceImpl extends BmpServiceImpl<SecrecyAdvice, String>
		implements SecrecyAdviceService{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SecrecyAdvice getSecrecyAdviceByYear(int year, Organ organ) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("from SecrecyAdvice s where 1=1 ");
		hql.append(" and s.year = :year");
		params.put("year", year);
		hql.append(" and s.organ.organId = :organId");
		params.put("organId", organ.getOrganId());
		SecrecyAdvice secrecyAdvice = getPersistProxy().getOrmPersistence().find(hql.toString(), params);
		return secrecyAdvice;
	}

}
