package com.cdthgk.bmp.secrecysystem.secrecynetwork.vo;

import java.util.Date;

import com.cdthgk.platform.permission.user.domain.User;

/**
 * SecrecyNetworkChange
 */
public class SecrecyNetworkChange implements java.io.Serializable {

	private static final long serialVersionUID = -558003752053986675L;

	private String networkChangeId;

	private SecrecyNetwork secrecyNetwork;

	private Integer beforeLevel;

	private Integer afterLevel;

	private Integer changeTimeState;

	private Date changeDate;

	private String changeReason;

	private User createPerson;

	private Date createDate;

	/**
	 * 默认构�?函数
	 */
	public SecrecyNetworkChange() {
	}

	/**
	 * @return 返回networkChangeId
	 */
	public String getNetworkChangeId() {
		return networkChangeId;
	}

	/**
	 * @param networkChangeId 设置networkChangeId
	 */
	public void setNetworkChangeId(String networkChangeId) {
		this.networkChangeId = networkChangeId;
	}

	/**
	 * @return 返回secrecyNetwork
	 */
	public SecrecyNetwork getSecrecyNetwork() {
		return secrecyNetwork;
	}

	/**
	 * @param secrecyNetwork 设置secrecyNetwork
	 */
	public void setSecrecyNetwork(SecrecyNetwork secrecyNetwork) {
		this.secrecyNetwork = secrecyNetwork;
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

	/**
	 * @return 返回createPerson
	 */
	public User getCreatePerson() {
		return createPerson;
	}

	/**
	 * @param createPerson 设置createPerson
	 */
	public void setCreatePerson(User createPerson) {
		this.createPerson = createPerson;
	}
}
