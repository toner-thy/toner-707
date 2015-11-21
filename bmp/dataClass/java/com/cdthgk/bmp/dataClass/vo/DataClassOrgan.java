package com.cdthgk.bmp.dataClass.vo;

import com.cdthgk.platform.organization.organ.domain.Organ;

public class DataClassOrgan {

	private String id;
	private DataClass dataClass;
	private Organ organ;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Organ getOrgan() {
		return organ;
	}
	public void setOrgan(Organ organ) {
		this.organ = organ;
	}
	public DataClass getDataClass() {
		return dataClass;
	}
	public void setDataClass(DataClass dataClass) {
		this.dataClass = dataClass;
	}
}