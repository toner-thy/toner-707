package com.cdthgk.bmp.keysection.qo;

import java.util.Date;

/**
 * <p>
 * 要害部门qo类
 * </p>
 * <p>
 * 王彭波 2012-12-14 17:01
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
 * @author wangpb
 * @since 1.0
 * @version 1.0
 */
public class KeySectionQo {
	// 部门
	private String departmentName;
	// 密级
	private Integer secrecyLevel;
	// 批准机关
	private String authorizeOrgan;
	// 批准日期
	private Date authorizeDate;
	// 重要性
	private Integer importance;
	// 涉密工作事项及范围
	private String secScope;
	// 撤销人
	private String annulPerson;
	// 撤销日期
	private Date annulDate;
	// 撤销原因
	private String annulCause;
	// 备注
	private String remark;
	// 状态
	private Integer status;
	// 存放涉密载体铁柜数量
	private Integer tankNum;
	// 存放涉密载体密码文件柜数量
	private Integer passwordFileTankNum;
	// 存放涉密载体保险柜数量
	private Integer secrecyCarrierTankNum;
	// 存放涉密载体密码文件柜数量
	private Integer secrecyCarrierDisTurbNum;
	// 所在办公室环境
	private String mainArea;
	// 涉密人员数量
	private Integer personNum;
	// 负责人
	private String principal;
	// 单位
	private String organName;
	// 联系电话
	private String phone;

	// 构造器
	/* 默认构造器 */
	public KeySectionQo() {
	}

	// ******************** Setter & Getter ********************
	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Integer getSecrecyLevel() {
		return secrecyLevel;
	}

	public void setSecrecyLevel(Integer secrecyLevel) {
		this.secrecyLevel = secrecyLevel;
	}

	public String getAuthorizeOrgan() {
		return authorizeOrgan;
	}

	public void setAuthorizeOrgan(String authorizeOrgan) {
		this.authorizeOrgan = authorizeOrgan;
	}

	public Date getAuthorizeDate() {
		return authorizeDate;
	}

	public void setAuthorizeDate(Date authorizeDate) {
		this.authorizeDate = authorizeDate;
	}

	public Integer getImportance() {
		return importance;
	}

	public void setImportance(Integer importance) {
		this.importance = importance;
	}

	public String getSecScope() {
		return secScope;
	}

	public void setSecScope(String secScope) {
		this.secScope = secScope;
	}

	public String getAnnulPerson() {
		return annulPerson;
	}

	public void setAnnulPerson(String annulPerson) {
		this.annulPerson = annulPerson;
	}

	public Date getAnnulDate() {
		return annulDate;
	}

	public void setAnnulDate(Date annulDate) {
		this.annulDate = annulDate;
	}

	public String getAnnulCause() {
		return annulCause;
	}

	public void setAnnulCause(String annulCause) {
		this.annulCause = annulCause;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getTankNum() {
		return tankNum;
	}

	public void setTankNum(Integer tankNum) {
		this.tankNum = tankNum;
	}

	public Integer getPasswordFileTankNum() {
		return passwordFileTankNum;
	}

	public void setPasswordFileTankNum(Integer passwordFileTankNum) {
		this.passwordFileTankNum = passwordFileTankNum;
	}

	public Integer getSecrecyCarrierTankNum() {
		return secrecyCarrierTankNum;
	}

	public void setSecrecyCarrierTankNum(Integer secrecyCarrierTankNum) {
		this.secrecyCarrierTankNum = secrecyCarrierTankNum;
	}

	public Integer getSecrecyCarrierDisTurbNum() {
		return secrecyCarrierDisTurbNum;
	}

	public void setSecrecyCarrierDisTurbNum(Integer secrecyCarrierDisTurbNum) {
		this.secrecyCarrierDisTurbNum = secrecyCarrierDisTurbNum;
	}

	public String getMainArea() {
		return mainArea;
	}

	public void setMainArea(String mainArea) {
		this.mainArea = mainArea;
	}

	public Integer getPersonNum() {
		return personNum;
	}

	public void setPersonNum(Integer personNum) {
		this.personNum = personNum;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getOrganName() {
		return organName;
	}

	public void setOrganName(String organName) {
		this.organName = organName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
