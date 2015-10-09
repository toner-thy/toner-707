package com.cdthgk.secrecyCarrier.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.common.lang.StringUtils;
import com.cdthgk.platform.permission.user.domain.User;
import com.cdthgk.secrecyCarrier.service.SecrecyTechnologyPreventionService;
import com.cdthgk.secrecyCarrier.vo.SecrecyTechnologyPrevention;

import ec.common.PageSortModel;
@SuppressWarnings("all")
public class SecrecyTechnologyPreventionServiceImpl extends BmpServiceImpl<SecrecyTechnologyPrevention, String> implements
SecrecyTechnologyPreventionService {


	public List getAllSecrecyTechnologyPrevention() {
		return this.getPersistProxy().getOrmPersistence()
				.findAll(SecrecyTechnologyPrevention.class);
	}
	public List getList(PageSortModel psm, SecrecyTechnologyPrevention SecrecyTechnologyPrevention) {
		String hql = "FROM SecrecyTechnologyPrevention m WHERE 1=1 ";
		Map<String, Object> params = new HashMap<String, Object>();
		/*if (SecrecyTechnologyPrevention != null && !"".equals(SecrecyTechnologyPrevention.getName())) {
			hql += " and m.name like :name";
			params.put("name", "%" + SecrecyTechnologyPrevention.getName() + "%");
		}*/
		return findList(hql.toString(), params, psm);
	}

	public List<SecrecyTechnologyPrevention> getPageList(PageSortModel psm, SecrecyTechnologyPrevention SecrecyTechnologyPrevention, Map<String, Object> params,User user) {
		String hql = "FROM SecrecyTechnologyPrevention m WHERE 1=1 and m.organId.organId=:organId";
		params.put("organId", user.getOrgan().getOrganId());
		hql += " and m.state=0";
		if (SecrecyTechnologyPrevention != null) {
                        if (StringUtils.isNotEmpty(SecrecyTechnologyPrevention.getName())) {
                                hql += " and m.name like :name";
                                params.put("name", "%" + SecrecyTechnologyPrevention.getName().trim() + "%");
                        }
                        if (null!=SecrecyTechnologyPrevention.getEquipDep()&&StringUtils.isNotEmpty(SecrecyTechnologyPrevention.getEquipDep().getDepartmentName())) {
                                hql += " and m.equipDep.departmentName like :departmentName";
                                params.put("departmentName", "%" + SecrecyTechnologyPrevention.getEquipDep().getDepartmentName().trim() + "%");
                        }
                }
		hql += " order by m.createTime desc";
		List<SecrecyTechnologyPrevention> returnList = new ArrayList<SecrecyTechnologyPrevention>();
		if( psm!=null ){
		        returnList = (List<SecrecyTechnologyPrevention>)findList(hql.toString(), params, psm);
		}else{
		        returnList = (List<SecrecyTechnologyPrevention>)findList(hql.toString(), params);
		}
		return returnList;
	}


        @Override
        public List<SecrecyTechnologyPrevention> listForSelect(PageSortModel<SecrecyTechnologyPrevention> psm, SecrecyTechnologyPrevention SecrecyTechnologyPrevention, String districtCode,
                        String includeChild) {
                Map<String, Object> params = new HashMap<String, Object>();
                String hql = "FROM SecrecyTechnologyPrevention m WHERE 1=1 ";

                hql += " and m.state=0";
                if (SecrecyTechnologyPrevention != null) {
                        if (StringUtils.isNotEmpty(SecrecyTechnologyPrevention.getName())) {
                                hql += " and m.name like :name";
                                params.put("name", "%" + SecrecyTechnologyPrevention.getName().trim() + "%");
                        }
                        if (null!=SecrecyTechnologyPrevention.getEquipDep()&&StringUtils.isNotEmpty(SecrecyTechnologyPrevention.getEquipDep().getDepartmentName())) {
                                hql += " and m.equipDep.departmentName like :departmentName";
                                params.put("departmentName", "%" + SecrecyTechnologyPrevention.getEquipDep().getDepartmentName().trim() + "%");
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
