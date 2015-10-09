
package com.cdthgk.bmp.pucha.tectool.service;

import com.cdthgk.bmp.pucha.tectool.domain.TecToolInfo;
import com.cdthgk.platform.base.service.GenericGetService;

/**
 * <p>
 * TecToolInfoServiceImpl
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 *
 * @author 钟冀
 */
public interface TecToolInfoService extends GenericGetService<TecToolInfo, String> {
	/**
	 * <p>
	 * 根据年份和单位获取
	 * </p>
	 * @param year
	 * @param organId
	 * @return
	 */
	TecToolInfo get(int year, String organId);
	/**
	 * <p>
	 * 保存
	 * </p>
	 * @param noSecNet noSecNet
	 * @return noSecNet
	 */
	TecToolInfo save(TecToolInfo noSecNet);
	/**
	 * <p>
	 * 更新
	 * </p>
	 * @param noSecNet noSecNet
	 * @param noSecNetIntranetList noSecNetIntranetList
	 * @return noSecNet
	 */
	TecToolInfo update(TecToolInfo noSecNet);
}
