package com.cdthgk.bmp.secrecyorganization.secrecyoffice.transmitor.handler;

import java.io.StringReader;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdthgk.bmp.secrecyorganization.secrecyoffice.dto.SecrecyOfficeDto;
import com.cdthgk.bmp.secrecyorganization.secrecyoffice.transmitor.exchange.SecrecyOfficeExchanger;
import com.cdthgk.bmp.secrecyperson.transmitor.handler.SecrecyPersonCommunicateHandler;
import com.cdthgk.common.db.data.xml.XmlImportor;
import com.cdthgk.common.db.data.DataImportor.ExistPolicy;
import com.cdthgk.communication.core.model.CommunicateHandler;
import com.cdthgk.exchange.serverorganmapping.service.ServerReportMappingService;
import com.cdthgk.platform.base.transmitor.vo.TransmitMessage;
import com.cdthgk.platform.base.util.TransmitNextOrganUtil;
import com.cdthgk.standard.exception.StandardAppException;

/**
 * <p>
 * SecrecyPersonCommunicateHandler
 * </p>
 *
 * @author taohy
 */
public class SecrecyOfficeCommunicateHandler implements
		CommunicateHandler<SecrecyOfficeDto, TransmitMessage> {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(SecrecyPersonCommunicateHandler.class);

	private SecrecyOfficeExchanger secrecyOfficeExchanger;
	private ServerReportMappingService serverReportMappingService;
	private XmlImportor importor;
	private DataSource dataSource;
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TransmitMessage processReceive(SecrecyOfficeDto data) {
		// 继续发送到上级单位
		TransmitNextOrganUtil.toNextOrgan(data, serverReportMappingService, secrecyOfficeExchanger);
		LOGGER.info("secrecyOfficeDto processReceive");
		TransmitMessage transmitMessage = new TransmitMessage();
		// 服务器接收方
		String reportData = data.getReportData();
		LOGGER.debug("准备上报保密办信息-------------------------------------！");
		importor.setFkCheck(false);
		importor.setExistPolicy(ExistPolicy.update);
		importor.setDataSource(dataSource);
		try{
			importor.imp(new StringReader(reportData));
			transmitMessage.setSuccess(true);
		}catch (Exception e) {
			LOGGER.debug("保密办上报发送失败！" + e.getMessage());
			transmitMessage.setSuccess(false);
		}
		return transmitMessage;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void processReturn(TransmitMessage transmitMessage) {
		LOGGER.debug("SecrecyOfficeDto 接收");
		if (!transmitMessage.isSuccess()) {
			throw new StandardAppException(transmitMessage.getMessage());
		}
	}

	/**
	 * @param secrecyOfficeExchanger 设置secrecyOfficeExchanger
	 */
	public void setSecrecyOfficeExchanger(
			SecrecyOfficeExchanger secrecyOfficeExchanger) {
		this.secrecyOfficeExchanger = secrecyOfficeExchanger;
	}

	/**
	 * @param serverReportMappingService 设置serverReportMappingService
	 */
	public void setServerReportMappingService(
			ServerReportMappingService serverReportMappingService) {
		this.serverReportMappingService = serverReportMappingService;
	}

	/**
	 * @param importor 设置importor
	 */
	public void setImportor(XmlImportor importor) {
		this.importor = importor;
	}

	/**
	 * @param dataSource 设置dataSource
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
}
