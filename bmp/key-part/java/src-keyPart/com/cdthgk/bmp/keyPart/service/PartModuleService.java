package com.cdthgk.bmp.keyPart.service;

import java.text.ParseException;
import java.util.List;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.bmp.keyPart.vo.Part;
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
 * PartModuleService
 * 业务模块内需要用到的方法，不需要对外提供
 * </p>
 * 刘椿成 2012-12-15 13:26:59
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
 * @author 刘椿成
 * @since 1.0
 * @version 1.0
 */
public interface PartModuleService extends BmpServiceTemplate<Part, String>{

	/**
	 * @author liucc
	 * @param organ 单位参数
	 * * @param part 对象参数
	 * @return list
	 * 刘椿成 2012-12-15 13:26:59
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
	 * @author 刘椿成
	 * @since 1.0
	 * @version 1.0
	 */
	public List<Part> getListPage(PageSortModel<Part> psm, Part part,Organ organ);

	/**
	 * 行政区划   查询要害部位 返回list
	 * @param psm
	 * @param part
	 * @param district    行政区划对象
	 * @param isChildren  是否查询下级
	 * @return
	 */
	public List<Part> getListPage(PageSortModel<Part> psm,Part part, District district, int isChildren) ;


	/**
	 *
	 * <p>
	 * 方法名：getDictionaryOptionList
	 * </p>
	 * <p>
	 * 宋亚非 2013-5-21 下午6:05:43
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
	 *
	 * <p>
	 * 方法名：getAllList  获取关键部位列表
	 * </p>
	 * <p>
	 * 宋亚非 2013-5-21 下午2:59:42
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@param part
	 *@param organ
	 *@return
	 */
	public List<Part> getAllList(Part part,Organ organ);

	/**
	 *得到要害部位列表(首页获取关键部位列表)
	 * 宋亚非  2013-04-10 09：06
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
	 * @author 刘椿成
	 * @since 1.0
	 * @version 1.0
	 * @author liucc
	 * @param organ 单位参数
	 * *@param part 对象参数
	 * @return list
	 */
	public List<Part> getListPage(PageSortModel psm, Part part,Organ organ, String showType);


	/**
	 * @author liucc
	 * @param name 姓名参数
	 * @return UserInfo
	 * 刘椿成 2012-12-15 13:26:59
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
	 * @author 刘椿成
	 * @since 1.0
	 * @version 1.0
	 */
	UserInfo saveUserInfo(String name,User user);

	/**
	 * @author liucc
	 * @param departmentName 部门名称参数
	 * @return department
	 * 刘椿成 2012-12-15 13:26:59
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
	 * @author 刘椿成
	 * @since 1.0
	 * @version 1.0
	 */
	Department saveDepartment(String departmentName,User user);

	/**
	 * @author liucc
	 * @param partId 要害部位id参数
	 * @return department
	 * 刘椿成 2012-12-15 13:26:59
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
	 * @author 刘椿成
	 * @since 1.0
	 * @version 1.0
	 */
	Department getDepartmentByPartId(String partId);

	/**
	 * <p>
	 * 通过行政区查询所有管辖内组织
	 * </p>
	 * 刘椿成 2012-12-15 13:26:59
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
	 * @author 刘椿成
	 * @since 1.0
	 * @version 1.0
	 */
	List<Part> getAllOrganList(PageSortModel psm,District district, Organ organ,  Part part) throws ParseException;

	/**
	 * <p>
	 * 得到指定单位下的要害部位
	 * </p>
	 * @param psm
	 * @param part
	 * @param organ
	 * @return
	 * 刘椿成 2012-12-15 13:26:59
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
	 * @author 刘椿成
	 * @since 1.0
	 * @version 1.0
	 */
	public Object getOrganPartList(PageSortModel psm, Part part, Organ organ,String districtCode);

	/**
	 * <p>
	 * 保存系统数据 部门，人员
	 * </p>
	 * @param part
	 * @param currentUser
	 * @param deptFlag
	 * @return
	 * 刘椿成 2012-12-15 13:26:59
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
	 * @author 刘椿成
	 * @since 1.0
	 * @version 1.0
	 */
	Part saveSystemDataByPart(Part part, User currentUser, String deptFlag);

	/**
	 * <p>
	 * 数据上报，保存远端信息
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-3-21 - 下午4:06:29
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param partList
	 * @param receiveOrganId
	 * @return
	 */
	public void saveRecKeyPart(List<Part> partList, String receiveOrganId);

	/**
	 *
	 * <p>
	 * 首页统计信息 查询
	 * </p>
	 * <p>
	 * 创建人 刘椿成 创建时间 2012 08 29 - 11:31:25
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>宋亚非  2013-04-09  从市平台移植到省平台
	 * </ul>
	 * </blockquote>
	 * @param PageSortModel psm
	 * @param Organ organ
	 * @param Integer secrecyLevel
	 * @param String districtCode
	 * @return List<Part>
	 */
	List<Part> getIndexPage(PageSortModel psm, Organ organ,
			Integer secrecyLevel, String districtCode);

	/**
	 *
	 * <p>
	 * 获取要害部门下的部位列表
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-4-22 - 上午10:31:34
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param currentUser
	 * @param section
	 * @param entry
	 * @return
	 */
	List<Part> getListBySection(User currentUser, KeySection section);



}