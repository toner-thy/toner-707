/**
 *
 */
package com.cdthgk.bmp.secrecyperson.service;

import java.util.Date;
import java.util.List;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.bmp.secrecyperson.vo.SecrecyPersonLevelChange;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;

/**
 * <p>
 * 类的说明放这里 2013-7-17 下午3:15:02
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 *
 * @author 宋亚非
 */
public interface SecrecyPersonLevelChangeService extends
		BmpServiceTemplate<SecrecyPersonLevelChange, String> {

	/**
	 *
	 * <p>
	 * 方法名：getAllPageList 获取涉密人员密级变更历史
	 * </p>
	 * <p>
	 * 宋亚非 2013-7-17 下午7:04:47
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@param psm
	 *@param secrecyPersonLevelChange
	 *@param organ
	 *@return
	 */
	public List<SecrecyPersonLevelChange> getAllPageList(
			PageSortModel<SecrecyPersonLevelChange> psm,
			SecrecyPersonLevelChange secrecyPersonLevelChange, Organ organ, District district,String checkLower, Date chageDateStart, Date chageDateEnd);

	/**
	 *
	 * <p>
	 * 方法名：findPersonList 按人员查询变更历史
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-15 上午9:09:05
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@param secrecyPersonId
	 *@return
	 */
	public List<SecrecyPersonLevelChange> findPersonList(String secrecyPersonId, String secrecyPersonLevelChangeId);
}
