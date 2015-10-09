package com.cdthgk.bmp.keysection.dto;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.cdthgk.exchange.vo.Reportable;
import com.cdthgk.platform.base.transmitor.dto.ReportListDto;

@XmlRootElement(name = "keySectionDto", namespace = "com.thgk.bmp.keysection.dto")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class KeySectionDto extends ReportListDto<KeySectionDto> implements Reportable, java.io.Serializable {

	// 序列化UID
	private static final long serialVersionUID = -7501038943342551957L;

	//上报数据xml内容
	private String reportData;

	private String sectionId;

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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<KeySectionDto> getReportableList() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setReportableList(List<KeySectionDto> reportableList) {
		// TODO Auto-generated method stub

	}

	/**
	 * @return 返回sectionId
	 */
	public String getSectionId() {
		return sectionId;
	}

	/**
	 * @param sectionId 设置sectionId
	 */
	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
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

}
