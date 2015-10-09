package com.cdthgk.bmp.secrecyperson.vo;
// default package
// Generated 2013-7-17 14:21:58 ----- 3.4.0.CR1

import java.util.Date;

import com.cdthgk.platform.organization.userinfo.domain.UserInfo;
import com.cdthgk.platform.permission.user.domain.User;

/**
 * BmSecrecyPersonLevelChange
 */
public class SecrecyPersonLevelChange implements java.io.Serializable {

	private String secrecyPersonLevelChangeId;

	//secrecy_person_id.
	private SecrecyPerson secrecyPersonId;

	//原密级
	private Integer beforeLevel;

	//现密级
	private Integer currentLevel;

	//保密期限变更
	private Integer changeTimeState;

	//变更时间
	private Date changeDate;

	//审批人
	private UserInfo reviewPerson;

	//变更说明
	private String changeReason;

	private User createPerson;

	private Date createDate;

	/**
	 * 默认构造函数
	 */
	public SecrecyPersonLevelChange() {
	}

	/**
	 * @return the secrecyPersonLevelChangeId
	 */
	public String getSecrecyPersonLevelChangeId() {
		return secrecyPersonLevelChangeId;
	}

	/**
	 * @param secrecyPersonLevelChangeId the secrecyPersonLevelChangeId to set
	 */
	public void setSecrecyPersonLevelChangeId(String secrecyPersonLevelChangeId) {
		this.secrecyPersonLevelChangeId = secrecyPersonLevelChangeId;
	}




	/**
	 * @return the secrecyPersonId
	 */
	public SecrecyPerson getSecrecyPersonId() {
		return secrecyPersonId;
	}

	/**
	 * @param secrecyPersonId the secrecyPersonId to set
	 */
	public void setSecrecyPersonId(SecrecyPerson secrecyPersonId) {
		this.secrecyPersonId = secrecyPersonId;
	}

	/**
	 * 返回beforeLevel
	 * @return beforeLevel
	 */
	public Integer getBeforeLevel() {
		return this.beforeLevel;
	}

	/**
	 * 设置beforeLevel
	 * @param beforeLevel beforeLevel
	 */
	public void setBeforeLevel(Integer beforeLevel) {
		this.beforeLevel = beforeLevel;
	}

	/**
	 * 返回currentLevel
	 * @return currentLevel
	 */
	public Integer getCurrentLevel() {
		return this.currentLevel;
	}

	/**
	 * 设置currentLevel
	 * @param currentLevel currentLevel
	 */
	public void setCurrentLevel(Integer currentLevel) {
		this.currentLevel = currentLevel;
	}

	/**
	 * 返回changeTimeState
	 * @return changeTimeState
	 */
	public Integer getChangeTimeState() {
		return this.changeTimeState;
	}

	/**
	 * 设置changeTimeState
	 * @param changeTimeState changeTimeState
	 */
	public void setChangeTimeState(Integer changeTimeState) {
		this.changeTimeState = changeTimeState;
	}

	/**
	 * 返回changeDate
	 * @return changeDate
	 */
	public Date getChangeDate() {
		return this.changeDate;
	}

	/**
	 * 设置changeDate
	 * @param changeDate changeDate
	 */
	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}

	/**
	 * 返回changeReason
	 * @return changeReason
	 */
	public String getChangeReason() {
		return this.changeReason;
	}

	/**
	 * 设置changeReason
	 * @param changeReason changeReason
	 */
	public void setChangeReason(String changeReason) {
		this.changeReason = changeReason;
	}


	/**
	 * @return the reviewPerson
	 */
	public UserInfo getReviewPerson() {
		return reviewPerson;
	}

	/**
	 * @param reviewPerson the reviewPerson to set
	 */
	public void setReviewPerson(UserInfo reviewPerson) {
		this.reviewPerson = reviewPerson;
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

	/**
	 * 返回createDate
	 * @return createDate
	 */
	public Date getCreateDate() {
		return this.createDate;
	}

	/**
	 * 设置createDate
	 * @param createDate createDate
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
