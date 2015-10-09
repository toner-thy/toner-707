package com.cdthgk.bmp.secrecyperson.transmitor.listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cdthgk.bmp.secrecyperson.dto.SecrecyPersonDto;
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
public class SecrecyPersonCommunicateListener implements CommunicateListener<SecrecyPersonDto, TransmitMessage> {

	private static final Log LOGGER = LogFactory.getLog(SecrecyPersonCommunicateListener.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onReceiveFailing(SecrecyPersonDto data,
			TransmitMessage result, Throwable e) {
		LOGGER.info("secrecyPerson onReceiveFailing");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onReceiveSuccess(SecrecyPersonDto data,
			TransmitMessage result) {
		LOGGER.info("secrecyPerson onReceiveSuccess");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onReturnFailing(SecrecyPersonDto data,
			TransmitMessage result, Throwable e) {
		System.out.println("secrecyPerson onReturnFailing");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onReturnSuccess(SecrecyPersonDto data,
			TransmitMessage result) {
		System.out.println("secrecyPerson onReturnSuccess");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onSendFailing(SecrecyPersonDto data, Throwable e) {
		System.out.println("secrecyPerson onSendFailing");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onSendSuccess(SecrecyPersonDto data) {
		System.out.println("secrecyPerson onSendSuccess");
	}

}
