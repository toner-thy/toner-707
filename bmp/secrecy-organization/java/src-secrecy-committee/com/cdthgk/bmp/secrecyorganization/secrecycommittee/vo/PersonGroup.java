package com.cdthgk.bmp.secrecyorganization.secrecycommittee.vo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.cdthgk.exchange.vo.Reportable;
import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.permission.user.domain.User;

/**
 * <p>
 * 成员组 Vo 类
 * </p>
 * <p>
 * 汪 钟 2012-12-18 14:59
 * </p>
 * <blockquote>
 * <h4>历史修改记录</h4>
 * <ul>
 * <li>修改人 修改时间 修改描述
 * </ul>
 * </blockquote>
 * <p>
 * copyright tianyu 2012, all rights reserved.
 * </p>
 *
 * @author FastCodeingTools
 * @author tianyu
 * @since 1.0
 * @version 1.0
 */
public class PersonGroup implements Reportable, java.io.Serializable {

	private static final long serialVersionUID = -8952354848877354411L;

	// Fields
	private String personGroupId;
	private String code;
	private String name;
	private Date setupDate;
	private Date adjustDate;
	private String organDesc;
	private Organ organ;
	private Department department;
	private Date createTime;
	private User createPerson;
	private User modifyPerson;
	private Date modifyTime;
	private String secrecyOrgan;
	private String organPrincipal;
	private String principalPhone;
	private String principalDuty;
	private Integer groupType;
	private String telephone;
	private String address;
	private String fax;
	private String zipCode;
	private String website;
	private Set<PersonGroupRelation> personGroupRelations = new HashSet<PersonGroupRelation>();
	private Date reportTime;
	private Date inceptTime;
	private Integer reportState;
	private Set<Organ> receiveOrgans = new HashSet<Organ>();
	//通过传输状态
	private Integer transmitState;
	// 1 全部成功
	public static final Integer SEND_SUCCESS = 1;
	// 2 失败
	public static final Integer SEND_FAILURE = 0;

	//上报字段
	private String reportOrgan;
	private Date reportOrganTime;
	private Date inceptOrganTime;
	private Integer reportOrganState;

	// ******************** Setter & Getter ********************
	public String getReportOrgan() {
		return reportOrgan;
	}
	public void setReportOrgan(String reportOrgan) {
		this.reportOrgan = reportOrgan;
	}

	public Date getReportOrganTime() {
		return reportOrganTime;
	}
	public void setReportOrganTime(Date reportOrganTime) {
		this.reportOrganTime = reportOrganTime;
	}

	public Date getInceptOrganTime() {
		return inceptOrganTime;
	}
	public void setInceptOrganTime(Date inceptOrganTime) {
		this.inceptOrganTime = inceptOrganTime;
	}

	public Integer getReportOrganState() {
		return reportOrganState;
	}
	public void setReportOrganState(Integer reportOrganState) {
		this.reportOrganState = reportOrganState;
	}

	public Integer getTransmitState() {
		return transmitState;
	}
	public void setTransmitState(Integer transmitState) {
		this.transmitState = transmitState;
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

	public Integer getReportState() {
		return reportState;
	}
	public void setReportState(Integer reportState) {
		this.reportState = reportState;
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}

	public String getPersonGroupId() {
		return personGroupId;
	}

	public void setPersonGroupId(String personGroupId) {
		this.personGroupId = personGroupId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getSetupDate() {
		return setupDate;
	}

	public void setSetupDate(Date setupDate) {
		this.setupDate = setupDate;
	}

	public Date getAdjustDate() {
		return adjustDate;
	}

	public void setAdjustDate(Date adjustDate) {
		this.adjustDate = adjustDate;
	}

	public String getOrganDesc() {
		return organDesc;
	}

	public void setOrganDesc(String organDesc) {
		this.organDesc = organDesc;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public User getCreatePerson() {
		return createPerson;
	}

	public void setCreatePerson(User createPerson) {
		this.createPerson = createPerson;
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

	public String getSecrecyOrgan() {
		return secrecyOrgan;
	}

	public void setSecrecyOrgan(String secrecyOrgan) {
		this.secrecyOrgan = secrecyOrgan;
	}

	public String getOrganPrincipal() {
		return organPrincipal;
	}

	public void setOrganPrincipal(String organPrincipal) {
		this.organPrincipal = organPrincipal;
	}

	public String getPrincipalPhone() {
		return principalPhone;
	}

	public void setPrincipalPhone(String principalPhone) {
		this.principalPhone = principalPhone;
	}

	public String getPrincipalDuty() {
		return principalDuty;
	}

	public void setPrincipalDuty(String principalDuty) {
		this.principalDuty = principalDuty;
	}

	public Integer getGroupType() {
		return groupType;
	}

	public void setGroupType(Integer groupType) {
		this.groupType = groupType;
	}

	public Set<PersonGroupRelation> getPersonGroupRelations() {
		return personGroupRelations;
	}

	public void setPersonGroupRelations(Set<PersonGroupRelation> personGroupRelations) {
		this.personGroupRelations = personGroupRelations;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Set<Organ> getReceiveOrgans() {
		return receiveOrgans;
	}
	public void setReceiveOrgans(Set<Organ> receiveOrgans) {
		this.receiveOrgans = receiveOrgans;
	}
}