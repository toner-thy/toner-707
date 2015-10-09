package com.cdthgk.secrecyCarrier.service;

import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.platform.permission.user.domain.User;
import com.cdthgk.secrecyCarrier.vo.SecrecyTechnologyPrevention;

import ec.common.PageSortModel;

@SuppressWarnings("all")
public interface SecrecyTechnologyPreventionService extends BmpServiceTemplate<SecrecyTechnologyPrevention, String>{

	List getAllSecrecyTechnologyPrevention();

	List getList(PageSortModel psm, SecrecyTechnologyPrevention SecrecyTechnologyPrevention);

	public List<SecrecyTechnologyPrevention> getPageList(PageSortModel psm, SecrecyTechnologyPrevention SecrecyTechnologyPrevention, Map<String, Object> params,User user);

        public List<SecrecyTechnologyPrevention>  listForSelect(PageSortModel<SecrecyTechnologyPrevention> psm, SecrecyTechnologyPrevention SecrecyTechnologyPrevention, String districtCode,
                        String includeChild);
}
