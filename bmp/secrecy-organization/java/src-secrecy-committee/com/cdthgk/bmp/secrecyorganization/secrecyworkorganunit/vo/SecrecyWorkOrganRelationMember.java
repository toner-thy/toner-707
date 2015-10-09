package com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.vo;

import java.util.Date;

import com.cdthgk.bmp.secrecyorganization.secrecycommittee.vo.PersonGroupPosition;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;

/**
 * <p>
 * SecrecyWorkOrganRelation.java
 * 保密工作机构 成员 Vo 类
 * </p>
 * <p>
 * 刘椿成  2012-12-29 17:24
 * </p>
 * <blockquote>
 * <h4>历史修改记录</h4>
 * <ul>
 * <li>宋亚非  2013-04-22 增加排序字段sort
 * </ul>
 * <ul>
 * <li>修改人 修改时间 修改描述
 * </ul>
 * </blockquote>
 * <p>
 * copyright liucc 2012, all rights reserved.
 * </p>
 * @author liucc
 * @since 1.0
 * @version 1.0
 */
public class SecrecyWorkOrganRelationMember implements java.io.Serializable,Comparable<SecrecyWorkOrganRelationMember> {

	// Fields
	private static final long serialVersionUID = -1128447886643364805L;
	private String secrecyWorkOrganRelationId;
	private UserInfo person;
	private PersonGroupPosition personGroupPosition;
	private SecrecyWorkOrgan secrecyWorkOrgan;
	private Integer sort;
	// 备 注
	private String remark;

	//是否专职
	private Integer isSoleDuty;

	//其他职务
	private String otherDuty;

	//何时开始从事保密工作
	private Date secrecyWorkStart;

	//脱密状态
	private Integer secrecyStatus;

	// 表单使用字段
	private String oldDeptId;
	private String oldUserId;

	// 只有导出word时使用的数据字典属性
	private String sexStr;
	private String learningLevelStr;
	private String polityStr;
	private String ageStr;

	// Constructors
	// 构造器
	/** 默认构造器 */
	public SecrecyWorkOrganRelationMember() {
	}

	/** full constructor */
	public SecrecyWorkOrganRelationMember(UserInfo person,
			PersonGroupPosition personGroupPosition,
			SecrecyWorkOrgan secrecyWorkOrgan) {
		this.person = person;
		this.personGroupPosition = personGroupPosition;
		this.secrecyWorkOrgan = secrecyWorkOrgan;
	}

	// ******************** Setter & Getter ********************
	/**
	 * 返回sysUserInfo
	 * @return sysUserInfo
	 */
	public UserInfo getPerson() {
		return person;
	}

	/**
	 * 设置sysUserInfo
	 * @param sysUserInfo sysUserInfo
	 */
	public void setPerson(UserInfo person) {
		this.person = person;
	}

	/**
	 * 返回personGroupPosition
	 * @return personGroupPosition
	 */
	public PersonGroupPosition getPersonGroupPosition() {
		return personGroupPosition;
	}

	/**
	 * 设置personGroupPosition
	 * @param personGroupPosition personGroupPosition
	 */
	public void setPersonGroupPosition(PersonGroupPosition personGroupPosition) {
		this.personGroupPosition = personGroupPosition;
	}

	/**
	 * 返回secrecyWorkOrgan
	 * @return secrecyWorkOrgan
	 */
	public SecrecyWorkOrgan getSecrecyWorkOrgan() {
		return secrecyWorkOrgan;
	}

	/**
	 * 设置secrecyWorkOrgan
	 * @param secrecyWorkOrgan secrecyWorkOrgan
	 */
	public void setSecrecyWorkOrgan(SecrecyWorkOrgan secrecyWorkOrgan) {
		this.secrecyWorkOrgan = secrecyWorkOrgan;
	}

	// Property accessors
	@Override
	public int compareTo(SecrecyWorkOrganRelationMember o) {
		if (personGroupPosition.getOrderNo() > o.personGroupPosition.getOrderNo()) {
			return 1;
		} else {
			return -1;
		}
	}

	/**
	 * 返回remark
	 * @return remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置remark
	 * @param remark remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
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
	 * 返回secrecyWorkOrganRelationId
	 * @return secrecyWorkOrganRelationId
	 */
	public String getSecrecyWorkOrganRelationId() {
		return secrecyWorkOrganRelationId;
	}

	/**
	 * 设置secrecyWorkOrganRelationId
	 * @param secrecyWorkOrganRelationId secrecyWorkOrganRelationId
	 */
	public void setSecrecyWorkOrganRelationId(String secrecyWorkOrganRelationId) {
		this.secrecyWorkOrganRelationId = secrecyWorkOrganRelationId;
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
	 * @return 返回sexStr
	 */
	public String getSexStr() {
		return sexStr;
	}

	/**
	 * @param sexStr 设置sexStr
	 */
	public void setSexStr(String sexStr) {
		this.sexStr = sexStr;
	}

	/**
	 * @return 返回learningLevelStr
	 */
	public String getLearningLevelStr() {
		return learningLevelStr;
	}

	/**
	 * @param learningLevelStr 设置learningLevelStr
	 */
	public void setLearningLevelStr(String learningLevelStr) {
		this.learningLevelStr = learningLevelStr;
	}

	/**
	 * @return 返回polityStr
	 */
	public String getPolityStr() {
		return polityStr;
	}

	/**
	 * @param polityStr 设置polityStr
	 */
	public void setPolityStr(String polityStr) {
		this.polityStr = polityStr;
	}

	/**
	 * @return 返回ageStr
	 */
	public String getAgeStr() {
		return ageStr;
	}

	/**
	 * @param ageStr 设置ageStr
	 */
	public void setAgeStr(String ageStr) {
		this.ageStr = ageStr;
	}
}