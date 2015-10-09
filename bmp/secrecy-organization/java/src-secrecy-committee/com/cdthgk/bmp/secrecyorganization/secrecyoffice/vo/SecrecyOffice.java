package com.cdthgk.bmp.secrecyorganization.secrecyoffice.vo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.cdthgk.bmp.secrecyorganization.secrecycommittee.vo.SecrecyCommittee;
import com.cdthgk.platform.base.util.ReportState;
import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;
import com.cdthgk.platform.permission.user.domain.User;

/**
 * <p>
 * 保密办（保密局） Vo 类
 * </p>
 * <p>
 * 陶汇源  2012-12-24 17:07
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
 *
 * @author FastCodeingTools
 * @author taohy
 * @since 1.0
 * @version 1.0
 */
public class SecrecyOffice extends ReportState implements java.io.Serializable {

	private static final long serialVersionUID = 4896359747081663550L;

	// VO字段
	// 保密办（保密局）表主键
	private String secrecyOfficeId;
	// 保密委
	private SecrecyCommittee secrecyCommittee;
	// 单位名称
	private String name;
	// 主任(局长)
	private UserInfo director;
	// 办公室
	private Department dept;
	// 负责人
	private UserInfo person;
	// 联系电话
	private String telephone;
	// 传 真
	private String fax;
	// 邮 编
	private String postcode;
	// 地 址
	private String address;
	// 职责与成员分工
	private String dutyMemberWork;

	// 主管单位
	private Organ mainOrgan;
	// 成立时间
	private Date establishTime;
	// 行政级别
	private Integer administrativeLevel;
	// 经费来源
	private String fundsSource;
	// 是否政府序列
	private Integer govSequence;
	// 机构类别
	private Integer organType;

	private Set<SecrecyOfficeMember> secrecyOfficeMemberSet = new HashSet<SecrecyOfficeMember>();

	// 编制情况
	private Set<EmployPerson> employPersonSet = new HashSet<EmployPerson>();
	private Set<EstablishSituation> establishSituationSet = new HashSet<EstablishSituation>();
	private Set<LeaderStaff> leaderStaffSet = new HashSet<LeaderStaff>();

	// 内设机构
	private Set<InternalOrgan> internalOrganSet = new HashSet<InternalOrgan>();
	// 基础设施建设
	private Set<Infrastructure> infrastructureSet = new HashSet<Infrastructure>();

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
	public SecrecyOffice() {
	}

	// ******************** Setter & Getter ********************
	public String getSecrecyOfficeId() {
		return secrecyOfficeId;
	}

	public void setSecrecyOfficeId(String secrecyOfficeId) {
		this.secrecyOfficeId = secrecyOfficeId;
	}

	public SecrecyCommittee getSecrecyCommittee() {
		return secrecyCommittee;
	}

	public void setSecrecyCommittee(SecrecyCommittee secrecyCommittee) {
		this.secrecyCommittee = secrecyCommittee;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UserInfo getDirector() {
		return director;
	}

	public void setDirector(UserInfo director) {
		this.director = director;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public UserInfo getPerson() {
		return person;
	}

	public void setPerson(UserInfo person) {
		this.person = person;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDutyMemberWork() {
		return dutyMemberWork;
	}

	public void setDutyMemberWork(String dutyMemberWork) {
		this.dutyMemberWork = dutyMemberWork;
	}

	public Set<SecrecyOfficeMember> getSecrecyOfficeMemberSet() {
		return secrecyOfficeMemberSet;
	}

	public void setSecrecyOfficeMemberSet(
			Set<SecrecyOfficeMember> secrecyOfficeMemberSet) {
		this.secrecyOfficeMemberSet = secrecyOfficeMemberSet;
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
	 * @return 返回mainOrgan
	 */
	public Organ getMainOrgan() {
		return mainOrgan;
	}

	/**
	 * @param mainOrgan 设置mainOrgan
	 */
	public void setMainOrgan(Organ mainOrgan) {
		this.mainOrgan = mainOrgan;
	}

	/**
	 * @return 返回establishTime
	 */
	public Date getEstablishTime() {
		return establishTime;
	}

	/**
	 * @param establishTime 设置establishTime
	 */
	public void setEstablishTime(Date establishTime) {
		this.establishTime = establishTime;
	}

	/**
	 * @return 返回administrativeLevel
	 */
	public Integer getAdministrativeLevel() {
		return administrativeLevel;
	}

	/**
	 * @param administrativeLevel 设置administrativeLevel
	 */
	public void setAdministrativeLevel(Integer administrativeLevel) {
		this.administrativeLevel = administrativeLevel;
	}

	/**
	 * @return 返回fundsSource
	 */
	public String getFundsSource() {
		return fundsSource;
	}

	/**
	 * @param fundsSource 设置fundsSource
	 */
	public void setFundsSource(String fundsSource) {
		this.fundsSource = fundsSource;
	}

	/**
	 * @return 返回govSequence
	 */
	public Integer getGovSequence() {
		return govSequence;
	}

	/**
	 * @param govSequence 设置govSequence
	 */
	public void setGovSequence(Integer govSequence) {
		this.govSequence = govSequence;
	}

	/**
	 * @return 返回organType
	 */
	public Integer getOrganType() {
		return organType;
	}

	/**
	 * @param organType 设置organType
	 */
	public void setOrganType(Integer organType) {
		this.organType = organType;
	}

	/**
	 * @return 返回employPersonSet
	 */
	public Set<EmployPerson> getEmployPersonSet() {
		return employPersonSet;
	}

	/**
	 * @param employPersonSet 设置employPersonSet
	 */
	public void setEmployPersonSet(Set<EmployPerson> employPersonSet) {
		this.employPersonSet = employPersonSet;
	}

	/**
	 * @return 返回establishSituationSet
	 */
	public Set<EstablishSituation> getEstablishSituationSet() {
		return establishSituationSet;
	}

	/**
	 * @param establishSituationSet 设置establishSituationSet
	 */
	public void setEstablishSituationSet(
			Set<EstablishSituation> establishSituationSet) {
		this.establishSituationSet = establishSituationSet;
	}

	/**
	 * @return 返回leaderStaffSet
	 */
	public Set<LeaderStaff> getLeaderStaffSet() {
		return leaderStaffSet;
	}

	/**
	 * @param leaderStaffSet 设置leaderStaffSet
	 */
	public void setLeaderStaffSet(Set<LeaderStaff> leaderStaffSet) {
		this.leaderStaffSet = leaderStaffSet;
	}

	/**
	 * @return 返回internalOrganSet
	 */
	public Set<InternalOrgan> getInternalOrganSet() {
		return internalOrganSet;
	}

	/**
	 * @param internalOrganSet 设置internalOrganSet
	 */
	public void setInternalOrganSet(Set<InternalOrgan> internalOrganSet) {
		this.internalOrganSet = internalOrganSet;
	}

	/**
	 * @return 返回infrastructureSet
	 */
	public Set<Infrastructure> getInfrastructureSet() {
		return infrastructureSet;
	}

	/**
	 * @param infrastructureSet 设置infrastructureSet
	 */
	public void setInfrastructureSet(Set<Infrastructure> infrastructureSet) {
		this.infrastructureSet = infrastructureSet;
	}
}