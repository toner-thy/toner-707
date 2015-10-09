/**
 *
 */
package com.cdthgk.bmp.publicityEducation.departmentWebsite.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.bmp.publicityEducation.departmentWebsite.service.DepartmentWebsiteService;
import com.cdthgk.bmp.publicityEducation.departmentWebsite.vo.Departmentwebsite;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

/**
 * <p>
 * 类的说明放这里
 * </p>
 * <p>
 * 创建人 宋亚非  创建时间 2014-5-5 - 上午11:11:50
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
public class DepartmentWebsiteServiceImpl extends BmpServiceImpl<Departmentwebsite, Serializable>
        implements DepartmentWebsiteService{

        /* (non-Javadoc)
         * @see com.cdthgk.bmp.publicityEducation.departmentWebsite.service.DepartmentWebsiteService#getListPage(ec.common.PageSortModel, com.cdthgk.bmp.publicityEducation.departmentWebsite.vo.Departmentwebsite, com.cdthgk.platform.permission.user.domain.User)
         */
        @Override
        public List<Departmentwebsite> getListPage(PageSortModel<Departmentwebsite> psm,
                        Departmentwebsite departmentWebsite, Organ organ,District district,Boolean flag,String checkLower) {
                Map<String, Object> params = new HashMap<String, Object>();
                StringBuilder hql = new StringBuilder("FROM Departmentwebsite a WHERE 1 = 1");

                if(departmentWebsite!=null){
                        if( departmentWebsite.getWebsiteName()!=null && !"".equals(departmentWebsite.getWebsiteName()) ){
                                hql.append(" and a.websiteName like :websiteName ");
                                params.put("websiteName", "%" + departmentWebsite.getWebsiteName() + "%");
                        }

                        if( departmentWebsite.getAdminName()!=null && !"".equals(departmentWebsite.getAdminName())){
                                hql.append(" and a.adminName like :adminName ");
                                params.put("adminName", "%"+departmentWebsite.getAdminName()+"%");
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
                List<Departmentwebsite> resultList = null;
                if( psm!=null ){
                        resultList = (List<Departmentwebsite>) this.findList(hql.toString(), params, psm);
                }else{
                        resultList = (List<Departmentwebsite>) this.findList(hql.toString(), params);
                }

                return resultList;
        }

        /* (non-Javadoc)
         * @see com.cdthgk.bmp.publicityEducation.departmentWebsite.service.DepartmentWebsiteService#deleteSelected(java.lang.String)
         */
        @Override
        public void deleteSelected(String deleteIds) {
                List<String> ids = new ArrayList<String>();
                for( String id : deleteIds.split(",") ){
                        ids.add(id);
                }
                Map<String, Object> params = new HashMap<String, Object>();
                StringBuilder sb = new StringBuilder("UPDATE bm_departmentwebsite a SET a.STATUS = :STATUS WHERE a.DEPARTMENTWEBSITE_ID IN ( :idSet ) ");
                params.put("STATUS", 0);
                params.put("idSet", ids);
                this.getPersistProxy().getJdbcPersistence().execute(sb.toString(), params);
        }

}
