package com.cdthgk.bmp.formatFile.service;

import java.util.List;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.bmp.formatFile.vo.FormatFile;
import com.cdthgk.bmp.formatFile.vo.FormatFileUserInfo;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;

public interface FormatFileService extends BmpServiceTemplate<FormatFile, String>{

	//发送列表
	public List<FormatFile> queryListPage(PageSortModel<FormatFile> psm,
			FormatFile formatFile, Organ organ);

	//公文查看，修改查看状态
	public void updateFormatFileUserInfo(FormatFileUserInfo formatFileUserInfo);

	//保存关联人员
	public void saveFormatFileUserInfo(List<FormatFileUserInfo> formatFileUserInfoList);

}
