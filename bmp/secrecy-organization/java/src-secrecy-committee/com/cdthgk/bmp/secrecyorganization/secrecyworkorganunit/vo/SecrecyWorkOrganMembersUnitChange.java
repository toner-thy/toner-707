package com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.vo;

import java.util.Date;

import com.cdthgk.platform.permission.user.domain.User;

/**
 * BmSecrecyWorkOrganMembersUnitChange
 */
public class SecrecyWorkOrganMembersUnitChange implements
		java.io.Serializable {

	//保密工作机构 办公室成员变更id
	private String secrecyWorkOrganMembersUnitChangeId;

	//保密工作机构 办公室成员
	private SecrecyWorkOrganMemberUnit secrecyWorkOrganMembersId;

	//变更类型
	private Integer changeType;

	//变更时间
	private Date changeDate;

	//变更说明
	private String changeReason;

	//创建人
	private User createPerson;

	//创建时间
	private Date createDate;

	/**
	 * 默认构造函数
	 */
	public SecrecyWorkOrganMembersUnitChange() {
	}

	/**
	 * 返回secrecyWorkOrganMembersUnitChangeId
	 * @return secrecyWorkOrganMembersUnitChangeId
	 */
	public String getSecrecyWorkOrganMembersUnitChangeId() {
		return this.secrecyWorkOrganMembersUnitChangeId;
	}

	/**
	 * 设置secrecyWorkOrganMembersUnitChangeId
	 * @param secrecyWorkOrganMembersUnitChangeId secrecyWorkOrganMembersUnitChangeId
	 */
	public void setSecrecyWorkOrganMembersUnitChangeId(
			String secrecyWorkOrganMembersUnitChangeId) {
		this.secrecyWorkOrganMembersUnitChangeId = secrecyWorkOrganMembersUnitChangeId;
	}

	/**
	 * @return the secrecyWorkOrganMembersId
	 */
	public SecrecyWorkOrganMemberUnit getSecrecyWorkOrganMembersId() {
		return secrecyWorkOrganMembersId;
	}

	/**
	 * @param secrecyWorkOrganMembersId the secrecyWorkOrganMembersId to set
	 */
	public void setSecrecyWorkOrganMembersId(
			SecrecyWorkOrganMemberUnit secrecyWorkOrganMembersId) {
		this.secrecyWorkOrganMembersId = secrecyWorkOrganMembersId;
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
