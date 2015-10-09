package com.cdthgk.disclosesecrecy.dto;

public class DiscloseSecrecyStatDto {

	private String name;
	private String id;
	private String districtCode;
	//秘密 	3
	//机密     2
	//绝密     1
	/**
	 * 直辖单位秘密
	 */
	private Integer num1 = 0;
	/**
	 * 直辖单位机密
	 */
	private Integer num2 = 0;
	/**
	 * 直辖单位绝密
	 */
	private Integer num3 = 0;
	/**
	 * 直辖单位总数
	 */
	private Integer selfTotal = 0;

	/**
	 * 本行政区所有单位秘密
	 */
	private Integer num4 = 0;
	/**
	 * 本行政区所有单位机密
	 */
	private Integer num5 = 0;
	/**
	 * 本行政区所有单位绝密
	 */
	private Integer num6 = 0;
	/**
	 * 本行政区所有单位总数
	 */
	private Integer allTotal = 0;
	/**
	 * @return 返回num1
	 */
	public Integer getNum1() {
		return num1;
	}
	/**
	 * @param num1 设置num1
	 */
	public void setNum1(Integer num1) {
		this.num1 = num1;
	}
	/**
	 * @return 返回num2
	 */
	public Integer getNum2() {
		return num2;
	}
	/**
	 * @param num2 设置num2
	 */
	public void setNum2(Integer num2) {
		this.num2 = num2;
	}
	/**
	 * @return 返回num3
	 */
	public Integer getNum3() {
		return num3;
	}
	/**
	 * @param num3 设置num3
	 */
	public void setNum3(Integer num3) {
		this.num3 = num3;
	}
	/**
	 * @return 返回selfTotal
	 */
	public Integer getSelfTotal() {
		return selfTotal;
	}
	/**
	 * @param selfTotal 设置selfTotal
	 */
	public void setSelfTotal(Integer selfTotal) {
		this.selfTotal = selfTotal;
	}
	/**
	 * @return 返回num4
	 */
	public Integer getNum4() {
		return num4;
	}
	/**
	 * @param num4 设置num4
	 */
	public void setNum4(Integer num4) {
		this.num4 = num4;
	}
	/**
	 * @return 返回num5
	 */
	public Integer getNum5() {
		return num5;
	}
	/**
	 * @param num5 设置num5
	 */
	public void setNum5(Integer num5) {
		this.num5 = num5;
	}
	/**
	 * @return 返回num6
	 */
	public Integer getNum6() {
		return num6;
	}
	/**
	 * @param num6 设置num6
	 */
	public void setNum6(Integer num6) {
		this.num6 = num6;
	}
	/**
	 * @return 返回allTotal
	 */
	public Integer getAllTotal() {
		return allTotal;
	}
	/**
	 * @param allTotal 设置allTotal
	 */
	public void setAllTotal(Integer allTotal) {
		this.allTotal = allTotal;
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
	 * @return 返回districtCode
	 */
	public String getDistrictCode() {
		return districtCode;
	}
	/**
	 * @param districtCode 设置districtCode
	 */
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	/**
	 * @return 返回id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id 设置id
	 */
	public void setId(String id) {
		this.id = id;
	}
}
