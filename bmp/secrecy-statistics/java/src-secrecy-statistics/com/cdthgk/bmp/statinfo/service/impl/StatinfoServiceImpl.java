package com.cdthgk.bmp.statinfo.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdthgk.bmp.core.action.BmpAction;
import com.cdthgk.bmp.keyPart.service.PartModuleService;
import com.cdthgk.bmp.keyPart.vo.Part;
import com.cdthgk.bmp.keysection.service.KeySectionModuleService;
import com.cdthgk.bmp.keysection.vo.KeySection;
import com.cdthgk.bmp.planSndSummary.annualPlan.service.AnnualPlanService;
import com.cdthgk.bmp.planSndSummary.annualPlan.vo.AnnualPlan;
import com.cdthgk.bmp.planSndSummary.annualSummary.service.AnnualSummaryService;
import com.cdthgk.bmp.planSndSummary.annualSummary.vo.AnnualSummary;
import com.cdthgk.bmp.publicityEducation.departmentWebsite.service.DepartmentWebsiteService;
import com.cdthgk.bmp.publicityEducation.departmentWebsite.vo.Departmentwebsite;
import com.cdthgk.bmp.publicityEducation.informationReview.service.InformationReviewService;
import com.cdthgk.bmp.publicityEducation.informationReview.vo.InformationReview;
import com.cdthgk.bmp.publicityEducation.publicityEducation.service.PublicityeducationService;
import com.cdthgk.bmp.publicityEducation.publicityEducation.vo.Publicityeducation;
import com.cdthgk.bmp.publicityEducation.undertaketask.service.UndertaketaskService;
import com.cdthgk.bmp.publicityEducation.undertaketask.vo.Undertaketask;
import com.cdthgk.bmp.secrecycountryitem.service.SecrecyCountryItemService;
import com.cdthgk.bmp.secrecycountryitem.vo.SecrecyCountryItem;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.service.SecrecyWorkOrganModuleService;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.service.SecrecyWorkOrganRelationMemberModuleService;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.vo.SecrecyWorkOrgan;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.vo.SecrecyWorkOrganRelationMember;
import com.cdthgk.bmp.secrecyperson.service.SecrecyPersonModuleService;
import com.cdthgk.bmp.secrecyperson.vo.SecrecyPerson;
import com.cdthgk.bmp.secrecyproducts.service.SecrecyProductsService;
import com.cdthgk.bmp.secrecyproducts.vo.SecrecyProducts;
import com.cdthgk.bmp.secrecyresearchproject.service.SecrecyResearchProjectService;
import com.cdthgk.bmp.secrecyresearchproject.vo.SecrecyResearchProject;
import com.cdthgk.bmp.secrecysystem.secrecycomputer.service.SecrecyComputerService;
import com.cdthgk.bmp.secrecysystem.secrecycomputer.vo.SecrecyComputer;
import com.cdthgk.bmp.secrecysystem.secrecynetwork.service.SecrecyNetworkService;
import com.cdthgk.bmp.secrecysystem.secrecynetwork.vo.SecrecyNetwork;
import com.cdthgk.bmp.secrecysystem.secrecyotherdevice.service.SecrecyMobilestoragemediaService;
import com.cdthgk.bmp.secrecysystem.secrecyotherdevice.service.SecrecyOthersService;
import com.cdthgk.bmp.secrecysystem.secrecyotherdevice.vo.SecrecyMobilestoragemedia;
import com.cdthgk.bmp.secrecysystem.secrecyotherdevice.vo.SecrecyOthers;
import com.cdthgk.bmp.stateSecretManagement.contactSecretPerson.service.ContactSecretPersonService;
import com.cdthgk.bmp.stateSecretManagement.contactSecretPerson.vo.ContactSecretPerson;
import com.cdthgk.bmp.stateSecretManagement.receiveConfidential.service.ReceiveConfidentialService;
import com.cdthgk.bmp.stateSecretManagement.receiveConfidential.vo.ReceiveConfidential;
import com.cdthgk.bmp.statinfo.service.StatinfoService;
import com.cdthgk.checkevent.service.SecrecyCheckEventService;
import com.cdthgk.checkevent.vo.SecrecyCheckEvent;
import com.cdthgk.common.lang.CollectionUtils;
import com.cdthgk.common.lang.DateUtils;
import com.cdthgk.common.lang.StringUtils;
import com.cdthgk.disclosesecrecy.service.DiscloseSecrecyService;
import com.cdthgk.disclosesecrecy.vo.DiscloseSecrecy;
import com.cdthgk.lawsystem.secrecyinstitution.service.SecrecyInstitutionService;
import com.cdthgk.lawsystem.secrecyinstitution.vo.SecrecyInstitution;
import com.cdthgk.meetingcategory.service.MeetingService;
import com.cdthgk.meetingcategory.vo.Meeting;
import com.cdthgk.meetingcategory.vo.MeetingCategory;
import com.cdthgk.platform.base.service.GenericServiceTemplate;
import com.cdthgk.platform.dictionary.context.DictionaryContext;
import com.cdthgk.platform.dictionary.spi.DictionaryService;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;
import com.cdthgk.platform.permission.user.domain.User;
import com.cdthgk.rewardAndPenalty.service.RewardAndPenaltyService;
import com.cdthgk.rewardAndPenalty.vo.RewardAndPenalty;
import com.cdthgk.secrecyCarrier.service.DestructionScrapService;
import com.cdthgk.secrecyCarrier.service.SecrecyBorrowService;
import com.cdthgk.secrecyCarrier.service.SecrecyCopyService;
import com.cdthgk.secrecyCarrier.service.SecrecyMaintainService;
import com.cdthgk.secrecyCarrier.service.SecrecyPrintService;
import com.cdthgk.secrecyCarrier.service.SecrecyTechnologyPreventionService;
import com.cdthgk.secrecyCarrier.vo.DestructionScrap;
import com.cdthgk.secrecyCarrier.vo.SecrecyBorrow;
import com.cdthgk.secrecyCarrier.vo.SecrecyCopy;
import com.cdthgk.secrecyCarrier.vo.SecrecyMaintain;
import com.cdthgk.secrecyCarrier.vo.SecrecyPrint;
import com.cdthgk.secrecyCarrier.vo.SecrecyTechnologyPrevention;
import com.cdthgk.secrecyFullPartTime.service.SecrecyFullPartTimeService;
import com.cdthgk.secrecyFullPartTime.vo.SecrecyFullPartTime;
import com.cdthgk.setTheDecryption.service.SetTheDecryptionService;
import com.cdthgk.setTheDecryption.vo.SetTheDecryption;

/**
 *
 * <p>
 * 类的说明放这里
 * </p>
 * <p>
 * 创建时间 2014-5-5 - 下午4:00:54
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
 * @author 陶汇源
 * @author cdthgk r&d
 * @since 1.0
 * @version 1.0
 */
public class StatinfoServiceImpl extends GenericServiceTemplate<Object, String>
			implements StatinfoService{

	private static final Logger LOGGER = LoggerFactory
			.getLogger(StatinfoServiceImpl.class);
	private SecrecyWorkOrganModuleService secrecyWorkOrganModuleService;
	private SecrecyWorkOrganRelationMemberModuleService secrecyWorkOrganRelationMemberModuleService;
	private PartModuleService partModuleService;
	private KeySectionModuleService keySectionModuleService;
	private SecrecyMobilestoragemediaService secrecyMobilestoragemediaService;
	private SecrecyComputerService secrecyComputerService;
	private AnnualPlanService annualPlanService;
	private AnnualSummaryService annualSummaryService;
	private DepartmentWebsiteService departmentWebsiteService;
	private InformationReviewService  informationReviewService;
	private PublicityeducationService publicityeducationService;
	private UndertaketaskService undertaketaskService;
	private SecrecyInstitutionService secrecyInstitutionService;
	private MeetingService meetingService;
	private SecrecyCheckEventService secrecyCheckEventService;
	private RewardAndPenaltyService rewardAndPenaltyService;
	private ReceiveConfidentialService receiveConfidentialService;
	private ContactSecretPersonService contactSecretPersonService;
	private DestructionScrapService destructionScrapService;
	private SecrecyBorrowService secrecyBorrowService;
	private SecrecyPrintService secrecyPrintService;
	private SecrecyMaintainService secrecyMaintainService;
	private SecrecyTechnologyPreventionService secrecyTechnologyPreventionService;
	private SecrecyCountryItemService secrecyCountryItemService;
	private SecrecyCopyService secrecyCopyService;
	private SecrecyPersonModuleService secrecyPersonModuleService;
	private SecrecyProductsService secrecyProductsService;
	private SecrecyResearchProjectService secrecyResearchProjectService;
	private SecrecyNetworkService secrecyNetworkService;
	private SecrecyOthersService secrecyOthersService;
	private SecrecyFullPartTimeService secrecyFullPartTimeService;
	private SetTheDecryptionService setTheDecryptionService;
	private DiscloseSecrecyService discloseSecrecyService;
	// 构造器
	/** 默认构造器 */
	public StatinfoServiceImpl() {
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Map<String, Object>> getAllBusinessModule() {
		Map<String, Object> params = new HashMap<String, Object>();
		List<Map<String, Object>> list = getPersistProxy().getJdbcPersistence().findList("select * from bm_business_module where status=1 order by sort", params);
		return list;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, Object> buildData(List<Map<String, Object>> selectedModuleList, Organ organ) {

		Map<String, Object> dataMap = new HashMap<String, Object>();
		// 设置表头
		dataMap.put("year", DateUtils.getCurrentYear()+"");
		dataMap.put("organName", organ.getOrganName());
		// 设置保密工作机构
		setSecrecyWorkOrganParams(dataMap, organ);
		// 设置要害部门、部位登记表
		setKeySectionAndPartParams(dataMap, organ);
		// 设置移动存储介质
		setMobilestoragemediaParams(dataMap, organ);
		// 设置计算机
		setSecrecyComputerParams(dataMap, organ);
		// 设置工作计划与总结
		setPlanAndSummaryParams(dataMap, organ);
		// 设置保密宣传教育、承担课题情况、信息发布保密审查情况、部门网站管理
		setPublicityEducationParams(dataMap, organ);
		// 设置保密制度
		setSecrecyInstitutionParams(dataMap, organ);
		// 设置保密会议记录、活动和涉密会议情况
		setMeetingParams(dataMap, organ);
		//设置开展保密监督检查工作情况
		setSecrecyCheckEventParams(dataMap, organ);
		//保密工作奖惩情况
		setRewardAndPenaltyParams(dataMap, organ);
		//设置收到密件情况情况
		setReceiveConfidentialParams(dataMap, organ);
		//设置接触和知悉绝密级国家秘密文件人员情况
		setContactSecretPersonParams(dataMap, organ);
		//设置涉密载体销毁、涉密设备报废情况
		setDestructionScrapParams(dataMap, organ);
		//设置涉密载体借阅登记情况
		setSecrecyBorrowParams(dataMap, organ);
		//设置涉密文件（资料）打印登记情况
		setSecrecyPrintParams(dataMap, organ);
		//设置涉密设备维修登记情况
		setSecrecyMaintainParams(dataMap, organ);
		//设置装备保密技术防范设备情况
		setSecrecyTechnologyPreventionParams(dataMap, organ);
		//设置本单位产生国家秘密事项一览表
		setSecrecyCountryItemParams(dataMap, organ);
		//设置涉密文件（资料）复印登记情况
		setSecrecyCopyParams(dataMap, organ);
		//设置涉密人员
		setSecrecyPersonParams(dataMap, organ);
		//设置密品
		setSecrecyProductsParams(dataMap, organ);
		//设置涉密科研项目
		setSecrecyResearchProjectParams(dataMap, organ);
		//设置涉密 网络
		setSecrecyNetworkParams(dataMap, organ);
		//其他设备
		setSecrecyOthersParams(dataMap, organ);
        //保密工作专（兼）职人员情况
		setSecrecyFullPartTimeParams(dataMap, organ);
		//定解密工作情况
		setSetTheDecryptionParams(dataMap, organ);
		//泄密案件
		setDiscloseSecrecyParams(dataMap, organ);
		return dataMap;
	}

	/**
	 *
	 * <p>
	 * 设置保密工作机构
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2014-5-9 - 上午10:23:19
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param dataMap
	 */
	private void setSecrecyWorkOrganParams(Map<String, Object> dataMap, Organ organ){
		SecrecyWorkOrgan secrecyWorkOrgan = secrecyWorkOrganModuleService.getSecrecyWorkOrganByOrgan(organ);
		List<SecrecyWorkOrganRelationMember> secrecyWorkOrganRelationMemberList = secrecyWorkOrganRelationMemberModuleService.getPageList(secrecyWorkOrgan,organ, new Integer(14));
		for (SecrecyWorkOrganRelationMember swor : secrecyWorkOrganRelationMemberList) {
		        if(swor.getPerson().getBirthDate()!=null){
		                swor.setAgeStr(DateUtils.getYearNumber(swor.getPerson().getBirthDate(), new Date()) + "");
		        }else{
		                swor.setAgeStr("");
		        }
			swor.setSexStr(DictionaryContext.getInstance().getDictionaryService().getOption("person", "sex", Integer.parseInt(swor.getPerson().getSex())).getOptionText());
			if(swor.getPerson().getLearningLevel()!=null){
			        swor.setLearningLevelStr(DictionaryContext.getInstance().getDictionaryService().getOption("person", "learning_level", swor.getPerson().getLearningLevel()).getOptionText());
			}else{
			        swor.setLearningLevelStr("");
			}
			if(StringUtils.isNotEmpty(swor.getPerson().getPolity())){
				swor.setPolityStr(DictionaryContext.getInstance().getDictionaryService().getOption("person", "polity", Integer.parseInt(swor.getPerson().getPolity())).getOptionText());
			} else {
				swor.setPolityStr("");
			}
		}
		dataMap.put("secrecyWorkOrgan", secrecyWorkOrgan);
		dataMap.put("secrecyWorkOrganRelationMemberList", secrecyWorkOrganRelationMemberList);
	}
	/**
	 *
	 * <p>
	 * 设置要害部门、部位登记表
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2014-5-9 - 上午10:23:19
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param dataMap
	 */
	private void setKeySectionAndPartParams(Map<String, Object> dataMap, Organ organ){
		List<Part> partList = partModuleService.getListPage(null, null, organ);
		for (Part part : partList) {
			part.setSecrecyLevelStr(DictionaryContext.getInstance().getDictionaryService().getOption("bmp", "secrecy_level_person", part.getSecrecyLevel()).getOptionText());
		}
		dataMap.put("partList", partList);
		List<KeySection> keySectionlist = keySectionModuleService.queryList(null, organ, null);
		for (KeySection keySection : keySectionlist) {
			keySection.setSecrecyLevelStr(DictionaryContext.getInstance().getDictionaryService().getOption("bmp", "secrecy_level_section", keySection.getSecrecyLevel()).getOptionText());
		}
		dataMap.put("keySectionlist", keySectionlist);
	}

	/**
	 *
	 * <p>
	 * 设置移动存储介质
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2014-5-9 - 下午4:28:03
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param dataMap
	 */
	private void setMobilestoragemediaParams(Map<String, Object> dataMap, Organ organ){
		List<SecrecyMobilestoragemedia> secrecyMobilestoragemediaList = secrecyMobilestoragemediaService.findPageAllList(null, null, organ, organ.getDistrict(), "0", 11);
		// 密级（用SecrecyLevelStr作为密级存放使用） 介质类型（用MediaTypeStr作为介质类型存放使用）
		for (SecrecyMobilestoragemedia secrecyMobilestoragemedia : secrecyMobilestoragemediaList) {
			secrecyMobilestoragemedia.setMediaTypeStr(DictionaryContext.getInstance().getDictionaryService().getOption("bmp", "media_type", secrecyMobilestoragemedia.getMediaType()).getOptionText());
			secrecyMobilestoragemedia.setSecrecyLevelStr(DictionaryContext.getInstance().getDictionaryService().getOption("bmp", "secrecy_level_thing", secrecyMobilestoragemedia.getSecrecyLevel()).getOptionText());
		}
		dataMap.put("secrecyMobilestoragemediaList", secrecyMobilestoragemediaList);
		//表格下方：处（室）  负责人；使用(创建人的部门和创建人)
        if(CollectionUtils.isNotEmpty(secrecyMobilestoragemediaList)){
        	dataMap.put("secrecyMobilestoragemediaCreateUserInfo", secrecyMobilestoragemediaList.get(0).getCreatePerson().getUserInfo());
        }
	}

	/**
	 *
	 * <p>
	 * 设置计算机
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2014-5-9 - 下午5:07:29
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param dataMap
	 */
	private void setSecrecyComputerParams(Map<String, Object> dataMap, Organ organ){
		User currentUserTmp = new User();
		currentUserTmp.setOrgan(organ);
		List<SecrecyComputer> secrecyComputerList = secrecyComputerService.getListPage(null,null, organ.getDistrict(), false, currentUserTmp, "0");
		for (SecrecyComputer secrecyComputer : secrecyComputerList) {
			secrecyComputer.setSecrecyLevelStr(DictionaryContext.getInstance().getDictionaryService().getOption("bmp", "secrecy_level_thing", secrecyComputer.getSecrecyLevel()).getOptionText());
			secrecyComputer.setComputerTypeStr(DictionaryContext.getInstance().getDictionaryService().getOption("bmp", "computer_type", secrecyComputer.getComputerType()).getOptionText());
		}
		dataMap.put("secrecyComputerList", secrecyComputerList);
		//表格下方：处（室）  负责人；使用(创建人的部门和创建人)
        if(CollectionUtils.isNotEmpty(secrecyComputerList)){
        	dataMap.put("secrecyComputerCreateUserInfo", secrecyComputerList.get(0).getCreatePerson().getUserInfo());
        }
	}

	/**
	 * <p>
	 * 设置工作计划与总结
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2014-5-12 - 上午10:01:03
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param dataMap
	 */
	private void setPlanAndSummaryParams(Map<String, Object> dataMap, Organ organ) {
		List<AnnualPlan> annualPlanList = annualPlanService.getListPage(null, null, organ, null, false, null);
		dataMap.put("annualPlanList", annualPlanList);
		List<AnnualSummary> annualSummaryList = this.annualSummaryService.getListPage(null, null, organ, null, false, null);
		dataMap.put("annualSummaryList", annualSummaryList);
	}

	/**
	 * <p>
	 * 设置保密宣传教育、承担课题情况、信息发布保密审查情况、部门网站管理
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2014-5-12 - 下午2:27:32
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param dataMap
	 */
	private void setPublicityEducationParams(Map<String, Object> dataMap, Organ organ) {
		List<Departmentwebsite> departmentwebsiteList = departmentWebsiteService.getListPage(null, null, organ, null, false, null);
		dataMap.put("departmentwebsiteList", departmentwebsiteList);
		//表格下方：处（室）  负责人；使用(创建人的部门和创建人)
        if(CollectionUtils.isNotEmpty(departmentwebsiteList)){
        	dataMap.put("departmentwebsiteCreateUserInfo", departmentwebsiteList.get(0).getCreatePerson());
        }
		
		
		List<InformationReview> informationReviewList = informationReviewService.getListPage(null, null, organ, null, false, null);
		for (InformationReview informationReview : informationReviewList) {
			informationReview.setInformationSourcesTxt(informationReviewService.dealOptions2Txt("bmp","infoSource",informationReview.getInformationSources()));
			informationReview.setReleaseWayTxt(informationReviewService.dealOptions2Txt("bmp","releaseWay",informationReview.getReleaseWay()));
		}
		dataMap.put("informationReviewList", informationReviewList);
		//表格下方：处（室）  负责人；使用(创建人的部门和创建人)
        if(CollectionUtils.isNotEmpty(informationReviewList)){
        	dataMap.put("informationReviewCreateUserInfo", informationReviewList.get(0).getCreatePerson());
        }
		
		List<Publicityeducation> publicityeducationList = publicityeducationService.getListPage(null, null, organ, null, false, null);
		dataMap.put("publicityeducationList", publicityeducationList);
		List<Undertaketask> undertaketaskList = undertaketaskService.getListPage(null, null, organ, null, false, null);
		dataMap.put("undertaketaskList", undertaketaskList);
	}

	/**
	 * <p>
	 * 设置保密会议记录、活动和涉密会议情况
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2014-5-13 - 下午2:14:40
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param dataMap
	 */
	private void setMeetingParams(Map<String, Object> dataMap, Organ organ) {
        //会议（活动）记录 保密工作领导小组会议记录
		Map<String, Object> params = new HashMap<String, Object>();
   		Meeting meeting1 = new Meeting();
    	meeting1.setMeetingCategory(meetingService.get("ca82caeb45d9c78f0145d9ce75990002", MeetingCategory.class));
    	List<Meeting> meetingList1 = meetingService.getPageList(null, meeting1, params, organ);
    	for (Meeting m : meetingList1) {
    		String attendUserInfoName = "";
    		String[] attendUserInfoIds = m.getAttendUserInfos().split(",");
            for (int i = 0; i < attendUserInfoIds.length; i++) {
                    String userInfoId = attendUserInfoIds[i].trim();
                    UserInfo userInfo = meetingService.get(userInfoId, UserInfo.class);
                    attendUserInfoName += userInfo.getName() + ",";
            }
    		m.setAttendUserInfoNames(attendUserInfoName);
		}
    	dataMap.put("meetingList1", meetingList1);
        //保密会议（活动）
    	meeting1.setMeetingCategory(meetingService.get("ca82caeb45d9c78f0145d9cdc2280001", MeetingCategory.class));
    	List<Meeting> meetingList2 = meetingService.getPageList(null, meeting1, params, organ);
    	for (Meeting meeting : meetingList2) {
    		String attendUserInfoName = "";
    		String[] attendUserInfoIds = meeting.getAttendUserInfos().split(",");
            for (int i = 0; i < attendUserInfoIds.length; i++) {
                    String userInfoId = attendUserInfoIds[i].trim();
                    UserInfo userInfo = meetingService.get(userInfoId, UserInfo.class);
                    attendUserInfoName += userInfo.getName() + ",";
            }
    		//保密参与情况字段用来存放出席人员名称
    		meeting.setAttendUserInfoNames(attendUserInfoName);
		}
    	dataMap.put("meetingList2", meetingList2);
	    //涉密会议（活动）
		meeting1.setMeetingCategory(meetingService.get("ca82caeb45d9c78f0145d9cd1cd00000", MeetingCategory.class));
		List<Meeting> meetingList3 = meetingService.getPageList(null, meeting1, params, organ);
    	for (Meeting meeting : meetingList3) {
    		meeting.setSecrecyLevelStr(DictionaryContext.getInstance().getDictionaryService().getOption("bmp", "secrecy_level_thing", meeting.getSecrecyLevel()).getOptionText());
		}
		dataMap.put("meetingList3", meetingList3);
		//表格下方：处（室）  负责人；使用(创建人的部门和创建人)
        if(CollectionUtils.isNotEmpty(meetingList3)){
        	dataMap.put("meeting3CreateUserInfo", meetingList3.get(0).getCreatePerson().getUserInfo());
        }
		
	}

	/**
	 * <p>
	 * 设置保密制度
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2014-5-13 - 上午10:09:42
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param dataMap
	 */
	private void setSecrecyInstitutionParams(Map<String, Object> dataMap, Organ organ) {
   		List<SecrecyInstitution> secrecyInstitutionList = secrecyInstitutionService.getsecrecyInstitutionQueryList(null, null, organ);
		dataMap.put("secrecyInstitutionList", secrecyInstitutionList);
	}

	/**
	 *
	 * <p>
	 * 设置开展保密监督检查工作情况
	 * </p>
	 * <p>
	 * 创建人 宋亚非  创建时间 2014-5-15 下午2:52:24
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param dataMap
	 * @param organ
	 */
	@SuppressWarnings("unchecked")
        private void setSecrecyCheckEventParams(Map<String, Object> dataMap, Organ organ){
	        List<SecrecyCheckEvent> secrecyCheckEventlist = secrecyCheckEventService.listForEc(null, null, organ);
                dataMap.put("secrecyCheckEventlist", secrecyCheckEventlist);
	}

	/**
        *
        * <p>
        * 设置保密工作奖惩情况
        * </p>
        * <p>
        * 创建人 宋亚非  创建时间 2014-5-15 下午2:52:24
        * </p>
        * <blockquote>
        * <h4>历史修改记录</h4>
        * <ul>
        * <li>修改人 修改时间 修改描述
        * </ul>
        * </blockquote>
        * @param dataMap
        * @param organ
        */
	private void setRewardAndPenaltyParams(Map<String, Object> dataMap, Organ organ){
	        User tmpUser = new User();
                tmpUser.setOrgan(organ);
                Map<String,Object> params = new HashMap<String, Object>();
	        List<RewardAndPenalty> rewardAndPenaltylist = rewardAndPenaltyService.getPageList(null, null,params, tmpUser);
	        dataMap.put("rewardAndPenaltylist", rewardAndPenaltylist);
	}

	/**
	 *
	 * <p>
	 * 设置收到密件情况情况
	 * </p>
	 * <p>
	 * 创建人 宋亚非  创建时间 2014-5-15 下午2:52:24
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param dataMap
	 * @param organ
	 */
	private void setReceiveConfidentialParams(Map<String, Object> dataMap, Organ organ){
               List<ReceiveConfidential> receiveConfidentialList = this.receiveConfidentialService.getListPage(null, null, organ, null, false, null);
               DictionaryService dictionAryService = DictionaryContext.getInstance().getDictionaryService();
               for( ReceiveConfidential rc : receiveConfidentialList ){
                       rc.setSecurityLevelTxt(dictionAryService.getOption("bmp","secrecy_level_thing", rc.getSecurityLevel()).getOptionText());
                       rc.setCarrierFormatTxt(dictionAryService.getOption("bmp","carrierFormat", rc.getCarrierFormat()).getOptionText());
               }
	       dataMap.put("receiveConfidentialList", receiveConfidentialList);
	       
	     //表格下方：处（室）  负责人；使用(创建人的部门和创建人)
           if(CollectionUtils.isNotEmpty(receiveConfidentialList)){
           	dataMap.put("receiveConfidentialCreateUserInfo", receiveConfidentialList.get(0).getCreatePerson());
           }
	}

	/**
	 *
	 * <p>
	 * 设置接触和知悉绝密级国家秘密文件情况
	 * </p>
	 * <p>
	 * 创建人 宋亚非  创建时间 2014-5-15 下午2:52:24
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param dataMap
	 * @param organ
	 */
	private void setContactSecretPersonParams(Map<String, Object> dataMap, Organ organ){
               List<ContactSecretPerson> contactSecretPersonList = this.contactSecretPersonService.getListPage(null, null, organ, null, false, null);
               DictionaryService dictionaryService = DictionaryContext.getInstance().getDictionaryService();
               if( contactSecretPersonList!=null && contactSecretPersonList.size()>0 ){
                       for( ContactSecretPerson csp : contactSecretPersonList ){
                               csp.setUndertackerNames(this.contactSecretPersonService.userInfoIds2Txt(csp.getUndertaker()));
                               csp.setApprovedLeaderNames(this.contactSecretPersonService.userInfoIds2Txt(csp.getApprovedLeader()));
                               csp.setContactWayTxt(dictionaryService.getOption("bmp", "contactWay", csp.getContactWay()).getOptionText());
                       }
               }
	        dataMap.put("contactSecretPersonList", contactSecretPersonList);
	      //表格下方：处（室）  负责人；使用(创建人的部门和创建人)
           if(CollectionUtils.isNotEmpty(contactSecretPersonList)){
           	dataMap.put("contactSecretCreateUserInfo", contactSecretPersonList.get(0).getCreatePerson());
           }
	}

	/**
	 *
	 * <p>
	 * 设置涉密载体销毁、涉密设备报废情况
	 * </p>
	 * <p>
	 * 创建人 宋亚非  创建时间 2014-5-15 下午2:52:24
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param dataMap
	 * @param organ
	 */
	private void setDestructionScrapParams(Map<String, Object> dataMap, Organ organ){
	       Map<String, Object> params = new HashMap<String, Object>();
	       User userTmp = new User();
               userTmp.setOrgan(organ);
               List<DestructionScrap> destructionScrapList = (List) destructionScrapService.getPageList(null, null,params, userTmp);
               DictionaryService dictionaryService = DictionaryContext.getInstance().getDictionaryService();
               if( destructionScrapList!=null && destructionScrapList.size()>0 ){
                       for( DestructionScrap ds : destructionScrapList ){
                               ds.setTypeTxt(dictionaryService.getOption("bmp", "secrecyCarrier_type", ds.getType()).getOptionText());
                               ds.setSecrecyLevelTxt(dictionaryService.getOption("bmp", "secrecy_level_thing", ds.getSecrecyLevel()).getOptionText());
                       }
               }
	       dataMap.put("destructionScrapList", destructionScrapList);
	}

	/**
	 *
	 * <p>
	 * 设置涉密载体借阅登记表情况
	 * </p>
	 * <p>
	 * 创建人 宋亚非  创建时间 2014-5-15 下午2:52:24
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param dataMap
	 * @param organ
	 */
	private void setSecrecyBorrowParams(Map<String, Object> dataMap, Organ organ){
               User userTmp = new User();
               userTmp.setOrgan(organ);
               Map<String, Object> params = new HashMap<String, Object>();
               List<SecrecyBorrow> secrecyBorrowList = (List<SecrecyBorrow>) secrecyBorrowService.getPageList(null, null,params, userTmp);
               DictionaryService dictionaryService = DictionaryContext.getInstance().getDictionaryService();
               if( secrecyBorrowList !=null && secrecyBorrowList.size()>0 ){
                       for( SecrecyBorrow sb : secrecyBorrowList ){
                               sb.setSecrecyLevelTxt(dictionaryService.getOption("bmp", "secrecy_level_thing", sb.getSecrecyLevel()).getOptionText());
                       }
               }
	       dataMap.put("secrecyBorrowList", secrecyBorrowList);
	     //表格下方：处（室）  负责人；使用(创建人的部门和创建人)
           if(CollectionUtils.isNotEmpty(secrecyBorrowList)){
           	dataMap.put("secrecyBorrowCreateUserInfo", secrecyBorrowList.get(0).getCreatePerson().getUserInfo());
           }
	}

	/**
	 *
	 * <p>
	 * 设置涉密文件（资料）打印登记情况
	 * </p>
	 * <p>
	 * 创建人 宋亚非  创建时间 2014-5-15 下午2:52:24
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param dataMap
	 * @param organ
	 */
	private void setSecrecyPrintParams(Map<String, Object> dataMap, Organ organ){
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
	        dataMap.put("secrecyPrintList", secrecyPrintList);
	      //表格下方：处（室）  负责人；使用(创建人的部门和创建人)
           if(CollectionUtils.isNotEmpty(secrecyPrintList)){
           	dataMap.put("secrecyPrintCreateUserInfo", secrecyPrintList.get(0).getCreatePerson().getUserInfo());
           }
	}

	/**
	 *
	 * <p>
	 * 设置涉密设备维修登记情况
	 * </p>
	 * <p>
	 * 创建人 宋亚非  创建时间 2014-5-15 下午2:52:24
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param dataMap
	 * @param organ
	 */
	private void setSecrecyMaintainParams(Map<String, Object> dataMap, Organ organ){
                Map<String, Object> params = new HashMap<String, Object>();
                User userTmp = new User();
                userTmp.setOrgan(organ);
                List<SecrecyMaintain> secrecyMaintainList = (List<SecrecyMaintain>) secrecyMaintainService.getPageList(null, null,params, userTmp);
                DictionaryService dictionaryService = DictionaryContext.getInstance().getDictionaryService();
                if( secrecyMaintainList!=null && secrecyMaintainList.size()>0 ){
                        for( SecrecyMaintain sm : secrecyMaintainList ){
                                sm.setSecrecyLevelTxt(dictionaryService.getOption("bmp", "secrecy_level_thing", sm.getSecrecyLevel()).getOptionText());
                        }
                }
	        dataMap.put("secrecyMaintainList", secrecyMaintainList);
	      //表格下方：处（室）  负责人；使用(创建人的部门和创建人)
           if(CollectionUtils.isNotEmpty(secrecyMaintainList)){
           	dataMap.put("secrecyMaintainCreateUserInfo", secrecyMaintainList.get(0).getCreatePerson().getUserInfo());
           }
	}

	/**
	 *
	 * <p>
	 * 设置装备保密技术防范设备情况
	 * </p>
	 * <p>
	 * 创建人 宋亚非  创建时间 2014-5-15 下午2:52:24
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param dataMap
	 * @param organ
	 */
	private void setSecrecyTechnologyPreventionParams(Map<String, Object> dataMap, Organ organ){
               User userTmp = new User();
               userTmp.setOrgan(organ);
               Map<String,Object> params = new HashMap<String, Object>();
               List<SecrecyTechnologyPrevention> secrecyTechnologyPreventionList = (List<SecrecyTechnologyPrevention>) secrecyTechnologyPreventionService.getPageList(null, null,params, userTmp);
	       dataMap.put("secrecyTechnologyPreventionList", secrecyTechnologyPreventionList);
	     //表格下方：处（室）  负责人；使用(创建人的部门和创建人)
           if(CollectionUtils.isNotEmpty(secrecyTechnologyPreventionList)){
           	dataMap.put("secrecyTechnologyPreventionCreateUserInfo", secrecyTechnologyPreventionList.get(0).getCreatePerson().getUserInfo());
           }
	}

	/**
	 *
	 * <p>
	 * 设置本单位产生国家秘密事项一览表
	 * </p>
	 * <p>
	 * 创建人 宋亚非  创建时间 2014-5-19 上午11:04:07
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param dataMap
	 * @param organ
	 */
	private void setSecrecyCountryItemParams(Map<String, Object> dataMap, Organ organ){
	        User userTmp = new User();
	        userTmp.setOrgan(organ);
            List<SecrecyCountryItem> secrecyCountryItemList = secrecyCountryItemService.queryCountryItemList(null, organ, null);// 查询
            DictionaryService dictionaryService = DictionaryContext.getInstance().getDictionaryService();
            if( secrecyCountryItemList!=null && secrecyCountryItemList.size()>0 ){
                    for( SecrecyCountryItem sci : secrecyCountryItemList ){
                            sci.setSecrecyLevelTxt(dictionaryService.getOption("bmp", "secrecy_level_thing", sci.getSecrecyLevel()).getOptionText());
                            sci.setSecrecyLimitTypeTxt(dictionaryService.getOption("bmp", "secrecy_limit_type", sci.getSecrecyLimitType()).getOptionText());
                            sci.setLimitTypeTxt(dictionaryService.getOption("bmp", "secrecy_limit_type", sci.getLimitType()).getOptionText());
                    }
            }
            dataMap.put("secrecyCountryItemList", secrecyCountryItemList);
            //表格下方：处（室）  负责人；使用(创建人的部门和创建人)
            if(CollectionUtils.isNotEmpty(secrecyCountryItemList)){
            	dataMap.put("secrecyCountryCreateUserInfo", secrecyCountryItemList.get(0).getCreatePerson().getUserInfo());
            }
            
	}

	/**
	 *
	 * <p>
	 * 设置涉密文件（资料）复印登记情况
	 * </p>
	 * <p>
	 * 创建人 宋亚非  创建时间 2014-5-19 上午11:04:07
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param dataMap
	 * @param organ
	 */
	private void setSecrecyCopyParams(Map<String, Object> dataMap, Organ organ){
	        User userTmp = new User();
	        userTmp.setOrgan(organ);
	        Map<String, Object> params = new HashMap<String, Object>();
	        DictionaryService dictionaryService = DictionaryContext.getInstance().getDictionaryService();
	        List<SecrecyCopy> secrecyCopyList = (List<SecrecyCopy>) secrecyCopyService.getPageList(null, null,params, userTmp);
	        if( secrecyCopyList!=null && secrecyCopyList.size()>0 ){
	                for( SecrecyCopy sc : secrecyCopyList ){
	                        sc.setSecrecyLevelTxt(dictionaryService.getOption("bmp", "secrecy_level_thing", sc.getSecrecyLevel()).getOptionText());
	                }
	        }
	        dataMap.put("secrecyCopyList", secrecyCopyList);
	      //表格下方：处（室）  负责人；使用(创建人的部门和创建人)
           if(CollectionUtils.isNotEmpty(secrecyCopyList)){
           	dataMap.put("secrecyCopyCreateUserInfo", secrecyCopyList.get(0).getCreatePerson().getUserInfo());
           }
	}

	/**
	 *
	 * <p>
	 * 设置涉密人员
	 * </p>
	 * <p>
	 * 创建人 宋亚非  创建时间 2014-5-19 上午11:04:07
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param dataMap
	 * @param organ
	 * @throws ParseException
	 */
	private void setSecrecyPersonParams(Map<String, Object> dataMap, Organ organ) {
	        DictionaryService dictionaryService = DictionaryContext.getInstance().getDictionaryService();
                List<SecrecyPerson> secrecyPersonList = new ArrayList<SecrecyPerson>();
                try {
                        secrecyPersonList = secrecyPersonModuleService.getPageAllList(null, null, organ, null, null, null, BmpAction.SECRECY_STATUS_NOW);
                        for (SecrecyPerson secrecyPerson : secrecyPersonList) {
                                if( secrecyPerson.getUserInfo().getBirthDate()!=null ){
                                        secrecyPerson.setPersonAge(DateUtils.getYearNumber(secrecyPerson.getUserInfo().getBirthDate(), new Date()) + "");
                                }
                                if(dictionaryService.getOption("person", "sex", Integer.parseInt(secrecyPerson.getUserInfo().getSex()))!=null){
                                        secrecyPerson.setSexTxt(dictionaryService.getOption("person", "sex",  Integer.parseInt(secrecyPerson.getUserInfo().getSex())).getOptionText());
                                }
                                if(secrecyPerson.getUserInfo().getLearningLevel()!=null){
                                        secrecyPerson.setLearnLevelTxt(dictionaryService.getOption("person", "learning_level", secrecyPerson.getUserInfo().getLearningLevel()).getOptionText());
                                }
                                if(secrecyPerson.getUserInfo().getNation()!=null){
                                        secrecyPerson.setNationTxt(dictionaryService.getOption("person", "nation", secrecyPerson.getUserInfo().getNation()).getOptionText());
                                }
                                if(StringUtils.isNotEmpty(secrecyPerson.getUserInfo().getPolity())){
                                	int polity = Integer.parseInt(secrecyPerson.getUserInfo().getPolity());
                    				if(dictionaryService.getOption("person", "polity", polity)!=null){
                    					secrecyPerson.setPolityTxt(dictionaryService.getOption("person", "polity", polity).getOptionText());
                    				}
                    			}
                                if(secrecyPerson.getUserInfo().getAdministrativeLevel()!=null){
                                        secrecyPerson.setAdministrativeLevelTxt(dictionaryService.getOption("bmp", "person_admin_level", secrecyPerson.getUserInfo().getAdministrativeLevel()).getOptionText());
                                }
                                if(secrecyPerson.getSecrecyPersonLevel()!=null){
                                        secrecyPerson.setSecrecyPersonLevelTxt(dictionaryService.getOption("bmp", "secrecy_level_person", secrecyPerson.getSecrecyPersonLevel()).getOptionText());
                                }
                                if( secrecyPerson.getResponsiblePerson()!=null && secrecyPerson.getResponsiblePerson() == 0 ){
                                        secrecyPerson.setResponsiblePersonTxt("否");
                                }
                                if( secrecyPerson.getResponsiblePerson()!=null && secrecyPerson.getResponsiblePerson() == 1 ){
                                        secrecyPerson.setResponsiblePersonTxt("是");
                                }
                        }
                } catch (ParseException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
	        dataMap.put("secrecyPersonList", secrecyPersonList);
	}

	/**
	 *
	 * <p>
	 * 设置密品
	 * </p>
	 * <p>
	 * 创建人 宋亚非  创建时间 2014-5-19 上午11:04:07
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param dataMap
	 * @param organ
	 * @throws ParseException
	 */
	private void setSecrecyProductsParams(Map<String, Object> dataMap, Organ organ) {
                List<SecrecyProducts> secrecyProductsList = secrecyProductsService.querySecrecyProductsList(null, organ, null);
                DictionaryService dictionaryService = DictionaryContext.getInstance().getDictionaryService();
                if( secrecyProductsList!=null && secrecyProductsList.size()>0 ){
                        for( SecrecyProducts sp : secrecyProductsList ){
                                sp.setSecrecyLevelTxt(dictionaryService.getOption("bmp", "secrecy_level_thing", sp.getSecrecyLevel()).getOptionText());
                                sp.setLimitTypeTxt(dictionaryService.getOption("bmp", "limit_type", sp.getLimitType()).getOptionText());
                        }
                }
	        dataMap.put("secrecyProductsList", secrecyProductsList);
	}

	/**
	 *
	 * <p>
	 * 设置涉密科研项目
	 * </p>
	 * <p>
	 * 创建人 宋亚非  创建时间 2014-5-19 上午11:04:07
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param dataMap
	 * @param organ
	 * @throws ParseException
	 */
	private void setSecrecyResearchProjectParams(Map<String, Object> dataMap, Organ organ) {
                List<SecrecyResearchProject> secrecyResearchProjectList = secrecyResearchProjectService.querySecrecyResearchProjectList(null, organ, null);
                DictionaryService dictionaryService = DictionaryContext.getInstance().getDictionaryService();
	        if( secrecyResearchProjectList!=null && secrecyResearchProjectList.size()>0 ){
	                for( SecrecyResearchProject srp : secrecyResearchProjectList ){
	                        srp.setSecrecyLevelTxt(dictionaryService.getOption("bmp", "secrecy_level_thing", srp.getSecrecyLevel()).getOptionText());
	                        srp.setLimitTypeTxt(dictionaryService.getOption("bmp", "limit_type", srp.getLimitType()).getOptionText());
	                        srp.setProjectStateTxt(dictionaryService.getOption("bmp", "project_state", srp.getProjectState()).getOptionText());
	                }
	        }
	        dataMap.put("secrecyResearchProjectList", secrecyResearchProjectList);
	}

	/**
	 *
	 * <p>
	 * 设置涉密网络
	 * </p>
	 * <p>
	 * 创建人 宋亚非  创建时间 2014-5-19 上午11:04:07
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param dataMap
	 * @param organ
	 * @throws ParseException
	 */
	private void setSecrecyNetworkParams(Map<String, Object> dataMap, Organ organ) {
	        User currentUserTemp = new User();
                currentUserTemp.setOrgan(organ);
                List<SecrecyNetwork> secrecyNetworkList = secrecyNetworkService.getListPage(null, null, organ.getDistrict(), false, currentUserTemp, "0");
                DictionaryService dictionaryService = DictionaryContext.getInstance().getDictionaryService();
                if( secrecyNetworkList!=null && secrecyNetworkList.size()>0 ){
                        for( SecrecyNetwork sn : secrecyNetworkList ){
                                if( sn!=null ){
                                        sn.setSecrecyLevelTxt(dictionaryService.getOption("bmp", "secrecy_level_thing", sn.getSecrecyLevel()).getOptionText());
                                        sn.setNetworkTypeTxt(dictionaryService.getOption("bmp", "network_type", sn.getNetworkType()).getOptionText());
                                        if( sn.getIsReview()!=null && sn.getIsReview() == 1 ){
                                                sn.setIsReviewTxt("是");
                                        }
                                        if( sn.getIsReview()!=null && sn.getIsReview() == 0 ){
                                                sn.setIsReviewTxt("否");
                                        }
                                        if( sn.getIsApproval()!=null && sn.getIsApproval() == 1 ){
                                            sn.setIsApprovalTxt("是");
                                        }
                                        if( sn.getIsApproval()!=null && sn.getIsApproval() == 0 ){
                                                sn.setIsApprovalTxt("否");
                                        }
                                }
                        }
                }

	        dataMap.put("secrecyNetworkList", secrecyNetworkList);
	}

	/**
	 *
	 * <p>
	 * 设置其他设备
	 * </p>
	 * <p>
	 * 创建人 宋亚非  创建时间 2014-5-19 上午11:04:07
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param dataMap
	 * @param organ
	 * @throws ParseException
	 */
	private void setSecrecyOthersParams(Map<String, Object> dataMap, Organ organ) {
	        User currentUserTemp = new User();
	        currentUserTemp.setOrgan(organ);
                List<SecrecyOthers> secrecyOthersList = secrecyOthersService.findPageAllList(null, null, organ, organ.getDistrict(), "", 11);
	        DictionaryService dictionaryService = DictionaryContext.getInstance().getDictionaryService();
	        if( secrecyOthersList!=null && secrecyOthersList.size()>0 ){
	                for( SecrecyOthers so : secrecyOthersList ){
	                        if( so!=null ){
	                                so.setSecrecyLevelTxt(dictionaryService.getOption("bmp", "secrecy_level_thing", so.getSecrecyLevel()).getOptionText());
	                                so.setSecrecyothersTypeTxt(dictionaryService.getOption("bmp", "other_media_type", so.getSecrecyothersType()).getOptionText());
	                        }
	                }
	        }
	        dataMap.put("secrecyOthersList", secrecyOthersList);
	}
	/**
        *
        * <p>
        * 保密工作专（兼）职人员情况
        * </p>
        * <p>
        * 创建人 刘兵兵  创建时间 2014-5-19 上午11:04:07
        * </p>
        * <blockquote>
        * <h4>历史修改记录</h4>
        * <ul>
        * <li>修改人 修改时间 修改描述
        * </ul>
        * </blockquote>
        * @param dataMap
        * @param organ
        * @throws ParseException
        */
       private void setSecrecyFullPartTimeParams(Map<String, Object> dataMap, Organ organ) {
               User userTmp = new User();
               userTmp.setOrgan(organ);
               Map<String, Object> params = new HashMap<String, Object>();
               DictionaryService dictionaryService = DictionaryContext.getInstance().getDictionaryService();
               List<SecrecyFullPartTime> secrecyFullPartTimeList = (List<SecrecyFullPartTime>) secrecyFullPartTimeService.getPageList(null, null,params, userTmp);
               if( secrecyFullPartTimeList!=null && secrecyFullPartTimeList.size()>0 ){
                       for( SecrecyFullPartTime sc : secrecyFullPartTimeList ){
                               sc.setDegreeTxt(dictionaryService.getOption("person", "learning_level", sc.getDegree()).getOptionText());

                               if (sc.getIsFull()==0) {
                                       sc.setIsFullTxt("专职");
                               }
                               if (sc.getIsFull()==1) {
                                       sc.setIsFullTxt("兼职");

                               }
                               if (sc.getIsTrain()==0) {
                                       sc.setIsTrainTxt("是");
                               }
                               if (sc.getIsTrain()==1) {
                                       sc.setIsTrainTxt(" 否");
                               }
                       }
               }
               dataMap.put("secrecyFullPartTimeList", secrecyFullPartTimeList);
       }

       /**
        *
        * <p>
        * 定解密工作情况
        * </p>
        * <p>
        * 创建人 刘兵兵  创建时间 2014-5-19 上午11:04:07
        * </p>
        * <blockquote>
        * <h4>历史修改记录</h4>
        * <ul>
        * <li>修改人 修改时间 修改描述
        * </ul>
        * </blockquote>
        * @param dataMap
        * @param organ
        * @throws ParseException
        */
       private void setSetTheDecryptionParams(Map<String, Object> dataMap, Organ organ) {
               User userTmp = new User();
               userTmp.setOrgan(organ);
               Map<String, Object> params = new HashMap<String, Object>();
               DictionaryService dictionaryService = DictionaryContext.getInstance().getDictionaryService();
               SetTheDecryption setTheDecryption = new SetTheDecryption();
               setTheDecryption.setSecrecyType(0);
               //定密
               List<SetTheDecryption> setTheDecryptionListByDingMi = (List<SetTheDecryption>) setTheDecryptionService.getPageList(null, setTheDecryption,params, userTmp);
               //解密
               setTheDecryption.setSecrecyType(1);
               List<SetTheDecryption> setTheDecryptionListByJieMi = (List<SetTheDecryption>) setTheDecryptionService.getPageList(null, setTheDecryption,params, userTmp);
               if( setTheDecryptionListByDingMi!=null && setTheDecryptionListByDingMi.size()>0 ){
                       for( SetTheDecryption sc : setTheDecryptionListByDingMi ){
                               sc.setSecrecyLevelTxt(dictionaryService.getOption("bmp", "secrecy_level_thing", sc.getSecrecyLevel()).getOptionText());
                       }
               }
               if( setTheDecryptionListByJieMi!=null && setTheDecryptionListByJieMi.size()>0 ){
                       for( SetTheDecryption sc : setTheDecryptionListByJieMi ){
                               sc.setSecrecyLevelTxt(dictionaryService.getOption("bmp", "secrecy_level_thing", sc.getSecrecyLevel()).getOptionText());
                       }
               }
               dataMap.put("setTheDecryptionListByDingMi", setTheDecryptionListByDingMi);
               dataMap.put("setTheDecryptionListByJieMi", setTheDecryptionListByJieMi);
             //表格下方：处（室）  负责人；使用(创建人的部门和创建人)
               if(CollectionUtils.isNotEmpty(setTheDecryptionListByDingMi)){
               	dataMap.put("dingMiCreateUserInfo", setTheDecryptionListByDingMi.get(0).getCreatePerson().getUserInfo());
               }
       }
       /**
        *
        * <p>
        * 泄密案件情况
        * </p>
        * <p>
        * 创建人 刘兵兵  创建时间 2014-5-19 上午11:04:07
        * </p>
        * <blockquote>
        * <h4>历史修改记录</h4>
        * <ul>
        * <li>修改人 修改时间 修改描述
        * </ul>
        * </blockquote>
        * @param dataMap
        * @param organ
        * @throws ParseException
        */
       private void setDiscloseSecrecyParams(Map<String, Object> dataMap, Organ organ) {
               User userTmp = new User();
               userTmp.setOrgan(organ);
               DictionaryService dictionaryService = DictionaryContext.getInstance().getDictionaryService();
               List<DiscloseSecrecy> discloseSecrecyList = (List<DiscloseSecrecy>)discloseSecrecyService.listForAll(null, userTmp,null, null);
               if( discloseSecrecyList!=null && discloseSecrecyList.size()>0 ){
                       for( DiscloseSecrecy sc : discloseSecrecyList ){
                               sc.setSecrecyLevelTxt(dictionaryService.getOption("bmp", "secrecy_level_thing", sc.getSecrecyLevel()).getOptionText());
                               sc.setCaseTypeTxt(dictionaryService.getOption("bmp", "case_Type", sc.getCaseType()).getOptionText());
                               sc.setDutyOrganKindTxt(dictionaryService.getOption("bmp", "duty_organ_kind", sc.getDutyOrganKind()).getOptionText());
                               sc.setCasekindTxt(dictionaryService.getOption("bmp", "case_kind", sc.getCasekind()).getOptionText());
                               sc.setDealResultTxt(dictionaryService.getOption("bmp", "find_result", sc.getDealResult()).getOptionText());
                       }
               }
               dataMap.put("discloseSecrecyList", discloseSecrecyList);
       }
	/**
	 * @param secrecyWorkOrganModuleService 设置secrecyWorkOrganModuleService
	 */
	public void setSecrecyWorkOrganModuleService(SecrecyWorkOrganModuleService secrecyWorkOrganModuleService) {
		this.secrecyWorkOrganModuleService = secrecyWorkOrganModuleService;
	}

	/**
	 * @param secrecyWorkOrganRelationMemberModuleService 设置secrecyWorkOrganRelationMemberModuleService
	 */
	public void setSecrecyWorkOrganRelationMemberModuleService(
			SecrecyWorkOrganRelationMemberModuleService secrecyWorkOrganRelationMemberModuleService) {
		this.secrecyWorkOrganRelationMemberModuleService = secrecyWorkOrganRelationMemberModuleService;
	}


	/**
	 * @param partModuleService 设置partModuleService
	 */
	public void setPartModuleService(PartModuleService partModuleService) {
		this.partModuleService = partModuleService;
	}


	/**
	 * @param keySectionModuleService 设置keySectionModuleService
	 */
	public void setKeySectionModuleService(KeySectionModuleService keySectionModuleService) {
		this.keySectionModuleService = keySectionModuleService;
	}


	/**
	 * @param secrecyMobilestoragemediaService 设置secrecyMobilestoragemediaService
	 */
	public void setSecrecyMobilestoragemediaService(SecrecyMobilestoragemediaService secrecyMobilestoragemediaService) {
		this.secrecyMobilestoragemediaService = secrecyMobilestoragemediaService;
	}


	/**
	 * @param secrecyComputerService 设置secrecyComputerService
	 */
	public void setSecrecyComputerService(SecrecyComputerService secrecyComputerService) {
		this.secrecyComputerService = secrecyComputerService;
	}


	/**
	 * @param annualPlanService 设置annualPlanService
	 */
	public void setAnnualPlanService(AnnualPlanService annualPlanService) {
		this.annualPlanService = annualPlanService;
	}


	/**
	 * @param annualSummaryService 设置annualSummaryService
	 */
	public void setAnnualSummaryService(AnnualSummaryService annualSummaryService) {
		this.annualSummaryService = annualSummaryService;
	}


	/**
	 * @param departmentWebsiteService 设置departmentWebsiteService
	 */
	public void setDepartmentWebsiteService(DepartmentWebsiteService departmentWebsiteService) {
		this.departmentWebsiteService = departmentWebsiteService;
	}


	/**
	 * @param informationReviewService 设置informationReviewService
	 */
	public void setInformationReviewService(InformationReviewService informationReviewService) {
		this.informationReviewService = informationReviewService;
	}


	/**
	 * @param publicityeducationService 设置publicityeducationService
	 */
	public void setPublicityeducationService(PublicityeducationService publicityeducationService) {
		this.publicityeducationService = publicityeducationService;
	}


	/**
	 * @param undertaketaskService 设置undertaketaskService
	 */
	public void setUndertaketaskService(UndertaketaskService undertaketaskService) {
		this.undertaketaskService = undertaketaskService;
	}


	/**
	 * @param secrecyInstitutionService 设置secrecyInstitutionService
	 */
	public void setSecrecyInstitutionService(SecrecyInstitutionService secrecyInstitutionService) {
		this.secrecyInstitutionService = secrecyInstitutionService;
	}


	/**
	 * @param meetingService 设置meetingService
	 */
	public void setMeetingService(MeetingService meetingService) {
		this.meetingService = meetingService;
	}


        /**
         * @param secrecyCheckEventService the secrecyCheckEventService to set
         */
        public void setSecrecyCheckEventService(SecrecyCheckEventService secrecyCheckEventService) {
                this.secrecyCheckEventService = secrecyCheckEventService;
        }


        /**
         * @param rewardAndPenaltyService the rewardAndPenaltyService to set
         */
        public void setRewardAndPenaltyService(RewardAndPenaltyService rewardAndPenaltyService) {
                this.rewardAndPenaltyService = rewardAndPenaltyService;
        }


        /**
         * @param receiveConfidentialService the receiveConfidentialService to set
         */
        public void setReceiveConfidentialService(ReceiveConfidentialService receiveConfidentialService) {
                this.receiveConfidentialService = receiveConfidentialService;
        }


        /**
         * @param contactSecretPersonService the contactSecretPersonService to set
         */
        public void setContactSecretPersonService(ContactSecretPersonService contactSecretPersonService) {
                this.contactSecretPersonService = contactSecretPersonService;
        }


        /**
         * @param destructionScrapService the destructionScrapService to set
         */
        public void setDestructionScrapService(DestructionScrapService destructionScrapService) {
                this.destructionScrapService = destructionScrapService;
        }


        /**
         * @param secrecyBorrowService the secrecyBorrowService to set
         */
        public void setSecrecyBorrowService(SecrecyBorrowService secrecyBorrowService) {
                this.secrecyBorrowService = secrecyBorrowService;
        }


        /**
         * @param secrecyPrintService the secrecyPrintService to set
         */
        public void setSecrecyPrintService(SecrecyPrintService secrecyPrintService) {
                this.secrecyPrintService = secrecyPrintService;
        }


        /**
         * @param secrecyMaintainService the secrecyMaintainService to set
         */
        public void setSecrecyMaintainService(SecrecyMaintainService secrecyMaintainService) {
                this.secrecyMaintainService = secrecyMaintainService;
        }


        /**
         * @param secrecyTechnologyPreventionService the secrecyTechnologyPreventionService to set
         */
        public void setSecrecyTechnologyPreventionService(SecrecyTechnologyPreventionService secrecyTechnologyPreventionService) {
                this.secrecyTechnologyPreventionService = secrecyTechnologyPreventionService;
        }


        /**
         * @param secrecyCountryItemService the secrecyCountryItemService to set
         */
        public void setSecrecyCountryItemService(SecrecyCountryItemService secrecyCountryItemService) {
                this.secrecyCountryItemService = secrecyCountryItemService;
        }


        /**
         * @param secrecyCopyService the secrecyCopyService to set
         */
        public void setSecrecyCopyService(SecrecyCopyService secrecyCopyService) {
                this.secrecyCopyService = secrecyCopyService;
        }


        /**
         * @param secrecyPersonModuleService the secrecyPersonModuleService to set
         */
        public void setSecrecyPersonModuleService(SecrecyPersonModuleService secrecyPersonModuleService) {
                this.secrecyPersonModuleService = secrecyPersonModuleService;
        }


        /**
         * @param secrecyProductsService the secrecyProductsService to set
         */
        public void setSecrecyProductsService(SecrecyProductsService secrecyProductsService) {
                this.secrecyProductsService = secrecyProductsService;
        }


        /**
         * @param secrecyResearchProjectService the secrecyResearchProjectService to set
         */
        public void setSecrecyResearchProjectService(SecrecyResearchProjectService secrecyResearchProjectService) {
                this.secrecyResearchProjectService = secrecyResearchProjectService;
        }


        /**
         * @param secrecyNetworkService the secrecyNetworkService to set
         */
        public void setSecrecyNetworkService(SecrecyNetworkService secrecyNetworkService) {
                this.secrecyNetworkService = secrecyNetworkService;
        }


        /**
         * @param secrecyOthersService the secrecyOthersService to set
         */
        public void setSecrecyOthersService(SecrecyOthersService secrecyOthersService) {
                this.secrecyOthersService = secrecyOthersService;
        }


        /**
         * @param secrecyFullPartTimeService 设置 secrecyFullPartTimeService
         */
        public void setSecrecyFullPartTimeService(SecrecyFullPartTimeService secrecyFullPartTimeService) {
                this.secrecyFullPartTimeService = secrecyFullPartTimeService;
        }


        /**
         * @param setTheDecryptionService 设置 setTheDecryptionService
         */
        public void setSetTheDecryptionService(SetTheDecryptionService setTheDecryptionService) {
                this.setTheDecryptionService = setTheDecryptionService;
        }


        /**
         * @param discloseSecrecyService 设置 discloseSecrecyService
         */
        public void setDiscloseSecrecyService(DiscloseSecrecyService discloseSecrecyService) {
                this.discloseSecrecyService = discloseSecrecyService;
        }



}