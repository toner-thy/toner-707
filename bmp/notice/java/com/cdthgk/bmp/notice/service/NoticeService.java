package com.cdthgk.bmp.notice.service;

import java.util.List;

import com.cdthgk.bmp.notice.vo.BmSystemNotice;
import com.cdthgk.platform.base.service.GenericBasicService;

import ec.common.PageSortModel;

public interface NoticeService extends GenericBasicService<BmSystemNotice, String> {

	public List<BmSystemNotice> getListPage(PageSortModel<BmSystemNotice> psm, BmSystemNotice systemNotice, String startTime, String endTime);

	public List<BmSystemNotice> indexList(PageSortModel<BmSystemNotice> psm);

	public List<BmSystemNotice> indexAll(PageSortModel<BmSystemNotice> psm);

}
