package com.cdthgk.bmp.info.vo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;
import com.thgk.platform.core.BaseDomain;

public class Info extends BaseDomain implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String infoId;
	//上报类型
	private InfoType infoType;
	private String title;
	private String source;
	private Integer	status;
	private Date infoTime;
	private Date reportTime;
	//审签领导
	private UserInfo leader;
	private String content;
	private String reportPhone;
	//报送人单位
	private Organ reportOrgan;
	private Integer	reportType;
	//上报单位
	private Set<InfoWarn> infoWarnSet = new HashSet<InfoWarn>();

	/**
	 * @return 返回infoId
	 */
	public String getInfoId() {
		return infoId;
	}
	/**
	 * @param infoId 设置infoId
	 */
	public void setInfoId(String infoId) {
		this.infoId = infoId;
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
	 * @return 返回source
	 */
	public String getSource() {
		return source;
	}
	/**
	 * @param source 设置source
	 */
	public void setSource(String source) {
		this.source = source;
	}
	/**
	 * @return 返回status
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * @param status 设置status
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * @return 返回infoTime
	 */
	public Date getInfoTime() {
		return infoTime;
	}
	/**
	 * @param infoTime 设置infoTime
	 */
	public void setInfoTime(Date infoTime) {
		this.infoTime = infoTime;
	}
	/**
	 * @return 返回leader
	 */
	public UserInfo getLeader() {
		return leader;
	}
	/**
	 * @param leader 设置leader
	 */
	public void setLeader(UserInfo leader) {
		this.leader = leader;
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
	/**
	 * @return 返回reportPhone
	 */
	public String getReportPhone() {
		return reportPhone;
	}
	/**
	 * @param reportPhone 设置reportPhone
	 */
	public void setReportPhone(String reportPhone) {
		this.reportPhone = reportPhone;
	}
	/**
	 * @return 返回reportOrgan
	 */
	public Organ getReportOrgan() {
		return reportOrgan;
	}
	/**
	 * @param reportOrgan 设置reportOrgan
	 */
	public void setReportOrgan(Organ reportOrgan) {
		this.reportOrgan = reportOrgan;
	}
	/**
	 * @return 返回reportType
	 */
	public Integer getReportType() {
		return reportType;
	}
	/**
	 * @param reportType 设置reportType
	 */
	public void setReportType(Integer reportType) {
		this.reportType = reportType;
	}
	/**
	 * @return 返回infoType
	 */
	public InfoType getInfoType() {
		return infoType;
	}
	/**
	 * @param infoType 设置infoType
	 */
	public void setInfoType(InfoType infoType) {
		this.infoType = infoType;
	}
	/**
	 * @return 返回reportTime
	 */
	public Date getReportTime() {
		return reportTime;
	}
	/**
	 * @param reportTime 设置reportTime
	 */
	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}
	/**
	 * @return 返回infoWarnSet
	 */
	public Set<InfoWarn> getInfoWarnSet() {
		return infoWarnSet;
	}
	/**
	 * @param infoWarnSet 设置infoWarnSet
	 */
	public void setInfoWarnSet(Set<InfoWarn> infoWarnSet) {
		this.infoWarnSet = infoWarnSet;
	}
}