package com.cdthgk.disclosesecrecy.vo;
// default package
// Generated 2013-7-15 9:42:10 ----- 3.4.0.CR1

import java.util.Date;

import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;

/**
 * CaseHandledutyPerson  泄密案件处理责任人
 */
public class CaseHandledutyPerson implements java.io.Serializable {
	private static final long serialVersionUID = -6814985591012551318L;
	private String caseHandledutyPersonId;//要害部位  密级解除
	private DiscloseSecrecy disclosesecrecycase;//泄密案件
	private CaseCriticalviolation caseCriticalviolation;//严重违规案件id
	private Integer handleType;   //处理形式
	private Integer politicalLandscape;//政治面貌
	private Integer discloseCaseType; //泄密案件类型(1泄密案件，2严重违规案件)
	private Integer manageLevel;   //行政级别
	private Integer clearType;   //解除类型
	private Integer dataState;//状态
	private Department department;//部门
	/**
	 * 泄密案件
	 */
	public static  Integer discloseCaseType_DC = 1;

	/**
	 * 严重违规案件
	 */
	public static  Integer discloseCaseType_CaseCcv = 2;

	private UserInfo userInfo;//责任人
	private Date createTime;
	/**
	 * 默认构函数
	 */
	public CaseHandledutyPerson() {

	}







	public String getCaseHandledutyPersonId() {
		return caseHandledutyPersonId;
	}







	public void setCaseHandledutyPersonId(String caseHandledutyPersonId) {
		this.caseHandledutyPersonId = caseHandledutyPersonId;
	}







	/**
	 * 返回clearType
	 * @return clearType
	 */
	public Integer getClearType() {
		return this.clearType;
	}

	/**
	 * 设置clearType
	 * @param clearType clearType
	 */
	public void setClearType(Integer clearType) {
		this.clearType = clearType;
	}








	public Integer getHandleType() {
		return handleType;
	}


	public void setHandleType(Integer handleType) {
		this.handleType = handleType;
	}


	public Integer getPoliticalLandscape() {
		return politicalLandscape;
	}


	public void setPoliticalLandscape(Integer politicalLandscape) {
		this.politicalLandscape = politicalLandscape;
	}


	public Integer getDiscloseCaseType() {
		return discloseCaseType;
	}


	public void setDiscloseCaseType(Integer discloseCaseType) {
		this.discloseCaseType = discloseCaseType;
	}


	public Integer getManageLevel() {
		return manageLevel;
	}


	public void setManageLevel(Integer manageLevel) {
		this.manageLevel = manageLevel;
	}




	public Integer getDataState() {
		return dataState;
	}


	public void setDataState(Integer dataState) {
		this.dataState = dataState;
	}


	public Department getDepartment() {
		return department;
	}


	public void setDepartment(Department department) {
		this.department = department;
	}



	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public DiscloseSecrecy getDisclosesecrecycase() {
		return disclosesecrecycase;
	}


	public void setDisclosesecrecycase(DiscloseSecrecy disclosesecrecycase) {
		this.disclosesecrecycase = disclosesecrecycase;
	}


	public CaseCriticalviolation getCaseCriticalviolation() {
		return caseCriticalviolation;
	}


	public void setCaseCriticalviolation(CaseCriticalviolation caseCriticalviolation) {
		this.caseCriticalviolation = caseCriticalviolation;
	}







	public UserInfo getUserInfo() {
		return userInfo;
	}







	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}




}
