
package com.cdthgk.bmp.pucha.nosecnet.service;

import java.util.List;

import com.cdthgk.bmp.pucha.nosecnet.domain.NoSecNet;
import com.cdthgk.bmp.pucha.nosecnet.domain.NoSecNetIntranet;
import com.cdthgk.platform.base.service.GenericGetService;

/**
 * <p>
 * NoSecNetServiceImpl
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 *
 * @author 钟冀
 */
public interface NoSecNetService extends GenericGetService<NoSecNet, String> {
	/**
	 * <p>
	 * 根据年份和单位获取
	 * </p>
	 * @param year
	 * @param organId
	 * @return
	 */
	NoSecNet get(int year, String organId);
	/**
	 * <p>
	 * 保存
	 * </p>
	 * @param noSecNet noSecNet
	 * @return noSecNet
	 */
	NoSecNet save(NoSecNet noSecNet);
	/**
	 * <p>
	 * 更新
	 * </p>
	 * @param noSecNet noSecNet
	 * @param noSecNetIntranetList noSecNetIntranetList
	 * @return noSecNet
	 */
	NoSecNet update(NoSecNet noSecNet, List<NoSecNetIntranet> noSecNetIntranetList);
}
