package com.cdthgk.agenda.vo;

import java.util.Date;

import com.cdthgk.platform.permission.user.domain.User;

/**
 * <p>
 * 日程
 * </p>
 * <p>
 * 创建时间 2011-3-18 - 下午03:55:57
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
public class Agenda implements java.io.Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 5885259406211064440L;

	// Fields

	private String agendaId;

	private String agendaTitle;

	private String agendaContent;

	private Date startTime;

	private Date endTime;

	private User agendaAssigner;

	private User agendaExecutor;

	/**
	 * @return 返回agendaId
	 */
	public String getAgendaId() {
		return agendaId;
	}

	/**
	 * @param agendaId 设置agendaId
	 */
	public void setAgendaId(String agendaId) {
		this.agendaId = agendaId;
	}

	/**
	 * @return 返回agendaTitle
	 */
	public String getAgendaTitle() {
		return agendaTitle;
	}

	/**
	 * @param agendaTitle 设置agendaTitle
	 */
	public void setAgendaTitle(String agendaTitle) {
		this.agendaTitle = agendaTitle;
	}

	/**
	 * @return 返回agendaContent
	 */
	public String getAgendaContent() {
		return agendaContent;
	}

	/**
	 * @param agendaContent 设置agendaContent
	 */
	public void setAgendaContent(String agendaContent) {
		this.agendaContent = agendaContent;
	}

	/**
	 * @return 返回startTime
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime 设置startTime
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return 返回endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime 设置endTime
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return 返回agendaAssigner
	 */
	public User getAgendaAssigner() {
		return agendaAssigner;
	}

	/**
	 * @param agendaAssigner 设置agendaAssigner
	 */
	public void setAgendaAssigner(User agendaAssigner) {
		this.agendaAssigner = agendaAssigner;
	}

	/**
	 * @return 返回agendaExecutor
	 */
	public User getAgendaExecutor() {
		return agendaExecutor;
	}

	/**
	 * @param agendaExecutor 设置agendaExecutor
	 */
	public void setAgendaExecutor(User agendaExecutor) {
		this.agendaExecutor = agendaExecutor;
	}

	private User createPerson;

	private Date createTime;

	private User modifyPerson;

	private Date modifyTime;

	// Constructors

	/** default constructor */
	public Agenda() {
	}

	/**
	 * @param agendaId 主键唯一值
	 */
	public Agenda(String agendaId) {
		this.agendaId = agendaId;
	}

	// Property accessors

	/**
	 * @return 返回createPerson
	 */
	public User getCreatePerson() {
		return createPerson;
	}

	/**
	 * @param createPerson
	 *            设置createPerson
	 */
	public void setCreatePerson(User createPerson) {
		this.createPerson = createPerson;
	}

	/**
	 * @return 返回createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            设置createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return 返回modifyPerson
	 */
	public User getModifyPerson() {
		return modifyPerson;
	}

	/**
	 * @param modifyPerson
	 *            设置modifyPerson
	 */
	public void setModifyPerson(User modifyPerson) {
		this.modifyPerson = modifyPerson;
	}

	/**
	 * @return 返回modifyTime
	 */
	public Date getModifyTime() {
		return modifyTime;
	}

	/**
	 * @param modifyTime
	 *            设置modifyTime
	 */
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
}