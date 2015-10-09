package com.cdthgk.bmp.secrecysystem.secrecycomputer.service;

import java.util.List;

import com.cdthgk.bmp.secrecysystem.secrecycomputer.vo.SecrecyComputerChange;
import com.cdthgk.platform.base.service.GenericBasicService;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

public interface SecrecyComputerChangeService extends GenericBasicService<SecrecyComputerChange, String> {

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
	 * @param secrecyComputerChangePsm
	 * @param district
	 * @param checkLower
	 * @param falg
	 * @param secrecyComputer
	 * @return
	 */
	public List<SecrecyComputerChange> getSecrecyComputerChangePageList(
			PageSortModel<SecrecyComputerChange> secrecyComputerChangePsm,
			SecrecyComputerChange secrecyComputerChange, User currentUser, boolean flag, District district, String checkLower);

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
	 * @param secrecyComputerChange
	 * @param currentUser
	 */
	public void saveSecrecyComputerChange(
			SecrecyComputerChange secrecyComputerChange, User currentUser);


}
