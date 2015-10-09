package com.cdthgk.bmp.secrecyresearchproject.service;

import java.io.Serializable;
import java.util.List;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.bmp.secrecyresearchproject.vo.SecrecyResearchProjectClear;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;

public interface SecrecyResearchProjectClearService extends BmpServiceTemplate<SecrecyResearchProjectClear, Serializable>{

	/**
	 * 查询涉密科研项目 的密级解除list
	 *
	 * @param psm  分页对象
	 * @param secrecyResearchProjectClear  涉密科研项目密级解除对象
	 * @param organ 单位
	 * @param district  行政区划
	 * @param isChildren 包含下级  1包含  0不包含
	 *
	 * @return
	 */
	public List<SecrecyResearchProjectClear> queryClearList(PageSortModel psm,
			SecrecyResearchProjectClear secrecyResearchProjectClear, Organ organ,District district,int isChildren);
}
