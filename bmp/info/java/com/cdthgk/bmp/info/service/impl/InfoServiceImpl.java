package com.cdthgk.bmp.info.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.info.service.InfoService;
import com.cdthgk.bmp.info.vo.Info;
import com.cdthgk.common.lang.StringUtils;
import com.cdthgk.platform.base.service.GenericServiceTemplate;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

/**
 *
 * <p>
 * 类的说明放这里
 * </p>
 * <p>
 * 创建时间 2015-1-7 - 上午10:02:25
 * </p>
 * <blockquote>
 * <h4>历史修改记录</h4>
 * <ul>
 * <li>修改人 修改时间 修改描述
 * </ul>
 * </blockquote>
 * <p>
 * copyright cdthgk 2010-2011, all rights reserved.
 * </p>
 *
 * @author 陶汇源
 * @author cdthgk r&d
 * @since 1.0
 * @version 1.0
 */
public class InfoServiceImpl extends GenericServiceTemplate<Info, String> implements InfoService{

	public List<Info> getInfoList(PageSortModel<Info> psm, Info info, User user){
		StringBuilder hql = new StringBuilder("FROM Info i where 1=1");
		Map<String, Object> params = new HashMap<String, Object>();
		//当前登陆系统人员
		if(user != null){
			hql.append(" and i.createPerson.userId = :userId");
			params.put("userId", user.getUserId());
		}

		if(info != null){
			if (StringUtils.isNotBlank(info.getTitle())) {
				hql.append(" and LOCATE(:title, i.title) > 0");
				params.put("title", info.getTitle());
			}
			if (info.getInfoType() != null && StringUtils.isNotBlank(info.getInfoType().getInfoTypeId())) {
				hql.append(" and i.infoType.infoTypeId = :infoTypeId");
				params.put("infoTypeId", info.getInfoType().getInfoTypeId());
			}
			if (info.getStatus() != null) {
				hql.append(" and i.status = :status");
				params.put("status", info.getStatus());
			}
			if (info.getReportType() != null) {
				hql.append(" and i.reportType = :reportType");
				params.put("reportType", info.getReportType());
			}
		}
		hql.append(" order by i.createTime desc");
		return this.findList(hql.toString(), params, psm);
	}

	public List<Info> getInfoAuditList(PageSortModel<Info> psm, Info info, User user) {
		//查询审核人员能看到状态数据： 审核，审核驳回，审核通过
		StringBuilder hql = new StringBuilder("FROM Info i WHERE i.status > 1");
		Map<String, Object> params = new HashMap<String, Object>();
		//当前登陆系统人员
		if(user != null){
			hql.append(" and i.leader.userInfoId = :userInfoId");
			params.put("userInfoId", user.getUserInfo().getUserInfoId());
		}

		if(info != null){
			if (StringUtils.isNotBlank(info.getTitle())) {
				if (StringUtils.isNotBlank(info.getTitle())) {
					hql.append(" and LOCATE(:title, i.title) > 0");
					params.put("title", info.getTitle());
				}
				if (info.getReportType() != null) {
					hql.append(" and i.reportType = :reportType");
					params.put("reportType", info.getReportType());
				}
			}
		}
		hql.append(" order by i.status");
		return this.findList(hql.toString(), params, psm);
	}

}
