package com.cdthgk.disclosesecrecy.service;

import java.util.List;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.disclosesecrecy.vo.CaseCriticalviolationClear;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

public interface CaseCriticalviolationClearService extends BmpServiceTemplate<CaseCriticalviolationClear, String>{

	 List<CaseCriticalviolationClear> queryCaseCriticalviolationClearList(PageSortModel<CaseCriticalviolationClear> psm,
			CaseCriticalviolationClear caseCriticalviolationClear,User user);

	List<CaseCriticalviolationClear> queryCaseCriticalviolationClearList(
			PageSortModel<CaseCriticalviolationClear> psm,
			CaseCriticalviolationClear caseCriticalviolationClear,
			User currentUser, String districtCode,String includeChild);

}
