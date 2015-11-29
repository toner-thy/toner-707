package com.cdthgk.bmp.formatFile.service;

import java.util.List;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.bmp.formatFile.vo.FormatFileUserInfo;

import ec.common.PageSortModel;

public interface FormatFileUserInfoService extends BmpServiceTemplate<FormatFileUserInfo, String>{

	//接收列表
	public List<FormatFileUserInfo> queryAcceptListPage(PageSortModel<FormatFileUserInfo> psm,
			FormatFileUserInfo formatFileUserInfo, String userInfoId);

	//查询文件对应的人员
	public List<FormatFileUserInfo> queryFormatFileUserInfoById(String formatFileId);

	public void deleteBatchFormatFileUserInfo(List<FormatFileUserInfo> flist);
}
