package com.cdthgk.bmp.externalpidgin.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.cdthgk.agenda.vo.Agenda;
import com.cdthgk.bmp.core.action.BmpAction;
import com.cdthgk.bmp.externalpidgin.dto.ExternalPidginDto;
import com.cdthgk.bmp.externalpidgin.service.ExternalPidginService;
import com.cdthgk.bmp.externalpidgin.vo.ExternalPidgin;
import com.cdthgk.bmp.log.context.BusinessLogContext;
import com.cdthgk.bmp.log.vo.BusinessLog;
import com.cdthgk.common.bean.BeanUtils;
import com.cdthgk.common.bean.rule.CopyRuleEnum;
import com.cdthgk.platform.attachment.domain.Attachment;
import com.cdthgk.platform.attachment.service.AttachmentService;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;

/**
 * @description 涉密涉外活动Action.
 * @author 王欢 2010 1 7 12:34:56
 * @modify 陈文聪 2010 8 19 02:10:40 修改注释格式
 */

@SuppressWarnings("all")
public class ExternalPidginAction extends BmpAction {

	// Fields

	private ExternalPidgin externalPidgin;
	private ExternalPidginService externalPidginService;
	private ExternalPidginDto externalPidginDto;
	private List<ExternalPidgin> externalPidginList;
	//private InitParamsService initParamsService;
	private Integer secrecyLevel;
	private Organ organ;
	// 上传
	private Attachment attachment;
	private List<Attachment> attachmentList;
	private List<String> attachments;
	//private UpLoadedFiles upLoadFiles;
	private AttachmentService attachmentService;
	private String countType;
	private String startTime;
	private String endTime;
	// 单位名称
	private String organName;
	private String organNames;

	//private IDistrictService districtService;
	private District district;
	private String showType;
	//private StatService statService;
	private String externalPidginIds;
	private String reportState;

	private Boolean needReload = false;

	//private OrganService organService;
	//OrganizationContext.getInstance().getOrganService().get(organId))
	// 联系人组件
	//private ModuleContactService moduleContactService;
	//private ModuleContact moduleContact;

	/**
	 * @return the reportState
	 */
	public String getReportState() {
		return reportState;
	}

	/**
	 * @param reportState
	 *            the reportState to set
	 */
	public void setReportState(String reportState) {
		this.reportState = reportState;
	}

	/**
	 * @description 取得EC LIST 显示列表
	 * @author 王欢 2010 1 7 12:34:56
	 * @modify 陈文聪 2010 8 19 02:12:35 修改注释格式.
	 * @throws ParseException
	 */
	public String list() throws ParseException {

		PageSortModel psm = new PageSortModel(getRequest(),
				"externalPidginList");
		putToRequest("externalPidginList",
				externalPidginService.listForEc(psm, externalPidginDto, getCurrentUser()));
		return "success";
	}

	/**
	 * @description 跳转到增加页面
	 * @author 王欢 2010 1 7 12:34:56
	 * @modify 陈文聪 2010 8 19 02:12:52 修改注释格式.
	 */
	public String add() {
		return "success";
	}

	/**
	 * 主页涉密涉外活动统计
	 *
	 * @return 物理视图
	 */
	public String indexView() {
		/*CurrentOrganStatDto cosDto = new CurrentOrganStatDto();
		Organ organ = getCurrentUser().getOrgan();
		putToRequest("cosDto",
				statService.countExternalPidginData(organ, cosDto));
		return SUCCESS;*/
		return null;
	}

	/**
	 * @description 保存一条数据
	 * @author 王欢 2010 1 7 12:34:56
	 * @modify 陈文聪 2010 8 19 02:13:00 修改注释格式.
	 */
	public String save() {
		externalPidgin.setCreatePerson(getCurrentUser());
		externalPidgin.setCreateTime(new Date());
		externalPidgin.setModifyPerson(getCurrentUser());
		externalPidgin.setModifyTime(new Date());

		externalPidgin.setDepartment(getCurrentUser()
				.getUserInfo().getDepartment());
		externalPidgin.setOrgan(getCurrentUser().getOrgan());
		externalPidgin.setReportState(1);
		externalPidginService.save(externalPidgin);

		// 上传附件
		attachmentService.updateHostId(externalPidgin.getExternalPidginId(),attachments);
		needReload = true;
		this.setNeedReload(true);
		addActionMessage("新增涉密涉外活动记录成功。");
		BusinessLog log=new BusinessLog();
		log.setBusinessName("主要涉密涉外活动备案");
		log.setPrimaryKey(externalPidgin.getExternalPidginId());
		BusinessLogContext.getInstance().getBusinessLogService().saveAddBusinessLogByModule(getCurrentUser(), log, externalPidgin);
		return "success";
	}

	/**
	 * @description 跳转到编辑页面
	 * @author 王欢 2010 1 7 12:34:56
	 * @modify 陈文聪 2010 8 19 02:13:11 修改注释格式.
	 */
	@SuppressWarnings("unchecked")
	public String edit() {
		externalPidgin = externalPidginService.get(getId());
		return SUCCESS;
	}

	/**
	 * @description 更新一条记录
	 * @author 王欢 2010 1 7 12:34:56
	 * @modify 陈文聪 2010 8 19 02:13:21 修改注释格式.
	 */
	public String update() {
		ExternalPidgin ep = externalPidginService.get(externalPidgin
				.getExternalPidginId());
		ExternalPidgin beforeep=new ExternalPidgin();
		BeanUtils.copyProperties(beforeep,ep,CopyRuleEnum.ignoreCaseNull);
		ep.setAidanceOrgan(externalPidgin.getAidanceOrgan());
		ep.setContent(externalPidgin.getContent());
		ep.setDutierHeadship(externalPidgin.getDutierHeadship());
		ep.setEndDate(externalPidgin.getEndDate());
		ep.setEternalPidginName(externalPidgin.getEternalPidginName());
		ep.setExternalPidginNo(externalPidgin.getExternalPidginNo());
		ep.setExternalPidginType(externalPidgin.getExternalPidginType());
		ep.setUndertaker(externalPidgin.getUndertaker());
		ep.setStartDate(externalPidgin.getStartDate());
		ep.setSecrecyLevel(externalPidgin.getSecrecyLevel());
		ep.setSecrecyDutier(externalPidgin.getSecrecyDutier());
		ep.setPlace(externalPidgin.getPlace());
		ep.setKeyWord(externalPidgin.getKeyWord());
		ep.setMainOrgan(externalPidgin.getMainOrgan());
		ep.setModifyTime(new Date());
		ep.setModifyPerson(getCurrentUser());
		externalPidginService.update(ep);
		// 编辑附件
		attachmentService.updateHostId(externalPidgin.getExternalPidginId(),attachments);

		needReload = true;
		this.setNeedReload(true);
		addActionMessage("编辑涉密涉外活动记录成功。");
		BusinessLog log = new BusinessLog();
		log.setBusinessName("主要涉密涉外活动备案");
		log.setPrimaryKey(ep.getExternalPidginId());
		BusinessLogContext.getInstance().getBusinessLogService().saveEditBusinessLogByModule(getCurrentUser(), log, ep, beforeep);
		return "success";
	}

	/**
	 * @description 下载附件
	 * @author 王欢 2010 1 7 12:34:56
	 * @modify 陈文聪 2010 8 19 02:13:38 修改注释格式.
	 * @throws ParseException
	 */
	public String download() throws ParseException {
		this.attachment = (Attachment) externalPidginService.get(
				attachment.getAttachId(), Attachment.class);
		putToRequest("attachment", attachment);
		return "ftpDown";
	}

	/**
	 * @description 删除下载
	 * @author 王欢 2010 1 7 12:34:56
	 * @modify 陈文聪 2010 8 19 02:13:50 修改注释格式.
	 */
	@SuppressWarnings("unchecked")
	public String deleteAttachment() {
		// FIXME 从未使用
		@SuppressWarnings("unused")
		HttpServletRequest request = ServletActionContext.getRequest();
//		attachementService.deleteAttachmentById(attachment.getAttachId());
//		attachmentList = attachementService
//				.getAttachmentByHostId(externalPidgin.getExternalPidginId());
		putToRequest("attachmentList", attachmentList);
		return "toAttachmentAjax";
	}

	/**
	 * @description 删除方法
	 * @author 王欢 2010 1 7 12:34:56
	 * @modify 陈文聪 2010 8 19 02:13:59 修改注释格式.
	 */
	public String del() {
		externalPidginService.deleteBatchIdList(getIds());
		addActionMessage("删除涉密涉外活动记录成功。");
		for (String id : getIds()) {
			BusinessLog log = new BusinessLog();
			log.setBusinessName("主要涉密涉外活动备案");
			log.setPrimaryKey(id);
			BusinessLogContext.getInstance().getBusinessLogService().saveDelBusinessLogByModule(getCurrentUser(), log, new ExternalPidgin());
		}
		return "success";
	}

	/**
	 * 上报
	 */
	public String report() {
		/*TransmitMessage transmitMessage = new TransmitMessage();
		if (externalPidginIds != null && !"".equals(externalPidginIds)) {
			// List<String> organIdList =
			// initParamsService.getReportToOragans();
			List<String> organIdList = initParamsService.getReportToOragans(GlobalSysInfo.getCurrentUser()
							.getOrgan());
			Iterator<String> i = organIdList.iterator();
			while (i.hasNext()) {
				String organId = i.next();
				transmitMessage = this.externalPidginService.reportToOrgan(
						Arrays.asList(externalPidginIds.split(",")),
						GlobalSysInfo.getCurrentUser().getOrgan(), organId);
			}
		}
		if (transmitMessage != null) {
			addActionMessage(transmitMessage.getMessage());
		}
		Map<String, String> message = new HashMap<String, String>();
		message.put("message", "上报成功");
		setOutData(message);*/
		return JSON;
	}
	/**
	 * 查询页上报
	 */
	public String listReport() {
		/*TransmitMessage transmitMessage = null;
		if (externalPidginIds != null && !"".equals(externalPidginIds)) {
			// List<String> organIdList =
			// initParamsService.getReportToOragans();
			List<String> organIdList = initParamsService.getReportToOragans(GlobalSysInfo.getCurrentUser()
							.getOrgan());
			Iterator<String> i = organIdList.iterator();
			while (i.hasNext()) {
				String organId = i.next();
				transmitMessage = this.externalPidginService.reportToOrgan(
						Arrays.asList(externalPidginIds.split(",")),
						GlobalSysInfo.getCurrentUser().getOrgan(), organId);
			}
		}
		if (transmitMessage != null) {
			addActionMessage(transmitMessage.getMessage());
		}
			Map<String, String> message = new HashMap<String, String>();
			message.put("message", "上报成功");
			setOutData(message);*/
			return JSON;
		}

	/**
	 * @description 详细方法
	 * @author 王欢 2010 1 7 12:34:56
	 * @modify 陈文聪 2010 8 19 02:14:08 修改注释格式.
	 */
	@SuppressWarnings("unchecked")
	public String detail() {
		externalPidgin = externalPidginService.get(getId());
		attachmentList = attachmentService.getAttachmentsByHostId(getId());
		return "success";
	}

	/**
	 * <p>
	 * 行政区划树
	 * </p>
	 *
	 * @author taohy 2011-9-30 上午09:57:28
	 * @return String 物理视图
	 */
	public String checkMain() {
		PageSortModel psm = new PageSortModel(getRequest(),
				"externalPidginList");
		List<District> districtList = null;//districtService.findPageByCriteria(psm, district, null);
		putToRequest("districtList", districtList);
		return "checkMain";
	}

	/**
	 * @description 当前单位所组织的涉密涉外活动
	 * @author 王欢 2010 1 7 12:34:56
	 * @modify 陈文聪 2010 8 19 02:14:16 修改注释格式.
	 * @modify taohy 2011 9 30 02:14:16 修改按行政区划查询.
	 */
	@SuppressWarnings("unchecked")
	public String epList() {
		PageSortModel pageSortModel = new PageSortModel(
				this.getRequest(), "externalPidginList");
		if (showType == null || "".equals(showType)) {
			showType = "0";
		}
		if (district == null || "".endsWith(district.getDistrictCode())) {
			district = externalPidginService.get(getCurrentUser().getOrgan()
					.getDistrict().getDistrictCode(), District.class);
		} else {
			district = externalPidginService.get(district.getDistrictCode(), District.class);
		}
		externalPidginList = externalPidginService
				.getDistrictExternalPidginList(pageSortModel, district,
						showType,getCurrentUser().getOrgan());
		return "success";
	}

	/**
	 * @description 查看统计单位组织的涉密涉外活动
	 * @author 王欢 2011 5-25 11:12:56
	 */
	@SuppressWarnings("unchecked")
	public String organAllEpList() {
		String tableId = "externalPidginList";
		PageSortModel pageSortModel = new PageSortModel(this.getRequest(), tableId);

		Organ organ = getCurrentUser().getOrgan();

		if (externalPidginDto != null) {
			externalPidginList = externalPidginService.getDataAllPageList(
					organNames, pageSortModel, externalPidgin,
					externalPidginDto, showType, countType, organ);
		} else {
			if (showType == null || "".equals(showType)) {
				showType = "0";
			}
			externalPidginList = externalPidginService.getAllPageList(
					pageSortModel, externalPidgin, showType, organ);
		}
		return "organAllEpList";
	}

	/**
	 * @description 选项卡
	 * @author liuyf 2011 5 25 10:46:56
	 */
	public String gotab0007() {
		return SUCCESS;
	}

	/**
	 * @description 选项卡
	 * @author liuyf 2011 5 25 10:46:56
	 */
	public String gotab0002() {
		return SUCCESS;
	}

	/**
	 *
	 * <p>
	 * 涉密涉外活动统计
	 * </p>
	 * <p>
	 * 创建人 创建时间 2010-8-23 - 下午05:31:08
	 * </p>
	 * <blockquote> <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 *
	 * @return
	 */

	public String countExternalPidgin() {
		if (countType == null) {
			countType = "1";
		}
		ServletActionContext.getRequest().getSession()
				.setAttribute("countType", countType);
		if (startTime == null)
			startTime = new SimpleDateFormat("yyyy").format(new Date())
					+ "-01-01";
		if (endTime == null)
			endTime = new SimpleDateFormat("yyyy").format(new Date())
					+ "-12-31";

		List<Object[]> list = externalPidginService.countExternalPidgin(
				countType, startTime, endTime, organName);
		this.putToRequest("list", list);
		return "countExternalPidgin";
	}

	/**
	 *
	 * <p>
	 * 管辖单位涉密涉外活动统计
	 * </p>
	 * <p>
	 * 创建人 liuyf 创建时间 2011-5-26 - 09:31
	 * </p>
	 * <blockquote> <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 *
	 * @return countAllOrgan
	 */
	public String countAllOrgan() {
		if (countType == null) {
			countType = "1";
		}
		ServletActionContext.getRequest().getSession()
				.setAttribute("countType", countType);
		if (startTime == null) {
			startTime = new SimpleDateFormat("yyyy").format(new Date())
					+ "-01-01";
		}
		if (endTime == null) {
			endTime = new SimpleDateFormat("yyyy").format(new Date())
					+ "-12-31";
		}

		List<Object[]> list = externalPidginService.countAllOrgan(countType,
				startTime, endTime, organName);
		this.putToRequest("list", list);
		return "countAllOrgan";
	}
	/**
	 * 单位统计详情
	 * @author 刘椿成
	 * *</p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * 2012 8 22 19:05:54 修改注释格式.
	 */
	public String checkdetail(){
		String id = getId();
		organ= externalPidginService.get(id, Organ.class);
		PageSortModel psm = new PageSortModel(getRequest(), "externalPidginList");
		//String districtCode = getRequestValue().getParameter("district.districtCode");
		String districtCode = getRequest().getParameter("district.districtCode");
		externalPidginList=externalPidginService.getForPage(psm, organ,secrecyLevel,districtCode);
		this.putToRequest("externalPidginList", externalPidginList);

		return "checkdetail";
	}

	/**
	 * 首页涉密涉外活动单位统计详情
	 * @author 刘椿成
	 * *</p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * 2012 8 29 10:45:54 修改注释格式.
	 */
	public String organExtPidginData(){
		organ= getCurrentUser().getOrgan();
		PageSortModel psm = new PageSortModel(getRequest(),
		"externalPidginList");
		String districtCode = organ.getDistrict().getDistrictCode();
		externalPidginList=externalPidginService.getIndexPage(psm, organ, secrecyLevel, districtCode);
		this.putToRequest("externalPidginList", externalPidginList);

		return "organExtPidginData";
	}
	/**
	 * 首页涉密涉外活动统计查询详情
	 * @author 刘椿成
	 * *</p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * 2012 8 29 10:45:54 修改注释格式.
	 */
	public String extPidginQueryData(){
		organ= getCurrentUser().getOrgan();
		PageSortModel psm = new PageSortModel(getRequest(),
		"externalPidginList");
		String districtCode = organ.getDistrict().getDistrictCode();

		String externalPidginName = getRequest().getParameter("externalPidgin.externalPidginName").toString();
		externalPidgin.setEternalPidginName(externalPidginName);
		externalPidginList=externalPidginService.getIndexPage(psm,externalPidgin, organ, districtCode);
		this.putToRequest("externalPidginList", externalPidginList);

		return "extPidginQueryData";
	}
	// geter & seter

	public ExternalPidgin getExternalPidgin() {
		return externalPidgin;
	}

	public void setExternalPidgin(ExternalPidgin externalPidgin) {
		this.externalPidgin = externalPidgin;
	}

	public ExternalPidginService getExternalPidginService() {
		return externalPidginService;
	}

	public void setExternalPidginService(
			ExternalPidginService externalPidginService) {
		this.externalPidginService = externalPidginService;
	}

	public List<ExternalPidgin> getExternalPidginList() {
		return externalPidginList;
	}

	public void setExternalPidginList(List<ExternalPidgin> externalPidginList) {
		this.externalPidginList = externalPidginList;
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

	/**
	 * @return the organNames
	 */
	public String getOrganNames() {
		return organNames;
	}

	/**
	 * @param organNames
	 *            the organNames to set
	 */
	public void setOrganNames(String organNames) {
		this.organNames = organNames;
	}



	/*public UpLoadedFiles getUpLoadFiles() {
		return upLoadFiles;
	}

	public void setUpLoadFiles(UpLoadedFiles upLoadFiles) {
		this.upLoadFiles = upLoadFiles;
	}*/

	public ExternalPidginDto getExternalPidginDto() {
		return externalPidginDto;
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

	public void setExternalPidginDto(ExternalPidginDto externalPidginDto) {
		this.externalPidginDto = externalPidginDto;
	}

	public String getCountType() {
		return countType;
	}

	public void setCountType(String countType) {
		this.countType = countType;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getOrganName() {
		return organName;
	}

	public void setOrganName(String organName) {
		this.organName = organName;
	}

	public String main() {
		return "main";
	}

	public String getShowType() {
		return showType;
	}

	public void setShowType(String showType) {
		this.showType = showType;
	}

	/*public IDistrictService getDistrictService() {
		return districtService;
	}

	public void setDistrictService(IDistrictService districtService) {
		this.districtService = districtService;
	}*/

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	/**
	 * @return 返回statService
	 */
//	public StatService getStatService() {
//		return statService;
//	}

	/**
	 * @param statService
	 *            设置statService
	 */
//	public void setStatService(StatService statService) {
//		this.statService = statService;
//	}

	/**
	 * @return the externalPidginIds
	 */
	public String getExternalPidginIds() {
		return externalPidginIds;
	}

	/**
	 * @param externalPidginIds
	 *            the externalPidginIds to set
	 */
	public void setExternalPidginIds(String externalPidginIds) {
		this.externalPidginIds = externalPidginIds;
	}

/*	public void setInitParamsService(InitParamsService initParamsService) {
		this.initParamsService = initParamsService;
	}*/

	/**
	 * @return 返回secrecyLevel
	 */
	public Integer getSecrecyLevel() {
		return secrecyLevel;
	}

	/**
	 * @param secrecyLevel 设置secrecyLevel
	 */
	public void setSecrecyLevel(Integer secrecyLevel) {
		this.secrecyLevel = secrecyLevel;
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

	/**
	 * @return the needReload
	 */
	public Boolean getNeedReload() {
		return needReload;
	}

	/**
	 * @param needReload the needReload to set
	 */
	public void setNeedReload(Boolean needReload) {
		this.needReload = needReload;
	}

	/**
	 * @return the attachments
	 */
	public List<String> getAttachments() {
		return attachments;
	}

	/**
	 * @param attachments the attachments to set
	 */
	public void setAttachments(List<String> attachments) {
		this.attachments = attachments;
	}



	/*public OrganService getOrganService() {
		return organService;
	}

	public void setOrganService(OrganService organService) {
		this.organService = organService;
	}*/

//	public ModuleContactService getModuleContactService() {
//		return moduleContactService;
//	}
//
//	public void setModuleContactService(ModuleContactService moduleContactService) {
//		this.moduleContactService = moduleContactService;
//	}
//
//	public ModuleContact getModuleContact() {
//		return moduleContact;
//	}
//
//	public void setModuleContact(ModuleContact moduleContact) {
//		this.moduleContact = moduleContact;
//	}

}
