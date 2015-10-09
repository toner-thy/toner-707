package com.cdthgk.secrecyFullPartTime.service;

import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.platform.permission.user.domain.User;
import com.cdthgk.secrecyFullPartTime.vo.SecrecyFullPartTime;

import ec.common.PageSortModel;
@SuppressWarnings("all")
public interface SecrecyFullPartTimeService extends BmpServiceTemplate<SecrecyFullPartTime, String>{

	List getAllSecrecyFullPartTime();

	List getList(PageSortModel psm, SecrecyFullPartTime SecrecyFullPartTime);

	public List<SecrecyFullPartTime> getPageList(PageSortModel psm, SecrecyFullPartTime SecrecyFullPartTime, Map<String, Object> params,User user);

        public List<SecrecyFullPartTime>  listForSelect(PageSortModel<SecrecyFullPartTime> psm, SecrecyFullPartTime SecrecyFullPartTime, String districtCode,
                        String includeChild);
}
