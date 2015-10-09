package com.cdthgk.secrecyCarrier.service;

import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.platform.permission.user.domain.User;
import com.cdthgk.secrecyCarrier.vo.SecrecyMaintain;

import ec.common.PageSortModel;

@SuppressWarnings("all")
public interface SecrecyMaintainService extends BmpServiceTemplate<SecrecyMaintain, String>{

	List getAllSecrecyMaintain();

	List getList(PageSortModel psm, SecrecyMaintain SecrecyMaintain);

	public List<SecrecyMaintain> getPageList(PageSortModel psm, SecrecyMaintain SecrecyMaintain, Map<String, Object> params,User user);

        public List<SecrecyMaintain>  listForSelect(PageSortModel<SecrecyMaintain> psm, SecrecyMaintain SecrecyMaintain, String districtCode,
                        String includeChild);
}
