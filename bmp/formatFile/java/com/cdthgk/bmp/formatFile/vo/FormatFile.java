package com.cdthgk.bmp.formatFile.vo;

import java.util.HashSet;
import java.util.Set;

import com.cdthgk.platform.organization.organ.domain.Organ;
import com.thgk.platform.core.BaseDomain;

public class FormatFile extends BaseDomain {

	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String formatFileUrl;
	private Integer status;
	private Organ organ;
	private Set<FormatFileUserInfo> userInfoSet = new HashSet<FormatFileUserInfo>();
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFormatFileUrl() {
		return formatFileUrl;
	}
	public void setFormatFileUrl(String formatFileUrl) {
		this.formatFileUrl = formatFileUrl;
	}
	public Organ getOrgan() {
		return organ;
	}
	public void setOrgan(Organ organ) {
		this.organ = organ;
	}
	public Set<FormatFileUserInfo> getUserInfoSet() {
		return userInfoSet;
	}
	public void setUserInfoSet(Set<FormatFileUserInfo> userInfoSet) {
		this.userInfoSet = userInfoSet;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}