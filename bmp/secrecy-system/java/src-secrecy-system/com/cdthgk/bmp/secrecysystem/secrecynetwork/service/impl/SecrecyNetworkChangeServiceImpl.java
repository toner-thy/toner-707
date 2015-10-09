package com.cdthgk.bmp.secrecysystem.secrecynetwork.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.secrecysystem.secrecynetwork.service.SecrecyNetworkChangeService;
import com.cdthgk.bmp.secrecysystem.secrecynetwork.vo.SecrecyNetworkChange;
import com.cdthgk.common.lang.StringUtils;
import com.cdthgk.platform.base.service.GenericServiceTemplate;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

public class SecrecyNetworkChangeServiceImpl extends GenericServiceTemplate<SecrecyNetworkChange, String> implements
	SecrecyNetworkChangeService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<SecrecyNetworkChange> getSecrecyNetworkChangePageList(
			PageSortModel<SecrecyNetworkChange> psm,
			SecrecyNetworkChange secrecyNetworkChange, User currentUser,
			boolean flag, District district, String checkLower) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("FROM SecrecyNetworkChange s WHERE 1 = 1");
		// false ： 查看本单位； true ：查看行政区
		if (flag) {
			// checkLower 是否查看下级 1：查看下级； 0：不查看
			if ("1".equals(checkLower)) {
				// 查看整个行政区
				hql.append(" and s.createPerson.organ.district.layer like :layer");
				params.put("layer", district.getLayer() + "%");
			} else {
				// 查看直辖机关单位
				hql.append(" and s.createPerson.organ.district.districtCode = :districtCode");
				params.put("districtCode", district.getDistrictCode());
			}
		} else {
			hql.append(" and s.createPerson.organ.organId = :organId");
			params.put("organId", currentUser.getOrgan().getOrganId());
		}
		if (secrecyNetworkChange != null) {
			if (secrecyNetworkChange.getSecrecyNetwork() != null && StringUtils.isNotEmpty(secrecyNetworkChange.getSecrecyNetwork().getName()) ) {
				hql.append(" and s.secrecyNetwork.name like :name");
				params.put("name", "%" + secrecyNetworkChange.getSecrecyNetwork().getName() + "%");
			}
			if (secrecyNetworkChange.getBeforeLevel() != null && !"".equals(secrecyNetworkChange.getBeforeLevel())) {
				hql.append(" and s.beforeLevel = :beforeLevel");
				params.put("beforeLevel", secrecyNetworkChange.getBeforeLevel());
			}
			if (secrecyNetworkChange.getAfterLevel() != null && !"".equals(secrecyNetworkChange.getAfterLevel())) {
				hql.append(" and s.afterLevel = :afterLevel");
				params.put("afterLevel", secrecyNetworkChange.getAfterLevel());
			}
		}
		hql.append(" order by s.createDate desc");
		return this.findList(hql.toString(), params, psm);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void saveSecrecyNetworkChange(
			SecrecyNetworkChange secrecyNetworkChange, User currentUser) {
		secrecyNetworkChange.setCreatePerson(currentUser);
		secrecyNetworkChange.setCreateDate(new Date());
		save(secrecyNetworkChange);
	}
}
