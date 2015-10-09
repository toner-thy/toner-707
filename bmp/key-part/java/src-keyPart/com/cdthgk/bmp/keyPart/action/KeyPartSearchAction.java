package com.cdthgk.bmp.keyPart.action;

import java.util.Collection;
import java.util.List;

import com.cdthgk.bmp.keyPart.service.PartModuleService;
import com.cdthgk.bmp.keyPart.vo.Part;
import com.cdthgk.common.bean.BeanUtils;
import com.cdthgk.common.lang.LangUtils;
import com.cdthgk.component.ioc.ContextUtils;
import com.cdthgk.model.structure.autocomplete.Result;
import com.cdthgk.model.structure.autocomplete.Results;
import com.cdthgk.platform.base.action.PlatformAction;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;

public class KeyPartSearchAction extends PlatformAction{

	private static final long serialVersionUID = -1166448547167346457L;

	/** Spring注入 */
	private PartModuleService partModuleService;
	/** Struts2*/
	private Part part;


	/**
	 *  筛选保密要害部门   自动补全
	 * @return
	 */
	public String autocomplete() {
		//查询数据
		Organ organ = getCurrentUser().getOrgan();
		List<Part> partlist = partModuleService.getListPage(null, part, organ);
		//构建数据
		Results results = new Results();//数据集对象
		String dataSelector = getHeader("data-selector");
		//遍历数据
		for (Part obj : partlist) {
			Result result = new Result(obj.getPartName(), obj.getPartId());
			if (LangUtils.isNotEmpty(dataSelector)) {
				Collection<String> selector = ContextUtils.getDefaultContext().getBean(dataSelector);
				for (String sn : selector) {
					result.addData(sn, BeanUtils.getProperty(obj, sn));
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
		PageSortModel psm = new PageSortModel(getRequest(), "partList");
		Organ organ = getCurrentUser().getOrgan();
		List<Part> partlist = partModuleService.getListPage(psm, part, organ);

		this.putToRequest("partList", partlist);
		return SUCCESS;
	}




	public PartModuleService getPartModuleService() {
		return partModuleService;
	}

	public void setPartModuleService(PartModuleService partModuleService) {
		this.partModuleService = partModuleService;
	}

	public Part getPart() {
		return part;
	}

	public void setPart(Part part) {
		this.part = part;
	}



}
