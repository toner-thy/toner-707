package com.cdthgk.bmp.secrecyperson.dto;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.cdthgk.exchange.vo.Reportable;
import com.cdthgk.platform.base.transmitor.dto.ReportListDto;

@XmlRootElement(name = "secrecyPersonDto", namespace = "com.cdthgk.bmp.secrecyperson.dto")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class SecrecyPersonDto extends ReportListDto<SecrecyPersonDto> implements Reportable, java.io.Serializable {

	// 序列化UID
	private static final long serialVersionUID = -4952802135556369067L;
	//上报数据xml内容
	private String reportData;

	private String secrecyPersonId;

	/**
	 * @return 返回reportData
	 */
	public String getReportData() {
		return reportData;
	}

	/**
	 * @param reportData 设置reportData
	 */
	public void setReportData(String reportData) {
		this.reportData = reportData;
	}

	/**
	 * @return 返回secrecyPersonId
	 */
	public String getSecrecyPersonId() {
		return secrecyPersonId;
	}

	/**
	 * @param secrecyPersonId 设置secrecyPersonId
	 */
	public void setSecrecyPersonId(String secrecyPersonId) {
		this.secrecyPersonId = secrecyPersonId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<SecrecyPersonDto> getReportableList() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setReportableList(List<SecrecyPersonDto> reportableList) {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Date getInceptTime() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer getReportState() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Date getReportTime() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setInceptTime(Date inceptTime) {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setReportState(Integer reportState) {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setReportTime(Date reportTime) {
		// TODO Auto-generated method stub

	}

}
