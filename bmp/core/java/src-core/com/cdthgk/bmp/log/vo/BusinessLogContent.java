package com.cdthgk.bmp.log.vo;



public class BusinessLogContent implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	private String logContentId;
	private BusinessLog log;
	private String filedName;
	private String tbkey;
	private String tbvalue;
	private String currentTbvalue;
	private String tbkeyDesc;
	/**
	 * @return 返回logContentId
	 */
	public String getLogContentId() {
		return logContentId;
	}
	/**
	 * @param logContentId 设置logContentId
	 */
	public void setLogContentId(String logContentId) {
		this.logContentId = logContentId;
	}
	/**
	 * @return 返回log
	 */
	public BusinessLog getLog() {
		return log;
	}
	/**
	 * @param log 设置log
	 */
	public void setLog(BusinessLog log) {
		this.log = log;
	}
	/**
	 * @return 返回tbkey
	 */
	public String getTbkey() {
		return tbkey;
	}
	/**
	 * @param tbkey 设置tbkey
	 */
	public void setTbkey(String tbkey) {
		this.tbkey = tbkey;
	}
	/**
	 * @return 返回tbvalue
	 */
	public String getTbvalue() {
		return tbvalue;
	}
	/**
	 * @param tbvalue 设置tbvalue
	 */
	public void setTbvalue(String tbvalue) {
		this.tbvalue = tbvalue;
	}
	/**
	 * @return 返回currentTbvalue
	 */
	public String getCurrentTbvalue() {
		return currentTbvalue;
	}
	/**
	 * @param currentTbvalue 设置currentTbvalue
	 */
	public void setCurrentTbvalue(String currentTbvalue) {
		this.currentTbvalue = currentTbvalue;
	}
	/**
	 * @return 返回tbkeyDesc
	 */
	public String getTbkeyDesc() {
		return tbkeyDesc;
	}
	/**
	 * @param tbkeyDesc 设置tbkeyDesc
	 */
	public void setTbkeyDesc(String tbkeyDesc) {
		this.tbkeyDesc = tbkeyDesc;
	}
	/**
	 * @return 返回filedName
	 */
	public String getFiledName() {
		return filedName;
	}
	/**
	 * @param filedName 设置filedName
	 */
	public void setFiledName(String filedName) {
		this.filedName = filedName;
	}
}

