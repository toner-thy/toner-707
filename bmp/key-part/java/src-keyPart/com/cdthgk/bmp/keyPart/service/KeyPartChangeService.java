package com.cdthgk.bmp.keyPart.service;

import java.util.List;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.bmp.keyPart.vo.KeyPartChange;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;

public interface KeyPartChangeService extends BmpServiceTemplate<KeyPartChange, String>{

	/**
	 * 要害部位密级变更记录  查询
	 *
	 * @param psm   分页对象
	 * @param keyPartChange   要害部位变更
	 * @param district    行政区划
	 * @param isChildren  是否查询下级
	 * @return
	 */
	public List<KeyPartChange> queryKeyPartChangeList(PageSortModel psm, KeyPartChange keyPartChange,Organ organ, District district, int isChildren);
}
