/**
 *
 */
package com.cdthgk.bmp.stateSecretManagement.contactSecretPerson.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.bmp.stateSecretManagement.contactSecretPerson.service.ContactSecretPersonService;
import com.cdthgk.bmp.stateSecretManagement.contactSecretPerson.vo.ContactSecretPerson;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;

import ec.common.PageSortModel;

/**
 * <p>
 * 类的说明放这里
 * </p>
 * <p>
 * 创建人 宋亚非  创建时间 2014-5-9 - 下午4:29:22
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
public class ContactSecretPersonServiceImpl extends BmpServiceImpl<ContactSecretPerson, Serializable> implements ContactSecretPersonService {
        /* (non-Javadoc)
         * @see com.cdthgk.bmp.publicityEducation.contactSecretPerson.service.DepartmentWebsiteService#getListPage(ec.common.PageSortModel, com.cdthgk.bmp.publicityEducation.contactSecretPerson.vo.ContactSecretPerson, com.cdthgk.platform.permission.user.domain.User)
         */
        @Override
        public List<ContactSecretPerson> getListPage(PageSortModel<ContactSecretPerson> psm,
                        ContactSecretPerson contactSecretPerson, Organ organ,District district,Boolean flag,String checkLower) {
                Map<String, Object> params = new HashMap<String, Object>();
                StringBuilder hql = new StringBuilder("FROM ContactSecretPerson a WHERE 1 = 1");

                if(contactSecretPerson!=null){
                        if( contactSecretPerson.getSecretFileName()!=null && !"".equals(contactSecretPerson.getSecretFileName()) ){
                                hql.append(" and a.secretFileName like :secretFileName ");
                                params.put("secretFileName", "%"+contactSecretPerson.getSecretFileName()+"%");
                        }

                        if( contactSecretPerson.getContactWay()!=null){
                                hql.append(" and a.contactWay = :contactWay ");
                                params.put("contactWay", contactSecretPerson.getContactWay());
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
                List<ContactSecretPerson> resultList = null;
                if( psm!=null ){
                        resultList = (List<ContactSecretPerson>) this.findList(hql.toString(), params, psm);
                }else{
                        resultList = (List<ContactSecretPerson>) this.findList(hql.toString(), params);
                }
                return resultList;
        }

        public String userInfoIds2Txt( String userInfoIds ){
                StringBuilder returnStr = new StringBuilder("");
                if( userInfoIds!=null ){
                        int flag = 0;
                        for( String userInfoId : userInfoIds.split(",") ){
                                if( userInfoId!=null && !"".equals(userInfoId) ){
                                        flag ++;
                                        UserInfo uiTmp = this.get(userInfoId.trim(), UserInfo.class);
                                        if( uiTmp!=null ){
                                                returnStr.append(uiTmp.getName());
                                                if( flag<userInfoIds.split(",").length ){
                                                        returnStr.append(",");
                                                }
                                        }
                                }
                        }
                }
                return returnStr.toString();
        }

        /**
         *
         * <p>
         * 转换为list对象方式
         * </p>
         * <p>
         * 创建人 宋亚非  创建时间 2014-5-13 上午10:13:08
         * </p>
         * <blockquote>
         * <h4>历史修改记录</h4>
         * <ul>
         * <li>修改人 修改时间 修改描述
         * </ul>
         * </blockquote>
         * @return
         */
        public List<UserInfo> userInfoIds2ListObj(String userInfoIds){
                List<UserInfo> returnList = new ArrayList<UserInfo>();
                if( userInfoIds!=null ){
                        for( String userInfoId : userInfoIds.split(",") ){
                                if( userInfoId!=null && !"".equals(userInfoId) ){
                                        UserInfo uiTmp = this.get(userInfoId.trim(), UserInfo.class);
                                        if( uiTmp!=null ){
                                                returnList.add(uiTmp);
                                        }
                                }
                        }
                }
                return returnList;
        }

        /* (non-Javadoc)
         * @see com.cdthgk.bmp.publicityEducation.contactSecretPerson.service.DepartmentWebsiteService#deleteSelected(java.lang.String)
         */
        @Override
        public void deleteSelected(String deleteIds) {
                List<String> ids = new ArrayList<String>();
                for( String id : deleteIds.split(",") ){
                        ids.add(id);
                }
                Map<String, Object> params = new HashMap<String, Object>();
                StringBuilder sb = new StringBuilder("UPDATE bm_contact_secret_person a SET a.STATUS = :STATUS WHERE a.CONTACT_SECRET_PERSON_ID IN ( :idSet ) ");
                params.put("STATUS", 0);
                params.put("idSet", ids);
                this.getPersistProxy().getJdbcPersistence().execute(sb.toString(), params);
        }


}
