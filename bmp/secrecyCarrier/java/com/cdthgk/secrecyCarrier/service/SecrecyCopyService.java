package com.cdthgk.secrecyCarrier.service;

import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.platform.permission.user.domain.User;
import com.cdthgk.secrecyCarrier.vo.SecrecyCopy;

import ec.common.PageSortModel;

@SuppressWarnings("all")
public interface SecrecyCopyService extends BmpServiceTemplate<SecrecyCopy, String>{

	List getAllSecrecyCopy();

	List getList(PageSortModel psm, SecrecyCopy SecrecyCopy);

	public List<SecrecyCopy> getPageList(PageSortModel psm, SecrecyCopy SecrecyCopy, Map<String, Object> params,User user);

        public List<SecrecyCopy>  listForSelect(PageSortModel<SecrecyCopy> psm, SecrecyCopy SecrecyCopy, String districtCode,
                        String includeChild);

}
