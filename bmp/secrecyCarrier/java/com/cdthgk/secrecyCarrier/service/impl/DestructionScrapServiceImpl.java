package com.cdthgk.secrecyCarrier.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.common.lang.StringUtils;
import com.cdthgk.platform.permission.user.domain.User;
import com.cdthgk.secrecyCarrier.service.DestructionScrapService;
import com.cdthgk.secrecyCarrier.vo.DestructionScrap;

import ec.common.PageSortModel;
@SuppressWarnings("all")
public class DestructionScrapServiceImpl extends BmpServiceImpl<DestructionScrap, String> implements
DestructionScrapService {


	public List getAllDestructionScrap() {
		return this.getPersistProxy().getOrmPersistence()
				.findAll(DestructionScrap.class);
	}
	public List getList(PageSortModel psm, DestructionScrap destructionScrap) {
		String hql = "FROM DestructionScrap m WHERE 1=1 ";
		Map<String, Object> params = new HashMap<String, Object>();
		/*if (DestructionScrap != null && !"".equals(DestructionScrap.getName())) {
			hql += " and m.name like :name";
			params.put("name", "%" + DestructionScrap.getName() + "%");
		}*/
		return findList(hql.toString(), params, psm);
	}

	public List<DestructionScrap> getPageList(PageSortModel psm, DestructionScrap destructionScrap, Map<String, Object> params,User user) {
		String hql = "FROM DestructionScrap m WHERE 1=1 and m.organId.organId=:organId";
		params.put("organId", user.getOrgan().getOrganId());
		hql += " and m.state=0";
		 if (destructionScrap != null) {
	                        if (destructionScrap.getType()!=null) {
	                                hql += " and m.type =:type";
	                                params.put("type",destructionScrap.getType());
	                        }
	                        if (destructionScrap.getSecrecyLevel()!=null) {
	                                hql += " and m.secrecyLevel =:secrecyLevel";
	                                params.put("secrecyLevel",destructionScrap.getSecrecyLevel());
	                        }

	                }
		hql += " order by m.createTime desc";

		List<DestructionScrap> returnList = new ArrayList<DestructionScrap>();
		if( psm!=null ){
		        returnList =  (List<DestructionScrap>)findList(hql.toString(), params, psm);
		}else{
		        returnList = (List<DestructionScrap>)findList(hql.toString(), params);
		}
		return returnList;
	}


        @Override
        public List<DestructionScrap> listForSelect(PageSortModel<DestructionScrap> psm, DestructionScrap destructionScrap, String districtCode,
                        String includeChild) {
                Map<String, Object> params = new HashMap<String, Object>();
                String hql = "FROM DestructionScrap m WHERE 1=1 ";

                hql += " and m.state=0";
                       if (destructionScrap != null) {
                                if (destructionScrap.getType()!=null) {
                                        hql += " and m.type =:type";
                                        params.put("type",destructionScrap.getType());
                                }
                                if (destructionScrap.getSecrecyLevel()!=null) {
                                        hql += " and m.secrecyLevel =:secrecyLevel";
                                        params.put("secrecyLevel",destructionScrap.getSecrecyLevel());
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
