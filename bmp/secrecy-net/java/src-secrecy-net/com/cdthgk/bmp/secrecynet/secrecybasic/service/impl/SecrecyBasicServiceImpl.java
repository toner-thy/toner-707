package com.cdthgk.bmp.secrecynet.secrecybasic.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.secrecynet.secrecybasic.service.SecrecyBasicService;
import com.cdthgk.bmp.secrecynet.secrecybasic.vo.SecrecyBasic;
import com.cdthgk.platform.base.service.GenericServiceTemplate;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

public class SecrecyBasicServiceImpl extends GenericServiceTemplate<SecrecyBasic, String> implements
	SecrecyBasicService {

	@Override
	public SecrecyBasic getSecrecyBasicByYear(Integer year, Organ organ) {
		String hql = "from SecrecyBasic s where s.year = :year and s.createOrgan.organId = :organId";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("year", year);
		params.put("organId", organ.getOrganId());
		return unique(hql, params);
	}

	@Override
	public Object getListPage(PageSortModel<SecrecyBasic> psm,
			SecrecyBasic secrecyBasic, User currentUser) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("FROM SecrecyBasic s WHERE s.createOrgan.organId = :organId");
		params.put("organId", currentUser.getOrgan().getOrganId());
		// 按创建时间降序排列
		hql.append(" order by s.createTime desc");
		List<SecrecyBasic> list =(List<SecrecyBasic>) this.findList(hql.toString(), params, psm);
		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<SecrecyBasic> getSecrecyBasicList(Integer year, String organId) {
		String hql = "from SecrecyBasic s where s.year = :year and s.reportOrgan.organId = :organId";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("year", year);
		params.put("organId", organId);
		return getPersistProxy().getOrmPersistence().findList(hql.toString(), params);
	}


}
