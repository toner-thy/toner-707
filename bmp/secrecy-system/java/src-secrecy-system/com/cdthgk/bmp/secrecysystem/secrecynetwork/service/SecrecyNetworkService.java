package com.cdthgk.bmp.secrecysystem.secrecynetwork.service;

import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.secrecycountryitem.qo.ZongHeTongJiStatDto;
import com.cdthgk.bmp.secrecysystem.secrecynetwork.vo.SecrecyNetwork;
import com.cdthgk.platform.base.service.GenericBasicService;
import com.cdthgk.platform.dictionary.domain.DictionaryOption;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

public interface SecrecyNetworkService extends GenericBasicService<SecrecyNetwork, String> {

	/**
	 * <p>
	 * 分页获取涉密网络
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-8-1 - 下午1:53:21
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param psm
	 * @param flag
	 * @param currentUser
	 * @param checkLower
	 * @param SecrecyNetwork
	 * @return
	 */
	public List<SecrecyNetwork> getListPage(PageSortModel<SecrecyNetwork> psm,
			SecrecyNetwork secrecyNetwork, District district, boolean flag, User currentUser, String checkLower);


	/**
	 *
	 * <p>
	 * 方法名称
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-21 上午11:14:13
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
	public Map<Integer,Map<Integer, Long>> countData(List<DictionaryOption> optionList, List<DictionaryOption> typeList, Organ currentOrgan, String countType );

	/**
	 *
	 * <p>
	 * 方法名称
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-21 上午11:14:18
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
	public List<SecrecyNetwork> findDataList(PageSortModel<SecrecyNetwork> psm, Organ currentOrgan, String countType, SecrecyNetwork secrecyOthers);

	/**
	 *
	 * <p>
	 * 方法名称  getSecrecyComputer_Total_District 综合统计 总数
	 * </p>
	 * <p>
	 * 宋亚非 2013-9-4 下午4:38:25
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
	public Map<String, Object> getSecrecyNetwork_Total_District(District district, int reflag);

	/**
	 *
	 * <p>
	 * 方法名称  count_SecrecyComputer_District 综合统计
	 * </p>
	 * <p>
	 * 宋亚非 2013-9-4 下午4:46:42
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
	public List<ZongHeTongJiStatDto> count_SecrecyNetwork_District(List<District> districtList, boolean isGroup);

	/**
	 *
	 * <p>
	 * 方法名称  count_SecrecyComputer_Organ  综合统计
	 * </p>
	 * <p>
	 * 宋亚非 2013-9-4 下午4:55:32
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
	public List<ZongHeTongJiStatDto> count_SecrecyNetwork_Organ(District district, Organ organ, boolean needTotal);

	/**
	 *
	 * <p>
	 * 方法名称  count_SecrecyComputer_District
	 * </p>
	 * <p>
	 * 宋亚非 2013-9-4 下午5:19:07
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
	public List<ZongHeTongJiStatDto> count_SecrecyNetwork_District(District district, boolean isGroup,Organ organ);

	/**
	 *
	 * <p>
	 * 方法的说明 增加部门
	 * </p>
	 * <p>
	 * 创建人 宋亚非  创建时间 2013-9-23 上午9:35:46
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param departmentName
	 * @param currentUser
	 * @return
	 */
	public Department addDepartment(String departmentName, User currentUser);

}
