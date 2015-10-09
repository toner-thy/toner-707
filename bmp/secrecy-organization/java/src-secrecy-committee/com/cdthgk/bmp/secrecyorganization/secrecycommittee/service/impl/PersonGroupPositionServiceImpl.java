package com.cdthgk.bmp.secrecyorganization.secrecycommittee.service.impl;

import java.util.List;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.bmp.secrecyorganization.secrecycommittee.service.PersonGroupPositionModuleService;
import com.cdthgk.bmp.secrecyorganization.secrecycommittee.vo.PersonGroupPosition;

/**
 * <p>
 * 人员组职位 Service 实现类
 * </p>
 * <p>
 * 汪 钟 2012-12-18 15:24
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
public class PersonGroupPositionServiceImpl extends BmpServiceImpl<PersonGroupPosition, String> implements PersonGroupPositionModuleService {
	// 构造器
	/** 默认构造器 */
	PersonGroupPositionServiceImpl() {
	}

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
	public List<PersonGroupPosition> getByGroupType(int type){
//		return getList("from PersonGroupPosition pgp where 1=1 and pgp.groupType=2", null);
		return findList("from PersonGroupPosition pgp where 1=1 and pgp.groupType=2", null);
	}
}