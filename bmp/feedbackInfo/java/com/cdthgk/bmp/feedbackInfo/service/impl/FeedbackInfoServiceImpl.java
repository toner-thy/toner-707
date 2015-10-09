package com.cdthgk.bmp.feedbackInfo.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.feedbackInfo.service.FeedbackInfoService;
import com.cdthgk.bmp.feedbackInfo.vo.FeedbackInfo;
import com.cdthgk.platform.base.service.GenericServiceTemplate;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

/**
 *
 * @author 牟远洋 2012 09 18 13:14:56
 */
public class FeedbackInfoServiceImpl extends GenericServiceTemplate<FeedbackInfo, String> implements FeedbackInfoService{

	/**
	 * @description 查询列表
	 * @author 牟远洋 2013 05 07 10:25:56
	 * @param PageSortModel psm
	 * @param FeedbackInfo feedbackInfo
	 * @return List<FeedbackInfo>
	 */
	public List<FeedbackInfo> getFeedbackInfoList(PageSortModel<FeedbackInfo> psm, FeedbackInfo feedbackInfo, User user){

		StringBuilder hql = new StringBuilder("FROM FeedbackInfo f where 1=1 ");
		Map<String, Object> params = new HashMap<String, Object>();
		//当前登陆系统人员
		if(user != null){
			hql.append(" and f.createPerson.userId = :userId");
			params.put("userId", user.getUserId());
		}

		if(feedbackInfo != null){
			if (feedbackInfo.getFeedbackTitle() != null && !"".equals(feedbackInfo.getFeedbackTitle())) {
				hql.append(" and f.feedbackTitle like :feedbackTitle");
				params.put("feedbackTitle", "%" + feedbackInfo.getFeedbackTitle() + "%");
			}
			if (feedbackInfo.getFeedbackType() != null && feedbackInfo.getFeedbackType() != 0){
				hql.append(" and f.feedbackType = :feedbackType");
				params.put("feedbackType", feedbackInfo.getFeedbackType());
			}
			if (feedbackInfo.getOrgan() != null && !"".equals(feedbackInfo.getOrgan().getOrganName())){
				hql.append(" and f.organ.organName like :organName");
				params.put("organName", "%" + feedbackInfo.getOrgan().getOrganName() + "%");
			}
			if (feedbackInfo.getCreatePerson() != null && !"".equals(feedbackInfo.getCreatePerson().getUserName())){
				hql.append(" and f.createPerson.userName like :userName");
				params.put("userName", "%" + feedbackInfo.getCreatePerson().getUserName() + "%");
			}
		}

		hql.append(" order by f.modifyTime desc");
		//return (List<FeedbackInfo>)listForEc(hql.toString(), psm, params);
		List<FeedbackInfo> list = this.findList(hql.toString(), params, psm);
		return list;
	}


}
