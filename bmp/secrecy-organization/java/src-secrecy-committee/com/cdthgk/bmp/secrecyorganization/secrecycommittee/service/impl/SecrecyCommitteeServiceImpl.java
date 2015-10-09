package com.cdthgk.bmp.secrecyorganization.secrecycommittee.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.bmp.secrecyorganization.secrecycommittee.service.SecrecyCommitteeMemberModuleService;
import com.cdthgk.bmp.secrecyorganization.secrecycommittee.service.SecrecyCommitteeModuleService;
import com.cdthgk.bmp.secrecyorganization.secrecycommittee.vo.SecrecyCommittee;
import com.cdthgk.bmp.secrecyorganization.secrecycommittee.vo.SecrecyCommitteeMember;
import com.cdthgk.bmp.secrecyorganization.secrecyoffice.vo.SecrecyOffice;
import com.cdthgk.code.enums.OrganType;
import com.cdthgk.common.bean.BeanUtils;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.core.OrganizationContext;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

/**
 * <p>
 * 保密委员会 Service 实现类
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
public class SecrecyCommitteeServiceImpl extends BmpServiceImpl<SecrecyCommittee, String> implements SecrecyCommitteeModuleService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(SecrecyCommitteeServiceImpl.class);

	private SecrecyCommitteeMemberModuleService secrecyCommitteeMemberModuleService;

	/** 默认构造器 */
	SecrecyCommitteeServiceImpl() {
	}

	/**
	 * <p>
	 * 查询列表方法
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
	public List<SecrecyCommittee> getPageList(PageSortModel psm, SecrecyCommittee secrecyCommittee){
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("from SecrecyCommittee secrecyCommittee where 1=1 ");

		// 加入职责与成员分工（dutyMemberWork）条件
		if(secrecyCommittee!=null && secrecyCommittee.getDutyMemberWork()!=null && !secrecyCommittee.getDutyMemberWork().equals("")){
			hql.append("and secrecyCommittee.dutyMemberWork like :dutyMemberWork ");
			params.put("dutyMemberWork", "%" + secrecyCommittee.getDutyMemberWork() + "%");
		}

		// 加入发文号（docNo）条件
		if(secrecyCommittee!=null && secrecyCommittee.getDocNo()!=null && !secrecyCommittee.getDocNo().equals("")){
			hql.append("and secrecyCommittee.docNo like :docNo ");
			params.put("docNo", "%" + secrecyCommittee.getDocNo() + "%");
		}

		// 加入保密委名称（name）条件
		if(secrecyCommittee!=null && secrecyCommittee.getName()!=null && !secrecyCommittee.getName().equals("")){
			hql.append("and secrecyCommittee.name like :name ");
			params.put("name", "%" + secrecyCommittee.getName() + "%");
		}

		// 加入排序条件
		hql.append(" order by secrecyCommittee.createTime desc");

		// 查询
//		List<SecrecyCommittee> list = (List<SecrecyCommittee>) this.getList(hql.toString(), psm, params);
		List<SecrecyCommittee> list = (List<SecrecyCommittee>) this.findList(hql.toString(), params, psm);

		return list;
	}

	/**
	 * <p>
	 * 通过行政区划查询保密委
	 * </p>
	 * <p>
	 * 汪 钟 2012-12-14 17:54
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
	public SecrecyCommittee getByDistrict(District district){
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = "from SecrecyCommittee sc where sc.district.districtCode = :districtCode ";
		params.put("districtCode", district.getDistrictCode());

//		List<SecrecyCommittee> list = (List<SecrecyCommittee>) getList(hql, 0, 1, params);
		List<SecrecyCommittee> list = (List<SecrecyCommittee>) findList(hql, params, 0, 1);

		if(list!=null && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}

	/**
	 * <p>
	 * 通过人员得到所属行政区划的保密委名称（系统判定的名称，可以修改）
	 * </p>
	 * <p>
	 * 汪 钟 2012-12-17 16:25
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
	public String getNameByUser(User user) {
		String secrecyCommitteeName = "中共" + user.getOrgan().getDistrict().getDistrictName() + "委保密委员会";
		return secrecyCommitteeName;
	}

	/**
	 * <p>
	 * 创建新人员
	 * </p>
	 * <p>
	 * 汪 钟 2013-01-05 20:52
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
	public UserInfo saveNewUserInfo(String userInfoName, Organ org, User currentUser, String duty){
		//根据名字判断（有就返回）
		List<UserInfo> uiList = (List<UserInfo>) OrganizationContext.getInstance().getMemberService().getByName(userInfoName, org);
		UserInfo ui = null;
		if(uiList!=null && uiList.size()>0){
			ui = uiList.get(0);
		}
		if(ui!=null){
			return ui;
		}else{
			ui = new UserInfo();
			ui.setOrgan(org);
			ui.setCreateTime(new Date());
			ui.setBirthDate(null);
			ui.setBirthPlace("");
			ui.setDegree(1);
			ui.setDuty(duty);
			ui.setEmail("");
			ui.setEmpCode("");
			ui.setEnglishName("");
			ui.setFax("");
			ui.setHomeAddress("");
			ui.setIdentityCard("");
			ui.setJoinOrganDate(null);
			ui.setMobile("");
			ui.setMsn("");
			ui.setName(userInfoName);
			ui.setNativePlace("");
			ui.setNowAddress("");
			ui.setOrderNo(1);
			ui.setPhone("");
			ui.setPinyin("");
			ui.setPolity("1");
			ui.setQq("");
			ui.setRemark(new Date().toString() + "产生。创建者：" + currentUser.getUserName() + "(UserID:" + currentUser.getUserId() + ")；创建环境：在【保密业务】-【保密委员会】-【新增保密委成员】时填写人员时创建。");
			ui.setSex("0");
			ui.setSpecialty("");
			ui.setCreatePerson(currentUser);
			ui.setDepartment(org.getDepartment());
			ui.setPhoto(null);
			ui.setPost(null);
			ui.setGraduated("");
			ui.setUser(null);
			ui.setGraduationTime(null);
			ui.setStaff(null);
			ui.setTechnicDuty(null);
			ui.setTechnicTitle(null);
			ui.setDepartment(org.getDepartment());
			ui.setModifyPerson(currentUser);

			OrganizationContext.getInstance().getMemberService().save(ui);

			return ui;
		}
	}

	/**
	 * <p>
	 * 创建新单位
	 * </p>
	 * <p>
	 * 汪 钟 2013-01-05 20:52
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
	public Organ saveNewOrgan(String orgName, District district, User currentUser){
		//根据名字判断（有就返回）
		Organ org = OrganizationContext.getInstance().getOrganService().getByName(orgName);
		if(org!=null){
			return org;
		}else{
			Organ parentOrg = OrganizationContext.getInstance().getOrganService().get("1");
			org = new Organ();
			org.setContactPhone("");
			org.setDescription(new Date().toString() + "产生。创建者：" + currentUser.getUserName() + "(UserID:" + currentUser.getUserId() + ")；创建环境：在【保密业务】-【保密委员会】-【新增保密委成员】时填写单位时创建。");
			org.setOrganAddress("");
			org.setOrganCategory(null);
			org.setOrganCode("");
			org.setOrganName(orgName);
			org.setOrganShortName(orgName);
			org.setOrganType(OrganType.UNKNOW);
			org.setPhoneAreaCode("");
			org.setSort(1);
			org.setCreatePerson(currentUser);
			org.setCreateTime(new Date());
			org.setContactPerson(null);
			org.setDistrict(currentUser.getOrgan().getDistrict());
			org.setParent(parentOrg);
			org.setModifyPerson(currentUser);

			org = OrganizationContext.getInstance().getOrganService().save(org);
			return org;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SecrecyCommittee checkContainsSecrecyCommittee(Organ organ) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("organId", organ.getOrganId());
		SecrecyCommittee secrecyCommittee = (SecrecyCommittee) unique("from SecrecyCommittee s where s.createOrgan.organId = :organId", params);
		return secrecyCommittee;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SecrecyCommittee getSecrecyCommitteeByOrgan(Organ organ) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("organId", organ.getOrganId());
		return unique("from SecrecyCommittee s where s.createOrgan.organId = :organId", params);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void saveRecSecrecyCommittee(List<SecrecyCommittee> secrecyCommitteeList, String receiveOrganId) {
		if (secrecyCommitteeList == null) {
			return;
		}
		for (SecrecyCommittee secrecyCommittee : secrecyCommitteeList) {
			List<SecrecyCommitteeMember> oldMemberList = secrecyCommitteeMemberModuleService.getSecrecyCommitteeMemberList(secrecyCommittee.getSecrecyCommitteeId());
			SecrecyCommittee sc = this.get(secrecyCommittee.getSecrecyCommitteeId());
			if (null != sc) { // null != eno 说明已经保存过，现在只需更新
				try {
					BeanUtils.copyProperties(sc, secrecyCommittee);
				} catch(Exception e) {
					LOGGER.debug("属性转换异常:" + e.getMessage());
				}
				this.update(sc);
			} else { // 第一次保存
				this.save(secrecyCommittee);
			}
			//删除保密委成员
			secrecyCommitteeMemberModuleService.deleteBatch(oldMemberList);
			//添加
			secrecyCommitteeMemberModuleService.saveBatch(secrecyCommittee.getSecrecyCommitteeMembers());
		}
	}

	/**
	 * @return 返回secrecyCommitteeMemberModuleService
	 */
	public SecrecyCommitteeMemberModuleService getSecrecyCommitteeMemberModuleService() {
		return secrecyCommitteeMemberModuleService;
	}

	/**
	 * @param secrecyCommitteeMemberModuleService 设置secrecyCommitteeMemberModuleService
	 */
	public void setSecrecyCommitteeMemberModuleService(
			SecrecyCommitteeMemberModuleService secrecyCommitteeMemberModuleService) {
		this.secrecyCommitteeMemberModuleService = secrecyCommitteeMemberModuleService;
	}
}