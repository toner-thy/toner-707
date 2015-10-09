package com.cdthgk.bmp.keysection.service;

import java.io.Serializable;
import java.util.List;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.bmp.keysection.vo.KeySectionSecrecyClear;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;

public interface KeySectionSecrecyClearService extends BmpServiceTemplate<KeySectionSecrecyClear, Serializable>{

	/**
	 * 查询 保密要害部门的密级解除记录
	 * @param psm  分页对象
	 * @param keySectionSecrecyClear 密级解除对象
	 * @param organ  单位对象
	 * @param district  行政区划
	 * @param isChildren  是否包含下级  针对行政区划对象   1表示包含   0表示不包含
	 *
	 * @return
	 */
	public List<KeySectionSecrecyClear> queryKeySectionClearList(PageSortModel psm, KeySectionSecrecyClear keySectionSecrecyClear, Organ organ, District district, Integer isChildren);
}
