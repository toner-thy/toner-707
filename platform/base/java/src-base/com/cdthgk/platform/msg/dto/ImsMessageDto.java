package com.cdthgk.platform.msg.dto;

import java.util.Date;
import java.util.List;

import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.permission.user.domain.User;

/**
 *
 * <p>
 * IMS消息封装类
 * </p>
 * <p>
 * 创建时间 2013-11-13 - 上午11:51:48
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
public class ImsMessageDto {

	//标题
	private	String title = "";
	//内容
	private	String content = "";
	//消息发送的机构organList
	private List<Organ> organList;
	//消息发送的城市districtList
	private	List<District> districtList;
	//消息发送的用户集合userList
	private	List<User> userList;
	//消息发送者的名称publisher
	private	String publisher;
	//消息发送公司名称companyname
	private	String companyname;
	//消息有效期如:2013-11-11
	private	Date msgPushTime;
	//消息发送的时间:如2013-11-11
	private	Date validity;
	//消息发送的机构LIST,消息发送的城市LIST,消息发送的用户集合 三者一般任选一个传递,若三者同事传参,将使用"消息发送的用户集合"另外两个参数内容将被忽略.
	/**
	 * @return 返回organList
	 */
	public List<Organ> getOrganList() {
		return organList;
	}
	/**
	 * @param organList 设置organList
	 */
	public void setOrganList(List<Organ> organList) {
		this.organList = organList;
	}
	/**
	 * @return 返回districtList
	 */
	public List<District> getDistrictList() {
		return districtList;
	}
	/**
	 * @param districtList 设置districtList
	 */
	public void setDistrictList(List<District> districtList) {
		this.districtList = districtList;
	}
	/**
	 * @return 返回userList
	 */
	public List<User> getUserList() {
		return userList;
	}
	/**
	 * @param userList 设置userList
	 */
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	/**
	 * @return 返回publisher
	 */
	public String getPublisher() {
		return publisher;
	}
	/**
	 * @param publisher 设置publisher
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	/**
	 * @return 返回companyname
	 */
	public String getCompanyname() {
		return companyname;
	}
	/**
	 * @param companyname 设置companyname
	 */
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	/**
	 * @return 返回msgPushTime
	 */
	public Date getMsgPushTime() {
		return msgPushTime;
	}
	/**
	 * @param msgPushTime 设置msgPushTime
	 */
	public void setMsgPushTime(Date msgPushTime) {
		this.msgPushTime = msgPushTime;
	}
	/**
	 * @return 返回validity
	 */
	public Date getValidity() {
		return validity;
	}
	/**
	 * @param validity 设置validity
	 */
	public void setValidity(Date validity) {
		this.validity = validity;
	}
	/**
	 * @return 返回title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title 设置title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return 返回content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content 设置content
	 */
	public void setContent(String content) {
		this.content = content;
	}
}