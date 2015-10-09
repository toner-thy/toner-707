package com.cdthgk.bmp.secrecycountryitem.service;

import java.io.Serializable;
import java.util.List;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.bmp.secrecycountryitem.vo.SecrecyCountryItemChange;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;

/**
 * 商业秘密事项   密级变更 借口
 * @author lwj 2013-07-15
 *
 */
public interface SecrecyCountryItemChangeService extends
		BmpServiceTemplate<SecrecyCountryItemChange, Serializable> {

	/**
	 * 查询商业秘密事项 的密级变更list
	 *
	 * @param psm  分页对象
	 * @param secrecyCountryItemChange  商业秘密事项密级变更对象
	 * @param organ   单位
	 * @param district  行政区划
	 * @param isChildren 包含下级  1包含  0不包含
	 * @return
	 */
	public List<SecrecyCountryItemChange> queryCountryItemChangeList(PageSortModel psm, Organ organ,
			SecrecyCountryItemChange secrecyCountryItemChange,District district,int isChildren) ;
}
