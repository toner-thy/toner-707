package com.cdthgk.bmp.keyPart.transmitor.handler;

import java.io.StringReader;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdthgk.bmp.keyPart.dto.KeyPartDto;
import com.cdthgk.bmp.keyPart.transmitor.exchange.KeyPartExchanger;
import com.cdthgk.bmp.secrecyperson.transmitor.handler.SecrecyPersonCommunicateHandler;
import com.cdthgk.code.enums.DataStatus;
import com.cdthgk.common.db.data.DataImportor.ExistPolicy;
import com.cdthgk.common.db.data.xml.XmlImportor;
import com.cdthgk.common.lang.CollectionUtils;
import com.cdthgk.communication.core.model.CommunicateHandler;
import com.cdthgk.exchange.serverorganmapping.service.ServerReportMappingService;
import com.cdthgk.platform.attachment.domain.Attachment;
import com.cdthgk.platform.attachment.service.AttachmentService;
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
public class KeyPartCommunicateHandler implements
		CommunicateHandler<KeyPartDto, TransmitMessage> {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(SecrecyPersonCommunicateHandler.class);

	private KeyPartExchanger keyPartExchanger;
	private ServerReportMappingService serverReportMappingService;
	private AttachmentService attachmentService;
	private XmlImportor importor;
	private DataSource dataSource;
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TransmitMessage processReceive(KeyPartDto data) {
		// 继续发送到上级单位
		TransmitNextOrganUtil.toNextOrgan(data, serverReportMappingService, keyPartExchanger);
		LOGGER.info("secrecyPersonDto processReceive");
		//由于XML文件已经将附件保存，这里做的是删除以前的要害部位附件
		List<Attachment> dbSectionAttachmentList = attachmentService.getAttachmentsByHostId(data.getPartId());
		if (!CollectionUtils.isEmpty(dbSectionAttachmentList)) {
			for (Attachment attachment : dbSectionAttachmentList) {
				attachment.setStatus(DataStatus.DELETE);
				attachmentService.update(attachment);
				attachmentService.deletePhysically(attachment);
			}
		}
		TransmitMessage transmitMessage = new TransmitMessage();
		// 服务器接收方
		String reportData = data.getReportData();
		LOGGER.debug("准备上报要害部位信息-------------------------------------！");
		importor.setFkCheck(false);
		importor.setExistPolicy(ExistPolicy.update);
		importor.setDataSource(dataSource);
		try{
			importor.imp(new StringReader(reportData));
			transmitMessage.setSuccess(true);
		}catch (Exception e) {
			LOGGER.debug("要害部位上报发送失败！" + e.getMessage());
			transmitMessage.setSuccess(false);
		}
		return transmitMessage;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void processReturn(TransmitMessage transmitMessage) {
		LOGGER.debug("KeyPartDto 接收");
		if (!transmitMessage.isSuccess()) {
			throw new StandardAppException(transmitMessage.getMessage());
		}
	}

	/**
	 * @param keyPartExchanger 设置keyPartExchanger
	 */
	public void setKeyPartExchanger(KeyPartExchanger keyPartExchanger) {
		this.keyPartExchanger = keyPartExchanger;
	}

	/**
	 * @param serverReportMappingService 设置serverReportMappingService
	 */
	public void setServerReportMappingService(
			ServerReportMappingService serverReportMappingService) {
		this.serverReportMappingService = serverReportMappingService;
	}

	/**
	 * @param attachmentService 设置attachmentService
	 */
	public void setAttachmentService(AttachmentService attachmentService) {
		this.attachmentService = attachmentService;
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
