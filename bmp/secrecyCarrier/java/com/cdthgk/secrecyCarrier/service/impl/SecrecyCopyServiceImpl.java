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
import com.cdthgk.secrecyCarrier.service.SecrecyCopyService;
import com.cdthgk.secrecyCarrier.vo.SecrecyCopy;

import ec.common.PageSortModel;
@SuppressWarnings("all")
public class SecrecyCopyServiceImpl extends BmpServiceImpl<SecrecyCopy, String> implements
SecrecyCopyService {


	public List getAllSecrecyCopy() {
		return this.getPersistProxy().getOrmPersistence()
				.findAll(SecrecyCopy.class);
	}
	public List getList(PageSortModel psm, SecrecyCopy SecrecyCopy) {
		String hql = "FROM SecrecyCopy m WHERE 1=1 ";
		Map<String, Object> params = new HashMap<String, Object>();
		if (SecrecyCopy != null && !"".equals(SecrecyCopy.getName())) {
			hql += " and m.name like :name";
			params.put("name", "%" + SecrecyCopy.getName() + "%");
		}
		return findList(hql.toString(), params, psm);
	}

	public List<SecrecyCopy> getPageList(PageSortModel psm, SecrecyCopy SecrecyCopy, Map<String, Object> params,User user) {
		String hql = "FROM SecrecyCopy m WHERE 1=1 and m.organId.organId=:organId";
		params.put("organId", user.getOrgan().getOrganId());
		hql += " and m.state=0";
		if (SecrecyCopy != null) {
                        if (StringUtils.isNotEmpty(SecrecyCopy.getName())) {
                                hql += " and m.name like :name";
                                params.put("name", "%" + SecrecyCopy.getName().trim() + "%");
                        }
                       if (null!=SecrecyCopy.getDate()) {
                               hql += " and DATE_FORMAT( m.date,'%Y-%m-%d') =:dateEnd";
                               SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                               String meetingTime = sdf.format(SecrecyCopy.getDate());
                               params.put("dateEnd", meetingTime);
                       }
                }
		hql += " order by m.date desc";
		List<SecrecyCopy> returnList = new ArrayList<SecrecyCopy>();
		if(psm!=null){
		        returnList = (List<SecrecyCopy>)findList(hql.toString(), params, psm);
		}else{
		        returnList = (List<SecrecyCopy>)findList(hql.toString(), params);
		}
		return returnList;
	}

        @Override
        public List<SecrecyCopy> listForSelect(PageSortModel<SecrecyCopy> psm, SecrecyCopy SecrecyCopy, String districtCode,
                        String includeChild) {
                Map<String, Object> params = new HashMap<String, Object>();
                String hql = "FROM SecrecyCopy m WHERE 1=1 ";

                hql += " and m.state=0";
                if (SecrecyCopy != null) {
                        if (StringUtils.isNotEmpty(SecrecyCopy.getName())) {
                                hql += " and m.name like :name";
                                params.put("name", "%" + SecrecyCopy.getName().trim() + "%");
                        }
                       if (null!=SecrecyCopy.getDate()) {
                               hql += " and DATE_FORMAT( m.date,'%Y-%m-%d') =:dateEnd";
                               SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                               String meetingTime = sdf.format(SecrecyCopy.getDate());
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
