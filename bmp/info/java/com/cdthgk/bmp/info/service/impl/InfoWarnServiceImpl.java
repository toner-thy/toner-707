/**
 *
 */
package com.cdthgk.bmp.info.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.info.service.InfoWarnService;
import com.cdthgk.bmp.info.vo.InfoWarn;
import com.cdthgk.common.lang.StringUtils;
import com.cdthgk.platform.base.service.GenericServiceTemplate;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

/**
 * @author Administrator
 *
 */
public class InfoWarnServiceImpl extends GenericServiceTemplate<InfoWarn, String>
		implements InfoWarnService {
	public List<InfoWarn> findInfoWarnList(PageSortModel<InfoWarn> psm, InfoWarn infoWarn, User user) {
		StringBuilder hql = new StringBuilder("FROM InfoWarn iw WHERE iw.status > 0");
		Map<String, Object> params = new HashMap<String, Object>();
		//当前登陆系统人员
		if(user != null){
			hql.append(" and iw.organ.organId = :organId");
			params.put("organId", user.getOrgan().getOrganId());
		}
		if(infoWarn != null){
			if (StringUtils.isNotBlank(infoWarn.getTitle())) {
				hql.append(" and LOCATE(:title, iw.title) > 0");
				params.put("title", infoWarn.getTitle());
			}
			if (StringUtils.isNotBlank(infoWarn.getReportOrgan())) {
				hql.append(" and LOCATE(:orgName, iw.reportOrgan) > 0");
				params.put("orgName", infoWarn.getReportOrgan());
			}
			if (infoWarn.getInfo() != null && StringUtils.isNotBlank(infoWarn.getInfo().getInfoType().getInfoTypeId())) {
				hql.append(" and iw.info.infoType.infoTypeId = :infoTypeId");
				params.put("infoTypeId", infoWarn.getInfo().getInfoType().getInfoTypeId());
			}
			if (StringUtils.isNotBlank(infoWarn.getReportBeginTime()) && StringUtils.isNotBlank(infoWarn.getReportEndTime())) {
				hql.append(" and (DATE_FORMAT(iw.reportTime, '%Y-%m-%d') BETWEEN :reportBeginTime and :reportEndTime)");
				params.put("reportBeginTime", infoWarn.getReportBeginTime());
				params.put("reportEndTime", infoWarn.getReportEndTime());
			}
			if (StringUtils.isNotBlank(infoWarn.getReportBeginTime()) && StringUtils.isBlank(infoWarn.getReportEndTime())) {
				hql.append(" and DATE_FORMAT(iw.reportTime, '%Y-%m-%d') >= :reportBeginTime");
				params.put("reportBeginTime", infoWarn.getReportBeginTime());
			}
			if (StringUtils.isBlank(infoWarn.getReportBeginTime()) && StringUtils.isNotBlank(infoWarn.getReportEndTime())) {
				hql.append(" and DATE_FORMAT(iw.reportTime, '%Y-%m-%d') <= :reportEndTime");
				params.put("reportEndTime", infoWarn.getReportEndTime());
			}
		}
		hql.append(" order by iw.reportTime desc");
		return this.findList(hql.toString(), params, psm);
	}

	public List<InfoWarn> findByOrgId(String orgId) {
		if (StringUtils.isNotBlank(orgId)) {
			StringBuilder hql = new StringBuilder("FROM InfoWarn iw");
			Map<String, Object> params = new HashMap<String, Object>();
			hql.append(" WHERE iw.organ.organId = :orgId");
			params.put("orgId", orgId);
			return this.findList(hql.toString(), params);
		} else
			return null;
	}
	public List<InfoWarn> findUnreadByOrgId(String orgId) {
		if (StringUtils.isNotBlank(orgId)) {
			StringBuilder hql = new StringBuilder("FROM InfoWarn iw WHERE ");
			Map<String, Object> params = new HashMap<String, Object>();
			hql.append("iw.status = :status");
			params.put("status", 1);
			hql.append(" and iw.organ.organId = :orgId");
			params.put("orgId", orgId);
			return this.findList(hql.toString(), params);
		} else
			return null;
	}

	public InfoWarn findByReportOrgId(String orgId) {
		if (StringUtils.isNotBlank(orgId)) {
			StringBuilder hql = new StringBuilder("FROM InfoWarn iw ");
			hql.append("WHERE iw.reportOrgan = :orgId");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("orgId", orgId);
			return this.unique(hql.toString(), params);
		} else
			return null;
	}
	public List<InfoWarn> findByInfoId(String infoId) {
		if (StringUtils.isNotBlank(infoId)) {
			StringBuilder hql = new StringBuilder("FROM InfoWarn iw ");
			hql.append("WHERE iw.info.infoId = :infoId");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("infoId", infoId);
			return this.findList(hql.toString(), params);
		} else
			 return null;
	}
	public int deleteByInfoId(String infoId) {
		if (StringUtils.isNotBlank(infoId)) {
			String delSql = "DELETE FROM bm_info_warn WHERE info_id = :infoId";
            Map<String, Object> params = new HashMap<String, Object>();
			params.put("infoId", infoId);
			return this.getPersistProxy().getJdbcPersistence().execute(delSql, params);
		} else
			 return 0;
	}
}
