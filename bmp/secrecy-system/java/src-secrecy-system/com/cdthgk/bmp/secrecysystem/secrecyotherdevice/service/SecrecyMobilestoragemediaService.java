/**
 *
 */
package com.cdthgk.bmp.secrecysystem.secrecyotherdevice.service;

import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.bmp.secrecycountryitem.qo.ZongHeTongJiStatDto;
import com.cdthgk.bmp.secrecysystem.secrecyotherdevice.vo.SecrecyMobilestoragemedia;
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
public interface SecrecyMobilestoragemediaService extends
		BmpServiceTemplate<SecrecyMobilestoragemedia, String> {

	/**
	 *
	 * <p>
	 * 方法名：findPageAllList 列表页查询
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-2 下午1:46:30
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
	 *@param secrecyMobilestoragemedia
	 *@param organ
	 *@param secrecyStatusFlag
	 *@return
	 */
	public List<SecrecyMobilestoragemedia> findPageAllList(
			PageSortModel<SecrecyMobilestoragemedia> psm,
			SecrecyMobilestoragemedia secrecyMobilestoragemedia, Organ organ, District district,String checkLower,
			Integer secrecyStatusFlag);

	/**
	 *
	 * <p>
	 * 方法名称  findPageAllList 按行政区划查询
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-22 下午4:08:18
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
	 *@param secrecyMobilestoragemedia
	 *@param district
	 *@param secrecyStatusFlag
	 *@return
	 */
	/*public List<SecrecyMobilestoragemedia> findPageAllList(
			PageSortModel<SecrecyMobilestoragemedia> psm,
			SecrecyMobilestoragemedia secrecyMobilestoragemedia, District district);*/


	/**
	 *
	 * <p>
	 * 方法名称
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-20 下午1:53:26
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
	Map<Integer,Map<Integer, Long>> countSecrecyMobilestoragemediaData(List<DictionaryOption> optionList, List<DictionaryOption> typeList, Organ currentOrgan, String countType );

	/**
	 *
	 * <p>
	 * 方法名称
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-20 下午4:19:53
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@param currentOrgan
	 *@param countType
	 *@param mediaType
	 *@return
	 */
	List<SecrecyMobilestoragemedia> findDataList(PageSortModel<SecrecyMobilestoragemedia> psm,Organ currentOrgan, String countType, SecrecyMobilestoragemedia secrecyMobilestoragemedia);

	/**
	 *
	 * <p>
	 * 方法名称  addDepartment
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-26 下午3:23:48
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
	 * 宋亚非 2013-8-26 下午3:23:53
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
	 * 方法名称  getSecrecyComputer_Total_District 综合统计 总数
	 * </p>
	 * <p>
	 * 宋亚非 2013-9-5 上午9:48:37
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
	public Map<String, Object> getSecrecyMobilestoragemedia_Total_District(District district, int reflag);

	/**
	 *
	 * <p>
	 * 方法名称  count_SecrecyComputer_District 综合统计
	 * </p>
	 * <p>
	 * 宋亚非 2013-9-5 上午10:09:54
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
	public List<ZongHeTongJiStatDto> count_SecrecyMobilestoragemedia_District(List<District> districtList, boolean isGroup);

	/**
	 *
	 * <p>
	 * 方法名称  count_SecrecyComputer_Organ 综合统计
	 * </p>
	 * <p>
	 * 宋亚非 2013-9-5 上午10:19:43
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
	public List<ZongHeTongJiStatDto> count_SecrecyMobilestoragemedia_Organ(District district, Organ organ, boolean needTotal);

	/**
	 *
	 * <p>
	 * 方法名称  count_SecrecyComputer_District 综合统计
	 * </p>
	 * <p>
	 * 宋亚非 2013-9-5 上午10:42:58
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
	public List<ZongHeTongJiStatDto> count_SecrecyMobilestoragemedia_District(District district, boolean isGroup,Organ organ);

}
