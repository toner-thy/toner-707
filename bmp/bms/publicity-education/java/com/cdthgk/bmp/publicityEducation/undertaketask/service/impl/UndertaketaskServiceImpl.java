/**
 *
 */
package com.cdthgk.bmp.publicityEducation.undertaketask.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.bmp.publicityEducation.departmentWebsite.vo.Departmentwebsite;
import com.cdthgk.bmp.publicityEducation.publicityEducation.vo.Publicityeducation;
import com.cdthgk.bmp.publicityEducation.undertaketask.service.UndertaketaskService;
import com.cdthgk.bmp.publicityEducation.undertaketask.vo.Undertaketask;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

/**
 * <p>
 * 类的说明放这里
 * </p>
 * <p>
 * 创建人 宋亚非  创建时间 2014-5-5 - 上午11:20:37
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
public class UndertaketaskServiceImpl extends BmpServiceImpl<Undertaketask, Serializable> implements
                UndertaketaskService {

        /* (non-Javadoc)
         * @see com.cdthgk.bmp.publicityEducation.undertaketask.service.UndertaketaskService#getListPage(ec.common.PageSortModel, com.cdthgk.bmp.publicityEducation.undertaketask.vo.Undertaketask, com.cdthgk.platform.permission.user.domain.User)
         */
        @Override
        public List<Undertaketask> getListPage(PageSortModel<Undertaketask> psm, Undertaketask undertaketask,
        		Organ organ,District district,Boolean flag,String checkLower) {
                Map<String, Object> params = new HashMap<String, Object>();
                StringBuilder sb = new StringBuilder("FROM Undertaketask p WHERE 1=1");
                if( undertaketask!=null && undertaketask.getTaskName() !=null && !"".equals(undertaketask.getTaskName()) ){
                        sb.append(" AND p.taskName like :taskName");
                        params.put("taskName", "%"+undertaketask.getTaskName()+"%");
                }
                if(undertaketask!=null && undertaketask.getCompleteTime() !=null && !"".equals(undertaketask.getCompleteTime())){
                        sb.append(" AND p.completeTime = :completeTime");
                        params.put("completeTime", undertaketask.getCompleteTime());
                }

                sb.append(" AND p.status = :status");
                params.put("status", 1);

                // false ： 查看本单位； true ：查看行政区
                if (flag) {
                        // checkLower 是否查看下级 1：查看下级； 0：不查看
                        if ("1".equals(checkLower)) {
                                // 查看整个行政区
                                sb.append(" and p.createOrgan.district.layer like :layer");
                                params.put("layer", district.getLayer() + "%");
                        } else {
                                // 查看直辖机关单位
                                sb.append(" and p.createOrgan.district.districtCode = :districtCode");
                                params.put("districtCode", district.getDistrictCode());
                        }
                } else {
                      //限定查找本单位
                        sb.append(" AND p.createPerson.organ = :organ");
                        params.put("organ", organ);
                }

                sb.append(" ORDER BY p.completeTime DESC");

                List<Undertaketask> resultList = null;
                if( psm!=null ){
                        resultList = (List<Undertaketask>) this.findList(sb.toString(), params, psm);
                }else{
                        resultList = (List<Undertaketask>) this.findList(sb.toString(), params);
                }
                return resultList;
        }

        /* (non-Javadoc)
         * @see com.cdthgk.bmp.publicityEducation.undertaketask.service.UndertaketaskService#deleteSelected(java.lang.String)
         */
        @Override
        public void deleteSelected(String deleteIds) {
                List<String> ids = new ArrayList<String>();
                for( String id : deleteIds.split(",") ){
                        ids.add(id);
                }
                Map<String, Object> params = new HashMap<String, Object>();
                StringBuilder sb = new StringBuilder("UPDATE bm_undertaketask a SET a.STATUS = :STATUS WHERE a.UNDERTAKETASK_ID IN ( :idSet ) ");
                params.put("STATUS", 0);
                params.put("idSet", ids);
                this.getPersistProxy().getJdbcPersistence().execute(sb.toString(), params);
        }



}
