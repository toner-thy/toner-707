/**
 *
 */
package com.cdthgk.bmp.secrecysystem.secrecyotherdevice.service;

import java.util.List;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.bmp.secrecysystem.secrecyotherdevice.vo.SecrecyOthersChange;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;

/**
 * <p>
 * 类的说明放这里 2013-8-5 下午2:09:06
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 *
 * @author 宋亚非
 */
public interface SecrecyOthersChangeService extends
		BmpServiceTemplate<SecrecyOthersChange, String> {

	/**
	 *
	 * <p>
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-5 下午2:12:24
	 * </p>
	 * <blockquote> <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 *
	 * @since 1.0
	 * @version 1.0
	 * @param psm
	 * @param secrecyOthersChange
	 * @param organ
	 * @param secrecyStatusFlag
	 * @return
	 */
	public List<SecrecyOthersChange> findPageAllList(
			PageSortModel<SecrecyOthersChange> psm,
			SecrecyOthersChange secrecyOthersChange, Organ organ, District district, String checkLower);


	public List<SecrecyOthersChange> findDataList(String secrecyOthersId ,String secrecyOthersChangeId );
}
