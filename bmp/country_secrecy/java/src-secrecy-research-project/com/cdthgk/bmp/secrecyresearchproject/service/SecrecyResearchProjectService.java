package com.cdthgk.bmp.secrecyresearchproject.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.bmp.secrecycountryitem.qo.ZongHeTongJiStatDto;
import com.cdthgk.bmp.secrecyresearchproject.vo.SecrecyResearchProject;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

public interface SecrecyResearchProjectService extends BmpServiceTemplate<SecrecyResearchProject, Serializable>{

	/**
	 * 查询 涉密科研项目 列表
	 * @param psm    分页对象
	 * @param organ   单位
	 * @param secrecyResearchProject  涉密科研项目对象
	 * @return
	 */
	public List<SecrecyResearchProject> querySecrecyResearchProjectList(PageSortModel psm, Organ organ, SecrecyResearchProject secrecyResearchProject) ;

	/**
	 * 查询 涉密科研项目 列表
	 * @param psm    分页对象
	 * @param district  行政区划
	 * @param isChildren 包含下级  1包含  0不包含
	 * @param secrecyResearchProject  涉密科研项目对象
	 * @return
	 */
	public List<SecrecyResearchProject> querySecrecyResearchProjectList(PageSortModel psm, District district, int isChildren, SecrecyResearchProject secrecyResearchProject);

	/**
	 * 查询  涉密科研项目  相关联的表 有哪些
	 * 1  变更表
	 * 2  解密表
	 * @param secrecyResearchProjectId    涉密科研项目id
	 * @return
	 */
	public Integer getRelationshipForTable(String secrecyResearchProjectId);

	/**
	 * 通过部门名称 直接保存部门,
	 * @param departName  保存部门
	 * @param u  人员
	 * @return
	 */
	public Department saveDepartmentByName(String departName, User u) ;

	/**
	 * 直接保存定密负责人人员信息
	 * @param userInfo  需要保存的人员对象  必须有名字和单位信息
	 * @param user  登录的用户
	 * @return
	 */
	public UserInfo saveSecrecyPerson(UserInfo userInfo, User user);

	/**********************************************************统计****************************************************/
	/**
	 * 统计 本单位  涉密科研项目的总数
	 * 对 没有解密的  当前单位的   涉密科研项目 的记录  进行统计
	 * @param organ
	 * @return
	 */
	public Map<String, Object> getSecrecyResearchProject_Total_CurrentOrgan(Organ organ);

	/** 保密局    统计 涉密科研项目
	 *  对 没有解密的    涉密科研项目  的记录  进行统计
	 * @param district  行政区划对象
	 * @param reflag    1行政区  2直辖单位
	 * @return
	 */
	public Map<String, Object> getSecrecyResearchProject_Total_District(District district, int reflag);


	/***************************************************************************综合统计涉密科研项目******start*********************************************************/
	/**
	 * 综合统计  统计涉密科研项目的个数.
	 * 包含了行政区划和直辖单位的个数    都要按照密级统计出来
	 *
	 * @param districtList    行政区划对象集合
	 * @param isGroup         是否需要合计数据
	 *
	 * @return 查询出来的列
	 *  district_name(行政区划名字)
	 *  district_code(行政区划编码)
	 *  a1 a2 a3 (按照行政区划统计出的个数信息)
	 *  b1 b2 b3 (按照直辖单位统计出的个数信息)
	 *
	 */
	public List<ZongHeTongJiStatDto> count_ResearchProject_District(List<District> districtList, boolean isGroup);

	/**
	 *  综合统计 sql  查询出 单位下的涉密科研项目个数  都要按照密级统计出来
	 * @param organ  单位
	 * @param district 行政区划
	 * @param needTotal 是否合计
	 *
	 * @return
	 *  查询出来的列 :
	 *  organName(本单位名字)
	 *  organId(本单位编码)
	 *  a1 a2 a3 (按照本单位统计出的个数信息)
	 */
	public List<ZongHeTongJiStatDto> count_ResearchProject_Organ(District district, Organ organ, boolean needTotal);

	/**通过行政区划对象统计   分单位显示行政区划下面的统计情况
	 * 综合统计  查询 行政区划涉密科研项目统计  通过行政区划编码
	 * @param district  行政区划对象 , 每个行政区划必须包含  districtCode
	 * @param isGroup  是否合计
	 * @param organ   单位对象   ，这个对象可以为空，只是在模糊查询的时候我们会用到，单位的名字
	 *
	 * @return
	 */
	public List<ZongHeTongJiStatDto> count_ResearchProject_District(District district, boolean isGroup,Organ organ);

	/**
	 * 查询 涉密科研项目 列表    只有行政区划的时候  查询单位下面的密品情况
	 * @param psm    分页对象
	 * @param district  行政区划
	 * @param secrecyResearchProject  涉密科研项目对象
	 * @return
	 */
	public List<SecrecyResearchProject> querySecrecyResearchProjectList(PageSortModel psm, District district, SecrecyResearchProject secrecyResearchProject);

	/***************************************************************************综合统计涉密科研项目******end*********************************************************/
}
