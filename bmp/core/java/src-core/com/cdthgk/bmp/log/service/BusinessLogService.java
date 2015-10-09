package com.cdthgk.bmp.log.service;

import java.util.List;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.bmp.log.vo.BusinessLog;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

/**
 *
 * <p>
 * 自动生成类
 * </p>
 * <p>
 * 创建时间 2014-7-15 - 下午1:57:22
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
public interface BusinessLogService extends BmpServiceTemplate<BusinessLog, String>{

	/**
	 *
	 * <p>
	 * 保存业务模块【新增操作】日志
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2014-8-13 - 下午1:07:42
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param currentUser 当前登陆用户
	 * @param log	日志主记录
	 * @param object 业务对象
	 */
	void saveAddBusinessLogByModule(User currentUser, BusinessLog log, Object object);

	/**
	 *
	 * <p>
	 * 保存业务模块【编辑操作】日志
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2014-8-13 - 下午1:08:39
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param currentUser 当前登陆用户
	 * @param log	日志主记录
	 * @param objLater	业务对象（编辑后：即页面表单传回来需要编辑的对象）
	 * @param objBefore	业务对象（编辑前：即数据库值对象）
	 */
	void saveEditBusinessLogByModule(User currentUser, BusinessLog log, Object objLater, Object objBefore);

	/**
	 *
	 * <p>
	 * 保存业务模块【删除操作】日志
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2014-8-13 - 下午1:08:39
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param currentUser 当前登陆用户
	 * @param log	日志主记录
	 * @param object 业务对象
	 */
	void saveDelBusinessLogByModule(User currentUser, BusinessLog log, Object object);

	/**
	 * <p>
	 *  根据单位分页获取操作日志
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2014-8-13 - 下午2:02:21
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param psm
	 * @param businessLog
	 * @param User
	 * @param startTime
	 * @param endTime
	 * @param userName
	 * @return
	 */
	List<BusinessLog> getPageList(PageSortModel<BusinessLog> psm, BusinessLog businessLog, User currentUser,
			String startTime, String endTime, String userName);
}
