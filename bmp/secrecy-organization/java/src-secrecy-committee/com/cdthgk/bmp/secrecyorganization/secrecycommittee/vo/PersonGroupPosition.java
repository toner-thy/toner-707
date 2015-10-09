package com.cdthgk.bmp.secrecyorganization.secrecycommittee.vo;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * 成员组职位 Vo 类
 * </p>
 * <p>
 * 汪 钟 2012-12-18 15:59
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
public class PersonGroupPosition implements java.io.Serializable {

	private static final long serialVersionUID = -8434272638583899027L;

	// 构造器
	/** 默认构造器 */
	public void AuditFlow() {
	}

	// VO字段
	private String personGroupPosition;
	private String positionName;
	private Integer orderNo;
	private Integer groupType;
	private Set<PersonGroupRelation> personGroupRelations = new HashSet<PersonGroupRelation>();

	// ******************** Setter & Getter ********************
	public String getPersonGroupPosition() {
		return personGroupPosition;
	}

	public void setPersonGroupPosition(String personGroupPosition) {
		this.personGroupPosition = personGroupPosition;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getGroupType() {
		return groupType;
	}

	public void setGroupType(Integer groupType) {
		this.groupType = groupType;
	}

	public Set<PersonGroupRelation> getPersonGroupRelations() {
		return personGroupRelations;
	}

	public void setPersonGroupRelations(
			Set<PersonGroupRelation> personGroupRelations) {
		this.personGroupRelations = personGroupRelations;
	}
}