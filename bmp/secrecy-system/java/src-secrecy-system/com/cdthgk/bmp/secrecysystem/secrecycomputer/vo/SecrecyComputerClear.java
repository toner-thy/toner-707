package com.cdthgk.bmp.secrecysystem.secrecycomputer.vo;

import java.util.Date;

import com.cdthgk.platform.organization.userinfo.domain.UserInfo;
import com.cdthgk.platform.permission.user.domain.User;

/**
 * SecrecyComputerClear
 */
public class SecrecyComputerClear implements java.io.Serializable {

	private static final long serialVersionUID = 2643015057664926592L;

	private String computerSecrecyClearId;

	private SecrecyComputer secrecyComputer;

	private Integer clearType;

	private Date clearTime;

	private UserInfo reviewPerson;

	private String cleanReason;

	private User createPerson;

	private Date createDate;

	/**
	 * 默认构�?函数
	 */
	public SecrecyComputerClear() {
	}

	/**
	 * @return 返回computerSecrecyClearId
	 */
	public String getComputerSecrecyClearId() {
		return computerSecrecyClearId;
	}

	/**
	 * @param computerSecrecyClearId 设置computerSecrecyClearId
	 */
	public void setComputerSecrecyClearId(String computerSecrecyClearId) {
		this.computerSecrecyClearId = computerSecrecyClearId;
	}

	/**
	 * @return 返回secrecyComputer
	 */
	public SecrecyComputer getSecrecyComputer() {
		return secrecyComputer;
	}

	/**
	 * @param secrecyComputer 设置secrecyComputer
	 */
	public void setSecrecyComputer(SecrecyComputer secrecyComputer) {
		this.secrecyComputer = secrecyComputer;
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
	 * @return 返回reviewPerson
	 */
	public UserInfo getReviewPerson() {
		return reviewPerson;
	}

	/**
	 * @param reviewPerson 设置reviewPerson
	 */
	public void setReviewPerson(UserInfo reviewPerson) {
		this.reviewPerson = reviewPerson;
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
	public User getCreatePerson() {
		return createPerson;
	}

	/**
	 * @param createPerson 设置createPerson
	 */
	public void setCreatePerson(User createPerson) {
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
}
