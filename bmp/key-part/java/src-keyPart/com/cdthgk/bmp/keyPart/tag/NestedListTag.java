package com.cdthgk.bmp.keyPart.tag;

import com.cdthgk.bmp.keyPart.service.PartModuleService;
import com.cdthgk.common.lang.UUIDGenerator;
import com.cdthgk.component.easytag.core.Parameter;
import com.cdthgk.component.easytag.core.ScopesHashModel;
import com.cdthgk.platform.ui.tag.UIThemeSkinTagSupport;

/**
 * <p>
 * 标签类
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
 * copyright wpb 2012, all rights reserved.
 * </p>
 * @author wpb
 * @since 1.0
 * @version 1.0
 */
public class NestedListTag extends UIThemeSkinTagSupport {

	private static final long serialVersionUID = -7805164733643861554L;

	private PartModuleService partModuleService;

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

	/**
	 * <p>
	 * 暂无描述
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
	 * copyright wpb 2012, all rights reserved.
	 * </p>
	 * @author wpb
	 * @since 1.0
	 * @version 1.0
	 */
	@Override
	public int doAfterMergeTemplate() {
		return SKIP_BODY;
	}

	/**
	 * <p>
	 * 暂无描述
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
	 * copyright wpb 2012, all rights reserved.
	 * </p>
	 * @author wpb
	 * @since 1.0
	 * @version 1.0
	 */
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
	 * @return the partModuleService
	 */
	public PartModuleService getPartModuleService() {
		return partModuleService;
	}

	/**
	 * @param partModuleService the partModuleService to set
	 */
	public void setPartModuleService(PartModuleService partModuleService) {
		this.partModuleService = partModuleService;
	}

}
