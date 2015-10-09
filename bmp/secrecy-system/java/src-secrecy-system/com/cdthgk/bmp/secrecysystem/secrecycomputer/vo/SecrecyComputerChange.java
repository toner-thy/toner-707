package com.cdthgk.bmp.secrecysystem.secrecycomputer.vo;

import java.util.Date;

import com.cdthgk.platform.permission.user.domain.User;

/**
 * SecrecyComputerChange
 */
public class SecrecyComputerChange implements java.io.Serializable {

	private static final long serialVersionUID = 7222638572362127977L;

	private String computerChangeId;

	private SecrecyComputer secrecyComputer;

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
	public SecrecyComputerChange() {
	}

	/**
	 * @return 返回computerChangeId
	 */
	public String getComputerChangeId() {
		return computerChangeId;
	}

	/**
	 * @param computerChangeId 设置computerChangeId
	 */
	public void setComputerChangeId(String computerChangeId) {
		this.computerChangeId = computerChangeId;
	}

	/**
	 * @return 返回secrecyComputer
	 */
	public SecrecyComputer getSecrecyComputer() {
		return secrecyComputer;
	}

	/**
	 * @param secrecyComputer 设置secrecyComputer
	 */
	public void setSecrecyComputer(SecrecyComputer secrecyComputer) {
		this.secrecyComputer = secrecyComputer;
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
	public User getCreatePerson() {
		return createPerson;
	}

	/**
	 * @param createPerson 设置createPerson
	 */
	public void setCreatePerson(User createPerson) {
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
