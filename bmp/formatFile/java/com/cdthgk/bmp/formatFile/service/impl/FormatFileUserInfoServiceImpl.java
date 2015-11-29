package com.cdthgk.bmp.formatFile.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.bmp.formatFile.service.FormatFileUserInfoService;
import com.cdthgk.bmp.formatFile.vo.FormatFile;
import com.cdthgk.bmp.formatFile.vo.FormatFileUserInfo;
import com.cdthgk.common.lang.StringUtils;
import com.cdthgk.platform.organization.core.OrganizationContext;

import ec.common.PageSortModel;


public class FormatFileUserInfoServiceImpl extends BmpServiceImpl<FormatFileUserInfo, String> implements FormatFileUserInfoService {

	@Override
	public List<FormatFileUserInfo> queryAcceptListPage(PageSortModel<FormatFileUserInfo> psm,
			FormatFileUserInfo formatFileUserInfo, String userInfoId) {
		StringBuffer hql = new StringBuffer("select u from FormatFileUserInfo u where u.userInfo.userInfoId = :userInfoId");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userInfoId", userInfoId);
		if(formatFileUserInfo!=null){
			if(StringUtils.isNotEmpty(formatFileUserInfo.getFormatFileName())){
				hql.append(" and u.formatFileName like :sname");
				params.put("sname", "%" + formatFileUserInfo.getFormatFileName() + "%");
			}
		}
		return findList(hql.toString(), params, psm);
	}
	@Override
	public List<FormatFileUserInfo> queryFormatFileUserInfoById(
			String formatFileId) {
		String sql = new String("select u.* from bip_format_file_userinfo u where u.formatFile_id=?");
		List<Object[]> list = this.getPersistProxy().getOrmPersistence().findByNativeQuery(sql, new Object[]{formatFileId});
		List<FormatFileUserInfo> formatFileUserInfoList = new ArrayList<FormatFileUserInfo>();
		for (Object[] objects : list) {
			FormatFileUserInfo ffi = new FormatFileUserInfo();
			ffi.setId(objects[0].toString());
			FormatFile formatFile = new FormatFile();
			formatFile.setId(objects[1].toString());
			ffi.setFormatFile(formatFile);
			ffi.setFormatFileName(objects[2] == null ? null : objects[2].toString());
			ffi.setUserInfo(OrganizationContext.getInstance().getMemberService().get(objects[3].toString()));
			ffi.setStatus(Integer.parseInt(objects[4].toString()));
			Timestamp t = (Timestamp)objects[5];
			ffi.setViewTime(t == null ? null : new Date(t.getTime()));
			formatFileUserInfoList.add(ffi);
		}
		return formatFileUserInfoList;
	}

	@Override
	public void deleteBatchFormatFileUserInfo(List<FormatFileUserInfo> flist) {
		this.getPersistProxy().getOrmPersistence().deleteBatch(flist);
	}

}
