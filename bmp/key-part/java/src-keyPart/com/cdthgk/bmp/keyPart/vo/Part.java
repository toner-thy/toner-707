package com.cdthgk.bmp.keyPart.vo;

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
 * Part.java：要害部位实体类
 * </p>
 * <p>
 * 刘椿成 2012-09-19 13:26
 * </p>
 * <blockquote>
 * <h4>历史修改记录</h4>
 * <ul>
 * <li>修改人 修改时间 修改描述
 * </ul>
 * </blockquote>
 * <p>
 * copyright liucc 2012, all rights reserved.
 * </p>
 *
 * @author 刘椿成
 * @author liucc
 * @since 1.0
 * @version 1.0
 */
public class Part extends ReportState implements java.io.Serializable {

	// 序列化UUID
	private static final long serialVersionUID = 633533711897985857L;
	// 部位id
	private String partId;
	// 涉密部位名称
	private String partName;
	// 主要负责人
	private UserInfo person;
	// 涉密等级（1.核心2.重要3.一般）
	private Integer secrecyLevel;
	// 具体位置
	private String location;
	// 涉密工作事项及范围
	private String secScope;
	// 技防措施
	private String skillMeasure;
	// 是否建立管理制度
	private Integer managerRule;
	// 主管部门
	private Department department;
	// 联系电话
	private String phone;
	// 表单使用字段
	private String oldDeptId;
	private String oldUserId;

	// 通用字段及级联关系
	private Organ organ;
	private User createperson;
	private Date createTime;
	private User modifyPerson;
	private Date modifyTime;

	private Set<PartPerson> partPersons = new HashSet<PartPerson>(0);
	private Set<Department> departments = new HashSet<Department>(0);
	private List<Attachment> attachmentList = new ArrayList<Attachment>();


	// 只有导出word时使用的数据字典属性
	private String secrecyLevelStr;

	/**
	 * 新增字段
	 */
	private Integer secrecyStatus;//解密状态(1是 0否)


	// ******************** Setter & Getter ********************
	public String getPartId() {
		return partId;
	}
	public void setPartId(String partId) {
		this.partId = partId;
	}
	public String getPartName() {
		return partName;
	}
	public void setPartName(String partName) {
		this.partName = partName;
	}
	public UserInfo getPerson() {
		return person;
	}
	public void setPerson(UserInfo person) {
		this.person = person;
	}
	public Integer getSecrecyLevel() {
		return secrecyLevel;
	}
	public void setSecrecyLevel(Integer secrecyLevel) {
		this.secrecyLevel = secrecyLevel;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getSecScope() {
		return secScope;
	}
	public void setSecScope(String secScope) {
		this.secScope = secScope;
	}
	public String getSkillMeasure() {
		return skillMeasure;
	}
	public void setSkillMeasure(String skillMeasure) {
		this.skillMeasure = skillMeasure;
	}
	public Integer getManagerRule() {
		return managerRule;
	}
	public void setManagerRule(Integer managerRule) {
		this.managerRule = managerRule;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getOldDeptId() {
		return oldDeptId;
	}
	public void setOldDeptId(String oldDeptId) {
		this.oldDeptId = oldDeptId;
	}
	public String getOldUserId() {
		return oldUserId;
	}
	public void setOldUserId(String oldUserId) {
		this.oldUserId = oldUserId;
	}
	public Organ getOrgan() {
		return organ;
	}
	public void setOrgan(Organ organ) {
		this.organ = organ;
	}
	public User getCreateperson() {
		return createperson;
	}
	public void setCreateperson(User createperson) {
		this.createperson = createperson;
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
	public Set<PartPerson> getPartPersons() {
		return partPersons;
	}
	public void setPartPersons(Set<PartPerson> partPersons) {
		this.partPersons = partPersons;
	}
	public Set<Department> getDepartments() {
		return departments;
	}
	public void setDepartments(Set<Department> departments) {
		this.departments = departments;
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

	public Integer getSecrecyStatus() {
		return secrecyStatus;
	}
	public void setSecrecyStatus(Integer secrecyStatus) {
		this.secrecyStatus = secrecyStatus;
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