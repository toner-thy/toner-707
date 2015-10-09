package com.cdthgk.bmp.secrecynet.secrecytroops.vo;

import java.util.Date;

import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.permission.user.domain.User;
import com.thgk.platform.core.BaseDomain;

public class SecrecyTroops extends BaseDomain{
	private static final long serialVersionUID = 1L;

	private String id;
	private Integer year;
	private Integer smallOrganNum;
	private Integer jupPlaitNum;
	private Integer juExistNum;
	private Integer zxPlaitNum;
	private Integer zxExistNum;
	private Integer degreeBoNum;
	private Integer degreeSuoNum;
	private Integer degreeBenNum;
	private Integer degreeDazNum;
	private Integer specialyComputerNum;
	private Integer specialyOtherNum;
	private Integer ageFortyfiveDown;
	private Integer ageFortyfiveUp;
	private Organ createOrgan;
	private Department createDepartment;
	private Organ reportOrgan;
	private User reportUser;
	private Date reportDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getSmallOrganNum() {
		return smallOrganNum;
	}
	public void setSmallOrganNum(Integer smallOrganNum) {
		this.smallOrganNum = smallOrganNum;
	}
	public Integer getJupPlaitNum() {
		return jupPlaitNum;
	}
	public void setJupPlaitNum(Integer jupPlaitNum) {
		this.jupPlaitNum = jupPlaitNum;
	}
	public Integer getJuExistNum() {
		return juExistNum;
	}
	public void setJuExistNum(Integer juExistNum) {
		this.juExistNum = juExistNum;
	}
	public Integer getZxPlaitNum() {
		return zxPlaitNum;
	}
	public void setZxPlaitNum(Integer zxPlaitNum) {
		this.zxPlaitNum = zxPlaitNum;
	}
	public Integer getZxExistNum() {
		return zxExistNum;
	}
	public void setZxExistNum(Integer zxExistNum) {
		this.zxExistNum = zxExistNum;
	}
	public Integer getDegreeBoNum() {
		return degreeBoNum;
	}
	public void setDegreeBoNum(Integer degreeBoNum) {
		this.degreeBoNum = degreeBoNum;
	}
	public Integer getDegreeSuoNum() {
		return degreeSuoNum;
	}
	public void setDegreeSuoNum(Integer degreeSuoNum) {
		this.degreeSuoNum = degreeSuoNum;
	}
	public Integer getDegreeBenNum() {
		return degreeBenNum;
	}
	public void setDegreeBenNum(Integer degreeBenNum) {
		this.degreeBenNum = degreeBenNum;
	}
	public Integer getDegreeDazNum() {
		return degreeDazNum;
	}
	public void setDegreeDazNum(Integer degreeDazNum) {
		this.degreeDazNum = degreeDazNum;
	}
	public Integer getSpecialyComputerNum() {
		return specialyComputerNum;
	}
	public void setSpecialyComputerNum(Integer specialyComputerNum) {
		this.specialyComputerNum = specialyComputerNum;
	}
	public Integer getSpecialyOtherNum() {
		return specialyOtherNum;
	}
	public void setSpecialyOtherNum(Integer specialyOtherNum) {
		this.specialyOtherNum = specialyOtherNum;
	}
	public Integer getAgeFortyfiveDown() {
		return ageFortyfiveDown;
	}
	public void setAgeFortyfiveDown(Integer ageFortyfiveDown) {
		this.ageFortyfiveDown = ageFortyfiveDown;
	}
	public Integer getAgeFortyfiveUp() {
		return ageFortyfiveUp;
	}
	public void setAgeFortyfiveUp(Integer ageFortyfiveUp) {
		this.ageFortyfiveUp = ageFortyfiveUp;
	}
	public Organ getCreateOrgan() {
		return createOrgan;
	}
	public void setCreateOrgan(Organ createOrgan) {
		this.createOrgan = createOrgan;
	}
	public Department getCreateDepartment() {
		return createDepartment;
	}
	public void setCreateDepartment(Department createDepartment) {
		this.createDepartment = createDepartment;
	}
	public Organ getReportOrgan() {
		return reportOrgan;
	}
	public void setReportOrgan(Organ reportOrgan) {
		this.reportOrgan = reportOrgan;
	}
	public User getReportUser() {
		return reportUser;
	}
	public void setReportUser(User reportUser) {
		this.reportUser = reportUser;
	}
	public Date getReportDate() {
		return reportDate;
	}
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

}