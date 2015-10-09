package com.cdthgk.bmp.secrecynet.secrecyfoundtrain.vo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.permission.user.domain.User;
import com.thgk.platform.core.BaseDomain;

public class SecrecyFoundTrain extends BaseDomain implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private Integer year;
	private User reportUser;
	private Date reportDate;
	private Department reportDepartment;
	private Organ reportOrgan;
	private Integer secrecyCadreNum;
	private Integer doctorNum;
	private Integer masterNum;
	private Integer undergraduateNum;
	private Integer juniorNum;
	private Integer signalCommuNum;
	private Integer otherNum;
	private Integer lessThanNum;
	private Integer greateThanNum;
	private String problemAdvice;
	private Department createDepartment;
	private Organ createOrgan;
	private Set<SecrecyFoundTrainContent> secrecyFoundTrainContentSet = new HashSet<SecrecyFoundTrainContent>(0);
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the year
	 */
	public Integer getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(Integer year) {
		this.year = year;
	}
	/**
	 * @return the reportUser
	 */
	public User getReportUser() {
		return reportUser;
	}
	/**
	 * @param reportUser the reportUser to set
	 */
	public void setReportUser(User reportUser) {
		this.reportUser = reportUser;
	}
	/**
	 * @return the reportDate
	 */
	public Date getReportDate() {
		return reportDate;
	}
	/**
	 * @param reportDate the reportDate to set
	 */
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}
	/**
	 * @return the reportDepartment
	 */
	public Department getReportDepartment() {
		return reportDepartment;
	}
	/**
	 * @param reportDepartment the reportDepartment to set
	 */
	public void setReportDepartment(Department reportDepartment) {
		this.reportDepartment = reportDepartment;
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
	 * @return the doctorNum
	 */
	public Integer getDoctorNum() {
		return doctorNum;
	}
	/**
	 * @param doctorNum the doctorNum to set
	 */
	public void setDoctorNum(Integer doctorNum) {
		this.doctorNum = doctorNum;
	}
	/**
	 * @return the masterNum
	 */
	public Integer getMasterNum() {
		return masterNum;
	}
	/**
	 * @param masterNum the masterNum to set
	 */
	public void setMasterNum(Integer masterNum) {
		this.masterNum = masterNum;
	}
	/**
	 * @return the undergraduateNum
	 */
	public Integer getUndergraduateNum() {
		return undergraduateNum;
	}
	/**
	 * @param undergraduateNum the undergraduateNum to set
	 */
	public void setUndergraduateNum(Integer undergraduateNum) {
		this.undergraduateNum = undergraduateNum;
	}
	/**
	 * @return the juniorNum
	 */
	public Integer getJuniorNum() {
		return juniorNum;
	}
	/**
	 * @param juniorNum the juniorNum to set
	 */
	public void setJuniorNum(Integer juniorNum) {
		this.juniorNum = juniorNum;
	}
	/**
	 * @return the signalCommuNum
	 */
	public Integer getSignalCommuNum() {
		return signalCommuNum;
	}
	/**
	 * @param signalCommuNum the signalCommuNum to set
	 */
	public void setSignalCommuNum(Integer signalCommuNum) {
		this.signalCommuNum = signalCommuNum;
	}
	/**
	 * @return the otherNum
	 */
	public Integer getOtherNum() {
		return otherNum;
	}
	/**
	 * @param otherNum the otherNum to set
	 */
	public void setOtherNum(Integer otherNum) {
		this.otherNum = otherNum;
	}
	/**
	 * @return the lessThanNum
	 */
	public Integer getLessThanNum() {
		return lessThanNum;
	}
	/**
	 * @param lessThanNum the lessThanNum to set
	 */
	public void setLessThanNum(Integer lessThanNum) {
		this.lessThanNum = lessThanNum;
	}
	/**
	 * @return the greateThanNum
	 */
	public Integer getGreateThanNum() {
		return greateThanNum;
	}
	/**
	 * @param greateThanNum the greateThanNum to set
	 */
	public void setGreateThanNum(Integer greateThanNum) {
		this.greateThanNum = greateThanNum;
	}
	/**
	 * @return the problemAdvice
	 */
	public String getProblemAdvice() {
		return problemAdvice;
	}
	/**
	 * @param problemAdvice the problemAdvice to set
	 */
	public void setProblemAdvice(String problemAdvice) {
		this.problemAdvice = problemAdvice;
	}
	/**
	 * @return the createDepartment
	 */
	public Department getCreateDepartment() {
		return createDepartment;
	}
	/**
	 * @param createDepartment the createDepartment to set
	 */
	public void setCreateDepartment(Department createDepartment) {
		this.createDepartment = createDepartment;
	}
	/**
	 * @return the createOrgan
	 */
	public Organ getCreateOrgan() {
		return createOrgan;
	}
	/**
	 * @param createOrgan the createOrgan to set
	 */
	public void setCreateOrgan(Organ createOrgan) {
		this.createOrgan = createOrgan;
	}
	/**
	 * @return the secrecyFoundTrainContentSet
	 */
	public Set<SecrecyFoundTrainContent> getSecrecyFoundTrainContentSet() {
		return secrecyFoundTrainContentSet;
	}
	/**
	 * @param secrecyFoundTrainContentSet the secrecyFoundTrainContentSet to set
	 */
	public void setSecrecyFoundTrainContentSet(
			Set<SecrecyFoundTrainContent> secrecyFoundTrainContentSet) {
		this.secrecyFoundTrainContentSet = secrecyFoundTrainContentSet;
	}
	/**
	 * @return the secrecyCadreNum
	 */
	public Integer getSecrecyCadreNum() {
		return secrecyCadreNum;
	}
	/**
	 * @param secrecyCadreNum the secrecyCadreNum to set
	 */
	public void setSecrecyCadreNum(Integer secrecyCadreNum) {
		this.secrecyCadreNum = secrecyCadreNum;
	}

}