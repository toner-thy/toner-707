package com.cdthgk.bmp.secrecyorganization.secrecycommittee.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.cdthgk.platform.attachment.domain.Attachment;
import com.cdthgk.platform.base.util.ReportState;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.permission.user.domain.User;

/**
 * <p>
 * 保密委 Vo 类
 * </p>
 * <p>
 * 汪 钟 2012-12-14 10:43:03
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
public class SecrecyCommittee extends ReportState implements java.io.Serializable {

	private static final long serialVersionUID = 4896359747081663550L;

	// VO字段
	// 保密委员会表主键
	private String secrecyCommitteeId;

	// 保密委名称
	private String name;

	// 发文号
	private String docNo;

	// 成立(发文)日期
	private Date setupDate;

	// 职责与成员分工
	private String dutyMemberWork;

	// 所属行政区划
	private District district;

	// 保密委成员
	private Set<SecrecyCommitteeMember> secrecyCommitteeMembers;

	// 以下为通用字段
	private User createUser;
	private Organ createOrgan;
	private Date createTime;
	private User modifyUser;
	private Organ modifyOrgan;
	private Date modifyTime;
	private Integer enable;

	private List<Attachment> attachmentList = new ArrayList<Attachment>();
	// 构造器
	/** 默认构造器 */
	public void AuditFlow() {
	}

	// ******************** Setter & Getter ********************
	public String getDutyMemberWork() {
		return dutyMemberWork;
	}

	public void setDutyMemberWork(String dutyMemberWork) {
		this.dutyMemberWork = dutyMemberWork;
	}

	public Date getSetupDate() {
		return setupDate;
	}

	public void setSetupDate(Date setupDate) {
		this.setupDate = setupDate;
	}

	public String getDocNo() {
		return docNo;
	}

	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSecrecyCommitteeId() {
		return secrecyCommitteeId;
	}

	public void setSecrecyCommitteeId(String secrecyCommitteeId) {
		this.secrecyCommitteeId = secrecyCommitteeId;
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

	public District getDistrict() {
		return district;
	}
	public void setDistrict(District district) {
		this.district = district;
	}

	public Set<SecrecyCommitteeMember> getSecrecyCommitteeMembers() {
		return secrecyCommitteeMembers;
	}
	public void setSecrecyCommitteeMembers(
			Set<SecrecyCommitteeMember> secrecyCommitteeMembers) {
		this.secrecyCommitteeMembers = secrecyCommitteeMembers;
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
}