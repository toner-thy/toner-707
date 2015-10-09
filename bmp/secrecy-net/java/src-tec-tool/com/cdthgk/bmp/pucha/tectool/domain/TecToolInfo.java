package com.cdthgk.bmp.pucha.tectool.domain;

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
 * The persistent class for the bm_tec_tool_info database table.
 *
 */
@Entity
@Table(name="bm_tec_tool_info")
public class TecToolInfo extends BaseDomain {
	private static final long serialVersionUID = 1L;
	private String tecToolInfoId;
	private Department createDepartment;
	private Organ createOrgan;
	private Integer impOrgCheckNum;
	private Integer internetConnectPlatNum;
	private Integer orgWebsiteCheckNum;
	private Date reportDate;
	private Organ reportOrgan;
	private User reportUser;
	private boolean toolCockhorse;
	private Integer toolCockhorseNum;
	private boolean toolComputer;
	private Integer toolComputerNum;
	private boolean toolNet;
	private Integer toolNetNum;
	private boolean toolNetTest;
	private Integer toolNetTestNum;
	private boolean toolOther;
	private String toolOtherDesc;
	private boolean toolSignal;
	private Integer toolSignalNum;
	private Integer year;

    public TecToolInfo() {
    }


    @Id
    @GeneratedValue(generator="assignedUUIDGenerator")
   	@GenericGenerator(name="assignedUUIDGenerator", strategy="com.cdthgk.persistece.hibernate.idgen.AssignedUUIDGenerator")
	@Column(name="TEC_TOOL_INFO_ID")
	public String getTecToolInfoId() {
		return this.tecToolInfoId;
	}

	public void setTecToolInfoId(String tecToolInfoId) {
		this.tecToolInfoId = tecToolInfoId;
	}

	@Column(name="IMP_ORG_CHECK_NUM")
	public Integer getImpOrgCheckNum() {
		return this.impOrgCheckNum;
	}

	public void setImpOrgCheckNum(Integer impOrgCheckNum) {
		this.impOrgCheckNum = impOrgCheckNum;
	}


	@Column(name="INTERNET_CONNECT_PLAT_NUM")
	public Integer getInternetConnectPlatNum() {
		return this.internetConnectPlatNum;
	}

	public void setInternetConnectPlatNum(Integer internetConnectPlatNum) {
		this.internetConnectPlatNum = internetConnectPlatNum;
	}

	@Column(name="ORG_WEBSITE_CHECK_NUM")
	public Integer getOrgWebsiteCheckNum() {
		return this.orgWebsiteCheckNum;
	}

	public void setOrgWebsiteCheckNum(Integer orgWebsiteCheckNum) {
		this.orgWebsiteCheckNum = orgWebsiteCheckNum;
	}


    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="REPORT_DATE")
	public Date getReportDate() {
		return this.reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}


	@Column(name="TOOL_COCKHORSE")
	public boolean getToolCockhorse() {
		return this.toolCockhorse;
	}

	public void setToolCockhorse(boolean toolCockhorse) {
		this.toolCockhorse = toolCockhorse;
	}


	@Column(name="TOOL_COCKHORSE_NUM")
	public Integer getToolCockhorseNum() {
		return this.toolCockhorseNum;
	}

	public void setToolCockhorseNum(Integer toolCockhorseNum) {
		this.toolCockhorseNum = toolCockhorseNum;
	}


	@Column(name="TOOL_COMPUTER")
	public boolean getToolComputer() {
		return this.toolComputer;
	}

	public void setToolComputer(boolean toolComputer) {
		this.toolComputer = toolComputer;
	}


	@Column(name="TOOL_COMPUTER_NUM")
	public Integer getToolComputerNum() {
		return this.toolComputerNum;
	}

	public void setToolComputerNum(Integer toolComputerNum) {
		this.toolComputerNum = toolComputerNum;
	}


	@Column(name="TOOL_NET")
	public boolean getToolNet() {
		return this.toolNet;
	}

	public void setToolNet(boolean toolNet) {
		this.toolNet = toolNet;
	}


	@Column(name="TOOL_NET_NUM")
	public Integer getToolNetNum() {
		return this.toolNetNum;
	}

	public void setToolNetNum(Integer toolNetNum) {
		this.toolNetNum = toolNetNum;
	}


	@Column(name="TOOL_NET_TEST")
	public boolean getToolNetTest() {
		return this.toolNetTest;
	}

	public void setToolNetTest(boolean toolNetTest) {
		this.toolNetTest = toolNetTest;
	}


	@Column(name="TOOL_NET_TEST_NUM")
	public Integer getToolNetTestNum() {
		return this.toolNetTestNum;
	}

	public void setToolNetTestNum(Integer toolNetTestNum) {
		this.toolNetTestNum = toolNetTestNum;
	}


	@Column(name="TOOL_OTHER")
	public boolean getToolOther() {
		return this.toolOther;
	}

	public void setToolOther(boolean toolOther) {
		this.toolOther = toolOther;
	}


	@Column(name="TOOL_OTHER_DESC")
	public String getToolOtherDesc() {
		return this.toolOtherDesc;
	}

	public void setToolOtherDesc(String toolOtherDesc) {
		this.toolOtherDesc = toolOtherDesc;
	}


	@Column(name="TOOL_SIGNAL")
	public boolean getToolSignal() {
		return this.toolSignal;
	}

	public void setToolSignal(boolean toolSignal) {
		this.toolSignal = toolSignal;
	}


	@Column(name="TOOL_SIGNAL_NUM")
	public Integer getToolSignalNum() {
		return this.toolSignalNum;
	}

	public void setToolSignalNum(Integer toolSignalNum) {
		this.toolSignalNum = toolSignalNum;
	}

	@Column(name = "YEAR")
	public Integer getYear() {
		return this.year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	/**
	 * 返回createDepartment
	 * @return createDepartment
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATE_DEPARTMENT")
	public Department getCreateDepartment() {
		return createDepartment;
	}
	/**
	 * 设置createDepartment
	 * @param createDepartment createDepartment
	 */
	public void setCreateDepartment(Department createDepartment) {
		this.createDepartment = createDepartment;
	}
	/**
	 * 返回createOrgan
	 * @return createOrgan
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "CREATE_ORGAN")
	public Organ getCreateOrgan() {
		return createOrgan;
	}
	/**
	 * 设置createOrgan
	 * @param createOrgan createOrgan
	 */
	public void setCreateOrgan(Organ createOrgan) {
		this.createOrgan = createOrgan;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REPORT_ORGAN")
	public Organ getReportOrgan() {
		return this.reportOrgan;
	}

	public void setReportOrgan(Organ reportOrgan) {
		this.reportOrgan = reportOrgan;
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

}