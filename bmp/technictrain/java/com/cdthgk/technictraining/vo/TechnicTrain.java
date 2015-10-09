package com.cdthgk.technictraining.vo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.permission.user.domain.User;

/**
 * TechnicTrain entity.
 *
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("all")
public class TechnicTrain implements java.io.Serializable {

	// Fields

	/**
	 *
	 */
	private static final long serialVersionUID = 1623826480977461546L;
	private String technicTrainingId;
	private String trainingTarget;
	private String trainingContent;
	private String trainingTitle;
	private Integer personNumber;
	private Date trainingDate;
	private String trainingPlace;
	private String remark;

	private Organ organ;
	private Department department;
	private User createPerson;
	private Date createTime;
	private User modifyPerson;
	private Date modifyTime;

	private Set notPresenceOrgans = new HashSet(0);
	private Set presenceOrgans = new HashSet(0);

	public String getTechnicTrainingId() {
		return technicTrainingId;
	}

	public void setTechnicTrainingId(String technicTrainingId) {
		this.technicTrainingId = technicTrainingId;
	}

	public String getTrainingTarget() {
		return trainingTarget;
	}

	public void setTrainingTarget(String trainingTarget) {
		this.trainingTarget = trainingTarget;
	}

	public String getTrainingContent() {
		return trainingContent;
	}

	public void setTrainingContent(String trainingContent) {
		this.trainingContent = trainingContent;
	}

	public String getTrainingTitle() {
		return trainingTitle;
	}

	public void setTrainingTitle(String trainingTitle) {
		this.trainingTitle = trainingTitle;
	}

	public Integer getPersonNumber() {
		return personNumber;
	}

	public void setPersonNumber(Integer personNumber) {
		this.personNumber = personNumber;
	}

	public Date getTrainingDate() {
		return trainingDate;
	}

	public void setTrainingDate(Date trainingDate) {
		this.trainingDate = trainingDate;
	}

	public String getTrainingPlace() {
		return trainingPlace;
	}

	public void setTrainingPlace(String trainingPlace) {
		this.trainingPlace = trainingPlace;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Organ getOrgan() {
		return organ;
	}

	public void setOrgan(Organ organ) {
		this.organ = organ;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public User getCreatePerson() {
		return createPerson;
	}

	public void setCreatePerson(User createPerson) {
		this.createPerson = createPerson;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public User getModifyPerson() {
		return modifyPerson;
	}

	public void setModifyPerson(User modifyPerson) {
		this.modifyPerson = modifyPerson;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Set getNotPresenceOrgans() {
		return notPresenceOrgans;
	}

	public void setNotPresenceOrgans(Set notPresenceOrgans) {
		this.notPresenceOrgans = notPresenceOrgans;
	}

	public Set getPresenceOrgans() {
		return presenceOrgans;
	}

	public void setPresenceOrgans(Set presenceOrgans) {
		this.presenceOrgans = presenceOrgans;
	}

}