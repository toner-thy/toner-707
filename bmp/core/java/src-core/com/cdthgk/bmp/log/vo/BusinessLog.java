package com.cdthgk.bmp.log.vo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.permission.user.domain.User;


public class BusinessLog implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;

	/**
	 * 新增操作
	 */
	public final static Integer OPERATE_TPYE_ADD = 1;
	/**
	 * 编辑操作
	 */
	public final static Integer OPERATE_TPYE_EDIT = 2;
	/**
	 * 删除操作
	 */
	public final static Integer OPERATE_TPYE_DELETE = 3;

	private String logId;
	private String tableName;
	private String businessName;
	private String primaryKey;
	private Integer operateType;
	private User operateUser;
	private Organ operateOrgan;
	private Date operateTime;
	private Set<BusinessLogContent> logContentSet = new HashSet<BusinessLogContent>();
	/**
	 * @return 返回logId
	 */
	public String getLogId() {
		return logId;
	}
	/**
	 * @param logId 设置logId
	 */
	public void setLogId(String logId) {
		this.logId = logId;
	}
	/**
	 * @return 返回tableName
	 */
	public String getTableName() {
		return tableName;
	}
	/**
	 * @param tableName 设置tableName
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	/**
	 * @return 返回businessName
	 */
	public String getBusinessName() {
		return businessName;
	}
	/**
	 * @param businessName 设置businessName
	 */
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	/**
	 * @return 返回primaryKey
	 */
	public String getPrimaryKey() {
		return primaryKey;
	}
	/**
	 * @param primaryKey 设置primaryKey
	 */
	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}
	/**
	 * @return 返回operateType
	 */
	public Integer getOperateType() {
		return operateType;
	}
	/**
	 * @param operateType 设置operateType
	 */
	public void setOperateType(Integer operateType) {
		this.operateType = operateType;
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
	 * @return 返回logContentSet
	 */
	public Set<BusinessLogContent> getLogContentSet() {
		return logContentSet;
	}
	/**
	 * @param logContentSet 设置logContentSet
	 */
	public void setLogContentSet(Set<BusinessLogContent> logContentSet) {
		this.logContentSet = logContentSet;
	}
}

