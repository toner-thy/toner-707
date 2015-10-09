package com.cdthgk.bmp.externalpidgin.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * @description 涉密涉外活动Dto.
 * @author yangc 2010 4 6 12:34:56
 * @modify 陈文聪 2010 8 19 02:19:53 修改注释格式
 */
@SuppressWarnings("all")
@XmlRootElement(name="externalPidginDto",namespace="com.thgk.bmp.externalpidgin.dto")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ExternalPidginDto {

	// TODO
	private static final long serialVersionUID = 1188685550656883354L;

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
	private String department;
	private String organ;
	private String createPerson;
	private Date createTime;
	private String modifyPerson;
	private Date modifyTime;

	private String reportOrgan;
	private Integer reportOrganState;
	private Date reportOrganTime;
	private Integer reportState;
	private Date reportTime;
	private Integer transmitState;

	private String startTime;
	private String endTime;

	// private ExternalPidgin externalPidgin;
	private String organName;


	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the externalPidginId
	 */
	public String getExternalPidginId() {
		return externalPidginId;
	}

	/**
	 * @param externalPidginId
	 *            the externalPidginId to set
	 */
	public void setExternalPidginId(String externalPidginId) {
		this.externalPidginId = externalPidginId;
	}

	/**
	 * @return the externalPidginNo
	 */
	public String getExternalPidginNo() {
		return externalPidginNo;
	}

	/**
	 * @param externalPidginNo
	 *            the externalPidginNo to set
	 */
	public void setExternalPidginNo(String externalPidginNo) {
		this.externalPidginNo = externalPidginNo;
	}

	/**
	 * @return the externalPidginType
	 */
	public String getExternalPidginType() {
		return externalPidginType;
	}

	/**
	 * @param externalPidginType
	 *            the externalPidginType to set
	 */
	public void setExternalPidginType(String externalPidginType) {
		this.externalPidginType = externalPidginType;
	}

	/**
	 * @return the secrecyLevel
	 */
	public Integer getSecrecyLevel() {
		return secrecyLevel;
	}

	/**
	 * @param secrecyLevel
	 *            the secrecyLevel to set
	 */
	public void setSecrecyLevel(Integer secrecyLevel) {
		this.secrecyLevel = secrecyLevel;
	}

	/**
	 * @return the eternalPidginName
	 */
	public String getEternalPidginName() {
		return eternalPidginName;
	}

	/**
	 * @param eternalPidginName
	 *            the eternalPidginName to set
	 */
	public void setEternalPidginName(String eternalPidginName) {
		this.eternalPidginName = eternalPidginName;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the place
	 */
	public String getPlace() {
		return place;
	}

	/**
	 * @param place
	 *            the place to set
	 */
	public void setPlace(String place) {
		this.place = place;
	}

	/**
	 * @return the mainOrgan
	 */
	public String getMainOrgan() {
		return mainOrgan;
	}

	/**
	 * @param mainOrgan
	 *            the mainOrgan to set
	 */
	public void setMainOrgan(String mainOrgan) {
		this.mainOrgan = mainOrgan;
	}

	/**
	 * @return the aidanceOrgan
	 */
	public String getAidanceOrgan() {
		return aidanceOrgan;
	}

	/**
	 * @param aidanceOrgan
	 *            the aidanceOrgan to set
	 */
	public void setAidanceOrgan(String aidanceOrgan) {
		this.aidanceOrgan = aidanceOrgan;
	}

	/**
	 * @return the keyWord
	 */
	public String getKeyWord() {
		return keyWord;
	}

	/**
	 * @param keyWord
	 *            the keyWord to set
	 */
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	/**
	 * @return the undertaker
	 */
	public String getUndertaker() {
		return undertaker;
	}

	/**
	 * @param undertaker
	 *            the undertaker to set
	 */
	public void setUndertaker(String undertaker) {
		this.undertaker = undertaker;
	}

	/**
	 * @return the secrecyDutier
	 */
	public String getSecrecyDutier() {
		return secrecyDutier;
	}

	/**
	 * @param secrecyDutier
	 *            the secrecyDutier to set
	 */
	public void setSecrecyDutier(String secrecyDutier) {
		this.secrecyDutier = secrecyDutier;
	}

	/**
	 * @return the dutierHeadship
	 */
	public String getDutierHeadship() {
		return dutierHeadship;
	}

	/**
	 * @param dutierHeadship
	 *            the dutierHeadship to set
	 */
	public void setDutierHeadship(String dutierHeadship) {
		this.dutierHeadship = dutierHeadship;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * @param department
	 *            the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * @return the organ
	 */
	public String getOrgan() {
		return organ;
	}

	/**
	 * @param organ
	 *            the organ to set
	 */
	public void setOrgan(String organ) {
		this.organ = organ;
	}

	/**
	 * @return the createPerson
	 */
	public String getCreatePerson() {
		return createPerson;
	}

	/**
	 * @param createPerson
	 *            the createPerson to set
	 */
	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the modifyPerson
	 */
	public String getModifyPerson() {
		return modifyPerson;
	}

	/**
	 * @param modifyPerson
	 *            the modifyPerson to set
	 */
	public void setModifyPerson(String modifyPerson) {
		this.modifyPerson = modifyPerson;
	}

	/**
	 * @return the modifyTime
	 */
	public Date getModifyTime() {
		return modifyTime;
	}

	/**
	 * @param modifyTime
	 *            the modifyTime to set
	 */
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	/**
	 * @return the reportOrgan
	 */
	public String getReportOrgan() {
		return reportOrgan;
	}

	/**
	 * @param reportOrgan
	 *            the reportOrgan to set
	 */
	public void setReportOrgan(String reportOrgan) {
		this.reportOrgan = reportOrgan;
	}

	/**
	 * @return the reportOrganState
	 */
	public Integer getReportOrganState() {
		return reportOrganState;
	}

	/**
	 * @param reportOrganState
	 *            the reportOrganState to set
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
	 * @param reportOrganTime
	 *            the reportOrganTime to set
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
	 * @param reportState
	 *            the reportState to set
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
	 * @param reportTime
	 *            the reportTime to set
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
	 * @param transmitState
	 *            the transmitState to set
	 */
	public void setTransmitState(Integer transmitState) {
		this.transmitState = transmitState;
	}

	/**
	 * @return the organName
	 */
	public String getOrganName() {
		return organName;
	}


	/**
	 * @param organName the organName to set
	 */
	public void setOrganName(String organName) {
		this.organName = organName;
	}

}
