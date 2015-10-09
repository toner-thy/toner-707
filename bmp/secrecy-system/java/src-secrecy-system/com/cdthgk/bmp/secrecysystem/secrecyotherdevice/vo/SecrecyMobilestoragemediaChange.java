package com.cdthgk.bmp.secrecysystem.secrecyotherdevice.vo;


import java.util.Date;

import com.cdthgk.platform.permission.user.domain.User;

/**
 * SecrecyMobilestoragemediaChange
 */
public class SecrecyMobilestoragemediaChange implements java.io.Serializable {

	private static final long serialVersionUID = 2044984413619185080L;

	private String mobilestoragemediaChangeId;

	private SecrecyMobilestoragemedia secrecyMobilestoragemedia;

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
	public SecrecyMobilestoragemediaChange() {
	}


	/**
	 * @return the mobilestoragemediaChangeId
	 */
	public String getMobilestoragemediaChangeId() {
		return mobilestoragemediaChangeId;
	}


	/**
	 * @param mobilestoragemediaChangeId the mobilestoragemediaChangeId to set
	 */
	public void setMobilestoragemediaChangeId(String mobilestoragemediaChangeId) {
		this.mobilestoragemediaChangeId = mobilestoragemediaChangeId;
	}


	/**
	 * @return 返回secrecyMobilestoragemedia
	 */
	public SecrecyMobilestoragemedia getSecrecyMobilestoragemedia() {
		return secrecyMobilestoragemedia;
	}

	/**
	 * @param secrecyMobilestoragemedia 设置secrecyMobilestoragemedia
	 */
	public void setSecrecyMobilestoragemedia(
			SecrecyMobilestoragemedia secrecyMobilestoragemedia) {
		this.secrecyMobilestoragemedia = secrecyMobilestoragemedia;
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
	 * @return the createPerson
	 */
	public User getCreatePerson() {
		return createPerson;
	}

	/**
	 * @param createPerson the createPerson to set
	 */
	public void setCreatePerson(User createPerson) {
		this.createPerson = createPerson;
	}


}
