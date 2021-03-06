package com.cdthgk.bmp.secrecyresearchproject.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.bmp.secrecycountryitem.qo.ZongHeTongJiStatDto;
import com.cdthgk.bmp.secrecycountryitem.qo.ZongHeTongJiUtil;
import com.cdthgk.bmp.secrecyperson.vo.SecrecyPerson;
import com.cdthgk.bmp.secrecyresearchproject.service.SecrecyResearchProjectService;
import com.cdthgk.bmp.secrecyresearchproject.vo.SecrecyResearchProject;
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

public class SecrecyResearchProjectServiceImpl extends BmpServiceImpl<SecrecyResearchProject, Serializable>
implements SecrecyResearchProjectService{


	/**
	 * 查询 涉密科研项目 列表
	 * @param psm    分页对象
	 * @param organ   单位
	 * @param secrecyResearchProject  涉密科研项目对象
	 * @return
	 */
	public List<SecrecyResearchProject> querySecrecyResearchProjectList(PageSortModel psm, Organ organ, SecrecyResearchProject secrecyResearchProject) {

		List<SecrecyResearchProject> list = new ArrayList<SecrecyResearchProject>();//返回对象
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("From SecrecyResearchProject p where p.secrecyStatus!=1 ");

		//涉密科研项目的查询对象不为空
		if(secrecyResearchProject!=null) {
			//涉密科研项目名称
			if(secrecyResearchProject.getSecrecyResearchProjectName()!= null && !"".equals(secrecyResearchProject.getSecrecyResearchProjectName())) {
				hql.append(" and p.secrecyResearchProjectName like :secrecyResearchProjectName");
				params.put("secrecyResearchProjectName", "%" + secrecyResearchProject.getSecrecyResearchProjectName() + "%");
			}
			//涉密等级
			if(secrecyResearchProject.getSecrecyLevel()!= null && !"".equals(secrecyResearchProject.getSecrecyLevel())) {
				hql.append(" and p.secrecyLevel = :secrecyLevel");
				params.put("secrecyLevel", secrecyResearchProject.getSecrecyLevel());
			}
			//定密负责人
			if(secrecyResearchProject.getFormulateSecrecyPerson()!= null && secrecyResearchProject.getFormulateSecrecyPerson().getName()!=null
					&& !"".equals(secrecyResearchProject.getFormulateSecrecyPerson().getName())) {
				hql.append(" and p.formulateSecrecyPerson.name like :formulateSecrecyPerson");
				params.put("formulateSecrecyPerson", "%"+secrecyResearchProject.getFormulateSecrecyPerson().getName()+"%");
			}
			//部门名称
			if(secrecyResearchProject.getDepartId()!= null && secrecyResearchProject.getDepartId().getDepartmentName()!=null
					&& !"".equals(secrecyResearchProject.getDepartId().getDepartmentName())) {
				hql.append(" and p.departId.departmentName like :departId");
				params.put("departId", "%"+secrecyResearchProject.getDepartId().getDepartmentName()+"%");
			}
			//项目负责人  名称模糊查询
			if(secrecyResearchProject.getProjectPerson()!= null && secrecyResearchProject.getProjectPerson().getName()!=null
					&& !"".equals(secrecyResearchProject.getProjectPerson().getName())) {
				hql.append(" and p.projectPerson.name like :name");
				params.put("name", "%"+secrecyResearchProject.getProjectPerson().getName() + "%");
			}
			//项目状态
			if(secrecyResearchProject.getProjectState()!= null && !"".equals(secrecyResearchProject.getProjectState())) {
				hql.append(" and p.projectState = :projectState");
				params.put("projectState", secrecyResearchProject.getProjectState());
			}
		}

		//单位不为空
		if(organ!=null) {
			hql.append(" and p.createOrgan.organId = :organ");
			params.put("organ", organ.getOrganId());
		}
		//创建时间倒叙
		hql.append(" order by p.createTime desc ");
		if(psm!=null) {
			list = (List<SecrecyResearchProject>) this.findList(hql.toString(), params, psm);//分页
		}else {
			list = (List<SecrecyResearchProject>) this.findList(hql.toString(), params);//不分页
		}

		return list;
	}


	/**
	 * 查询 涉密科研项目 列表
	 * @param psm    分页对象
	 * @param district  行政区划
	 * @param isChildren 包含下级  1包含  0不包含
	 * @param secrecyResearchProject  涉密科研项目对象
	 * @return
	 */
	public List<SecrecyResearchProject> querySecrecyResearchProjectList(PageSortModel psm, District district,int isChildren, SecrecyResearchProject secrecyResearchProject) {

		List<SecrecyResearchProject> list = new ArrayList<SecrecyResearchProject>();//返回对象
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("From SecrecyResearchProject p where p.secrecyStatus!=1 ");

		//涉密科研项目的查询对象不为空
		if(secrecyResearchProject!=null) {
			//涉密科研项目名称
			if(secrecyResearchProject.getSecrecyResearchProjectName()!= null && !"".equals(secrecyResearchProject.getSecrecyResearchProjectName())) {
				hql.append(" and p.secrecyResearchProjectName like :secrecyResearchProjectName");
				params.put("secrecyResearchProjectName", "%" + secrecyResearchProject.getSecrecyResearchProjectName() + "%");
			}
			//涉密等级
			if(secrecyResearchProject.getSecrecyLevel()!= null && !"".equals(secrecyResearchProject.getSecrecyLevel())) {
				hql.append(" and p.secrecyLevel = :secrecyLevel");
				params.put("secrecyLevel", secrecyResearchProject.getSecrecyLevel());
			}
			//定密负责人
			if(secrecyResearchProject.getFormulateSecrecyPerson()!= null && secrecyResearchProject.getFormulateSecrecyPerson().getName()!=null
					&& !"".equals(secrecyResearchProject.getFormulateSecrecyPerson().getName())) {
				hql.append(" and p.formulateSecrecyPerson.name like :formulateSecrecyPerson");
				params.put("formulateSecrecyPerson", "%"+secrecyResearchProject.getFormulateSecrecyPerson().getName()+"%");
			}
			//部门名称
			if(secrecyResearchProject.getDepartId()!= null && secrecyResearchProject.getDepartId().getDepartmentName()!=null
					&& !"".equals(secrecyResearchProject.getDepartId().getDepartmentName())) {
				hql.append(" and p.departId.departmentName like :departId");
				params.put("departId", "%"+secrecyResearchProject.getDepartId().getDepartmentName()+"%");
			}
			//项目负责人  名称模糊查询
			if(secrecyResearchProject.getProjectPerson()!= null && secrecyResearchProject.getProjectPerson().getName()!=null
					&& !"".equals(secrecyResearchProject.getProjectPerson().getName())) {
				hql.append(" and p.projectPerson.name like :name");
				params.put("name", "%"+secrecyResearchProject.getProjectPerson().getName() + "%");
			}
			//项目状态
			if(secrecyResearchProject.getProjectState()!= null && !"".equals(secrecyResearchProject.getProjectState())) {
				hql.append(" and p.projectState = :projectState");
				params.put("projectState", secrecyResearchProject.getProjectState());
			}
		}

		//行政区划不为空
		if(district!=null && district.getLayer()!=null) {
			if(isChildren==1){
				hql.append(" and p.createOrgan.district.layer like :layer ");
				params.put("layer", district.getLayer()+"%");
			}else{
				hql.append(" and p.createOrgan.district.layer = :layer ");
				params.put("layer", district.getLayer());
			}
		}

		//创建时间倒叙
		hql.append(" order by p.createTime desc ");
		if(psm!=null) {
			list = (List<SecrecyResearchProject>) this.findList(hql.toString(), params, psm);//分页
		}else {
			list = (List<SecrecyResearchProject>) this.findList(hql.toString(), params);//不分页
		}

		return list;
	}

	/**
	 * 查询  涉密科研项目  相关联的表 有哪些
	 * 1  变更表
	 * 2  解密表
	 * @param secrecyResearchProjectId    涉密科研项目id
	 * @return
	 */
	public Integer getRelationshipForTable(String secrecyResearchProjectId) {

		Integer iValue = new Integer(0);//返回值

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (secrecyResearchProjectId == null || secrecyResearchProjectId.equals("")) {
			return iValue;//如果id为空
		}

		StringBuffer sb = new StringBuffer();
		Map<String ,Object> params = new HashMap<String, Object>();

		//1变更记录表
		sb = new StringBuffer();
		params = new HashMap<String, Object>();
		sb.append("select p.change_id from bm_Secrecy_ResearchProject_change p where p.SecrecyResearchProject_id = :secrecyResearchProjectId ");
		params.put("secrecyResearchProjectId", secrecyResearchProjectId);
		list = this.getPersistProxy().getJdbcPersistence().findList(sb.toString(), params);
		if(list!=null && list.size()>0) {
			return new Integer(1);
		}

		//2解密记录表
		sb = new StringBuffer();
		params = new HashMap<String, Object>();
		sb.append("select p.clear_id from bm_Secrecy_ResearchProject_clear p where p.SecrecyResearchProject_id = :secrecyResearchProjectId ");
		params.put("secrecyResearchProjectId", secrecyResearchProjectId);
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
			userInfo.setRemark(user.getUserInfo().getName()+"在(商业秘密->涉密科研项目)中新增");
			userInfo.save();
		}

		//系统人员处理完毕以后,设置涉密人员
		SecrecyPerson sp = new SecrecyPerson();
		sp.setUserInfo(userInfo);//人员信息
		sp.setCreatePerson(user);//创建人
		sp.setCreateTime(new Date());//创建时间
		sp.setOrgan(user.getOrgan());//创建单位
		sp.setResponsiblePerson(1);//是否是定密负责人
		sp.setSecrecyPersonLevel(3);//设置为密级为秘密
		sp.setPersonType(1);//在编
		this.getPersistProxy().getOrmPersistence().save(sp);

		//最后还是只需要返回人员的信息  就可以了
		return userInfo;
	}




	/**********************************************************统计****************************************************/


	/**
	 * 统计 本单位  涉密科研项目的总数
	 * 对 没有解密的  当前单位的   涉密科研项目 的记录  进行统计
	 * @param organ
	 * @return
	 */
	public Map<String, Object> getSecrecyResearchProject_Total_CurrentOrgan(Organ organ){

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
		sb.append(" SELECT secrecy_level, 1 AS fcount FROM bm_secrecy_researchproject WHERE secrecy_status!=1 AND create_organ=:organ)AS A GROUP BY  secrecy_level)AS B)AS C  ");
		params.put("organ", organ.getOrganId());
		cmap = this.getPersistProxy().getJdbcPersistence().find(sb.toString(), params);

		return cmap;
	}

	/** 保密局    统计 涉密科研项目
	 *  对 没有解密的    涉密科研项目  的记录  进行统计
	 * @param district  行政区划对象
	 * @param reflag    1行政区  2直辖单位
	 * @return
	 */
	public Map<String, Object> getSecrecyResearchProject_Total_District(District district, int reflag) {
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
		sb.append(" SELECT secrecy_level, 1 AS fcount FROM bm_secrecy_researchproject WHERE secrecy_status!=1 AND create_organ IN(SELECT organ_id FROM sys_organization  ");
		sb.append(" WHERE district_code IN (SELECT district_code FROM sys_district WHERE "+sql+")) ");
		sb.append(" )AS A GROUP BY secrecy_level)AS B)AS C ");
		cmap = this.getPersistProxy().getJdbcPersistence().find(sb.toString(), params);

		return cmap;
	}



	/***************************************************************************综合统计涉密科研项目******start*********************************************************/
	/**
	 * 综合统计  统计涉密科研项目的个数.
	 * 包含了行政区划和直辖单位的个数    都要按照密级统计出来
	 *
	 * @param districtList    行政区划对象集合
	 * @param isGroup         是否需要合计数据
	 * @return 查询出来的列
	 *  district_name(行政区划名字)
	 *  district_code(行政区划编码)
	 *  a1 a2 a3 (按照行政区划统计出的个数信息)
	 *  b1 b2 b3 (按照直辖单位统计出的个数信息)
	 *
	 */
	public List<ZongHeTongJiStatDto> count_ResearchProject_District(List<District> districtList, boolean isGroup) {

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
			StringBuffer sql = ZongHeTongJiUtil.getDistrictSql_ZongHeTongJi(district.getLayer(),"bm_secrecy_researchproject","create_organ");
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

	/**
	 *  综合统计 sql  查询出 单位下的涉密科研项目个数  都要按照密级统计出来
	 * @param organ  单位
	 * @param district 行政区划
	 * @param needTotal 是否合计
	 *
	 * @return
	 *  查询出来的列 :
	 *  organName(本单位名字)
	 *  organId(本单位编码)
	 *  a1 a2 a3 (按照本单位统计出的个数信息)
	 */
	public List<ZongHeTongJiStatDto> count_ResearchProject_Organ(District district, Organ organ, boolean needTotal) {

		//返回值
		List<ZongHeTongJiStatDto> list = new ArrayList<ZongHeTongJiStatDto>();
		ZongHeTongJiStatDto totalMap = new ZongHeTongJiStatDto();//合计数

		//获取sql语句
		StringBuffer sb = ZongHeTongJiUtil.getOrganSql_ZongHeTongJi(district, organ, "bm_secrecy_researchproject", "create_organ");
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
	 * 综合统计  查询 行政区划涉密科研项目统计  通过行政区划编码
	 * @param district  行政区划对象 , 每个行政区划必须包含  districtCode
	 * @param isGroup  是否合计
	 * @param organ   单位对象   ，这个对象可以为空，只是在模糊查询的时候我们会用到，单位的名字
	 *
	 * @return
	 */
	public List<ZongHeTongJiStatDto> count_ResearchProject_District(District district, boolean isGroup,Organ organ){

		//返回值
		List<ZongHeTongJiStatDto> list = new ArrayList<ZongHeTongJiStatDto>();

		if(district!=null && district.getDistrictCode()!=null) {
			ZongHeTongJiStatDto totalMap = new ZongHeTongJiStatDto();//合计数

			//获取sql语句
			StringBuffer sb = ZongHeTongJiUtil.getOrganSqlByDistrict_ZongHeTongJi(district, "bm_secrecy_researchproject", "create_organ",organ);
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

	/**
	 * 查询 涉密科研项目 列表    只有行政区划的时候  查询单位下面的密品情况
	 * @param psm    分页对象
	 * @param district  行政区划
	 * @param secrecyResearchProject  涉密科研项目对象
	 * @return
	 */
	public List<SecrecyResearchProject> querySecrecyResearchProjectList(PageSortModel psm, District district, SecrecyResearchProject secrecyResearchProject) {

		List<SecrecyResearchProject> list = new ArrayList<SecrecyResearchProject>();//返回对象
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("From SecrecyResearchProject p where p.secrecyStatus!=1 ");

		//涉密科研项目的查询对象不为空
		if(secrecyResearchProject!=null) {
			//涉密科研项目名称
			if(secrecyResearchProject.getSecrecyResearchProjectName()!= null && !"".equals(secrecyResearchProject.getSecrecyResearchProjectName())) {
				hql.append(" and p.secrecyResearchProjectName like :secrecyResearchProjectName");
				params.put("secrecyResearchProjectName", "%" + secrecyResearchProject.getSecrecyResearchProjectName() + "%");
			}
			//涉密等级
			if(secrecyResearchProject.getSecrecyLevel()!= null && !"".equals(secrecyResearchProject.getSecrecyLevel())) {
				hql.append(" and p.secrecyLevel = :secrecyLevel");
				params.put("secrecyLevel", secrecyResearchProject.getSecrecyLevel());
			}
			//定密负责人
			if(secrecyResearchProject.getFormulateSecrecyPerson()!= null && secrecyResearchProject.getFormulateSecrecyPerson().getName()!=null
					&& !"".equals(secrecyResearchProject.getFormulateSecrecyPerson().getName())) {
				hql.append(" and p.formulateSecrecyPerson.name like :formulateSecrecyPerson");
				params.put("formulateSecrecyPerson", "%"+secrecyResearchProject.getFormulateSecrecyPerson().getName()+"%");
			}
			//部门名称
			if(secrecyResearchProject.getDepartId()!= null && secrecyResearchProject.getDepartId().getDepartmentName()!=null
					&& !"".equals(secrecyResearchProject.getDepartId().getDepartmentName())) {
				hql.append(" and p.departId.departmentName like :departId");
				params.put("departId", "%"+secrecyResearchProject.getDepartId().getDepartmentName()+"%");
			}
			//项目负责人  名称模糊查询
			if(secrecyResearchProject.getProjectPerson()!= null && secrecyResearchProject.getProjectPerson().getName()!=null
					&& !"".equals(secrecyResearchProject.getProjectPerson().getName())) {
				hql.append(" and p.projectPerson.name like :name");
				params.put("name", "%"+secrecyResearchProject.getProjectPerson().getName() + "%");
			}
			//项目状态
			if(secrecyResearchProject.getProjectState()!= null && !"".equals(secrecyResearchProject.getProjectState())) {
				hql.append(" and p.projectState = :projectState");
				params.put("projectState", secrecyResearchProject.getProjectState());
			}
		}

		//行政区划不为空
		if(district!=null && district.getLayer()!=null) {
			hql.append(" and p.createOrgan.district.districtCode = :districtCode ");
			params.put("districtCode", district.getDistrictCode());
		}

		//创建时间倒叙
		hql.append(" order by p.createTime desc ");
		if(psm!=null) {
			list = (List<SecrecyResearchProject>) this.findList(hql.toString(), params, psm);//分页
		}else {
			list = (List<SecrecyResearchProject>) this.findList(hql.toString(), params);//不分页
		}

		return list;
	}

	/***************************************************************************综合统计涉密科研项目******end*********************************************************/
}
