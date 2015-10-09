/**
 *
 */
package com.cdthgk.bmp.info.service.impl;

import java.util.List;

import com.cdthgk.bmp.info.service.InfoTypeService;
import com.cdthgk.bmp.info.vo.InfoType;
import com.cdthgk.platform.base.service.GenericServiceTemplate;

/**
 * @author Administrator
 *
 */
public class InfoTypeServiceImpl extends GenericServiceTemplate<InfoType, String>
		implements InfoTypeService {

	/* (non-Javadoc)
	 * @see com.cdthgk.bmp.info.service.InfoTypeService#getInfoTypeList()
	 */
	public List<InfoType> findAll() {
		StringBuilder hql = new StringBuilder("FROM InfoType it");
		return this.findList(hql.toString(), null);
	}

}
