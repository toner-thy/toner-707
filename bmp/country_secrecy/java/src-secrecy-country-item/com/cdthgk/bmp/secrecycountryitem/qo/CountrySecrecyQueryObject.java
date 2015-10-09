package com.cdthgk.bmp.secrecycountryitem.qo;

import java.io.Serializable;
import java.util.Calendar;

public class CountrySecrecyQueryObject implements Serializable{

	private static final long serialVersionUID = 430453165635787774L;

	private String beginDate;//开始时间
	private String endDate;//结束时间
	private Integer rflag;//标志  1新增国家秘密  2新解国家秘密  0国家秘密总数

	public Integer getRflag() {
		return rflag;
	}
	public void setRflag(Integer rflag) {
		this.rflag = rflag;
	}
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}


	/**(本单位)总数
	 * 统计本单位 国家秘密总数  得到初始化的查询条件
	 * @return
	 */
	public static CountrySecrecyQueryObject organ_CountrySecrecy_Count() {
		CountrySecrecyQueryObject obj = new CountrySecrecyQueryObject();
		obj.setRflag(0);
		return obj;
	}

	/**(本单位)新增
	 * 统计本单位新增  国家秘密总数   得到初始化的查询条件
	 * @return
	 */
	public static CountrySecrecyQueryObject organ_CountrySecrecy_New_Add() {
		CountrySecrecyQueryObject obj = new CountrySecrecyQueryObject();
		obj.setRflag(1);
		return obj;
	}

	/**(本单位)新解
	 * 统计本单位新解 国家秘密总数   得到初始化的查询条件
	 * @return
	 */
	public static CountrySecrecyQueryObject organ_CountrySecrecy_New_Clear() {
		CountrySecrecyQueryObject obj = new CountrySecrecyQueryObject();
		obj.setRflag(2);
		return obj;
	}




}
