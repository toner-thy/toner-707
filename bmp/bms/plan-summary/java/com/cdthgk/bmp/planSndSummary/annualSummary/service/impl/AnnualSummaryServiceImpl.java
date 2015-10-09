/**
 *
 */
package com.cdthgk.bmp.planSndSummary.annualSummary.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.bmp.planSndSummary.annualPlan.vo.AnnualPlan;
import com.cdthgk.bmp.planSndSummary.annualSummary.service.AnnualSummaryService;
import com.cdthgk.bmp.planSndSummary.annualSummary.vo.AnnualSummary;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;

/**
 * <p>
 * 类的说明放这里
 * </p>
 * <p>
 * 创建人 宋亚非  创建时间 2014-4-28 - 下午5:06:51
 * </p>
 * <blockquote>
 * <h4>历史修改记录</h4>
 * <ul>
 * <li>修改人 修改时间 修改描述
 * </ul>
 * </blockquote>
 * <p>
 * copyright cdthgk 2010-2011, all rights reserved.
 * </p>
 *
 * @author 钟冀
 * @author cdthgk r&d
 * @since 1.0
 * @version 1.0
 */
public class AnnualSummaryServiceImpl extends BmpServiceImpl<AnnualSummary, Serializable> implements AnnualSummaryService{

        /* (non-Javadoc)
         * @see com.cdthgk.bmp.SummarySndSummary.annualSummary.service.AnnualSummaryService#getListPage(ec.common.PageSortModel, com.cdthgk.bmp.SummarySndSummary.annualSummary.vo.AnnualSummary)
         */
        @Override
        public List<AnnualSummary> getListPage(PageSortModel<AnnualSummary> psm, AnnualSummary annualSummary, Organ organ, District district,Boolean flag,String checkLower) {
                Map<String, Object> params = new HashMap<String, Object>();
                StringBuilder hql = new StringBuilder("FROM AnnualSummary a WHERE 1 = 1");
                //按标题查询
                if(annualSummary!=null){
                        if( annualSummary.getAnnualSummaryTitle()!=null && !"".equals(annualSummary.getAnnualSummaryTitle()) ){
                                hql.append(" and a.annualSummaryTitle like :annualSummaryTitle ");
                                params.put("annualSummaryTitle", "%" + annualSummary.getAnnualSummaryTitle() + "%");
                        }

                        if( annualSummary.getAnnualSummaryYear()!=null ){
                                hql.append(" and a.annualSummaryYear = :annualSummaryYear ");
                                params.put("annualSummaryYear", annualSummary.getAnnualSummaryYear());
                        }
                }

                hql.append(" and a.status = :status ");
                params.put("status", 1);

                // false ： 查看本单位； true ：查看行政区
                if (flag) {
                        // checkLower 是否查看下级 1：查看下级； 0：不查看
                        if ("1".equals(checkLower)) {
                                // 查看整个行政区
                                hql.append(" and a.createOrgan.district.layer like :layer");
                                params.put("layer", district.getLayer() + "%");
                        } else {
                                // 查看直辖机关单位
                                hql.append(" and a.createOrgan.district.districtCode = :districtCode");
                                params.put("districtCode", district.getDistrictCode());
                        }
                } else {
                        //限制查看本单位的数据
                        hql.append(" and a.createOrgan.organId = :createOrganId");
                        params.put("createOrganId", organ.getOrganId());
                }

                //按年度排序
                hql.append(" ORDER BY a.annualSummaryYear, a.annualSummaryId DESC");

                List<AnnualSummary> resultList = null;
                if( psm!=null ){
                        resultList = (List<AnnualSummary>) this.findList(hql.toString(), params, psm);
                } else{
                        resultList = this.findList(hql.toString(), params);
                }
                return resultList;
        }

        /* (non-Javadoc)
         * @see com.cdthgk.bmp.planSndSummary.annualPlan.service.AnnualPlanService#deleteSelected(java.lang.String)
         */
        @Override
        public void deleteSelected(String deleteIds) {
                List<String> ids = new ArrayList<String>();
                for( String id : deleteIds.split(",") ){
                        ids.add(id);
                }
                Map<String, Object> params = new HashMap<String, Object>();
                StringBuilder sb = new StringBuilder("UPDATE bm_annual_summary a SET a.STATUS = :STATUS WHERE a.ANNUAL_SUMMARY_ID IN ( :annualSummaryIdSet ) ");
                params.put("STATUS", 0);
                params.put("annualSummaryIdSet", ids);
                this.getPersistProxy().getJdbcPersistence().execute(sb.toString(), params);
        }

}
