package com.cdthgk.bmp.attachment.dto;

import java.util.Date;

import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.permission.user.domain.User;

public class AttachmentDto {

	private String attachId;
	private String attachName;
	private String attachUrl;
	private String attachSize;
	private Integer attachLength;
	private String hostId;
	private Date createTime;
	private String description;
	private Integer downCount;
	private String isFree;
	private String attachFtpUrl;

	private User user;
	private Organ organ;
	private District district;

	public String getAttachId() {
		return attachId;
	}
	public void setAttachId(String attachId) {
		this.attachId = attachId;
	}
	public String getAttachName() {
		return attachName;
	}
	public void setAttachName(String attachName) {
		this.attachName = attachName;
	}
	public String getAttachUrl() {
		return attachUrl;
	}
	public void setAttachUrl(String attachUrl) {
		this.attachUrl = attachUrl;
	}
	public String getAttachSize() {
		return attachSize;
	}
	public void setAttachSize(String attachSize) {
		this.attachSize = attachSize;
	}
	public Integer getAttachLength() {
		return attachLength;
	}
	public void setAttachLength(Integer attachLength) {
		this.attachLength = attachLength;
	}
	public String getHostId() {
		return hostId;
	}
	public void setHostId(String hostId) {
		this.hostId = hostId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getDownCount() {
		return downCount;
	}
	public void setDownCount(Integer downCount) {
		this.downCount = downCount;
	}
	public String getIsFree() {
		return isFree;
	}
	public void setIsFree(String isFree) {
		this.isFree = isFree;
	}
	public String getAttachFtpUrl() {
		return attachFtpUrl;
	}
	public void setAttachFtpUrl(String attachFtpUrl) {
		this.attachFtpUrl = attachFtpUrl;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Organ getOrgan() {
		return organ;
	}
	public void setOrgan(Organ organ) {
		this.organ = organ;
	}
	public District getDistrict() {
		return district;
	}
	public void setDistrict(District district) {
		this.district = district;
	}

}
