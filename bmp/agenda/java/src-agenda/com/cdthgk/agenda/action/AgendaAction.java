package com.cdthgk.agenda.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.cdthgk.agenda.service.AgendaService;
import com.cdthgk.agenda.vo.Agenda;
import com.cdthgk.bmp.log.context.BusinessLogContext;
import com.cdthgk.bmp.log.vo.BusinessLog;
import com.cdthgk.common.bean.BeanUtils;
import com.cdthgk.common.bean.rule.CopyRuleEnum;
import com.cdthgk.common.lang.DateUtils;

import ec.common.PageSortModel;

/**
 * <p>
 * 日程安排Action
 * </p>
 * <p>
 * 创建时间 2011-3-21 - 上午10:20:12
 * </p>
 * <blockquote> <h4>历史修改记录</h4>
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
public class AgendaAction extends com.cdthgk.platform.base.action.BaseAction {

	// Fields

	private static final long serialVersionUID = 5772380390010375421L;
	private Boolean needReload = false;

	// methods

	/**
	 * 取得ECLIST 显示列表
	 *
	 * @return result
	 */
	@SuppressWarnings("rawtypes")
	public String list() {
		String listId = "agendaList";
		PageSortModel psm = new PageSortModel(getRequest(), listId);
		putToRequest(listId, agendaService.listForEc(psm,
				getCurrentUser(), getAgendaCategory(), agenda));
		return SUCCESS;
	}

	public String indexView() {
		Calendar start = Calendar.getInstance();
		start.set(Calendar.HOUR, -12);
		start.set(Calendar.MINUTE, 0);
		start.set(Calendar.SECOND, 0);
		Date todayStart = start.getTime();
		Calendar end = Calendar.getInstance();
		end.set(Calendar.HOUR, 11);
		end.set(Calendar.MINUTE, 59);
		end.set(Calendar.SECOND, 59);
		Date todayEnd = end.getTime();
		putToRequest("agendaList", agendaService.list(
				getCurrentUser(), 1, todayStart, todayEnd));
		return SUCCESS;
	}

	/**
	 * 跳转到增加页面（我的事务）
	 *
	 * @return result
	 */
	public String add() {
		return SUCCESS;
	}

	/**
	 * 跳转到增加页面（他人事务）
	 *
	 * @return result
	 */
	public String addOther() {
		putToRequest("other", true);
		return SUCCESS;
	}

	/**
	 * 保存一条数据
	 *
	 * @return result
	 */
	public String adding() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String startTime = getRequest().getParameter("agenda.startTime");
		String endTime = getRequest().getParameter("agenda.endTime");
		try {
			agenda.setStartTime(sdf.parse(startTime));
			agenda.setEndTime(sdf.parse(endTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		agenda.setCreatePerson(getCurrentUser());
		agenda.setCreateTime(new Date());
		agenda.setAgendaAssigner(getCurrentUser());
		// 没有指派执行人，表示自己执行
		if (agenda.getAgendaExecutor() == null) {
			agenda.setAgendaExecutor(getCurrentUser());
		}
		agendaService.save(agenda);
		needReload = true;
		addActionMessage("添加日程安排成功。");

		BusinessLog log = new BusinessLog();
		log.setBusinessName("日程管理");
		log.setPrimaryKey(agenda.getAgendaId());
		BusinessLogContext.getInstance().getBusinessLogService().saveAddBusinessLogByModule(getCurrentUser(), log, agenda);
		return SUCCESS;
	}

	/**
	 * 跳转到编辑页面
	 *
	 * @return result
	 */
	public String edit() {
		agenda = agendaService.get(getId());
		return SUCCESS;
	}

	/**
	 * 更新一条记录
	 *
	 * @return result
	 */
	public String editing() {
		Agenda oldAgenda = agendaService.get(agenda.getAgendaId());
		Agenda beforeAgenda = new Agenda();
		BeanUtils.copyProperties(beforeAgenda, oldAgenda, CopyRuleEnum.ignoreCaseNull);


		oldAgenda.setAgendaContent(agenda.getAgendaContent());
		oldAgenda.setAgendaTitle(agenda.getAgendaTitle());

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String startTime = getRequest().getParameter("agenda.startTime");
		String endTime = getRequest().getParameter("agenda.endTime");
		try {
			oldAgenda.setStartTime(sdf.parse(startTime));
			oldAgenda.setEndTime(sdf.parse(endTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		oldAgenda.setModifyPerson(getCurrentUser());
		oldAgenda.setModifyTime(new Date());
		agendaService.update(oldAgenda);
		needReload = true;
		addActionMessage("修改日程安排成功。");

		BusinessLog log = new BusinessLog();
		log.setBusinessName("日程管理");
		log.setPrimaryKey(agenda.getAgendaId());
		BusinessLogContext.getInstance().getBusinessLogService().saveEditBusinessLogByModule(getCurrentUser(), log, oldAgenda, beforeAgenda);
		return SUCCESS;
	}

	/**
	 * 删除方法
	 *
	 * @return result
	 */
	public String delete() {
		agendaService.deleteBatchIdList(getIds());
		addActionMessage("删除日程安排成功。");

		for (String id : getIds()) {
			BusinessLog log = new BusinessLog();
			log.setBusinessName("日程管理");
			log.setPrimaryKey(id);
			BusinessLogContext.getInstance().getBusinessLogService().saveDelBusinessLogByModule(getCurrentUser(), log, new Agenda());
		}
		return SUCCESS;
	}

	/**
	 * 详细方法
	 *
	 * @return result
	 */
	public String detail() {
		agenda = agendaService.get(getId());
		return SUCCESS;
	}

	/**
	 * 日历显示
	 *
	 * @return result
	 */
	public String calendar() {
		if (agenda == null) {
			agenda = new Agenda();
		}
		int y = year != null ? year : DateUtils.getCurrentYear();
		int m = month != null ? month : DateUtils.getCurrentMonth();
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, y);
		c.set(Calendar.MONTH, m - 1);
		c.set(Calendar.DATE, 1);
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		if (agenda.getStartTime() == null) {
			agenda.setStartTime(c.getTime());
		}
		final int lastDay = DateUtils.getMaxDayOfMonth(y, m);
		final int lastHour = 23;
		final int lastMinute = 59;
		final int lastSecond = lastMinute;
		c = Calendar.getInstance();
		c.set(Calendar.YEAR, y);
		c.set(Calendar.MONTH, m - 1);
		c.set(Calendar.DATE, lastDay);
		c.set(Calendar.HOUR_OF_DAY, lastHour);
		c.set(Calendar.MINUTE, lastMinute);
		c.set(Calendar.SECOND, lastSecond);
		if (agenda.getEndTime() == null) {
			agenda.setEndTime(c.getTime());
		}
		List<Agenda> agendaList = agendaService.list(
				getCurrentUser(), 1, agenda);
		putToRequest("agendaList", agendaList);

		return SUCCESS;
	}

	/**
	 *
	 * 跳转到邮件系统收件箱
	 */
	public String redictMail() {
		return SUCCESS;
	}

	// ********************************************************************
	// property
	// ********************************************************************

	private AgendaService agendaService;

	private Agenda agenda;

	private Integer agendaCategory;

	private Integer year;

	private Integer month;

	/**
	 * @return 返回year
	 */
	public Integer getYear() {
		return year;
	}

	/**
	 * @param year
	 *            设置year
	 */
	public void setYear(Integer year) {
		this.year = year;
	}

	/**
	 * @return 返回month
	 */
	public Integer getMonth() {
		return month;
	}

	/**
	 * @param month
	 *            设置month
	 */
	public void setMonth(Integer month) {
		this.month = month;
	}

	/**
	 * @return 返回agendaCategory
	 */
	public Integer getAgendaCategory() {
		if (agendaCategory == null) {
			agendaCategory = 0;
		}
		return agendaCategory;
	}

	/**
	 * @param agendaCategory
	 *            设置agendaCategory
	 */
	public void setAgendaCategory(Integer agendaCategory) {
		this.agendaCategory = agendaCategory;
	}

	/**
	 * @return 返回agenda
	 */
	public Agenda getAgenda() {
		return agenda;
	}

	/**
	 * @param agenda
	 *            设置agenda
	 */
	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	/**
	 * @param agendaService
	 *            设置agendaService
	 */
	public void setAgendaService(AgendaService agendaService) {
		this.agendaService = agendaService;
	}

	/**
	 * @return the needReload
	 */
	public Boolean getNeedReload() {
		return needReload;
	}

	/**
	 * @param needReload the needReload to set
	 */
	public void setNeedReload(Boolean needReload) {
		this.needReload = needReload;
	}
}
