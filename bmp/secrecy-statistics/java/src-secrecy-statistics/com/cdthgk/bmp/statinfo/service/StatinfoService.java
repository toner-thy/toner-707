package com.cdthgk.bmp.statinfo.service;

import java.util.List;
import java.util.Map;

import com.cdthgk.platform.base.service.GenericBasicService;
import com.cdthgk.platform.organization.organ.domain.Organ;

/**
 *
 * <p>
 * 保密工作概况service
 * </p>
 * <p>
 * 创建时间 2013-1-13 - 上午11:47:17
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
public interface StatinfoService extends GenericBasicService<Object, String>{

	/**
	 * <p>
	 * 方法的说明
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2014-5-7 - 上午9:31:27
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 */
	List<Map<String, Object>> getAllBusinessModule();

	/**
	 * <p>
	 * 方法的说明
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2014-5-9 - 上午9:15:47
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param selectedModuleList
	 * @param organ
	 * @return
	 */
	Map<String, Object> buildData(List<Map<String, Object>> selectedModuleList, Organ organ);
}