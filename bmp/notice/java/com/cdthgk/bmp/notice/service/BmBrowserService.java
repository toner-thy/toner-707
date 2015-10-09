package com.cdthgk.bmp.notice.service;

import java.util.List;

import com.cdthgk.bmp.notice.vo.BmBrowser;
import com.cdthgk.platform.base.service.GenericBasicService;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;

public interface BmBrowserService extends GenericBasicService<BmBrowser, String> {

	void saveOrUpdateBrowser(BmBrowser browser);

	List<BmBrowser> queryBMSUseStatePage(PageSortModel<BmBrowser> psm, District district,BmBrowser bmBrowser, String checkLower);

}
