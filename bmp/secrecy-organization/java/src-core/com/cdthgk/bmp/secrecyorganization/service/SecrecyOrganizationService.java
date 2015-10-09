
package com.cdthgk.bmp.secrecyorganization.service;

import com.cdthgk.bmp.secrecyorganization.model.SecrecyOrganization;
import com.cdthgk.platform.organization.model.Employee;

/**
 * <p>
 * SecrecyOrganizationService
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 * @param <O> 保密组织机构泛型
 *
 * @author 钟冀
 */
public interface SecrecyOrganizationService<O extends SecrecyOrganization<?, ?, ?>> {
	/**
	 * <p>
	 * 保密组织机构建立
	 * </p>
	 * @param secrecyOrganization 保密组织机构
	 */
	void create(O secrecyOrganization);
	/**
	 * <p>
	 * 变更责任人
	 * </p>
	 * @param secrecyOrganization 保密组织机构
	 * @param principal 新负责人
	 */
	void updatePrincipal(O secrecyOrganization, Employee principal);
}
