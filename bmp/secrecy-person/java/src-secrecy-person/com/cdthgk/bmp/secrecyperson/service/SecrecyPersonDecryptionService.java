/**
 *
 */
package com.cdthgk.bmp.secrecyperson.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.bmp.secrecyperson.vo.SecrecyPersonDecryption;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;

/**
 * <p>
 * 类的说明放这里 2013-7-17 下午3:17:20
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 *
 * @author 宋亚非
 */
public interface SecrecyPersonDecryptionService extends
		BmpServiceTemplate<SecrecyPersonDecryption, String> {
	public List<SecrecyPersonDecryption> getPageAllList(
			PageSortModel<SecrecyPersonDecryption> psm,
			SecrecyPersonDecryption secrecyPersonDecryption, Organ organ, District district, String checkLower,
			Map<String,Date> dateParams,Integer historyFlag);

	public List<SecrecyPersonDecryption> findPersonList(String secrecyPersonId);

}
