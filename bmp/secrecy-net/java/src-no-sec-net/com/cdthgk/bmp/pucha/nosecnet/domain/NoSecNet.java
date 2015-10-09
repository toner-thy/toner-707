package com.cdthgk.bmp.pucha.nosecnet.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.permission.user.domain.User;
import com.thgk.platform.core.BaseDomain;


/**
 * The persistent class for the bm_no_sec_net database table.
 *
 */
@Entity
@Table(name="bm_no_sec_net")
public class NoSecNet extends BaseDomain {

	private static final long serialVersionUID = 2474400991285538252L;

	private String noSecNetId;
	private Department createDepartment;
	private Organ createOrgan;
	private Integer internetComputerNum;
	private Integer internetNum;
	private boolean internetOa;
	private boolean internetOther;
	private String internetOtherDesc;
	private boolean internetParty;
	private String internetPortIp;
	private Integer internetPortNum;
	private String internetWebsiteDn;
	private boolean internetWebsiteExist;
	private String internetWebsiteIp;
	private Integer internetWebsiteNum;
	private Integer intranetNum;
	private Date reportDate;
	private Organ reportOrgan;
	private User reportUser;
	private boolean serviceMfDx;
	private boolean serviceMfHs;
	private boolean serviceMfOther;
	private String serviceMfOtherDesc;
	private boolean serviceMfWt;
	private Set<NoSecNetIntranet> noSecNetIntranets;
	private Integer year;

    public NoSecNet() {
    }


    @Id
    @GeneratedValue(generator="assignedUUIDGenerator")
   	@GenericGenerator(name="assignedUUIDGenerator", strategy="com.cdthgk.persistece.hibernate.idgen.AssignedUUIDGenerator")
	@Column(name="NO_SEC_NET_ID")
	public String getNoSecNetId() {
		return this.noSecNetId;
	}

	public void setNoSecNetId(String noSecNetId) {
		this.noSecNetId = noSecNetId;
	}

	@Column(name="INTERNET_COMPUTER_NUM")
	public Integer getInternetComputerNum() {
		return this.internetComputerNum;
	}

	public void setInternetComputerNum(Integer internetComputerNum) {
		this.internetComputerNum = internetComputerNum;
	}


	@Column(name="INTERNET_NUM")
	public Integer getInternetNum() {
		return this.internetNum;
	}

	public void setInternetNum(Integer internetNum) {
		this.internetNum = internetNum;
	}


	@Column(name="INTERNET_OA")
	public boolean getInternetOa() {
		return this.internetOa;
	}

	public void setInternetOa(boolean internetOa) {
		this.internetOa = internetOa;
	}


	@Column(name="INTERNET_OTHER")
	public boolean getInternetOther() {
		return this.internetOther;
	}

	public void setInternetOther(boolean internetOther) {
		this.internetOther = internetOther;
	}


	@Column(name="INTERNET_OTHER_DESC")
	public String getInternetOtherDesc() {
		return this.internetOtherDesc;
	}

	public void setInternetOtherDesc(String internetOtherDesc) {
		this.internetOtherDesc = internetOtherDesc;
	}


	@Column(name="INTERNET_PARTY")
	public boolean getInternetParty() {
		return this.internetParty;
	}

	public void setInternetParty(boolean internetParty) {
		this.internetParty = internetParty;
	}


	@Column(name="INTERNET_PORT_IP")
	public String getInternetPortIp() {
		return this.internetPortIp;
	}

	public void setInternetPortIp(String internetPortIp) {
		this.internetPortIp = internetPortIp;
	}


	@Column(name="INTERNET_PORT_NUM")
	public Integer getInternetPortNum() {
		return this.internetPortNum;
	}

	public void setInternetPortNum(Integer internetPortNum) {
		this.internetPortNum = internetPortNum;
	}


	@Column(name="INTERNET_WEBSITE_DN")
	public String getInternetWebsiteDn() {
		return this.internetWebsiteDn;
	}

	public void setInternetWebsiteDn(String internetWebsiteDn) {
		this.internetWebsiteDn = internetWebsiteDn;
	}


	@Column(name="INTERNET_WEBSITE_EXIST")
	public boolean getInternetWebsiteExist() {
		return this.internetWebsiteExist;
	}

	public void setInternetWebsiteExist(boolean internetWebsiteExist) {
		this.internetWebsiteExist = internetWebsiteExist;
	}


	@Column(name="INTERNET_WEBSITE_IP")
	public String getInternetWebsiteIp() {
		return this.internetWebsiteIp;
	}

	public void setInternetWebsiteIp(String internetWebsiteIp) {
		this.internetWebsiteIp = internetWebsiteIp;
	}


	@Column(name="INTERNET_WEBSITE_NUM")
	public Integer getInternetWebsiteNum() {
		return this.internetWebsiteNum;
	}

	public void setInternetWebsiteNum(Integer internetWebsiteNum) {
		this.internetWebsiteNum = internetWebsiteNum;
	}


	@Column(name="INTRANET_NUM")
	public Integer getIntranetNum() {
		return this.intranetNum;
	}

	public void setIntranetNum(Integer intranetNum) {
		this.intranetNum = intranetNum;
	}

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="REPORT_DATE")
	public Date getReportDate() {
		return this.reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	@Column(name="SERVICE_MF_DX")
	public boolean getServiceMfDx() {
		return this.serviceMfDx;
	}

	public void setServiceMfDx(boolean serviceMfDx) {
		this.serviceMfDx = serviceMfDx;
	}


	@Column(name="SERVICE_MF_HS")
	public boolean getServiceMfHs() {
		return this.serviceMfHs;
	}

	public void setServiceMfHs(boolean serviceMfHs) {
		this.serviceMfHs = serviceMfHs;
	}


	@Column(name="SERVICE_MF_OTHER")
	public boolean getServiceMfOther() {
		return this.serviceMfOther;
	}

	public void setServiceMfOther(boolean serviceMfOther) {
		this.serviceMfOther = serviceMfOther;
	}


	@Column(name="SERVICE_MF_OTHER_DESC")
	public String getServiceMfOtherDesc() {
		return this.serviceMfOtherDesc;
	}

	public void setServiceMfOtherDesc(String serviceMfOtherDesc) {
		this.serviceMfOtherDesc = serviceMfOtherDesc;
	}


	@Column(name="SERVICE_MF_WT")
	public boolean getServiceMfWt() {
		return this.serviceMfWt;
	}

	public void setServiceMfWt(boolean serviceMfWt) {
		this.serviceMfWt = serviceMfWt;
	}


	//bi-directional many-to-one association to NoSecNetIntranet
	@OneToMany(mappedBy="noSecNet", cascade={CascadeType.PERSIST, CascadeType.REMOVE})
	@OrderBy("sort asc")
	public Set<NoSecNetIntranet> getNoSecNetIntranets() {
		return this.noSecNetIntranets;
	}

	public void setNoSecNetIntranets(Set<NoSecNetIntranet> noSecNetIntranets) {
		this.noSecNetIntranets = noSecNetIntranets;
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


	/**
	 * 返回year
	 * @return year
	 */
	@Column(name="YEAR")
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
}