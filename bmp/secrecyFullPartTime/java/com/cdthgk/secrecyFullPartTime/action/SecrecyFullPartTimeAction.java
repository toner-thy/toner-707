package com.cdthgk.secrecyFullPartTime.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.action.BmpAction;
import com.cdthgk.bmp.log.context.BusinessLogContext;
import com.cdthgk.bmp.log.vo.BusinessLog;
import com.cdthgk.bmp.secrecysystem.secrecyotherdevice.vo.SecrecyOthers;
import com.cdthgk.bmp.statinfo.dto.QueryDto;
import com.cdthgk.common.bean.BeanUtils;
import com.cdthgk.common.bean.rule.CopyRuleEnum;
import com.cdthgk.common.lang.CollectionUtils;
import com.cdthgk.platform.attachment.domain.Attachment;
import com.cdthgk.platform.dataValidate.service.DataValidateService;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.district.service.DistrictService;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.organ.service.OrganService;
import com.cdthgk.platform.permission.user.domain.User;
import com.cdthgk.secrecyFullPartTime.service.SecrecyFullPartTimeService;
import com.cdthgk.secrecyFullPartTime.vo.SecrecyFullPartTime;

import ec.common.PageSortModel;
@SuppressWarnings("all")
public class SecrecyFullPartTimeAction extends BmpAction {
	private SecrecyFullPartTimeService secrecyFullPartTimeService;
	private DataValidateService dataValidateService;
	private SecrecyFullPartTime secrecyFullPartTime;

	private Attachment attachment;
	List<Attachment> attachmentList;
	private String showType;
	private District district;
	private DistrictService districtService;
	private OrganService organService;
	private boolean needReload=false;
	//--------销毁报废情况---------------------------------------------------------

	public String list() {
		PageSortModel psm = new PageSortModel(getRequest(),"list");
		Map<String, Object> params = new HashMap<String, Object>();
		List list = (List) secrecyFullPartTimeService.getPageList(psm, secrecyFullPartTime,params, getCurrentUser());
		this.putToRequest("list", list);
		return SUCCESS;
	}

	public String add() {
		return SUCCESS;
	}

	public String edit() {
		secrecyFullPartTime = secrecyFullPartTimeService.get(secrecyFullPartTime.getId());
		return SUCCESS;
	}
        public boolean isNeedReload() {
                return needReload;
        }

        public void setNeedReload(boolean needReload) {
                this.needReload = needReload;
        }


        public String save() {
                secrecyFullPartTime.setOrganId(getCurrentUser().getOrgan());
		secrecyFullPartTime.setCreatePerson(getCurrentUser());
		secrecyFullPartTime.setState(0);
		secrecyFullPartTime.setCreateTime(new Date());
		secrecyFullPartTimeService.save(secrecyFullPartTime);
		addActionMessage("添加成功!");
		needReload = true;
		BusinessLog log = new BusinessLog();
		log.setBusinessName("保密工作专（兼）职人员情况");
		log.setPrimaryKey(secrecyFullPartTime.getId());
		BusinessLogContext
				.getInstance()
				.getBusinessLogService()
				.saveAddBusinessLogByModule(getCurrentUser(), log,
						new SecrecyFullPartTime());
		return redirectActionResult(LIST);
	}

	public String update() {
			SecrecyFullPartTime beforeSfp=new SecrecyFullPartTime();
	        SecrecyFullPartTime secrecyFullPartTime2=secrecyFullPartTimeService.get(secrecyFullPartTime.getId());
	        BeanUtils.copyProperties(beforeSfp, secrecyFullPartTime2, CopyRuleEnum.ignoreCaseNull);
	        BeanUtils.copyProperties(secrecyFullPartTime2, secrecyFullPartTime, CopyRuleEnum.ignoreCaseNull);
	        secrecyFullPartTime2.setDescription(secrecyFullPartTime.getDescription());
                secrecyFullPartTime2.setModifyPerson(getCurrentUser());
                secrecyFullPartTime2.setModifyTime(new Date());
                secrecyFullPartTimeService.update(secrecyFullPartTime2);
                addActionMessage("修改成功!");
                needReload = true;
                BusinessLog log = new BusinessLog();
    	 		log.setBusinessName("保密工作专（兼）职人员情况");
    	 		log.setPrimaryKey(secrecyFullPartTime2.getId());
    	 		BusinessLogContext
    	 				.getInstance()
    	 				.getBusinessLogService()
    	 				.saveEditBusinessLogByModule(getCurrentUser(), log,
    	 						secrecyFullPartTime2, beforeSfp);
		return redirectActionResult(LIST);
	}


	public String delete() {
	        List<String> strings = this.getIds();
                List<SecrecyFullPartTime> rewardAndPenalties = new ArrayList<SecrecyFullPartTime>();
                for (int i = 0; i < strings.size(); i++) {
                        SecrecyFullPartTime rewardAndPenalty2=secrecyFullPartTimeService.get(strings.get(i));
                        rewardAndPenalty2.setState(1);
                         rewardAndPenalties.add(rewardAndPenalty2);
                         BusinessLog log = new BusinessLog();
              			log.setBusinessName("保密工作专（兼）职人员情况");
              			log.setPrimaryKey(rewardAndPenalty2.getId());
              			BusinessLogContext
              					.getInstance()
              					.getBusinessLogService()
              					.saveDelBusinessLogByModule(getCurrentUser(), log,
              							rewardAndPenalty2);

                }
                secrecyFullPartTimeService.updateBatch(rewardAndPenalties);
                addActionMessage("删除成功!");
		return redirectActionResult(LIST);
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
               PageSortModel psm = new PageSortModel(getRequest(), "list");
               Organ organ = this.secrecyFullPartTimeService.get(organId, Organ.class);
               Map<String, Object> params = new HashMap<String, Object>();
               User userTmp = new User();
               userTmp.setOrgan(organ);
               List<SecrecyFullPartTime> dataList = this.secrecyFullPartTimeService.getPageList(null, null,params, userTmp);
               putToRequest("dataList", dataList);
               return SUCCESS;
       }
       public String dataValidate(){
               Map<String, Object> resultData = new HashMap<String, Object>();
               // ID值为bm_business_module中的ID
               resultData.put("id", "33");
               Map<String, Object> params = new HashMap<String, Object>();
               List<SecrecyFullPartTime> dataList = this.secrecyFullPartTimeService.getPageList(null, null,params, getCurrentUser());
               String msg = dataValidateService.validateData("保密工作专（兼）职人员情况", dataList, "33");
               resultData.put("msg", msg);
               setResultData(resultData);
               return JSON;
       }
	public String detail() {
		secrecyFullPartTime = secrecyFullPartTimeService.get(secrecyFullPartTime.getId());
		return SUCCESS;
	}


	public String main(){
	        //district= secrecyFullPartTimeService.get(district.getDistrictCode(), District.class);
		return SUCCESS;
	}

	public String allList() {
		PageSortModel psm = new PageSortModel(getRequest(), "list");
		if(showType==null || "".equals(showType)){
			showType = "0";
		}

		if(district == null || "".equals(district.getDistrictCode())) {
			district = new District();
			district=getCurrentUser().getOrgan().getDistrict();
		}
		if (district != null && "".equals(district.getDistrictCode())) {
			district = new District();
			district = getCurrentUser().getOrgan().getDistrict();
		}
		district= secrecyFullPartTimeService.get(district.getDistrictCode(), District.class);
		this.putToRequest("district", district);
		List list = (List) secrecyFullPartTimeService.listForSelect(psm, secrecyFullPartTime, district.getDistrictCode(), showType);
		this.putToRequest("list", list);
		return "allList";
	}





	public Attachment getAttachment() {
		return attachment;
	}

	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}

	public List<Attachment> getAttachmentList() {
		return attachmentList;
	}

	public void setAttachmentList(List<Attachment> attachmentList) {
		this.attachmentList = attachmentList;
	}

	public String getShowType() {
		return showType;
	}

	public void setShowType(String showType) {
		this.showType = showType;
	}

	public OrganService getOrganService() {
		return organService;
	}

	public void setOrganService(OrganService organService) {
		this.organService = organService;
	}


	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public DistrictService getDistrictService() {
		return districtService;
	}

	public void setDistrictService(DistrictService districtService) {
		this.districtService = districtService;
	}

        /**
         * @return 返回 secrecyFullPartTimeService
         */
        public SecrecyFullPartTimeService getSecrecyFullPartTimeService() {
                return secrecyFullPartTimeService;
        }

        /**
         * @param secrecyFullPartTimeService 设置 secrecyFullPartTimeService
         */
        public void setSecrecyFullPartTimeService(SecrecyFullPartTimeService secrecyFullPartTimeService) {
                this.secrecyFullPartTimeService = secrecyFullPartTimeService;
        }

        /**
         * @return 返回 secrecyFullPartTime
         */
        public SecrecyFullPartTime getSecrecyFullPartTime() {
                return secrecyFullPartTime;
        }

        /**
         * @param secrecyFullPartTime 设置 secrecyFullPartTime
         */
        public void setSecrecyFullPartTime(SecrecyFullPartTime secrecyFullPartTime) {
                this.secrecyFullPartTime = secrecyFullPartTime;
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
