package com.cdthgk.bmp.equipment.equipmentcheck.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.mapping.Array;

import com.cdthgk.bmp.equipment.equipmentcheck.service.EquipmentCheckService;
import com.cdthgk.bmp.equipment.equipmentcheck.vo.EquipmentCheck;
import com.cdthgk.platform.base.action.PlatformAction;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.organ.service.OrganService;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;
import com.cdthgk.platform.permission.user.service.UserService;

import ec.common.PageSortModel;

public class EquipmentCheckAction extends PlatformAction {

	private Boolean needReload = false;
	public String list() {
		PageSortModel psm = new PageSortModel(getRequest(), "equipmentCheckList");
		equipmentCheckList = equipmentCheckService.getEquipmentCheckPageList(psm, equipmentCheck);
		return LIST;
	}

	public String add() {
		return ADD;
	}

	public String adding() {
		boolean flag = false;
		if (equipmentCheck != null) {
			try {
				equipmentCheck.setCreatePerson(getCurrentUser());
				equipmentCheck.setCreateTime(new Date());
				equipmentCheck.setModifyPerson(getCurrentUser());
				equipmentCheck.setModifyTime(new Date());
				equipmentCheckService.save(equipmentCheck);
				flag = true;
			} catch (Exception e) {
				flag = false;
			}
		}
		addActionMessage(flag ? ADD_SUCCESS : ADD_FAILURE);
		needReload = true;
		return redirectActionResult(LIST);
	}

	public String detail() {
		if (equipmentCheck != null) {
			if (equipmentCheck.getEquipmentCheckId() != null && !"".equals(equipmentCheck.getEquipmentCheckId())) {
				equipmentCheck = equipmentCheckService.get(equipmentCheck.getEquipmentCheckId());
			}
		}
		return RETURN_DETAIL;
	}

	public String del() {
		boolean flag = false;
		if (equipmentCheckIds != null && !"".equals(equipmentCheckIds)) {
			try {
				//equipmentCheckService.deleteBatchWithIds(equipmentCheckIds);
				equipmentCheckService.deleteBatchIds(equipmentCheckIds);
				flag = true;
			} catch (Exception e) {
				flag = false;
			}
		}
		addActionMessage(flag ? DEL_SUCCESS : DEL_FAILURE);
		return redirectActionResult(LIST);
	}

	public String edit() {
		if (equipmentCheck != null) {
			if (equipmentCheck.getEquipmentCheckId() != null && !"".equals(equipmentCheck.getEquipmentCheckId())) {
				equipmentCheck = equipmentCheckService.get(equipmentCheck.getEquipmentCheckId());
				//List<UserInfo> userInfoList = new ArrayList<UserInfo>();
				//userInfoList.add(equipmentCheck.getCheckPerson());
				this.putToRequest("userInfo", equipmentCheck.getCheckPerson());
				//List<Organ> organList = new ArrayList<Organ>();
				//organList.add(equipmentCheck.getCheckOrgan());
				this.putToRequest("organ", equipmentCheck.getCheckOrgan());
			}
		}
		return EDIT;
	}

	public String editing() {
		boolean flag = false;
		if (equipmentCheck != null) {
			if (equipmentCheck.getEquipmentCheckId() != null && !"".equals(equipmentCheck.getEquipmentCheckId())) {
				try {
					EquipmentCheck ec = equipmentCheckService.get(equipmentCheck.getEquipmentCheckId());

					if (equipmentCheck.getCheckOrgan().getOrganId() != null &&
							!"".equals(equipmentCheck.getCheckOrgan().getOrganId())) {
						ec.setCheckOrgan(equipmentCheckService.get(equipmentCheck.getCheckOrgan().getOrganId(), Organ.class));
					}
					if (equipmentCheck.getCheckPerson().getUserInfoId() != null &&
							!"".equals(equipmentCheck.getCheckPerson().getUserInfoId())) {
						//ec.setCheckPerson(userService.get(equipmentCheck.getCheckPerson().getUserId(), User.class));
						ec.setCheckPerson(equipmentCheckService.get(equipmentCheck.getCheckPerson().getUserInfoId(), UserInfo.class));
					}
					ec.setCheckContent(equipmentCheck.getCheckContent());
					ec.setCheckTime(equipmentCheck.getCheckTime());
					ec.setRemark(equipmentCheck.getRemark());
					ec.setEquipmentPurpose(equipmentCheck.getEquipmentPurpose());
					ec.setEquipmentProducingArea(equipmentCheck.getEquipmentProducingArea());
					ec.setNumber(equipmentCheck.getNumber());
					ec.setEquipmentType(equipmentCheck.getEquipmentType());
					ec.setEquipmentOrgan(equipmentCheck.getEquipmentOrgan());
					ec.setEquipmentName(equipmentCheck.getEquipmentName());
					equipmentCheckService.update(ec);
					flag = true;
				} catch (Exception e) {
					flag = false;
				}
			}
		}
		addActionMessage(flag ? EDIT_SUCCESS : EDIT_FAILURE);
		needReload = true;
		return redirectActionResult(LIST);
	}


	private static final long serialVersionUID = 1L;
	// 业务逻辑组件
	private EquipmentCheckService equipmentCheckService;
	private OrganService organService;
	private UserService userService;
	//实体
	private EquipmentCheck equipmentCheck;
	//集合
	private List<EquipmentCheck> equipmentCheckList;
	private static final String ADD_SUCCESS = "新增保密设备配备记录成功。";
	private static final String ADD_FAILURE = "新增保密设备配备记录失败。";
	private static final String DEL_SUCCESS = "删除保密设备配备记录成功。";
	private static final String DEL_FAILURE = "删除保密设备配备记录失败。";
	private static final String EDIT_SUCCESS = "编辑保密设备配备记录成功。";
	private static final String EDIT_FAILURE = "编辑保密设备配备记录失败。";
	private static final String RETURN_DETAIL = "detail";
	//删除ids
	private String equipmentCheckIds;

	/**
	 * @return the equipmentCheckIds
	 */
	public String getEquipmentCheckIds() {
		return equipmentCheckIds;
	}

	/**
	 * @param equipmentCheckIds the equipmentCheckIds to set
	 */
	public void setEquipmentCheckIds(String equipmentCheckIds) {
		this.equipmentCheckIds = equipmentCheckIds;
	}

	/**
	 * @return the equipmentCheckList
	 */
	public List<EquipmentCheck> getEquipmentCheckList() {
		return equipmentCheckList;
	}

	/**
	 * @param equipmentCheckList the equipmentCheckList to set
	 */
	public void setEquipmentCheckList(List<EquipmentCheck> equipmentCheckList) {
		this.equipmentCheckList = equipmentCheckList;
	}

	/**
	 * @return the equipmentCheck
	 */
	public EquipmentCheck getEquipmentCheck() {
		return equipmentCheck;
	}

	/**
	 * @param equipmentCheck the equipmentCheck to set
	 */
	public void setEquipmentCheck(EquipmentCheck equipmentCheck) {
		this.equipmentCheck = equipmentCheck;
	}

	/**
	 * @return the equipmentCheckService
	 */
	public EquipmentCheckService getEquipmentCheckService() {
		return equipmentCheckService;
	}

	/**
	 * @param equipmentCheckService
	 *            the equipmentCheckService to set
	 */
	public void setEquipmentCheckService(
			EquipmentCheckService equipmentCheckService) {
		this.equipmentCheckService = equipmentCheckService;
	}

	/**
	 * @return the organService
	 */
	public OrganService getOrganService() {
		return organService;
	}

	/**
	 * @param organService the organService to set
	 */
	public void setOrganService(OrganService organService) {
		this.organService = organService;
	}

	/**
	 * @return the userService
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * @param userService the userService to set
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

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
