package com.cdthgk.bmp.secrecyperson.tag;

import com.cdthgk.bmp.secrecyperson.service.SecrecyPersonModuleService;
import com.cdthgk.common.lang.UUIDGenerator;
import com.cdthgk.component.easytag.core.Parameter;
import com.cdthgk.component.easytag.core.ScopesHashModel;
import com.cdthgk.platform.ui.tag.UIThemeSkinTagSupport;

/**
 * <p>
 * 机关涉密人员列表标签
 * </p>
 * <p>
 * 牟远洋 2012-12-23 17:01
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
public class SecrecyPersonListTag extends UIThemeSkinTagSupport{

	private static final long serialVersionUID = -7805164733643861554L;

	private SecrecyPersonModuleService secrecyPersonModuleService;

	@Parameter(desc = "是否启用下级权限按钮")
	private Boolean readonly;

	@Parameter(desc = "调用表单名称")
	private String formName;

	@Parameter(desc = "是否传入参数")
	private boolean parameter;

	@Parameter(desc = "参数名称")
	private String parameterText;

	@Parameter(desc = "容器ID")
	private String elId;

	// 构造器
	/** 默认构造器 */
	public SecrecyPersonListTag() {
	}

	@Override
	public int doAfterMergeTemplate() {
		return SKIP_BODY;
	}

	@Override
	public void doBeforeMergeTemplate(ScopesHashModel model) {
		model.put("id", UUIDGenerator.generateUUID32());
	}

	// ******************** Setter & Getter ********************
	/**
	 * @return the readonly
	 */
	public Boolean getReadonly() {
		return readonly;
	}

	/**
	 * @return the formName
	 */
	public String getFormName() {
		return formName;
	}

	/**
	 * @return the parameter
	 */
	public boolean isParameter() {
		return parameter;
	}

	/**
	 * @return the parameterText
	 */
	public String getParameterText() {
		return parameterText;
	}

	/**
	 * @param readonly the readonly to set
	 */
	public void setReadonly(Boolean readonly) {
		this.readonly = readonly;
	}

	/**
	 * @param formName the formName to set
	 */
	public void setFormName(String formName) {
		this.formName = formName;
	}

	/**
	 * @param parameter the parameter to set
	 */
	public void setParameter(boolean parameter) {
		this.parameter = parameter;
	}

	/**
	 * @param parameterText the parameterText to set
	 */
	public void setParameterText(String parameterText) {
		this.parameterText = parameterText;
	}

	/**
	 * @return the elId
	 */
	public String getElId() {
		return elId;
	}

	/**
	 * @param elId the elId to set
	 */
	public void setElId(String elId) {
		this.elId = elId;
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
}