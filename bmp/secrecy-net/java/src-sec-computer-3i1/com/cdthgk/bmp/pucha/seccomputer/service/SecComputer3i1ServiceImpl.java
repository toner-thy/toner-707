
package com.cdthgk.bmp.pucha.seccomputer.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.pucha.seccomputer.domain.SecComputer3i1;
import com.cdthgk.common.lang.LangUtils;
import com.cdthgk.platform.base.service.GenericServiceTemplate;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.standard.asserts.AssertStandardApp;
import com.cdthgk.standard.exception.StandardAppException;

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
public class SecComputer3i1ServiceImpl extends GenericServiceTemplate<SecComputer3i1, String>
	implements SecComputer3i1Service {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<SecComputer3i1> getList(int year, String organId) {
		AssertStandardApp.isNotNull(year, "year不能为空");
		AssertStandardApp.isNotEmpty(organId, "organId不能为空");
		StringBuilder hql = new StringBuilder();
		hql.append("FROM ")
			.append(SecComputer3i1.class.getSimpleName())
			.append(" WHERE year = :year and reportOrgan.organId = :organId order by sort");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("year", year);
		params.put("organId", organId);
		return findList(hql.toString(), params);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void save(List<SecComputer3i1> secComputer3i1List, Integer year, String organId) {
		AssertStandardApp.isNotNull(year, "年份不能为空");
		AssertStandardApp.isNotNull(organId, "填报单位不能为空");
		// 忽略空
		if (secComputer3i1List == null) {
			secComputer3i1List = new ArrayList<SecComputer3i1>();
		}
		Iterator<SecComputer3i1> it = secComputer3i1List.iterator();
		while(it.hasNext()) {
			SecComputer3i1 secComputer3i1 = it.next();
			// 忽略空
			if (secComputer3i1 == null) {
				continue;
			}
			if (secComputer3i1.getYear() == null) {
				secComputer3i1.setYear(year);
			}
			if (secComputer3i1.getReportOrgan() == null) {
				secComputer3i1.setReportOrgan(new Organ());
			}
			if (LangUtils.isEmpty(secComputer3i1.getReportOrgan().getId())) {
				secComputer3i1.getReportOrgan().setOrganId(organId);
			}
			// 判断年份
			if (!year.equals(secComputer3i1.getYear())) {
				throw new StandardAppException("保存对象集合中的年份不相同");
			}
			// 判断单位
			if (!organId.equals(secComputer3i1.getReportOrgan().getOrganId())) {
				throw new StandardAppException("保存对象集合中的单位不相同");
			}
		}
		deleteBatch(getList(year, organId));
		saveBatch(secComputer3i1List);
	}
}
