package com.cdthgk.bmp.secrecyresearchproject.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.action.BmpAction;
import com.cdthgk.bmp.log.context.BusinessLogContext;
import com.cdthgk.bmp.log.vo.BusinessLog;
import com.cdthgk.bmp.secrecycountryitem.qo.ZongHeTongJiStatDto;
import com.cdthgk.bmp.secrecyproducts.vo.SecrecyProducts;
import com.cdthgk.bmp.secrecyresearchproject.service.SecrecyResearchProjectChangeService;
import com.cdthgk.bmp.secrecyresearchproject.service.SecrecyResearchProjectClearService;
import com.cdthgk.bmp.secrecyresearchproject.service.SecrecyResearchProjectService;
import com.cdthgk.bmp.secrecyresearchproject.vo.SecrecyResearchProject;
import com.cdthgk.bmp.secrecyresearchproject.vo.SecrecyResearchProjectChange;
import com.cdthgk.bmp.secrecyresearchproject.vo.SecrecyResearchProjectClear;
import com.cdthgk.bmp.statinfo.dto.QueryDto;
import com.cdthgk.common.bean.BeanUtils;
import com.cdthgk.common.bean.rule.CopyRuleEnum;
import com.cdthgk.common.lang.CollectionUtils;
import com.cdthgk.common.lang.StringUtils;
import com.cdthgk.platform.dataValidate.service.DataValidateService;
import com.cdthgk.platform.dictionary.context.DictionaryContext;
import com.cdthgk.platform.dictionary.spi.DictionaryService;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.district.service.DistrictService;
import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.organ.service.OrganService;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

public class SecrecyResearchProjectAction extends BmpAction {

	private static final long serialVersionUID = -6669689656174296611L;

	/*** spring 注入 ***/
	private SecrecyResearchProjectService secrecyResearchProjectService;
	private SecrecyResearchProjectChangeService secrecyResearchProjectChangeService;
	private SecrecyResearchProjectClearService secrecyResearchProjectClearService;
	private DistrictService districtService;// 行政区划
	private OrganService organService;
	private DataValidateService dataValidateService;

	/**** struts2 ***/
	private SecrecyResearchProject secrecyResearchProject;
	private SecrecyResearchProjectChange secrecyResearchProjectChange;
	private SecrecyResearchProjectClear secrecyResearchProjectClear;
	private District district;

	/**
	 * 进入涉密科研项目的 主列表
	 *
	 * @return
	 */
	public String list() {
		// 标志的传递 业务标志 1表示查询 0表示普通业务模块
		String ywFlag = this.getRequest().getParameter("ywFlag");
		if (ywFlag == null) {
			ywFlag = "0";
		}
		this.putToRequest("ywFlag", ywFlag);// 标志的传递 业务标志 1表示查询 0表示普通业务模块

		// 行政区划编码
		String districtCode = this.getRequest().getParameter("districtCode");
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = districtService.get(districtCode);
		}
		this.putToRequest("district", district);

		// 是否包含下级
		String strChildren = this.getRequest().getParameter("isChildren");// 行政区划编码
		int isChildren = 0;
		if (strChildren != null && !"".equals(strChildren)) {
			isChildren = Integer.parseInt(strChildren);
		}
		this.putToRequest("isChildren", isChildren);

		return SUCCESS;
	}

	/**
	 * 进入涉密科研项目的 列表页面
	 *
	 * @return
	 */
	public String list_list() {

		List<SecrecyResearchProject> secrecyResearchProjectList = new ArrayList<SecrecyResearchProject>();
		PageSortModel psm = new PageSortModel(this.getRequest(),
				"secrecyResearchProjectList");

		// 标志的传递 业务标志 1表示查询 0表示普通业务模块
		String ywFlag = this.getRequest().getParameter("ywFlag");
		if (ywFlag == null) {
			ywFlag = "0";
		}
		this.putToRequest("ywFlag", ywFlag);// 标志的传递 业务标志 1表示查询 0表示普通业务模块

		if (ywFlag.equals("1")) {// 查询页面 按照行政区划 来查
			// 行政区划编码
			String districtCode = this.getRequest()
					.getParameter("districtCode");// 行政区划编码
			District district = null;
			if (StringUtils.isEmpty(districtCode)) {
				district = getCurrentUser().getOrgan().getDistrict();
			} else {
				district = districtService.get(districtCode);
			}
			this.putToRequest("district", district);

			// 是否包含下级
			String strChildren = this.getRequest().getParameter("isChildren");// 行政区划编码
			int isChildren = 0;
			if (strChildren != null && !"".equals(strChildren)) {
				isChildren = Integer.parseInt(strChildren);
			}
			this.putToRequest("isChildren", isChildren);

			secrecyResearchProjectList = secrecyResearchProjectService
					.querySecrecyResearchProjectList(psm, district, isChildren,
							secrecyResearchProject);
		} else if (ywFlag.equals("0")) { // 普通页面按照 单位来查询
			String organId = getRequest().getParameter("organ.organId");
			Organ organ = new Organ();
			if (organId == null) {
				organ = this.getCurrentUser().getOrgan();
			} else {
				organ = organService.get(organId);
			}
			putToRequest("dataGetFlag",
					this.getRequest().getParameter("dataGetFlag"));// 数据撰取标志
																	// 用于综合统计
			putToRequest("organ", organ);

			secrecyResearchProjectList = secrecyResearchProjectService
					.querySecrecyResearchProjectList(psm, organ,
							secrecyResearchProject);
		}

		this.putToRequest("secrecyResearchProjectList",
				secrecyResearchProjectList);
		this.putToRequest("secrecyResearchProject", secrecyResearchProject);
		return SUCCESS;
	}

	/**
	 * 涉密科研项目 密级变更的列表
	 *
	 * @return
	 */
	public String change_list() {

		List<SecrecyResearchProjectChange> secrecyResearchProjectChangeList = new ArrayList<SecrecyResearchProjectChange>();
		PageSortModel psm = new PageSortModel(this.getRequest(),
				"secrecyResearchProjectChangeList");

		// 标志的传递 业务标志 1表示查询 0表示普通业务模块
		String ywFlag = this.getRequest().getParameter("ywFlag");
		if (ywFlag == null) {
			ywFlag = "0";
		}
		this.putToRequest("ywFlag", ywFlag);// 标志的传递 业务标志 1表示查询 0表示普通业务模块

		if (ywFlag.equals("1")) {// 查询页面 按照行政区划 来查
			// 行政区划编码
			String districtCode = this.getRequest()
					.getParameter("districtCode");// 行政区划编码
			District district = null;
			if (StringUtils.isEmpty(districtCode)) {
				district = getCurrentUser().getOrgan().getDistrict();
			} else {
				district = districtService.get(districtCode);
			}
			this.putToRequest("district", district);

			// 是否包含下级
			String strChildren = this.getRequest().getParameter("isChildren");// 行政区划编码
			int isChildren = 0;
			if (strChildren != null && !"".equals(strChildren)) {
				isChildren = Integer.parseInt(strChildren);
			}
			this.putToRequest("isChildren", isChildren);

			secrecyResearchProjectChangeList = secrecyResearchProjectChangeService
					.queryChangeList(psm, secrecyResearchProjectChange, null,
							district, isChildren);
		} else if (ywFlag.equals("0")) { // 普通页面按照 单位来查询
			Organ organ = this.getCurrentUser().getOrgan();
			secrecyResearchProjectChangeList = secrecyResearchProjectChangeService
					.queryChangeList(psm, secrecyResearchProjectChange, organ,
							null, 0);
		}

		this.putToRequest("secrecyResearchProjectChangeList",
				secrecyResearchProjectChangeList);
		this.putToRequest("secrecyResearchProjectChange",
				secrecyResearchProjectChange);
		return SUCCESS;
	}

	/**
	 * 涉密科研项目 密级解除列表
	 *
	 * @return
	 */
	public String clear_list() {

		List<SecrecyResearchProjectClear> secrecyResearchProjectClearList = new ArrayList<SecrecyResearchProjectClear>();
		PageSortModel psm = new PageSortModel(this.getRequest(),
				"secrecyResearchProjectClearList");

		// 标志的传递 业务标志 1表示查询 0表示普通业务模块
		String ywFlag = this.getRequest().getParameter("ywFlag");
		if (ywFlag == null) {
			ywFlag = "0";
		}
		this.putToRequest("ywFlag", ywFlag);// 标志的传递 业务标志 1表示查询 0表示普通业务模块

		if (ywFlag.equals("1")) {// 查询页面 按照行政区划 来查
			// 行政区划编码
			String districtCode = this.getRequest()
					.getParameter("districtCode");// 行政区划编码
			District district = null;
			if (StringUtils.isEmpty(districtCode)) {
				district = getCurrentUser().getOrgan().getDistrict();
			} else {
				district = districtService.get(districtCode);
			}
			this.putToRequest("district", district);

			// 是否包含下级
			String strChildren = this.getRequest().getParameter("isChildren");// 行政区划编码
			int isChildren = 0;
			if (strChildren != null && !"".equals(strChildren)) {
				isChildren = Integer.parseInt(strChildren);
			}
			this.putToRequest("isChildren", isChildren);

			secrecyResearchProjectClearList = secrecyResearchProjectClearService
					.queryClearList(psm, secrecyResearchProjectClear, null,
							district, isChildren);
		} else if (ywFlag.equals("0")) { // 普通页面按照 单位来查询
			Organ organ = this.getCurrentUser().getOrgan();
			secrecyResearchProjectClearList = secrecyResearchProjectClearService
					.queryClearList(psm, secrecyResearchProjectClear, organ,
							null, 0);
		}

		this.putToRequest("secrecyResearchProjectClearList",
				secrecyResearchProjectClearList);
		this.putToRequest("secrecyResearchProjectClear",
				secrecyResearchProjectClear);
		return SUCCESS;
	}

	/**
	 * 进入涉密科研项目的 新增页面
	 *
	 * @return
	 */
	public String add() {

		// 设置涉密科研项目初始化信息
		this.putToRequest("needReload", Boolean.FALSE);
		return SUCCESS;
	}

	/**
	 * 涉密科研项目的 新增保存
	 *
	 * @return
	 */
	public String adding() {

		// 设置创建人信息
		User u = this.getCurrentUser();
		secrecyResearchProject.setCreatePerson(u);
		secrecyResearchProject.setCreateTime(new Date());
		secrecyResearchProject.setCreateOrgan(u.getOrgan());

		// 处理定密负责人
		String userid = secrecyResearchProject.getFormulateSecrecyPerson()
				.getUserInfoId();
		if (userid == null || "".equals(userid)) {// 系统中不存在该定密负责人
			UserInfo secrecyPerson = secrecyResearchProjectService
					.saveSecrecyPerson(
							secrecyResearchProject.getFormulateSecrecyPerson(),
							u);
			secrecyResearchProject.setFormulateSecrecyPerson(secrecyPerson);
		}

		// 处理项目负责人
		String projectPerson = secrecyResearchProject.getProjectPerson()
				.getUserInfoId();
		if (projectPerson == null || "".equals(projectPerson)) {// 系统中不存在该人员
			UserInfo secrecyPerson1 = secrecyResearchProjectService
					.saveSecrecyPerson(
							secrecyResearchProject.getProjectPerson(), u);
			secrecyResearchProject.setProjectPerson(secrecyPerson1);
		}

		// 处理部门
		String departmentId = secrecyResearchProject.getDepartId()
				.getDepartmentId();
		if (departmentId == null || "".equals(departmentId)) {// 是在系统中不存在 的部门
			String departmentName = this.getRequest().getParameter(
					"departmentName");// 获取部门的名称
			Department department = secrecyResearchProjectService
					.saveDepartmentByName(departmentName, u);// 保存部门
			secrecyResearchProject.setDepartId(department);
		}

		secrecyResearchProject = secrecyResearchProjectService
				.save(secrecyResearchProject);// 保存
		if (secrecyResearchProject != null) {
			this.putToRequest("needReload", Boolean.TRUE);
		} else {
			this.putToRequest("needReload", Boolean.FALSE);
		}
		this.putToRequest("secrecyResearchProject", secrecyResearchProject);
		BusinessLog log = new BusinessLog();
		log.setBusinessName("密品");
		log.setPrimaryKey(secrecyResearchProject.getSecrecyResearchProjectId());
		BusinessLogContext
				.getInstance()
				.getBusinessLogService()
				.saveAddBusinessLogByModule(getCurrentUser(), log,
						secrecyResearchProject);
		return SUCCESS;
	}

	/**
	 * 进入涉密科研项目的 编辑页面
	 *
	 * @return
	 */
	public String edit() {

		String pkId = secrecyResearchProject.getSecrecyResearchProjectId();// 获取id
		secrecyResearchProject = secrecyResearchProjectService.get(pkId);// 通过id查询出对象

		this.putToRequest("secrecyResearchProject", secrecyResearchProject);
		this.putToRequest("needReload", Boolean.FALSE);
		return SUCCESS;
	}

	/**
	 * 涉密科研项目的 编辑保存
	 *
	 * @return
	 */
	public String editing() {

		// 设置修改人
		SecrecyResearchProject oldSrp = secrecyResearchProjectService
				.get(secrecyResearchProject.getSecrecyResearchProjectId());
		User u = this.getCurrentUser();
		secrecyResearchProject.setModifyPerson(u);
		secrecyResearchProject.setModifyOrgan(u.getOrgan());
		secrecyResearchProject.setModifyTime(new Date());

		// 处理定密负责人
		String userid = secrecyResearchProject.getFormulateSecrecyPerson()
				.getUserInfoId();
		if (userid == null || "".equals(userid)) {// 系统中不存在该定密负责人
			UserInfo secrecyPerson = secrecyResearchProjectService
					.saveSecrecyPerson(
							secrecyResearchProject.getFormulateSecrecyPerson(),
							u);
			secrecyResearchProject.setFormulateSecrecyPerson(secrecyPerson);
		}

		// 处理项目负责人
		String projectPerson = secrecyResearchProject.getProjectPerson()
				.getUserInfoId();
		if (projectPerson == null || "".equals(projectPerson)) {// 系统中不存在该人员
			UserInfo secrecyPerson = secrecyResearchProjectService
					.saveSecrecyPerson(
							secrecyResearchProject.getProjectPerson(), u);
			secrecyResearchProject.setProjectPerson(secrecyPerson);
		}

		// 处理部门
		String departmentId = secrecyResearchProject.getDepartId()
				.getDepartmentId();
		if (departmentId == null || "".equals(departmentId)) {// 是在系统中不存在 的部门
			String departmentName = this.getRequest().getParameter(
					"departmentName");// 获取部门的名称
			Department department = secrecyResearchProjectService
					.saveDepartmentByName(departmentName, u);// 保存部门
			secrecyResearchProject.setDepartId(department);
		}

		secrecyResearchProject = secrecyResearchProjectService
				.saveOrUpdate(secrecyResearchProject);
		if (secrecyResearchProject != null) {
			this.putToRequest("needReload", Boolean.TRUE);
		} else {
			this.putToRequest("needReload", Boolean.FALSE);
		}
		this.putToRequest("secrecyResearchProject", secrecyResearchProject);
		BusinessLog log = new BusinessLog();
		log.setBusinessName("涉密科研项目");
		log.setPrimaryKey(secrecyResearchProject.getSecrecyResearchProjectId());
		BusinessLogContext
				.getInstance()
				.getBusinessLogService()
				.saveEditBusinessLogByModule(getCurrentUser(), log,
						secrecyResearchProject, oldSrp);
		return SUCCESS;
	}

	/**
	 * 涉密科研项目的 删除
	 *
	 * @return
	 */
	public String del() {

		String secrecyResearchProjectIds = this.getRequest().getParameter(
				"secrecyResearchProjectIds");
		if (secrecyResearchProjectIds != null
				&& !"".equals(secrecyResearchProjectIds)) {
			secrecyResearchProjectService
					.deleteBatchIds(secrecyResearchProjectIds);
		}
		for (String id : secrecyResearchProjectIds.split(",")) {
			BusinessLog log = new BusinessLog();
			log.setBusinessName("涉密科研项目");
			log.setPrimaryKey(id);
			BusinessLogContext
					.getInstance()
					.getBusinessLogService()
					.saveDelBusinessLogByModule(getCurrentUser(), log,
							new SecrecyResearchProject());
		}
		this.addActionMessage(getMessageConstant().getDeleteSuccess());
		return LIST;
	}

	/**
	 * 进入 密级变更
	 *
	 * @return
	 */
	public String change() {

		String pkId = secrecyResearchProject.getSecrecyResearchProjectId();// 获取id
		secrecyResearchProject = secrecyResearchProjectService.get(pkId);// 通过id查询出对象

		// 构建密级变更对象
		SecrecyResearchProjectChange obj = new SecrecyResearchProjectChange();
		obj.setSecrecyResearchProject(secrecyResearchProject);
		obj.setBeforeLevel(secrecyResearchProject.getSecrecyLevel());
		obj.setChangeDate(new Date());

		this.putToRequest("secrecyResearchProjectChange", obj);
		return SUCCESS;
	}

	/**
	 * 密级变更
	 *
	 * @return
	 */
	public String changeing() {
		// 设置创建信息
		secrecyResearchProjectChange.setCreatePerson(getCurrentUser());
		secrecyResearchProjectChange.setCreateDate(new Date());
		SecrecyResearchProject beforeSrp = null;
		SecrecyResearchProject fk = null;
		try {
			// 保存 密级变更对象
			SecrecyResearchProjectChange obj = secrecyResearchProjectChangeService
					.save(secrecyResearchProjectChange);

			// 变更涉密科研项目的密级
			fk = secrecyResearchProjectService.get(obj
					.getSecrecyResearchProject().getSecrecyResearchProjectId());
			beforeSrp = new SecrecyResearchProject();
			BeanUtils
					.copyProperties(beforeSrp, fk, CopyRuleEnum.ignoreCaseNull);
			fk.setSecrecyLevel(obj.getAfterLevel());
			secrecyResearchProjectService.saveOrUpdate(fk);

		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage("涉密科研项目密级变更失败！");
			this.putToRequest("needReload", Boolean.FALSE);
		}

		addActionMessage("涉密科研项目密级变更成功！");
		this.putToRequest("needReload", Boolean.TRUE);
		BusinessLog log = new BusinessLog();
		log.setBusinessName("涉密科研项目");
		log.setPrimaryKey(fk.getSecrecyResearchProjectId());
		BusinessLogContext
				.getInstance()
				.getBusinessLogService()
				.saveEditBusinessLogByModule(getCurrentUser(), log, fk,
						beforeSrp);
		return SUCCESS;
	}

	/**
	 * 进入 密级解除
	 *
	 * @return
	 */
	public String clear() {
		String pkId = secrecyResearchProject.getSecrecyResearchProjectId();// 获取id
		secrecyResearchProject = secrecyResearchProjectService.get(pkId);// 通过id查询出对象

		// 构建
		SecrecyResearchProjectClear obj = new SecrecyResearchProjectClear();
		obj.setSecrecyResearchProject(secrecyResearchProject);
		obj.setClearTime(new Date());

		this.putToRequest("secrecyResearchProjectClear", obj);
		return SUCCESS;
	}

	/**
	 * 密级解除
	 *
	 * @return
	 */
	public String clearing() {

		// 设置创建信息
		secrecyResearchProjectClear.setCreatePerson(getCurrentUser());
		secrecyResearchProjectClear.setCreateDate(new Date());
		SecrecyResearchProject beforeSrp = null;
		SecrecyResearchProject fk = null;
		try {
			// 保存解密
			SecrecyResearchProjectClear obj = secrecyResearchProjectClearService
					.save(secrecyResearchProjectClear);

			// 涉密科研项目解密
			fk = secrecyResearchProjectService.get(obj
					.getSecrecyResearchProject().getSecrecyResearchProjectId());
			beforeSrp = new SecrecyResearchProject();
			BeanUtils
					.copyProperties(beforeSrp, fk, CopyRuleEnum.ignoreCaseNull);
			fk.setSecrecyStatus(1);
			secrecyResearchProjectService.saveOrUpdate(fk);

		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage("涉密科研项目密级解除失败！");
			this.putToRequest("needReload", Boolean.FALSE);
		}

		addActionMessage("涉密科研项目密级解除成功！");
		this.putToRequest("needReload", Boolean.TRUE);
		BusinessLog log = new BusinessLog();
		log.setBusinessName("涉密科研项目");
		log.setPrimaryKey(fk.getSecrecyResearchProjectId());
		BusinessLogContext
				.getInstance()
				.getBusinessLogService()
				.saveEditBusinessLogByModule(getCurrentUser(), log, fk,
						beforeSrp);
		return SUCCESS;
	}

	/**
	 * 变更明细
	 *
	 * @return
	 */
	public String changeDetial() {
		// 获取变更对象
		secrecyResearchProjectChange = secrecyResearchProjectChangeService
				.get(secrecyResearchProjectChange.getChangeId());
		// 涉密科研项目
		secrecyResearchProject = secrecyResearchProjectChange
				.getSecrecyResearchProject();

		this.putToRequest("secrecyResearchProjectChange",
				secrecyResearchProjectChange);
		this.putToRequest("secrecyResearchProject", secrecyResearchProject);

		return SUCCESS;
	}

	/**
	 * 解密明细
	 *
	 * @return
	 */
	public String clearDetial() {
		// 获取解除对象
		secrecyResearchProjectClear = secrecyResearchProjectClearService
				.get(secrecyResearchProjectClear.getClearId());
		// 涉密科研项目
		secrecyResearchProject = secrecyResearchProjectClear
				.getSecrecyResearchProject();
		// 查询 涉密科研项目变更
		if (secrecyResearchProjectChange == null) {
			secrecyResearchProjectChange = new SecrecyResearchProjectChange();
		}
		secrecyResearchProjectChange
				.setSecrecyResearchProject(secrecyResearchProject);
		List<SecrecyResearchProjectChange> secrecyResearchProjectChangeList = new ArrayList<SecrecyResearchProjectChange>();

		// 业务标志 1查询模块 0普通业务模块
		String ywFlag = this.getRequest().getParameter("ywFlag");
		Organ organ = this.getCurrentUser().getOrgan();
		if (ywFlag != null && ywFlag.equals("1")) {
			organ = secrecyResearchProject.getCreateOrgan();
		}
		secrecyResearchProjectChangeList = secrecyResearchProjectChangeService
				.queryChangeList(null, secrecyResearchProjectChange, organ,
						null, 0);

		this.putToRequest("secrecyResearchProjectClear",
				secrecyResearchProjectClear);
		this.putToRequest("secrecyResearchProject", secrecyResearchProject);
		this.putToRequest("secrecyResearchProjectChangeList",
				secrecyResearchProjectChangeList);
		this.putToRequest("secrecyResearchProjectChange",
				secrecyResearchProjectChange);

		return SUCCESS;
	}

	/**
	 * 涉密科研项目明细
	 *
	 * @return
	 */
	public String detail() {
		Organ organ = this.getCurrentUser().getOrgan();
		District district = organ.getDistrict();
		// 获取涉密科研项目对象
		secrecyResearchProject = secrecyResearchProjectService
				.get(secrecyResearchProject.getSecrecyResearchProjectId());

		// 查询 涉密科研项目
		if (secrecyResearchProjectChange == null) {
			secrecyResearchProjectChange = new SecrecyResearchProjectChange();
		}
		secrecyResearchProjectChange
				.setSecrecyResearchProject(secrecyResearchProject);

		// 查询
		List<SecrecyResearchProjectChange> secrecyResearchProjectChangeList = null;
		if (!organ.getOrganId().equals(
				secrecyResearchProject.getCreateOrgan().getOrganId())) {
			secrecyResearchProjectChangeList = secrecyResearchProjectChangeService
					.queryChangeList(null, secrecyResearchProjectChange, null,
							district, 1);
		} else {
			secrecyResearchProjectChangeList = secrecyResearchProjectChangeService
					.queryChangeList(null, secrecyResearchProjectChange, organ,
							null, 0);
		}

		this.putToRequest("secrecyResearchProjectChangeList",
				secrecyResearchProjectChangeList);
		this.putToRequest("secrecyResearchProjectChange",
				secrecyResearchProjectChange);
		this.putToRequest("secrecyResearchProject", secrecyResearchProject);

		return SUCCESS;
	}

	/**
	 * 通过ajax查询 涉密科研项目表 中的数据被哪些表所引用 传递参数 secrecyResearchProjectId -> 涉密科研项目id
	 *
	 * @return
	 */
	public String ajax_relationship() {

		String ids = this.getRequest().getParameter("secrecyResearchProjectId");
		Integer iValue = 0;
		String message = "";

		if (ids != null) {
			String[] args = ids.split(",");
			if (args != null && args.length > 0) {
				for (int i = 0; i < args.length; i++) {
					String id = args[i];

					iValue = secrecyResearchProjectService
							.getRelationshipForTable(id);// 获取到引用的信息
					if (iValue == 1) {
						message = "密级变更";
					} else if (iValue == 2) {
						message = "密级解除";
					}

					if (iValue != 0) {
						break;
					}
				}
			}
		}

		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("flag", String.valueOf(iValue));
		resultMap.put("message", message);

		this.setResultData(resultMap);
		return JSON;
	}

	/**
	 * 导出数据
	 *
	 * @return
	 */
	public String export() {

		List<SecrecyResearchProject> secrecyResearchProjectList = new ArrayList<SecrecyResearchProject>();

		// 业务标志 1： 查询页面 0：普通业务模块
		String ywFlag = this.getRequest().getParameter("ywFlag");
		if (ywFlag.equals("1")) {
			String districtCode = this.getRequest()
					.getParameter("districtCode");// 行政区划编码
			String isChildren = this.getRequest().getParameter("isChildren");// 是否包含下级
			// 处理是否包含下级的标志
			isChildren = isChildren == null || isChildren.equals("") ? "0"
					: isChildren;
			// 处理行政区划对象
			District district = null;
			if (districtCode == null) {
				district = this.getCurrentUser().getOrgan().getDistrict();
			} else {
				district = districtService.get(districtCode);
			}
			secrecyResearchProjectList = secrecyResearchProjectService
					.querySecrecyResearchProjectList(null, district,
							Integer.parseInt(isChildren),
							secrecyResearchProject);

		} else {
			Organ organ = this.getCurrentUser().getOrgan();
			// 查询 涉密科研项目
			secrecyResearchProjectList = secrecyResearchProjectService
					.querySecrecyResearchProjectList(null, organ,
							secrecyResearchProject);
		}
		this.putToRequest("secrecyResearchProjectList",
				secrecyResearchProjectList);

		Map<String, Object> params = new HashMap<String, Object>();
		// 被导出的数据
		params.put("secrecyResearchProjectList", secrecyResearchProjectList);
		// 时间格式化器
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		params.put("dateFormat", dateFormat);
		// 数据字典工具
		DictionaryService dictionary = DictionaryContext.getInstance()
				.getDictionaryService();
		params.put("dictionary", dictionary);
		// 数据类型转换
		params.put("Integer", Integer.class);

		setResultData(params);
		return SUCCESS;
	}

	/**
	 * (本单位) 首页统计涉密科研项目
	 *
	 * @return
	 */
	public String indexView_SecrecyResearchProject() {
		// 本单位
		Organ organ = getCurrentUser().getOrgan();
		// 总数查询
		Map<String, Object> cmap = secrecyResearchProjectService
				.getSecrecyResearchProject_Total_CurrentOrgan(organ);

		putToRequest("cmap", cmap);
		putToRequest("organ", organ);
		return SUCCESS;
	}

	/**
	 * (本单位) 涉密科研项目 明细 列表
	 *
	 * @return
	 */
	public String indexView_DetailList() {
		String organId = getRequest().getParameter("organId"); // 单位id
		String secrecy_level = getRequest().getParameter("secrecyLevel");// 密级

		// 设置单位
		Organ organ = null;
		if (organId == null || organId.equals("")) {
			organ = getCurrentUser().getOrgan();
		} else {
			organ = new Organ();
			organ.setOrganId(organId);
		}

		// 设置密级
		if (secrecyResearchProject == null) {
			secrecyResearchProject = new SecrecyResearchProject();
		}
		if (secrecy_level != null && !"".equals(secrecy_level)) {
			if (!secrecy_level.equals("-1")) {
				secrecyResearchProject.setSecrecyLevel(Integer
						.parseInt(secrecy_level));
				if (secrecy_level.equals("1")) {
					putToRequest("secrecy_level_name", "秘密");
				} else if (secrecy_level.equals("2")) {
					putToRequest("secrecy_level_name", "机密");
				} else if (secrecy_level.equals("3")) {
					putToRequest("secrecy_level_name", "绝密");
				}
			} else {
				putToRequest("secrecy_level_name", "全部");
			}
		}

		// 查询记录
		PageSortModel psm = new PageSortModel(this.getRequest(),
				"secrecyResearchProjectList");
		List<SecrecyResearchProject> secrecyResearchProjectList = secrecyResearchProjectService
				.querySecrecyResearchProjectList(psm, organ,
						secrecyResearchProject);

		putToRequest("secrecyResearchProjectList", secrecyResearchProjectList);
		putToRequest("secrecy_level", secrecy_level);
		return SUCCESS;
	}

	/**
	 * (行政区) 首页统计 涉密科研项目
	 *
	 * @return
	 */
	public String indexView_SecrecyResearchProject_District() {
		// 行政区
		District district = getCurrentUser().getOrgan().getDistrict();
		// 总数查询
		Map<String, Object> cmap = secrecyResearchProjectService
				.getSecrecyResearchProject_Total_District(district, 1);

		putToRequest("cmap", cmap);
		putToRequest("district", district);
		return SUCCESS;
	}

	/**
	 * (行政区) 涉密科研项目 明细 列表
	 *
	 * @return
	 */
	public String indexView_DetailList_District() {
		String layer = getRequest().getParameter("layer"); // 单位id
		String secrecy_level = getRequest().getParameter("secrecyLevel");// 密级

		// 设置行政区划
		District district = null;
		if (layer == null || layer.equals("")) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = new District();
			district.setLayer(layer);
		}

		// 设置密级
		if (secrecyResearchProject == null) {
			secrecyResearchProject = new SecrecyResearchProject();
		}
		if (secrecy_level != null && !"".equals(secrecy_level)) {
			if (!secrecy_level.equals("-1")) {
				secrecyResearchProject.setSecrecyLevel(Integer
						.parseInt(secrecy_level));
				if (secrecy_level.equals("1")) {
					putToRequest("secrecy_level_name", "秘密");
				} else if (secrecy_level.equals("2")) {
					putToRequest("secrecy_level_name", "机密");
				} else if (secrecy_level.equals("3")) {
					putToRequest("secrecy_level_name", "绝密");
				}
			} else {
				putToRequest("secrecy_level_name", "全部");
			}
		}

		PageSortModel psm = new PageSortModel(this.getRequest(),
				"secrecyResearchProjectList");
		List<SecrecyResearchProject> secrecyResearchProjectList = secrecyResearchProjectService
				.querySecrecyResearchProjectList(psm, district, 1,
						secrecyResearchProject);

		putToRequest("secrecyResearchProjectList", secrecyResearchProjectList);
		putToRequest("secrecy_level", secrecy_level);
		return SUCCESS;
	}

	/**
	 * 涉密科研项目查询 左树页面
	 *
	 * @return
	 */
	public String query_main() {
		return SUCCESS;
	}

	/*************************************** 综合统计 *******************************************/
	/**
	 * 综合统计 通过行政区划 查询涉密科研项目个数 的明细列表 包括当前行政区划 和下级行政区划 的涉密科研项目的明细
	 *
	 * @return
	 */
	public String zhtj_query_Detail() {

		district = districtService.get(district.getDistrictCode());

		// 处理行政区划
		List<District> districtList = new ArrayList<District>();
		List<District> childrenDistrictList = new ArrayList<District>(
				district.getChildren());
		districtList.add(district);

		// 查询 当前行政区划的 明细
		List<ZongHeTongJiStatDto> secrecyResearchProjectStatDtoList = secrecyResearchProjectService
				.count_ResearchProject_District(districtList, false);
		// 查询 子行政区划的明细
		List<ZongHeTongJiStatDto> childrenStatDtoList = secrecyResearchProjectService
				.count_ResearchProject_District(childrenDistrictList, true);

		putToRequest("secrecyResearchProjectStatDtoList",
				secrecyResearchProjectStatDtoList);
		putToRequest("childrenStatDtoList", childrenStatDtoList);
		putToRequest("districtCode", district.getDistrictCode());
		putToRequest("topDistrict", this.getCurrentUser().getOrgan()
				.getDistrict());

		return SUCCESS;
	}

	/**
	 * 综合统计 通过行政区划编码 统计该行政区划涉密科研项目的统计 一个单位一排数据，同时这个action提供了通过单位的名称模糊查询的功能。
	 *
	 * @return
	 */
	public String zhtj_queryByDistrict() {
		// 这里只会得到 行政区划编码，没有单位的信息
		district = districtService.get(district.getDistrictCode());

		String organName = this.getRequest().getParameter("organ.organName");
		Organ organ = null;
		if (organName != null && !"".equals(organName)) {
			organ = new Organ();
			organ.setOrganName(organName);
		}

		List<ZongHeTongJiStatDto> secrecyResearchProjectStatDtoList = secrecyResearchProjectService
				.count_ResearchProject_District(district, true, organ);
		putToRequest("secrecyResearchProjectStatDtoList",
				secrecyResearchProjectStatDtoList);
		putToRequest("district", district);
		putToRequest("topDistrict", this.getCurrentUser().getOrgan()
				.getDistrict());

		return SUCCESS;
	}

	/**
	 * 通过密级，行政区划对象或者单位对象，查询涉密科研项目对应的列表 密级是必须字段，如果点到合计上面了，这里的单位是空的，所以需要行政区划对象
	 * 如果没有点到合计，那么会使用单位的对象去查询
	 *
	 * @return
	 */
	public String zhtj_DetailList() {

		List<SecrecyResearchProject> secrecyResearchProjectList = new ArrayList<SecrecyResearchProject>();
		PageSortModel psm = new PageSortModel(this.getRequest(),
				"secrecyResearchProjectList");

		// 设置密级
		String secrecy_level = getRequest().getParameter("secrecyLevel");// 密级
		if (secrecyResearchProject == null) {
			secrecyResearchProject = new SecrecyResearchProject();
		}
		if (secrecy_level != null && !"".equals(secrecy_level)) {
			secrecyResearchProject.setSecrecyLevel(Integer
					.parseInt(secrecy_level));
		}

		String organId = getRequest().getParameter("organId"); // 单位id
		if (organId != null && !"".equals(organId)) {// 如果有单位，就用单位对象
			Organ organ = new Organ();
			organ.setOrganId(organId);
			secrecyResearchProjectList = secrecyResearchProjectService
					.querySecrecyResearchProjectList(psm, organ,
							secrecyResearchProject);
		} else {// 如果没有单位对象 就需要使用 行政区划对象 也说明:鼠标点到合计了
			district = districtService.get(district.getDistrictCode());
			secrecyResearchProjectList = secrecyResearchProjectService
					.querySecrecyResearchProjectList(psm, district,
							secrecyResearchProject);
		}

		putToRequest("secrecyResearchProjectList", secrecyResearchProjectList);
		putToRequest("secrecy_level", secrecy_level);
		return SUCCESS;
	}

	/**
	 * 一个单位的 数据篆取
	 *
	 * @return
	 */
	public String zhtj_OrganDetailList() {

		// 设置密级
		String secrecy_level = getRequest().getParameter("secrecyType");// 密级
		if (secrecyResearchProject == null) {
			secrecyResearchProject = new SecrecyResearchProject();
		}
		secrecyResearchProject.setSecrecyLevel(Integer.parseInt(secrecy_level));

		String organId = getRequest().getParameter("organId"); // 单位id
		Organ organ = organService.get(organId);
		// 查询
		List<SecrecyResearchProject> secrecyResearchProjectList = new ArrayList<SecrecyResearchProject>();
		PageSortModel psm = new PageSortModel(this.getRequest(),
				"secrecyResearchProjectList");
		secrecyResearchProjectList = secrecyResearchProjectService
				.querySecrecyResearchProjectList(psm, organ,
						secrecyResearchProject);// 查询 涉密科研项目

		putToRequest("secrecyResearchProjectList", secrecyResearchProjectList);
		putToRequest("secrecy_level", secrecy_level);
		putToRequest("organ", organ);
		putToRequest("dataGetFlag", "1");
		putToRequest("ywFlag", "0");
		return SUCCESS;
	}

	/**
	 * 综合统计 通过行政区划 查询涉密科研项目个数 的明细列表 包括当前行政区划 和下级行政区划 的涉密科研项目的明细
	 *
	 * @return
	 */
	public String zhtj_query() {

		district = this.getCurrentUser().getOrgan().getDistrict();
		district = districtService.get(district.getDistrictCode());
		// 处理行政区划
		List<District> districtList = new ArrayList<District>();
		List<District> childrenDistrictList = new ArrayList<District>(
				district.getChildren());
		districtList.add(district);

		// 查询 当前行政区划的 明细
		List<ZongHeTongJiStatDto> secrecyResearchProjectStatDtoList = secrecyResearchProjectService
				.count_ResearchProject_District(districtList, false);
		// 查询 子行政区划的明细
		List<ZongHeTongJiStatDto> childrenStatDtoList = secrecyResearchProjectService
				.count_ResearchProject_District(childrenDistrictList, true);

		putToRequest("secrecyResearchProjectStatDtoList",
				secrecyResearchProjectStatDtoList);
		putToRequest("childrenStatDtoList", childrenStatDtoList);
		putToRequest("districtCode", district.getDistrictCode());
		putToRequest("topDistrict", this.getCurrentUser().getOrgan()
				.getDistrict());

		return SUCCESS;
	}

	/************************************ 综合统计导出 ************************************************************/
	/**
	 * 行政区划导出
	 *
	 * @return
	 */
	public String stat_exportDistrict() {
		district = districtService.get(district.getDistrictCode());

		// 处理行政区划
		List<District> districtList = new ArrayList<District>();
		districtList.add(district);

		// 查询 当前行政区划的 明细
		List<ZongHeTongJiStatDto> secrecyResearchProjectStatDtoList = secrecyResearchProjectService
				.count_ResearchProject_District(districtList, false);

		// 导出设置
		Map<String, Object> params = new HashMap<String, Object>();
		// 被导出的数据
		params.put("secrecyResearchProjectStatDtoList",
				secrecyResearchProjectStatDtoList);
		params.put("district", district);
		this.putToRequest("district", district);
		setResultData(params);
		return SUCCESS;
	}

	/**
	 * 行政区内导出
	 *
	 * @return
	 */
	public String stat_exportLayer() {
		district = districtService.get(district.getDistrictCode());

		// 处理行政区划
		List<District> childrenDistrictList = new ArrayList<District>(
				district.getChildren());

		// 查询 子行政区划的明细
		List<ZongHeTongJiStatDto> childrenStatDtoList = secrecyResearchProjectService
				.count_ResearchProject_District(childrenDistrictList, true);

		// 导出设置
		Map<String, Object> params = new HashMap<String, Object>();
		// 被导出的数据
		params.put("childrenStatDtoList", childrenStatDtoList);
		params.put("district", district);
		this.putToRequest("district", district);
		setResultData(params);
		return SUCCESS;
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
		List<SecrecyResearchProject> secrecyResearchProjectList = secrecyResearchProjectService
				.querySecrecyResearchProjectList(
						null,
						secrecyResearchProjectService.get(organId, Organ.class),
						secrecyResearchProject);
		putToRequest("secrecyResearchProjectList", secrecyResearchProjectList);
		return SUCCESS;
	}

	/**
	 *
	 * <p>
	 * 数据填写校验
	 * </p>
	 * <p>
	 * 创建人 陶汇源 创建时间 2014-5-26 - 上午9:53:11
	 * </p>
	 * <blockquote> <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 *
	 * @return
	 */
	public String dataValidate() {
		Map<String, Object> resultData = new HashMap<String, Object>();
		// ID值为bm_business_module中的ID
		resultData.put("id", "7");
		List<SecrecyResearchProject> secrecyResearchProjectList = secrecyResearchProjectService
				.querySecrecyResearchProjectList(null, getCurrentUser()
						.getOrgan(), null);
		String msg = dataValidateService.validateData("涉密科研项目",
				secrecyResearchProjectList, "7");
		resultData.put("msg", msg);
		setResultData(resultData);
		return JSON;
	}

	/**********************************************************************************************************/
	public SecrecyResearchProjectService getSecrecyResearchProjectService() {
		return secrecyResearchProjectService;
	}

	public void setSecrecyResearchProjectService(
			SecrecyResearchProjectService secrecyResearchProjectService) {
		this.secrecyResearchProjectService = secrecyResearchProjectService;
	}

	public SecrecyResearchProjectChangeService getSecrecyResearchProjectChangeService() {
		return secrecyResearchProjectChangeService;
	}

	public void setSecrecyResearchProjectChangeService(
			SecrecyResearchProjectChangeService secrecyResearchProjectChangeService) {
		this.secrecyResearchProjectChangeService = secrecyResearchProjectChangeService;
	}

	public SecrecyResearchProjectClearService getSecrecyResearchProjectClearService() {
		return secrecyResearchProjectClearService;
	}

	public void setSecrecyResearchProjectClearService(
			SecrecyResearchProjectClearService secrecyResearchProjectClearService) {
		this.secrecyResearchProjectClearService = secrecyResearchProjectClearService;
	}

	public SecrecyResearchProject getSecrecyResearchProject() {
		return secrecyResearchProject;
	}

	public void setSecrecyResearchProject(
			SecrecyResearchProject secrecyResearchProject) {
		this.secrecyResearchProject = secrecyResearchProject;
	}

	public SecrecyResearchProjectChange getSecrecyResearchProjectChange() {
		return secrecyResearchProjectChange;
	}

	public void setSecrecyResearchProjectChange(
			SecrecyResearchProjectChange secrecyResearchProjectChange) {
		this.secrecyResearchProjectChange = secrecyResearchProjectChange;
	}

	public SecrecyResearchProjectClear getSecrecyResearchProjectClear() {
		return secrecyResearchProjectClear;
	}

	public void setSecrecyResearchProjectClear(
			SecrecyResearchProjectClear secrecyResearchProjectClear) {
		this.secrecyResearchProjectClear = secrecyResearchProjectClear;
	}

	public DistrictService getDistrictService() {
		return districtService;
	}

	public void setDistrictService(DistrictService districtService) {
		this.districtService = districtService;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public OrganService getOrganService() {
		return organService;
	}

	public void setOrganService(OrganService organService) {
		this.organService = organService;
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
