package com.cdthgk.checkevent.dto;

import com.cdthgk.checkevent.vo.SecrecyCheckEvent;

/**
 * <p>
 * 密级查询条件
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 *
 * @author 刘一帆
 */
public class SecrecyCheckEventDto {

	private SecrecyCheckEvent secrecyCheckEvent;

	/**
	 * 默认构造函数
	 */
	public SecrecyCheckEventDto() {
	}

	/**
	 * 返回secrecyCheckEvent
	 * @return secrecyCheckEvent
	 */
	public SecrecyCheckEvent getSecrecyCheckEvent() {
		return secrecyCheckEvent;
	}

	/**
	 * 设置secrecyCheckEvent
	 * @param secrecyCheckEvent secrecyCheckEvent
	 */
	public void setSecrecyCheckEvent(SecrecyCheckEvent secrecyCheckEvent) {
		this.secrecyCheckEvent = secrecyCheckEvent;
	}
}
