/**
 *
 */
package com.cdthgk.bmp.publicityEducation.publicityEducation.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.action.BmpAction;
import com.cdthgk.bmp.publicityEducation.publicityEducation.service.PublicityeducationService;
import com.cdthgk.bmp.publicityEducation.publicityEducation.vo.Publicityeducation;
import com.cdthgk.bmp.statinfo.dto.QueryDto;
import com.cdthgk.common.lang.CollectionUtils;
import com.cdthgk.platform.dataValidate.service.DataValidateService;
import com.cdthgk.platform.dictionary.context.DictionaryContext;
import com.cdthgk.platform.dictionary.spi.DictionaryService;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;

/**
 * <p>
 * 宣传教育培训情况
 * </p>
 * <p>
 * 创建人 宋亚非  创建时间 2014-5-5 - 上午11:28:39
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
public class PublicityEducationAction extends BmpAction {

        private PublicityeducationService publicityeducationService;

        private DataValidateService dataValidateService;

        private Publicityeducation publicityeducation;

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
                        district = publicityeducationService.get(district.getDistrictCode(), District.class);
                }
                boolean flag = false;
                // 判断查询列表过来的1查看保密局，0不是查询列表过来的查看本单位
                if("1".equals(fromQuery)){
                        flag = true;
                }

                List<Publicityeducation> dataList = new ArrayList<Publicityeducation>();
                PageSortModel<Publicityeducation> psm = new PageSortModel<Publicityeducation>(getRequest(), "dataList");

                dataList = this.publicityeducationService.getListPage(psm, publicityeducation, getCurrentUser().getOrgan(),
                                district, flag, checkLower);

                this.putToRequest("dataList", dataList);
                return SUCCESS;
        }


        public String add(){

                return SUCCESS;
        }

        public String adding(){
                publicityeducation.setCreatePerson(this.getCurrentUser().getUserInfo());
                publicityeducation.setCreateTime(new Date(System.currentTimeMillis()));
                publicityeducation.setCreateOrgan(this.getCurrentUser().getUserInfo().getOrgan());
                publicityeducation.setStatus(1);
                this.publicityeducationService.save(publicityeducation);
                this.addActionMessage("添加成功");
                return redirectActionResult(LIST);
        }

        public String edit(){
                if(publicityeducation!=null && publicityeducation.getPublicityeducationId()!=null && !"".equals(publicityeducation.getPublicityeducationId()) ){
                        publicityeducation = this.publicityeducationService.get(publicityeducation.getPublicityeducationId());
                        if(publicityeducation!=null){
                                this.putToRequest("publicityeducation", publicityeducation);
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
                if( publicityeducation!=null && publicityeducation.getPublicityeducationId()!=null && !"".equals(publicityeducation.getPublicityeducationId())){
                        Publicityeducation peDB = publicityeducationService.get(publicityeducation.getPublicityeducationId());
                        peDB.setTrainTime(publicityeducation.getTrainTime());
                        peDB.setTrainRange(publicityeducation.getTrainRange());
                        peDB.setForm(publicityeducation.getForm());
                        peDB.setContent(publicityeducation.getContent());
                        peDB.setModifyPerson(this.getCurrentUser().getUserInfo());
                        peDB.setModifyTime(new Date());
                        this.publicityeducationService.update(peDB);
                        this.addActionError("修改成功");
                }else{
                        this.addActionError("修改失败");
                }
                return redirectActionResult(LIST);
        }

        public String delete(){
                if( deleteIds!=null && !"".equals(deleteIds) ){
                        this.publicityeducationService.deleteSelected(deleteIds);
                        this.addActionMessage("删除成功");
                }else{
                        this.addActionMessage("删除失败");
                }

                return redirectActionResult(LIST);
        }

        public String detail(){
                if( publicityeducation!=null && publicityeducation.getPublicityeducationId()!=null && !"".equals(publicityeducation.getPublicityeducationId())){
                        publicityeducation = publicityeducationService.get(publicityeducation.getPublicityeducationId());
                        if(publicityeducation!=null){
                                this.putToApplication("publicityeducation", publicityeducation);
                        }
                }
                return SUCCESS;
        }

        public String export(){
                List<Publicityeducation> dataList = new ArrayList<Publicityeducation>();
                dataList = this.publicityeducationService.getListPage(null, publicityeducation, getCurrentUser().getOrgan(), null ,false, null);

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
	   		List<Publicityeducation> publicityeducationList = publicityeducationService.getListPage(null, publicityeducation, publicityeducationService.get(organId, Organ.class),null,false, null);
	   		putToRequest("publicityeducationList", publicityeducationList);
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
                       resultData.put("id", "15");
                       List<Publicityeducation> publicityeducationList = publicityeducationService.getListPage(null, publicityeducation, getCurrentUser().getOrgan(),null,false, null);
                       String msg = dataValidateService.validateData("宣传教育培训情况", publicityeducationList, "15");
                       resultData.put("msg", msg);
                       setResultData(resultData);
                       return JSON;
               }

        /************************************************* getter && setter *********************************************************************/

        /**
         * @return the publicityeducationService
         */
        public PublicityeducationService getPublicityeducationService() {
                return publicityeducationService;
        }

        /**
         * @param publicityeducationService the publicityeducationService to set
         */
        public void setPublicityeducationService(PublicityeducationService publicityeducationService) {
                this.publicityeducationService = publicityeducationService;
        }

        /**
         * @return the publicityeducation
         */
        public Publicityeducation getPublicityeducation() {
                return publicityeducation;
        }

        /**
         * @param publicityeducation the publicityeducation to set
         */
        public void setPublicityeducation(Publicityeducation publicityeducation) {
                this.publicityeducation = publicityeducation;
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
