package com.cdthgk.bmp.keysection.service;

import java.io.Serializable;
import java.util.List;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.bmp.keysection.qo.KeySectionQo;
import com.cdthgk.bmp.keysection.vo.KeySection;
import com.cdthgk.platform.dictionary.domain.DictionaryOption;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

/**
 * <p>
 * 要害部门Service类（模块内使用）
 * </p>
 * <p>
 * 王彭波 2012-12-14 18:01
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
 * @author wangpb
 * @since 1.0
 * @version 1.0
 */
public interface KeySectionModuleService extends BmpServiceTemplate<KeySection, Serializable> {

	/**
	 * <p>
	 * 获取要害部门列表
	 * </p>
	 * <p>
	 * 王彭波 2012-12-14 18:01
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
	 * @author wangpb
	 * @since 1.0
	 * @version 1.0
	 * @param psm PageSortModel
	 * @param organ 单位
	 * @return List<KeySection>
	 */
	@SuppressWarnings("rawtypes")
	public List<KeySection> getPageList(PageSortModel psm, Organ organ);

	/**
	 * <p>
	 * 查询列表
	 * </p>
	 * <p>
	 * 王彭波 2012-12-21 18:01
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
	 * @author wangpb
	 * @since 1.0
	 * @version 1.0
	 * @param psm PageSortModel
	 * @param organ 当前登录单位
	 * @param keySectionQo keySectionQo
	 * @return List<KeySection>
	 */
	@SuppressWarnings("rawtypes")
	public List<KeySection> queryList(PageSortModel psm, Organ organ, KeySectionQo keySectionQo);

	/**
	 *
	 * <p>
	 * 方法名：queryList 获取所有保密要害部门列表信息
	 * </p>
	 * <p>
	 * 宋亚非 2013-5-21 上午11:32:49
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@param organ
	 *@param keySectionQo
	 *@return
	 */
	public List<KeySection> queryList(Organ organ, KeySectionQo keySectionQo);

	/**
	 *
	 * <p>
	 * 方法名：getDictionaryOptionList 返回字典表内容
	 * </p>
	 * <p>
	 * 宋亚非 2013-5-21 下午1:49:09
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@param tableCode
	 *@param fieldCode
	 *@return
	 */
	public List<DictionaryOption> getDictionaryOptionList(String tableCode, String fieldCode);

	/**
	 * <p>
	 * 查询列表(首页获取关键部门查询列表)
	 * </p>
	 * <p>
	 * 宋亚非  2013-04-10 09:22
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
	 * @author wangpb
	 * @since 1.0
	 * @version 1.0
	 * @param psm PageSortModel
	 * @param organ 当前登录单位
	 * @param keySectionQo keySectionQo
	 * @return List<KeySection>
	 */
	@SuppressWarnings("rawtypes")
	public List<KeySection> queryList(PageSortModel psm, Organ organ, KeySectionQo keySectionQo, String showType);


	/**
	 * <p>
	 * 保存用户信息
	 * </p>
	 * <p>
	 * 王彭波 2012-12-21 18:01
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
	 * @author wangpb
	 * @since 1.0
	 * @version 1.0
	 * @param name 用户名称
	 * @param user 人员
	 * @return userInfo
	 */
	public UserInfo saveUserInfo(UserInfo userInfo, User user);

	/**
	 * <p>
	 * 保存部门信息
	 * </p>
	 * <p>
	 * 王彭波 2012-12-21 18:01
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
	 * @author wangpb
	 * @since 1.0
	 * @version 1.0
	 * @param departmentName 部门名称
	 * @param user 人员
	 * @return userInfo
	 */
	public Department saveDepartment(String departmentName, User user);

	/**
	 * <p>
	 * 查询部门信息
	 * </p>
	 * <p>
	 * 王彭波 2012-12-22 18:01
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
	 * @author wangpb
	 * @since 1.0
	 * @version 1.0
	 * @param departmentName 部门名称
	 * @param organ 当前登录单位
	 * @return List<KeySection>
	 */
	public int searchDepartment(String departmentName, Organ organ);

	/**
	 * <p>
	 * 查询部门信息
	 * </p>
	 * <p>
	 * 王彭波 2013-01-14 18:01
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 *
	 * @author wangpb
	 * @since 1.0
	 * @version 1.0
	 * @param organ 当前登录单位
	 * @return List<String>
	 */
	public List<Long> countSectionData(Organ organ);

	/**
	 * 保密局统计  要害部门总数
	 * @param district
	 * @return
	 */
	public List<Long> countSectionData(District district) ;

	/**
	 * <p>
	 * 上报要害部门远程执行保存
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-3-22 - 上午10:00:46
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param keySectionList
	 * @param receiveOrganId
	 */
	public void saveRecKeySection(List<KeySection> keySectionList,
			String receiveOrganId);


	/**
	 *
	 * <p>
	 * 首页统计 数字撰取
	 * </p>
	 * <p>
	 * 创建人 刘椿成 创建时间 2012 08 29 - 11:31:25
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>宋亚非  2013-04-09  从市平台迁移到省平台
	 * </ul>
	 * </blockquote>
	 * @param PageSortModel psm
	 * @param Organ organ
	 * @param Integer secrecyLevel
	 * @param String districtCode
	 * @return List<Section>
	 */
	List<KeySection> getIndexPage(PageSortModel psm, Organ organ,
			Integer secrecyLevel, String districtCode);

	/**
	 * 按照行政区划统计  要害部门
	 * @param psm
	 * @param district
	 * @param keySectionQo
	 * @param isChildren 是否包含下级  1包含  0不包含
	 * @return
	 */
	public List<KeySection> queryList(PageSortModel psm, District district,Integer isChildren,
			KeySectionQo keySectionQo) ;
}