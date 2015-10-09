package com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.action;

import java.util.Date;
import java.util.List;

import com.cdthgk.bmp.core.action.BmpAction;
import com.cdthgk.bmp.secrecyorganization.secrecycommittee.vo.PersonGroupPosition;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.service.SecrecyWorkOrganMemberUnitModuleService;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.service.SecrecyWorkOrganMembersUnitChangeService;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.service.SecrecyWorkOrganModuleService;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.service.SecrecyWorkOrganPositionModuleService;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.vo.SecrecyWorkOrgan;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.vo.SecrecyWorkOrganMemberUnit;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.vo.SecrecyWorkOrganMembersUnitChange;
import com.cdthgk.platform.organization.organ.domain.Organ;

/**
 * <p>
 * 保密办 Action 类
 * </p>
 * <p>
 * 刘椿成 2012-12-18 13:53
 * </p>
 * <blockquote>
 * <h4>历史修改记录</h4>
 * <ul>
 * <li>修改人 修改时间 修改描述
 * </ul>
 * </blockquote>
 * <p>
 * copyright liucc 2012, all rights reserved.
 * </p>
 * @author liucc
 * @since 1.0
 * @version 1.0
 */
public class SecrecyWorkOrganMemberUnitAction extends BmpAction {

	private static final long serialVersionUID = -2561934305983981072L;

	// 通用字段
	private SecrecyWorkOrganModuleService secrecyWorkOrganModuleService;
	private SecrecyWorkOrganMemberUnitModuleService secrecyWorkOrganMemberUnitModuleService;
	private SecrecyWorkOrganPositionModuleService secrecyWorkOrganPositionModuleService;
	private SecrecyWorkOrgan secrecyWorkOrgan;
	private SecrecyWorkOrganMemberUnit secrecyWorkOrganMemberUnit;
	private List<SecrecyWorkOrgan> secrecyWorkOrganList;
	private List<SecrecyWorkOrganMemberUnit> secrecyWorkOrganMemberUnitList;
	private String secrecyWorkOrganMemberUnitIds;
	private SecrecyWorkOrganMembersUnitChange secrecyWorkOrganMembersUnitChange;
	private SecrecyWorkOrganMembersUnitChangeService secrecyWorkOrganMembersUnitChangeService;
	private Boolean needReload = false;

	//部门变更或者部门改名的标志：1：部门改名；2部门变更
	private String deptFlag;
	private Organ organ;

	// *********************** 方 法 ***********************
	// 构造器
	/** 默认构造器 */
	public SecrecyWorkOrganMemberUnitAction() {
	}

	/**
	 * <p>
	 * 保密办列表页面(保密组织机构编辑页用)
	 * </p>
	 * <p>
	 * 刘椿成 2013-01-06 15:43:03
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * <p>
	 * copyright liucc 2012, all rights reserved.
	 * </p>
	 * @author liucc
	 * @since 1.0
	 * @version 1.0
	 */
	public String list() {
		secrecyWorkOrgan = secrecyWorkOrganModuleService.get(secrecyWorkOrgan.getSecrecyWorkOrganId());
		if (organ == null) {
			organ = getCurrentUser().getOrgan();
		}else {
			organ = secrecyWorkOrganMemberUnitModuleService.get(getRequest().getParameter("organ.organId").toString(), Organ.class);
		}
		putToRequest("secrecyWorkOrganMemberUnitList", secrecyWorkOrganMemberUnitModuleService.getPageList(secrecyWorkOrgan, organ, PERSON_CHANGE_NOW));
		return this.redirectActionResult(LIST);
	}

	/**
	 * <p>
	 * 保密办列表页面(保密组织机构列表显示)
	 * </p>
	 * <p>
	 * 刘椿成 2013-01-06 15:43:03
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * <p>
	 * copyright liucc 2012, all rights reserved.
	 * </p>
	 * @author liucc
	 * @since 1.0
	 * @version 1.0
	 */
	public String detailList() {
		secrecyWorkOrgan = secrecyWorkOrganModuleService.get(secrecyWorkOrgan.getSecrecyWorkOrganId());
		if (organ == null) {
			organ = getCurrentUser().getOrgan();
		}else {
			organ = secrecyWorkOrganMemberUnitModuleService.get(getRequest().getParameter("organ.organId").toString(), Organ.class);
		}
		putToRequest("secrecyWorkOrganMemberUnitList", secrecyWorkOrganMemberUnitModuleService.getPageList(secrecyWorkOrgan, organ, PERSON_CHANGE_NOW));
		return this.redirectActionResult(LIST);
	}

	/**
	 * <p>
	 * 详情
	 * </p>
	 * <p>
	 * 刘椿成 2012-12-14 10:43:03
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * <p>
	 * copyright liucc 2012, all rights reserved.
	 * </p>
	 * @author liucc
	 * @since 1.0
	 * @version 1.0
	 */
	public String detail() {
		return "detail";
	}

	/**
	 * <p>
	 * 到新增页面
	 * </p>
	 * <p>
	 * 刘椿成 2012-12-14 10:43:03
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * <p>
	 * copyright liucc 2012, all rights reserved.
	 * </p>
	 *
	 * @author FastCodeingTools
	 * @author liucc
	 * @since 1.0
	 * @version 1.0
	 */
	public String add() {
		// 检查
		// 条件1： 检查传入的保密工作机构ID
		if(secrecyWorkOrgan==null || secrecyWorkOrgan.getSecrecyWorkOrganId()==null || secrecyWorkOrgan.getSecrecyWorkOrganId().equals("")){
			this.addActionMessage("传入的单位保密工作机构ID有误，请检查。");
			return this.redirectActionResult("edit");
		}
		// 条件2： 检查传入的保密工作机构是否存在
		secrecyWorkOrgan = secrecyWorkOrganModuleService.get(secrecyWorkOrgan.getSecrecyWorkOrganId());
		if(secrecyWorkOrgan==null){
			this.addActionMessage("未找到相应的保密工作机构，请检查。");
			return this.redirectActionResult("edit");
		}

		// 获取保密组织机构职位列表
		List<PersonGroupPosition> personGroupPositionList = secrecyWorkOrganPositionModuleService.getByGroupType(secrecyWorkOrgan);
		putToRequest("personGroupPositionList", personGroupPositionList);
		putToRequest("secrecyWorkOrgan",secrecyWorkOrgan);
		return "add";
	}

	/**
	 * <p>
	 * 新增操作
	 * </p>
	 * <p>
	 * 刘椿成 2012-12-14 10:43:03
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * <p>
	 * copyright liucc 2012, all rights reserved.
	 * </p>
	 *
	 * @author FastCodeingTools
	 * @author liucc
	 * @since 1.0
	 * @version 1.0
	 */
	public String adding() {
		secrecyWorkOrganMemberUnit = secrecyWorkOrganMemberUnitModuleService
				.saveSystemDataBySecrecyWorkOrgan(secrecyWorkOrganMemberUnit, getCurrentUser(), null);
		secrecyWorkOrganMemberUnit.setSecrecyWorkOrgan(secrecyWorkOrgan);
		secrecyWorkOrganMemberUnitModuleService.save(secrecyWorkOrganMemberUnit);

		// 设置信息
		this.addActionMessage(getMessageConstant().getSaveSuccess());
		// 设置是否需要重载
		needReload = true;
		return this.redirectActionResult("edit");
	}

	/**
	 * <p>
	 * 到编辑页面
	 * </p>
	 * <p>
	 * 刘椿成 2012-12-14 10:43:03
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * <p>
	 * copyright liucc 2012, all rights reserved.
	 * </p>
	 * @author liucc
	 * @since 1.0
	 * @version 1.0
	 */
	public String edit() {
		secrecyWorkOrganMemberUnit = secrecyWorkOrganMemberUnitModuleService
			.get(secrecyWorkOrganMemberUnit.getSecrecyWorkUnitMemberId());
		secrecyWorkOrgan = secrecyWorkOrganModuleService
			.get(secrecyWorkOrgan.getSecrecyWorkOrganId(), SecrecyWorkOrgan.class);

		// 获取保密职位列表
		List<PersonGroupPosition> personGroupPositionList = secrecyWorkOrganPositionModuleService.getByGroupType(secrecyWorkOrgan);
		putToRequest("personGroupPositionList", personGroupPositionList);
		return SUCCESS;
	}

	/**
	 * <p>
	 * 编辑操作
	 * </p>
	 * <p>
	 * 刘椿成 2012-12-14 10:43:03
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * <p>
	 * copyright liucc 2012, all rights reserved.
	 * </p>
	 * @author liucc
	 * @since 1.0
	 * @version 1.0
	 */
	public String editing() {
		secrecyWorkOrganMemberUnit = secrecyWorkOrganMemberUnitModuleService
			.saveSystemDataBySecrecyWorkOrgan(secrecyWorkOrganMemberUnit, getCurrentUser(), deptFlag);
		secrecyWorkOrganMemberUnit.setSecrecyWorkOrgan(secrecyWorkOrgan);
		secrecyWorkOrganMemberUnitModuleService.update(secrecyWorkOrganMemberUnit);
		addActionMessage(getMessageConstant().getUpdateSuccess());

		// 设置是否需要重载
		needReload = true;
		return this.redirectActionResult("edit");
	}

	/**
	 * <p>
	 * 删除操作
	 * </p>
	 * <p>
	 * 刘椿成 2012-12-14 14:24
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * <p>
	 * copyright liucc 2012, all rights reserved.
	 * </p>
	 * @author liucc
	 * @since 1.0
	 * @version 1.0
	 */
	public String delete() {
		// 检查
		// 条件1： 检查传入的保密办成员ID
		if(secrecyWorkOrganMemberUnitIds==null || secrecyWorkOrganMemberUnitIds.equals("") || secrecyWorkOrganMemberUnitIds.equals(",")){
			this.addActionMessage("传入的保密办成员ID有误，请检查。");
			return this.redirectActionResult("edit");
		}
		// 删除
		secrecyWorkOrganMemberUnitModuleService.deleteBatchIds(secrecyWorkOrganMemberUnitIds);
		secrecyWorkOrgan = secrecyWorkOrganModuleService.get(secrecyWorkOrgan.getSecrecyWorkOrganId());

		// 设置信息
		this.addActionMessage(getMessageConstant().getDeleteSuccess());
		return this.redirectActionResult("edit");
	}

	/**
	 *
	 * <p>
	 * 方法名：personnelChange
	 * </p>
	 * <p>
	 * 宋亚非 2013-7-20 下午3:44:16
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@return
	 */
	public String personnelChange(){
		return "success";
	}

	/**
	 *
	 * <p>
	 * 方法名：personnelChanging
	 * </p>
	 * <p>
	 * 宋亚非 2013-7-20 下午3:43:57
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@return
	 */
	public String personnelChanging(){
		secrecyWorkOrganMembersUnitChange.setCreatePerson(getCurrentUser());
		secrecyWorkOrganMembersUnitChange.setCreateDate(new Date());
		this.secrecyWorkOrganMembersUnitChangeService.save(secrecyWorkOrganMembersUnitChange);

		SecrecyWorkOrganMemberUnit swomu = this.secrecyWorkOrganMembersUnitChangeService.get(this.secrecyWorkOrganMembersUnitChange.getSecrecyWorkOrganMembersId().getSecrecyWorkUnitMemberId(), SecrecyWorkOrganMemberUnit.class);
		swomu.setSecrecyStatus(PERSON_CHANGE_HISTORY);
		this.secrecyWorkOrganMemberUnitModuleService.update(swomu);
		this.needReload = true;
		addActionMessage("变更成功");
		return "changed";
	}

	/**
	 *
	 * <p>
	 * 方法名称  memberdetail  保密办成员明细明
	 * </p>
	 * <p>
	 * 宋亚非 2013-9-10 下午6:03:27
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@return
	 */
	public String memberdetail(){
		secrecyWorkOrganMemberUnit = secrecyWorkOrganMemberUnitModuleService
				.get(secrecyWorkOrganMemberUnit.getSecrecyWorkUnitMemberId());
			secrecyWorkOrgan = secrecyWorkOrganModuleService
				.get(secrecyWorkOrgan.getSecrecyWorkOrganId(), SecrecyWorkOrgan.class);

			// 获取保密职位列表
			List<PersonGroupPosition> personGroupPositionList = secrecyWorkOrganPositionModuleService.getByGroupType(secrecyWorkOrgan);
			putToRequest("personGroupPositionList", personGroupPositionList);
			return SUCCESS;
	}


	// ******************** Setter & Getter ********************

	/**
	 * 返回secrecyWorkOrganModuleService
	 * @return secrecyWorkOrganModuleService
	 */
	public SecrecyWorkOrganModuleService getSecrecyWorkOrganModuleService() {
		return secrecyWorkOrganModuleService;
	}

	/**
	 * 设置secrecyWorkOrganModuleService
	 * @param secrecyWorkOrganModuleService secrecyWorkOrganModuleService
	 */
	public void setSecrecyWorkOrganModuleService(
			SecrecyWorkOrganModuleService secrecyWorkOrganModuleService) {
		this.secrecyWorkOrganModuleService = secrecyWorkOrganModuleService;
	}

	/**
	 * 返回secrecyWorkOrganMemberUnitModuleService
	 * @return secrecyWorkOrganMemberUnitModuleService
	 */
	public SecrecyWorkOrganMemberUnitModuleService getSecrecyWorkOrganMemberUnitModuleService() {
		return secrecyWorkOrganMemberUnitModuleService;
	}

	/**
	 * 设置secrecyWorkOrganMemberUnitModuleService
	 * @param secrecyWorkOrganMemberUnitModuleService secrecyWorkOrganMemberUnitModuleService
	 */
	public void setSecrecyWorkOrganMemberUnitModuleService(
			SecrecyWorkOrganMemberUnitModuleService secrecyWorkOrganMemberUnitModuleService) {
		this.secrecyWorkOrganMemberUnitModuleService = secrecyWorkOrganMemberUnitModuleService;
	}

	/**
	 * 返回secrecyWorkOrgan
	 * @return secrecyWorkOrgan
	 */
	public SecrecyWorkOrgan getSecrecyWorkOrgan() {
		return secrecyWorkOrgan;
	}

	/**
	 * 设置secrecyWorkOrgan
	 * @param secrecyWorkOrgan secrecyWorkOrgan
	 */
	public void setSecrecyWorkOrgan(SecrecyWorkOrgan secrecyWorkOrgan) {
		this.secrecyWorkOrgan = secrecyWorkOrgan;
	}

	/**
	 * 返回secrecyWorkOrganMemberUnit
	 * @return secrecyWorkOrganMemberUnit
	 */
	public SecrecyWorkOrganMemberUnit getSecrecyWorkOrganMemberUnit() {
		return secrecyWorkOrganMemberUnit;
	}

	/**
	 * 设置secrecyWorkOrganMemberUnit
	 * @param secrecyWorkOrganMemberUnit secrecyWorkOrganMemberUnit
	 */
	public void setSecrecyWorkOrganMemberUnit(
			SecrecyWorkOrganMemberUnit secrecyWorkOrganMemberUnit) {
		this.secrecyWorkOrganMemberUnit = secrecyWorkOrganMemberUnit;
	}

	/**
	 * 返回secrecyWorkOrganList
	 * @return secrecyWorkOrganList
	 */
	public List<SecrecyWorkOrgan> getSecrecyWorkOrganList() {
		return secrecyWorkOrganList;
	}

	/**
	 * 设置secrecyWorkOrganList
	 * @param secrecyWorkOrganList secrecyWorkOrganList
	 */
	public void setSecrecyWorkOrganList(List<SecrecyWorkOrgan> secrecyWorkOrganList) {
		this.secrecyWorkOrganList = secrecyWorkOrganList;
	}

	/**
	 * 返回secrecyWorkOrganMemberUnitList
	 * @return secrecyWorkOrganMemberUnitList
	 */
	public List<SecrecyWorkOrganMemberUnit> getSecrecyWorkOrganMemberUnitList() {
		return secrecyWorkOrganMemberUnitList;
	}

	/**
	 * 设置secrecyWorkOrganMemberUnitList
	 * @param secrecyWorkOrganMemberUnitList secrecyWorkOrganMemberUnitList
	 */
	public void setSecrecyWorkOrganMemberUnitList(
			List<SecrecyWorkOrganMemberUnit> secrecyWorkOrganMemberUnitList) {
		this.secrecyWorkOrganMemberUnitList = secrecyWorkOrganMemberUnitList;
	}

	/**
	 * 返回secrecyWorkOrganMemberUnitIds
	 * @return secrecyWorkOrganMemberUnitIds
	 */
	public String getSecrecyWorkOrganMemberUnitIds() {
		return secrecyWorkOrganMemberUnitIds;
	}

	/**
	 * 设置secrecyWorkOrganMemberUnitIds
	 * @param secrecyWorkOrganMemberUnitIds secrecyWorkOrganMemberUnitIds
	 */
	public void setSecrecyWorkOrganMemberUnitIds(
			String secrecyWorkOrganMemberUnitIds) {
		this.secrecyWorkOrganMemberUnitIds = secrecyWorkOrganMemberUnitIds;
	}

	/**
	 * 返回needReload
	 * @return needReload
	 */
	public Boolean getNeedReload() {
		return needReload;
	}

	/**
	 * 设置needReload
	 * @param needReload needReload
	 */
	public void setNeedReload(Boolean needReload) {
		this.needReload = needReload;
	}

	/**
	 * 返回deptFlag
	 * @return deptFlag
	 */
	public String getDeptFlag() {
		return deptFlag;
	}

	/**
	 * 设置deptFlag
	 * @param deptFlag deptFlag
	 */
	public void setDeptFlag(String deptFlag) {
		this.deptFlag = deptFlag;
	}

	/**
	 * 返回organ
	 * @return organ
	 */
	public Organ getOrgan() {
		return organ;
	}

	/**
	 * 设置organ
	 * @param organ organ
	 */
	public void setOrgan(Organ organ) {
		this.organ = organ;
	}

	/**
	 * 返回secrecyWorkOrganPositionModuleService
	 * @return secrecyWorkOrganPositionModuleService
	 */
	public SecrecyWorkOrganPositionModuleService getSecrecyWorkOrganPositionModuleService() {
		return secrecyWorkOrganPositionModuleService;
	}

	/**
	 * 设置secrecyWorkOrganPositionModuleService
	 * @param secrecyWorkOrganPositionModuleService secrecyWorkOrganPositionModuleService
	 */
	public void setSecrecyWorkOrganPositionModuleService(
			SecrecyWorkOrganPositionModuleService secrecyWorkOrganPositionModuleService) {
		this.secrecyWorkOrganPositionModuleService = secrecyWorkOrganPositionModuleService;
	}

	/**
	 * @return the secrecyWorkOrganMembersUnitChange
	 */
	public SecrecyWorkOrganMembersUnitChange getSecrecyWorkOrganMembersUnitChange() {
		return secrecyWorkOrganMembersUnitChange;
	}

	/**
	 * @param secrecyWorkOrganMembersUnitChange the secrecyWorkOrganMembersUnitChange to set
	 */
	public void setSecrecyWorkOrganMembersUnitChange(
			SecrecyWorkOrganMembersUnitChange secrecyWorkOrganMembersUnitChange) {
		this.secrecyWorkOrganMembersUnitChange = secrecyWorkOrganMembersUnitChange;
	}

	/**
	 * @return the secrecyWorkOrganMembersUnitChangeService
	 */
	public SecrecyWorkOrganMembersUnitChangeService getSecrecyWorkOrganMembersUnitChangeService() {
		return secrecyWorkOrganMembersUnitChangeService;
	}

	/**
	 * @param secrecyWorkOrganMembersUnitChangeService the secrecyWorkOrganMembersUnitChangeService to set
	 */
	public void setSecrecyWorkOrganMembersUnitChangeService(
			SecrecyWorkOrganMembersUnitChangeService secrecyWorkOrganMembersUnitChangeService) {
		this.secrecyWorkOrganMembersUnitChangeService = secrecyWorkOrganMembersUnitChangeService;
	}

}