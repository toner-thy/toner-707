package com.cdthgk.bmp.secrecynet.secrecytroops.service;

import com.cdthgk.bmp.secrecynet.secrecytroops.vo.SecrecyTroops;
import com.cdthgk.platform.base.service.GenericBasicService;
import com.cdthgk.platform.organization.organ.domain.Organ;

public interface SecrecyTroopsService extends GenericBasicService<SecrecyTroops, String> {

	/**
	 * 根据年份和单位获取改年份的记录
	 * @param year
	 * @param organ
	 * @return
	 */
	SecrecyTroops getSecrecyTroopsByYear(Integer year, Organ organ);

}
