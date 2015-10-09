package com.cdthgk.bmp.equipment.equipment.service;

import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.equipment.equipment.vo.EquipmentNumber;
import com.cdthgk.bmp.equipment.equipmenttype.vo.EquipmentType;
import com.cdthgk.platform.base.service.GenericBasicService;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

/**
 * @description 技术设备配备Service.
 * @author 陈文聪 2010 3 4 12:34:56
 * @modify 陈文聪 2010 8 19 02:28:28 修改注释格式
 */

public interface EquipmentService extends GenericBasicService<EquipmentNumber, String> {

	/**
	 * @description 未用
	 * @author 陈文聪 2010 3 4 12:34:56
	 * @modify 陈文聪 2010 8 19 02:31:01 修改注释格式.
	 * @param PageSortModel psm
	 * @param EquipmentNumber equipment
	 * @return List<EquipmentNumber>
	 */
	public List<EquipmentNumber> getPageList(PageSortModel psm, EquipmentNumber equipment);

	//public List<EquipmentType> getEquipmentTypeList(EquipmentType equipmentType);


	/**
	 * @description 批量保存
	 * @author 陈文聪 2010 3 4 12:34:56
	 * @modify 陈文聪 2010 8 19 02:31:42 修改注释格式.
	 * @param Map<String, String> numberMap
	 * @param EquipmentType equipmentType
	 * @return void
	 */
	public void saveButch(Map<String, String> numberMap, EquipmentType equipmentType,User user,Organ organ,Department department);

	void saveOrUpdateBatch(List<EquipmentNumber> equipmentNumberList,String receiveOrganId);

	/**
	 * @description 根据技术设备类型ID读取技术设备信息
	 * @author 陈文聪 2010 3 4 12:34:56
	 * @modify 陈文聪 2010 8 19 02:32:03 修改注释格式.
	 * @param String equipmentTypeId
	 * @return EquipmentNumber
	 */
	public EquipmentNumber getEquipmentNumber(String equipmentTypeId,Organ organ);

	/**
	 * @description 读取技术设备信息
	 * @author 陈文聪 2010 3 4 12:34:56
	 * @modify 陈文聪 2010 8 19 02:32:20 修改注释格式.
	 * @param EquipmentType equipmentType
	 * @param EquipmentNumber equipment
	 * @return List<EquipmentNumber>
	 */
	public List<EquipmentNumber> getEquipmentList(EquipmentType equipmentType, EquipmentNumber equipment,Organ organ);

	/**
	 * @description 获取当前用户所在行政区划下的所有单位数
	 * @author 陈文聪 2010 3 4 12:34:56
	 * @modify 陈文聪 2010 8 19 02:32:43 修改注释格式.
	 * @param Map<String, Object> params
	 * @return List<Map<String,Object>>
	 */
	public List<Map<String, Object>> getTree(Map<String, Object> params,Organ organ);

	/**
	 *
	 * @author 陈文聪 2010 3 4 12:34:56
	 * @modify 陈文聪 2010 8 19 02:33:00 修改注释格式.
	 * @param String id
	 * @param String organId
	 * @return EquipmentNumber
	 */
	public EquipmentNumber getEquipmentNumber(String id, String organId);

	/**
	 * 统计
	 * @param organName
	 * @return
	 */
	public List<Object[]> countStatOrgan(String organName,Organ organ);

	/**
	 * 获得撰取数据
	 * @param equipment
	 * @return
	 */
	public List<Object[]> countDataStat(EquipmentNumber equipment);

	/**
	 * 获得单位历史报表数据
	 * @param reportDto
	 * @return
	 */
	@SuppressWarnings("unchecked")
	/*public List getEquipmentReportOrganList(ReportDto reportDto,Organ organ);*/

	/**
	 *
	 * <p>
	 * 批量修改
	 * </p>
	 * <p>
	 * 向 钰 创建时间  2011 9 3 - 14:05:04
	 * @param equipmentNumberList
	 * @return
	 */
	public void updateBatch(List<EquipmentNumber> equipmentNumberList);
	/**
	 * <p>
	 * 方法的说明
	 * </p>
	 * <p>
	 * 向 钰 创建时间  2011 9 7 - 15:31:31
	 * @param psm
	 * @param district
	 * @return
	 */
	public List<Organ> getDistrictList(PageSortModel psm,
			District district, Organ organ, String status,EquipmentNumber equipment);

	/**
	 * <p>
	 * 方法的说明
	 * </p>
	 * <p>
	 * 向 钰 创建时间  2011 9 8 - 11:07:02
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param organ
	 * @return
	 */
	public List<EquipmentNumber> getEquipmentList(PageSortModel psm,Organ organ);

	/**
	 * <p>
	 * 方法的说明
	 * </p>
	 * <p>
	 * 向 钰 创建时间  2011 9 13 - 14:06:32
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param eqNumberIds
	 * @return
	 */
	public void updateBatchDate(String eqNumberIds,String reportState);

	/**
	 * <p>
	 * 方法的说明
	 * </p>
	 * <p>
	 * 向 钰 创建时间  2011 11 8 - 09:45:14
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param equipmentNumber
	 */
	public void updateBatch(EquipmentNumber equipmentNumber);
}
