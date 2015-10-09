package com.cdthgk.bmp.info.vo;

import com.cdthgk.platform.organization.organ.domain.Organ;

public class InfoType implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String infoTypeId;
	private String name;
	private String description;
	private Organ organ;
	/**
	 * @return 返回infoTypeId
	 */
	public String getInfoTypeId() {
		return infoTypeId;
	}
	/**
	 * @param infoTypeId 设置infoTypeId
	 */
	public void setInfoTypeId(String infoTypeId) {
		this.infoTypeId = infoTypeId;
	}
	/**
	 * @return 返回name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name 设置name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return 返回description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description 设置description
	 */
	public void setDescription(String description) {
		this.description = description;
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

}