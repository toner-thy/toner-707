package com.cdthgk.bmp.secrecyorganization.secrecyoffice.service;

import java.util.List;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.bmp.secrecyorganization.secrecyoffice.vo.SecrecyOffice;
import com.cdthgk.bmp.secrecyorganization.secrecyoffice.vo.SecrecyOfficeMemberChange;
import com.cdthgk.platform.district.domain.District;

import ec.common.PageSortModel;

/**
 * <p>
 * 保密办（保密局）成员 Service 抽象类(对外)
 * </p>
 * <p>
 * 陶汇源 2012-12-18 13:42
 * </p>
 * <blockquote>
 * <h4>历史修改记录</h4>
 * <ul>
 * <li>修改人 修改时间 修改描述
 * </ul>
 * </blockquote>
 * <p>
 * copyright tianyu 2012, all rights reserved.
 * </p>
 *
 * @author FastCodeingTools
 * @author tianyu
 * @since 1.0
 * @version 1.0
 */
public interface SecrecyOfficeMemberChangeModuleService extends BmpServiceTemplate<SecrecyOfficeMemberChange, String> {


	/**
	 * <p>
	 * 分页获取保密办（保密局）密级变更成员
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-7-18 - 下午5:17:42
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param secrecyOfficeMemberChangePsm
	 * @param secrecyOffice
	 * @param district
	 * @return
	 */
	List<SecrecyOfficeMemberChange> getSecrecyOfficeMemberChangePageList(
			PageSortModel<SecrecyOfficeMemberChange> psm,
			SecrecyOffice secrecyOffice, District district);
}