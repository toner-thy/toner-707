package com.cdthgk.bmp.secrecyorganization.secrecyoffice.vo;

import java.util.Date;

import com.cdthgk.platform.permission.user.domain.User;

public class SecrecyOfficeMemberDecryption implements java.io.Serializable {

	private static final long serialVersionUID = 8561660684223448284L;

	private String secrecyOfficeMemberDecryptionId;

	// 所属保密办（保密局）成员
	private SecrecyOfficeMember secrecyOfficeMember;

	private Integer decryptionType;

	private Integer decryptionLimit;

	private Integer limitMeasure;

	private Date decryptionStart;

	private Date decryptionEnd;

	private String decryptionReason;

	private User createPerson;

	private Date createDate;

	/**
	 * @return 返回secrecyOfficeMemberDecryptionId
	 */
	public String getSecrecyOfficeMemberDecryptionId() {
		return secrecyOfficeMemberDecryptionId;
	}

	/**
	 * @param secrecyOfficeMemberDecryptionId 设置secrecyOfficeMemberDecryptionId
	 */
	public void setSecrecyOfficeMemberDecryptionId(
			String secrecyOfficeMemberDecryptionId) {
		this.secrecyOfficeMemberDecryptionId = secrecyOfficeMemberDecryptionId;
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
	 * @return 返回decryptionType
	 */
	public Integer getDecryptionType() {
		return decryptionType;
	}

	/**
	 * @param decryptionType 设置decryptionType
	 */
	public void setDecryptionType(Integer decryptionType) {
		this.decryptionType = decryptionType;
	}

	/**
	 * @return 返回decryptionLimit
	 */
	public Integer getDecryptionLimit() {
		return decryptionLimit;
	}

	/**
	 * @param decryptionLimit 设置decryptionLimit
	 */
	public void setDecryptionLimit(Integer decryptionLimit) {
		this.decryptionLimit = decryptionLimit;
	}

	/**
	 * @return 返回limitMeasure
	 */
	public Integer getLimitMeasure() {
		return limitMeasure;
	}

	/**
	 * @param limitMeasure 设置limitMeasure
	 */
	public void setLimitMeasure(Integer limitMeasure) {
		this.limitMeasure = limitMeasure;
	}

	/**
	 * @return 返回decryptionStart
	 */
	public Date getDecryptionStart() {
		return decryptionStart;
	}

	/**
	 * @param decryptionStart 设置decryptionStart
	 */
	public void setDecryptionStart(Date decryptionStart) {
		this.decryptionStart = decryptionStart;
	}

	/**
	 * @return 返回decryptionEnd
	 */
	public Date getDecryptionEnd() {
		return decryptionEnd;
	}

	/**
	 * @param decryptionEnd 设置decryptionEnd
	 */
	public void setDecryptionEnd(Date decryptionEnd) {
		this.decryptionEnd = decryptionEnd;
	}

	/**
	 * @return 返回decryptionReason
	 */
	public String getDecryptionReason() {
		return decryptionReason;
	}

	/**
	 * @param decryptionReason 设置decryptionReason
	 */
	public void setDecryptionReason(String decryptionReason) {
		this.decryptionReason = decryptionReason;
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
