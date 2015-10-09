package com.cdthgk.bmp.secrecysystem.secrecynetwork.service;

import java.util.List;

import com.cdthgk.bmp.secrecysystem.secrecynetwork.vo.SecrecyNetworkClear;
import com.cdthgk.platform.base.service.GenericBasicService;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

public interface SecrecyNetworkClearService extends GenericBasicService<SecrecyNetworkClear, String> {

	/**
	 * <p>
	 * 保存密级解除
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-8-5 - 上午10:11:06
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param secrecyNetworkClear
	 * @param currentUser
	 */
	public void saveSecrecyNetworkClear(
			SecrecyNetworkClear secrecyNetworkClear, User currentUser);

	/**
	 * <p>
	 * 得到解除成员列表
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-8-5 - 上午10:15:05
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param secrecyNetworkClearPsm
	 * @param checkLower
	 * @param district
	 * @param flag
	 * @param secrecyNetwork
	 * @return
	 */
	public List<SecrecyNetworkClear> getSecrecyNetworkClearPageList(
			PageSortModel<SecrecyNetworkClear> secrecyNetworkClearPsm,
			SecrecyNetworkClear secrecyNetworkClear, User currentUser, boolean flag, District district, String checkLower);
}
