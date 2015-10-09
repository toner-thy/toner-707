package com.cdthgk.secrecyCarrier.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.common.lang.StringUtils;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;
import com.cdthgk.platform.permission.user.domain.User;
import com.cdthgk.secrecyCarrier.service.SecrecyPrintService;
import com.cdthgk.secrecyCarrier.vo.SecrecyPrint;

import ec.common.PageSortModel;
@SuppressWarnings("all")
public class SecrecyPrintServiceImpl extends BmpServiceImpl<SecrecyPrint, String> implements
SecrecyPrintService {


	public List getAllSecrecyPrint() {
		return this.getPersistProxy().getOrmPersistence()
				.findAll(SecrecyPrint.class);
	}
	public List getList(PageSortModel psm, SecrecyPrint SecrecyPrint) {
		String hql = "FROM SecrecyPrint m WHERE 1=1 ";
		Map<String, Object> params = new HashMap<String, Object>();
		if (SecrecyPrint != null && !"".equals(SecrecyPrint.getName())) {
			hql += " and m.name like :name";
			params.put("name", "%" + SecrecyPrint.getName() + "%");
		}
		return findList(hql.toString(), params, psm);
	}

	public List<SecrecyPrint> getPageList(PageSortModel psm, SecrecyPrint SecrecyPrint, Map<String, Object> params,User user) {
		String hql = "FROM SecrecyPrint m WHERE 1=1 and m.organId.organId=:organId";
		params.put("organId", user.getOrgan().getOrganId());
		hql += " and m.state=0";
		if (SecrecyPrint != null) {
                        if (StringUtils.isNotEmpty(SecrecyPrint.getName())) {
                                hql += " and m.name like :name";
                                params.put("name", "%" + SecrecyPrint.getName().trim() + "%");
                        }
                       if (null!=SecrecyPrint.getDate()) {
                               hql += " and  m.date =:dateEnd";
                               params.put("dateEnd", SecrecyPrint.getDate());
                       }
                }
		hql += " order by m.date desc";
		List<SecrecyPrint> returnList = new ArrayList<SecrecyPrint>();
		if(psm!=null){
		        returnList = (List<SecrecyPrint>)findList(hql.toString(), params, psm);
		}else{
		        returnList = (List<SecrecyPrint>)findList(hql.toString(), params);
		}
		return returnList;
	}

	public String userInfoIds2Names(String userInfoIds){
	        StringBuilder returnNames = new StringBuilder();
	        if( userInfoIds!=null && !"".equals(userInfoIds) ){
	                int count = 0;
	                for( String id : userInfoIds.split(",") ){
	                        count++;
	                        UserInfo ui = this.get(id.trim(), UserInfo.class);
	                        if( ui!=null ){
	                                returnNames.append(ui.getName());
	                                if( count < userInfoIds.split(",").length ){
	                                        returnNames.append(",");
	                                }
	                        }
	                }
	        }
	        return returnNames.toString();
	}


        @Override
        public List<SecrecyPrint> listForSelect(PageSortModel<SecrecyPrint> psm, SecrecyPrint SecrecyPrint, String districtCode,
                        String includeChild) {
                Map<String, Object> params = new HashMap<String, Object>();
                String hql = "FROM SecrecyPrint m WHERE 1=1 ";

                hql += " and m.state=0";
                if (SecrecyPrint != null) {
                        if (StringUtils.isNotEmpty(SecrecyPrint.getName())) {
                                hql += " and m.name like :name";
                                params.put("name", "%" + SecrecyPrint.getName().trim() + "%");
                        }
                       if (null!=SecrecyPrint.getDate()) {
                               hql += " and DATE_FORMAT( m.date,'%Y-%m-%d') =:dateEnd";
                               SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                               String meetingTime = sdf.format(SecrecyPrint.getDate());
                               params.put("dateEnd", meetingTime);
                       }
                }
                if (includeChild.equals("0")) {
                        //hql语句--保密局
                        int layer=this.getPersistProxy().getJdbcPersistence().findForInt(
                                        "select layer from sys_district where district_code ='"+districtCode+"'", params);
                        hql +=" and m.organId.organId in (";
                         hql +="select o.organId from Organ as o where o.district.districtCode in ";
                         hql +="(select district.districtCode from District as district where district.layer like :layer ))";
                        params.put("layer",  layer+"%");
                }
                if (includeChild.equals("1")) {
                        //hql语句--直辖单位
                         hql +=" and m.organId.organId in (";
                         hql +="select o.organId from Organ as o where o.district.districtCode=:districtCode)";
                        params.put("districtCode",  districtCode);
                }
                hql += " order by m.date desc";
                return findList(hql.toString(), params, psm);
        }


}
