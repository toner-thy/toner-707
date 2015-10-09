package com.cdthgk.bmp.secrecysystem.secrecycomputer.service;

import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.secrecycountryitem.qo.ZongHeTongJiStatDto;
import com.cdthgk.bmp.secrecysystem.secrecycomputer.vo.SecrecyComputer;
import com.cdthgk.platform.base.service.GenericBasicService;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

public interface SecrecyComputerService extends GenericBasicService<SecrecyComputer, String> {

	/**
	 * <p>
	 * 分页获取计算机
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
	 * @param secrecyComputer
	 * @param checkLower
	 * @return
	 */
	public List<SecrecyComputer> getListPage(PageSortModel<SecrecyComputer> psm,
			SecrecyComputer secrecyComputer, District district, boolean flag, User currentUser, String checkLower);

	/**
	 *
	 * <p>
	 * 方法名：checkDiskSeq 验证硬盘序列号
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-13 下午2:44:04
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@param diskSeq
	 *@return ture:检查结果唯一；false:检查结果不唯一；
	 */
	public Boolean checkDiskSeq(String diskSeq, String checkType, String secrecyComputerId);

	/**
	 *
	 * <p>
	 * 方法名：checkComputerNo 验证计算机序号单位内唯一
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-13 下午2:45:04
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
	 *@param computerNo
	 *@return ture:检查结果唯一；false:检查结果不唯一；
	 */
	public Boolean checkComputerNo(Organ organ, String computerNo, String checkType, String secrecyComputerId);

	/**
	 *
	 * <p>
	 * 方法名称  addDepartment 检查并返回部门信息，通过部门名查询，若不存在则新增
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-26 下午2:43:40
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
	 * 方法名称  addUserinfo 检查人员信息，通过人名查询，不存在则新增
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-26 下午2:50:01
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
	 * 方法名称  dealSecrecyComputer 处理涉密计算机信息，存在则更新，不存在则新增
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-28 下午6:06:14
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@param secrecyComputer
	 *@param user
	 *@return
	 */
	public SecrecyComputer addSecrecyComputer(SecrecyComputer secrecyComputer, User user);

	/**
	 *
	 * <p>
	 * 方法名称  getSecrecyCountryItim_Total_District 涉密计算机综合统计  总数
	 * </p>
	 * <p>
	 * 宋亚非 2013-9-4 下午1:58:51
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
	public Map<String, Object> getSecrecyComputer_Total_District(District district, int reflag);

	/**
	 *
	 * <p>
	 * 方法名称  count_SecrecyCountryItim_District  涉密计算机综合统计
	 * </p>
	 * <p>
	 * 宋亚非 2013-9-4 下午2:04:56
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
	public List<ZongHeTongJiStatDto> count_SecrecyComputer_District(List<District> districtList, boolean isGroup);

	/**
	 *
	 * <p>
	 * 方法名称  count_SecrecyCountryItim_Organ  涉密计算机综合统计
	 * </p>
	 * <p>
	 * 宋亚非 2013-9-4 下午2:16:15
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
	public List<ZongHeTongJiStatDto> count_SecrecyComputer_Organ(District district, Organ organ, boolean needTotal);

	/**
	 *
	 * <p>
	 * 方法名称  count_SecrecyPerson_District 涉密计算机综合统计
	 * </p>
	 * <p>
	 * 宋亚非 2013-9-4 下午3:08:22
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
	public List<ZongHeTongJiStatDto> count_SecrecyComputer_District(District district, boolean isGroup,Organ organ);
}
