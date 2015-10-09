package com.cdthgk.bmp.secrecycountryitem.qo;

import javax.servlet.http.HttpServletRequest;

/**
 * 年度统计 控件  提供者
 * @author lwj
 *
 */
public class YearStatProvider {

	//返回的map
	private static YearControlDto yearcd = null;

	private YearStatProvider(HttpServletRequest request){
		String url = request.getParameter("actionUrl");//跳转路径
		String dateType = request.getParameter("dateType");//分时段统计的类型

		//本年度
		if(dateType!=null && dateType.equals(YearControlUtils.YEAR)) {
			yearcd = new YearControlDto();
			yearcd.setBeginDate(YearControlUtils.getCurrentYear_BeginDay());
			yearcd.setEndDate(YearControlUtils.getCurrentYear_EndDay());
			yearcd.setDateType(YearControlUtils.YEAR);
			yearcd.setUrl(url);
		}
		//当前半年度，如果是上半年就是上半年
		else if(dateType!=null && dateType.equals(YearControlUtils.FIRSTHALF)){//上半年
			yearcd = new YearControlDto();
			yearcd.setBeginDate(YearControlUtils.getCurrentYear_BeginDay());
			yearcd.setEndDate(YearControlUtils.getFirstHalf_EndDay());
			yearcd.setDateType(YearControlUtils.FIRSTHALF);
			yearcd.setUrl(url);
		}
		//下半年
		else if(dateType!=null && dateType.equals(YearControlUtils.SECONDHALF)){
			yearcd = new YearControlDto();
			yearcd.setBeginDate(YearControlUtils.getSecondHalf_BeginDay());
			yearcd.setEndDate(YearControlUtils.getCurrentYear_EndDay());
			yearcd.setDateType(YearControlUtils.SECONDHALF);
			yearcd.setUrl(url);
		}
		//本季度
		else if(dateType!=null && dateType.equals(YearControlUtils.QUARTER)){
			yearcd = new YearControlDto();
			yearcd.setBeginDate(YearControlUtils.getCurrentQuarter_BeginDay());
			yearcd.setEndDate(YearControlUtils.getCurrentQuarter_EndDay());
			yearcd.setDateType(YearControlUtils.QUARTER);
			yearcd.setUrl(url);
		}
		//本月
		else if(dateType!=null && dateType.equals(YearControlUtils.MONTH)){
			yearcd = new YearControlDto();
			yearcd.setBeginDate(YearControlUtils.getCurrentMonth_BeginDay());
			yearcd.setEndDate(YearControlUtils.getCurrentMonth_EndDay());
			yearcd.setDateType(YearControlUtils.MONTH);
			yearcd.setUrl(url);
		}
		//自定义
		else {
			yearcd = new YearControlDto();
			if(request.getParameter("beginDate")!=null) {
				yearcd.setBeginDate(request.getParameter("beginDate"));
			}else {
				yearcd.setBeginDate(YearControlUtils.getCurrentDay());
			}
			if(request.getParameter("endDate")!=null) {
				yearcd.setEndDate(request.getParameter("endDate"));
			}else {
				yearcd.setEndDate(YearControlUtils.getCurrentDay());
			}
			yearcd.setDateType(YearControlUtils.RANDDOM);
			if(url!=null) {
				yearcd.setUrl(url);
			}else {
				yearcd.setUrl("");
			}

		}
	}

	public static YearControlDto getIntance(HttpServletRequest request) {
		new YearStatProvider(request);
		return yearcd;
	}

}
