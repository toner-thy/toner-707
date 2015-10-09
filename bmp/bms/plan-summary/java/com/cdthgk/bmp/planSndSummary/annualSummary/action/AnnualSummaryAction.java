/**
 *
 */
package com.cdthgk.bmp.planSndSummary.annualSummary.action;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.cdthgk.bmp.core.action.BmpAction;
import com.cdthgk.bmp.planSndSummary.annualPlan.vo.AnnualPlan;
import com.cdthgk.bmp.planSndSummary.annualSummary.service.AnnualSummaryService;
import com.cdthgk.bmp.planSndSummary.annualSummary.vo.AnnualSummary;
import com.cdthgk.bmp.statinfo.dto.QueryDto;
import com.cdthgk.common.lang.CollectionUtils;
import com.cdthgk.platform.attachment.service.AttachmentService;
import com.cdthgk.platform.dataValidate.service.DataValidateService;
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
public class AnnualSummaryAction extends BmpAction{
        /**
         *
         */
        private static final long serialVersionUID = 1L;

        private AnnualSummaryService annualSummaryService;

        private DataValidateService dataValidateService;

        private AnnualSummary annualSummary;

        private String deleteIds;
        // 附件使用字段
        private AttachmentService attachmentService;
        private List<String> attachs;

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


        /**
         *
         * <p>
         * 方法的说明
         * </p>
         * <p>
         * 创建人 宋亚非  创建时间 2014-4-30 上午10:04:29
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
                } else {
                        district = annualSummaryService.get(district.getDistrictCode(), District.class);
                }
                boolean flag = false;
                // 判断查询列表过来的1查看保密局，0不是查询列表过来的查看本单位
                if("1".equals(fromQuery)){
                        flag = true;
                }

                PageSortModel<AnnualSummary> psm = new PageSortModel<AnnualSummary>(getRequest(), "dataList");
                List<AnnualSummary> dataList = this.annualSummaryService.getListPage(psm, annualSummary, getCurrentUser().getOrgan(),
                                district, flag, checkLower);
                this.putToRequest("dataList", dataList);
                return SUCCESS;
        }

        /**
         *
         * <p>
         * 方法的说明
         * </p>
         * <p>
         * 创建人 宋亚非  创建时间 2014-4-30 上午10:41:07
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
                annualSummary = new AnnualSummary();
                annualSummary.setAnnualSummaryYear(cl.get(cl.YEAR));
                this.putToRequest("annualSummary", annualSummary);
                return SUCCESS;
        }

        /**
         *
         * <p>
         * 方法的说明
         * </p>
         * <p>
         * 创建人 宋亚非  创建时间 2014-4-30 上午10:41:11
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
                annualSummary.setCreatePerson(this.getCurrentUser().getUserInfo());
                annualSummary.setCreateTime(new Date());
                annualSummary.setCreateOrgan(this.getCurrentUser().getOrgan());
                if(annualSummary.getBmAnnualPlan()!=null && annualSummary.getBmAnnualPlan().getAnnualPlanId()!=null && !"".equals(annualSummary.getBmAnnualPlan().getAnnualPlanId())){
                   //do nothing
                }else{
                        annualSummary.setBmAnnualPlan(null);
                }
                annualSummary.setStatus(1);
                this.annualSummaryService.save(annualSummary);
                // 上传附件
                attachmentService.updateHostId(annualSummary.getAnnualSummaryId(),attachs);
                this.addActionMessage("添加成功");
                return redirectActionResult(LIST);
        }

        /**
         *
         * <p>
         * 方法的说明
         * </p>
         * <p>
         * 创建人 宋亚非  创建时间 2014-4-30 上午10:41:15
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
                if( annualSummary!=null && annualSummary.getAnnualSummaryId()!=null && !"".equals(annualSummary.getAnnualSummaryId()) ){
                        annualSummary = this.annualSummaryService.get(annualSummary.getAnnualSummaryId());
                        if(annualSummary!=null){
                                this.putToRequest("annualSummary", annualSummary);
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
         * 创建人 宋亚非  创建时间 2014-4-30 上午10:41:55
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
                if( annualSummary.getAnnualSummaryId()!=null && !"".equals(annualSummary.getAnnualSummaryId()) ){
                        AnnualSummary annualSummaryDB = this.annualSummaryService.get(annualSummary.getAnnualSummaryId());
                        annualSummaryDB.setAnnualSummaryTitle(annualSummary.getAnnualSummaryTitle());
                        annualSummaryDB.setAnnualSummaryYear(annualSummary.getAnnualSummaryYear());
                        annualSummaryDB.setAnnualSummaryContent(annualSummary.getAnnualSummaryContent());
                        annualSummaryDB.setAnnualSummaryContentPre(annualSummary.getAnnualSummaryContentPre());
                        if(annualSummary.getBmAnnualPlan()!=null && annualSummary.getBmAnnualPlan().getAnnualPlanId()!=null && !"".equals(annualSummary.getBmAnnualPlan().getAnnualPlanId())){
                                AnnualPlan ap = new AnnualPlan();
                                ap.setAnnualPlanId(annualSummary.getBmAnnualPlan().getAnnualPlanId());
                                annualSummaryDB.setBmAnnualPlan(ap);
                        }else{
                             annualSummary.setBmAnnualPlan(null);
                        }
                        annualSummaryDB.setModifyPerson(this.getCurrentUser().getUserInfo());
                        annualSummaryDB.setModifyTime(new Date());
                        this.annualSummaryService.update(annualSummaryDB);
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
         * 创建人 宋亚非  创建时间 2014-4-30 上午10:41:58
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
                        this.annualSummaryService.deleteSelected(deleteIds);
                        this.addActionMessage("删除成功");
                }else{
                        this.addActionMessage("删除失败");
                }

                return redirectActionResult(LIST);
        }

        /**
         *
         * <p>
         * 方法的说明
         * </p>
         * <p>
         * 创建人 宋亚非  创建时间 2014-4-30 上午10:42:02
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
                if( annualSummary!=null && annualSummary.getAnnualSummaryId()!=null && !"".equals(annualSummary.getAnnualSummaryId()) ){
                        annualSummary = this.annualSummaryService.get(annualSummary.getAnnualSummaryId());
                        if(annualSummary!=null){
                                this.putToRequest("annualSummary", annualSummary);
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
         * 创建人 宋亚非  创建时间 2014-4-30 上午10:42:06
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
                if( annualSummary!=null && annualSummary.getAnnualSummaryId()!=null && !"".equals(annualSummary.getAnnualSummaryId()) ){
                        annualSummary = this.annualSummaryService.get(annualSummary.getAnnualSummaryId());
                        if(annualSummary!=null){
                                this.putToRequest("annualSummary", annualSummary);
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
	   		List<AnnualSummary> dataList = this.annualSummaryService.getListPage(null, annualSummary, annualSummaryService.get(organId, Organ.class),null, false, null);
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
	   		resultData.put("id", "14");
	   		List<AnnualSummary> dataList = this.annualSummaryService.getListPage(null, null, getCurrentUser().getOrgan(),null, false, null);
	                String msg = dataValidateService.validateData("保密工作领导小组年度工作总结", dataList, "14");
	   		resultData.put("msg", msg);
	   		setResultData(resultData);
	   		return JSON;
	   	}

        /*------------------------------- getter && setter ----------------------------------------*/
        /**
         * @return the annualSummaryService
         */
        public AnnualSummaryService getAnnualSummaryService() {
                return annualSummaryService;
        }

        /**
         * @param annualSummaryService the annualSummaryService to set
         */
        public void setAnnualSummaryService(AnnualSummaryService annualSummaryService) {
                this.annualSummaryService = annualSummaryService;
        }

        /**
         * @return the annualSummary
         */
        public AnnualSummary getAnnualSummary() {
                return annualSummary;
        }

        /**
         * @param annualSummary the annualSummary to set
         */
        public void setAnnualSummary(AnnualSummary annualSummary) {
                this.annualSummary = annualSummary;
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
