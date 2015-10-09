package com.cdthgk.bmp.secrecycountryitem.qo;

/**年度控件  dto
 * @author lwj
 *
 * beginDate开始时间
 * endDate结束时间
 * url跳转路径
 */
public class YearControlDto {

	private String dateType; //类型
 	private String beginDate;//开始时间
	private String endDate;//结束时间
	private String url;//路径


	/***********************************************getter and setter************************************************/
	public String getDateType() {
		return dateType;
	}

	public void setDateType(String dateType) {
		this.dateType = dateType;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
