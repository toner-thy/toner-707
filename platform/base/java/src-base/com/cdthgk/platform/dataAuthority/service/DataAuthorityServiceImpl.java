package com.cdthgk.platform.dataAuthority.service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cdthgk.common.structure.tree.Tree;
import com.cdthgk.common.structure.tree.TreeNode;
import com.cdthgk.common.structure.tree.component.MifTreeHelper;
import com.cdthgk.common.structure.tree.component.MifTreeNode;
import com.cdthgk.common.structure.tree.component.MifTreeNodeCreator;
import com.cdthgk.platform.base.service.GenericServiceTemplate;
import com.cdthgk.platform.dataAuthority.vo.DataAuthority;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;

import ec.common.PageSortModel;
public class DataAuthorityServiceImpl extends
GenericServiceTemplate<DataAuthority, String> implements
DataAuthorityService {

        @Override
        public List<DataAuthority> listForAll() {
                return this.findAll();
        }

        @Override
        public List<DataAuthority> listForEc(PageSortModel<DataAuthority> psm, DataAuthority dataAuthority) {

                StringBuffer stringBuffer=new StringBuffer();
                Map<String, Object> params=new HashMap<String, Object>();
                stringBuffer.append("from DataAuthority d where 1=1 and d.dataAuthority=null ");
                if (dataAuthority!=null) {

                }
                stringBuffer.append("order by d.sort");
                return this.findList(stringBuffer.toString(), params, psm);
        }
        public List<DataAuthority> listForChrild(DataAuthority dataAuthority) {

                StringBuffer stringBuffer=new StringBuffer();
                Map<String, Object> params=new HashMap<String, Object>();
                stringBuffer.append("from DataAuthority d where 1=1  ");
                if (dataAuthority.getDataAuthority()==null) {
                         //为父节点
                        stringBuffer.append(" and d.dataAuthority.dataId=:dataId");
                        params.put("dataId", dataAuthority.getDataId());

                }else {
                        //为子节点
                        stringBuffer.append(" and d.dataAuthority.dataId=:dataId");
                        params.put("dataId", dataAuthority.getDataAuthority().getDataId());

                }
                return this.findList(stringBuffer.toString(), params);
        }
        @Override
        public String addUserInfros(DataAuthority dataAuthority,List<String> readPersonIds) {
                String msg="添加人员：";
                String succString="";
                String errString="";
                if (dataAuthority!=null) {

                        try {

                                for(String ids:readPersonIds)
                                {

                                        Map<String, Object> params=new HashMap<String, Object>();
                                        String sqlString="select user_info_id from bm_data_authority_role where data_id=:dataid and user_info_id=:user_info_id";
                                        params.put("dataid", dataAuthority.getDataId());
                                        params.put("user_info_id", ids);
                                        List<Object> userinfoids=this.getPersistProxy().getOrmPersistence().findByNativeQuery(sqlString, params);
                                        if(userinfoids.size()<=0)
                                        {

                                                Map<String, Object> map=new HashMap<String, Object>();
                                                map.put("user_info_id", ids);
                                                map.put("data_id", dataAuthority.getDataId());
                                                this.getPersistProxy().getJdbcPersistence().insert("bm_data_authority_role", map);
                                                String hql="from UserInfo where userInfoId=:user_info_id";
                                                params.remove("dataid");
                                                UserInfo userInfo=this.getPersistProxy().getOrmPersistence().find(hql, params);
                                                succString+=userInfo.getName()+",";
                                        }else{
                                                String hql="from UserInfo where userInfoId=:user_info_id";
                                                params.remove("dataid");
                                                UserInfo userInfo=this.getPersistProxy().getOrmPersistence().find(hql, params);
                                                errString+=userInfo.getName()+",";
                                        }
                                }
                                if (errString.equals("")&&!succString.equals("")) {
                                        msg="添加成功";

                                }
                                else if (succString.equals("")&&!errString.equals("")) {
                                        msg="添加失败,由于"+errString+"已经存在了！";

                                }
                                else if (errString.equals("")&&succString.equals("")) {
                                        msg="添加失败！";

                                }
                                else if (!succString.equals("")&&!errString.equals("")) {
                                        msg="添加："+succString+"成功" +"添加："+errString+"失败,由于他们已经存在了！";;

                                }
                        } catch (Exception e) {
                                msg="添加失败";
                        }


                }else {
                        msg="不存在数据权限名字了！";
                }
                return msg;
        }
        @Override
        public String deleteUserInfros(DataAuthority dataAuthority,List<String> userinfoIds) {
                String msg="";
                if (dataAuthority!=null) {
                        try {
                                for (String userinfoId:userinfoIds) {
                                                Map<String, Object> map=new HashMap<String, Object>();
                                                map.put("user_info_id", userinfoId);
                                                map.put("data_id", dataAuthority.getDataId());
                                                this.getPersistProxy().getJdbcPersistence().delete("bm_data_authority_role", map);
                                                /*String sql="delete from bm_data_authority_role where user_info_id=:user_info_id and data_id=:data_id ";
                                                this.getPersistProxy().getJdbcPersistence().execute(sql, map);*/
                                }
                                msg="删除成功";
                        } catch (Exception e) {
                                e.printStackTrace();
                                msg="删除失败！";
                        }
                }else {
                        msg="删除失败，不存在数据权限名字了！";
                }
                return msg;
        }
        public Map<DataAuthority,UserInfo> findUserInfros(DataAuthority dataAuthority,UserInfo userinfo) {
                Map<DataAuthority,UserInfo> map=new HashMap<DataAuthority, UserInfo>();
                if (dataAuthority!=null) {
                        List<DataAuthority> childDataAuthorities=listForChrild(dataAuthority);//子节点数据维护权限
                        for (DataAuthority dataAuthority2:childDataAuthorities) {

                                Map<String, Object> params=new HashMap<String, Object>();
                                String sqlString="select user_info_id from bm_data_authority_role where data_id=:dataid and user_info_id=:user_info_id";
                                params.put("dataid", dataAuthority2.getDataId());
                                params.put("user_info_id", userinfo.getUserInfoId());
                                List<Object> userinfoids=this.getPersistProxy().getOrmPersistence().findByNativeQuery(sqlString, params);
                                params.remove("dataid");
                                params.remove("user_info_id");
                                for (int i = 0; i < userinfoids.size(); i++) {
                                        Object object=userinfoids.get(i);
                                        String userInfoid=object.toString();
                                        String hql="from UserInfo where userInfoId=:userInfoId";
                                        params.put("userInfoId", userInfoid);
                                        UserInfo userInfo=this.getPersistProxy().getOrmPersistence().find(hql, params);
                                        map.put(dataAuthority2, userInfo);
                                }

                        }
                }else {
                }
                return map;
        }
        public String editUserInfros(DataAuthority dataAuthority,UserInfo userinfo ,String type) {
                        if(type.equals("alert"))
                        {


                        }else if(type.equals("delete")){
                               /* String sqlString="delete from bm_data_authority_role where data_id=:dataid and user_info_id=:user_info_id";
                                params.put("dataid", dataAuthority2.getDataId());
                                params.put("user_info_id", userinfo.getUserInfoId());
                                List<Object> userinfoids=this.getPersistProxy().getOrmPersistence().findByNativeQuery(sqlString, params);*/
                                Map<String, Object> map=new HashMap<String, Object>();
                                map.put("user_info_id",userinfo.getUserInfoId().trim());
                                map.put("data_id", dataAuthority.getDataId());
                                try {
                                        this.getPersistProxy().getJdbcPersistence().delete("bm_data_authority_role", map);
                                        return "删除成功";
                                } catch (Exception e) {
                                        // TODO: handle exception
                                        e.printStackTrace();
                                        return "删除失败";

                                }
                        }else {

                        }
                        return null;
        }


        public List<UserInfo> listForUserInfos(PageSortModel<UserInfo> psm, DataAuthority dataAuthority,UserInfo userInfo2 ) {
                List<UserInfo> userInfoslist=new ArrayList<UserInfo>();
                if (userInfo2==null||(userInfo2.getName()!=null&&userInfo2.getName().equals("")&&
                    userInfo2.getDepartment().getDepartmentName()!=null&&userInfo2.getDepartment().getDepartmentName().equals("")&&
                    userInfo2.getOrgan().getOrganName()!=null&&userInfo2.getOrgan().getOrganName().equals(""))) {
                        int countAll=0;
                        if (dataAuthority.getDataAuthority()==null) {
                                String sqlStringAll="select user_info_id from bm_data_authority_role where data_id in (" +
                                                "select data_id from bm_data_authority where parent_id='"+dataAuthority.getDataId()+"')" ;
                                //查询对应人员
                                List<Object> userinfoidAll=this.getPersistProxy().getOrmPersistence().findByNativeQuery(sqlStringAll);
                                countAll=userinfoidAll.size();
                                Set<UserInfo> userInfos=new LinkedHashSet<UserInfo>();
                                Map<String, Object> params=new HashMap<String, Object>();
                                String sqlString="select user_info_id from bm_data_authority_role where data_id in (" +
                                		"select data_id from bm_data_authority where parent_id='"+dataAuthority.getDataId()+"')" +
                                				" limit " +(psm.getPageNumber()-1)*psm.getPageSize()+","+psm.getPageSize();
                                //查询对应人员
                                List<Object> userinfoids=this.getPersistProxy().getOrmPersistence().findByNativeQuery(sqlString);

                                for (int i = 0; i < userinfoids.size(); i++) {
                                        Object object=userinfoids.get(i);
                                        String userInfoid=object.toString();
                                        String hql="from UserInfo where userInfoId=:userInfoId";
                                        params.put("userInfoId", userInfoid);
                                        UserInfo userInfo=this.getPersistProxy().getOrmPersistence().find(hql, params);
                                        userInfos.add(userInfo);
                                }
                                userInfoslist.addAll(userInfos);
                                psm.setTotalRows(countAll);
                                psm.setPageSize(10);

                        }else {
                                Map<String, Object> params=new HashMap<String, Object>();
                                String sqlStringAll="select user_info_id from bm_data_authority_role where data_id=:dataid" ;
                                params.put("dataid", dataAuthority.getDataId());
                                //查询对应人员
                                List<Object> userinfoidAll=this.getPersistProxy().getOrmPersistence().findByNativeQuery(sqlStringAll, params);
                                countAll=userinfoidAll.size();
                                Set<UserInfo> userInfos=new LinkedHashSet<UserInfo>();
                                String sqlString="select user_info_id from bm_data_authority_role where data_id=:dataid"+
                                                " limit " +(psm.getPageNumber()-1)*psm.getPageSize()+","+psm.getPageSize();
                                List<Object> userinfoids=this.getPersistProxy().getOrmPersistence().findByNativeQuery(sqlString, params);
                                params.remove("dataid");
                                for (int i = 0; i < userinfoids.size(); i++) {
                                        Object object=userinfoids.get(i);
                                        String userInfoid=object.toString();
                                        String hql="from UserInfo where userInfoId=:userInfoId";
                                        params.put("userInfoId", userInfoid);
                                        UserInfo userInfo=this.getPersistProxy().getOrmPersistence().find(hql, params);
                                        userInfos.add(userInfo);
                                }
                                userInfoslist.addAll(userInfos);
                                psm.setTotalRows(countAll);
                                psm.setPageSize(10);
                        }
                }else{
                        //查出有那个人不？
                        Map<String, Object> params=new HashMap<String, Object>();
                        StringBuffer hqlBuffer=new StringBuffer();
                        hqlBuffer.append("from UserInfo u where 1=1");
                        if(userInfo2.getName()!=null&&!userInfo2.getName().equals(""))
                        {
                                hqlBuffer.append(" and u.name like:name ");
                                params.put("name","%"+userInfo2.getName()+"%");
                        }
                        if(userInfo2.getDepartment().getDepartmentName()!=null&&!userInfo2.getDepartment().getDepartmentName().equals(""))
                        {
                                hqlBuffer.append(" and u.department.departmentName like:depname ");
                                params.put("depname","%"+userInfo2.getDepartment().getDepartmentName()+"%");
                        }
                        if(userInfo2.getOrgan().getOrganName()!=null&&!userInfo2.getOrgan().getOrganName().equals(""))
                        {
                                hqlBuffer.append(" and u.organ.organName like:oname");
                                params.put("oname","%"+userInfo2.getOrgan().getOrganName()+"%");
                        }
                        List<UserInfo> userInfos=this.getPersistProxy().getOrmPersistence().findList(hqlBuffer.toString(), params);
                        if(userInfos.size()>0)
                        {
                                if (dataAuthority.getDataAuthority()==null) {
                                        //全部
                                        for (int i = 0; i < userInfos.size(); i++) {
                                                UserInfo userInfo=userInfos.get(i);
                                                String sqlString="select * from bm_data_authority_role where user_info_id='"+userInfo.getUserInfoId()+"'";
                                                //查询对应人员
                                                List<Object> userinfoids=this.getPersistProxy().getOrmPersistence().findByNativeQuery(sqlString);
                                                if (userinfoids.size()>0) {
                                                        userInfoslist.add(userInfo);
                                                }

                                        }
                                        psm.setTotalRows(userInfoslist.size());
                                        psm.setPageSize(10);
                                }else {

                                        for (int i = 0; i < userInfos.size(); i++) {
                                                UserInfo userInfo=userInfos.get(i);
                                                String sqlString="select * from bm_data_authority_role where user_info_id='"+userInfo.getUserInfoId()
                                                                +"'"+" and data_id='"+dataAuthority.getDataId()+"'";
                                                //查询对应人员
                                                List<Object> userinfoids=this.getPersistProxy().getOrmPersistence().findByNativeQuery(sqlString);
                                                if (userinfoids.size()>0) {
                                                        userInfoslist.add(userInfo);
                                                }

                                        }
                                        psm.setTotalRows(userInfoslist.size());
                                        psm.setPageSize(10);

                                }
                        }


                }


                return userInfoslist;
        }

        @Override
        public List<DataAuthority> listForSelect(PageSortModel<DataAuthority> psm, DataAuthority dataAuthority) {
                return null;
        }

        @Override
        public List<Map<String, Object>> getTree(DataAuthority sc) {
                String hql = null;
                List<DataAuthority> secrecySystemClassList = null;

                hql = "FROM DataAuthority d where d.dataType="+sc.getDataType();
                secrecySystemClassList = this.getPersistProxy().getOrmPersistence().findList(hql);

                // 获取树节点
                List<TreeNode<DataAuthority>> nodeList = new ArrayList<TreeNode<DataAuthority>>();
                for (DataAuthority dataAuthority : secrecySystemClassList) {
                        TreeNode<DataAuthority> node = new TreeNode<DataAuthority>(dataAuthority.getDataId());
                        node.setNodeObject(dataAuthority);
                        node.setNodeObject(dataAuthority);
                        DataAuthority pSecrecySystemClass = dataAuthority.getDataAuthority();
                        if (pSecrecySystemClass != null) {
                                TreeNode<DataAuthority> parentNode = new TreeNode<DataAuthority>(pSecrecySystemClass.getDataId());
                                parentNode.setNodeObject(pSecrecySystemClass);
                                node.setParentNode(parentNode);
                                //parentNode.setId(pSecrecySystemClass.getSecrecySystemClassId());
                                parentNode.setNodeObject(pSecrecySystemClass);
                        }
                        nodeList.add(node);
                }
                Tree<DataAuthority> secrecySystemClassTree = new Tree<DataAuthority>(nodeList);

                MifTreeHelper mifTreeHelper = new MifTreeHelper(secrecySystemClassTree);

                return mifTreeHelper.format(new MifTreeNodeCreator<DataAuthority>(){
                        public MifTreeNode createNode(TreeNode<DataAuthority> node) {
                                MifTreeNode mifNode = new MifTreeNode();

                                Map<String, Object> propertyMap = new HashMap<String, Object>();
                                propertyMap.put("name", node.getNodeObject().getName());
                                mifNode.setProperty(propertyMap);

                                Map<String, Object> stateMap = new HashMap<String, Object>();
                                stateMap.put("open", true);
                                mifNode.setState(stateMap);

                                Map<String, Object> dataMap = new HashMap<String, Object>();
                                dataMap.put("id", node.getNodeObject().getDataId());
                                mifNode.setData(dataMap);
                                mifNode.setType("folder");
                                return mifNode;
                        }
                });
        }





}