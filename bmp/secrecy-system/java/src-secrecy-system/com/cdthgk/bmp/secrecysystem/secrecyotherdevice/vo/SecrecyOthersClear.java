package com.cdthgk.bmp.secrecysystem.secrecyotherdevice.vo;

import java.util.Date;

import com.cdthgk.platform.organization.userinfo.domain.UserInfo;
import com.cdthgk.platform.permission.user.domain.User;

/**
 * SecrecyOthersClear
 */
public class SecrecyOthersClear implements java.io.Serializable {

	private static final long serialVersionUID = -3765766667080069577L;

	private String secrecyothersClearId;

	private SecrecyOthers secrecyOthers;

	private Integer clearType;

	private Date clearTime;

	private UserInfo reviewPerson;

	private String cleanReason;

	private User createPerson;

	private Date createDate;

	/**
	 * 默认构�?函数
	 */
	public SecrecyOthersClear() {
	}


	/**
	 * @return the secrecyothersClearId
	 */
	public String getSecrecyothersClearId() {
		return secrecyothersClearId;
	}


	/**
	 * @param secrecyothersClearId the secrecyothersClearId to set
	 */
	public void setSecrecyothersClearId(String secrecyothersClearId) {
		this.secrecyothersClearId = secrecyothersClearId;
	}


	/**
	 * @return 返回secrecyOthers
	 */
	public SecrecyOthers getSecrecyOthers() {
		return secrecyOthers;
	}

	/**
	 * @param secrecyOthers 设置secrecyOthers
	 */
	public void setSecrecyOthers(SecrecyOthers secrecyOthers) {
		this.secrecyOthers = secrecyOthers;
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
	 * @return the createPerson
	 */
	public User getCreatePerson() {
		return createPerson;
	}

	/**
	 * @param createPerson the createPerson to set
	 */
	public void setCreatePerson(User createPerson) {
		this.createPerson = createPerson;
	}

	/**
	 * @return the reviewPerson
	 */
	public UserInfo getReviewPerson() {
		return reviewPerson;
	}

	/**
	 * @param reviewPerson the reviewPerson to set
	 */
	public void setReviewPerson(UserInfo reviewPerson) {
		this.reviewPerson = reviewPerson;
	}


}
