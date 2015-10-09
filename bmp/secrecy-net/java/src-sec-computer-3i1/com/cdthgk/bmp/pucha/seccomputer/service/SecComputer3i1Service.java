
package com.cdthgk.bmp.pucha.seccomputer.service;

import java.util.List;

import com.cdthgk.bmp.pucha.seccomputer.domain.SecComputer3i1;
import com.cdthgk.platform.base.service.GenericService;

/**
 * <p>
 * SecComputer3i1Service
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 *
 * @author 钟冀
 */
public interface SecComputer3i1Service extends GenericService<SecComputer3i1, String>{

	/**
	 * <p>
	 * 根据年份和单位获取列表
	 * </p>
	 * @param year
	 * @param organId
	 * @return 列表
	 */
	List<SecComputer3i1> getList(int year, String organId);
	/**
	 * <p>
	 * 保存指定年份指定单位的SecComputer3i1集合
	 * </p>
	 * @param secComputer3i1List secComputer3i1List
	 */
	void save(List<SecComputer3i1> secComputer3i1List, Integer year, String organId);
}
