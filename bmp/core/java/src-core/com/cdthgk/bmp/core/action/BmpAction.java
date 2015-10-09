package com.cdthgk.bmp.core.action;

import com.cdthgk.platform.base.action.PlatformAction;

/**
 * <p>
 * 类的说明放这里
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 *
 * @author 钟冀
 */
public class BmpAction extends PlatformAction{

	/**
	 */
	public BmpAction() {
	}

	/**
	 * 涉密状态 现有的 =11
	 */
	public static final Integer SECRECY_STATUS_NOW = 11;
	/**
	 * 涉密状态 密级变更 =12
	 */
	public static final Integer SECRECY_STATUS_CHANGE = 12;
	/**
	 * 涉密状态 脱密 =13
	 */
	public static final Integer SECRECY_STATUS_DECRYPTION = 13;
	/**
	 *  人员变动状态 现有人员状态
	 */
	public static final Integer PERSON_CHANGE_NOW = 14;
	/**
	 *  人员变动状态  已变更状态
	 */
	public static final Integer PERSON_CHANGE_HISTORY = 15;


	private static final long serialVersionUID = -5950323181832784144L;


}
