package com.cdthgk.bmp.dataClass.vo;

import java.util.HashSet;
import java.util.Set;

import com.cdthgk.platform.organization.organ.domain.Organ;
import com.thgk.platform.core.BaseDomain;

public class DataClass extends BaseDomain {

	private static final long serialVersionUID = 1L;
	private String dataClassId;
	private Organ organ;
	private String name;
	private Integer status;
	private Set<DataClassOrgan> organs = new HashSet<DataClassOrgan>();
	public String getDataClassId() {
		return dataClassId;
	}
	public void setDataClassId(String dataClassId) {
		this.dataClassId = dataClassId;
	}
	public Organ getOrgan() {
		return organ;
	}
	public void setOrgan(Organ organ) {
		this.organ = organ;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Set<DataClassOrgan> getOrgans() {
		return organs;
	}
	public void setOrgans(Set<DataClassOrgan> organs) {
		this.organs = organs;
	}
}