package com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.service;

import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.vo.SecrecyWorkOrgan;
import com.cdthgk.bmp.statinfo.dto.QueryDto;
import com.cdthgk.platform.dictionary.domain.DictionaryOption;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.permission.user.domain.User;

/**
 * <p>
 * 保密工作机构  Service 抽象类(接口)
 * </p>
 * <p>
 * 刘椿成  2012-12-29 16:43:03
 * </p>
 * <blockquote>
 * <h4>历史修改记录</h4>
 * <ul>
 * <li>修改人 修改时间 修改描述
 * </ul>
 * </blockquote>
 * <p>
 * copyright liucc 2012, all rights reserved.
 * </p>
 * @author liucc
 * @since 1.0
 * @version 1.0
 */
public interface SecrecyWorkOrganModuleService extends BmpServiceTemplate<SecrecyWorkOrgan, String> {

	/**
	 * 刘椿成  2012-12-29 16:13:03
	 * <p>
	 * 根据当前登录用户所在单位获取保密工作机构
	 * </p>
	 * @param organ
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * <p>
	 * copyright liucc 2012, all rights reserved.
	 * </p>
	 * @author liucc
	 * @since 1.0
	 * @version 1.0
	 */
	public SecrecyWorkOrgan getSecrecyWorkOrganByOrgan(Organ organ);

	/**
	 * <p>
	 * 保存系统数据部门，人员
	 * </p>
	 * @param secrecyWorkOrgan
	 * @param currentUser
	 * @param deptFlag
	 * @return
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * <p>
	 * copyright liucc 2012, all rights reserved.
	 * </p>
	 * @author liucc
	 * @since 1.0
	 * @version 1.0
	 */
	SecrecyWorkOrgan saveSystemDataBySecrecyWorkOrgan(SecrecyWorkOrgan secrecyWorkOrgan,
			User currentUser, String deptFlag);

	/**
	 * <p>
	 * 上报保密工作机构远程执行
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-3-22 - 上午11:46:05
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param secrecyWorkOrganList
	 * @param receiveOrganId
	 */
	public void saveRecSecrecyWorkOrgan(
			List<SecrecyWorkOrgan> secrecyWorkOrganList, String receiveOrganId);

	/**
	 *
	 * <p>
	 * 方法名：statisticsByOrganType 统计 按机构类别统计
	 * </p>
	 * <p>
	 * 宋亚非 2013-7-24 下午3:31:12
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@param organCategoryDictionary
	 *@param district
	 *@param organId
	 *@return
	 */
	public Map<String, Map<Integer, Integer>> statisticsByOrganType(List<DictionaryOption> organCategoryDictionary,District district, String organId);

	/**
	 *
	 * <p>
	 * 方法名：statisticsByLayerOfOrganType
	 * </p>
	 * <p>
	 * 宋亚非 2013-7-25 上午8:39:06
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@param organCategoryDictionary
	 *@param district
	 *@param organId
	 *@return
	 */
	//行政区划合计统计
	public Map<String, Map<Integer, Integer>> statisticsByLayerOfOrganType(List<DictionaryOption> organCategoryDictionary,District district, String organId);
	//直辖单位合计统计
	public Map<String, Map<Integer, Integer>> statisticsByAreaOfOrganType(List<DictionaryOption> organCategoryDictionary,District district, String organId);
	//行政区划子行政区划
	public Map<String, Map<Integer, Integer>> statisticsByOrganTypeOfDistrict(List<DictionaryOption> organCategoryDictionary,District district, String organId);
	//行政区划子单位
	public Map<String, Map<Integer, Integer>> statisticsByOrganTypeOfOrgan(List<DictionaryOption> organCategoryDictionary,District district, String organId);
	/**
	 *
	 * <p>
	 * 方法名：statisticsByAdministrativeLevel 统计 按行政级别统计
	 * </p>
	 * <p>
	 * 宋亚非 2013-7-24 下午4:35:43
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@param organAdminLevelDictionary
	 *@param district
	 *@param organId
	 *@return
	 */
	public Map<String, Map<Integer, Integer>> statisticsByAdministrativeLevel(List<DictionaryOption> organAdminLevelDictionary,District district, String organId);
	/**
	 *
	 * <p>
	 * 方法名：statisticsByLayerOfAdministrativeLevel
	 * </p>
	 * <p>
	 * 宋亚非 2013-7-25 上午8:39:20
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@param organAdminLevelDictionary
	 *@param district
	 *@param organId
	 *@return
	 */
	//行政区划合计统计
	public Map<String, Map<Integer, Integer>> statisticsByLayerOfAdministrativeLevel(List<DictionaryOption> organAdminLevelDictionary,District district, String organId);
	//直辖单位合计统计
	public Map<String, Map<Integer, Integer>> statisticsByAreaOfAdministrativeLevel(List<DictionaryOption> organAdminLevelDictionary,District district, String organId);
	//统计行政区划下各行政区划数据
	public Map<String, Map<Integer, Integer>> statisticsByAdministrativeLevelOfDistrict(List<DictionaryOption> organAdminLevelDictionary,District district,String organId);
	//统计行政区划下各直属单位数据
	public Map<String, Map<Integer, Integer>> statisticsByAdministrativeLevelOfOrgan(List<DictionaryOption> organAdminLevelDictionary,District district,String organId);

	/**
	 *
	 * <p>
	 * 方法名：statisticsByEstablishmentNum 统计 按编制人数统计
	 * </p>
	 * <p>
	 * 宋亚非 2013-7-24 下午4:53:38
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@param numRange
	 *@param district
	 *@param organId
	 *@return
	 */
	public Map<String, Integer> statisticsByEstablishmentNum(List<Map<String, Integer>> numRange,District district,String organId, Integer hisFlag);
	/**
	 *
	 * <p>
	 * 方法名：statisticsByLayerOfEstablishmentNum
	 * </p>
	 * <p>
	 * 宋亚非 2013-7-25 上午8:39:51
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@param numRange
	 *@param district
	 *@param organId
	 *@return
	 */
	//行政区划合计统计
	public Map<String, Integer> statisticsByLayerOfEstablishmentNum(List<Map<String, Integer>> numRange,District district,String organId, Integer hisFlag);
	//直辖单位合计统计
	public Map<String, Integer> statisticsByAreaOfEstablishmentNum(List<Map<String, Integer>> numRange,District district,String organId, Integer hisFlag);
	//子行政区划
	public Map<String,Map<String, Integer>> statisticsByEstablishmentNumOfDistrict(List<Map<String, Integer>> numRange,District district,String organId, Integer hisFlag);
	//子直辖单位
	public Map<String, Integer> statisticsByEstablishmentNumOfOrgan(List<Map<String, Integer>> numRange,District district,String organId, Integer hisFlag);

	/**
	 * <p>
	 * 通过时间查询保密组织机构
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2014-5-7 - 下午3:26:31
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param organ
	 * @param queryDto
	 * @return
	 */
	public SecrecyWorkOrgan queryOrganIndex(Organ organ, QueryDto queryDto);
}