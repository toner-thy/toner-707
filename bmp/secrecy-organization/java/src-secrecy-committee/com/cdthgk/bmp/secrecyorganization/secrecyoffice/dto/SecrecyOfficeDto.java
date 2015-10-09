package com.cdthgk.bmp.secrecyorganization.secrecyoffice.dto;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.cdthgk.exchange.vo.Reportable;
import com.cdthgk.platform.base.transmitor.dto.ReportListDto;

@XmlRootElement(name = "secrecyOfficeDto", namespace = "com.cdthgk.bmp.secrecyorganization.secrecyoffice.dto")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class SecrecyOfficeDto extends ReportListDto<SecrecyOfficeDto> implements Reportable, java.io.Serializable {

	// 序列化UID
	private static final long serialVersionUID = -7501038943342551957L;
	//上报数据xml内容
	private String reportData;
	private String secrecyOfficeId;

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
	 * @return 返回secrecyOfficeId
	 */
	public String getSecrecyOfficeId() {
		return secrecyOfficeId;
	}

	/**
	 * @param secrecyOfficeId 设置secrecyOfficeId
	 */
	public void setSecrecyOfficeId(String secrecyOfficeId) {
		this.secrecyOfficeId = secrecyOfficeId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<SecrecyOfficeDto> getReportableList() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setReportableList(List<SecrecyOfficeDto> reportableList) {
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
