package com.cdthgk.bmp.info.vo;

import java.util.Date;

import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.permission.user.domain.User;

public class InfoLog implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String infoLogId;
	private Info info;
	private Date operateTime;
	private Organ operateOrgan;
	private User operateUser;
	private Integer	operateStatus;
	private String operateDes;
	/**
	 * @return 返回infoLogId
	 */
	public String getInfoLogId() {
		return infoLogId;
	}
	/**
	 * @param infoLogId 设置infoLogId
	 */
	public void setInfoLogId(String infoLogId) {
		this.infoLogId = infoLogId;
	}
	/**
	 * @return 返回operateTime
	 */
	public Date getOperateTime() {
		return operateTime;
	}
	/**
	 * @param operateTime 设置operateTime
	 */
	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}
	/**
	 * @return 返回operateOrgan
	 */
	public Organ getOperateOrgan() {
		return operateOrgan;
	}
	/**
	 * @param operateOrgan 设置operateOrgan
	 */
	public void setOperateOrgan(Organ operateOrgan) {
		this.operateOrgan = operateOrgan;
	}
	/**
	 * @return 返回operateUser
	 */
	public User getOperateUser() {
		return operateUser;
	}
	/**
	 * @param operateUser 设置operateUser
	 */
	public void setOperateUser(User operateUser) {
		this.operateUser = operateUser;
	}
	/**
	 * @return 返回operateStatus
	 */
	public Integer getOperateStatus() {
		return operateStatus;
	}
	/**
	 * @param operateStatus 设置operateStatus
	 */
	public void setOperateStatus(Integer operateStatus) {
		this.operateStatus = operateStatus;
	}
	/**
	 * @return 返回info
	 */
	public Info getInfo() {
		return info;
	}
	/**
	 * @param info 设置info
	 */
	public void setInfo(Info info) {
		this.info = info;
	}
	/**
	 * @return the operateDes
	 */
	public String getOperateDes() {
		return operateDes;
	}
	/**
	 * @param operateDes the operateDes to set
	 */
	public void setOperateDes(String operateDes) {
		this.operateDes = operateDes;
	}

}