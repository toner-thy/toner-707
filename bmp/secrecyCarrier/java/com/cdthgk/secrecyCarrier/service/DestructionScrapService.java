package com.cdthgk.secrecyCarrier.service;

import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.platform.permission.user.domain.User;
import com.cdthgk.secrecyCarrier.vo.DestructionScrap;

import ec.common.PageSortModel;
@SuppressWarnings("all")
public interface DestructionScrapService extends BmpServiceTemplate<DestructionScrap, String>{

	List getAllDestructionScrap();

	List getList(PageSortModel psm, DestructionScrap destructionScrap);

	public List<DestructionScrap> getPageList(PageSortModel psm, DestructionScrap destructionScrap, Map<String, Object> params,User user);

        public List<DestructionScrap>  listForSelect(PageSortModel<DestructionScrap> psm, DestructionScrap destructionScrap, String districtCode,
                        String includeChild);
}
