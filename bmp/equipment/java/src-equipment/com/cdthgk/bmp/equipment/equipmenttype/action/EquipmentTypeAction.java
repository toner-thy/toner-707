package com.cdthgk.bmp.equipment.equipmenttype.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.cdthgk.bmp.equipment.equipment.service.EquipmentService;
import com.cdthgk.bmp.equipment.equipment.vo.EquipmentNumber;
import com.cdthgk.bmp.equipment.equipmenttype.service.EquipmentTypeService;
import com.cdthgk.bmp.equipment.equipmenttype.vo.EquipmentType;
import com.cdthgk.common.lang.UUIDGenerator;
import com.cdthgk.platform.base.action.PlatformAction;
import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.organ.service.OrganService;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

/**
 * @description 设备分类Action.
 * @author 陈文聪 2010 3 4 12:34:56
 * @modify 陈文聪 2010 8 19 02:22:17 修改注释格式
 */

public class EquipmentTypeAction extends PlatformAction {

	// Fields

	private static final long serialVersionUID = 1L;
	private EquipmentNumber equipment;
	private EquipmentType equipmentType;
	private EquipmentService equipmentService;
	private EquipmentTypeService equipmentTypeService;
	private String organIds;
	private String EquipmentIds;
	private Boolean needReload = false;
	/*private List<TransmitLogMain> transmitLogMainList;*/

	private OrganService organService;
	// 联系人组件
	/*private ModuleContact moduleContact;
	private ModuleContactService moduleContactService;*/

	// methods
	/**
	 * @description 取得技术设备类型列表
	 * @author 陈文聪 2010 3 4 12:34:56
	 * @modify 陈文聪 2010 8 19 02:23:02 修改注释格式.
	 */
	public String list() {
		String tableId = "equipmentTypeList";
		PageSortModel psm = new PageSortModel(getRequest(), tableId);
		this.putToRequest("equipmentTypeList", equipmentTypeService.getPageList(psm, equipmentType));
		return SUCCESS;
	}

	/**
	 * @description 转向到新增
	 * @author 陈文聪 2010 3 4 12:34:56
	 * @modify 陈文聪 2010 8 19 02:23:20 修改注释格式.
	 */
	public String add() {
		return SUCCESS;
	}

	/**
	 * @description 新增保存
	 * @author 陈文聪 2010 3 4 12:34:56
	 * @modify 陈文聪 2010 8 19 02:23:29 修改注释格式.
	 */
	public String save() {
		User user = getCurrentUser();
		Organ organ = getCurrentUser().getUserInfo().getOrgan();
		Department department = getCurrentUser().getUserInfo().getDepartment();
		Date date = new Date();

		equipmentType.setCreatePerson(user);
		equipmentType.setCreateTime(date);
		equipmentType.setDepartment(department);
		equipmentType.setModifyPerson(user);
		equipmentType.setModifyTime(date);
		equipmentType.setOrgan(organ);
		equipmentType.setEquipmentTypeId(UUIDGenerator.generateUUID32());
		equipmentType.setState(EquipmentType.PUBLISH_NO);
		equipmentTypeService.save(equipmentType);
		needReload = true;
		addActionMessage("新增保密设备成功。");
		return SAVE;
	}

	public String publishEquipment() {

		List<EquipmentType> etList = new ArrayList<EquipmentType>();

		for (String id : getIds()) {
			EquipmentType equipmentType = equipmentTypeService.get(id);
			equipmentType.setState(EquipmentType.PUBLISH_YES);

			etList.add(equipmentType);
		}

		equipmentTypeService.updateBatch(etList);

		return redirectActionResult(LIST);

	}

	public String publishingEquipment(){
		if(EquipmentIds != null && !"".equals(EquipmentIds)) {
			List<String> organIdList = Arrays.asList(organIds.split(","));
			Iterator<String> i = organIdList.iterator();
			while (i.hasNext()) {
				String organId = i.next();
				//equipmentTypeService.reportToOrgan(Arrays.asList(EquipmentIds.split(",")),  getCurrentUser().getOrgan(), organId);
			}
		}
		return redirectActionResult(LIST);
	}

	/**
	 * @description 转向到编辑
	 * @author 陈文聪 2010 3 4 12:34:56
	 * @modify 陈文聪 2010 8 19 02:24:02 修改注释格式.
	 */
	public String edit() {
		equipmentType = equipmentTypeService.get(this.getId());
		return SUCCESS;
	}

	/**
	 * @description 编辑更新
	 * @author 陈文聪 2010 3 4 12:34:56
	 * @modify 陈文聪 2010 8 19 02:24:11 修改注释格式.
	 */
	public String update() {
		User user = getCurrentUser();
		EquipmentType et = equipmentTypeService.get(equipmentType.getEquipmentTypeId());

		et.setModifyPerson(user);
		et.setModifyTime(new Date());
		et.setName(equipmentType.getName());
		et.setDescription(equipmentType.getDescription());
		equipmentTypeService.update(et);
		needReload = true;
		addActionMessage("编辑保密设备成功。");
		return UPDATE;
	}

	/**
	 * @description 删除记录
	 * @author 陈文聪 2010 3 4 12:34:56
	 * @modify 陈文聪 2010 8 19 02:24:41 修改注释格式.
	 */
	public String delete() {
		List<String> ids = this.getIds();
		if(ids != null){
			boolean flag = false;
			for(int i=0;i<ids.size();i++){
				EquipmentType et = equipmentTypeService.get(ids.get(i));
				if(EquipmentType.PUBLISH_YES.equals(et.getState())){
					flag = true;
				}
			}
			if(!flag){
				//equipmentTypeService.deleteBatchWithId(this.getIds());
				equipmentTypeService.deleteBatchIdList(this.getIds());
				addActionMessage("删除保密设备成功。");
				needReload = true;
			}else{
				addActionMessage("你选择的设备可能已经被发布,不能删除!");
			}
		}
		return "list_action";
	}

	/**
	 * @description 显示详情.
	 * @author 陈文聪 2010 3 4 12:34:56
	 * @modify 陈文聪 2010 8 19 02:24:50 修改注释格式.
	 */
	public String view() {
		equipmentType = equipmentTypeService.get(this.getId());
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String viewObject() {
		/*List<EquipmentTypeData> equipmentTypeDataList = equipmentTypeService.queryTransmitObject(equipmentType.getEquipmentTypeId());
		Set<TransmitLogMain> transmitLogMainSet = new HashSet<TransmitLogMain>();
		for (EquipmentTypeData equipmentTypeData : equipmentTypeDataList) {
			transmitLogMainSet.add(equipmentTypeData.getTransmitLog().getTransmitLogMain());
		}
		transmitLogMainList = new ArrayList(transmitLogMainSet);
		Collections.sort(transmitLogMainList);*/
		return SUCCESS;
	}

	// geter & seter

	public EquipmentType getEquipmentType() {
		return equipmentType;
	}
	public void setEquipmentType(EquipmentType equipmentType) {
		this.equipmentType = equipmentType;
	}
	public EquipmentTypeService getEquipmentTypeService() {
		return equipmentTypeService;
	}
	public void setEquipmentTypeService(EquipmentTypeService equipmentTypeService) {
		this.equipmentTypeService = equipmentTypeService;
	}

	public EquipmentService getEquipmentService() {
		return equipmentService;
	}

	public void setEquipmentService(EquipmentService equipmentService) {
		this.equipmentService = equipmentService;
	}

	public EquipmentNumber getEquipment() {
		return equipment;
	}

	public void setEquipment(EquipmentNumber equipment) {
		this.equipment = equipment;
	}

	/**
	 * @return the organIds
	 */
	public String getOrganIds() {
		return organIds;
	}

	/**
	 * @param organIds the organIds to set
	 */
	public void setOrganIds(String organIds) {
		this.organIds = organIds;
	}

	/**
	 * @return the equipmentIds
	 */
	public String getEquipmentIds() {
		return EquipmentIds;
	}

	/**
	 * @param equipmentIds the equipmentIds to set
	 */
	public void setEquipmentIds(String equipmentIds) {
		EquipmentIds = equipmentIds;
	}

	/**
	 * @return the transmitLogMainList
	 */
	/*public List<TransmitLogMain> getTransmitLogMainList() {
		return transmitLogMainList;
	}

	*//**
	 * @param transmitLogMainList the transmitLogMainList to set
	 *//*
	public void setTransmitLogMainList(List<TransmitLogMain> transmitLogMainList) {
		this.transmitLogMainList = transmitLogMainList;
	}*/

	public OrganService getOrganService() {
		return organService;
	}

	public void setOrganService(OrganService organService) {
		this.organService = organService;
	}

	/*public ModuleContact getModuleContact() {
		return moduleContact;
	}

	public void setModuleContact(ModuleContact moduleContact) {
		this.moduleContact = moduleContact;
	}

	public ModuleContactService getModuleContactService() {
		return moduleContactService;
	}

	public void setModuleContactService(ModuleContactService moduleContactService) {
		this.moduleContactService = moduleContactService;
	}*/

	/**
	 * @return the needReload
	 */
	public Boolean getNeedReload() {
		return needReload;
	}

	/**
	 * @param needReload the needReload to set
	 */
	public void setNeedReload(Boolean needReload) {
		this.needReload = needReload;
	}

}
