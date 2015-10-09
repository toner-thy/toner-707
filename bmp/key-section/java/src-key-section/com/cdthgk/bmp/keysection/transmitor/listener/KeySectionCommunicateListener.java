package com.cdthgk.bmp.keysection.transmitor.listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cdthgk.bmp.keysection.dto.KeySectionDto;
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
public class KeySectionCommunicateListener implements CommunicateListener<KeySectionDto, TransmitMessage> {

	private static final Log LOGGER = LogFactory.getLog(KeySectionCommunicateListener.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onReceiveFailing(KeySectionDto data,
			TransmitMessage result, Throwable e) {
		LOGGER.info("KeySection onReceiveFailing");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onReceiveSuccess(KeySectionDto data,
			TransmitMessage result) {
		LOGGER.info("KeySection onReceiveSuccess");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onReturnFailing(KeySectionDto data,
			TransmitMessage result, Throwable e) {
		System.out.println("KeySection onReturnFailing");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onReturnSuccess(KeySectionDto data,
			TransmitMessage result) {
		System.out.println("KeySection onReturnSuccess");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onSendFailing(KeySectionDto data, Throwable e) {
		System.out.println("KeySection onSendFailing");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onSendSuccess(KeySectionDto data) {
		System.out.println("KeySection onSendSuccess");
	}

}
