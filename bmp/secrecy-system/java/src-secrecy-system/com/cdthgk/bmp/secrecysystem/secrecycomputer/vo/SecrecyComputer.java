package com.cdthgk.bmp.secrecysystem.secrecycomputer.vo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.cdthgk.bmp.keyPart.vo.Part;
import com.cdthgk.bmp.keysection.vo.KeySection;
import com.cdthgk.bmp.secrecysystem.secrecynetwork.vo.SecrecyNetworkterminal;
import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;
import com.cdthgk.platform.permission.user.domain.User;

/**
 * SecrecyComputer
 */
public class SecrecyComputer implements java.io.Serializable {

	private static final long serialVersionUID = -7981782917496786196L;

	private String secrecycomputerId;

	private UserInfo dutyPerson;

	private Integer secrecyLevel;

	private Integer computerType;

	private String computerNo;

	private String diskSeq;

	private Integer isbelongKeydepartment;

	private Integer isFanghu;

	private Integer isWailian;

	private Department department;

	private KeySection keySection;

	private Part keyPart;

	private User createPerson;

	private Date createTime;

	private User modifyPerson;

	private Date modifyTime;

	private Integer dataState;

	private Organ createOrgan;

	private Organ modifyOrgan;

	private Integer secrecyStatus;

	private Integer isNetTerminal;

	private Set<SecrecyNetworkterminal> secrecyNetworkterminals = new HashSet<SecrecyNetworkterminal>(
			0);

	private Set<SecrecyComputerChange> secrecyComputerChanges = new HashSet<SecrecyComputerChange>(
			0);

	private Set<SecrecyComputerClear> secrecyComputerClears = new HashSet<SecrecyComputerClear>(
			0);

	// 只有导出word时使用的数据字典属性
	private String secrecyLevelStr;
	private String computerTypeStr;

	/**
	 * 默认构函数
	 */
	public SecrecyComputer() {
	}

	/**
	 * @return 返回secrecycomputerId
	 */
	public String getSecrecycomputerId() {
		return secrecycomputerId;
	}

	/**
	 * @param secrecycomputerId 设置secrecycomputerId
	 */
	public void setSecrecycomputerId(String secrecycomputerId) {
		this.secrecycomputerId = secrecycomputerId;
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

	/**
	 * @return 返回secrecyLevel
	 */
	public Integer getSecrecyLevel() {
		return secrecyLevel;
	}

	/**
	 * @param secrecyLevel 设置secrecyLevel
	 */
	public void setSecrecyLevel(Integer secrecyLevel) {
		this.secrecyLevel = secrecyLevel;
	}

	/**
	 * @return 返回computerType
	 */
	public Integer getComputerType() {
		return computerType;
	}

	/**
	 * @param computerType 设置computerType
	 */
	public void setComputerType(Integer computerType) {
		this.computerType = computerType;
	}

	/**
	 * @return 返回computerNo
	 */
	public String getComputerNo() {
		return computerNo;
	}

	/**
	 * @param computerNo 设置computerNo
	 */
	public void setComputerNo(String computerNo) {
		this.computerNo = computerNo;
	}

	/**
	 * @return 返回diskSeq
	 */
	public String getDiskSeq() {
		return diskSeq;
	}

	/**
	 * @param diskSeq 设置diskSeq
	 */
	public void setDiskSeq(String diskSeq) {
		this.diskSeq = diskSeq;
	}

	/**
	 * @return 返回isbelongKeydepartment
	 */
	public Integer getIsbelongKeydepartment() {
		return isbelongKeydepartment;
	}

	/**
	 * @param isbelongKeydepartment 设置isbelongKeydepartment
	 */
	public void setIsbelongKeydepartment(Integer isbelongKeydepartment) {
		this.isbelongKeydepartment = isbelongKeydepartment;
	}

	/**
	 * @return 返回isFanghu
	 */
	public Integer getIsFanghu() {
		return isFanghu;
	}

	/**
	 * @param isFanghu 设置isFanghu
	 */
	public void setIsFanghu(Integer isFanghu) {
		this.isFanghu = isFanghu;
	}

	/**
	 * @return 返回isWailian
	 */
	public Integer getIsWailian() {
		return isWailian;
	}

	/**
	 * @param isWailian 设置isWailian
	 */
	public void setIsWailian(Integer isWailian) {
		this.isWailian = isWailian;
	}

	/**
	 * @return 返回department
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * @param department 设置department
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}

	/**
	 * @return 返回createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime 设置createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return 返回createPerson
	 */
	public User getCreatePerson() {
		return createPerson;
	}

	/**
	 * @param createPerson 设置createPerson
	 */
	public void setCreatePerson(User createPerson) {
		this.createPerson = createPerson;
	}

	/**
	 * @return 返回modifyPerson
	 */
	public User getModifyPerson() {
		return modifyPerson;
	}

	/**
	 * @param modifyPerson 设置modifyPerson
	 */
	public void setModifyPerson(User modifyPerson) {
		this.modifyPerson = modifyPerson;
	}

	/**
	 * @return 返回modifyTime
	 */
	public Date getModifyTime() {
		return modifyTime;
	}

	/**
	 * @param modifyTime 设置modifyTime
	 */
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	/**
	 * @return 返回dataState
	 */
	public Integer getDataState() {
		return dataState;
	}

	/**
	 * @param dataState 设置dataState
	 */
	public void setDataState(Integer dataState) {
		this.dataState = dataState;
	}

	/**
	 * @return 返回createOrgan
	 */
	public Organ getCreateOrgan() {
		return createOrgan;
	}

	/**
	 * @param createOrgan 设置createOrgan
	 */
	public void setCreateOrgan(Organ createOrgan) {
		this.createOrgan = createOrgan;
	}

	/**
	 * @return 返回modifyOrgan
	 */
	public Organ getModifyOrgan() {
		return modifyOrgan;
	}

	/**
	 * @param modifyOrgan 设置modifyOrgan
	 */
	public void setModifyOrgan(Organ modifyOrgan) {
		this.modifyOrgan = modifyOrgan;
	}

	/**
	 * @return 返回secrecyStatus
	 */
	public Integer getSecrecyStatus() {
		return secrecyStatus;
	}

	/**
	 * @param secrecyStatus 设置secrecyStatus
	 */
	public void setSecrecyStatus(Integer secrecyStatus) {
		this.secrecyStatus = secrecyStatus;
	}

	/**
	 * @return 返回secrecyNetworkterminals
	 */
	public Set<SecrecyNetworkterminal> getSecrecyNetworkterminals() {
		return secrecyNetworkterminals;
	}

	/**
	 * @param secrecyNetworkterminals 设置secrecyNetworkterminals
	 */
	public void setSecrecyNetworkterminals(
			Set<SecrecyNetworkterminal> secrecyNetworkterminals) {
		this.secrecyNetworkterminals = secrecyNetworkterminals;
	}

	/**
	 * @return 返回secrecyComputerChanges
	 */
	public Set<SecrecyComputerChange> getSecrecyComputerChanges() {
		return secrecyComputerChanges;
	}

	/**
	 * @param secrecyComputerChanges 设置secrecyComputerChanges
	 */
	public void setSecrecyComputerChanges(
			Set<SecrecyComputerChange> secrecyComputerChanges) {
		this.secrecyComputerChanges = secrecyComputerChanges;
	}

	/**
	 * @return 返回secrecyComputerClears
	 */
	public Set<SecrecyComputerClear> getSecrecyComputerClears() {
		return secrecyComputerClears;
	}

	/**
	 * @param secrecyComputerClears 设置secrecyComputerClears
	 */
	public void setSecrecyComputerClears(
			Set<SecrecyComputerClear> secrecyComputerClears) {
		this.secrecyComputerClears = secrecyComputerClears;
	}

	/**
	 * @return 返回keySection
	 */
	public KeySection getKeySection() {
		return keySection;
	}

	/**
	 * @param keySection 设置keySection
	 */
	public void setKeySection(KeySection keySection) {
		this.keySection = keySection;
	}

	/**
	 * @return 返回keyPart
	 */
	public Part getKeyPart() {
		return keyPart;
	}

	/**
	 * @param keyPart 设置keyPart
	 */
	public void setKeyPart(Part keyPart) {
		this.keyPart = keyPart;
	}

	/**
	 * @return the isNetTerminal
	 */
	public Integer getIsNetTerminal() {
		return isNetTerminal;
	}

	/**
	 * @param isNetTerminal the isNetTerminal to set
	 */
	public void setIsNetTerminal(Integer isNetTerminal) {
		this.isNetTerminal = isNetTerminal;
	}

	/**
	 * @return 返回secrecyLevelStr
	 */
	public String getSecrecyLevelStr() {
		return secrecyLevelStr;
	}

	/**
	 * @param secrecyLevelStr 设置secrecyLevelStr
	 */
	public void setSecrecyLevelStr(String secrecyLevelStr) {
		this.secrecyLevelStr = secrecyLevelStr;
	}

	/**
	 * @return 返回computerTypeStr
	 */
	public String getComputerTypeStr() {
		return computerTypeStr;
	}

	/**
	 * @param computerTypeStr 设置computerTypeStr
	 */
	public void setComputerTypeStr(String computerTypeStr) {
		this.computerTypeStr = computerTypeStr;
	}
}
