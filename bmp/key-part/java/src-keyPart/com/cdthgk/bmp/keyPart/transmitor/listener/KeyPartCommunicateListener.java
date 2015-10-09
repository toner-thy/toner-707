package com.cdthgk.bmp.keyPart.transmitor.listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cdthgk.bmp.keyPart.dto.KeyPartDto;
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
public class KeyPartCommunicateListener implements CommunicateListener<KeyPartDto, TransmitMessage> {

	private static final Log LOGGER = LogFactory.getLog(KeyPartCommunicateListener.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onReceiveFailing(KeyPartDto data,
			TransmitMessage result, Throwable e) {
		LOGGER.info("KeyPart onReceiveFailing");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onReceiveSuccess(KeyPartDto data,
			TransmitMessage result) {
		LOGGER.info("KeyPart onReceiveSuccess");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onReturnFailing(KeyPartDto data,
			TransmitMessage result, Throwable e) {
		System.out.println("KeyPart onReturnFailing");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onReturnSuccess(KeyPartDto data,
			TransmitMessage result) {
		System.out.println("KeyPart onReturnSuccess");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onSendFailing(KeyPartDto data, Throwable e) {
		System.out.println("KeyPart onSendFailing");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onSendSuccess(KeyPartDto data) {
		System.out.println("KeyPart onSendSuccess");
	}

}
