package com.cdthgk.bmp.secrecysystem.secrecynetwork.vo;

import java.util.Date;

import com.cdthgk.platform.organization.userinfo.domain.UserInfo;
import com.cdthgk.platform.permission.user.domain.User;

/**
 * SecrecyNetworkClear
 */
public class SecrecyNetworkClear implements java.io.Serializable {

	private static final long serialVersionUID = -4017775330164716209L;

	private String networkClearId;

	private SecrecyNetwork secrecyNetwork;

	private Integer clearType;

	private Date clearTime;

	private UserInfo reviewPerson;

	private String cleanReason;

	private User createPerson;

	private Date createDate;

	/**
	 * 默认构�?函数
	 */
	public SecrecyNetworkClear() {
	}

	/**
	 * @return 返回networkClearId
	 */
	public String getNetworkClearId() {
		return networkClearId;
	}

	/**
	 * @param networkClearId 设置networkClearId
	 */
	public void setNetworkClearId(String networkClearId) {
		this.networkClearId = networkClearId;
	}

	/**
	 * @return 返回secrecyNetwork
	 */
	public SecrecyNetwork getSecrecyNetwork() {
		return secrecyNetwork;
	}

	/**
	 * @param secrecyNetwork 设置secrecyNetwork
	 */
	public void setSecrecyNetwork(SecrecyNetwork secrecyNetwork) {
		this.secrecyNetwork = secrecyNetwork;
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
