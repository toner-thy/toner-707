package com.cdthgk.bmp.dataClass.service;

import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.bmp.dataClass.vo.DataClass;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;

public interface DataClassService extends BmpServiceTemplate<DataClass, String>{

	public List<DataClass> queryListPage(PageSortModel<DataClass> psm,
			DataClass dataClass, Organ organ);

	public void publish(String dataClassId);

	public void update(DataClass dataClass, String organIds);

	public List<Map<String, Object>> getTree(Map<String, Object> params);

	public List<Map<String, Object>> queryDataClassProById(DataClass dataClass);

}
