package com.cdthgk.bmp.secrecyresearchproject.vo;
// default package
// Generated 2013-7-15 14:28:32 ----- 3.4.0.CR1

import java.util.Date;

import com.cdthgk.platform.permission.user.domain.User;

/**
 * SecrecyResearchprojectClear  泄密科研项目  密级解除
 */
public class SecrecyResearchProjectClear implements java.io.Serializable {

	private static final long serialVersionUID = 6292942848238291604L;

	private String clearId;//设密科研项目  密级解除
	private SecrecyResearchProject secrecyResearchProject;//设密科研项目
	private Integer clearType;  //解除类型
	private Date clearTime;  //解除时间
	private String reviewPerson;  //审核人
	private String cleanReason;  //解除原因

	private User createPerson;
	private Date createDate;

	/**
	 * 默认构函数
	 */
	public SecrecyResearchProjectClear() {
	}


	public String getClearId() {
		return clearId;
	}


	public void setClearId(String clearId) {
		this.clearId = clearId;
	}


	public SecrecyResearchProject getSecrecyResearchProject() {
		return secrecyResearchProject;
	}

	public void setSecrecyResearchProject(
			SecrecyResearchProject secrecyResearchProject) {
		this.secrecyResearchProject = secrecyResearchProject;
	}

	public Integer getClearType() {
		return clearType;
	}

	public void setClearType(Integer clearType) {
		this.clearType = clearType;
	}

	public Date getClearTime() {
		return clearTime;
	}

	public void setClearTime(Date clearTime) {
		this.clearTime = clearTime;
	}

	public String getReviewPerson() {
		return reviewPerson;
	}

	public void setReviewPerson(String reviewPerson) {
		this.reviewPerson = reviewPerson;
	}

	public String getCleanReason() {
		return cleanReason;
	}

	public void setCleanReason(String cleanReason) {
		this.cleanReason = cleanReason;
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
