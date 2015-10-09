package com.cdthgk.bmp.secrecyorganization.secrecyoffice.vo;

import java.util.Date;

import com.cdthgk.platform.organization.userinfo.domain.UserInfo;
import com.cdthgk.platform.permission.user.domain.User;

/**
 * BmSecrecyPersonLevelChange
 */
public class SecrecyOfficeMemberChange implements java.io.Serializable {

	private static final long serialVersionUID = 746396115646471547L;

	private String secrecyOfficeMemberChangeId;

	// 所属保密办（保密局）成员
	private SecrecyOfficeMember secrecyOfficeMember;

	private Integer beforeLevel;

	private Integer currentLevel;

	private Integer changeTimeState;

	private Date changeDate;

	private UserInfo reviewPerson;

	private String changeReason;

	private User createPerson;

	private Date createDate;

	/**
	 * @return 返回secrecyOfficeMemberChangeId
	 */
	public String getSecrecyOfficeMemberChangeId() {
		return secrecyOfficeMemberChangeId;
	}

	/**
	 * @param secrecyOfficeMemberChangeId 设置secrecyOfficeMemberChangeId
	 */
	public void setSecrecyOfficeMemberChangeId(String secrecyOfficeMemberChangeId) {
		this.secrecyOfficeMemberChangeId = secrecyOfficeMemberChangeId;
	}

	/**
	 * @return 返回secrecyOfficeMember
	 */
	public SecrecyOfficeMember getSecrecyOfficeMember() {
		return secrecyOfficeMember;
	}

	/**
	 * @param secrecyOfficeMember 设置secrecyOfficeMember
	 */
	public void setSecrecyOfficeMember(SecrecyOfficeMember secrecyOfficeMember) {
		this.secrecyOfficeMember = secrecyOfficeMember;
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
	 * @return 返回currentLevel
	 */
	public Integer getCurrentLevel() {
		return currentLevel;
	}

	/**
	 * @param currentLevel 设置currentLevel
	 */
	public void setCurrentLevel(Integer currentLevel) {
		this.currentLevel = currentLevel;
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
	 * @return 返回reviewPerson
	 */
	public UserInfo getReviewPerson() {
		return reviewPerson;
	}

	/**
	 * @param reviewPerson 设置reviewPerson
	 */
	public void setReviewPerson(UserInfo reviewPerson) {
		this.reviewPerson = reviewPerson;
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
