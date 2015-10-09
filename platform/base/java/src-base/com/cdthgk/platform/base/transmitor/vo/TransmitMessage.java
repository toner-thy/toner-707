
package com.cdthgk.platform.base.transmitor.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * <p>
 * 传输的消息
 * </p>
 * <p>
 * 创建时间 2011-8-31 - 上午11:37:31
 * </p>
 * 
 * @author 钟冀
 * @author thgk r&d
 * @since 1.0
 * @version 1.0
 */
@XmlRootElement(name="transmitMessage",namespace="com.thgk.sys.transmit.vo")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class TransmitMessage {

	private boolean success;
	
	private String message;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}