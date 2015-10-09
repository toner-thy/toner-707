
package com.cdthgk.bmp.secrecynet.secrecyAdvice.vo;

import java.util.Date;

import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.permission.user.domain.User;
import com.thgk.platform.core.BaseDomain;

/**
 * <p>
 * 类的说明放这里
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 *
 * @author 钟冀
 */
public class SecrecyAdvice extends BaseDomain{

	/**
	 *
	 */
	private static final long serialVersionUID = 7629074982703497181L;


	private String id;
	private Integer year;
	private Organ organ;
	private User reportUser;
	private Date reportTime;
	private String question;
	private String advise;
	private Organ createOrgan;
	private Department createDepartment;
	/**
	 * 返回id
	 * @return id
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置id
	 * @param id id
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 返回organ
	 * @return organ
	 */
	public Organ getOrgan() {
		return organ;
	}
	/**
	 * 设置organ
	 * @param organ organ
	 */
	public void setOrgan(Organ organ) {
		this.organ = organ;
	}
	/**
	 * 返回reportUser
	 * @return reportUser
	 */
	public User getReportUser() {
		return reportUser;
	}
	/**
	 * 设置reportUser
	 * @param reportUser reportUser
	 */
	public void setReportUser(User reportUser) {
		this.reportUser = reportUser;
	}
	/**
	 * 返回reportTime
	 * @return reportTime
	 */
	public Date getReportTime() {
		return reportTime;
	}
	/**
	 * 设置reportTime
	 * @param reportTime reportTime
	 */
	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}
	/**
	 * 返回question
	 * @return question
	 */
	public String getQuestion() {
		return question;
	}
	/**
	 * 设置question
	 * @param question question
	 */
	public void setQuestion(String question) {
		this.question = question;
	}
	/**
	 * 返回advise
	 * @return advise
	 */
	public String getAdvise() {
		return advise;
	}
	/**
	 * 设置advise
	 * @param advise advise
	 */
	public void setAdvise(String advise) {
		this.advise = advise;
	}
	/**
	 * 返回createOrgan
	 * @return createOrgan
	 */
	public Organ getCreateOrgan() {
		return createOrgan;
	}
	/**
	 * 设置createOrgan
	 * @param createOrgan createOrgan
	 */
	public void setCreateOrgan(Organ createOrgan) {
		this.createOrgan = createOrgan;
	}
	/**
	 * 返回createDepartment
	 * @return createDepartment
	 */
	public Department getCreateDepartment() {
		return createDepartment;
	}
	/**
	 * 设置createDepartment
	 * @param createDepartment createDepartment
	 */
	public void setCreateDepartment(Department createDepartment) {
		this.createDepartment = createDepartment;
	}
	/**
	 * 返回year
	 * @return year
	 */
	public Integer getYear() {
		return year;
	}
	/**
	 * 设置year
	 * @param year year
	 */
	public void setYear(Integer year) {
		this.year = year;
	}
}
