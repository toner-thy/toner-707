package com.cdthgk.bmp.secrecyorganization.secrecycommittee.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.action.BmpAction;
import com.cdthgk.bmp.secrecyorganization.secrecycommittee.service.PersonGroupPositionModuleService;
import com.cdthgk.bmp.secrecyorganization.secrecycommittee.service.SecrecyCommitteeMemberModuleService;
import com.cdthgk.bmp.secrecyorganization.secrecycommittee.service.SecrecyCommitteeMembersChangeService;
import com.cdthgk.bmp.secrecyorganization.secrecycommittee.service.SecrecyCommitteeModuleService;
import com.cdthgk.bmp.secrecyorganization.secrecycommittee.vo.PersonGroupPosition;
import com.cdthgk.bmp.secrecyorganization.secrecycommittee.vo.SecrecyCommittee;
import com.cdthgk.bmp.secrecyorganization.secrecycommittee.vo.SecrecyCommitteeMember;
import com.cdthgk.bmp.secrecyorganization.secrecycommittee.vo.SecrecyCommitteeMembersChange;
import com.cdthgk.platform.district.domain.District;

import ec.common.PageSortModel;

/**
 * <p>
 * 保密委成员 Action 类
 * </p>
 * <p>
 * 汪 钟 2012-12-18 13:53
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
 * @author FastCodeingTools
 * @author tianyu
 * @since 1.0
 * @version 1.0
 */
public class SecrecyCommitteeMemberAction extends BmpAction {

	private static final long serialVersionUID = -2561934305983981072L;

	// 通用字段
	private SecrecyCommitteeModuleService secrecyCommitteeModuleService;
	private SecrecyCommitteeMemberModuleService secrecyCommitteeMemberModuleService;
	private PersonGroupPositionModuleService personGroupPositionModuleService;
	private SecrecyCommitteeMembersChangeService secrecyCommitteeMembersChangeService;

	private SecrecyCommittee secrecyCommittee;
	private SecrecyCommitteeMember secrecyCommitteeMember;
	private List<SecrecyCommittee> secrecyCommitteeList;
	private List<SecrecyCommitteeMember> secrecyCommitteeMemberList;
	private String secrecyCommitteeMemberIds;
	private SecrecyCommitteeMembersChange secrecyCommitteeMembersChange;
	private District district;
	private Boolean needReload = false;

	// *********************** 方 法 ***********************

	// 构造器
	/** 默认构造器 */
	public SecrecyCommitteeMemberAction() {
	}

	/**
	 * <p>
	 * 详情
	 * </p>
	 * <p>
	 * 汪 钟 2012-12-14 10:43:03
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
	 * @author FastCodeingTools
	 * @author tianyu
	 * @since 1.0
	 * @version 1.0
	 */
	public String detail() {
		secrecyCommittee = secrecyCommitteeModuleService.getByDistrict(getCurrentUser().getOrgan().getDistrict());
		return "detail";
	}

	/**
	 * <p>
	 * 到新增页面
	 * </p>
	 * <p>
	 * 汪 钟 2012-12-14 10:43:03
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
	 * @author FastCodeingTools
	 * @author tianyu
	 * @since 1.0
	 * @version 1.0
	 */
	public String add() {
		// 检查
			// 条件1： 检查传入的保密委ID
			if(secrecyCommittee==null || secrecyCommittee.getSecrecyCommitteeId()==null || secrecyCommittee.getSecrecyCommitteeId().equals("")){
				this.addActionMessage("传入的保密委ID有误，请检查。");
				return this.redirectActionResult("edit");
			}
			// 条件2： 检查传入的保密委是否存在
			secrecyCommittee = secrecyCommitteeModuleService.get(secrecyCommittee.getSecrecyCommitteeId());
			if(secrecyCommittee==null){
				this.addActionMessage("未找到相应的保密委，请检查。");
				return this.redirectActionResult("edit");
			}
			// 条件3： 检查当前登录人员是否属于该保密委所在行政区划内
			String currentUserDistrictCodeStr = getCurrentUser().getOrgan().getDistrict().getDistrictCode();
			if(!currentUserDistrictCodeStr.equals(secrecyCommittee.getDistrict().getDistrictCode())){
				this.addActionMessage("您当前不是【" + secrecyCommittee.getDistrict().getDistrictName() + "】的人员，没有权限对【" + secrecyCommittee.getName() + "】进行新增成员操作。");
				return this.redirectActionResult("edit");
			}
//			// 条件4： 检查当前登录人员是否属于保密局
//			String currentUserOrganNameStr = getCurrentUser().getOrgan().getOrganName();
//			if(currentUserOrganNameStr.endsWith("保密局")){
//				this.addActionMessage("您当前不是保密局人员，没有权限对【" + secrecyCommittee.getName() + "】进行新增成员操作。");
//				return this.redirectActionResult("edit");
//			}


		// 获取保密委职位列表
		List<PersonGroupPosition> personGroupPositionList = personGroupPositionModuleService.getByGroupType(2);
		putToRequest("personGroupPositionList", personGroupPositionList);

		return "add";
	}

	/**
	 * <p>
	 * 新增操作
	 * </p>
	 * <p>
	 * 汪 钟 2012-12-14 10:43:03
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
	 * @author FastCodeingTools
	 * @author tianyu
	 * @since 1.0
	 * @version 1.0
	 */
	public String adding() {
		// 检查
			// 条件1： 检查传入的保密委ID
			if(secrecyCommittee==null || secrecyCommittee.getSecrecyCommitteeId()==null || secrecyCommittee.getSecrecyCommitteeId().equals("")){
				this.addActionMessage("传入的保密委ID有误，请检查。");
				return this.redirectActionResult("edit");
			}
			// 条件2： 检查传入的保密委是否存在
			secrecyCommittee = secrecyCommitteeModuleService.get(secrecyCommittee.getSecrecyCommitteeId());
			if(secrecyCommittee==null){
				this.addActionMessage("未找到相应的保密委，请检查。");
				return this.redirectActionResult("edit");
			}
			// 条件3： 检查当前登录人员是否属于该保密委所在行政区划内
			String currentUserDistrictCodeStr = getCurrentUser().getOrgan().getDistrict().getDistrictCode();
			if(!currentUserDistrictCodeStr.equals(secrecyCommittee.getDistrict().getDistrictCode())){
				this.addActionMessage("您当前不是【" + secrecyCommittee.getDistrict().getDistrictName() + "】的人员，没有权限对【" + secrecyCommittee.getName() + "】进行新增成员操作。");
				return this.redirectActionResult("edit");
			}
			// 检查保密委员会职务主任只能有一个
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("organId", getCurrentUser().getOrgan().getOrganId());
			SecrecyCommitteeMember uniqueSecrecyCommitteeMember = secrecyCommitteeMemberModuleService
					.unique("From SecrecyCommitteeMember WHERE personGroupPosition.personGroupPosition = 1 " +
							"AND createOrgan.organId = :organId and secrecyStatus ="+PERSON_CHANGE_NOW, params);
			if(uniqueSecrecyCommitteeMember != null
					&& secrecyCommitteeMember.getPersonGroupPosition().getPersonGroupPosition().equals("1")){
				addActionMessage("该保密委员会职务中只能允许有一个【主任】，请不要重复添加。");
				// 获取保密委职位列表
				List<PersonGroupPosition> personGroupPositionList = personGroupPositionModuleService.getByGroupType(2);
				putToRequest("personGroupPositionList", personGroupPositionList);
				return SUCCESS;
			}

		// 传入的字段
		SecrecyCommitteeMember secrecyCommitteeMemberTemp = new SecrecyCommitteeMember();
		secrecyCommitteeMemberTemp.setSecrecyCommittee(secrecyCommittee);
		secrecyCommitteeMemberTemp.setRemark(secrecyCommitteeMember.getRemark());
		secrecyCommitteeMemberTemp.setPerson(secrecyCommitteeMember.getPerson());
		secrecyCommitteeMemberTemp.setOrganName(secrecyCommitteeMember.getOrganName());
		secrecyCommitteeMemberTemp.setPersonDuty(secrecyCommitteeMember.getPersonDuty());
		secrecyCommitteeMemberTemp.setPersonPhone(secrecyCommitteeMember.getPersonPhone());
		secrecyCommitteeMemberTemp.setSort(secrecyCommitteeMember.getSort());
		secrecyCommitteeMemberTemp.setSecrecyStatus(PERSON_CHANGE_NOW);
		secrecyCommitteeMemberTemp.setSecrecyWorkStart(secrecyCommitteeMember.getSecrecyWorkStart());
		secrecyCommitteeMemberTemp.setIsSoleDuty(secrecyCommitteeMember.getIsSoleDuty());
		secrecyCommitteeMemberTemp.setOtherDuty(secrecyCommitteeMember.getOtherDuty());
		secrecyCommitteeMemberTemp.setAdministrativeLevel(secrecyCommitteeMember.getAdministrativeLevel());
		secrecyCommitteeMemberTemp.setTechnicalTitle(secrecyCommitteeMember.getTechnicalTitle());
		secrecyCommitteeMemberTemp.setSex(secrecyCommitteeMember.getSex());
		secrecyCommitteeMemberTemp.setBirthdate(secrecyCommitteeMember.getBirthdate());
		secrecyCommitteeMemberTemp.setNation(secrecyCommitteeMember.getNation());
		secrecyCommitteeMemberTemp.setEducationBackground(secrecyCommitteeMember.getEducationBackground());
		secrecyCommitteeMemberTemp.setDegree(secrecyCommitteeMember.getDegree());
		secrecyCommitteeMemberTemp.setPoliticalStatus(secrecyCommitteeMember.getPoliticalStatus());
		secrecyCommitteeMemberTemp.setMajor(secrecyCommitteeMember.getMajor());
		secrecyCommitteeMemberTemp.setAdminPost(secrecyCommitteeMember.getAdminPost());
		secrecyCommitteeMemberTemp.setDepartmentName(secrecyCommitteeMember.getDepartmentName());

		PersonGroupPosition pgp = personGroupPositionModuleService.get(secrecyCommitteeMember.getPersonGroupPosition().getPersonGroupPosition());
		secrecyCommitteeMemberTemp.setPersonGroupPosition(pgp);

		// 设置通用字段
		secrecyCommitteeMemberTemp.setCreateTime(new Date());
		secrecyCommitteeMemberTemp.setCreateUser(getCurrentUser());
		secrecyCommitteeMemberTemp.setCreateOrgan(getCurrentUser().getUserInfo().getOrgan());
		secrecyCommitteeMemberTemp.setEnable(1);
		secrecyCommitteeMemberModuleService.save(secrecyCommitteeMemberTemp);

		// 设置信息
		this.addActionMessage("新增成功。");

		// 设置是否需要重载
		needReload = true;

		return this.redirectActionResult("edit");
	}

	/**
	 * <p>
	 * 到编辑页面
	 * </p>
	 * <p>
	 * 汪 钟 2012-12-14 10:43:03
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
	 * @author FastCodeingTools
	 * @author tianyu
	 * @since 1.0
	 * @version 1.0
	 */
	public String edit() {
		// 检查
			// 条件1： 检查保密委成员id
		if(secrecyCommitteeMember.getSecrecyCommitteeMemberId()==null || secrecyCommitteeMember.getSecrecyCommitteeMemberId().equals("")){
			this.addActionMessage("您所访问的资源Id不存在，请确认后重试。");
			return this.redirectActionResult("edit");
		}
		secrecyCommitteeMember = secrecyCommitteeMemberModuleService.get(secrecyCommitteeMember.getSecrecyCommitteeMemberId());
		if (secrecyCommitteeMember == null) {
			// 设置信息
			this.addActionMessage("您所访问的保密委成员不存在，请确认后重试。");
			return this.redirectActionResult("edit");
		}

		// 获取保密委职位列表
		List<PersonGroupPosition> personGroupPositionList = personGroupPositionModuleService.getByGroupType(2);
		putToRequest("personGroupPositionList", personGroupPositionList);

		return "edit";
	}

	/**
	 * <p>
	 * 编辑操作
	 * </p>
	 * <p>
	 * 汪 钟 2012-12-14 10:43:03
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
	 * @author FastCodeingTools
	 * @author tianyu
	 * @since 1.0
	 * @version 1.0
	 */
	public String editing() {
		// 检查
			// 条件1： 检查保密委成员id
			if(secrecyCommitteeMember==null || secrecyCommitteeMember.getSecrecyCommitteeMemberId()==null || secrecyCommitteeMember.getSecrecyCommitteeMemberId().equals("")){
				this.addActionMessage("您所访问的资源Id不存在，请确认后重试。");
				return this.redirectActionResult("edit");
			}
			// 条件2：检查保密委成员是否存在
			SecrecyCommitteeMember secrecyCommitteeMemberFormDb = secrecyCommitteeMemberModuleService.get(secrecyCommitteeMember.getSecrecyCommitteeMemberId());
			if(secrecyCommitteeMemberFormDb==null){
				// 设置信息
				this.addActionMessage("您所访问的保密委成员不存在，请确认后重试。");
				return this.redirectActionResult("edit");
			}

			// 检查保密委员会职务主任只能有一个
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("organId", getCurrentUser().getOrgan().getOrganId());
			SecrecyCommitteeMember uniqueSecrecyCommitteeMember = secrecyCommitteeMemberModuleService
					.unique("From SecrecyCommitteeMember WHERE personGroupPosition.personGroupPosition = 1 AND createOrgan.organId = :organId", params);
			if(uniqueSecrecyCommitteeMember != null
					&& secrecyCommitteeMember.getPersonGroupPosition().getPersonGroupPosition().equals("1")
					&& !uniqueSecrecyCommitteeMember.getSecrecyCommitteeMemberId().equals(secrecyCommitteeMember.getSecrecyCommitteeMemberId())){
				addActionMessage("该保密委员会职务中只能允许有一个【主任】，请不要重复添加。");
				secrecyCommitteeMember = secrecyCommitteeMemberFormDb;
				// 获取保密委职位列表
				List<PersonGroupPosition> personGroupPositionList = personGroupPositionModuleService.getByGroupType(2);
				putToRequest("personGroupPositionList", personGroupPositionList);
				return SUCCESS;
			}

		// 更新
			// 更新保密委员会职务
			secrecyCommitteeMemberFormDb.setPersonGroupPosition(secrecyCommitteeMember.getPersonGroupPosition());
			// 更新备注
			secrecyCommitteeMemberFormDb.setRemark(secrecyCommitteeMember.getRemark());

			secrecyCommitteeMemberFormDb.setSort(secrecyCommitteeMember.getSort());

			// FIXME 下一步在做单位/人员的更新，当前编辑中仅显示。
//			// 更新单位/人员

		// 设置办公室电话
		secrecyCommitteeMemberFormDb.setPersonPhone(secrecyCommitteeMember.getPersonPhone());
		// 设置行政职务
		secrecyCommitteeMemberFormDb.setPersonDuty(secrecyCommitteeMember.getPersonDuty());

		secrecyCommitteeMemberFormDb.setPerson(secrecyCommitteeMember.getPerson());
		secrecyCommitteeMemberFormDb.setOrganName(secrecyCommitteeMember.getOrganName());

		secrecyCommitteeMemberFormDb.setSecrecyWorkStart(secrecyCommitteeMember.getSecrecyWorkStart());
		secrecyCommitteeMemberFormDb.setIsSoleDuty(secrecyCommitteeMember.getIsSoleDuty());
		secrecyCommitteeMemberFormDb.setOtherDuty(secrecyCommitteeMember.getOtherDuty());
		secrecyCommitteeMemberFormDb.setAdministrativeLevel(secrecyCommitteeMember.getAdministrativeLevel());
		secrecyCommitteeMemberFormDb.setTechnicalTitle(secrecyCommitteeMember.getTechnicalTitle());
		secrecyCommitteeMemberFormDb.setSex(secrecyCommitteeMember.getSex());
		secrecyCommitteeMemberFormDb.setBirthdate(secrecyCommitteeMember.getBirthdate());
		secrecyCommitteeMemberFormDb.setNation(secrecyCommitteeMember.getNation());
		secrecyCommitteeMemberFormDb.setEducationBackground(secrecyCommitteeMember.getEducationBackground());
		secrecyCommitteeMemberFormDb.setDegree(secrecyCommitteeMember.getDegree());
		secrecyCommitteeMemberFormDb.setPoliticalStatus(secrecyCommitteeMember.getPoliticalStatus());
		secrecyCommitteeMemberFormDb.setMajor(secrecyCommitteeMember.getMajor());
		secrecyCommitteeMemberFormDb.setAdminPost(secrecyCommitteeMember.getAdminPost());
		secrecyCommitteeMemberFormDb.setDepartmentName(secrecyCommitteeMember.getDepartmentName());

		// 更新基础信息
		secrecyCommitteeMemberFormDb.setModifyUser(getCurrentUser());
		secrecyCommitteeMemberFormDb.setModifyOrgan(getCurrentUser().getOrgan());
		secrecyCommitteeMemberFormDb.setModifyTime(new Date());
		secrecyCommitteeMemberFormDb.setSecrecyStatus(PERSON_CHANGE_NOW);
		secrecyCommitteeMemberModuleService.update(secrecyCommitteeMemberFormDb);

		// 设置信息
		this.addActionMessage("更新成功!");

		// 设置是否需要重载
		needReload = true;

		secrecyCommittee = secrecyCommitteeMemberFormDb.getSecrecyCommittee();

		return this.redirectActionResult("edit");
	}

	/**
	 * <p>
	 * 删除操作
	 * </p>
	 * <p>
	 * 汪 钟 2012-12-14 14:24
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
	 * @author FastCodeingTools
	 * @author tianyu
	 * @since 1.0
	 * @version 1.0
	 */
	public String delete() {
		// 检查
			// 条件1： 检查传入的保密委ID
			if(secrecyCommittee==null || secrecyCommittee.getSecrecyCommitteeId()==null || secrecyCommittee.getSecrecyCommitteeId().equals("")){
				this.addActionMessage("传入的保密委ID有误，请检查。");
				return this.redirectActionResult("edit");
			}

		secrecyCommittee = secrecyCommitteeModuleService.get(secrecyCommittee.getSecrecyCommitteeId());

		// 删除
		secrecyCommitteeMemberModuleService.deleteBatchIds(secrecyCommitteeMemberIds);

		// 设置信息
		this.addActionMessage("删除成功!");

		return this.redirectActionResult("edit");
	}

	/**
	 *
	 * <p>
	 * 方法名：personnelChange 人员变动
	 * </p>
	 * <p>
	 * 宋亚非 2013-7-16 下午4:11:20
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
	 * 方法名：personnelChanging 人员变动信息保存
	 * </p>
	 * <p>
	 * 宋亚非 2013-7-16 下午4:11:24
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
		secrecyCommitteeMembersChange.setCreateDate(new Date());
		secrecyCommitteeMembersChange.setCreatePerson(getCurrentUser());
		this.secrecyCommitteeMembersChangeService.save(secrecyCommitteeMembersChange);

		SecrecyCommitteeMember scm = this.secrecyCommitteeMemberModuleService.get(secrecyCommitteeMembersChange.getSecrecyCommitteeMemberId().getSecrecyCommitteeMemberId(), SecrecyCommitteeMember.class);
		scm.setSecrecyStatus(SecrecyCommitteeMemberAction.PERSON_CHANGE_HISTORY);
		this.secrecyCommitteeMemberModuleService.update(scm);
		this.needReload = true;
		addActionMessage("变更成功");
		return "changed";
	}

	/**
	 *
	 * <p>
	 * 方法名：historyList 展示人员变更历史列表
	 * </p>
	 * <p>
	 * 宋亚非 2013-7-16 下午3:50:56
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
	public String historyList(){
		if(district == null){
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district =  secrecyCommitteeModuleService.get(district.getDistrictCode(), District.class);
		}
		secrecyCommittee = secrecyCommitteeModuleService.getByDistrict(district);

		// 检查
		if(secrecyCommittee==null || secrecyCommittee.getSecrecyCommitteeId()==null || secrecyCommittee.getSecrecyCommitteeId().equals("")){
			this.addActionMessage("您所访问的资源Id不存在，请确认后重试。");
			//return this.redirectActionResult("success");
			return "success";
		}

		// 得到成员历史列表
		PageSortModel<SecrecyCommitteeMember> psm = new PageSortModel<SecrecyCommitteeMember>(getRequest(), "secrecyCommitteeMemberHistoryList");
		List<SecrecyCommitteeMember> secrecyCommitteeMemberHistoryList = secrecyCommitteeMemberModuleService.getPageHistoryList(psm, secrecyCommittee, PERSON_CHANGE_HISTORY);
		this.putToRequest("secrecyCommitteeMemberHistoryList", secrecyCommitteeMemberHistoryList);
		return "success";
	}

	/**
	 *
	 * <p>
	 * 方法名：changedList 展示某个人员变更历史列表
	 * </p>
	 * <p>
	 * 宋亚非 2013-7-16 下午3:51:32
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
	public String changedList(){
		PageSortModel<SecrecyCommitteeMembersChange> psm = new PageSortModel<SecrecyCommitteeMembersChange>(getRequest(), "secrecyCommitteeMemberChangeTable");
		List<SecrecyCommitteeMembersChange> pgrcList = this.secrecyCommitteeMembersChangeService.getSecrecyCommitteeMemberChangeList(psm, this.secrecyCommitteeMember.getSecrecyCommitteeMemberId());
		this.putToRequest("secrecyCommitteeMembersChangeList", pgrcList);
		return "success";
	}


	public String persondetail(){
		// 获取保密委职位列表
		List<PersonGroupPosition> personGroupPositionList = personGroupPositionModuleService.getByGroupType(2);
		putToRequest("personGroupPositionList", personGroupPositionList);

		secrecyCommitteeMember = this.secrecyCommitteeMemberModuleService.get(secrecyCommitteeMember.getSecrecyCommitteeMemberId());
		return SUCCESS;
	}

	// ******************** Setter & Getter ********************
	public SecrecyCommitteeModuleService getSecrecyCommitteeModuleService() {
		return secrecyCommitteeModuleService;
	}

	public void setSecrecyCommitteeModuleService(
			SecrecyCommitteeModuleService secrecyCommitteeModuleService) {
		this.secrecyCommitteeModuleService = secrecyCommitteeModuleService;
	}

	public SecrecyCommitteeMemberModuleService getSecrecyCommitteeMemberModuleService() {
		return secrecyCommitteeMemberModuleService;
	}

	public void setSecrecyCommitteeMemberModuleService(
			SecrecyCommitteeMemberModuleService secrecyCommitteeMemberModuleService) {
		this.secrecyCommitteeMemberModuleService = secrecyCommitteeMemberModuleService;
	}

	public SecrecyCommittee getSecrecyCommittee() {
		return secrecyCommittee;
	}

	public void setSecrecyCommittee(SecrecyCommittee secrecyCommittee) {
		this.secrecyCommittee = secrecyCommittee;
	}

	public SecrecyCommitteeMember getSecrecyCommitteeMember() {
		return secrecyCommitteeMember;
	}

	public void setSecrecyCommitteeMember(
			SecrecyCommitteeMember secrecyCommitteeMember) {
		this.secrecyCommitteeMember = secrecyCommitteeMember;
	}

	public List<SecrecyCommittee> getSecrecyCommitteeList() {
		return secrecyCommitteeList;
	}

	public void setSecrecyCommitteeList(List<SecrecyCommittee> secrecyCommitteeList) {
		this.secrecyCommitteeList = secrecyCommitteeList;
	}

	public List<SecrecyCommitteeMember> getSecrecyCommitteeMemberList() {
		return secrecyCommitteeMemberList;
	}

	public void setSecrecyCommitteeMemberList(
			List<SecrecyCommitteeMember> secrecyCommitteeMemberList) {
		this.secrecyCommitteeMemberList = secrecyCommitteeMemberList;
	}

	public String getSecrecyCommitteeMemberIds() {
		return secrecyCommitteeMemberIds;
	}

	public void setSecrecyCommitteeMemberIds(String secrecyCommitteeMemberIds) {
		this.secrecyCommitteeMemberIds = secrecyCommitteeMemberIds;
	}

	public Boolean getNeedReload() {
		return needReload;
	}

	public void setNeedReload(Boolean needReload) {
		this.needReload = needReload;
	}

	public PersonGroupPositionModuleService getPersonGroupPositionModuleService() {
		return personGroupPositionModuleService;
	}

	public void setPersonGroupPositionModuleService(
			PersonGroupPositionModuleService personGroupPositionModuleService) {
		this.personGroupPositionModuleService = personGroupPositionModuleService;
	}

	/**
	 * @return the secrecyCommitteeMembersChangeService
	 */
	public SecrecyCommitteeMembersChangeService getSecrecyCommitteeMembersChangeService() {
		return secrecyCommitteeMembersChangeService;
	}

	/**
	 * @param secrecyCommitteeMembersChangeService the secrecyCommitteeMembersChangeService to set
	 */
	public void setSecrecyCommitteeMembersChangeService(
			SecrecyCommitteeMembersChangeService secrecyCommitteeMembersChangeService) {
		this.secrecyCommitteeMembersChangeService = secrecyCommitteeMembersChangeService;
	}

	/**
	 * @return the secrecyCommitteeMembersChange
	 */
	public SecrecyCommitteeMembersChange getSecrecyCommitteeMembersChange() {
		return secrecyCommitteeMembersChange;
	}

	/**
	 * @param secrecyCommitteeMembersChange the secrecyCommitteeMembersChange to set
	 */
	public void setSecrecyCommitteeMembersChange(
			SecrecyCommitteeMembersChange secrecyCommitteeMembersChange) {
		this.secrecyCommitteeMembersChange = secrecyCommitteeMembersChange;
	}

	/**
	 * @return the district
	 */
	public District getDistrict() {
		return district;
	}

	/**
	 * @param district the district to set
	 */
	public void setDistrict(District district) {
		this.district = district;
	}


}