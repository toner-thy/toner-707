package com.cdthgk.bmp.dataClass.service;

import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.bmp.dataClass.vo.DataClassPro;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;

public interface DataClassProService extends BmpServiceTemplate<DataClassPro, String>{

	List<DataClassPro> getPageList(PageSortModel<DataClassPro> psm,
			Map<String, Object> params, DataClassPro dataClassPro, Organ organ);

	List<DataClassPro> queryDataClassProById(String dataClassId, String organId, PageSortModel<DataClassPro> psm);


}
