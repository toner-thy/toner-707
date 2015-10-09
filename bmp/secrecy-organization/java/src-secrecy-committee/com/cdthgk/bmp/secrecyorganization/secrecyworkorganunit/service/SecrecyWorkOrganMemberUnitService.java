package com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.service;

import com.cdthgk.bmp.core.service.BmpService;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.vo.SecrecyWorkOrganMemberUnit;
import com.cdthgk.platform.organization.organ.domain.Organ;

/**
 * <p>
 * 保密办成员  Service 抽象类(对外接口)
 * </p>
 * <p>
 * 刘椿成  2012-12-29 16:43:03
 * </p>
 * <blockquote>
 * <h4>历史修改记录</h4>
 * <ul>
 * <li>修改人 修改时间 修改描述
 * </ul>
 * </blockquote>
 * <p>
 * copyright liucc 2012, all rights reserved.
 * </p>
 * @author liucc
 * @since 1.0
 * @version 1.0
 */
public interface SecrecyWorkOrganMemberUnitService extends BmpService<SecrecyWorkOrganMemberUnit, String> {

	/**
	 * 刘椿成  2012-12-29 16:13:03
	 * <p>
	 * 根据当前登录用户所在单位获取保密工作机构
	 * </p>
	 * @param organ
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * <p>
	 * copyright liucc 2012, all rights reserved.
	 * </p>
	 * @author liucc
	 * @since 1.0
	 * @version 1.0
	 */
	public SecrecyWorkOrganMemberUnit getSecrecyOfficeByOrgan(Organ organ);
}