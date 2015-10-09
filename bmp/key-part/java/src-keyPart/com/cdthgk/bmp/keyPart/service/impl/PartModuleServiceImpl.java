package com.cdthgk.bmp.keyPart.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.bmp.keyPart.service.PartModuleService;
import com.cdthgk.bmp.keyPart.service.PartPersonModuleService;
import com.cdthgk.bmp.keyPart.vo.Part;
import com.cdthgk.bmp.keyPart.vo.PartPerson;
import com.cdthgk.bmp.keysection.vo.KeySection;
import com.cdthgk.common.bean.BeanUtils;
import com.cdthgk.common.lang.CollectionUtils;
import com.cdthgk.common.lang.LangUtils;
import com.cdthgk.component.ioc.ContextUtils;
import com.cdthgk.platform.dictionary.domain.DictionaryOption;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.core.OrganizationContext;
import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.department.service.DepartmentService;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;
import com.cdthgk.platform.permission.user.domain.User;
import com.cdthgk.standard.asserts.AssertStandardApp;

import ec.common.PageSortModel;

/**
 * <p>
 * PartModuleServiceImpl
 * 要害部位内部实现类
 * </p>
 * 刘椿成 2012-12-15 13:26:59
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
 * @author 刘椿成
 * @since 1.0
 * @version 1.0
 */
public class PartModuleServiceImpl extends BmpServiceImpl<Part, String> implements PartModuleService {

	private PartModuleService partModuleService;
	private DepartmentService departmentService;

	PartModuleServiceImpl() {
	}

	private static final Logger LOGGER = LoggerFactory
			.getLogger(PartModuleServiceImpl.class);

	/**
	 * 得到要害部位列表
	 * 刘椿成 2012-12-15 13:26:59
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
	 * @author 刘椿成
	 * @since 1.0
	 * @version 1.0
	 */
	@Override
	public List<Part> getListPage(PageSortModel<Part> psm,Part part, Organ organ) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("FROM Part pt WHERE 1 = 1 and pt.secrecyStatus!=1 ");
		if(organ!=null){
			hql.append(" and pt.organ.organId = :organId ");
			params.put("organId", organ.getOrganId());
		}

		// 按部位名称搜索
		if(part !=null && part.getPartName() !=null
			&& !"".equals(part.getPartName())){
			hql.append(" and pt.partName like :partName");
			params.put("partName", "%"+part.getPartName()+"%");
		}
		// 按涉密程度搜索
		if(part !=null && part.getSecrecyLevel() !=null && part.getSecrecyLevel()!= 4){
			hql.append(" and pt.secrecyLevel = :secrecyLevel");
			params.put("secrecyLevel", part.getSecrecyLevel());
		}
		// 按联系电话搜索
		if(part !=null && part.getPhone() !=null && !"".equals(part.getPhone())){
			hql.append(" and pt.phone like :phone");
			params.put("phone", "%"+part.getPhone()+"%");
		}
		// 按责任人搜索
		if(part !=null && part.getPerson() !=null
			&& !"".equals(part.getPerson())){
			hql.append(" and pt.person.name like :name");
			params.put("name", "%"+part.getPerson().getName()+"%");
		}
		if (part != null && part.getDepartment() != null && LangUtils.isNotEmpty(part.getDepartment().getDepartmentId())) {
			hql.append(" and pt.department.departmentId = :departmentId");
			params.put("departmentId", part.getDepartment().getDepartmentId());
		}

		// 按创建时间降序排列
		hql.append(" order by pt.createTime desc");
		List<Part> list = null;
		if(psm!=null) {
			list =(List<Part>) this.findList(hql.toString(), params,psm);
		}else {
			list =(List<Part>) this.findList(hql.toString(), params);
		}
		return list;
	}


	/**
	 * 行政区划   查询要害部位 返回list
	 * @param psm
	 * @param part
	 * @param district    行政区划对象
	 * @param isChildren  是否查询下级
	 * @return
	 */
	public List<Part> getListPage(PageSortModel<Part> psm,Part part, District district, int isChildren) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("FROM Part pt WHERE 1 = 1 and pt.secrecyStatus!=1 ");
		if(district!=null && district.getLayer()!=null){
			if(isChildren==1) {
				hql.append(" and pt.organ.district.layer like :layer");
				params.put("layer", district.getLayer()+"%");
			}else {
				hql.append(" and pt.organ.district.layer = :layer");
				params.put("layer", district.getLayer());
			}
		}

		// 按部位名称搜索
		if(part !=null && part.getPartName() !=null
			&& !"".equals(part.getPartName())){
			hql.append(" and pt.partName like :partName");
			params.put("partName", "%"+part.getPartName()+"%");
		}
		// 按涉密程度搜索
		if(part !=null && part.getSecrecyLevel() !=null && part.getSecrecyLevel()!= 4){
			hql.append(" and pt.secrecyLevel = :secrecyLevel");
			params.put("secrecyLevel", part.getSecrecyLevel());
		}
		// 按联系电话搜索
		if(part !=null && part.getPhone() !=null && !"".equals(part.getPhone())){
			hql.append(" and pt.phone like :phone");
			params.put("phone", "%"+part.getPhone()+"%");
		}
		// 按责任人搜索
		if(part !=null && part.getPerson() !=null
			&& !"".equals(part.getPerson().getName())){
			hql.append(" and pt.person.name like :name");
			params.put("name", "%"+part.getPerson().getName()+"%");
		}
		if (part != null && part.getDepartment() != null && LangUtils.isNotEmpty(part.getDepartment().getDepartmentId())) {
			hql.append(" and pt.department.departmentId = :departmentId");
			params.put("departmentId", part.getDepartment().getDepartmentId());
		}

		// 按创建时间降序排列
		hql.append(" order by pt.createTime desc");
		List<Part> list = null;
		if(psm!=null) {
			list =(List<Part>) this.findList(hql.toString(), params,psm);
		}else {
			list =(List<Part>) this.findList(hql.toString(), params);
		}
		return list;
	}






	/**
	 * 获取字典表信息
	 */
	public List<DictionaryOption> getDictionaryOptionList(String tableCode, String fieldCode){
		Map<String ,Object> params = new HashMap<String, Object>();
		String hql = "SELECT dop FROM DictionaryTable dt, DictionaryField df, DictionaryOption dop WHERE df.dictionaryTable.tableId = dt.tableId AND dop.dictionaryField.fieldId=df.fieldId AND" +
					" dt.tableCode=:tableCode AND df.fieldCode=:fieldCode";
		params.put("tableCode", tableCode);
		params.put("fieldCode", fieldCode);
		List<DictionaryOption> dictionaryOptionList = this.getPersistProxy().getOrmPersistence().findList(hql, params);
		return dictionaryOptionList;
	}

	public List<Part> getAllList(Part part, Organ organ) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("FROM Part pt WHERE 1 = 1 and pt.secrecyStatus!=1 ");
		if(organ!=null){
			hql.append(" and pt.organ.organId = :organId ");
			params.put("organId", organ.getOrganId());
		}
		// 按部位名称搜索
		if(part !=null && part.getPartName() !=null
				&& !"".equals(part.getPartName())){
			hql.append(" and pt.partName like :partName");
			params.put("partName", "%"+part.getPartName()+"%");
		}
		// 按涉密程度搜索
		if(part !=null && part.getSecrecyLevel() !=null && part.getSecrecyLevel()!= 4){
			hql.append(" and pt.secrecyLevel = :secrecyLevel");
			params.put("secrecyLevel", part.getSecrecyLevel());
		}
		// 按联系电话搜索
		if(part !=null && part.getPhone() !=null && !"".equals(part.getPhone())){
			hql.append(" and pt.phone like :phone");
			params.put("phone", "%"+part.getPhone()+"%");
		}
		// 按责任人搜索
		if(part !=null && part.getPerson() !=null
				&& !"".equals(part.getPerson())){
			hql.append(" and pt.person.name like :name");
			params.put("name", "%"+part.getPerson().getName()+"%");
		}
		if (part != null && part.getDepartment() != null && LangUtils.isNotEmpty(part.getDepartment().getDepartmentId())) {
			hql.append(" and pt.department.departmentId = :departmentId");
			params.put("departmentId", part.getDepartment().getDepartmentId());
		}

		// 按创建时间降序排列
		hql.append(" order by pt.createTime desc");
		List<Part> list =(List<Part>) this.findList(hql.toString(), params);
		return list;
	}

	/**
	 * 得到要害部位列表(首页获取关键部位列表)
	 * 宋亚非  2013-04-10 09：06
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
	 * @author 刘椿成
	 * @since 1.0
	 * @version 1.0
	 */

	@Override
	public List<Part> getListPage(PageSortModel psm, Part part, Organ organ,
			String showType) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("FROM Part pt WHERE 1 = 1 and pt.secrecyStatus!=1 ");
		if(organ!=null){
			organ = get(organ.getOrganId(), Organ.class);
			String layer = organ.getDistrict().getLayer();
			if( showType!=null ){
				if( "1".equals(showType) ){
					hql.append(" and pt.organ.district.layer like :layer");
					params.put("layer", layer + "%");
				}else{
					hql.append(" and pt.organ.organId = :organId ");
					params.put("organId", organ.getOrganId());
				}
			}else{
				hql.append(" and pt.organ.organId = :organId ");
				params.put("organId", organ.getOrganId());
//				hql.append(" and pt.organ.layer like :layer");
//				params.put("layer", layer + "%");
			}
		}
		// 按部位名称搜索
		if(part !=null && part.getPartName() !=null
			&& !"".equals(part.getPartName())){
			hql.append(" and pt.partName like :partName");
			params.put("partName", "%"+part.getPartName()+"%");
		}
		// 按涉密程度搜索
		if(part !=null && part.getSecrecyLevel() !=null && part.getSecrecyLevel()!= 4){
			hql.append(" and pt.secrecyLevel = :secrecyLevel");
			params.put("secrecyLevel", part.getSecrecyLevel());
		}
		// 按联系电话搜索
		if(part !=null && part.getPhone() !=null && !"".equals(part.getPhone())){
			hql.append(" and pt.phone like :phone");
			params.put("phone", "%"+part.getPhone()+"%");
		}
		// 按责任人搜索
		if(part !=null && part.getPerson() !=null
			&& !"".equals(part.getPerson())){
			hql.append(" and pt.person.name like :name");
			params.put("name", "%"+part.getPerson().getName()+"%");
		}
		if (part != null && part.getDepartment() != null && LangUtils.isNotEmpty(part.getDepartment().getDepartmentId())) {
			hql.append(" and pt.department.departmentId = :departmentId");
			params.put("departmentId", part.getDepartment().getDepartmentId());
		}

		// 按创建时间降序排列
		hql.append(" order by pt.createTime desc");
		List<Part> list =(List<Part>) this.findList(hql.toString(), params,psm);
		return list;
	}


	/**
	 * 根据填写责任人的姓名新增责任人
	 * 刘椿成 2012-12-15 13:26:59
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
	 * @author 刘椿成
	 * @since 1.0
	 * @version 1.0
	 */
	@Override
	public UserInfo saveUserInfo(String name, User user) {
		//如果单位下的人员存在，直接返回该人员
		List<UserInfo> userInfoList = (List<UserInfo>) OrganizationContext.getInstance().getMemberService()
				.getByName(name, user.getOrgan());
		if (CollectionUtils.isNotEmpty(userInfoList)) {
			return userInfoList.get(0);
		}
		Date d = new Date();
		UserInfo userInfo = new UserInfo();
		userInfo.setName(name);
		userInfo.setOrgan(user.getOrgan());
		userInfo.setDepartment(user.getOrgan().getDepartment());
		userInfo.setCreateTime(d);
		userInfo.setCreatePerson(user);
		userInfo.setModifyPerson(user);
		//this.getPersistProxy().getOrmPersistence().save(userInfo);
		userInfo.save();
		return userInfo;
	}

	/**
	 * 根据填写部门的名称新增部门
	 * 刘椿成 2012-12-15 13:26:59
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
	 * @author 刘椿成
	 * @since 1.0
	 * @version 1.0
	 */
	@Override
	public Department saveDepartment(String departmentName, User user) {
		Department deptDb = OrganizationContext.getInstance().getDepartmentService().getByName(departmentName, user.getOrgan());
		if (deptDb != null) {
			return deptDb;
		}
		Department department = new Department();
		Date d = new Date();
		department.setDepartmentName(departmentName);
		department.setOrgan(user.getOrgan());
		department.setCreateTime(d);
		department.setCreatePerson(user);
		department.setModifyPerson(user);
		//department.setStatus(1);
		department.setParent(user.getOrgan().getDepartment());
		department.save();
		return department;
	}

	/**
	 * 通过要害部位ID得到要害部门
	 * 刘椿成 2012-12-15 13:26:59
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
	 * @author 刘椿成
	 * @since 1.0
	 * @version 1.0
	 */
	@Override
	public Department getDepartmentByPartId(String partId) {
		String sql = "SELECT DEPARTMENT_ID FROM key_department_part where key_part_id= ?";
		List<Department> departmentList = this.getPersistProxy().getOrmPersistence().
			findByNativeQuery(sql,new Object[]{partId});
		if (departmentList != null && departmentList.size() > 0) {
			return departmentList.get(0);
		}
		return null;
	}

	/**
	 * 得到指定行政区划下所有单位
	 * 刘椿成 2012-12-15 13:26:59
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
	 * @author 刘椿成
	 * @since 1.0
	 * @version 1.0
	 */
	@Override
	public List<Part> getAllOrganList(PageSortModel psm,District district, Organ organ,  Part part)
			throws ParseException {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("From Organ o where 1=1 ");
		//包含子机构
		District d = get(district.getDistrictCode(), District.class);
		hql.append(" AND o.district.layer LIKE :layer ");
		params.put("layer", d.getLayer() + "%");
		if (organ != null) {
			if (organ.getOrganName() != null && !"".equals(organ.getOrganName())) {
				hql.append(" AND o.organName like :organName");
				params.put("organName", "%" + organ.getOrganName() + "%");
			}
			if (organ.getOrganId() != null && !"".equals(organ.getOrganId())) {
				hql.append(" AND o.organId =:organId");
				params.put("organId", organ.getOrganId());
			}
		}
		return (List<Part>) this.findList(hql.toString(),  params,psm);
	}

	/**
	 * 得到单位下要害部位数据
	 * 刘椿成 2012-12-15 13:26:59
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
	 * @author 刘椿成
	 * @since 1.0
	 * @version 1.0
	 */
	public List<Part> getOrganPartList(PageSortModel psm,Part part, Organ organ,String districtCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("FROM Part pt WHERE 1 = 1");
		if(organ!=null){
			hql.append(" and pt.organ.organId = :organId ");
			hql.append(" and pt.organ.district.districtCode = :districtCode");
			params.put("organId", organ.getOrganId());
		}
		// 按部位名称搜索
		if(part !=null && part.getPartName() !=null
			&& !"".equals(part.getPartName())){
			hql.append(" and pt.partName like :partName");
			params.put("partName", "%"+part.getPartName()+"%");
		}
		// 按涉密程度搜索
		if(part !=null && part.getSecrecyLevel() !=null && part.getSecrecyLevel()!= 4){
			hql.append(" and pt.secrecyLevel = :secrecyLevel");
			params.put("secrecyLevel", part.getSecrecyLevel());
		}
		// 按联系电话搜索
		if(part !=null && part.getPhone() !=null){
			hql.append(" and pt.phone like :phone");
			params.put("phone", "%"+part.getPhone()+"%");
		}
		// 按责任人搜索
		if(part !=null && part.getPerson() !=null
			&& !"".equals(part.getPerson())){
			hql.append(" and pt.person.name like :name");
			params.put("name", "%"+part.getPerson().getName()+"%");
		}
		params.put("districtCode", districtCode);
		hql.append(" order by pt.createTime desc");
		List<Part> list =(List<Part>) this.findList(hql.toString(), params,psm);
		return list;
	}

	/**
	 * 保存要害部位：部门，责任人
	 * 刘椿成 2012-12-15 13:26:59
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
	 * @author 刘椿成
	 * @since 1.0
	 * @version 1.0
	 */
	@Override
	public Part saveSystemDataByPart(Part part,User currentUser, String deptFlag) {

		AssertStandardApp.isNotNull(part.getPerson(), "输入的人员不能为空");
		AssertStandardApp.isNotNull(part.getDepartment().getDepartmentName(), "输入的部门不能为空");

		//当前单位下输入的部门如果有则保存，如果没有则通过输入的部门名称新增部门。
		Department department = new Department();
		if (LangUtils.isEmpty(part.getDepartment().getDepartmentId())) {
			//部门变更或者部门改名的标志：1：部门改名；2部门变更
			if(deptFlag != null && "1".equals(deptFlag)) {
				Department existDepartment  = this.get(part.getOldDeptId(), Department.class);
				existDepartment.setDepartmentName(part.getDepartment()
					.getDepartmentName());
				department = existDepartment;
				department.update();
			}else {
				//变更时，如果存在该部门，则不理，把关系建立起。
				Department existDepartment = OrganizationContext.getInstance().getDepartmentService()
					.getByName(part.getDepartment()
					.getDepartmentName(), currentUser.getOrgan());
				if(existDepartment == null){
					//当前单位不存在该部门，新增系统部门
					department.setDepartmentName(part.getDepartment()
						.getDepartmentName());
					department.setOrgan(currentUser.getOrgan());
					department.setParent(currentUser.getOrgan().getDepartment());
					department.setCreatePerson(currentUser);
					department.setModifyPerson(currentUser);
					department.save();
				} else {
					department = existDepartment;
				}
			}
		}else {
			// 部门已经存在
			department = this.get(part.getDepartment().getDepartmentId(), Department.class);
		}

		//当前单位下输入的人员如果有则保存，如果没有则通过输入的名字新增人员。
		//姓 名
		UserInfo userInfo = new UserInfo();
		if (LangUtils.isEmpty(part.getPerson().getUserInfoId())) {
			//FIXME 先检查是否存在该人员(注意这里返回的是集合需要修改)
			List<UserInfo> userInfoList = (List<UserInfo>) OrganizationContext.getInstance().getMemberService()
					.getByName(part.getPerson().getName());
			if(userInfoList != null && userInfoList.size() > 0) {
				userInfo = userInfoList.get(0);
				userInfo.setDuty(part.getPerson().getDuty());
			}else {
				//当前单位不存在该人员，新增系统人员
				userInfo.setDuty(part.getPerson().getDuty());
				userInfo.setName(part.getPerson().getName());
				userInfo.setOrgan(currentUser.getOrgan());
				userInfo.setDepartment(currentUser.getOrgan().getDepartment());
				userInfo.setCreatePerson(currentUser);
				userInfo.setModifyPerson(currentUser);
				userInfo.save();
			}
		}else {
			userInfo = this.get(part.getPerson().getUserInfoId(), UserInfo.class);
			// userInfo.setDuty(part.getPerson().getDuty());
		}
		userInfo.setDepartment(department);
		part.setPerson(userInfo);
		part.setDepartment(department);
		return part;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void saveRecKeyPart(List<Part> partList, String receiveOrganId) {
		if (partList == null) {
			return;
		}
		PartPersonModuleService partPersonModuleService = ContextUtils.getDefaultContext().getBean("partPersonModuleService");
		for (Part part : partList) {
			List<PartPerson> PartPersonList = new ArrayList<PartPerson>(part.getPartPersons());
			Part p = this.get(part.getPartId());
			if (null != p) { // null != eno 说明已经保存过，现在只需更新
				try {
					BeanUtils.copyProperties(p, part);
				} catch(Exception e) {
					LOGGER.debug("属性转换异常:" + e.getMessage());
				}
				this.update(p);
			} else { // 第一次保存
				this.save(part);
			}

			for (PartPerson partPerson : PartPersonList) {
				PartPerson partPersonDb = partPersonModuleService.get(partPerson.getId());
				System.out.println(partPerson.getSecrecyPerson().getUserInfo().getName());
				if(partPersonDb == null){
					this.getPersistProxy().getOrmPersistence().flush();
					partPersonModuleService.save(partPerson);
				}
			}
		}
	}

	@Override
	public List<Part> getIndexPage(PageSortModel psm,
			Organ organ,Integer secrecyLevel,String districtCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder(
				"FROM Part p where 1=1 and p.secrecyStatus!=1");
		if(organ !=null){
			hql.append("and p.organ.organId = :organId ");
			params.put("organId", organ.getOrganId());
		}
		hql.append("and p.secrecyLevel = :secrecyLevel ");
		params.put("secrecyLevel", secrecyLevel);
		hql.append(" and p.organ.district.districtCode = :districtCode");
		params.put("districtCode", districtCode);
		return (List<Part>) findList(hql.toString(),params, psm);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Part> getListBySection(User currentUser, KeySection section) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("organId", currentUser.getOrgan().getOrganId());
		params.put("departmentId", section.getDepartment().getDepartmentId());
		return findList("FROM Part pt WHERE pt.organ.organId = :organId and pt.department.departmentId = :departmentId ", params);
	}


	// ******************** Setter & Getter ********************
	/**
	 * 返回partModuleService
	 * @return partModuleService
	 */
	public PartModuleService getPartModuleService() {
		return partModuleService;
	}

	/**
	 * 设置partModuleService
	 * @param partModuleService partModuleService
	 */
	public void setPartModuleService(PartModuleService partModuleService) {
		this.partModuleService = partModuleService;
	}

	/**
	 * 返回departmentService
	 * @return departmentService
	 */
	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	/**
	 * 设置departmentService
	 * @param departmentService departmentService
	 */
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

}