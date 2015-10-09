package com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.cdthgk.platform.attachment.domain.Attachment;
import com.cdthgk.platform.base.util.ReportState;
import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;
import com.cdthgk.platform.permission.user.domain.User;

/**
 * <p>
 * 保密工作机构 Vo 类
 * </p>
 * <p>
 * 刘椿成  2012-12-29 17:24
 * </p>
 * <blockquote>
 * <h4>历史修改记录</h4>
 * <ul>
 * <li>修改人 修改时间 修改描述
 * </ul>
 * </blockquote>
 * <p>
 * copyright taohy 2012, all rights reserved.
 * </p>
 * @author liucc
 * @since 1.0
 * @version 1.0
 */
public class SecrecyWorkOrgan extends ReportState implements java.io.Serializable {

	private static final long serialVersionUID = -8952354848877354411L;

	// Fields
	// 保密组织机构ID
	private String secrecyWorkOrganId;
	// 编号
	private String code;
	// 名称
	private String name;
	// 成立日期
	private Date setupDate;
	// 调整日期
	private Date adjustDate;
	// 组织机构描述
	private String organDesc;
	// 保密组织机构
	private String secrecyOrgan;
	// 保密组织机构负责人
	private UserInfo organPrincipal;
	// 负责人电话
	private String principalPhone;
	// 负责人行政职务
	private String principalDuty;
	// 分组类型
	private Integer groupType;
	// 所在单位 外键
	private Organ organ;
	// 发文号
	private String docNo;
	// 保密办设在
	private Department department;
	// 职责与成员分工
	private String dutyMemberWork;

	//机构类型
	private Integer organType;

	//行政级别
	private Integer organAdminLevel;

	//负责人行政级别
	private Integer organAdminPostLevel;

	//编制人数
	private Integer establishmentNum;

	// 保密办下成员关系
	private Set<SecrecyWorkOrganMemberUnit> secrecyWorkOrganMemberUnitSet = new HashSet<SecrecyWorkOrganMemberUnit>();
	// 保密工作机构成员
	private Set<SecrecyWorkOrganRelationMember> personGroupRelations = new HashSet<SecrecyWorkOrganRelationMember>();

	private List<Attachment> attachmentList = new ArrayList<Attachment>();

	// 表单使用字段
	private String oldDeptId;
	private String oldUserId;
	private String telephone;
	private String address;
	private String fax;
	private String zipCode;
	private Date createTime;
	private User createPerson;
	private User modifyPerson;
	private Date modifyTime;

	// ******************** Setter & Getter ********************
	/**
	 * 返回secrecyWorkOrganId
	 * @return secrecyWorkOrganId
	 */
	public String getSecrecyWorkOrganId() {
		return secrecyWorkOrganId;
	}
	/**
	 * 设置secrecyWorkOrganId
	 * @param secrecyWorkOrganId secrecyWorkOrganId
	 */
	public void setSecrecyWorkOrganId(String secrecyWorkOrganId) {
		this.secrecyWorkOrganId = secrecyWorkOrganId;
	}
	/**
	 * 返回code
	 * @return code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置code
	 * @param code code
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 返回name
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置name
	 * @param name name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 返回setupDate
	 * @return setupDate
	 */
	public Date getSetupDate() {
		return setupDate;
	}
	/**
	 * 设置setupDate
	 * @param setupDate setupDate
	 */
	public void setSetupDate(Date setupDate) {
		this.setupDate = setupDate;
	}
	/**
	 * 返回adjustDate
	 * @return adjustDate
	 */
	public Date getAdjustDate() {
		return adjustDate;
	}
	/**
	 * 设置adjustDate
	 * @param adjustDate adjustDate
	 */
	public void setAdjustDate(Date adjustDate) {
		this.adjustDate = adjustDate;
	}
	/**
	 * 返回organDesc
	 * @return organDesc
	 */
	public String getOrganDesc() {
		return organDesc;
	}
	/**
	 * 设置organDesc
	 * @param organDesc organDesc
	 */
	public void setOrganDesc(String organDesc) {
		this.organDesc = organDesc;
	}
	/**
	 * 返回secrecyOrgan
	 * @return secrecyOrgan
	 */
	public String getSecrecyOrgan() {
		return secrecyOrgan;
	}
	/**
	 * 设置secrecyOrgan
	 * @param secrecyOrgan secrecyOrgan
	 */
	public void setSecrecyOrgan(String secrecyOrgan) {
		this.secrecyOrgan = secrecyOrgan;
	}

	/**
	 * 返回organPrincipal
	 * @return organPrincipal
	 */
	public UserInfo getOrganPrincipal() {
		return organPrincipal;
	}
	/**
	 * 设置organPrincipal
	 * @param organPrincipal organPrincipal
	 */
	public void setOrganPrincipal(UserInfo organPrincipal) {
		this.organPrincipal = organPrincipal;
	}
	/**
	 * 返回principalPhone
	 * @return principalPhone
	 */
	public String getPrincipalPhone() {
		return principalPhone;
	}
	/**
	 * 设置principalPhone
	 * @param principalPhone principalPhone
	 */
	public void setPrincipalPhone(String principalPhone) {
		this.principalPhone = principalPhone;
	}
	/**
	 * 返回principalDuty
	 * @return principalDuty
	 */
	public String getPrincipalDuty() {
		return principalDuty;
	}
	/**
	 * 设置principalDuty
	 * @param principalDuty principalDuty
	 */
	public void setPrincipalDuty(String principalDuty) {
		this.principalDuty = principalDuty;
	}
	/**
	 * 返回groupType
	 * @return groupType
	 */
	public Integer getGroupType() {
		return groupType;
	}
	/**
	 * 设置groupType
	 * @param groupType groupType
	 */
	public void setGroupType(Integer groupType) {
		this.groupType = groupType;
	}
	/**
	 * 返回organ
	 * @return organ
	 */
	public Organ getOrgan() {
		return organ;
	}
	/**
	 * 设置organ
	 * @param organ organ
	 */
	public void setOrgan(Organ organ) {
		this.organ = organ;
	}
	/**
	 * 返回docNo
	 * @return docNo
	 */
	public String getDocNo() {
		return docNo;
	}
	/**
	 * 设置docNo
	 * @param docNo docNo
	 */
	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}
	/**
	 * 返回department
	 * @return department
	 */
	public Department getDepartment() {
		return department;
	}
	/**
	 * 设置department
	 * @param department department
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}
	/**
	 * 返回dutyMemberWork
	 * @return dutyMemberWork
	 */
	public String getDutyMemberWork() {
		return dutyMemberWork;
	}
	/**
	 * 设置dutyMemberWork
	 * @param dutyMemberWork dutyMemberWork
	 */
	public void setDutyMemberWork(String dutyMemberWork) {
		this.dutyMemberWork = dutyMemberWork;
	}
	/**
	 * 返回telephone
	 * @return telephone
	 */
	public String getTelephone() {
		return telephone;
	}
	/**
	 * 设置telephone
	 * @param telephone telephone
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	/**
	 * 返回address
	 * @return address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置address
	 * @param address address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 返回fax
	 * @return fax
	 */
	public String getFax() {
		return fax;
	}
	/**
	 * 设置fax
	 * @param fax fax
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}
	/**
	 * 返回zipCode
	 * @return zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}
	/**
	 * 设置zipCode
	 * @param zipCode zipCode
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	/**
	 * 返回createTime
	 * @return createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置createTime
	 * @param createTime createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 返回createPerson
	 * @return createPerson
	 */
	public User getCreatePerson() {
		return createPerson;
	}
	/**
	 * 设置createPerson
	 * @param createPerson createPerson
	 */
	public void setCreatePerson(User createPerson) {
		this.createPerson = createPerson;
	}
	/**
	 * 返回modifyPerson
	 * @return modifyPerson
	 */
	public User getModifyPerson() {
		return modifyPerson;
	}
	/**
	 * 设置modifyPerson
	 * @param modifyPerson modifyPerson
	 */
	public void setModifyPerson(User modifyPerson) {
		this.modifyPerson = modifyPerson;
	}
	/**
	 * 返回modifyTime
	 * @return modifyTime
	 */
	public Date getModifyTime() {
		return modifyTime;
	}
	/**
	 * 设置modifyTime
	 * @param modifyTime modifyTime
	 */
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	/**
	 * 返回secrecyWorkOrganMemberUnitSet
	 * @return secrecyWorkOrganMemberUnitSet
	 */
	public Set<SecrecyWorkOrganMemberUnit> getSecrecyWorkOrganMemberUnitSet() {
		return secrecyWorkOrganMemberUnitSet;
	}
	/**
	 * 设置secrecyWorkOrganMemberUnitSet
	 * @param secrecyWorkOrganMemberUnitSet secrecyWorkOrganMemberUnitSet
	 */
	public void setSecrecyWorkOrganMemberUnitSet(
			Set<SecrecyWorkOrganMemberUnit> secrecyWorkOrganMemberUnitSet) {
		this.secrecyWorkOrganMemberUnitSet = secrecyWorkOrganMemberUnitSet;
	}
	/**
	 * 返回oldDeptId
	 * @return oldDeptId
	 */
	public String getOldDeptId() {
		return oldDeptId;
	}
	/**
	 * 设置oldDeptId
	 * @param oldDeptId oldDeptId
	 */
	public void setOldDeptId(String oldDeptId) {
		this.oldDeptId = oldDeptId;
	}
	/**
	 * 返回oldUserId
	 * @return oldUserId
	 */
	public String getOldUserId() {
		return oldUserId;
	}
	/**
	 * 设置oldUserId
	 * @param oldUserId oldUserId
	 */
	public void setOldUserId(String oldUserId) {
		this.oldUserId = oldUserId;
	}
	/**
	 * @return 返回personGroupRelations
	 */
	public Set<SecrecyWorkOrganRelationMember> getPersonGroupRelations() {
		return personGroupRelations;
	}
	/**
	 * @param personGroupRelations 设置personGroupRelations
	 */
	public void setPersonGroupRelations(
			Set<SecrecyWorkOrganRelationMember> personGroupRelations) {
		this.personGroupRelations = personGroupRelations;
	}
	/**
	 * @return 返回attachmentList
	 */
	public List<Attachment> getAttachmentList() {
		return attachmentList;
	}
	/**
	 * @param attachmentList 设置attachmentList
	 */
	public void setAttachmentList(List<Attachment> attachmentList) {
		this.attachmentList = attachmentList;
	}
	/**
	 * @return the organType
	 */
	public Integer getOrganType() {
		return organType;
	}
	/**
	 * @param organType the organType to set
	 */
	public void setOrganType(Integer organType) {
		this.organType = organType;
	}
	/**
	 * @return the organAdminLevel
	 */
	public Integer getOrganAdminLevel() {
		return organAdminLevel;
	}
	/**
	 * @param organAdminLevel the organAdminLevel to set
	 */
	public void setOrganAdminLevel(Integer organAdminLevel) {
		this.organAdminLevel = organAdminLevel;
	}
	/**
	 * @return the organAdminPostLeve
	 */
	public Integer getOrganAdminPostLevel() {
		return organAdminPostLevel;
	}
	/**
	 * @param organAdminPostLeve the organAdminPostLeve to set
	 */
	public void setOrganAdminPostLevel(Integer organAdminPostLevel) {
		this.organAdminPostLevel = organAdminPostLevel;
	}
	/**
	 * @return the establishmentNum
	 */
	public Integer getEstablishmentNum() {
		return establishmentNum;
	}
	/**
	 * @param establishmentNum the establishmentNum to set
	 */
	public void setEstablishmentNum(Integer establishmentNum) {
		this.establishmentNum = establishmentNum;
	}

}