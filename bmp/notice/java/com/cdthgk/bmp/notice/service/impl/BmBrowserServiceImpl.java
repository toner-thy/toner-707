package com.cdthgk.bmp.notice.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.notice.service.BmBrowserService;
import com.cdthgk.bmp.notice.vo.BmBrowser;
import com.cdthgk.common.bean.BeanUtils;
import com.cdthgk.common.bean.rule.CopyRuleEnum;
import com.cdthgk.common.lang.CollectionUtils;
import com.cdthgk.common.lang.StringUtils;
import com.cdthgk.platform.base.service.GenericServiceTemplate;
import com.cdthgk.platform.district.domain.District;

import ec.common.PageSortModel;

public class BmBrowserServiceImpl extends GenericServiceTemplate<BmBrowser, String> implements
	BmBrowserService {

	@Override
	public void saveOrUpdateBrowser(BmBrowser browser) {
		// 保存浏览器、机关单位ID、登陆最后时间（一个单位只有一条记录）
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("organId", browser.getOrgan().getOrganId());
		List<BmBrowser> list = findList("From BmBrowser where organ.organId = :organId", params);
		if (CollectionUtils.isNotEmpty(list)) {
			BmBrowser dbBmBrowser = list.get(0);
			BeanUtils.copyProperties(dbBmBrowser, browser, CopyRuleEnum.ignoreCaseNull);
			update(dbBmBrowser);
		} else {
			save(browser);
		}

//		List<BmBrowser> list = this.findList("From BmBrowser", null);
//		boolean isSame = false;
//		String id = "";
//		for (BmBrowser bmBrowser : list) {
//			if (bmBrowser.getRemoteAddress().trim().equals(browser.getRemoteAddress().trim())) {
//				isSame = true;
//				id = bmBrowser.getId();
//				break;
//			} else {
//				isSame = false;
//			}
//		}
//		if (isSame) {
//			BmBrowser b = new BmBrowser();
//			b.setId(id);
//			b.setRemoteAddress(browser.getRemoteAddress());
//			b.setUserAgent(browser.getUserAgent());
//			getPersistProxy().getOrmPersistence().evict(browser);
//			getPersistProxy().getOrmPersistence().getHibernateTemplate().clear();
//			update(b);
//		} else {
//			save(browser);
//		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<BmBrowser> queryBMSUseStatePage(PageSortModel<BmBrowser> psm, District district, BmBrowser bmBrowser, String checkLower) {
		List<BmBrowser> bmBrowserList = new ArrayList<BmBrowser>();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder sql = new StringBuilder("select b.user_agent" +
				", if(b.remote_address is null,'',b.remote_address)" +
				", b.last_login_time" +
				", o.organ_id,o.organ_name from bm_browser b RIGHT join sys_organization o on b.organ_id=o.ORGAN_ID");
		//ADMIN 查看所有
		if(district.getParent() != null){
			sql.append(" left join sys_district d on o.DISTRICT_CODE=d.DISTRICT_CODE where 1=1 ");
			// 是否查看下级
			if(checkLower != null && "1".equals(checkLower) ){
				sql.append(" and d.layer like :layer");
				params.put("layer", district.getLayer()+"%");
			}else{
				sql.append(" and o.DISTRICT_CODE = :districtCode ");
				params.put("districtCode", district.getDistrictCode());
			}
			// 查询
			if( bmBrowser!=null ){
        			if (bmBrowser.getOrgan() != null && StringUtils.isNotEmpty(bmBrowser.getOrgan().getOrganName())) {
        				sql.append(" and o.ORGAN_NAME like :organName ");
        				params.put("organName", "%" + bmBrowser.getOrgan().getOrganName() + "%");
        			}
        			if( bmBrowser.getState()!=null ){
        			        if( bmBrowser.getState().equals(1) ){
        			                sql.append(" and b.last_login_time is not null ");
        			        }else{
        			                sql.append(" and b.last_login_time is null ");
        			        }
        			        if( bmBrowser.getLastLoginTime()!=null ){
        			                sql.append(" and b.last_login_time >= :lastLoginTime ");
        			                params.put("lastLoginTime", bmBrowser.getLastLoginTime());
        			        }
        			}
			}
			sql.append("  and o.data_state = 1 and o.organ_type = 2 order by b.last_login_time desc");
		} else {
			sql.append("  where o.data_state = 1 and o.organ_type = 2");
			// 查询
			if( bmBrowser!=null ){
        			if (bmBrowser.getOrgan() != null && StringUtils.isNotEmpty(bmBrowser.getOrgan().getOrganName())) {
        				sql.append(" and o.ORGAN_NAME like :organName ");
        				params.put("organName", "%" + bmBrowser.getOrgan().getOrganName() + "%");
        			}
                                if( bmBrowser.getState()!=null ){
                                        if( bmBrowser.getState().equals(1) ){
                                                sql.append(" and b.last_login_time is not null ");
                                        }else{
                                                sql.append(" and b.last_login_time is null ");
                                        }
                                        if( bmBrowser.getLastLoginTime()!=null ){
                                                sql.append(" and b.last_login_time >= :lastLoginTime ");
                                                params.put("lastLoginTime", bmBrowser.getLastLoginTime());
                                        }
                                }
			}
			sql.append(" order by b.last_login_time desc");
		}

		List<Object[]> list = getPersistProxy().getOrmPersistence().findByNativeQuery(sql.toString(), params);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (Object[] obj : list) {
			BmBrowser browser = new BmBrowser();
			if(obj[0] != null){
				browser.setUserAgent(judgeBrowser(obj[0].toString()));
			} else {
				browser.setUserAgent("");
			}
			browser.setRemoteAddress(obj[1].toString());
			try {
				if (obj[2] != null) {
					browser.setLastLoginTime(sdf.parse(obj[2].toString()));
					browser.setState(BmBrowser.STATE_LOGIN);
				} else {
					browser.setLastLoginTime(null);
					browser.setState(BmBrowser.STATE_NO_LOGIN);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			browser.setOrganName(obj[4].toString());
			bmBrowserList.add(browser);
		}
		psm.setTotalRows(bmBrowserList.size());
		return bmBrowserList;
	}

	private String judgeBrowser(String userAgent){
		String userBrowser = "";
		String str = userAgent.toLowerCase();
		if(str.contains("firefox")){
			userBrowser="firefox";
		} else if(str.contains("360se")){
			userBrowser="360se";
		} else if(str.contains("msie")){
			 if(str.indexOf("msie 6.0")>0){
				 userBrowser="IE 6.0";
			 }
			 if(str.indexOf("msie 7.0")>0){
				 userBrowser="IE 7.0";
			 }
		     if(str.indexOf("msie 8.0")>0){
				 userBrowser="IE 8.0";
			 }
		     if(str.indexOf("msie 9.0")>0){
				 userBrowser="IE 9.0";
			 }
		     if(str.indexOf("msie 10.0")>0){
		    	 userBrowser="IE 10.0";
		     }
		} else if(str.contains("chrome")){
			userBrowser="chrome";
		} else if(str.contains("opera")){
			userBrowser="opera";
		}
		return userBrowser;
	}

}
