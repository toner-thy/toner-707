package com.cdthgk.bmp.keyPart.vo;
// default package
// Generated 2013-7-15 9:42:10 ----- 3.4.0.CR1

import java.util.Date;

import com.cdthgk.platform.permission.user.domain.User;

/**
 * KeyPartSecrecyClear  要害部位  密级解除
 */
public class KeyPartSecrecyClear implements java.io.Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -6814985591012551318L;

	private String keyPartSecrecyClearId;//要害部位  密级解除

	private Part keyPartId;  //要害部位

	private Integer clearType;   //解除类型

	private Date clearTime;   //解除时间

	private String reviewPerson;   //审核人

	private String cleanReason; //解除原因

	private User createPerson; //创建人

	private Date createDate;//创建 时间

	/**
	 * 默认构函数
	 */
	public KeyPartSecrecyClear() {
	}

	/**
	 * 返回keyPartSecrecyClearId
	 * @return keyPartSecrecyClearId
	 */
	public String getKeyPartSecrecyClearId() {
		return this.keyPartSecrecyClearId;
	}

	/**
	 * 设置keyPartSecrecyClearId
	 * @param keyPartSecrecyClearId keyPartSecrecyClearId
	 */
	public void setKeyPartSecrecyClearId(String keyPartSecrecyClearId) {
		this.keyPartSecrecyClearId = keyPartSecrecyClearId;
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
