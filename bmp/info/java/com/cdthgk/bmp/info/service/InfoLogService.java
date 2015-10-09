/**
 *
 */
package com.cdthgk.bmp.info.service;

import java.util.List;

import com.cdthgk.bmp.info.vo.InfoLog;
import com.cdthgk.platform.base.service.GenericBasicService;

/**
*
* @description 信息上报日志接口
* @author YuJ
* @date 2015-01-20
* @since 1.0
* @version 1.0
*
*/
public interface InfoLogService extends GenericBasicService<InfoLog, String> {
	/**
	 * 根据infoId获取该信息的全部操作日志
	 * @param infoId
	 * @return List<InfoLog>
	 */
	List<InfoLog> findByInfoId(String infoId);
}
