package com.cdthgk.bmp.secrecyperson.vo;
// default package
// Generated 2013-7-17 14:21:58 ----- 3.4.0.CR1

import java.util.Date;

import com.cdthgk.platform.permission.user.domain.User;

/**
 * BmSecrecyPersonDecryption
 */
public class SecrecyPersonDecryption implements java.io.Serializable {

	private String secrecyPersonDecryptionId;

	//secrecy_person_id.
	private SecrecyPerson secrecyPersonId;

	//脱密类型
	private Integer decryptionType;

	//脱密期限
	private Integer decryptionLimit;

	//脱密期限单位
	private Integer limitMeasure;

	//脱密时间起
	private Date decryptionStart;

	//脱密时间止
	private Date decryptionEnd;

	//脱密说明
	private String decryptionReason;

	private User createPerson;

	private Date createDate;

	/**
	 * 默认构造函数
	 */
	public SecrecyPersonDecryption() {
	}

	/**
	 * @return the secrecyPersonDecryptionId
	 */
	public String getSecrecyPersonDecryptionId() {
		return secrecyPersonDecryptionId;
	}

	/**
	 * @param secrecyPersonDecryptionId the secrecyPersonDecryptionId to set
	 */
	public void setSecrecyPersonDecryptionId(String secrecyPersonDecryptionId) {
		this.secrecyPersonDecryptionId = secrecyPersonDecryptionId;
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
	 * 返回decryptionType
	 * @return decryptionType
	 */
	public Integer getDecryptionType() {
		return this.decryptionType;
	}

	/**
	 * 设置decryptionType
	 * @param decryptionType decryptionType
	 */
	public void setDecryptionType(Integer decryptionType) {
		this.decryptionType = decryptionType;
	}

	/**
	 * 返回decryptionLimit
	 * @return decryptionLimit
	 */
	public Integer getDecryptionLimit() {
		return this.decryptionLimit;
	}

	/**
	 * 设置decryptionLimit
	 * @param decryptionLimit decryptionLimit
	 */
	public void setDecryptionLimit(Integer decryptionLimit) {
		this.decryptionLimit = decryptionLimit;
	}

	/**
	 * 返回limitMeasure
	 * @return limitMeasure
	 */
	public Integer getLimitMeasure() {
		return this.limitMeasure;
	}

	/**
	 * 设置limitMeasure
	 * @param limitMeasure limitMeasure
	 */
	public void setLimitMeasure(Integer limitMeasure) {
		this.limitMeasure = limitMeasure;
	}

	/**
	 * 返回decryptionStart
	 * @return decryptionStart
	 */
	public Date getDecryptionStart() {
		return this.decryptionStart;
	}

	/**
	 * 设置decryptionStart
	 * @param decryptionStart decryptionStart
	 */
	public void setDecryptionStart(Date decryptionStart) {
		this.decryptionStart = decryptionStart;
	}

	/**
	 * 返回decryptionEnd
	 * @return decryptionEnd
	 */
	public Date getDecryptionEnd() {
		return this.decryptionEnd;
	}

	/**
	 * 设置decryptionEnd
	 * @param decryptionEnd decryptionEnd
	 */
	public void setDecryptionEnd(Date decryptionEnd) {
		this.decryptionEnd = decryptionEnd;
	}

	/**
	 * 返回decryptionReason
	 * @return decryptionReason
	 */
	public String getDecryptionReason() {
		return this.decryptionReason;
	}

	/**
	 * 设置decryptionReason
	 * @param decryptionReason decryptionReason
	 */
	public void setDecryptionReason(String decryptionReason) {
		this.decryptionReason = decryptionReason;
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
