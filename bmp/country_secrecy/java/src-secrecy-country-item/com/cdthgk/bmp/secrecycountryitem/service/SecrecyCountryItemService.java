package com.cdthgk.bmp.secrecycountryitem.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.bmp.keysection.vo.KeySection;
import com.cdthgk.bmp.secrecycountryitem.qo.CountrySecrecyQueryObject;
import com.cdthgk.bmp.secrecycountryitem.qo.ZongHeTongJiStatDto;
import com.cdthgk.bmp.secrecycountryitem.vo.SecrecyCountryItem;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

/**
 * 商业秘密事项      service层
 * @author lwj 2013-07-15
 */
public interface SecrecyCountryItemService extends BmpServiceTemplate<SecrecyCountryItem, Serializable>{

	/**
	 * 查询  商业秘密事项
	 * @param psm   分页对象
	 * @param organ   单位对象
	 * @param secrecyCountryItem  商业秘密事项对象
	 * @return
	 */
	public List<SecrecyCountryItem> queryCountryItemList(PageSortModel psm, Organ organ, SecrecyCountryItem secrecyCountryItem);

	/**
	 * 查询  商业秘密事项   行政区划 下的数据
	 * 梁文杰 2013-07-16
	 *
	 * @param psm   分页对象
	 * @param district   行政区对象
	 * @param isChildren 包含下级  1包含  0不包含
	 * @param secrecyCountryItem  商业秘密事项对象
	 * @return
	 */
	public List<SecrecyCountryItem> queryCountryItemList(PageSortModel psm, District district, int isChildren,SecrecyCountryItem secrecyCountryItem);

	/**
	 * 查询  商业秘密事项   本单位下的查询  但是没有单位对象，只有行政区划的对象
	 * 梁文杰 2013-07-16
	 *
	 * @param psm   分页对象
	 * @param district   行政区对象
	 * @param secrecyCountryItem  商业秘密事项对象
	 * @return
	 */
	public List<SecrecyCountryItem> queryCountryItemList(PageSortModel psm, District district, SecrecyCountryItem secrecyCountryItem);
	/**
	 * 查询  商业秘密事项  相关联的表 有哪些
	 * 1  变更表
	 * 2  解密表
	 * @param secrecyCountryItemId    商业秘密事项id
	 * @return
	 */
	public Integer getRelationshipForTable(String secrecyCountryItemId);

	/**
	 * 通过部门名称 直接保存部门,
	 * @param departName  保存部门
	 * @param u  人员
	 * @return
	 */
	public Department saveDepartmentByName(String departName, User u);


	/**
	 * 通过部门对象  和人   直接新增一个保密要害部门
	 * @param department  部门对象
	 * @param u   人对象
	 * @return
	 */
	public KeySection saveKeySection(Department department, User u);

	/**
	 * 直接保存定密负责人人员信息
	 * @param userInfo  需要保存的人员对象  必须有名字和单位信息
	 * @param user  登录的用户
	 * @return
	 */
	public UserInfo saveSecrecyPerson(UserInfo userInfo, User user);

	////////////////////////////**********************************统计*********************************///////////////////////////


	/**(本单位)  按照部门显示
	 *  统计本单位 商业秘密的情况
 	 *	按照部门显示
	 * @param organ  单位
	 * @param obj    查询对象
	 * @return
	 */
	public List<Map<String,Object>> getOrganCountrySecrecy_ByDepartment(Organ organ, CountrySecrecyQueryObject obj);

	/**(本单位)  按照单位显示
	 *  统计本单位 商业秘密的情况
	 *  按照单位显示
	 * @param organ  单位
	 * @param obj    查询对象
	 * @return
	 */
	public List<Map<String,Object>> getOrganCountrySecrecy(Organ organ, CountrySecrecyQueryObject obj);

	/**(本单位)
	 * 查询本单位的商业秘密事项总数
	 * 按照查询对象过滤
 	 * 按照部门显示
	 * @param organ  单位
	 * @param obj    查询对象
	 * @return
	 */
	public Map<String, Object> getCountrySecrecyTotal_ByDepartment(Organ organ, CountrySecrecyQueryObject obj);

	/**行政区划
	 * 行政区划统计  商业秘密
	 * @param  district  行政区划
	 * @param  obj       查询对象
	 * @return
	 */
	public List<Map<String,Object>> getCountrySecrecy_District(District district ,CountrySecrecyQueryObject obj);


	/**直辖单位
	 * 直辖单位  统计 商业秘密
	 * @param  district  行政区划
	 * @param  obj       查询对象
	 * @return
	 */
	public List<Map<String,Object>> getCountrySecrecy_Layer(District district ,CountrySecrecyQueryObject obj);


	/**单位明细
	 * 单位明细统计 商业秘密
	 * @param  district  行政区划
	 * @param  obj       查询对象
	 * @return
	 */
	public List<Map<String,Object>> getCountrySecrecy_OrganDetail(District district ,CountrySecrecyQueryObject obj);


	/**(本单位)  要害部门统计商业秘密事项
	 *  要害部门统计商业秘密事项
	 * @param organ  单位
	 * @return
	 */
	public List<Map<String,Object>> getOrganSecrecyCountryItim_BySection(Organ organ);

	/**保密局    统计商业秘密事项
	 * 保密要害部门统计商业秘密事项
	 * @param district  行政区划对象
	 * @param reflag    1行政区  2直辖单位
	 * @return
	 */
	public List<Map<String,Object>> getSecrecyCountryItim_BySection_ByFlag(District district, int reflag);


	/**(本单位)
	 *  统计系统  部门生成 的商业秘密事项
	 * @param organ  单位
	 * @return
	 */
	public List<Map<String,Object>> getOrganSecrecyCountryItim_NoBySection(Organ organ);

	/**
	 * 统计 本单位   商业秘密事项的总数 按照密级统计
	 * @param organ
	 * @return
	 */
	public Map<String, Object> getSecrecyCountryItim_Total_CurrentOrgan(Organ organ);

	/** 保密局    统计商业秘密事项
	 *  对 没有解密的   商业秘密事项 的记录  进行统计
	 * @param district  行政区划对象
	 * @param reflag    1行政区  2直辖单位
	 * @return
	 */
	public Map<String, Object> getSecrecyCountryItim_Total_District(District district, int reflag);

	/**(综合统计)
	 * 通过行政区划对象集合
	 * 综合统计  统计商业秘密事项的  个数.
	 * 包含了   行政区划和行政区内的  商业秘密事项 个数    都需要分密级统计出来
	 *
	 * @param districtList    行政区划对象集合    这里每个行政区划对象必须包含layer对象
	 * @param isGroup         是否需要合计数据
	 * @return
	 * 查询出来的列
	 *  district_name(行政区划名字)
	 *  district_code(行政区划编码)
	 *  a1 a2 a3 (按照行政区划统计出的个数信息)
	 *  b1 b2 b3 (按照直辖单位统计出的个数信息)
	 */
	public List<ZongHeTongJiStatDto> count_SecrecyCountryItim_District(List<District> districtList, boolean isGroup);

	/**(综合统计 )
	 *  综合统计   查询出 一个单位下的商业秘密事项个数  都要按照密级统计出来
	 * @param organ  单位    这个的单位对象必须要包含organId信息
	 * @param district
	 * @param needTotal 是否合计
	 * @return
	 *  查询出来的列 :
	 *  organName(本单位名字)
	 *  organId(本单位编码)
	 *  a1 a2 a3 (按照本单位统计出的个数信息)
	 */
	public List<ZongHeTongJiStatDto> count_SecrecyCountryItim_Organ(District district,Organ organ, boolean needTotal);

	/**综合统计  分单位显示行政区划下面的统计情况
	 * 通过行政区划对象统计  分单位显示行政区划下面的统计情况
	 * 综合统计  查询 行政区划商业秘密事项统计  通过行政区划编码
	 * @param district  行政区划对象 , 每个行政区划必须包含  districtCode
	 * @param isGroup  是否合计
	 * @param organ 单位对象   ，这个对象可以为空，只是在模糊查询的时候我们会用到，单位的名字
	 * @return
	 */
	public List<ZongHeTongJiStatDto> count_SecrecyCountryItem_District(District district, boolean isGroup,Organ organ);
}
