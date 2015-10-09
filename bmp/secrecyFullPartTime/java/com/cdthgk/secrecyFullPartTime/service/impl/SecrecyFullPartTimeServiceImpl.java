package com.cdthgk.secrecyFullPartTime.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.common.lang.StringUtils;
import com.cdthgk.platform.permission.user.domain.User;
import com.cdthgk.secrecyFullPartTime.service.SecrecyFullPartTimeService;
import com.cdthgk.secrecyFullPartTime.vo.SecrecyFullPartTime;

import ec.common.PageSortModel;
@SuppressWarnings("all")
public class SecrecyFullPartTimeServiceImpl extends BmpServiceImpl<SecrecyFullPartTime, String> implements
SecrecyFullPartTimeService {


	public List getAllSecrecyFullPartTime() {
		return this.getPersistProxy().getOrmPersistence()
				.findAll(SecrecyFullPartTime.class);
	}
	public List getList(PageSortModel psm, SecrecyFullPartTime SecrecyFullPartTime) {
		String hql = "FROM SecrecyFullPartTime m WHERE 1=1 ";
		Map<String, Object> params = new HashMap<String, Object>();
		if (SecrecyFullPartTime != null && !"".equals(SecrecyFullPartTime.getName())) {
			hql += " and m.name like :name";
			params.put("name", "%" + SecrecyFullPartTime.getName() + "%");
		}
		return findList(hql.toString(), params, psm);
	}

	public List<SecrecyFullPartTime> getPageList(PageSortModel psm, SecrecyFullPartTime SecrecyFullPartTime, Map<String, Object> params,User user) {
		String hql = "FROM SecrecyFullPartTime m WHERE 1=1 and m.organId.organId=:organId";
		params.put("organId", user.getOrgan().getOrganId());
		hql += " and m.state=0";
		if (SecrecyFullPartTime != null) {
                        if (null!=SecrecyFullPartTime.getName()&&StringUtils.isNotEmpty(SecrecyFullPartTime.getName().getName())) {
                                hql += " and m.name.name like :name";
                                params.put("name", "%" + SecrecyFullPartTime.getName().getName().trim() + "%");
                        }
                        if (StringUtils.isNotEmpty(SecrecyFullPartTime.getPosition())) {
                                hql += " and m.position like :position";
                                params.put("position", "%" + SecrecyFullPartTime.getPosition().trim() + "%");
                        }
                }
		hql += " order by m.createTime desc";

		List<SecrecyFullPartTime> returnList = new ArrayList<SecrecyFullPartTime>();
		if( psm!=null ){
		        returnList =  (List<SecrecyFullPartTime>)findList(hql.toString(), params, psm);
		}else{
		        returnList = (List<SecrecyFullPartTime>)findList(hql.toString(), params);
		}
		return returnList;
	}


        @Override
        public List<SecrecyFullPartTime> listForSelect(PageSortModel<SecrecyFullPartTime> psm, SecrecyFullPartTime SecrecyFullPartTime, String districtCode,
                        String includeChild) {
                Map<String, Object> params = new HashMap<String, Object>();
                String hql = "FROM SecrecyFullPartTime m WHERE 1=1 ";

                hql += " and m.state=0";
                if (SecrecyFullPartTime != null) {
                        if (null!=SecrecyFullPartTime.getName()&&StringUtils.isNotEmpty(SecrecyFullPartTime.getName().getName())) {
                                hql += " and m.name.name like :name";
                                params.put("name", "%" + SecrecyFullPartTime.getName().getName().trim() + "%");
                        }
                        if (StringUtils.isNotEmpty(SecrecyFullPartTime.getPosition())) {
                                hql += " and m.position like :position";
                                params.put("position", "%" + SecrecyFullPartTime.getPosition().trim() + "%");
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
                hql += " order by m.createTime desc";
                return findList(hql.toString(), params, psm);
        }


}
