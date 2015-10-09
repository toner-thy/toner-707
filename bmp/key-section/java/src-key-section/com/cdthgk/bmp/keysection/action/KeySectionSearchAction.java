package com.cdthgk.bmp.keysection.action;

import java.util.Collection;
import java.util.List;

import com.cdthgk.bmp.keysection.qo.KeySectionQo;
import com.cdthgk.bmp.keysection.service.KeySectionModuleService;
import com.cdthgk.bmp.keysection.vo.KeySection;
import com.cdthgk.common.bean.BeanUtils;
import com.cdthgk.common.lang.LangUtils;
import com.cdthgk.component.easytag.core.Parameter;
import com.cdthgk.component.ioc.ContextUtils;
import com.cdthgk.model.structure.autocomplete.Result;
import com.cdthgk.model.structure.autocomplete.Results;
import com.cdthgk.platform.base.action.PlatformAction;
import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;

public class KeySectionSearchAction extends PlatformAction{

	private static final long serialVersionUID = -1166448547167346457L;

	/** Spring注入 */
	private KeySectionModuleService keySectionModuleService;
	/** Struts2*/
	private KeySectionQo keySectionQo;
	private Department department;


	/**
	 *  筛选保密要害部门   自动补全
	 * @return
	 */
	public String autocomplete() {
		//查询数据
		String depName = "";
		if(department!=null) {
			depName = department.getDepartmentName();
		}
		if(keySectionQo==null) {
			keySectionQo = new KeySectionQo();
		}
		keySectionQo.setDepartmentName(depName);
		Organ organ = getCurrentUser().getOrgan();
		//PageSortModel psm = new PageSortModel(getRequest(), "keySectionlist");
		List<KeySection> keySectionlist = keySectionModuleService.queryList(null, organ, keySectionQo);

		//构建数据
		Results results = new Results();//数据集对象
		String dataSelector = getHeader("data-selector");
		//遍历数据
		for (KeySection keySection : keySectionlist) {
			Result result = new Result(keySection.getDepartment().getDepartmentName(), keySection.getKeySectionId());
			if (LangUtils.isNotEmpty(dataSelector)) {
				Collection<String> selector = ContextUtils.getDefaultContext().getBean(dataSelector);
				for (String sn : selector) {
					result.addData(sn, BeanUtils.getProperty(keySection, sn));
				}
			}
			results.addResult(result);
		}

		setResultData(results);
		return AUTOCOMPLETE;
	}

	/**
	 * 页面
	 * @return
	 */
	public String singelSelect() {
		Organ organ = getCurrentUser().getOrgan();
		PageSortModel psm = new PageSortModel(getRequest(), "keySectionlist");
		List<KeySection> keySectionlist = keySectionModuleService.queryList(psm, organ, keySectionQo);

		this.putToRequest("keySectionlist", keySectionlist);
		return SUCCESS;
	}




	public KeySectionModuleService getKeySectionModuleService() {
		return keySectionModuleService;
	}

	public void setKeySectionModuleService(
			KeySectionModuleService keySectionModuleService) {
		this.keySectionModuleService = keySectionModuleService;
	}

	public KeySectionQo getKeySectionQo() {
		return keySectionQo;
	}

	public void setKeySectionQo(KeySectionQo keySectionQo) {
		this.keySectionQo = keySectionQo;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}


}
