package com.cdthgk.rewardAndPenalty.service.impl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.common.lang.StringUtils;
import com.cdthgk.platform.permission.user.domain.User;
import com.cdthgk.rewardAndPenalty.service.PaperPresentedService;
import com.cdthgk.rewardAndPenalty.vo.PaperPresented;

import ec.common.PageSortModel;
@SuppressWarnings("all")
public class PaperpresentedServiceImpl extends BmpServiceImpl<PaperPresented, String> implements
PaperPresentedService {


	public List getAllPaperPresented() {
		return this.getPersistProxy().getOrmPersistence()
				.findAll(PaperPresented.class);
	}
	public List getList(PageSortModel psm, PaperPresented paperPresented) {
		String hql = "FROM PaperPresented m WHERE 1=1 ";
		Map<String, Object> params = new HashMap<String, Object>();
		if (paperPresented != null && !"".equals(paperPresented.getName())) {
			hql += " and m.name like :name";
			params.put("name", "%" + paperPresented.getName() + "%");
		}
		return findList(hql.toString(), params, psm);
	}

	public List<PaperPresented> getPageList(PageSortModel psm, PaperPresented paperPresented, Map<String, Object> params,User user) {
		String hql = "FROM PaperPresented m WHERE 1=1 and m.organId.organId=:organId";
		params.put("organId", user.getOrgan().getOrganId());
		hql += " and m.state=0";
		if (paperPresented != null) {
                        if (StringUtils.isNotEmpty(paperPresented.getName())) {
                                hql += " and m.name like :name";
                                params.put("name", "%" + paperPresented.getName().trim() + "%");
                        }
                        if (StringUtils.isNotEmpty(paperPresented.getAuthor())) {
                                hql += " and m.author like :author";
                                params.put("author", "%" + paperPresented.getAuthor().trim() + "%");
                        }
                }
		hql += " order by m.date desc";
		return (List<PaperPresented>)findList(hql.toString(), params, psm);
	}


        @Override
        public List<PaperPresented> listForSelect(PageSortModel<PaperPresented> psm, PaperPresented paperPresented, String districtCode,
                        String includeChild) {
                Map<String, Object> params = new HashMap<String, Object>();
                String hql = "FROM PaperPresented m WHERE 1=1 ";

                hql += " and m.state=0";
                if (paperPresented != null) {
                        if (StringUtils.isNotEmpty(paperPresented.getName())) {
                                hql += " and m.name like :name";
                                params.put("name", "%" + paperPresented.getName().trim() + "%");
                        }
                        if (StringUtils.isNotEmpty(paperPresented.getStartTime())) {
                                hql += " and DATE_FORMAT( m.date,'%Y-%m-%d') >=:dateStart";
                                params.put("dateStart", paperPresented.getStartTime());
                        }
                        if (StringUtils.isNotEmpty( paperPresented.getEndTime())) {
                                hql += " and DATE_FORMAT( m.date,'%Y-%m-%d') <=:dateEnd";
                                params.put("dateEnd", paperPresented.getEndTime());
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
