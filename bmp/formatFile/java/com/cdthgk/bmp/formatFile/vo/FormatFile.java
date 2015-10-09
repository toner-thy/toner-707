package com.cdthgk.bmp.formatFile.vo;

import com.cdthgk.platform.organization.organ.domain.Organ;
import com.thgk.platform.core.BaseDomain;

public class FormatFile extends BaseDomain {

	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String fileUrl;
	private String formatFileUrl;
	private Organ organ;
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
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
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
}