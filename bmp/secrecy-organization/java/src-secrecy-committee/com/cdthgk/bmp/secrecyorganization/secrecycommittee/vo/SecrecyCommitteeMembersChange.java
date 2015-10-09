package com.cdthgk.bmp.secrecyorganization.secrecycommittee.vo;
// default package
// Generated 2013-7-16 15:13:14 ----- 3.4.0.CR1

import java.util.Date;

import com.cdthgk.platform.permission.user.domain.User;

/**
 * BmSecrecyCommitteeMembersChange
 */
public class SecrecyCommitteeMembersChange implements java.io.Serializable {

	private String secrecyCommitteeMembersChangeId;

	private SecrecyCommitteeMember secrecyCommitteeMemberId;

	private Integer changeType;

	private Date changeDate;

	private String changeReason;

	private User createPerson;

	private Date createDate;

	/**
	 * 默认构造函数
	 */
	public SecrecyCommitteeMembersChange() {
	}

	/**
	 * 返回secrecyCommitteeMembersChangeId
	 * @return secrecyCommitteeMembersChangeId
	 */
	public String getSecrecyCommitteeMembersChangeId() {
		return this.secrecyCommitteeMembersChangeId;
	}

	/**
	 * 设置secrecyCommitteeMembersChangeId
	 * @param secrecyCommitteeMembersChangeId secrecyCommitteeMembersChangeId
	 */
	public void setSecrecyCommitteeMembersChangeId(
			String secrecyCommitteeMembersChangeId) {
		this.secrecyCommitteeMembersChangeId = secrecyCommitteeMembersChangeId;
	}

	/**
	 * 返回changeType
	 * @return changeType
	 */
	public Integer getChangeType() {
		return this.changeType;
	}

	/**
	 * 设置changeType
	 * @param changeType changeType
	 */
	public void setChangeType(Integer changeType) {
		this.changeType = changeType;
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

	/**
	 * @return the secrecyCommitteeMemberId
	 */
	public SecrecyCommitteeMember getSecrecyCommitteeMemberId() {
		return secrecyCommitteeMemberId;
	}

	/**
	 * @param secrecyCommitteeMemberId the secrecyCommitteeMemberId to set
	 */
	public void setSecrecyCommitteeMemberId(
			SecrecyCommitteeMember secrecyCommitteeMemberId) {
		this.secrecyCommitteeMemberId = secrecyCommitteeMemberId;
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
