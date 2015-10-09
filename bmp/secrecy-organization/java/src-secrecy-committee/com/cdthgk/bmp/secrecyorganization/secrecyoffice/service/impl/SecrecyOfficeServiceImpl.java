package com.cdthgk.bmp.secrecyorganization.secrecyoffice.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.bmp.secrecyorganization.secrecycommittee.vo.SecrecyCommittee;
import com.cdthgk.bmp.secrecyorganization.secrecyoffice.service.SecrecyOfficeMemberModuleService;
import com.cdthgk.bmp.secrecyorganization.secrecyoffice.service.SecrecyOfficeModuleService;
import com.cdthgk.bmp.secrecyorganization.secrecyoffice.vo.EmployPerson;
import com.cdthgk.bmp.secrecyorganization.secrecyoffice.vo.EstablishSituation;
import com.cdthgk.bmp.secrecyorganization.secrecyoffice.vo.Infrastructure;
import com.cdthgk.bmp.secrecyorganization.secrecyoffice.vo.InternalOrgan;
import com.cdthgk.bmp.secrecyorganization.secrecyoffice.vo.LeaderStaff;
import com.cdthgk.bmp.secrecyorganization.secrecyoffice.vo.SecrecyOffice;
import com.cdthgk.bmp.secrecyorganization.secrecyoffice.vo.SecrecyOfficeMember;
import com.cdthgk.bmp.secrecyperson.service.SecrecyPersonModuleService;
import com.cdthgk.code.enums.DataStatus;
import com.cdthgk.code.enums.OrganType;
import com.cdthgk.common.bean.BeanUtils;
import com.cdthgk.common.lang.CollectionUtils;
import com.cdthgk.common.lang.LangUtils;
import com.cdthgk.common.lang.StringUtils;
import com.cdthgk.common.lang.UUIDGenerator;
import com.cdthgk.platform.dictionary.context.DictionaryContext;
import com.cdthgk.platform.dictionary.domain.DictionaryOption;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.core.OrganizationContext;
import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.organ.query.OrganHqlQuery;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;
import com.cdthgk.platform.permission.user.domain.User;
import com.cdthgk.standard.asserts.AssertStandardApp;

/**
 * <p>
 * 保密办（保密局） Service 实现类
 * </p>
 * <p>
 * 陶汇源 创建时间 2013-1-6 - 下午12:21:56
 * </p>
 * <blockquote>
 * <h4>历史修改记录</h4>
 * <ul>
 * <li>修改人 修改时间 修改描述
 * </ul>
 * </blockquote>
 * <p>
 * copyright cdthgk 2010-2011, all rights reserved.
 * </p>
 *
 * @author 陶汇源
 * @author cdthgk r&d
 * @since 1.0
 * @version 1.0
 */
public class SecrecyOfficeServiceImpl extends BmpServiceImpl<SecrecyOffice, String>
	implements SecrecyOfficeModuleService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(SecrecyOfficeServiceImpl.class);

	private SecrecyPersonModuleService secrecyPersonModuleService;
	private SecrecyOfficeMemberModuleService secrecyOfficeMemberModuleService;


	// 构造器
	/** 默认构造器 */
	SecrecyOfficeServiceImpl() {
	}

	/**
	 * <p>
	 * 设置人员基本属性
	 * </p>
	 * <p>
	 * 陶汇源  2013-01-06 16:16:25
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * <p>
	 * copyright cdthgk 2010-2011, all rights reserved.
	 * </p>
	 *
	 * @author 陶汇源
	 * @author cdthgk r&d
	 * @since 1.0
	 * @version 1.0
	 */
	private void setUserInfoProperties(UserInfo userInfo, User currentUser) {
		userInfo.setOrgan(currentUser.getOrgan());
		userInfo.setDepartment(currentUser.getOrgan().getDepartment());
		userInfo.setCreatePerson(currentUser);
		userInfo.setModifyPerson(currentUser);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SecrecyOffice saveSystemDataBySecrecyOffice(
			SecrecyOffice secrecyOffice, User currentUser) {
		AssertStandardApp.isNotNull(secrecyOffice.getDirector(), "主任(局长)不能为空");
		AssertStandardApp.isNotNull(secrecyOffice.getPerson(), "负责人不能为空");
		AssertStandardApp.isNotNull(secrecyOffice.getDept(), "办公室不能为空");
		//当前单位下输入的人员如果有则保存，如果没有则通过输入的名字新增人员。
		//主任(局长)
		UserInfo directorUserInfo = new UserInfo();
		if (LangUtils.isEmpty(secrecyOffice.getDirector().getUserInfoId())) {
			//当前单位不存在该人员，新增系统人员
			List<UserInfo> userInfoList = (List<UserInfo>) OrganizationContext.getInstance().getMemberService()
					.getByName(secrecyOffice.getDirector().getName());
			if(userInfoList != null && userInfoList.size() > 0) {
				directorUserInfo = userInfoList.get(0);
			} else{
				directorUserInfo.setName(secrecyOffice.getDirector().getName());
				setUserInfoProperties(directorUserInfo, currentUser);
				directorUserInfo.save();
			}
		} else {
			directorUserInfo = secrecyPersonModuleService.get(secrecyOffice.getDirector()
								.getUserInfoId(), UserInfo.class);
		}
		secrecyOffice.setDirector(directorUserInfo);
		//负责人
		UserInfo dutyUserInfo = new UserInfo();
		if (LangUtils.isEmpty(secrecyOffice.getPerson().getUserInfoId())) {
			//当前单位不存在该人员，新增系统人员
			List<UserInfo> userInfoList = (List<UserInfo>) OrganizationContext.getInstance().getMemberService()
					.getByName(secrecyOffice.getPerson().getName());
			if(userInfoList != null && userInfoList.size() > 0) {
				dutyUserInfo = userInfoList.get(0);
			} else {
				dutyUserInfo.setName(secrecyOffice.getPerson().getName());
				setUserInfoProperties(dutyUserInfo, currentUser);
				dutyUserInfo.save();
			}
		} else {
			dutyUserInfo = secrecyPersonModuleService.get(secrecyOffice.getPerson()
							.getUserInfoId(), UserInfo.class);
		}
		secrecyOffice.setPerson(dutyUserInfo);
		//当前单位下输入的部门如果有则保存，如果没有则通过输入的名字新增部门。
		//办公室
		Department dept = new Department();
		if (LangUtils.isEmpty(secrecyOffice.getDept().getDepartmentId())) {
			//当前单位不存在该部门，新增系统部门
			Department existDepartment  = OrganizationContext.getInstance().getDepartmentService()
					.getByName(secrecyOffice.getDept().getDepartmentName(), currentUser.getOrgan());
			if(existDepartment == null) {
				dept.setDepartmentName(secrecyOffice.getDept().getDepartmentName());
				dept.setOrgan(currentUser.getOrgan());
				dept.setParent(currentUser.getOrgan().getDepartment());
				dept.setCreatePerson(currentUser);
				dept.setModifyPerson(currentUser);
				dept.save();
			} else {
				dept = existDepartment;
			}
		} else {
			dept = secrecyPersonModuleService.get(secrecyOffice.getDept()
					.getDepartmentId(), Department.class);
		}
		secrecyOffice.setDept(dept);
		return secrecyOffice;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SecrecyOffice getSecrecyOfficeByOrgan(Organ organ) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("organId", organ.getOrganId());
		return unique("from SecrecyOffice s where s.createOrgan.organId = :organId", params);
	}

	public SecrecyOffice getSecrecyOfficeBySecrecyCommittee(
			SecrecyCommittee secrecyCommittee) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("secrecyCommitteeId", secrecyCommittee.getSecrecyCommitteeId());
		return unique("from SecrecyOffice s where s.secrecyCommittee.secrecyCommitteeId = :secrecyCommitteeId", params);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void saveRecSecrecyOffice(List<SecrecyOffice> secrecyOfficeList,
			String receiveOrganId) {
		if (secrecyOfficeList == null) {
			return;
		}
		for (SecrecyOffice secrecyOffice : secrecyOfficeList) {
			List<SecrecyOfficeMember> oldMemberList = secrecyOfficeMemberModuleService.getSecrecyOfficeMemberList(secrecyOffice);
			SecrecyOffice so = this.get(secrecyOffice.getSecrecyOfficeId());
			if (null != so) { // null != eno 说明已经保存过，现在只需更新
				try {
					BeanUtils.copyProperties(so, secrecyOffice);
				} catch(Exception e) {
					LOGGER.debug("属性转换异常:" + e.getMessage());
				}
				this.update(so);
			} else { // 第一次保存
				this.save(secrecyOffice);
			}
			//删除保密办成员
			secrecyOfficeMemberModuleService.deleteBatch(oldMemberList);
			//添加
			secrecyOfficeMemberModuleService.saveBatch(secrecyOffice.getSecrecyOfficeMemberSet());
		}
	}

	// ******************** Setter & Getter ********************
	public void setSecrecyPersonModuleService(
			SecrecyPersonModuleService secrecyPersonModuleService) {
		this.secrecyPersonModuleService = secrecyPersonModuleService;
	}
	/**
	 * @param secrecyOfficeMemberModuleService 设置secrecyOfficeMemberModuleService
	 */
	public void setSecrecyOfficeMemberModuleService(
			SecrecyOfficeMemberModuleService secrecyOfficeMemberModuleService) {
		this.secrecyOfficeMemberModuleService = secrecyOfficeMemberModuleService;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SecrecyOffice initEstablishSituation(SecrecyOffice secrecyOffice) {
		if(CollectionUtils.isEmpty(secrecyOffice.getEstablishSituationSet())){
			Set<EstablishSituation> establishSituationSet = new HashSet<EstablishSituation>();
			EstablishSituation esSituation = new EstablishSituation();
			esSituation.setCivil(0);
			esSituation.setFullFunds(0);
			esSituation.setReferMamager(0);
			esSituation.setSelfFunds(0);
			establishSituationSet.add(esSituation);
			secrecyOffice.setEstablishSituationSet(establishSituationSet);
		} else {
			// 多个对象需要求和
			int civilCount = 0;
			int fullFundsCount = 0;
			int referMamagerCount = 0;
			int selfFundsCount = 0;
			for (EstablishSituation els : secrecyOffice.getEstablishSituationSet()) {
				civilCount += els.getCivil() == null ? 0 : els.getCivil();
				fullFundsCount += els.getFullFunds() == null ? 0 : els.getFullFunds();
				referMamagerCount += els.getReferMamager() == null ? 0 : els.getReferMamager();
				selfFundsCount += els.getSelfFunds() == null ? 0 : els.getSelfFunds();
			}
			Set<EstablishSituation> establishSituationSet = new HashSet<EstablishSituation>();
			EstablishSituation esSituation = new EstablishSituation();
			esSituation.setCivil(civilCount);
			esSituation.setFullFunds(fullFundsCount);
			esSituation.setReferMamager(referMamagerCount);
			esSituation.setSelfFunds(selfFundsCount);
			establishSituationSet.add(esSituation);
			secrecyOffice.setEstablishSituationSet(establishSituationSet);
		}
		if(CollectionUtils.isEmpty(secrecyOffice.getEmployPersonSet())){
			Set<EmployPerson> employPersonSet = new HashSet<EmployPerson>();
			EmployPerson employPerson = new EmployPerson();
			employPerson.setManagerStaff(0);
			employPerson.setOther(0);
			employPerson.setSpecialtyStaff(0);
			employPerson.setWorkStaff(0);
			employPersonSet.add(employPerson);
			secrecyOffice.setEmployPersonSet(employPersonSet);
		} else {
			// 多个对象需要求和
			int managerStaffCount = 0;
			int otherCount = 0;
			int specialtyStaffCount = 0;
			int workStaffCount = 0;
			for (EmployPerson elp : secrecyOffice.getEmployPersonSet()) {
				managerStaffCount += elp.getManagerStaff() == null ? 0 : elp.getManagerStaff();
				otherCount += elp.getOther() == null ? 0 : elp.getOther();
				specialtyStaffCount += elp.getSpecialtyStaff() == null ? 0 : elp.getSpecialtyStaff();
				workStaffCount += elp.getWorkStaff() == null ? 0 : elp.getWorkStaff();
			}
			Set<EmployPerson> employPersonSet = new HashSet<EmployPerson>();
			EmployPerson employPerson = new EmployPerson();
			employPerson.setManagerStaff(managerStaffCount);
			employPerson.setOther(otherCount);
			employPerson.setSpecialtyStaff(specialtyStaffCount);
			employPerson.setWorkStaff(workStaffCount);
			employPersonSet.add(employPerson);
			secrecyOffice.setEmployPersonSet(employPersonSet);
		}

		if(CollectionUtils.isEmpty(secrecyOffice.getLeaderStaffSet())){
			Set<LeaderStaff> leaderStaffSet = new HashSet<LeaderStaff>();
			LeaderStaff leaderStaff = new LeaderStaff();
			leaderStaff.setDeptStaff(0);
			leaderStaff.setOther(0);
			leaderStaff.setDeputyInspector(0);
			leaderStaff.setDeputySecretary(0);
			leaderStaff.setInspector(0);
			leaderStaff.setSecretary(0);
			leaderStaffSet.add(leaderStaff);
			secrecyOffice.setLeaderStaffSet(leaderStaffSet);
		} else {
			// 多个对象需要求和
			int deptStaffCount = 0;
			int otherCount = 0;
			int deputyInspectorCount = 0;
			int deputySecretaryCount = 0;
			int inspectorCount = 0;
			int secretaryCount = 0;
			for (LeaderStaff ls : secrecyOffice.getLeaderStaffSet()) {
				deptStaffCount += ls.getDeptStaff();
				otherCount += ls.getOther() == null ? 0 : ls.getOther();
				deputyInspectorCount += ls.getDeputyInspector() == null ? 0 : ls.getDeputyInspector();
				deputySecretaryCount += ls.getDeputySecretary() == null ? 0 : ls.getDeputySecretary();
				inspectorCount += ls.getInspector() == null ? 0 : ls.getInspector();
				secretaryCount += ls.getSecretary() == null ? 0 : ls.getSecretary();
			}
			Set<LeaderStaff> leaderStaffSet = new HashSet<LeaderStaff>();
			LeaderStaff leaderStaff = new LeaderStaff();
			leaderStaff.setDeptStaff(deptStaffCount);
			leaderStaff.setOther(otherCount);
			leaderStaff.setDeputyInspector(deputyInspectorCount);
			leaderStaff.setDeputySecretary(deputySecretaryCount);
			leaderStaff.setInspector(inspectorCount);
			leaderStaff.setSecretary(secretaryCount);
			leaderStaffSet.add(leaderStaff);
			secrecyOffice.setLeaderStaffSet(leaderStaffSet);
		}
		return secrecyOffice;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void saveEstablishSituation(SecrecyOffice secrecyOffice,
			EstablishSituation establishSituation, LeaderStaff leaderStaff,
			EmployPerson employPerson) {
		establishSituation.setSecrecyOffice(secrecyOffice);
		if (StringUtils.isEmpty(establishSituation.getEstablishSituationId())) {
			establishSituation.setEstablishSituationId(UUIDGenerator.generateUUID36());
			this.getPersistProxy().getOrmPersistence().getHibernateTemplate().save(establishSituation);
		} else {
			this.getPersistProxy().getOrmPersistence().getHibernateTemplate().update(establishSituation);
		}
		leaderStaff.setSecrecyOffice(secrecyOffice);
		if (StringUtils.isEmpty(leaderStaff.getLeaderStaffId())) {
			leaderStaff.setLeaderStaffId(UUIDGenerator.generateUUID36());
			this.getPersistProxy().getOrmPersistence().getHibernateTemplate().save(leaderStaff);
		} else {
			this.getPersistProxy().getOrmPersistence().getHibernateTemplate().update(leaderStaff);
		}
		employPerson.setSecrecyOffice(secrecyOffice);
		if (StringUtils.isEmpty(employPerson.getEmployPersonId())) {
			employPerson.setEmployPersonId(UUIDGenerator.generateUUID36());
			this.getPersistProxy().getOrmPersistence().getHibernateTemplate().save(employPerson);
		} else {
			this.getPersistProxy().getOrmPersistence().getHibernateTemplate().update(employPerson);
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void saveInternalOrgan(SecrecyOffice secrecyOffice, List<InternalOrgan> internalOrganList) {
		secrecyOffice = get(secrecyOffice.getSecrecyOfficeId());
		this.getPersistProxy().getOrmPersistence().deleteBatch(new ArrayList<InternalOrgan>(secrecyOffice.getInternalOrganSet()));
		for (InternalOrgan internalOrgan : internalOrganList) {
			if(internalOrgan != null){
				internalOrgan.setSecrecyOffice(secrecyOffice);
				this.getPersistProxy().getOrmPersistence().getHibernateTemplate().save(internalOrgan);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Map<String, Object>> initInternalOrgan(SecrecyOffice secrecyOffice, List<DictionaryOption> dictionaryOptionList) {
		Map<Integer, List<InternalOrgan>> listMap = new HashMap<Integer, List<InternalOrgan>>();
		for (DictionaryOption dict : dictionaryOptionList) {
			listMap.put(dict.getOptionValue(), new ArrayList<InternalOrgan>());
		}
		Iterator<InternalOrgan> iter = secrecyOffice.getInternalOrganSet().iterator();
		while (iter.hasNext()) {
			InternalOrgan internalOrgan = iter.next();
			listMap.get(internalOrgan.getInternalType()).add(internalOrgan);
		}
		int max = -1;
		for(List<InternalOrgan> list : listMap.values()) {
			if (max < list.size()) {
				max = list.size();
			}
		}
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		for (int i = 0; i < max; i++) {
			Map<String, Object> internalOrganParams = new HashMap<String, Object>();
			for (DictionaryOption dict : dictionaryOptionList) {
				List<InternalOrgan> internalOrganList = listMap.get(dict.getOptionValue());
				Collections.sort(internalOrganList, new Comparator<InternalOrgan>() {
					@Override
					public int compare(InternalOrgan o1, InternalOrgan o2) {
						if (o1.getSort() > o2.getSort()) {
							return 1;
						} else {
							return -1;
						}
					}
				});
				if (internalOrganList.size() > i) {
					InternalOrgan internalOrgan = internalOrganList.get(i);
					internalOrganParams.put("xuhao"+internalOrgan.getInternalType(), internalOrgan.getSort());
					internalOrganParams.put("deptName"+internalOrgan.getInternalType(), internalOrgan.getInternalDeptname());
					internalOrganParams.put("workNum"+internalOrgan.getInternalType(), internalOrgan.getInternalWorkNum());
					internalOrganParams.put("realNum"+internalOrgan.getInternalType(), internalOrgan.getInternalRealNum());
					internalOrganParams.put("adminLevel"+internalOrgan.getInternalType()
							,  DictionaryContext.getInstance().getDictionaryService()
								.getOption("bmp", "organ_admin_level", internalOrgan.getInternalAdminLevel())
								.getOptionText());
				}
			}
			list.add(internalOrganParams);
		}
		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void saveInfrastructure(SecrecyOffice secrecyOffice,
			List<Infrastructure> infrastructureList) {
		secrecyOffice = get(secrecyOffice.getSecrecyOfficeId());
		this.getPersistProxy().getOrmPersistence().deleteBatch(new ArrayList<Infrastructure>(secrecyOffice.getInfrastructureSet()));
		for (Infrastructure infrastructure : infrastructureList) {
			if(infrastructure != null){
				infrastructure.setSecrecyOffice(secrecyOffice);
				this.getPersistProxy().getOrmPersistence().getHibernateTemplate().save(infrastructure);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<?> queryEstablishSituation(String entityName, District district, boolean isLayer) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder sql = new StringBuilder("select e from " + entityName + " e left join e.secrecyOffice so left join so.createOrgan.district d  where 1=1" );
		// false ： 查看本单位； true ：查看行政区
		if (isLayer) {
			sql.append(" and d.layer like :layer");
			params.put("layer", district.getLayer() + "%");
		} else {
			sql.append(" and d.districtCode = :districtCode");
			params.put("districtCode", district.getDistrictCode());
		}
		return this.getPersistProxy().getOrmPersistence().findList(sql.toString(), params);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, Integer> queryYearMap(District district, boolean isLayer) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("key56", queryOnlineSituation(district, new Integer[]{56}, isLayer));
		map.put("key46", queryOnlineSituation(district, new Integer[]{46,55}, isLayer));
		map.put("key36", queryOnlineSituation(district, new Integer[]{36,45}, isLayer));
		map.put("key35", queryOnlineSituation(district, new Integer[]{0,35}, isLayer));
		return map;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, Integer> queryDiplomaMap(District district, boolean isLayer) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		List<DictionaryOption> optionList = DictionaryContext.getInstance().getDictionaryService().getOptionList("person", "learning_level");
		for (DictionaryOption dictionaryOption : optionList) {
			map.put(dictionaryOption.getOptionId(), queryDiploma(district, dictionaryOption.getOptionValue(), isLayer));
		}
		return map;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, Integer> querySpecialMap(District district, boolean isLayer) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		List<DictionaryOption> optionList = DictionaryContext.getInstance().getDictionaryService().getOptionList("person", "specialty_code");
		Integer other = 0;
		for (DictionaryOption dictionaryOption : optionList) {
			// 只统计文学、法律、	管理、计算机、其他
			if (dictionaryOption.getOptionId().equals("sys_person_specialty_code_wx")) {
				map.put(dictionaryOption.getOptionId(), querySpecial(district, dictionaryOption.getOptionValue(), isLayer));
			} else if(dictionaryOption.getOptionId().equals("sys_person_specialty_code_fx")) {
				map.put(dictionaryOption.getOptionId(), querySpecial(district, dictionaryOption.getOptionValue(), isLayer));
			} else if(dictionaryOption.getOptionId().equals("sys_person_specialty_code_gl")) {
				map.put(dictionaryOption.getOptionId(), querySpecial(district, dictionaryOption.getOptionValue(), isLayer));
			} else if(dictionaryOption.getOptionId().equals("sys_person_specialty_code_jsj")) {
				map.put(dictionaryOption.getOptionId(), querySpecial(district, dictionaryOption.getOptionValue(), isLayer));
			} else {
				other += querySpecial(district, dictionaryOption.getOptionValue(), isLayer);
			}
		}
		map.put("other", other);
		return map;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, Integer> queryPostTypeMap(District district, boolean isLayer) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		List<DictionaryOption> optionList = DictionaryContext.getInstance().getDictionaryService().getOptionList("person", "post_type");
		for (DictionaryOption dictionaryOption : optionList) {
			map.put(dictionaryOption.getOptionId(), queryPostType(district, dictionaryOption.getOptionValue(), isLayer));
		}
		return map;
	}

	/**
	 *
	 * <p>
	 * 根据行政区划和年龄段查询人员个数
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-7-22 - 下午2:24:08
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param districtCode
	 * @param age
	 * @return
	 */
	private Integer queryOnlineSituation(District district, Integer[] age, boolean isLayer){
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder sql = new StringBuilder("select count(1) " +
				" from (select (YEAR(CURDATE())-YEAR(ui.BIRTH_DATE))-(RIGHT(CURDATE(),5)<RIGHT(ui.BIRTH_DATE,5)) as age" +
				" from bm_secrecy_office_members secrecyoff0_ left outer join bm_secrecy_office secrecyoff1_ on secrecyoff0_.SECRECY_OFFICE_ID=secrecyoff1_.SECRECY_OFFICE_ID" +
				" left outer join SYS_ORGANIZATION organ2_ on secrecyoff1_.CREATE_ORGAN=organ2_.ORGAN_ID" +
				" left outer join SYS_DISTRICT district3_ on organ2_.district_code=district3_.district_code" +
				" left outer join sys_user_info ui on ui.PERSON_ID=secrecyoff0_.PERSON_ID" +
				" where 1=1 " );
		// false ： 查看本单位； true ：查看行政区
		if (isLayer) {
			sql.append(" and district3_.layer like :layer) t");
			params.put("layer", district.getLayer() + "%");
		} else {
			sql.append(" and district3_.district_code= :districtCode) t");
			params.put("districtCode", district.getDistrictCode());
		}
		if(age.length > 1){
			sql.append(" where t.age between :startAge and :endAge");
			params.put("startAge", age[0]);
			params.put("endAge", age[1]);
		} else {
			sql.append(" where t.age >= :age");
			params.put("age", age[0]);
		}

		List<Object> list = this.getPersistProxy().getOrmPersistence().findByNativeQuery(sql.toString(), params);
		if (CollectionUtils.isNotEmpty(list)) {
			return Integer.parseInt(list.get(0).toString());
		}
		return 0;
	}
	/**
	 *
	 * <p>
	 * 根据行政区划和指定学历查询人员个数
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-7-22 - 下午2:27:04
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param districtCode
	 * @param diploma
	 * @return
	 */
	private Integer queryDiploma(District district, Integer diploma, boolean isLayer){
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder sql = new StringBuilder("select count(1) from bm_secrecy_office_members secrecyoff0_" +
				" left outer join bm_secrecy_office secrecyoff1_ on secrecyoff0_.SECRECY_OFFICE_ID=secrecyoff1_.SECRECY_OFFICE_ID" +
				" left outer join SYS_ORGANIZATION organ2_ on secrecyoff1_.CREATE_ORGAN=organ2_.ORGAN_ID" +
				" left outer join SYS_DISTRICT district3_ on organ2_.district_code=district3_.district_code" +
				" left outer join sys_user_info ui on ui.PERSON_ID=secrecyoff0_.PERSON_ID" +
				" where 1=1 and ui.diploma = :diploma");
		params.put("diploma", diploma);
		// false ： 查看本单位； true ：查看行政区
		if (isLayer) {
			sql.append(" and district3_.layer like :layer");
			params.put("layer", district.getLayer() + "%");
		} else {
			sql.append(" and district3_.district_code= :districtCode");
			params.put("districtCode", district.getDistrictCode());
		}
		List<Object> list = this.getPersistProxy().getOrmPersistence().findByNativeQuery(sql.toString(), params);
		if (CollectionUtils.isNotEmpty(list)) {
			return Integer.parseInt(list.get(0).toString());
		}
		return 0;
	}

	/**
	 *
	 * <p>
	 * 根据行政区划和指定专业查询人员个数
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-7-22 - 下午5:07:00
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param districtCode
	 * @param specialtyCode
	 * @return
	 */
	private Integer querySpecial(District district, Integer specialtyCode, boolean isLayer){
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder sql = new StringBuilder("select count(1) from bm_secrecy_office_members secrecyoff0_" +
				" left outer join bm_secrecy_office secrecyoff1_ on secrecyoff0_.SECRECY_OFFICE_ID=secrecyoff1_.SECRECY_OFFICE_ID" +
				" left outer join SYS_ORGANIZATION organ2_ on secrecyoff1_.CREATE_ORGAN=organ2_.ORGAN_ID" +
				" left outer join SYS_DISTRICT district3_ on organ2_.district_code=district3_.district_code" +
				" left outer join sys_user_info ui on ui.PERSON_ID=secrecyoff0_.PERSON_ID" +
				" where 1=1 and ui.specialty_code = :specialtyCode");
		params.put("specialtyCode", specialtyCode);
		// false ： 查看本单位； true ：查看行政区
		if (isLayer) {
			sql.append(" and district3_.layer like :layer");
			params.put("layer", district.getLayer() + "%");
		} else {
			sql.append(" and district3_.district_code= :districtCode ");
			params.put("districtCode", district.getDistrictCode());
		}
		List<Object> list = this.getPersistProxy().getOrmPersistence().findByNativeQuery(sql.toString(), params);
		if (CollectionUtils.isNotEmpty(list)) {
			return Integer.parseInt(list.get(0).toString());
		}
		return 0;
	}

	/**
	 *
	 * <p>
	 * 根据行政区划和指定岗位查询人员个数
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-7-22 - 下午5:07:21
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param districtCode
	 * @param specialtyCode
	 * @return
	 */
	private Integer queryPostType(District district, Integer postType, boolean isLayer){
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder sql = new StringBuilder("select count(1) from bm_secrecy_office_members secrecyoff0_" +
				" left outer join bm_secrecy_office secrecyoff1_ on secrecyoff0_.SECRECY_OFFICE_ID=secrecyoff1_.SECRECY_OFFICE_ID" +
				" left outer join SYS_ORGANIZATION organ2_ on secrecyoff1_.CREATE_ORGAN=organ2_.ORGAN_ID" +
				" left outer join SYS_DISTRICT district3_ on organ2_.district_code=district3_.district_code" +
				" left outer join sys_user_info ui on ui.PERSON_ID=secrecyoff0_.PERSON_ID" +
				" where 1=1 and ui.post_type = :postType");
		params.put("postType", postType);
		// false ： 查看本单位； true ：查看行政区
		if (isLayer) {
			sql.append(" and district3_.layer like :layer");
			params.put("layer", district.getLayer() + "%");
		} else {
			sql.append(" and district3_.district_code= :districtCode ");
			params.put("districtCode", district.getDistrictCode());
		}
		List<Object> list = this.getPersistProxy().getOrmPersistence().findByNativeQuery(sql.toString(), params);
		if (CollectionUtils.isNotEmpty(list)) {
			return Integer.parseInt(list.get(0).toString());
		}
		return 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, Integer> queryInfrastructure(District district, boolean isLayer) {
		Map<String, Object> params = new HashMap<String, Object>();
		List<SecrecyOffice> secrecyOfficeList = new ArrayList<SecrecyOffice>();
		// false ： 查看本单位； true ：查看行政区
		if (isLayer) {
			params.put("layer", district.getLayer() + "%");
			secrecyOfficeList = findList("from SecrecyOffice s where s.createOrgan.district.layer like :layer", params);
		} else {
			params.put("districtCode", district.getDistrictCode());
			secrecyOfficeList = findList("from SecrecyOffice s where s.createOrgan.district.districtCode = :districtCode", params);
		}

		Map<String, Integer> map = new HashMap<String, Integer>();
		List<DictionaryOption> optionList = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "infrastructure");
		for (DictionaryOption dictionaryOption : optionList) {
			int total = 0;
			for (SecrecyOffice secrecyOffice : secrecyOfficeList) {
				int count = 0;
				for (Infrastructure infrastructure : secrecyOffice.getInfrastructureSet()) {
					if(infrastructure.getInfrastructureType().equals(dictionaryOption.getOptionValue())){
						count ++;
					}
				}
				total += count;
			}
			map.put(dictionaryOption.getOptionId(), total);
		}
		return map;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Organ> getOrganListByDistrict(District district) {
		OrganHqlQuery query = OrganizationContext.getInstance().getOrganService().createQuery();
		query.eqStatus(DataStatus.USE);
		query.eqOrganType(OrganType.SECRECY_OFFICE);
		query.eqDistrict(district.getDistrictCode());
		return query.list();
	}

}