package com.cdthgk.bmp.secrecyorganization.secrecyoffice.vo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.cdthgk.platform.organization.userinfo.domain.UserInfo;

/**
 * <p>
 * 保密委成员 Vo 类
 * </p>
 * <p>
 * 陶汇源  2012-12-24 17:24
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
public class SecrecyOfficeMember implements java.io.Serializable {

	private static final long serialVersionUID = 4896359747081663550L;

	// VO字段
	// 保密办（保密局）成员表主键
	private String secrecyOfficeMemberId;

	// 所属保密办（保密局）
	private SecrecyOffice secrecyOffice;

	// 保密办（保密局）成员
	private UserInfo person;

	// 政治面貌
	private String politicalStatus;

	// 政治面貌(20130428修改维护用户界面的政治面貌一栏的字段应为选择使用，为不和历史数据冲突，新增字段)
	private String politicalType;

	// 参加工作日期
	private Date firstWorkDate;

	// 涉密程度
	private Integer secrecyPersonLevel;

	// 岗位
	private String post;

	// 办公室电话
	private String officePhone;

	//人员排序功能
	private Integer sort;

	// 备 注
	private String remark;

	// 简历
	private String resume;

	//涉密状态
	private Integer secrecyStatus;

	//是否专职
	private Integer isSoleDuty;

	//何时从事保密工作
	private Date secrecyWorkStart;



	// 密级变更成员
	private Set<SecrecyOfficeMemberChange> secrecyOfficeMemberChangeSet = new HashSet<SecrecyOfficeMemberChange>();
	// 脱密成员
	private Set<SecrecyOfficeMemberDecryption> secrecyOfficeMemberDecryptionSet = new HashSet<SecrecyOfficeMemberDecryption>();

	// 表单使用字段
	private String oldDeptId;

	// 构造器
	/** 默认构造器 */
	public SecrecyOfficeMember() {
	}

	// ******************** Setter & Getter ********************
	public String getSecrecyOfficeMemberId() {
		return secrecyOfficeMemberId;
	}

	public void setSecrecyOfficeMemberId(String secrecyOfficeMemberId) {
		this.secrecyOfficeMemberId = secrecyOfficeMemberId;
	}

	public SecrecyOffice getSecrecyOffice() {
		return secrecyOffice;
	}

	public void setSecrecyOffice(SecrecyOffice secrecyOffice) {
		this.secrecyOffice = secrecyOffice;
	}

	public UserInfo getPerson() {
		return person;
	}

	public void setPerson(UserInfo person) {
		this.person = person;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	public String getOldDeptId() {
		return oldDeptId;
	}

	public void setOldDeptId(String oldDeptId) {
		this.oldDeptId = oldDeptId;
	}

	public String getPoliticalStatus() {
		return politicalStatus;
	}

	public void setPoliticalStatus(String politicalStatus) {
		this.politicalStatus = politicalStatus;
	}
	/**
	 * @return the politicalType
	 */
	public String getPoliticalType() {
		return politicalType;
	}

	/**
	 * @param politicalType the politicalType to set
	 */
	public void setPoliticalType(String politicalType) {
		this.politicalType = politicalType;
	}

	public Date getFirstWorkDate() {
		return firstWorkDate;
	}

	public void setFirstWorkDate(Date firstWorkDate) {
		this.firstWorkDate = firstWorkDate;
	}

	public Integer getSecrecyPersonLevel() {
		return secrecyPersonLevel;
	}

	public void setSecrecyPersonLevel(Integer secrecyPersonLevel) {
		this.secrecyPersonLevel = secrecyPersonLevel;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
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
	 * @return 返回secrecyOfficeMemberChangeSet
	 */
	public Set<SecrecyOfficeMemberChange> getSecrecyOfficeMemberChangeSet() {
		return secrecyOfficeMemberChangeSet;
	}

	/**
	 * @param secrecyOfficeMemberChangeSet 设置secrecyOfficeMemberChangeSet
	 */
	public void setSecrecyOfficeMemberChangeSet(
			Set<SecrecyOfficeMemberChange> secrecyOfficeMemberChangeSet) {
		this.secrecyOfficeMemberChangeSet = secrecyOfficeMemberChangeSet;
	}

	/**
	 * @return 返回secrecyOfficeMemberDecryptionSet
	 */
	public Set<SecrecyOfficeMemberDecryption> getSecrecyOfficeMemberDecryptionSet() {
		return secrecyOfficeMemberDecryptionSet;
	}

	/**
	 * @param secrecyOfficeMemberDecryptionSet 设置secrecyOfficeMemberDecryptionSet
	 */
	public void setSecrecyOfficeMemberDecryptionSet(
			Set<SecrecyOfficeMemberDecryption> secrecyOfficeMemberDecryptionSet) {
		this.secrecyOfficeMemberDecryptionSet = secrecyOfficeMemberDecryptionSet;
	}

	/**
	 * @return 返回isSoleDuty
	 */
	public Integer getIsSoleDuty() {
		return isSoleDuty;
	}

	/**
	 * @param isSoleDuty 设置isSoleDuty
	 */
	public void setIsSoleDuty(Integer isSoleDuty) {
		this.isSoleDuty = isSoleDuty;
	}

	/**
	 * @return 返回secrecyWorkStart
	 */
	public Date getSecrecyWorkStart() {
		return secrecyWorkStart;
	}

	/**
	 * @param secrecyWorkStart 设置secrecyWorkStart
	 */
	public void setSecrecyWorkStart(Date secrecyWorkStart) {
		this.secrecyWorkStart = secrecyWorkStart;
	}

}