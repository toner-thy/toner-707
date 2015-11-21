package com.cdthgk.bmp.dataClass.vo;

import java.util.Date;

import com.cdthgk.platform.organization.organ.domain.Organ;
import com.thgk.platform.core.BaseDomain;

public class DataClassPro extends BaseDomain{
	private static final long serialVersionUID = 1L;
	private String id;
	private String fillPerson;
	private String auditPerson;
	private Date fillTime;
	private String remark;
	private DataClass dataClass;
	private Organ organ;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFillPerson() {
		return fillPerson;
	}
	public void setFillPerson(String fillPerson) {
		this.fillPerson = fillPerson;
	}
	public String getAuditPerson() {
		return auditPerson;
	}
	public void setAuditPerson(String auditPerson) {
		this.auditPerson = auditPerson;
	}
	public Date getFillTime() {
		return fillTime;
	}
	public void setFillTime(Date fillTime) {
		this.fillTime = fillTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public DataClass getDataClass() {
		return dataClass;
	}
	public void setDataClass(DataClass dataClass) {
		this.dataClass = dataClass;
	}
	public Organ getOrgan() {
		return organ;
	}
	public void setOrgan(Organ organ) {
		this.organ = organ;
	}
}