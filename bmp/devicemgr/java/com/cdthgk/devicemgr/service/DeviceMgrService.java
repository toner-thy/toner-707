package com.cdthgk.devicemgr.service;

import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.devicemgr.vo.EquipmentLoan;
import com.cdthgk.devicemgr.vo.EquipmentTrash;
import com.cdthgk.devicemgr.vo.SecrecyEquipment;
import com.cdthgk.devicemgr.vo.SecrecyEquipmentCategory;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

/**
 * @description 保密技术设备管理基本操作
 * @author 熊  超 2009-10-12 9:30
 * @modify 彭  维 2010-08-19 9:00 整理注释
 */
@SuppressWarnings("all")
public interface DeviceMgrService extends BmpServiceTemplate<SecrecyEquipment, String>{

	public List<SecrecyEquipment> listForEc(PageSortModel psm, SecrecyEquipment secrecyEquipment,User user);

	public List<EquipmentTrash> listForEc(PageSortModel psm, EquipmentTrash equipmentTrash);

	public void updateTrash(EquipmentTrash equipmentTrash);

	public void saveTrash(EquipmentTrash equipmentTrash);

	public void deleteBatchTrash(List<String> ids);

	public List<EquipmentLoan> listForEc(PageSortModel psm, EquipmentLoan equipmentLoan);

	public void updateLoan(EquipmentLoan equipmentLoan);

	public void saveLoan(EquipmentLoan equipmentLoan);

	public void deleteBatchLoan(List<String> ids);

	public List listForEcPass(PageSortModel psm, EquipmentLoan equipmentLoan);

	public List listForEcAuditPass(PageSortModel psm, EquipmentLoan equipmentLoan);

	public List listForEcAudit(PageSortModel psm, EquipmentLoan equipmentLoan);

	public List listForEcAudit1(PageSortModel psm, EquipmentLoan equipmentLoan);

	public List listForEcAudit(PageSortModel psm,
			EquipmentTrash equipmentTrash);

	public List listForEcAudit1(PageSortModel psm, EquipmentTrash equipmentTrash);

	/**
	 * @description 得到设备列表
	 * @author 熊  超 2009-10-12 9:30
	 * @param null
	 * @return List<Map<String,String>>
	 */
	public List<Map<String,String>> getSecrecyEquipmentList(User user);

	/**
	 *
	 * <p>
	 * 删除技术设备信息
	 * </p>
	 * <p>
	 * 创建人 梁富胜 创建时间 Oct 12, 2010 - 3:24:33 PM
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param ids
	 * @return
	 */
	public String deleteBatchs(List<String> ids);

	/**
	 * @description 查询列表
	 * @author 牟远洋 2013 04 08 11:01:56
	 * @param PageSortModel psm
	 * @param MessageIssue messageIssue
	 * @return List<MessageIssue>
	 */
	public List<SecrecyEquipment> getSecrecyEquipmentList(PageSortModel psm, SecrecyEquipmentCategory secrecyEquipmentCategor, SecrecyEquipment secrecyEquipment, Organ organ);

        public List<SecrecyEquipment> listById(String hql, PageSortModel psm);
}