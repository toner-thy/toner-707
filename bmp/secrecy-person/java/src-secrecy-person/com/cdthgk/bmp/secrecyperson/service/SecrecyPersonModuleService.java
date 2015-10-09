package com.cdthgk.bmp.secrecyperson.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.bmp.secrecycountryitem.qo.ZongHeTongJiStatDto;
import com.cdthgk.bmp.secrecyperson.qo.SecrecyPersonQo;
import com.cdthgk.bmp.secrecyperson.vo.SecrecyPerson;
import com.cdthgk.platform.dictionary.domain.DictionaryOption;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;
import com.cdthgk.platform.permission.user.domain.User;
import com.cdthgk.web.upload.UploadFile;

import ec.common.PageSortModel;

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
public interface SecrecyPersonModuleService extends SecrecyPersonService, BmpServiceTemplate<SecrecyPerson, String> {

	/**
	 * <p>
	 * 机关涉密人员 列表
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
	 * @param psm PageSortModel
	 * @param secrecyPerson 涉密人员对象
	 * @param organ 当前登录人员单位
	 * @return List<SecrecyPerson>
	 * @throws ParseException ParseException
	 */
	List<SecrecyPerson> getPageAllList(PageSortModel psm, SecrecyPerson secrecyPerson,
										Organ organ, District district,String partId, String checkLower,Integer nowFlag) throws ParseException;

	/**
	 *
	 * <p>
	 * 方法名：getAllList 导出方法使用
	 * </p>
	 * <p>
	 * 宋亚非 2013-5-21 下午3:30:56
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
	 *@param secrecyPerson
	 *@param organ
	 *@return
	 *@throws ParseException
	 */
	List<SecrecyPerson> getAllList(SecrecyPerson secrecyPerson,	Organ organ) throws ParseException;

	/**
	 *
	 * <p>
	 * 方法名称  findPageAllList 涉密人员查询功能使用
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-22 下午3:02:46
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
	 *@param secrecyPerson
	 *@param district
	 *@return
	 *@throws ParseException
	 */
	/*List<SecrecyPerson> findPageAllList(PageSortModel<SecrecyPerson> psm, SecrecyPerson secrecyPerson,District district);*/


	/**
	 *
	 * <p>
	 * 方法名：getDictionaryOptionList
	 * </p>
	 * <p>
	 * 宋亚非 2013-5-21 下午6:22:54
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
	 * 机关涉密人员 列表(首页获取涉密人员信息)
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
	 * @param psm PageSortModel
	 * @param secrecyPerson 涉密人员对象
	 * @param organ 当前登录人员单位
	 * @return List<SecrecyPerson>
	 * @throws ParseException ParseException
	 */
	List<SecrecyPerson> getPageAllList(PageSortModel psm, SecrecyPerson secrecyPerson,
										Organ organ, String showType) throws ParseException;


	/**
	 * <p>
	 * 获取行政区列表
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
	 * @param psm PageSortModel
	 * @param district 行政区
	 * @param secrecyPerson 涉密人员对象
	 * @param status 状态
	 * @param organ 当前登录人员单位
	 * @return List<SecrecyPerson>
	 * @throws ParseException ParseException
	 */
	List<SecrecyPerson> getDistrictList(PageSortModel psm, District district, Organ organ,
						String status, SecrecyPerson secrecyPerson) throws ParseException;

	/**
	 * <p>
	 * 获取部门涉密人员
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
	 * @param psm PageSortModel
	 * @param departmentId 部门Id
	 * @param organ 当前登录人员单位
	 * @return List<SecrecyPerson>
	 * @throws ParseException ParseException
	 */
	List<SecrecyPerson> getSecrecyPersonByDepartment(PageSortModel psm,
			String departmentId, Organ organ) throws ParseException;

	/**
	 * <p>
	 * 获取用户信息
	 * </p>
	 * <p>
	 * @author 牟远洋 2012 12 23 12:00
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
	 * @param userInfoId 人员ID
	 * @return userInfo
	 */
	UserInfo getUserInfo(String userInfoId);

	/**
	 * <p>
	 * 检查当前人员是否已经是涉密人员
	 * </p>
	 * <p>
	 * @author 牟远洋 2012 12 24 12:00
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
	 * @param userInfoId 人员ID
	 * @param departmentId 部门ID
	 * @param organ 当前登录人员单位
	 * @return boolean
	 */
	Boolean checkSecrecyPerson(String userInfoId, String departmentId, Organ organ);

	/**
	 * <p>
	 * 检查当前涉密人员是否已经存在
	 * </p>
	 * <p>
	 * @author 宋亚非 2013-4-19 09:38
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
	 * @author songyafei
	 * @version 1.0
	 * @param secrecyPerson 人员ID
	 * @return boolean
	 */
	Boolean checkSecrecyPerson( SecrecyPerson secrecyPerson );

	/**
	 * <p>
	 * 保存人员信息
	 * </p>
	 * <p>
	 * @author 牟远洋 2012 12 24 14:00
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
	 * @param userInfo 人员信息对象
	 * @param user 人员
	 * @param department 部门
	 * @return userInfo
	 */
	UserInfo saveUserInfo(UserInfo userInfo, User user, Department department);

	/**
	 * <p>
	 * 更新人员信息
	 * </p>
	 * <p>
	 * @author 牟远洋 2012 12 24 14:00
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
	 * @param userInfoDb DB抽出人员信息对象
	 * @param userInfoReceive 页面接收人员信息对象
	 * @param user 人员对象
	 * @param department 部门
	 */
	void updateUserInfo(UserInfo userInfoDb, UserInfo userInfoReceive, User user, Department department);

	/**
	 * <p>
	 * 保存部门信息
	 * </p>
	 * <p>
	 * @author 宋亚非 2013-04-19 10:36
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
	 * @author songyafei
	 * @version 1.0
	 * @param departmentName 部门名称
	 * @param user 人员对象
	 * @return department
	 */
	Department saveDepartment(String departmentName, User user, Organ organ);

	/**
	 * <p>
	 * 保存部门信息
	 * </p>
	 * <p>
	 * @author 牟远洋 2012 12 24 14:20
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
	 * @param departmentName 部门名称
	 * @param user 人员对象
	 * @return department
	 */
	Department saveDepartment(String departmentName, User user);


	/**
	 * <p>
	 * 获取部门涉密人员
	 * </p>
	 * <p>
	 * @author 牟远洋 2012 12 24 14:25
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
	 * @param departmentId 部门ID
	 * @param organ 当前登录人员单位
	 * @return List<SecrecyPerson>
	 */
	List<SecrecyPerson> getSecrecyPersonByDepartment(String departmentId, Organ organ);

	/**
	 * <p>
	 * 导入word信息
	 * </p>
	 * <p>
	 * 刘椿成2013-01-10 15:46:13
	 * </p>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * @param upLoadFile word文档
	 * @param currentUser 当前登录用户
	 * @return
	 * @author liuchuncheng
	 * @since 1.0
	 * @version 1.0
	 * @throws IOException
	 */
	Map<Boolean, List<String>> saveImportData(UploadFile upLoadFile,
			User currentUser) throws IOException;

	/**
	 * createTime 2013.01.18
	 * @author wangpb
	 * @param organ
	 * @return
	 */
	List<Long> countSecrecyPersonData(Organ organ);

	/**
	 * <p>
	 * 方法的说明
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-3-20 - 下午3:32:57
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param secrecyPersonList
	 * @param receiveOrganId
	 */
	void saveOrUpdateBatch(List<SecrecyPerson> secrecyPersonList,
			String receiveOrganId);

	/**
	 * <p>
	 * 单位首页统计数据撰取详情
	 * </p>
	 * <p>
	 * 刘椿成 创建时间  2012 8 29 - 10:38:49
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>宋亚非  2013-04-09  从市平台移植
	 * </ul>
	 * </blockquote>
	 * @param psm organ secrecyLevel districtCode
	 * @param secrecyPerson
	 */
	List<SecrecyPerson> getIndexPage(PageSortModel psm, Organ organ, Integer secrecyLevel, String districtCode, SecrecyPerson secrecyPerson, String showType);


	/**
	 * <p>
	 * 保存涉密人员信息
	 * </p>
	 * <blockquote>
	 * <h4>保存</h4>
	 * <ul>
	 * <li>宋亚非 2013-04-19 10:46:13
	 * </ul>
	 * </blockquote>
	 * <p>
	 * copyright songyafei 2013, all rights reserved.
	 * </p>
	 * @author songyafei
	 * @since 1.0
	 * @version 1.0
	 */
	public Boolean saveSecrecyPerson( List<SecrecyPerson> secrecyPersonList, User currentUser );

	/**
	 *
	 * <p>
	 * 方法名：statisticsByAge 按年龄阶段统计
	 * </p>
	 * <p>
	 * 宋亚非 2013-7-22 下午3:09:37
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@param ageRange
	 *@param organ
	 *@return
	 */
	public List<Map<String, List<Map<Integer, Integer>>>> statisticsByAge(List<Map<String,Integer>> ageRange, String districtCode, String organId, Integer hisFlag, String countScope);

	/**
	 *
	 * <p>
	 * 方法名：statisticsByLayerOfAge 按行政区划layer查询 统计 年龄阶段
	 * </p>
	 * <p>
	 * 宋亚非 2013-7-23 下午3:14:33
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@param ageRange
	 *@param layer
	 *@return
	 */
	public List<Map<String, List<Map<Integer, Integer>>>> statisticsByLayerOfAge(List<Map<String,Integer>> ageRange, String layer, String organId, Integer hisFlag, String countScope);
	public Map<String,List<Map<String, List<Map<Integer, Integer>>>>> statisticsByAgeOfAll(List<Map<String,Integer>> ageRange, String districtCode, String organId, Integer hisFlag, String countScope);
	public Map<String,List<Map<String, List<Map<Integer, Integer>>>>> statisticsByAgeOfDistrict(List<Map<String,Integer>> ageRange, String districtCode, String organId, Integer hisFlag, String countScope);
	public Map<String,List<Map<String, List<Map<Integer, Integer>>>>> statisticsByAgeOfOrgan(List<Map<String,Integer>> ageRange, String districtCode, String organId, Integer hisFlag, String countScope);

	/**
	 *
	 * <p>
	 * 方法名：statisticsByNation 按民族统计
	 * </p>
	 * <p>
	 * 宋亚非 2013-7-22 下午5:49:51
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@param nationOptions
	 *@param organ
	 *@return
	 */
	public List<Map<String, Map<Integer, Integer>>> statisticsByNation(List<DictionaryOption> nationOptions, String districtCode, String organId, Integer hisFlag,String countScope);

	/**
	 *
	 * <p>
	 * 方法名：statisticsByLayerOfNation 按行政区划layer查询 统计民族
	 * </p>
	 * <p>
	 * 宋亚非 2013-7-23 下午3:15:51
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@param nationOptions
	 *@param layer
	 *@return
	 */
	public List<Map<String, Map<Integer, Integer>>> statisticsByLayerOfNation(List<DictionaryOption> nationOptions, String layer, String organId, Integer hisFlag, String countScope);
	public Map<String,List<Map<String, Map<Integer, Integer>>>> statisticsByNationOfAll(List<DictionaryOption> nationOptions, District district, String organId, Integer hisFlag, String countScope);
	public Map<String,List<Map<String, Map<Integer, Integer>>>> statisticsByNationOfDistrict(List<DictionaryOption> nationOptions, District district, String organId, Integer hisFlag, String countScope);
	public Map<String,List<Map<String, Map<Integer, Integer>>>> statisticsByNationOfOrgan(List<DictionaryOption> nationOptions, District district, String organId, Integer hisFlag, String countScope);

	/**
	 *
	 * <p>
	 * 方法名：statisticsByLeanLevel 按学历统计
	 * </p>
	 * <p>
	 * 宋亚非 2013-7-22 下午7:20:52
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@param leanLevelOptions
	 *@param organ
	 *@return
	 */
	public List<Map<String, Map<Integer, Integer>>> statisticsByLeanLevel(List<DictionaryOption> leanLevelOptions, String districtCode, String organId, Integer hisFlag, String countScope);

	/**
	 *
	 * <p>
	 * 方法名：statisticsByLayerOfLeanLevel 按行政区划layer查询 统计学历
	 * </p>
	 * <p>
	 * 宋亚非 2013-7-23 下午3:16:52
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@param leanLevelOptions
	 *@param layer
	 *@return
	 */
	public List<Map<String, Map<Integer, Integer>>> statisticsByLayerOfLeanLevel(List<DictionaryOption> leanLevelOptions, String layer ,String organId, Integer hisFlag, String countScope);
	public Map<String, List<Map<String, Map<Integer, Integer>>>> statisticsByLearnLevelOfAll(List<DictionaryOption> leanLevelOptions, District district,String organId, Integer hisFlag,String countScope);
	public Map<String, List<Map<String, Map<Integer, Integer>>>> statisticsByLearnLevelOfDistrict(List<DictionaryOption> leanLevelOptions, District district,String organId, Integer hisFlag, String countScope);
	public Map<String, List<Map<String, Map<Integer, Integer>>>> statisticsByLearnLevelOfOrgan(List<DictionaryOption> leanLevelOptions, District district,String organId, Integer hisFlag,String countScope);

	/**
	 *
	 * <p>
	 * 方法名：statisticsByPolity 按政治面貌统计
	 * </p>
	 * <p>
	 * 宋亚非 2013-7-22 下午7:41:55
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@param leanLevelOptions
	 *@param organ
	 *@return
	 */
	public List<Map<String, Map<Integer, Integer>>> statisticsByPolity(List<DictionaryOption> leanLevelOptions, String districtCode,  String organId, Integer hisFlag, String countScope);

	/**
	 *
	 * <p>
	 * 方法名：statisticsByLayerOfPolity 按行政区划layer查询统计政治面貌
	 * </p>
	 * <p>
	 * 宋亚非 2013-7-23 下午3:18:21
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@param leanLevelOptions
	 *@param layer
	 *@return
	 */
	public List<Map<String, Map<Integer, Integer>>> statisticsByLayerOfPolity(List<DictionaryOption> leanLevelOptions, String layer,  String organId, Integer hisFlag, String countScope);
	public Map<String,List<Map<String, Map<Integer, Integer>>>> statisticsByPolityOfAll(List<DictionaryOption> leanLevelOptions, District district,  String organId, Integer hisFlag, String countScope);
	public Map<String,List<Map<String, Map<Integer, Integer>>>> statisticsByPolityOfDistrict(List<DictionaryOption> leanLevelOptions, District district,  String organId, Integer hisFlag, String countScope);
	public Map<String,List<Map<String, Map<Integer, Integer>>>> statisticsByPolityOfOrgan(List<DictionaryOption> leanLevelOptions, District district,  String organId, Integer hisFlag, String countScope);

	/**
	 *
	 * <p>
	 * 方法名：statisticsByPersonType 按人员类型统计
	 * </p>
	 * <p>
	 * 宋亚非 2013-7-22 下午7:58:52
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@param personTypeOptions
	 *@param organ
	 *@return
	 */
	public List<Map<String, Map<Integer, Integer>>> statisticsByPersonType(List<DictionaryOption> personTypeOptions, String districtCode,  String organId, Integer hisFlag, String countScope);

	/**
	 *
	 * <p>
	 * 方法名：statisticsByLayerOfPersonType 按行政区划layer查询统计人员类型
	 * </p>
	 * <p>
	 * 宋亚非 2013-7-23 下午3:19:50
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@param personTypeOptions
	 *@param layer
	 *@return
	 */
	public List<Map<String, Map<Integer, Integer>>> statisticsByLayerOfPersonType(List<DictionaryOption> personTypeOptions, String layer,  String organId, Integer hisFlag, String countScope);
	public Map<String,List<Map<String, Map<Integer, Integer>>>> statisticsByPersonTypeOfAll(List<DictionaryOption> personTypeOptions, District district,  String organId, Integer hisFlag, String countScope);
	public Map<String,List<Map<String, Map<Integer, Integer>>>> statisticsByPersonTypeOfDistrict(List<DictionaryOption> personTypeOptions, District district,  String organId, Integer hisFlag, String countScope);
	public Map<String,List<Map<String, Map<Integer, Integer>>>> statisticsByPersonTypeOfOrgan(List<DictionaryOption> personTypeOptions, District district,  String organId, Integer hisFlag, String countScope);

	/**
	 *
	 * <p>
	 * 方法名：statisticsByAdminTechLevel 按行政级别统计
	 * </p>
	 * <p>
	 * 宋亚非 2013-7-22 下午8:14:01
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@param adminLevelOptions
	 *@param technicTitleLevelOptions
	 *@param organ
	 *@return
	 */
	public List<Map<String, Map<Integer, Integer>>> statisticsByAdmin(List<DictionaryOption> adminLevelOptions, String districtCode, String organId, Integer hisFlag, String countScope);

	/**
	 *
	 * <p>
	 * 方法名：statisticsByLayerOfAdmin 按行政区划layer查询统计行政级别
	 * </p>
	 * <p>
	 * 宋亚非 2013-7-23 下午3:21:49
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@param adminLevelOptions
	 *@param layer
	 *@return
	 */
	public List<Map<String, Map<Integer, Integer>>> statisticsByLayerOfAdmin(List<DictionaryOption> adminLevelOptions, String layer,  String organId, Integer hisFlag, String countScope);
	public Map<String, List<Map<String, Map<Integer, Integer>>>> statisticsByAdminOfAll(List<DictionaryOption> personTypeOptions, District district,  String organId, Integer hisFlag, String countScope);
	public Map<String, List<Map<String, Map<Integer, Integer>>>> statisticsByAdminOfDistrict(List<DictionaryOption> personTypeOptions, District district,  String organId, Integer hisFlag, String countScope);
	public Map<String, List<Map<String, Map<Integer, Integer>>>> statisticsByAdminOfOrgan(List<DictionaryOption> personTypeOptions, District district,  String organId, Integer hisFlag, String countScope);

	/**
	 *
	 * <p>
	 * 方法名：statisticsByAdminTechLevel 按技术职称统计
	 * </p>
	 * <p>
	 * 宋亚非 2013-7-22 下午8:14:01
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@param adminLevelOptions
	 *@param technicTitleLevelOptions
	 *@param organ
	 *@return
	 */
	public List<Map<String, Map<Integer, Integer>>> statisticsByTechLevel( List<DictionaryOption> technicTitleLevelOptions, String districtCode, String organId, Integer hisFlag, String countScope);

	/**
	 *
	 * <p>
	 * 方法名：statisticsByLayerOfTechLevel 按行政区划layer查询统计行政级别
	 * </p>
	 * <p>
	 * 宋亚非 2013-7-23 下午3:23:59
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@param statisticsByLayerOfTechLevel
	 *@param layer
	 *@return
	 */
	public List<Map<String, Map<Integer, Integer>>> statisticsByLayerOfTechLevel( List<DictionaryOption> statisticsByLayerOfTechLevel, String layer, String organId, Integer hisFlag, String countScope);
	public Map<String, List<Map<String, Map<Integer, Integer>>>> statisticsByTechLevelOfAll(List<DictionaryOption> personTypeOptions, District district,  String organId, Integer hisFlag, String countScope);
	public Map<String, List<Map<String, Map<Integer, Integer>>>> statisticsByTechLevelOfDistrict(List<DictionaryOption> personTypeOptions, District district,  String organId, Integer hisFlag, String countScope);
	public Map<String, List<Map<String, Map<Integer, Integer>>>> statisticsByTechLevelOfOrgan(List<DictionaryOption> personTypeOptions, District district,  String organId, Integer hisFlag, String countScope);

	/**
	 *
	 * <p>
	 * 方法名：staticsOverViewCurrent 概览  当前行政区划统计
	 * </p>
	 * <p>
	 * 宋亚非 2013-7-23 下午6:16:26
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
	 *@return
	 */
	public Map<String,Map<String, Map<Integer,Integer>>> statisticsOverViewCurrent(District district, String organId, Integer hisFlag);

	/**
	 *
	 * <p>
	 * 方法名：staticsOverViewDistinct 概览 下级行政区划统计
	 * </p>
	 * <p>
	 * 宋亚非 2013-7-23 下午6:16:54
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
	 *@return
	 */
	public Map<String,Map<String, Map<Integer,Integer>>>  statisticsOverViewDistinct(District district, String organId, Integer hisFlag);

	/**
	 *
	 * <p>
	 * 方法名：statisticsOverViewOgran 直单位统计
	 * </p>
	 * <p>
	 * 宋亚非 2013-7-23 下午6:20:51
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
	 *@return
	 */
	public Map<String,Map<Integer,Integer>> statisticsOverViewOgran(District district, String organId, Integer hisFlag, String countScope);

	/**
	 *
	 * <p>
	 * 方法名：queryList 按条件查询涉密人员列表
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-1 下午4:24:18
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
	 *@param sq
	 *@return
	 */
	public List<SecrecyPerson> queryList(PageSortModel<SecrecyPerson> psm, SecrecyPersonQo sq);

	/**
	 *
	 * <p>
	 * 方法名：countSecrecyPersonData 机关涉密人员首页面板查询方法
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-16 上午11:11:00
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
	 *@param currentOrgan
	 *@param countType
	 *@return
	 */
	public Map<Integer, Long> countSecrecyPersonData(List<DictionaryOption> optionList, Organ currentOrgan, String countType );

	/**
	 *
	 * <p>
	 * 方法名称  getSecrecyCountryItim_Total_District  综合统计总数
	 * </p>
	 * <p>
	 * 宋亚非 2013-9-4 上午10:21:17
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
	public Map<String, Object> getSecrecyPerson_Total_District(District district, int reflag);

	/**
	 *
	 * <p>
	 * 方法名称  count_SecrecyCountryItim_District 通过行政区划  统计   行政区划和直辖单位的数目
	 * </p>
	 * <p>
	 * 宋亚非 2013-9-4 上午10:26:17
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
	public List<ZongHeTongJiStatDto> count_SecrecyPerson_District(List<District> districtList, boolean isGroup);

	/**
	 *
	 * <p>
	 * 方法名称  count_SecrecyCountryItim_Organ 查询出一个单位下的个数  都要按照密级统计出来
	 * </p>
	 * <p>
	 * 宋亚非 2013-9-4 上午10:38:31
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
	public List<ZongHeTongJiStatDto> count_SecrecyPerson_Organ(District district, Organ organ, boolean needTotal);

	/**
	 *
	 * <p>
	 * 方法名称  count_SecrecyPerson_District 综合统计
	 * </p>
	 * <p>
	 * 宋亚非 2013-9-4 上午11:07:10
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
	public List<ZongHeTongJiStatDto> count_SecrecyPerson_District(District district, boolean isGroup,Organ organ);

	  /**
         * <p>
         * 方法的说明
         * </p>
         * <p>
         * 创建人 宋亚非  创建时间 2014-7-9 上午10:59:30
         * </p>
         * <blockquote>
         * <h4>历史修改记录</h4>
         * <ul>
         * <li>修改人 修改时间 修改描述
         * </ul>
         * </blockquote>
         * @param u
         * @param organId 
         * @return
         */
        List<UserInfo> findUserInfo(UserInfo u, String organId);
}