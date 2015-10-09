package com.cdthgk.bmp.secrecyorganization.secrecycommittee.action;

import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.cdthgk.bmp.core.action.BmpAction;
import com.cdthgk.bmp.secrecyorganization.secrecycommittee.dto.SecrecyCommitteeDto;
import com.cdthgk.bmp.secrecyorganization.secrecycommittee.service.PersonGroupRelationService;
import com.cdthgk.bmp.secrecyorganization.secrecycommittee.service.PersonGroupService;
import com.cdthgk.bmp.secrecyorganization.secrecycommittee.service.SecrecyCommitteeMemberModuleService;
import com.cdthgk.bmp.secrecyorganization.secrecycommittee.service.SecrecyCommitteeModuleService;
import com.cdthgk.bmp.secrecyorganization.secrecycommittee.transmitor.exchange.SecrecyCommitteeExchanger;
import com.cdthgk.bmp.secrecyorganization.secrecycommittee.vo.SecrecyCommittee;
import com.cdthgk.bmp.secrecyorganization.secrecycommittee.vo.SecrecyCommitteeMember;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.service.SecrecyWorkOrganMemberUnitModuleService;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.service.SecrecyWorkOrganModuleService;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.service.SecrecyWorkOrganRelationMemberModuleService;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.vo.SecrecyWorkOrgan;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.vo.SecrecyWorkOrganMemberUnit;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.vo.SecrecyWorkOrganRelationMember;
import com.cdthgk.common.db.data.xml.XmlExportor;
import com.cdthgk.exchange.serverorganmapping.service.ServerReportMappingService;
import com.cdthgk.platform.attachment.domain.Attachment;
import com.cdthgk.platform.attachment.service.AttachmentService;
import com.cdthgk.platform.base.util.ReportState;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.core.OrganizationContext;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.organ.service.OrganService;

import ec.common.PageSortModel;

/**
 * <p>
 * 保密委员会 Action 类
 * </p>
 * <p>
 * 汪 钟 2012-12-14 10:43:03
 * </p>
 * <blockquote>
 * <h4>历史修改记录</h4>
 * <ul>
 * <li>修改人 修改时间 修改描述
 * </ul>
 * </blockquote>
 * <p>
 * copyright tianyu 2012, all rights reserved.
 * </p>
 *
 * @author FastCodeingTools
 * @author tianyu
 * @since 1.0
 * @version 1.0
 * @param <E>
 */
@SuppressWarnings("rawtypes")
public class SecrecyCommitteeAction<E> extends BmpAction {

	private static final long serialVersionUID = -2561934305983981072L;

	// 通用字段
	private SecrecyCommitteeModuleService secrecyCommitteeModuleService;
	private SecrecyCommitteeMemberModuleService secrecyCommitteeMemberModuleService;
	private SecrecyWorkOrganModuleService secrecyWorkOrganModuleService;
	private SecrecyWorkOrganMemberUnitModuleService secrecyWorkOrganMemberUnitModuleService;
	private SecrecyWorkOrganRelationMemberModuleService secrecyWorkOrganRelationMemberModuleService;


	private SecrecyCommittee secrecyCommittee;
	private List<SecrecyCommittee> secrecyCommitteeList;
	private List<SecrecyCommitteeMember> secrecyCommitteeMemberList;
	private String secrecyCommitteeIds;
	private Boolean needReload = false;
	//是否显示上报字段
	private boolean isShow = true;

	// 附件
	private AttachmentService attachmentService;
	private List<Attachment> attachmentList;
	private List<String> attachmentIds;

	private District district;
	private Organ organ;
	private DataSource dataSource;
	private SecrecyCommitteeExchanger secrecyCommitteeExchanger;
	private ServerReportMappingService serverReportMappingService;
	private PersonGroupService personGroupService;
	private PersonGroupRelationService personGroupRelationService;
	private OrganService organService;

	// *********************** 方 法 ***********************



	// 构造器
	/** 默认构造器 */
	public SecrecyCommitteeAction() {
	}

	/**
	 * <p>
	 * 详情
	 * </p>
	 * <p>
	 * 汪 钟 2012-12-14 10:43:03
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 宋亚非  修改时间 2013-05-13  修改描述：屏蔽查询功能中的操作按钮
	 * </ul>
	 * </blockquote>
	 * <p>
	 * copyright tianyu 2012, all rights reserved.
	 * </p>
	 *
	 * @author FastCodeingTools
	 * @author tianyu
	 * @since 1.0
	 * @version 1.0
	 */
	public String detail() {

		if(district == null){
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district =  secrecyCommitteeModuleService.get(district.getDistrictCode(), District.class);
		}
		secrecyCommittee = secrecyCommitteeModuleService.getByDistrict(district);

		// 得到成员列表
		if(secrecyCommittee != null){
			PageSortModel psm = new PageSortModel(getRequest(), "secrecyCommitteeMemberList");
			secrecyCommitteeMemberList = secrecyCommitteeMemberModuleService.getPageList(psm, secrecyCommittee, PERSON_CHANGE_NOW);
		}

		// 得到附件
		if(secrecyCommittee!=null){
			attachmentList = attachmentService.getAttachmentsByHostId(secrecyCommittee.getSecrecyCommitteeId());
		}

		// 查询下级保密委员会判断依据
		if(district.getDistrictCode().equals(getCurrentUser().getOrgan().getDistrict().getDistrictCode())){
			putToRequest("viewCondition", "1");
		} else {
			putToRequest("viewCondition", "0");
		}
		//增加了从页面传递过来的参数editable，目的是让查询的结果中不显示操作按钮。
		String editable = this.getRequest().getParameter("editable");
		if( editable!=null && "0".equals(editable) ){
			putToRequest("viewCondition", "0");
		}
		//是否显示上报(列表页和带行政区的查询列表页)
		if(getRequest().getParameter("va") != null){
			isShow = false;
		}
		putToRequest("isShow", isShow);
		return "detail";
	}

	/**
	 * <p>
	 * 到新增页面
	 * </p>
	 * <p>
	 * 汪 钟 2012-12-14 10:43:03
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * <p>
	 * copyright tianyu 2012, all rights reserved.
	 * </p>
	 *
	 * @author FastCodeingTools
	 * @author tianyu
	 * @since 1.0
	 * @version 1.0
	 */
	public String add() {
		// 检查
			// 条件1： 检查一个行政区划是否有唯一的一个保密委
			secrecyCommittee = secrecyCommitteeModuleService.getByDistrict(getCurrentUser().getOrgan().getDistrict());
			if(secrecyCommittee!=null){
				// 设置信息
				this.addActionMessage("当前行政区划已经有保密委，请勿新增。");
				return this.redirectActionResult("detail");
			}

		// 自动生成表单中保密委的名称
		secrecyCommittee = new SecrecyCommittee();
		secrecyCommittee.setName(secrecyCommitteeModuleService.getNameByUser(getCurrentUser()));

		return "add";
	}

	/**
	 * <p>
	 * 新增操作
	 * </p>
	 * <p>
	 * 汪 钟 2012-12-14 10:43:03
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * <p>
	 * copyright tianyu 2012, all rights reserved.
	 * </p>
	 *
	 * @author FastCodeingTools
	 * @author tianyu
	 * @since 1.0
	 * @version 1.0
	 */
	public String adding() {
		// 检查
			// 条件1： 检查一个行政区划是否有唯一的一个保密委
			SecrecyCommittee secrecyCommitteeForChk = secrecyCommitteeModuleService.getByDistrict(getCurrentUser().getOrgan().getDistrict());
			if(secrecyCommitteeForChk!=null){
				// 设置信息
				this.addActionMessage("当前行政区划已经有保密委，请勿新增。");
				return this.redirectActionResult("detail");
			}

		// 传入的字段
		SecrecyCommittee secrecyCommitteeTemp = new SecrecyCommittee();
		secrecyCommitteeTemp.setDutyMemberWork(secrecyCommittee.getDutyMemberWork());
		secrecyCommitteeTemp.setSetupDate(secrecyCommittee.getSetupDate());
		secrecyCommitteeTemp.setDocNo(secrecyCommittee.getDocNo());
		secrecyCommitteeTemp.setName(secrecyCommittee.getName());
		secrecyCommitteeTemp.setDistrict(getCurrentUser().getOrgan().getDistrict());
		// 设置通用字段
		secrecyCommitteeTemp.setCreateTime(new Date());
		secrecyCommitteeTemp.setCreateUser(getCurrentUser());
		secrecyCommitteeTemp.setCreateOrgan(getCurrentUser().getUserInfo().getOrgan());
		secrecyCommitteeTemp.setEnable(1);

		//修改人：宋亚非   修改原因：数据库统一至一个库后，取消上报功能，填写信息后状态即为已上报   修改时间：2013-06-03
		//secrecyCommitteeTemp.setReportState(ReportState.REPORT_NO);
		secrecyCommitteeTemp.setReportState(ReportState.REPORT_YES);

		secrecyCommitteeModuleService.save(secrecyCommitteeTemp);

		// 附件
		attachmentService.updateHostId(secrecyCommitteeTemp.getSecrecyCommitteeId(), attachmentIds);

		// 设置信息
		addActionMessage(getMessageConstant().getSaveSuccess()+ "请继续添加该保密委员会下的成员信息！");

		secrecyCommittee = secrecyCommitteeTemp;

		return this.redirectActionResult("edit");
	}

	/**
	 * <p>
	 * 到编辑页面
	 * </p>
	 * <p>
	 * 汪 钟 2012-12-14 10:43:03
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * <p>
	 * copyright tianyu 2012, all rights reserved.
	 * </p>
	 *
	 * @author FastCodeingTools
	 * @author tianyu
	 * @since 1.0
	 * @version 1.0
	 */
	public String edit() {
		// 检查
			// 条件1： 检查id
			if(secrecyCommittee==null || secrecyCommittee.getSecrecyCommitteeId()==null || secrecyCommittee.getSecrecyCommitteeId().equals("")){
				this.addActionMessage("您所访问的资源Id不存在，请确认后重试。");
				return this.redirectActionResult("detail");
			}
			// 条件2： 检查一个行政区划是否有唯一的一个保密委
			secrecyCommittee = secrecyCommitteeModuleService.getByDistrict(getCurrentUser().getOrgan().getDistrict());
			if(secrecyCommittee==null){
				// 设置信息
				this.addActionMessage("当前行政区划暂无保密委，请先新增。");
				return this.redirectActionResult("detail");
			}

		secrecyCommittee = secrecyCommitteeModuleService.get(secrecyCommittee.getSecrecyCommitteeId());

		// 得到附件
		attachmentList = attachmentService.getAttachmentsByHostId(secrecyCommittee.getSecrecyCommitteeId());

		// 得到成员列表
		PageSortModel psm = new PageSortModel(getRequest(), "secrecyCommitteeMemberList");
		secrecyCommitteeMemberList = secrecyCommitteeMemberModuleService.getPageList(psm, secrecyCommittee, PERSON_CHANGE_NOW );

		return "edit";
	}

	/**
	 * <p>
	 * 编辑操作
	 * </p>
	 * <p>
	 * 汪 钟 2012-12-14 10:43:03
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * <p>
	 * copyright tianyu 2012, all rights reserved.
	 * </p>
	 *
	 * @author FastCodeingTools
	 * @author tianyu
	 * @since 1.0
	 * @version 1.0
	 */
	public String editing() {
		// 检查
			// 条件1： 检查id
			if(secrecyCommittee==null || secrecyCommittee.getSecrecyCommitteeId()==null || secrecyCommittee.getSecrecyCommitteeId().equals("")){
				this.addActionMessage("您所访问的资源Id不存在，请确认后重试。");
				return this.redirectActionResult("detail");
			}
			// 条件2： 检查一个行政区划是否有唯一的一个保密委
			SecrecyCommittee secrecyCommitteeFormDb = secrecyCommitteeModuleService.get(secrecyCommittee.getSecrecyCommitteeId());
			if(secrecyCommitteeFormDb==null){
				// 设置信息
				this.addActionMessage("当前行政区划暂无保密委，请先新增。");
				return this.redirectActionResult("detail");
			}

		// 更新业务信息
		secrecyCommitteeFormDb.setDutyMemberWork(secrecyCommittee.getDutyMemberWork());
		secrecyCommitteeFormDb.setSetupDate(secrecyCommittee.getSetupDate());
		secrecyCommitteeFormDb.setDocNo(secrecyCommittee.getDocNo());
		secrecyCommitteeFormDb.setName(secrecyCommittee.getName());
		// 更新基础信息
		secrecyCommitteeFormDb.setModifyUser(getCurrentUser());
		secrecyCommitteeFormDb.setModifyOrgan(getCurrentUser().getOrgan());
		secrecyCommitteeFormDb.setModifyTime(new Date());

		//修改人：宋亚非   修改原因：数据库统一至一个库后，取消上报功能，不再存在上报后修改状态   修改时间：2013-06-03
		/*if(secrecyCommitteeFormDb.getReportState() > 0) {
			secrecyCommitteeFormDb.setReportState(ReportState.REPORT_YES_MODIFY);
		}*/
		secrecyCommitteeModuleService.update(secrecyCommitteeFormDb);

		// 保存附件
		attachmentService.updateHostId(secrecyCommitteeFormDb.getSecrecyCommitteeId(), attachmentIds);

		// 设置信息
		this.addActionMessage("更新成功!");

		// 设置是否需要重载
		needReload = true;

		return this.redirectActionResult("detail");
	}

	/**
	 *
	 * <p>
	 * 查询下级保密委员会
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-3-20 - 上午10:59:09
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
		if(district == null) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = secrecyCommitteeModuleService.get(district.getDistrictCode(), District.class);
		}
		return redirectActionResult("detail");
	}

	public String main(){
		return SUCCESS;
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
			if(secrecyCommitteeIds != null && !"".equals(secrecyCommitteeIds)) {
				// 查询需要上报的数据 domainList
				List<SecrecyCommittee> domainList = new ArrayList<SecrecyCommittee>();
				String [] secrecyCommitteeIdArray =  secrecyCommitteeIds.split(",");
				for (String sid : secrecyCommitteeIdArray) {
					SecrecyCommittee sc = secrecyCommitteeModuleService.get(sid);
					sc.setAttachmentList(attachmentService.getAttachmentsByHostId(sc.getSecrecyCommitteeId()));
					sc.setReportState(ReportState.REPORT_YES);
					secrecyCommitteeModuleService.update(sc);
					domainList.add(sc);
				}
				// 查询上报单位
				List<String> organIdList = serverReportMappingService.getReportToOragans(getCurrentUser().getOrgan());
				Iterator<String> i = organIdList.iterator();
				while (i.hasNext()) {
					String organId = i.next();
					//组织需要上报内容
					for (String secrecyCommitteeId : secrecyCommitteeIdArray) {
						SecrecyCommitteeDto secrecyCommitteeDto = new SecrecyCommitteeDto();
						List<String> querySqls = new ArrayList<String>();
						querySqls.add("select * from bm_secrecy_committee where SECRECY_COMMITTEE_ID = '" + secrecyCommitteeId + "'");
						//因为接收单位并没有同步，所以需要上报。
						querySqls.add("select * from sys_organization where organ_id = '" + getCurrentUser().getOrgan().getOrganId() + "'");
						//单位下所有子部门，因为接收单位并没有同步，所以需要上报。
						querySqls.add("select * from sys_department where organ_id = '" + getCurrentUser().getOrgan().getOrganId() + "'");
						//单位下所有人员，因为要害部门负责人需要，所以需要上报。
						querySqls.add("select * from sys_user_info where organ_id = '" + getCurrentUser().getOrgan().getOrganId() + "'");
						//保密委员会成员列表
						querySqls.add("select * from bm_secrecy_committee_members where SECRECY_COMMITTEE = '" + secrecyCommitteeId + "'");
						//保密委员会附件需要，所以需要上报。
						querySqls.add("select * from base_attachment where host_id = '" + secrecyCommitteeId + "'");
						XmlExportor exportor = new XmlExportor();
						exportor.setDataSource(dataSource);
						StringWriter writer = new StringWriter();
						exportor.exportData(writer , querySqls.toArray(new String[]{}));
						secrecyCommitteeDto.setReportData(writer.toString());
						secrecyCommitteeDto.setReceiveOrganId(organId);
						secrecyCommitteeDto.setSecrecyCommitteeId(secrecyCommitteeId);
						// 发送数据
						secrecyCommitteeExchanger.exchange(secrecyCommitteeDto, getCurrentUser().getOrgan()
								, OrganizationContext.getInstance().getOrganService().get(organId));
					}
				}
			}
			addActionMessage("上报成功！");
		}catch (Exception e) {
			addActionMessage("上报失败！" + e.getMessage());
		}
		return redirectActionResult(DETAIL);
	}

	/**
	 *
	 * <p>
	 * 方法名：exportData 导出查询结果
	 * </p>
	 * <p>
	 * 宋亚非 2013-5-20 下午6:07:11
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
		if(district == null) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = secrecyCommitteeModuleService.get(district.getDistrictCode(), District.class);
		}
		secrecyCommittee = secrecyCommitteeModuleService.getByDistrict(district);

		// 得到成员列表
		if(secrecyCommittee != null){
			params.put("secrecyCommittee", secrecyCommittee);
			secrecyCommitteeMemberList = secrecyCommitteeMemberModuleService.getAllList(secrecyCommittee, PERSON_CHANGE_NOW);
			if( secrecyCommitteeMemberList!=null ){
				params.put("secrecyCommitteeMemberList", secrecyCommitteeMemberList);
			}
		}
		setResultData(params);
		return SUCCESS;
	}


	/**
	 * 综合统计用
	 * <p>
	 * 方法的说明
	 * </p>
	 * @return
	 */
	public String stat() {

		SecrecyWorkOrgan secrecyWorkOrgan = new SecrecyWorkOrgan();
		secrecyWorkOrgan = secrecyWorkOrganModuleService.getSecrecyWorkOrganByOrgan(organ);
		if (secrecyWorkOrgan == null) {
			// 不存在保密工作机构
			return "stat_no";
		} else {
			if(organ == null || ("").equals(organ.getOrganId())){
				organ = this.getCurrentUser().getOrgan();
			}else {
				organ = organService.get(organ.getOrganId());
			}
			putToRequest("organ", organ);

			// 得到保密办成员列表
			List<SecrecyWorkOrganMemberUnit> secrecyWorkOrganMemberUnitList = new ArrayList<SecrecyWorkOrganMemberUnit>();
			secrecyWorkOrganMemberUnitList = secrecyWorkOrganMemberUnitModuleService.getPageList(secrecyWorkOrgan,organ, PERSON_CHANGE_NOW);
			putToRequest("secrecyWorkOrganMemberUnitList", secrecyWorkOrganMemberUnitList);

			// 得到保密机构成员列表
			List<SecrecyWorkOrganRelationMember> secrecyWorkOrganRelationMemberList = new ArrayList<SecrecyWorkOrganRelationMember>();
			secrecyWorkOrganRelationMemberList = secrecyWorkOrganRelationMemberModuleService.getPageList(secrecyWorkOrgan,organ,PERSON_CHANGE_NOW);
			putToRequest("secrecyWorkOrganRelationMemberList", secrecyWorkOrganRelationMemberList);
			secrecyWorkOrgan.setEstablishmentNum(secrecyWorkOrganRelationMemberList == null ? 0 : secrecyWorkOrganRelationMemberList.size());
		}

		// 得到附件
		putToRequest("attachments", attachmentService.getAttachmentsByHostId(secrecyWorkOrgan.getSecrecyWorkOrganId()));
		putToRequest("secrecyWorkOrgan", secrecyWorkOrgan);

		return "stat";
	}



	// ******************** Setter & Getter ********************
	// 服务 Setter & Getter
	/**
	 * @return 返回district
	 */
	public District getDistrict() {
		return district;
	}

	/**
	 * @param district 设置district
	 */
	public void setDistrict(District district) {
		this.district = district;
	}

	/**
	 * @return 返回organ
	 */
	public Organ getOrgan() {
		return organ;
	}

	/**
	 * @param organ 设置organ
	 */
	public void setOrgan(Organ organ) {
		this.organ = organ;
	}
	public void setSecrecyCommitteeModuleService(SecrecyCommitteeModuleService secrecyCommitteeModuleService) {
		this.secrecyCommitteeModuleService = secrecyCommitteeModuleService;
	}
	public void setSecrecyCommittee(SecrecyCommittee secrecyCommittee) {
		this.secrecyCommittee = secrecyCommittee;
	}

	// 字段 Setter & Getter
	public SecrecyCommittee getSecrecyCommittee() {
		return secrecyCommittee;
	}
	public SecrecyCommitteeModuleService getSecrecyCommitteeModuleService() {
		return secrecyCommitteeModuleService;
	}

	public List<SecrecyCommittee> getSecrecyCommitteeList() {
		return secrecyCommitteeList;
	}
	public void setSecrecyCommitteeList(List<SecrecyCommittee> secrecyCommitteeList) {
		this.secrecyCommitteeList = secrecyCommitteeList;
	}

	public String getSecrecyCommitteeIds() {
		return secrecyCommitteeIds;
	}
	public void setSecrecyCommitteeIds(String secrecyCommitteeIds) {
		this.secrecyCommitteeIds = secrecyCommitteeIds;
	}

	public Boolean getNeedReload() {
		return needReload;
	}
	public void setNeedReload(Boolean needReload) {
		this.needReload = needReload;
	}

	public SecrecyCommitteeMemberModuleService getSecrecyCommitteeMemberModuleService() {
		return secrecyCommitteeMemberModuleService;
	}

	public void setSecrecyCommitteeMemberModuleService(
			SecrecyCommitteeMemberModuleService secrecyCommitteeMemberModuleService) {
		this.secrecyCommitteeMemberModuleService = secrecyCommitteeMemberModuleService;
	}

	public List<SecrecyCommitteeMember> getSecrecyCommitteeMemberList() {
		return secrecyCommitteeMemberList;
	}

	public void setSecrecyCommitteeMemberList(List<SecrecyCommitteeMember> secrecyCommitteeMemberList) {
		this.secrecyCommitteeMemberList = secrecyCommitteeMemberList;
	}

	public AttachmentService getAttachmentService() {
		return attachmentService;
	}

	public void setAttachmentService(AttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}

	public List<String> getAttachmentIds() {
		return attachmentIds;
	}

	public void setAttachmentIds(List<String> attachmentIds) {
		this.attachmentIds = attachmentIds;
	}

	public List<Attachment> getAttachmentList() {
		return attachmentList;
	}

	public void setAttachmentList(List<Attachment> attachmentList) {
		this.attachmentList = attachmentList;
	}

	/**
	 * @param secrecyCommitteeExchanger 设置secrecyCommitteeExchanger
	 */
	public void setSecrecyCommitteeExchanger(
			SecrecyCommitteeExchanger secrecyCommitteeExchanger) {
		this.secrecyCommitteeExchanger = secrecyCommitteeExchanger;
	}

	/**
	 * @param serverReportMappingService 设置serverReportMappingService
	 */
	public void setServerReportMappingService(
			ServerReportMappingService serverReportMappingService) {
		this.serverReportMappingService = serverReportMappingService;
	}

	/**
	 * @return 返回isShow
	 */
	public boolean isShow() {
		return isShow;
	}

	/**
	 * @param isShow 设置isShow
	 */
	public void setShow(boolean isShow) {
		this.isShow = isShow;
	}

	/**
	 * @param dataSource 设置dataSource
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public PersonGroupService getPersonGroupService() {
		return personGroupService;
	}

	public void setPersonGroupService(PersonGroupService personGroupService) {
		this.personGroupService = personGroupService;
	}
	public PersonGroupRelationService getPersonGroupRelationService() {
		return personGroupRelationService;
	}
	public void setPersonGroupRelationService(
			PersonGroupRelationService personGroupRelationService) {
		this.personGroupRelationService = personGroupRelationService;
	}
	public OrganService getOrganService() {
		return organService;
	}
	public SecrecyWorkOrganModuleService getSecrecyWorkOrganModuleService() {
		return secrecyWorkOrganModuleService;
	}
	public SecrecyWorkOrganMemberUnitModuleService getSecrecyWorkOrganMemberUnitModuleService() {
		return secrecyWorkOrganMemberUnitModuleService;
	}

	public void setSecrecyWorkOrganMemberUnitModuleService(
			SecrecyWorkOrganMemberUnitModuleService secrecyWorkOrganMemberUnitModuleService) {
		this.secrecyWorkOrganMemberUnitModuleService = secrecyWorkOrganMemberUnitModuleService;
	}

	public SecrecyWorkOrganRelationMemberModuleService getSecrecyWorkOrganRelationMemberModuleService() {
		return secrecyWorkOrganRelationMemberModuleService;
	}

	public void setSecrecyWorkOrganRelationMemberModuleService(
			SecrecyWorkOrganRelationMemberModuleService secrecyWorkOrganRelationMemberModuleService) {
		this.secrecyWorkOrganRelationMemberModuleService = secrecyWorkOrganRelationMemberModuleService;
	}
	public void setSecrecyWorkOrganModuleService(
			SecrecyWorkOrganModuleService secrecyWorkOrganModuleService) {
		this.secrecyWorkOrganModuleService = secrecyWorkOrganModuleService;
	}
	public void setOrganService(OrganService organService) {
		this.organService = organService;
	}
}