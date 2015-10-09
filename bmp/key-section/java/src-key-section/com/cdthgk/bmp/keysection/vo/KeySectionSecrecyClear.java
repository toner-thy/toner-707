package com.cdthgk.bmp.keysection.vo;
// default package
// Generated 2013-7-13 14:02:01 ----- 3.4.0.CR1

import java.util.Date;

import com.cdthgk.platform.permission.user.domain.User;

/**
 * BmKeySectionSecrecyClear   要害部门解密
 */
public class KeySectionSecrecyClear implements java.io.Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 6880410550817333115L;

	private String sectionSecrecyClearId;//要害部门解密  主键id

	private KeySection keySectionId;//外键   要害部门ID

	private Integer clearType; //解密类型

	private Date clearTime;  //解密时间

	private User reviewPerson; //审核人

	private String cleanReason; //解密原因

	private User createPerson; //创建人

	private Date createDate; //创建时间

	/**
	 * 默认构造函数
	 */
	public KeySectionSecrecyClear() {
	}

	/**
	 * 返回sectionSecrecyClearId
	 * @return sectionSecrecyClearId
	 */
	public String getSectionSecrecyClearId() {
		return this.sectionSecrecyClearId;
	}

	/**
	 * 设置sectionSecrecyClearId
	 * @param sectionSecrecyClearId sectionSecrecyClearId
	 */
	public void setSectionSecrecyClearId(String sectionSecrecyClearId) {
		this.sectionSecrecyClearId = sectionSecrecyClearId;
	}

	/**
	 * 返回keySectionId
	 * @return keySectionId
	 */
	public KeySection getKeySectionId() {
		return this.keySectionId;
	}

	/**
	 * 设置keySectionId
	 * @param keySectionId keySectionId
	 */
	public void setKeySectionId(KeySection keySectionId) {
		this.keySectionId = keySectionId;
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
	public User getReviewPerson() {
		return this.reviewPerson;
	}

	/**
	 * 设置reviewPerson
	 * @param reviewPerson reviewPerson
	 */
	public void setReviewPerson(User reviewPerson) {
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
