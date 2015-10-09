package com.cdthgk.bmp.equipment.equipmenttype.service;

import java.util.List;

import com.cdthgk.bmp.equipment.equipmenttype.vo.EquipmentType;
import com.cdthgk.platform.base.service.GenericBasicService;
import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

/**
 * @description 设备分类Service.
 * @author 陈文聪 2010 3 4 12:34:56
 * @modify 陈文聪 2010 8 19 02:22:17 修改注释格式
 */

public interface EquipmentTypeService extends GenericBasicService<EquipmentType, String> {

	/**
	 * @description 获取技术设备类型
	 * @author 陈文聪 2010 3 4 12:34:56
	 * @modify 陈文聪 2010 8 19 02:25:45 修改注释格式.
	 * @param PageSortModel psm
	 * @param EquipmentType equipmentType
	 * @return List<EquipmentType>
	 */
	public List<EquipmentType> getPageList(PageSortModel psm, EquipmentType equipmentType);

	public void saveEquipmet(EquipmentType equipmentType, User user,
			Organ organ, Department department);

	/**
	 * <p>
	 * 方法的说明
	 * </p>
	 * <p>
	 * 向 钰 创建时间  2011 11 1 - 14:25:47
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param equipmentNumberList
	 */
	public void saveOrUpdateBatch(List<EquipmentType> equipmentNumberList,String receiveOrganId);

	/**
	 * <p>
	 * 方法的说明
	 * </p>
	 * <p>
	 * 向 钰 创建时间  2011 11 1 - 15:32:41
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param equipmentTypeIds
	 * @param organId
	 */
	public void updateBatch(String equipmentTypeIds, String organId);

	/**
	 * <p>
	 * 方法的说明
	 * </p>
	 * <p>
	 * 向 钰 创建时间  2011 11 8 - 09:53:27
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param equipmentType
	 */
	public void updateBatch(EquipmentType equipmentType);

	/**
	 * <p>
	 * 方法的说明
	 * </p>
	 * <p>
	 * 向 钰 创建时间  2011 11 8 - 10:30:11
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param equipmentTypeId
	 * @return
	 */
	/*public List<EquipmentTypeData> queryTransmitObject(String equipmentTypeId);*/

	public void updateBatch(List<EquipmentType> etList);

}
