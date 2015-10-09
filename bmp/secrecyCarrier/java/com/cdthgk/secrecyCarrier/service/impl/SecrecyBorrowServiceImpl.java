package com.cdthgk.secrecyCarrier.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.mapping.Array;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.common.lang.StringUtils;
import com.cdthgk.platform.permission.user.domain.User;
import com.cdthgk.secrecyCarrier.service.SecrecyBorrowService;
import com.cdthgk.secrecyCarrier.vo.SecrecyBorrow;

import ec.common.PageSortModel;
@SuppressWarnings("all")
public class SecrecyBorrowServiceImpl extends BmpServiceImpl<SecrecyBorrow, String> implements
SecrecyBorrowService {


	public List getAllSecrecyBorrow() {
		return this.getPersistProxy().getOrmPersistence()
				.findAll(SecrecyBorrow.class);
	}
	public List getList(PageSortModel psm, SecrecyBorrow SecrecyBorrow) {
		String hql = "FROM SecrecyBorrow m WHERE 1=1 ";
		Map<String, Object> params = new HashMap<String, Object>();
		if (SecrecyBorrow != null && !"".equals(SecrecyBorrow.getName())) {
			hql += " and m.name like :name";
			params.put("name", "%" + SecrecyBorrow.getName() + "%");
		}
		return findList(hql.toString(), params, psm);
	}

	public List<SecrecyBorrow> getPageList(PageSortModel psm, SecrecyBorrow SecrecyBorrow, Map<String, Object> params,User user) {
		String hql = "FROM SecrecyBorrow m WHERE 1=1 and m.organId.organId=:organId";
		params.put("organId", user.getOrgan().getOrganId());
		hql += " and m.state=0";
		if (SecrecyBorrow != null) {
                        if (StringUtils.isNotEmpty(SecrecyBorrow.getName())) {
                                hql += " and m.name like :name";
                                params.put("name", "%" + SecrecyBorrow.getName().trim() + "%");
                        }
                        if (null!=SecrecyBorrow.getBorrowUserInfo()&&StringUtils.isNotEmpty(SecrecyBorrow.getBorrowUserInfo().getName())) {
                                hql += " and m.borrowUserInfo.name like :author";
                                params.put("author", "%" + SecrecyBorrow.getBorrowUserInfo().getName().trim() + "%");
                        }
                }
		hql += " order by m.date desc";
		List<SecrecyBorrow> returnList = new ArrayList<SecrecyBorrow>();
		if( psm!=null ){
		        returnList = (List<SecrecyBorrow>)findList(hql.toString(), params, psm);
		}else{
		        returnList = (List<SecrecyBorrow>)findList(hql.toString(), params);
		}
		return returnList;
	}


        @Override
        public List<SecrecyBorrow> listForSelect(PageSortModel<SecrecyBorrow> psm, SecrecyBorrow SecrecyBorrow, String districtCode,
                        String includeChild) {
                Map<String, Object> params = new HashMap<String, Object>();
                String hql = "FROM SecrecyBorrow m WHERE 1=1 ";

                hql += " and m.state=0";
                if (SecrecyBorrow != null) {
                        if (StringUtils.isNotEmpty(SecrecyBorrow.getName())) {
                                hql += " and m.name like :name";
                                params.put("name", "%" + SecrecyBorrow.getName().trim() + "%");
                        }
                        if (null!=SecrecyBorrow.getBorrowUserInfo()&&StringUtils.isNotEmpty(SecrecyBorrow.getBorrowUserInfo().getName())) {
                                hql += " and m.borrowUserInfo.name like :author";
                                params.put("author", "%" + SecrecyBorrow.getBorrowUserInfo().getName().trim() + "%");
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
