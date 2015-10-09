package com.cdthgk.bmp.secrecynet.secrecySupervision.vo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.permission.user.domain.User;
import com.thgk.platform.core.BaseDomain;

public class SecrecySupervision extends BaseDomain implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private Integer year;
	private User reportUser;
	private Date reportDate;
	private Department reportDepartment;
	private Organ reportOrgan;
	private Integer inPlatformDuty;
	private Integer inCaseInvestigation;
	private Integer inImportantOrganNet;
	private Integer inSecrecyComInterent;
	private Integer inInternetMsgCheck;
	private Integer inSecrecyCheck;
	private String inOther;
	private Integer outSociologySupervision;
	private Integer outIpUserMsg;
	private Integer outOrganReport;
	private Integer outInternetAccess;
	private String outOther;
	private Integer netCheckTimes;
	private Integer netCheckNum;
	private Integer netEvaluationNum;
	private String netCheckAdvice;
	private Integer netSecrecyCheckTimes;
	private Integer netSecrecyCheckNum;
	private Integer netNoneSecrecyNum;
	private Integer computerSecrecyNum;
	private Integer computerNoneNum;
	private Integer computerInternetNum;
	private Integer storageSecrecyNum;
	private Integer stotageNoneNum;
	private Integer errComputerInternetNum;
	private Integer errInternetMsgNum;
	private Integer errStorageExchangeUseNum;
	private Integer errPeoplePunishment;
	private String errOtherAdvice;
	private String illegalDealMsg;
	private String warningOtherAdvice;
	private Integer warningMsgNum;
	private Integer webSecrecyMsgNum;
	private Integer errComputerTrojanNum;
	private Department createDepartment;
	private Organ createOrgan;

	private Set<SecrecySupervisionContent> secrecySupervisionContentSet = new HashSet<SecrecySupervisionContent>(0);

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
	 * @return the inPlatformDuty
	 */
	public Integer getInPlatformDuty() {
		return inPlatformDuty;
	}

	/**
	 * @param inPlatformDuty the inPlatformDuty to set
	 */
	public void setInPlatformDuty(Integer inPlatformDuty) {
		this.inPlatformDuty = inPlatformDuty;
	}

	/**
	 * @return the inCaseInvestigation
	 */
	public Integer getInCaseInvestigation() {
		return inCaseInvestigation;
	}

	/**
	 * @param inCaseInvestigation the inCaseInvestigation to set
	 */
	public void setInCaseInvestigation(Integer inCaseInvestigation) {
		this.inCaseInvestigation = inCaseInvestigation;
	}

	/**
	 * @return the inImportantOrganNet
	 */
	public Integer getInImportantOrganNet() {
		return inImportantOrganNet;
	}

	/**
	 * @param inImportantOrganNet the inImportantOrganNet to set
	 */
	public void setInImportantOrganNet(Integer inImportantOrganNet) {
		this.inImportantOrganNet = inImportantOrganNet;
	}

	/**
	 * @return the inSecrecyComInterent
	 */
	public Integer getInSecrecyComInterent() {
		return inSecrecyComInterent;
	}

	/**
	 * @param inSecrecyComInterent the inSecrecyComInterent to set
	 */
	public void setInSecrecyComInterent(Integer inSecrecyComInterent) {
		this.inSecrecyComInterent = inSecrecyComInterent;
	}

	/**
	 * @return the inInternetMsgCheck
	 */
	public Integer getInInternetMsgCheck() {
		return inInternetMsgCheck;
	}

	/**
	 * @param inInternetMsgCheck the inInternetMsgCheck to set
	 */
	public void setInInternetMsgCheck(Integer inInternetMsgCheck) {
		this.inInternetMsgCheck = inInternetMsgCheck;
	}

	/**
	 * @return the inSecrecyCheck
	 */
	public Integer getInSecrecyCheck() {
		return inSecrecyCheck;
	}

	/**
	 * @param inSecrecyCheck the inSecrecyCheck to set
	 */
	public void setInSecrecyCheck(Integer inSecrecyCheck) {
		this.inSecrecyCheck = inSecrecyCheck;
	}

	/**
	 * @return the inOther
	 */
	public String getInOther() {
		return inOther;
	}

	/**
	 * @param inOther the inOther to set
	 */
	public void setInOther(String inOther) {
		this.inOther = inOther;
	}

	/**
	 * @return the outSociologySupervision
	 */
	public Integer getOutSociologySupervision() {
		return outSociologySupervision;
	}

	/**
	 * @param outSociologySupervision the outSociologySupervision to set
	 */
	public void setOutSociologySupervision(Integer outSociologySupervision) {
		this.outSociologySupervision = outSociologySupervision;
	}

	/**
	 * @return the outIpUserMsg
	 */
	public Integer getOutIpUserMsg() {
		return outIpUserMsg;
	}

	/**
	 * @param outIpUserMsg the outIpUserMsg to set
	 */
	public void setOutIpUserMsg(Integer outIpUserMsg) {
		this.outIpUserMsg = outIpUserMsg;
	}

	/**
	 * @return the outOrganReport
	 */
	public Integer getOutOrganReport() {
		return outOrganReport;
	}

	/**
	 * @param outOrganReport the outOrganReport to set
	 */
	public void setOutOrganReport(Integer outOrganReport) {
		this.outOrganReport = outOrganReport;
	}

	/**
	 * @return the outInternetAccess
	 */
	public Integer getOutInternetAccess() {
		return outInternetAccess;
	}

	/**
	 * @param outInternetAccess the outInternetAccess to set
	 */
	public void setOutInternetAccess(Integer outInternetAccess) {
		this.outInternetAccess = outInternetAccess;
	}

	/**
	 * @return the outOther
	 */
	public String getOutOther() {
		return outOther;
	}

	/**
	 * @param outOther the outOther to set
	 */
	public void setOutOther(String outOther) {
		this.outOther = outOther;
	}

	/**
	 * @return the netCheckTimes
	 */
	public Integer getNetCheckTimes() {
		return netCheckTimes;
	}

	/**
	 * @param netCheckTimes the netCheckTimes to set
	 */
	public void setNetCheckTimes(Integer netCheckTimes) {
		this.netCheckTimes = netCheckTimes;
	}

	/**
	 * @return the netCheckNum
	 */
	public Integer getNetCheckNum() {
		return netCheckNum;
	}

	/**
	 * @param netCheckNum the netCheckNum to set
	 */
	public void setNetCheckNum(Integer netCheckNum) {
		this.netCheckNum = netCheckNum;
	}

	/**
	 * @return the netEvaluationNum
	 */
	public Integer getNetEvaluationNum() {
		return netEvaluationNum;
	}

	/**
	 * @param netEvaluationNum the netEvaluationNum to set
	 */
	public void setNetEvaluationNum(Integer netEvaluationNum) {
		this.netEvaluationNum = netEvaluationNum;
	}

	/**
	 * @return the netCheckAdvice
	 */
	public String getNetCheckAdvice() {
		return netCheckAdvice;
	}

	/**
	 * @param netCheckAdvice the netCheckAdvice to set
	 */
	public void setNetCheckAdvice(String netCheckAdvice) {
		this.netCheckAdvice = netCheckAdvice;
	}

	/**
	 * @return the netSecrecyCheckTimes
	 */
	public Integer getNetSecrecyCheckTimes() {
		return netSecrecyCheckTimes;
	}

	/**
	 * @param netSecrecyCheckTimes the netSecrecyCheckTimes to set
	 */
	public void setNetSecrecyCheckTimes(Integer netSecrecyCheckTimes) {
		this.netSecrecyCheckTimes = netSecrecyCheckTimes;
	}

	/**
	 * @return the netSecrecyCheckNum
	 */
	public Integer getNetSecrecyCheckNum() {
		return netSecrecyCheckNum;
	}

	/**
	 * @param netSecrecyCheckNum the netSecrecyCheckNum to set
	 */
	public void setNetSecrecyCheckNum(Integer netSecrecyCheckNum) {
		this.netSecrecyCheckNum = netSecrecyCheckNum;
	}

	/**
	 * @return the computerSecrecyNum
	 */
	public Integer getComputerSecrecyNum() {
		return computerSecrecyNum;
	}

	/**
	 * @param computerSecrecyNum the computerSecrecyNum to set
	 */
	public void setComputerSecrecyNum(Integer computerSecrecyNum) {
		this.computerSecrecyNum = computerSecrecyNum;
	}

	/**
	 * @return the computerNoneNum
	 */
	public Integer getComputerNoneNum() {
		return computerNoneNum;
	}

	/**
	 * @param computerNoneNum the computerNoneNum to set
	 */
	public void setComputerNoneNum(Integer computerNoneNum) {
		this.computerNoneNum = computerNoneNum;
	}

	/**
	 * @return the computerInternetNum
	 */
	public Integer getComputerInternetNum() {
		return computerInternetNum;
	}

	/**
	 * @param computerInternetNum the computerInternetNum to set
	 */
	public void setComputerInternetNum(Integer computerInternetNum) {
		this.computerInternetNum = computerInternetNum;
	}

	/**
	 * @return the storageSecrecyNum
	 */
	public Integer getStorageSecrecyNum() {
		return storageSecrecyNum;
	}

	/**
	 * @param storageSecrecyNum the storageSecrecyNum to set
	 */
	public void setStorageSecrecyNum(Integer storageSecrecyNum) {
		this.storageSecrecyNum = storageSecrecyNum;
	}

	/**
	 * @return the stotageNoneNum
	 */
	public Integer getStotageNoneNum() {
		return stotageNoneNum;
	}

	/**
	 * @param stotageNoneNum the stotageNoneNum to set
	 */
	public void setStotageNoneNum(Integer stotageNoneNum) {
		this.stotageNoneNum = stotageNoneNum;
	}

	/**
	 * @return the errComputerInternetNum
	 */
	public Integer getErrComputerInternetNum() {
		return errComputerInternetNum;
	}

	/**
	 * @param errComputerInternetNum the errComputerInternetNum to set
	 */
	public void setErrComputerInternetNum(Integer errComputerInternetNum) {
		this.errComputerInternetNum = errComputerInternetNum;
	}

	/**
	 * @return the errInternetMsgNum
	 */
	public Integer getErrInternetMsgNum() {
		return errInternetMsgNum;
	}

	/**
	 * @param errInternetMsgNum the errInternetMsgNum to set
	 */
	public void setErrInternetMsgNum(Integer errInternetMsgNum) {
		this.errInternetMsgNum = errInternetMsgNum;
	}

	/**
	 * @return the errStorageExchangeUseNum
	 */
	public Integer getErrStorageExchangeUseNum() {
		return errStorageExchangeUseNum;
	}

	/**
	 * @param errStorageExchangeUseNum the errStorageExchangeUseNum to set
	 */
	public void setErrStorageExchangeUseNum(Integer errStorageExchangeUseNum) {
		this.errStorageExchangeUseNum = errStorageExchangeUseNum;
	}

	/**
	 * @return the errPeoplePunishment
	 */
	public Integer getErrPeoplePunishment() {
		return errPeoplePunishment;
	}

	/**
	 * @param errPeoplePunishment the errPeoplePunishment to set
	 */
	public void setErrPeoplePunishment(Integer errPeoplePunishment) {
		this.errPeoplePunishment = errPeoplePunishment;
	}

	/**
	 * @return the errOtherAdvice
	 */
	public String getErrOtherAdvice() {
		return errOtherAdvice;
	}

	/**
	 * @param errOtherAdvice the errOtherAdvice to set
	 */
	public void setErrOtherAdvice(String errOtherAdvice) {
		this.errOtherAdvice = errOtherAdvice;
	}

	/**
	 * @return the illegalDealMsg
	 */
	public String getIllegalDealMsg() {
		return illegalDealMsg;
	}

	/**
	 * @param illegalDealMsg the illegalDealMsg to set
	 */
	public void setIllegalDealMsg(String illegalDealMsg) {
		this.illegalDealMsg = illegalDealMsg;
	}

	/**
	 * @return the warningOtherAdvice
	 */
	public String getWarningOtherAdvice() {
		return warningOtherAdvice;
	}

	/**
	 * @param warningOtherAdvice the warningOtherAdvice to set
	 */
	public void setWarningOtherAdvice(String warningOtherAdvice) {
		this.warningOtherAdvice = warningOtherAdvice;
	}

	/**
	 * @return the warningMsgNum
	 */
	public Integer getWarningMsgNum() {
		return warningMsgNum;
	}

	/**
	 * @param warningMsgNum the warningMsgNum to set
	 */
	public void setWarningMsgNum(Integer warningMsgNum) {
		this.warningMsgNum = warningMsgNum;
	}

	/**
	 * @return the webSecrecyMsgNum
	 */
	public Integer getWebSecrecyMsgNum() {
		return webSecrecyMsgNum;
	}

	/**
	 * @param webSecrecyMsgNum the webSecrecyMsgNum to set
	 */
	public void setWebSecrecyMsgNum(Integer webSecrecyMsgNum) {
		this.webSecrecyMsgNum = webSecrecyMsgNum;
	}

	/**
	 * @return the errComputerTrojanNum
	 */
	public Integer getErrComputerTrojanNum() {
		return errComputerTrojanNum;
	}

	/**
	 * @param errComputerTrojanNum the errComputerTrojanNum to set
	 */
	public void setErrComputerTrojanNum(Integer errComputerTrojanNum) {
		this.errComputerTrojanNum = errComputerTrojanNum;
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
	 * @return the secrecySupervisionContentSet
	 */
	public Set<SecrecySupervisionContent> getSecrecySupervisionContentSet() {
		return secrecySupervisionContentSet;
	}

	/**
	 * @param secrecySupervisionContentSet the secrecySupervisionContentSet to set
	 */
	public void setSecrecySupervisionContentSet(
			Set<SecrecySupervisionContent> secrecySupervisionContentSet) {
		this.secrecySupervisionContentSet = secrecySupervisionContentSet;
	}

	/**
	 * @return the netNoneSecrecyNum
	 */
	public Integer getNetNoneSecrecyNum() {
		return netNoneSecrecyNum;
	}

	/**
	 * @param netNoneSecrecyNum the netNoneSecrecyNum to set
	 */
	public void setNetNoneSecrecyNum(Integer netNoneSecrecyNum) {
		this.netNoneSecrecyNum = netNoneSecrecyNum;
	}



}