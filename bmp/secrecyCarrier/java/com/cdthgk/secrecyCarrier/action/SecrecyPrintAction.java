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
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;
import com.cdthgk.platform.permission.user.domain.User;
import com.cdthgk.secrecyCarrier.service.SecrecyPrintService;
import com.cdthgk.secrecyCarrier.vo.SecrecyBorrow;
import com.cdthgk.secrecyCarrier.vo.SecrecyMaintain;
import com.cdthgk.secrecyCarrier.vo.SecrecyPrint;

import ec.common.PageSortModel;
@SuppressWarnings("all")
public class SecrecyPrintAction extends BmpAction {
	private SecrecyPrintService secrecyPrintService;
	// 目标条目分类
	private SecrecyPrint secrecyPrint;
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
		List list = (List) secrecyPrintService.getPageList(psm, secrecyPrint,params, getCurrentUser());
		this.putToRequest("list", list);
		return SUCCESS;
	}

	public String add() {
		return SUCCESS;
	}

	public String edit() {
		secrecyPrint = secrecyPrintService.get(secrecyPrint.getId());
		attachmentList = AttachmentContext.getInstance().getAttachmentService().getAttachmentsByHostId(secrecyPrint.getId());
		 if (secrecyPrint.getUndertaker()!=null) {
	                        String[] strings=secrecyPrint.getUndertaker().split(",");
	                        List<UserInfo> list=new ArrayList<UserInfo>();
	                        for (int i = 0; i < strings.length; i++) {
	                                UserInfo department = secrecyPrintService.get(strings[i].trim(), UserInfo.class);
	                                list.add(department);

	                        }
	                        putToRequest("undertaker", list);
	                }
		 if (secrecyPrint.getApprover()!=null) {
                         String[] strings=secrecyPrint.getApprover().split(",");
                         List<UserInfo> list=new ArrayList<UserInfo>();
                         for (int i = 0; i < strings.length; i++) {
                                 UserInfo department = secrecyPrintService.get(strings[i].trim(), UserInfo.class);
                                 list.add(department);

                         }
                         putToRequest("approver", list);
                 }

		return SUCCESS;
	}
        public boolean isNeedReload() {
                return needReload;
        }

        public void setNeedReload(boolean needReload) {
                this.needReload = needReload;
        }


        public String save() {
                secrecyPrint.setOrganId(getCurrentUser().getOrgan());
		secrecyPrint.setCreatePerson(getCurrentUser());
		secrecyPrint.setState(0);
		secrecyPrint.setCreateTime(new Date());
		secrecyPrintService.save(secrecyPrint);
		AttachmentContext.getInstance().getAttachmentService().updateHostId(secrecyPrint.getId(),secAttach);
		addActionMessage("添加成功!");
		needReload = true;
		BusinessLog log = new BusinessLog();
		log.setBusinessName("涉密载体打印情况");
		log.setPrimaryKey(secrecyPrint.getId());
		BusinessLogContext
				.getInstance()
				.getBusinessLogService()
				.saveAddBusinessLogByModule(getCurrentUser(), log,
						new SecrecyPrint());
		return redirectActionResult(LIST);
	}

	public String update() {
			SecrecyPrint beforeSp=new SecrecyPrint();
	        SecrecyPrint secrecyPrint2=secrecyPrintService.get(secrecyPrint.getId());
	        BeanUtils.copyProperties(beforeSp, secrecyPrint2, CopyRuleEnum.ignoreCaseNull);
	        BeanUtils.copyProperties(secrecyPrint2, secrecyPrint, CopyRuleEnum.ignoreCaseNull);
	        secrecyPrint2.setModifyPerson(getCurrentUser());
	        secrecyPrint2.setModifyTime(new Date());
                secrecyPrintService.update(secrecyPrint2);
                AttachmentContext.getInstance().getAttachmentService().updateHostId(secrecyPrint2.getId(),secAttach);
                addActionMessage("修改成功!");
                needReload = true;
                BusinessLog log = new BusinessLog();
        		log.setBusinessName("涉密载体打印情况");
        		log.setPrimaryKey(secrecyPrint2.getId());
        		BusinessLogContext
        				.getInstance()
        				.getBusinessLogService()
        				.saveEditBusinessLogByModule(getCurrentUser(), log,
        						secrecyPrint2, beforeSp);
		return redirectActionResult(LIST);
	}


	public String delete() {
	        List<String> strings = this.getIds();
                List<SecrecyPrint> rewardAndPenalties = new ArrayList<SecrecyPrint>();
                for (int i = 0; i < strings.size(); i++) {
                        SecrecyPrint rewardAndPenalty2=secrecyPrintService.get(strings.get(i));
			BusinessLog log = new BusinessLog();
			log.setBusinessName("涉密载体打印情况");
			log.setPrimaryKey(strings.get(i));
			BusinessLogContext
					.getInstance()
					.getBusinessLogService()
					.saveDelBusinessLogByModule(getCurrentUser(), log,
							rewardAndPenalty2);
                        rewardAndPenalty2.setState(1);
                         rewardAndPenalties.add(rewardAndPenalty2);

                }
                secrecyPrintService.updateBatch(rewardAndPenalties);
                addActionMessage("删除成功!");
		return redirectActionResult(LIST);
	}


	public String detail() {
		secrecyPrint = secrecyPrintService.get(secrecyPrint.getId());
		attachmentList = AttachmentContext.getInstance().getAttachmentService().getAttachmentsByHostId(secrecyPrint.getId());
		 if (secrecyPrint.getUndertaker()!=null) {
		         String[] strings=secrecyPrint.getUndertaker().split(",");
	                        String list="";
	                        for (int i = 0; i < strings.length; i++) {
	                                UserInfo department = secrecyPrintService.get(strings[i].trim(), UserInfo.class);
	                                if (i==strings.length-1) {

	                                        list+=department.getName();
	                                }else {
	                                        list+=department.getName()+"，";
	                                }

	                        }
                         putToRequest("undertaker", list);
                 }
          if (secrecyPrint.getApprover()!=null) {
                  String[] strings=secrecyPrint.getApprover().split(",");
                  String list="";
                  for (int i = 0; i < strings.length; i++) {
                          UserInfo department = secrecyPrintService.get(strings[i].trim(), UserInfo.class);
                          if (i==strings.length-1) {

                                  list+=department.getName();
                          }else {
                                  list+=department.getName()+"，";
                          }

                  }
                  putToRequest("approver", list);
          }
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


		district= secrecyPrintService.get(district.getDistrictCode(), District.class);
		List list = (List) secrecyPrintService.listForSelect(psm, secrecyPrint, district.getDistrictCode(), showType);
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
               Organ organ = this.secrecyPrintService.get(organId, Organ.class);
               User userTmp = new User();
               userTmp.setOrgan(organ);
               Map<String, Object> params = new HashMap<String, Object>();
               DictionaryService dictionaryService = DictionaryContext.getInstance().getDictionaryService();
               List<SecrecyPrint> secrecyPrintList = (List<SecrecyPrint>) secrecyPrintService.getPageList(null, null,params, userTmp);
               if( secrecyPrintList!=null && secrecyPrintList.size()>0 ){
                       for( SecrecyPrint sp : secrecyPrintList ){
                               sp.setSecrecyLevelTxt(dictionaryService.getOption("bmp", "secrecy_level_thing", sp.getSecrecyLevel()).getOptionText());
                               sp.setUncertakerNames(this.secrecyPrintService.userInfoIds2Names(sp.getUndertaker()));
                               sp.setApproverNames(this.secrecyPrintService.userInfoIds2Names(sp.getApprover()));
                       }
               }
               putToRequest("secrecyPrintList", secrecyPrintList);
               return SUCCESS;
       }
       public String dataValidate(){
               Map<String, Object> resultData = new HashMap<String, Object>();
               // ID值为bm_business_module中的ID
               resultData.put("id", "29");
               Map<String, Object> params = new HashMap<String, Object>();
               List<SecrecyPrint> secrecyPrintList = (List<SecrecyPrint>) secrecyPrintService.getPageList(null, null,params, getCurrentUser());
               String msg = dataValidateService.validateData("涉密载体打印情况", secrecyPrintList, "29");
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
         * @return 返回 secrecyPrintService
         */
        public SecrecyPrintService getSecrecyPrintService() {
                return secrecyPrintService;
        }

        /**
         * @param secrecyPrintService 设置 secrecyPrintService
         */
        public void setSecrecyPrintService(SecrecyPrintService secrecyPrintService) {
                this.secrecyPrintService = secrecyPrintService;
        }

        /**
         * @return 返回 secrecyPrint
         */
        public SecrecyPrint getSecrecyPrint() {
                return secrecyPrint;
        }

        /**
         * @param secrecyPrint 设置 secrecyPrint
         */
        public void setSecrecyPrint(SecrecyPrint secrecyPrint) {
                this.secrecyPrint = secrecyPrint;
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
