package com.cdthgk.bmp.secrecyorganization.secrecycommittee.vo;

import com.cdthgk.platform.organization.userinfo.domain.UserInfo;

/**
 * <p>
 * 成员组关系 Vo 类
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
public class PersonGroupRelation implements java.io.Serializable,Comparable<PersonGroupRelation> {

	private static final long serialVersionUID = -1128447886643364805L;

	private String bmPersonGroupRelationId;
	private UserInfo sysUserInfo;
	private PersonGroupPosition bmPersonGroupPosition;
	private PersonGroup bmPersonGroup;
	private Integer secrecyStatus;  //解密状态



	// 构造器
	/** 默认构造器 */
	public PersonGroupRelation() {
	}

	// ******************** Setter & Getter ********************
	public String getBmPersonGroupRelationId() {
		return this.bmPersonGroupRelationId;
	}

	public void setBmPersonGroupRelationId(String bmPersonGroupRelationId) {
		this.bmPersonGroupRelationId = bmPersonGroupRelationId;
	}

	public UserInfo getSysUserInfo() {
		return this.sysUserInfo;
	}

	public void setSysUserInfo(UserInfo sysUserInfo) {
		this.sysUserInfo = sysUserInfo;
	}

	public PersonGroupPosition getBmPersonGroupPosition() {
		return this.bmPersonGroupPosition;
	}

	public void setBmPersonGroupPosition(
			PersonGroupPosition bmPersonGroupPosition) {
		this.bmPersonGroupPosition = bmPersonGroupPosition;
	}

	public PersonGroup getBmPersonGroup() {
		return this.bmPersonGroup;
	}

	public void setBmPersonGroup(PersonGroup bmPersonGroup) {
		this.bmPersonGroup = bmPersonGroup;
	}

	@Override
	public int compareTo(PersonGroupRelation o) {
		if (bmPersonGroupPosition.getOrderNo() > o.bmPersonGroupPosition.getOrderNo()) {
			return 1;
		} else {
			return -1;
		}
	}

	public Integer getSecrecyStatus() {
		return secrecyStatus;
	}

	public void setSecrecyStatus(Integer secrecyStatus) {
		this.secrecyStatus = secrecyStatus;
	}
}