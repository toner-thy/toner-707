package com.cdthgk.disclosesecrecy.service;

import java.util.List;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.disclosesecrecy.vo.CaseCriticalviolationChange;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

public interface CaseCriticalviolationChangeService
extends BmpServiceTemplate<CaseCriticalviolationChange, String>{

	List<CaseCriticalviolationChange> queryCaseCriticalviolationChangelist(
			PageSortModel<CaseCriticalviolationChange> psm, CaseCriticalviolationChange caseCriticalviolationChange,User user);

	@SuppressWarnings("rawtypes")
	List<CaseCriticalviolationChange> queryCaseCriticalviolationChangelist(
			PageSortModel psm,
			CaseCriticalviolationChange caseCriticalviolationChange,
			User currentUser, String districtCode,String includeChild);


}
