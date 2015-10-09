package com.cdthgk.bmp.secrecysystem.secrecycomputer.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.secrecysystem.secrecycomputer.service.SecrecyComputerChangeService;
import com.cdthgk.bmp.secrecysystem.secrecycomputer.vo.SecrecyComputerChange;
import com.cdthgk.common.lang.StringUtils;
import com.cdthgk.platform.base.service.GenericServiceTemplate;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

public class SecrecyComputerChangeServiceImpl extends GenericServiceTemplate<SecrecyComputerChange, String> implements
	SecrecyComputerChangeService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<SecrecyComputerChange> getSecrecyComputerChangePageList(
			PageSortModel<SecrecyComputerChange> psm,
			SecrecyComputerChange secrecyComputerChange, User currentUser,
			boolean flag, District district, String checkLower) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("FROM SecrecyComputerChange s WHERE 1 = 1");
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

		if (secrecyComputerChange != null) {
			if (StringUtils.isNotEmpty(secrecyComputerChange.getSecrecyComputer().getDutyPerson().getName())) {
				hql.append(" and s.secrecyComputer.dutyPerson.name like :name");
				params.put("name", "%" + secrecyComputerChange.getSecrecyComputer().getDutyPerson().getName() + "%");
			}
			if (secrecyComputerChange.getBeforeLevel() != null && !"".equals(secrecyComputerChange.getBeforeLevel())) {
				hql.append(" and s.beforeLevel = :beforeLevel");
				params.put("beforeLevel", secrecyComputerChange.getBeforeLevel());
			}
			if (secrecyComputerChange.getAfterLevel() != null && !"".equals(secrecyComputerChange.getAfterLevel())) {
				hql.append(" and s.afterLevel = :afterLevel");
				params.put("afterLevel", secrecyComputerChange.getAfterLevel());
			}
		}
		hql.append(" order by s.createDate desc");
		return this.findList(hql.toString(), params, psm);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void saveSecrecyComputerChange(
			SecrecyComputerChange secrecyComputerChange, User currentUser) {
		secrecyComputerChange.setCreatePerson(currentUser);
		secrecyComputerChange.setCreateDate(new Date());
		save(secrecyComputerChange);
	}
}
