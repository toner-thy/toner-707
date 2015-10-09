package com.cdthgk.bmp.secrecysystem.secrecycomputer.service;

import java.util.List;

import com.cdthgk.bmp.secrecysystem.secrecycomputer.vo.SecrecyComputerClear;
import com.cdthgk.platform.base.service.GenericBasicService;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

public interface SecrecyComputerClearService extends GenericBasicService<SecrecyComputerClear, String> {

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
	 * @param secrecyComputerClear
	 * @param currentUser
	 */
	public void saveSecrecyComputerClear(
			SecrecyComputerClear secrecyComputerClear, User currentUser);

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
	 * @param secrecyComputerClearPsm
	 * @param district
	 * @param flag
	 * @param checkLower
	 * @param secrecyComputer
	 * @return
	 */
	public List<SecrecyComputerClear> getSecrecyComputerClearPageList(
			PageSortModel<SecrecyComputerClear> secrecyComputerClearPsm,
			SecrecyComputerClear secrecyComputerClear, User currentUser, boolean flag, District district, String checkLower);
}
