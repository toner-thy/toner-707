package com.cdthgk.bmp.keysection.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cdthgk.bmp.model.ReferSecrecy;
import com.cdthgk.platform.attachment.domain.Attachment;
import com.cdthgk.platform.base.util.ReportState;
import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;
import com.cdthgk.platform.permission.user.domain.User;

/**
 * <p>
 * 要害部门VO类
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
public class KeySection extends ReportState implements Serializable, ReferSecrecy{

	private static final long serialVersionUID = -1581575984268339506L;

	//主键ID
	private String keySectionId;
	//部门
	private Department department;
	//密级
	private Integer secrecyLevel;
	//批准机关
	private String authorizeOrgan;
	//批准日期
	private Date authorizeDate;
	//重要性
	private Integer importance;
	//涉密工作事项及范围
	private String secScope;
	//撤销人
	private User annulPerson;
	//撤销日期
	private Date annulDate;
	//撤销原因
	private String annulCause;
	//备注
	private String remark;
	//状态
	private Integer status;
	//存放涉密载体铁柜数量
	private Integer tankNum;
	//存放涉密载体密码文件柜数量
	private Integer passwordFileTankNum;
	//存放涉密载体保险柜数量
	private Integer secrecyCarrierTankNum;
	//存放涉密载体密码文件柜数量
	private Integer secrecyCarrierDisTurbNum;
	//所在办公室环境
	private String mainArea;
	//涉密人员数量
	private Integer personNum;
	//负责人
	private UserInfo principal;
	//单位
	private Organ organ;
	//创建人
	private User createPerson;
	//创建时间
	private Date createTime;
	//修改人
	private User modifyPerson;
	//修改时间
	private Date modifyTime;
	//联系电话
	private String phone;

	private List<Attachment> attachmentList = new ArrayList<Attachment>();

	/**
	 * 新增字段
	 */
	private Integer secrecyStatus;//解密状态(1是 0否)

	// 只有导出word时使用的数据字典属性
	private String secrecyLevelStr;

	// 构造器
	/* 默认构造器 */
	public KeySection() {
	}

	// ******************** Setter & Getter ********************
	public String getKeySectionId() {
		return keySectionId;
	}

	public void setKeySectionId(String keySectionId) {
		this.keySectionId = keySectionId;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
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

	public User getAnnulPerson() {
		return annulPerson;
	}

	public void setAnnulPerson(User annulPerson) {
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

	public UserInfo getPrincipal() {
		return principal;
	}

	public void setPrincipal(UserInfo principal) {
		this.principal = principal;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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
