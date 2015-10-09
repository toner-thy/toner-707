package com.cdthgk.bmp.keysection.transmitor.handler;

import java.io.StringReader;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdthgk.bmp.keysection.dto.KeySectionDto;
import com.cdthgk.bmp.keysection.transmitor.exchange.KeySectionExchanger;
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
public class KeySectionCommunicateHandler implements
		CommunicateHandler<KeySectionDto, TransmitMessage> {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(SecrecyPersonCommunicateHandler.class);

	private AttachmentService attachmentService;
	private ServerReportMappingService serverReportMappingService;
	private KeySectionExchanger keySectionExchanger;
	private XmlImportor importor;
	private DataSource dataSource;
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TransmitMessage processReceive(KeySectionDto data) {
		// 继续发送到上级单位
		TransmitNextOrganUtil.toNextOrgan(data, serverReportMappingService, keySectionExchanger);
		LOGGER.info("keySectionDto processReceive");

		//由于XML文件已经将附件保存，这里做的是删除以前的要害部门附件
		List<Attachment> dbSectionAttachmentList = attachmentService.getAttachmentsByHostId(data.getSectionId());
		if (!CollectionUtils.isEmpty(dbSectionAttachmentList)) {
			for (Attachment attachment : dbSectionAttachmentList) {
				attachment.setStatus(DataStatus.DELETE);
				attachmentService.update(attachment);
				attachmentService.deletePhysically(attachment);
			}
		}

		//由于XML文件已经将附件保存，这里做的是删除以前的要害部位附件
		List<String> partListId = data.getPartListId();
		if (CollectionUtils.isNotEmpty(partListId)) {
			for (String partId : partListId) {
				List<Attachment> dbPartAttachmentList = attachmentService.getAttachmentsByHostId(partId);
				if (CollectionUtils.isNotEmpty(dbPartAttachmentList)) {
					for (Attachment attachment : dbPartAttachmentList) {
						attachment.setStatus(DataStatus.DELETE);
						attachmentService.update(attachment);
						attachmentService.deletePhysically(attachment);
					}
				}
			}
		}


		TransmitMessage transmitMessage = new TransmitMessage();
		// 服务器接收方
		String reportData = data.getReportData();
		LOGGER.debug("准备上报要害部门信息-------------------------------------！");
		importor.setFkCheck(false);
		importor.setExistPolicy(ExistPolicy.update);
		importor.setDataSource(dataSource);
		try{
			importor.imp(new StringReader(reportData));
			transmitMessage.setSuccess(true);
		}catch (Exception e) {
			LOGGER.debug("要害部门上报发送失败！" + e.getMessage());
			transmitMessage.setSuccess(false);
		}
		return transmitMessage;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void processReturn(TransmitMessage transmitMessage) {
		LOGGER.debug("KeySectionListDto 接收");
		if (!transmitMessage.isSuccess()) {
			throw new StandardAppException(transmitMessage.getMessage());
		}
	}

	/**
	 * @param serverReportMappingService 设置serverReportMappingService
	 */
	public void setServerReportMappingService(
			ServerReportMappingService serverReportMappingService) {
		this.serverReportMappingService = serverReportMappingService;
	}

	/**
	 * @param keySectionExchanger 设置keySectionExchanger
	 */
	public void setKeySectionExchanger(KeySectionExchanger keySectionExchanger) {
		this.keySectionExchanger = keySectionExchanger;
	}

	/**
	 * @param importor 设置importor
	 */
	public void setImportor(XmlImportor importor) {
		this.importor = importor;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * @param attachmentService 设置attachmentService
	 */
	public void setAttachmentService(AttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}

}
