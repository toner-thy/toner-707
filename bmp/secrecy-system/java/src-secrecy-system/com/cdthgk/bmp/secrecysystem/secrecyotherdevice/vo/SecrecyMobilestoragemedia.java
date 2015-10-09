package com.cdthgk.bmp.secrecysystem.secrecyotherdevice.vo;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.cdthgk.bmp.keyPart.vo.Part;
import com.cdthgk.bmp.keysection.vo.KeySection;
import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;
import com.cdthgk.platform.permission.user.domain.User;

/**
 * SecrecyMobilestoragemedia
 */
public class SecrecyMobilestoragemedia implements java.io.Serializable {

	private static final long serialVersionUID = -1515513114609758633L;

	private String secrecymobilestoragemediaId;

	private Integer mediaType;

	private String mediaDescription;

	private Integer secrecyLevel;

	private UserInfo dutyPerson;

	private String mediaNo;

	private String mediaSeq;

	private Integer isbelongKeydepartment;

	private Department department;

	private Part keyPart;

	private KeySection keySection;

	private User createPerson;

	private Date createTime;

	private User modifyPerson;

	private Date modifyTime;

	private Integer dataState;

	private Organ createOrgan;

	private Organ modifyOrgan;

	private Integer secrecyStatus;

	private Set<SecrecyMobilestoragemediaClear> secrecyMobilestoragemediaClears = new HashSet<SecrecyMobilestoragemediaClear>(
			0);

	private Set<SecrecyMobilestoragemediaChange> secrecyMobilestoragemediaChanges = new HashSet<SecrecyMobilestoragemediaChange>(
			0);

	// 只有导出word时使用的数据字典属性
	private String mediaTypeStr;
	private String secrecyLevelStr;
	/**
	 * 默认构�?函数
	 */
	public SecrecyMobilestoragemedia() {
	}


	/**
	 * @return 返回secrecymobilestoragemediaId
	 */
	public String getSecrecymobilestoragemediaId() {
		return secrecymobilestoragemediaId;
	}

	/**
	 * @param secrecymobilestoragemediaId 设置secrecymobilestoragemediaId
	 */
	public void setSecrecymobilestoragemediaId(String secrecymobilestoragemediaId) {
		this.secrecymobilestoragemediaId = secrecymobilestoragemediaId;
	}

	/**
	 * @return 返回mediaType
	 */
	public Integer getMediaType() {
		return mediaType;
	}

	/**
	 * @param mediaType 设置mediaType
	 */
	public void setMediaType(Integer mediaType) {
		this.mediaType = mediaType;
	}

	/**
	 * @return 返回mediaDescription
	 */
	public String getMediaDescription() {
		return mediaDescription;
	}

	/**
	 * @param mediaDescription 设置mediaDescription
	 */
	public void setMediaDescription(String mediaDescription) {
		this.mediaDescription = mediaDescription;
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
	 * @return 返回mediaNo
	 */
	public String getMediaNo() {
		return mediaNo;
	}

	/**
	 * @param mediaNo 设置mediaNo
	 */
	public void setMediaNo(String mediaNo) {
		this.mediaNo = mediaNo;
	}

	/**
	 * @return 返回mediaSeq
	 */
	public String getMediaSeq() {
		return mediaSeq;
	}

	/**
	 * @param mediaSeq 设置mediaSeq
	 */
	public void setMediaSeq(String mediaSeq) {
		this.mediaSeq = mediaSeq;
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
	 * @return 返回secrecyMobilestoragemediaClears
	 */
	public Set<SecrecyMobilestoragemediaClear> getSecrecyMobilestoragemediaClears() {
		return secrecyMobilestoragemediaClears;
	}

	/**
	 * @param secrecyMobilestoragemediaClears 设置secrecyMobilestoragemediaClears
	 */
	public void setSecrecyMobilestoragemediaClears(
			Set<SecrecyMobilestoragemediaClear> secrecyMobilestoragemediaClears) {
		this.secrecyMobilestoragemediaClears = secrecyMobilestoragemediaClears;
	}

	/**
	 * @return 返回secrecyMobilestoragemediaChanges
	 */
	public Set<SecrecyMobilestoragemediaChange> getSecrecyMobilestoragemediaChanges() {
		return secrecyMobilestoragemediaChanges;
	}

	/**
	 * @param secrecyMobilestoragemediaChanges 设置secrecyMobilestoragemediaChanges
	 */
	public void setSecrecyMobilestoragemediaChanges(
			Set<SecrecyMobilestoragemediaChange> secrecyMobilestoragemediaChanges) {
		this.secrecyMobilestoragemediaChanges = secrecyMobilestoragemediaChanges;
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
	 * @return the dutyPerson
	 */
	public UserInfo getDutyPerson() {
		return dutyPerson;
	}


	/**
	 * @param dutyPerson the dutyPerson to set
	 */
	public void setDutyPerson(UserInfo dutyPerson) {
		this.dutyPerson = dutyPerson;
	}


	/**
	 * @return the keyPart
	 */
	public Part getKeyPart() {
		return keyPart;
	}


	/**
	 * @param keyPart the keyPart to set
	 */
	public void setKeyPart(Part keyPart) {
		this.keyPart = keyPart;
	}


	/**
	 * @return the keySection
	 */
	public KeySection getKeySection() {
		return keySection;
	}


	/**
	 * @param keySection the keySection to set
	 */
	public void setKeySection(KeySection keySection) {
		this.keySection = keySection;
	}


	/**
	 * @return 返回mediaTypeStr
	 */
	public String getMediaTypeStr() {
		return mediaTypeStr;
	}


	/**
	 * @param mediaTypeStr 设置mediaTypeStr
	 */
	public void setMediaTypeStr(String mediaTypeStr) {
		this.mediaTypeStr = mediaTypeStr;
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
}
