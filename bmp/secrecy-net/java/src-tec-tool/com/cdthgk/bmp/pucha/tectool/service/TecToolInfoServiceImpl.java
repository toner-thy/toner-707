
package com.cdthgk.bmp.pucha.tectool.service;

import java.util.HashMap;
import java.util.Map;

import com.cdthgk.bmp.pucha.tectool.domain.TecToolInfo;
import com.cdthgk.platform.base.service.GenericServiceTemplate;
import com.cdthgk.standard.asserts.AssertStandardApp;

/**
 * <p>
 * TecToolInfoServiceImpl
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 *
 * @author 钟冀
 */
public class TecToolInfoServiceImpl extends GenericServiceTemplate<TecToolInfo, String>
	implements TecToolInfoService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TecToolInfo get(int year, String organId) {
		AssertStandardApp.isNotNull(year, "year不能为空");
		AssertStandardApp.isNotEmpty(organId, "organId不能为空");
		StringBuilder hql = new StringBuilder();
		hql.append("FROM ")
			.append(TecToolInfo.class.getSimpleName())
			.append(" WHERE year = :year and reportOrgan.organId = :organId");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("year", year);
		params.put("organId", organId);
		return unique(hql.toString(), params);
	}
}