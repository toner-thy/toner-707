package com.cdthgk.devicemgr.vo;

import java.util.Date;

import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;
import com.cdthgk.platform.permission.user.domain.User;

/**
 * @description 设备借出
 * @author 熊  超 2009-10-12 9:30
 * @modify 彭  维 2010-08-19 9:00 整理注释
 */
public class EquipmentLoan implements java.io.Serializable {

	private static final long serialVersionUID = -8160682885699154665L;
	private String equipmentLoanId;
	private SecrecyEquipment secrecyEquipment;
	private UserInfo loanPerson;
	private String remark;
	private String loanReason;
	private int status;
	private Date loanStartTime;
	private Date loanEndTime;
	private Organ organ;
	private Department department;
	private User createPerson;
	private Date createTime;
	private User modifyPerson;
	private Date modifyTime;

	public String getEquipmentLoanId() {
		return equipmentLoanId;
	}
	public void setEquipmentLoanId(String equipmentLoanId) {
		this.equipmentLoanId = equipmentLoanId;
	}
	public SecrecyEquipment getSecrecyEquipment() {
		return secrecyEquipment;
	}
	public void setSecrecyEquipment(SecrecyEquipment secrecyEquipment) {
		this.secrecyEquipment = secrecyEquipment;
	}

	public UserInfo getLoanPerson() {
		return loanPerson;
	}
	public void setLoanPerson(UserInfo loanPerson) {
		this.loanPerson = loanPerson;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getLoanReason() {
		return loanReason;
	}
	public void setLoanReason(String loanReason) {
		this.loanReason = loanReason;
	}
	public Date getLoanStartTime() {
		return loanStartTime;
	}
	public void setLoanStartTime(Date loanStartTime) {
		this.loanStartTime = loanStartTime;
	}
	public Date getLoanEndTime() {
		return loanEndTime;
	}
	public void setLoanEndTime(Date loanEndTime) {
		this.loanEndTime = loanEndTime;
	}
	public Organ getOrgan() {
		return organ;
	}
	public void setOrgan(Organ organ) {
		this.organ = organ;
	}

	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public User getCreatePerson() {
		return createPerson;
	}
	public void setCreatePerson(User createPerson) {
		this.createPerson = createPerson;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public User getModifyPerson() {
		return modifyPerson;
	}
	public void setModifyPerson(User modifyPerson) {
		this.modifyPerson = modifyPerson;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}