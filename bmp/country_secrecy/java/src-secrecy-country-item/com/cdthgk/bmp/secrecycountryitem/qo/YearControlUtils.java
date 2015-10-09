package com.cdthgk.bmp.secrecycountryitem.qo;

import java.util.Calendar;

/**
 * 年度控件 辅助类
 * @author 梁文杰
 *
 *
 *
 */
public class YearControlUtils {

	public static String YEAR = "year";//year    年
	public static String FIRSTHALF = "firstHalf";//firstHalf   上半年
	public static String SECONDHALF = "secondHalf";//secondHalf   下半年
	public static String QUARTER = "quarter";//quarter    本季度
	public static String MONTH = "month";//month   本月
	public static String RANDDOM = "random";//自定义


	/**得到当前年的开始日期  yyyy-01-01*/
	public static String getCurrentYear_BeginDay() {
		int iYear = getCurrentYear();
		return iYear + "-01-01";
	}

	/** 得到当前年的结束日期  yyyy-12-31*/
	public static String getCurrentYear_EndDay() {
		int iYear = getCurrentYear();
		return iYear + "-12-31";
	}

	/**得到当前年上半年    的结束时间  yyyy-06-30*/
	public static String getFirstHalf_EndDay(){
		int iYear = getCurrentYear();
		return iYear + "-06-30";
	}

	/**得到当前年下半年  的开始时间  yyyy-07-01*/
	public static String getSecondHalf_BeginDay(){
		int iYear = getCurrentYear();
		return iYear + "-07-01";
	}

	/**得到本季度的第一天**/
	public static String getCurrentQuarter_BeginDay() {
		int iYear = getCurrentYear();
		int iQuarter = getCurrentQuarter();

		if(iQuarter==1) {
			return iYear + "-01-01";
		}else if(iQuarter==2) {
			return iYear + "-04-01";
		}else if(iQuarter==3) {
			return iYear + "-07-01";
		}else {
			return iYear + "-10-01";
		}
	}

	/**得到本季度的最后一天*/
	public static String getCurrentQuarter_EndDay() {
		int iYear = getCurrentYear();
		int iQuarter = getCurrentQuarter();

		if(iQuarter==1) {
			return iYear + "-03-31";
		}else if(iQuarter==2) {
			return iYear + "-06-30";
		}else if(iQuarter==3) {
			return iYear + "-09-30";
		}else{
			return iYear + "-12-31";
		}
	}

	/**得到当前月份的第一天**/
	public static String getCurrentMonth_BeginDay() {
		int iMonth = getCurrentMonth();
		return getCurrentYear() + "-" + formatNumber2String(iMonth) + "-01";
	}

	/**得到当前月份的最后一天**/
	public static String getCurrentMonth_EndDay() {
		int iYear = getCurrentYear();
		int iMonth = getCurrentMonth();
		return iYear + "-" + formatNumber2String(iMonth) + "-" + getMonthDays(iYear,iMonth);
	}

	/**得到今天的日期yyyy-MM-dd，不包含时间字段      **/
	public static String getCurrentDay(){
		int iYear = getCurrentYear();
		String sMonth = formatNumber2String(getCurrentMonth());
		String sDay = formatNumber2String(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

		return iYear + "-" + sMonth + "-" + sDay;
	}


	/**得到当前年度，比如今年是2013年，则返回2013*/
	public static int getCurrentYear(){
		return Calendar.getInstance().get(Calendar.YEAR);
	}

	/** 得到当前月份,比如现在是6月，则返回6 */
	public static int getCurrentMonth() {
		//java 里边月份是从0开始的，所以要+1
		return Calendar.getInstance().get(Calendar.MONTH) + 1;
	}

	/**得到当前季度,比如现在是2月，就返回1，如果是7月，返回3,如果出现其他的情况返回0*/
	public static int getCurrentQuarter(){
		int monthNumber = getCurrentMonth();//得到月份

		if(monthNumber==1 || monthNumber==2 || monthNumber==3) {
			return 1;
		}else if(monthNumber==4 || monthNumber==5 || monthNumber==6) {
			return 2;
		}else if(monthNumber==7 || monthNumber==8 || monthNumber==9) {
			return 3;
		}else if(monthNumber==10 || monthNumber==11 || monthNumber==12) {
			return 4;
		}else {
			return 0;
		}
	}

	/**格式化  将数字1到9转成字符串，并且补充0**/
	private static String formatNumber2String(int iMonth) {
		return iMonth < 10 ? "0" + String.valueOf(iMonth) : String.valueOf(iMonth);
	}

	/**判断是否是闰年**/
	public static boolean isRunYear(int iYear){
		return (iYear%4 == 0 && iYear%100 != 0) || iYear%400 == 0 ? Boolean.TRUE : Boolean.FALSE;
	}

	/**得到每个月有多少天**/
	public static int getMonthDays(int iYear, int iMonth) {
		if(iMonth==2) {
			return isRunYear(iYear)? 29: 28;
		}else if(iMonth==1 || iMonth==3 || iMonth==5 || iMonth==7 || iMonth==8 || iMonth==10 || iMonth==12){
			return 31;
		}else if(iMonth==4 || iMonth==6 || iMonth==9 || iMonth==11){
			return 30;
		}else {
			return 0;
		}
	}

	/**判断是否是上半年  是返回true **/
	public static boolean isFirstHalf() {
		return getCurrentMonth() < 6 ? Boolean.TRUE : Boolean.FALSE;
	}

	public static void main(String[] args) {
		String s = YearControlUtils.getCurrentDay();
		System.out.println(s);
	}
}
