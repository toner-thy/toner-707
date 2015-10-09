package com.cdthgk.bmp.pucha.seccomputer.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.permission.user.domain.User;
import com.thgk.platform.core.BaseDomain;

/**
 * The persistent class for the bm_sec_computer_3i1 database table.
 *
 */
@Entity
@Table(name = "bm_sec_computer_3i1")
public class SecComputer3i1 extends BaseDomain {
	/**
	 *
	 */
	private static final long serialVersionUID = 1175636144604825270L;

	private String id;

	private String departmentName;

	private String diskSn;

	private boolean install3i1;

	private Date reportDate;

	private Organ reportOrgan;

	private User reportUser;

	private int secrecyLevel;

	private String useDutyPerson;

	private Department createDepartment;

	private Organ createOrgan;

	private Integer year;

	private Integer sort = 1;

	public SecComputer3i1() {
	}

	@Id
	@GeneratedValue(generator="assignedUUIDGenerator")
   	@GenericGenerator(name="assignedUUIDGenerator", strategy="com.cdthgk.persistece.hibernate.idgen.AssignedUUIDGenerator")
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATE_DEPARTMENT")
	public Department getCreateDepartment() {
		return this.createDepartment;
	}

	public void setCreateDepartment(Department createDepartment) {
		this.createDepartment = createDepartment;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "CREATE_ORGAN")
	public Organ getCreateOrgan() {
		return this.createOrgan;
	}

	public void setCreateOrgan(Organ createOrgan) {
		this.createOrgan = createOrgan;
	}

	@Column(name = "DEPARTMENT_NAME")
	public String getDepartmentName() {
		return this.departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	@Column(name = "DISK_SN")
	public String getDiskSn() {
		return this.diskSn;
	}

	public void setDiskSn(String diskSn) {
		this.diskSn = diskSn;
	}

	@Column(name = "INSTALL_3I1")
	public boolean getInstall3i1() {
		return this.install3i1;
	}

	public void setInstall3i1(boolean install3i1) {
		this.install3i1 = install3i1;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "REPORT_DATE")
	public Date getReportDate() {
		return this.reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REPORT_ORGAN")
	public Organ getReportOrgan() {
		return this.reportOrgan;
	}

	public void setReportOrgan(Organ reportOrgan) {
		this.reportOrgan = reportOrgan;
	}

	@Column(name = "SECRECY_LEVEL")
	public int getSecrecyLevel() {
		return this.secrecyLevel;
	}

	public void setSecrecyLevel(int secrecyLevel) {
		this.secrecyLevel = secrecyLevel;
	}

	@Column(name = "USE_DUTY_PERSON")
	public String getUseDutyPerson() {
		return this.useDutyPerson;
	}

	public void setUseDutyPerson(String useDutyPerson) {
		this.useDutyPerson = useDutyPerson;
	}

	/**
	 * 返回reportUser
	 * @return reportUser
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REPORT_USER")
	public User getReportUser() {
		return reportUser;
	}

	/**
	 * 设置reportUser
	 * @param reportUser reportUser
	 */
	public void setReportUser(User reportUser) {
		this.reportUser = reportUser;
	}

	/**
	 * 返回year
	 * @return year
	 */
	@Column(name = "YEAR")
	public Integer getYear() {
		return year;
	}

	/**
	 * 设置year
	 * @param year year
	 */
	public void setYear(Integer year) {
		this.year = year;
	}

	/**
	 * 返回sort
	 * @return sort
	 */
	@Column(name = "SORT")
	public Integer getSort() {
		return sort;
	}

	/**
	 * 设置sort
	 * @param sort sort
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
}