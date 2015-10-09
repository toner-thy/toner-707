package com.cdthgk.bmp.equipment.equipmentcheck.vo;

import java.io.Serializable;
import java.util.Date;

import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;
import com.cdthgk.platform.permission.user.domain.User;



public class EquipmentCheck implements Serializable {

	private static final long serialVersionUID = 1L;
	private String equipmentCheckId;
	private String equipmentName;
	private String equipmentOrgan;
	private String equipmentType;
	private Integer number;
	private String equipmentProducingArea;
	private String equipmentPurpose;
	private String remark;
	private Organ checkOrgan;
	//private User checkPerson;
	private UserInfo checkPerson;
	private Date checkTime;
	private String checkContent;
	private User modifyPerson;
	private Date modifyTime;
	private User createPerson;
	private Date createTime;
	/**
	 * @return the equipmentCheckId
	 */
	public String getEquipmentCheckId() {
		return equipmentCheckId;
	}
	/**
	 * @param equipmentCheckId the equipmentCheckId to set
	 */
	public void setEquipmentCheckId(String equipmentCheckId) {
		this.equipmentCheckId = equipmentCheckId;
	}
	/**
	 * @return the equipmentName
	 */
	public String getEquipmentName() {
		return equipmentName;
	}
	/**
	 * @param equipmentName the equipmentName to set
	 */
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	/**
	 * @return the equipmentOrgan
	 */
	public String getEquipmentOrgan() {
		return equipmentOrgan;
	}
	/**
	 * @param equipmentOrgan the equipmentOrgan to set
	 */
	public void setEquipmentOrgan(String equipmentOrgan) {
		this.equipmentOrgan = equipmentOrgan;
	}
	/**
	 * @return the equipmentType
	 */
	public String getEquipmentType() {
		return equipmentType;
	}
	/**
	 * @param equipmentType the equipmentType to set
	 */
	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}
	/**
	 * @return the number
	 */
	public Integer getNumber() {
		return number;
	}
	/**
	 * @param number the number to set
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}
	/**
	 * @return the equipmentProducingArea
	 */
	public String getEquipmentProducingArea() {
		return equipmentProducingArea;
	}
	/**
	 * @param equipmentProducingArea the equipmentProducingArea to set
	 */
	public void setEquipmentProducingArea(String equipmentProducingArea) {
		this.equipmentProducingArea = equipmentProducingArea;
	}
	/**
	 * @return the equipmentPurpose
	 */
	public String getEquipmentPurpose() {
		return equipmentPurpose;
	}
	/**
	 * @param equipmentPurpose the equipmentPurpose to set
	 */
	public void setEquipmentPurpose(String equipmentPurpose) {
		this.equipmentPurpose = equipmentPurpose;
	}
	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * @return the checkOrgan
	 */
	public Organ getCheckOrgan() {
		return checkOrgan;
	}
	/**
	 * @param checkOrgan the checkOrgan to set
	 */
	public void setCheckOrgan(Organ checkOrgan) {
		this.checkOrgan = checkOrgan;
	}
	/**
	 * @return the checkPerson
	 */
	/*public User getCheckPerson() {
		return checkPerson;
	}*/
	/**
	 * @param checkPerson the checkPerson to set
	 */
	/*public void setCheckPerson(User checkPerson) {
		this.checkPerson = checkPerson;
	}*/

	/**
	 * @return the checkContent
	 */
	public String getCheckContent() {
		return checkContent;
	}
	/**
	 * @return the checkPerson
	 */
	public UserInfo getCheckPerson() {
		return checkPerson;
	}
	/**
	 * @param checkPerson the checkPerson to set
	 */
	public void setCheckPerson(UserInfo checkPerson) {
		this.checkPerson = checkPerson;
	}
	/**
	 * @param checkContent the checkContent to set
	 */
	public void setCheckContent(String checkContent) {
		this.checkContent = checkContent;
	}
	/**
	 * @return the modifyPerson
	 */
	public User getModifyPerson() {
		return modifyPerson;
	}
	/**
	 * @param modifyPerson the modifyPerson to set
	 */
	public void setModifyPerson(User modifyPerson) {
		this.modifyPerson = modifyPerson;
	}
	/**
	 * @return the modifyTime
	 */
	public Date getModifyTime() {
		return modifyTime;
	}
	/**
	 * @param modifyTime the modifyTime to set
	 */
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	/**
	 * @return the createPerson
	 */
	public User getCreatePerson() {
		return createPerson;
	}
	/**
	 * @param createPerson the createPerson to set
	 */
	public void setCreatePerson(User createPerson) {
		this.createPerson = createPerson;
	}
	/**
	 * @return the checkTime
	 */
	public Date getCheckTime() {
		return checkTime;
	}
	/**
	 * @param checkTime the checkTime to set
	 */
	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}
	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
