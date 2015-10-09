package com.cdthgk.bmp.secrecysystem.secrecynetwork.vo;

import java.util.Date;

import com.cdthgk.platform.organization.userinfo.domain.UserInfo;

/**
 * SecrecyNetworkterminalClear
 */
public class SecrecyNetworkterminalClear implements java.io.Serializable {

	private static final long serialVersionUID = 4763274711817966730L;

	private String networkterminalClearId;

	private SecrecyNetworkterminal secrecyNetworkterminal;

	private Integer clearType;

	private Date clearTime;

	private UserInfo reviewPerson;

	private String cleanReason;

	private UserInfo createPerson;

	private Date createDate;

	/**
	 * 默认构�?函数
	 */
	public SecrecyNetworkterminalClear() {
	}

	/**
	 * @return 返回networkterminalClearId
	 */
	public String getNetworkterminalClearId() {
		return networkterminalClearId;
	}

	/**
	 * @param networkterminalClearId 设置networkterminalClearId
	 */
	public void setNetworkterminalClearId(String networkterminalClearId) {
		this.networkterminalClearId = networkterminalClearId;
	}

	/**
	 * @return 返回secrecyNetworkterminal
	 */
	public SecrecyNetworkterminal getSecrecyNetworkterminal() {
		return secrecyNetworkterminal;
	}

	/**
	 * @param secrecyNetworkterminal 设置secrecyNetworkterminal
	 */
	public void setSecrecyNetworkterminal(
			SecrecyNetworkterminal secrecyNetworkterminal) {
		this.secrecyNetworkterminal = secrecyNetworkterminal;
	}

	/**
	 * @param reviewPerson 设置reviewPerson
	 */
	public void setReviewPerson(UserInfo reviewPerson) {
		this.reviewPerson = reviewPerson;
	}

	/**
	 * @return 返回clearType
	 */
	public Integer getClearType() {
		return clearType;
	}

	/**
	 * @param clearType 设置clearType
	 */
	public void setClearType(Integer clearType) {
		this.clearType = clearType;
	}

	/**
	 * @return 返回clearTime
	 */
	public Date getClearTime() {
		return clearTime;
	}

	/**
	 * @param clearTime 设置clearTime
	 */
	public void setClearTime(Date clearTime) {
		this.clearTime = clearTime;
	}

	/**
	 * @return 返回cleanReason
	 */
	public String getCleanReason() {
		return cleanReason;
	}

	/**
	 * @param cleanReason 设置cleanReason
	 */
	public void setCleanReason(String cleanReason) {
		this.cleanReason = cleanReason;
	}

	/**
	 * @return 返回createPerson
	 */
	public UserInfo getCreatePerson() {
		return createPerson;
	}

	/**
	 * @param createPerson 设置createPerson
	 */
	public void setCreatePerson(UserInfo createPerson) {
		this.createPerson = createPerson;
	}

	/**
	 * @return 返回createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate 设置createDate
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return 返回reviewPerson
	 */
	public UserInfo getReviewPerson() {
		return reviewPerson;
	}
}
