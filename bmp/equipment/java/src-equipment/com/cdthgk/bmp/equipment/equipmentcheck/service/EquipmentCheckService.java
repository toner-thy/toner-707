package com.cdthgk.bmp.equipment.equipmentcheck.service;

import java.util.List;

import com.cdthgk.bmp.equipment.equipmentcheck.vo.EquipmentCheck;
import com.cdthgk.platform.base.service.GenericBasicService;

import ec.common.PageSortModel;

public interface EquipmentCheckService extends GenericBasicService<EquipmentCheck, String> {

	List<EquipmentCheck> getEquipmentCheckPageList(PageSortModel psm,
			EquipmentCheck equipmentCheck);
}
