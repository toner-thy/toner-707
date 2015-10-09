package com.cdthgk.bmp.secrecyStatistics.service;

import java.util.List;

import com.cdthgk.bmp.secrecyStatistics.dto.SecrecyStatisticsDto;
import com.cdthgk.platform.base.service.GenericService;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;

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
public interface SecrecyStatisticsModuleService extends SecrecyStatisticsService, GenericService<Object, String> {

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
	List<SecrecyStatisticsDto> getSecrecyStatisticsPageList(District district, Organ queryOrgan);

	/**
	 * <p>
	 * 统计数据导出EXCEL
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-4-26 - 上午10:06:25
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param baseUri
	 * @param list
	 * @param district
	 * @return
	 */
	String exportData(String baseUri, List<SecrecyStatisticsDto> list, District district);


}