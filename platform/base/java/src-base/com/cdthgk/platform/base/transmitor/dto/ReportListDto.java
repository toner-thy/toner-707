package com.cdthgk.platform.base.transmitor.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.cdthgk.exchange.annotation.ReceiveOrgan;
import com.cdthgk.exchange.vo.Reportable;
import com.cdthgk.exchange.vo.ReportableListDto;

@XmlRootElement(name="reportListDto",namespace="com.cdthgk.platform.base.transmitor.dto")
@XmlAccessorType(XmlAccessType.PROPERTY)
public abstract class ReportListDto<E extends Reportable> implements ReportableListDto<E> {


	private static final long serialVersionUID = -2415973970607682189L;

	@ReceiveOrgan
	private String receiveOrganId;


	/**
	 * @return 返回receiveOrganId
	 */
	public String getReceiveOrganId() {
		return receiveOrganId;
	}

	/**
	 * @param receiveOrganId 设置receiveOrganId
	 */
	public void setReceiveOrganId(String receiveOrganId) {
		this.receiveOrganId = receiveOrganId;
	}
}
