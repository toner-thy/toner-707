package com.cdthgk.disclosesecrecy.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;
/*
 * CaseCriticalviolation 严重违规案件
 */
public class CaseCriticalviolation implements Serializable {
	private static final long serialVersionUID = 1L;
	private String caseCriticalviolationId;
	private String name;
	private Integer dealResult;//查处结果
	private Integer secrecyLevel;//密级
	private Integer caseType;//违规方式
	private Integer dutyOrganKind;//责任单位性质
	private Department department;//部门名称
	private Integer status;//涉密状态
	private Organ createOrgan;//创建单位
	private UserInfo createPerson;
	private Date createTime;
	private UserInfo modifyPerson;
	private Date modifyTime;
	private Integer state;

	private Set<CaseCriticalviolationChange>  caseCriticalviolationChangeSet = new HashSet<CaseCriticalviolationChange>();
	private Set<CaseCriticalviolationClear> caseCriticalviolationClearSet = new HashSet<CaseCriticalviolationClear>();

	/**
	 * 未发布
	 */
	public static final Integer PUBLISH_NO = 0;
	/**
	 * 已发布
	 */
	public static final Integer PUBLISH_YES = 1;

	// 构造器
	/** 默认构造器 */
	public CaseCriticalviolation() {
	}

	//********************************* getter and setter ********************************




	public void setState(Integer state) {
		this.state = state;
	}





	public String getCaseCriticalviolationId() {
		return caseCriticalviolationId;
	}

	public void setCaseCriticalviolationId(String caseCriticalviolationId) {
		this.caseCriticalviolationId = caseCriticalviolationId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getDealResult() {
		return dealResult;
	}

	public void setDealResult(Integer dealResult) {
		this.dealResult = dealResult;
	}



	public Integer getCaseType() {
		return caseType;
	}

	public void setCaseType(Integer caseType) {
		this.caseType = caseType;
	}

	public Integer getDutyOrganKind() {
		return dutyOrganKind;
	}

	public void setDutyOrganKind(Integer dutyOrganKind) {
		this.dutyOrganKind = dutyOrganKind;
	}

	public Integer getState() {
		return state;
	}

	public Integer getSecrecyLevel() {
		return this.secrecyLevel;
	}

	public void setSecrecyLevel(Integer secrecyLevel) {
		this.secrecyLevel = secrecyLevel;
	}



	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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


	public Organ getCreateOrgan() {
		return createOrgan;
	}

	public void setCreateOrgan(Organ createOrgan) {
		this.createOrgan = createOrgan;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public UserInfo getCreatePerson() {
		return createPerson;
	}

	public void setCreatePerson(UserInfo createPerson) {
		this.createPerson = createPerson;
	}

	public UserInfo getModifyPerson() {
		return modifyPerson;
	}

	public void setModifyPerson(UserInfo modifyPerson) {
		this.modifyPerson = modifyPerson;
	}

	/**
	 * @return 返回caseCriticalviolationChangeSet
	 */
	public Set<CaseCriticalviolationChange> getCaseCriticalviolationChangeSet() {
		return caseCriticalviolationChangeSet;
	}

	/**
	 * @param caseCriticalviolationChangeSet 设置caseCriticalviolationChangeSet
	 */
	public void setCaseCriticalviolationChangeSet(
			Set<CaseCriticalviolationChange> caseCriticalviolationChangeSet) {
		this.caseCriticalviolationChangeSet = caseCriticalviolationChangeSet;
	}

	/**
	 * @return 返回caseCriticalviolationClearSet
	 */
	public Set<CaseCriticalviolationClear> getCaseCriticalviolationClearSet() {
		return caseCriticalviolationClearSet;
	}

	/**
	 * @param caseCriticalviolationClearSet 设置caseCriticalviolationClearSet
	 */
	public void setCaseCriticalviolationClearSet(
			Set<CaseCriticalviolationClear> caseCriticalviolationClearSet) {
		this.caseCriticalviolationClearSet = caseCriticalviolationClearSet;
	}
}