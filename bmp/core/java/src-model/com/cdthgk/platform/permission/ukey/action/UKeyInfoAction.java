package com.cdthgk.platform.permission.ukey.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.cdthgk.common.algorithm.Base64;
import com.cdthgk.common.lang.UUIDGenerator;
import com.cdthgk.platform.base.action.PlatformAction;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;
import com.cdthgk.platform.organization.userinfo.service.UserInfoService;
import com.cdthgk.platform.permission.ukey.domain.UKeyHistory;
import com.cdthgk.platform.permission.ukey.domain.UKeyInfo;
import com.cdthgk.platform.permission.ukey.service.UKeyHistoryService;
import com.cdthgk.platform.permission.ukey.service.UKeyInfoService;
import com.cdthgk.platform.permission.user.domain.User;
import com.cdthgk.platform.permission.user.service.UserService;

import ec.common.PageSortModel;

/**
 * <p>
 *  UKeyInfoAction.java: ukey信息控制类
 * <p>
 * 刘椿成 2012-06-25 10:30
 * </p>
 * <blockquote>
 * <h4>历史修改记录</h4>
 * <ul>
 * <li>修改人 修改时间 修改描述
 * <li>宋亚菲 2013-03-26 13:40 从老平台移植到新平台
 * </ul>
 * </blockquote>
 * <p>
 * copyright cdthgk 2012-2013, all rights reserved.
 * </p>
 *
 * @author 刘椿成
 * @author thgk
 * @since 1.0
 * @version 1.0
 */
public class UKeyInfoAction extends PlatformAction {

	// Fields
	private static final long serialVersionUID = 1L;
	private UKeyInfoService uKeyInfoService;
	private UserInfo userInfo;
	private UserInfoService userInfoService;
	private UKeyInfo ukeyInfo;
	private District district;
	private Organ organ;
	private User user;
	private String userId;
	private String keyId;
	private String keyURL;

	// 业务逻辑组件
	private String idFromToken;
	private UserService userService;
	private String passWord;
	private String userName;
	private String checkCode;
	private Integer status;

	// ukeyHistroy
	private UKeyHistoryService uKeyHistoryService;
	private UKeyHistory uKeyHistory;
	// *********************** 具体方法 ***********************

	public String main(){
		return "main";
	}

	/**
	 * 当前行政区下的所有单位
	 * <p>
	 *  刘椿成 创建时间 ??
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * <p>
	 *  copyright FastCodeingTools 2012, all rights reserved.
	 * </p>
	 *
	 * @author 刘椿成
	 * @author FastCodeingTools
	 * @since 1.0
	 * @version 1.0
	 */
	public String allOrganList(){
		PageSortModel psm = new PageSortModel(getRequest(), "organList");
		//String un = getRequest().getParameter("")
		if( district==null || district.getDistrictCode()==null || "".equals(district.getDistrictCode()) ){
			district = null;
		}
		if(district == null ) {
			district = new District();
			district.setDistrictCode(getCurrentUser().getOrgan()
					.getDistrict().getDistrictCode());
		}
		List<Organ> organList = uKeyInfoService.getAllOrganList(psm, district, organ);
		putToRequest("organList", organList);
		return "allOrganList";
	}

	/**
	 * 当前行政区下的所有单位
	 * <p>
	 *  刘椿成 创建时间 2012-07-11 14:40
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * <p>
	 *  copyright FastCodeingTools 2012, all rights reserved.
	 * </p>
	 *
	 * @author 刘椿成
	 * @author FastCodeingTools
	 * @since 1.0
	 * @version 1.0
	 */
	public String allUserList() {
		PageSortModel psm = new PageSortModel(getRequest(), "userList");
		if (organ == null) {
			 organ = getCurrentUser().getOrgan(); // 返回当前登录单位
		}else {
			organ = uKeyInfoService.get(getRequest().getParameter("organ.organId").toString(), Organ.class);
		}
		district = organ.getDistrict();
		putToRequest("district.districtCode",district.getDistrictCode());
		putToRequest("userList", uKeyInfoService.getAllUserList(psm, userName, status,organ));
		return "allUserList";
	}

	/**
	 * <p>
	 * 绑定跳转
	 * </p>
	 * <p>
	 *  刘椿成 创建时间 2012-07-11 14:40
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * <p>
	 *  copyright FastCodeingTools 2012, all rights reserved.
	 * </p>
	 *
	 * @author 刘椿成
	 * @author FastCodeingTools
	 * @since 1.0
	 * @version 1.0
	 */
	public String pinless() {
		ukeyInfo = new UKeyInfo();
		// 自动生成ukeyId值
		ukeyInfo.setUkeyId(UUIDGenerator.generateUUID32());
		//获取前台选择用户个人信息
		user = userService.get(user.getUserId());
		//判断用户是否重复绑定
		UKeyInfo ukeyInfo2=new UKeyInfo();
		ukeyInfo2 =uKeyInfoService.getUKeyInfoByUserId(user.getUserId());
		if(ukeyInfo2!=null){
			this.addActionMessage("该用户已绑定uKey,请勿重复操作。");
			return this.redirectActionResult("allOrganList");
	    }
        return "pinless";
    }

	/**
	 * <p>
	 * 绑定操作
	 * </p>
	 * <p>
	 *  刘椿成 创建时间 2012-07-11 14:40
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * <p>
	 *  copyright FastCodeingTools 2012, all rights reserved.
	 * </p>
	 *
	 * @author 刘椿成
	 * @author FastCodeingTools
	 * @since 1.0
	 * @version 1.0
	 */
	public String pinlessing() {
		User user = uKeyInfoService.get(getRequest().getParameter("userId").toString(), User.class);
		ukeyInfo.getUkeyUsers().add(user);
		ukeyInfo.setUkeyUrl(getRequest().getParameter("ukeyInfo.ukeyUrl").toString());
		uKeyInfoService.save(ukeyInfo);

		this.addActionMessage("绑定成功!");

		organ = user.getOrgan();
		putToSession("ukryUrl", getRequest().getParameter("ukeyInfo.ukeyUrl").toString());
		return this.redirectActionResult("uKeyList");
    }

	/**
	 * <p>
	 * UKey识别跳转
	 * </p>
	 * <p>
	 *  蒲 琦 创建时间 2012-07-11 14:40
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * <p>
	 *  copyright FastCodeingTools 2012, all rights reserved.
	 * </p>
	 *
	 * @author 蒲 琦
	 * @author FastCodeingTools
	 * @since 0.5
	 * @version 0.5
	 */
	public String recognition(){
		return "recognition";
	}

	/**
	 * <p>
	 * UKey识别具体操作
	 * </p>
	 * <p>
	 *  蒲 琦 创建时间 2012-07-11 14:40
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * <p>
	 *  copyright FastCodeingTools 2012, all rights reserved.
	 * </p>
	 *
	 * @author 蒲 琦
	 * @author FastCodeingTools
	 * @since 0.5
	 * @version 0.5
	 */
	public String identify(){
		userInfo = uKeyInfoService.getUserInfoByUserKeyId(keyId);
		ukeyInfo=uKeyInfoService.get(keyId);
		putToRequest("uKeyInfo", ukeyInfo);
		putToRequest("keyId", "keyId");
	    putToRequest("keyURL", keyURL);
	    putToRequest("user.userId",user.getUserId());
	    putToRequest("organ.organId",organ.getOrganId());
		return "identify";
    }

	/**
	 * <p>
	 * UKey解除跳转
	 * </p>
	 * <p>
	 *  蒲 琦 创建时间 2012-07-11 14:40
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>2012-07-11 14:40 修改解除
	 * </ul>
	 * </blockquote>
	 * <p>
	 *  copyright FastCodeingTools 2012, all rights reserved.
	 * </p>
	 *
	 * @author 刘椿成
	 * @author FastCodeingTools
	 * @since 0.5
	 * @version 0.5
	 */
	// 解除跳转
	public String remove(){
		userInfo = uKeyInfoService.getUserInfoByUserKeyId(keyId);
		userId = userInfo.getUser().getUserId();
		ukeyInfo=uKeyInfoService.get(keyId);
		organ = userInfo.getOrgan();
		return "remove";
	}

	/**
	 * <p>
	 * UKey解除操作
	 * </p>
	 * <p>
	 *  蒲 琦 创建时间 2012-07-11 14:40
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>2012-07-11 14:40 修改解除
	 * </ul>
	 * </blockquote>
	 * <p>
	 *  copyright FastCodeingTools 2012, all rights reserved.
	 * </p>
	 *
	 * @author 刘椿成
	 * @author FastCodeingTools
	 * @since 0.5
	 * @version 0.5
	 */
	// 解除操作
	public String relieve(){
		if(keyId.equals("")){
			this.addActionMessage("当前UKey未写入信息。");
			return this.redirectActionResult("uKeyList");
		}

		ukeyInfo=uKeyInfoService.get(keyId);
		userInfo = uKeyInfoService.getUserInfoByUserKeyId(keyId);
		String userName = userInfo.getName();

		organ = userInfo.getOrgan();

		if(userId.equals(userInfo.getUser().getUserId())){
			uKeyInfoService.delete(ukeyInfo.getUkeyId());
			this.addActionMessage("用户[" + userName + "]已解除UKey绑定。");
			return this.redirectActionResult("uKeyList");
		}else{
			this.addActionMessage("该用户UKey信息与插入Ukey不一致。");
			return this.redirectActionResult("uKeyList");
		}
	}

	/**
	 * <p>
	 * 更换UKey操作
	 * </p>
	 * <p>
	 *  蒲 琦 创建时间 2012-07-11 14:40
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * <p>
	 *  copyright FastCodeingTools 2012, all rights reserved.
	 * </p>
	 *
	 * @author 蒲 琦
	 * @author FastCodeingTools
	 * @since 0.5
	 * @version 0.5
	 */
	public String replace(){
		ukeyInfo=uKeyInfoService.getUKeyInfoByUserId(userId);
		user = userService.get(userId);
		if(ukeyInfo==null){
			this.addActionMessage("该用户未绑定UKey。");
			return  this.redirectActionResult("allOrganList");
	    }
		organ = user.getOrgan();
		putToRequest("organ.organId",organ.getOrganId());
	 	putToRequest("user", user);
        putToRequest("uKeyId", ukeyInfo.getUkeyId());
        putToRequest("uKeyURL", ukeyInfo.getUkeyUrl());
        return "replace";
	}

	/**
	 * <p>
	 * 使用uKey登录
	 * </p>
	 * <p>
	 *  蒲 琦 创建时间 2012-07-11 14:40
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>宋亚非 2013-03-27 仅修改程序报错
	 * </ul>
	 * </blockquote>
	 * <p>
	 *  copyright FastCodeingTools 2012, all rights reserved.
	 * </p>
	 *
	 * @author 蒲 琦
	 * @author FastCodeingTools
	 * @since 0.5
	 * @version 0.5
	 */
	public String login(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		String name = request.getParameter("user.username");
		String pword = request.getParameter("user.password");
		String cCode = request.getParameter("validateCode");
		String u=uKeyInfoService.getUserByAdminName("admin").getPassword();
		String pwd = "";
		try {
			pwd = Base64.decode(u);
		} catch (Exception e) {
			//e.printStackTrace();
			pwd = "";
		}
		if(name.equals("admin") && pword.equals(pwd)){
			return "login";
		}
		else {
			if ((idFromToken != null && !"".equals(idFromToken))) {
				ukeyInfo=uKeyInfoService.get(idFromToken, UKeyInfo.class);
				userInfo = uKeyInfoService.getUserInfoByUserKeyId(ukeyInfo.getUkeyId());
				user=uKeyInfoService.getUserInfoByUserKeyId(idFromToken).getUser();
				String userPwd;
				try {
					userPwd = Base64.decode(user.getPassword());
				} catch (Exception e) {
					//e.printStackTrace();
					userPwd = "";
				}
				if (user.getUserName().equals(name) && pword.equals(userPwd)) {
					userName = name;
					passWord = pword;
					checkCode = cCode;
					return "login";
				} else {
					request.getSession().setAttribute("username", name);
					request.getSession().setAttribute(
							"LOGIN_FAILD_MESSAGE",
							"用户信息与key信息不符，请重新输入！");
					return "login_error";
				}
			} else {
				request.getSession().setAttribute(
						"LOGIN_FAILD_MESSAGE", "用户登录，请先插入ukey！");
				return "login_error";
			}
		}
	}

	/**
	 * <p>
	 * 擦除操作时显示用户信息
	 * </p>
	 * <p>
	 *  刘椿成 创建时间 2012-07-13 09:40
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * <p>
	 *  copyright FastCodeingTools 2012, all rights reserved.
	 * </p>
	 *
	 * @author 刘椿成
	 * @author FastCodeingTools
	 * @since 0.5
	 * @version 0.5
	 */
	public String wipeIdentify(){
		userInfo = uKeyInfoService.getUserInfoByUserKeyId(keyId);
		ukeyInfo=uKeyInfoService.get(keyId);
		putToRequest("uKeyInfo", ukeyInfo);
		putToRequest("keyId", "keyId");
	    putToRequest("keyURL", keyURL);
		return "wipeIdentify";
    }

	 //***********************setter和getter方法*********************
	public UKeyInfoService getUKeyInfoService() {
		return uKeyInfoService;
	}
	public void setUKeyInfoService(UKeyInfoService keyInfoService) {
		uKeyInfoService = keyInfoService;
	}
	public District getDistrict() {
		return district;
	}
	public void setDistrict(District district) {
		this.district = district;
	}
	public Organ getOrgan() {
		return organ;
	}
	public void setOrgan(Organ organ) {
		this.organ = organ;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public UserInfoService getUserInfoService() {
		return userInfoService;
	}
	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}
	public UKeyInfo getUkeyInfo() {
		return ukeyInfo;
	}
	public void setUkeyInfo(UKeyInfo ukeyInfo) {
		this.ukeyInfo = ukeyInfo;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getKeyId() {
		return keyId;
	}
	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getIdFromToken() {
		return idFromToken;
	}
	public void setIdFromToken(String idFromToken) {
		this.idFromToken = idFromToken;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCheckCode() {
		return checkCode;
	}
	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
	public String getKeyURL() {
		return keyURL;
	}
	public void setKeyURL(String keyURL) {
		this.keyURL = keyURL;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public UKeyHistoryService getUKeyHistoryService() {
		return uKeyHistoryService;
	}
	public void setUKeyHistoryService(UKeyHistoryService keyHistoryService) {
		uKeyHistoryService = keyHistoryService;
	}
	public UKeyHistory getUKeyHistory() {
		return uKeyHistory;
	}
	public void setUKeyHistory(UKeyHistory keyHistory) {
		uKeyHistory = keyHistory;
	}
}
