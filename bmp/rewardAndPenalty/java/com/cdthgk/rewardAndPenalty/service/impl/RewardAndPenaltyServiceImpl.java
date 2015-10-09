package com.cdthgk.rewardAndPenalty.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.common.lang.StringUtils;
import com.cdthgk.platform.permission.user.domain.User;
import com.cdthgk.rewardAndPenalty.service.RewardAndPenaltyService;
import com.cdthgk.rewardAndPenalty.vo.RewardAndPenalty;

import ec.common.PageSortModel;
@SuppressWarnings("all")
public class RewardAndPenaltyServiceImpl extends BmpServiceImpl<RewardAndPenalty, String> implements
RewardAndPenaltyService {


	public List getAllRewardAndPenalty() {
		return this.getPersistProxy().getOrmPersistence()
				.findAll(RewardAndPenalty.class);
	}
	public List getList(PageSortModel psm, RewardAndPenalty rewardAndPenalty) {
		String hql = "FROM RewardAndPenalty m WHERE 1=1 ";
		Map<String, Object> params = new HashMap<String, Object>();
		if (rewardAndPenalty != null && !"".equals(rewardAndPenalty.getName())) {
			hql += " and m.name like :name";
			params.put("name", "%" + rewardAndPenalty.getName() + "%");
		}
		return findList(hql.toString(), params, psm);
	}

	public List<RewardAndPenalty> getPageList(PageSortModel psm, RewardAndPenalty rewardAndPenalty, Map<String, Object> params,User user) {
		String hql = "FROM RewardAndPenalty m WHERE 1=1 and m.organId.organId=:organId";
		params.put("organId", user.getOrgan().getOrganId());
		hql += " and m.state=0";

		if (rewardAndPenalty != null) {
		        if (StringUtils.isNotEmpty(rewardAndPenalty.getName())) {
        			hql += " and m.name like :name";
        			params.put("name", "%" + rewardAndPenalty.getName().trim() + "%");
		        }
		        if (StringUtils.isNotEmpty(rewardAndPenalty.getStartTime())) {
        			hql += " and DATE_FORMAT( m.date,'%Y-%m-%d') >=:dateStart";
        			params.put("dateStart", rewardAndPenalty.getStartTime());
		        }
		        if (StringUtils.isNotEmpty( rewardAndPenalty.getEndTime())) {
		                hql += " and DATE_FORMAT( m.date,'%Y-%m-%d') <=:dateEnd";
		                params.put("dateEnd", rewardAndPenalty.getEndTime());
		        }
		}
		hql += " order by m.date desc";
		List<RewardAndPenalty> returnList = new ArrayList<RewardAndPenalty>();
		if( psm!=null ){
		        returnList = findList(hql.toString(), params, psm);
		}else{
		        returnList = findList(hql.toString(), params);
		}
		return returnList;
	}


        @Override
        public List<RewardAndPenalty> listForSelect(PageSortModel<RewardAndPenalty> psm, RewardAndPenalty rewardAndPenalty, String districtCode,
                        String includeChild) {
                Map<String, Object> params = new HashMap<String, Object>();
                String hql = "FROM RewardAndPenalty m WHERE 1=1 ";

                hql += " and m.state=0 ";

                if (rewardAndPenalty != null) {
                        if (StringUtils.isNotEmpty(rewardAndPenalty.getName())) {
                                hql += " and m.name like :name";
                                params.put("name", "%" + rewardAndPenalty.getName().trim() + "%");
                        }
                        if (StringUtils.isNotEmpty(rewardAndPenalty.getStartTime())) {
                                hql += " and DATE_FORMAT( m.date,'%Y-%m-%d') >=:dateStart";
                                params.put("dateStart", rewardAndPenalty.getStartTime());
                        }
                        if (StringUtils.isNotEmpty( rewardAndPenalty.getEndTime())) {
                                hql += " and DATE_FORMAT( m.date,'%Y-%m-%d') <=:dateEnd";
                                params.put("dateEnd", rewardAndPenalty.getEndTime());
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
