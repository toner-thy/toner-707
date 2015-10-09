package com.cdthgk.bmp.secrecynet.secrecybasic.vo;

import java.util.Date;

import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.permission.user.domain.User;
import com.thgk.platform.core.BaseDomain;

public class SecrecyBasic extends BaseDomain{
	private static final long serialVersionUID = 1L;

	private String id;
	private Integer year;
	private Organ createOrgan;
	private Department createDepartment;
	private Organ reportOrgan;
	private User reportUser;
	private Date reportDate;
	private String title;
	private String name;
	private Integer secrecyLevel;
	private Integer basic;
	private String basicExplanation;
	private String safeSecrecyOrgan;
	private String workMaintainOrgan;
	private Integer threePeople;
	private String mainSystem;
	private Integer userNum;
	private String netRange;
	private Integer netTerminalGhNum;
	private Integer netTerminalJrNum;
	private Integer cushiIdcard;
	private Integer cushiVisitControl;
	private Integer cushiProcessControl;
	private Integer cushiSafe;
	private Integer cushiBianjie;
	private Integer cushiPassword;
	private String address;
	private Integer linkedNo;
	private Integer linkedYes;
	private String linkedYesContent;
	private Integer safeSecrecyCeping;
	private Integer audit;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Organ getCreateOrgan() {
		return createOrgan;
	}
	public void setCreateOrgan(Organ createOrgan) {
		this.createOrgan = createOrgan;
	}
	public Department getCreateDepartment() {
		return createDepartment;
	}
	public void setCreateDepartment(Department createDepartment) {
		this.createDepartment = createDepartment;
	}
	public Organ getReportOrgan() {
		return reportOrgan;
	}
	public void setReportOrgan(Organ reportOrgan) {
		this.reportOrgan = reportOrgan;
	}
	public User getReportUser() {
		return reportUser;
	}
	public void setReportUser(User reportUser) {
		this.reportUser = reportUser;
	}
	public Date getReportDate() {
		return reportDate;
	}
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSecrecyLevel() {
		return secrecyLevel;
	}
	public void setSecrecyLevel(Integer secrecyLevel) {
		this.secrecyLevel = secrecyLevel;
	}
	public Integer getBasic() {
		return basic;
	}
	public void setBasic(Integer basic) {
		this.basic = basic;
	}
	public String getBasicExplanation() {
		return basicExplanation;
	}
	public void setBasicExplanation(String basicExplanation) {
		this.basicExplanation = basicExplanation;
	}
	public String getSafeSecrecyOrgan() {
		return safeSecrecyOrgan;
	}
	public void setSafeSecrecyOrgan(String safeSecrecyOrgan) {
		this.safeSecrecyOrgan = safeSecrecyOrgan;
	}
	public String getWorkMaintainOrgan() {
		return workMaintainOrgan;
	}
	public void setWorkMaintainOrgan(String workMaintainOrgan) {
		this.workMaintainOrgan = workMaintainOrgan;
	}
	public Integer getThreePeople() {
		return threePeople;
	}
	public void setThreePeople(Integer threePeople) {
		this.threePeople = threePeople;
	}
	public String getMainSystem() {
		return mainSystem;
	}
	public void setMainSystem(String mainSystem) {
		this.mainSystem = mainSystem;
	}
	public Integer getUserNum() {
		return userNum;
	}
	public void setUserNum(Integer userNum) {
		this.userNum = userNum;
	}
	public String getNetRange() {
		return netRange;
	}
	public void setNetRange(String netRange) {
		this.netRange = netRange;
	}
	public Integer getNetTerminalGhNum() {
		return netTerminalGhNum;
	}
	public void setNetTerminalGhNum(Integer netTerminalGhNum) {
		this.netTerminalGhNum = netTerminalGhNum;
	}
	public Integer getNetTerminalJrNum() {
		return netTerminalJrNum;
	}
	public void setNetTerminalJrNum(Integer netTerminalJrNum) {
		this.netTerminalJrNum = netTerminalJrNum;
	}
	public Integer getCushiIdcard() {
		return cushiIdcard;
	}
	public void setCushiIdcard(Integer cushiIdcard) {
		this.cushiIdcard = cushiIdcard;
	}
	public Integer getCushiVisitControl() {
		return cushiVisitControl;
	}
	public void setCushiVisitControl(Integer cushiVisitControl) {
		this.cushiVisitControl = cushiVisitControl;
	}
	public Integer getCushiProcessControl() {
		return cushiProcessControl;
	}
	public void setCushiProcessControl(Integer cushiProcessControl) {
		this.cushiProcessControl = cushiProcessControl;
	}
	public Integer getCushiSafe() {
		return cushiSafe;
	}
	public void setCushiSafe(Integer cushiSafe) {
		this.cushiSafe = cushiSafe;
	}
	public Integer getCushiBianjie() {
		return cushiBianjie;
	}
	public void setCushiBianjie(Integer cushiBianjie) {
		this.cushiBianjie = cushiBianjie;
	}
	public Integer getCushiPassword() {
		return cushiPassword;
	}
	public void setCushiPassword(Integer cushiPassword) {
		this.cushiPassword = cushiPassword;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLinkedYesContent() {
		return linkedYesContent;
	}
	public void setLinkedYesContent(String linkedYesContent) {
		this.linkedYesContent = linkedYesContent;
	}
	public Integer getAudit() {
		return audit;
	}
	public void setAudit(Integer audit) {
		this.audit = audit;
	}
	public Integer getLinkedNo() {
		return linkedNo;
	}
	public void setLinkedNo(Integer linkedNo) {
		this.linkedNo = linkedNo;
	}
	public Integer getLinkedYes() {
		return linkedYes;
	}
	public void setLinkedYes(Integer linkedYes) {
		this.linkedYes = linkedYes;
	}
	public Integer getSafeSecrecyCeping() {
		return safeSecrecyCeping;
	}
	public void setSafeSecrecyCeping(Integer safeSecrecyCeping) {
		this.safeSecrecyCeping = safeSecrecyCeping;
	}
}