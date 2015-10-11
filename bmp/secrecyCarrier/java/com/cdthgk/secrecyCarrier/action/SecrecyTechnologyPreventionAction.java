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
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.district.service.DistrictService;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.organ.service.OrganService;
import com.cdthgk.platform.permission.user.domain.User;
import com.cdthgk.secrecyCarrier.service.SecrecyTechnologyPreventionService;
import com.cdthgk.secrecyCarrier.vo.SecrecyMaintain;
import com.cdthgk.secrecyCarrier.vo.SecrecyPrint;
import com.cdthgk.secrecyCarrier.vo.SecrecyTechnologyPrevention;

import ec.common.PageSortModel;
@SuppressWarnings("all")
public class SecrecyTechnologyPreventionAction extends BmpAction {
	private SecrecyTechnologyPreventionService secrecyTechnologyPreventionService;
	// 目标条目分类
	private SecrecyTechnologyPrevention secrecyTechnologyPrevention;

	private DataValidateService dataValidateService;

	private Attachment attachment;
	List<Attachment> attachmentList;
	private List<String> secAttach;
	private String showType;
	private District district;
	private DistrictService districtService;
	private OrganService organService;
	private boolean needReload=false;

	public String list() {
		PageSortModel psm = new PageSortModel(getRequest(),"list");
		Map<String, Object> params = new HashMap<String, Object>();
		List list = (List) secrecyTechnologyPreventionService.getPageList(psm, secrecyTechnologyPrevention,params, getCurrentUser());
		this.putToRequest("list", list);
		return SUCCESS;
	}

	public String add() {
		return SUCCESS;
	}

	public String edit() {
		secrecyTechnologyPrevention = secrecyTechnologyPreventionService.get(secrecyTechnologyPrevention.getId());
		attachmentList = AttachmentContext.getInstance().getAttachmentService().getAttachmentsByHostId(secrecyTechnologyPrevention.getId());
		return SUCCESS;
	}
        public boolean isNeedReload() {
                return needReload;
        }

        public void setNeedReload(boolean needReload) {
                this.needReload = needReload;
        }


        public String save() {
                secrecyTechnologyPrevention.setOrganId(getCurrentUser().getOrgan());
		secrecyTechnologyPrevention.setCreatePerson(getCurrentUser());
		secrecyTechnologyPrevention.setState(0);
		secrecyTechnologyPrevention.setCreateTime(new Date());
		secrecyTechnologyPreventionService.save(secrecyTechnologyPrevention);
		AttachmentContext.getInstance().getAttachmentService().updateHostId(secrecyTechnologyPrevention.getId(),secAttach);
		addActionMessage("添加成功!");
		needReload = true;
		BusinessLog log = new BusinessLog();
		log.setBusinessName("装备保密技术防范设备情况");
		log.setPrimaryKey(secrecyTechnologyPrevention.getId());
		BusinessLogContext
				.getInstance()
				.getBusinessLogService()
				.saveAddBusinessLogByModule(getCurrentUser(), log,
						new SecrecyTechnologyPrevention());
		return redirectActionResult(LIST);
	}

	public String update() {
			SecrecyTechnologyPrevention beforeStp=new SecrecyTechnologyPrevention();
	        SecrecyTechnologyPrevention secrecyTechnologyPrevention2=secrecyTechnologyPreventionService.get(secrecyTechnologyPrevention.getId());
	        BeanUtils.copyProperties(beforeStp, secrecyTechnologyPrevention2, CopyRuleEnum.ignoreCaseNull);
	        BeanUtils.copyProperties(secrecyTechnologyPrevention2, secrecyTechnologyPrevention, CopyRuleEnum.ignoreCaseNull);
	        secrecyTechnologyPrevention2.setModifyPerson(getCurrentUser());
	        secrecyTechnologyPrevention2.setModifyTime(new Date());
                secrecyTechnologyPreventionService.update(secrecyTechnologyPrevention2);
                AttachmentContext.getInstance().getAttachmentService().updateHostId(secrecyTechnologyPrevention2.getId(),secAttach);
                addActionMessage("修改成功!");
                needReload = true;
                BusinessLog log = new BusinessLog();
        		log.setBusinessName("装备保密技术防范设备情况");
        		log.setPrimaryKey(secrecyTechnologyPrevention2.getId());
        		BusinessLogContext
        				.getInstance()
        				.getBusinessLogService()
        				.saveEditBusinessLogByModule(getCurrentUser(), log,
        						secrecyTechnologyPrevention2, beforeStp);
		return redirectActionResult(LIST);
	}


	public String delete() {
	        List<String> strings = this.getIds();
                List<SecrecyTechnologyPrevention> rewardAndPenalties = new ArrayList<SecrecyTechnologyPrevention>();
                for (int i = 0; i < strings.size(); i++) {
                        SecrecyTechnologyPrevention rewardAndPenalty2=secrecyTechnologyPreventionService.get(strings.get(i));
                        BusinessLog log = new BusinessLog();
            			log.setBusinessName("装备保密技术防范设备情况");
            			log.setPrimaryKey(strings.get(i));
            			BusinessLogContext
            					.getInstance()
            					.getBusinessLogService()
            					.saveDelBusinessLogByModule(getCurrentUser(), log,
            							rewardAndPenalty2);
                        rewardAndPenalty2.setState(1);
                         rewardAndPenalties.add(rewardAndPenalty2);

                }
                secrecyTechnologyPreventionService.updateBatch(rewardAndPenalties);
                addActionMessage("删除成功!");
		return redirectActionResult(LIST);
	}


	public String detail() {
		secrecyTechnologyPrevention = secrecyTechnologyPreventionService.get(secrecyTechnologyPrevention.getId());
		attachmentList = AttachmentContext.getInstance().getAttachmentService().getAttachmentsByHostId(secrecyTechnologyPrevention.getId());
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
		district= secrecyTechnologyPreventionService.get(district.getDistrictCode(), District.class);
		List list = (List) secrecyTechnologyPreventionService.listForSelect(psm, secrecyTechnologyPrevention, district.getDistrictCode(), showType);
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
               Organ organ = this.secrecyTechnologyPreventionService.get(organId, Organ.class);
               User userTmp = new User();
               userTmp.setOrgan(organ);
               Map<String,Object> params = new HashMap<String, Object>();
               List<SecrecyTechnologyPrevention> secrecyTechnologyPreventionList = (List<SecrecyTechnologyPrevention>) secrecyTechnologyPreventionService.getPageList(null, null,params, userTmp);
               putToRequest("secrecyTechnologyPreventionList", secrecyTechnologyPreventionList);
               return SUCCESS;
       }
       public String dataValidate(){
               Map<String, Object> resultData = new HashMap<String, Object>();
               // ID值为bm_business_module中的ID
               resultData.put("id", "31");
               Map<String, Object> params = new HashMap<String, Object>();
               List<SecrecyTechnologyPrevention> secrecyTechnologyPreventionList = (List<SecrecyTechnologyPrevention>) secrecyTechnologyPreventionService.getPageList(null, null,params, getCurrentUser());
               String msg = dataValidateService.validateData("装备保密技术防范设备情况", secrecyTechnologyPreventionList, "31");
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
         * @return 返回 secrecyTechnologyPreventionService
         */
        public SecrecyTechnologyPreventionService getSecrecyTechnologyPreventionService() {
                return secrecyTechnologyPreventionService;
        }

        /**
         * @param secrecyTechnologyPreventionService 设置 secrecyTechnologyPreventionService
         */
        public void setSecrecyTechnologyPreventionService(SecrecyTechnologyPreventionService secrecyTechnologyPreventionService) {
                this.secrecyTechnologyPreventionService = secrecyTechnologyPreventionService;
        }

        /**
         * @return 返回 secrecyTechnologyPrevention
         */
        public SecrecyTechnologyPrevention getSecrecyTechnologyPrevention() {
                return secrecyTechnologyPrevention;
        }

        /**
         * @param secrecyTechnologyPrevention 设置 secrecyTechnologyPrevention
         */
        public void setSecrecyTechnologyPrevention(SecrecyTechnologyPrevention secrecyTechnologyPrevention) {
                this.secrecyTechnologyPrevention = secrecyTechnologyPrevention;
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
