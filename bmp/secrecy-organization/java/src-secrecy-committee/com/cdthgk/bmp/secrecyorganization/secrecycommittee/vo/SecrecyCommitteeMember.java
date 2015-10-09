package com.cdthgk.bmp.secrecyorganization.secrecycommittee.vo;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder.In;

import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.permission.user.domain.User;

/**
 * <p>
 * 保密委成员 Vo 类
 * </p>
 * <p>
 * 汪 钟  2012-12-18 10:04
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
public class SecrecyCommitteeMember implements java.io.Serializable {

	private static final long serialVersionUID = 4896359747081663550L;

	// VO字段
	// 保密委成员表主键
	private String secrecyCommitteeMemberId;

	// 所属保密委
	private SecrecyCommittee secrecyCommittee;

	// 保密委成员
	private String person;

	//保密委成员所在单位
	private String organName;

	//保密委成员行政职务
	private String personDuty;

	//保密委成员电话
	private String personPhone;

	// 保密委员会职务
	private PersonGroupPosition personGroupPosition;

	//人员排序字段
	private Integer sort;

	// 备 注
	private String remark;

	//何时从事保密工作
	private Date secrecyWorkStart;

	//是否专职
	private Integer isSoleDuty;

	//兼任其他任务情况
	private String otherDuty;

	//行政级别
	private Integer administrativeLevel;

	//技术职称
	private Integer technicalTitle;

	//人员变动状态
	private Integer secrecyStatus;

	//性别
	private Integer sex;

	//生日
	private Date birthdate;

	//民族
	private Integer nation;

	//学历
	private Integer educationBackground;

	//学位
	private Integer degree;

	//政治面貌
	private Integer politicalStatus;

	//专业
	private Integer major;

	//行政职务
	private String adminPost;

	//部门名称
	private String departmentName;


	// 以下为通用字段
	private User createUser;
	private Organ createOrgan;
	private Date createTime;
	private User modifyUser;
	private Organ modifyOrgan;
	private Date modifyTime;
	private Integer enable;

	// 构造器
	/** 默认构造器 */
	public void AuditFlow() {
	}

	// ******************** Setter & Getter ********************
	public String getSecrecyCommitteeMemberId() {
		return secrecyCommitteeMemberId;
	}

	public void setSecrecyCommitteeMemberId(String secrecyCommitteeMemberId) {
		this.secrecyCommitteeMemberId = secrecyCommitteeMemberId;
	}

	public SecrecyCommittee getSecrecyCommittee() {
		return secrecyCommittee;
	}

	public void setSecrecyCommittee(SecrecyCommittee secrecyCommittee) {
		this.secrecyCommittee = secrecyCommittee;
	}

	/**
	 * @return the personName
	 */
	public String getPerson() {
		return person;
	}

	/**
	 * @return the organName
	 */
	public String getOrganName() {
		return organName;
	}

	/**
	 * @param personName the personName to set
	 */
	public void setPerson(String person) {
		this.person = person;
	}

	/**
	 * @param organName the organName to set
	 */
	public void setOrganName(String organName) {
		this.organName = organName;
	}

	/**
	 * @return the personDuty
	 */
	public String getPersonDuty() {
		return personDuty;
	}

	/**
	 * @return the personPhone
	 */
	public String getPersonPhone() {
		return personPhone;
	}

	/**
	 * @param personDuty the personDuty to set
	 */
	public void setPersonDuty(String personDuty) {
		this.personDuty = personDuty;
	}

	/**
	 * @param personPhone the personPhone to set
	 */
	public void setPersonPhone(String personPhone) {
		this.personPhone = personPhone;
	}

	public PersonGroupPosition getPersonGroupPosition() {
		return personGroupPosition;
	}

	public void setPersonGroupPosition(PersonGroupPosition personGroupPosition) {
		this.personGroupPosition = personGroupPosition;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	public Organ getCreateOrgan() {
		return createOrgan;
	}

	public void setCreateOrgan(Organ createOrgan) {
		this.createOrgan = createOrgan;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public User getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(User modifyUser) {
		this.modifyUser = modifyUser;
	}

	public Organ getModifyOrgan() {
		return modifyOrgan;
	}

	public void setModifyOrgan(Organ modifyOrgan) {
		this.modifyOrgan = modifyOrgan;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Integer getEnable() {
		return enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	/**
	 * @return the sort
	 */
	public Integer getSort() {
		return sort;
	}

	/**
	 * @param sort the sort to set
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 * @return the secrecyWorkStart
	 */
	public Date getSecrecyWorkStart() {
		return secrecyWorkStart;
	}

	/**
	 * @param secrecyWorkStart the secrecyWorkStart to set
	 */
	public void setSecrecyWorkStart(Date secrecyWorkStart) {
		this.secrecyWorkStart = secrecyWorkStart;
	}

	/**
	 * @return the isSoleDuty
	 */
	public Integer getIsSoleDuty() {
		return isSoleDuty;
	}

	/**
	 * @param isSoleDuty the isSoleDuty to set
	 */
	public void setIsSoleDuty(Integer isSoleDuty) {
		this.isSoleDuty = isSoleDuty;
	}

	/**
	 * @return the otherDuty
	 */
	public String getOtherDuty() {
		return otherDuty;
	}

	/**
	 * @param otherDuty the otherDuty to set
	 */
	public void setOtherDuty(String otherDuty) {
		this.otherDuty = otherDuty;
	}

	/**
	 * @return the administrativeLevel
	 */
	public Integer getAdministrativeLevel() {
		return administrativeLevel;
	}

	/**
	 * @param administrativeLevel the administrativeLevel to set
	 */
	public void setAdministrativeLevel(Integer administrativeLevel) {
		this.administrativeLevel = administrativeLevel;
	}

	/**
	 * @return the secrecyStatus
	 */
	public Integer getSecrecyStatus() {
		return secrecyStatus;
	}

	/**
	 * @param secrecyStatus the secrecyStatus to set
	 */
	public void setSecrecyStatus(Integer secrecyStatus) {
		this.secrecyStatus = secrecyStatus;
	}

	/**
	 * @return the sex
	 */
	public Integer getSex() {
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}

	/**
	 * @return the birthdate
	 */
	public Date getBirthdate() {
		return birthdate;
	}

	/**
	 * @param birthdate the birthdate to set
	 */
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	/**
	 * @return the nation
	 */
	public Integer getNation() {
		return nation;
	}

	/**
	 * @param nation the nation to set
	 */
	public void setNation(Integer nation) {
		this.nation = nation;
	}

	/**
	 * @return the educationBackground
	 */
	public Integer getEducationBackground() {
		return educationBackground;
	}

	/**
	 * @param educationBackground the educationBackground to set
	 */
	public void setEducationBackground(Integer educationBackground) {
		this.educationBackground = educationBackground;
	}

	/**
	 * @return the degree
	 */
	public Integer getDegree() {
		return degree;
	}

	/**
	 * @param degree the degree to set
	 */
	public void setDegree(Integer degree) {
		this.degree = degree;
	}

	/**
	 * @return the politicalStatus
	 */
	public Integer getPoliticalStatus() {
		return politicalStatus;
	}

	/**
	 * @param politicalStatus the politicalStatus to set
	 */
	public void setPoliticalStatus(Integer politicalStatus) {
		this.politicalStatus = politicalStatus;
	}

	/**
	 * @return the major
	 */
	public Integer getMajor() {
		return major;
	}

	/**
	 * @param major the major to set
	 */
	public void setMajor(Integer major) {
		this.major = major;
	}

	/**
	 * @return the adminPost
	 */
	public String getAdminPost() {
		return adminPost;
	}

	/**
	 * @param adminPost the adminPost to set
	 */
	public void setAdminPost(String adminPost) {
		this.adminPost = adminPost;
	}

	/**
	 * @return the departmentName
	 */
	public String getDepartmentName() {
		return departmentName;
	}

	/**
	 * @param departmentName the departmentName to set
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	/**
	 * @return the technicalTitle
	 */
	public Integer getTechnicalTitle() {
		return technicalTitle;
	}

	/**
	 * @param technicalTitle the technicalTitle to set
	 */
	public void setTechnicalTitle(Integer technicalTitle) {
		this.technicalTitle = technicalTitle;
	}


}