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
 * SecrecyOthers
 */
public class SecrecyOthers implements java.io.Serializable {

	private static final long serialVersionUID = -6800382074486795750L;

	private String secrecyothersId;

	private Integer secrecyothersType;

	private String secrecyothersTypeTxt;

	private String secrecyothersDescription;

	private Integer secrecyLevel;

	private String secrecyLevelTxt;

	private UserInfo dutyPerson;

	private String secrecyothersNo;

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

	private Set<SecrecyOthersChange> secrecyOthersChanges = new HashSet<SecrecyOthersChange>(
			0);

	private Set<SecrecyOthersClear> secrecyOthersClears = new HashSet<SecrecyOthersClear>(
			0);

	/**
	 * 默认构�?函数
	 */
	public SecrecyOthers() {
	}

	/**
	 * @return 返回secrecyothersId
	 */
	public String getSecrecyothersId() {
		return secrecyothersId;
	}

	/**
	 * @param secrecyothersId 设置secrecyothersId
	 */
	public void setSecrecyothersId(String secrecyothersId) {
		this.secrecyothersId = secrecyothersId;
	}

	/**
	 * @return 返回secrecyothersType
	 */
	public Integer getSecrecyothersType() {
		return secrecyothersType;
	}

	/**
	 * @param secrecyothersType 设置secrecyothersType
	 */
	public void setSecrecyothersType(Integer secrecyothersType) {
		this.secrecyothersType = secrecyothersType;
	}

	/**
	 * @return 返回secrecyothersDescription
	 */
	public String getSecrecyothersDescription() {
		return secrecyothersDescription;
	}

	/**
	 * @param secrecyothersDescription 设置secrecyothersDescription
	 */
	public void setSecrecyothersDescription(String secrecyothersDescription) {
		this.secrecyothersDescription = secrecyothersDescription;
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
	 * @return 返回secrecyothersNo
	 */
	public String getSecrecyothersNo() {
		return secrecyothersNo;
	}

	/**
	 * @param secrecyothersNo 设置secrecyothersNo
	 */
	public void setSecrecyothersNo(String secrecyothersNo) {
		this.secrecyothersNo = secrecyothersNo;
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
	 * @return 返回secrecyOthersChanges
	 */
	public Set<SecrecyOthersChange> getSecrecyOthersChanges() {
		return secrecyOthersChanges;
	}

	/**
	 * @param secrecyOthersChanges 设置secrecyOthersChanges
	 */
	public void setSecrecyOthersChanges(
			Set<SecrecyOthersChange> secrecyOthersChanges) {
		this.secrecyOthersChanges = secrecyOthersChanges;
	}

	/**
	 * @return 返回secrecyOthersClears
	 */
	public Set<SecrecyOthersClear> getSecrecyOthersClears() {
		return secrecyOthersClears;
	}

	/**
	 * @param secrecyOthersClears 设置secrecyOthersClears
	 */
	public void setSecrecyOthersClears(Set<SecrecyOthersClear> secrecyOthersClears) {
		this.secrecyOthersClears = secrecyOthersClears;
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
         * @return the secrecyothersTypeTxt
         */
        public String getSecrecyothersTypeTxt() {
                return secrecyothersTypeTxt;
        }

        /**
         * @param secrecyothersTypeTxt the secrecyothersTypeTxt to set
         */
        public void setSecrecyothersTypeTxt(String secrecyothersTypeTxt) {
                this.secrecyothersTypeTxt = secrecyothersTypeTxt;
        }

        /**
         * @return the secrecyLevelTxt
         */
        public String getSecrecyLevelTxt() {
                return secrecyLevelTxt;
        }

        /**
         * @param secrecyLevelTxt the secrecyLevelTxt to set
         */
        public void setSecrecyLevelTxt(String secrecyLevelTxt) {
                this.secrecyLevelTxt = secrecyLevelTxt;
        }


}
