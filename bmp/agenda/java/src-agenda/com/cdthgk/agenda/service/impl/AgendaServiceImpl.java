package com.cdthgk.agenda.service.impl;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.cdthgk.agenda.service.AgendaService;
import com.cdthgk.agenda.vo.Agenda;
import com.cdthgk.platform.base.service.BaseServiceImpl;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

/**
 * <p>
 * 日程安排Service实现类
 * </p>
 * <p>
 * 创建时间 2011-3-18 - 下午04:05:46
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
@SuppressWarnings("unchecked")
public class AgendaServiceImpl extends
	BaseServiceImpl<Agenda, String> implements AgendaService  {

	/**
	 *
	 */
	public AgendaServiceImpl() {

	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List<Agenda> listForEc(PageSortModel psm, final Map<String, Object> params) {
		return (List<Agenda>) findList("FROM Agenda", params, psm);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List<Agenda> listForEc(PageSortModel psm, User user, Integer agendaCategory, Agenda agenda) {
		Map<String, Object> params = new HashMap<String, Object>();
		return (List<Agenda>) findList(createHql(params, user, agendaCategory, agenda), params, psm);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Agenda> list(User user, Integer agendaCategory, Agenda agenda) {
		Map<String, Object> params = new HashMap<String, Object>();
		return (List<Agenda>) findList(createHql(params, user, agendaCategory, agenda), params);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Agenda> list(User user, Integer agendaCategory, Date start, Date end) {
		Map<String, Object> params = new HashMap<String, Object>();
		return (List<Agenda>) findList(createHql(params, user, agendaCategory, start, end), params);
	}

	// ********************************************************************
	//	private method
	// ********************************************************************

	private String createHql(Map<String, Object> params,
			User user, Integer agendaCategory, Agenda agenda) {
		StringBuilder hql = new StringBuilder("FROM Agenda ");
		StringBuilder conditions = new StringBuilder();
		if (user != null && StringUtils.isNotEmpty(user.getUserId())) {
			if (agendaCategory != null) {
				if (agendaCategory == 1) {
					conditions.append(" AND agendaExecutor.userId = :userId");
				} else if (agendaCategory == 2) {
					conditions.append(" AND agendaAssigner.userId = :userId");
				} else {
					conditions.append(" AND")
						.append(" ( agendaExecutor.userId = :userId")
						.append(" OR agendaAssigner.userId = :userId )");
				}
				params.put("userId", user.getUserId());
			}
		}
		if (agenda != null) {
			if (StringUtils.isNotEmpty(agenda.getAgendaTitle())) {
				conditions.append(" AND agendaTitle like :agendaTitle");
				params.put("agendaTitle", "%" + agenda.getAgendaTitle() + "%");
			}
			String startTime = "startTime";
			String endTime = "endTime";

			if (agenda.getStartTime() != null && agenda.getEndTime() != null) {

				// 日历查询
				// 查询条件开始时间和结束时间都有
				conditions.append(" AND ( startTime <= :endTime AND endTime >= :startTime )");
				params.put(startTime, agenda.getStartTime());
				//有结束时间时对结束时间进行限定（XX日23时59分59秒）
				long x = 24*60*60*1000-1;

				params.put(endTime, new Date(agenda.getEndTime().getTime()+x));
			} else if (agenda.getStartTime() != null) {
				// 查询条件只有开始时间，表示日程时间段内有这个时间点的就行
				conditions.append(" AND startTime <= :startTime AND endTime >= :startTime");
				params.put(startTime, agenda.getStartTime());
			} else if (agenda.getEndTime() != null) {
				// 查询条件只有结束时间，表示日程时间段内有这个时间点的就行
				conditions.append(" AND startTime <= :endTime AND endTime >= :endTime");
				//有结束时间时对结束时间进行限定（XX日23时59分59秒）
				long x = 24*60*60*1000-1;

				params.put(endTime, new Date(agenda.getEndTime().getTime()+x));
			}

		}
		if (conditions.length() > 0) {
			hql.append("WHERE 1=1 ").append(conditions.toString());
		}
		return hql.toString();
	}


	private String createHql(Map<String, Object> params,
			User user, Integer agendaCategory, Date start, Date end) {
		StringBuilder hql = new StringBuilder("FROM Agenda ");
		StringBuilder conditions = new StringBuilder();
		if (user != null && StringUtils.isNotEmpty(user.getUserId())) {
			if (agendaCategory != null) {
				if (agendaCategory == 1) {
					conditions.append(" AND agendaExecutor.userId = :userId");
				} else if (agendaCategory == 2) {
					conditions.append(" AND agendaAssigner.userId = :userId");
				} else {
					conditions.append(" AND")
						.append(" ( agendaExecutor.userId = :userId")
						.append(" OR agendaAssigner.userId = :userId )");
				}
				params.put("userId", user.getUserId());
			}
		}
		String startTime = "startTime";
		String endTime = "endTime";

		if (start != null && end != null) {
			// 今日日程，表示开始时间是今天安排的。
			conditions.append(" AND")
				.append(" ( startTime between :start AND :end )");
//				.append(" ( startTime <= :endTime  )");
			params.put("start", start);
			params.put("end", end);
		}
		if (conditions.length() > 0) {
			hql.append("WHERE 1=1 ").append(conditions.toString());
		}
		return hql.toString();
	}
}
