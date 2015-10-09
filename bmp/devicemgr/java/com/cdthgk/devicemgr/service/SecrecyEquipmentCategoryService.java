package com.cdthgk.devicemgr.service;

import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.devicemgr.vo.SecrecyEquipment;
import com.cdthgk.devicemgr.vo.SecrecyEquipmentCategory;
import com.cdthgk.platform.base.service.Service;

import ec.common.PageSortModel;
/**
 * @description 保密技术设备管理服务接口
 * @author 熊  超 2009-10-12 9:30
 * @modify 彭  维 2010-08-19 9:00 整理注释
 */
@SuppressWarnings("all")
public interface SecrecyEquipmentCategoryService extends BmpServiceTemplate<SecrecyEquipmentCategory, String>{

	/**
	 * @description 得到保密设备分类列表
	 * @author 熊  超 2009-10-12 9:30
	 * @param PageSortModel pageSortModel
	 * @param Map<String, Object> params
	 * @return List<SecrecyEquipmentCategory>
	 */
	public List<SecrecyEquipmentCategory> getPageList(PageSortModel pageSortModel,Map<String, Object> params);

	/**
	 * @description 得到保密设备分类树
	 * @author 熊  超 2009-10-12 9:30
	 * @param PageSortModel pageSortModel
	 * @param Map<String, Object> params
	 * @return List<SecrecyEquipmentCategory>
	 */
	public List<Map<String,Object>> getTree();

	/**
	 * <p>
	 * 设备列表
	 * </p>
	 * @param psm
	 * @param secrecyEquipmentCategory
	 * @param secrecyEquipment
	 * @return
	 */
	List<SecrecyEquipmentCategory> getListPage(PageSortModel psm,
			SecrecyEquipmentCategory secrecyEquipmentCategory,
			SecrecyEquipment secrecyEquipment);
	/**
	 * <p>
	 * 是否存在子节点
	 * </p>
	 * @param secrecyEquipmentCategory
	 * @return
	 */
	public Long hasChildren(SecrecyEquipmentCategory secrecyEquipmentCategory);

}
