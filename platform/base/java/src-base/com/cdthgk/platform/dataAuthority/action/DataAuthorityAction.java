package com.cdthgk.platform.dataAuthority.action;
import java.util.List;
import java.util.Map;

import com.cdthgk.platform.base.action.PlatformAction;
import com.cdthgk.platform.dataAuthority.service.DataAuthorityService;
import com.cdthgk.platform.dataAuthority.vo.DataAuthority;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;

import ec.common.PageSortModel;
public class DataAuthorityAction extends PlatformAction {

	private static final long serialVersionUID = 9064155163585641297L;

	private DataAuthority dataAuthority;
	private DataAuthorityService dataAuthorityService;
	private Boolean needReload = false;
	private String dataAuthorityIds;
	private List<DataAuthority> dataAuthoritys;
	public List<String> readPersonIds;
        public List<String> getReadPersonIds() {
                return readPersonIds;
        }

        public void setReadPersonIds(List<String> readPersonIds) {
                this.readPersonIds = readPersonIds;
        }

	public String add() {
	        String id = dataAuthority.getDataId();
                dataAuthority = dataAuthorityService.get(id);
		return SUCCESS;
	}


	public String adding() {
	        if (readPersonIds==null||readPersonIds.size()==0) {
	                addActionMessage("添加失败，添加的人员不能为空！");
	                return redirectActionResult(LIST);
                }
		String id = dataAuthority.getDataId();
                dataAuthority = dataAuthorityService.get(id);
		addActionMessage(dataAuthorityService.addUserInfros(dataAuthority,readPersonIds));
		dataId=dataAuthority.getDataId();
		return redirectActionResult(LIST);
	}


	public String detail() {
		String id = dataAuthority.getDataId();
		dataAuthority = dataAuthorityService.get(id);
		this.putToRequest("dataAuthority", dataAuthority);
		return SUCCESS;
	}

	public String detail_userInfo() {
	        String id = dataAuthority.getDataId();
	        dataAuthority = dataAuthorityService.get(id);
	        Map<DataAuthority,UserInfo> map=dataAuthorityService.findUserInfros(dataAuthority, userInfo);//
	        this.putToRequest("map", map);
	        this.putToRequest("userInfo2", userInfo);
	        return SUCCESS;
	}

	public String del() {
	        String id2 = dataAuthority.getDataId();
                dataAuthority = dataAuthorityService.get(id2);
		addActionMessage(dataAuthorityService.deleteUserInfros(dataAuthority,getIds()));
		dataId=dataAuthority.getDataId();
		return redirectActionResult(LIST);
	}


	public String edit() {
	        String id = dataAuthority.getDataId();
                dataAuthority = dataAuthorityService.get(id);
                Map<DataAuthority,UserInfo> map=dataAuthorityService.findUserInfros(dataAuthority, userInfo);
                this.putToRequest("map", map);
                this.putToRequest("userInfo2", userInfo);
	        return SUCCESS;
	}
	private String leixin;

	public String editing() {
	        String id = dataAuthority.getDataId();
	        id=id.trim();
                dataAuthority = dataAuthorityService.get(id);
                System.out.println(id.equals("2"));
                if (leixin.equals("delete")||leixin.equals("alert")) {
                        if (dataAuthority==null) {
                                addActionMessage("删除失败！");
                        }else {
                                addActionMessage(dataAuthorityService.editUserInfros(dataAuthority, userInfo, leixin));
                        }
                }else {
                        addActionMessage("删除失败！");
                }

	        return SUCCESS;
	}
	public String  list()
	{
		PageSortModel<DataAuthority> psm = new PageSortModel<DataAuthority>(getRequest(), "dataAuthorityList");
		putToRequest("dataAuthorityList", dataAuthorityService.listForEc(psm,
		                dataAuthority));
	        return SUCCESS;
	}
	public String  query_list_main()
	{
	        putToRequest("dataId", dataAuthority.getDataId());
	        return SUCCESS;
	}

	private String dataId;
	private UserInfo userInfo;
	public String  query_list_list()
	{

	        PageSortModel<UserInfo> psm = new PageSortModel<UserInfo>(getRequest(), "userInfoList");
	        if(dataId==null||dataId.equals(""))
	        {
	                dataAuthority = dataAuthorityService.get(dataAuthority.getDataId());
	        }else {
	                dataAuthority = dataAuthorityService.get(dataId);
                }
	        List<DataAuthority> dataAuthoritiesChrild=dataAuthorityService.listForChrild(dataAuthority);
	        List<UserInfo> userInfoList=dataAuthorityService.listForUserInfos(psm,dataAuthority,userInfo);
	        putToRequest("userInfoList", userInfoList);
	        putToRequest("dataAuthoritiesChrild", dataAuthoritiesChrild);
	        return SUCCESS;
	}
	public String  query_list_stype()
	{
	        dataAuthority = dataAuthorityService.get(dataId);
	        List<Map<String, Object>> secrecySystemCategoryTree = dataAuthorityService.getTree(dataAuthority);
                setResultData(secrecySystemCategoryTree);
                return JSON;
	}


        public DataAuthority getDataAuthority() {
                return dataAuthority;
        }


        public void setDataAuthority(DataAuthority dataAuthority) {
                this.dataAuthority = dataAuthority;
        }


        public DataAuthorityService getDataAuthorityService() {
                return dataAuthorityService;
        }


        public void setDataAuthorityService(DataAuthorityService dataAuthorityService) {
                this.dataAuthorityService = dataAuthorityService;
        }


        public Boolean getNeedReload() {
                return needReload;
        }


        public void setNeedReload(Boolean needReload) {
                this.needReload = needReload;
        }


        public String getDataAuthorityIds() {
                return dataAuthorityIds;
        }


        public void setDataAuthorityIds(String dataAuthorityIds) {
                this.dataAuthorityIds = dataAuthorityIds;
        }


        public List<DataAuthority> getDataAuthoritys() {
                return dataAuthoritys;
        }


        public void setDataAuthoritys(List<DataAuthority> dataAuthoritys) {
                this.dataAuthoritys = dataAuthoritys;
        }


        public String getDataId() {
                return dataId;
        }


        public void setDataId(String dataId) {
                this.dataId = dataId;
        }

        public UserInfo getUserInfo() {
                return userInfo;
        }

        public void setUserInfo(UserInfo userInfo) {
                this.userInfo = userInfo;
        }

        public String getLeixin() {
                return leixin;
        }

        public void setLeixin(String leixin) {
                this.leixin = leixin;
        }


}
