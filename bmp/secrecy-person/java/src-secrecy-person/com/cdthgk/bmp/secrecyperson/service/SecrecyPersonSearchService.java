package com.cdthgk.bmp.secrecyperson.service;

import java.text.ParseException;
import java.util.List;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.bmp.secrecyperson.vo.SecrecyPerson;
import com.cdthgk.platform.organization.organ.domain.Organ;

/**
 * <p>
 * 机关涉密人员 Service 接口类(模块内使用)
 * </p>
 * <p>
 * 牟远洋 2012-12-14 17:01
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
public interface SecrecyPersonSearchService extends SecrecyPersonService, BmpServiceTemplate<SecrecyPerson, String> {

	/**
	 * <p>
	 * 获取机关涉密人员查询类别
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
	 * @param userName 人员名称
	 * @param organ 当前登录人员ID
	 * @return List<SecrecyPerson>
	 * @throws ParseException ParseException
	 */
	List<SecrecyPerson> getSecrecyPersonSearchList(String userName, Organ organ) throws ParseException;

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
	 * @param secrecyPersonId 涉密人员ID
	 * @return secrecyPerson
	 */
	SecrecyPerson getSecrecyPersonInfo(String secrecyPersonId);
}