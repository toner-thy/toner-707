/**
 *
 */
package com.cdthgk.bmp.publicityEducation.informationReview.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.bmp.publicityEducation.informationReview.service.InformationReviewService;
import com.cdthgk.bmp.publicityEducation.informationReview.vo.InformationReview;
import com.cdthgk.platform.dictionary.context.DictionaryContext;
import com.cdthgk.platform.dictionary.domain.DictionaryOption;
import com.cdthgk.platform.dictionary.spi.DictionaryService;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

/**
 * <p>
 * 类的说明放这里
 * </p>
 * <p>
 * 创建人 宋亚非  创建时间 2014-5-5 - 上午11:14:12
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
public class InformationReviewServiceImpl extends BmpServiceImpl<InformationReview, Serializable>
implements InformationReviewService {

        /* (non-Javadoc)
         * @see com.cdthgk.bmp.publicityEducation.informationReview.service.InformationReviewService#getListPage(ec.common.PageSortModel, com.cdthgk.bmp.publicityEducation.informationReview.vo.InformationReview, com.cdthgk.platform.permission.user.domain.User)
         */
        @Override
        public List<InformationReview> getListPage(PageSortModel<InformationReview> psm,
                        InformationReview informationReview, Organ organ,District district, Boolean flag, String checkLower) {
                Map<String, Object> params = new HashMap<String, Object>();
                StringBuilder hql = new StringBuilder("FROM InformationReview a WHERE 1 = 1");

                if(informationReview!=null){
                        if( informationReview.getTitle()!=null && !"".equals(informationReview.getTitle()) ){
                                hql.append(" and a.title like :title ");
                                params.put("title", "%" + informationReview.getTitle() + "%");
                        }

                        if( informationReview.getContent()!=null && !"".equals(informationReview.getContent())){
                                hql.append(" and a.content like :content ");
                                params.put("content", "%"+informationReview.getContent()+"%");
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
                hql.append(" ORDER BY a.createTime DESC");

                List<InformationReview> resultList = null;
                if( psm!=null ){
                        resultList = (List<InformationReview>) this.findList(hql.toString(), params, psm);
                }else{
                        resultList = (List<InformationReview>) this.findList(hql.toString(), params);
                }

                return resultList;
        }

        /* (non-Javadoc)
         * @see com.cdthgk.bmp.publicityEducation.informationReview.service.InformationReviewService#deleteSelected(java.lang.String)
         */
        @Override
        public void deleteSelected(String deleteIds) {
                List<String> ids = new ArrayList<String>();
                for( String id : deleteIds.split(",") ){
                        ids.add(id);
                }
                Map<String, Object> params = new HashMap<String, Object>();
                StringBuilder sb = new StringBuilder("UPDATE bm_information_review a SET a.STATUS = :STATUS WHERE a.INFORMATION_REVIEW_ID IN ( :idSet ) ");
                params.put("STATUS", 0);
                params.put("idSet", ids);
                this.getPersistProxy().getJdbcPersistence().execute(sb.toString(), params);
        }

		/**
		 * {@inheritDoc}
		 */
		@Override
		public String dealOptions2Txt(String tableId, String field, String informationSources) {
                StringBuilder returnStr = new StringBuilder("");
                DictionaryService dictionaryService = DictionaryContext.getInstance().getDictionaryService();
                if( informationSources!=null && dictionaryService!=null){
                       int flag = 0;
                       for( String id : informationSources.split(",") ){
                              flag ++;
                              DictionaryOption dicop = dictionaryService.getOption(tableId, field, Integer.parseInt(id.trim()));
                              if( dicop!=null ){
                                 if( flag < informationSources.split(",").length ){
                                         returnStr.append(dicop.getOptionText() + ",");
                                 }else{
                                         returnStr.append(dicop.getOptionText());
                                 }
                              }
                       }
                }
                return returnStr.toString();
		}
}
