/**
 *
 */
package com.cdthgk.bmp.secrecysystem.secrecynetwork.service;

import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.bmp.secrecycountryitem.qo.ZongHeTongJiStatDto;
import com.cdthgk.bmp.secrecysystem.secrecynetwork.vo.SecrecyNetwork;
import com.cdthgk.bmp.secrecysystem.secrecynetwork.vo.SecrecyNetworkterminal;
import com.cdthgk.platform.dictionary.domain.DictionaryOption;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

/**
 * <p>
 * 类的说明放这里 2013-8-7 下午3:31:45
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 *
 * @author 宋亚非
 */
public interface SecrecyNetworkterminalService extends
		BmpServiceTemplate<SecrecyNetworkterminal, String> {

	/**
	 *
	 * <p>
	 * 方法名：findListPage 查询列表
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-7 下午3:36:15
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
	 *@param secrecyNetworkterminal
	 *@param organ
	 *@return
	 */
	public List<SecrecyNetworkterminal> findListPage(
			PageSortModel<SecrecyNetworkterminal> psm,
			SecrecyNetworkterminal secrecyNetworkterminal, Organ organ, District district,
			boolean flag, User user, String checkLower);

	/**
	 *
	 * <p>
	 * 方法名：findTerminalListByNetwork 根据涉密网络信息查询其下的涉密计算机列表
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-8 下午2:12:26
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
	 *@param secrecyNetwork
	 *@param secrecyNetworkterminal
	 *@param secrecyStatusFlag
	 *@return
	 */
	public List<SecrecyNetworkterminal> findTerminalListByNetwork(
			PageSortModel<SecrecyNetworkterminal> psm,SecrecyNetwork secrecyNetwork,
			SecrecyNetworkterminal secrecyNetworkterminal, Integer secrecyStatusFlag);

	/**
	 *
	 * <p>
	 * 方法名称  countOthersData
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-21 下午1:43:22
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
	 * 宋亚非 2013-8-21 下午1:38:34
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
	 *@param secrecyNetworkterminal
	 *@return
	 */
	public List<SecrecyNetworkterminal> findDataList(PageSortModel<SecrecyNetworkterminal> psm, Organ currentOrgan, String countType, SecrecyNetworkterminal secrecyNetworkterminal);

	/**
	 *
	 * <p>
	 * 方法名称  getSecrecyNetwork_Total_District 综合统计 总数
	 * </p>
	 * <p>
	 * 宋亚非 2013-9-4 下午5:44:00
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
	public Map<String, Object> getSecrecyNetworkterminal_Total_District(District district, int reflag);

	/**
	 *
	 * <p>
	 * 方法名称  count_SecrecyNetwork_District 综合统计
	 * </p>
	 * <p>
	 * 宋亚非 2013-9-4 下午5:48:17
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
	public List<ZongHeTongJiStatDto> count_SecrecyNetworkterminal_District(List<District> districtList, boolean isGroup);

	/**
	 *
	 * <p>
	 * 方法名称  count_SecrecyNetwork_Organ 综合统计
	 * </p>
	 * <p>
	 * 宋亚非 2013-9-4 下午5:54:44
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
	public List<ZongHeTongJiStatDto> count_SecrecyNetworkterminal_Organ(District district, Organ organ, boolean needTotal);

	/**
	 *
	 * <p>
	 * 方法名称  count_SecrecyNetwork_District 综合统计
	 * </p>
	 * <p>
	 * 宋亚非 2013-9-4 下午6:10:56
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
	public List<ZongHeTongJiStatDto> count_SecrecyNetworkterminal_District(District district, boolean isGroup,Organ organ);
}
