package com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.service;

import java.util.List;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.bmp.secrecyorganization.secrecycommittee.vo.PersonGroupPosition;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.vo.SecrecyWorkOrgan;

/**
 * <p>
 * 人员组职位 Service 抽象类(对外)
 * </p>
 * <p>
 * 刘椿成 2012-12-18 15:23
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
 * @author 刘椿成
 * @since 1.0
 * @version 1.0
 */
public interface SecrecyWorkOrganPositionModuleService extends BmpServiceTemplate<PersonGroupPosition, String> {

	/**
	 * <p>
	 * 根据类型得到列表（type=1为得到保密工作小组，type=2为得到保密委）
	 * </p>
	 * <p>
	 * 刘椿成 2012-12-18 15:30
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
	public List<PersonGroupPosition> getByGroupType(SecrecyWorkOrgan secrecyWorkOrgan);
}