
package com.cdthgk.bmp.pucha.detail.constants;

import com.cdthgk.component.constant.annotation.Constant;
import com.cdthgk.component.constant.annotation.ConstantClass;

/**
 * <p>
 * 填报详情配置
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 *
 * @author 钟冀
 */
@ConstantClass("填报详情配置")
public class DetailConstants {

	@Constant("详情显示的模块URI请求地址，不包含contextPath")
	private String[] moduleUris;

	/**
	 * 返回moduleUris
	 * @return moduleUris
	 */
	public String[] getModuleUris() {
		return moduleUris;
	}
}
