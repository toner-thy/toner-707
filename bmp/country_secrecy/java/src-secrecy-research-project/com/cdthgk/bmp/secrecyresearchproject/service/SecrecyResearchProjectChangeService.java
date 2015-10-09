package com.cdthgk.bmp.secrecyresearchproject.service;

import java.io.Serializable;
import java.util.List;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.bmp.secrecyresearchproject.vo.SecrecyResearchProjectChange;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;

public interface SecrecyResearchProjectChangeService extends BmpServiceTemplate<SecrecyResearchProjectChange, Serializable>{

	/**
	 * 查询涉密科研项目变更 列表
	 * @param psm   分页对象
	 * @param secrecyResearchProjectChange  变更对象
	 * @param organ 单位
	 * @param district  行政区划
	 * @param isChildren 包含下级  1包含  0不包含
	 *
	 * @return
	 */
	public List<SecrecyResearchProjectChange> queryChangeList(PageSortModel psm, SecrecyResearchProjectChange secrecyResearchProjectChange, Organ organ,District district,int isChildren);
}
