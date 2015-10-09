/**
 *
 */
package com.cdthgk.bmp.info.service;

import java.util.List;

import com.cdthgk.bmp.info.vo.InfoType;
import com.cdthgk.platform.base.service.GenericBasicService;

/**
 *
 * @description 信息上报类型接口
 * @author YuJ
 * @date 2015-01-20
 * @since 1.0
 * @version 1.0
 *
 */
public interface InfoTypeService extends GenericBasicService<InfoType, String> {
	/**
	 *
	 * 获取全部信息类型
	 * @return List<InfoType>
	 */
	public List<InfoType> findAll();

}
