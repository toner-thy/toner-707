package com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.transmitor.listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.dto.SecrecyWorkOrganDto;
import com.cdthgk.communication.core.model.CommunicateListener;
import com.cdthgk.platform.base.transmitor.vo.TransmitMessage;

/**
 * <p>
 * 类的说明放这里
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 *
 * @author taohy
 */
public class SecrecyWorkOrganCommunicateListener implements CommunicateListener<SecrecyWorkOrganDto, TransmitMessage> {

	private static final Log LOGGER = LogFactory.getLog(SecrecyWorkOrganCommunicateListener.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onReceiveFailing(SecrecyWorkOrganDto data,
			TransmitMessage result, Throwable e) {
		LOGGER.info("SecrecyWorkOrgan onReceiveFailing");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onReceiveSuccess(SecrecyWorkOrganDto data,
			TransmitMessage result) {
		LOGGER.info("SecrecyWorkOrgan onReceiveSuccess");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onReturnFailing(SecrecyWorkOrganDto data,
			TransmitMessage result, Throwable e) {
		System.out.println("SecrecyWorkOrgan onReturnFailing");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onReturnSuccess(SecrecyWorkOrganDto data,
			TransmitMessage result) {
		System.out.println("SecrecyWorkOrgan onReturnSuccess");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onSendFailing(SecrecyWorkOrganDto data, Throwable e) {
		System.out.println("SecrecyWorkOrgan onSendFailing");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onSendSuccess(SecrecyWorkOrganDto data) {
		System.out.println("SecrecyWorkOrgan onSendSuccess");
	}

}
