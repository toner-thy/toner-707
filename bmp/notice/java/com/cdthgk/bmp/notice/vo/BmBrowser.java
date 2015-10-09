package com.cdthgk.bmp.notice.vo;

import java.util.Date;

import com.cdthgk.platform.organization.organ.domain.Organ;
import com.thgk.platform.core.BaseDomain;

public class BmBrowser extends BaseDomain{
	private static final long serialVersionUID = 1L;

	public static final Integer STATE_LOGIN = 1;
	public static final Integer STATE_NO_LOGIN = 0;

	private String id;
	private String userAgent;
	private String remoteAddress;
	private Organ organ;
	private Date lastLoginTime;
	// 表单查询
	private Integer state;
	private String organName;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserAgent() {
		return userAgent;
	}
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	public String getRemoteAddress() {
		return remoteAddress;
	}
	public void setRemoteAddress(String remoteAddress) {
		this.remoteAddress = remoteAddress;
	}
	/**
	 * @return 返回organ
	 */
	public Organ getOrgan() {
		return organ;
	}
	/**
	 * @param organ 设置organ
	 */
	public void setOrgan(Organ organ) {
		this.organ = organ;
	}
	/**
	 * @return 返回lastLoginTime
	 */
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	/**
	 * @param lastLoginTime 设置lastLoginTime
	 */
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	/**
	 * @return 返回organName
	 */
	public String getOrganName() {
		return organName;
	}
	/**
	 * @param organName 设置organName
	 */
	public void setOrganName(String organName) {
		this.organName = organName;
	}
	/**
	 * @return 返回state
	 */
	public Integer getState() {
		return state;
	}
	/**
	 * @param state 设置state
	 */
	public void setState(Integer state) {
		this.state = state;
	}
}
