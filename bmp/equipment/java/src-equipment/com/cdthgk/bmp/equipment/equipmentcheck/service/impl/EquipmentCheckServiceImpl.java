package com.cdthgk.bmp.equipment.equipmentcheck.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.equipment.equipmentcheck.service.EquipmentCheckService;
import com.cdthgk.bmp.equipment.equipmentcheck.vo.EquipmentCheck;
import com.cdthgk.platform.base.service.GenericServiceTemplate;

import ec.common.PageSortModel;

@SuppressWarnings("unchecked")
public class EquipmentCheckServiceImpl extends GenericServiceTemplate<EquipmentCheck, String> implements EquipmentCheckService {
	@Override
	public List<EquipmentCheck> getEquipmentCheckPageList(PageSortModel psm,
			EquipmentCheck equipmentCheck) {
		StringBuilder hql = new StringBuilder("from EquipmentCheck ec where 1=1");
		Map<String, Object> params = new HashMap<String, Object>();

		if (equipmentCheck != null) {
			if (equipmentCheck.getEquipmentType() != null && !"".equals(equipmentCheck.getEquipmentType())) {
				hql.append(" and ec.equipmentType like :equipmentType");
				params.put("equipmentType", "%"+equipmentCheck.getEquipmentType()+"%");
			}
			if (equipmentCheck.getEquipmentName() != null && !"".equals(equipmentCheck.getEquipmentName())) {
				hql.append(" and ec.equipmentName like :equipmentName");
				params.put("equipmentName", "%"+equipmentCheck.getEquipmentName()+"%");
			}
			if (equipmentCheck.getCheckOrgan() != null) {
				if (equipmentCheck.getCheckOrgan().getOrganName() != null && !"".equals(equipmentCheck.getCheckOrgan().getOrganName())) {
					hql.append(" and ec.checkOrgan.organName like :organName");
					params.put("organName", "%"+equipmentCheck.getCheckOrgan().getOrganName()+"%");
				}
			}
			if (equipmentCheck.getEquipmentOrgan() != null) {
				if (equipmentCheck.getEquipmentOrgan() != null && !"".equals(equipmentCheck.getEquipmentOrgan())) {
					hql.append(" and ec.equipmentOrgan like :equipmentOrgan");
					params.put("equipmentOrgan", "%"+equipmentCheck.getEquipmentOrgan()+"%");
				}
			}
			if (equipmentCheck.getCheckTime() != null) {
				hql.append(" and ec.checkTime between :startTime and :endTime");
				params.put("startTime", equipmentCheck.getCheckTime());
				params.put("endTime", new Date(equipmentCheck.getCheckTime().getTime() + 24 * 60 * 60 * 1000-1));
			}
		}

		hql.append(" order by ec.createTime desc");
		return (List<EquipmentCheck>) findList(hql.toString(), params, psm);
	}
}
