package com.cdthgk.setTheDecryption.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.common.lang.StringUtils;
import com.cdthgk.platform.permission.user.domain.User;
import com.cdthgk.setTheDecryption.service.SetTheDecryptionService;
import com.cdthgk.setTheDecryption.vo.SetTheDecryption;

import ec.common.PageSortModel;
@SuppressWarnings("all")
public class SetTheDecryptionServiceImpl extends BmpServiceImpl<SetTheDecryption, String> implements
SetTheDecryptionService {


	public List getAllSetTheDecryption() {
		return this.getPersistProxy().getOrmPersistence()
				.findAll(SetTheDecryption.class);
	}
	public List getList(PageSortModel psm, SetTheDecryption SetTheDecryption) {
		String hql = "FROM SetTheDecryption m WHERE 1=1 ";
		Map<String, Object> params = new HashMap<String, Object>();
		/*if (SetTheDecryption != null && !"".equals(SetTheDecryption.getName())) {
			hql += " and m.name like :name";
			params.put("name", "%" + SetTheDecryption.getName() + "%");
		}*/
		return findList(hql.toString(), params, psm);
	}

	public List<SetTheDecryption> getPageList(PageSortModel psm, SetTheDecryption SetTheDecryption, Map<String, Object> params,User user) {
		String hql = "FROM SetTheDecryption m WHERE 1=1 and m.organId.organId=:organId";
		params.put("organId", user.getOrgan().getOrganId());
		hql += " and m.state=0";
		if (SetTheDecryption != null) {
                        if (StringUtils.isNotEmpty(SetTheDecryption.getName())) {
                                hql += " and m.name like :name";
                                params.put("name", "%" + SetTheDecryption.getName().trim() + "%");
                        }
                        if (null!=SetTheDecryption.getDate()) {
                                hql += " and m.date =:date";
                                params.put("date", SetTheDecryption.getDate());
                        }
                        if (null!=SetTheDecryption.getSecrecyType()) {
                                hql += " and m.secrecyType =:secrecyType";
                                params.put("secrecyType", SetTheDecryption.getSecrecyType());
                        }
                }
		hql += " order by m.createTime desc";

		List<SetTheDecryption> returnList = new ArrayList<SetTheDecryption>();
		if( psm!=null ){
		        returnList =  (List<SetTheDecryption>)findList(hql.toString(), params, psm);
		}else{
		        returnList = (List<SetTheDecryption>)findList(hql.toString(), params);
		}
		return returnList;
	}


        @Override
        public List<SetTheDecryption> listForSelect(PageSortModel<SetTheDecryption> psm, SetTheDecryption SetTheDecryption, String districtCode,
                        String includeChild) {
                Map<String, Object> params = new HashMap<String, Object>();
                String hql = "FROM SetTheDecryption m WHERE 1=1 ";

                hql += " and m.state=0";
                if (SetTheDecryption != null) {
                        if (StringUtils.isNotEmpty(SetTheDecryption.getName())) {
                                hql += " and m.name like :name";
                                params.put("name", "%" + SetTheDecryption.getName().trim() + "%");
                        }
                        if (null!=SetTheDecryption.getDate()) {
                                hql += " and m.date =:date";
                                params.put("date", SetTheDecryption.getDate());
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
