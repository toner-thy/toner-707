package com.cdthgk.bmp.keyPart.dto;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.cdthgk.exchange.vo.Reportable;
import com.cdthgk.platform.base.transmitor.dto.ReportListDto;

@XmlRootElement(name = "keyPartDto", namespace = "com.thgk.bmp.keypart.dto")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class KeyPartDto extends ReportListDto<KeyPartDto> implements Reportable, java.io.Serializable {

	// 序列化UID
	private static final long serialVersionUID = -4952802135556369067L;
	//上报数据xml内容
	private String reportData;

	private String partId;

	//附件使用
	private List<String> partListId;

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
	 * @return 返回partId
	 */
	public String getPartId() {
		return partId;
	}

	/**
	 * @param partId 设置partId
	 */
	public void setPartId(String partId) {
		this.partId = partId;
	}

	/**
	 * @return 返回partListId
	 */
	public List<String> getPartListId() {
		return partListId;
	}

	/**
	 * @param partListId 设置partListId
	 */
	public void setPartListId(List<String> partListId) {
		this.partListId = partListId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<KeyPartDto> getReportableList() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setReportableList(List<KeyPartDto> reportableList) {
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
