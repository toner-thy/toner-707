package com.cdthgk.bmp.secrecysystem.secrecynetwork.vo;

import java.util.Date;

import com.cdthgk.platform.organization.userinfo.domain.UserInfo;

/**
 * SecrecyNetworkterminalChange
 */
public class SecrecyNetworkterminalChange implements java.io.Serializable {

	private static final long serialVersionUID = -2135928270716859980L;

	private String networkterminalChangeId;

	private SecrecyNetworkterminal secrecyNetworkterminal;

	private Integer beforeLevel;

	private Integer afterLevel;

	private Integer changeTimeState;

	private Date changeDate;

	private String changeReason;

	private UserInfo createPerson;

	private Date createDate;

	/**
	 * 默认构�?函数
	 */
	public SecrecyNetworkterminalChange() {
	}

	/**
	 * @return 返回networkterminalChangeId
	 */
	public String getNetworkterminalChangeId() {
		return networkterminalChangeId;
	}

	/**
	 * @param networkterminalChangeId 设置networkterminalChangeId
	 */
	public void setNetworkterminalChangeId(String networkterminalChangeId) {
		this.networkterminalChangeId = networkterminalChangeId;
	}

	/**
	 * @return 返回secrecyNetworkterminal
	 */
	public SecrecyNetworkterminal getSecrecyNetworkterminal() {
		return secrecyNetworkterminal;
	}

	/**
	 * @param secrecyNetworkterminal 设置secrecyNetworkterminal
	 */
	public void setSecrecyNetworkterminal(
			SecrecyNetworkterminal secrecyNetworkterminal) {
		this.secrecyNetworkterminal = secrecyNetworkterminal;
	}

	/**
	 * @return 返回beforeLevel
	 */
	public Integer getBeforeLevel() {
		return beforeLevel;
	}

	/**
	 * @param beforeLevel 设置beforeLevel
	 */
	public void setBeforeLevel(Integer beforeLevel) {
		this.beforeLevel = beforeLevel;
	}

	/**
	 * @return 返回afterLevel
	 */
	public Integer getAfterLevel() {
		return afterLevel;
	}

	/**
	 * @param afterLevel 设置afterLevel
	 */
	public void setAfterLevel(Integer afterLevel) {
		this.afterLevel = afterLevel;
	}

	/**
	 * @return 返回changeTimeState
	 */
	public Integer getChangeTimeState() {
		return changeTimeState;
	}

	/**
	 * @param changeTimeState 设置changeTimeState
	 */
	public void setChangeTimeState(Integer changeTimeState) {
		this.changeTimeState = changeTimeState;
	}

	/**
	 * @return 返回changeDate
	 */
	public Date getChangeDate() {
		return changeDate;
	}

	/**
	 * @param changeDate 设置changeDate
	 */
	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}

	/**
	 * @return 返回changeReason
	 */
	public String getChangeReason() {
		return changeReason;
	}

	/**
	 * @param changeReason 设置changeReason
	 */
	public void setChangeReason(String changeReason) {
		this.changeReason = changeReason;
	}

	/**
	 * @return 返回createPerson
	 */
	public UserInfo getCreatePerson() {
		return createPerson;
	}

	/**
	 * @param createPerson 设置createPerson
	 */
	public void setCreatePerson(UserInfo createPerson) {
		this.createPerson = createPerson;
	}

	/**
	 * @return 返回createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate 设置createDate
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
