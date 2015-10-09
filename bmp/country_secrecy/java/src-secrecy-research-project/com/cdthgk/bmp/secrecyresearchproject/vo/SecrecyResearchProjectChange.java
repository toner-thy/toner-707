package com.cdthgk.bmp.secrecyresearchproject.vo;
// default package
// Generated 2013-7-15 14:28:32 ----- 3.4.0.CR1

import java.util.Date;

import com.cdthgk.platform.permission.user.domain.User;

/**
 * SecrecyResearchprojectChange  涉密科研项目密级变更
 */
public class SecrecyResearchProjectChange implements java.io.Serializable {

	private static final long serialVersionUID = -3312877463997630749L;

	private String changeId;//涉密科研项目密级变更
	private SecrecyResearchProject secrecyResearchProject; //涉密科研项目
	private Integer beforeLevel;//变更前密级
	private Integer afterLevel;   //变更后密级
	private Integer changeTimeState;//保密期限变更
	private Date changeDate;//变更日期
	private String changeReason; //变更原因

	private User createPerson;
	private Date createDate;

	/**
	 * 默认构函数
	 */
	public SecrecyResearchProjectChange() {
	}

	public String getChangeId() {
		return changeId;
	}



	public void setChangeId(String changeId) {
		this.changeId = changeId;
	}

	public SecrecyResearchProject getSecrecyResearchProject() {
		return secrecyResearchProject;
	}

	public void setSecrecyResearchProject(
			SecrecyResearchProject secrecyResearchProject) {
		this.secrecyResearchProject = secrecyResearchProject;
	}

	public Integer getBeforeLevel() {
		return beforeLevel;
	}

	public void setBeforeLevel(Integer beforeLevel) {
		this.beforeLevel = beforeLevel;
	}

	public Integer getAfterLevel() {
		return afterLevel;
	}

	public void setAfterLevel(Integer afterLevel) {
		this.afterLevel = afterLevel;
	}

	public Integer getChangeTimeState() {
		return changeTimeState;
	}

	public void setChangeTimeState(Integer changeTimeState) {
		this.changeTimeState = changeTimeState;
	}

	public Date getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}

	public String getChangeReason() {
		return changeReason;
	}

	public void setChangeReason(String changeReason) {
		this.changeReason = changeReason;
	}

	public User getCreatePerson() {
		return createPerson;
	}

	public void setCreatePerson(User createPerson) {
		this.createPerson = createPerson;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}



}
