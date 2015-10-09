package com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;

import com.cdthgk.bmp.core.action.BmpAction;
import com.cdthgk.bmp.log.context.BusinessLogContext;
import com.cdthgk.bmp.log.vo.BusinessLog;
import com.cdthgk.bmp.secrecyorganization.secrecycommittee.vo.PersonGroupPosition;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.service.SecrecyWorkOrganModuleService;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.service.SecrecyWorkOrganPositionModuleService;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.service.SecrecyWorkOrganRelationMemberChangeService;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.service.SecrecyWorkOrganRelationMemberModuleService;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.vo.PersonGroupRelationChange;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.vo.SecrecyWorkOrgan;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.vo.SecrecyWorkOrganRelationMember;
import com.cdthgk.component.ioc.ContextUtils;
import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;

/**
 * <p>
 * 保密组织机构成员 Action 类
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
 *
 * @author FastCodeingTools
 * @author liucc
 * @since 1.0
 * @version 1.0
 */
public class SecrecyWorkOrganRelationMemberAction extends BmpAction {

	private static final long serialVersionUID = -2561934305983981072L;

	// 构造器
	/** 默认构造器 */
	public SecrecyWorkOrganRelationMemberAction() {
	}

	// 通用字段
	private SecrecyWorkOrganModuleService secrecyWorkOrganModuleService;
	private SecrecyWorkOrganRelationMemberModuleService secrecyWorkOrganRelationMemberModuleService;
	private SecrecyWorkOrganPositionModuleService secrecyWorkOrganPositionModuleService;
	private SecrecyWorkOrganRelationMemberChangeService secrecyWorkOrganRelationMemberChangeService;
	private SecrecyWorkOrgan secrecyWorkOrgan;
	private SecrecyWorkOrganRelationMember secrecyWorkOrganRelationMember;
	private List<SecrecyWorkOrgan> secrecyWorkOrganList;
	private List<SecrecyWorkOrganRelationMember> secrecyWorkOrganRelationMemberList;
	private String secrecyWorkOrganRelationMemberIds;
	private PersonGroupRelationChange personGroupRelationChange;
	private Boolean needReload = false;

	//部门变更或者部门改名的标志：1：部门改名；2部门变更
	private String deptFlag;
	private Organ organ;

	// *********************** 方 法 ***********************
	/**
	 * <p>
	 * 保密组织机构成员列表页面
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
		// 通过组织机构ID得到组织机构信息
		secrecyWorkOrgan = secrecyWorkOrganModuleService.get(secrecyWorkOrgan.getSecrecyWorkOrganId());

		// 获取组织机构下组织机构人员列表
		if (organ == null) {
			organ = getCurrentUser().getOrgan();
		}else {
			organ = secrecyWorkOrganRelationMemberModuleService.get(getRequest().getParameter("organ.organId").toString(), Organ.class);
		}
		putToRequest("secrecyWorkOrganMemberUnitList", secrecyWorkOrganRelationMemberModuleService.getPageList(secrecyWorkOrgan, organ, PERSON_CHANGE_NOW));
		return this.redirectActionResult(LIST);
	}

	/**
	 * <p>
	 * 保密办列表页面
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
		// 通过组织机构ID得到组织机构信息
		secrecyWorkOrgan = secrecyWorkOrganModuleService.get(secrecyWorkOrgan.getSecrecyWorkOrganId());

		// 获取组织机构下组织机构人员列表
		if (organ == null) {
			organ = getCurrentUser().getOrgan();
		}else {
			organ = secrecyWorkOrganRelationMemberModuleService.get(getRequest().getParameter("organ.organId").toString(), Organ.class);
		}
		putToRequest("secrecyWorkOrganMemberUnitList", secrecyWorkOrganRelationMemberModuleService.getPageList(secrecyWorkOrgan, organ, PERSON_CHANGE_NOW));
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
		// 通过组织机构ID得到组织机构信息
		secrecyWorkOrganRelationMember =
				secrecyWorkOrganRelationMemberModuleService.get(secrecyWorkOrganRelationMember.getSecrecyWorkOrganRelationId());
		putToRequest("secrecyWorkOrganRelationMember",secrecyWorkOrganRelationMember);
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
		secrecyWorkOrgan = secrecyWorkOrganModuleService.get(secrecyWorkOrgan.getSecrecyWorkOrganId());

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
	 * @author liucc
	 * @since 1.0
	 * @version 1.0
	 */
	public String adding() {
		if(secrecyWorkOrgan==null || secrecyWorkOrgan.getSecrecyWorkOrganId()==null || secrecyWorkOrgan.getSecrecyWorkOrganId().equals("")){
			this.addActionMessage("传入的单位保密工作机构ID有误，请检查。");
			return this.redirectActionResult("edit");
		}
		secrecyWorkOrgan = secrecyWorkOrganModuleService.get(secrecyWorkOrgan.getSecrecyWorkOrganId());
		// 检查保密委员会职务主任只能有一个
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("secrecyWorkOrganId", secrecyWorkOrgan.getSecrecyWorkOrganId());
		List<SecrecyWorkOrganRelationMember> uniqueSecrecyWorkOrganRelationMember =
				secrecyWorkOrganRelationMemberModuleService.
				getList("FROM SecrecyWorkOrganRelationMember WHERE (personGroupPosition = '1' or personGroupPosition = '4' ) and (secrecyStatus <> "+ PERSON_CHANGE_HISTORY +" or secrecyStatus is null ) and secrecyWorkOrgan.id = :secrecyWorkOrganId",
						params);
		if(uniqueSecrecyWorkOrganRelationMember != null && uniqueSecrecyWorkOrganRelationMember.size()>0 &&
				(secrecyWorkOrganRelationMember.getPersonGroupPosition().getPersonGroupPosition().equals("1")
						|| secrecyWorkOrganRelationMember.getPersonGroupPosition().getPersonGroupPosition().equals("4"))){
			if(secrecyWorkOrganRelationMember.getPersonGroupPosition().getPersonGroupPosition().equals("1")){
				addActionMessage("增加失败：该保密工作机构职务中只能允许有一个【主任】，请不要重复添加。");
			}
			if(secrecyWorkOrganRelationMember.getPersonGroupPosition().getPersonGroupPosition().equals("4")){
				addActionMessage("增加失败：该保密工作机构职务中只能允许有一个【组长】，请不要重复添加。");
			}
			// 获取保密组织机构职位列表
			List<PersonGroupPosition> personGroupPositionList = secrecyWorkOrganPositionModuleService.getByGroupType(secrecyWorkOrgan);
			putToRequest("personGroupPositionList", personGroupPositionList);
			putToRequest("secrecyWorkOrgan",secrecyWorkOrgan);
			return "edit";
		}
		secrecyWorkOrganRelationMember = secrecyWorkOrganRelationMemberModuleService
				.saveSystemDataBySecrecyWorkOrgan(secrecyWorkOrganRelationMember, getCurrentUser(), null);
		PersonGroupPosition pgp = secrecyWorkOrganPositionModuleService.get(secrecyWorkOrganRelationMember.getPersonGroupPosition().getPersonGroupPosition());
		secrecyWorkOrganRelationMember.setPersonGroupPosition(pgp);
		secrecyWorkOrganRelationMember.setSecrecyWorkOrgan(secrecyWorkOrgan);
		secrecyWorkOrganRelationMemberModuleService.save(secrecyWorkOrganRelationMember);

                BusinessLog log = new BusinessLog();
                log.setBusinessName("保密工作机构成员 ");
                log.setPrimaryKey(secrecyWorkOrganRelationMember.getSecrecyWorkOrganRelationId());
                BusinessLogContext.getInstance().getBusinessLogService().saveAddBusinessLogByModule(getCurrentUser(), log, secrecyWorkOrganRelationMember);

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
	 * @author FastCodeingTools
	 * @author liucc
	 * @since 1.0
	 * @version 1.0
	 */
	public String edit() {
		secrecyWorkOrganRelationMember = secrecyWorkOrganRelationMemberModuleService
			.get(secrecyWorkOrganRelationMember.getSecrecyWorkOrganRelationId());
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
	 *
	 * @author FastCodeingTools
	 * @author liucc
	 * @since 1.0
	 * @version 1.0
	 */
	public String editing() {
	        if(secrecyWorkOrgan==null || secrecyWorkOrgan.getSecrecyWorkOrganId()==null || secrecyWorkOrgan.getSecrecyWorkOrganId().equals("")){
			this.addActionMessage("传入的单位保密工作机构ID有误，请检查。");
			return this.redirectActionResult("edit");
		}
		secrecyWorkOrgan = secrecyWorkOrganModuleService.get(secrecyWorkOrgan.getSecrecyWorkOrganId());
		// 检查保密委员会职务主任只能有一个
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("secrecyWorkOrganId", secrecyWorkOrgan.getSecrecyWorkOrganId());
		params.put("secrecyWorkOrganRelationId", secrecyWorkOrganRelationMember.getSecrecyWorkOrganRelationId());
		List<SecrecyWorkOrganRelationMember> uniqueSecrecyWorkOrganRelationMember =
				secrecyWorkOrganRelationMemberModuleService.
				getList("FROM SecrecyWorkOrganRelationMember WHERE (personGroupPosition = '1' or personGroupPosition = '4' ) and (secrecyStatus <> "+ PERSON_CHANGE_HISTORY +" or secrecyStatus is null ) and secrecyWorkOrgan.id = :secrecyWorkOrganId and secrecyWorkOrganRelationId != :secrecyWorkOrganRelationId",
						params);
		if(uniqueSecrecyWorkOrganRelationMember != null && uniqueSecrecyWorkOrganRelationMember.size()>0 &&
				(secrecyWorkOrganRelationMember.getPersonGroupPosition().getPersonGroupPosition().equals("1")
						|| secrecyWorkOrganRelationMember.getPersonGroupPosition().getPersonGroupPosition().equals("4"))){
			if(secrecyWorkOrganRelationMember.getPersonGroupPosition().getPersonGroupPosition().equals("1")){
				addActionMessage("该保密工作机构职务中只能允许有一个【主任】或【组长】，请不要重复添加。");
			}
			if(secrecyWorkOrganRelationMember.getPersonGroupPosition().getPersonGroupPosition().equals("4")){
				addActionMessage("该保密工作机构职务中只能允许有一个【组长】或【主任】，请不要重复添加。");
			}
			// 获取保密组织机构职位列表
			List<PersonGroupPosition> personGroupPositionList = secrecyWorkOrganPositionModuleService.getByGroupType(secrecyWorkOrgan);
			putToRequest("personGroupPositionList", personGroupPositionList);
			putToRequest("secrecyWorkOrgan",secrecyWorkOrgan);
			return "edit";
		}
		SecrecyWorkOrganRelationMember swormDb = secrecyWorkOrganRelationMemberModuleService.get(secrecyWorkOrganRelationMember.getSecrecyWorkOrganRelationId());

		SessionFactory sf =  ContextUtils.getDefaultContext().getBean("sessionFactory");
                sf.getCurrentSession().evict(swormDb);

		secrecyWorkOrganRelationMember = secrecyWorkOrganRelationMemberModuleService
			.saveSystemDataBySecrecyWorkOrgan(secrecyWorkOrganRelationMember, getCurrentUser(), deptFlag);
		PersonGroupPosition pgp = secrecyWorkOrganPositionModuleService.get(secrecyWorkOrganRelationMember.getPersonGroupPosition().getPersonGroupPosition());
		secrecyWorkOrganRelationMember.setPersonGroupPosition(pgp);
		secrecyWorkOrganRelationMember.setSecrecyWorkOrgan(secrecyWorkOrgan);
		secrecyWorkOrganRelationMemberModuleService.update(secrecyWorkOrganRelationMember);

		BusinessLog log = new BusinessLog();
                log.setBusinessName("保密工作机构成员");
                log.setPrimaryKey(secrecyWorkOrganRelationMember.getSecrecyWorkOrganRelationId());
                BusinessLogContext.getInstance().getBusinessLogService().saveEditBusinessLogByModule(getCurrentUser(), log, secrecyWorkOrganRelationMember, swormDb);

		// 设置信息
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
		// 条件1： 检查传入的保密组织机构ID
		if(secrecyWorkOrganRelationMemberIds==null || secrecyWorkOrganRelationMemberIds.equals("") || secrecyWorkOrganRelationMemberIds.equals(",")){
			this.addActionMessage("传入的保密工作机构成员ID有误，请检查。");
			return this.redirectActionResult("edit");
		}
		if( secrecyWorkOrganRelationMemberIds!=null && !"".equals(secrecyWorkOrganRelationMemberIds) ){
        		for( String id : secrecyWorkOrganRelationMemberIds.split(",")){
        		        SecrecyWorkOrganRelationMember swormTmp =  secrecyWorkOrganRelationMemberModuleService.get(id);
        		        if( swormTmp!=null ){
        		                        BusinessLog log = new BusinessLog();
        		                        log.setBusinessName("保密工作机构成员 ");
        		                        log.setPrimaryKey(swormTmp.getSecrecyWorkOrganRelationId());
        		                        BusinessLogContext.getInstance().getBusinessLogService().saveDelBusinessLogByModule(getCurrentUser(), log, swormTmp);
        		        }
        		}
		}

		// 删除
		secrecyWorkOrganRelationMemberModuleService.deleteBatchIds(secrecyWorkOrganRelationMemberIds);
		secrecyWorkOrgan = secrecyWorkOrganModuleService.get(secrecyWorkOrgan.getSecrecyWorkOrganId());

		// 设置信息
		this.addActionMessage(getMessageConstant().getDeleteSuccess());
		return this.redirectActionResult(LIST);
	}

	/**
	 *
	 * <p>
	 * 方法名：personnelChange
	 * </p>
	 * <p>
	 * 宋亚非 2013-7-15 上午10:57:54
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
	 * 方法名：personelChanging
	 * </p>
	 * <p>
	 * 宋亚非 2013-7-15 上午10:58:14
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
		personGroupRelationChange.setCreatePerson(getCurrentUser());
		personGroupRelationChange.setCreateDate(new Date());
		this.secrecyWorkOrganRelationMemberChangeService.save(personGroupRelationChange);

		SecrecyWorkOrganRelationMember sworm = this.secrecyWorkOrganRelationMemberModuleService.get(this.personGroupRelationChange.getBmPersonGroupRelationId().getSecrecyWorkOrganRelationId(), SecrecyWorkOrganRelationMember.class);
		sworm.setSecrecyStatus(BmpAction.PERSON_CHANGE_HISTORY);

		this.secrecyWorkOrganRelationMemberModuleService.update(sworm);
		this.needReload = true;
		addActionMessage("变更成功");
		return "changed";
	}

        public String fillUserInfo() {

                SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
                Map<String, String> resultMap = new HashMap<String, String>();
                String userInfoId = this.getRequest().getParameter("userInfoId");
                if (userInfoId==null || "".equals(userInfoId)) {
                        resultMap.put("message", "信息错误，未取得选取用户信息!");
                } else {
                        // 获取人员信息
                        UserInfo  userInfoDB = this.secrecyWorkOrganRelationMemberModuleService.get(userInfoId, UserInfo.class);
                        // 设定页面显示字段
                        resultMap.put("userName", userInfoDB.getName());
                        resultMap.put("sex", userInfoDB.getSex()==null ? "" : userInfoDB.getSex());
                        resultMap.put("nation", userInfoDB.getNation().ordinal() + "");
                        resultMap.put("birthDate", userInfoDB.getBirthDate() == null ?
                                        "" : s.format(userInfoDB.getBirthDate()));
                        resultMap.put("learningLevel", userInfoDB.getLearningLevel() + "");
                        resultMap.put("idCard", userInfoDB.getIdentityCard());
                        resultMap.put("polity", userInfoDB.getPolity());
                        resultMap.put("departmentId", userInfoDB.getDepartment() == null ?
                                        "" : userInfoDB.getDepartment().getDepartmentId());
                        resultMap.put("departmentName", userInfoDB.getDepartment() == null ?
                                        "" : userInfoDB.getDepartment().getDepartmentName());
                        resultMap.put("staff", userInfoDB.getStaff() == null ? "" : userInfoDB.getStaff().toString());
                        resultMap.put("mobile", userInfoDB.getMobile());
                        resultMap.put("administrativeLevel", userInfoDB == null ? "" : userInfoDB.getAdministrativeLevel() == null ? "" : userInfoDB.getAdministrativeLevel()+"");

                        resultMap.put("duty", userInfoDB == null ? "" : userInfoDB.getDuty() == null ? "" : userInfoDB.getDuty());
                        resultMap.put("phone", userInfoDB == null ? "" : userInfoDB.getPhone() == null ? "" : userInfoDB.getPhone());
                        resultMap.put("technicTitleLevel", userInfoDB == null ? "" : userInfoDB.getTechnicTitleLevel() == null ? "" : userInfoDB.getTechnicTitleLevel()+"");
                        resultMap.put("specialtyCode", userInfoDB == null ? "" : userInfoDB.getSpecialtyCode() == null ? "" : userInfoDB.getSpecialtyCode()+"");

                }
                // 将设定值返回给页面
                this.setResultData(resultMap);
                return JSON;
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
	 * 返回secrecyWorkOrganRelationMemberModuleService
	 * @return secrecyWorkOrganRelationMemberModuleService
	 */
	public SecrecyWorkOrganRelationMemberModuleService getSecrecyWorkOrganRelationMemberModuleService() {
		return secrecyWorkOrganRelationMemberModuleService;
	}

	/**
	 * 设置secrecyWorkOrganRelationMemberModuleService
	 * @param secrecyWorkOrganRelationMemberModuleService secrecyWorkOrganRelationMemberModuleService
	 */
	public void setSecrecyWorkOrganRelationMemberModuleService(
			SecrecyWorkOrganRelationMemberModuleService secrecyWorkOrganRelationMemberModuleService) {
		this.secrecyWorkOrganRelationMemberModuleService = secrecyWorkOrganRelationMemberModuleService;
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
	 * 返回secrecyWorkOrganRelationMember
	 * @return secrecyWorkOrganRelationMember
	 */
	public SecrecyWorkOrganRelationMember getSecrecyWorkOrganRelationMember() {
		return secrecyWorkOrganRelationMember;
	}

	/**
	 * 设置secrecyWorkOrganRelationMember
	 * @param secrecyWorkOrganRelationMember secrecyWorkOrganRelationMember
	 */
	public void setSecrecyWorkOrganRelationMember(
			SecrecyWorkOrganRelationMember secrecyWorkOrganRelationMember) {
		this.secrecyWorkOrganRelationMember = secrecyWorkOrganRelationMember;
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
	 * 返回secrecyWorkOrganRelationMemberList
	 * @return secrecyWorkOrganRelationMemberList
	 */
	public List<SecrecyWorkOrganRelationMember> getSecrecyWorkOrganRelationMemberList() {
		return secrecyWorkOrganRelationMemberList;
	}

	/**
	 * 设置secrecyWorkOrganRelationMemberList
	 * @param secrecyWorkOrganRelationMemberList secrecyWorkOrganRelationMemberList
	 */
	public void setSecrecyWorkOrganRelationMemberList(
			List<SecrecyWorkOrganRelationMember> secrecyWorkOrganRelationMemberList) {
		this.secrecyWorkOrganRelationMemberList = secrecyWorkOrganRelationMemberList;
	}

	/**
	 * 返回secrecyWorkOrganRelationMemberIds
	 * @return secrecyWorkOrganRelationMemberIds
	 */
	public String getSecrecyWorkOrganRelationMemberIds() {
		return secrecyWorkOrganRelationMemberIds;
	}

	/**
	 * 设置secrecyWorkOrganRelationMemberIds
	 * @param secrecyWorkOrganRelationMemberIds secrecyWorkOrganRelationMemberIds
	 */
	public void setSecrecyWorkOrganRelationMemberIds(
			String secrecyWorkOrganRelationMemberIds) {
		this.secrecyWorkOrganRelationMemberIds = secrecyWorkOrganRelationMemberIds;
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
	 * @return the personGroupRelationChange
	 */
	public PersonGroupRelationChange getPersonGroupRelationChange() {
		return personGroupRelationChange;
	}

	/**
	 * @param personGroupRelationChange the personGroupRelationChange to set
	 */
	public void setPersonGroupRelationChange(
			PersonGroupRelationChange personGroupRelationChange) {
		this.personGroupRelationChange = personGroupRelationChange;
	}

	/**
	 * @return the secrecyWorkOrganRelationMemberChangeService
	 */
	public SecrecyWorkOrganRelationMemberChangeService getSecrecyWorkOrganRelationMemberChangeService() {
		return secrecyWorkOrganRelationMemberChangeService;
	}

	/**
	 * @param secrecyWorkOrganRelationMemberChangeService the secrecyWorkOrganRelationMemberChangeService to set
	 */
	public void setSecrecyWorkOrganRelationMemberChangeService(
			SecrecyWorkOrganRelationMemberChangeService secrecyWorkOrganRelationMemberChangeService) {
		this.secrecyWorkOrganRelationMemberChangeService = secrecyWorkOrganRelationMemberChangeService;
	}


}