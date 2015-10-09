package com.cdthgk.bmp.keyPart.service;

import java.util.List;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.bmp.keyPart.vo.KeyPartSecrecyClear;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;

public interface KeyPartSecrecyClearService extends BmpServiceTemplate<KeyPartSecrecyClear, String>{

	/**
	 * 查询 保密要害部位的密级解除记录
	 * @param psm  分页对象
	 * @param keyPartSecrecyClear 密级解除对象
	 * @param district   行政区划
	 * @param isChildren 是否包含下级
	 *
	 * @return
	 */
	public List<KeyPartSecrecyClear> queryKeyPartClearList(PageSortModel psm, KeyPartSecrecyClear keyPartSecrecyClear,Organ organ ,District district, int isChildren);
}
