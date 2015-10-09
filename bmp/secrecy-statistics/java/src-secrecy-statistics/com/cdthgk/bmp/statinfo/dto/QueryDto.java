
package com.cdthgk.bmp.statinfo.dto;

import java.util.Date;

/**
 * <p>
 * 类的说明放这里
 * </p>
 * <p>
 * 创建时间 2014-5-5 - 下午4:25:05
 * </p>
 * <blockquote>
 * <h4>历史修改记录</h4>
 * <ul>
 * <li>修改人 修改时间 修改描述
 * </ul>
 * </blockquote>
 * <p>
 * copyright cdthgk 2010-2011, all rights reserved.
 * </p>
 *
 * @author 陶汇源
 * @author cdthgk r&d
 * @since 1.0
 * @version 1.0
 */
public class QueryDto {

	public int year;
	public int month;

	/**
	 * @return 返回year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @param year 设置year
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * @return 返回month
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * @param month 设置month
	 */
	public void setMonth(int month) {
		this.month = month;
	}
}
