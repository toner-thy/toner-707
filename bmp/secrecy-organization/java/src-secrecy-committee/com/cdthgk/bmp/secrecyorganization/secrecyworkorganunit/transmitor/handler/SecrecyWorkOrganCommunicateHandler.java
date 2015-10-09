package com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.transmitor.handler;

import java.io.StringReader;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.dto.SecrecyWorkOrganDto;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.transmitor.exchange.SecrecyWorkOrganExchanger;
import com.cdthgk.bmp.secrecyperson.transmitor.handler.SecrecyPersonCommunicateHandler;
import com.cdthgk.code.enums.DataStatus;
import com.cdthgk.common.db.data.xml.XmlImportor;
import com.cdthgk.common.db.data.DataImportor.ExistPolicy;
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
public class SecrecyWorkOrganCommunicateHandler implements
		CommunicateHandler<SecrecyWorkOrganDto, TransmitMessage> {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(SecrecyPersonCommunicateHandler.class);

	private SecrecyWorkOrganExchanger secrecyWorkOrganExchanger;
	private ServerReportMappingService serverReportMappingService;
	private AttachmentService attachmentService;
	private XmlImportor importor;
	private DataSource dataSource;
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TransmitMessage processReceive(SecrecyWorkOrganDto data) {
		// 继续发送到上级单位
		TransmitNextOrganUtil.toNextOrgan(data, serverReportMappingService, secrecyWorkOrganExchanger);
		LOGGER.info("keySectionDto processReceive");
		LOGGER.debug("准备上报保密工作机构信息-------------------------------------！");
		//由于XML文件已经将附件保存，这里做的是删除以前的保密工作机构附件
		List<Attachment> dbSectionAttachmentList = attachmentService.getAttachmentsByHostId(data.getSecrecyWorkOrganId());
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
		LOGGER.debug("准备上报保密工作机构信息-------------------------------------！");
		importor.setFkCheck(false);
		importor.setExistPolicy(ExistPolicy.update);
		importor.setDataSource(dataSource);
		try{
			importor.imp(new StringReader(reportData));
			transmitMessage.setSuccess(true);
		}catch (Exception e) {
			LOGGER.debug("保密工作机构上报发送失败！" + e.getMessage());
			transmitMessage.setSuccess(false);
		}
		return transmitMessage;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void processReturn(TransmitMessage transmitMessage) {
		LOGGER.debug("SecrecyWorkOrganDto 接收");
		if (!transmitMessage.isSuccess()) {
			throw new StandardAppException(transmitMessage.getMessage());
		}
	}

	/**
	 * @param secrecyWorkOrganExchanger 设置secrecyWorkOrganExchanger
	 */
	public void setSecrecyWorkOrganExchanger(
			SecrecyWorkOrganExchanger secrecyWorkOrganExchanger) {
		this.secrecyWorkOrganExchanger = secrecyWorkOrganExchanger;
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
