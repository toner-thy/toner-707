package com.cdthgk.bmp.secrecyproducts.vo;
// default package
// Generated 2013-7-15 14:28:32 ----- 3.4.0.CR1

import java.util.Date;

import com.cdthgk.platform.permission.user.domain.User;

/**
 * SecrecyProductsClear  密品 密级解除
 */
public class SecrecyProductsClear implements java.io.Serializable {

	private static final long serialVersionUID = 8284184486475530827L;

	private String clearId;//密品 密级解除
	private SecrecyProducts secrecyProducts; //密品
	private Integer clearType; //解除类型
	private Date clearTime;   //解除时间
	private String reviewPerson;  //审核人
	private String cleanReason;  //解除原因

	private User createPerson;
	private Date createDate;

	/**
	 * 默认构函数
	 */
	public SecrecyProductsClear() {
	}


	public String getClearId() {
		return clearId;
	}


	public void setClearId(String clearId) {
		this.clearId = clearId;
	}


	/**
	 * 返回bmSecrecyProducts
	 * @return bmSecrecyProducts
	 */
	public SecrecyProducts getSecrecyProducts() {
		return this.secrecyProducts;
	}

	/**
	 * 设置bmSecrecyProducts
	 * @param bmSecrecyProducts bmSecrecyProducts
	 */
	public void setSecrecyProducts(SecrecyProducts secrecyProducts) {
		this.secrecyProducts = secrecyProducts;
	}

	/**
	 * 返回clearType
	 * @return clearType
	 */
	public Integer getClearType() {
		return this.clearType;
	}

	/**
	 * 设置clearType
	 * @param clearType clearType
	 */
	public void setClearType(Integer clearType) {
		this.clearType = clearType;
	}

	/**
	 * 返回clearTime
	 * @return clearTime
	 */
	public Date getClearTime() {
		return this.clearTime;
	}

	/**
	 * 设置clearTime
	 * @param clearTime clearTime
	 */
	public void setClearTime(Date clearTime) {
		this.clearTime = clearTime;
	}

	/**
	 * 返回reviewPerson
	 * @return reviewPerson
	 */
	public String getReviewPerson() {
		return this.reviewPerson;
	}

	/**
	 * 设置reviewPerson
	 * @param reviewPerson reviewPerson
	 */
	public void setReviewPerson(String reviewPerson) {
		this.reviewPerson = reviewPerson;
	}

	/**
	 * 返回cleanReason
	 * @return cleanReason
	 */
	public String getCleanReason() {
		return this.cleanReason;
	}

	/**
	 * 设置cleanReason
	 * @param cleanReason cleanReason
	 */
	public void setCleanReason(String cleanReason) {
		this.cleanReason = cleanReason;
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
