package com.cdthgk.platform.dataAuthority.service;

import java.util.List;
import java.util.Map;

import com.cdthgk.disclosesecrecy.dto.DiscloseSecrecyStatDto;
import com.cdthgk.disclosesecrecy.vo.DiscloseSecrecy;
import com.cdthgk.platform.base.service.GenericBasicService;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

public interface DataAuthorityRoleService extends
GenericBasicService<DiscloseSecrecy, String> {

    /**
     * 用于导出数据时
     * @param discloseSecrecy
     * @param user
     * @param districtCode
     * @param includeChild
     * @return
     */
	public List<DiscloseSecrecy> listForAll(DiscloseSecrecy discloseSecrecy,User user,String districtCode,String includeChild);
	/**
	 * 本单位或者是保密局 泄密案件 列表
	 * @param psm 分页
	 * @param discloseSecrecy 查询时传入
	 * @param user 登陆用户
	 * @param baomijuObendanwei false查询本单位的，true 查询保密局的
	 * @return
	 */
	public List<DiscloseSecrecy> listForEc(PageSortModel<DiscloseSecrecy> psm,
			DiscloseSecrecy discloseSecrecy, User user ,boolean baomijuObendanwei);
	/**
	 * 保密局查询列表
	 * @param psm
	 * @param discloseSecrecy
	 * @param user
	 * @param baomijuObendanwei
	 * @return
	 */
	public List<DiscloseSecrecy> listForSelect(PageSortModel<DiscloseSecrecy> psm,
			DiscloseSecrecy discloseSecrecy, String districtCode ,String includeChild);
	public int getDisclosesecrecycaseIdRelationship(String disclosesecrecycaseId);
	/**
	 * 泄密事件统计
	 * @param organId 单位id
	 * @param dealResult 查处结果（1,2）
	 * @param groupBy 分组字段
	 * @param className 统计对象（DiscloseSecrecy--统计泄密案件,CaseCriticalviolation--统计严重违规案件）
	 * @param district 行政区域
	 * @param zhixiadanwei boolean true为统计直辖单位（挂在选择区域下（不包括子区域下的单位！）的单位机构泄密事件），false统计保密局（选择区域下的所有单位机构（包括子区域下）的泄密事件）
	 * @return
	 */
	public List<Map<String, Object>> countDiscloseSecrecy(
			String organId,String dealResult,String groupBy,String className,String district,boolean zhixiadanwei);
	/**
	 * 直辖单位泄密案件统计--表格展示列出各个直辖单位的值
	 * @param organId 单位id
	 * @param dealResult 查处结果（1,2）
	 * @param groupBy 分组字段
	 * @param className 统计对象（DiscloseSecrecy,CaseCriticalviolation）
	 * @param district 行政区域
	 * @return
	 */
	public List<List<String>> countDiscloseSecrecy_zhixiadanwei(
			String dealResult,String groupBy,String className,String district);
	/**
	 *  本单位的泄密事件按照密级统计，
	 * @param organId
	 * @return
	 */
	public List<Map<String, Object>> countDiscloseSecrecyBySecrecy_Level(
			String organId,String dealResult,String className);
	/**
	 *
	 * @param organId 本单位，可以为null
	 * @param classname 查询对象
	 * @param dealResult 查询结果 可以为null
	 * @param district 查询区域
	 * @param zhixiadanwei true直辖单位,false 保密局；
	 * @return
	 */
	public Integer getDiscloseSecrecyTotal(String organId,String classname,String dealResult,String district,boolean zhixiadanwei);
	public List<Map<String, Object>> countCaseCriticalviolationBySecrecyLevel(
			String organId,String className);
	/**
	 * 泄密事件综合统计（区域和子区域）区域详情
	 * @param districtList 行政区划 对象  集合
	 * @param b 是否需要 总数
	 * @param organ  组织对象
	 * @param className  查询对象
	 * @return
	 */
	public List<DiscloseSecrecyStatDto> queryDiscloseSecrecyByDistrict(
			List<District> districtList, boolean b, Organ organ,String className);
	/**
	 * 泄密事件综合统计 直辖单位详情
	 * @param districtList 行政区划 对象  集合
	 * @param b 是否需要 总数
	 * @param organ  组织对象
	 * @param className  查询对象
	 * @return
	 */
	public List<DiscloseSecrecyStatDto> queryDiscloseSecrecyByOrgan(
			District  district, boolean b, Organ organ,String className);
	public List<Object[]> getOrganKeySection(Organ organ,String tableName);

}
