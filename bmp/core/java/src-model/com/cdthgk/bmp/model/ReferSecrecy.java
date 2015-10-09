
package com.cdthgk.bmp.model;

/**
 * <p>
 * 涉密
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 *
 * @author 钟冀
 */
public interface ReferSecrecy {
	/**
	 * <p>
	 * 返回涉密程度
	 * </p>
	 * @return 涉密程度
	 */
	Integer getSecrecyLevel();
	/**
	 * <p>
	 * 设置涉密程度
	 * </p>
	 * @param secrecyLevel secrecyLevel
	 */
	void setSecrecyLevel(Integer secrecyLevel);
}
