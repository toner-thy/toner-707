package com.cdthgk.devicemgr.vo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;

/**
 * @description 密级设备分类实体类
 * @author 熊  超 2009-10-12 9:30
 * @modify 彭  维 2010-08-19 9:00 整理注释
 */
@SuppressWarnings("all")
public class SecrecyEquipmentCategory implements java.io.Serializable {

	private static final long serialVersionUID = -6906017342471977959L;
	private String secrecyEquipmentCategoryId;
	private String name;
	private Integer orderNo;
	private Organ organ;
	private Department department;
	private UserInfo createPerson;
	private Date createTime;
	private UserInfo modifyPerson;
	private Date modifyTime;
	private String description;
	private SecrecyEquipmentCategory parentSecrecyEquipmentCategory;
	private Set secrecyEquipmentCategorys = new HashSet(0);
	private Set secrecyEquipments = new HashSet(0);

	// Property accessors
	public Set getSecrecyEquipments() {
		return secrecyEquipments;
	}

	public void setSecrecyEquipments(Set secrecyEquipments) {
		this.secrecyEquipments = secrecyEquipments;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
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

	public String getSecrecyEquipmentCategoryId() {
		return secrecyEquipmentCategoryId;
	}

	public void setSecrecyEquipmentCategoryId(String secrecyEquipmentCategoryId) {
		this.secrecyEquipmentCategoryId = secrecyEquipmentCategoryId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public SecrecyEquipmentCategory getParentSecrecyEquipmentCategory() {
		return parentSecrecyEquipmentCategory;
	}

	public void setParentSecrecyEquipmentCategory(
			SecrecyEquipmentCategory parentSecrecyEquipmentCategory) {
		this.parentSecrecyEquipmentCategory = parentSecrecyEquipmentCategory;
	}

	public Set getSecrecyEquipmentCategorys() {
		return secrecyEquipmentCategorys;
	}

	public void setSecrecyEquipmentCategorys(Set secrecyEquipmentCategorys) {
		this.secrecyEquipmentCategorys = secrecyEquipmentCategorys;
	}
}