
package com.cdthgk.bmp.secrecyorganization.secrecyoffice.dto;

import java.text.DecimalFormat;

/**
 * <p>
 * 类的说明放这里
 * </p>
 * <p>
 * 创建时间 2013-7-25 - 下午4:43:41
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
public class ObjectNumberFomart {

	public String percent(int chushu, int beichushu) {
		Double bei = Double.parseDouble(beichushu + "");
		if(chushu == 0){
			return "0.00%";
		}

		if(bei != 0){
			Double result = Double.parseDouble(chushu + "") / bei * 100;
			DecimalFormat df = new DecimalFormat(".00");
			String resultStr = df.format(result) + "%";
			return resultStr;
		}

		return "";
	}

}
