package com.cdthgk.bmp.keyPart.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.cdthgk.bmp.core.action.BmpAction;
import com.cdthgk.bmp.keyPart.service.PartModuleService;
import com.cdthgk.bmp.keyPart.service.PartPersonModuleService;
import com.cdthgk.bmp.keyPart.vo.Part;
import com.cdthgk.bmp.keyPart.vo.PartPerson;
import com.cdthgk.bmp.secrecyperson.service.SecrecyPersonModuleService;
import com.cdthgk.bmp.secrecyperson.vo.SecrecyPerson;
import com.cdthgk.common.lang.LangUtils;
import com.cdthgk.platform.attachment.service.AttachmentService;
import com.cdthgk.platform.base.util.ReportState;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;
import com.cdthgk.platform.permission.user.domain.User;
import ec.common.PageSortModel;

/**
 * <p>
 * PartAction.java 要害部位控制类
 * </p>
 * <blockquote>
 * <h4>历史修改记录</h4>
 * <ul>
 * * 刘椿成 2013-1-06 13:26:59 加入注释
 * </ul>
 * </blockquote>
 * <p>
 * copyright wpb 2012, all rights reserved.
 * </p>
 * @author 王彭波
 * @author wpb
 * @since 1.0
 * @version 1.0
 */
public class NestedPartAction extends BmpAction {

	private static final long serialVersionUID = 884065922675229699L;

	private PartModuleService partModuleService;
	private Part part;
	private String partIds;
	private String departmentId;
	private Integer secrecyLevel;
	private List<Part> partList;
	private String partPersonIds;
	private Organ organ;
	private District district;
	private String listIds;
	private Boolean needReload = false;
	private Boolean needReload2 = false;
	private UserInfo userInfo;

	// 涉密人员使用字段
	private PartPerson partPerson;
	private List<PartPerson> partPersonList;
	private PartPersonModuleService partPersonModuleService;
	private SecrecyPerson secrecyPerson;
	private SecrecyPersonModuleService secrecyPersonModuleService;
	// 附件使用字段
	private AttachmentService attachmentService;
	private List<String> keyPartAttachs;

	/**
	 * <p>
	 * 获取列表
	 * </p>
	 * <p>
	 * wangpb 2012-12-24 9:26:03
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * <p>
	 * copyright 王彭波 2012, all rights reserved.
	 * </p>
	 *
	 * @author FastCodeingTools
	 * @author 王彭波
	 * @since 1.0
	 * @version 1.0
	 * @return JSON
	 */
	@SuppressWarnings("rawtypes")
	public String list(){
		// 获取list
		PageSortModel psm = new PageSortModel(getRequest(), "partList");
		psm.setPageSize(-1);
		part = new Part();
		Department department = partModuleService.get(departmentId, Department.class);
		part.setDepartment(department);
		putToRequest("partList", partModuleService.getListPage(psm, part, this.getCurrentUser().getOrgan()));
		putToRequest("department", department);
		return "list";
	}

	/**
	 * <p>
	 * 删除操作
	 * </p>
	 * <p>
	 * wangpb 2012-12-24 9:26:03
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * <p>
	 * copyright 王彭波 2012, all rights reserved.
	 * </p>
	 *
	 * @author FastCodeingTools
	 * @author 王彭波
	 * @since 1.0
	 * @version 1.0
	 * @return JSON
	 */
	public String delete() {
		// 检查
		if (LangUtils.isEmpty(partIds) || partIds.equals(",")) {
			this.addActionMessage("您所访问的资源Id不存在，请确认后重试。");
			return this.redirectActionResult("list");
		}

		// 删除
		partModuleService.deleteBatchIds(partIds);
		// 设置信息
		this.addActionMessage(getMessageConstant().getDeleteSuccess());
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("message", "删除成功!");
		resultMap.put("success", "true");
		// 将设定值返回给页面
		this.setResultData(resultMap);
		return JSON;
	}

	/**
	 * <p>
	 * 到新增页面
	 * </p>
	 * <p>
	 * 王彭波 2012-12-14 10:43:03
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
		putToRequest("organ", getCurrentUser().getOrgan());
		Department department = new Department();
		if (LangUtils.isNotEmpty(departmentId)) {
			// 获取传入部门信息
			department = partPersonModuleService.get(departmentId, Department.class);
		}
		putToRequest("department", department);
		return "add";
	}

	/**
	 * <p>
	 * 新增操作
	 * </p>
	 * <p>
	 * 王彭波 2012-12-14 16:46:11
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * <p>
	 * </p>
	 *
	 * @author FastCodeingTools
	 * @author 王彭波
	 * @since 1.0
	 * @version 1.0
	 */
	@SuppressWarnings("rawtypes")
	public String adding() {
		// 传入的字段
		Part partTemp = new Part();
		partTemp.setPartName(part.getPartName());
		partTemp.setLocation(part.getLocation());
		partTemp.setManagerRule(part.getManagerRule());
		partTemp.setSecScope(part.getSecScope());
		partTemp.setSecrecyLevel(part.getSecrecyLevel());
		partTemp.setSkillMeasure(part.getSkillMeasure());
		partTemp.setPhone(part.getPhone());
		// 设置通用字段
		partTemp.setCreateperson(getCurrentUser());
		partTemp.setCreateTime(new Date());
		partTemp.setOrgan(getCurrentUser().getUserInfo().getOrgan());

		User user = getCurrentUser();
		//设置部门
		if (part.getDepartment() != null && LangUtils.isNotEmpty(part.getDepartment().getDepartmentId())) {
			partTemp.setDepartment(partModuleService.get(part.getDepartment().getDepartmentId(), Department.class));
		} else if (part.getDepartment() != null&& LangUtils.isEmpty(part.getDepartment().getDepartmentId())  && LangUtils.isNotEmpty(part.getDepartment().getDepartmentName())) {
			Department department = partModuleService.saveDepartment(part.getDepartment().getDepartmentName(),user);
			partTemp.setDepartment(department);
		}

		//department = partModuleService.get(part.getDepartment().getDepartmentName(), Department.class);
		//partTemp.getDepartments().add(department);


		//设置负责人
		if (part.getPerson() != null && LangUtils.isNotEmpty(part.getPerson().getUserInfoId())) {
			partTemp.setPerson(partModuleService.get(part.getPerson().getUserInfoId(), UserInfo.class));
		} else if (part.getPerson() != null && LangUtils.isNotEmpty(part.getPerson().getName())) {
			UserInfo userInfo = partModuleService.saveUserInfo(part.getPerson().getName(),user);
			partTemp.setPerson(userInfo);

		}
		//修改人：宋亚非   修改原因：数据库统一至一个库后，取消上报功能，填写信息后状态即为已上报   修改时间：2013-06-03
		//partTemp.setReportState(ReportState.REPORT_NO);
		partTemp.setReportState(ReportState.REPORT_YES);
		partModuleService.save(partTemp);
		// 设置信息
		this.addActionMessage("新增【" + part.getPartName() + "】成功!");

		part = partModuleService.get(partTemp.getPartId());
		putToRequest("organ", getCurrentUser().getOrgan());
		// 上传附件
		attachmentService.updateHostId(part.getPartId(),keyPartAttachs);

		// 获取指定部位下涉密人员列表及计算涉密人员数量
		PageSortModel psm = new PageSortModel(getRequest(), "partList");
		if (organ == null) {
			organ = getCurrentUser().getOrgan(); // 返回当前登录单位
			}
		else {
			organ = partModuleService.get(getRequest().getParameter("organ.organId").toString(), Organ.class); // 得到指定的单位
			}
		partPersonList = partPersonModuleService.getPersonListPage(psm, part, organ, SECRECY_STATUS_NOW);
		putToRequest("partPersonSize", partPersonList == null ? 0 :partPersonList.size());

		// 得到要害部位附件
		putToRequest("attachments", attachmentService.getAttachmentsByHostId(part.getPartId()));
		needReload = true;
		needReload2 = true;
		return "edit";
	}

	/**
	 * <p>
	 * 到编辑页面
	 * </p>
	 * <p>
	 * 王彭波 2012-12-15 10:43:03
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * <p>
	 * copyright 王彭波 2012, all rights reserved.
	 * </p>
	 *
	 * @author FastCodeingTools
	 * @author 王彭波
	 * @since 1.0
	 * @version 1.0
	 */
	@SuppressWarnings("rawtypes")
	public String edit() {
		// 检查
		if(part==null || part.getPartId()==null || part.getPartId().equals("")){
			this.addActionMessage("您所访问的资源Id不存在，请确认后重试。");
			return "edit";
		}

		part = partModuleService.get(part.getPartId());
		putToRequest("organ", getCurrentUser().getOrgan());

		// 获取指定部位下涉密人员列表及计算涉密人员数量
		PageSortModel psm = new PageSortModel(getRequest(), "partList");
		if (organ == null) {
			organ = getCurrentUser().getOrgan(); // 返回当前登录单位
		}
		else {
			organ = partModuleService.get(getRequest().getParameter("organ.organId").toString(), Organ.class); // 得到指定的单位
		}
		partPersonList = partPersonModuleService.getPersonListPage(psm, part, organ, SECRECY_STATUS_NOW);
		putToRequest("partPersonSize", partPersonList == null ? 0 :partPersonList.size());
		// 得到要害部位附件
		putToRequest("attachments", attachmentService.getAttachmentsByHostId(part.getPartId()));
		return "edit";
	}

	/**
	 * <p>
	 * 编辑操作
	 * </p>
	 * <p>
	 * 王彭波 2012-12-14 16:46:11
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * <p>
	 * </p>
	 *
	 * @author FastCodeingTools
	 * @author 王彭波
	 * @since 1.0
	 * @version 1.0
	 */
	public String editing() {
		// 得到数据库原始bean
		Part partFormDb = partModuleService.get(part.getPartId());
		// 更新业务信息
		partFormDb.setPartName(part.getPartName());
		partFormDb.setLocation(part.getLocation());
		partFormDb.setManagerRule(part.getManagerRule());
		partFormDb.setSecScope(part.getSecScope());
		partFormDb.setSecrecyLevel(part.getSecrecyLevel());
		partFormDb.setSkillMeasure(part.getSkillMeasure());
		partFormDb.setPhone(part.getPhone());
		// 更新基础信息
		partFormDb.setCreateperson(getCurrentUser());
		partFormDb.setOrgan(getCurrentUser().getOrgan());
		partFormDb.setModifyTime(new Date());

		User user = getCurrentUser();
		//设置部门
		if (part.getDepartment() != null && LangUtils.isNotEmpty(part.getDepartment().getDepartmentId())) {
			partFormDb.setDepartment(partModuleService.get(
					part.getDepartment().getDepartmentId(), Department.class));
		} else if (part.getDepartment() != null && LangUtils.isEmpty(part.getDepartment().getDepartmentId()) && LangUtils.isNotEmpty(part.getDepartment().getDepartmentName())) {
			Department department = partModuleService.saveDepartment( part.getDepartment().getDepartmentName(),user);
			partFormDb.setDepartment(department);
		}

		//设置负责人
		if (part.getPerson() != null && LangUtils.isNotEmpty(
				part.getPerson().getUserInfoId())) {
			partFormDb.setPerson(partModuleService.get(
					part.getPerson().getUserInfoId(), UserInfo.class));
		} else if (part.getPerson() != null && LangUtils.isNotEmpty(
				part.getPerson().getName())) {
			UserInfo userInfo = partModuleService.saveUserInfo(part.getPerson().getName(),user);
			partFormDb.setPerson(userInfo);
		}

		// department = partModuleService.get(department.getDepartmentId(), Department.class);
		//partFormDb.getDepartments().add(department);
		//修改人：宋亚非   修改原因：数据库统一至一个库后，取消上报功能，不再存在上报后修改状态   修改时间：2013-06-03
		/*if(partFormDb.getReportState() > 0) {
			partFormDb.setReportState(ReportState.REPORT_YES_MODIFY);
		}*/
		partModuleService.update(partFormDb);
		attachmentService.updateHostId(part.getPartId(), keyPartAttachs);
		// 设置信息
		this.addActionMessage("更新【" + part.getPartName() + "】成功!");
		needReload = true;
		return "list";
	}

	// ******************** Setter & Getter ********************
	/**
	 * @return the partModuleService
	 */
	public PartModuleService getPartModuleService() {
		return partModuleService;
	}

	/**
	 * @return the part
	 */
	public Part getPart() {
		return part;
	}

	/**
	 * @param partModuleService the partModuleService to set
	 */
	public void setPartModuleService(PartModuleService partModuleService) {
		this.partModuleService = partModuleService;
	}

	/**
	 * @param part the part to set
	 */
	public void setPart(Part part) {
		this.part = part;
	}

	/**
	 * @return the partIds
	 */
	public String getPartIds() {
		return partIds;
	}

	/**
	 * @param partIds the partIds to set
	 */
	public void setPartIds(String partIds) {
		this.partIds = partIds;
	}

	/**
	 * @return the departmentId
	 */
	public String getDepartmentId() {
		return departmentId;
	}

	/**
	 * @param departmentId the departmentId to set
	 */
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	/**
	 * @return the secrecyLevel
	 */
	public Integer getSecrecyLevel() {
		return secrecyLevel;
	}

	/**
	 * @return the partList
	 */
	public List<Part> getPartList() {
		return partList;
	}

	/**
	 * @return the partPersonIds
	 */
	public String getPartPersonIds() {
		return partPersonIds;
	}

	/**
	 * @return the organ
	 */
	public Organ getOrgan() {
		return organ;
	}

	/**
	 * @return the district
	 */
	public District getDistrict() {
		return district;
	}

	/**
	 * @return the listIds
	 */
	public String getListIds() {
		return listIds;
	}

	/**
	 * @return the needReload
	 */
	public Boolean getNeedReload() {
		return needReload;
	}

	/**
	 * @return the userInfo
	 */
	public UserInfo getUserInfo() {
		return userInfo;
	}

	/**
	 * @return the partPerson
	 */
	public PartPerson getPartPerson() {
		return partPerson;
	}

	/**
	 * @return the partPersonList
	 */
	public List<PartPerson> getPartPersonList() {
		return partPersonList;
	}

	/**
	 * @return the partPersonModuleService
	 */
	public PartPersonModuleService getPartPersonModuleService() {
		return partPersonModuleService;
	}

	/**
	 * @return the secrecyPerson
	 */
	public SecrecyPerson getSecrecyPerson() {
		return secrecyPerson;
	}

	/**
	 * @return the secrecyPersonModuleService
	 */
	public SecrecyPersonModuleService getSecrecyPersonModuleService() {
		return secrecyPersonModuleService;
	}

	/**
	 * @return the attachmentService
	 */
	public AttachmentService getAttachmentService() {
		return attachmentService;
	}

	/**
	 * @return the keyPartAttachs
	 */
	public List<String> getKeyPartAttachs() {
		return keyPartAttachs;
	}

	/**
	 * @param secrecyLevel the secrecyLevel to set
	 */
	public void setSecrecyLevel(Integer secrecyLevel) {
		this.secrecyLevel = secrecyLevel;
	}

	/**
	 * @param partList the partList to set
	 */
	public void setPartList(List<Part> partList) {
		this.partList = partList;
	}

	/**
	 * @param partPersonIds the partPersonIds to set
	 */
	public void setPartPersonIds(String partPersonIds) {
		this.partPersonIds = partPersonIds;
	}

	/**
	 * @param organ the organ to set
	 */
	public void setOrgan(Organ organ) {
		this.organ = organ;
	}

	/**
	 * @param district the district to set
	 */
	public void setDistrict(District district) {
		this.district = district;
	}

	/**
	 * @param listIds the listIds to set
	 */
	public void setListIds(String listIds) {
		this.listIds = listIds;
	}

	/**
	 * @param needReload the needReload to set
	 */
	public void setNeedReload(Boolean needReload) {
		this.needReload = needReload;
	}

	/**
	 * @return the needReload2
	 */
	public Boolean getNeedReload2() {
		return needReload2;
	}

	/**
	 * @param needReload2 the needReload2 to set
	 */
	public void setNeedReload2(Boolean needReload2) {
		this.needReload2 = needReload2;
	}

	/**
	 * @param userInfo the userInfo to set
	 */
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	/**
	 * @param partPerson the partPerson to set
	 */
	public void setPartPerson(PartPerson partPerson) {
		this.partPerson = partPerson;
	}

	/**
	 * @param partPersonList the partPersonList to set
	 */
	public void setPartPersonList(List<PartPerson> partPersonList) {
		this.partPersonList = partPersonList;
	}

	/**
	 * @param partPersonModuleService the partPersonModuleService to set
	 */
	public void setPartPersonModuleService(
			PartPersonModuleService partPersonModuleService) {
		this.partPersonModuleService = partPersonModuleService;
	}

	/**
	 * @param secrecyPerson the secrecyPerson to set
	 */
	public void setSecrecyPerson(SecrecyPerson secrecyPerson) {
		this.secrecyPerson = secrecyPerson;
	}

	/**
	 * @param secrecyPersonModuleService the secrecyPersonModuleService to set
	 */
	public void setSecrecyPersonModuleService(
			SecrecyPersonModuleService secrecyPersonModuleService) {
		this.secrecyPersonModuleService = secrecyPersonModuleService;
	}

	/**
	 * @param attachmentService the attachmentService to set
	 */
	public void setAttachmentService(AttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}

	/**
	 * @param keyPartAttachs the keyPartAttachs to set
	 */
	public void setKeyPartAttachs(List<String> keyPartAttachs) {
		this.keyPartAttachs = keyPartAttachs;
	}
}
