package com.cdthgk.bmp.secrecyworksummary.service;

import java.util.List;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.bmp.secrecyworksummary.dto.SecrecyWorksummaryDto;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;

/**
 *
 * <p>
 * 保密工作概况moduleService
 * </p>
 * <p>
 * 创建时间 2013-1-13 - 上午11:46:39
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
public interface SecrecyWorksummaryModuleService extends SecrecyWorksummaryService, BmpServiceTemplate<Object, String> {

	/**
	 *
	 * <p>
	 * 根据行政区划获取保密业务数据概况
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-1-13 - 下午4:17:15
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param psm
	 * @param district
	 * @param organ
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	List<SecrecyWorksummaryDto> getSecrecyWorksummaryPageList(PageSortModel psm,
			District district, Organ queryOrgan);

	/**
	 *
	 * <p>
	 * 方法名：getSecrecyWorksummaryAllList 报表使用，全部导出
	 * </p>
	 * <p>
	 * 宋亚非 2013-5-21 下午4:02:27
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@param psm
	 *@param district
	 *@param queryOrgan
	 *@return
	 */
	List<SecrecyWorksummaryDto> getSecrecyWorksummaryAllList(District district, Organ queryOrgan);


}