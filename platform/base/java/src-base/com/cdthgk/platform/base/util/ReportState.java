
package com.cdthgk.platform.base.util;

/**
 * <p>
 * 上报状态
 * </p>
 * <p>
 * 创建时间 2013-4-11 - 上午9:37:52
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
public abstract class ReportState {
	Integer reportState;

	// 未上报0
	public static final Integer REPORT_NO = 0;
	// 已上报1
	public static final Integer REPORT_YES = 1;
	// 上报后修改2
	public static final Integer REPORT_YES_MODIFY = 2;

	/**
	 * @return 返回reportState
	 */
	public Integer getReportState() {
		return reportState;
	}

	/**
	 * @param reportState 设置reportState
	 */
	public void setReportState(Integer reportState) {
		this.reportState = reportState;
	}
}
