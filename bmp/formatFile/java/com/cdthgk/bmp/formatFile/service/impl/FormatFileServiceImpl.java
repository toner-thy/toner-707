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

	@Override
	public List<FormatFile> queryAcceptListPage(PageSortModel<FormatFile> psm,
			FormatFile formatFile, String userInfoId) {
		StringBuffer hql = new StringBuffer("select f from FormatFile f left join f.userInfoSet u where u.userInfo.userInfoId = :userInfoId");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userInfoId", userInfoId);
		//select * from bip_format_file f left join bip_format_file_userinfo u on f.id=u.formatFile_id where u.userInfo_id='5'
		if(formatFile!=null){
			if(StringUtils.isNotEmpty(formatFile.getName())){
				hql.append(" and f.name like :sname");
				params.put("sname", "%" + formatFile.getName() + "%");
			}
		}
		return findList(hql.toString(), params, psm);
	}
}
