package com.cdthgk.bmp.secrecynet.secrecybasic.service;

import java.util.List;

import com.cdthgk.bmp.secrecynet.secrecybasic.vo.SecrecyBasic;
import com.cdthgk.platform.base.service.GenericBasicService;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

public interface SecrecyBasicService extends GenericBasicService<SecrecyBasic, String> {

	/**
	 * 根据年份和单位获取改年份的记录
	 * @param year
	 * @param organ
	 * @return
	 */
	SecrecyBasic getSecrecyBasicByYear(Integer year, Organ organ);

	Object getListPage(PageSortModel<SecrecyBasic> psm,
			SecrecyBasic secrecyBasic, User currentUser);

	List<SecrecyBasic> getSecrecyBasicList(Integer year, String organId);

}
