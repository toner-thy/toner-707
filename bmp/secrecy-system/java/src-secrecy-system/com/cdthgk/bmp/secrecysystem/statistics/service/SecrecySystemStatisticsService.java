/**
 *
 */
package com.cdthgk.bmp.secrecysystem.statistics.service;

import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.platform.dictionary.domain.DictionaryOption;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;

/**
 *
 * <p>
 * 类的说明放这里
 * </p>
 * <p>
 * 创建时间 2013-8-9 - 下午4:05:31
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
public interface SecrecySystemStatisticsService extends BmpServiceTemplate<Object, String> {

	/**
	 * <p>
	 * 通过行政区查询涉密计算机总数
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-8-9 - 下午4:08:16
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param district
	 * @param organ
	 */
	List<Map<String, String>> getComputerCount(District district, Organ organ, boolean flag);

	/**
	 * <p>
	 * 通过行政区查询涉密计算机3和1总数
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-8-12 - 上午9:24:18
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param district
	 * @return
	 */
	List<Map<String, String>> getComputerThreeCount(District district, Organ organ, boolean flag);

	/**
	 * <p>
	 * 通过行政区查询涉密计算机违规外联总数
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-8-12 - 上午9:36:54
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param district
	 * @return
	 */
	List<Map<String, String>> getComputerWaiLianCount(District district, Organ organ, boolean flag);

	/**单位明细
	 * 涉密计算机总数统计
	 * @param district
	 * @return
	 */
	public List<Map<String,Object>> getSecrecyComputer_OrganDetail(District district);


	/**（本单位） 移动存储介质
	 * 本单位统计
	 * @param organ
	 * @return
	 */
	public List<Map<String,Object>> getMobileStorageMedia_CurrentOrgan(Organ organ);
	/**(行政区划) 移动存储介质
	 * 行政区划统计
	 * @param district
	 * @return
	 */
	public List<Map<String,Object>> getMobileStorageMedia_District(District district);
	/**(直辖单位) 移动存储介质
	 * 直辖单位统计
	 * @param district
	 * @return
	 */
	public List<Map<String,Object>> getMobileStorageMedia_Layer(District district);

	/**（本单位） 其他涉密设备
	 * 本单位统计
	 * @param organ
	 * @return
	 */
	public List<Map<String,Object>> getOthers_CurrentOrgan(Organ organ);

	/**(行政区划)  其他涉密设备
	 * 行政区划统计
	 * @param district
	 * @return
	 */
	public List<Map<String,Object>> getOthers_District(District district);

	/**(直辖单位)  其他涉密设备
	 * 直辖单位统计
	 * @param district
	 * @return
	 */
	public List<Map<String,Object>> getOthers_Layer(District district);

	/**
	 *
	 * <p>
	 * 方法名：getNetwork_CurrentOrgan 涉密网络本单位统计
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-12 下午2:22:04
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
	 *@param secrecyStatusFlag
	 *@return
	 */
	public List<Map<String, Map<Integer,Integer>>> getNetwork_CurrentOrgan(List<DictionaryOption> optionsList ,Organ organ ,Integer secrecyStatusFlag);

	/**
	 *
	 * <p>
	 * 方法名：getNetwork_District 涉密网络 行政区划统计
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-12 下午2:22:49
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
	 *@param secrecyStatusFlag
	 *@return
	 */
	public List<Map<String, Map<Integer,Integer>>> getNetwork_District(List<DictionaryOption> optionsList ,District district ,Integer secrecyStatusFlag);

	/**
	 *
	 * <p>
	 * 方法名：getNetwork_Layer 涉密网络直辖单位统计
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-12 下午2:23:11
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
	 *@param secrecyStatusFlag
	 *@return
	 */
	public List<Map<String, Map<Integer,Integer>>> getNetwork_Layer(List<DictionaryOption> optionsList ,District district ,Integer secrecyStatusFlag);

	/**
	 *
	 * <p>
	 * 方法名：getNetwork_DistrictOrgan 直辖单位统计
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-12 下午4:48:57
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@param optionsList
	 *@param district
	 *@param secrecyStatusFlag
	 *@return
	 */
	public List<Map<String, Map<Integer,Integer>>> getNetwork_DistrictOrgan(List<DictionaryOption> optionsList ,District district ,Integer secrecyStatusFlag);


}
