package com.cdthgk.bmp.secrecynet.secrecytechnologytrain.vo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.permission.user.domain.User;
import com.thgk.platform.core.BaseDomain;

public class SecrecyTechnologyTrain extends BaseDomain implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private Integer year;
	private Organ reportOrgan;
	private Department reportDepartment;
	private User reportUser;
	private Date reportDate;
	private String problemAdvice;
	private Department createDepartment;
	private Organ createOrgan;
	private Set<SecrecyTechnologyTrainContent> secrecyTechnologyTrainContentSet = new HashSet<SecrecyTechnologyTrainContent>(0);
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
	 * @return the secrecyTechnologyTrainContentSet
	 */
	public Set<SecrecyTechnologyTrainContent> getSecrecyTechnologyTrainContentSet() {
		return secrecyTechnologyTrainContentSet;
	}
	/**
	 * @param secrecyTechnologyTrainContentSet the secrecyTechnologyTrainContentSet to set
	 */
	public void setSecrecyTechnologyTrainContentSet(
			Set<SecrecyTechnologyTrainContent> secrecyTechnologyTrainContentSet) {
		this.secrecyTechnologyTrainContentSet = secrecyTechnologyTrainContentSet;
	}


}