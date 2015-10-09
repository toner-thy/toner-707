package com.cdthgk.bmp.secrecyorganization.model;

import com.cdthgk.platform.organization.model.Organization;

/**
 * <p>
 * 保密办
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 * @param <O> 泛型保密办
 * @param <SO> 泛型保密组织机构
 * @param <E> 泛型雇员
 * @author 钟冀
 */
public interface SecrecyOffice<O extends SecrecyOffice<?, ?, ?>
	, SO extends SecrecyOrganization<?, ?, ?>, E extends Employee>
	extends Organization<O, E>{
	/**
	 * <p>
	 * 获取保密办对应的保密组织机构
	 * </p>
	 * @return 保密组织机构
	 */
	SO getSecrecyOrganization();
}