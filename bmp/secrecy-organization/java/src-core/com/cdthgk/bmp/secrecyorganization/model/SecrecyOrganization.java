package com.cdthgk.bmp.secrecyorganization.model;

import com.cdthgk.platform.organization.model.Member;
import com.cdthgk.platform.organization.model.Organization;

/**
 * <p>
 * 保密组织机构
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 * @param <SO> 保密组织机构泛型
 * @param <OF> 泛型保密办
 * @param <E> 泛型雇员
 * @author 钟冀
 */
public interface SecrecyOrganization<SO extends SecrecyOrganization<SO, ?, ?>, OF extends SecrecyOffice<?, ?, ?>
	, E extends Member> extends Organization<SO, E>{
	/**
	 * <p>
	 * 获取保密组织机构的保密办
	 * </p>
	 * @return 保密办
	 */
	OF getSecrecyOffice();
}
