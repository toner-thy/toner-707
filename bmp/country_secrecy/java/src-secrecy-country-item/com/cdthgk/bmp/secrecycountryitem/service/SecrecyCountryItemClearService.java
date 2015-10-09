package com.cdthgk.bmp.secrecycountryitem.service;

import java.io.Serializable;
import java.util.List;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.bmp.secrecycountryitem.vo.SecrecyCountryItemClear;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;

/**
 * 国家秘密事项  密级解除接口
 * @author lwj 2013-07-15
 *
 */
public interface SecrecyCountryItemClearService extends
		BmpServiceTemplate<SecrecyCountryItemClear, Serializable> {

	/**
	 * 查询国家秘密事项 的密级解除list
	 *
	 * @param psm  分页对象
	 * @param secrecyCountryItemChange  国家秘密事项密级解除对象
	 * @param organ 单位
	 * @param district  行政区划
	 * @param isChildren 包含下级  1包含  0不包含
	 * @return
	 */
	public List<SecrecyCountryItemClear> queryCountryItemChangeList(PageSortModel psm, Organ organ,
			SecrecyCountryItemClear secrecyCountryItemClear,District district,int isChildren);
}
