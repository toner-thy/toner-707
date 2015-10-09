package com.cdthgk.bmp.secrecyproducts.service;

import java.io.Serializable;
import java.util.List;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.bmp.secrecyproducts.vo.SecrecyProductsClear;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;

public interface SecrecyProductsClearService extends
BmpServiceTemplate<SecrecyProductsClear, Serializable>{

	/**
	 * 查询密品 的密级解除list
	 *
	 * @param psm  分页对象
	 * @param secrecyProductsClear  密品密级解除对象
	 * @param organ 单位
	 * @param district  行政区划
	 * @param isChildren 包含下级  1包含  0不包含
	 * @return
	 */
	public List<SecrecyProductsClear> queryClearList(PageSortModel psm, Organ organ,
			SecrecyProductsClear secrecyProductsClear,District district,int isChildren);
}
