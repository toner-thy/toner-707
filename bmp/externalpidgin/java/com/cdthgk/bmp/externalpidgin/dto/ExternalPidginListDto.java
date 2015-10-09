/**
 *
 */
package com.cdthgk.bmp.externalpidgin.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.cdthgk.exchange.annotation.MultiData;
import com.cdthgk.exchange.annotation.ReceiveOrgan;


@SuppressWarnings("all")
@XmlRootElement(name="externalPidginListDto",namespace="com.thgk.bmp.externalpidgin.dto")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ExternalPidginListDto {

	/**
	 *
	 */
	private static final long serialVersionUID = -1112621056912824029L;

	@MultiData
	private List<ExternalPidginDto> reportableList = new ArrayList<ExternalPidginDto>();

	@ReceiveOrgan
	private String receiveOrganId;

	/**
	 * @return the receiveOrganId
	 */
	public String getReceiveOrganId() {
		return receiveOrganId;
	}

	/**
	 * @param receiveOrganId the receiveOrganId to set
	 */
	public void setReceiveOrganId(String receiveOrganId) {
		this.receiveOrganId = receiveOrganId;
	}

}
