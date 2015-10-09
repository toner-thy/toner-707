
package com.cdthgk.bmp.secrecyStatistics.dto;

/**
 * <p>
 * 保密工作概况DTO
 * </p>
 * <p>
 * 创建时间 2013-1-13 - 下午2:52:58
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
public class SecrecyStatisticsDto {

	/**
	 * 单位ID
	 */
	private String organId;
	/**
	 * 单位名称
	 */
	private String organName;
	/**
	 * 保密工作机构录入
	 */
	private Integer numGroupEntering;
	/**
	 * 保密工作机构上报
	 */
	private Integer numGroupReprot;
	/**
	 * 保密工作机构成员
	 */
	private Integer numGroupMember;

	/**
	 * 保密工作机构保密办成员
	 */
	private Integer numSecrecyWorkOrganMember;
	/**
	 * 要害部门数据录入
	 */
	private Integer numKeysectionEntering;
	/**
	 * 要害部门数据上报
	 */
	private Integer numKeysectionReport;
	/**
	 * 要害部位录入
	 */
	private Integer numKeyPartEntering;
	/**
	 * 要害部位上报
	 */
	private Integer numKeyPartReport;
	/**
	 * 机关涉密人员录入
	 */
	private Integer numSecrecyPersonEntering;
	/**
	 * 机关涉密人员上报
	 */
	private Integer numSecrecyPersonReport;

	/**
	 * @return the organId
	 */
	public String getOrganId() {
		return organId;
	}
	/**
	 * @param organId the organId to set
	 */
	public void setOrganId(String organId) {
		this.organId = organId;
	}
	/**
	 * @return the organName
	 */
	public String getOrganName() {
		return organName;
	}
	/**
	 * @param organName the organName to set
	 */
	public void setOrganName(String organName) {
		this.organName = organName;
	}
	/**
	 * @return the numGroupEntering
	 */
	public Integer getNumGroupEntering() {
		return numGroupEntering;
	}
	/**
	 * @param numGroupEntering the numGroupEntering to set
	 */
	public void setNumGroupEntering(Integer numGroupEntering) {
		this.numGroupEntering = numGroupEntering;
	}
	/**
	 * @return the numGroupReprot
	 */
	public Integer getNumGroupReprot() {
		return numGroupReprot;
	}
	/**
	 * @param numGroupReprot the numGroupReprot to set
	 */
	public void setNumGroupReprot(Integer numGroupReprot) {
		this.numGroupReprot = numGroupReprot;
	}

	/**
	 * @return the numKeysectionEntering
	 */
	public Integer getNumKeysectionEntering() {
		return numKeysectionEntering;
	}
	/**
	 * @param numKeysectionEntering the numKeysectionEntering to set
	 */
	public void setNumKeysectionEntering(Integer numKeysectionEntering) {
		this.numKeysectionEntering = numKeysectionEntering;
	}
	/**
	 * @return the numKeysectionReport
	 */
	public Integer getNumKeysectionReport() {
		return numKeysectionReport;
	}
	/**
	 * @param numKeysectionReport the numKeysectionReport to set
	 */
	public void setNumKeysectionReport(Integer numKeysectionReport) {
		this.numKeysectionReport = numKeysectionReport;
	}
	/**
	 * @return the numKeyPartEntering
	 */
	public Integer getNumKeyPartEntering() {
		return numKeyPartEntering;
	}
	/**
	 * @param numKeyPartEntering the numKeyPartEntering to set
	 */
	public void setNumKeyPartEntering(Integer numKeyPartEntering) {
		this.numKeyPartEntering = numKeyPartEntering;
	}
	/**
	 * @return the numKeyPartReport
	 */
	public Integer getNumKeyPartReport() {
		return numKeyPartReport;
	}
	/**
	 * @param numKeyPartReport the numKeyPartReport to set
	 */
	public void setNumKeyPartReport(Integer numKeyPartReport) {
		this.numKeyPartReport = numKeyPartReport;
	}
	/**
	 * @return the numSecrecyPersonEntering
	 */
	public Integer getNumSecrecyPersonEntering() {
		return numSecrecyPersonEntering;
	}
	/**
	 * @param numSecrecyPersonEntering the numSecrecyPersonEntering to set
	 */
	public void setNumSecrecyPersonEntering(Integer numSecrecyPersonEntering) {
		this.numSecrecyPersonEntering = numSecrecyPersonEntering;
	}
	/**
	 * @return the numSecrecyPersonReport
	 */
	public Integer getNumSecrecyPersonReport() {
		return numSecrecyPersonReport;
	}
	/**
	 * @param numSecrecyPersonReport the numSecrecyPersonReport to set
	 */
	public void setNumSecrecyPersonReport(Integer numSecrecyPersonReport) {
		this.numSecrecyPersonReport = numSecrecyPersonReport;
	}
	/**
	 * @return the numGroupMember
	 */
	public Integer getNumGroupMember() {
		return numGroupMember;
	}
	/**
	 * @param numGroupMember the numGroupMember to set
	 */
	public void setNumGroupMember(Integer numGroupMember) {
		this.numGroupMember = numGroupMember;
	}
	/**
	 * @return the numSecrecyWorkOrganMember
	 */
	public Integer getNumSecrecyWorkOrganMember() {
		return numSecrecyWorkOrganMember;
	}
	/**
	 * @param numSecrecyWorkOrganMember the numSecrecyWorkOrganMember to set
	 */
	public void setNumSecrecyWorkOrganMember(Integer numSecrecyWorkOrganMember) {
		this.numSecrecyWorkOrganMember = numSecrecyWorkOrganMember;
	}
}
