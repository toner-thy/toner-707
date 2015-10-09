package com.cdthgk.bmp.secrecyorganization.secrecycommittee.service;

import java.util.List;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.bmp.secrecyorganization.secrecycommittee.vo.PersonGroupPosition;

/**
 * <p>
 * 成员组职位 Service 抽象类(对外)
 * </p>
 * <p>
 * 汪 钟 2012-12-18 15:23
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
public interface PersonGroupPositionModuleService extends PersonGroupPositionService, BmpServiceTemplate<PersonGroupPosition, String> {

	/**
	 * <p>
	 * 根据类型得到列表（type=1为得到保密工作小组，type=2为得到保密委）
	 * </p>
	 * <p>
	 * 汪 钟 2012-12-18 15:30
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
	public List<PersonGroupPosition> getByGroupType(int type);

}