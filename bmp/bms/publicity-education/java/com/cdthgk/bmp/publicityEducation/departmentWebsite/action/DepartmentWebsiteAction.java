/**
 *
 */
package com.cdthgk.bmp.publicityEducation.departmentWebsite.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.agenda.vo.Agenda;
import com.cdthgk.bmp.core.action.BmpAction;
import com.cdthgk.bmp.log.context.BusinessLogContext;
import com.cdthgk.bmp.log.vo.BusinessLog;
import com.cdthgk.bmp.publicityEducation.departmentWebsite.service.DepartmentWebsiteService;
import com.cdthgk.bmp.publicityEducation.departmentWebsite.vo.Departmentwebsite;
import com.cdthgk.bmp.statinfo.dto.QueryDto;
import com.cdthgk.common.bean.BeanUtils;
import com.cdthgk.common.bean.rule.CopyRuleEnum;
import com.cdthgk.common.lang.CollectionUtils;
import com.cdthgk.platform.dataValidate.service.DataValidateService;
import com.cdthgk.platform.dictionary.context.DictionaryContext;
import com.cdthgk.platform.dictionary.spi.DictionaryService;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;

/**
 * <p>
 * 部门网站管理情况
 * </p>
 * <p>
 * 创建人 宋亚非  创建时间 2014-5-5 - 上午11:26:54
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
public class DepartmentWebsiteAction extends BmpAction{
        private DepartmentWebsiteService departmentWebsiteService;

        private Departmentwebsite departmentWebsite;

        private DataValidateService dataValidateService;

        private String deleteIds;

        private District district;
        private String fromQuery;
        private String checkLower;

        /**
        *
        * <p>
        * 方法的说明
        * </p>
        * <p>
        * 创建人 宋亚非  创建时间 2014-5-20 下午2:51:24
        * </p>
        * <blockquote>
        * <h4>历史修改记录</h4>
        * <ul>
        * <li>修改人 修改时间 修改描述
        * </ul>
        * </blockquote>
        * @return
        */
       public String main(){
               district = getCurrentUser().getOrgan().getDistrict();
               return SUCCESS;
       }

        public String list(){

                // 统计中判断行政区代码
                if(district == null){
                        district = getCurrentUser().getOrgan().getDistrict();
                } else {
                        district = departmentWebsiteService.get(district.getDistrictCode(), District.class);
                }
                boolean flag = false;
                // 判断查询列表过来的1查看保密局，0不是查询列表过来的查看本单位
                if("1".equals(fromQuery)){
                        flag = true;
                }

                List<Departmentwebsite> dataList = new ArrayList<Departmentwebsite>();
                PageSortModel<Departmentwebsite> psm = new PageSortModel<Departmentwebsite>(getRequest(), "dataList");

                dataList = this.departmentWebsiteService.getListPage(psm, departmentWebsite, getCurrentUser().getOrgan(),
                                district, flag, checkLower);

                this.putToRequest("dataList", dataList);
                return SUCCESS;
        }


        public String add(){

                return SUCCESS;
        }

        public String adding(){
                departmentWebsite.setCreatePerson(this.getCurrentUser().getUserInfo());
                departmentWebsite.setCreateTime(new Date());
                departmentWebsite.setCreateOrgan(this.getCurrentUser().getUserInfo().getOrgan());
                departmentWebsite.setStatus(1);
                this.departmentWebsiteService.save(departmentWebsite);
                this.addActionMessage("添加成功");
                BusinessLog log = new BusinessLog();
        		log.setBusinessName("部门网站管理");
        		log.setPrimaryKey(departmentWebsite.getDepartmentwebsiteId());
        		BusinessLogContext.getInstance().getBusinessLogService().saveAddBusinessLogByModule(getCurrentUser(), log, new Departmentwebsite());
                return redirectActionResult(LIST);
        }

        public String edit(){
                if(departmentWebsite!=null && departmentWebsite.getDepartmentwebsiteId()!=null && !"".equals(departmentWebsite.getDepartmentwebsiteId())){
                        departmentWebsite = this.departmentWebsiteService.get(departmentWebsite.getDepartmentwebsiteId());
                        if(departmentWebsite!=null){
                                this.putToRequest("departmentWebsite", departmentWebsite);
                                return SUCCESS;
                        }else{
                                this.addActionMessage("获取信息失败，请重试");
                                return redirectActionResult(LIST);
                        }
                }else{
                        this.addActionMessage("获取信息失败，请重试");
                        return redirectActionResult(LIST);
                }
        }

        public String editing(){
        		Departmentwebsite beforeD=new Departmentwebsite();
                if(departmentWebsite!=null && departmentWebsite.getDepartmentwebsiteId()!=null && !"".equals(departmentWebsite.getDepartmentwebsiteId())){
                        Departmentwebsite dwDB = this.departmentWebsiteService.get(departmentWebsite.getDepartmentwebsiteId());
                        BeanUtils.copyProperties(beforeD, dwDB, CopyRuleEnum.ignoreCaseNull);
                        dwDB.setWebsiteName(departmentWebsite.getWebsiteName());
                        dwDB.setAdminName(departmentWebsite.getAdminName());
                        dwDB.setAdminPost(departmentWebsite.getAdminPost());
                        dwDB.setDepartment(departmentWebsite.getDepartment());
                        dwDB.setModifyPerson(this.getCurrentUser().getUserInfo());
                        dwDB.setModifyTime(new Date());
                        this.departmentWebsiteService.update(dwDB);
                        this.addActionMessage("修改成功");
                        BusinessLog log = new BusinessLog();
                		log.setBusinessName("部门网站管理");
                		log.setPrimaryKey(departmentWebsite.getDepartmentwebsiteId());
                		BusinessLogContext.getInstance().getBusinessLogService().saveEditBusinessLogByModule(getCurrentUser(), log, dwDB, beforeD);
                }else{
                        this.addActionMessage("修改失败");
                }
                return redirectActionResult(LIST);
        }

        public String delete(){
                if( deleteIds!=null && !"".equals(deleteIds) ){
                        this.departmentWebsiteService.deleteSelected(deleteIds);
                        this.addActionMessage("删除成功");
                        for (String id : deleteIds.split(",")) {
                			BusinessLog log = new BusinessLog();
                			log.setBusinessName("部门网站管理");
                			log.setPrimaryKey(id);
                			BusinessLogContext.getInstance().getBusinessLogService().saveDelBusinessLogByModule(getCurrentUser(), log, new Departmentwebsite());
                		}
                }else{
                        this.addActionMessage("删除失败");
                }

                return redirectActionResult(LIST);
        }

        public String detail(){
                if(departmentWebsite!=null && departmentWebsite.getDepartmentwebsiteId()!=null && !"".equals(departmentWebsite.getDepartmentwebsiteId())){
                        departmentWebsite = departmentWebsiteService.get(departmentWebsite.getDepartmentwebsiteId());
                        if(departmentWebsite!=null){
                                this.putToApplication("departmentWebsite", departmentWebsite);
                        }
                }
                return SUCCESS;
        }


        public String export(){
                List<Departmentwebsite> dataList = new ArrayList<Departmentwebsite>();
                dataList = this.departmentWebsiteService.getListPage(null, departmentWebsite, getCurrentUser().getOrgan(), null, false, null);

                Map<String, Object> params = new HashMap<String, Object>();
                // 被导出的数据
                params.put("dataList", dataList);
                // 时间格式化器
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                params.put("dateFormat", dateFormat);
                // 数据字典工具
                DictionaryService dictionary = DictionaryContext.getInstance()
                                .getDictionaryService();
                params.put("dictionary", dictionary);
                // 数据类型转换
                params.put("Integer", Integer.class);

                setResultData(params);
                return SUCCESS;
        }


        /**
	   	 *
	   	 * <p>
	   	 * 保密工作信息总览获取数据
	   	 * </p>
	   	 * <p>
	   	 * 创建人 陶汇源  创建时间  2014-5-7 - 下午2:28:35
	   	 * </p>
	   	 * <blockquote>
	   	 * <h4>历史修改记录</h4>
	   	 * <ul>
	   	 * <li>修改人 修改时间 修改描述
	   	 * </ul>
	   	 * </blockquote>
	   	 * @return
	   	 */
	   	public String organIndex(){
	   		// TODO 暂无考虑时间查询，时间设计到业务模块数据变动历史情况，需要先调整业务模块
	   		String organId = getRequest().getParameter("organId").toString();
	   		QueryDto queryDto = new QueryDto();
	   		queryDto.setYear(Integer.parseInt(getRequest().getParameter("queryDto.year").toString()));
	   		queryDto.setMonth(Integer.parseInt(getRequest().getParameter("queryDto.month").toString()));
	   		List<Departmentwebsite> departmentwebsiteList = departmentWebsiteService.getListPage(null, departmentWebsite
	   				, departmentWebsiteService.get(organId,Organ.class), null, false, null);
	   		putToRequest("departmentwebsiteList", departmentwebsiteList);
	   		return SUCCESS;
	   	}

	        /**
                *
                * <p>
                * 数据填写校验
                * </p>
                * <p>
                * 创建人 陶汇源  创建时间  2014-5-26 - 上午9:53:11
                * </p>
                * <blockquote>
                * <h4>历史修改记录</h4>
                * <ul>
                * <li>修改人 修改时间 修改描述
                * </ul>
                * </blockquote>
                * @return
                */
               public String dataValidate(){
                       Map<String, Object> resultData = new HashMap<String, Object>();
                       // ID值为bm_business_module中的ID
                       resultData.put("id", "18");
                       List<Departmentwebsite> departmentwebsiteList = departmentWebsiteService.getListPage(null, departmentWebsite
                                       , getCurrentUser().getOrgan(), null, false, null);
                       String msg = dataValidateService.validateData("部门网站管理", departmentwebsiteList, "18");
                       resultData.put("msg", msg);
                       setResultData(resultData);
                       return JSON;
               }

        /************************************************* getter && setter *********************************************************************/

        /**
         * @return the departmentWebsiteService
         */
        public DepartmentWebsiteService getDepartmentWebsiteService() {
                return departmentWebsiteService;
        }

        /**
         * @param departmentWebsiteService the departmentWebsiteService to set
         */
        public void setDepartmentWebsiteService(DepartmentWebsiteService departmentWebsiteService) {
                this.departmentWebsiteService = departmentWebsiteService;
        }

        /**
         * @return the departmentWebsite
         */
        public Departmentwebsite getDepartmentWebsite() {
                return departmentWebsite;
        }

        /**
         * @param departmentWebsite the departmentWebsite to set
         */
        public void setDepartmentWebsite(Departmentwebsite departmentWebsite) {
                this.departmentWebsite = departmentWebsite;
        }

        /**
         * @return the deleteIds
         */
        public String getDeleteIds() {
                return deleteIds;
        }

        /**
         * @param deleteIds the deleteIds to set
         */
        public void setDeleteIds(String deleteIds) {
                this.deleteIds = deleteIds;
        }


        /**
         * @return the district
         */
        public District getDistrict() {
                return district;
        }


        /**
         * @param district the district to set
         */
        public void setDistrict(District district) {
                this.district = district;
        }


        /**
         * @return the fromQuery
         */
        public String getFromQuery() {
                return fromQuery;
        }


        /**
         * @param fromQuery the fromQuery to set
         */
        public void setFromQuery(String fromQuery) {
                this.fromQuery = fromQuery;
        }


        /**
         * @return the checkLower
         */
        public String getCheckLower() {
                return checkLower;
        }


        /**
         * @param checkLower the checkLower to set
         */
        public void setCheckLower(String checkLower) {
                this.checkLower = checkLower;
        }

        /**
         * @return the dataValidateService
         */
        public DataValidateService getDataValidateService() {
                return dataValidateService;
        }

        /**
         * @param dataValidateService the dataValidateService to set
         */
        public void setDataValidateService(DataValidateService dataValidateService) {
                this.dataValidateService = dataValidateService;
        }



}
