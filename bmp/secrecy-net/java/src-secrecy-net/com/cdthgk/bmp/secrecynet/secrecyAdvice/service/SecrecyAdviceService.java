
package com.cdthgk.bmp.secrecynet.secrecyAdvice.service;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.bmp.secrecynet.secrecyAdvice.vo.SecrecyAdvice;
import com.cdthgk.platform.organization.organ.domain.Organ;

/**
 * <p>
 * 类的说明放这里
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 *
 * @author 钟冀
 */
public interface SecrecyAdviceService extends BmpServiceTemplate<SecrecyAdvice, String> {

	/**
	 * <p>
	 * 方法的说明
	 * </p>
	 * @param year
	 * @param organ
	 * @return
	 */
	SecrecyAdvice getSecrecyAdviceByYear(int year, Organ organ);

}
