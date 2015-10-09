package com.cdthgk.bmp.info.vo;

import java.util.Date;

import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.permission.user.domain.User;

public class InfoWarn implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String infoWarnId;
	private Info info;
	private Organ organ;
	private Integer	status;
	private Date viewTime;
	private User viewUser;
	private String reportOrgan;
	private String title;
	private Date reportTime;

	/**
	 * 冗余字段用户按时间段查询
	 */
	private String reportBeginTime;
	private String reportEndTime;
	/**
	 * @return 返回infoWarnId
	 */
	public String getInfoWarnId() {
		return infoWarnId;
	}
	/**
	 * @param infoWarnId 设置infoWarnId
	 */
	public void setInfoWarnId(String infoWarnId) {
		this.infoWarnId = infoWarnId;
	}
	/**
	 * @return 返回info
	 */
	public Info getInfo() {
		return info;
	}
	/**
	 * @param info 设置info
	 */
	public void setInfo(Info info) {
		this.info = info;
	}
	/**
	 * @return 返回organ
	 */
	public Organ getOrgan() {
		return organ;
	}
	/**
	 * @param organ 设置organ
	 */
	public void setOrgan(Organ organ) {
		this.organ = organ;
	}
	/**
	 * @return 返回status
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * @param status 设置status
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * @return 返回viewTime
	 */
	public Date getViewTime() {
		return viewTime;
	}
	/**
	 * @param viewTime 设置viewTime
	 */
	public void setViewTime(Date viewTime) {
		this.viewTime = viewTime;
	}
	/**
	 * @return 返回viewUser
	 */
	public User getViewUser() {
		return viewUser;
	}
	/**
	 * @param viewUser 设置viewUser
	 */
	public void setViewUser(User viewUser) {
		this.viewUser = viewUser;
	}
	/**
	 * @return 返回reportOrgan
	 */
	public String getReportOrgan() {
		return reportOrgan;
	}
	/**
	 * @param reportOrgan 设置reportOrgan
	 */
	public void setReportOrgan(String reportOrgan) {
		this.reportOrgan = reportOrgan;
	}
	/**
	 * @return 返回title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title 设置title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return 返回reportTime
	 */
	public Date getReportTime() {
		return reportTime;
	}
	/**
	 * @param reportTime 设置reportTime
	 */
	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}
	/**
	 * @return the reportBeginTime
	 */
	public String getReportBeginTime() {
		return reportBeginTime;
	}
	/**
	 * @param reportBeginTime the reportBeginTime to set
	 */
	public void setReportBeginTime(String reportBeginTime) {
		this.reportBeginTime = reportBeginTime;
	}
	/**
	 * @return the reportEndTime
	 */
	public String getReportEndTime() {
		return reportEndTime;
	}
	/**
	 * @param reportEndTime the reportEndTime to set
	 */
	public void setReportEndTime(String reportEndTime) {
		this.reportEndTime = reportEndTime;
	}

}