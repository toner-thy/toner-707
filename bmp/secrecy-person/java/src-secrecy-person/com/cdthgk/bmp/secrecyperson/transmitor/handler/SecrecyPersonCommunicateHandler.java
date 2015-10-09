package com.cdthgk.bmp.secrecyperson.transmitor.handler;

import java.io.StringReader;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdthgk.bmp.secrecyperson.dto.SecrecyPersonDto;
import com.cdthgk.bmp.secrecyperson.transmitor.exchange.SecrecyPersonExchanger;
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
public class SecrecyPersonCommunicateHandler implements
		CommunicateHandler<SecrecyPersonDto, TransmitMessage> {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(SecrecyPersonCommunicateHandler.class);

	private SecrecyPersonExchanger secrecyPersonExchanger;
	private ServerReportMappingService serverReportMappingService;
	private XmlImportor importor;
	private DataSource dataSource;
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TransmitMessage processReceive(SecrecyPersonDto data) {
		// 继续发送到上级单位
		TransmitNextOrganUtil.toNextOrgan(data, serverReportMappingService, secrecyPersonExchanger);
		LOGGER.info("secrecyPersonDto processReceive");
		TransmitMessage transmitMessage = new TransmitMessage();
		// 服务器接收方
		String reportData = data.getReportData();
		LOGGER.debug("准备上报涉密人员信息-------------------------------------！");
		importor.setFkCheck(false);
		importor.setExistPolicy(ExistPolicy.update);
		importor.setDataSource(dataSource);
		try{
			importor.imp(new StringReader(reportData));
			transmitMessage.setSuccess(true);
		}catch (Exception e) {
			LOGGER.debug("涉密人员上报发送失败！" + e.getMessage());
			transmitMessage.setSuccess(false);
		}
		return transmitMessage;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void processReturn(TransmitMessage transmitMessage) {
		LOGGER.debug("SecrecyPersonDto 接收");
		if (!transmitMessage.isSuccess()) {
			throw new StandardAppException(transmitMessage.getMessage());
		}
	}

	/**
	 * @param secrecyPersonExchanger 设置secrecyPersonExchanger
	 */
	public void setSecrecyPersonExchanger(
			SecrecyPersonExchanger secrecyPersonExchanger) {
		this.secrecyPersonExchanger = secrecyPersonExchanger;
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
