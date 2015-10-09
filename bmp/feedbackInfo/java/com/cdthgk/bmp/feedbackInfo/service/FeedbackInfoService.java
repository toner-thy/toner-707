package com.cdthgk.bmp.feedbackInfo.service;

import java.util.List;

import com.cdthgk.bmp.feedbackInfo.vo.FeedbackInfo;
import com.cdthgk.platform.base.service.GenericBasicService;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

/**
 *
 * @author 牟远洋 2013 05 07 10:14:56
 */
public interface FeedbackInfoService extends GenericBasicService<FeedbackInfo, String>{

	/**
	 * @description 查询列表
	 * @author 牟远洋 2013 05 07 10:21:56
	 * @param PageSortModel psm
	 * @param FeedbackInfo feedbackInfo
	 * @return List<FeedbackInfo>
	 */
	public List<FeedbackInfo> getFeedbackInfoList(PageSortModel<FeedbackInfo> psm, FeedbackInfo feedbackInfo, User user);
}
