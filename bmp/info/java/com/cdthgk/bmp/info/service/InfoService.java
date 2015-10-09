package com.cdthgk.bmp.info.service;

import java.util.List;

import com.cdthgk.bmp.info.vo.Info;
import com.cdthgk.platform.base.service.GenericBasicService;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

/**
 *
 * <p>
 * 类的说明放这里
 * </p>
 * <p>
 * 创建时间 2015-1-7 - 上午10:01:55
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
public interface InfoService extends GenericBasicService<Info, String>{

	/**
	 *
	 * <p>
	 * 通过用户，查询本单位信息上报记录
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2015-1-19 - 下午3:22:52
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param psm
	 * @param info
	 * @param user
	 * @return
	 */
	List<Info> getInfoList(PageSortModel<Info> psm, Info info, User user);
	/**
	 * 获取需要审核的列表数据
	 * @param psm
	 * @param info
	 * @param user
	 * @return
	 */
	List<Info> getInfoAuditList(PageSortModel<Info> psm, Info info, User user);
}
