package com.cdthgk.bmp.secrecyorganization.secrecyoffice.service;

import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.bmp.secrecyorganization.secrecyoffice.vo.EmployPerson;
import com.cdthgk.bmp.secrecyorganization.secrecyoffice.vo.EstablishSituation;
import com.cdthgk.bmp.secrecyorganization.secrecyoffice.vo.Infrastructure;
import com.cdthgk.bmp.secrecyorganization.secrecyoffice.vo.InternalOrgan;
import com.cdthgk.bmp.secrecyorganization.secrecyoffice.vo.LeaderStaff;
import com.cdthgk.bmp.secrecyorganization.secrecyoffice.vo.SecrecyOffice;
import com.cdthgk.platform.dictionary.domain.DictionaryOption;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.permission.user.domain.User;

/**
 * <p>
 * 保密办（保密局） Service 抽象类
 * </p>
 * <p>
 * 陶汇源 2012-12-24 18:43:03
 * </p>
 * <blockquote>
 * <h4>历史修改记录</h4>
 * <ul>
 * <li>修改人 修改时间 修改描述
 * </ul>
 * </blockquote>
 * <p>
 * copyright taohy 2012, all rights reserved.
 * </p>
 *
 * @author FastCodeingTools
 * @author taohy
 * @since 1.0
 * @version 1.0
 */
public interface SecrecyOfficeModuleService extends SecrecyOfficeService, BmpServiceTemplate<SecrecyOffice, String> {

	/**
	 * <p>
	 * 根据当前办公室保存系统数据人员、部门
	 * </p>
	 * <p>
	 * 陶汇源   2012-12-29 14:50:13
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
	 * @author FastCodeingTools
	 * @author tianyu
	 * @since 1.0
	 * @version 1.0
	 */
	SecrecyOffice saveSystemDataBySecrecyOffice(SecrecyOffice secrecyOffice, User currentUser);

	/**
	 * <p>
	 * 上报保存保密办远端执行
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-4-7 - 下午1:01:24
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param secrecyOfficeList
	 * @param receiveOrganId
	 */
	void saveRecSecrecyOffice(List<SecrecyOffice> secrecyOfficeList,
			String receiveOrganId);

	/**
	 * <p>
	 * 保存人员编制构成
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-7-16 - 下午2:33:35
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param secrecyOffice
	 * @param establishSituation
	 * @param leaderStaff
	 * @param employPerson
	 */
	void saveEstablishSituation(SecrecyOffice secrecyOffice,
			EstablishSituation establishSituation, LeaderStaff leaderStaff,
			EmployPerson employPerson);

	/**
	 * <p>
	 * 保存内设机构基本情况
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-7-17 - 上午11:23:06
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param secrecyOffice
	 * @param internalOrganList
	 */
	void saveInternalOrgan(SecrecyOffice secrecyOffice, List<InternalOrgan> internalOrganList);

	/**
	 * <p>
	 * 初始化内设机构基本情况
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-7-17 - 下午5:45:16
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param dictionaryOptionList
	 * @param secrecyOffice
	 * @return
	 */
	List<Map<String, Object>> initInternalOrgan(SecrecyOffice secrecyOffice, List<DictionaryOption> dictionaryOptionList);

	/**
	 * <p>
	 * 新增、编辑基础设施建设
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-7-18 - 下午1:46:34
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param secrecyOffice
	 * @param infrastructureList
	 */
	void saveInfrastructure(SecrecyOffice secrecyOffice,
			List<Infrastructure> infrastructureList);

	/**
	 * <p>
	 * 根据当前登陆用户和实体名称查询，人员编制构成情况
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-7-20 - 下午4:59:52
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param string
	 * @param district
	 */
	List<?> queryEstablishSituation(String string, District district, boolean isLayer);

	/**
	 * <p>
	 * 根据当前登陆用户查询各年龄阶段人员个数
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-7-22 - 上午11:33:37
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param currentUser
	 */
	Map<String, Integer> queryYearMap(District district, boolean isLayer);

	/**
	 * <p>
	 * 据当前登陆用户查询各学历阶段人员个数
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-7-22 - 下午2:29:13
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param currentUser
	 * @return
	 */
	Map<String, Integer> queryDiplomaMap(District district, boolean isLayer);

	/**
	 * <p>
	 * 据当前登陆用户查询各专业阶段人员个数
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-7-22 - 下午2:55:53
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param currentUser
	 * @return
	 */
	Map<String, Integer> querySpecialMap(District district, boolean isLayer);

	/**
	 * <p>
	 * 据当前登陆用户查询各岗位人员个数
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-7-22 - 下午5:03:52
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param currentUser
	 * @return
	 */
	Map<String, Integer> queryPostTypeMap(District district, boolean isLayer);

	/**
	 * <p>
	 * 根据行政区查询保密局基础设施
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-7-23 - 上午10:13:14
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param district
	 * @param isLayer
	 */
	Map<String, Integer> queryInfrastructure(District district, boolean isLayer);

	/**
	 * <p>
	 * 查询当前行政区的保密局
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-7-27 - 下午4:14:04
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
	List<Organ> getOrganListByDistrict(District district);

}