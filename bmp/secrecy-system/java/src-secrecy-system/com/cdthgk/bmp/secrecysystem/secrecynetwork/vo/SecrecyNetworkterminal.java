package com.cdthgk.bmp.secrecysystem.secrecynetwork.vo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.cdthgk.bmp.secrecysystem.secrecycomputer.vo.SecrecyComputer;
import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;
import com.cdthgk.platform.permission.user.domain.User;

/**
 * SecrecyNetworkterminal
 */
public class SecrecyNetworkterminal implements java.io.Serializable {

	private static final long serialVersionUID = 4257092167820583287L;

	private String secrecynetworkterminalId;

	private SecrecyNetwork secrecyNetwork;

	private SecrecyComputer secrecyComputer;

	private UserInfo dutyPerson;

	private Integer secrecyLevel;

	private Integer computerType;

	private String ipAddress;

	private String macAddress;

	private Integer isbelongKeydepartment;

	private Integer isFanghu;

	private Integer isWailian;

	private Department department;

	private User createPerson;

	private Date createTime;

	private User modifyPerson;

	private Date modifyTime;

	private Integer dataState;

	private Date joinNetworkDate;

	private String joinNetworkReason;

	private Date removeNetworkDate;

	private String removeNetworkReason;

	private Integer joinNetworkStatus;

	private Organ createOrgan;

	private Organ modifyOrgan;

	private Integer secrecyStatus;

	private Set<SecrecyNetworkterminalClear> secrecyNetworkterminalClears = new HashSet<SecrecyNetworkterminalClear>(
			0);

	private Set<SecrecyNetworkterminalChange> secrecyNetworkterminalChanges = new HashSet<SecrecyNetworkterminalChange>(
			0);

	/**
	 * 默认构�?函数
	 */
	public SecrecyNetworkterminal() {
	}

	/**
	 * @return 返回secrecynetworkterminalId
	 */
	public String getSecrecynetworkterminalId() {
		return secrecynetworkterminalId;
	}

	/**
	 * @param secrecynetworkterminalId 设置secrecynetworkterminalId
	 */
	public void setSecrecynetworkterminalId(String secrecynetworkterminalId) {
		this.secrecynetworkterminalId = secrecynetworkterminalId;
	}

	/**
	 * @return 返回secrecyNetwork
	 */
	public SecrecyNetwork getSecrecyNetwork() {
		return secrecyNetwork;
	}

	/**
	 * @param secrecyNetwork 设置secrecyNetwork
	 */
	public void setSecrecyNetwork(SecrecyNetwork secrecyNetwork) {
		this.secrecyNetwork = secrecyNetwork;
	}

	/**
	 * @return 返回secrecyComputer
	 */
	public SecrecyComputer getSecrecyComputer() {
		return secrecyComputer;
	}

	/**
	 * @param secrecyComputer 设置secrecyComputer
	 */
	public void setSecrecyComputer(SecrecyComputer secrecyComputer) {
		this.secrecyComputer = secrecyComputer;
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
	 * @return 返回ipAddress
	 */
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * @param ipAddress 设置ipAddress
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	/**
	 * @return 返回macAddress
	 */
	public String getMacAddress() {
		return macAddress;
	}

	/**
	 * @param macAddress 设置macAddress
	 */
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
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
	 * @param modifyPerson the modifyPerson to set
	 */
	public void setModifyPerson(User modifyPerson) {
		this.modifyPerson = modifyPerson;
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
	 * @return the modifyPerson
	 */
	public User getModifyPerson() {
		return modifyPerson;
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
	 * @return 返回joinNetworkDate
	 */
	public Date getJoinNetworkDate() {
		return joinNetworkDate;
	}

	/**
	 * @param joinNetworkDate 设置joinNetworkDate
	 */
	public void setJoinNetworkDate(Date joinNetworkDate) {
		this.joinNetworkDate = joinNetworkDate;
	}

	/**
	 * @return 返回joinNetworkReason
	 */
	public String getJoinNetworkReason() {
		return joinNetworkReason;
	}

	/**
	 * @param joinNetworkReason 设置joinNetworkReason
	 */
	public void setJoinNetworkReason(String joinNetworkReason) {
		this.joinNetworkReason = joinNetworkReason;
	}

	/**
	 * @return 返回removeNetworkDate
	 */
	public Date getRemoveNetworkDate() {
		return removeNetworkDate;
	}

	/**
	 * @param removeNetworkDate 设置removeNetworkDate
	 */
	public void setRemoveNetworkDate(Date removeNetworkDate) {
		this.removeNetworkDate = removeNetworkDate;
	}

	/**
	 * @return 返回removeNetworkReason
	 */
	public String getRemoveNetworkReason() {
		return removeNetworkReason;
	}

	/**
	 * @param removeNetworkReason 设置removeNetworkReason
	 */
	public void setRemoveNetworkReason(String removeNetworkReason) {
		this.removeNetworkReason = removeNetworkReason;
	}

	/**
	 * @return 返回joinNetworkStatus
	 */
	public Integer getJoinNetworkStatus() {
		return joinNetworkStatus;
	}

	/**
	 * @param joinNetworkStatus 设置joinNetworkStatus
	 */
	public void setJoinNetworkStatus(Integer joinNetworkStatus) {
		this.joinNetworkStatus = joinNetworkStatus;
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
	 * @return 返回secrecyNetworkterminalClears
	 */
	public Set<SecrecyNetworkterminalClear> getSecrecyNetworkterminalClears() {
		return secrecyNetworkterminalClears;
	}

	/**
	 * @param secrecyNetworkterminalClears 设置secrecyNetworkterminalClears
	 */
	public void setSecrecyNetworkterminalClears(
			Set<SecrecyNetworkterminalClear> secrecyNetworkterminalClears) {
		this.secrecyNetworkterminalClears = secrecyNetworkterminalClears;
	}

	/**
	 * @return 返回secrecyNetworkterminalChanges
	 */
	public Set<SecrecyNetworkterminalChange> getSecrecyNetworkterminalChanges() {
		return secrecyNetworkterminalChanges;
	}

	/**
	 * @param secrecyNetworkterminalChanges 设置secrecyNetworkterminalChanges
	 */
	public void setSecrecyNetworkterminalChanges(
			Set<SecrecyNetworkterminalChange> secrecyNetworkterminalChanges) {
		this.secrecyNetworkterminalChanges = secrecyNetworkterminalChanges;
	}
}
