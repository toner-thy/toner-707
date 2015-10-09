/**
 *
 */
package com.cdthgk.bmp.securityInformationSystem.secretNetwork.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.bmp.securityInformationSystem.secretNetwork.service.SecretNetworkManagersService;
import com.cdthgk.bmp.securityInformationSystem.secretNetwork.service.SecretNetworkService;
import com.cdthgk.bmp.securityInformationSystem.secretNetwork.vo.SecretNetwork;
import com.cdthgk.bmp.securityInformationSystem.secretNetwork.vo.SecretNetworkManagers;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

/**
 * <p>
 * 类的说明放这里
 * </p>
 * <p>
 * 创建人 宋亚非  创建时间 2014-5-9 - 下午4:09:26
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
public class SecretNetworkServiceImpl extends BmpServiceImpl<SecretNetwork, Serializable> implements SecretNetworkService {

        private SecretNetworkManagersService secretNetworkManagersService;

        /* (non-Javadoc)
         * @see com.cdthgk.bmp.publicityEducation.secretNetwork.service.DepartmentWebsiteService#getListPage(ec.common.PageSortModel, com.cdthgk.bmp.publicityEducation.secretNetwork.vo.SecretNetwork, com.cdthgk.platform.permission.user.domain.User)
         */
        @Override
        public List<SecretNetwork> getListPage(PageSortModel<SecretNetwork> psm,
                        SecretNetwork secretNetwork, Organ organ,District district, Boolean flag, String checkLower) {
                Map<String, Object> params = new HashMap<String, Object>();
                StringBuilder hql = new StringBuilder("FROM SecretNetwork a WHERE 1 = 1");

                if(secretNetwork!=null){
                        if( secretNetwork.getBuildTime()!=null ){
                                hql.append(" and a.buildTime = :buildTime ");
                                params.put("buildTime", secretNetwork.getBuildTime());
                        }

                        if( secretNetwork.getApprovalDepartment()!=null && !"".equals(secretNetwork.getApprovalDepartment())){
                                hql.append(" and a.approvalDepartment like :approvalDepartment ");
                                params.put("approvalDepartment", "%"+ secretNetwork.getApprovalDepartment() +"%");
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
                List<SecretNetwork> resultList = null;
                if( psm!=null ){
                        resultList = (List<SecretNetwork>) this.findList(hql.toString(), params, psm);
                }else{
                        resultList = (List<SecretNetwork>) this.findList(hql.toString(), params);
                }

                return resultList;
        }



        /* (non-Javadoc)
         * @see com.cdthgk.bmp.publicityEducation.secretNetwork.service.DepartmentWebsiteService#deleteSelected(java.lang.String)
         */
        @Override
        public void deleteSelected(String deleteIds) {
                List<String> ids = new ArrayList<String>();
                for( String id : deleteIds.split(",") ){
                        ids.add(id);
                }
                Map<String, Object> params = new HashMap<String, Object>();
                StringBuilder sb = new StringBuilder("UPDATE bm_secret_network a SET a.STATUS = :STATUS WHERE a.SECRET_NETWORK_ID IN ( :idSet ) ");
                params.put("STATUS", 0);
                params.put("idSet", ids);
                this.getPersistProxy().getJdbcPersistence().execute(sb.toString(), params);
        }



        /* (non-Javadoc)
         * @see com.cdthgk.bmp.securityInformationSystem.secretNetwork.service.SecretNetworkService#saveSecretNetwork(com.cdthgk.bmp.securityInformationSystem.secretNetwork.vo.SecretNetwork)
         */
        @Override
        public String saveSecretNetwork(SecretNetwork secretNetwork, User currentUser) {
                String returnStr = "";
                if( secretNetwork!=null ){
                        secretNetwork.setStatus(1);
                        secretNetwork.setCreatePerson(currentUser.getUserInfo());
                        secretNetwork.setCreateTime(new Date());
                        secretNetwork.setCreateOrgan(currentUser.getUserInfo().getOrgan());
                        List<SecretNetworkManagers> secretNetworkManagersList = new ArrayList<SecretNetworkManagers>();
                        if( secretNetwork.getSecretNetworkManagerses()!=null && secretNetwork.getSecretNetworkManagerses().size()>0 ){
                                for( SecretNetworkManagers tmpSNM : secretNetwork.getSecretNetworkManagerses() ){
                                        if( tmpSNM!=null ){
                                                if(tmpSNM.getSecretNetworkManagersId()!=null && "".equals(tmpSNM.getSecretNetworkManagersId())){
                                                        tmpSNM.setSecretNetworkManagersId(null);
                                                }
                                                tmpSNM.setStatus(1);
                                                tmpSNM.setBmSecretNetwork(secretNetwork);
                                                tmpSNM.setCreatePerson(currentUser.getUserInfo());
                                                tmpSNM.setCreateTime(new Date());
                                                tmpSNM.setCreateOrgan(currentUser.getUserInfo().getOrgan());
                                                secretNetworkManagersList.add(tmpSNM);
                                        }
                                }
                        }
                        this.save(secretNetwork);
                        if( secretNetworkManagersList!=null && secretNetworkManagersList.size()>0 ){
                                for( SecretNetworkManagers tmpSNM : secretNetworkManagersList ){
                                   this.secretNetworkManagersService.save(tmpSNM);
                                }
                        }
                        returnStr = "新增成功";
                }else{
                        returnStr = "获取数据故障，添加信息失败";
                }
                return returnStr;
        }

        /* (non-Javadoc)
         * @see com.cdthgk.bmp.securityInformationSystem.secretNetwork.service.SecretNetworkService#updateSecretNetwork(com.cdthgk.bmp.securityInformationSystem.secretNetwork.vo.SecretNetwork, com.cdthgk.platform.permission.user.domain.User)
         */
        @Override
        public String updateSecretNetwork(SecretNetwork secretNetwork, User currentUser) {
                String returnStr = "";
                if( secretNetwork!=null ){
                        SecretNetwork snDB = this.get(secretNetwork.getSecretNetworkId());
                        if(snDB!=null){
                                snDB.setBuildTime(secretNetwork.getBuildTime());
                                snDB.setConstructionUnit(secretNetwork.getConstructionUnit());
                                snDB.setApprovalDepartment(secretNetwork.getApprovalDepartment());
                                snDB.setApprovalTime(secretNetwork.getApprovalTime());
                                snDB.setDocNum(secretNetwork.getDocNum());
                                snDB.setModifyPerson(currentUser.getUserInfo());
                                snDB.setModifyTime(new Date());

                                List<SecretNetworkManagers> secretNetworkManagersList = new ArrayList<SecretNetworkManagers>();
                                String delSql = "delete from bm_secret_network_managers where SECRET_NETWORK_ID= :bmSecretNetwork ";
                                Map<String, Object> params = new HashMap<String, Object>();
                                params.put("status", 1);
                                params.put("bmSecretNetwork", snDB.getSecretNetworkId());
                                if( secretNetwork.getSecretNetworkManagerses()!=null && secretNetwork.getSecretNetworkManagerses().size()>0 ){
                                        for( SecretNetworkManagers tmpSNM : secretNetwork.getSecretNetworkManagerses() ){
                                                if( tmpSNM!=null ){
                                                        if(tmpSNM.getSecretNetworkManagersId()!=null && !"".equals(tmpSNM.getSecretNetworkManagersId())){
                                                                SecretNetworkManagers snmDB = this.secretNetworkManagersService.get(tmpSNM.getSecretNetworkManagersId());
                                                                if( snmDB!=null ){
                                                                        this.getPersistProxy().getOrmPersistence().getHibernateTemplate().evict(snmDB);
                                                                        snmDB.setSecretNetworkManagersId(null);
                                                                        snmDB.setBmSecretNetwork(snDB);
                                                                        snmDB.setManagerType(tmpSNM.getManagerType());
                                                                        snmDB.setManagerName(tmpSNM.getManagerName());
                                                                        snmDB.setManagerPost(tmpSNM.getManagerPost());
                                                                        snmDB.setLearning(tmpSNM.getLearning());
                                                                        snmDB.setGraduateSchool(tmpSNM.getGraduateSchool());
                                                                        snmDB.setIsParticipatedSecre(tmpSNM.getIsParticipatedSecre());

                                                                        snmDB.setModifyPerson(currentUser.getUserInfo());
                                                                        snmDB.setModifyTime(new Date());
                                                                        secretNetworkManagersList.add(snmDB);
                                                                }
                                                        }else{
                                                                tmpSNM.setSecretNetworkManagersId(null);
                                                                tmpSNM.setStatus(1);
                                                                tmpSNM.setBmSecretNetwork(secretNetwork);
                                                                tmpSNM.setCreatePerson(currentUser.getUserInfo());
                                                                tmpSNM.setCreateTime(new Date());
                                                                tmpSNM.setCreateOrgan(currentUser.getUserInfo().getOrgan());
                                                                secretNetworkManagersList.add(tmpSNM);
                                                        }


                                                }
                                        }
                                }
                                this.update(snDB);
                                this.getPersistProxy().getJdbcPersistence().execute(delSql, params);
                                if( secretNetworkManagersList!=null && secretNetworkManagersList.size()>0 ){
                                        for( SecretNetworkManagers tmpSNM : secretNetworkManagersList ){
                                           this.secretNetworkManagersService.save(tmpSNM);
                                        }
                                }
                                returnStr = "修改成功";
                        }else{
                                returnStr = "获取数据故障，添加信息失败";
                        }
                }else{
                        returnStr = "获取数据故障，添加信息失败";
                }
                return null;
        }


        /**
         * @return the secretNetworkManagersService
         */
        public SecretNetworkManagersService getSecretNetworkManagersService() {
                return secretNetworkManagersService;
        }



        /**
         * @param secretNetworkManagersService the secretNetworkManagersService to set
         */
        public void setSecretNetworkManagersService(SecretNetworkManagersService secretNetworkManagersService) {
                this.secretNetworkManagersService = secretNetworkManagersService;
        }




}
