package com.cdthgk.platform.dataAuthority.service;
import java.util.List;
import java.util.Map;

import com.cdthgk.platform.base.service.GenericBasicService;
import com.cdthgk.platform.dataAuthority.vo.DataAuthority;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;

import ec.common.PageSortModel;
public interface DataAuthorityService extends
GenericBasicService<DataAuthority, String> {

        public List<DataAuthority> listForAll();
        public List<DataAuthority> listForEc(PageSortModel<DataAuthority> psm,
                        DataAuthority discloseSecrecy);
        public List<DataAuthority> listForSelect(PageSortModel<DataAuthority> psm,
                        DataAuthority discloseSecrecy);
        public List<Map<String, Object>> getTree(DataAuthority sc);
        public List<UserInfo> listForUserInfos(PageSortModel<UserInfo> psm, DataAuthority dataAuthority,UserInfo userInfo );
        public String addUserInfros(DataAuthority dataAuthority,List<String> readPersonIds) ;
        public String deleteUserInfros(DataAuthority dataAuthority,List<String> userinfoIds);
        public List<DataAuthority> listForChrild(DataAuthority dataAuthority);
        public Map<DataAuthority,UserInfo> findUserInfros(DataAuthority dataAuthority,UserInfo userinfo) ;
        public String editUserInfros(DataAuthority dataAuthority,UserInfo userinfo ,String type) ;

}
