package com.cdthgk.loginCheck.action;

import java.io.File;
import java.io.FileNotFoundException;

import javax.servlet.http.HttpSession;

import com.cdthgk.bmp.core.action.BmpAction;

/**
 * <p>
 * 登录平台Login图片设置
 * </p>
 * <p>
 * 牟远洋 2012-1-22 18:01
 * </p>
 * <blockquote>
 * <h4>历史修改记录</h4>
 * <ul>
 * <li>修改人 修改时间 修改描述
 * </ul>
 * </blockquote>
 * <p>
 * copyright tianyu 2012, all rights reserved.
 * </p>
 *
 * @author mouyuanyang
 * @since 1.0
 * @version 1.0
 */
public class LoginTypeCheckAction extends BmpAction{

	private static final long serialVersionUID = 1L;

	// *********************** 方 法 ***********************

	// 构造器
	/** 默认构造器 */
	public LoginTypeCheckAction() {
	}

	/**
	 * <p>
	 * 获取登录Login图片
	 * </p>
	 * <p>
	 * 创建时间 2012-1-22 16:25
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * <p>
	 * copyright tianyu 2012, all rights reserved.
	 * </p>
	 *
	 * @author mouyuanyang
	 * @version 1.0
	 * @return secrecyPersonList
	 * @throws FileNotFoundException
	 * @throws ParseException ParseException
	 */
	public String loginType() throws FileNotFoundException{

		HttpSession session = getRequest().getSession();
		String loginType = (String)session.getAttribute("loginType");

		File imgFile = null;
		// 判断登录类型，得到相应LOGO
		if("scbms".equals(loginType)) {
			imgFile = new File(getRequest().getSession().getServletContext().getRealPath("/bmp/borderlayout/skin/blue/img/index/logo_blue_scbms.GIF"));
		} else if("scbmp".equals(loginType)) {
			imgFile = new File(getRequest().getSession().getServletContext().getRealPath("/bmp/borderlayout/skin/blue/img/index/logo_blue_scbmp.GIF"));
		} else if("center".equals(loginType)) {
			imgFile = new File(getRequest().getSession().getServletContext().getRealPath("/bmp/borderlayout/skin/blue/img/index/logo_blue_cen.GIF"));
		}else {
			imgFile = new File(getRequest().getSession().getServletContext().getRealPath("/bmp/borderlayout/skin/blue/img/index/logo_blue_bms.GIF"));
		}
		setResultData(imgFile);
		return DOWNLOAD;
	}

}