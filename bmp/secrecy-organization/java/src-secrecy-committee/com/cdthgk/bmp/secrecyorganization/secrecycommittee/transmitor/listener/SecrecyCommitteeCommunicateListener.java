package com.cdthgk.bmp.secrecyorganization.secrecycommittee.transmitor.listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cdthgk.bmp.secrecyorganization.secrecycommittee.dto.SecrecyCommitteeDto;
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
public class SecrecyCommitteeCommunicateListener implements CommunicateListener<SecrecyCommitteeDto, TransmitMessage> {

	private static final Log LOGGER = LogFactory.getLog(SecrecyCommitteeCommunicateListener.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onReceiveFailing(SecrecyCommitteeDto data,
			TransmitMessage result, Throwable e) {
		LOGGER.info("SecrecyCommittee onReceiveFailing");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onReceiveSuccess(SecrecyCommitteeDto data,
			TransmitMessage result) {
		LOGGER.info("SecrecyCommittee onReceiveSuccess");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onReturnFailing(SecrecyCommitteeDto data,
			TransmitMessage result, Throwable e) {
		System.out.println("SecrecyCommittee onReturnFailing");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onReturnSuccess(SecrecyCommitteeDto data,
			TransmitMessage result) {
		System.out.println("SecrecyCommittee onReturnSuccess");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onSendFailing(SecrecyCommitteeDto data, Throwable e) {
		System.out.println("SecrecyCommittee onSendFailing");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onSendSuccess(SecrecyCommitteeDto data) {
		System.out.println("SecrecyCommittee onSendSuccess");
	}

}
