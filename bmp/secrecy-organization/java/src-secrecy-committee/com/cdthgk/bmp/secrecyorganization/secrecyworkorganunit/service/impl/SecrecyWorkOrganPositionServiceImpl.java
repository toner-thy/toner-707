package com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.bmp.secrecyorganization.secrecycommittee.vo.PersonGroupPosition;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.service.SecrecyWorkOrganPositionModuleService;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.vo.SecrecyWorkOrgan;

/**
 * <p>
 * 人员职位 Service 实现类
 * </p>
 * <p>
 * 刘椿成2012-12-18 15:24
 * </p>
 * <blockquote>
 * <h4>历史修改记录</h4>
 * <ul>
 * <li>修改人 修改时间 修改描述
 * </ul>
 * </blockquote>
 * <p>
 * copyright liucc 2012, all rights reserved.
 * </p>
 * @author liucc
 * @since 1.0
 * @version 1.0
 */
public class SecrecyWorkOrganPositionServiceImpl extends BmpServiceImpl<PersonGroupPosition, String> implements SecrecyWorkOrganPositionModuleService {

	// 构造器
	/** 默认构造器 */
	SecrecyWorkOrganPositionServiceImpl() {
	}

	/**
	 * <p>
	 * 根据类型得到列表（type=1为得到保密工作小组，type=2为得到保密委）
	 * </p>
	 * <p>
	 * 刘椿成 2012-12-18 15:30
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * <p>
	 * copyright liucc 2012, all rights reserved.
	 * </p>
	 * @author liucc
	 * @since 1.0
	 * @version 1.0
	 */
	public List<PersonGroupPosition> getByGroupType(SecrecyWorkOrgan secrecyWorkOrgan){
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("from PersonGroupPosition pgp where 1=1 ");
		hql.append(" and pgp.groupType = :type");
		params.put("type", secrecyWorkOrgan.getGroupType());
//		return getList(hql.toString(),params);
		return findList(hql.toString(),params);
	}
}