package com.cdthgk.bmp.dataClass.service.impl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.bmp.dataClass.service.DataClassProService;
import com.cdthgk.bmp.dataClass.vo.DataClassPro;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;


public class DataClassProServiceImpl extends BmpServiceImpl<DataClassPro, String> implements DataClassProService {

	@Override
	public List<DataClassPro> getPageList(PageSortModel<DataClassPro> psm,
			Map<String, Object> params, DataClassPro dataClassPro, Organ organ) {
		//查询本单位，指定分类下
		StringBuffer hql = new StringBuffer("from DataClassPro where organ.organId=:organId");
		if ("1".equals(params.get("dataClassId"))) {
			params.clear();
		} else {
			hql.append(" and dataClassId =:dataClassId");
		}

		// 时间查询
		if (dataClassPro != null
				&& !"".equals(dataClassPro.getFillTime())
				&& null != dataClassPro.getFillTime()) {
			hql.append(" and DATE_FORMAT(fillTime,'%Y-%m-%d') =:fillTime");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String fillTime = sdf.format(dataClassPro.getFillTime());
			params.put("fillTime", fillTime);
		}

		params.put("organId", organ.getOrganId());
		hql.append(" order by createTime desc");
		return findList(hql.toString(), params, psm);
	}

	@Override
	public List<DataClassPro> queryDataClassProById(String dataClassId, String organId,PageSortModel<DataClassPro> psm) {
		StringBuffer hql = new StringBuffer("from DataClassPro where organ.organId=:organId and dataClass.dataClassId=:dataClassId");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("organId", organId);
		params.put("dataClassId", dataClassId);
		if(psm == null){
			return findList(hql.toString(), params);
		}
		return findList(hql.toString(), params, psm);
	}


}
