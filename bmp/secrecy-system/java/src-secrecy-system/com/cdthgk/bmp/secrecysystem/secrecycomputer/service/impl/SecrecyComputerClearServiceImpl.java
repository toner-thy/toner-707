package com.cdthgk.bmp.secrecysystem.secrecycomputer.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.secrecysystem.secrecycomputer.service.SecrecyComputerClearService;
import com.cdthgk.bmp.secrecysystem.secrecycomputer.vo.SecrecyComputerClear;
import com.cdthgk.common.lang.StringUtils;
import com.cdthgk.platform.base.service.GenericServiceTemplate;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

public class SecrecyComputerClearServiceImpl extends GenericServiceTemplate<SecrecyComputerClear, String> implements
	SecrecyComputerClearService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<SecrecyComputerClear> getSecrecyComputerClearPageList(
			PageSortModel<SecrecyComputerClear> psm,
			SecrecyComputerClear secrecyComputerClear, User currentUser,
			boolean flag, District district, String checkLower) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("FROM SecrecyComputerClear s WHERE 1 = 1");
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

		if (secrecyComputerClear != null) {
			if (StringUtils.isNotEmpty(secrecyComputerClear.getSecrecyComputer().getDutyPerson().getName())) {
				hql.append(" and s.secrecyComputer.dutyPerson.name like :name");
				params.put("name", "%" + secrecyComputerClear.getSecrecyComputer().getDutyPerson().getName() + "%");
			}
			if (secrecyComputerClear.getSecrecyComputer().getComputerType() != null
					&& !"".equals(secrecyComputerClear.getSecrecyComputer().getComputerType())) {
				hql.append(" and s.secrecyComputer.computerType = :computerType");
				params.put("computerType", secrecyComputerClear.getSecrecyComputer().getComputerType());
			}
			if (secrecyComputerClear.getSecrecyComputer().getSecrecyLevel() != null
					&& !"".equals(secrecyComputerClear.getSecrecyComputer().getSecrecyLevel())) {
				hql.append(" and s.secrecyComputer.secrecyLevel = :secrecyLevel");
				params.put("secrecyLevel", secrecyComputerClear.getSecrecyComputer().getSecrecyLevel());
			}
			if (secrecyComputerClear.getClearType() != null
					&& !"".equals(secrecyComputerClear.getClearType())) {
				hql.append(" and s.clearType = :clearType");
				params.put("clearType", secrecyComputerClear.getClearType());
			}
		}
		hql.append(" order by s.createDate desc");
		return this.findList(hql.toString(), params, psm);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public void saveSecrecyComputerClear(
			SecrecyComputerClear secrecyComputerClear, User currentUser) {
		secrecyComputerClear.setCreatePerson(currentUser);
		secrecyComputerClear.setCreateDate(new Date());
		save(secrecyComputerClear);
	}


}
