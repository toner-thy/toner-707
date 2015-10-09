package com.cdthgk.bmp.secrecyorganization.secrecyoffice.service;

import com.cdthgk.bmp.core.service.BmpService;
import com.cdthgk.bmp.secrecyorganization.secrecycommittee.vo.SecrecyCommittee;
import com.cdthgk.bmp.secrecyorganization.secrecyoffice.vo.SecrecyOffice;
import com.cdthgk.platform.organization.organ.domain.Organ;

/**
 * <p>
 * 保密办（保密局） Service 抽象类(对外接口)
 * </p>
 * <p>
 * 陶汇源  2012-12-14 10:43:03
 * </p>
 * <blockquote>
 * <h4>历史修改记录</h4>
 * <ul>
 * <li>修改人 修改时间 修改描述
 * </ul>
 * </blockquote>
 * <p>
 * copyright tianyu 2012, all rights reserved.
 * </p>
 *
 * @author FastCodeingTools
 * @author tianyu
 * @since 1.0
 * @version 1.0
 */
public interface SecrecyOfficeService extends BmpService<SecrecyOffice, String> {

	/**
	 * <p>
	 * 根据当前登录用户所在单位获取保密办（保密局）
	 * </p>
	 * <p>
	 * 陶汇源   2012-12-29 14:50:51
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * <p>
	 * copyright tianyu 2012, all rights reserved.
	 * </p>
	 *
	 * @author FastCodeingTools
	 * @author tianyu
	 * @since 1.0
	 * @version 1.0
	 */
	SecrecyOffice getSecrecyOfficeByOrgan(Organ organ);

	/**
	 *
	 * <p>
	 * 根据保密委查询保密办
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-4-2 - 下午5:47:23
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param secrecyCommittee
	 * @return
	 */
	public SecrecyOffice getSecrecyOfficeBySecrecyCommittee(SecrecyCommittee secrecyCommittee);

	/**
	 *
	 * <p>
	 * 初始化人员编制构成情况
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-7-13 - 下午5:37:49
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param secrecyOffice
	 * @return
	 */
	public SecrecyOffice initEstablishSituation(SecrecyOffice secrecyOffice);
}