/**
 *
 */
package com.cdthgk.bmp.info.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.info.service.InfoLogService;
import com.cdthgk.bmp.info.vo.InfoLog;
import com.cdthgk.common.lang.StringUtils;
import com.cdthgk.platform.base.service.GenericServiceTemplate;

/**
 * @author Administrator
 *
 */
public class InfoLogServiceImpl extends GenericServiceTemplate<InfoLog, String> implements
		InfoLogService {


	public List<InfoLog> findByInfoId(String infoId) {
		StringBuilder hql = new StringBuilder("FROM InfoLog il ");
		if(StringUtils.isNotBlank(infoId)){
			Map<String, Object> params = new HashMap<String, Object>();
			hql.append("WHERE il.info.infoId = :infoId");
			params.put("infoId", infoId);
			return this.findList(hql.toString(), params);
		} else
			return null;
	}

}
