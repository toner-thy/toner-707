package com.cdthgk.platform.msg;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cdthgk.platform.msg.dto.ImsMessageDto;

/**
 *
 * <p>
 * IMS发送消息帮助类
 * </p>
 * <p>
 * 创建时间 2013-11-13 - 上午11:52:12
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
@Service
public class ImsMessageUtil{

	private static final Logger LOGGER = LoggerFactory.getLogger(ImsMessageUtil.class);
	/**
	 *
	 * <p>
	 * 发送IMS消息（ImsMessageDto中消息发送的机构LIST,消息发送的城市LIST,消息发送的用户集合三者一般任选一个传递,若三者同事传参,将使用"消息发送的用户集合"另外两个参数内容将被忽略.）
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-11-13 - 上午11:28:37
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param httpServletRequest request
	 * @param ImsMessageDto imsDto
	 * @return
	 */
	public static String sendImsMessage(ImsMessageDto imsDto, HttpServletRequest req) {
//		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(req.getSession().getServletContext());
//		ImsService imsService = (ImsService) ctx.getBean("ims.imsService");
		String result = "";
//		try {
//			result = imsService.saveErrorAndSendMsg(imsDto.getTitle()
//					, imsDto.getContent(), imsDto.getOrganList()
//					, imsDto.getDistrictList(),imsDto.getUserList()
//					, imsDto.getPublisher(), imsDto.getCompanyname()
//					, imsDto.getMsgPushTime(), imsDto.getValidity());
//		} catch (Exception e) {
//			e.printStackTrace();
//			LOGGER.debug("------->>>>>>>>发送IMS消息时出错<<<<<<<<------");
//		}
		return result;
	}
}