package com.cdthgk.agenda.service;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.cdthgk.agenda.vo.Agenda;
import com.cdthgk.platform.base.service.BaseServiceTemplate;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

/**
 * <p>
 * 日程安排Service
 * </p>
 * <p>
 * 创建时间 2011-3-18 - 下午04:00:41
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
 * @author 钟冀
 * @author cdpws r&d
 * @since 1.0
 * @version 1.0
 */
public interface AgendaService extends
BaseServiceTemplate<Agenda, String>{

	/**
	 * <p>
	 * 获取指定用户的日程列表
	 * </p>
	 * <p>
	 * 钟冀 创建时间 2011-3-21 - 下午02:08:53
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param psm EC分页模型
	 * @param user 用户
	 * @param agendaCategory 类型（自己的，指派的）
	 * @param agenda 查询参数
	 * @return 指定用户的日程列表
	 */
	@SuppressWarnings("rawtypes")
	List<Agenda> listForEc(PageSortModel psm, User user, Integer agendaCategory, Agenda agenda);

	/**
	 * <p>
	 * 获取指定用户的日程列表
	 * </p>
	 * <p>
	 * 钟冀 创建时间 2011-3-21 - 下午02:08:53
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param user 用户
	 * @param agendaCategory 类型（自己的，指派的）
	 * @param agenda 查询参数
	 * @return 指定用户的日程列表
	 */
	List<Agenda> list(User user, Integer agendaCategory, Agenda agenda);
	/**
	 * <p>
	 * 查询指定时间段内的日程，开始时间或者结束时间在其区间的就算
	 * </p>
	 * @param user
	 * @param agendaCategory
	 * @param start 开始时间
	 * @param end 结束时间
	 * @return
	 */
	List<Agenda> list(User user, Integer agendaCategory, Date start, Date end);
	/**
	 * <p>
	 * 取得EClist 将HQL封装
	 * </p>
	 * <p>
	 * 钟冀 创建时间 2011-3-18 - 下午04:00:59
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param psm 分页模型
	 * @param params 参数
	 * @return 列表
	 */
	@SuppressWarnings("rawtypes")
	List<Agenda> listForEc(PageSortModel psm, final Map<String, Object> params);
}
