package com.cdthgk.bmp.formatFile.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.formatFile.service.FormatFileService;
import com.cdthgk.bmp.formatFile.vo.FormatFile;
import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.common.lang.StringUtils;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;


public class FormatFileServiceImpl extends BmpServiceImpl<FormatFile, String> implements FormatFileService {

	@Override
	public List<FormatFile> queryListPage(PageSortModel<FormatFile> psm, FormatFile formatFile, Organ organ) {
		StringBuffer hql = new StringBuffer("from FormatFile  where  organ.organId = :organId");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("organId", organ.getOrganId());
		if(formatFile!=null){
			if(StringUtils.isNotEmpty(formatFile.getName())){
				hql.append(" and name like :sname");
				params.put("sname", "%" + formatFile.getName() + "%");
			}
		}
		return findList(hql.toString(), params, psm);
	}
}
