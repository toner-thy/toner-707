package com.cdthgk.bmp.secrecyperson.action;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import com.cdthgk.bmp.core.action.BmpAction;
import com.cdthgk.bmp.secrecyperson.service.SecrecyPersonModuleService;
import com.cdthgk.bmp.secrecyperson.vo.SecrecyPerson;
import com.cdthgk.common.lang.LangUtils;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;

/**
 * <p>
 * 机关涉密人员列表标签Action类
 * </p>
 * <p>
 * 牟远洋 2012-12-16 17:01
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
 * @author mouyuanyang
 * @since 1.0
 * @version 1.0
 */
public class SecrecyPersonListAction extends BmpAction {

	private static final long serialVersionUID = 884065922675229699L;

	private final String markFlag = ",";

	private SecrecyPerson secrecyPerson;
	private SecrecyPersonModuleService secrecyPersonModuleService;
	private String secrecyPersonIds;
	private String departmentId;

	// *********************** 方 法 ***********************

	// 构造器
	/** 默认构造器 */
	public SecrecyPersonListAction() {
	}

	/**
	 * <p>
	 * 机关涉密人员选择列表
	 * </p>
	 * <p>
	 * 创建时间 2012-12-14 16:25
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
	 * @author mouyuanyang
	 * @version 1.0
	 * @return list
	 * @throws ParseException ParseException
	 */
	@SuppressWarnings("rawtypes")
	public String list() throws ParseException {

		PageSortModel psm = new PageSortModel(getRequest(), "secrecyPersonList");
		psm.setPageSize(-1);
		Organ organ = getCurrentUser().getOrgan();

		putToRequest("secrecyPersonList",
				secrecyPersonModuleService.getSecrecyPersonByDepartment(psm, departmentId, null));
		putToRequest("department", departmentId);

		return "list";
	}

	/**
	 * <p>
	 * 删除操作
	 * </p>
	 * <p>
	 * 牟远洋 2012-12-24 9:26
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
	 * @author mouyuanyang
	 * @version 1.0
	 * @return JSON
	 */
	public String delete() {
		// 检查
		if (LangUtils.isEmpty(secrecyPersonIds) || secrecyPersonIds.equals(markFlag)) {
			this.addActionMessage("您所访问的资源Id不存在，请确认后重试。");
			return this.redirectActionResult("list");
		}

		// 删除
		secrecyPersonModuleService.deleteBatchIds(secrecyPersonIds);
		// 设置信息
		this.addActionMessage(getMessageConstant().getDeleteSuccess());

		Map<String, String> resultMap = new HashMap<String, String>();

		resultMap.put("message", "删除成功!");
		resultMap.put("success", "true");
		// 将设定值返回给页面
		this.setResultData(resultMap);
		return JSON;
	}

	// ******************** Setter & Getter ********************
	/**
	 * 返回secrecyPerson
	 * @return secrecyPerson
	 */
	public SecrecyPerson getSecrecyPerson() {
		return secrecyPerson;
	}

	/**
	 * 设置secrecyPerson
	 * @param secrecyPerson secrecyPerson
	 */
	public void setSecrecyPerson(SecrecyPerson secrecyPerson) {
		this.secrecyPerson = secrecyPerson;
	}

	/**
	 * 返回secrecyPersonModuleService
	 * @return secrecyPersonModuleService
	 */
	public SecrecyPersonModuleService getSecrecyPersonModuleService() {
		return secrecyPersonModuleService;
	}

	/**
	 * 设置secrecyPersonModuleService
	 * @param secrecyPersonModuleService secrecyPersonModuleService
	 */
	public void setSecrecyPersonModuleService(
			SecrecyPersonModuleService secrecyPersonModuleService) {
		this.secrecyPersonModuleService = secrecyPersonModuleService;
	}

	/**
	 * 返回secrecyPersonIds
	 * @return secrecyPersonIds
	 */
	public String getSecrecyPersonIds() {
		return secrecyPersonIds;
	}

	/**
	 * 设置secrecyPersonIds
	 * @param secrecyPersonIds secrecyPersonIds
	 */
	public void setSecrecyPersonIds(String secrecyPersonIds) {
		this.secrecyPersonIds = secrecyPersonIds;
	}

	/**
	 * 返回departmentId
	 * @return departmentId
	 */
	public String getDepartmentId() {
		return departmentId;
	}

	/**
	 * 设置departmentId
	 * @param departmentId departmentId
	 */
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
}