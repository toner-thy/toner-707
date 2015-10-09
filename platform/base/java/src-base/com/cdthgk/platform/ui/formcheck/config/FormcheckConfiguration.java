
package com.cdthgk.platform.ui.formcheck.config;

import com.cdthgk.component.configuration.annotation.Configuration;
import com.cdthgk.component.configuration.annotation.ConfigurationClass;

/**
 * <p>
 * 类的说明放这里
 * </p>
 * <p>
 * 创建时间 2013-8-27 - 上午9:57:11
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
@ConfigurationClass("Formcheck配置类")
public class FormcheckConfiguration {

	@Configuration("Formcheck何时显示提示")
	private int showErrors = 1;

	@Configuration("Formcheck验证显示方式")
	private int errorsLocation = 3;

	@Configuration("Formcheck过滤空白字符")
	private boolean trimValue = true;

	/**
	 * @return 返回showErrors
	 */
	public int getShowErrors() {
		return showErrors;
	}

	/**
	 * @param showErrors 设置showErrors
	 */
	public void setShowErrors(int showErrors) {
		this.showErrors = showErrors;
	}

	/**
	 * @return 返回errorsLocation
	 */
	public int getErrorsLocation() {
		return errorsLocation;
	}

	/**
	 * @param errorsLocation 设置errorsLocation
	 */
	public void setErrorsLocation(int errorsLocation) {
		this.errorsLocation = errorsLocation;
	}

	/**
	 * @return 返回trimValue
	 */
	public boolean isTrimValue() {
		return trimValue;
	}

	/**
	 * @param trimValue 设置trimValue
	 */
	public void setTrimValue(boolean trimValue) {
		this.trimValue = trimValue;
	}
}
