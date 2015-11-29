package com.cdthgk.bmp.formatFile.vo;

import java.util.Date;

import com.cdthgk.platform.organization.userinfo.domain.UserInfo;

public class FormatFileUserInfo {
	private String id;
	private Integer status;
	private FormatFile formatFile;
	private String formatFileName;
	private UserInfo userInfo;
	private Date viewTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public FormatFile getFormatFile() {
		return formatFile;
	}
	public void setFormatFile(FormatFile formatFile) {
		this.formatFile = formatFile;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public Date getViewTime() {
		return viewTime;
	}
	public void setViewTime(Date viewTime) {
		this.viewTime = viewTime;
	}
	public String getFormatFileName() {
		return formatFileName;
	}
	public void setFormatFileName(String formatFileName) {
		this.formatFileName = formatFileName;
	}
}