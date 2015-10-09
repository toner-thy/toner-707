package com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.action;

import java.io.StringWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.cdthgk.bmp.core.action.BmpAction;
import com.cdthgk.bmp.log.context.BusinessLogContext;
import com.cdthgk.bmp.log.vo.BusinessLog;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.dto.SecrecyWorkOrganDto;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.service.SecrecyWorkOrganMemberUnitModuleService;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.service.SecrecyWorkOrganMembersUnitChangeService;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.service.SecrecyWorkOrganModuleService;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.service.SecrecyWorkOrganRelationMemberChangeService;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.service.SecrecyWorkOrganRelationMemberModuleService;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.transmitor.exchange.SecrecyWorkOrganExchanger;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.vo.PersonGroupRelationChange;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.vo.SecrecyWorkOrgan;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.vo.SecrecyWorkOrganMemberUnit;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.vo.SecrecyWorkOrganMembersUnitChange;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.vo.SecrecyWorkOrganRelationMember;
import com.cdthgk.bmp.statinfo.dto.QueryDto;
import com.cdthgk.common.bean.BeanUtils;
import com.cdthgk.common.bean.rule.CopyRuleEnum;
import com.cdthgk.common.db.data.xml.XmlExportor;
import com.cdthgk.common.lang.DateUtils;
import com.cdthgk.common.lang.StringUtils;
import com.cdthgk.exchange.serverorganmapping.service.ServerReportMappingService;
import com.cdthgk.platform.attachment.service.AttachmentService;
import com.cdthgk.platform.base.util.ReportState;
import com.cdthgk.platform.dataValidate.service.DataValidateService;
import com.cdthgk.platform.dictionary.context.DictionaryContext;
import com.cdthgk.platform.dictionary.domain.DictionaryOption;
import com.cdthgk.platform.dictionary.spi.DictionaryService;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.core.OrganizationContext;
import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;


/**
 * <p>
 * 保密工作机构 Action 类
 * </p>
 * <p>
 * 刘椿成 2013-01-06 16:43:03
 * </p>
 * <blockquote>
 * <h4>历史修改记录</h4>
 * <ul>
 * <li>修改人 修改时间 修改描述
 * </ul>
 * </blockquote>
 * <p>
 * copyright liucc 2012, all rights reserved.
 * </p>
 * @author liucc
 * @since 1.0
 * @version 1.0
 * @param <E>
 */
public class SecrecyWorkOrganAction<E> extends BmpAction {
	// 业务相关字段
	private static final long serialVersionUID = -2561934305983981072L;
	private SecrecyWorkOrganModuleService secrecyWorkOrganModuleService;
	private SecrecyWorkOrganMemberUnitModuleService secrecyWorkOrganMemberUnitModuleService;
	private SecrecyWorkOrganRelationMemberChangeService secrecyWorkOrganRelationMemberChangeService;
	private SecrecyWorkOrganMemberUnit secrecyWorkOrganMemberUnit;
	private SecrecyWorkOrganMembersUnitChangeService secrecyWorkOrganMembersUnitChangeService;
	private SecrecyWorkOrgan secrecyWorkOrgan;
	private List<SecrecyWorkOrgan> secrecyWorkOrganList;
	private List<SecrecyWorkOrganMemberUnit> secrecyWorkOrganMemberUnitList;
	private SecrecyWorkOrganRelationMember secrecyWorkOrganRelationMember;
	private SecrecyWorkOrganRelationMemberModuleService secrecyWorkOrganRelationMemberModuleService;
	private List<SecrecyWorkOrganRelationMember> secrecyWorkOrganRelationMemberList;
	private DataValidateService dataValidateService;
	private Organ organ;
	private District district;

	private DataSource dataSource;

	// 附件使用字段
	private AttachmentService attachmentService;
	private List<String> secrecyWorkOrganAttachs;

	//部门变更或者部门改名的标志：1：部门改名；2部门变更
	private String deptFlag;

	private String secrecyWorkOrganIds;
	private SecrecyWorkOrganExchanger secrecyWorkOrganExchanger;
	private ServerReportMappingService serverReportMappingService;

	private String expBtnFlag;

	// *********************** 方 法 ***********************
	// 构造器
	/** 默认构造器 */
	public SecrecyWorkOrganAction() {
	}

	/**
	 * <p>
	 * 到详情页面
	 * </p>
	 * <p>
	 * 刘椿成 2012-12-15 10:43:03
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * <p>
	 * copyright 刘椿成 2012, all rights reserved.
	 * </p>
	 * @author 刘椿成
	 * @since 1.0
	 * @version 1.0
	 */
	public String detail() {
		secrecyWorkOrgan = secrecyWorkOrganModuleService.getSecrecyWorkOrganByOrgan(getCurrentUser().getOrgan());
		if (secrecyWorkOrgan == null) {
			// 不存在保密工作机构
			return redirectActionResult(ADD);
		} else {
			if (organ == null) {
				organ = getCurrentUser().getOrgan();
			}else {
				organ = secrecyWorkOrganMemberUnitModuleService.get(getRequest().getParameter("organ.organId").toString(), Organ.class);
			}

			// 得到保密办成员列表
			putToRequest("secrecyWorkOrganMemberUnitList", secrecyWorkOrganMemberUnitModuleService.getPageList(secrecyWorkOrgan,organ, PERSON_CHANGE_NOW));

			// 得到保密机构成员列表
			List<SecrecyWorkOrganRelationMember> secrecyWorkOrganRelationMemberList = secrecyWorkOrganRelationMemberModuleService.getPageList(secrecyWorkOrgan,organ,PERSON_CHANGE_NOW);
			putToRequest("secrecyWorkOrganRelationMemberList", secrecyWorkOrganRelationMemberList);
			secrecyWorkOrgan.setEstablishmentNum(secrecyWorkOrganRelationMemberList == null ? 0 : secrecyWorkOrganRelationMemberList.size());
		}

		// 得到附件
		putToRequest("attachments", attachmentService.getAttachmentsByHostId(secrecyWorkOrgan.getSecrecyWorkOrganId()));
		putToRequest("secrecyWorkOrgan", secrecyWorkOrgan);
		return DETAIL;
	}

	/**
	 *
	 * <p>
	 * 方法名：exportData 导出信息
	 * </p>
	 * <p>
	 * 宋亚非 2013-6-5 上午10:40:23
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@return
	 */
	public String exportData(){
		Map<String, Object> params = new HashMap<String, Object>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		params.put("dateFormat", dateFormat);
		secrecyWorkOrgan = secrecyWorkOrganModuleService.getSecrecyWorkOrganByOrgan(getCurrentUser().getOrgan());
		if (secrecyWorkOrgan == null) {
			// 不存在保密工作机构
			return SUCCESS;
		} else {
			if (organ == null) {
				organ = getCurrentUser().getOrgan();
			}else {
				organ = secrecyWorkOrganMemberUnitModuleService.get(getRequest().getParameter("organ.organId").toString(), Organ.class);
			}
			// 得到保密办成员列表
			params.put("secrecyWorkOrganMemberUnitList", secrecyWorkOrganMemberUnitModuleService.getPageList(secrecyWorkOrgan,organ, PERSON_CHANGE_NOW));
			// 得到保密机构成员列表
			params.put("secrecyWorkOrganRelationMemberList", secrecyWorkOrganRelationMemberModuleService.getPageList(secrecyWorkOrgan,organ,PERSON_CHANGE_NOW));
		}
		List<DictionaryOption> groupTypeList = new ArrayList<DictionaryOption>();
		DictionaryOption dictionaryOption1 = new DictionaryOption();
		DictionaryOption dictionaryOption2 = new DictionaryOption();
		dictionaryOption1.setOptionValue(1);
		dictionaryOption1.setOptionText("保密工作领导小组");
		dictionaryOption2.setOptionValue(2);
		dictionaryOption2.setOptionText("保密委员会");
		groupTypeList.add(dictionaryOption1);
		groupTypeList.add(dictionaryOption2);
		params.put("groupTypeList", groupTypeList);

		//数据字典工具
                DictionaryService dictionary = DictionaryContext.getInstance().getDictionaryService();
                params.put("dictionary", dictionary);

		params.put("secrecyWorkOrgan", secrecyWorkOrgan);
		setResultData(params);
		return SUCCESS;
	}

	/**
	 * <p>
	 * 到新增页面
	 * </p>
	 * <p>
	 * 刘椿成 2012-12-15 10:43:03
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * <p>
	 * copyright 刘椿成 2012, all rights reserved.
	 * </p>
	 * @author 刘椿成
	 * @since 1.0
	 * @version 1.0
	 */
	public String add() {
		putToRequest("currentOrgan", getCurrentUser().getOrgan());
		return SUCCESS;
	}

	/**
	 * <p>
	 * 新增操作
	 * </p>
	 * <p>
	 * 刘椿成 2012-12-15 10:43:03
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * <p>
	 * copyright 刘椿成 2012, all rights reserved.
	 * </p>
	 * @author 刘椿成
	 * @since 1.0
	 * @version 1.0
	 */
	public String adding() {
		// 传入的字段
		secrecyWorkOrgan = secrecyWorkOrganModuleService.saveSystemDataBySecrecyWorkOrgan(secrecyWorkOrgan, getCurrentUser(), null);
		secrecyWorkOrgan.setCreatePerson(getCurrentUser());
		secrecyWorkOrgan.setCreateTime(new Date());
		secrecyWorkOrgan.setOrgan(getCurrentUser().getOrgan());
		//修改人：宋亚非   修改原因：数据库统一至一个库后，取消上报功能，填写信息后状态即为已上报   修改时间：2013-06-03
		//secrecyWorkOrgan.setReportState(ReportState.REPORT_NO);
		secrecyWorkOrgan.setReportState(ReportState.REPORT_YES);
		secrecyWorkOrganModuleService.save(secrecyWorkOrgan);

		// 设置信息
		this.addActionMessage(getMessageConstant().getSaveSuccess() + "请继续添加该保密组织机构下的保密机构成员和保密办成员信息！");
		secrecyWorkOrgan = secrecyWorkOrganModuleService.get(secrecyWorkOrgan.getSecrecyWorkOrganId());
		putToRequest("organ", getCurrentUser().getOrgan());

		// 上传附件
		attachmentService.updateHostId(secrecyWorkOrgan.getSecrecyWorkOrganId(),secrecyWorkOrganAttachs);

		// 返回当前登录单位
		if (organ == null) {
			organ = getCurrentUser().getOrgan();
		}else {
			// 得到指定的单位
			organ = secrecyWorkOrganMemberUnitModuleService.get(getRequest().getParameter("organ.organId").toString(), Organ.class);
		}

		// 得到保密办成员列表
		putToRequest("secrecyWorkOrganMemberUnitList", secrecyWorkOrganMemberUnitModuleService.getPageList(secrecyWorkOrgan,organ, PERSON_CHANGE_NOW));

		// 得到保密机构成员列表
		putToRequest("secrecyWorkOrganRelationMemberList", secrecyWorkOrganRelationMemberModuleService.getPageList(secrecyWorkOrgan,organ, PERSON_CHANGE_HISTORY));

		// 得到附件
		putToRequest("attachments", attachmentService.getAttachmentsByHostId(secrecyWorkOrgan.getSecrecyWorkOrganId()));

		BusinessLog log = new BusinessLog();
                log.setBusinessName("保密工作机构");
                log.setPrimaryKey(secrecyWorkOrgan.getSecrecyWorkOrganId());
                BusinessLogContext.getInstance().getBusinessLogService().saveAddBusinessLogByModule(getCurrentUser(), log, secrecyWorkOrgan);
		return "edit";
	}

	/**
	 * <p>
	 * 到编辑页面
	 * </p>
	 * <p>
	 * 刘椿成 2012-12-15 10:43:03
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * <p>
	 * copyright 刘椿成 2012, all rights reserved.
	 * </p>
	 * @author 刘椿成
	 * @since 1.0
	 * @version 1.0
	 */
	public String edit() {
		// 检查
		if(secrecyWorkOrgan==null || secrecyWorkOrgan.getSecrecyWorkOrganId()==null || secrecyWorkOrgan.getSecrecyWorkOrganId().equals("")){
			this.addActionMessage("您所访问的资源Id不存在，请确认后重试。");
			return redirectActionResult(DETAIL);
		}
		secrecyWorkOrgan = secrecyWorkOrganModuleService.get(secrecyWorkOrgan.getSecrecyWorkOrganId());

		putToRequest("organ", getCurrentUser().getOrgan());

		// 获取保密办人员列表
		if (organ == null) {
			// 返回当前登录单位
			organ = getCurrentUser().getOrgan();
		}else {
			// 得到指定的单位
			organ = secrecyWorkOrganMemberUnitModuleService.get(getRequest().getParameter("organ.organId").toString(), Organ.class);
		}

		// 得到保密办成员列表
		putToRequest("secrecyWorkOrganMemberUnitList", secrecyWorkOrganMemberUnitModuleService.getPageList(secrecyWorkOrgan,organ, PERSON_CHANGE_NOW));

		// 得到保密机构成员列表
		List<SecrecyWorkOrganRelationMember> secrecyWorkOrganRelationMemberList = secrecyWorkOrganRelationMemberModuleService.getPageList(secrecyWorkOrgan,organ, PERSON_CHANGE_NOW);
		putToRequest("secrecyWorkOrganRelationMemberList", secrecyWorkOrganRelationMemberList);
		secrecyWorkOrgan.setEstablishmentNum(secrecyWorkOrganRelationMemberList==null ? 0 : secrecyWorkOrganRelationMemberList.size());

		// 得到保密工作机附件
		putToRequest("attachments", attachmentService.getAttachmentsByHostId(secrecyWorkOrgan.getSecrecyWorkOrganId()));
		return "edit";
	}

	/**
	 *
	 * <p>
	 * 方法名：historyList 获取保密工作机构成员的变动人员信息列表
	 * </p>
	 * <p>
	 * 宋亚非 2013-7-15 下午3:10:19
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@return
	 */
	public String historyList(){
		// 检查
		if(secrecyWorkOrgan==null || secrecyWorkOrgan.getSecrecyWorkOrganId()==null || secrecyWorkOrgan.getSecrecyWorkOrganId().equals("")){
			this.addActionMessage("您所访问的资源Id不存在，请确认后重试。");
			return "success";
		}
		secrecyWorkOrgan = secrecyWorkOrganModuleService.get(secrecyWorkOrgan.getSecrecyWorkOrganId());
		putToRequest("organ", getCurrentUser().getOrgan());

		// 获取保密办人员列表
		if (organ == null) {
			// 返回当前登录单位
			organ = getCurrentUser().getOrgan();
		}else {
			// 得到指定的单位
			organ = secrecyWorkOrganMemberUnitModuleService.get(getRequest().getParameter("organ.organId").toString(), Organ.class);
		}

		PageSortModel<PersonGroupRelationChange> psm = new PageSortModel<PersonGroupRelationChange>(getRequest(),"relationMemberHistoryList");

		putToRequest("secrecyWorkOrganRelationMemberHistoryList", secrecyWorkOrganRelationMemberChangeService.getHistoryPageList(psm,secrecyWorkOrgan,organ,PERSON_CHANGE_HISTORY));
		return "success";
	}

	/**
	 *
	 * <p>
	 * 方法名：changedList 人员变更历史详细列表
	 * </p>
	 * <p>
	 * 宋亚非 2013-7-15 下午5:21:58
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@return
	 */
	public String changedList(){
		PageSortModel<PersonGroupRelationChange> psm = new PageSortModel<PersonGroupRelationChange>(getRequest(), "personGroupRelationChangeTable");
		List<PersonGroupRelationChange> pgrcList = this.secrecyWorkOrganRelationMemberChangeService.getPersonGroupRelationChangeList(psm, this.secrecyWorkOrganRelationMember.getSecrecyWorkOrganRelationId());
		this.putToRequest("personGroupRelationChangeList", pgrcList);
		return "success";
	}

	/**
	 *
	 * <p>
	 * 方法名：unitHistoryList 获得办公室成员 人员变更信息列表
	 * </p>
	 * <p>
	 * 宋亚非 2013-7-20 下午3:23:56
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@return
	 */
	public String unitHistoryList(){
		// 检查
		if(secrecyWorkOrgan==null || secrecyWorkOrgan.getSecrecyWorkOrganId()==null || secrecyWorkOrgan.getSecrecyWorkOrganId().equals("")){
			this.addActionMessage("您所访问的资源Id不存在，请确认后重试。");
			return "success";
		}
		secrecyWorkOrgan = secrecyWorkOrganModuleService.get(secrecyWorkOrgan.getSecrecyWorkOrganId());
		putToRequest("organ", getCurrentUser().getOrgan());

		// 获取保密办人员列表
		if (organ == null) {
			// 返回当前登录单位
			organ = getCurrentUser().getOrgan();
		}else {
			// 得到指定的单位
			organ = secrecyWorkOrganMemberUnitModuleService.get(getRequest().getParameter("organ.organId").toString(), Organ.class);
		}

		PageSortModel<SecrecyWorkOrganMembersUnitChange> psm = new PageSortModel<SecrecyWorkOrganMembersUnitChange>(getRequest(),"memberUnitHistoryList");

		putToRequest("secrecyWorkOrganMemberUnitHistoryList", this.secrecyWorkOrganMembersUnitChangeService.getHistoryPageList(psm,secrecyWorkOrgan,organ,PERSON_CHANGE_HISTORY));
		return "success";
	}

	/**
	 *
	 * <p>
	 * 方法名：unitChangedList 或得某人员变更明细列表
	 * </p>
	 * <p>
	 * 宋亚非 2013-7-20 下午3:23:59
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@return
	 */
	public String unitChangedList(){
		PageSortModel<SecrecyWorkOrganMembersUnitChange> psm = new PageSortModel<SecrecyWorkOrganMembersUnitChange>(getRequest(), "unitMemberChangeTable");
		List<SecrecyWorkOrganMembersUnitChange> swomucList = this.secrecyWorkOrganMembersUnitChangeService.getMemberUnitChangeList(psm, this.secrecyWorkOrganRelationMember.getSecrecyWorkOrganRelationId());
		this.putToRequest("personGroupRelationChangeList", swomucList);
		return "success";
	}

	/**
	 * <p>
	 * 编辑操作
	 * </p>
	 * <p>
	 * 刘椿成 2012-12-14 16:46:11
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * <p>
	 * </p>
	 * @author FastCodeingTools
	 * @author 刘椿成
	 * @since 1.0
	 * @version 1.0
	 */
	public String editing() {
	        // 记录操作日志开始
	        SecrecyWorkOrgan secrecyWorkOrganDb = secrecyWorkOrganModuleService.get(secrecyWorkOrgan.getSecrecyWorkOrganId());
	        SecrecyWorkOrgan beforeSecrecyWorkOrgan = new SecrecyWorkOrgan();
	        BeanUtils.copyProperties(beforeSecrecyWorkOrgan, secrecyWorkOrganDb, CopyRuleEnum.ignoreCaseNull);
	        Department d = new Department();
	        d.setDepartmentName(secrecyWorkOrganDb.getDepartment().getDepartmentName());
	        beforeSecrecyWorkOrgan.setDepartment(d);
	        // 记录操作日志结束

	        BeanUtils.copyProperties(secrecyWorkOrganDb, secrecyWorkOrgan, CopyRuleEnum.ignoreCaseNull);
	        secrecyWorkOrganDb = secrecyWorkOrganModuleService.saveSystemDataBySecrecyWorkOrgan(secrecyWorkOrganDb, getCurrentUser(),deptFlag );
		secrecyWorkOrganDb.setModifyPerson(getCurrentUser());
		secrecyWorkOrganDb.setModifyTime(new Date());
		//修改人：宋亚非   修改原因：数据库统一至一个库后，取消上报功能，不再存在上报后修改状态   修改时间：2013-06-03
		/*if(secrecyWorkOrgan.getReportState() > 0) {
			secrecyWorkOrgan.setReportState(ReportState.REPORT_YES_MODIFY);
		}*/
		secrecyWorkOrganModuleService.update(secrecyWorkOrganDb);
		attachmentService.updateHostId(secrecyWorkOrganDb.getSecrecyWorkOrganId(), secrecyWorkOrganAttachs);

		BusinessLog log = new BusinessLog();
                log.setBusinessName("保密工作机构");
                log.setPrimaryKey(secrecyWorkOrganDb.getSecrecyWorkOrganId());
                BusinessLogContext.getInstance().getBusinessLogService().saveEditBusinessLogByModule(getCurrentUser(), log, secrecyWorkOrganDb, beforeSecrecyWorkOrgan);

		// 设置信息
		this.addActionMessage(getMessageConstant().getUpdateSuccess());
		return redirectActionResult(DETAIL);
	}

	/**
	 *
	 * <p>
	 * 上报
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-3-19 - 下午5:16:28
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @return
	 */
	public String report(){
		try{
			if(secrecyWorkOrganIds != null && !"".equals(secrecyWorkOrganIds)) {
				// 查询需要上报的数据 domainList
				List<SecrecyWorkOrgan> domainList = new ArrayList<SecrecyWorkOrgan>();
				String [] secrecyWorkOrganIdArray =  secrecyWorkOrganIds.split(",");
				for (String sid : secrecyWorkOrganIdArray) {
					SecrecyWorkOrgan swo = secrecyWorkOrganModuleService.get(sid);
					swo.setAttachmentList(attachmentService.getAttachmentsByHostId(swo.getSecrecyWorkOrganId()));
					swo.setReportState(ReportState.REPORT_YES);
					secrecyWorkOrganModuleService.update(swo);
					domainList.add(swo);
				}
				// 查询上报单位
				List<String> organIdList = serverReportMappingService.getReportToOragans(getCurrentUser().getOrgan());
				Iterator<String> i = organIdList.iterator();
				while (i.hasNext()) {
					String organId = i.next();
					//组织需要上报内容
					SecrecyWorkOrganDto secrecyWorkOrganDto = new SecrecyWorkOrganDto();
					List<String> querySqls = new ArrayList<String>();
					for (String secrecyWorkOrganId : secrecyWorkOrganIdArray) {
						querySqls.add("select * from bm_person_group where PERSON_GROUP_ID = '" + secrecyWorkOrganId + "'");
						//因为接收单位并没有同步，所以需要上报。
						querySqls.add("select * from sys_organization where organ_id = '" + getCurrentUser().getOrgan().getOrganId() + "'");
						//单位下所有子部门，因为接收单位并没有同步，所以需要上报。
						querySqls.add("select * from sys_department where organ_id = '" + getCurrentUser().getOrgan().getOrganId() + "'");
						//单位下所有人员，因为要害部门负责人需要，所以需要上报。
						querySqls.add("select * from sys_user_info where organ_id = '" + getCurrentUser().getOrgan().getOrganId() + "'");
						//保密工作机构成员列表
						querySqls.add("select * from bm_person_group_relation where PERSON_GROUP_RELATION_ID = '" + secrecyWorkOrganId + "'");
						//保密办成员列表
						querySqls.add("select * from bm_secrecy_work_organ_members_unit where PERSON_GROUP_ID = '" + secrecyWorkOrganId + "'");
						//保密组织机构附件需要，所以需要上报。
						querySqls.add("select * from base_attachment where host_id = '" + secrecyWorkOrganId + "'");
						XmlExportor exportor = new XmlExportor();
						exportor.setDataSource(dataSource);
						StringWriter writer = new StringWriter();
						exportor.exportData(writer , querySqls.toArray(new String[]{}));
						secrecyWorkOrganDto.setReportData(writer.toString());
						secrecyWorkOrganDto.setReceiveOrganId(organId);
						secrecyWorkOrganDto.setSecrecyWorkOrganId(secrecyWorkOrganId);
						// 发送数据
						secrecyWorkOrganExchanger.exchange(secrecyWorkOrganDto, getCurrentUser().getOrgan()
								, OrganizationContext.getInstance().getOrganService().get(organId));
					}
				}
			}
			addActionMessage("上报成功！");
		}catch (Exception e) {
			e.printStackTrace();
			addActionMessage("上报失败！" + e.getMessage());
		}
		return redirectActionResult(DETAIL);
	}

	//TODO 迁移使用，V4.1.1升级成功后去掉该方法
	public String query() throws SQLException{

//		DataSource data = ContextUtils.getDefaultContext().getBean("dataSource");
//		DatabaseMetadata meta;
//		try {
//			meta = DatabaseMetadataManager.getDefaultManager().create(data.getConnection());
//			for (TableMetadata tmd : meta.getTables()) {
//				for (ColumnMetadata cmd : tmd.getColumns()) {
//					if (cmd.getName().equalsIgnoreCase("department")) {
//						System.out.println("table\t" + tmd.getName() + "-------------column:\t" +  cmd.getName());
//					}
//				}
//			}
//		} catch (SQLException e) {
//		}


//		// 查处所有保密组织机构"保密办设在","保密办负责人"
//		List<SecrecyWorkOrgan> list = new ArrayList<SecrecyWorkOrgan>();
//		DataSource data = ContextUtils.getDefaultContext().getBean("dataSource");
//		Connection con = data.getConnection();
//		PreparedStatement pstat = con.prepareStatement("select * from bm_person_group");
//		ResultSet rs = pstat.executeQuery();
//		while (rs.next()) {
//			SecrecyWorkOrgan swo = new SecrecyWorkOrgan();
//			swo.setSecrecyWorkOrganId(rs.getString("PERSON_GROUP_ID"));
//			swo.setCode(rs.getString("CODE"));
//			swo.setName(rs.getString("NAME"));
//			swo.setSetupDate(rs.getDate("SETUP_DATE"));
//			swo.setAdjustDate(rs.getDate("ADJUST_DATE"));
//			swo.setOrganDesc(rs.getString("ORGAN_DESC"));
//
//			Organ organ = new Organ();
//			organ.setOrganId(rs.getString("ORGAN_ID"));
//			swo.setOrgan(organ);
//
//			Department dept = new Department();
//			dept.setDepartmentId(rs.getString("DEPARTMENT_ID"));
//			swo.setDepartment(dept);
//
//			swo.setCreateTime(rs.getDate("CREATE_TIME"));
//
//			User createPerson = new User();
//			createPerson.setUserId(rs.getString("CREATE_PERSON"));
//			swo.setCreatePerson(createPerson);
//
//			User modifyPerson = new User();
//			modifyPerson.setUserId(rs.getString("MODIFY_PERSON"));
//			swo.setCreatePerson(modifyPerson);
//
//			swo.setModifyTime(rs.getDate("MODIFY_TIME"));
//			swo.setSecrecyOrgan(rs.getString("SECRECY_ORGAN"));
//
//			UserInfo organPrincipal = new UserInfo();
//			organPrincipal.setName(rs.getString("ORGAN_PRINCIPAL"));
//			swo.setOrganPrincipal(organPrincipal);
//
//			swo.setPrincipalPhone(rs.getString("PRINCIPAL_PHONE"));
//			swo.setPrincipalDuty(rs.getString("PRINCIPAL_DUTY"));
//			swo.setTelephone(rs.getString("TELEPHONE"));
//			swo.setGroupType(rs.getInt("GROUP_TYPE"));
//
//			swo.setAddress(rs.getString("ADDRESS"));
//			swo.setFax(rs.getString("FAX"));
//			swo.setZipCode(rs.getString("ZIPCODE"));
//			swo.setDocNo(rs.getString("DOC_NO"));
//			list.add(swo);
//		}
//		for (SecrecyWorkOrgan s : list) {
//			// 重新保存
//			SecrecyWorkOrgan swo = secrecyWorkOrganModuleService.saveSystemDataBySecrecyWorkOrgan(s, getCurrentUser(), null);
//			secrecyWorkOrganModuleService.update(swo);
//
//		}


		return SUCCESS;
	}

	/*********************************统计部分*******************************************/
	//按机构类别统计
	//本单位查询
	public String statisticsByOrganTypeCurrent(){
		//查询表头
		List<DictionaryOption> organCategoryDictionary = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "organ_category");
		//查询数据
		String organId = getRequest().getParameter("organId");
		if( !(organId!=null&& !"".equals(organId))){
			organId = getCurrentUser().getOrgan().getOrganId();
		}
		Map<String, Map<Integer, Integer>> organTypeResult = this.secrecyWorkOrganModuleService.statisticsByOrganType(organCategoryDictionary, null, organId);
		this.putToRequest("organCategoryDictionary", organCategoryDictionary);
		this.putToRequest("organTypeResult", organTypeResult);
		this.putToRequest("expBtnFlag", expBtnFlag);
		return "success";
	}
	public String statisticsByOrganType(){
		//查询表头
		List<DictionaryOption> organCategoryDictionary = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "organ_category");
		//查询数据
		String districtCode = getRequest().getParameter("districtCode");
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		}else{
			district = this.secrecyWorkOrganModuleService.get(districtCode, District.class);
		}
		String organId = getRequest().getParameter("organId");
		Map<String, Map<Integer, Integer>> organTypeResult = this.secrecyWorkOrganModuleService.statisticsByOrganType(organCategoryDictionary, district, organId);
		this.putToRequest("organCategoryDictionary", organCategoryDictionary);
		this.putToRequest("organTypeResult", organTypeResult);
		this.putToRequest("expBtnFlag", expBtnFlag);
		return "success";
	}
	public String statisticsByAreaOfOrganType(){
		//查询表头
		List<DictionaryOption> organCategoryDictionary = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "organ_category");
		//查询数据
		String districtCode = getRequest().getParameter("districtCode");
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		}else{
			district = this.secrecyWorkOrganModuleService.get(districtCode, District.class);
		}
		String organId = getRequest().getParameter("organId");
		Map<String, Map<Integer, Integer>> organTypeResult = this.secrecyWorkOrganModuleService.statisticsByAreaOfOrganType(organCategoryDictionary, district, organId);
		this.putToRequest("organCategoryDictionary", organCategoryDictionary);
		this.putToRequest("organTypeResult", organTypeResult);
		return "success";
	}
	public String statisticsByLayerOfOrganType(){
		//查询表头
		List<DictionaryOption> organCategoryDictionary = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "organ_category");
		//查询数据
		String districtCode = getRequest().getParameter("districtCode");
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		}else{
			district = this.secrecyWorkOrganModuleService.get(districtCode, District.class);
		}
		String organId = getRequest().getParameter("organId");
		Map<String, Map<Integer, Integer>> organTypeResult = this.secrecyWorkOrganModuleService.statisticsByLayerOfOrganType(organCategoryDictionary, district, organId);
		this.putToRequest("organCategoryDictionary", organCategoryDictionary);
		this.putToRequest("organTypeResult", organTypeResult);
		return "success";
	}

	//统计子行政区划
	public String statisticsByOrganTypeOfDistrict(){
		//查询表头
		List<DictionaryOption> organCategoryDictionary = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "organ_category");
		//查询数据
		String districtCode = getRequest().getParameter("districtCode");
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		}else{
			district = this.secrecyWorkOrganModuleService.get(districtCode, District.class);
		}
		String organId = getRequest().getParameter("organId");
		Map<String, Map<Integer, Integer>> organTypeResult = this.secrecyWorkOrganModuleService.statisticsByOrganTypeOfDistrict(organCategoryDictionary, district, organId);
		this.putToRequest("organCategoryDictionary", organCategoryDictionary);
		this.putToRequest("organTypeResult", organTypeResult);
		return "success";
	}

	//统计子单位
	public String statisticsByOrganTypeOfOrgan(){
		//查询表头
		List<DictionaryOption> organCategoryDictionary = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "organ_category");
		//查询数据
		String districtCode = getRequest().getParameter("districtCode");
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		}else{
			district = this.secrecyWorkOrganModuleService.get(districtCode, District.class);
		}
		String organId = getRequest().getParameter("organId");
		Map<String, Map<Integer, Integer>> organTypeResult = this.secrecyWorkOrganModuleService.statisticsByOrganTypeOfOrgan(organCategoryDictionary, district, organId);
		this.putToRequest("organCategoryDictionary", organCategoryDictionary);
		this.putToRequest("organTypeResult", organTypeResult);
		return "success";
	}

///////////////////////////////////////////////////////////////
	//按行政级别统计   本行政区划
	//本单位查询
	public String statisticsByAdministrativeLevelCurrent(){
		//查询表头
		List<DictionaryOption> organAdminLevelDictionary = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "organ_admin_level");
		//查询数据
		String organId = getRequest().getParameter("organId");
		if( !(organId!=null && !"".equals(organId)) ){
			organId = getCurrentUser().getOrgan().getOrganId();
		}
		Map<String, Map<Integer, Integer>> organTypeResult = this.secrecyWorkOrganModuleService.statisticsByAdministrativeLevel(organAdminLevelDictionary, null, organId);
		this.putToRequest("organAdminLevelDictionary", organAdminLevelDictionary);
		this.putToRequest("organAdminResult", organTypeResult);
		return "success";
	}
	public String statisticsByAdministrativeLevel(){
		//查询表头
		List<DictionaryOption> organAdminLevelDictionary = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "organ_admin_level");
		//查询数据
		String districtCode = getRequest().getParameter("districtCode");
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		}else{
			district = this.secrecyWorkOrganModuleService.get(districtCode, District.class);
		}
		String organId = getRequest().getParameter("organId");
		Map<String, Map<Integer, Integer>> organTypeResult = this.secrecyWorkOrganModuleService.statisticsByAdministrativeLevel(organAdminLevelDictionary, district, organId);
		this.putToRequest("organAdminLevelDictionary", organAdminLevelDictionary);
		this.putToRequest("organAdminResult", organTypeResult);
		return "success";
	}
	//按行政级别    统计当前行政区划下 直属单位 总数
	public String statisticsByAreaOfAdministrativeLevel(){
		//查询表头
		List<DictionaryOption> organAdminLevelDictionary = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "organ_admin_level");
		//查询数据
		String districtCode = getRequest().getParameter("districtCode");
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		}else{
			district = this.secrecyWorkOrganModuleService.get(districtCode, District.class);
		}
		String organId = getRequest().getParameter("organId");
		Map<String, Map<Integer, Integer>> organTypeResult = this.secrecyWorkOrganModuleService.statisticsByAreaOfAdministrativeLevel(organAdminLevelDictionary, district, organId);
		this.putToRequest("organAdminLevelDictionary", organAdminLevelDictionary);
		this.putToRequest("organAdminResult", organTypeResult);
		return "success";
	}
	//   按行政区划统计总数
	public String statisticsByLayerOfAdministrativeLevel(){
		//查询表头
		List<DictionaryOption> organAdminLevelDictionary = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "organ_admin_level");
		//查询数据
		String districtCode = getRequest().getParameter("districtCode");
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		}else{
			district = this.secrecyWorkOrganModuleService.get(districtCode, District.class);
		}
		String organId = getRequest().getParameter("organId");
		Map<String, Map<Integer, Integer>> organTypeResult = this.secrecyWorkOrganModuleService.statisticsByLayerOfAdministrativeLevel(organAdminLevelDictionary, district, organId);
		this.putToRequest("organAdminLevelDictionary", organAdminLevelDictionary);
		this.putToRequest("organAdminResult", organTypeResult);
		return "success";
	}

	//按行政级别统计当前行政区划下的行政区划的数据
	public String statisticsByAdministrativeLevelOfDistrict(){
		//查询表头
		List<DictionaryOption> organAdminLevelDictionary = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "organ_admin_level");
		//查询数据
		String districtCode = getRequest().getParameter("districtCode");
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		}else{
			district = this.secrecyWorkOrganModuleService.get(districtCode, District.class);
		}
		String organId = getRequest().getParameter("organId");
		Map<String, Map<Integer, Integer>> organTypeResult = this.secrecyWorkOrganModuleService.statisticsByAdministrativeLevelOfDistrict(organAdminLevelDictionary, district, organId);
		this.putToRequest("organAdminLevelDictionary", organAdminLevelDictionary);
		this.putToRequest("organAdminResult", organTypeResult);
		return "success";
	}

	//按行政级别统计当前行政区下的单位的数据
	public String statisticsByAdministrativeLevelOfOrgan(){
		//查询表头
		List<DictionaryOption> organAdminLevelDictionary = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "organ_admin_level");
		//查询数据
		String districtCode = getRequest().getParameter("districtCode");
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		}else{
			district = this.secrecyWorkOrganModuleService.get(districtCode, District.class);
		}
		String organId = getRequest().getParameter("organId");
		Map<String, Map<Integer, Integer>> organTypeResult = this.secrecyWorkOrganModuleService.statisticsByAdministrativeLevelOfOrgan(organAdminLevelDictionary, district, organId);
		this.putToRequest("organAdminLevelDictionary", organAdminLevelDictionary);
		this.putToRequest("organAdminResult", organTypeResult);
		return "success";
	}


///////////////////////////////////////////////////////////////////////////////////////////
	//按编制人数统计
	//本单位
	public String statisticsByEstablishmentNumCurrent(){
		List<Map<String,Integer>> numRange = new ArrayList<Map<String,Integer>>();
		Map<String ,Integer> numRange1 = new HashMap<String, Integer>();
		numRange1.put("start", 0);
		numRange1.put("end", 35);
		numRange.add(numRange1);

		Map<String ,Integer> numRange2 = new HashMap<String, Integer>();
		numRange2.put("start", 35);
		numRange2.put("end", 60);
		numRange.add(numRange2);

		Map<String ,Integer> ageRange3 = new HashMap<String, Integer>();
		ageRange3.put("start", 60);
		ageRange3.put("end", null);
		numRange.add(ageRange3);

		String organId = getRequest().getParameter("organId");
		if( !(organId!=null && !"".equals(organId)) ){
			organId = getCurrentUser().getOrgan().getOrganId();
		}
		Map<String, Integer> organNumMap = this.secrecyWorkOrganModuleService.statisticsByEstablishmentNum(numRange, null, organId, SECRECY_STATUS_NOW);
		this.putToRequest("organNumMap", organNumMap);
		this.putToRequest("headMap", numRange);
		return "success";
	}

	public String statisticsByEstablishmentNum(){
		List<Map<String,Integer>> numRange = new ArrayList<Map<String,Integer>>();
		Map<String ,Integer> numRange1 = new HashMap<String, Integer>();
		numRange1.put("start", 0);
		numRange1.put("end", 35);
		numRange.add(numRange1);

		Map<String ,Integer> numRange2 = new HashMap<String, Integer>();
		numRange2.put("start", 35);
		numRange2.put("end", 60);
		numRange.add(numRange2);

		Map<String ,Integer> ageRange3 = new HashMap<String, Integer>();
		ageRange3.put("start", 60);
		ageRange3.put("end", null);
		numRange.add(ageRange3);

		String districtCode = getRequest().getParameter("districtCode");
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		}else{
			district = this.secrecyWorkOrganModuleService.get(districtCode, District.class);
		}
		String organId = getRequest().getParameter("organId");

		Map<String, Integer> organNumMap = this.secrecyWorkOrganModuleService.statisticsByEstablishmentNum(numRange, district, organId, SECRECY_STATUS_NOW);
		this.putToRequest("organNumMap", organNumMap);
		this.putToRequest("headMap", numRange);
		return "success";
	}

	//直辖单位统计
	public String statisticsByAreaOfEstablishmentNum(){
		List<Map<String,Integer>> numRange = new ArrayList<Map<String,Integer>>();
		Map<String ,Integer> numRange1 = new HashMap<String, Integer>();
		numRange1.put("start", 0);
		numRange1.put("end", 35);
		numRange.add(numRange1);

		Map<String ,Integer> numRange2 = new HashMap<String, Integer>();
		numRange2.put("start", 35);
		numRange2.put("end", 60);
		numRange.add(numRange2);

		Map<String ,Integer> ageRange3 = new HashMap<String, Integer>();
		ageRange3.put("start", 60);
		ageRange3.put("end", null);
		numRange.add(ageRange3);

		String districtCode = getRequest().getParameter("districtCode");
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		}else{
			district = this.secrecyWorkOrganModuleService.get(districtCode, District.class);
		}
		String organId = getRequest().getParameter("organId");

		Map<String, Integer> organNumMap = this.secrecyWorkOrganModuleService.statisticsByAreaOfEstablishmentNum(numRange, district, organId, SECRECY_STATUS_NOW);
		Map<Integer, Integer> resultMap = new HashMap<Integer, Integer>();
		for( int i=0;i<numRange.size();i++){
			resultMap.put(i, 0);
		}
		for(String onKey : organNumMap.keySet() ){
			int tmpInt = 0;
			for(Map<String,Integer> range : numRange){
				if( (range.get("start")==0 || range.get("start")==null )&& range.get("end")!=null ){
					if( organNumMap.get(onKey)< range.get("end")){
						resultMap.put(tmpInt, resultMap.get(tmpInt)+1);
					}
				}else if( (range.get("start")!=null )&& range.get("end")!=null){
					if(organNumMap.get(onKey) > range.get("start") && organNumMap.get(onKey)< range.get("end") ){
						resultMap.put(tmpInt, resultMap.get(tmpInt)+1);
					}
				}else if( (range.get("start")!=null )&& range.get("end")==null  ){
					if(organNumMap.get(onKey) > range.get("start") ){
						resultMap.put(tmpInt, resultMap.get(tmpInt)+1);
					}
				}
				tmpInt++;
			}
		}
		Map<String,Map<Integer,Integer>> resultMaps = new HashMap<String, Map<Integer,Integer>>();
		resultMaps.put(district.getDistrictName(), resultMap);

		this.putToRequest("resultMaps", resultMaps);
		//this.putToRequest("organNumMap", organNumMap);
		this.putToRequest("headMap", numRange);
		return "success";
	}
	public String statisticsByLayerOfEstablishmentNum(){
		List<Map<String,Integer>> numRange = new ArrayList<Map<String,Integer>>();
		Map<String ,Integer> numRange1 = new HashMap<String, Integer>();
		numRange1.put("start", 0);
		numRange1.put("end", 35);
		numRange.add(numRange1);

		Map<String ,Integer> numRange2 = new HashMap<String, Integer>();
		numRange2.put("start", 35);
		numRange2.put("end", 60);
		numRange.add(numRange2);

		Map<String ,Integer> ageRange3 = new HashMap<String, Integer>();
		ageRange3.put("start", 60);
		ageRange3.put("end", null);
		numRange.add(ageRange3);

		String districtCode = getRequest().getParameter("districtCode");
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		}else{
			district = this.secrecyWorkOrganModuleService.get(districtCode, District.class);
		}
		String organId = getRequest().getParameter("organId");

		Map<String, Integer> organNumMap = this.secrecyWorkOrganModuleService.statisticsByLayerOfEstablishmentNum(numRange, district, organId, SECRECY_STATUS_NOW);
		Map<Integer, Integer> resultMap = new HashMap<Integer, Integer>();
		for( int i=0;i<numRange.size();i++){
			resultMap.put(i, 0);
		}
		for(String onKey : organNumMap.keySet() ){
			int tmpInt = 0;
			for(Map<String,Integer> range : numRange){
				if( (range.get("start")==0 || range.get("start")==null )&& range.get("end")!=null ){
					if( organNumMap.get(onKey)< range.get("end")){
						resultMap.put(tmpInt, resultMap.get(tmpInt)+1);
					}
				}else if( (range.get("start")!=null )&& range.get("end")!=null){
					if(organNumMap.get(onKey) > range.get("start") && organNumMap.get(onKey)< range.get("end") ){
						resultMap.put(tmpInt, resultMap.get(tmpInt)+1);
					}
				}else if( (range.get("start")!=null )&& range.get("end")==null  ){
					if(organNumMap.get(onKey) > range.get("start") ){
						resultMap.put(tmpInt, resultMap.get(tmpInt)+1);
					}
				}
				tmpInt++;
			}
		}
		Map<String,Map<Integer,Integer>> resultMaps = new HashMap<String, Map<Integer,Integer>>();
		resultMaps.put(district.getDistrictName(), resultMap);

		this.putToRequest("resultMaps", resultMaps);
		//this.putToRequest("organNumMap", organNumMap);
		this.putToRequest("headMap", numRange);
		return "success";
	}

	//统计子行政区划
	public String statisticsByEstablishmentNumOfDistrict(){
		List<Map<String,Integer>> numRange = new ArrayList<Map<String,Integer>>();
		Map<String ,Integer> numRange1 = new HashMap<String, Integer>();
		numRange1.put("start", 0);
		numRange1.put("end", 35);
		numRange.add(numRange1);

		Map<String ,Integer> numRange2 = new HashMap<String, Integer>();
		numRange2.put("start", 35);
		numRange2.put("end", 60);
		numRange.add(numRange2);

		Map<String ,Integer> ageRange3 = new HashMap<String, Integer>();
		ageRange3.put("start", 60);
		ageRange3.put("end", null);
		numRange.add(ageRange3);

		String districtCode = getRequest().getParameter("districtCode");
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		}else{
			district = this.secrecyWorkOrganModuleService.get(districtCode, District.class);
		}
		String organId = getRequest().getParameter("organId");

		Map<String,Map<Integer,Integer>> resultMaps = new HashMap<String, Map<Integer,Integer>>();
		Map<String, Map<String, Integer>> organNumMap = this.secrecyWorkOrganModuleService.statisticsByEstablishmentNumOfDistrict(numRange, district, organId, SECRECY_STATUS_NOW);
		for(String onKey : organNumMap.keySet() ){
			Map<Integer, Integer> resultMap = new HashMap<Integer, Integer>();
			for( int i=0;i<numRange.size();i++){
				resultMap.put(i, 0);
			}
			Map<String, Integer> tmpMap = organNumMap.get(onKey);
			for( String toKey : tmpMap.keySet() ){
				int tmpInt = 0;
				for(Map<String,Integer> range : numRange){
					if( (range.get("start")==0 || range.get("start")==null )&& range.get("end")!=null ){
						if( tmpMap.get(toKey)< range.get("end")){
							resultMap.put(tmpInt, resultMap.get(tmpInt)+1);
						}
					}else if( (range.get("start")!=null )&& range.get("end")!=null){
						if(tmpMap.get(toKey) > range.get("start") && tmpMap.get(toKey)< range.get("end") ){
							resultMap.put(tmpInt, resultMap.get(tmpInt)+1);
						}
					}else if( (range.get("start")!=null )&& range.get("end")==null  ){
						if(tmpMap.get(toKey) > range.get("start") ){
							resultMap.put(tmpInt, resultMap.get(tmpInt)+1);
						}
					}
					tmpInt++;
				}
			}
			resultMaps.put(onKey, resultMap);
		}
		this.putToRequest("resultMaps", resultMaps);
		//this.putToRequest("organNumMap", organNumMap);
		this.putToRequest("headMap", numRange);
		return "success";
	}

	//统计子单位
	public String statisticsByEstablishmentNumOfOrgan(){
		List<Map<String,Integer>> numRange = new ArrayList<Map<String,Integer>>();
		Map<String ,Integer> numRange1 = new HashMap<String, Integer>();
		numRange1.put("start", 0);
		numRange1.put("end", 35);
		numRange.add(numRange1);

		Map<String ,Integer> numRange2 = new HashMap<String, Integer>();
		numRange2.put("start", 35);
		numRange2.put("end", 60);
		numRange.add(numRange2);

		Map<String ,Integer> ageRange3 = new HashMap<String, Integer>();
		ageRange3.put("start", 60);
		ageRange3.put("end", null);
		numRange.add(ageRange3);

		String districtCode = getRequest().getParameter("districtCode");
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		}else{
			district = this.secrecyWorkOrganModuleService.get(districtCode, District.class);
		}
		String organId = getRequest().getParameter("organId");

		Map<String,Map<Integer,Integer>> resultMaps = new HashMap<String, Map<Integer,Integer>>();
		Map<String, Integer> organNumMap = this.secrecyWorkOrganModuleService.statisticsByEstablishmentNumOfOrgan(numRange, district, organId, SECRECY_STATUS_NOW);
		for(String onKey : organNumMap.keySet() ){
			Map<Integer, Integer> resultMap = new HashMap<Integer, Integer>();
			for( int i=0;i<numRange.size();i++){
				resultMap.put(i, 0);
			}
			int tmpInt = 0;
			for(Map<String,Integer> range : numRange){
				if( (range.get("start")==0 || range.get("start")==null )&& range.get("end")!=null ){
					if( organNumMap.get(onKey)< range.get("end")){
						resultMap.put(tmpInt, resultMap.get(tmpInt)+1);
					}
				}else if( (range.get("start")!=null )&& range.get("end")!=null){
					if(organNumMap.get(onKey) > range.get("start") && organNumMap.get(onKey)< range.get("end") ){
						resultMap.put(tmpInt, resultMap.get(tmpInt)+1);
					}
				}else if( (range.get("start")!=null )&& range.get("end")==null  ){
					if(organNumMap.get(onKey) > range.get("start") ){
						resultMap.put(tmpInt, resultMap.get(tmpInt)+1);
					}
				}
				tmpInt++;
			}
			resultMaps.put(onKey, resultMap);
		}

		this.putToRequest("resultMaps", resultMaps);
		//this.putToRequest("organNumMap", organNumMap);
		this.putToRequest("headMap", numRange);
		return "success";
	}

	/******************************************导出部分**************************************************/
	/**
	 *
	 * <p>
	 * 方法名：collationOfData 导出 公共处理数据程序
	 * </p>
	 * <p>
	 * 宋亚非 2013-7-27 上午10:00:11
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@param resultdata
	 *@param options
	 *@return
	 */
	public List<LinkedList<String>>  collationOfData( Map<String, Map<Integer, Integer>> resultdata, List<DictionaryOption> options,Integer ...totalType ){
		List<LinkedList<String>> middleResult = new ArrayList<LinkedList<String>>();
		LinkedList<String> header = new LinkedList<String>();
		header.add("名称");
		for( DictionaryOption d : options ){
			header.add(d.getOptionText());
		}
		header.add("合计");
		middleResult.add(header);
		for( String key : resultdata.keySet() ){
			LinkedList<String> aRow = new LinkedList<String>();
			aRow.add(key);
			//计算合计
			Integer rowTotal = 0;
			Map<Integer,Integer> resMap = resultdata.get(key);
			if( totalType!=null && totalType.length>0 ){
				for( Integer resultKey : resMap.keySet() ){
					rowTotal += resMap.get(resultKey);
				}
			}else{
				for( Integer resultKey : resMap.keySet() ){
					rowTotal = resMap.get(resultKey);
				}
			}
			for( DictionaryOption d : options ){
				//输出表格并计算比例
				int dividend = 0;
				for( Integer resultKey : resMap.keySet() ){
					if( d.getOptionValue() == resultKey ){
						dividend = resMap.get(resultKey);
					}
				}
				String divResult = "0.00%";
				if( rowTotal!=null && rowTotal!=0){
					divResult = (Math.round((dividend*10000.00)/rowTotal)/100.00) + "%";
				}
				aRow.add(dividend + " (" +divResult+")");
			}
			aRow.add(rowTotal+"");
			middleResult.add(aRow);
		}
		return middleResult;
	}

	/**
	 *
	 * <p>
	 * 方法名：collationOfDataDetail 导出 公共处理数据部分
	 * </p>
	 * <p>
	 * 宋亚非 2013-7-27 上午10:00:56
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@param resultdata
	 *@param options
	 *@return
	 */
	public List<LinkedList<String>>  collationOfDataDetail( Map<String, Map<Integer, Integer>> resultdata, List<DictionaryOption> options ){
		List<LinkedList<String>> middleResult = new ArrayList<LinkedList<String>>();
		LinkedList<String> header = new LinkedList<String>();
		header.add("名称");
		for( DictionaryOption d : options ){
			header.add(d.getOptionText());
		}
		middleResult.add(header);
		for( String key : resultdata.keySet() ){
			LinkedList<String> aRow = new LinkedList<String>();
			aRow.add(key);
			Map<Integer,Integer> resMap = resultdata.get(key);
			for( DictionaryOption d : options ){
				//输出表格
				int dividend = 0;
				for( Integer resultKey : resMap.keySet() ){
					if( d.getOptionValue() == resultKey ){
						dividend = resMap.get(resultKey);
					}
				}
				aRow.add(dividend==0?"":"是");
			}
			middleResult.add(aRow);
		}
		return middleResult;
	}

	/**
	 *
	 * <p>
	 * 方法名：exportOverview  导出全省概览
	 * </p>
	 * <p>
	 * 宋亚非 2013-7-27 上午9:19:47
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@return
	 */
	public String exportOverview(){
		//分段标题
		//table 表头1
		//table 表体 合计 百分比计算等等
		Map<String, Object> result = new HashMap<String, Object>();
		//查询表头
		List<DictionaryOption> organCategoryDictionary = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "organ_category");
		//查询数据
		String districtCode = getRequest().getParameter("districtCode");
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		}else{
			district = this.secrecyWorkOrganModuleService.get(districtCode, District.class);
		}
		String organId = getRequest().getParameter("organId");
		Map<String, Map<Integer, Integer>> organTypeResult = this.secrecyWorkOrganModuleService.statisticsByLayerOfOrganType(organCategoryDictionary, district, organId);
		result.put("organTypeResult", this.collationOfData(organTypeResult, organCategoryDictionary));

		//查询表头
		List<DictionaryOption> organAdminLevelDictionary = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "organ_admin_level");
		//查询数据
		Map<String, Map<Integer, Integer>> AdministrativeLevelResult = this.secrecyWorkOrganModuleService.statisticsByLayerOfAdministrativeLevel(organAdminLevelDictionary, district, organId);
		result.put("AdministrativeLevel", this.collationOfData(AdministrativeLevelResult, organAdminLevelDictionary));

		List<Map<String,Integer>> numRange = new ArrayList<Map<String,Integer>>();
		Map<String ,Integer> numRange1 = new HashMap<String, Integer>();
		numRange1.put("start", 0);
		numRange1.put("end", 35);
		numRange.add(numRange1);

		Map<String ,Integer> numRange2 = new HashMap<String, Integer>();
		numRange2.put("start", 35);
		numRange2.put("end", 60);
		numRange.add(numRange2);

		Map<String ,Integer> ageRange3 = new HashMap<String, Integer>();
		ageRange3.put("start", 60);
		ageRange3.put("end", null);
		numRange.add(ageRange3);

		Map<String, Integer> organNumMap = this.secrecyWorkOrganModuleService.statisticsByLayerOfEstablishmentNum(numRange, district, organId, SECRECY_STATUS_NOW);
		List<DictionaryOption> numRangeDic = new ArrayList<DictionaryOption>();
		int dicNum = 0;
		for( Map<String, Integer> aRange : numRange ){
			DictionaryOption dicOption = new DictionaryOption();
			Integer startNum = aRange.get("start");
			Integer endNum = aRange.get("end");
			if( startNum==0 || startNum==null ){
				dicOption.setOptionId(dicNum+"");
				dicOption.setOptionText("小于"+endNum+"人");
				dicOption.setOptionValue(dicNum);
			}
			if( startNum!=null && startNum>0 && endNum!=null && endNum >0 ){
				dicOption.setOptionId(dicNum+"");
				dicOption.setOptionText(startNum+"人~"+endNum+"人");
				dicOption.setOptionValue(dicNum);
			}
			if( startNum!=null && endNum==null ){
				dicOption.setOptionId(dicNum+"");
				dicOption.setOptionText("大于"+startNum+"人");
				dicOption.setOptionValue(dicNum);
			}
			numRangeDic.add(dicOption);
			dicNum++;
		}

		Map<Integer, Integer> resultMap = new HashMap<Integer, Integer>();
		for( int i=0;i<numRange.size();i++){
			resultMap.put(i, 0);
		}
		for(String onKey : organNumMap.keySet() ){
			int tmpInt = 0;
			for(Map<String,Integer> range : numRange){
				if( (range.get("start")==0 || range.get("start")==null )&& range.get("end")!=null ){
					if( organNumMap.get(onKey)< range.get("end")){
						resultMap.put(tmpInt, resultMap.get(tmpInt)+1);
					}
				}else if( (range.get("start")!=null )&& range.get("end")!=null){
					if(organNumMap.get(onKey) > range.get("start") && organNumMap.get(onKey)< range.get("end") ){
						resultMap.put(tmpInt, resultMap.get(tmpInt)+1);
					}
				}else if( (range.get("start")!=null )&& range.get("end")==null  ){
					if(organNumMap.get(onKey) > range.get("start") ){
						resultMap.put(tmpInt, resultMap.get(tmpInt)+1);
					}
				}
				tmpInt++;
			}
		}
		Map<String,Map<Integer,Integer>> resultMaps = new HashMap<String, Map<Integer,Integer>>();
		resultMaps.put(district.getDistrictName(), resultMap);
		if( resultMaps!=null ){
			result.put("organNumResult", this.collationOfData(resultMaps, numRangeDic,1));
		}
		setResultData(result);
		return SUCCESS;
	}

	/**
	 *
	 * <p>
	 * 方法名：exportOverviewOrgan 导出概览直辖机构统计信息
	 * </p>
	 * <p>
	 * 宋亚非 2013-7-27 上午9:24:31
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@return
	 */
	public String exportOverviewOrgan(){
		//分段标题
		//table 表头1
		//table 表体 合计 百分比计算等等
		Map<String, Object> result = new HashMap<String, Object>();
		//查询表头
		List<DictionaryOption> organCategoryDictionary = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "organ_category");
		//查询数据
		String districtCode = getRequest().getParameter("districtCode");
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		}else{
			district = this.secrecyWorkOrganModuleService.get(districtCode, District.class);
		}
		String organId = getRequest().getParameter("organId");
		Map<String, Map<Integer, Integer>> organTypeResult = this.secrecyWorkOrganModuleService.statisticsByAreaOfOrganType(organCategoryDictionary, district, organId);
		result.put("organTypeResult", this.collationOfData(organTypeResult, organCategoryDictionary));

		//查询表头
		List<DictionaryOption> organAdminLevelDictionary = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "organ_admin_level");
		//查询数据
		Map<String, Map<Integer, Integer>> AdministrativeLevelResult = this.secrecyWorkOrganModuleService.statisticsByAreaOfAdministrativeLevel(organAdminLevelDictionary, district, organId);
		result.put("AdministrativeLevel", this.collationOfData(AdministrativeLevelResult, organAdminLevelDictionary));

		List<Map<String,Integer>> numRange = new ArrayList<Map<String,Integer>>();
		Map<String ,Integer> numRange1 = new HashMap<String, Integer>();
		numRange1.put("start", 0);
		numRange1.put("end", 35);
		numRange.add(numRange1);

		Map<String ,Integer> numRange2 = new HashMap<String, Integer>();
		numRange2.put("start", 35);
		numRange2.put("end", 60);
		numRange.add(numRange2);

		Map<String ,Integer> ageRange3 = new HashMap<String, Integer>();
		ageRange3.put("start", 60);
		ageRange3.put("end", null);
		numRange.add(ageRange3);

		Map<String, Integer> organNumMap = this.secrecyWorkOrganModuleService.statisticsByAreaOfEstablishmentNum(numRange, district, organId, SECRECY_STATUS_NOW);
		List<DictionaryOption> numRangeDic = new ArrayList<DictionaryOption>();
		int dicNum = 0;
		for( Map<String, Integer> aRange : numRange ){
			DictionaryOption dicOption = new DictionaryOption();
			Integer startNum = aRange.get("start");
			Integer endNum = aRange.get("end");
			if( startNum==0 || startNum==null ){
				dicOption.setOptionId(dicNum+"");
				dicOption.setOptionText("小于"+endNum+"人");
				dicOption.setOptionValue(dicNum);
			}
			if( startNum!=null && startNum>0 && endNum!=null && endNum >0 ){
				dicOption.setOptionId(dicNum+"");
				dicOption.setOptionText(startNum+"人~"+endNum+"人");
				dicOption.setOptionValue(dicNum);
			}
			if( startNum!=null && endNum==null ){
				dicOption.setOptionId(dicNum+"");
				dicOption.setOptionText("大于"+startNum+"人");
				dicOption.setOptionValue(dicNum);
			}
			numRangeDic.add(dicOption);
			dicNum++;
		}

		Map<Integer, Integer> resultMap = new HashMap<Integer, Integer>();
		for( int i=0;i<numRange.size();i++){
			resultMap.put(i, 0);
		}
		for(String onKey : organNumMap.keySet() ){
			int tmpInt = 0;
			for(Map<String,Integer> range : numRange){
				if( (range.get("start")==0 || range.get("start")==null )&& range.get("end")!=null ){
					if( organNumMap.get(onKey)< range.get("end")){
						resultMap.put(tmpInt, resultMap.get(tmpInt)+1);
					}
				}else if( (range.get("start")!=null )&& range.get("end")!=null){
					if(organNumMap.get(onKey) > range.get("start") && organNumMap.get(onKey)< range.get("end") ){
						resultMap.put(tmpInt, resultMap.get(tmpInt)+1);
					}
				}else if( (range.get("start")!=null )&& range.get("end")==null  ){
					if(organNumMap.get(onKey) > range.get("start") ){
						resultMap.put(tmpInt, resultMap.get(tmpInt)+1);
					}
				}
				tmpInt++;
			}
		}
		Map<String,Map<Integer,Integer>> resultMaps = new HashMap<String, Map<Integer,Integer>>();
		resultMaps.put(district.getDistrictName(), resultMap);
		if( resultMaps!=null ){
			result.put("organNumResult", this.collationOfData(resultMaps, numRangeDic,1));
		}
		setResultData(result);
		return SUCCESS;
	}

	/**
	 *
	 * <p>
	 * 方法名：exportOrganTypeDistrict 分类标签 按机构类别统计 行政区划导出
	 * </p>
	 * <p>
	 * 宋亚非 2013-7-27 上午9:35:33
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@return
	 */
	public String exportOrganTypeDistrict(){
		//分段标题
		//table 表头1
		//table 表体 合计 百分比计算等等
		Map<String, Object> result = new HashMap<String, Object>();
		//查询表头
		List<DictionaryOption> organCategoryDictionary = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "organ_category");
		//查询数据
		String districtCode = getRequest().getParameter("districtCode");
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		}else{
			district = this.secrecyWorkOrganModuleService.get(districtCode, District.class);
		}
		String organId = getRequest().getParameter("organId");
		Map<String, Map<Integer, Integer>> organTypeResult = this.secrecyWorkOrganModuleService.statisticsByOrganTypeOfDistrict(organCategoryDictionary, district, organId);

		result.put("result", this.collationOfData(organTypeResult, organCategoryDictionary, 1));
		result.put("subTitle", "按机构类别统计");
		setResultData(result);
		return SUCCESS;
	}

	/**
	 *
	 * <p>
	 * 方法名：exportOrganTypeOrgan 分类标签 按机构类别统计 直辖机构导出
	 * </p>
	 * <p>
	 * 宋亚非 2013-7-27 上午9:49:58
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@return
	 */
	public String exportOrganTypeOrgan(){
		//分段标题
		//table 表头1
		//table 表体 合计 百分比计算等等
		Map<String, Object> result = new HashMap<String, Object>();
		//查询表头
		List<DictionaryOption> organCategoryDictionary = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "organ_category");
		//查询数据
		String districtCode = getRequest().getParameter("districtCode");
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		}else{
			district = this.secrecyWorkOrganModuleService.get(districtCode, District.class);
		}
		String organId = getRequest().getParameter("organId");
		Map<String, Map<Integer, Integer>> organTypeResult = this.secrecyWorkOrganModuleService.statisticsByOrganTypeOfOrgan(organCategoryDictionary, district, organId);
		result.put("result", this.collationOfDataDetail(organTypeResult, organCategoryDictionary));
		result.put("subTitle", "按机构类别统计");
		setResultData(result);
		return SUCCESS;
	}

	/**
	 *
	 * <p>
	 * 方法名：exportAdministrativeLevelOrgan 分类标签 按机构行政级别统计 子行政区划导出
	 * </p>
	 * <p>
	 * 宋亚非 2013-7-27 上午10:09:50
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@return
	 */
	public String exportAdministrativeLevelDistrict(){
		Map<String, Object> result = new HashMap<String, Object>();
		String districtCode = getRequest().getParameter("districtCode");
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		}else{
			district = this.secrecyWorkOrganModuleService.get(districtCode, District.class);
		}
		String organId = getRequest().getParameter("organId");

		//查询表头
		List<DictionaryOption> organAdminLevelDictionary = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "organ_admin_level");
		//查询数据
		Map<String, Map<Integer, Integer>> AdministrativeLevelResult = this.secrecyWorkOrganModuleService.statisticsByAdministrativeLevelOfDistrict(organAdminLevelDictionary, district, organId);
		result.put("result", this.collationOfData(AdministrativeLevelResult, organAdminLevelDictionary));
		result.put("subTitle", "按行政级别统计");
		setResultData(result);
		return SUCCESS;
	}

	/**
	 *
	 * <p>
	 * 方法名：exportAdministrativeLevelOrgan 分类标签 按机构行政级别统计 直辖机构导出
	 * </p>
	 * <p>
	 * 宋亚非 2013-7-27 上午10:22:04
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@return
	 */
	public String exportAdministrativeLevelOrgan(){
		Map<String, Object> result = new HashMap<String, Object>();
		String districtCode = getRequest().getParameter("districtCode");
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		}else{
			district = this.secrecyWorkOrganModuleService.get(districtCode, District.class);
		}
		String organId = getRequest().getParameter("organId");

		//查询表头
		List<DictionaryOption> organAdminLevelDictionary = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "organ_admin_level");
		//查询数据
		Map<String, Map<Integer, Integer>> AdministrativeLevelResult = this.secrecyWorkOrganModuleService.statisticsByAdministrativeLevelOfOrgan(organAdminLevelDictionary, district, organId);
		result.put("result", this.collationOfDataDetail(AdministrativeLevelResult, organAdminLevelDictionary));
		result.put("subTitle", "按行政级别统计");
		setResultData(result);
		return SUCCESS;
	}

	public String exportEstablishmentNum(){
		Map<String, Object> result = new HashMap<String, Object>();
		//查询数据
		String districtCode = getRequest().getParameter("districtCode");
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		}else{
			district = this.secrecyWorkOrganModuleService.get(districtCode, District.class);
		}
		String organId = getRequest().getParameter("organId");

		List<Map<String,Integer>> numRange = new ArrayList<Map<String,Integer>>();
		Map<String ,Integer> numRange1 = new HashMap<String, Integer>();
		numRange1.put("start", 0);
		numRange1.put("end", 35);
		numRange.add(numRange1);

		Map<String ,Integer> numRange2 = new HashMap<String, Integer>();
		numRange2.put("start", 35);
		numRange2.put("end", 60);
		numRange.add(numRange2);

		Map<String ,Integer> ageRange3 = new HashMap<String, Integer>();
		ageRange3.put("start", 60);
		ageRange3.put("end", null);
		numRange.add(ageRange3);

		Map<String,Map<String, Integer>> organNumMap = this.secrecyWorkOrganModuleService.statisticsByEstablishmentNumOfDistrict(numRange, district, organId, SECRECY_STATUS_NOW);
		List<DictionaryOption> numRangeDic = new ArrayList<DictionaryOption>();
		int dicNum = 0;
		for( Map<String, Integer> aRange : numRange ){
			DictionaryOption dicOption = new DictionaryOption();
			Integer startNum = aRange.get("start");
			Integer endNum = aRange.get("end");
			if( startNum==0 || startNum==null ){
				dicOption.setOptionId(dicNum+"");
				dicOption.setOptionText("小于"+endNum+"人");
				dicOption.setOptionValue(dicNum);
			}
			if( startNum!=null && startNum>0 && endNum!=null && endNum >0 ){
				dicOption.setOptionId(dicNum+"");
				dicOption.setOptionText(startNum+"人~"+endNum+"人");
				dicOption.setOptionValue(dicNum);
			}
			if( startNum!=null && endNum==null ){
				dicOption.setOptionId(dicNum+"");
				dicOption.setOptionText("大于"+startNum+"人");
				dicOption.setOptionValue(dicNum);
			}
			numRangeDic.add(dicOption);
			dicNum++;
		}

		Map<String,Map<Integer,Integer>> resultMaps = new HashMap<String, Map<Integer,Integer>>();
		for( String districtName : organNumMap.keySet() ){
			Map<Integer, Integer> resultMap = new HashMap<Integer, Integer>();
			for( int i=0;i<(numRange.size()+1);i++){
				resultMap.put(i, 0);
			}
			Map<String, Integer> subMap = organNumMap.get(districtName);
			Integer rowTotal = 0;
			for(String onKey : subMap.keySet() ){
				int tmpInt = 0;
				for(Map<String,Integer> range : numRange){
					if( (range.get("start")==0 || range.get("start")==null )&& range.get("end")!=null ){
						if( subMap.get(onKey)< range.get("end")){
							resultMap.put(tmpInt, resultMap.get(tmpInt)+1);
							rowTotal++;
						}
					}else if( (range.get("start")!=null )&& range.get("end")!=null){
						if(subMap.get(onKey) > range.get("start") && subMap.get(onKey)< range.get("end") ){
							resultMap.put(tmpInt, resultMap.get(tmpInt)+1);
							rowTotal++;
						}
					}else if( (range.get("start")!=null )&& range.get("end")==null  ){
						if(subMap.get(onKey) > range.get("start") ){
							resultMap.put(tmpInt, resultMap.get(tmpInt)+1);
							rowTotal++;
						}
					}
					tmpInt++;
				}
			}
			resultMap.put(numRange.size(), rowTotal);
			resultMaps.put(districtName, resultMap);
		}
		result.put("result", this.collationOfData(resultMaps, numRangeDic));
		setResultData(result);
		return SUCCESS;
	}

	public String exportEstablishmentNumOrgan(){
		Map<String, Object> result = new HashMap<String, Object>();
		//查询数据
		String districtCode = getRequest().getParameter("districtCode");
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		}else{
			district = this.secrecyWorkOrganModuleService.get(districtCode, District.class);
		}
		String organId = getRequest().getParameter("organId");

		List<Map<String,Integer>> numRange = new ArrayList<Map<String,Integer>>();
		Map<String ,Integer> numRange1 = new HashMap<String, Integer>();
		numRange1.put("start", 0);
		numRange1.put("end", 35);
		numRange.add(numRange1);

		Map<String ,Integer> numRange2 = new HashMap<String, Integer>();
		numRange2.put("start", 35);
		numRange2.put("end", 60);
		numRange.add(numRange2);

		Map<String ,Integer> ageRange3 = new HashMap<String, Integer>();
		ageRange3.put("start", 60);
		ageRange3.put("end", null);
		numRange.add(ageRange3);

		Map<String, Integer> organNumMap = this.secrecyWorkOrganModuleService.statisticsByEstablishmentNumOfOrgan(numRange, district, organId, SECRECY_STATUS_NOW);
		List<DictionaryOption> numRangeDic = new ArrayList<DictionaryOption>();
		int dicNum = 0;
		for( Map<String, Integer> aRange : numRange ){
			DictionaryOption dicOption = new DictionaryOption();
			Integer startNum = aRange.get("start");
			Integer endNum = aRange.get("end");
			if( startNum==0 || startNum==null ){
				dicOption.setOptionId(dicNum+"");
				dicOption.setOptionText("小于"+endNum+"人");
				dicOption.setOptionValue(dicNum);
			}
			if( startNum!=null && startNum>0 && endNum!=null && endNum >0 ){
				dicOption.setOptionId(dicNum+"");
				dicOption.setOptionText(startNum+"人~"+endNum+"人");
				dicOption.setOptionValue(dicNum);
			}
			if( startNum!=null && endNum==null ){
				dicOption.setOptionId(dicNum+"");
				dicOption.setOptionText("大于"+startNum+"人");
				dicOption.setOptionValue(dicNum);
			}
			numRangeDic.add(dicOption);
			dicNum++;
		}

		Map<Integer, Integer> resultMap = new HashMap<Integer, Integer>();
		for( int i=0;i<numRange.size();i++){
			resultMap.put(i, 0);
		}
		Map<String,Map<Integer,Integer>> resultMaps = new HashMap<String, Map<Integer,Integer>>();
		for(String onKey : organNumMap.keySet() ){
			int tmpInt = 0;
			for(Map<String,Integer> range : numRange){
				if( (range.get("start")==0 || range.get("start")==null )&& range.get("end")!=null ){
					if( organNumMap.get(onKey)< range.get("end")){
						resultMap.put(tmpInt, resultMap.get(tmpInt)+1);
					}
				}else if( (range.get("start")!=null )&& range.get("end")!=null){
					if(organNumMap.get(onKey) > range.get("start") && organNumMap.get(onKey)< range.get("end") ){
						resultMap.put(tmpInt, resultMap.get(tmpInt)+1);
					}
				}else if( (range.get("start")!=null )&& range.get("end")==null  ){
					if(organNumMap.get(onKey) > range.get("start") ){
						resultMap.put(tmpInt, resultMap.get(tmpInt)+1);
					}
				}
				tmpInt++;
			}
			resultMaps.put(onKey, resultMap);
		}
		result.put("result", this.collationOfDataDetail(resultMaps, numRangeDic));
		setResultData(result);
		return SUCCESS;
	}

	/**
	 *
	 * <p>
	 * 方法名：exportOverview
	 * </p>
	 * <p>
	 * 宋亚非 2013-7-27 上午11:28:59
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@return
	 */
	public String exportCurrent(){
		//分段标题
		//table 表头1
		//table 表体 合计 百分比计算等等
		Map<String, Object> result = new HashMap<String, Object>();
		//查询表头
		List<DictionaryOption> organCategoryDictionary = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "organ_category");
		//查询数据
		String districtCode = getRequest().getParameter("districtCode");
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		}else{
			district = this.secrecyWorkOrganModuleService.get(districtCode, District.class);
		}
		//导出单位明细统计信息功能使用
		String organId = getRequest().getParameter("organId");
		if( !(organId!=null && !"".equals(organId))){
			//导出本单位统计信息使用
			organId = getCurrentUser().getOrgan().getOrganId();
		}
		Map<String, Map<Integer, Integer>> organTypeResult = this.secrecyWorkOrganModuleService.statisticsByOrganType(organCategoryDictionary, district, organId);
		result.put("organTypeResult", this.collationOfDataDetail(organTypeResult, organCategoryDictionary));

		//查询表头
		List<DictionaryOption> organAdminLevelDictionary = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "organ_admin_level");
		//查询数据
		Map<String, Map<Integer, Integer>> AdministrativeLevelResult = this.secrecyWorkOrganModuleService.statisticsByAdministrativeLevel(organAdminLevelDictionary, district, organId);
		result.put("AdministrativeLevel", this.collationOfDataDetail(AdministrativeLevelResult, organAdminLevelDictionary));

		List<Map<String,Integer>> numRange = new ArrayList<Map<String,Integer>>();
		Map<String ,Integer> numRange1 = new HashMap<String, Integer>();
		numRange1.put("start", 0);
		numRange1.put("end", 35);
		numRange.add(numRange1);

		Map<String ,Integer> numRange2 = new HashMap<String, Integer>();
		numRange2.put("start", 35);
		numRange2.put("end", 60);
		numRange.add(numRange2);

		Map<String ,Integer> ageRange3 = new HashMap<String, Integer>();
		ageRange3.put("start", 60);
		ageRange3.put("end", null);
		numRange.add(ageRange3);

		Map<String, Integer> organNumMap = this.secrecyWorkOrganModuleService.statisticsByEstablishmentNum(numRange, district, organId, SECRECY_STATUS_NOW);
		List<DictionaryOption> numRangeDic = new ArrayList<DictionaryOption>();
		int dicNum = 0;
		for( Map<String, Integer> aRange : numRange ){
			DictionaryOption dicOption = new DictionaryOption();
			Integer startNum = aRange.get("start");
			Integer endNum = aRange.get("end");
			if( startNum==0 || startNum==null ){
				dicOption.setOptionId(dicNum+"");
				dicOption.setOptionText("小于"+endNum+"人");
				dicOption.setOptionValue(dicNum);
			}
			if( startNum!=null && startNum>0 && endNum!=null && endNum >0 ){
				dicOption.setOptionId(dicNum+"");
				dicOption.setOptionText(startNum+"人~"+endNum+"人");
				dicOption.setOptionValue(dicNum);
			}
			if( startNum!=null && endNum==null ){
				dicOption.setOptionId(dicNum+"");
				dicOption.setOptionText("大于"+startNum+"人");
				dicOption.setOptionValue(dicNum);
			}
			numRangeDic.add(dicOption);
			dicNum++;
		}

		Map<Integer, Integer> resultMap = new HashMap<Integer, Integer>();
		for( int i=0;i<numRange.size();i++){
			resultMap.put(i, 0);
		}
		for(String onKey : organNumMap.keySet() ){
			int tmpInt = 0;
			for(Map<String,Integer> range : numRange){
				if( (range.get("start")==0 || range.get("start")==null )&& range.get("end")!=null ){
					if( organNumMap.get(onKey)< range.get("end")){
						resultMap.put(tmpInt, resultMap.get(tmpInt)+1);
					}
				}else if( (range.get("start")!=null )&& range.get("end")!=null){
					if(organNumMap.get(onKey) > range.get("start") && organNumMap.get(onKey)< range.get("end") ){
						resultMap.put(tmpInt, resultMap.get(tmpInt)+1);
					}
				}else if( (range.get("start")!=null )&& range.get("end")==null  ){
					if(organNumMap.get(onKey) > range.get("start") ){
						resultMap.put(tmpInt, resultMap.get(tmpInt)+1);
					}
				}
				tmpInt++;
			}
		}
		Map<String,Map<Integer,Integer>> resultMaps = new HashMap<String, Map<Integer,Integer>>();
		resultMaps.put(district.getDistrictName(), resultMap);
		result.put("organNumResult", this.collationOfDataDetail(resultMaps, numRangeDic));
		setResultData(result);
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
		// 保密工作机构成员
		String organId = getRequest().getParameter("organId").toString();
		QueryDto queryDto = new QueryDto();
		queryDto.setYear(Integer.parseInt(getRequest().getParameter("queryDto.year").toString()));
		queryDto.setMonth(Integer.parseInt(getRequest().getParameter("queryDto.month").toString()));
		organ = secrecyWorkOrganRelationMemberModuleService.get(organId, Organ.class);
		secrecyWorkOrgan = secrecyWorkOrganModuleService.getSecrecyWorkOrganByOrgan(organ);
		secrecyWorkOrganRelationMemberList = secrecyWorkOrganRelationMemberModuleService.getPageList(secrecyWorkOrgan,organ,PERSON_CHANGE_NOW);
		for (SecrecyWorkOrganRelationMember swor : secrecyWorkOrganRelationMemberList) {
			if(swor.getPerson().getBirthDate() != null){
				swor.setAgeStr(DateUtils.getYearNumber(swor.getPerson().getBirthDate(), new Date()) + "");
			}
		}
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
		resultData.put("id", "1");
		SecrecyWorkOrgan swo = secrecyWorkOrganModuleService.getSecrecyWorkOrganByOrgan(getCurrentUser().getOrgan());
		List<SecrecyWorkOrgan> dataList = new ArrayList<SecrecyWorkOrgan>();
		if (swo != null) {
			dataList.add(swo);
		}
		String msg = dataValidateService.validateData("保密工作机构", dataList, "1");
		resultData.put("msg", msg);
		setResultData(resultData);
		return JSON;
	}

	// ******************** Setter & Getter ********************
	/**
	 * 返回secrecyWorkOrganModuleService
	 * @return secrecyWorkOrganModuleService
	 */
	public SecrecyWorkOrganModuleService getSecrecyWorkOrganModuleService() {
		return secrecyWorkOrganModuleService;
	}

	/**
	 * 设置secrecyWorkOrganModuleService
	 * @param secrecyWorkOrganModuleService secrecyWorkOrganModuleService
	 */
	public void setSecrecyWorkOrganModuleService(
			SecrecyWorkOrganModuleService secrecyWorkOrganModuleService) {
		this.secrecyWorkOrganModuleService = secrecyWorkOrganModuleService;
	}

	/**
	 * 返回secrecyWorkOrgan
	 * @return secrecyWorkOrgan
	 */
	public SecrecyWorkOrgan getSecrecyWorkOrgan() {
		return secrecyWorkOrgan;
	}

	/**
	 * 设置secrecyWorkOrgan
	 * @param secrecyWorkOrgan secrecyWorkOrgan
	 */
	public void setSecrecyWorkOrgan(SecrecyWorkOrgan secrecyWorkOrgan) {
		this.secrecyWorkOrgan = secrecyWorkOrgan;
	}

	/**
	 * 返回secrecyWorkOrganList
	 * @return secrecyWorkOrganList
	 */
	public List<SecrecyWorkOrgan> getSecrecyWorkOrganList() {
		return secrecyWorkOrganList;
	}

	/**
	 * 设置secrecyWorkOrganList
	 * @param secrecyWorkOrganList secrecyWorkOrganList
	 */
	public void setSecrecyWorkOrganList(List<SecrecyWorkOrgan> secrecyWorkOrganList) {
		this.secrecyWorkOrganList = secrecyWorkOrganList;
	}

	/**
	 * 返回secrecyWorkOrganMemberUnitModuleService
	 * @return secrecyWorkOrganMemberUnitModuleService
	 */
	public SecrecyWorkOrganMemberUnitModuleService getSecrecyWorkOrganMemberUnitModuleService() {
		return secrecyWorkOrganMemberUnitModuleService;
	}

	/**
	 * 设置secrecyWorkOrganMemberUnitModuleService
	 * @param secrecyWorkOrganMemberUnitModuleService secrecyWorkOrganMemberUnitModuleService
	 */
	public void setSecrecyWorkOrganMemberUnitModuleService(
			SecrecyWorkOrganMemberUnitModuleService secrecyWorkOrganMemberUnitModuleService) {
		this.secrecyWorkOrganMemberUnitModuleService = secrecyWorkOrganMemberUnitModuleService;
	}

	/**
	 * 返回secrecyWorkOrganMemberUnit
	 * @return secrecyWorkOrganMemberUnit
	 */
	public SecrecyWorkOrganMemberUnit getSecrecyWorkOrganMemberUnit() {
		return secrecyWorkOrganMemberUnit;
	}

	/**
	 * 设置secrecyWorkOrganMemberUnit
	 * @param secrecyWorkOrganMemberUnit secrecyWorkOrganMemberUnit
	 */
	public void setSecrecyWorkOrganMemberUnit(
			SecrecyWorkOrganMemberUnit secrecyWorkOrganMemberUnit) {
		this.secrecyWorkOrganMemberUnit = secrecyWorkOrganMemberUnit;
	}

	/**
	 * 返回secrecyWorkOrganMemberUnitList
	 * @return secrecyWorkOrganMemberUnitList
	 */
	public List<SecrecyWorkOrganMemberUnit> getSecrecyWorkOrganMemberUnitList() {
		return secrecyWorkOrganMemberUnitList;
	}

	/**
	 * 设置secrecyWorkOrganMemberUnitList
	 * @param secrecyWorkOrganMemberUnitList secrecyWorkOrganMemberUnitList
	 */
	public void setSecrecyWorkOrganMemberUnitList(
			List<SecrecyWorkOrganMemberUnit> secrecyWorkOrganMemberUnitList) {
		this.secrecyWorkOrganMemberUnitList = secrecyWorkOrganMemberUnitList;
	}

	/**
	 * 返回organ
	 * @return organ
	 */
	public Organ getOrgan() {
		return organ;
	}

	/**
	 * 设置organ
	 * @param organ organ
	 */
	public void setOrgan(Organ organ) {
		this.organ = organ;
	}

	/**
	 * 返回district
	 * @return district
	 */
	public District getDistrict() {
		return district;
	}

	/**
	 * 设置district
	 * @param district district
	 */
	public void setDistrict(District district) {
		this.district = district;
	}

	/**
	 * 返回attachmentService
	 * @return attachmentService
	 */
	public AttachmentService getAttachmentService() {
		return attachmentService;
	}

	/**
	 * 设置attachmentService
	 * @param attachmentService attachmentService
	 */
	public void setAttachmentService(AttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}

	/**
	 * 返回secrecyWorkOrganAttachs
	 * @return secrecyWorkOrganAttachs
	 */
	public List<String> getSecrecyWorkOrganAttachs() {
		return secrecyWorkOrganAttachs;
	}

	/**
	 * 设置secrecyWorkOrganAttachs
	 * @param secrecyWorkOrganAttachs secrecyWorkOrganAttachs
	 */
	public void setSecrecyWorkOrganAttachs(List<String> secrecyWorkOrganAttachs) {
		this.secrecyWorkOrganAttachs = secrecyWorkOrganAttachs;
	}

	/**
	 * 返回deptFlag
	 * @return deptFlag
	 */
	public String getDeptFlag() {
		return deptFlag;
	}

	/**
	 * 设置deptFlag
	 * @param deptFlag deptFlag
	 */
	public void setDeptFlag(String deptFlag) {
		this.deptFlag = deptFlag;
	}

	/**
	 * 返回secrecyWorkOrganRelationMember
	 * @return secrecyWorkOrganRelationMember
	 */
	public SecrecyWorkOrganRelationMember getSecrecyWorkOrganRelationMember() {
		return secrecyWorkOrganRelationMember;
	}

	/**
	 * 设置secrecyWorkOrganRelationMember
	 * @param secrecyWorkOrganRelationMember secrecyWorkOrganRelationMember
	 */
	public void setSecrecyWorkOrganRelationMember(
			SecrecyWorkOrganRelationMember secrecyWorkOrganRelationMember) {
		this.secrecyWorkOrganRelationMember = secrecyWorkOrganRelationMember;
	}

	/**
	 * 返回secrecyWorkOrganRelationMemberModuleService
	 * @return secrecyWorkOrganRelationMemberModuleService
	 */
	public SecrecyWorkOrganRelationMemberModuleService getSecrecyWorkOrganRelationMemberModuleService() {
		return secrecyWorkOrganRelationMemberModuleService;
	}

	/**
	 * 设置secrecyWorkOrganRelationMemberModuleService
	 * @param secrecyWorkOrganRelationMemberModuleService secrecyWorkOrganRelationMemberModuleService
	 */
	public void setSecrecyWorkOrganRelationMemberModuleService(
			SecrecyWorkOrganRelationMemberModuleService secrecyWorkOrganRelationMemberModuleService) {
		this.secrecyWorkOrganRelationMemberModuleService = secrecyWorkOrganRelationMemberModuleService;
	}

	/**
	 * 返回secrecyWorkOrganRelationMemberList
	 * @return secrecyWorkOrganRelationMemberList
	 */
	public List<SecrecyWorkOrganRelationMember> getSecrecyWorkOrganRelationMemberList() {
		return secrecyWorkOrganRelationMemberList;
	}

	/**
	 * 设置secrecyWorkOrganRelationMemberList
	 * @param secrecyWorkOrganRelationMemberList secrecyWorkOrganRelationMemberList
	 */
	public void setSecrecyWorkOrganRelationMemberList(
			List<SecrecyWorkOrganRelationMember> secrecyWorkOrganRelationMemberList) {
		this.secrecyWorkOrganRelationMemberList = secrecyWorkOrganRelationMemberList;
	}

	/**
	 * @return 返回secrecyWorkOrganIds
	 */
	public String getSecrecyWorkOrganIds() {
		return secrecyWorkOrganIds;
	}

	/**
	 * @param secrecyWorkOrganIds 设置secrecyWorkOrganIds
	 */
	public void setSecrecyWorkOrganIds(String secrecyWorkOrganIds) {
		this.secrecyWorkOrganIds = secrecyWorkOrganIds;
	}

	/**
	 * @param secrecyWorkOrganExchanger 设置secrecyWorkOrganExchanger
	 */
	public void setSecrecyWorkOrganExchanger(
			SecrecyWorkOrganExchanger secrecyWorkOrganExchanger) {
		this.secrecyWorkOrganExchanger = secrecyWorkOrganExchanger;
	}

	/**
	 * @param serverReportMappingService 设置serverReportMappingService
	 */
	public void setServerReportMappingService(
			ServerReportMappingService serverReportMappingService) {
		this.serverReportMappingService = serverReportMappingService;
	}

	/**
	 * @param dataSource 设置dataSource
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * @return the secrecyWorkOrganRelationMemberChangeService
	 */
	public SecrecyWorkOrganRelationMemberChangeService getSecrecyWorkOrganRelationMemberChangeService() {
		return secrecyWorkOrganRelationMemberChangeService;
	}

	/**
	 * @param secrecyWorkOrganRelationMemberChangeService the secrecyWorkOrganRelationMemberChangeService to set
	 */
	public void setSecrecyWorkOrganRelationMemberChangeService(
			SecrecyWorkOrganRelationMemberChangeService secrecyWorkOrganRelationMemberChangeService) {
		this.secrecyWorkOrganRelationMemberChangeService = secrecyWorkOrganRelationMemberChangeService;
	}

	/**
	 * @return the secrecyWorkOrganMembersUnitChangeService
	 */
	public SecrecyWorkOrganMembersUnitChangeService getSecrecyWorkOrganMembersUnitChangeService() {
		return secrecyWorkOrganMembersUnitChangeService;
	}

	/**
	 * @param secrecyWorkOrganMembersUnitChangeService the secrecyWorkOrganMembersUnitChangeService to set
	 */
	public void setSecrecyWorkOrganMembersUnitChangeService(
			SecrecyWorkOrganMembersUnitChangeService secrecyWorkOrganMembersUnitChangeService) {
		this.secrecyWorkOrganMembersUnitChangeService = secrecyWorkOrganMembersUnitChangeService;
	}

	/**
	 * @return the expBtnFlag
	 */
	public String getExpBtnFlag() {
		return expBtnFlag;
	}

	/**
	 * @param expBtnFlag the expBtnFlag to set
	 */
	public void setExpBtnFlag(String expBtnFlag) {
		this.expBtnFlag = expBtnFlag;
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