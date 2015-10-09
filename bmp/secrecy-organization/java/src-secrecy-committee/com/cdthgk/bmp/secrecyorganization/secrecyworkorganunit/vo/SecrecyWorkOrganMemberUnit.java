package com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.vo;

import java.util.Date;

import com.cdthgk.platform.organization.userinfo.domain.UserInfo;

/**
 * <p>
 * 保密办成员 Vo 类
 * </p>
 * <p>
 * 刘椿成  2012-12-29 17:24
 * </p>
 * <blockquote>
 * <h4>历史修改记录</h4>
 * <ul>
 * <li>宋亚非 2013-04-21 10：39 增加人员排序字段
 * </ul>
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
public class SecrecyWorkOrganMemberUnit implements java.io.Serializable {

	private static final long serialVersionUID = 4896359747081663550L;
	// VO字段
	// 单位保密办成员表主键
	private String secrecyWorkUnitMemberId;
	// 单位保密工作机构
	private SecrecyWorkOrgan secrecyWorkOrgan;
	// 单位保密办成员
	private UserInfo person;
	//人员排序字段
	private Integer sort;

	//是否专职
	private Integer isSoleDuty;

	//其他职务
	private String otherDuty;

	//何时从事保密工作
	private Date secrecyWorkStart;

	//变更状态
	private Integer secrecyStatus;

	// 备 注
	private String remark;
	// 表单使用字段
	private String oldDeptId;
	private String oldUserId;

	// 构造器
	/** 默认构造器 */
	public SecrecyWorkOrganMemberUnit() {
	}

	// ******************** Setter & Getter ********************
	/**
	 * 返回secrecyWorkUnitMemberId
	 * @return secrecyWorkUnitMemberId
	 */
	public String getSecrecyWorkUnitMemberId() {
		return secrecyWorkUnitMemberId;
	}

	/**
	 * 设置secrecyWorkUnitMemberId
	 * @param secrecyWorkUnitMemberId secrecyWorkUnitMemberId
	 */
	public void setSecrecyWorkUnitMemberId(String secrecyWorkUnitMemberId) {
		this.secrecyWorkUnitMemberId = secrecyWorkUnitMemberId;
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

	/**
	 * 返回person
	 * @return person
	 */
	public UserInfo getPerson() {
		return person;
	}

	/**
	 * 设置person
	 * @param person person
	 */
	public void setPerson(UserInfo person) {
		this.person = person;
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

}