package com.cdthgk.bmp.secrecysystem.secrecynetwork.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.secrecysystem.secrecynetwork.service.SecrecyNetworkClearService;
import com.cdthgk.bmp.secrecysystem.secrecynetwork.vo.SecrecyNetworkClear;
import com.cdthgk.platform.base.service.GenericServiceTemplate;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

public class SecrecyNetworkClearServiceImpl extends GenericServiceTemplate<SecrecyNetworkClear, String> implements
	SecrecyNetworkClearService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<SecrecyNetworkClear> getSecrecyNetworkClearPageList(
			PageSortModel<SecrecyNetworkClear> psm,
			SecrecyNetworkClear secrecyNetworkClear, User currentUser,
			boolean flag, District district, String checkLower) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("FROM SecrecyNetworkClear s WHERE 1 = 1");
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
		if (secrecyNetworkClear != null) {
			if (secrecyNetworkClear.getSecrecyNetwork().getNetworkType() != null
					&& !"".equals(secrecyNetworkClear.getSecrecyNetwork().getNetworkType())) {
				hql.append(" and s.secrecyNetwork.NetworkType = :NetworkType");
				params.put("NetworkType", secrecyNetworkClear.getSecrecyNetwork().getNetworkType());
			}
			if (secrecyNetworkClear.getSecrecyNetwork().getSecrecyLevel() != null
					&& !"".equals(secrecyNetworkClear.getSecrecyNetwork().getSecrecyLevel())) {
				hql.append(" and s.secrecyNetwork.secrecyLevel = :secrecyLevel");
				params.put("secrecyLevel", secrecyNetworkClear.getSecrecyNetwork().getSecrecyLevel());
			}
			if (secrecyNetworkClear.getClearType() != null
					&& !"".equals(secrecyNetworkClear.getClearType())) {
				hql.append(" and s.clearType = :clearType");
				params.put("clearType", secrecyNetworkClear.getClearType());
			}
		}
		hql.append(" order by s.createDate desc");
		return this.findList(hql.toString(), params, psm);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public void saveSecrecyNetworkClear(
			SecrecyNetworkClear secrecyNetworkClear, User currentUser) {
		secrecyNetworkClear.setCreatePerson(currentUser);
		secrecyNetworkClear.setCreateDate(new Date());
		save(secrecyNetworkClear);
	}


}
