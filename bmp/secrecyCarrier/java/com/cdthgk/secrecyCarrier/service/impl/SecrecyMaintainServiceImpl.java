package com.cdthgk.secrecyCarrier.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.common.lang.StringUtils;
import com.cdthgk.platform.permission.user.domain.User;
import com.cdthgk.secrecyCarrier.service.SecrecyMaintainService;
import com.cdthgk.secrecyCarrier.vo.SecrecyMaintain;

import ec.common.PageSortModel;
@SuppressWarnings("all")
public class SecrecyMaintainServiceImpl extends BmpServiceImpl<SecrecyMaintain, String> implements
SecrecyMaintainService {


	public List getAllSecrecyMaintain() {
		return this.getPersistProxy().getOrmPersistence()
				.findAll(SecrecyMaintain.class);
	}
	public List getList(PageSortModel psm, SecrecyMaintain SecrecyMaintain) {
		String hql = "FROM SecrecyMaintain m WHERE 1=1 ";
		Map<String, Object> params = new HashMap<String, Object>();
		/*if (SecrecyMaintain != null && !"".equals(SecrecyMaintain.getName())) {
			hql += " and m.name like :name";
			params.put("name", "%" + SecrecyMaintain.getName() + "%");
		}*/
		return findList(hql.toString(), params, psm);
	}

	public List<SecrecyMaintain> getPageList(PageSortModel psm, SecrecyMaintain SecrecyMaintain, Map<String, Object> params,User user) {
		String hql = "FROM SecrecyMaintain m WHERE 1=1 and m.organId.organId=:organId";
		params.put("organId", user.getOrgan().getOrganId());
		hql += " and m.state=0";
		if (SecrecyMaintain != null) {
                        if (StringUtils.isNotEmpty(SecrecyMaintain.getType())) {
                                hql += " and m.type like :type";
                                params.put("type", "%" + SecrecyMaintain.getType().trim() + "%");
                        }
                        if (SecrecyMaintain.getSecrecyLevel()!=null) {
                                hql += " and m.secrecyLevel = :secrecyLevel";
                                params.put("secrecyLevel", SecrecyMaintain.getSecrecyLevel() );
                        }
                }
		hql += " order by m.date desc";
		List<SecrecyMaintain> returnList = new ArrayList<SecrecyMaintain>();
		if( psm!=null ){
		        returnList = (List<SecrecyMaintain>)findList(hql.toString(), params, psm);
		}else{
		        returnList = (List<SecrecyMaintain>)findList(hql.toString(), params);
		}
		return returnList;
	}


        @Override
        public List<SecrecyMaintain> listForSelect(PageSortModel<SecrecyMaintain> psm, SecrecyMaintain SecrecyMaintain, String districtCode,
                        String includeChild) {
                Map<String, Object> params = new HashMap<String, Object>();
                String hql = "FROM SecrecyMaintain m WHERE 1=1 ";

                hql += " and m.state=0";
                if (SecrecyMaintain != null) {
                        if (StringUtils.isNotEmpty(SecrecyMaintain.getType())) {
                                hql += " and m.type like :type";
                                params.put("type", "%" + SecrecyMaintain.getType().trim() + "%");
                        }
                        if (SecrecyMaintain.getSecrecyLevel()!=null) {
                                hql += " and m.secrecyLevel = :secrecyLevel";
                                params.put("secrecyLevel", SecrecyMaintain.getSecrecyLevel() );
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
