package com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.vo;

import java.util.Date;

import com.cdthgk.platform.permission.user.domain.User;

/**
 * BmPersonGroupRelationChange
 */
public class PersonGroupRelationChange implements java.io.Serializable {

	private String personGroupRelationChangeId;

	private SecrecyWorkOrganRelationMember bmPersonGroupRelationId;

	private Integer changeType;

	private Date changeDate;

	private String changeReason;

	private User createPerson;

	private Date createDate;

	/**
	 * 默认构�?函数
	 */
	public PersonGroupRelationChange() {
	}

	/**
	 * 返回personGroupRelationChangeId
	 * @return personGroupRelationChangeId
	 */
	public String getPersonGroupRelationChangeId() {
		return this.personGroupRelationChangeId;
	}

	/**
	 * 设置personGroupRelationChangeId
	 * @param personGroupRelationChangeId personGroupRelationChangeId
	 */
	public void setPersonGroupRelationChangeId(
			String personGroupRelationChangeId) {
		this.personGroupRelationChangeId = personGroupRelationChangeId;
	}


	/**
	 * @return the bmPersonGroupRelationId
	 */
	public SecrecyWorkOrganRelationMember getBmPersonGroupRelationId() {
		return bmPersonGroupRelationId;
	}

	/**
	 * @param bmPersonGroupRelationId the bmPersonGroupRelationId to set
	 */
	public void setBmPersonGroupRelationId(
			SecrecyWorkOrganRelationMember bmPersonGroupRelationId) {
		this.bmPersonGroupRelationId = bmPersonGroupRelationId;
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
