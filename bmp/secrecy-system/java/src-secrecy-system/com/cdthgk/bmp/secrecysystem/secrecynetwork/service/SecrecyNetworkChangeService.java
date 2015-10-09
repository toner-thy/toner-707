package com.cdthgk.bmp.secrecysystem.secrecynetwork.service;

import java.util.List;

import com.cdthgk.bmp.secrecysystem.secrecynetwork.vo.SecrecyNetworkChange;
import com.cdthgk.platform.base.service.GenericBasicService;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

public interface SecrecyNetworkChangeService extends GenericBasicService<SecrecyNetworkChange, String> {

	/**
	 * <p>
	 * 得到密级变更成员列表
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-8-5 - 上午9:59:37
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param secrecyNetworkChangePsm
	 * @param checkLower
	 * @param district
	 * @param flag
	 * @param secrecyNetwork
	 * @return
	 */
	public List<SecrecyNetworkChange> getSecrecyNetworkChangePageList(
			PageSortModel<SecrecyNetworkChange> secrecyNetworkChangePsm,
			SecrecyNetworkChange secrecyNetworkChange, User currentUser, boolean flag, District district, String checkLower);

	/**
	 * <p>
	 * 保存密级变更
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-8-5 - 上午10:05:55
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param secrecyNetworkChange
	 * @param currentUser
	 */
	public void saveSecrecyNetworkChange(
			SecrecyNetworkChange secrecyNetworkChange, User currentUser);


}
