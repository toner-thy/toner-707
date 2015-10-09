package com.cdthgk.devicemgr.vo;

import java.util.Date;

import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;

/**
 * @description 设备借出
 * @author 熊  超 2009-10-12 9:30
 * @modify 彭  维 2010-08-19 9:00 整理注释
 */
public class EquipmentTrash implements java.io.Serializable {

	private static final long serialVersionUID = 3775514638912105573L;
	private String equipmentTrashId;
	private SecrecyEquipment secrecyEquipment;
	private UserInfo applyPerson;
	private String remark;
	private String applyReason;
	private int status;
	private Date trashDate;
	private Organ organId;
	private Department departmentId;
	private UserInfo createPerson;
	private Date createTime;
	private UserInfo modifyPerson;
	private Date modifyTime;

	/** default constructor */
	public EquipmentTrash() {
	}

	public String getEquipmentTrashId() {
		return equipmentTrashId;
	}

	public void setEquipmentTrashId(String equipmentTrashId) {
		this.equipmentTrashId = equipmentTrashId;
	}

	public SecrecyEquipment getSecrecyEquipment() {
		return secrecyEquipment;
	}

	public void setSecrecyEquipment(SecrecyEquipment secrecyEquipment) {
		this.secrecyEquipment = secrecyEquipment;
	}

	public UserInfo getApplyPerson() {
		return applyPerson;
	}

	public void setApplyPerson(UserInfo applyPerson) {
		this.applyPerson = applyPerson;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getApplyReason() {
		return applyReason;
	}

	public void setApplyReason(String applyReason) {
		this.applyReason = applyReason;
	}

	public Date getTrashDate() {
		return trashDate;
	}

	public void setTrashDate(Date trashDate) {
		this.trashDate = trashDate;
	}

	public Organ getOrganId() {
		return organId;
	}

	public void setOrganId(Organ organId) {
		this.organId = organId;
	}

	public Department getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Department departmentId) {
		this.departmentId = departmentId;
	}

	public UserInfo getCreatePerson() {
		return createPerson;
	}

	public void setCreatePerson(UserInfo createPerson) {
		this.createPerson = createPerson;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public UserInfo getModifyPerson() {
		return modifyPerson;
	}

	public void setModifyPerson(UserInfo modifyPerson) {
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