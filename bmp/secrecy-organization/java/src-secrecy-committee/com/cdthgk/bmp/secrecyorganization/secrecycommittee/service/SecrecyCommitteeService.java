package com.cdthgk.bmp.secrecyorganization.secrecycommittee.service;

import com.cdthgk.bmp.core.service.BmpService;
import com.cdthgk.bmp.secrecyorganization.secrecycommittee.vo.SecrecyCommittee;
import com.cdthgk.platform.organization.organ.domain.Organ;

/**
 * <p>
 * 保密委员会 Service 抽象类(对外接口)
 * </p>
 * <p>
 * 汪 钟 2012-12-14 10:43:03
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
public interface SecrecyCommitteeService extends BmpService<SecrecyCommittee, String> {

	/**
	 * <p>
	 * 根据当前登录用户所在单位，判断是否存在保密委
	 * </p>
	 * <p>
	 * 陶汇源  2012-12-24 18:13:03
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 *
	 * @author tianyu
	 * @since 1.0
	 * @version 1.0
	 */
	public SecrecyCommittee checkContainsSecrecyCommittee(Organ organ);

	/**
	 * <p>
	 * 根据单位返回所在的保密委
	 * </p>
	 * <p>
	 * 陶汇源  2012-12-25 16:13:03
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 *
	 * @author tianyu
	 * @since 1.0
	 * @version 1.0
	 */
	public SecrecyCommittee getSecrecyCommitteeByOrgan(Organ organ);

}