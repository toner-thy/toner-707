package com.cdthgk.bmp.feedbackInfo.vo;

import com.cdthgk.platform.organization.organ.domain.Organ;
import com.thgk.platform.core.BaseDomain;

public class FeedbackInfo extends BaseDomain implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// 反馈信息ID
	private String feedbackInfoId;
	// 类型
	private Integer feedbackType;
	// 反馈主题
	private String feedbackTitle;
	// 状态
	private Integer status;
	// 内容描述
	private String content;

	//单位
	private Organ organ;

	//部门
	private String department;
	/**
	 * 返回feedbackInfoId
	 * @return feedbackInfoId
	 */
	public String getFeedbackInfoId() {
		return feedbackInfoId;
	}
	/**
	 * 设置feedbackInfoId
	 * @param feedbackInfoId feedbackInfoId
	 */
	public void setFeedbackInfoId(String feedbackInfoId) {
		this.feedbackInfoId = feedbackInfoId;
	}
	/**
	 * 返回feedbackType
	 * @return feedbackType
	 */
	public Integer getFeedbackType() {
		return feedbackType;
	}
	/**
	 * 设置feedbackType
	 * @param feedbackType feedbackType
	 */
	public void setFeedbackType(Integer feedbackType) {
		this.feedbackType = feedbackType;
	}
	/**
	 * 返回feedbackTitle
	 * @return feedbackTitle
	 */
	public String getFeedbackTitle() {
		return feedbackTitle;
	}
	/**
	 * 设置feedbackTitle
	 * @param feedbackTitle feedbackTitle
	 */
	public void setFeedbackTitle(String feedbackTitle) {
		this.feedbackTitle = feedbackTitle;
	}
	/**
	 * 返回status
	 * @return status
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置status
	 * @param status status
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 返回content
	 * @return content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置content
	 * @param content content
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 返回serialversionuid
	 * @return serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 * @return the organ
	 */
	public Organ getOrgan() {
		return organ;
	}
	/**
	 * @param organ the organ to set
	 */
	public void setOrgan(Organ organ) {
		this.organ = organ;
	}
	/**
	 * @return the department
	 */
	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}
	/**
	 * @param department the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}
}