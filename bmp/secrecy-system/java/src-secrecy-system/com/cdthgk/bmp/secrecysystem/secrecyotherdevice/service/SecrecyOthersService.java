/**
 *
 */
package com.cdthgk.bmp.secrecysystem.secrecyotherdevice.service;

import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.bmp.secrecycountryitem.qo.ZongHeTongJiStatDto;
import com.cdthgk.bmp.secrecysystem.secrecyotherdevice.vo.SecrecyOthers;
import com.cdthgk.platform.dictionary.domain.DictionaryOption;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

/**
 * <p>
 * 类的说明放这里 2013-7-31 下午1:55:04
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 *
 * @author 宋亚非
 */
public interface SecrecyOthersService extends
		BmpServiceTemplate<SecrecyOthers, String> {

	/**
	 *
	 * <p>
	 * 方法名称 列表查询
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-21 上午10:03:22
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@param psm
	 *@param secrecyOthers
	 *@param organ
	 *@param secrecyStatusFlag
	 *@return
	 */
	public List<SecrecyOthers> findPageAllList(
			PageSortModel<SecrecyOthers> psm, SecrecyOthers secrecyOthers,
			Organ organ, District district, String checkLower, Integer secrecyStatusFlag);

	/**
	 *
	 * <p>
	 * 方法名称  findPageAllList 其他涉密设备查询
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-22 下午4:47:45
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@param psm
	 *@param secrecyOthers
	 *@param district
	 *@return
	 */
	/*public List<SecrecyOthers> findPageAllList(
			PageSortModel<SecrecyOthers> psm, SecrecyOthers secrecyOthers,
			District district);*/

	/**
	 *
	 * <p>
	 * 方法名称 首页统计用，统计其他涉密设备的数据
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-21 上午10:02:10
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@param optionList
	 *@param typeList
	 *@param currentOrgan
	 *@param countType
	 *@return
	 */
	public Map<Integer,Map<Integer, Long>> countOthersData(List<DictionaryOption> optionList, List<DictionaryOption> typeList, Organ currentOrgan, String countType );

	/**
	 *
	 * <p>
	 * 方法名称  首页统计用，查询点击数字的记录
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-21 上午10:02:14
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@param psm
	 *@param currentOrgan
	 *@param countType
	 *@param secrecyOthers
	 *@return
	 */
	public List<SecrecyOthers> findDataList(PageSortModel<SecrecyOthers> psm, Organ currentOrgan, String countType, SecrecyOthers secrecyOthers);

	/**
	 *
	 * <p>
	 * 方法名称  addDepartment
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-26 下午3:30:40
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@param departmentName
	 *@param currentUser
	 *@return
	 */
	public Department addDepartment(String departmentName, User currentUser);

	/**
	 *
	 * <p>
	 * 方法名称  addUserinfo
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-26 下午3:30:44
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@param userName
	 *@param currentUser
	 *@return
	 */
	public UserInfo addUserinfo(String userName, User currentUser);

	/**
	 *
	 * <p>
	 * 方法名称  getSecrecyComputer_Total_District 综合统计
	 * </p>
	 * <p>
	 * 宋亚非 2013-9-5 上午11:13:41
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@param district
	 *@param reflag
	 *@return
	 */
	public Map<String, Object> getSecrecyOthers_Total_District(District district, int reflag);

	/**
	 *
	 * <p>
	 * 方法名称  count_SecrecyComputer_District 综合统计
	 * </p>
	 * <p>
	 * 宋亚非 2013-9-5 上午11:21:38
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@param districtList
	 *@param isGroup
	 *@return
	 */
	public List<ZongHeTongJiStatDto> count_SecrecyOthers_District(List<District> districtList, boolean isGroup);

	/**
	 *
	 * <p>
	 * 方法名称  count_SecrecyComputer_Organ 总合统计
	 * </p>
	 * <p>
	 * 宋亚非 2013-9-5 上午11:29:17
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@param district
	 *@param organ
	 *@param needTotal
	 *@return
	 */
	public List<ZongHeTongJiStatDto> count_SecrecyOthers_Organ(District district, Organ organ, boolean needTotal);


	/**
	 *
	 * <p>
	 * 方法名称  count_SecrecyComputer_District 综合统计
	 * </p>
	 * <p>
	 * 宋亚非 2013-9-5 上午11:56:13
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@param district
	 *@param isGroup
	 *@param organ
	 *@return
	 */
	public List<ZongHeTongJiStatDto> count_SecrecyOthers_District(District district, boolean isGroup,Organ organ);

}
