package com.cdthgk.bmp.secrecyorganization.secrecyoffice.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.action.BmpAction;
import com.cdthgk.bmp.secrecyorganization.secrecyoffice.service.SecrecyOfficeMemberChangeModuleService;
import com.cdthgk.bmp.secrecyorganization.secrecyoffice.service.SecrecyOfficeMemberDecryptionModuleService;
import com.cdthgk.bmp.secrecyorganization.secrecyoffice.service.SecrecyOfficeMemberModuleService;
import com.cdthgk.bmp.secrecyorganization.secrecyoffice.vo.SecrecyOffice;
import com.cdthgk.bmp.secrecyorganization.secrecyoffice.vo.SecrecyOfficeMember;
import com.cdthgk.bmp.secrecyorganization.secrecyoffice.vo.SecrecyOfficeMemberChange;
import com.cdthgk.bmp.secrecyorganization.secrecyoffice.vo.SecrecyOfficeMemberDecryption;
import com.cdthgk.common.lang.CollectionUtils;
import com.cdthgk.common.lang.DateUtils;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;

import ec.common.PageSortModel;

/**
 * <p>
 * 保密办（保密局）成员 Action 类
 * </p>
 * <p>
 * 陶汇源 2012-12-24 17:43:03
 * </p>
 * <blockquote>
 * <h4>历史修改记录</h4>
 * <ul>
 * <li>修改人 修改时间 修改描述
 * </ul>
 * </blockquote>
 * <p>
 * copyright taohy 2012, all rights reserved.
 * </p>
 *
 * @author FastCodeingTools
 * @author taohy
 * @since 1.0
 * @version 1.0
 * @param <E>
 */
public class SecrecyOfficeMemberAction<E> extends BmpAction {

	private static final long serialVersionUID = -2561934305983981072L;

	private SecrecyOffice secrecyOffice;
	private SecrecyOfficeMember secrecyOfficeMember;
	private List<SecrecyOfficeMember> secrecyOfficeMemberList;
	private SecrecyOfficeMemberModuleService secrecyOfficeMemberModuleService;
	private SecrecyOfficeMemberChangeModuleService secrecyOfficeMemberChangeModuleService;
	private SecrecyOfficeMemberDecryptionModuleService secrecyOfficeMemberDecryptionModuleService;

	private SecrecyOfficeMemberChange secrecyOfficeMemberChange;
	private SecrecyOfficeMemberDecryption secrecyOfficeMemberDecryption;
	private List<SecrecyOfficeMemberChange> secrecyOfficeMemberChangeList;
	private List<SecrecyOfficeMemberDecryption> secrecyOfficeMemberDecryptionList;

	private District district;
	private String secrecyOfficeMemberIds;
	private String userInfoId;

	//部门变更或者部门改名的标志：1：部门改名；2部门变更
	private String deptFlag;

	// *********************** 方 法 ***********************

	// 构造器
	/** 默认构造器 */
	public SecrecyOfficeMemberAction() {
	}

	/**
	 * <p>
	 * 详情
	 * </p>
	 * <p>
	 * 陶汇源 2012-12-29 14:39:55
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
	 * @author 陶汇源
	 * @since 1.0
	 * @version 1.0
	 */
	public String detail() {
		secrecyOfficeMember = secrecyOfficeMemberModuleService.get(secrecyOfficeMember.getSecrecyOfficeMemberId());
		return DETAIL;
	}

	/**
	 * <p>
	 * 新增
	 * </p>
	 * <p>
	 * 陶汇源 2012-12-29 14:40:20
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
	 * @author 陶汇源
	 * @since 1.0
	 * @version 1.0
	 */
	public String add() {
		secrecyOffice = secrecyOfficeMemberModuleService
				.get(secrecyOffice.getSecrecyOfficeId(), SecrecyOffice.class);
		return SUCCESS;
	}

	/**
	 * <p>
	 * 保存
	 * </p>
	 * <p>
	 * 陶汇源 2012-12-29 14:40:41
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
	 * @author 陶汇源
	 * @since 1.0
	 * @version 1.0
	 */
	public String adding() {
		secrecyOfficeMember = secrecyOfficeMemberModuleService
				.saveSystemDataBySecrecyOffice(secrecyOfficeMember, getCurrentUser(), null);
		secrecyOfficeMember.setSecrecyStatus(SECRECY_STATUS_NOW);
		secrecyOfficeMember.setSecrecyOffice(secrecyOffice);
		secrecyOfficeMemberModuleService.save(secrecyOfficeMember);
		addActionMessage(getMessageConstant().getSaveSuccess());
		return redirectActionResult(EDIT);
	}

	/**
	 * <p>
	 * 编辑
	 * </p>
	 * <p>
	 * 陶汇源 2012-12-29 14:41:36
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
	 * @author 陶汇源
	 * @since 1.0
	 * @version 1.0
	 */
	public String edit() {
		secrecyOfficeMember = secrecyOfficeMemberModuleService
				.get(secrecyOfficeMember.getSecrecyOfficeMemberId());
		secrecyOffice = secrecyOfficeMemberModuleService
				.get(secrecyOffice.getSecrecyOfficeId(), SecrecyOffice.class);
		return SUCCESS;
	}

	/**
	 * <p>
	 * 更新
	 * </p>
	 * <p>
	 * 陶汇源 2012-12-29 14:42:16
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
	 * @author 陶汇源
	 * @since 1.0
	 * @version 1.0
	 */
	public String editing() {
		secrecyOfficeMember = secrecyOfficeMemberModuleService
				.saveSystemDataBySecrecyOffice(secrecyOfficeMember, getCurrentUser(), deptFlag);
		secrecyOfficeMember.setSecrecyStatus(SECRECY_STATUS_NOW);
		secrecyOfficeMember.setSecrecyOffice(secrecyOffice);
		secrecyOfficeMemberModuleService.update(secrecyOfficeMember);
		addActionMessage(getMessageConstant().getUpdateSuccess());
		return redirectActionResult(EDIT);
	}

	/**
	 * <p>
	 * 删除
	 * </p>
	 * <p>
	 * 陶汇源 2012-12-29 14:42:50
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
	 * @author 陶汇源
	 * @since 1.0
	 * @version 1.0
	 */
	public String delete() {
		String [] idsArray = secrecyOfficeMemberIds.split(",");
		String message = "";
		for (String id : idsArray) {
			secrecyOfficeMember = secrecyOfficeMemberModuleService.get(id);
			if(secrecyOfficeMember != null){
				if(CollectionUtils.isEmpty(secrecyOfficeMember.getSecrecyOfficeMemberChangeSet())
						&& CollectionUtils.isEmpty(secrecyOfficeMember.getSecrecyOfficeMemberDecryptionSet())){
					secrecyOfficeMemberModuleService.delete(secrecyOfficeMember);
					message = getMessageConstant().getDeleteSuccess();
				} else {
					message = getMessageConstant().getDeleteFailure();
				}
				addActionMessage(message);
			}
		}
		return redirectActionResult(LIST);
	}

	/**
	 * <p>
	 * 通过人员ID获取人员信息
	 * </p>
	 * <p>
	 * 陶汇源 2012-12-29 11:07:58
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
	 * @author 陶汇源
	 * @since 1.0
	 * @version 1.0
	 */
	public String catchUserInfo() {
		Map<String, String> resultMap = new HashMap<String, String>();
		UserInfo userInfo = secrecyOfficeMemberModuleService.get(userInfoId, UserInfo.class);
		resultMap.put("duty", userInfo == null ? "" : userInfo.getDuty() == null ? "" : userInfo.getDuty());
		resultMap.put("sex", userInfo == null ? "" : userInfo.getSex() == null ? "" : userInfo.getSex());
		resultMap.put("nation", userInfo == null ? "" : userInfo.getNation() == null ? "" : userInfo.getNation()+"");
		if( userInfo!=null && userInfo.getBirthDate()!=null ){
			resultMap.put("birthDate", DateUtils.formart(userInfo.getBirthDate(), "yyyy-MM-dd"));
		}else{
			resultMap.put("birthDate", "");
		}
		resultMap.put("learningLevel", userInfo == null ? "" : userInfo.getLearningLevel() == null ? "" : userInfo.getLearningLevel()+"");
		resultMap.put("identityCard", userInfo == null ? "" : userInfo.getIdentityCard() == null ? "" : userInfo.getIdentityCard());
		resultMap.put("specialtyCode", userInfo == null ? "" : userInfo.getSpecialtyCode() == null ? "" : userInfo.getSpecialtyCode()+"");
		resultMap.put("polity", userInfo == null ? "" : userInfo.getPolity() == null ? "" : userInfo.getPolity());
		resultMap.put("administrativeLevel", userInfo == null ? "" : userInfo.getAdministrativeLevel() == null ? "" : userInfo.getAdministrativeLevel()+"");
		resultMap.put("technicTitleLevel", userInfo == null ? "" : userInfo.getTechnicTitleLevel() == null ? "" : userInfo.getTechnicTitleLevel()+"");
		resultMap.put("departmentId", userInfo == null ? "" : userInfo.getDepartment() == null ? "" : userInfo.getDepartment().getDepartmentId());
		resultMap.put("departmentName", userInfo == null ? "" : userInfo.getDepartment() == null ? "" : userInfo.getDepartment().getDepartmentName()+"");
		resultMap.put("postType", userInfo == null ? "" : userInfo.getPostType() == null ? "" : userInfo.getPostType()+"");
		resultMap.put("staff", userInfo == null ? "" : userInfo.getStaff() == null ? "" : userInfo.getStaff()+"");
		resultMap.put("mobile", userInfo == null ? "" : userInfo.getMobile() == null ? "" : userInfo.getMobile());

		this.setResultData(resultMap);
		return JSON;
	}

	/**
	 *
	 * <p>
	 * 得到成员列表
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-7-19 - 下午1:26:31
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @return
	 */
	public String list() {
		PageSortModel<SecrecyOfficeMember> psm = new PageSortModel<SecrecyOfficeMember>(getRequest(), "secrecyOfficeMemberList");
		secrecyOfficeMemberList = secrecyOfficeMemberModuleService.getSecrecyOfficeMemberPageList(psm, secrecyOffice
				, SECRECY_STATUS_NOW, district);
		putToRequest("isDetail", getRequest().getParameter("isDetail"));
		return SUCCESS;
	}

	/**
	 *
	 * <p>
	 * 得到密级变更成员列表
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-7-19 - 上午9:48:40
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @return
	 */
	public String secrecyChangeList() {
		PageSortModel<SecrecyOfficeMemberChange> secrecyOfficeMemberChangePsm
			= new PageSortModel<SecrecyOfficeMemberChange>(getRequest(), "secrecyOfficeMemberChangeList");
		secrecyOfficeMemberChangeList = secrecyOfficeMemberChangeModuleService
				.getSecrecyOfficeMemberChangePageList(secrecyOfficeMemberChangePsm, secrecyOffice, district);
		return SUCCESS;
	}
	/**
	 *
	 * <p>
	 * 得到脱密成员列表
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-7-19 - 上午9:48:50
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @return
	 */
	public String decryptionList() {
		PageSortModel<SecrecyOfficeMemberDecryption> secrecyOfficeMemberDecryptionPsm
		= new PageSortModel<SecrecyOfficeMemberDecryption>(getRequest(), "secrecyOfficeMemberDecryptionList");
		secrecyOfficeMemberDecryptionList = secrecyOfficeMemberDecryptionModuleService
				.getSecrecyOfficeMemberDecryptionPageList(secrecyOfficeMemberDecryptionPsm, secrecyOffice, district);
		return SUCCESS;
	}

	/**
	 *
	 * <p>
	 * 密级变更
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-7-18 - 下午4:38:51
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @return
	 */
	public String secrecyChange() {
		secrecyOfficeMember = secrecyOfficeMemberModuleService.get(secrecyOfficeMember.getSecrecyOfficeMemberId());
		return SUCCESS;
	}
	/**
	 *
	 * <p>
	 * 密级变更ing
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-7-18 - 下午4:40:23
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @return
	 */
	public String secrecyChanging() {
		secrecyOfficeMemberChange.setCreatePerson(getCurrentUser());
		secrecyOfficeMemberChange.setCreateDate(new Date());
		secrecyOfficeMemberChangeModuleService.save(secrecyOfficeMemberChange);
		//更改原保密办成员涉密状态
		secrecyOfficeMember = secrecyOfficeMemberModuleService.get(secrecyOfficeMemberChange.getSecrecyOfficeMember().getSecrecyOfficeMemberId());
		secrecyOfficeMember.setSecrecyPersonLevel(secrecyOfficeMemberChange.getCurrentLevel());
		secrecyOfficeMember.setSecrecyStatus(SECRECY_STATUS_CHANGE);
		secrecyOfficeMemberModuleService.update(secrecyOfficeMember);
		addActionMessage(getMessageConstant().getSaveSuccess());
		//是否关闭密级变更页面 0 关闭
		putToRequest("reflag", "0");
		return SUCCESS;
	}
	/**
	 *
	 * <p>
	 * 脱密
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-7-18 - 下午4:38:51
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @return
	 */
	public String decryption() {
		secrecyOfficeMember = secrecyOfficeMemberModuleService.get(secrecyOfficeMember.getSecrecyOfficeMemberId());
		return SUCCESS;
	}
	/**
	 *
	 * <p>
	 * 脱密ing
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-7-18 - 下午4:40:23
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @return
	 */
	public String decryptioning() {
		secrecyOfficeMemberDecryption.setCreatePerson(getCurrentUser());
		secrecyOfficeMemberDecryption.setCreateDate(new Date());
		secrecyOfficeMemberDecryptionModuleService.save(secrecyOfficeMemberDecryption);
		//更改原保密办成员涉密状态
		secrecyOfficeMember = secrecyOfficeMemberModuleService.get(secrecyOfficeMemberDecryption.getSecrecyOfficeMember().getSecrecyOfficeMemberId());
		secrecyOfficeMember.setSecrecyStatus(SECRECY_STATUS_DECRYPTION);
		secrecyOfficeMemberModuleService.update(secrecyOfficeMember);
		addActionMessage(getMessageConstant().getSaveSuccess());
		putToRequest("reflag", "0");
		return SUCCESS;
	}

	// ******************** Setter & Getter ********************
	public SecrecyOffice getSecrecyOffice() {
		return secrecyOffice;
	}

	public void setSecrecyOffice(SecrecyOffice secrecyOffice) {
		this.secrecyOffice = secrecyOffice;
	}

	public SecrecyOfficeMember getSecrecyOfficeMember() {
		return secrecyOfficeMember;
	}

	public void setSecrecyOfficeMember(SecrecyOfficeMember secrecyOfficeMember) {
		this.secrecyOfficeMember = secrecyOfficeMember;
	}

	public List<SecrecyOfficeMember> getSecrecyOfficeMemberList() {
		return secrecyOfficeMemberList;
	}

	public void setSecrecyOfficeMemberList(
			List<SecrecyOfficeMember> secrecyOfficeMemberList) {
		this.secrecyOfficeMemberList = secrecyOfficeMemberList;
	}

	public SecrecyOfficeMemberModuleService getSecrecyOfficeMemberModuleService() {
		return secrecyOfficeMemberModuleService;
	}

	public void setSecrecyOfficeMemberModuleService(
			SecrecyOfficeMemberModuleService secrecyOfficeMemberModuleService) {
		this.secrecyOfficeMemberModuleService = secrecyOfficeMemberModuleService;
	}

	public String getSecrecyOfficeMemberIds() {
		return secrecyOfficeMemberIds;
	}

	public void setSecrecyOfficeMemberIds(String secrecyOfficeMemberIds) {
		this.secrecyOfficeMemberIds = secrecyOfficeMemberIds;
	}

	public String getUserInfoId() {
		return userInfoId;
	}

	public void setUserInfoId(String userInfoId) {
		this.userInfoId = userInfoId;
	}

	public String getDeptFlag() {
		return deptFlag;
	}

	public void setDeptFlag(String deptFlag) {
		this.deptFlag = deptFlag;
	}

	/**
	 * @return 返回secrecyOfficeMemberChange
	 */
	public SecrecyOfficeMemberChange getSecrecyOfficeMemberChange() {
		return secrecyOfficeMemberChange;
	}

	/**
	 * @param secrecyOfficeMemberChange 设置secrecyOfficeMemberChange
	 */
	public void setSecrecyOfficeMemberChange(
			SecrecyOfficeMemberChange secrecyOfficeMemberChange) {
		this.secrecyOfficeMemberChange = secrecyOfficeMemberChange;
	}

	/**
	 * @return 返回secrecyOfficeMemberDecryption
	 */
	public SecrecyOfficeMemberDecryption getSecrecyOfficeMemberDecryption() {
		return secrecyOfficeMemberDecryption;
	}

	/**
	 * @param secrecyOfficeMemberDecryption 设置secrecyOfficeMemberDecryption
	 */
	public void setSecrecyOfficeMemberDecryption(
			SecrecyOfficeMemberDecryption secrecyOfficeMemberDecryption) {
		this.secrecyOfficeMemberDecryption = secrecyOfficeMemberDecryption;
	}

	/**
	 * @param secrecyOfficeMemberChangeModuleService 设置secrecyOfficeMemberChangeModuleService
	 */
	public void setSecrecyOfficeMemberChangeModuleService(
			SecrecyOfficeMemberChangeModuleService secrecyOfficeMemberChangeModuleService) {
		this.secrecyOfficeMemberChangeModuleService = secrecyOfficeMemberChangeModuleService;
	}

	/**
	 * @param secrecyOfficeMemberDecryptionModuleService 设置secrecyOfficeMemberDecryptionModuleService
	 */
	public void setSecrecyOfficeMemberDecryptionModuleService(
			SecrecyOfficeMemberDecryptionModuleService secrecyOfficeMemberDecryptionModuleService) {
		this.secrecyOfficeMemberDecryptionModuleService = secrecyOfficeMemberDecryptionModuleService;
	}

	/**
	 * @return 返回secrecyOfficeMemberChangeList
	 */
	public List<SecrecyOfficeMemberChange> getSecrecyOfficeMemberChangeList() {
		return secrecyOfficeMemberChangeList;
	}

	/**
	 * @param secrecyOfficeMemberChangeList 设置secrecyOfficeMemberChangeList
	 */
	public void setSecrecyOfficeMemberChangeList(
			List<SecrecyOfficeMemberChange> secrecyOfficeMemberChangeList) {
		this.secrecyOfficeMemberChangeList = secrecyOfficeMemberChangeList;
	}

	/**
	 * @return 返回secrecyOfficeMemberDecryptionList
	 */
	public List<SecrecyOfficeMemberDecryption> getSecrecyOfficeMemberDecryptionList() {
		return secrecyOfficeMemberDecryptionList;
	}

	/**
	 * @param secrecyOfficeMemberDecryptionList 设置secrecyOfficeMemberDecryptionList
	 */
	public void setSecrecyOfficeMemberDecryptionList(
			List<SecrecyOfficeMemberDecryption> secrecyOfficeMemberDecryptionList) {
		this.secrecyOfficeMemberDecryptionList = secrecyOfficeMemberDecryptionList;
	}

	/**
	 * @return 返回district
	 */
	public District getDistrict() {
		return district;
	}

	/**
	 * @param district 设置district
	 */
	public void setDistrict(District district) {
		this.district = district;
	}
}