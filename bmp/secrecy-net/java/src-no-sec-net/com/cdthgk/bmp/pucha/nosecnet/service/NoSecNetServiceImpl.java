
package com.cdthgk.bmp.pucha.nosecnet.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.pucha.nosecnet.domain.NoSecNet;
import com.cdthgk.bmp.pucha.nosecnet.domain.NoSecNetIntranet;
import com.cdthgk.platform.base.service.GenericServiceTemplate;
import com.cdthgk.standard.asserts.AssertStandardApp;

/**
 * <p>
 * NoSecNetServiceImpl
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 *
 * @author 钟冀
 */
public class NoSecNetServiceImpl extends GenericServiceTemplate<NoSecNet, String>
	implements NoSecNetService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public NoSecNet get(int year, String organId) {
		AssertStandardApp.isNotNull(year, "year不能为空");
		AssertStandardApp.isNotEmpty(organId, "organId不能为空");
		StringBuilder hql = new StringBuilder();
		hql.append("FROM ")
			.append(NoSecNet.class.getSimpleName())
			.append(" WHERE year = :year and reportOrgan.organId = :organId");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("year", year);
		params.put("organId", organId);
		return unique(hql.toString(), params);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public NoSecNet update(NoSecNet noSecNet, List<NoSecNetIntranet> noSecNetIntranetList) {
		if( noSecNet.getNoSecNetIntranets()!=null && noSecNet.getNoSecNetIntranets().size()>0 ){
			for (NoSecNetIntranet noSecNetIntranet : noSecNet.getNoSecNetIntranets()) {
				getPersistProxy().getOrmPersistence().delete(noSecNetIntranet);
			}
		}
		if (noSecNetIntranetList != null) {
			for (NoSecNetIntranet noSecNetIntranet : noSecNetIntranetList) {
				getPersistProxy().getOrmPersistence().save(noSecNetIntranet);
			}
		}
		return super.update(noSecNet);
	}
}