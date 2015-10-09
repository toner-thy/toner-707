package com.cdthgk.bmp.equipment.equipmenttype.vo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.cdthgk.bmp.equipment.equipment.vo.EquipmentNumber;
import com.cdthgk.exchange.vo.Reportable;
import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.permission.user.domain.User;


/**
 * @ 类用途
 * @ 创建人 chenwc
 * @ 创建时间 Mar 2, 2010-2:02:22 PM
 * @ 修改人 chenwc
 * @ 修改时间 Mar 2, 2010-2:02:22 PM
 * @ 修改描述
 * @ 公司名称 清华高科
 * @ 当前系统主版本号
 */
public class EquipmentType {

	// Fields

	private static final long serialVersionUID = 1L;
	private String equipmentTypeId;
	private String name;
	private String description;
	private Department department;
	private Organ organ;
	private User modifyPerson;
	private Date modifyTime;
	private User createPerson;
	private Date createTime;
	private Integer state;
	//通过传输状态
	private Integer transmitState;
	private Set<EquipmentNumber> equipmentNumbers = new HashSet<EquipmentNumber>(0);
	private Set<Organ> receiveOrgans = new HashSet<Organ>();
	/**
	 * 未发布
	 */
	public static final Integer PUBLISH_NO = 0;
	/**
	 * 已发布
	 */
	public static final Integer PUBLISH_YES = 1;

	// Constructors

	/** default constructor */
	public EquipmentType() {
	}

	// Property accessors
	public String getEquipmentTypeId() {
		return equipmentTypeId;
	}

	public void setEquipmentTypeId(String equipmentTypeId) {
		this.equipmentTypeId = equipmentTypeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Organ getOrgan() {
		return organ;
	}

	public void setOrgan(Organ organ) {
		this.organ = organ;
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

	public Set<EquipmentNumber> getEquipmentNumbers() {
		return equipmentNumbers;
	}

	public void setEquipmentNumbers(Set<EquipmentNumber> equipmentNumbers) {
		this.equipmentNumbers = equipmentNumbers;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	/* (non-Javadoc)
	 * @see com.thgk.sys.base.vo.Reportable#getInceptTime()
	 */
	/*@Override
	public Date getInceptTime() {
		// TODO Auto-generated method stub
		return null;
	}*/

	/* (non-Javadoc)
	 * @see com.thgk.sys.base.vo.Reportable#getReportState()
	 */
	/*@Override
	public Integer getReportState() {
		// TODO Auto-generated method stub
		return null;
	}*/

	/* (non-Javadoc)
	 * @see com.thgk.sys.base.vo.Reportable#getReportTime()
	 */
	/*@Override
	public Date getReportTime() {
		// TODO Auto-generated method stub
		return null;
	}*/

	/* (non-Javadoc)
	 * @see com.thgk.sys.base.vo.Reportable#setInceptTime(java.util.Date)
	 */
	/*@Override
	public void setInceptTime(Date inceptTime) {
		// TODO Auto-generated method stub

	}*/

	/* (non-Javadoc)
	 * @see com.thgk.sys.base.vo.Reportable#setReportState(java.lang.Integer)
	 */
	/*@Override
	public void setReportState(Integer reportState) {
		// TODO Auto-generated method stub

	}*/

	/* (non-Javadoc)
	 * @see com.thgk.sys.base.vo.Reportable#setReportTime(java.util.Date)
	 */
	/*@Override
	public void setReportTime(Date reportTime) {
		// TODO Auto-generated method stub

	}*/

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