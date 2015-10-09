package com.cdthgk.bmp.log.context;

import com.cdthgk.bmp.log.service.BusinessLogService;
import com.cdthgk.component.ioc.ContextUtils;


/**
 * <p>
 * 类的说明放这里
 * </p>
 * <p>
 * 创建时间 2013-1-16 - 下午1:54:06
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
public class BusinessLogContext {

	private BusinessLogContext(){}

	private final static BusinessLogContext BUSINESS_LOG_CONTEXT = new BusinessLogContext();

	public static BusinessLogContext getInstance(){
		return BUSINESS_LOG_CONTEXT;
	}

	public BusinessLogService getBusinessLogService(){
		return ContextUtils.getDefaultContext().getBean("bmp.businessLogService");
	}
}
