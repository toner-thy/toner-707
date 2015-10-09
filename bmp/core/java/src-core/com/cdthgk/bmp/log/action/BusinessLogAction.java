package com.cdthgk.bmp.log.action;

import java.util.List;

import com.cdthgk.bmp.log.service.BusinessLogService;
import com.cdthgk.bmp.log.vo.BusinessLog;
import com.cdthgk.platform.base.action.PlatformAction;

import ec.common.PageSortModel;

/**
 *
 * <p>
 * 类的说明放这里
 * </p>
 * <p>
 * 创建时间 2014-8-13 - 下午4:57:59
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
public class BusinessLogAction extends PlatformAction{

	private static final long serialVersionUID = 2550289289406134302L;
	private BusinessLogService businessLogService;
	private BusinessLog businessLog;
	private List<BusinessLog> businessLogList;
	private String startTime;
	private String endTime;
	private String userName;


	/** 列表页 */
	public String list() {
		PageSortModel<BusinessLog> psm = new PageSortModel<BusinessLog>(getRequest(), "businessLogList");
		businessLogList = businessLogService.getPageList(psm
				, businessLog, getCurrentUser(), startTime, endTime, userName);
		putToRequest("businessLogList", businessLogList);
		return SUCCESS;
	}


	public String districtList(){
		PageSortModel<BusinessLog> psm = new PageSortModel<BusinessLog>(getRequest(), "businessLogList");
		businessLogList = businessLogService.getPageList(psm
				, businessLog, getCurrentUser(), startTime, endTime, userName);
		putToRequest("businessLogList", businessLogList);
		return SUCCESS;
	}


	/** 删除 */
	public String delete() {
		String deleteIds = getRequest().getParameter("deleteIds");
		businessLogService.deleteBatchIds(deleteIds);
		addActionMessage(getMessageConstant().getDeleteSuccess());
		return SUCCESS;
	}

	public String detail(){
		businessLog = businessLogService.get(businessLog.getLogId());
		return SUCCESS;
	}

	/**
	 * @return 返回businessLog
	 */
	public BusinessLog getBusinessLog() {
		return businessLog;
	}


	/**
	 * @param businessLog 设置businessLog
	 */
	public void setBusinessLog(BusinessLog businessLog) {
		this.businessLog = businessLog;
	}


	/**
	 * @param businessLogService 设置businessLogService
	 */
	public void setBusinessLogService(BusinessLogService businessLogService) {
		this.businessLogService = businessLogService;
	}


	/**
	 * @return 返回businessLogList
	 */
	public List<BusinessLog> getBusinessLogList() {
		return businessLogList;
	}


	/**
	 * @param businessLogList 设置businessLogList
	 */
	public void setBusinessLogList(List<BusinessLog> businessLogList) {
		this.businessLogList = businessLogList;
	}


	/**
	 * @return 返回startTime
	 */
	public String getStartTime() {
		return startTime;
	}


	/**
	 * @param startTime 设置startTime
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}


	/**
	 * @return 返回endTime
	 */
	public String getEndTime() {
		return endTime;
	}


	/**
	 * @param endTime 设置endTime
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}


	/**
	 * @return 返回userName
	 */
	public String getUserName() {
		return userName;
	}


	/**
	 * @param userName 设置userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
