package com.cdthgk.bmp.secrecyperson.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.bmp.secrecyperson.service.SecrecyPersonSearchService;
import com.cdthgk.bmp.secrecyperson.vo.SecrecyPerson;
import com.cdthgk.platform.organization.organ.domain.Organ;

/**
 * <p>
 * 机关涉密人员查询Service类
 * </p>
 * <p>
 * 牟远洋 2012-12-19 17:01
 * </p>
 * <blockquote>
 * <h4>历史修改记录</h4>
 * <ul>
 * <li>修改人 修改时间 修改描述
 * </ul>
 * </blockquote>
 * <p>
 * copyright tianyu 2012, all rights reserved.
 * </p>
 *
 * @author mouyuanyang
 * @since 1.0
 * @version 1.0
 */
public class SecrecyPersonSearchServiceImpl extends BmpServiceImpl<SecrecyPerson, String>
			implements SecrecyPersonSearchService{

	// 构造器
	/** 默认构造器 */
	public SecrecyPersonSearchServiceImpl() {
	}

	/**
	 * <p>
	 * 获取涉密人员查询列表
	 * </p>
	 * <p>
	 * @author 牟远洋 2012 12 23 11:00
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * <p>
	 * copyright tianyu 2012, all rights reserved.
	 * </p>
	 *
	 * @author mouyuanyang
	 * @version 1.0
	 * @param userName 用户名
	 * @param organ 当前单位
	 * @return List<SecrecyPerson>
	 */
	public List<SecrecyPerson> getSecrecyPersonSearchList(String userName, Organ organ) {

		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("from SecrecyPerson s where 1=1 ");

		// 单位
		hql.append(" and s.organ.organId = :organId");
		params.put("organId", organ.getOrganId());

		// 姓名
		hql.append(" and s.userInfo.name like :name");
		params.put("name", "%" + userName + "%");

//		return (List<SecrecyPerson>) this.getList(hql.toString(), params);
		return (List<SecrecyPerson>) this.findList(hql.toString(), params);
	}

	/**
	 * <p>
	 * 获取涉密人员信息
	 * </p>
	 * <p>
	 * @author 牟远洋 2012 12 23 11:00
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * <p>
	 * copyright tianyu 2012, all rights reserved.
	 * </p>
	 *
	 * @author mouyuanyang
	 * @version 1.0
	 * @author 牟远洋 2012 12 23 17:10:56
	 * @param secrecyPersonId 涉密人员ID
	 * @return secrecyPerson
	 */
	public SecrecyPerson getSecrecyPersonInfo(String secrecyPersonId) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("from SecrecyPerson sp where 1=1 ");

		// 查询指定人员信息
		hql.append(" and sp.secrecyPersonId = :secrecyPersonId");
		params.put("secrecyPersonId", secrecyPersonId);

		return (SecrecyPerson) getPersistProxy().getOrmPersistence().find(hql.toString(), params);
	}
}