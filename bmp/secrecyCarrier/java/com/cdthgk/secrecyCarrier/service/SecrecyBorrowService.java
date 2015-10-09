package com.cdthgk.secrecyCarrier.service;

import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.platform.permission.user.domain.User;
import com.cdthgk.secrecyCarrier.vo.SecrecyBorrow;

import ec.common.PageSortModel;

@SuppressWarnings("all")
public interface SecrecyBorrowService extends BmpServiceTemplate<SecrecyBorrow, String>{

	List getAllSecrecyBorrow();

	List getList(PageSortModel psm, SecrecyBorrow secrecyBorrow);

	public List<SecrecyBorrow> getPageList(PageSortModel psm, SecrecyBorrow secrecyBorrow, Map<String, Object> params,User user);

        public List<SecrecyBorrow>  listForSelect(PageSortModel<SecrecyBorrow> psm, SecrecyBorrow secrecyBorrow, String districtCode,
                        String includeChild);
}
