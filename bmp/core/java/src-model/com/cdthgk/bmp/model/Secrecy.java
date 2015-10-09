
package com.cdthgk.bmp.model;

/**
 * <p>
 * 秘密
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 *
 * @author 钟冀
 */
public interface Secrecy {
	/**
	 * <p>
	 * id
	 * </p>
	 * @return id
	 */
	String getId();
	/**
	 * <p>
	 * 获取名称
	 * </p>
	 * @return 名称
	 */
	String getName();
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
