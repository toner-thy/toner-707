package com.cdthgk.bmp.secrecycountryitem.vo;
// default package
// Generated 2013-7-15 14:28:32 ----- 3.4.0.CR1

import java.util.Date;

import com.cdthgk.platform.permission.user.domain.User;

/**
 * SecrecyCountryItemChange  国家秘密变更
 */
public class SecrecyCountryItemChange implements java.io.Serializable {

	private static final long serialVersionUID = -3466227623032925625L;

	private String secrecyChangeId; //国家秘密事项  密级变更id
	private SecrecyCountryItem secrecyCountryItem;  //国家秘密事项
	private Integer beforeLevel; //变更前密级
	private Integer afterLevel;  //变更后密级
	private Integer changeTimeState; //保密期限变更
	private Date changeDate;  //变更日期
	private String changeReason;  //变更原因

	private User createPerson;  //创建人

	private Date createDate;//创建时间

	/**
	 * 默认构函数
	 */
	public SecrecyCountryItemChange() {
	}


	public String getSecrecyChangeId() {
		return secrecyChangeId;
	}


	public void setSecrecyChangeId(String secrecyChangeId) {
		this.secrecyChangeId = secrecyChangeId;
	}


	public SecrecyCountryItem getSecrecyCountryItem() {
		return secrecyCountryItem;
	}

	public void setSecrecyCountryItem(SecrecyCountryItem secrecyCountryItem) {
		this.secrecyCountryItem = secrecyCountryItem;
	}

	public Integer getBeforeLevel() {
		return beforeLevel;
	}

	public void setBeforeLevel(Integer beforeLevel) {
		this.beforeLevel = beforeLevel;
	}

	public Integer getAfterLevel() {
		return afterLevel;
	}

	public void setAfterLevel(Integer afterLevel) {
		this.afterLevel = afterLevel;
	}

	public Integer getChangeTimeState() {
		return changeTimeState;
	}

	public void setChangeTimeState(Integer changeTimeState) {
		this.changeTimeState = changeTimeState;
	}

	public Date getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}

	public String getChangeReason() {
		return changeReason;
	}

	public void setChangeReason(String changeReason) {
		this.changeReason = changeReason;
	}

	public User getCreatePerson() {
		return createPerson;
	}

	public void setCreatePerson(User createPerson) {
		this.createPerson = createPerson;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


}
