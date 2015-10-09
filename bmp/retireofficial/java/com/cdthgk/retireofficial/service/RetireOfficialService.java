/**
 *
 */
package com.cdthgk.retireofficial.service;

import java.util.Date;
import java.util.List;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.platform.permission.user.domain.User;
import com.cdthgk.retireofficial.vo.RetireOfficial;

import ec.common.PageSortModel;
@SuppressWarnings("all")
/**
 * @description 离退休Service.
 * @author 王欢 2009 10 21 12:34:56
 * @modify 陈文聪 2010 8 18 02:58:17 修改注释格式
 */

public interface RetireOfficialService extends BmpServiceTemplate<RetireOfficial, String>{

	/**
	 *
	 * @author 王欢 2009 10 21 12:34:56
	 * @modify 陈文聪 2010 8 18 03:02:23 修改注释格式.
	 * @param PageSortModel psm
	 * @param RetireOfficial retireOfficial
	 * @return List<RetireOfficial>
	 */
	public List<RetireOfficial> listForEc(PageSortModel psm, RetireOfficial retireOfficial, Date startTime, Date endTime,User user);
}
