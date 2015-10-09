package com.cdthgk.bmp.keyPart.vo;
// default package
// Generated 2013-7-15 9:42:10 ----- 3.4.0.CR1

import java.util.Date;

import com.cdthgk.platform.permission.user.domain.User;

/**
 * KeyPartChange  要害部位密级变更
 */
public class KeyPartChange implements java.io.Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 4330173068321413670L;

	private String keyPartSecrecyChangeId; //要害部位密级变更id

	private Part keyPartId; //要害部位id

	private Integer beforeLevel;  //变更前密级

	private Integer afterLevel;  //变更后密级

	private Integer changeTimeState; //保密期限变更

	private Date changeDate;   //变更时间

	private String changeReason;  // 变更原因

	private User createPerson; //创建人

	private Date createDate; //创建时间

	/**
	 * 默认构函数
	 */
	public KeyPartChange() {
	}

	/**
	 * 返回keyPartSecrecyChangeId
	 * @return keyPartSecrecyChangeId
	 */
	public String getKeyPartSecrecyChangeId() {
		return this.keyPartSecrecyChangeId;
	}

	/**
	 * 设置keyPartSecrecyChangeId
	 * @param keyPartSecrecyChangeId keyPartSecrecyChangeId
	 */
	public void setKeyPartSecrecyChangeId(String keyPartSecrecyChangeId) {
		this.keyPartSecrecyChangeId = keyPartSecrecyChangeId;
	}

	/**
	 * 返回keyPartId
	 * @return keyPartId
	 */
	public Part getKeyPartId() {
		return this.keyPartId;
	}

	/**
	 * 设置keyPartId
	 * @param keyPartId keyPartId
	 */
	public void setKeyPartId(Part keyPartId) {
		this.keyPartId = keyPartId;
	}

	/**
	 * 返回beforeLevel
	 * @return beforeLevel
	 */
	public Integer getBeforeLevel() {
		return this.beforeLevel;
	}

	/**
	 * 设置beforeLevel
	 * @param beforeLevel beforeLevel
	 */
	public void setBeforeLevel(Integer beforeLevel) {
		this.beforeLevel = beforeLevel;
	}

	/**
	 * 返回afterLevel
	 * @return afterLevel
	 */
	public Integer getAfterLevel() {
		return this.afterLevel;
	}

	/**
	 * 设置afterLevel
	 * @param afterLevel afterLevel
	 */
	public void setAfterLevel(Integer afterLevel) {
		this.afterLevel = afterLevel;
	}

	/**
	 * 返回changeTimeState
	 * @return changeTimeState
	 */
	public Integer getChangeTimeState() {
		return this.changeTimeState;
	}

	/**
	 * 设置changeTimeState
	 * @param changeTimeState changeTimeState
	 */
	public void setChangeTimeState(Integer changeTimeState) {
		this.changeTimeState = changeTimeState;
	}

	/**
	 * 返回changeDate
	 * @return changeDate
	 */
	public Date getChangeDate() {
		return this.changeDate;
	}

	/**
	 * 设置changeDate
	 * @param changeDate changeDate
	 */
	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}

	/**
	 * 返回changeReason
	 * @return changeReason
	 */
	public String getChangeReason() {
		return this.changeReason;
	}

	/**
	 * 设置changeReason
	 * @param changeReason changeReason
	 */
	public void setChangeReason(String changeReason) {
		this.changeReason = changeReason;
	}

	/**
	 * 返回createPerson
	 * @return createPerson
	 */
	public User getCreatePerson() {
		return this.createPerson;
	}

	/**
	 * 设置createPerson
	 * @param createPerson createPerson
	 */
	public void setCreatePerson(User createPerson) {
		this.createPerson = createPerson;
	}

	/**
	 * 返回createDate
	 * @return createDate
	 */
	public Date getCreateDate() {
		return this.createDate;
	}

	/**
	 * 设置createDate
	 * @param createDate createDate
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
