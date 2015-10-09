
package com.cdthgk.bmp.model;

/**
 * <p>
 * 国家秘密
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 *
 * @author 钟冀
 */
public interface NationSecrecy extends Secrecy{
	/**
	 * 绝密
	 */
	int SECRECY_LEVEL_HIGH = SecrecyLevel.high.ordinal();
	/**
	 * 机密
	 */
	int SECRECY_LEVEL_MIDDLE = SecrecyLevel.middle.ordinal();
	/**
	 * 秘密
	 */
	int SECRECY_LEVEL_LOW = SecrecyLevel.low.ordinal();

	/**
	 * <p>
	 * 国家秘密等级
	 * </p>
	 *
	 * @author 钟冀
	 */
	public static enum SecrecyLevel {
		/**
		 * 绝密
		 */
		high,
		/**
		 * 机密
		 */
		middle,
		/**
		 * 秘密
		 */
		low
	}
}
