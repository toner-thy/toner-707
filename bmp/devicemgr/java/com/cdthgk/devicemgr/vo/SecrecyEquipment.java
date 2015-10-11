package com.cdthgk.devicemgr.vo;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;
import com.cdthgk.platform.permission.user.domain.User;
/**
 * @description 密级设备实体类
 * @author 熊  超 2009-10-12 9:30
 * @modify 彭  维 2010-08-19 9:00 整理注释
 */

@SuppressWarnings("all")
public class SecrecyEquipment implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String secrecyEquipmentId;

	private String name;

	private String typeCode;

	private String categoryCode;

	private Double price;
	private Double unitPrice;

	private Date buyTime;

	private String atPlace;

	private User usePerson;

	private Integer  number;

	private UserInfo dutyPerson;

	private Department dutyDepartment;

	private Date modifyTime;

	private String modifyPerson;

	private Date createTime;

	private User createPerson;

	private Department department;

	private Organ organ;
	private Organ obtainOrgan;


	private Set equipmentTrash=new HashSet();

	private Set equipmentLoan=new HashSet();

	private SecrecyEquipmentCategory secrecyEquipmentCategory;

	/**
	 * 状态（正常，借出，报废等等）
	 */
	private Integer status;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getAtPlace() {
		return atPlace;
	}

	public void setAtPlace(String atPlace) {
		this.atPlace = atPlace;
	}

	public Date getBuyTime() {
		return buyTime;
	}

	public void setBuyTime(Date buyTime) {
		this.buyTime = buyTime;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
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

	public Department getDepartment() {
		return department;
	}

	public Double getUnitPrice() {
                return unitPrice;
        }

        public void setUnitPrice(Double unitPrice) {
                this.unitPrice = unitPrice;
        }

        public void setDepartment(Department department) {
		this.department = department;
	}

	public Department getDutyDepartment() {
		return dutyDepartment;
	}

	public void setDutyDepartment(Department dutyDepartment) {
		this.dutyDepartment = dutyDepartment;
	}

	public String getModifyPerson() {
		return modifyPerson;
	}

	public void setModifyPerson(String modifyPerson) {
		this.modifyPerson = modifyPerson;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Organ getOrgan() {
		return organ;
	}

	public void setOrgan(Organ organ) {
		this.organ = organ;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getSecrecyEquipmentId() {
		return secrecyEquipmentId;
	}

	public void setSecrecyEquipmentId(String secrecyEquipmentId) {
		this.secrecyEquipmentId = secrecyEquipmentId;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public User getUsePerson() {
		return usePerson;
	}

	public void setUsePerson(User usePerson) {
		this.usePerson = usePerson;
	}

	public Set getEquipmentTrash() {
		return equipmentTrash;
	}

	public void setEquipmentTrash(Set equipmentTrash) {
		this.equipmentTrash = equipmentTrash;
	}

	public Set getEquipmentLoan() {
		return equipmentLoan;
	}

	public void setEquipmentLoan(Set equipmentLoan) {
		this.equipmentLoan = equipmentLoan;
	}


	public SecrecyEquipmentCategory getSecrecyEquipmentCategory() {
		return secrecyEquipmentCategory;
	}

	public void setSecrecyEquipmentCategory(
			SecrecyEquipmentCategory secrecyEquipmentCategory) {
		this.secrecyEquipmentCategory = secrecyEquipmentCategory;
	}

    public Integer getNumber() {
            return number;
    }

    public void setNumber(Integer number) {
            this.number = number;
    }

	/**
	 * @return 返回dutyPerson
	 */
	public UserInfo getDutyPerson() {
		return dutyPerson;
	}

	/**
	 * @param dutyPerson 设置dutyPerson
	 */
	public void setDutyPerson(UserInfo dutyPerson) {
		this.dutyPerson = dutyPerson;
	}

	public Organ getObtainOrgan() {
		return obtainOrgan;
	}

	public void setObtainOrgan(Organ obtainOrgan) {
		this.obtainOrgan = obtainOrgan;
	}

}