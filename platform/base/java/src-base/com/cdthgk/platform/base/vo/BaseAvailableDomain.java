package com.cdthgk.platform.base.vo;

import com.thgk.platform.core.BaseDomain;

/**
 * <p>
 * 启用/禁用实体的基础类
 * </p>
 * <p>
 * 创建时间 2011-6-23 - 下午05:00:21
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
 * @author 钟冀
 * @author cdpws r&d
 * @since 1.0
 * @version 1.0
 */
public class BaseAvailableDomain extends BaseDomain implements Available {

	private static final long serialVersionUID = -3932404799395672432L;

	/**
	 *
	 */
	protected BaseAvailableDomain() {
	}

	// ********************************************************************
	//	property
	// ********************************************************************

	private Integer state;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer getState() {
		return state;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setState(Integer state) {
		this.state = state;
	}
}
