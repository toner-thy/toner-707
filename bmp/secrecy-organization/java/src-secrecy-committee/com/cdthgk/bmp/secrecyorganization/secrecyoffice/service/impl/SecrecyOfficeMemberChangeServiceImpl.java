package com.cdthgk.bmp.secrecyorganization.secrecyoffice.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.bmp.secrecyorganization.secrecyoffice.service.SecrecyOfficeMemberChangeModuleService;
import com.cdthgk.bmp.secrecyorganization.secrecyoffice.vo.SecrecyOffice;
import com.cdthgk.bmp.secrecyorganization.secrecyoffice.vo.SecrecyOfficeMemberChange;
import com.cdthgk.platform.district.domain.District;

import ec.common.PageSortModel;

/**
 * <p>
 * 保密办（保密局）成员 Service 实现类
 * </p>
 * <p>
 * 陶汇源  2013-01-06 12:21:43
 * </p>
 * <blockquote>
 * <h4>历史修改记录</h4>
 * <ul>
 * <li>修改人 修改时间 修改描述
 * </ul>
 * </blockquote>
 * <p>
 * copyright cdthgk 2010-2011, all rights reserved.
 * </p>
 *
 * @author 陶汇源
 * @author cdthgk r&d
 * @since 1.0
 * @version 1.0
 */
public class SecrecyOfficeMemberChangeServiceImpl extends BmpServiceImpl<SecrecyOfficeMemberChange, String>
	implements SecrecyOfficeMemberChangeModuleService{


	// 构造器
	/** 默认构造器 */
	SecrecyOfficeMemberChangeServiceImpl() {
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<SecrecyOfficeMemberChange> getSecrecyOfficeMemberChangePageList(
			PageSortModel<SecrecyOfficeMemberChange> psm,
			SecrecyOffice secrecyOffice, District district) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("from SecrecyOfficeMemberChange som where 1=1 ");
		if(district != null && !"".equals(district.getDistrictCode())){
			// 行政区查询时所带参数
			hql.append(" and som.secrecyOfficeMember.secrecyOffice.createOrgan.district.districtCode = :districtCode");
			params.put("districtCode", district.getDistrictCode());
		}
		return (List<SecrecyOfficeMemberChange>) this.findList(hql.toString(), params, psm);
	}



}