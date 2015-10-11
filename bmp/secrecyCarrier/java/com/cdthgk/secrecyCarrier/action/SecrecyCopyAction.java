package com.cdthgk.secrecyCarrier.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.action.BmpAction;
import com.cdthgk.bmp.log.context.BusinessLogContext;
import com.cdthgk.bmp.log.vo.BusinessLog;
import com.cdthgk.bmp.statinfo.dto.QueryDto;
import com.cdthgk.common.bean.BeanUtils;
import com.cdthgk.common.bean.rule.CopyRuleEnum;
import com.cdthgk.common.lang.CollectionUtils;
import com.cdthgk.platform.attachment.context.AttachmentContext;
import com.cdthgk.platform.attachment.domain.Attachment;
import com.cdthgk.platform.dataValidate.service.DataValidateService;
import com.cdthgk.platform.dictionary.context.DictionaryContext;
import com.cdthgk.platform.dictionary.spi.DictionaryService;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.district.service.DistrictService;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.organ.service.OrganService;
import com.cdthgk.platform.permission.user.domain.User;
import com.cdthgk.secrecyCarrier.service.SecrecyCopyService;
import com.cdthgk.secrecyCarrier.vo.SecrecyCopy;
import com.cdthgk.secrecyCarrier.vo.SecrecyPrint;

import ec.common.PageSortModel;
@SuppressWarnings("all")
public class SecrecyCopyAction extends BmpAction {
	private SecrecyCopyService secrecyCopyService;
	// 目标条目分类
	private SecrecyCopy secrecyCopy;

	private DataValidateService dataValidateService;

	private Attachment attachment;
	List<Attachment> attachmentList;
	private List<String> secAttach;
	private String showType;
	private District district;
	private DistrictService districtService;
	private OrganService organService;
	private boolean needReload=false;
	//--------销毁报废情况---------------------------------------------------------

	public String list() {
		PageSortModel psm = new PageSortModel(getRequest(),"list");
		Map<String, Object> params = new HashMap<String, Object>();
		List list = (List) secrecyCopyService.getPageList(psm, secrecyCopy,params, getCurrentUser());
		this.putToRequest("list", list);
		return SUCCESS;
	}

	public String add() {
		return SUCCESS;
	}

	public String edit() {
		secrecyCopy = secrecyCopyService.get(secrecyCopy.getId());
		attachmentList = AttachmentContext.getInstance().getAttachmentService().getAttachmentsByHostId(secrecyCopy.getId());
		return SUCCESS;
	}
        public boolean isNeedReload() {
                return needReload;
        }

        public void setNeedReload(boolean needReload) {
                this.needReload = needReload;
        }


        public String save() {
                secrecyCopy.setOrganId(getCurrentUser().getOrgan());
		secrecyCopy.setCreatePerson(getCurrentUser());
		secrecyCopy.setState(0);
		secrecyCopy.setCreateTime(new Date());
		secrecyCopyService.save(secrecyCopy);
		AttachmentContext.getInstance().getAttachmentService().updateHostId(secrecyCopy.getId(),secAttach);
		addActionMessage("添加成功!");
		needReload = true;
		BusinessLog log = new BusinessLog();
		log.setBusinessName("涉密载体复印情况");
		log.setPrimaryKey(secrecyCopy.getId());
		BusinessLogContext
				.getInstance()
				.getBusinessLogService()
				.saveAddBusinessLogByModule(getCurrentUser(), log,
						new SecrecyCopy());
		return redirectActionResult(LIST);
	}

	public String update() {
			SecrecyCopy beforeSc=new SecrecyCopy();
	        SecrecyCopy secrecyCopy2=secrecyCopyService.get(secrecyCopy.getId());
	        BeanUtils.copyProperties(beforeSc, secrecyCopy2, CopyRuleEnum.ignoreCaseNull);
	        BeanUtils.copyProperties(secrecyCopy2, secrecyCopy, CopyRuleEnum.ignoreCaseNull);
	        secrecyCopy2.setModifyPerson(getCurrentUser());
	        secrecyCopy2.setModifyTime(new Date());
                secrecyCopyService.update(secrecyCopy2);
                AttachmentContext.getInstance().getAttachmentService().updateHostId(secrecyCopy2.getId(),secAttach);
                addActionMessage("修改成功!");
                needReload = true;
            BusinessLog log=new BusinessLog();
            log.setBusinessName("涉密载体复印情况");
            log.setPrimaryKey(secrecyCopy2.getId());
            BusinessLogContext.getInstance().getBusinessLogService().saveEditBusinessLogByModule(getCurrentUser(), log, secrecyCopy2, beforeSc);
		return redirectActionResult(LIST);
	}


	public String delete() {
	        List<String> strings = this.getIds();
                List<SecrecyCopy> rewardAndPenalties = new ArrayList<SecrecyCopy>();
                for (int i = 0; i < strings.size(); i++) {
                        SecrecyCopy rewardAndPenalty2=secrecyCopyService.get(strings.get(i));
			BusinessLog log = new BusinessLog();
			log.setBusinessName("涉密载体复印情况");
			log.setPrimaryKey(strings.get(i));
			BusinessLogContext
					.getInstance()
					.getBusinessLogService()
					.saveDelBusinessLogByModule(getCurrentUser(), log,
							rewardAndPenalty2);
                        rewardAndPenalty2.setState(1);
                         rewardAndPenalties.add(rewardAndPenalty2);

                }
                secrecyCopyService.updateBatch(rewardAndPenalties);
                addActionMessage("删除成功!");
		return redirectActionResult(LIST);
	}


	public String detail() {
		secrecyCopy = secrecyCopyService.get(secrecyCopy.getId());
		attachmentList = AttachmentContext.getInstance().getAttachmentService().getAttachmentsByHostId(secrecyCopy.getId());
		return SUCCESS;
	}


	public String main(){
		return SUCCESS;
	}

	public String allList() {
		PageSortModel psm = new PageSortModel(getRequest(), "list");
		if(showType==null || "".equals(showType)){
			showType = "0";
		}

		if(district == null) {
			district = new District();
			district.setDistrictCode(getCurrentUser().getOrgan()
					.getDistrict().getDistrictCode());
		}
		if (district != null && "".equals(district.getDistrictCode())) {
			district = new District();
			district.setDistrictCode(getCurrentUser().getOrgan()
					.getDistrict().getDistrictCode());
		}


		district= secrecyCopyService.get(district.getDistrictCode(), District.class);
		List list = (List) secrecyCopyService.listForSelect(psm, secrecyCopy, district.getDistrictCode(), showType);
		this.putToRequest("list", list);
		return "allList";
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
        * <li>宋亚非  2014-05-15 创建
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
               Organ organ = this.secrecyCopyService.get(organId, Organ.class);
               User userTmp = new User();
               userTmp.setOrgan(organ);
               Map<String, Object> params = new HashMap<String, Object>();
               List<SecrecyCopy> secrecyCopyList = (List<SecrecyCopy>) secrecyCopyService.getPageList(null, secrecyCopy,params, userTmp);
               DictionaryService dictionaryService = DictionaryContext.getInstance().getDictionaryService();
               if( secrecyCopyList!=null && secrecyCopyList.size()>0 ){
                       for( SecrecyCopy sc : secrecyCopyList ){
                               sc.setSecrecyLevelTxt(dictionaryService.getOption("bmp", "secrecy_level_thing", sc.getSecrecyLevel()).getOptionText());
                       }
               }
               putToRequest("secrecyCopyList", secrecyCopyList);
               return SUCCESS;
       }
       public String dataValidate(){
               Map<String, Object> resultData = new HashMap<String, Object>();
               // ID值为bm_business_module中的ID
               resultData.put("id", "32");
               Map<String, Object> params = new HashMap<String, Object>();
               List<SecrecyCopy> dataList = (List<SecrecyCopy>) secrecyCopyService.getPageList(null, null,params, getCurrentUser());
               String msg = dataValidateService.validateData("涉密载体复印情况", dataList, "32");
               resultData.put("msg", msg);
               setResultData(resultData);
               return JSON;
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
         * @return 返回 secrecyCopyService
         */
        public SecrecyCopyService getSecrecyCopyService() {
                return secrecyCopyService;
        }

        /**
         * @param secrecyCopyService 设置 secrecyCopyService
         */
        public void setSecrecyCopyService(SecrecyCopyService secrecyCopyService) {
                this.secrecyCopyService = secrecyCopyService;
        }

        /**
         * @return 返回 secrecyCopy
         */
        public SecrecyCopy getSecrecyCopy() {
                return secrecyCopy;
        }

        /**
         * @param secrecyCopy 设置 secrecyCopy
         */
        public void setSecrecyCopy(SecrecyCopy secrecyCopy) {
                this.secrecyCopy = secrecyCopy;
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

		public List<String> getSecAttach() {
			return secAttach;
		}

		public void setSecAttach(List<String> secAttach) {
			this.secAttach = secAttach;
		}
}
