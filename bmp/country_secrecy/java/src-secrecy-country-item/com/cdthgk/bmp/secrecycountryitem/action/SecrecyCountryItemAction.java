package com.cdthgk.bmp.secrecycountryitem.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.action.BmpAction;
import com.cdthgk.bmp.keysection.service.KeySectionModuleService;
import com.cdthgk.bmp.keysection.vo.KeySection;
import com.cdthgk.bmp.log.context.BusinessLogContext;
import com.cdthgk.bmp.log.vo.BusinessLog;
import com.cdthgk.bmp.secrecycountryitem.qo.CountrySecrecyQueryObject;
import com.cdthgk.bmp.secrecycountryitem.qo.YearControlDto;
import com.cdthgk.bmp.secrecycountryitem.qo.YearStatProvider;
import com.cdthgk.bmp.secrecycountryitem.qo.ZongHeTongJiStatDto;
import com.cdthgk.bmp.secrecycountryitem.service.SecrecyCountryItemChangeService;
import com.cdthgk.bmp.secrecycountryitem.service.SecrecyCountryItemClearService;
import com.cdthgk.bmp.secrecycountryitem.service.SecrecyCountryItemService;
import com.cdthgk.bmp.secrecycountryitem.vo.SecrecyCountryItem;
import com.cdthgk.bmp.secrecycountryitem.vo.SecrecyCountryItemChange;
import com.cdthgk.bmp.secrecycountryitem.vo.SecrecyCountryItemClear;
import com.cdthgk.bmp.secrecyperson.vo.SecrecyPerson;
import com.cdthgk.bmp.statinfo.dto.QueryDto;
import com.cdthgk.common.bean.BeanUtils;
import com.cdthgk.common.bean.rule.CopyRuleEnum;
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

/**
 * 商业秘密事项的action
 *
 * @author 梁文杰 2013-07-15
 *
 */
public class SecrecyCountryItemAction extends BmpAction {

	private static final long serialVersionUID = 1728628061765731570L;

	/**
	 * Spring 依赖注入
	 */
	private SecrecyCountryItemService secrecyCountryItemService; // 商业秘密事项的service
	private SecrecyCountryItemChangeService secrecyCountryItemChangeService;// 商业秘密事项密级变更的service
	private SecrecyCountryItemClearService secrecyCountryItemClearService;// 商业秘密事项密级解除的service
	private KeySectionModuleService keySectionModuleService;// 要害部门
	private DistrictService districtService;// 行政区划
	private OrganService organService;
	private DataValidateService dataValidateService;

	/**
	 * Struts2 modelDriver
	 */
	private SecrecyCountryItem secrecyCountryItem;// 商业秘密事项对象
	private SecrecyCountryItemChange secrecyCountryItemChange;// 商业秘密事项 密级变对象
	private SecrecyCountryItemClear secrecyCountryItemClear;// 商业秘密事项 密级解除对象
	private District district;

	/************************************************************************************************/

	/**
	 * 进入商业秘密事项的 主列表
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
	 * 进入商业秘密事项的 列表页面
	 *
	 * @return
	 */
	public String list_list() {

		List<SecrecyCountryItem> secrecyCountryItemList = new ArrayList<SecrecyCountryItem>();
		PageSortModel psm = new PageSortModel(this.getRequest(),
				"secrecyCountryItemList");

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

			secrecyCountryItemList = secrecyCountryItemService
					.queryCountryItemList(psm, district, isChildren,
							secrecyCountryItem);// 查询 商业秘密事项
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

			secrecyCountryItemList = secrecyCountryItemService
					.queryCountryItemList(psm, organ, secrecyCountryItem);// 查询
																			// 商业秘密事项
		}

		this.putToRequest("secrecyCountryItemList", secrecyCountryItemList);
		this.putToRequest("secrecyCountryItem", secrecyCountryItem);
		return SUCCESS;
	}

	/**
	 * 商业秘密事项 密级变更的列表
	 *
	 * @return
	 */
	public String change_list() {

		List<SecrecyCountryItemChange> secrecyCountryItemChangeList = new ArrayList<SecrecyCountryItemChange>();
		PageSortModel psm = new PageSortModel(this.getRequest(),
				"secrecyCountryItemChangeList");

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

			secrecyCountryItemChangeList = secrecyCountryItemChangeService
					.queryCountryItemChangeList(psm, null,
							secrecyCountryItemChange, district, isChildren);// 查询
																			// 商业秘密事项变更
		} else if (ywFlag.equals("0")) { // 普通页面按照 单位来查询
			String organId = getRequest().getParameter("organ.organId");
			Organ organ = new Organ();
			if (organId == null) {
				organ = this.getCurrentUser().getOrgan();
			} else {
				organ = organService.get(organId);
			}
			secrecyCountryItemChangeList = secrecyCountryItemChangeService
					.queryCountryItemChangeList(psm, organ,
							secrecyCountryItemChange, null, 0);// 查询 商业秘密事项变更
		}

		this.putToRequest("secrecyCountryItemChangeList",
				secrecyCountryItemChangeList);
		this.putToRequest("secrecyCountryItemChange", secrecyCountryItemChange);
		return SUCCESS;
	}

	/**
	 * 商业秘密事项 密级解除列表
	 *
	 * @return
	 */
	public String clear_list() {

		List<SecrecyCountryItemClear> secrecyCountryItemClearList = new ArrayList<SecrecyCountryItemClear>();
		PageSortModel psm = new PageSortModel(this.getRequest(),
				"secrecyCountryItemClearList");

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

			secrecyCountryItemClearList = secrecyCountryItemClearService
					.queryCountryItemChangeList(psm, null,
							secrecyCountryItemClear, district, isChildren);
		} else if (ywFlag.equals("0")) { // 普通页面按照 单位来查询
			String organId = getRequest().getParameter("organ.organId");
			Organ organ = new Organ();
			if (organId == null) {
				organ = this.getCurrentUser().getOrgan();
			} else {
				organ = organService.get(organId);
			}
			secrecyCountryItemClearList = secrecyCountryItemClearService
					.queryCountryItemChangeList(psm, organ,
							secrecyCountryItemClear, null, 0);
		}

		this.putToRequest("secrecyCountryItemClearList",
				secrecyCountryItemClearList);
		this.putToRequest("secrecyCountryItemClear", secrecyCountryItemClear);
		return SUCCESS;
	}

	/**
	 * 进入商业秘密事项的 新增页面
	 *
	 * @return
	 */
	public String add() {

		// 设置商业秘密事项初始化信息
		this.putToRequest("needReload", Boolean.FALSE);
		return SUCCESS;
	}

	/**
	 * 商业秘密事项的 新增保存
	 *
	 * @return
	 */
	public String adding() {

		// 设置创建人信息
		User u = this.getCurrentUser();
		secrecyCountryItem.setCreatePerson(u);
		secrecyCountryItem.setCreateTime(new Date());
		secrecyCountryItem.setCreateOrgan(u.getOrgan());

		// 判断是否由保密要害部门产生
		int iflag = secrecyCountryItem.getIsfromKeyDepartment();
		if (iflag == 1) {// 由要害部门产生的情况
			String keySectionId = secrecyCountryItem.getKeySectionId()
					.getKeySectionId();// 要害部门id
			// 1.如果存在该要害部门，那么直接保存即可;
			// 2.如果 在系统中该要害部门不存在，需要新增这个要害部门,(也就是说需要先新增部门，然后在将新增的部门制定位要害部门);
			if (keySectionId != null && !"".equals(keySectionId)) {// 存在的情况
				KeySection keySection = keySectionModuleService
						.get(keySectionId);
				secrecyCountryItem.setKeySectionId(keySection);
				secrecyCountryItem.setDepartId(keySection.getDepartment());
			} else {// 不存在
					// 首先保存部门,先有部门信息才能保存要害部门
				String departmentName = this.getRequest().getParameter(
						"keySectionName");// 获取部门的名称
				// 1.新增部门
				Department department = secrecyCountryItemService
						.saveDepartmentByName(departmentName, u);
				// 2.部门保存以后，继续制定该部门为要害部门
				KeySection keySection = secrecyCountryItemService
						.saveKeySection(department, u);
				// 3.设置 商业秘密事项中的 要害部门信息和部门信息
				secrecyCountryItem.setKeySectionId(keySection);
				secrecyCountryItem.setDepartId(department);
			}
		} else {// 不是由要害部门产生的情况
			secrecyCountryItem.setKeySectionId(null);// 设置要害部门位空
			String departmentId = secrecyCountryItem.getDepartId()
					.getDepartmentId();
			if (departmentId == null || "".equals(departmentId)) {// 是在系统中不存在
																	// 的部门
				String departmentName = this.getRequest().getParameter(
						"departmentName");// 获取部门的名称
				Department department = secrecyCountryItemService
						.saveDepartmentByName(departmentName, u);// 保存部门
				secrecyCountryItem.setDepartId(department);
			}
		}

		// 处理定密负责人
		String userid = secrecyCountryItem.getFormulateSecrecyPerson()
				.getUserInfoId();
		if (userid == null || "".equals(userid)) {// 系统中不存在该定密负责人
			UserInfo secrecyPerson = secrecyCountryItemService
					.saveSecrecyPerson(
							secrecyCountryItem.getFormulateSecrecyPerson(), u);
			secrecyCountryItem.setFormulateSecrecyPerson(secrecyPerson);
		}

		secrecyCountryItem = secrecyCountryItemService.save(secrecyCountryItem);// 保存
		if (secrecyCountryItem != null) {
			this.putToRequest("needReload", Boolean.TRUE);
		} else {
			this.putToRequest("needReload", Boolean.FALSE);
		}
		this.putToRequest("secrecyCountryItem", secrecyCountryItem);
		BusinessLog log=new BusinessLog();
		log.setBusinessName("商业秘密事项");
		log.setPrimaryKey(secrecyCountryItem.getSecrecyCountryItemId());
		BusinessLogContext.getInstance().getBusinessLogService().saveAddBusinessLogByModule(getCurrentUser(), log, secrecyCountryItem);
		return SUCCESS;
	}

	/**
	 * 进入商业秘密事项的 编辑页面
	 *
	 * @return
	 */
	public String edit() {

		String pkId = secrecyCountryItem.getSecrecyCountryItemId();// 获取id
		secrecyCountryItem = secrecyCountryItemService.get(pkId);// 通过id查询出对象

		this.putToRequest("secrecyCountryItem", secrecyCountryItem);
		this.putToRequest("needReload", Boolean.FALSE);
		return SUCCESS;
	}

	/**
	 * 商业秘密事项的 编辑保存
	 *
	 * @return
	 */
	public String editing() {

		// 设置修改人
		User u = this.getCurrentUser();
		SecrecyCountryItem oldsci=secrecyCountryItemService.get(secrecyCountryItem.getSecrecyCountryItemId());
		SecrecyCountryItem beforesci=new SecrecyCountryItem();
		BeanUtils.copyProperties(beforesci, oldsci, CopyRuleEnum.ignoreCaseNull);
		secrecyCountryItem.setModifyPerson(u);
		secrecyCountryItem.setModifyOrgan(u.getOrgan());
		secrecyCountryItem.setModifyTime(new Date());
		// 判断是否由保密要害部门产生
		int iflag = secrecyCountryItem.getIsfromKeyDepartment();
		if (iflag == 1) {// 要害部门
			String keySectionId = secrecyCountryItem.getKeySectionId()
					.getKeySectionId();// 要害部门id
			// 用户填写的要害部门 :
			// 1.如果存在直接保存;
			// 2.如果 在系统中不存在，需要新增这个要害部门;
			if (keySectionId != null && !"".equals(keySectionId)) {// 存在 直接保存
				KeySection keySection = keySectionModuleService
						.get(keySectionId);
				secrecyCountryItem.setKeySectionId(keySection);
				secrecyCountryItem.setDepartId(keySection.getDepartment());
			} else {// 不存在
					// 首先保存部门,先有部门信息才能保存要害部门
				String departmentName = this.getRequest().getParameter(
						"keySectionName");// 获取页面上要害部门的名称
				// 1.保存部门
				Department department = secrecyCountryItemService
						.saveDepartmentByName(departmentName, u);
				// 2.部门保存以后，继续保存要害部门
				KeySection keySection = secrecyCountryItemService
						.saveKeySection(department, u);
				// 3.设置 商业秘密事项中的 要害部门信息和部门信息
				secrecyCountryItem.setKeySectionId(keySection);
				secrecyCountryItem.setDepartId(department);
			}
		} else {// 不是要害部门
			secrecyCountryItem.setKeySectionId(null);// 设置要害部门位空
			String departmentId = secrecyCountryItem.getDepartId()
					.getDepartmentId();
			if (departmentId == null || "".equals(departmentId)) {// 是在系统中不存在
																	// 的部门
				String departmentName = this.getRequest().getParameter(
						"departmentName");// 获取部门的名称
				Department department = secrecyCountryItemService
						.saveDepartmentByName(departmentName, u);// 保存部门
				secrecyCountryItem.setDepartId(department);
			}
		}

		// 处理定密负责人
		String userid = secrecyCountryItem.getFormulateSecrecyPerson()
				.getUserInfoId();
		if (userid == null || "".equals(userid)) {// 系统中不存在该定密负责人
			UserInfo secrecyPerson = secrecyCountryItemService
					.saveSecrecyPerson(
							secrecyCountryItem.getFormulateSecrecyPerson(), u);
			secrecyCountryItem.setFormulateSecrecyPerson(secrecyPerson);
		}

		// 根据保密期限的类型 设置值
		int secrecyLimittype = secrecyCountryItem.getSecrecyLimitType();// 保密期限的类型
		// 选择 "1时间区间”，只填写“保密期限起”、“保密期限止”;
		// 选择“2解密时间”，只填写“保密期限止”;
		// 选择“3解密条件”或者“4其他（长期）”，只填写“解密条件”;
		if (secrecyLimittype == 1) { // 时间区间
			secrecyCountryItem.setRemoveSecrecyCondition(null);// 解密条件为空
		} else if (secrecyLimittype == 2) {// 解密时间
			secrecyCountryItem.setRemoveSecrecyCondition(null);// 解密条件为空
			secrecyCountryItem.setSecrecyLimitBeginDate(null);// 保密期限起时间为空
		} else {// 解密条件”或者“4其他（长期）
			secrecyCountryItem.setSecrecyLimitBeginDate(null);// 保密期限起时间为空
			secrecyCountryItem.setSecrecyLimitEndDate(null);// 保密期限止时间为空
		}

		// 保存
		secrecyCountryItem = secrecyCountryItemService
				.saveOrUpdate(secrecyCountryItem);
		if (secrecyCountryItem != null) {
			this.putToRequest("needReload", Boolean.TRUE);
		} else {
			this.putToRequest("needReload", Boolean.FALSE);
		}
		this.putToRequest("secrecyCountryItem", secrecyCountryItem);
		BusinessLog log = new BusinessLog();
		log.setBusinessName("商业秘密事项");
		log.setPrimaryKey(secrecyCountryItem.getSecrecyCountryItemId());
		BusinessLogContext.getInstance().getBusinessLogService().saveEditBusinessLogByModule(getCurrentUser(), log, secrecyCountryItem, beforesci);
		return SUCCESS;
	}

	/**
	 * 商业秘密事项的 删除
	 *
	 * @return
	 */
	public String del() {

		String secrecyCountryItemIds = this.getRequest().getParameter(
				"secrecyCountryItemIds");
		if (secrecyCountryItemIds != null && !"".equals(secrecyCountryItemIds)) {
			secrecyCountryItemService.deleteBatchIds(secrecyCountryItemIds);
		}

		this.addActionMessage(getMessageConstant().getDeleteSuccess());
		for( String id : secrecyCountryItemIds.split(",") ){
                BusinessLog log = new BusinessLog();
                log.setBusinessName("商业秘密事项");
                log.setPrimaryKey(id);
                BusinessLogContext.getInstance().getBusinessLogService().saveDelBusinessLogByModule(getCurrentUser(), log, new SecrecyCountryItem());
		}
		return LIST;
	}

	/**
	 * 进入 密级变更
	 *
	 * @return
	 */
	public String change() {

		String pkId = secrecyCountryItem.getSecrecyCountryItemId();// 获取id
		secrecyCountryItem = secrecyCountryItemService.get(pkId);// 通过id查询出对象

		// 构建密级变更对象
		SecrecyCountryItemChange obj = new SecrecyCountryItemChange();
		obj.setSecrecyCountryItem(secrecyCountryItem);
		obj.setBeforeLevel(secrecyCountryItem.getSecrecyLevel());
		obj.setChangeDate(new Date());

		this.putToRequest("secrecyCountryItemChange", obj);
		return SUCCESS;
	}

	/**
	 * 密级变更
	 *
	 * @return
	 */
	public String changeing() {
		// 设置创建信息
		secrecyCountryItemChange.setCreatePerson(getCurrentUser());
		secrecyCountryItemChange.setCreateDate(new Date());
		SecrecyCountryItem beforesci=null;
		SecrecyCountryItem fk=null;
		try {
			// 保存 密级变更对象
			SecrecyCountryItemChange obj = secrecyCountryItemChangeService
					.save(secrecyCountryItemChange);

			// 变更商业秘密事项的密级
			fk = secrecyCountryItemService.get(obj
					.getSecrecyCountryItem().getSecrecyCountryItemId());
			beforesci=new SecrecyCountryItem();
			BeanUtils.copyProperties(beforesci, fk, CopyRuleEnum.ignoreCaseNull);

			fk.setSecrecyLevel(obj.getAfterLevel());
			secrecyCountryItemService.saveOrUpdate(fk);

		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage("商业秘密事项密级变更失败！");
			this.putToRequest("needReload", Boolean.FALSE);
		}

		addActionMessage("商业秘密事项密级变更成功！");
		this.putToRequest("needReload", Boolean.TRUE);
		BusinessLog log = new BusinessLog();
		log.setBusinessName("商业秘密事项");
		log.setPrimaryKey(fk.getSecrecyCountryItemId());
		BusinessLogContext.getInstance().getBusinessLogService().saveEditBusinessLogByModule(getCurrentUser(), log, fk, beforesci);
		return SUCCESS;
	}

	/**
	 * 进入 密级解除
	 *
	 * @return
	 */
	public String clear() {
		String pkId = secrecyCountryItem.getSecrecyCountryItemId();// 获取id
		secrecyCountryItem = secrecyCountryItemService.get(pkId);// 通过id查询出对象

		// 构建
		SecrecyCountryItemClear obj = new SecrecyCountryItemClear();
		obj.setSecrecyCountryItem(secrecyCountryItem);
		obj.setClearTime(new Date());

		this.putToRequest("secrecyCountryItemClear", obj);
		return SUCCESS;
	}

	/**
	 * 密级解除
	 *
	 * @return
	 */
	public String clearing() {

		// 设置创建信息
		secrecyCountryItemClear.setCreatePerson(getCurrentUser());
		secrecyCountryItemClear.setCreateDate(new Date());
		SecrecyCountryItem beforesci=null;
		SecrecyCountryItem fk=null;

		try {
			// 保存解密
			SecrecyCountryItemClear obj = secrecyCountryItemClearService
					.save(secrecyCountryItemClear);

			// 商业秘密事项解密
			fk = secrecyCountryItemService.get(obj
					.getSecrecyCountryItem().getSecrecyCountryItemId());
			beforesci=new SecrecyCountryItem();
			BeanUtils.copyProperties(beforesci, fk, CopyRuleEnum.ignoreCaseNull);
			fk.setSecrecyStatus(1);
			secrecyCountryItemService.saveOrUpdate(fk);

		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage("商业秘密事项密级解除失败！");
			this.putToRequest("needReload", Boolean.FALSE);
		}

		addActionMessage("商业秘密事项密级解除成功！");
		this.putToRequest("needReload", Boolean.TRUE);
		BusinessLog log = new BusinessLog();
		log.setBusinessName("商业秘密事项");
		log.setPrimaryKey(fk.getSecrecyCountryItemId());
		BusinessLogContext.getInstance().getBusinessLogService().saveEditBusinessLogByModule(getCurrentUser(), log, fk, beforesci);
		return SUCCESS;
	}

	/**
	 * 变更明细
	 *
	 * @return
	 */
	public String changeDetial() {
		// 获取变更对象
		secrecyCountryItemChange = secrecyCountryItemChangeService
				.get(secrecyCountryItemChange.getSecrecyChangeId());
		// 商业秘密事项
		secrecyCountryItem = secrecyCountryItemChange.getSecrecyCountryItem();

		this.putToRequest("secrecyCountryItemChange", secrecyCountryItemChange);
		this.putToRequest("secrecyCountryItem", secrecyCountryItem);
		return SUCCESS;
	}

	/**
	 * 解密明细
	 *
	 * @return
	 */
	public String clearDetial() {
		Organ organ = this.getCurrentUser().getOrgan();
		// 获取解除对象
		secrecyCountryItemClear = secrecyCountryItemClearService
				.get(secrecyCountryItemClear.getSecrecyContryClearId());

		// 商业秘密事项
		secrecyCountryItem = secrecyCountryItemClear.getSecrecyCountryItem();

		// 查询 商业秘密事项 变更记录
		if (secrecyCountryItemChange == null) {
			secrecyCountryItemChange = new SecrecyCountryItemChange();
		}
		secrecyCountryItemChange.setSecrecyCountryItem(secrecyCountryItem);
		List<SecrecyCountryItemChange> secrecyCountryItemChangeList = new ArrayList<SecrecyCountryItemChange>();
		//业务标志 1查询模块  0普通业务模块
		String ywFlag = this.getRequest().getParameter("ywFlag");
		if(ywFlag!=null && ywFlag.equals("1")){
			//通过商业秘密事项对象，该商业秘密事项适用的组织对象
			organ = secrecyCountryItem.getCreateOrgan();
		}
		secrecyCountryItemChangeList = secrecyCountryItemChangeService
				.queryCountryItemChangeList(null, organ,
						secrecyCountryItemChange, null, 0);


		this.putToRequest("secrecyCountryItemChangeList",
				secrecyCountryItemChangeList);
		this.putToRequest("secrecyCountryItemChange", secrecyCountryItemChange);
		this.putToRequest("secrecyCountryItemClear", secrecyCountryItemClear);
		this.putToRequest("secrecyCountryItem", secrecyCountryItem);
		return SUCCESS;
	}

	/**
	 * 商业秘密事项明细
	 *
	 * @return
	 */
	public String detail() {
		Organ organ = this.getCurrentUser().getOrgan();
		District district = organ.getDistrict();
		// 获取商业秘密事项对象
		secrecyCountryItem = secrecyCountryItemService.get(secrecyCountryItem
				.getSecrecyCountryItemId());

		// 设置密级变更对象
		if (secrecyCountryItemChange == null) {
			secrecyCountryItemChange = new SecrecyCountryItemChange();
		}
		// 将商业秘密事项对象设置 到 变更对象中
		secrecyCountryItemChange.setSecrecyCountryItem(secrecyCountryItem);
		// 查询 商业秘密事项 变更记录
		List<SecrecyCountryItemChange> secrecyCountryItemChangeList = null;
		if(!organ.getOrganId().equals(secrecyCountryItem.getCreateOrgan().getOrganId())) {
			secrecyCountryItemChangeList = secrecyCountryItemChangeService
					.queryCountryItemChangeList(null, null,
							secrecyCountryItemChange, district, 1);
		}else {
			secrecyCountryItemChangeList = secrecyCountryItemChangeService
					.queryCountryItemChangeList(null, organ,
							secrecyCountryItemChange, null, 0);
		}

		this.putToRequest("secrecyCountryItemChangeList",
				secrecyCountryItemChangeList);
		this.putToRequest("secrecyCountryItemChange", secrecyCountryItemChange);
		this.putToRequest("secrecyCountryItem", secrecyCountryItem);
		return SUCCESS;
	}

	/**
	 * 通过ajax查询 商业秘密事项表 中的数据被哪些表所引用 传递参数 secrecyCountryItemId -> 商业秘密事项id
	 *
	 * @return
	 */
	public String ajax_relationship() {

		String ids = this.getRequest().getParameter("secrecyCountryItemId");
		Integer iValue = 0;
		String message = "";

		if (ids != null && !ids.equals("")) {
			String[] rgs = ids.split(",");
			if (rgs != null) {
				for (int i = 0; i < rgs.length; i++) {
					String id = rgs[i];

					iValue = secrecyCountryItemService
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

		List<SecrecyCountryItem> secrecyCountryItemList = new ArrayList<SecrecyCountryItem>();
		;

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
			if (districtCode == null) {
				district = this.getCurrentUser().getOrgan().getDistrict();
			} else {
				district = districtService.get(districtCode);
			}
			secrecyCountryItemList = secrecyCountryItemService
					.queryCountryItemList(null, district,
							Integer.parseInt(isChildren), secrecyCountryItem);

		} else {
			Organ organ = this.getCurrentUser().getOrgan();
			secrecyCountryItemList = secrecyCountryItemService
					.queryCountryItemList(null, organ, secrecyCountryItem);// 查询
																			// 商业秘密事项
		}
		this.putToRequest("secrecyCountryItemList", secrecyCountryItemList);

		Map<String, Object> params = new HashMap<String, Object>();
		// 被导出的数据
		params.put("secrecyCountryItemList", secrecyCountryItemList);
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
	 * (本单位) 按照单位显示 本单位 统计 商业秘密
	 *
	 * @return
	 */
	public String count_Organ_CountrySecrecy() {

		//年度过滤控件
		YearControlDto yearStatProvider = YearStatProvider.getIntance(this.getRequest());//初始化
		this.putToRequest("yearStatProvider", yearStatProvider);

		// 处理单位id信息
		Organ organ = new Organ();
		if (getRequest().getParameter("organId") != null
				&& !"".equals(getRequest().getParameter("organId"))) {
			String organId = getRequest().getParameter("organId");
			organ.setOrganId(organId);
		} else {
			organ = getCurrentUser().getOrgan();
		}

		// 获取初始化查询条件
		CountrySecrecyQueryObject obj = CountrySecrecyQueryObject
				.organ_CountrySecrecy_Count();
		obj.setBeginDate(yearStatProvider.getBeginDate());
		obj.setEndDate(yearStatProvider.getEndDate());

		// 统计查询商业秘密 按照单位显示 按照单位显示不需要时间参数
		List<Map<String, Object>> countrySecrecyList = secrecyCountryItemService
				.getOrganCountrySecrecy(organ, obj);

		// 统计查询商业秘密 按照部门显示 按照部门显示需要时间参数
		List<Map<String, Object>> countrySecrecy_deaprtmentList = secrecyCountryItemService
				.getOrganCountrySecrecy_ByDepartment(organ, obj);

		this.putToRequest("countrySecrecyList", countrySecrecyList);
		this.putToRequest("countrySecrecy_deaprtmentList",
				countrySecrecy_deaprtmentList);
		return SUCCESS;
	}

	/**
	 * (本单位) 按照单位显示 导出 本单位 统计 商业秘密
	 *
	 * @return
	 */
	public String export_Organ_CountrySecrecy() {

		//年度过滤控件
		YearControlDto yearStatProvider = YearStatProvider.getIntance(this.getRequest());//初始化
		this.putToRequest("yearStatProvider", yearStatProvider);

		// 处理单位id信息
		Organ organ = new Organ();
		if (getRequest().getParameter("organId") != null
				&& !"".equals(getRequest().getParameter("organId"))) {
			String organId = getRequest().getParameter("organId");
			organ.setOrganId(organId);
		} else {
			organ = getCurrentUser().getOrgan();
		}

		// 获取初始化查询条件
		CountrySecrecyQueryObject obj = CountrySecrecyQueryObject
				.organ_CountrySecrecy_Count();
		obj.setBeginDate(yearStatProvider.getBeginDate());
		obj.setEndDate(yearStatProvider.getEndDate());

		// 统计查询商业秘密 按照单位显示
		List<Map<String, Object>> countrySecrecyList = secrecyCountryItemService
				.getOrganCountrySecrecy(organ, obj);

		// 统计查询商业秘密 按照部门显示 按照部门显示需要时间参数
		List<Map<String, Object>> countrySecrecy_deaprtmentList = secrecyCountryItemService
				.getOrganCountrySecrecy_ByDepartment(organ, obj);

		// 导出设置
		Map<String, Object> params = new HashMap<String, Object>();
		// 被导出的数据
		params.put("countrySecrecyList", countrySecrecyList);
		params.put("countrySecrecy_deaprtmentList",
				countrySecrecy_deaprtmentList);
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
	 * (本单位) 新增商业秘密 本单位 统计 新增商业秘密
	 *
	 * @return
	 */
	public String count_Organ_CountrySecrecy_Add() {

		//年度过滤控件
		YearControlDto yearStatProvider = YearStatProvider.getIntance(this.getRequest());//初始化
		this.putToRequest("yearStatProvider", yearStatProvider);

		// 处理单位id信息
		Organ organ = new Organ();
		if (getRequest().getParameter("organId") != null
				&& !"".equals(getRequest().getParameter("organId"))) {
			String organId = getRequest().getParameter("organId");
			organ.setOrganId(organId);
		} else {
			organ = getCurrentUser().getOrgan();
		}

		// 获取初始化查询条件
		CountrySecrecyQueryObject obj = CountrySecrecyQueryObject
				.organ_CountrySecrecy_New_Add();
		obj.setBeginDate(yearStatProvider.getBeginDate());
		obj.setEndDate(yearStatProvider.getEndDate());


		// 统计查询商业秘密
		List<Map<String, Object>> countrySecrecyList = secrecyCountryItemService
				.getOrganCountrySecrecy(organ, obj);

		this.putToRequest("countrySecrecyList", countrySecrecyList);
		return SUCCESS;
	}

	/**
	 * (本单位) 新增商业秘密 导出 本单位 统计 新增商业秘密
	 *
	 * @return
	 */
	public String export_Organ_CountrySecrecy_Add() {

		//年度过滤控件
		YearControlDto yearStatProvider = YearStatProvider.getIntance(this.getRequest());//初始化
		this.putToRequest("yearStatProvider", yearStatProvider);

		// 处理单位id信息
		Organ organ = new Organ();
		if (getRequest().getParameter("organId") != null
				&& !"".equals(getRequest().getParameter("organId"))) {
			String organId = getRequest().getParameter("organId");
			organ.setOrganId(organId);
		} else {
			organ = getCurrentUser().getOrgan();
		}

		// 获取初始化查询条件
		CountrySecrecyQueryObject obj = CountrySecrecyQueryObject
				.organ_CountrySecrecy_New_Add();
		obj.setBeginDate(yearStatProvider.getBeginDate());
		obj.setEndDate(yearStatProvider.getEndDate());

		// 统计查询商业秘密
		List<Map<String, Object>> countrySecrecyList = secrecyCountryItemService
				.getOrganCountrySecrecy(organ, obj);

		// 导出设置
		Map<String, Object> params = new HashMap<String, Object>();
		// 被导出的数据
		params.put("countrySecrecyList", countrySecrecyList);
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
	 * (本单位) 新解商业秘密 本单位 统计 新解商业秘密
	 *
	 * @return
	 */
	public String count_Organ_CountrySecrecy_Clear() {

		//年度过滤控件
		YearControlDto yearStatProvider = YearStatProvider.getIntance(this.getRequest());//初始化
		this.putToRequest("yearStatProvider", yearStatProvider);

		// 处理单位id信息
		Organ organ = new Organ();
		if (getRequest().getParameter("organId") != null
				&& !"".equals(getRequest().getParameter("organId"))) {
			String organId = getRequest().getParameter("organId");
			organ.setOrganId(organId);
		} else {
			organ = getCurrentUser().getOrgan();
		}

		// 获取初始化查询条件
		CountrySecrecyQueryObject obj = CountrySecrecyQueryObject
				.organ_CountrySecrecy_New_Clear();
		obj.setBeginDate(yearStatProvider.getBeginDate());
		obj.setEndDate(yearStatProvider.getEndDate());

		// 统计查询商业秘密
		List<Map<String, Object>> countrySecrecyList = secrecyCountryItemService
				.getOrganCountrySecrecy(organ, obj);

		this.putToRequest("countrySecrecyList", countrySecrecyList);
		return SUCCESS;
	}

	/**
	 * (本单位) 新解商业秘密 导出 本单位 统计 新解商业秘密
	 *
	 * @return
	 */
	public String export_Organ_CountrySecrecy_Clear() {

		//年度过滤控件
		YearControlDto yearStatProvider = YearStatProvider.getIntance(this.getRequest());//初始化
		this.putToRequest("yearStatProvider", yearStatProvider);

		// 处理单位id信息
		Organ organ = new Organ();
		if (getRequest().getParameter("organId") != null
				&& !"".equals(getRequest().getParameter("organId"))) {
			String organId = getRequest().getParameter("organId");
			organ.setOrganId(organId);
		} else {
			organ = getCurrentUser().getOrgan();
		}

		// 获取初始化查询条件
		CountrySecrecyQueryObject obj = CountrySecrecyQueryObject
				.organ_CountrySecrecy_New_Clear();
		obj.setBeginDate(yearStatProvider.getBeginDate());
		obj.setEndDate(yearStatProvider.getEndDate());

		// 统计查询商业秘密
		List<Map<String, Object>> countrySecrecyList = secrecyCountryItemService
				.getOrganCountrySecrecy(organ, obj);

		// 导出设置
		Map<String, Object> params = new HashMap<String, Object>();
		// 被导出的数据
		params.put("countrySecrecyList", countrySecrecyList);
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
	 * （行政区划） 行政区划 查询 商业秘密统计
	 *
	 * @return
	 */
	public String count_CountrySecrecy_District() {

		//年度过滤控件
		YearControlDto yearStatProvider = YearStatProvider.getIntance(this.getRequest());//初始化
		this.putToRequest("yearStatProvider", yearStatProvider);

		// 处理行政区划
		String districtCode = this.getRequest().getParameter("districtCode");// 行政区划编码
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = districtService.get(districtCode);
		}

		// 获取初始化查询条件
		CountrySecrecyQueryObject obj = CountrySecrecyQueryObject
				.organ_CountrySecrecy_Count();
		obj.setBeginDate(yearStatProvider.getBeginDate());
		obj.setEndDate(yearStatProvider.getEndDate());

		List<Map<String, Object>> countrySecrecyList = secrecyCountryItemService
				.getCountrySecrecy_District(district, obj);

		this.putToRequest("countrySecrecyList", countrySecrecyList);
		return SUCCESS;
	}

	/**
	 * （直辖单位） 行政区划 查询 商业秘密统计
	 *
	 * @return
	 */
	public String count_CountrySecrecy_Layer() {

		//年度过滤控件
		YearControlDto yearStatProvider = YearStatProvider.getIntance(this.getRequest());//初始化
		this.putToRequest("yearStatProvider", yearStatProvider);

		// 处理行政区划
		String districtCode = this.getRequest().getParameter("districtCode");// 行政区划编码
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = districtService.get(districtCode);
		}

		// 获取初始化查询条件
		CountrySecrecyQueryObject obj = CountrySecrecyQueryObject
				.organ_CountrySecrecy_Count();
		obj.setBeginDate(yearStatProvider.getBeginDate());
		obj.setEndDate(yearStatProvider.getEndDate());

		List<Map<String, Object>> countrySecrecyList = secrecyCountryItemService
				.getCountrySecrecy_Layer(district, obj);

		this.putToRequest("countrySecrecyList", countrySecrecyList);
		return SUCCESS;
	}

	/**
	 * （单位明细） 行政区划 查询 商业秘密统计
	 *
	 * @return
	 */
	public String count_CountrySecrecy_OrganDetail() {
		// 处理行政区划
		String districtCode = this.getRequest().getParameter("districtCode");// 行政区划编码
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = districtService.get(districtCode);
		}

		// 获取初始化查询条件
		CountrySecrecyQueryObject obj = CountrySecrecyQueryObject
				.organ_CountrySecrecy_Count();
		List<Map<String, Object>> countrySecrecyList = secrecyCountryItemService
				.getCountrySecrecy_OrganDetail(district, obj);

		this.putToRequest("countrySecrecyList", countrySecrecyList);
		return SUCCESS;
	}

	/**
	 * （行政区划） 行政区划 查询 新增商业秘密统计
	 *
	 * @return
	 */
	public String count_AddCountrySecrecy_District() {

		//年度过滤控件
		YearControlDto yearStatProvider = YearStatProvider.getIntance(this.getRequest());//初始化
		this.putToRequest("yearStatProvider", yearStatProvider);

		// 处理行政区划
		String districtCode = this.getRequest().getParameter("districtCode");// 行政区划编码
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = districtService.get(districtCode);
		}

		// 获取初始化查询条件
		CountrySecrecyQueryObject obj = CountrySecrecyQueryObject
				.organ_CountrySecrecy_New_Add();
		obj.setBeginDate(yearStatProvider.getBeginDate());
		obj.setEndDate(yearStatProvider.getEndDate());

		List<Map<String, Object>> countrySecrecyList = secrecyCountryItemService
				.getCountrySecrecy_District(district, obj);

		this.putToRequest("countrySecrecyList", countrySecrecyList);
		return SUCCESS;
	}

	/**
	 * （直辖单位） 行政区划 查询 新增商业秘密统计
	 *
	 * @return
	 */
	public String count_AddCountrySecrecy_Layer() {

		//年度过滤控件
		YearControlDto yearStatProvider = YearStatProvider.getIntance(this.getRequest());//初始化
		this.putToRequest("yearStatProvider", yearStatProvider);

		// 处理行政区划
		String districtCode = this.getRequest().getParameter("districtCode");// 行政区划编码
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = districtService.get(districtCode);
		}

		// 获取初始化查询条件
		CountrySecrecyQueryObject obj = CountrySecrecyQueryObject
				.organ_CountrySecrecy_New_Add();
		obj.setBeginDate(yearStatProvider.getBeginDate());
		obj.setEndDate(yearStatProvider.getEndDate());

		List<Map<String, Object>> countrySecrecyList = secrecyCountryItemService
				.getCountrySecrecy_Layer(district, obj);

		this.putToRequest("countrySecrecyList", countrySecrecyList);
		return SUCCESS;
	}

	/**
	 * （行政区划） 行政区划 查询 新解除 商业秘密统计
	 *
	 * @return
	 */
	public String count_ClearCountrySecrecy_District() {

		//年度过滤控件
		YearControlDto yearStatProvider = YearStatProvider.getIntance(this.getRequest());//初始化
		this.putToRequest("yearStatProvider", yearStatProvider);

		// 处理行政区划
		String districtCode = this.getRequest().getParameter("districtCode");// 行政区划编码
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = districtService.get(districtCode);
		}

		// 获取初始化查询条件
		CountrySecrecyQueryObject obj = CountrySecrecyQueryObject
				.organ_CountrySecrecy_New_Clear();
		obj.setBeginDate(yearStatProvider.getBeginDate());
		obj.setEndDate(yearStatProvider.getEndDate());

		List<Map<String, Object>> countrySecrecyList = secrecyCountryItemService
				.getCountrySecrecy_District(district, obj);

		this.putToRequest("countrySecrecyList", countrySecrecyList);
		return SUCCESS;
	}

	/**
	 * （直辖单位） 行政区划 查询 新解除 商业秘密统计
	 *
	 * @return
	 */
	public String count_ClearCountrySecrecy_Layer() {

		//年度过滤控件
		YearControlDto yearStatProvider = YearStatProvider.getIntance(this.getRequest());//初始化
		this.putToRequest("yearStatProvider", yearStatProvider);

		// 处理行政区划
		String districtCode = this.getRequest().getParameter("districtCode");// 行政区划编码
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = districtService.get(districtCode);
		}

		// 获取初始化查询条件
		CountrySecrecyQueryObject obj = CountrySecrecyQueryObject
				.organ_CountrySecrecy_New_Clear();
		obj.setBeginDate(yearStatProvider.getBeginDate());
		obj.setEndDate(yearStatProvider.getEndDate());

		List<Map<String, Object>> countrySecrecyList = secrecyCountryItemService
				.getCountrySecrecy_Layer(district, obj);

		this.putToRequest("countrySecrecyList", countrySecrecyList);
		return SUCCESS;
	}

	/**
	 * 导出（行政区划） 行政区划 导出 商业秘密统计
	 *
	 * @return
	 */
	public String export_CountrySecrecy_District() {

		//年度过滤控件
		YearControlDto yearStatProvider = YearStatProvider.getIntance(this.getRequest());//初始化
		this.putToRequest("yearStatProvider", yearStatProvider);

		// 处理行政区划
		String districtCode = this.getRequest().getParameter("districtCode");// 行政区划编码
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = districtService.get(districtCode);
		}

		// 获取初始化查询条件
		CountrySecrecyQueryObject obj = CountrySecrecyQueryObject
				.organ_CountrySecrecy_Count();
		obj.setBeginDate(yearStatProvider.getBeginDate());
		obj.setEndDate(yearStatProvider.getEndDate());

		List<Map<String, Object>> countrySecrecyList = secrecyCountryItemService
				.getCountrySecrecy_District(district, obj);

		// 导出设置
		Map<String, Object> params = new HashMap<String, Object>();
		// 被导出的数据
		params.put("countrySecrecyList", countrySecrecyList);
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
	 * 导出（直辖单位） 行政区划 导出 商业秘密统计
	 *
	 * @return
	 */
	public String export_CountrySecrecy_Layer() {

		//年度过滤控件
		YearControlDto yearStatProvider = YearStatProvider.getIntance(this.getRequest());//初始化
		this.putToRequest("yearStatProvider", yearStatProvider);

		// 处理行政区划
		String districtCode = this.getRequest().getParameter("districtCode");// 行政区划编码
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = districtService.get(districtCode);
		}

		// 获取初始化查询条件
		CountrySecrecyQueryObject obj = CountrySecrecyQueryObject
				.organ_CountrySecrecy_Count();
		obj.setBeginDate(yearStatProvider.getBeginDate());
		obj.setEndDate(yearStatProvider.getEndDate());

		List<Map<String, Object>> countrySecrecyList = secrecyCountryItemService
				.getCountrySecrecy_Layer(district, obj);

		// 导出设置
		Map<String, Object> params = new HashMap<String, Object>();
		// 被导出的数据
		params.put("countrySecrecyList", countrySecrecyList);
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
	 * 导出（单位明细） 行政区划 导出 商业秘密统计
	 *
	 * @return
	 */
	public String export_CountrySecrecy_OrganDetail() {
		// 处理行政区划
		String districtCode = this.getRequest().getParameter("districtCode");// 行政区划编码
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = districtService.get(districtCode);
		}

		// 获取初始化查询条件
		CountrySecrecyQueryObject obj = CountrySecrecyQueryObject
				.organ_CountrySecrecy_Count();
		List<Map<String, Object>> countrySecrecyList = secrecyCountryItemService
				.getCountrySecrecy_OrganDetail(district, obj);

		// 导出设置
		Map<String, Object> params = new HashMap<String, Object>();
		// 被导出的数据
		params.put("countrySecrecyList", countrySecrecyList);
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
	 * 导出（行政区划） 行政区划 导出 新增商业秘密统计
	 *
	 * @return
	 */
	public String export_AddCountrySecrecy_District() {

		//年度过滤控件
		YearControlDto yearStatProvider = YearStatProvider.getIntance(this.getRequest());//初始化
		this.putToRequest("yearStatProvider", yearStatProvider);

		// 处理行政区划
		String districtCode = this.getRequest().getParameter("districtCode");// 行政区划编码
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = districtService.get(districtCode);
		}

		// 获取初始化查询条件
		CountrySecrecyQueryObject obj = CountrySecrecyQueryObject
				.organ_CountrySecrecy_New_Add();
		obj.setBeginDate(yearStatProvider.getBeginDate());
		obj.setEndDate(yearStatProvider.getEndDate());

		List<Map<String, Object>> countrySecrecyList = secrecyCountryItemService
				.getCountrySecrecy_District(district, obj);

		// 导出设置
		Map<String, Object> params = new HashMap<String, Object>();
		// 被导出的数据
		params.put("countrySecrecyList", countrySecrecyList);
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
	 * 导出（直辖单位） 行政区划 导出 新增商业秘密统计
	 *
	 * @return
	 */
	public String export_AddCountrySecrecy_Layer() {

		//年度过滤控件
		YearControlDto yearStatProvider = YearStatProvider.getIntance(this.getRequest());//初始化
		this.putToRequest("yearStatProvider", yearStatProvider);

		// 处理行政区划
		String districtCode = this.getRequest().getParameter("districtCode");// 行政区划编码
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = districtService.get(districtCode);
		}

		// 获取初始化查询条件
		CountrySecrecyQueryObject obj = CountrySecrecyQueryObject
				.organ_CountrySecrecy_New_Add();
		obj.setBeginDate(yearStatProvider.getBeginDate());
		obj.setEndDate(yearStatProvider.getEndDate());

		List<Map<String, Object>> countrySecrecyList = secrecyCountryItemService
				.getCountrySecrecy_Layer(district, obj);

		// 导出设置
		Map<String, Object> params = new HashMap<String, Object>();
		// 被导出的数据
		params.put("countrySecrecyList", countrySecrecyList);
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
	 * 导出（行政区划） 行政区划 导出 新解除 商业秘密统计
	 *
	 * @return
	 */
	public String export_ClearCountrySecrecy_District() {

		//年度过滤控件
		YearControlDto yearStatProvider = YearStatProvider.getIntance(this.getRequest());//初始化
		this.putToRequest("yearStatProvider", yearStatProvider);

		// 处理行政区划
		String districtCode = this.getRequest().getParameter("districtCode");// 行政区划编码
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = districtService.get(districtCode);
		}

		// 获取初始化查询条件
		CountrySecrecyQueryObject obj = CountrySecrecyQueryObject
				.organ_CountrySecrecy_New_Clear();
		obj.setBeginDate(yearStatProvider.getBeginDate());
		obj.setEndDate(yearStatProvider.getEndDate());

		List<Map<String, Object>> countrySecrecyList = secrecyCountryItemService
				.getCountrySecrecy_District(district, obj);

		// 导出设置
		Map<String, Object> params = new HashMap<String, Object>();
		// 被导出的数据
		params.put("countrySecrecyList", countrySecrecyList);
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
	 * 导出（直辖单位） 行政区划 导出 新解除 商业秘密统计
	 *
	 * @return
	 */
	public String export_ClearCountrySecrecy_Layer() {

		//年度过滤控件
		YearControlDto yearStatProvider = YearStatProvider.getIntance(this.getRequest());//初始化
		this.putToRequest("yearStatProvider", yearStatProvider);

		// 处理行政区划
		String districtCode = this.getRequest().getParameter("districtCode");// 行政区划编码
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = districtService.get(districtCode);
		}

		// 获取初始化查询条件
		CountrySecrecyQueryObject obj = CountrySecrecyQueryObject
				.organ_CountrySecrecy_New_Clear();
		obj.setBeginDate(yearStatProvider.getBeginDate());
		obj.setEndDate(yearStatProvider.getEndDate());

		List<Map<String, Object>> countrySecrecyList = secrecyCountryItemService
				.getCountrySecrecy_Layer(district, obj);

		// 导出设置
		Map<String, Object> params = new HashMap<String, Object>();
		// 被导出的数据
		params.put("countrySecrecyList", countrySecrecyList);
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
	 * (本单位) 其他模块引用的 商业秘密事项统计
	 *
	 * @return
	 */
	public String count_OrganSecrecyCountryItim() {

		// 处理单位id信息
		Organ organ = new Organ();
		if (getRequest().getParameter("organId") != null
				&& !"".equals(getRequest().getParameter("organId"))) {
			String organId = getRequest().getParameter("organId");
			organ.setOrganId(organId);
		} else {
			organ = getCurrentUser().getOrgan();
		}

		// 要害部门生成的 商业秘密事项
		List<Map<String, Object>> countrySecrecyList = secrecyCountryItemService
				.getOrganSecrecyCountryItim_BySection(organ);

		// 系统部门生成的 商业秘密事项
		List<Map<String, Object>> countryNoSectionList = secrecyCountryItemService
				.getOrganSecrecyCountryItim_NoBySection(organ);

		this.putToRequest("countrySecrecyList", countrySecrecyList);
		this.putToRequest("countryNoSectionList", countryNoSectionList);
		return SUCCESS;
	}

	/**
	 * 导出 (本单位) 其他模块引用的 导出商业秘密事项统计
	 *
	 * @return
	 */
	public String export_OrganSecrecyCountryItim() {

		// 处理单位id信息
		Organ organ = new Organ();
		if (getRequest().getParameter("organId") != null
				&& !"".equals(getRequest().getParameter("organId"))) {
			String organId = getRequest().getParameter("organId");
			organ.setOrganId(organId);
		} else {
			organ = getCurrentUser().getOrgan();
		}

		// 要害部门生成的 商业秘密事项
		List<Map<String, Object>> countrySecrecyList = secrecyCountryItemService
				.getOrganSecrecyCountryItim_BySection(organ);

		// 系统部门生成的 商业秘密事项
		List<Map<String, Object>> countryNoSectionList = secrecyCountryItemService
				.getOrganSecrecyCountryItim_NoBySection(organ);

		// 导出设置
		Map<String, Object> params = new HashMap<String, Object>();
		// 被导出的数据
		params.put("countrySecrecyList", countrySecrecyList);
		params.put("countryNoSectionList", countryNoSectionList);
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
	 * (行政区划) 其他模块引用的 统计商业秘密事项
	 *
	 * @return
	 */
	public String count_SecrecyCountryItim_District() {
		// 处理行政区划
		String districtCode = this.getRequest().getParameter("districtCode");// 行政区划编码
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = districtService.get(districtCode);
		}

		List<Map<String, Object>> countrySecrecyList = secrecyCountryItemService
				.getSecrecyCountryItim_BySection_ByFlag(district, 1);
		this.putToRequest("countrySecrecyList", countrySecrecyList);
		return SUCCESS;
	}

	/**
	 * 导出(行政区划) 其他模块引用的 统计商业秘密事项
	 *
	 * @return
	 */
	public String export_SecrecyCountryItim_District() {
		// 处理行政区划
		String districtCode = this.getRequest().getParameter("districtCode");// 行政区划编码
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = districtService.get(districtCode);
		}

		List<Map<String, Object>> countrySecrecyList = secrecyCountryItemService
				.getSecrecyCountryItim_BySection_ByFlag(district, 1);

		// 导出设置
		Map<String, Object> params = new HashMap<String, Object>();
		// 被导出的数据
		params.put("countrySecrecyList", countrySecrecyList);
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
	 * (直辖单位) 其他模块引用的 统计商业秘密事项
	 *
	 * @return
	 */
	public String count_SecrecyCountryItim_Layer() {
		// 处理行政区划
		String districtCode = this.getRequest().getParameter("districtCode");// 行政区划编码
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = districtService.get(districtCode);
		}

		List<Map<String, Object>> countrySecrecyList = secrecyCountryItemService
				.getSecrecyCountryItim_BySection_ByFlag(district, 2);
		this.putToRequest("countrySecrecyList", countrySecrecyList);
		return SUCCESS;
	}

	/**
	 * 导出(直辖单位) 其他模块引用的 统计商业秘密事项
	 *
	 * @return
	 */
	public String export_SecrecyCountryItim_Layer() {
		// 处理行政区划
		String districtCode = this.getRequest().getParameter("districtCode");// 行政区划编码
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = districtService.get(districtCode);
		}

		List<Map<String, Object>> countrySecrecyList = secrecyCountryItemService
				.getSecrecyCountryItim_BySection_ByFlag(district, 2);

		// 导出设置
		Map<String, Object> params = new HashMap<String, Object>();
		// 被导出的数据
		params.put("countrySecrecyList", countrySecrecyList);
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
	 * (本单位) 首页统计 商业秘密事项
	 *
	 * @return
	 */
	public String indexView_SecrecyCountryItim() {
		// 本单位
		Organ organ = getCurrentUser().getOrgan();
		// 总数查询
		Map<String, Object> cmap = secrecyCountryItemService
				.getSecrecyCountryItim_Total_CurrentOrgan(organ);

		putToRequest("cmap", cmap);
		putToRequest("organ", organ);
		return SUCCESS;
	}

	/**
	 * (本单位) 商业秘密事项 明细 列表
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
		if (secrecyCountryItem == null) {
			secrecyCountryItem = new SecrecyCountryItem();
		}
		if (secrecy_level != null && !"".equals(secrecy_level)) {
			if (!secrecy_level.equals("-1")) {
				secrecyCountryItem.setSecrecyLevel(Integer
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
				"secrecyCountryItemList");
		List<SecrecyCountryItem> secrecyCountryItemList = secrecyCountryItemService
				.queryCountryItemList(psm, organ, secrecyCountryItem);

		putToRequest("secrecyCountryItemList", secrecyCountryItemList);
		putToRequest("secrecy_level", secrecy_level);
		return SUCCESS;
	}

	/**
	 * (行政区) 首页统计 商业秘密事项
	 *
	 * @return
	 */
	public String indexView_SecrecyCountryItim_District() {
		// 行政区
		District district = getCurrentUser().getOrgan().getDistrict();
		// 总数查询
		Map<String, Object> cmap = secrecyCountryItemService
				.getSecrecyCountryItim_Total_District(district, 1);

		putToRequest("cmap", cmap);
		putToRequest("district", district);
		return SUCCESS;
	}

	/**
	 * (行政区) 商业秘密事项 明细 列表
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
		if (secrecyCountryItem == null) {
			secrecyCountryItem = new SecrecyCountryItem();
		}
		if (secrecy_level != null && !"".equals(secrecy_level)) {
			if (!secrecy_level.equals("-1")) {
				secrecyCountryItem.setSecrecyLevel(Integer
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
				"secrecyCountryItemList");
		List<SecrecyCountryItem> secrecyCountryItemList = secrecyCountryItemService
				.queryCountryItemList(psm, district, 1, secrecyCountryItem);

		putToRequest("secrecyCountryItemList", secrecyCountryItemList);
		putToRequest("secrecy_level", secrecy_level);
		return SUCCESS;
	}

	/**
	 * 商业秘密事项查询 左树页面
	 *
	 * @return
	 */
	public String query_main() {
		return SUCCESS;
	}

	/*************************************** 综合统计 *******************************************/
	/**
	 * 综合统计 通过行政区划 查询商业秘密事项个数 的明细列表 包括当前行政区划 和下级行政区划 的商业秘密事项的明细
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
		List<ZongHeTongJiStatDto> secrecyCountryItemStatDtoList = secrecyCountryItemService
				.count_SecrecyCountryItim_District(districtList, false);
		// 查询 子行政区划的明细
		List<ZongHeTongJiStatDto> childrenStatDtoList = secrecyCountryItemService
				.count_SecrecyCountryItim_District(childrenDistrictList, true);

		putToRequest("secrecyCountryItemStatDtoList",
				secrecyCountryItemStatDtoList);
		putToRequest("childrenStatDtoList", childrenStatDtoList);
		putToRequest("districtCode", district.getDistrictCode());
		putToRequest("topDistrict", this.getCurrentUser().getOrgan()
				.getDistrict());

		return SUCCESS;
	}

	/**
	 * 综合统计 通过行政区划编码 统计该行政区划商业秘密事项的统计 一个单位一排数据，同时这个action提供了通过单位的名称模糊查询的功能。
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

		List<ZongHeTongJiStatDto> secrecyCountryItemStatDtoList = secrecyCountryItemService
				.count_SecrecyCountryItem_District(district, true, organ);
		putToRequest("secrecyCountryItemStatDtoList",
				secrecyCountryItemStatDtoList);
		putToRequest("district", district);
		putToRequest("topDistrict", this.getCurrentUser().getOrgan()
				.getDistrict());

		return SUCCESS;
	}

	/**
	 * 通过密级，行政区划对象或者单位对象，查询商业秘密事项对应的列表 密级是必须字段，如果点到合计上面了，这里的单位是空的，所以需要行政区划对象
	 * 如果没有点到合计，那么会使用单位的对象去查询
	 *
	 * @return
	 */
	public String zhtj_DetailList() {

		List<SecrecyCountryItem> secrecyCountryItemList = new ArrayList<SecrecyCountryItem>();
		PageSortModel psm = new PageSortModel(this.getRequest(),
				"secrecyCountryItemList");

		// 设置密级
		String secrecy_level = getRequest().getParameter("secrecyLevel");// 密级
		if (secrecyCountryItem == null) {
			secrecyCountryItem = new SecrecyCountryItem();
		}
		if (secrecy_level != null && !"".equals(secrecy_level)) {
			secrecyCountryItem.setSecrecyLevel(Integer.parseInt(secrecy_level));
		}

		String organId = getRequest().getParameter("organId"); // 单位id
		if (organId != null && !"".equals(organId)) {// 如果有单位，就用单位对象
			Organ organ = new Organ();
			organ.setOrganId(organId);
			secrecyCountryItemList = secrecyCountryItemService
					.queryCountryItemList(psm, organ, secrecyCountryItem);
		} else {// 如果没有单位对象 就需要使用 行政区划对象 也说明点到合计了
			district = districtService.get(district.getDistrictCode());
			secrecyCountryItemList = secrecyCountryItemService
					.queryCountryItemList(psm, district, secrecyCountryItem);
		}

		putToRequest("secrecyCountryItemList", secrecyCountryItemList);
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
		if (secrecyCountryItem == null) {
			secrecyCountryItem = new SecrecyCountryItem();
		}
		secrecyCountryItem.setSecrecyLevel(Integer.parseInt(secrecy_level));

		String organId = getRequest().getParameter("organId"); // 单位id
		Organ organ = organService.get(organId);
		// 查询
		List<SecrecyCountryItem> secrecyCountryItemList = new ArrayList<SecrecyCountryItem>();
		PageSortModel psm = new PageSortModel(this.getRequest(),
				"secrecyCountryItemList");
		secrecyCountryItemList = secrecyCountryItemService
				.queryCountryItemList(psm, organ, secrecyCountryItem);// 查询
																		// 商业秘密事项

		putToRequest("secrecyCountryItemList", secrecyCountryItemList);
		putToRequest("secrecy_level", secrecy_level);
		putToRequest("organ", organ);
		putToRequest("dataGetFlag", "1");
		putToRequest("ywFlag", "0");

		return SUCCESS;
	}

	/**
	 * 综合统计 通过行政区划 查询商业秘密事项个数 的明细列表 包括当前行政区划 和下级行政区划 的商业秘密事项的明细
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
		List<ZongHeTongJiStatDto> secrecyCountryItemStatDtoList = secrecyCountryItemService
				.count_SecrecyCountryItim_District(districtList, false);
		// 查询 子行政区划的明细
		List<ZongHeTongJiStatDto> childrenStatDtoList = secrecyCountryItemService
				.count_SecrecyCountryItim_District(childrenDistrictList, true);

		putToRequest("secrecyCountryItemStatDtoList",
				secrecyCountryItemStatDtoList);
		putToRequest("childrenStatDtoList", childrenStatDtoList);
		putToRequest("districtCode", district.getDistrictCode());
		putToRequest("topDistrict", this.getCurrentUser().getOrgan()
				.getDistrict());

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
		String organId = getRequest().getParameter("organId").toString();
		QueryDto queryDto = new QueryDto();
		queryDto.setYear(Integer.parseInt(getRequest().getParameter("queryDto.year").toString()));
		queryDto.setMonth(Integer.parseInt(getRequest().getParameter("queryDto.month").toString()));
		List<SecrecyCountryItem> secrecyCountryItemList = secrecyCountryItemService.queryCountryItemList(null, secrecyCountryItemService.get(organId, Organ.class), secrecyCountryItem);// 查询
		putToRequest("secrecyCountryItemList", secrecyCountryItemList);
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
		resultData.put("id", "5");
		List<SecrecyCountryItem> secrecyCountryItemList = secrecyCountryItemService.queryCountryItemList(null, getCurrentUser().getOrgan(), null);// 查询
		String msg = dataValidateService.validateData("商业秘密事项", secrecyCountryItemList, "5");
		resultData.put("msg", msg);
		setResultData(resultData);
		return JSON;
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
		List<ZongHeTongJiStatDto> secrecyCountryItemStatDtoList = secrecyCountryItemService
				.count_SecrecyCountryItim_District(districtList, false);

		// 导出设置
		Map<String, Object> params = new HashMap<String, Object>();
		// 被导出的数据
		params.put("secrecyCountryItemStatDtoList",
				secrecyCountryItemStatDtoList);
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
		List<ZongHeTongJiStatDto> childrenStatDtoList = secrecyCountryItemService
				.count_SecrecyCountryItim_District(childrenDistrictList, true);

		// 导出设置
		Map<String, Object> params = new HashMap<String, Object>();
		// 被导出的数据
		params.put("childrenStatDtoList", childrenStatDtoList);
		params.put("district", district);
		this.putToRequest("district", district);
		setResultData(params);
		return SUCCESS;
	}

	/*********************************************************************************/

	/************************************************************************************************/

	public SecrecyCountryItemChangeService getSecrecyCountryItemChangeService() {
		return secrecyCountryItemChangeService;
	}

	public void setSecrecyCountryItemChangeService(
			SecrecyCountryItemChangeService secrecyCountryItemChangeService) {
		this.secrecyCountryItemChangeService = secrecyCountryItemChangeService;
	}

	public SecrecyCountryItemClearService getSecrecyCountryItemClearService() {
		return secrecyCountryItemClearService;
	}

	public void setSecrecyCountryItemClearService(
			SecrecyCountryItemClearService secrecyCountryItemClearService) {
		this.secrecyCountryItemClearService = secrecyCountryItemClearService;
	}

	public SecrecyCountryItem getSecrecyCountryItem() {
		return secrecyCountryItem;
	}

	public void setSecrecyCountryItem(SecrecyCountryItem secrecyCountryItem) {
		this.secrecyCountryItem = secrecyCountryItem;
	}

	public SecrecyCountryItemChange getSecrecyCountryItemChange() {
		return secrecyCountryItemChange;
	}

	public void setSecrecyCountryItemChange(
			SecrecyCountryItemChange secrecyCountryItemChange) {
		this.secrecyCountryItemChange = secrecyCountryItemChange;
	}

	public SecrecyCountryItemClear getSecrecyCountryItemClear() {
		return secrecyCountryItemClear;
	}

	public void setSecrecyCountryItemClear(
			SecrecyCountryItemClear secrecyCountryItemClear) {
		this.secrecyCountryItemClear = secrecyCountryItemClear;
	}

	public SecrecyCountryItemService getSecrecyCountryItemService() {
		return secrecyCountryItemService;
	}

	public void setSecrecyCountryItemService(
			SecrecyCountryItemService secrecyCountryItemService) {
		this.secrecyCountryItemService = secrecyCountryItemService;
	}

	public KeySectionModuleService getKeySectionModuleService() {
		return keySectionModuleService;
	}

	public void setKeySectionModuleService(
			KeySectionModuleService keySectionModuleService) {
		this.keySectionModuleService = keySectionModuleService;
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
         * @param dataValidateService the dataValidateService to set
         */
        public void setDataValidateService(DataValidateService dataValidateService) {
                this.dataValidateService = dataValidateService;
        }


}
