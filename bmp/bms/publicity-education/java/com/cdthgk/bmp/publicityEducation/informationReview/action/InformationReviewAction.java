/**
 *
 */
package com.cdthgk.bmp.publicityEducation.informationReview.action;

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
import com.cdthgk.bmp.publicityEducation.informationReview.service.InformationReviewService;
import com.cdthgk.bmp.publicityEducation.informationReview.vo.InformationReview;
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
 * 信息发布保密审查情况
 * </p>
 * <p>
 * 创建人 宋亚非  创建时间 2014-5-5 - 上午11:28:03
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
public class InformationReviewAction extends BmpAction {

        private InformationReviewService  informationReviewService;

        private DataValidateService dataValidateService;

        private InformationReview informationReview;

        private String deleteIds;

        private List<String> infoSources;
        private List<String> releaseWays;

        private District district;
        private String fromQuery;
        private String checkLower;

        /**
         *
         * <p>
         * 方法的说明
         * </p>
         * <p>
         * 创建人 宋亚非  创建时间 2014-5-21 上午9:19:14
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
                        district = informationReviewService.get(district.getDistrictCode(), District.class);
                }
                boolean flag = false;
                // 判断查询列表过来的1查看保密局，0不是查询列表过来的查看本单位
                if("1".equals(fromQuery)){
                        flag = true;
                }

                List<InformationReview> dataList = new ArrayList<InformationReview>();
                PageSortModel<InformationReview> psm = new PageSortModel<InformationReview>(getRequest(), "dataList");

                dataList = this.informationReviewService.getListPage(psm, informationReview, getCurrentUser().getOrgan(),
                                district, flag, checkLower);

                this.putToRequest("dataList", dataList);
                return SUCCESS;
        }


        public String add(){

                return SUCCESS;
        }

        public String list2Str(List<String> strList){
                StringBuilder returnStr = new StringBuilder("");
                if( strList!=null && strList.size()>0 ){
                        int f = 0;
                        for( String str : strList ){
                                returnStr.append(str);
                                f++;
                                if( f != strList.size() ){
                                        returnStr.append(",");
                                }
                        }
                }
                return returnStr.toString();
        }

        public String adding(){

                informationReview.setInformationSources(list2Str(this.getInfoSources()));
                informationReview.setReleaseWay(list2Str(this.getReleaseWays()));

                informationReview.setCreatePerson(this.getCurrentUser().getUserInfo());
                informationReview.setCreateTime(new Date());
                informationReview.setCreateOrgan(this.getCurrentUser().getUserInfo().getOrgan());
                informationReview.setStatus(1);
                this.informationReviewService.save(informationReview);
                BusinessLog log = new BusinessLog();
        		log.setBusinessName("信息发布保密审查情况");
        		log.setPrimaryKey(informationReview.getInformationReviewId());
        		BusinessLogContext.getInstance().getBusinessLogService().saveAddBusinessLogByModule(getCurrentUser(), log, informationReview);
                return redirectActionResult(LIST);
        }

        public String edit(){
                if(informationReview!=null && informationReview.getInformationReviewId() !=null && !"".equals(informationReview.getInformationReviewId())){
                        informationReview = this.informationReviewService.get(informationReview.getInformationReviewId());
                        if(informationReview!=null){
                                this.putToRequest("informationReview", informationReview);
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
        	InformationReview beforeIr=new InformationReview();
                if(informationReview!=null && informationReview.getInformationReviewId() !=null && !"".equals(informationReview.getInformationReviewId())){
                        InformationReview irDB = this.informationReviewService.get(informationReview.getInformationReviewId() );
                        BeanUtils.copyProperties(beforeIr, irDB, CopyRuleEnum.ignoreCaseNull);
                        irDB.setTitle(informationReview.getTitle());
                        irDB.setContent(informationReview.getContent());
                        irDB.setInformationSources(list2Str(this.getInfoSources()));
                        irDB.setUndertakingDepartment(informationReview.getUndertakingDepartment());
                        irDB.setReleaseWay(list2Str(this.getReleaseWays()));
                        irDB.setInitialOpinions(informationReview.getInitialOpinions());
                        irDB.setProfessionalOpinion(informationReview.getProfessionalOpinion());
                        irDB.setUnitLeadersOpinions(informationReview.getUnitLeadersOpinions());
                        irDB.setRemark(informationReview.getRemark());
                        irDB.setModifyPerson(this.getCurrentUser().getUserInfo());
                        irDB.setModifyTime(new Date());
                        this.informationReviewService.update(irDB);
                        this.addActionMessage("修改成功");
                        BusinessLog log = new BusinessLog();
                		log.setBusinessName("信息发布保密审查情况");
                		log.setPrimaryKey(irDB.getInformationReviewId());
                		BusinessLogContext.getInstance().getBusinessLogService().saveEditBusinessLogByModule(getCurrentUser(), log, irDB, beforeIr);
                }else{
                        this.addActionMessage("修改失败");
                }
                return redirectActionResult(LIST);
        }

        public String delete(){
                if( deleteIds!=null && !"".equals(deleteIds) ){
                        this.informationReviewService.deleteSelected(deleteIds);
                        this.addActionMessage("删除成功");
                        for (String id : deleteIds.split(",")) {
                			BusinessLog log = new BusinessLog();
                			log.setBusinessName("信息发布保密审查情况");
                			log.setPrimaryKey(id);
                			BusinessLogContext.getInstance().getBusinessLogService().saveDelBusinessLogByModule(getCurrentUser(), log, new InformationReview());
                		}
                }else{
                        this.addActionMessage("删除失败");
                }

                return redirectActionResult(LIST);
        }

        public String detail(){
                if(informationReview!=null && informationReview.getInformationReviewId() !=null && !"".equals(informationReview.getInformationReviewId())){
                        informationReview = informationReviewService.get(informationReview.getInformationReviewId());
                        if(informationReview!=null){
                                this.putToApplication("informationReview", informationReview);
                        }
                }
                return SUCCESS;
        }

        public String export(){
                List<InformationReview> dataList = new ArrayList<InformationReview>();
                dataList = this.informationReviewService.getListPage(null, informationReview, getCurrentUser().getOrgan(), null, false, null);
                if( dataList!=null && dataList.size()>0 ){
                    for( InformationReview ir : dataList ){
                        ir.setInformationSourcesTxt(informationReviewService.dealOptions2Txt("bmp","infoSource",ir.getInformationSources()));
                        ir.setReleaseWayTxt(informationReviewService.dealOptions2Txt("bmp","releaseWay",ir.getReleaseWay()));
                    }
                }

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
	   		List<InformationReview> informationReviewList = informationReviewService.getListPage(null, informationReview, informationReviewService.get(organId, Organ.class), null, false, null);
	   		putToRequest("informationReviewList", informationReviewList);
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
                       resultData.put("id", "17");
                       List<InformationReview> informationReviewList = informationReviewService.getListPage(null, informationReview, getCurrentUser().getOrgan(), null, false, null);
                       String msg = dataValidateService.validateData("信息发布保密审查情况", informationReviewList, "17");
                       resultData.put("msg", msg);
                       setResultData(resultData);
                       return JSON;
               }


        /************************************************* getter && setter *********************************************************************/

        /**
         * @return the informationReviewService
         */
        public InformationReviewService getInformationReviewService() {
                return informationReviewService;
        }

        /**
         * @param informationReviewService the informationReviewService to set
         */
        public void setInformationReviewService(InformationReviewService informationReviewService) {
                this.informationReviewService = informationReviewService;
        }

        /**
         * @return the informationReview
         */
        public InformationReview getInformationReview() {
                return informationReview;
        }

        /**
         * @param informationReview the informationReview to set
         */
        public void setInformationReview(InformationReview informationReview) {
                this.informationReview = informationReview;
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
         * @return the infoSources
         */
        public List<String> getInfoSources() {
                return infoSources;
        }


        /**
         * @param infoSources the infoSources to set
         */
        public void setInfoSources(List<String> infoSources) {
                this.infoSources = infoSources;
        }


        /**
         * @return the releaseWay
         */
        public List<String> getReleaseWays() {
                return releaseWays;
        }


        /**
         * @param releaseWay the releaseWay to set
         */
        public void setReleaseWays(List<String> releaseWays) {
                this.releaseWays = releaseWays;
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
