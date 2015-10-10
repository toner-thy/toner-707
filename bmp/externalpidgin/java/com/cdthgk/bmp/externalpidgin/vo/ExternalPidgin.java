package com.cdthgk.bmp.externalpidgin.vo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.permission.user.domain.User;
import com.thgk.platform.core.BaseDomain;


/**
 * ExternalPidgin entity.
 *
 * @author MyEclipse Persistence Tools
 */
public class ExternalPidgin extends BaseDomain implements java.io.Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -8673605958030778162L;
	// Fields
	private String externalPidginId;
	private String externalPidginNo;
	private String externalPidginType;
	private Integer secrecyLevel;
	private String eternalPidginName;
	private Date startDate;
	private Date endDate;
	private String place;
	private String mainOrgan;
	private String aidanceOrgan;
	private String keyWord;
	private String undertaker;
	private String secrecyDutier;
	private String dutierHeadship;
	private String content;
	private String plan;
	private Department department;
	private Organ organ;

	private Date inceptTime;
	private Organ reportOrgan;
	private Integer reportOrganState;
	private Date reportOrganTime;
	private Integer reportState;
	private Date reportTime;
	private Integer transmitState;

	private Set<Organ> receiveOrgans = new HashSet<Organ>();

	// Constructors

	/** default constructor */
	public ExternalPidgin() {
	}

	// Property accessors

	public String getExternalPidginId() {
		return this.externalPidginId;
	}

	public void setExternalPidginId(String externalPidginId) {
		this.externalPidginId = externalPidginId;
	}

	public String getExternalPidginNo() {
		return this.externalPidginNo;
	}

	public void setExternalPidginNo(String externalPidginNo) {
		this.externalPidginNo = externalPidginNo;
	}

	public String getExternalPidginType() {
		return this.externalPidginType;
	}

	public void setExternalPidginType(String externalPidginType) {
		this.externalPidginType = externalPidginType;
	}

	public Integer getSecrecyLevel() {
		return this.secrecyLevel;
	}

	public void setSecrecyLevel(Integer secrecyLevel) {
		this.secrecyLevel = secrecyLevel;
	}

	public String getEternalPidginName() {
		return this.eternalPidginName;
	}

	public void setEternalPidginName(String eternalPidginName) {
		this.eternalPidginName = eternalPidginName;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getPlace() {
		return this.place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getMainOrgan() {
		return this.mainOrgan;
	}

	public void setMainOrgan(String mainOrgan) {
		this.mainOrgan = mainOrgan;
	}

	public String getAidanceOrgan() {
		return this.aidanceOrgan;
	}

	public void setAidanceOrgan(String aidanceOrgan) {
		this.aidanceOrgan = aidanceOrgan;
	}

	public String getKeyWord() {
		return this.keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getUndertaker() {
		return this.undertaker;
	}

	public void setUndertaker(String undertaker) {
		this.undertaker = undertaker;
	}

	public String getSecrecyDutier() {
		return this.secrecyDutier;
	}

	public void setSecrecyDutier(String secrecyDutier) {
		this.secrecyDutier = secrecyDutier;
	}

	public String getDutierHeadship() {
		return this.dutierHeadship;
	}

	public void setDutierHeadship(String dutierHeadship) {
		this.dutierHeadship = dutierHeadship;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Organ getOrgan() {
		return organ;
	}

	public void setOrgan(Organ organ) {
		this.organ = organ;
	}

	public User getCreatePerson() {
		return createPerson;
	}

	public void setCreatePerson(User createPerson) {
		this.createPerson = createPerson;
	}

	public User getModifyPerson() {
		return modifyPerson;
	}

	public void setModifyPerson(User modifyPerson) {
		this.modifyPerson = modifyPerson;
	}

	/**
	 * @return the reportOrgan
	 */
	public Organ getReportOrgan() {
		return reportOrgan;
	}

	/**
	 * @param reportOrgan the reportOrgan to set
	 */
	public void setReportOrgan(Organ reportOrgan) {
		this.reportOrgan = reportOrgan;
	}

	/**
	 * @return the reportOrganState
	 */
	public Integer getReportOrganState() {
		return reportOrganState;
	}

	/**
	 * @param reportOrganState the reportOrganState to set
	 */
	public void setReportOrganState(Integer reportOrganState) {
		this.reportOrganState = reportOrganState;
	}

	/**
	 * @return the reportOrganTime
	 */
	public Date getReportOrganTime() {
		return reportOrganTime;
	}

	/**
	 * @param reportOrganTime the reportOrganTime to set
	 */
	public void setReportOrganTime(Date reportOrganTime) {
		this.reportOrganTime = reportOrganTime;
	}

	/**
	 * @return the reportState
	 */
	public Integer getReportState() {
		return reportState;
	}

	/**
	 * @param reportState the reportState to set
	 */
	public void setReportState(Integer reportState) {
		this.reportState = reportState;
	}

	/**
	 * @return the reportTime
	 */
	public Date getReportTime() {
		return reportTime;
	}

	/**
	 * @param reportTime the reportTime to set
	 */
	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}

	/**
	 * @return the transmitState
	 */
	public Integer getTransmitState() {
		return transmitState;
	}

	/**
	 * @param transmitState the transmitState to set
	 */
	public void setTransmitState(Integer transmitState) {
		this.transmitState = transmitState;
	}

	/**
	 * @return the receiveOrgans
	 */
	public Set<Organ> getReceiveOrgans() {
		return receiveOrgans;
	}

	/**
	 * @param receiveOrgans the receiveOrgans to set
	 */
	public void setReceiveOrgans(Set<Organ> receiveOrgans) {
		this.receiveOrgans = receiveOrgans;
	}

	/**
	 * @return the inceptTime
	 */
	public Date getInceptTime() {
		return inceptTime;
	}

	/**
	 * @param inceptTime the inceptTime to set
	 */
	public void setInceptTime(Date inceptTime) {
		this.inceptTime = inceptTime;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}
}