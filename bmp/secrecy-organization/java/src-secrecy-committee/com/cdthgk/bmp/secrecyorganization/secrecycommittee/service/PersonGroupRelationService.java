package com.cdthgk.bmp.secrecyorganization.secrecycommittee.service;

import java.util.List;

import com.cdthgk.bmp.core.service.BmpService;
import com.cdthgk.bmp.secrecyorganization.secrecycommittee.vo.PersonGroup;
import com.cdthgk.bmp.secrecyorganization.secrecycommittee.vo.PersonGroupRelation;

import ec.common.PageSortModel;

public interface PersonGroupRelationService  extends BmpService<PersonGroupRelation, String> {

	public List<PersonGroupRelation> getForPage(PageSortModel psm,
			PersonGroup personGroup) ;
}
