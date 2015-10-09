package com.cdthgk.bmp.keysection.service;

import java.io.Serializable;
import java.util.List;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.bmp.keysection.vo.KeySectionChange;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;

/**
 * 要害部门密级变更  接口
 * @author 梁文杰  2013-07-13
 *
 */
public interface KeySectionChangeService extends BmpServiceTemplate<KeySectionChange, Serializable>{

	/**
	 * 查询保密要害部门的变更记录
	 * @param psm   页面对象
	 * @param keySectionChange  变更对象
	 * @param organ     单位对象
	 * @param district  行政区对象
	 * @param isChildren  是否包含下级  针对行政区划对象   1表示包含   0表示不包含
	 *
	 * @return
	 */
	public List<KeySectionChange> queryKeySectionChangeList(PageSortModel psm, KeySectionChange keySectionChange, Organ organ, District district, Integer isChildren);
}
