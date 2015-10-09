package com.cdthgk.meetingcategory.vo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.permission.user.domain.User;

/**
 * BmMeetingCategory entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class MeetingCategory implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	private String meetingCategoryId;
	private String categoryName;
	private String categoryDesc;
	private Integer orderNo;
	//private String departmentId;
	private Department department;
	//private String organId;
	private Organ organ;
	//private String createPerson;
	private User createPerson;
	private Date createTime;
	//private String modifyPerson;
	private User modifyPerson;
	private Date modifyTime;

	private Set<Meeting> meetings = new HashSet<Meeting>(0);
	//
	private Set<MeetingCategory> meetingCategorys = new HashSet<MeetingCategory>(0);
	public String getMeetingCategoryId() {
		return meetingCategoryId;
	}
	public void setMeetingCategoryId(String meetingCategoryId) {
		this.meetingCategoryId = meetingCategoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryDesc() {
		return categoryDesc;
	}
	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
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
	public Set<Meeting> getMeetings() {
		return meetings;
	}
	public void setMeetings(Set<Meeting> meetings) {
		this.meetings = meetings;
	}
	public Set<MeetingCategory> getMeetingCategorys() {
		return meetingCategorys;
	}
	public void setMeetingCategorys(Set<MeetingCategory> meetingCategorys) {
		this.meetingCategorys = meetingCategorys;
	}
}