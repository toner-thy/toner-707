package com.cdthgk.bmp.secrecyorganization.secrecyoffice.transmitor.listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cdthgk.bmp.secrecyorganization.secrecyoffice.dto.SecrecyOfficeDto;
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
public class SecrecyOfficeCommunicateListener implements CommunicateListener<SecrecyOfficeDto, TransmitMessage> {

	private static final Log LOGGER = LogFactory.getLog(SecrecyOfficeCommunicateListener.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onReceiveFailing(SecrecyOfficeDto data,
			TransmitMessage result, Throwable e) {
		LOGGER.info("SecrecyOffice onReceiveFailing");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onReceiveSuccess(SecrecyOfficeDto data,
			TransmitMessage result) {
		LOGGER.info("SecrecyOffice onReceiveSuccess");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onReturnFailing(SecrecyOfficeDto data,
			TransmitMessage result, Throwable e) {
		System.out.println("SecrecyOffice onReturnFailing");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onReturnSuccess(SecrecyOfficeDto data,
			TransmitMessage result) {
		System.out.println("SecrecyOffice onReturnSuccess");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onSendFailing(SecrecyOfficeDto data, Throwable e) {
		System.out.println("SecrecyOffice onSendFailing");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onSendSuccess(SecrecyOfficeDto data) {
		System.out.println("SecrecyOffice onSendSuccess");
	}

}
