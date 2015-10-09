package com.cdthgk.bmp.keysection.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpService;
import com.cdthgk.bmp.keysection.dto.KeySectionStatDto;
import com.cdthgk.bmp.keysection.vo.KeySection;
import com.cdthgk.bmp.secrecycountryitem.qo.ZongHeTongJiStatDto;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;

/**
 * <p>
 * 要害部门Service类（外部模块使用）
 * </p>
 * <p>
 * 王彭波 2012-12-14 18:01
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
 * @author wangpb
 * @since 1.0
 * @version 1.0
 */
public interface KeySectionService extends BmpService<KeySection, Serializable> {

	/**(本单位)
	 * 本单位统计 保密要害部门    按照要害部门的密级
	 * 梁文杰 2013-07-18
	 * @param organId  单位id
	 * @return
	 */
	public List<Map<String, Object>> count_KeySection_By_Secrecy_Level(String organId);

	/**(本单位)
	 * 返回 本单位的要害部门总个数
	 * 1.通过单位id过滤    2.通过密级是否启用过滤
	 *
	 * @param organId
	 * @return
	 */
	public Integer getOrganSectionTotal(String organId);

	/**(行政区划)
	 * 保密局  统计 行政区划   保密要害部门的信息
	 * 梁文杰  2013-07-23
	 * @param district  行政区划
	 * @return
	 */
	public List<Map<String, Object>> count_KeySection_By_Secrecy_Level_District(District district);


	/**(直辖单位)
	 * 保密局  统计  行政区内  各个单位的   保密要害部门的信息
	 * 梁文杰  2013-07-23
	 * @param district  行政区划对象
	 * @return
	 */
	public List<Map<String, Object>> count_KeySection_By_Secrecy_Level_Layer(District district);

	/**（行政区划）
	 * 保密局     获取要害部门总数
	 * @param district  行政区划
	 * @param reflag    标志信息       1：行政区划统计   2:行政区内的单位统计
	 * @return
	 */
	public Integer getSectionTotal_basic(District district, int reflag);

	/**(单位明细)
	 *  保密局    获取 行政区下面   各个单位的   保密要害部门的信息
	 * 梁文杰  2013-07-23
	 * @param district  行政区划对象
	 * @return
	 */
	public List<Map<String, Object>> count_KeySection_By_Secrecy_Level_OrganDetail(District district);


	/** (单位明细)
	 * 保密局    获取 行政区下面   各个单位的    要害部门总数
	 * 梁文杰  2013-07-23
	 * @param district  行政区划 对象
	 * @return
	 */
	public List<Map<String, Object>>  getOrganDetailSectionTotal(District district);

	/**
	 * 通过要害部门id 查询  要害部门是否被其他的表引用
	 * 1.要害部位表
	 * 2.要害部门密级变更表
	 * 3.要害部门密级解除表
	 * 4.涉密人员表
	 *
	 * @param keySectionId  要害部门id
	 * @return  true有关联  false没有关联
	 */
	public int getKeySectionRelationship(String keySectionId);

	/***********************************************综合统计**********************************************/
	/**
	 *
	 * <p>
	 * 获取单位密级情况
	 * </p>
	 * <p>
	 * 梁文杰 2013-08-22 14:04:56
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @return
	 */
	public List<Object[]> getOrganKeySection(Organ organ);

	/**
	 * 统计  行政区划  和直辖单位的  保密要害部门
	 * @param districtList  行政区划 对象  集合
	 * @param needTotal   是否需要 总数
	 * @param organ  组织对象
	 * @return
	 */
	public List<KeySectionStatDto> queryKeySectionByDistrict(List<District> districtList, boolean needTotal, Organ organ) ;
	/**
	 * 统计  按照单位统计
	 * @param district
	 * @param needTotal
	 * @param organ
	 * @return
	 */
	public List<KeySectionStatDto> queryOrganByDistrict(District district, boolean needTotal, Organ organ);

	/**通过行政区划对象统计   分单位显示行政区划下面的统计情况
	 * 综合统计  查询 行政区划要害部门统计  通过行政区划编码
	 * @param district  行政区划对象 , 每个行政区划必须包含  districtCode
	 * @param isGroup  是否合计
	 * @param organ   单位对象   ，这个对象可以为空，只是在模糊查询的时候我们会用到，单位的名字
	 *
	 * @return
	 */
	public List<ZongHeTongJiStatDto> count_KeySection_District(District district, boolean isGroup,Organ organ);

	/**
	 * @param psm PageSortModel
	 * @param district 行政区划对象
	 * @param keySection keySection
	 * @return List<KeySection>
	 */
	public List<KeySection> queryList(PageSortModel psm, District district,
			KeySection keySection) ;
	/**
	 * @param psm PageSortModel
	 * @param organ 当前登录单位
	 * @param keySection keySection
	 * @return List<KeySection>
	 */
	public List<KeySection> queryList(PageSortModel psm, Organ organ,
			KeySection keySection) ;
}