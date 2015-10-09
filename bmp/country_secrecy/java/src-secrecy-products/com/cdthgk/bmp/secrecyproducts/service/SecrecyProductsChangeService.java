package com.cdthgk.bmp.secrecyproducts.service;

import java.io.Serializable;
import java.util.List;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.bmp.secrecyproducts.vo.SecrecyProductsChange;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;

public interface SecrecyProductsChangeService  extends BmpServiceTemplate<SecrecyProductsChange, Serializable>{

	/**
	 * 查询密品变更 列表
	 * @param psm   分页对象
	 * @param secrecyProductsChange  变更对象
	 * @param organ 单位
	 * @param district  行政区划
	 * @param isChildren 包含下级  1包含  0不包含
	 *
	 * @return
	 */
	public List<SecrecyProductsChange> queryChangeList(PageSortModel psm, SecrecyProductsChange secrecyProductsChange, Organ organ,District district,int isChildren);
}
