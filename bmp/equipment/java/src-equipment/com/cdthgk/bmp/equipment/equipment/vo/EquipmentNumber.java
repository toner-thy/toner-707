package com.cdthgk.bmp.equipment.equipment.vo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.cdthgk.bmp.equipment.equipmenttype.vo.EquipmentType;
import com.cdthgk.exchange.vo.Reportable;
import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.permission.user.domain.User;



/**
 * @ 类用途
 * @ 创建人 chenwc
 * @ 创建时间 Mar 2, 2010-2:04:14 PM
 * @ 修改人 chenwc
 * @ 修改时间 Mar 2, 2010-2:04:14 PM
 * @ 修改描述
 * @ 公司名称 清华高科
 * @ 当前系统主版本号
 */
public class EquipmentNumber implements Reportable {

	// Fields

	private static final long serialVersionUID = 1L;
	private String equipmentId;
	private EquipmentType equipmentType;
	private Integer number;
	//private String organId;
	private Organ organ;
	//private String departmentId;
	private Department department;
	//private String modifyPerson;
	private User modifyPerson;
	private Date modifyTime;
	//private String createPerson;
	private User createPerson;
	private Date createTime;

	private Date reportTime;
	private Date inceptTime;
	private Integer reportState;

	private String reportOrgan;

	private Date reportOrganTime;

	private Date inceptOrganTime;

	private Integer reportOrganState;
	//通过传输状态
	private Integer transmitState;
	// Constructors
	private Set<Organ> receiveOrgans = new HashSet<Organ>();

	/** default constructor */
	public EquipmentNumber() {
	}

	// Property accessors

	public EquipmentType getEquipmentType() {
		return equipmentType;
	}

	public void setEquipmentType(EquipmentType equipmentType) {
		this.equipmentType = equipmentType;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
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

	public String getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}

	public Date getReportTime() {
		return reportTime;
	}

	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}

	public Date getInceptTime() {
		return inceptTime;
	}

	public void setInceptTime(Date inceptTime) {
		this.inceptTime = inceptTime;
	}

	/**
	 * @return the reportState
	 */
	public Integer getReportState() {
		return reportState;
	}

	/**
	 * @param reportState the reportState to set
	 */
	public void setReportState(Integer reportState) {
		this.reportState = reportState;
	}

	/**
	 * @return the reportOrgan
	 */
	public String getReportOrgan() {
		return reportOrgan;
	}

	/**
	 * @param reportOrgan the reportOrgan to set
	 */
	public void setReportOrgan(String reportOrgan) {
		this.reportOrgan = reportOrgan;
	}

	/**
	 * @return the reportOrganTime
	 */
	public Date getReportOrganTime() {
		return reportOrganTime;
	}

	/**
	 * @param reportOrganTime the reportOrganTime to set
	 */
	public void setReportOrganTime(Date reportOrganTime) {
		this.reportOrganTime = reportOrganTime;
	}

	/**
	 * @return the inceptOrganTime
	 */
	public Date getInceptOrganTime() {
		return inceptOrganTime;
	}

	/**
	 * @param inceptOrganTime the inceptOrganTime to set
	 */
	public void setInceptOrganTime(Date inceptOrganTime) {
		this.inceptOrganTime = inceptOrganTime;
	}

	/**
	 * @return the reportOrganState
	 */
	public Integer getReportOrganState() {
		return reportOrganState;
	}

	/**
	 * @param reportOrganState the reportOrganState to set
	 */
	public void setReportOrganState(Integer reportOrganState) {
		this.reportOrganState = reportOrganState;
	}

	/**
	 * @return the receiveOrgans
	 */
	public Set<Organ> getReceiveOrgans() {
		return receiveOrgans;
	}

	/**
	 * @param receiveOrgans the receiveOrgans to set
	 */
	public void setReceiveOrgans(Set<Organ> receiveOrgans) {
		this.receiveOrgans = receiveOrgans;
	}

	/**
	 * @return the transmitState
	 */
	public Integer getTransmitState() {
		return transmitState;
	}

	/**
	 * @param transmitState the transmitState to set
	 */
	public void setTransmitState(Integer transmitState) {
		this.transmitState = transmitState;
	}

}