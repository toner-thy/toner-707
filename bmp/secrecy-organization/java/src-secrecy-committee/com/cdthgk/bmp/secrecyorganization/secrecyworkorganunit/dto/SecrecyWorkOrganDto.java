package com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.dto;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.cdthgk.exchange.vo.Reportable;
import com.cdthgk.platform.base.transmitor.dto.ReportListDto;

@XmlRootElement(name = "secrecyWorkOrganDto", namespace = "com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.dto")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class SecrecyWorkOrganDto extends ReportListDto<SecrecyWorkOrganDto> implements Reportable, java.io.Serializable {

	// 序列化UID
	private static final long serialVersionUID = -7501038943342551957L;
	//上报数据xml内容
	private String reportData;
	private String secrecyWorkOrganId;

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
	 * @return 返回secrecyWorkOrganId
	 */
	public String getSecrecyWorkOrganId() {
		return secrecyWorkOrganId;
	}

	/**
	 * @param secrecyWorkOrganId 设置secrecyWorkOrganId
	 */
	public void setSecrecyWorkOrganId(String secrecyWorkOrganId) {
		this.secrecyWorkOrganId = secrecyWorkOrganId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<SecrecyWorkOrganDto> getReportableList() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setReportableList(List<SecrecyWorkOrganDto> reportableList) {
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
