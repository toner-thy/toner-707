package com.cdthgk.bmp.secrecycountryitem.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.bmp.keysection.vo.KeySection;
import com.cdthgk.bmp.secrecycountryitem.qo.CountrySecrecyQueryObject;
import com.cdthgk.bmp.secrecycountryitem.qo.ZongHeTongJiStatDto;
import com.cdthgk.bmp.secrecycountryitem.qo.ZongHeTongJiUtil;
import com.cdthgk.bmp.secrecycountryitem.service.SecrecyCountryItemService;
import com.cdthgk.bmp.secrecycountryitem.vo.SecrecyCountryItem;
import com.cdthgk.bmp.secrecyperson.vo.SecrecyPerson;
import com.cdthgk.code.enums.DataStatus;
import com.cdthgk.code.enums.Nation;
import com.cdthgk.common.lang.CollectionUtils;
import com.cdthgk.common.lang.LangUtils;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.core.OrganizationContext;
import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

/**
 * 国家秘密事项  实现类
 * @author lwj 2013-07-15
 *
 */
public class SecrecyCountryItemServiceImpl extends
		BmpServiceImpl<SecrecyCountryItem, Serializable> implements
		SecrecyCountryItemService {

	/**
	 * 记录日志
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(SecrecyCountryItemServiceImpl.class);

	/**
	 * 构造函数
	 */
	public SecrecyCountryItemServiceImpl(){

	}

	/**
	 * 查询  国家秘密事项
	 * 梁文杰 2013-07-16
	 *
	 * @param psm   分页对象
	 * @param organ   单位对象
	 * @param secrecyCountryItem  国家秘密事项对象
	 * @return
	 */
	public List<SecrecyCountryItem> queryCountryItemList(PageSortModel psm, Organ organ, SecrecyCountryItem secrecyCountryItem){

		List<SecrecyCountryItem> list = new ArrayList<SecrecyCountryItem>();//返回对象

		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("From SecrecyCountryItem sci where sci.secrecyStatus!=1 ");

		//国家秘密事项的查询对象不为空
		if(secrecyCountryItem!=null) {
			//国家秘密事项名称
			if(secrecyCountryItem.getSecrecyCountryItemName()!= null && !"".equals(secrecyCountryItem.getSecrecyCountryItemName())) {
				hql.append(" and sci.secrecyCountryItemName like :secrecyCountryItemName");
				params.put("secrecyCountryItemName", "%" + secrecyCountryItem.getSecrecyCountryItemName() + "%");
			}
			//涉密等级
			if(secrecyCountryItem.getSecrecyLevel()!= null && !"".equals(secrecyCountryItem.getSecrecyLevel())) {
				hql.append(" and sci.secrecyLevel = :secrecyLevel");
				params.put("secrecyLevel", secrecyCountryItem.getSecrecyLevel());
			}
			//定密负责人
			if(secrecyCountryItem.getFormulateSecrecyPerson()!= null && secrecyCountryItem.getFormulateSecrecyPerson().getName()!=null
					&& !"".equals(secrecyCountryItem.getFormulateSecrecyPerson().getName())) {
				hql.append(" and sci.formulateSecrecyPerson.name like :formulateSecrecyPerson");
				params.put("formulateSecrecyPerson", "%"+secrecyCountryItem.getFormulateSecrecyPerson().getName()+"%");
			}
			//是否保密要害部门
			if(secrecyCountryItem.getIsfromKeyDepartment()!= null && !"".equals(secrecyCountryItem.getIsfromKeyDepartment())) {
				hql.append(" and sci.isfromKeyDepartment = :isfromKeyDepartment");
				params.put("isfromKeyDepartment", secrecyCountryItem.getIsfromKeyDepartment());
			}
			//部门名称
			if(secrecyCountryItem.getDepartId()!= null && secrecyCountryItem.getDepartId().getDepartmentName()!=null
					&& !"".equals(secrecyCountryItem.getDepartId().getDepartmentName())) {
				hql.append(" and sci.departId.departmentName like :departId");
				params.put("departId", "%"+secrecyCountryItem.getDepartId().getDepartmentName()+"%");
			}
		}

		//单位不为空
		if(organ!=null && organ.getOrganId()!=null) {
			hql.append(" and sci.createOrgan.organId = :organ");
			params.put("organ", organ.getOrganId());
		}

		//创建时间倒叙
		hql.append(" order by sci.createTime desc ");
		if(psm!=null) {
			list = (List<SecrecyCountryItem>) this.findList(hql.toString(), params, psm);//分页
		}else {
			list = (List<SecrecyCountryItem>) this.findList(hql.toString(), params);//不分页
		}

		return list;
	}


	/**
	 * 查询  国家秘密事项   行政区划 下的数据
	 * 梁文杰 2013-07-16
	 *
	 * @param psm   分页对象
	 * @param district   行政区对象
	 * @param isChildren 包含下级  1包含  0不包含
	 * @param secrecyCountryItem  国家秘密事项对象
	 * @return
	 */
	public List<SecrecyCountryItem> queryCountryItemList(PageSortModel psm, District district, int isChildren,SecrecyCountryItem secrecyCountryItem){

		List<SecrecyCountryItem> list = new ArrayList<SecrecyCountryItem>();//返回对象

		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("From SecrecyCountryItem sci where sci.secrecyStatus!=1 ");

		//国家秘密事项的查询对象不为空
		if(secrecyCountryItem!=null) {
			//国家秘密事项名称
			if(secrecyCountryItem.getSecrecyCountryItemName()!= null && !"".equals(secrecyCountryItem.getSecrecyCountryItemName())) {
				hql.append(" and sci.secrecyCountryItemName like :secrecyCountryItemName");
				params.put("secrecyCountryItemName", "%" + secrecyCountryItem.getSecrecyCountryItemName() + "%");
			}
			//涉密等级
			if(secrecyCountryItem.getSecrecyLevel()!= null && !"".equals(secrecyCountryItem.getSecrecyLevel())) {
				hql.append(" and sci.secrecyLevel = :secrecyLevel");
				params.put("secrecyLevel", secrecyCountryItem.getSecrecyLevel());
			}
			//定密负责人
			if(secrecyCountryItem.getFormulateSecrecyPerson()!= null && secrecyCountryItem.getFormulateSecrecyPerson().getName()!=null
					&& !"".equals(secrecyCountryItem.getFormulateSecrecyPerson().getName())) {
				hql.append(" and sci.formulateSecrecyPerson.name like :formulateSecrecyPerson");
				params.put("formulateSecrecyPerson", "%"+secrecyCountryItem.getFormulateSecrecyPerson().getName()+"%");
			}
			//是否保密要害部门
			if(secrecyCountryItem.getIsfromKeyDepartment()!= null && !"".equals(secrecyCountryItem.getIsfromKeyDepartment())) {
				hql.append(" and sci.isfromKeyDepartment = :isfromKeyDepartment");
				params.put("isfromKeyDepartment", secrecyCountryItem.getIsfromKeyDepartment());
			}
			//部门名称
			if(secrecyCountryItem.getDepartId()!= null && secrecyCountryItem.getDepartId().getDepartmentName()!=null
					&& !"".equals(secrecyCountryItem.getDepartId().getDepartmentName())) {
				hql.append(" and sci.departId.departmentName like :departId");
				params.put("departId", "%"+secrecyCountryItem.getDepartId().getDepartmentName()+"%");
			}
		}


		//行政区划不为空
		if(district!=null && district.getLayer()!=null) {
			if(isChildren==1){
				hql.append(" and sci.createOrgan.district.layer like :layer ");
				params.put("layer", district.getLayer()+"%");
			}else{
				hql.append(" and sci.createOrgan.district.layer = :layer ");
				params.put("layer", district.getLayer());
			}
		}


		//创建时间倒叙
		hql.append(" order by sci.createTime desc ");
		if(psm!=null) {
			list = (List<SecrecyCountryItem>) this.findList(hql.toString(), params, psm);//分页
		}else {
			list = (List<SecrecyCountryItem>) this.findList(hql.toString(), params);//不分页
		}

		return list;
	}


	/**
	 * 查询  国家秘密事项   本单位下的查询  但是没有单位对象，只有行政区划的对象
	 * 梁文杰 2013-07-16
	 *
	 * @param psm   分页对象
	 * @param district   行政区对象
	 * @param secrecyCountryItem  国家秘密事项对象
	 * @return
	 */
	public List<SecrecyCountryItem> queryCountryItemList(PageSortModel psm, District district, SecrecyCountryItem secrecyCountryItem){

		List<SecrecyCountryItem> list = new ArrayList<SecrecyCountryItem>();//返回对象

		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("From SecrecyCountryItem sci where sci.secrecyStatus!=1 ");

		//国家秘密事项的查询对象不为空
		if(secrecyCountryItem!=null) {
			//国家秘密事项名称
			if(secrecyCountryItem.getSecrecyCountryItemName()!= null && !"".equals(secrecyCountryItem.getSecrecyCountryItemName())) {
				hql.append(" and sci.secrecyCountryItemName like :secrecyCountryItemName");
				params.put("secrecyCountryItemName", "%" + secrecyCountryItem.getSecrecyCountryItemName() + "%");
			}
			//涉密等级
			if(secrecyCountryItem.getSecrecyLevel()!= null && !"".equals(secrecyCountryItem.getSecrecyLevel())) {
				hql.append(" and sci.secrecyLevel = :secrecyLevel");
				params.put("secrecyLevel", secrecyCountryItem.getSecrecyLevel());
			}
			//定密负责人
			if(secrecyCountryItem.getFormulateSecrecyPerson()!= null && secrecyCountryItem.getFormulateSecrecyPerson().getName()!=null
					&& !"".equals(secrecyCountryItem.getFormulateSecrecyPerson().getName())) {
				hql.append(" and sci.formulateSecrecyPerson.name like :formulateSecrecyPerson");
				params.put("formulateSecrecyPerson", "%"+secrecyCountryItem.getFormulateSecrecyPerson().getName()+"%");
			}
			//是否保密要害部门
			if(secrecyCountryItem.getIsfromKeyDepartment()!= null && !"".equals(secrecyCountryItem.getIsfromKeyDepartment())) {
				hql.append(" and sci.isfromKeyDepartment = :isfromKeyDepartment");
				params.put("isfromKeyDepartment", secrecyCountryItem.getIsfromKeyDepartment());
			}
			//部门名称
			if(secrecyCountryItem.getDepartId()!= null && secrecyCountryItem.getDepartId().getDepartmentName()!=null
					&& !"".equals(secrecyCountryItem.getDepartId().getDepartmentName())) {
				hql.append(" and sci.departId.departmentName like :departId");
				params.put("departId", "%"+secrecyCountryItem.getDepartId().getDepartmentName()+"%");
			}
		}

		//行政区划不为空
		if(district!=null && district.getLayer()!=null) {
			hql.append(" and sci.createOrgan.district.districtCode = :districtCode ");
			params.put("districtCode", district.getDistrictCode());
		}

		//创建时间倒叙
		hql.append(" order by sci.createTime desc ");
		if(psm!=null) {
			list = (List<SecrecyCountryItem>) this.findList(hql.toString(), params, psm);//分页
		}else {
			list = (List<SecrecyCountryItem>) this.findList(hql.toString(), params);//不分页
		}

		return list;
	}


	/**
	 * 查询  国家秘密事项  相关联的表 有哪些
	 * 1  变更表
	 * 2  解密表
	 * @param secrecyCountryItemId    国家秘密事项id
	 * @return
	 */
	public Integer getRelationshipForTable(String secrecyCountryItemId) {

		Integer iValue = new Integer(0);//返回值

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (secrecyCountryItemId == null || secrecyCountryItemId.equals("")) {
			return iValue;//如果id为空
		}

		StringBuffer sb = new StringBuffer();
		Map<String ,Object> params = new HashMap<String, Object>();

		//1变更记录表
		sb = new StringBuffer();
		params = new HashMap<String, Object>();
		sb.append("select p.secrecy_change_id from bm_secrecy_countryitem_change p where p.secrecycountryitem_id = :secrecyCountryItemId ");
		params.put("secrecyCountryItemId", secrecyCountryItemId);
		list = this.getPersistProxy().getJdbcPersistence().findList(sb.toString(), params);
		if(list!=null && list.size()>0) {
			return new Integer(1);
		}

		//2解密记录表
		sb = new StringBuffer();
		params = new HashMap<String, Object>();
		sb.append("select p.secrecy_Contry_clear_id from bm_secrecy_countryitem_clear p where p.secrecycountryitem_id = :secrecyCountryItemId ");
		params.put("secrecyCountryItemId", secrecyCountryItemId);
		list = this.getPersistProxy().getJdbcPersistence().findList(sb.toString(), params);
		if(list!=null && list.size()>0) {
			return new Integer(2);
		}

		return iValue;
	}

	/**
	 * 通过部门名称 直接保存部门,
	 * @param departName  保存部门
	 * @param u  人员
	 * @return
	 */
	public Department saveDepartmentByName(String departName, User u) {
		//查询是否存在该部门,如果存在,就直接返回了
		Department deptDb = OrganizationContext.getInstance().getDepartmentService().getByName(departName, u.getOrgan());
		if (deptDb != null) {
			return deptDb;
		}
		Department department = new Department();
		//设置需要保存的属性
		department.setDepartmentName(departName);//部门的名称
		department.setCreatePerson(u);//创建人
		department.setModifyPerson(u);//修改人
		department.setCreateTime(new Date());//创建时间
		department.setModifyTime(new Date());//修改时间
		department.setOrgan(u.getOrgan());//创建单位
		department.setParent(u.getOrgan().getDepartment());//父部门
		department.setStatus(DataStatus.USE);//启用状态
		department.save();

		return department;
	}

	/**
	 * 通过部门对象  和人   直接新增一个保密要害部门
	 * @param department  部门对象
	 * @param u   人对象
	 * @return
	 */
	public KeySection saveKeySection(Department department, User u){

		KeySection keySection = new KeySection();

		//查询数据库中是否存在  该保密要害部门
		StringBuffer sb = new StringBuffer("select key_Section_id from bm_key_section where department_id= '"+department.getDepartmentId()+"'");
		List<Map<String, Object>> list = this.getPersistProxy().getJdbcPersistence().findList(sb.toString(), new HashMap<String,Object>());

		//如果存在就直接返回
		if(list!=null && list.size()>0){
			Object o = list.get(0).get("key_Section_id");
			if(o!=null) {
				String key_Section_id = o.toString();
				keySection.setKeySectionId(key_Section_id);
				return keySection;
			}
		}

		//不存在  就新增保密要害部门
		keySection.setDepartment(department);//设置部门
		keySection.setCreatePerson(u);//设置创建人
		keySection.setCreateTime(new Date());//设置创建时间
		keySection.setOrgan(u.getOrgan());//设置单位
		keySection.setStatus(1);//数据状态
		keySection.setSecrecyStatus(0);//密级解除标志
		keySection.setSecrecyLevel(3);//设置密级为一般
		//备注信息
		keySection.setRemark(u.getUserInfo().getName()+"在(国家秘密->国家秘密事项)中新增");
		this.getPersistProxy().getOrmPersistence().save(keySection);

		return keySection;
	}

	/**
	 * 直接保存定密负责人人员信息
	 * @param userInfo  需要保存的人员对象  必须有名字和单位信息
	 * @param user  登录的用户
	 * @return
	 */
	public UserInfo saveSecrecyPerson(UserInfo userInfo, User user) {

		//先看人员 是否在系统中存在
		//1.如果单位下的人员存在，直接将该人员设置为涉密人员
		//2.不存在先保存
		List<UserInfo> userInfoList = (List<UserInfo>) OrganizationContext.getInstance().getMemberService().getByName(userInfo.getName(), user.getOrgan());

		if (CollectionUtils.isNotEmpty(userInfoList)) {//如果存在该系统人员
			userInfo =  userInfoList.get(0);
		}else {//系统不存在该人员  ，那么先新增该人员
			Date d = new Date();
			userInfo.setOrgan(user.getOrgan());
			userInfo.setDepartment(user.getOrgan().getDepartment());
			userInfo.setCreateTime(d);
			userInfo.setCreatePerson(user);
			userInfo.setModifyPerson(user);
			userInfo.setModifyTime(d);
			//设置默认值
			userInfo.setSex("1");//男
			userInfo.setLearningLevel(7);//本科
			userInfo.setPolity("5");//中共党员
			userInfo.setNation(Nation.HAN);//汉族
			userInfo.setAdministrativeLevel(99);//其他
			userInfo.setTechnicTitleLevel(4);//其他
			if (LangUtils.isEmpty(userInfo.getUserInfoId())) {
				userInfo.setUserInfoId(null);
			}
			userInfo.setRemark(user.getUserInfo().getName()+"在(国家秘密->国家秘密事项)中新增");
			userInfo.save();
		}

		//系统人员处理完毕以后,设置涉密人员
		SecrecyPerson sp = new SecrecyPerson();
		sp.setUserInfo(userInfo);//人员信息
		sp.setCreatePerson(user);//创建人
		sp.setCreateTime(new Date());//创建时间
		sp.setOrgan(user.getOrgan());//创建单位
		sp.setResponsiblePerson(1);//是否是定密负责人
		sp.setSecrecyPersonLevel(3);//设置为密级为一般
		sp.setPersonType(1);//在编
		this.getPersistProxy().getOrmPersistence().save(sp);

		//最后还是只需要返回人员的信息  就可以了
		return userInfo;
	}


	/*********************************************************************本单位统计***********start***********************************************************************/
	/**(本单位)  按照单位显示
	 *  统计本单位 国家秘密的情况
	 *  按照单位显示
	 * @param organ  单位
	 * @param obj    查询对象
	 * @return
	 */
	public List<Map<String,Object>> getOrganCountrySecrecy(Organ organ, CountrySecrecyQueryObject obj){

		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		if(organ==null) {
			return list;
		}
		StringBuffer sb = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();

		sb.append(" SELECT a.sc_name, IFNULL(b.a1,0)AS a1 ,IFNULL(b.b1,'0.00%')AS b1, IFNULL(b.a2,0)AS a2, IFNULL(b.b2,'0.00%')AS b2 ,IFNULL(b.a3,0)AS a3, IFNULL(b.b3,'0.00%')AS b3,IFNULL(total,0)AS total FROM ");
		sb.append(" (SELECT '国家秘密事项'AS sc_name FROM dual UNION ALL SELECT '密品'AS sc_name FROM dual UNION ALL SELECT '涉密科研项目'AS sc_name FROM dual) a LEFT JOIN( ");
		sb.append(" SELECT sc_name, a1, ROUND(a1/IFNULL(a1+a2+a3,1)*100,2) AS b1, a2,ROUND(a2/IFNULL(a1+a2+a3,1)*100,2) AS b2, ");
		sb.append(" a3, ROUND(a3/IFNULL(a1+a2+a3,1)*100,2) AS b3,(a1+a2+a3)AS total FROM( ");
		sb.append(" SELECT sc_name, IFNULL(SUM(CASE WHEN secrecy_level=1 THEN fcount END),0)AS a1, IFNULL(SUM(CASE WHEN secrecy_level=2 THEN fcount END),0)AS a2, IFNULL(SUM(CASE WHEN secrecy_level=3 THEN fcount END),0)AS a3 FROM( ");
		sb.append(" SELECT sc_name, secrecy_level, SUM(fcount) AS fcount FROM( ");
		sb.append(this.getCountrySecrecySql(obj));//查询 国家秘密的sql
		sb.append(" )AS A GROUP BY sc_name, secrecy_level)AS B GROUP BY sc_name)AS C )b ON a.sc_name=b.sc_name  ");
		params.put("organ", organ.getOrganId());
		params.put("begdate", obj.getBeginDate());
		params.put("enddate", obj.getEndDate());

		list = this.getPersistProxy().getJdbcPersistence().findList(sb.toString(), params);
		//将总数查询出来，并且放入结果集中
		Map<String, Object> cmap = getCountrySecrecyTotal_ByDepartment(organ, obj);//查询总数
		Map<String, Object> listMap = new HashMap<String, Object>();
		if(cmap!=null) {
			listMap.put("sc_name", "合计");
			listMap.put("a1", cmap.get("a1"));
			listMap.put("a2", cmap.get("a2"));
			listMap.put("a3", cmap.get("a3"));
			listMap.put("total", cmap.get("total"));
			list.add(listMap);
		}

		return list;
	}

	/**(本单位)  按照部门显示
	 *  统计本单位 国家秘密的情况
 	 *	按照部门显示
	 * @param organ  单位
	 * @param obj    查询对象
	 * @return
	 */
	public List<Map<String,Object>> getOrganCountrySecrecy_ByDepartment(Organ organ, CountrySecrecyQueryObject obj){

		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		if(organ==null) {
			return list;
		}

		StringBuffer sb = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();

		sb.append(" SELECT depart.department_id, depart.department_name, a1, ROUND(a1/IFNULL(a1+a2+a3,1)*100,2) AS b1, ");
		sb.append(" a2, ROUND(a2/IFNULL(a1+a2+a3,1)*100,2) AS b2, a3, ROUND(a3/IFNULL(a1+a2+a3,1)*100,2) AS b3, (a1+a2+a3)AS total FROM( ");
		sb.append(" SELECT depart_id, IFNULL(SUM(CASE WHEN secrecy_level=1 THEN fcount END),0)AS a1, ");
		sb.append(" IFNULL(SUM(CASE WHEN secrecy_level=2 THEN fcount END),0)AS a2, IFNULL(SUM(CASE WHEN secrecy_level=3 THEN fcount END),0)AS a3 FROM( ");
		sb.append(" SELECT depart_id, secrecy_level, SUM(fcount) AS fcount FROM( ");
		sb.append(this.getCountrySecrecySql(obj));//查询 国家秘密的sql
		sb.append(" )AS A  GROUP BY depart_id, secrecy_level)AS B GROUP BY depart_id)AS secrecy  ");
		sb.append(" LEFT JOIN  (SELECT department_id,department_name FROM sys_department)AS depart ON secrecy.depart_id=depart.department_id   ");
		params.put("organ", organ.getOrganId());
		params.put("begdate", obj.getBeginDate());
		params.put("enddate", obj.getEndDate());
		list = this.getPersistProxy().getJdbcPersistence().findList(sb.toString(), params);//查询结果集

		//将总数查询出来，并且放入结果集中
		Map<String, Object> cmap = getCountrySecrecyTotal_ByDepartment(organ, obj);//查询总数
		Map<String, Object> listMap = new HashMap<String, Object>();
		if(cmap!=null) {
			listMap.put("department_name", "合计");
			listMap.put("a1", cmap.get("a1"));
			listMap.put("a2", cmap.get("a2"));
			listMap.put("a3", cmap.get("a3"));
			listMap.put("total", cmap.get("total"));
			list.add(listMap);
		}

		return list;
	}

	/**(本单位)
	 * 查询本单位的国家秘密事项总数
	 * 按照查询对象过滤
 	 * 按照部门显示
	 * @param organ  单位
	 * @param obj    查询对象
	 * @return
	 */
	public Map<String, Object> getCountrySecrecyTotal_ByDepartment(Organ organ, CountrySecrecyQueryObject obj) {

		Map<String, Object> cmap = new HashMap<String, Object>();
		if(organ==null) {
			return cmap;
		}
		StringBuffer sb = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();

		sb.append(" SELECT a1,a2,a3,a1+a2+a3 AS total FROM(");
		sb.append(" SELECT IFNULL(SUM(CASE WHEN secrecy_level=1 THEN fcount END),0)AS a1,IFNULL(SUM(CASE WHEN secrecy_level=2 THEN fcount END),0)AS a2,IFNULL(SUM(CASE WHEN secrecy_level=3 THEN fcount END),0)AS a3 FROM( ");
		sb.append(" SELECT secrecy_level, SUM(fcount) AS fcount FROM( ");
		sb.append(this.getCountrySecrecySql(obj));//查询 国家秘密的sql
		sb.append(" )AS A GROUP BY secrecy_level)AS B)AS C");
		params.put("organ", organ.getOrganId());
		params.put("begdate", obj.getBeginDate());
		params.put("enddate", obj.getEndDate());

		cmap = this.getPersistProxy().getJdbcPersistence().find(sb.toString(), params);
		return cmap;
	}

	/**（本单位）
	 * 国家秘密的sql
	 * @param obj  查询对象
	 * @return
	 */
	private StringBuffer getCountrySecrecySql(CountrySecrecyQueryObject obj) {
		StringBuffer sb = new StringBuffer();

		String strSQL = "";
		if(obj!=null && obj.getRflag()!=null) {
			Integer iv = obj.getRflag();
			if(iv==1) {//查询新增
				strSQL = " 1=1 ";
			}else if(iv==2) {//查询新解
				strSQL = " secrecy_status=1 ";
			}else {//统计总数
				strSQL = " secrecy_status!=1 ";
			}
		}

		if(obj==null || (obj!=null && obj.getBeginDate()==null && obj.getEndDate()==null)) {
			sb.append(" SELECT '国家秘密事项'AS sc_name, depart_id, secrecy_level, 1 AS fcount  FROM bm_secrecy_countryitem WHERE "+strSQL+" AND create_organ=:organ  ");
			sb.append(" UNION ALL ");
			sb.append(" SELECT '密品'AS sc_name, depart_id, secrecy_level, 1 AS fcount  FROM bm_secrecy_products WHERE "+strSQL+" AND create_organ=:organ  ");
			sb.append(" UNION ALL ");
			sb.append(" SELECT '涉密科研项目'AS sc_name, depart_id, secrecy_level, 1 AS fcount  FROM bm_secrecy_researchproject WHERE "+strSQL+" AND create_organ=:organ  ");
		}else if(obj!=null && obj.getBeginDate()!=null && obj.getEndDate()!=null){
			sb.append(" SELECT '国家秘密事项'AS sc_name, depart_id, secrecy_level, 1 AS fcount FROM bm_secrecy_countryitem WHERE "+strSQL+" AND create_organ=:organ AND DATE_FORMAT(create_time,'%Y-%m-%d') BETWEEN :begdate AND :enddate ");
			sb.append(" UNION ALL ");
			sb.append(" SELECT '密品'AS sc_name, depart_id, secrecy_level, 1 AS fcount FROM bm_secrecy_products WHERE "+strSQL+" AND create_organ=:organ AND DATE_FORMAT(create_time,'%Y-%m-%d') BETWEEN :begdate AND :enddate ");
			sb.append(" UNION ALL ");
			sb.append(" SELECT '涉密科研项目'AS sc_name, depart_id, secrecy_level, 1 AS fcount FROM bm_secrecy_researchproject WHERE "+strSQL+" AND create_organ=:organ AND DATE_FORMAT(create_time,'%Y-%m-%d') BETWEEN :begdate AND :enddate ");
		}
		return sb;
	}
	/*********************************************************************本单位统计***********end***********************************************************************/



	/*********************************************************************行政区统计**********start***********************************************************************/
	/**行政区划
	 * 行政区划统计  国家秘密
	 * @param  district  行政区划
	 * @param  obj       查询对象
	 * @return
	 */
	public List<Map<String,Object>> getCountrySecrecy_District(District district ,CountrySecrecyQueryObject obj) {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();

		StringBuffer sb = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();

		sb.append(" SELECT a.sc_name, IFNULL(b.a1,0)AS a1 ,IFNULL(b.b1,'0.00%')AS b1, IFNULL(b.a2,0)AS a2, IFNULL(b.b2,'0.00%')AS b2 ,IFNULL(b.a3,0)AS a3, IFNULL(b.b3,'0.00%')AS b3,IFNULL(total,0)AS total FROM ");
		sb.append(" (SELECT '国家秘密事项'AS sc_name FROM dual UNION ALL SELECT '密品'AS sc_name FROM dual UNION ALL SELECT '涉密科研项目'AS sc_name FROM dual) a LEFT JOIN( ");
		sb.append(" SELECT sc_name, a1, ROUND(a1/IFNULL(a1+a2+a3,1)*100,2) AS b1, a2, ROUND(a2/IFNULL(a1+a2+a3,1)*100,2) AS b2, ");
		sb.append(" a3, ROUND(a3/IFNULL(a1+a2+a3,1)*100,2) AS b3,(a1+a2+a3)AS total FROM( ");
		sb.append(" SELECT sc_name, IFNULL(SUM(CASE WHEN secrecy_level=1 THEN fcount END),0)AS a1, IFNULL(SUM(CASE WHEN secrecy_level=2 THEN fcount END),0)AS a2, IFNULL(SUM(CASE WHEN secrecy_level=3 THEN fcount END),0)AS a3 FROM( ");
		sb.append(" SELECT sc_name, secrecy_level, SUM(fcount) AS fcount FROM( ");
		sb.append(this.getCountrySecrecySql(obj,1));//查询 国家秘密的sql
		sb.append(" )AS A GROUP BY sc_name, secrecy_level)AS B GROUP BY sc_name)AS C )b ON a.sc_name=b.sc_name  ");
		params.put("layer", district.getLayer()+"%");
		params.put("begdate", obj.getBeginDate());
		params.put("enddate", obj.getEndDate());
		list = this.getPersistProxy().getJdbcPersistence().findList(sb.toString(), params);

		//将总数查询出来，并且放入结果集中
		Map<String, Object> cmap = getCountrySecrecyTotal(district, obj, 1);//查询总数
		Map<String, Object> listMap = new HashMap<String, Object>();
		if(cmap!=null) {
			listMap.put("sc_name", cmap.get("sc_name"));
			listMap.put("a1", cmap.get("a1"));
			listMap.put("a2", cmap.get("a2"));
			listMap.put("a3", cmap.get("a3"));
			listMap.put("total", cmap.get("total"));
			list.add(listMap);
		}

		return list;
	}

	/**直辖单位
	 * 直辖单位  统计 国家秘密
	 * @param  district  行政区划
	 * @param  obj       查询对象
	 * @return
	 */
	public List<Map<String,Object>> getCountrySecrecy_Layer(District district ,CountrySecrecyQueryObject obj) {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();

		StringBuffer sb = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();

		sb.append(" SELECT a.sc_name, IFNULL(b.a1,0)AS a1 ,IFNULL(b.b1,'0.00%')AS b1, IFNULL(b.a2,0)AS a2, IFNULL(b.b2,'0.00%')AS b2 ,IFNULL(b.a3,0)AS a3, IFNULL(b.b3,'0.00%')AS b3,IFNULL(total,0)AS total FROM ");
		sb.append(" (SELECT '国家秘密事项'AS sc_name FROM dual UNION ALL SELECT '密品'AS sc_name FROM dual UNION ALL SELECT '涉密科研项目'AS sc_name FROM dual) a LEFT JOIN( ");
		sb.append(" SELECT sc_name, a1, ROUND(a1/IFNULL(a1+a2+a3,1)*100,2) AS b1, a2, ROUND(a2/IFNULL(a1+a2+a3,1)*100,2) AS b2, ");
		sb.append(" a3, ROUND(a3/IFNULL(a1+a2+a3,1)*100,2) AS b3,(a1+a2+a3)AS total FROM( ");
		sb.append(" SELECT sc_name, IFNULL(SUM(CASE WHEN secrecy_level=1 THEN fcount END),0)AS a1, IFNULL(SUM(CASE WHEN secrecy_level=2 THEN fcount END),0)AS a2, IFNULL(SUM(CASE WHEN secrecy_level=3 THEN fcount END),0)AS a3 FROM( ");
		sb.append(" SELECT sc_name, secrecy_level, SUM(fcount) AS fcount FROM( ");
		sb.append(this.getCountrySecrecySql(obj,2));//查询 国家秘密的sql
		sb.append(" )AS A GROUP BY sc_name, secrecy_level)AS B GROUP BY sc_name)AS C )b ON a.sc_name=b.sc_name  ");
		params.put("layer", district.getLayer());
		params.put("begdate", obj.getBeginDate());
		params.put("enddate", obj.getEndDate());
		list = this.getPersistProxy().getJdbcPersistence().findList(sb.toString(), params);

		//将总数查询出来，并且放入结果集中
		Map<String, Object> cmap = getCountrySecrecyTotal(district, obj, 2);//查询总数
		Map<String, Object> listMap = new HashMap<String, Object>();
		if(cmap!=null) {
			listMap.put("sc_name", cmap.get("sc_name"));
			listMap.put("a1", cmap.get("a1"));
			listMap.put("a2", cmap.get("a2"));
			listMap.put("a3", cmap.get("a3"));
			listMap.put("total", cmap.get("total"));
			list.add(listMap);
		}

		return list;
	}

	/**单位明细
	 * 单位明细统计 国家秘密
	 * @param  district  行政区划
	 * @param  obj       查询对象
	 * @return
	 */
	public List<Map<String,Object>> getCountrySecrecy_OrganDetail(District district ,CountrySecrecyQueryObject obj) {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();

		StringBuffer sb = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();

		sb.append(" SELECT organ_id,organ_name FROM sys_organization WHERE district_code IN(SELECT district_code FROM sys_district WHERE layer = :layer) ");
		params.put("layer", district.getLayer());
		list = this.getPersistProxy().getJdbcPersistence().findList(sb.toString(), params);

		return list;
	}


	/**保密局通用
	 * 保密局  统计国家秘密的  通用的sql
	 * @param obj       查询对象
	 * @param layerFlag   行政区划标志   1行政区   2直辖单位
	 * @return
	 */
	private StringBuffer getCountrySecrecySql(CountrySecrecyQueryObject obj, int layerFlag){
		StringBuffer sb = new StringBuffer();
		String strSQL = "" ;//拼装sql的字符串

		 //解密标志  区分1统计总数  2统计新增  3统计新解
		if(obj!=null && obj.getRflag()!=null) {
			Integer iv = obj.getRflag();
			if(iv==1) {//查询新增
				strSQL = "";
			}else if(iv==2) {//查询新解
				strSQL = " AND secrecy_status=1 ";
			}else {//统计总数
				strSQL = " AND secrecy_status!=1 ";
			}
		}

		//查看是否包含时间
		if(obj!=null && obj.getBeginDate()!=null && obj.getEndDate()!=null) {
			strSQL += " AND DATE_FORMAT(create_time,'%Y-%m-%d') BETWEEN :begdate AND :enddate ";
		}

		//查看是行政区划   还是直辖单位   的情况
		if(layerFlag==1){//行政区划
			strSQL += " AND create_organ IN(SELECT organ_id FROM sys_organization WHERE district_code IN (SELECT district_code FROM sys_district WHERE layer like :layer)) ";
		}else{//直辖单位
			strSQL += " AND create_organ IN(SELECT organ_id FROM sys_organization WHERE district_code IN (SELECT district_code FROM sys_district WHERE layer = :layer)) ";
		}

		sb.append(" SELECT '国家秘密事项'AS sc_name, depart_id, secrecy_level, 1 AS fcount  FROM bm_secrecy_countryitem WHERE 1=1 ").append(strSQL).append(" UNION ALL ");
		sb.append(" SELECT '密品'AS sc_name, depart_id, secrecy_level, 1 AS fcount  FROM bm_secrecy_products WHERE 1=1 ").append(strSQL).append(" UNION ALL ");
		sb.append(" SELECT '涉密科研项目'AS sc_name, depart_id, secrecy_level, 1 AS fcount  FROM bm_secrecy_researchproject WHERE 1=1 ").append(strSQL);
		return sb;
	}


	/**(行政区   直辖单位)
	 * 保密局 统计国家秘密事项总数
	 * @param district  行政区划
	 * @param obj    查询对象
	 * @param  layerFlag    行政区划标志   1行政区   2直辖单位
	 * @return
	 */
	private Map<String, Object> getCountrySecrecyTotal(District district, CountrySecrecyQueryObject obj, int layerFlag) {

		Map<String, Object> cmap = new HashMap<String, Object>();
		if(district==null) {
			return cmap;
		}
		StringBuffer sb = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();

		sb.append(" SELECT sc_name, a1,a2,a3, a1+a2+a3 AS total FROM(");
		sb.append(" SELECT '合计' AS sc_name, IFNULL(SUM(CASE WHEN secrecy_level=1 THEN fcount END),0)AS a1,IFNULL(SUM(CASE WHEN secrecy_level=2 THEN fcount END),0)AS a2,IFNULL(SUM(CASE WHEN secrecy_level=3 THEN fcount END),0)AS a3 FROM( ");
		sb.append(" SELECT secrecy_level, SUM(fcount) AS fcount FROM( ");
		sb.append(this.getCountrySecrecySql(obj, layerFlag));//查询 国家秘密的sql
		sb.append(" )AS A GROUP BY secrecy_level)AS B)AS C");
		if(layerFlag==1) {
			params.put("layer", district.getLayer() + "%");
		}else {
			params.put("layer", district.getLayer());
		}
		params.put("begdate", obj.getBeginDate());
		params.put("enddate", obj.getEndDate());

		cmap = this.getPersistProxy().getJdbcPersistence().find(sb.toString(), params);
		return cmap;
	}
	/*********************************************************************行政区统计******end****************************************************************************/


	/*********************************************************************要害部门统计国家秘密事项**********start************************************************************************/

	/**(本单位)
	 *  统计 要害部门生成 国家秘密事项
	 * @param organ  单位
	 * @return
	 */
	public List<Map<String,Object>> getOrganSecrecyCountryItim_BySection(Organ organ){

		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		if(organ==null) {
			return list;
		}
		StringBuffer sb = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();

		//统计
		sb.append(" SELECT country.key_section_id, depart.department_name, a1, ROUND(a1/IFNULL(a1+a2+a3,1)*100,2)AS b1, ");
		sb.append(" a2, ROUND(a2/IFNULL(a1+a2+a3,1)*100,2)AS b2, a3, ROUND(a3/IFNULL(a1+a2+a3,1)*100,2)AS b3, a1+a2+a3 AS total FROM ");
		sb.append(" (SELECT key_section_id, depart_id, IFNULL(SUM(CASE WHEN secrecy_level=1 THEN fcount END),0)AS a1, ");
		sb.append(" IFNULL(SUM(CASE WHEN secrecy_level=2 THEN fcount END),0)AS a2, IFNULL(SUM(CASE WHEN secrecy_level=3 THEN fcount END),0)AS a3 FROM ( ");
		sb.append(" SELECT key_section_id, depart_id, secrecy_level, SUM(fcount)AS fcount FROM( ");
		sb.append(" SELECT key_section_id, depart_id, secrecy_level, 1 AS fcount FROM bm_secrecy_countryitem WHERE secrecy_status!=1 AND key_section_id IS NOT NULL ");
		sb.append(" AND create_organ=:organ)AS A GROUP BY key_section_id, depart_id, secrecy_level)AS B GROUP BY key_section_id, depart_id)AS country ");
		sb.append(" LEFT JOIN (SELECT department_id, department_name FROM sys_department WHERE organ_id= :organ)AS depart ON country.depart_id = depart.department_id ");
		params.put("organ", organ.getOrganId());
		list = this.getPersistProxy().getJdbcPersistence().findList(sb.toString(), params);//查询结果集


		//将总数查询出来，并且放入结果集中  按照要害部门查询
		sb = new StringBuffer();
		sb.append(" SELECT  a1,a2,a3,a1+a2+a3 AS total FROM(  ");
		sb.append(" SELECT  IFNULL(SUM(CASE WHEN secrecy_level=1 THEN fcount END),0)AS a1,  ");
		sb.append(" IFNULL(SUM(CASE WHEN secrecy_level=2 THEN fcount END),0)AS a2, IFNULL(SUM(CASE WHEN secrecy_level=3 THEN fcount END),0)AS a3 FROM ( ");
		sb.append(" SELECT secrecy_level, SUM(fcount)AS fcount FROM( ");
		sb.append(" SELECT secrecy_level, 1 AS fcount FROM bm_secrecy_countryitem WHERE secrecy_status!=1 AND key_section_id IS NOT NULL AND create_organ=:organ)AS A GROUP BY  secrecy_level)AS B)AS C  ");
		Map<String, Object> cmap = this.getPersistProxy().getJdbcPersistence().find(sb.toString(), params);

		//将合计行加入
		Map<String, Object> listMap = new HashMap<String, Object>();
		if(cmap!=null) {
			listMap.put("department_name", "合计");
			listMap.put("a1", cmap.get("a1"));
			listMap.put("a2", cmap.get("a2"));
			listMap.put("a3", cmap.get("a3"));
			listMap.put("total", cmap.get("total"));
			list.add(listMap);
		}

		return list;
	}

	/**(本单位)
	 *  统计系统  部门生成 的国家秘密事项
	 * @param organ  单位
	 * @return
	 */
	public List<Map<String,Object>> getOrganSecrecyCountryItim_NoBySection(Organ organ){

		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		if(organ==null) {
			return list;
		}
		StringBuffer sb = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();

		//统计
		sb.append(" SELECT country.key_section_id, depart.department_name, a1, ROUND(a1/IFNULL(a1+a2+a3,1)*100,2)AS b1, ");
		sb.append(" a2, ROUND(a2/IFNULL(a1+a2+a3,1)*100,2)AS b2, a3, ROUND(a3/IFNULL(a1+a2+a3,1)*100,2)AS b3, a1+a2+a3 AS total FROM ");
		sb.append(" (SELECT key_section_id, depart_id, IFNULL(SUM(CASE WHEN secrecy_level=1 THEN fcount END),0)AS a1, ");
		sb.append(" IFNULL(SUM(CASE WHEN secrecy_level=2 THEN fcount END),0)AS a2, IFNULL(SUM(CASE WHEN secrecy_level=3 THEN fcount END),0)AS a3 FROM ( ");
		sb.append(" SELECT key_section_id, depart_id, secrecy_level, SUM(fcount)AS fcount FROM( ");
		sb.append(" SELECT key_section_id, depart_id, secrecy_level, 1 AS fcount FROM bm_secrecy_countryitem WHERE secrecy_status!=1 AND key_section_id IS NULL ");
		sb.append(" AND create_organ=:organ)AS A GROUP BY key_section_id, depart_id, secrecy_level)AS B GROUP BY key_section_id, depart_id)AS country ");
		sb.append(" LEFT JOIN (SELECT department_id, department_name FROM sys_department WHERE organ_id= :organ)AS depart ON country.depart_id = depart.department_id ");
		params.put("organ", organ.getOrganId());
		list = this.getPersistProxy().getJdbcPersistence().findList(sb.toString(), params);//查询结果集


		//将总数查询出来，并且放入结果集中
		sb = new StringBuffer();
		sb.append(" SELECT  a1,a2,a3,a1+a2+a3 AS total FROM(  ");
		sb.append(" SELECT  IFNULL(SUM(CASE WHEN secrecy_level=1 THEN fcount END),0)AS a1,  ");
		sb.append(" IFNULL(SUM(CASE WHEN secrecy_level=2 THEN fcount END),0)AS a2, IFNULL(SUM(CASE WHEN secrecy_level=3 THEN fcount END),0)AS a3 FROM ( ");
		sb.append(" SELECT secrecy_level, SUM(fcount)AS fcount FROM( ");
		sb.append(" SELECT secrecy_level, 1 AS fcount FROM bm_secrecy_countryitem WHERE secrecy_status!=1 AND key_section_id IS NULL AND create_organ=:organ)AS A GROUP BY  secrecy_level)AS B)AS C  ");
		Map<String, Object> cmap = this.getPersistProxy().getJdbcPersistence().find(sb.toString(), params);

		//将合计行加入
		Map<String, Object> listMap = new HashMap<String, Object>();
		if(cmap!=null) {
			listMap.put("department_name", "合计");
			listMap.put("a1", cmap.get("a1"));
			listMap.put("a2", cmap.get("a2"));
			listMap.put("a3", cmap.get("a3"));
			listMap.put("total", cmap.get("total"));
			list.add(listMap);
		}

		return list;
	}


	/**保密局    统计国家秘密事项
	 *  保密要害部门统计国家秘密事项
	 * @param district  行政区划对象
	 * @param reflag    1行政区  2直辖单位
	 * @return
	 */
	public List<Map<String,Object>> getSecrecyCountryItim_BySection_ByFlag(District district, int reflag) {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		if(district==null) {
			return list;
		}

		StringBuffer sb = new StringBuffer();
		String sql = "";
		Map<String, Object> params = new HashMap<String, Object>();

		if(reflag==1) {//行政区划
			sql = " layer like :layer ";
			params.put("layer", district.getLayer() + "%");
		}else {//直辖单位
			sql = " layer = :layer ";
			params.put("layer", district.getLayer());
		}
		//统计
		sb.append(" SELECT sec_name, a1, ROUND(a1/IFNULL(a1+a2+a3,1)*100,2)AS b1, ");
		sb.append(" a2, ROUND(a2/IFNULL(a1+a2+a3,1)*100,2)AS b2, a3, ROUND(a3/IFNULL(a1+a2+a3,1)*100,2)AS b3, a1+a2+a3 AS total FROM( ");
		sb.append(" SELECT sec_name, IFNULL(SUM(CASE WHEN secrecy_level=1 THEN fcount END),0)AS a1, ");
		sb.append(" IFNULL(SUM(CASE WHEN secrecy_level=2 THEN fcount END),0)AS a2, IFNULL(SUM(CASE WHEN secrecy_level=3 THEN fcount END),0)AS a3 FROM ( ");
		sb.append(" SELECT sec_name, secrecy_level, SUM(fcount)AS fcount FROM( ");
		sb.append(" SELECT '由保密要害部门产生' AS sec_name, secrecy_level, 1 AS fcount FROM bm_secrecy_countryitem WHERE secrecy_status!=1 AND key_section_id IS NOT NULL ");
		sb.append(" AND create_organ IN(SELECT organ_id FROM sys_organization WHERE district_code IN (SELECT district_code FROM sys_district WHERE "+sql+")) ");
		/*sb.append(" UNION ALL ");
		sb.append(" SELECT '系统部门产生' AS sec_name, secrecy_level, 1 AS fcount FROM bm_secrecy_countryitem WHERE secrecy_status!=1 AND key_section_id IS NULL ");
		sb.append(" AND create_organ IN(SELECT organ_id FROM sys_organization WHERE district_code IN (SELECT district_code FROM sys_district WHERE "+sql+")) ");*/
		sb.append(" )AS A GROUP BY sec_name, secrecy_level)AS B GROUP BY sec_name)AS C ");
		list = this.getPersistProxy().getJdbcPersistence().findList(sb.toString(), params);

		//查询总数
		sb = new StringBuffer();
		sb.append(" SELECT  a1,a2,a3,a1+a2+a3 AS total FROM(  ");
		sb.append(" SELECT IFNULL(SUM(CASE WHEN secrecy_level=1 THEN fcount END),0)AS a1,IFNULL(SUM(CASE WHEN secrecy_level=2 THEN fcount END),0)AS a2, ");
		sb.append(" IFNULL(SUM(CASE WHEN secrecy_level=3 THEN fcount END),0)AS a3 FROM ( ");
		sb.append(" SELECT secrecy_level, SUM(fcount)AS fcount FROM( ");
		sb.append(" SELECT secrecy_level, 1 AS fcount FROM bm_secrecy_countryitem WHERE secrecy_status!=1 AND key_section_id IS NOT NULL AND create_organ IN(SELECT organ_id FROM sys_organization  ");
		sb.append(" WHERE district_code IN (SELECT district_code FROM sys_district WHERE "+sql+")) ");
		sb.append(" )AS A GROUP BY secrecy_level)AS B)AS C ");
		Map<String, Object> cmap = this.getPersistProxy().getJdbcPersistence().find(sb.toString(), params);

		//将合计行加入
		Map<String, Object> listMap = new HashMap<String, Object>();
		if(cmap!=null) {
			listMap.put("sec_name", "合计");
			listMap.put("a1", cmap.get("a1"));
			listMap.put("a2", cmap.get("a2"));
			listMap.put("a3", cmap.get("a3"));
			listMap.put("total", cmap.get("total"));
			list.add(listMap);
		}

		return list;
	}

	/**(本单位)
	 * 统计 本单位   国家秘密事项的总数
	 * 对 没有解密的  当前单位的  国家秘密事项 的记录  进行统计
	 * @param organ
	 * @return
	 */
	public Map<String, Object> getSecrecyCountryItim_Total_CurrentOrgan(Organ organ){

		Map<String, Object> cmap = new HashMap<String, Object>();
		if(organ==null) {
			return cmap;
		}
		StringBuffer sb = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();

		sb.append(" SELECT  a1,a2,a3,a1+a2+a3 AS total FROM(  ");
		sb.append(" SELECT  IFNULL(SUM(CASE WHEN secrecy_level=1 THEN fcount END),0)AS a1,  ");
		sb.append(" IFNULL(SUM(CASE WHEN secrecy_level=2 THEN fcount END),0)AS a2, IFNULL(SUM(CASE WHEN secrecy_level=3 THEN fcount END),0)AS a3 FROM ( ");
		sb.append(" SELECT secrecy_level, SUM(fcount)AS fcount FROM( ");
		sb.append(" SELECT secrecy_level, 1 AS fcount FROM bm_secrecy_countryitem WHERE secrecy_status!=1 AND create_organ=:organ)AS A GROUP BY  secrecy_level)AS B)AS C  ");
		params.put("organ", organ.getOrganId());
		cmap = this.getPersistProxy().getJdbcPersistence().find(sb.toString(), params);

		return cmap;
	}


	/** 保密局    统计国家秘密事项
	 *  对 没有解密的   国家秘密事项 的记录  进行统计
	 * @param district  行政区划对象
	 * @param reflag    1行政区  2直辖单位
	 * @return
	 */
	public Map<String, Object> getSecrecyCountryItim_Total_District(District district, int reflag) {
		Map<String, Object> cmap = new HashMap<String,Object>();
		if(district==null) {
			return cmap;
		}

		StringBuffer sb = new StringBuffer();
		String sql = "";
		Map<String, Object> params = new HashMap<String, Object>();

		if(reflag==1) {//行政区划
			sql = " layer like :layer ";
			params.put("layer", district.getLayer() + "%");
		}else {//直辖单位
			sql = " layer = :layer ";
			params.put("layer", district.getLayer());
		}

		//查询总数
		sb = new StringBuffer();
		sb.append(" SELECT  a1,a2,a3,a1+a2+a3 AS total FROM(  ");
		sb.append(" SELECT IFNULL(SUM(CASE WHEN secrecy_level=1 THEN fcount END),0)AS a1,IFNULL(SUM(CASE WHEN secrecy_level=2 THEN fcount END),0)AS a2, ");
		sb.append(" IFNULL(SUM(CASE WHEN secrecy_level=3 THEN fcount END),0)AS a3 FROM ( ");
		sb.append(" SELECT secrecy_level, SUM(fcount)AS fcount FROM( ");
		sb.append(" SELECT secrecy_level, 1 AS fcount FROM bm_secrecy_countryitem WHERE secrecy_status!=1 AND create_organ IN(SELECT organ_id FROM sys_organization  ");
		sb.append(" WHERE district_code IN (SELECT district_code FROM sys_district WHERE "+sql+")) ");
		sb.append(" )AS A GROUP BY secrecy_level)AS B)AS C ");
		cmap = this.getPersistProxy().getJdbcPersistence().find(sb.toString(), params);

		return cmap;
	}
	/*********************************************************************要害部门统计国家秘密事项***********end***********************************************************************/

	/***************************************************************************综合统计国家秘密事项******start********************************************************/
	/**(综合统计)
	 * 通过行政区划对象集合
	 * 综合统计  统计国家秘密事项的  个数.
	 * 包含了   行政区划和行政区内的  国家秘密事项 个数    都需要分密级统计出来
	 *
	 * @param districtList    行政区划对象集合    这里每个行政区划对象必须包含layer对象
	 * @param isGroup         是否需要合计数据
	 * @return 查询出来的列
	 *  district_name(行政区划名字)
	 *  district_code(行政区划编码)
	 *  a1 a2 a3 (按照行政区划统计出的个数信息)
	 *  b1 b2 b3 (按照直辖单位统计出的个数信息)
	 *
	 */
	public List<ZongHeTongJiStatDto> count_SecrecyCountryItim_District(List<District> districtList, boolean isGroup) {

		//返回list
		List<ZongHeTongJiStatDto> list = new ArrayList<ZongHeTongJiStatDto>();
		if(districtList==null) {//如果行政区集合为空  直接返回
			return list;
		}
		ZongHeTongJiStatDto totalMap = new ZongHeTongJiStatDto();//合计数

		//轮循
		Iterator<District> it = districtList.iterator();
		while(it.hasNext()) {
			District district =  it.next();
			//获取sql语句
			StringBuffer sql = ZongHeTongJiUtil.getDistrictSql_ZongHeTongJi(district.getLayer(),"bm_secrecy_countryitem","create_organ");
			//查询
			Map<String, Object> cmsp = this.getPersistProxy().getJdbcPersistence().find(sql.toString(), new HashMap<String, Object>());
			//类型转换
			ZongHeTongJiStatDto stat = ZongHeTongJiUtil.map2Dto(cmsp);//类型转换;
			list.add(stat);

			if(isGroup) {//是否合计  如果是  ，这里将计算合计列
				totalMap = ZongHeTongJiUtil.setTotalRow(totalMap, stat);
			}
		}

		//如果需要合计，list又不为空的情况
		if(isGroup && !list.isEmpty()){
			list.add(totalMap);
		}

		return list;
	}

	/** (综合统计)
	 *  通过单位对象   查询出一个单位下的国家秘密事项个数  都要按照密级统计出来
	 * @param organ  单位   这个的单位对象必须要包含organId信息
	 * @param district 行政区划对象
	 * @param needTotal 是否合计
	 * @return
	 *  查询出来的列 :
	 *  organName(本单位名字)
	 *  organId(本单位编码)
	 *  a1 a2 a3 (按照本单位统计出的个数信息)
	 */
	public List<ZongHeTongJiStatDto> count_SecrecyCountryItim_Organ(District district, Organ organ, boolean needTotal) {

		//返回值
		List<ZongHeTongJiStatDto> list = new ArrayList<ZongHeTongJiStatDto>();
		ZongHeTongJiStatDto totalMap = new ZongHeTongJiStatDto();//合计数

		//获取sql语句
		StringBuffer sb = ZongHeTongJiUtil.getOrganSql_ZongHeTongJi(district, organ, "bm_secrecy_countryitem", "create_organ");
		List<Map<String, Object>> cmpList = this.getPersistProxy().getJdbcPersistence().findList(sb.toString(), new HashMap<String, Object>());//查询
		if(cmpList!=null) {
			Iterator<Map<String, Object>> it = cmpList.iterator();
			while(it.hasNext()) {
				Map<String, Object> cmp = it.next();
				//类型转换
				ZongHeTongJiStatDto stat = ZongHeTongJiUtil.map2Dto(cmp);
				list.add(stat);

				if(needTotal) {//是否合计  如果是  ，这里将计算合计列
					totalMap = ZongHeTongJiUtil.setTotalRow(totalMap, stat);
				}
			}
		}

		if(needTotal && !list.isEmpty()) {//是否合计  如果是  ，这里将计算合计列
			list.add(totalMap);
		}

		return list;
	}

	/**通过行政区划对象统计   分单位显示行政区划下面的统计情况
	 * 综合统计  查询 行政区划国家秘密事项统计  通过行政区划编码
	 * @param district  行政区划对象 , 每个行政区划必须包含  districtCode
	 * @param isGroup  是否合计
	 * @param organ   单位对象   ，这个对象可以为空，只是在模糊查询的时候我们会用到，单位的名字
	 *
	 * @return
	 */
	public List<ZongHeTongJiStatDto> count_SecrecyCountryItem_District(District district, boolean isGroup,Organ organ){

		//返回值
		List<ZongHeTongJiStatDto> list = new ArrayList<ZongHeTongJiStatDto>();

		if(district!=null && district.getDistrictCode()!=null) {
			ZongHeTongJiStatDto totalMap = new ZongHeTongJiStatDto();//合计数

			//获取sql语句
			StringBuffer sb = ZongHeTongJiUtil.getOrganSqlByDistrict_ZongHeTongJi(district, "bm_secrecy_countryitem", "create_organ",organ);
			//查询
			List<Map<String, Object>> cmpList = this.getPersistProxy().getJdbcPersistence().findList(sb.toString(), new HashMap<String, Object>());
			if(cmpList!=null) {
				Iterator<Map<String, Object>> it = cmpList.iterator();
				while(it.hasNext()) {
					Map<String, Object> cmp = it.next();
					//类型转换
					ZongHeTongJiStatDto stat = ZongHeTongJiUtil.map2Dto(cmp);
					list.add(stat);

					if(isGroup) {//是否合计  如果是  ，这里将计算合计列
						totalMap = ZongHeTongJiUtil.setTotalRow(totalMap, stat);
					}
				}
			}

			if(isGroup && !list.isEmpty()) {//是否合计  如果是  ，这里将计算合计列
				list.add(totalMap);
			}
		}

		return list;
	}

	/***************************************************************************综合统计国家秘密事项******end*********************************************************/

}
