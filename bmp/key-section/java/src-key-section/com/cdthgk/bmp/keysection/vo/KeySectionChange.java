package com.cdthgk.bmp.keysection.vo;
// Generated 2013-7-13 14:02:01 ----- 3.4.0.CR1

import java.util.Date;

import com.cdthgk.platform.permission.user.domain.User;

/**
 * BmKeySectionChange 要害部门密级变更表
 */
public class KeySectionChange implements java.io.Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -8611994692511738571L;

	private String sectionChangeId;  //要害部门密级变更表 主键id

	private KeySection keySectionId; //外键    要害部门ID

	private Integer beforeLevel; //原密级

	private Integer afterLevel;  //变更后密级

	private Integer changeTimeState; //保密期限变更

	private Date changeDate; //变更时间

	private User approvalPerson;//审批人

	private String changeReason; //变更原因

	private User createPerson; //创建人

	private Date createDate; //创建时间


	/**
	 * 构造函数
	 */
	public KeySectionChange() {
	}

	/**
	 * 返回sectionChangeId
	 * @return sectionChangeId
	 */
	public String getSectionChangeId() {
		return this.sectionChangeId;
	}

	/**
	 * 设置sectionChangeId
	 * @param sectionChangeId sectionChangeId
	 */
	public void setSectionChangeId(String sectionChangeId) {
		this.sectionChangeId = sectionChangeId;
	}

	/**
	 * 返回keySectionId
	 * @return keySectionId
	 */
	public KeySection getKeySectionId() {
		return this.keySectionId;
	}

	/**
	 * 设置keySectionId
	 * @param keySectionId keySectionId
	 */
	public void setKeySectionId(KeySection keySectionId) {
		this.keySectionId = keySectionId;
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
	 * 返回afterLevel
	 * @return afterLevel
	 */
	public Integer getAfterLevel() {
		return this.afterLevel;
	}

	/**
	 * 设置afterLevel
	 * @param afterLevel afterLevel
	 */
	public void setAfterLevel(Integer afterLevel) {
		this.afterLevel = afterLevel;
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

	public User getCreatePerson() {
		return createPerson;
	}

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

	public User getApprovalPerson() {
		return approvalPerson;
	}

	public void setApprovalPerson(User approvalPerson) {
		this.approvalPerson = approvalPerson;
	}

}
