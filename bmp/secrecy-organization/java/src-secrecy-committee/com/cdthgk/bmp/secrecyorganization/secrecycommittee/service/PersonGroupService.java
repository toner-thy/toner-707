package com.cdthgk.bmp.secrecyorganization.secrecycommittee.service;

import java.util.List;

import com.cdthgk.bmp.core.service.BmpService;
import com.cdthgk.bmp.secrecyorganization.secrecycommittee.vo.PersonGroup;
import com.cdthgk.platform.organization.organ.domain.Organ;

public interface PersonGroupService  extends BmpService<PersonGroup, String> {

	/**
	 * 查找组
	 * @param organ  单位对象
	 * @return
	 */
	public List<PersonGroup> findGroup(Organ organ);
}
