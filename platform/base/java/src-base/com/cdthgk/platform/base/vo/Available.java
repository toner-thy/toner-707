package com.cdthgk.platform.base.vo;

/**
 * <p>
 * 启用/禁用 接口
 * </p>
 * <p>
 * 创建时间 2011-7-31 - 下午04:29:32
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
public interface Available {

	/**
	 * 1：启用
	 */
	int STATE_ENABLE = 1;
	/**
	 * 2：禁用
	 */
	int STATE_DISABLE = 2;

	/**
	 * 返回state
	 * @return state
	 */
	Integer getState();

	/**
	 * 设置state
	 * @param state state
	 */
	void setState(Integer state);
}
