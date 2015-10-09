package com.cdthgk.checkevent.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.action.BmpAction;
import com.cdthgk.bmp.log.context.BusinessLogContext;
import com.cdthgk.bmp.log.vo.BusinessLog;
import com.cdthgk.bmp.statinfo.dto.QueryDto;
import com.cdthgk.checkevent.service.SecrecyCheckEventService;
import com.cdthgk.checkevent.vo.SecrecyCheckEvent;
import com.cdthgk.common.bean.BeanUtils;
import com.cdthgk.common.lang.CollectionUtils;
import com.cdthgk.platform.attachment.context.AttachmentContext;
import com.cdthgk.platform.attachment.domain.Attachment;
import com.cdthgk.platform.dataValidate.service.DataValidateService;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.district.service.DistrictService;
import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.organ.service.OrganService;
import com.cdthgk.setTheDecryption.vo.SetTheDecryption;

import ec.common.PageSortModel;

@SuppressWarnings("all")
/**
 * @ 类用途 @ 创建人 杨成 @ 创建时间 Oct 12, 2009 - 9:43:07 AM @ 修改人 @ 修改时间 @ 修改描述 @ 公司名称 @ 当前系统主版本号
 */
public class SecrecyCheckEventAction extends BmpAction {
	private SecrecyCheckEventService secrecyCheckEventService;
	// 目标条目分类
	private SecrecyCheckEvent secrecyCheckEvent;
	private DataValidateService dataValidateService;
	private String tableIds;
	private Attachment attachment;
	List<Attachment> attachmentList;
	private String showType;
	private District district;
	private DistrictService districtService;

	private OrganService organService;

	/**
	 * 根据所点击树节点的id来得到该树下的字节点及本身节点
	 */
	public String list() {

		Organ organ = getCurrentUser().getOrgan();
		PageSortModel psm = new PageSortModel(getRequest(), id);
		List list = (List) secrecyCheckEventService.listForEc(psm,
				secrecyCheckEvent, organ);
		this.putToRequest("list", list);
		return SUCCESS;
	}

	public String add() {
		return SUCCESS;
	}

	public String edit() {
		secrecyCheckEvent = secrecyCheckEventService.get(secrecyCheckEvent
				.getSecrecyCheckEventId());
		attachmentList = AttachmentContext
				.getInstance()
				.getAttachmentService()
				.getAttachmentsByHostId(
						secrecyCheckEvent.getSecrecyCheckEventId());
		if (secrecyCheckEvent.getCheckedDepartment() != null) {
			String[] strings = secrecyCheckEvent.getCheckedDepartment().split(
					",");
			List<Department> list = new ArrayList<Department>();
			for (int i = 0; i < strings.length; i++) {
				Department department = secrecyCheckEventService.get(
						strings[i].trim(), Department.class);
				list.add(department);

			}
			putToRequest("checkedDepartment", list);
		}

		return SUCCESS;
	}

	private List<String> secAttach;
	private boolean needReload = false;

	public boolean isNeedReload() {
		return needReload;
	}

	public void setNeedReload(boolean needReload) {
		this.needReload = needReload;
	}

	public List<String> getSecAttach() {
		return secAttach;
	}

	public void setSecAttach(List<String> secAttach) {
		this.secAttach = secAttach;
	}

	public String save() {
		secrecyCheckEvent.setOrganId(getCurrentUser().getOrgan());
		secrecyCheckEvent.setCreateTime(new Date());
		secrecyCheckEvent.setCreatePerson(getCurrentUser());
		secrecyCheckEventService.save(secrecyCheckEvent);
		AttachmentContext
				.getInstance()
				.getAttachmentService()
				.updateHostId(secrecyCheckEvent.getSecrecyCheckEventId(),
						secAttach);
		addActionMessage("添加成功!");
		needReload = true;
		BusinessLog log = new BusinessLog();
		log.setBusinessName("保密检查工作");
		log.setPrimaryKey(secrecyCheckEvent.getSecrecyCheckEventId());
		BusinessLogContext
				.getInstance()
				.getBusinessLogService()
				.saveAddBusinessLogByModule(getCurrentUser(), log,
						secrecyCheckEvent);
		return redirectActionResult(LIST);
	}

	public String update() {
		Department department = new Department();
		SecrecyCheckEvent oldSce = secrecyCheckEventService
				.get(secrecyCheckEvent.getSecrecyCheckEventId());
		secrecyCheckEvent.setOrganId(getCurrentUser().getOrgan());
		secrecyCheckEventService.updateEvent(secrecyCheckEvent, tableIds,
				getCurrentUser());
		AttachmentContext
				.getInstance()
				.getAttachmentService()
				.updateHostId(secrecyCheckEvent.getSecrecyCheckEventId(),
						secAttach);
		addActionMessage("修改成功!");
		BusinessLog log = new BusinessLog();
		log.setBusinessName("保密检查工作");
		log.setPrimaryKey(secrecyCheckEvent.getSecrecyCheckEventId());
		BusinessLogContext
				.getInstance()
				.getBusinessLogService()
				.saveEditBusinessLogByModule(getCurrentUser(), log,
						secrecyCheckEvent, oldSce);
		needReload = true;
		return redirectActionResult(LIST);
	}

	public String updateTable() {
		SecrecyCheckEvent oldSce = secrecyCheckEventService
				.get(secrecyCheckEvent.getSecrecyCheckEventId());
		secrecyCheckEventService.updateEvent(secrecyCheckEvent, tableIds,
				getCurrentUser());
		AttachmentContext
				.getInstance()
				.getAttachmentService()
				.updateHostId(secrecyCheckEvent.getSecrecyCheckEventId(),
						secAttach);
		addActionMessage("修改成功!");
		BusinessLog log = new BusinessLog();
		log.setBusinessName("保密检查工作");
		log.setPrimaryKey(secrecyCheckEvent.getSecrecyCheckEventId());
		BusinessLogContext
				.getInstance()
				.getBusinessLogService()
				.saveEditBusinessLogByModule(getCurrentUser(), log,
						secrecyCheckEvent, oldSce);
		return redirectActionResult(EDIT);
	}

	public String delete() {
		secrecyCheckEventService.deleteBatchIdList(this.getIds());
		for (String string : getIds()) {
			BusinessLog log = new BusinessLog();
			log.setBusinessName("保密检查工作");
			log.setPrimaryKey(string);
			BusinessLogContext
					.getInstance()
					.getBusinessLogService()
					.saveDelBusinessLogByModule(getCurrentUser(), log,
							new SecrecyCheckEvent());
		}
		addActionMessage("删除成功!");
		return redirectActionResult(LIST);
	}

	public String selectTable() {
		return SUCCESS;
	}

	public String selectTableList() {
		String id = "list";
		PageSortModel psm = new PageSortModel(getRequest(), id);
		/*
		 * CheckTable checkTable = new CheckTable(); List list = (List)
		 * checkTableService.listForEc(psm, checkTable);
		 * this.putToRequest("list", list);
		 */
		return SUCCESS;
	}

	/**
	 * 显示详请
	 *
	 * @return
	 */
	public String detail() {
		// 得到id进行查询
		secrecyCheckEvent = secrecyCheckEventService.get(secrecyCheckEvent
				.getSecrecyCheckEventId());
		attachmentList = AttachmentContext
				.getInstance()
				.getAttachmentService()
				.getAttachmentsByHostId(
						secrecyCheckEvent.getSecrecyCheckEventId());
		if (secrecyCheckEvent.getCheckedDepartment() != null) {
			String[] strings = secrecyCheckEvent.getCheckedDepartment().split(
					",");
			String list = "";
			for (int i = 0; i < strings.length; i++) {
				Department department = secrecyCheckEventService.get(
						strings[i].trim(), Department.class);
				if (i == strings.length - 1) {

					list += department.getDepartmentName();
				} else {
					list += department.getDepartmentName() + "，";
				}

			}
			putToRequest("checkedDepartment", list);
		}

		return SUCCESS;
	}

	public String tabcard() {
		return "success";
	}

	public String main() {
		return SUCCESS;
	}

	/**
	 * @description 查询所有保密检查事件
	 * @author 杨 成 2009-10-12 10:12
	 * @param null
	 */
	public String allList() {
		PageSortModel psm = new PageSortModel(getRequest(), "list");
		if (showType == null || "".equals(showType)) {
			showType = "0";
		}

		if (district == null || "".equals(district.getDistrictCode())) {
			district = new District();
			district = getCurrentUser().getOrgan().getDistrict();
		}
		if (district != null && "".endsWith(district.getDistrictCode())) {
			district = new District();
			district.setDistrictCode(getCurrentUser().getOrgan().getDistrict()
					.getDistrictCode());
		}
		district = secrecyCheckEventService.get(district.getCode(),
				District.class);
		List list = (List) secrecyCheckEventService.allListForEc(psm,
				secrecyCheckEvent, showType, district);
		this.putToRequest("list", list);
		return "allList";
	}

	/**
	 *
	 * <p>
	 * 保密工作信息总览获取数据
	 * </p>
	 * <p>
	 * 创建人 陶汇源 创建时间 2014-5-7 - 下午2:28:35
	 * </p>
	 * <blockquote> <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * <li>宋亚非 2014-05-15 创建
	 * </ul>
	 * </blockquote>
	 *
	 * @return
	 */
	public String organIndex() {
		// TODO 暂无考虑时间查询，时间设计到业务模块数据变动历史情况，需要先调整业务模块
		String organId = getRequest().getParameter("organId").toString();
		QueryDto queryDto = new QueryDto();
		queryDto.setYear(Integer.parseInt(getRequest().getParameter(
				"queryDto.year").toString()));
		queryDto.setMonth(Integer.parseInt(getRequest().getParameter(
				"queryDto.month").toString()));
		Organ organ = this.secrecyCheckEventService.get(organId, Organ.class);
		List<SecrecyCheckEvent> secrecyCheckEventlist = secrecyCheckEventService
				.listForEc(null, secrecyCheckEvent, organ);
		putToRequest("secrecyCheckEventlist", secrecyCheckEventlist);
		return SUCCESS;
	}

	public String dataValidate() {
		Map<String, Object> resultData = new HashMap<String, Object>();
		// ID值为bm_business_module中的ID
		resultData.put("id", "23");
		List<SecrecyCheckEvent> secrecyCheckEventlist = secrecyCheckEventService
				.listForEc(null, secrecyCheckEvent, getCurrentUser().getOrgan());
		String msg = dataValidateService.validateData("保密监督检查工作",
				secrecyCheckEventlist, "23");
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

	public SecrecyCheckEventService getSecrecyCheckEventService() {
		return secrecyCheckEventService;
	}

	public void setSecrecyCheckEventService(
			SecrecyCheckEventService secrecyCheckEventService) {
		this.secrecyCheckEventService = secrecyCheckEventService;
	}

	public SecrecyCheckEvent getSecrecyCheckEvent() {
		return secrecyCheckEvent;
	}

	public void setSecrecyCheckEvent(SecrecyCheckEvent secrecyCheckEvent) {
		this.secrecyCheckEvent = secrecyCheckEvent;
	}

	public String getTableIds() {
		return tableIds;
	}

	public void setTableIds(String tableIds) {
		this.tableIds = tableIds;
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
	 * @return the dataValidateService
	 */
	public DataValidateService getDataValidateService() {
		return dataValidateService;
	}

	/**
	 * @param dataValidateService
	 *            the dataValidateService to set
	 */
	public void setDataValidateService(DataValidateService dataValidateService) {
		this.dataValidateService = dataValidateService;
	}

}
