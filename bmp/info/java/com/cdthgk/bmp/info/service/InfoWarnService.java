/**
 *
 */
package com.cdthgk.bmp.info.service;

import java.util.List;

import com.cdthgk.bmp.info.vo.InfoWarn;
import com.cdthgk.platform.base.service.GenericBasicService;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

/**
 * @author Administrator
 *
 */
public interface InfoWarnService extends GenericBasicService<InfoWarn, String> {
	/**
	 * 根据接收机构Id获取需要查看的上报信息
	 * @param orgId
	 * @return List<InfoWarn>
	 */
	List<InfoWarn> findByOrgId(String orgId);
	/**
	 * 根据接收机构Id获取未查看的上报信息
	 * @param orgId
	 * @return
	 */
	List<InfoWarn> findUnreadByOrgId(String orgId);
	/**
	 * 根据接收然获取需要查看的上报信息
	 * @param psm
	 * @param infoWarn
	 * @param user
	 * @return
	 */
	List<InfoWarn> findInfoWarnList(PageSortModel<InfoWarn> psm, InfoWarn infoWarn, User user);
	/**
	 * 根据上报机构Id获取提醒信息
	 * @param orgId
	 * @return InfoWarn
	 */
	InfoWarn findByReportOrgId(String orgId);
	/**
	 * 根据infoId获取提醒信息
	 * @param infoId
	 * @return
	 */
	List<InfoWarn> findByInfoId(String infoId);
	/**
	 * 根据infoId删除上报提醒
	 * @param infoId
	 * @return
	 */
	int deleteByInfoId(String infoId);
}
