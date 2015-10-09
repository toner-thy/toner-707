/**
 *
 */
package com.cdthgk.bmp.planSndSummary.annualPlan.action;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.cdthgk.bmp.core.action.BmpAction;
import com.cdthgk.bmp.planSndSummary.annualPlan.service.AnnualPlanService;
import com.cdthgk.bmp.planSndSummary.annualPlan.vo.AnnualPlan;
import com.cdthgk.bmp.statinfo.dto.QueryDto;
import com.cdthgk.common.lang.CollectionUtils;
import com.cdthgk.platform.attachment.service.AttachmentService;
import com.cdthgk.platform.dataValidate.service.DataValidateService;
import com.cdthgk.platform.dataValidate.service.impl.DataValidateServiceImpl;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;

/**
 * <p>
 * 类的说明放这里
 * </p>
 * <p>
 * 创建人 宋亚非  创建时间 2014-4-28 - 下午5:00:03
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
public class AnnualPlanAction extends BmpAction{

        /**
		 *
		 */
        private static final long serialVersionUID = -6220376079965552880L;

	private AnnualPlanService annualPlanService;

	private DataValidateService dataValidateService;

        private AnnualPlan annualPlan;

        private String deleteIds;

        private District district;
        private String fromQuery;
        private String checkLower;

        // 附件使用字段
        private AttachmentService attachmentService;
        private List<String> attachs;


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

        /**
         *
         * <p>
         * 方法的说明
         * </p>
         * <p>
         * 创建人 宋亚非  创建时间 2014-4-29 下午2:15:51
         * </p>
         * <blockquote>
         * <h4>历史修改记录</h4>
         * <ul>
         * <li>修改人 修改时间 修改描述
         * </ul>
         * </blockquote>
         * @return
         */
        public String list(){
             // 统计中判断行政区代码
                if(district == null){
                        district = getCurrentUser().getOrgan().getDistrict();
                        System.out.println(district.getDistrictName());
                } else {
                        district = annualPlanService.get(district.getDistrictCode(), District.class);
                }
                boolean flag = false;
                // 判断查询列表过来的1查看保密局，0不是查询列表过来的查看本单位
                if("1".equals(fromQuery)){
                        flag = true;
                }

                PageSortModel<AnnualPlan> psm = new PageSortModel<AnnualPlan>(getRequest(), "dataList");
                List<AnnualPlan> dataList = this.annualPlanService.getListPage(psm, annualPlan, getCurrentUser().getOrgan(),
                                district, flag, checkLower);
                this.putToRequest("dataList", dataList);
                this.putToRequest("district", district);
                return SUCCESS;
        }


        /**
         *
         * <p>
         * 方法的说明
         * </p>
         * <p>
         * 创建人 宋亚非  创建时间 2014-4-29 下午2:16:13
         * </p>
         * <blockquote>
         * <h4>历史修改记录</h4>
         * <ul>
         * <li>修改人 修改时间 修改描述
         * </ul>
         * </blockquote>
         * @return
         */
        @SuppressWarnings("static-access")
		public String add(){
                Calendar cl = Calendar.getInstance();
                annualPlan = new AnnualPlan();
                annualPlan.setAnnualPlanYear(cl.get(cl.YEAR));
                this.putToRequest("annualPlan", annualPlan);
                return SUCCESS;
        }

        /**
         *
         * <p>
         * 方法的说明
         * </p>
         * <p>
         * 创建人 宋亚非  创建时间 2014-4-29 下午2:16:28
         * </p>
         * <blockquote>
         * <h4>历史修改记录</h4>
         * <ul>
         * <li>修改人 修改时间 修改描述
         * </ul>
         * </blockquote>
         * @return
         */
        public String adding(){
                annualPlan.setCreatePerson(this.getCurrentUser().getUserInfo());
                annualPlan.setCreateTime(new Date());
                annualPlan.setCreateOrgan(this.getCurrentUser().getOrgan());
                annualPlan.setStatus(1);
                this.annualPlanService.save(annualPlan);
                // 上传附件
                attachmentService.updateHostId(annualPlan.getAnnualPlanId(),attachs);
                this.addActionMessage("添加成功");
                return redirectActionResult(LIST);
        }

        /**
         *
         * <p>
         * 方法的说明
         * </p>
         * <p>
         * 创建人 宋亚非  创建时间 2014-4-29 下午2:17:58
         * </p>
         * <blockquote>
         * <h4>历史修改记录</h4>
         * <ul>
         * <li>修改人 修改时间 修改描述
         * </ul>
         * </blockquote>
         * @return
         */
        public String edit(){
                if( annualPlan!=null && annualPlan.getAnnualPlanId()!=null && !"".equals(annualPlan.getAnnualPlanId()) ){
                        annualPlan = this.annualPlanService.get(annualPlan.getAnnualPlanId());
                        if(annualPlan!=null){
                                this.putToRequest("annualPlan", annualPlan);
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

        /**
         *
         * <p>
         * 方法的说明
         * </p>
         * <p>
         * 创建人 宋亚非  创建时间 2014-4-29 下午2:18:19
         * </p>
         * <blockquote>
         * <h4>历史修改记录</h4>
         * <ul>
         * <li>修改人 修改时间 修改描述
         * </ul>
         * </blockquote>
         * @return
         */
        public String editing(){
                if( annualPlan.getAnnualPlanId()!=null && !"".equals(annualPlan.getAnnualPlanId()) ){
                        AnnualPlan annualPlanDB = this.annualPlanService.get(annualPlan.getAnnualPlanId());
                        annualPlanDB.setAnnualPlanTitle(annualPlan.getAnnualPlanTitle());
                        annualPlanDB.setAnnualPlanYear(annualPlan.getAnnualPlanYear());
                        annualPlanDB.setAnnualPlanContent(annualPlan.getAnnualPlanContent());
                        annualPlanDB.setAnnualPlanContentPre(annualPlan.getAnnualPlanContentPre());
                        annualPlanDB.setModifyPerson(this.getCurrentUser().getUserInfo());
                        annualPlanDB.setModifyTime(new Date());
                        this.annualPlanService.update(annualPlanDB);
                        this.addActionMessage("修改成功");
                }else{
                        this.addActionError("修改失败");
                }
               return redirectActionResult(LIST);
        }

        /**
         *
         * <p>
         * 方法的说明
         * </p>
         * <p>
         * 创建人 宋亚非  创建时间 2014-4-29 下午5:07:08
         * </p>
         * <blockquote>
         * <h4>历史修改记录</h4>
         * <ul>
         * <li>修改人 修改时间 修改描述
         * </ul>
         * </blockquote>
         * @return
         */
        public String delete(){
                if( deleteIds!=null && !"".equals(deleteIds) ){
                        this.annualPlanService.deleteSelected(deleteIds);
                        this.addActionMessage("删除成功");
                }else{
                        this.addActionMessage("删除失败");
                }

                return redirectActionResult(LIST);
        }

        /**
         *
         * <p>
         * 引用
         * </p>
         * <p>
         * 创建人 宋亚非  创建时间 2014-4-29 下午2:34:54
         * </p>
         * <blockquote>
         * <h4>历史修改记录</h4>
         * <ul>
         * <li>修改人 修改时间 修改描述
         * </ul>
         * </blockquote>
         * @return
         */
        public String quote(){
                if( annualPlan!=null && annualPlan.getAnnualPlanId()!=null && !"".equals(annualPlan.getAnnualPlanId()) ){
                        annualPlan = this.annualPlanService.get(annualPlan.getAnnualPlanId());
                        if(annualPlan!=null){
                                this.putToRequest("annualPlan", annualPlan);
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


        /**
         *
         * <p>
         * 方法的说明
         * </p>
         * <p>
         * 创建人 宋亚非  创建时间 2014-4-30 上午9:23:36
         * </p>
         * <blockquote>
         * <h4>历史修改记录</h4>
         * <ul>
         * <li>修改人 修改时间 修改描述
         * </ul>
         * </blockquote>
         * @return
         */
        public String detail(){
                if( annualPlan!=null && annualPlan.getAnnualPlanId()!=null && !"".equals(annualPlan.getAnnualPlanId()) ){
                        annualPlan = this.annualPlanService.get(annualPlan.getAnnualPlanId());
                        if(annualPlan!=null){
                                this.putToRequest("annualPlan", annualPlan);
                        }else{
                                annualPlan = new AnnualPlan();
                        }
                }
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
    		List<AnnualPlan> dataList = this.annualPlanService.getListPage(null, annualPlan, annualPlanService.get(organId, Organ.class),null,false, null);
    		putToRequest("dataList", dataList);
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
	   		resultData.put("id", "13");
	   		List<AnnualPlan> dataList = this.annualPlanService.getListPage(null, annualPlan, getCurrentUser().getOrgan(),null,false, null);
	   		String msg = dataValidateService.validateData("保密工作领导小组年度工作计划", dataList, "13");
	   		resultData.put("msg", msg);
	   		setResultData(resultData);
	   		return JSON;
	   	}

        /*----------------------------------------- getter && setter --------------------------------------------*/
        /**
         * @return the annualPlanService
         */
        public AnnualPlanService getAnnualPlanService() {
                return annualPlanService;
        }

        /**
         * @param annualPlanService the annualPlanService to set
         */
        public void setAnnualPlanService(AnnualPlanService annualPlanService) {
                this.annualPlanService = annualPlanService;
        }

        /**
         * @return the annualPlan
         */
        public AnnualPlan getAnnualPlan() {
                return annualPlan;
        }

        /**
         * @param annualPlan the annualPlan to set
         */
        public void setAnnualPlan(AnnualPlan annualPlan) {
                this.annualPlan = annualPlan;
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
         * @return the attachmentService
         */
        public AttachmentService getAttachmentService() {
                return attachmentService;
        }


        /**
         * @param attachmentService the attachmentService to set
         */
        public void setAttachmentService(AttachmentService attachmentService) {
                this.attachmentService = attachmentService;
        }


        /**
         * @return the attachs
         */
        public List<String> getAttachs() {
                return attachs;
        }


        /**
         * @param attachs the attachs to set
         */
        public void setAttachs(List<String> attachs) {
                this.attachs = attachs;
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
