package com.cdthgk.bmp.secrecyperson.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Distinct;
import org.hibernate.mapping.Array;

import com.cdthgk.bmp.core.action.BmpAction;
import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.bmp.keysection.vo.KeySection;
import com.cdthgk.bmp.secrecycountryitem.qo.ZongHeTongJiStatDto;
import com.cdthgk.bmp.secrecycountryitem.qo.ZongHeTongJiUtil;
import com.cdthgk.bmp.secrecyperson.qo.SecrecyPersonQo;
import com.cdthgk.bmp.secrecyperson.service.SecrecyPersonModuleService;
import com.cdthgk.bmp.secrecyperson.vo.SecrecyPerson;
import com.cdthgk.code.enums.Nation;
import com.cdthgk.code.enums.Nationality;
import com.cdthgk.common.bean.BeanUtils;
import com.cdthgk.common.lang.CollectionUtils;
import com.cdthgk.common.lang.DateUtils;
import com.cdthgk.common.lang.LangUtils;
import com.cdthgk.common.lang.StringUtils;
import com.cdthgk.data.core.DataRecord;
import com.cdthgk.data.core.DataSet;
import com.cdthgk.data.core.DataSource;
import com.cdthgk.data.office.word.WordDataSource;
import com.cdthgk.platform.base.util.ReportState;
import com.cdthgk.platform.dictionary.context.DictionaryContext;
import com.cdthgk.platform.dictionary.domain.DictionaryOption;
import com.cdthgk.platform.dictionary.spi.DictionaryService;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.core.OrganizationContext;
import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.department.service.DepartmentService;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;
import com.cdthgk.platform.organization.userinfo.service.UserInfoService;
import com.cdthgk.platform.permission.user.domain.User;
import com.cdthgk.web.upload.UploadFile;
import com.thgk.framework.core.pinyin.PinYin;

import ec.common.PageSortModel;
import eu.medsea.util.StringUtil;

/**
 * <p>
 * 机关涉密人员Service类
 * </p>
 * <p>
 * 牟远洋 2012-12-19 17:01
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
 * @author mouyuanyang
 * @since 1.0
 * @version 1.0
 */
public class SecrecyPersonServiceImpl extends BmpServiceImpl<SecrecyPerson, String>
			implements SecrecyPersonModuleService{

	private final int allLeavel = 4;

	private List<String> msgList = new ArrayList<String>();
	// 日志记录
	private static final Log LOG = LogFactory.getLog(SecrecyPersonSearchServiceImpl.class);

	private UserInfoService userInfoService;

	private DepartmentService departmentService;

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

	/**
	 * 返回userInfoService
	 * @return userInfoService
	 */
	public UserInfoService getUserInfoService() {
		return userInfoService;
	}

	/**
	 * 设置userInfoService
	 * @param userInfoService userInfoService
	 */
	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}

	// 构造器
	/** 默认构造器 */
	public SecrecyPersonServiceImpl() {
	}

	/**
	 * <p>
	 * 单位涉密人员查询列表
	 * </p>
	 * <p>
	 * @author 牟远洋 2012 12 23 11:00
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
	 * @author mouyuanyang
	 * @version 1.0
	 * @param psm psm PageSortModel
	 * @param secrecyPerson 涉密人员对象
	 * @param organ 当前单位
	 * @return List<SecrecyPerson>
	 */
	public List<SecrecyPerson> getPageAllList(PageSortModel psm, SecrecyPerson secrecyPerson, Organ organ, District district,
			String partId, String checkLow ,Integer nowFlag) {

		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("from SecrecyPerson s where 1=1 ");

		if( partId!=null && !"".equals(partId)){
			hql = new StringBuilder("select s from PartPerson pp , SecrecyPerson s WHERE pp.secrecyPerson.secrecyPersonId = s.secrecyPersonId AND 1=1 ");
			hql.append(" AND pp.part.partId = :partId ");
			params.put("partId", partId);
		}else if( organ!=null ){
			// 当前单位涉密人员
			hql.append(" and s.organ.organId = :organId");
			params.put("organId", organ.getOrganId());
		}else if( district!=null ){
			if( checkLow!=null && "1".equals(checkLow) ){
				hql.append(" and s.organ.district.layer like :layer");
				params.put("layer", district.getLayer()+"%");
			}else{
				hql.append(" and s.organ.district.layer = :layer");
				params.put("layer", district.getLayer());
			}
		}

		// 拼接查询条件
		if (secrecyPerson != null) {
			// 姓 名
			if (secrecyPerson.getUserInfo()!=null && secrecyPerson.getUserInfo().getName() != null
					&& !"".equals(secrecyPerson.getUserInfo().getName())) {
				hql.append(" and s.userInfo.name like :name");
				params.put("name", "%" + secrecyPerson.getUserInfo().getName() + "%");
			}
			// 涉密等级
			if (secrecyPerson.getSecrecyPersonLevel() != null
					&& secrecyPerson.getSecrecyPersonLevel().intValue() != allLeavel) {
				hql.append(" and s.secrecyPersonLevel = :secrecyPersonLevel");
				params.put("secrecyPersonLevel", secrecyPerson.getSecrecyPersonLevel());
			}
			// 人员类型
			if (secrecyPerson.getPersonType() != null
					&& secrecyPerson.getPersonType().intValue() != allLeavel) {
				hql.append(" and s.personType = :personType");
				params.put("personType", secrecyPerson.getPersonType());
			}
			// 类别
			if (secrecyPerson.getSpPerType() != null
					&& secrecyPerson.getSpPerType().intValue() != allLeavel) {
				hql.append(" and s.spPerType = :spPerType");
				params.put("spPerType", secrecyPerson.getSpPerType());
			}
			// 部 门
			if (secrecyPerson.getDepartment()!=null) {
				//部门名称过滤  梁文杰 2013-07-20修改
				if(secrecyPerson.getDepartment().getDepartmentName() != null && !"".equals(secrecyPerson.getDepartment().getDepartmentName())) {
					hql.append(" and s.department.departmentName like :departmentName");
					params.put("departmentName",
							"%" + secrecyPerson.getDepartment().getDepartmentName() + "%");
				}
				//部门id也不为空的时候  梁文杰 2013-07-20修改
				if(secrecyPerson.getDepartment().getDepartmentId() != null && !"".equals(secrecyPerson.getDepartment().getDepartmentId())) {
					hql.append(" and s.department.departmentId = :departmentId");
					params.put("departmentId", secrecyPerson.getDepartment().getDepartmentId() );
				}
			}
			// 职 务
			if (secrecyPerson.getOfficeDuty() != null && !"".equals(secrecyPerson.getOfficeDuty())) {
				hql.append(" and s.officeDuty like :officeDuty");
				params.put("officeDuty", "%" + secrecyPerson.getOfficeDuty() + "%");
			}
		}

		if( nowFlag!=null  ){
			hql.append(" and (s.secrecyStatus is null or s.secrecyStatus = :statusNow)");
			params.put("statusNow", nowFlag);
		}

		// 按创建时间降序排列
		hql.append(" order by s.modifyTime desc");

		if(psm!=null) {
//			return (List<SecrecyPerson>) this.getList(hql.toString(), psm, params);
			return (List<SecrecyPerson>) this.findList(hql.toString(), params, psm);
		}else {
//			return (List<SecrecyPerson>) this.getList(hql.toString(), params);
			return (List<SecrecyPerson>) this.findList(hql.toString(), params);
		}

	}


	/**
	 * 导出方法使用
	 */
	public List<SecrecyPerson> getAllList(SecrecyPerson secrecyPerson, Organ organ) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("from SecrecyPerson s where 1=1 ");

		// 当前单位涉密人员
		hql.append(" and s.organ.organId = :organId");
		params.put("organId", organ.getOrganId());

		// 拼接查询条件
		if (secrecyPerson != null) {
			// 姓 名
			if (secrecyPerson.getUserInfo().getName() != null
					&& !"".equals(secrecyPerson.getUserInfo().getName())) {
				hql.append(" and s.userInfo.name like :name");
				params.put("name", "%" + secrecyPerson.getUserInfo().getName() + "%");
			}
			// 涉密等级
			if (secrecyPerson.getSecrecyPersonLevel() != null
					&& secrecyPerson.getSecrecyPersonLevel().intValue() != allLeavel) {
				hql.append(" and s.secrecyPersonLevel = :secrecyPersonLevel");
				params.put("secrecyPersonLevel", secrecyPerson.getSecrecyPersonLevel());
			}
			// 部 门
			if (secrecyPerson.getDepartment()!=null && secrecyPerson.getDepartment().getDepartmentName() != null
					&& !"".equals(secrecyPerson.getDepartment().getDepartmentName())) {
				hql.append(" and s.department.departmentName like :departmentName");
				params.put("departmentName",
						"%" + secrecyPerson.getDepartment().getDepartmentName() + "%");
			}
			// 职 务
			if (secrecyPerson.getOfficeDuty() != null && !"".equals(secrecyPerson.getOfficeDuty())) {
				hql.append(" and s.officeDuty like :officeDuty");
				params.put("officeDuty", "%" + secrecyPerson.getOfficeDuty() + "%");
			}
		}

		// 按创建时间降序排列
		hql.append(" order by s.modifyTime desc");

//		return (List<SecrecyPerson>) this.getList(hql.toString(), params);
		return (List<SecrecyPerson>) this.findList(hql.toString(), params);
	}

	/**
	 * 涉密人员查询使用方法
	 */
	/*public List<SecrecyPerson> findPageAllList(PageSortModel<SecrecyPerson> psm, SecrecyPerson secrecyPerson,District district){
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("from SecrecyPerson s where 1=1 ");

		// 拼接查询条件
		if (secrecyPerson != null) {
			// 姓 名
			if (secrecyPerson.getUserInfo()!=null && secrecyPerson.getUserInfo().getName() != null
					&& !"".equals(secrecyPerson.getUserInfo().getName())) {
				hql.append(" and s.userInfo.name like :name");
				params.put("name", "%" + secrecyPerson.getUserInfo().getName() + "%");
			}
			// 涉密等级
			if (secrecyPerson.getSecrecyPersonLevel() != null
					&& secrecyPerson.getSecrecyPersonLevel().intValue() != allLeavel) {
				hql.append(" and s.secrecyPersonLevel = :secrecyPersonLevel");
				params.put("secrecyPersonLevel", secrecyPerson.getSecrecyPersonLevel());
			}
			// 部 门
			if (secrecyPerson.getDepartment()!=null) {
				//部门名称过滤  梁文杰 2013-07-20修改
				if(secrecyPerson.getDepartment().getDepartmentName() != null && !"".equals(secrecyPerson.getDepartment().getDepartmentName())) {
					hql.append(" and s.department.departmentName like :departmentName");
					params.put("departmentName",
							"%" + secrecyPerson.getDepartment().getDepartmentName() + "%");
				}
				//部门id也不为空的时候  梁文杰 2013-07-20修改
				if(secrecyPerson.getDepartment().getDepartmentId() != null && !"".equals(secrecyPerson.getDepartment().getDepartmentId())) {
					hql.append(" and s.department.departmentId = :departmentId");
					params.put("departmentId", secrecyPerson.getDepartment().getDepartmentId() );
				}
			}
			// 职 务
			if (secrecyPerson.getOfficeDuty() != null && !"".equals(secrecyPerson.getOfficeDuty())) {
				hql.append(" and s.officeDuty like :officeDuty");
				params.put("officeDuty", "%" + secrecyPerson.getOfficeDuty() + "%");
			}
		}

		hql.append(" and (s.secrecyStatus is null or s.secrecyStatus = :statusNow)");
		params.put("statusNow", BmpAction.SECRECY_STATUS_NOW);

		//行政区划不为空
		if(district!=null && district.getLayer()!=null) {
			hql.append(" and s.organ.district.layer = :layer");
			params.put("layer", district.getLayer());
		}


		// 按创建时间降序排列
		hql.append(" order by s.modifyTime desc");

		if(psm!=null) {
			return (List<SecrecyPerson>) this.findList(hql.toString(), params, psm);
		}else {
			return (List<SecrecyPerson>) this.findList(hql.toString(), params);
		}
	}*/

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

	@Override
	public List<SecrecyPerson> getPageAllList(PageSortModel psm,
			SecrecyPerson secrecyPerson, Organ organ, String showType)
			throws ParseException {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder(
				"FROM SecrecyPerson s where 1=1 and (s.secrecyStatus is null or s.secrecyStatus = :statusNow) ");

		params.put("statusNow", BmpAction.SECRECY_STATUS_NOW);
		// 当前单位涉密人员
		if( organ!=null ){
			organ = get(organ.getOrganId(), Organ.class);
			String layer = organ.getDistrict().getLayer();
			if( showType!=null && !"".equals(showType) ){
				if( "1".equals(showType) ){
					hql.append(" and s.organ.district.layer like :layer");
					params.put("layer", layer + "%");
				}else{
					hql.append(" and s.organ.organId = :organId");
					params.put("organId", organ.getOrganId());
				}
			}else{
				hql.append(" and s.organ.organId = :organId");
				params.put("organId", organ.getOrganId());
//				hql.append(" and s.organ.layer like :layer");
//				params.put("layer", layer + "%");
			}
		}

		// 拼接查询条件
		if (secrecyPerson != null) {
			// 姓 名
			if (secrecyPerson.getUserInfo().getName() != null
					&& !"".equals(secrecyPerson.getUserInfo().getName())) {
				hql.append(" and s.userInfo.name like :name");
				params.put("name", "%" + secrecyPerson.getUserInfo().getName() + "%");
			}
			// 涉密等级
			if (secrecyPerson.getSecrecyPersonLevel() != null
					&& secrecyPerson.getSecrecyPersonLevel().intValue() != allLeavel) {
				hql.append(" and s.secrecyPersonLevel = :secrecyPersonLevel");
				params.put("secrecyPersonLevel", secrecyPerson.getSecrecyPersonLevel());
			}
			// 部 门
			if (secrecyPerson.getDepartment()!=null && secrecyPerson.getDepartment().getDepartmentName() != null
					&& !"".equals(secrecyPerson.getDepartment().getDepartmentName())) {
				hql.append(" and s.department.departmentName like :departmentName");
				params.put("departmentName",
						"%" + secrecyPerson.getDepartment().getDepartmentName() + "%");
			}
			// 职 务
			if (secrecyPerson.getOfficeDuty() != null && !"".equals(secrecyPerson.getOfficeDuty())) {
				hql.append(" and s.officeDuty like :officeDuty");
				params.put("officeDuty", "%" + secrecyPerson.getOfficeDuty() + "%");
			}
		}

		// 按创建时间降序排列
		hql.append(" order by s.modifyTime desc");

//		return (List<SecrecyPerson>) this.getList(hql.toString(), psm, params);
		return (List<SecrecyPerson>) this.findList(hql.toString(), params, psm);

	}


	/**
	 * <p>
	 * 行政区涉密人员查询列表
	 * </p>
	 * <p>
	 * @author 牟远洋 2012 12 17 16:20
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
	 * @author mouyuanyang
	 * @version 1.0
	 * @param psm PageSortModel
	 * @param district 行政区
	 * @param status 状态
	 * @param organ 当前单位
	 * @param secrecyPerson 涉密人员对象
	 * @return List<SecrecyPerson>
	 */
	public List<SecrecyPerson> getDistrictList(PageSortModel psm, District district,
			Organ organ, String status, SecrecyPerson secrecyPerson) {
		Map<String, Object> params = new HashMap<String, Object>();

		StringBuilder hql =
				new StringBuilder("select s from SecrecyPerson s left join s.receiveOrgans ro" +
					" where 1=1 and (ro.organId = :currentOrganId or s.organ.organId = :organId)");
		params.put("currentOrganId", organ.getOrganId());
		params.put("organId", organ.getOrganId());

		if (secrecyPerson != null) {
			// 加入【姓名】查询条件
			if (secrecyPerson.getUserInfo().getName() != null
					&& !"".equals(secrecyPerson.getUserInfo().getName())) {
				hql.append(" and s.userInfo.name like :name");
				params.put("name", "%" + secrecyPerson.getUserInfo().getName() + "%");
			}
			// 加入【单位】查询条件
			if (secrecyPerson.getOrgan().getOrganName() != null
					&& !"".equals(secrecyPerson.getOrgan().getOrganName())) {
				hql.append(" and s.organ.organName like :organName");
				params.put("organName", "%" + secrecyPerson.getOrgan().getOrganName() + "%");
			}
			// 加入【涉密等级】查询条件
			if (secrecyPerson.getSecrecyPersonLevel() != null
					&& secrecyPerson.getSecrecyPersonLevel().intValue() != allLeavel) {
				hql.append(" and s.secrecyPersonLevel = :secrecyPersonLevel");
				params.put("secrecyPersonLevel", secrecyPerson.getSecrecyPersonLevel());
			}
		}

		// 加入【查询范围】查询条件
		if (status != null && district != null && district.getDistrictCode() != null) {
			// 行政区划内
			if ("2".equals(status)) {
				District d = this.get(district.getDistrictCode(), District.class);
				hql.append(" and s.organ.district.layer LIKE :layer ");
				params.put("layer", d.getLayer() + "%");
			} else if ("1".equals(status)) {
				hql.append(" and s.organ.district.districtCode = :districtCode ");
				params.put("districtCode", district.getDistrictCode());
			}
		}

		hql.append(" order by s.createTime DESC");
//		return (List<SecrecyPerson>) this.getList(hql.toString(), psm, params);
		return (List<SecrecyPerson>) this.findList(hql.toString(), params, psm);
	}

	/**
	 * <p>
	 * 获取部门涉密人员列表
	 * </p>
	 * <p>
	 * @author 牟远洋 2012 12 23 18:00:56
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
	 * @author mouyuanyang
	 * @version 1.0
	 * @param psm PageSortModel
	 * @param departmentId 部门Id
	 * @param organ 当前单位
	 * @return List<SecrecyPerson>
	 */
	public List<SecrecyPerson> getSecrecyPersonByDepartment(PageSortModel psm, String departmentId, Organ organ) {

		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("from SecrecyPerson s where 1=1 ");

		// 查询当前单位涉密人员
		if (organ != null) {
			hql.append(" and s.organ.organId = :organId");
			params.put("organId", organ.getOrganId());
		}

		// 查询当前部门涉密人员
		hql.append(" and s.department.departmentId = :departmentId");
		params.put("departmentId", departmentId);


		// 按创建时间降序排列
		hql.append(" order by s.createTime desc");

//		return (List<SecrecyPerson>) this.getList(hql.toString(), psm, params);
		return (List<SecrecyPerson>) this.findList(hql.toString(), params, psm);
	}

	/**
	 * <p>
	 * 获取部门涉密人员列表
	 * </p>
	 * <p>
	 * @author 牟远洋 2012 12 23 18:00:56
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
	 * @author mouyuanyang
	 * @version 1.0
	 * @param departmentId 部门ID
	 * @param organ 当前登录人员单位
	 * @return List<SecrecyPerson>
	 */
	@Override
	public List<SecrecyPerson> getSecrecyPersonByDepartment(String departmentId, Organ organ) {

		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("from SecrecyPerson s where 1=1 ");

		// 查询当前单位涉密人员
		hql.append(" and s.organ.organId = :organId");
		params.put("organId", organ.getOrganId());

		// 查询当前部门涉密人员
		hql.append(" and s.department.departmentId = :departmentId");
		params.put("departmentId", departmentId);

		//仅查询当前使用人员
		hql.append(" and ( s.secrecyStatus is null OR s.secrecyStatus = :secrecyStatus ) ");
		params.put("secrecyStatus", BmpAction.SECRECY_STATUS_NOW);


		// 按创建时间降序排列
		hql.append(" order by s.createTime desc");

//		return (List<SecrecyPerson>) this.getList(hql.toString(), params);
		return (List<SecrecyPerson>) this.findList(hql.toString(), params);
	}

	/**
	 * <p>
	 * 获取用户信息
	 * </p>
	 * <p>
	 * @author 牟远洋 2012 12 20 15:00
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
	 * @author mouyuanyang
	 * @version 1.0
	 * @param userInfoId 人员ID
	 * @return UserInfo
	 */
	public UserInfo getUserInfo(String userInfoId) {

		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("from UserInfo u where 1=1 ");

		// 查询指定人员信息
		hql.append(" and u.userInfoId = :userInfoId");
		params.put("userInfoId", userInfoId);
		return (UserInfo) getPersistProxy().getOrmPersistence().find(hql.toString(), params);
	}

	/**
	 * <p>
	 * 检查当前人员是否已经是涉密人员
	 * </p>
	 * <p>
	 * @author 牟远洋 2012 12 21 15:00
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
	 * @author mouyuanyang
	 * @version 1.0
	 * @param userInfoId 人员ID
	 * @param departmentId 部门ID
	 * @param organ 当前登录人员单位
	 * @return boolean
	 */
	public Boolean checkSecrecyPerson(String userInfoId, String departmentId, Organ organ) {

		boolean status = true;

		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("from SecrecyPerson s where 1=1 ");

		// 查询当前单位涉密人员
		hql.append(" and s.organ.organId = :organId");
		params.put("organId", organ.getOrganId());

		// 查询指定人员信息
		hql.append(" and s.userInfo.userInfoId = :userInfoId");
		params.put("userInfoId", userInfoId);

		// 部门查询条件
		if (departmentId != null && !"".equals(departmentId)) {
			hql.append(" and s.department.departmentId = :departmentId");
			params.put("departmentId", departmentId);
		}

		//排除已经脱密人员，即 仅检查当前活动人员
		hql.append(" and ( s.secrecyStatus = :secrecyStatus or s.secrecyStatus is null )" );
                params.put("secrecyStatus", BmpAction.SECRECY_STATUS_NOW);

		SecrecyPerson sp = this.unique(hql.toString(), params);

		if (sp == null) {
			status = false;
		}
		return status;
	}

	/**
	 * <p>
	 * 检查当前涉密人员是否已经存在
	 * </p>
	 * <p>
	 * @author 宋亚非 2013-4-19 09:50
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
	 * @author songyafei
	 * @version 1.0
	 * @param secrecyPerson 人员ID
	 * @return boolean
	 */
	public Boolean checkSecrecyPerson(SecrecyPerson secrecyPerson ){
		Boolean status = true;

		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("from SecrecyPerson s where 1=1 ");

		// 查询当前单位涉密人员
		hql.append(" and s.organ.organName = :organName");
		params.put("organName", secrecyPerson.getOrgan().getOrganName());

		// 查询指定人员信息
		hql.append(" and s.userInfo.name = :name");
		params.put("name", secrecyPerson.getUserInfo().getName());

		// 部门查询条件
		hql.append(" and s.department.departmentName = :departmentName");
		params.put("departmentName", secrecyPerson.getDepartment().getDepartmentName());

		SecrecyPerson sp = this.unique(hql.toString(), params);

		if (sp == null) {
			status = false;
		}

		return status;
	}

	/**
	 * <p>
	 * 保存人员信息
	 * </p>
	 * <p>
	 * @author 牟远洋 2012 12 21 15:00
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
	 * @author mouyuanyang
	 * @version 1.0
	 * @param userInfo 人员对象
	 * @param currentUser 当前登录人员
	 * @param department 部门
	 * @return userInfo
	 */
	public UserInfo saveUserInfo(UserInfo userInfo, User currentUser, Department department) {
		//如果单位下的人员存在，直接返回该人员
		List<UserInfo> userInfoList = (List<UserInfo>) OrganizationContext.getInstance().getMemberService()
				.getByName(userInfo.getName(), currentUser.getOrgan());
		if (CollectionUtils.isNotEmpty(userInfoList)) {
		        UserInfo userInfoDb = userInfoList.get(0);
		        userInfoDb.setName(userInfo.getName());
	                userInfoDb.setSex(userInfo.getSex());
	                userInfoDb.setNation(userInfo.getNation());
	                userInfoDb.setBirthDate(userInfo.getBirthDate());
	                userInfoDb.setLearningLevel(userInfo.getLearningLevel());
	                userInfoDb.setIdentityCard(userInfo.getIdentityCard());
	                userInfoDb.setPolity(userInfo.getPolity());
	                userInfoDb.setDepartment(department);
	                userInfoDb.setStaff(userInfo.getStaff());
	                userInfoDb.setMobile(userInfo.getMobile());
	                userInfoDb.setPost(userInfo.getPost());
	                userInfoDb.setAdministrativeLevel(userInfo.getAdministrativeLevel());
	                userInfoDb.setTechnicTitleLevel(userInfo.getTechnicTitleLevel());
	                if( userInfoDb.getCreatePerson()==null ){
	                        userInfoDb.setCreatePerson(currentUser);
	                        userInfoDb.setCreateTime(new Date());
	                }
	                // 通用字段
	                userInfoDb.setModifyPerson(currentUser);
	                userInfoDb.setModifyTime(new Date());

	                userInfoDb.update();
			return userInfoDb;
		}

		// 该人员不存在，新增人员信息
		userInfo.setUserInfoId(null);
		userInfo.setDepartment(department);
		userInfo.setOrgan(currentUser.getOrgan());
		userInfo.setRemark(new Date().toString() + "产生。创建者：" + currentUser.getUserName()
				+ "(UserID:" + currentUser.getUserId() + ")；创建环境：在【保密业务】-【机关涉密人员】-【新增涉密人员】时填写人员时创建。");
		userInfo.setCreatePerson(currentUser);
		userInfo.setModifyPerson(currentUser);
		userInfo.setCreateTime(new Date());
		userInfo.setModifyTime(new Date());
		userInfo.setNationality(Nationality.CN);

		userInfo.save();
		return userInfo;
	}

	/**
	 * <p>
	 * 更新人员信息
	 * </p>
	 * <p>
	 * @author 牟远洋 2012 12 21 15:00
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
	 * @author mouyuanyang
	 * @version 1.0
	 * @param userInfoDb 抽出人员对象
	 * @param userInfoReceive 页面接收人员对象
	 * @param currentUser 当前登录人员
	 * @param department 部门
	 */
	public void updateUserInfo(UserInfo userInfoDb, UserInfo userInfoReceive,
			User currentUser, Department department) {
		//如果单位下的人员存在，直接返回该人员
		List<UserInfo> userInfoList = (List<UserInfo>) OrganizationContext.getInstance().getMemberService()
				.getByName(userInfoDb.getName(), currentUser.getOrgan());
		if (CollectionUtils.isNotEmpty(userInfoList)) {
			userInfoDb = userInfoList.get(0);
		}
		// 设置业务字段
//		userInfoDb.setName(userInfoReceive.getName());
		userInfoDb.setSex(userInfoReceive.getSex());
		userInfoDb.setNation(userInfoReceive.getNation());
		userInfoDb.setBirthDate(userInfoReceive.getBirthDate());
		userInfoDb.setLearningLevel(userInfoReceive.getLearningLevel());
		userInfoDb.setIdentityCard(userInfoReceive.getIdentityCard());
		userInfoDb.setPolity(userInfoReceive.getPolity());
		userInfoDb.setDepartment(department);
		userInfoDb.setStaff(userInfoReceive.getStaff());
		userInfoDb.setMobile(userInfoReceive.getMobile());
		userInfoDb.setPost(userInfoReceive.getPost());
		userInfoDb.setAdministrativeLevel(userInfoReceive.getAdministrativeLevel());
		userInfoDb.setTechnicTitleLevel(userInfoReceive.getTechnicTitleLevel());
		// 通用字段
		userInfoDb.setModifyPerson(currentUser);
		userInfoDb.setModifyTime(new Date());

		userInfoDb.update();
	}

	/**
	 * <p>
	 * 保存部门信息
	 * </p>
	 * <p>
	 * @author 牟远洋 2012 12 21 15:00
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
	 * @author mouyuanyang
	 * @version 1.0
	 * @param departmentName 部门名称
	 * @param currentUser 当前人员对象
	 * @return department
	 */
	public Department saveDepartment(String departmentName, User currentUser) {
		Department deptDb = OrganizationContext.getInstance().getDepartmentService()
				.getByName(departmentName, currentUser.getOrgan());
		if (deptDb != null) {
			return deptDb;
		}
		Department department = new Department();
		department.setDepartmentName(departmentName);
		department.setOrgan(currentUser.getOrgan());
		department.setCreateTime(new Date());
		department.setCreatePerson(currentUser);
		department.setModifyTime(new Date());
		department.setModifyPerson(currentUser);
		department.setParent(currentUser.getOrgan().getDepartment());

		department.save();
		return department;
	}

	/**
	 * <p>
	 * 保存部门信息
	 * </p>
	 * <p>
	 * @author 宋亚非 2013-04-19 10:36
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
	 * @author songyafei
	 * @version 1.0
	 * @param departmentName 部门名称
	 * @param user 人员对象
	 * @return department
	 */
	public Department saveDepartment(String departmentName, User currentUser, Organ organ){
		Department deptDb = OrganizationContext.getInstance().getDepartmentService()
				.getByName(departmentName, currentUser.getOrgan());
		if (deptDb != null) {
			return deptDb;
		}
		Department department = new Department();
		department.setDepartmentName(departmentName);
		department.setOrgan(organ);
		department.setCreateTime(new Date());
		department.setCreatePerson(currentUser);
		department.setModifyTime(new Date());
		department.setModifyPerson(currentUser);
		department.setParent(currentUser.getOrgan().getDepartment());

		department.save();
		return department;
	}

	/**
	 * <p>
	 * 导入的word信息
	 * </p>
	 * <p>
	 * @author 刘椿成 2012 12 21 15:00
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
	 * @version 1.0
	 * @param upLoadFile 文档对象
	 * @param currentUser 当前人员对象
	 * @throws IOException
	 */
	@Override
	public Map<Boolean, List<String>> saveImportData(UploadFile upLoadFile,
			User currentUser) throws IOException {
		boolean flag = true;
		Map<Boolean, List<String>> rsMap = new HashMap<Boolean, List<String>>();
		if (upLoadFile != null) {
			File importFile = upLoadFile.getFile();
			if (importFile !=null) {
				DataSource source = new WordDataSource(importFile);
				if (source != null) {
					// 验证数据有效性
					Map<Boolean, List<String>> chkDataMap = chkImportData(source,currentUser);
					Set<Entry<Boolean, List<String>>> chkEntrySet = chkDataMap.entrySet();
					Iterator<Entry<Boolean, List<String>>> chkIte = chkEntrySet.iterator();
					Map.Entry<Boolean, List<String>> errorEntry = chkIte.next();
					List<String> errorInfoEntry = errorEntry.getValue();
					if (errorInfoEntry.size()>0) {
						flag = false;
						msgList.add("导入数据存在以上错误信息，请修正后重试。");
						LOG.error("导入数据存在以上错误信息，请修正后重试。");
					}else{
						// 保存数据
						Map<SecrecyPerson, List<String>> dataMap = createImportData(source,currentUser);
						Set<Entry<SecrecyPerson, List<String>>> entrySet = dataMap.entrySet();
						Iterator<Entry<SecrecyPerson, List<String>>> ite = entrySet.iterator();
						Map.Entry<SecrecyPerson, List<String>> entry = ite.next();
						SecrecyPerson secPerson = entry.getKey();
						if (secPerson != null) {
							save(secPerson);
						}
					}
				}
			}
		}else{
			msgList.add("未找到导入文件");
			LOG.error("未找到导入文件");
		}
		rsMap.put(flag, msgList);
		return rsMap;
	}

	/**
	 * <p>
	 * 解析word信息
	 * </p>
	 * <p>
	 * @author 刘椿成 2013 1 18 15:00
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * <p>
	 * copyright liucc 2013, all rights reserved.
	 * </p>
	 * @author liucc
	 * @version 1.0
	 * @param source 数据源
	 * @param currentUser 当前人员对象
	 * @throws IOException
	 */
	private Map<SecrecyPerson, List<String>> createImportData(
			DataSource source, User currentUser) {
		Map<SecrecyPerson, List<String>> dataMap = new HashMap<SecrecyPerson, List<String>>();
		SecrecyPerson secrecyPerson = new SecrecyPerson();
		UserInfo userReceiveInfo = new UserInfo();
		DictionaryService dictionaryService = DictionaryContext.getInstance().getDictionaryService();
		// 获取第一个表格
		DataSet dataSet = source.getDataSet(0);
		// 第一个表格第一行数据
		DataRecord record = dataSet.getDataRecord(0);
		// 姓名
		String secrecyPersonName = record.get("1").toString();
		userReceiveInfo.setName(secrecyPersonName);

		// 性 别
		String secrecyPersonSex = record.get("3").toString();
		List<DictionaryOption> sexList = dictionaryService.getOptionList("person", "sex");
		for (DictionaryOption d : sexList) {
			if(d.getOptionText().equals(secrecyPersonSex)){
				userReceiveInfo.setSex(d.getOptionValue().toString());
				break;
			}
		}

		// 第一个表格第二行数据
		DataRecord recordTwo = dataSet.getDataRecord(1);
		String nation = recordTwo.get("1");
		List<DictionaryOption> nationList = dictionaryService.getOptionList("person", "nation");
		for (DictionaryOption d : nationList) {
			if(d.getOptionText().equals(nation)){
				String mingzu = PinYin.getPinYin(d.getOptionText());
				String name = mingzu.substring(0, mingzu.length() - 2).toUpperCase();
				userReceiveInfo.setNation(Nation.valueOf(name));
				break;
			}
		}

		String birthTimeDate = recordTwo.get("3").toString().trim();
		String formatTime = "yyyy-MM-dd";
		formatTime = formatTime.replaceFirst("yyyy", birthTimeDate.substring(0, 4));
		formatTime = formatTime.replaceFirst("MM", birthTimeDate.substring(5, 7));
		formatTime = formatTime.replaceFirst("dd", birthTimeDate.substring(8, 10));
		Date birthDate =null;
		birthDate =java.sql.Date.valueOf(formatTime);
		userReceiveInfo.setBirthDate(birthDate);

		// 第一个表格第三行数据
		DataRecord recordTh = dataSet.getDataRecord(2);
		String learningLevel = recordTh.get("1").toString().trim();
		List<DictionaryOption> learningList = dictionaryService.getOptionList("person", "learning_level");
		for (DictionaryOption d : learningList) {
			if(d.getOptionText().equals(learningLevel)){
				userReceiveInfo.setLearningLevel(d.getOptionValue());
				break;
			}
		}

		String identityCard = recordTh.get("3").toString().trim();
		userReceiveInfo.setIdentityCard(identityCard);

		// 第一个表格第四行数据
		DataRecord recordFor = dataSet.getDataRecord(3);
		String politicalStatus = recordFor.get("1").toString().trim();
		secrecyPerson.setPoliticalStatus(politicalStatus);

		Department department = new Department();
		String departmentName = recordFor.get("3").toString().trim();
		department.setDepartmentName(departmentName);

		//Department departmentQuery = (Department) getList(hqlDe.toString(),null, deParams);
		Department departmentQuery =getDepartmentByName(departmentName, currentUser.getOrgan());
		if (departmentQuery !=null) {
			department = departmentQuery;
			secrecyPerson.setDepartment(department);
		}else{
			department = saveDepartment(departmentName, currentUser);
			secrecyPerson.setDepartment(department);
		}

		// 第一个表格第五行数据
		DataRecord recordFiv = dataSet.getDataRecord(4);
		String officeDuty = recordFiv.get("1").toString().trim();
		secrecyPerson.setOfficeDuty(officeDuty);

		String post = recordFiv.get("3").toString().trim();
		secrecyPerson.setPost(post);

		// 第一个表格第六行数据
		DataRecord recordSix = dataSet.getDataRecord(5);
		String staff = recordSix.get("1").toString().trim();
		List<DictionaryOption> staffList = dictionaryService.getOptionList("person", "staff");
		for (DictionaryOption d : staffList) {
			if(d.getOptionText().equals(staff)){
				userReceiveInfo.setStaff(d.getOptionValue());
				break;
			}
		}

		String secrecyPersonLevel = recordSix.get("3").toString().trim();
		List<DictionaryOption> secrecyLevelList = dictionaryService.getOptionList("bmp", "secrecy_level_person");
		for (DictionaryOption d : secrecyLevelList) {
			if(d.getOptionText().equals(secrecyPersonLevel)){
				secrecyPerson.setSecrecyPersonLevel(d.getOptionValue());
				break;
			}
		}

		// 第一个表格第七行数据
		DataRecord recordSev = dataSet.getDataRecord(6);
		String officePhone = recordSev.get("1").toString().trim();
		secrecyPerson.setOfficePhone(officePhone);

		String mobile = recordSev.get("3").toString().trim();
		userReceiveInfo.setMobile(mobile);

		// 第一个表格第八行数据
		DataRecord recordEt = dataSet.getDataRecord(7);
		String firstWorkDateValue = recordEt.get("1").toString().trim();
		String formatTimeFwd = "yyyy-MM-dd";
		formatTimeFwd = formatTimeFwd.replaceFirst("yyyy", firstWorkDateValue.substring(0, 4));
		formatTimeFwd = formatTimeFwd.replaceFirst("MM", firstWorkDateValue.substring(5, 7));
		formatTimeFwd = formatTimeFwd.replaceFirst("dd", firstWorkDateValue.substring(8, 10));
		Date firstWorkDate =null;
		firstWorkDate =java.sql.Date.valueOf(formatTimeFwd);
		secrecyPerson.setFirstWorkDate(firstWorkDate);

		// 第一个表格第九行数据
		DataRecord recordNi = dataSet.getDataRecord(8);
		String secSignBookTimeValue = recordNi.get("1").toString().trim();
		String formatTimeSbt = "yyyy-MM-dd";
		formatTimeSbt = formatTimeSbt.replaceFirst("yyyy", secSignBookTimeValue.substring(0, 4));
		formatTimeSbt = formatTimeSbt.replaceFirst("MM", secSignBookTimeValue.substring(5, 7));
		formatTimeSbt = formatTimeSbt.replaceFirst("dd", secSignBookTimeValue.substring(8, 10));
		Date secSignBookTime =null;
		secSignBookTime =java.sql.Date.valueOf(formatTimeSbt);
		secrecyPerson.setSecSignBookTime(secSignBookTime);

		// 第一个表格第十行数据
		DataRecord recordTe = dataSet.getDataRecord(9);
		String secUppostTimeValue = recordTe.get("1").toString().trim();
		String formatTimeSut = "yyyy-MM-dd";
		formatTimeSut = formatTimeSut.replaceFirst("yyyy", secUppostTimeValue.substring(0, 4));
		formatTimeSut = formatTimeSut.replaceFirst("MM", secUppostTimeValue.substring(5, 7));
		formatTimeSut = formatTimeSut.replaceFirst("dd", secUppostTimeValue.substring(8, 10));
		Date secUppostTime =null;
		secUppostTime =java.sql.Date.valueOf(formatTimeSut);
		secrecyPerson.setSecUppostTime(secUppostTime);

		// 第一个表格第十一行数据
		DataRecord recordElv = dataSet.getDataRecord(10);
		String organCheckOpinion = recordElv.get("1").toString().trim();
		secrecyPerson.setOrganCheckOpinion(organCheckOpinion);


		// 获取第二个表格
		DataSet dataSetResume = source.getDataSet(1);
		// 第一个表格第一行数据
		DataRecord recordResume = dataSetResume.getDataRecord(1);
		String resume = recordResume.get("0").toString().trim();
		secrecyPerson.setResume(resume);

		//	查询新增的涉密人员用户信息是否存在，判断是新增该用户信息还是更新？？？
		UserInfo userInfoQuery = this.getUserInfoByName(userReceiveInfo.getName(), currentUser.getOrgan());
		if (userInfoQuery!=null) {
			// 设置业务字段
			userInfoQuery.setName(userReceiveInfo.getName());
			userInfoQuery.setSex(userReceiveInfo.getSex());
			userInfoQuery.setNation(userReceiveInfo.getNation());
			userInfoQuery.setBirthDate(userReceiveInfo.getBirthDate());
			userInfoQuery.setLearningLevel(userReceiveInfo.getLearningLevel());
			userInfoQuery.setIdentityCard(userReceiveInfo.getIdentityCard());
			userInfoQuery.setPolity(userReceiveInfo.getPolity());
			userInfoQuery.setDepartment(department);
			userInfoQuery.setStaff(userReceiveInfo.getStaff());
			userInfoQuery.setMobile(userReceiveInfo.getMobile());
			userInfoQuery.setPost(userReceiveInfo.getPost());
			// 通用字段
			userInfoQuery.setModifyPerson(currentUser);
			userInfoQuery.setModifyTime(new Date());
			userInfoQuery.update();
			secrecyPerson.setUserInfo(userInfoQuery);
		}else{
			UserInfo userInfoSaveValue = saveUserInfo(userReceiveInfo, currentUser, department);
			secrecyPerson.setUserInfo(userInfoSaveValue);
		}
		secrecyPerson.setCreatePerson(currentUser);
		secrecyPerson.setCreateTime(new Date());
		secrecyPerson.setOrgan(currentUser.getOrgan());

		dataMap.put(secrecyPerson, msgList);
		return dataMap;
	}

	/**
	 * <p>
	 * 通过部门名称查询部门
	 * </p>
	 * <p>
	 * @author 刘椿成 2013 1 18 15:00
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * <p>
	 * copyright liucc 2013, all rights reserved.
	 * </p>
	 * @author liucc
	 * @version 1.0
	 * @param departmentName 查询参数
	 * @param organ 当前人员对象单位
	 * @throws IOException
	 */
	private Department getDepartmentByName(String departmentName, Organ organ) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("from Department dt where 1=1 ");
		hql.append(" and dt.departmentName = :departmentName");
		params.put("departmentName", departmentName);
		hql.append(" and dt.organ.organId = :organId");
		params.put("organId", organ.getOrganId());

		return (Department) getPersistProxy().getOrmPersistence().find(hql.toString(), params);
	}

	/**
	 * <p>
	 * 通过用户名称查询用户
	 * </p>
	 * <p>
	 * @author 刘椿成 2013 1 18 15:00
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * <p>
	 * copyright liucc 2013, all rights reserved.
	 * </p>
	 * @author liucc
	 * @version 1.0
	 * @param userInfoName 查询参数
	 * @param organ 当前人员对象单位
	 * @throws IOException
	 */
	private UserInfo getUserInfoByName(String userInfoName, Organ organ) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("from UserInfo uif where 1=1 ");
		hql.append(" and uif.name = :name");
		params.put("name", userInfoName);
		hql.append(" and uif.organ.organId = :organId");
		params.put("organId", organ.getOrganId());
		return (UserInfo) getPersistProxy().getOrmPersistence().find(hql.toString(), params);
	}

	/**
	 * <p>
	 * 通过用户名，身份证查询涉密人员
	 * </p>
	 * <p>
	 * @author 刘椿成 2013 1 18 15:00
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * <p>
	 * copyright liucc 2013, all rights reserved.
	 * </p>
	 * @author liucc
	 * @version 1.0
	 * @param name 查询参数人员姓名
	 * @param idCard 身份证
	 * @param organ 当前人员对象单位
	 * @throws IOException
	 */
	private SecrecyPerson getSecPersonByName(String name,String idCard, Organ organ) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("from SecrecyPerson sp where 1=1 ");
		hql.append(" and sp.userInfo.name = :name");
		params.put("name", name);

		if (idCard!=null && StringUtils.isNotEmpty(idCard)) {
			hql.append(" and sp.userInfo.identityCard = :identityCard");
			params.put("identityCard", idCard);
		}
		hql.append(" and sp.organ.organId = :organId");
		params.put("organId", organ.getOrganId());

		return (SecrecyPerson) getPersistProxy().getOrmPersistence().find(hql.toString(), params);
	}

	/**
	 * <p>
	 * 导入前检查数据合法性
	 * </p>
	 * <p>
	 * @author 刘椿成 2013 1 18 15:00
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * <p>
	 * copyright liucc 2013, all rights reserved.
	 * </p>
	 * @author liucc
	 * @version 1.0
	 * @param source 数据源
	 * @param currentUser 当前人员对象
	 */
	private Map<Boolean, List<String>> chkImportData(DataSource source,User currentUser) {
		// 设置一个公共判断
		Boolean chkOk = true;
		Map<Boolean, List<String>> errorMap = new HashMap<Boolean, List<String>>();
		// 获取第一个表格
		DataSet dataSet = source.getDataSet(0);
		String name = dataSet.getDataRecord(0).get("1").toString().trim();

		// 1.1检查用户是否存在
		msgList = isEmpty(msgList, name, "第1页中的【姓名】必须填写。");
		UserInfo ui = getUserInfoByName(name, currentUser.getOrgan());
		if (ui!=null) {
			msgList.add("当前导入人员姓名与系统用户【"+ name +"】重复，建议核对后手动录入该人员信息。");
		}

		// 1.2检查性别填写是否正确
		String sex = dataSet.getDataRecord(0).get("3").toString().trim();
		msgList = isEmpty(msgList, sex, "第1页中的【性别】必须填写，正确形式为：“男”或“女”。");
		msgList = isContains(msgList, sex,"男,女", "第1页中的【性别】填写错误，正确形式为：“男”或“女”。");

		// 1.3检查民族填写是否正确
		String nation = dataSet.getDataRecord(1).get("1").toString().trim();
		msgList = isEmpty(msgList, nation, "第1页中的【民族】必须填写，正确形式为：“汉族”、“彝族”、“蒙古族”、“朝鲜族”等56个民族。");
		msgList = isContains(msgList, nation,"汉族,苗族,壮族,彝族,回族,蒙古族,藏族,维吾尔族,布依族,藏族,朝鲜族,满族,侗族,瑶族,白族,土家族,哈尼族,哈萨克族,傣族,黎族,傈僳族,佤族,畲族,高山族,京族,拉祜族,水族,东乡族,纳西族,景颇族,柯尔克孜族,土族,达斡尔族,仫佬族,羌族,布朗族,撒拉族,毛南族,仡佬族,锡伯族,阿昌族,普米族,塔吉克族,乌孜别克族,俄罗斯族,鄂温克族,德昂族,保安族,裕固族,塔塔尔族,独龙族,鄂伦春族,赫哲族,门巴族,珞巴族,基诺族", "第1页中的【民族】填写错误，正确形式为：“汉族”、“彝族”、“蒙古族”、“朝鲜族”等56个民族。");

		// 1.4检查学历填写是否正确
		String learningLevel = dataSet.getDataRecord(2).get("1").toString().trim();
		msgList = isEmpty(msgList, learningLevel, "第1页中的【学历】必须填写，正确形式为：“无”、“小学”、“初中”、“高中”、“中专”、“大专”、“本科”、“研究生”。");
		msgList = isContains(msgList,learningLevel,"无,小学,初中,高中,中专,大专,本科,研究生", "第1页中的【学历】填写错误，正确形式为：“无”、“小学”、“初中”、“高中”、“中专”、“大专”、“本科”、“研究生”。");

		// 1.5检查部门是否填写
		String departmentName = dataSet.getDataRecord(3).get("3").toString().trim();
		msgList = isEmpty(msgList, departmentName, "第1页中的【部门】必须填写。");

		// 1.6检查编制填写是否正确
		String staff = dataSet.getDataRecord(5).get("1").toString().trim();
		msgList = isEmpty(msgList, staff, "第1页中的【行政职务】必须填写，正确形式为：“行政”、“事业”、“工勤”、“应聘”。");
		msgList = isContains(msgList,staff,"行政,事业,工勤,应聘", "第1页中的【行政职务】填写错误，正确形式为：“行政”、“事业”、“工勤”、“应聘”。");

		// 1.7检查涉密等级填写是否正确
		String secrecyPersonLevel = dataSet.getDataRecord(5).get("3").toString().trim();
		msgList = isEmpty(msgList, secrecyPersonLevel, "第1页中的【涉密等级】必须填写。正确的形式为“一般”、“重要”、“核心”。");
		msgList = isContains(msgList, secrecyPersonLevel, "一般,重要,核心", "第1页中的【涉密等级】填写有误，正确的形式为“一般”、“重要”、“核心”。");

		// 1.8检查涉密人员信息是否存在。
		String identityCard = dataSet.getDataRecord(2).get("3").toString().trim();
		SecrecyPerson secPerson = getSecPersonByName(name,identityCard, currentUser.getOrgan());
		if (secPerson!=null) {
			msgList.add("当前导入人员【"+ name +"】已经在涉密人员库中，建议不导入或核对后手动更新原涉密人员信息。");
		}

		// 返回判断错误信息
		for(String it :msgList) {
			if (it!=null) {
				chkOk = false;
			}
		}
		errorMap.put(chkOk, msgList);
		return errorMap;
	}

	/**
	 * <p>
	 * 判断系统数据中是否包含输入数据
	 * </p>
	 * @param msgList 信息list
	 * @param str 传入的输入值
	 * @param itemStr 传入的系统值
	 * @param errorInfo 错误信息
	 * @return
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>刘椿成2013-01-10 15:46:13
	 * </ul>
	 * </blockquote>
	 * <p>
	 * copyright liucc 2013, all rights reserved.
	 * </p>
	 * @author liucc
	 * @since 1.0
	 * @version 1.0
	 */
	private List<String> isContains(List<String> msgList,
			String str, String itemStr, String errorInfo) {
		Boolean isOk = false;

	 	if (itemStr.contains(str)) {
			 isOk = false;
		} else {
			isOk = true;
		}

		// 如果没有包含，则设置错误信息
		if(isOk!=false){
			msgList.add(errorInfo);
		}
		return msgList;
	}

	/**
	 * <p>
	 * 判断是否为空，为空则在msgList中加入一条错误信息。不为空，则返回原始的msgList。
	 * </p>
	 * @param str 传入的值
	 * @param errorInfo 错误信息
	 * <p>
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>刘椿成2013-01-10 15:46:13
	 * </ul>
	 * </blockquote>
	 * <p>
	 * copyright liucc 2013, all rights reserved.
	 * </p>
	 * @author liucc
	 * @since 1.0
	 * @version 1.0
	 */
	private List<String> isEmpty(List<String> msgList, String str, String errorInfo) {
		if (str== null || StringUtils.isEmpty(str)) {
			msgList.add(errorInfo);
			LOG.error(errorInfo);
		}
		return msgList;
	}

	/**
	 * <p>
	 * 涉密人员统计
	 * </p>
	 * <blockquote>
	 * <h4>加入注释</h4>
	 * <ul>
	 * <li>刘椿成 2013-01-10 15:46:13
	 * </ul>
	 * </blockquote>
	 * <p>
	 * copyright liucc 2013, all rights reserved.
	 * </p>
	 * @author liucc
	 * @since 1.0
	 * @version 1.0
	 */
	@Override
	public List<Long> countSecrecyPersonData(Organ organ) {
		// 涉密人员统计
		List<Long> secrecyPersonArgs = new ArrayList<Long>();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("select count(sp) from SecrecyPerson sp " +
				" where 1=1 and (sp.secrecyStatus is null or sp.secrecyStatus = :statusNow) ");
		params.put("statusNow", BmpAction.SECRECY_STATUS_NOW);

		hql.append(" and sp.secrecyPersonLevel = :secrecyPersonLevel");
		hql.append(" and sp.organ.organId = :organId");

		params.put("secrecyPersonLevel", 1000);
		params.put("organId", organ.getOrganId());
		Long countNum = this.unique(hql.toString(), params, Long.class);
		secrecyPersonArgs.add(0, countNum);

		params.put("secrecyPersonLevel", 3);
		countNum =  this.unique(hql.toString(), params, Long.class);
		secrecyPersonArgs.add(1, countNum);

		params.put("secrecyPersonLevel", 2);
		countNum =  this.unique(hql.toString(), params, Long.class);
		secrecyPersonArgs.add(2, countNum);

		params.put("secrecyPersonLevel", 1);
		countNum =  this.unique(hql.toString(), params, Long.class);
		secrecyPersonArgs.add(3, countNum);
		return secrecyPersonArgs;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void saveOrUpdateBatch(List<SecrecyPerson> secrecyPersonList,
			String receiveOrganId) {
		if (secrecyPersonList == null) {
			return;
		}
		for (SecrecyPerson secrecyPerson : secrecyPersonList) {
			SecrecyPerson sp = this.get(secrecyPerson.getSecrecyPersonId());
			if (null != sp) { // null != eno 说明已经保存过，现在只需更新
//				sp.setInceptTime(new Date());
//				sp.setReportOrganState(Reportable.REPORT_ALREADY);
//				sp.setReportState(Reportable.REPORT_ALREADY);
//				sp.setReportOrgan(secrecyPerson.getReportOrgan());
//				sp.getReceiveOrgans().add(this.get(receiveOrganId, Organ.class));
				try {
					BeanUtils.copyProperties(sp, secrecyPerson);
				} catch(Exception e) {
					LOG.debug("属性转换异常:" + e.getMessage());
				}
				this.update(sp);
			} else { // 第一次保存
//				secrecyPerson.setInceptTime(new Date());
//				secrecyPerson.setReportOrganState(SecrecyPerson.REPORT_YET);
//				secrecyPerson.setReportState(SecrecyPerson.REPORT_YET);
//				Set<Organ> receiveOrganSet = new HashSet<Organ>();
//				if (receiveOrganId != null && !"".equals(receiveOrganId)) {
//					receiveOrganSet.add(this.get(receiveOrganId, Organ.class));
//				}
//				secrecyPerson.setReceiveOrgans(receiveOrganSet);
//				secrecyPerson.setReportOrgan(secrecyPerson.getReportOrgan());
				this.save(secrecyPerson);
			}
		}
	}

	@Override
	public List<SecrecyPerson> getIndexPage(PageSortModel psm,
			Organ organ,Integer secrecyLevel,String districtCode, SecrecyPerson secrecyPerson, String showType) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder(
				"FROM SecrecyPerson s where 1=1 and (s.secrecyStatus is null or s.secrecyStatus = :statusNow) ");

		params.put("statusNow", BmpAction.SECRECY_STATUS_NOW);

		if(organ !=null){
			if( showType!=null && "1".equals(showType) ){
				hql.append("and s.organ.district.layer like :layer ");
				params.put("layer", organ.getDistrict().getLayer()+"%");
			}else{
				hql.append("and s.organ.organId = :organId ");
				params.put("organId", organ.getOrganId());
			}
		}
		hql.append("and s.secrecyPersonLevel = :secrecyLevel ");
		params.put("secrecyLevel", secrecyLevel);
		//hql.append(" and s.organ.district.districtCode = :districtCode");
		//params.put("districtCode", districtCode);

		if( secrecyPerson!=null ){
			if( secrecyPerson.getUserInfo()!=null && secrecyPerson.getUserInfo().getName()!=null && !"".equals(secrecyPerson.getUserInfo().getName()) ){
				hql.append(" and s.userInfo.name like :name ");
				params.put("name", "%" + secrecyPerson.getUserInfo().getName() + "%");
			}

			if( secrecyPerson.getDepartment()!=null && secrecyPerson.getDepartment().getDepartmentName()!=null && !"".equals(secrecyPerson.getDepartment().getDepartmentName()) ){
				hql.append(" and s.department.departmentName like :departmentName ");
				params.put("departmentName", "%" + secrecyPerson.getDepartment().getDepartmentName() + "%");
			}
			if( secrecyPerson.getOfficeDuty() != null && !"".equals(secrecyPerson.getOfficeDuty()) ){
				hql.append(" and s.officeDuty like :officeDuty");
				params.put("officeDuty", "%" + secrecyPerson.getOfficeDuty() + "%");
			}
		}

		return (List<SecrecyPerson>) findList(hql.toString(),params, psm);
	}

	/**
	 * <p>
	 * 保存涉密人员信息
	 * </p>
	 * <blockquote>
	 * <h4>保存</h4>
	 * <ul>
	 * <li>宋亚非 2013-04-19 10:46:13
	 * </ul>
	 * </blockquote>
	 * <p>
	 * copyright songyafei 2013, all rights reserved.
	 * </p>
	 * @author songyafei
	 * @since 1.0
	 * @version 1.0
	 */
	@Override
	public Boolean saveSecrecyPerson(List<SecrecyPerson> secrecyPersonList, User currentUser) {
		Boolean flag = true;
		for( SecrecyPerson secrecyPerson : secrecyPersonList ){
			// 部门(判断存在与否)
			if (secrecyPerson.getDepartment() != null && LangUtils.isNotEmpty(secrecyPerson.getDepartment().getDepartmentName())) {
				Map<String, Object> params = new HashMap<String, Object>();
				StringBuilder hql = new StringBuilder("from Department d where 1=1 ");
				hql.append(" and d.organ.organId = :organId");
				params.put("organId", secrecyPerson.getOrgan().getOrganId());

				hql.append(" and d.departmentName = :departmentName");
				params.put("departmentName", secrecyPerson.getDepartment().getDepartmentName());

				List<Department> departmentList =  this.getPersistProxy().getOrmPersistence().findList(hql.toString(), params);
				if( departmentList!=null && departmentList.size()>0 ){
					secrecyPerson.setDepartment(departmentList.get(0));
				}else{
					Department department = this.saveDepartment(secrecyPerson.getDepartment().getDepartmentName() ,currentUser ,secrecyPerson.getOrgan());
					secrecyPerson.setDepartment(department);
				}
			}
			Department depart = secrecyPerson.getDepartment();

			// 新增人员信息
			UserInfo userInfo = this.saveUserInfo(secrecyPerson.getUserInfo(), currentUser, depart);
			secrecyPerson.setUserInfo(userInfo);
			// 设置涉密人员通用字段
			secrecyPerson.setOrgan(currentUser.getOrgan());
			secrecyPerson.setCreateTime(new Date());
			secrecyPerson.setModifyTime(new Date());
			secrecyPerson.setCreatePerson(currentUser);
			secrecyPerson.setModifyPerson(currentUser);
			try {
				// 保存机关涉密人员实体
				//修改人：宋亚非   修改原因：数据库统一至一个库后，取消上报功能，填写信息后状态即为已上报   修改时间：2013-06-03
				//secrecyPerson.setReportState(ReportState.REPORT_NO);
				secrecyPerson.setReportState(ReportState.REPORT_YES);
				this.save(secrecyPerson);
				flag = true;
			} catch (Exception e) {
				flag = false;
			}
		}
		return flag;
	}
	//*************************统计部分********************************

	public List<Map<String, List<Map<Integer, Integer>>>> statisticsByAge(List<Map<String,Integer>> ageRange, String districtCode,  String organId, Integer hisFlag, String countScope){
		//最终结果
		List<Map<String, List<Map<Integer, Integer>>>> resultList = new ArrayList<Map<String,List<Map<Integer,Integer>>>>();
		//计算合计值
		List<Map<Integer, Integer>> resultRowSum = new ArrayList<Map<Integer,Integer>>();
		Map<Integer, Integer> mspSum = new HashMap<Integer, Integer>();
		mspSum.put(1, 0);
		mspSum.put(2, 0);
		mspSum.put(3, 0);
		mspSum.put(4, 0);
		mspSum.put(1000, 0);

		for( Map<String ,Integer> ageRangeObj : ageRange){
			// 一个年龄段的
			Map<String,List<Map<Integer, Integer>>> aAgeRange = new HashMap<String, List<Map<Integer,Integer>>>();

			Map<String, Object> params = new HashMap<String, Object>();
			StringBuffer hql = new StringBuffer("SELECT sp.secrecyPersonLevel, count(sp.secrecyPersonLevel) as num  FROM SecrecyPerson sp where 1=1 ");
			if( ageRangeObj.get("start")==null ){
				ageRangeObj.put("start", 0);
			}
			if( hisFlag!=null ){
				hql.append("AND (sp.secrecyStatus is null or sp.secrecyStatus = :secrecyStatusNow )");
				params.put("secrecyStatusNow", hisFlag);
			}

			hql.append("AND sp.userInfo.birthDate<=STR_TO_DATE(:startDate,'%Y-%c-%d') ");
			Integer startYears = DateUtils.getCurrentYear()-ageRangeObj.get("start");
			params.put("startDate", startYears+"-12-31");

			if(ageRangeObj.get("end")!=null){
				hql.append("AND sp.userInfo.birthDate>=STR_TO_DATE(:endDate,'%Y-%c-%d') ");
				Integer endYears = DateUtils.getCurrentYear()-ageRangeObj.get("end");
				params.put("endDate", ""+endYears+"-1-1");
			}
			if( StringUtils.isNotEmpty(organId) ){
				hql.append(" AND sp.organ.organId = :organId ");
				params.put("organId", organId);

				//此处应分3中类型来统计（1、单位：不做任何约束；2、要害部门：做要害部门人员约束；3、要害部位：做要害部位人员约束。）
				if( "keysection".equals(countScope) ){
					//要害部门限制条件
					hql.append(" AND sp.secrecyPersonId in (SELECT bsp.secrecyPersonId FROM KeySection bks ,SecrecyPerson bsp WHERE  bsp.department.departmentId=bks.department.departmentId AND bks.organ.organId = :keysectionOrganId AND (bks.secrecyStatus is null or bks.secrecyStatus = :secrecyStatusN )) ");
					params.put("keysectionOrganId", organId);
					params.put("secrecyStatusN", 0);
				}
				if( "keypart".equals(countScope) ){
					//要害部位限制条件
					hql.append(" AND sp.secrecyPersonId in (SELECT DISTINCT bsp.secrecyPersonId FROM Part bkp, PartPerson bkpp,SecrecyPerson bsp WHERE  bsp.secrecyPersonId = bkpp.secrecyPerson.secrecyPersonId AND bkp.partId = bkpp.part.partId AND bkp.organ.organId = :keyPartOrganId AND (bkp.secrecyStatus is null or bkp.secrecyStatus = :secrecyStatusN )) ");
					params.put("keyPartOrganId", organId);
					params.put("secrecyStatusN", 0);
				}

			}else if( StringUtils.isNotEmpty(districtCode) ){
				hql.append(" AND sp.organ.district.districtCode = :districtCode ");
				params.put("districtCode", districtCode);

				if( "keysection".equals(countScope) ){
					//要害部门限制条件
					hql.append(" AND sp.secrecyPersonId in (SELECT bsp.secrecyPersonId FROM KeySection bks ,SecrecyPerson bsp WHERE  bsp.department.departmentId=bks.department.departmentId AND bks.organ.district.districtCode = :keysectionDistrictCode AND (bks.secrecyStatus is null or bks.secrecyStatus = :secrecyStatusN )) ");
					params.put("keysectionDistrictCode", districtCode);
					params.put("secrecyStatusN", 0);
				}

				if( "keypart".equals(countScope) ){
					//要害部位限制条件
					hql.append(" AND sp.secrecyPersonId in (SELECT DISTINCT bsp.secrecyPersonId FROM Part bkp, PartPerson bkpp,SecrecyPerson bsp WHERE  bsp.secrecyPersonId = bkpp.secrecyPerson.secrecyPersonId AND bkp.partId = bkpp.part.partId AND bkp.organ.district.districtCode = :keyPartDistrictCode AND (bkp.secrecyStatus is null or bkp.secrecyStatus = :secrecyStatusN )) ");
					params.put("keyPartDistrictCode", districtCode);
					params.put("secrecyStatusN", 0);
				}

			}
			hql.append(" AND sp.secrecyPersonLevel is not null ");
			hql.append("GROUP BY sp.secrecyPersonLevel");
			List<Object[]> resultTemp = this.getPersistProxy().getOrmPersistence().findList(hql.toString(), params);
			List<Map<Integer, Integer>> resultRow = new ArrayList<Map<Integer,Integer>>();
			if( resultTemp!=null ){
				Map<Integer,Integer> colMap = new HashMap<Integer, Integer>();
				Integer rowSum = 0;
				for( Object[] objArr : resultTemp ){
					rowSum += Integer.parseInt(objArr[1].toString());
					colMap.put(Integer.parseInt(objArr[0].toString()) , Integer.parseInt(objArr[1].toString()));
					mspSum.put(Integer.parseInt(objArr[0].toString()), mspSum.get(Integer.parseInt(objArr[0].toString()))+ Integer.parseInt(objArr[1].toString()));
					mspSum.put(4, mspSum.get(4)+ Integer.parseInt(objArr[1].toString()));
				}
				colMap.put(4, rowSum);
				resultRow.add(colMap);
			}
			String rowTitle = "";
			if( ageRangeObj.get("start")==null || ageRangeObj.get("start")== 0 ){
				rowTitle = "小于" + ageRangeObj.get("end") + "岁";
			}else if( ageRangeObj.get("end")==null){
				rowTitle = "大于" + ageRangeObj.get("start") + "岁";
			}else{
				rowTitle = ageRangeObj.get("start") + "岁至" + ageRangeObj.get("end") + "岁";
			}
			aAgeRange.put(rowTitle, resultRow);
			resultList.add(aAgeRange);
		}
//		StringBuffer hqltest = new StringBuffer("SELECT sp.secrecyPersonLevel ,count(sp.secrecyPersonLevel) as num  FROM SecrecyPerson sp where 1=1 ");
//		hqltest.append(" AND sp.organ.district.layer is not null");
//		hqltest.append(" AND sp.userInfo.birthDate is not null");
//		hqltest.append(" GROUP BY sp.secrecyPersonLevel");
//		this.getPersistProxy().getOrmPersistence().findList(hqltest.toString());

		Map<String,List<Map<Integer, Integer>>> aAgeRangeSum = new HashMap<String, List<Map<Integer,Integer>>>();
		resultRowSum.add(mspSum);
		aAgeRangeSum.put("合计", resultRowSum);
		resultList.add(aAgeRangeSum);
		return resultList;
	}

	public List<Map<String, List<Map<Integer, Integer>>>> statisticsByLayerOfAge(List<Map<String,Integer>> ageRange, String layer, String organId, Integer hisFlag, String countScope){
		//最终结果
		List<Map<String, List<Map<Integer, Integer>>>> resultList = new ArrayList<Map<String,List<Map<Integer,Integer>>>>();
		//计算合计值
		List<Map<Integer, Integer>> resultRowSum = new ArrayList<Map<Integer,Integer>>();
		Map<Integer, Integer> mspSum = new HashMap<Integer, Integer>();
		mspSum.put(1, 0);
		mspSum.put(2, 0);
		mspSum.put(3, 0);
		mspSum.put(4, 0);
		mspSum.put(1000, 0);

		for( Map<String ,Integer> ageRangeObj : ageRange){
			// 一个年龄段的
			Map<String,List<Map<Integer, Integer>>> aAgeRange = new HashMap<String, List<Map<Integer,Integer>>>();

			Map<String, Object> params = new HashMap<String, Object>();
			StringBuffer hql = new StringBuffer("SELECT sp.secrecyPersonLevel, count(sp.secrecyPersonLevel) as num  FROM SecrecyPerson sp where 1=1 ");
			if( ageRangeObj.get("start")==null ){
				ageRangeObj.put("start", 0);
			}

			if( hisFlag!=null ){
				hql.append("AND (sp.secrecyStatus is null or sp.secrecyStatus = :secrecyStatusNow )");
				params.put("secrecyStatusNow", hisFlag);
			}

			hql.append("AND sp.userInfo.birthDate<=STR_TO_DATE(:startDate,'%Y-%c-%d') ");
			Integer startYears = DateUtils.getCurrentYear()-ageRangeObj.get("start");
			params.put("startDate", startYears+"-12-31");

			if(ageRangeObj.get("end")!=null){
				hql.append("AND sp.userInfo.birthDate>=STR_TO_DATE(:endDate,'%Y-%c-%d') ");
				Integer endYears = DateUtils.getCurrentYear()-ageRangeObj.get("end");
				params.put("endDate", ""+endYears+"-1-1");
			}
			if( StringUtils.isNotEmpty(organId) ){
				hql.append(" AND sp.organ.organId = :organId ");
				params.put("organId", organId);

				if( "keysection".equals(countScope) ){
					hql.append(" AND sp.secrecyPersonId in (SELECT bsp.secrecyPersonId FROM KeySection bks ,SecrecyPerson bsp WHERE  bsp.department.departmentId=bks.department.departmentId AND bks.organ.organId = :keysectionOrganId AND (bks.secrecyStatus is null or bks.secrecyStatus = :secrecyStatusN )) ");
					params.put("keysectionOrganId", organId);
					params.put("secrecyStatusN", 0);
				}

			}else if( StringUtils.isNotEmpty(layer) ){
				hql.append(" AND sp.organ.district.layer like :layer ");
				params.put("layer", layer+"%");

				if( "keysection".equals(countScope) ){
					//要害部门限制条件
					hql.append(" AND sp.secrecyPersonId in (SELECT bsp.secrecyPersonId FROM KeySection bks ,SecrecyPerson bsp WHERE  bsp.department.departmentId=bks.department.departmentId AND bks.organ.district.layer like :keysectionLayer AND (bks.secrecyStatus is null or bks.secrecyStatus = :secrecyStatusN )) ");
					params.put("keysectionLayer", layer+"%");
					params.put("secrecyStatusN", 0);
				}

				if( "keypart".equals(countScope) ){
					//要害部位限制条件
					hql.append(" AND sp.secrecyPersonId in (SELECT DISTINCT bsp.secrecyPersonId FROM Part bkp, PartPerson bkpp,SecrecyPerson bsp WHERE  bsp.secrecyPersonId = bkpp.secrecyPerson.secrecyPersonId AND bkp.partId = bkpp.part.partId AND bkp.organ.district.layer like :keypartLayer AND (bkp.secrecyStatus is null or bkp.secrecyStatus = :secrecyStatusN )) ");
					params.put("keypartLayer", layer+"%");
					params.put("secrecyStatusN", 0);
				}

			}

			hql.append(" AND sp.secrecyPersonLevel is not null ");
			hql.append("GROUP BY sp.secrecyPersonLevel");
			List<Object[]> resultTemp = this.getPersistProxy().getOrmPersistence().findList(hql.toString(), params);
			List<Map<Integer, Integer>> resultRow = new ArrayList<Map<Integer,Integer>>();
			if( resultTemp!=null ){
				Map<Integer,Integer> colMap = new HashMap<Integer, Integer>();
				Integer rowSum = 0;
				for( Object[] objArr : resultTemp ){
					rowSum += Integer.parseInt(objArr[1].toString());
					colMap.put(Integer.parseInt(objArr[0].toString()) , Integer.parseInt(objArr[1].toString()));
					mspSum.put(Integer.parseInt(objArr[0].toString()), mspSum.get(Integer.parseInt(objArr[0].toString()))+ Integer.parseInt(objArr[1].toString()));
					mspSum.put(4, mspSum.get(4)+ Integer.parseInt(objArr[1].toString()));
				}
				colMap.put(4, rowSum);
				resultRow.add(colMap);
			}
			String rowTitle = "";
			if( ageRangeObj.get("start")==null || ageRangeObj.get("start")== 0 ){
				rowTitle = "小于" + ageRangeObj.get("end") + "岁";
			}else if( ageRangeObj.get("end")==null){
				rowTitle = "大于" + ageRangeObj.get("start") + "岁";
			}else{
				rowTitle = ageRangeObj.get("start") + "岁至" + ageRangeObj.get("end") + "岁";
			}
			aAgeRange.put(rowTitle, resultRow);
			resultList.add(aAgeRange);
		}
		//StringBuffer hqltest = new StringBuffer("SELECT sp.secrecyPersonLevel ,count(sp.secrecyPersonLevel) as num  FROM SecrecyPerson sp where 1=1 ");
		//hqltest.append(" AND sp.organ.district is not null");
		//hqltest.append(" AND sp.userInfo.birthDate is not null GROUP BY sp.secrecyPersonLevel");
		//hqltest.append(" GROUP BY sp.secrecyPersonLevel");
		//this.getPersistProxy().getOrmPersistence().findList(hqltest.toString());

		Map<String,List<Map<Integer, Integer>>> aAgeRangeSum = new HashMap<String, List<Map<Integer,Integer>>>();
		resultRowSum.add(mspSum);
		aAgeRangeSum.put("合计", resultRowSum);
		resultList.add(aAgeRangeSum);
		return resultList;
	}

	public Map<String,List<Map<String, List<Map<Integer, Integer>>>>> statisticsByAgeOfAll(List<Map<String,Integer>> ageRange, String districtCode, String organId, Integer hisFlag, String countScope){
		Map<String,List<Map<String, List<Map<Integer, Integer>>>>> resultMap = new HashMap<String, List<Map<String,List<Map<Integer,Integer>>>>>();
		District district = this.get(districtCode, District.class);
		if( district!=null ){
			resultMap.put(district.getDistrictName(), this.statisticsByLayerOfAge(ageRange, district.getLayer(), organId, hisFlag, countScope));
		}
		return resultMap;
	}
	public Map<String,List<Map<String, List<Map<Integer, Integer>>>>> statisticsByAgeOfDistrict(List<Map<String,Integer>> ageRange, String districtCode, String organId, Integer hisFlag, String countScope){
		Map<String,List<Map<String, List<Map<Integer, Integer>>>>> returnResult = new HashMap<String,List<Map<String, List<Map<Integer, Integer>>>>>();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append("SELECT district2_.district_name,district2_.district_code, district2_.layer FROM SYS_DISTRICT district2_ ");
		sqlStr.append("WHERE district2_.pid = :districtCode ");
		params.put("districtCode", districtCode);
		List<Map<String,Object>> subDistrict = this.getPersistProxy().getJdbcPersistence().findList(sqlStr.toString(), params);
		if( subDistrict!=null ){
			for( Map<String,Object> aDistrict : subDistrict ){
				returnResult.put(aDistrict.get("district_name").toString(),this.statisticsByLayerOfAge(ageRange, aDistrict.get("layer").toString(), organId,hisFlag, countScope));
			}
		}
		return returnResult;
	}
	public Map<String,List<Map<String, List<Map<Integer, Integer>>>>> statisticsByAgeOfOrgan(List<Map<String,Integer>> ageRange, String districtCode, String organId, Integer hisFlag, String countScope){
		Map<String,List<Map<String, List<Map<Integer, Integer>>>>> returnResult = new HashMap<String,List<Map<String, List<Map<Integer, Integer>>>>>();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT o FROM  Organ o WHERE 1=1 ");
		hql.append("AND o.district.districtCode = :districtCode ");
		params.put("districtCode", districtCode);
		List<Organ> subOrgan = this.getPersistProxy().getOrmPersistence().findList(hql.toString(), params);
		if( subOrgan!=null ){
			for( Organ tmpOrgan : subOrgan ){
				returnResult.put(tmpOrgan.getName(),this.statisticsByAge(ageRange, null, tmpOrgan.getOrganId(), hisFlag, countScope));
			}
		}
		return returnResult;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//按民族查询
	public List<Map<String, Map<Integer, Integer>>> statisticsByNation(List<DictionaryOption> nationOptions, String districtCode, String organId, Integer hisFlag,String countScope){
		//最终结果
		List<Map<String, Map<Integer, Integer>>> resultList = new ArrayList<Map<String,Map<Integer,Integer>>>();
		//计算合计值
		Map<Integer, Integer> resultRowSum = new HashMap<Integer, Integer>();
		resultRowSum.put(1, 0);
		resultRowSum.put(2, 0);
		resultRowSum.put(3, 0);
		resultRowSum.put(4, 0);
		resultRowSum.put(1000, 0);

		for( DictionaryOption nationObj : nationOptions){
			// 一个民族的
			Map<String,Map<Integer, Integer>> aAgeRange = new HashMap<String, Map<Integer,Integer>>();

			Map<String, Object> params = new HashMap<String, Object>();
			StringBuffer hql = new StringBuffer("select secrecyper0_.secrecy_person_level , count(secrecyper0_.secrecy_person_level) as num from bm_secrecy_person secrecyper0_ cross join SYS_ORGANIZATION organ1_ cross join SYS_USER_INFO userinfo2_ where secrecyper0_.organ_id=organ1_.ORGAN_ID and secrecyper0_.USER_INFO_ID=userinfo2_.PERSON_ID and 1=1 ");
			hql.append(" AND userinfo2_.FOLK = :nation");
			params.put("nation", nationObj.getOptionValue());
			if( StringUtils.isNotEmpty(organId) ){
				hql.append(" AND organ1_.organ_id = :organId ");
				params.put("organId", organId);

				if( "keysection".equals(countScope) ){
					//要害部门限制条件
					hql.append(" AND secrecyper0_.person_id in (SELECT DISTINCT bsp.person_id FROM `bm_key_section` bks JOIN bm_secrecy_person bsp ON  bsp.department_id=bks.DEPARTMENT_ID where bks.organ_id = :keysectionOrganId AND (bks.secrecy_status is null or bks.secrecy_status = :secrecyStatusN )) ");
					params.put("keysectionOrganId", organId);
					params.put("secrecyStatusN", 0);
				}

				if( "keypart".equals(countScope) ){
					//要害部位限制条件
					hql.append(" AND secrecyper0_.person_id in (SELECT DISTINCT bsp.person_id FROM `bm_key_part_person` bkpp,`bm_secrecy_person` bsp,`bm_key_part` bkp WHERE bkpp.PERSON_ID = bsp.person_id AND bkp.KEY_PART_ID = bkpp.KEY_PART_ID AND bkp.ORGAN_ID =:keyPartOrganId AND( bkp.secrecy_status IS NULL OR bkp.secrecy_status = :secrecyStatusN)) ");
					params.put("keyPartOrganId", organId);
					params.put("secrecyStatusN", 0);
				}


			}else if( StringUtils.isNotEmpty(districtCode) ){
				hql.append(" AND organ1_.district_code = :districtCode ");
				params.put("districtCode", districtCode);

				if( "keysection".equals(countScope) ){
					hql.append(" AND secrecyper0_.person_id in (SELECT DISTINCT bsp.person_id FROM bm_key_section bks, bm_secrecy_person bsp, sys_department sd, sys_organization so, sys_district sdi WHERE bsp.department_Id = bks.department_Id AND bks.organ_id = so.organ_id AND so.DISTRICT_CODE = sdi.district_code AND sdi.district_code LIKE :keysectionDistrictCode AND( bks.secrecy_Status IS NULL OR bks.secrecy_Status = :secrecyStatusN )) ");
					params.put("keysectionDistrictCode", districtCode);
					params.put("secrecyStatusN", 0);
				}

			}

			if( hisFlag != null ){
				hql.append(" AND (secrecyper0_.secrecy_status is null or secrecyper0_.secrecy_status = :secrecyStatusNow) ");
				params.put("secrecyStatusNow", hisFlag);
			}

			hql.append(" AND secrecyper0_.secrecy_person_level is not null ");
			hql.append(" GROUP BY secrecyper0_.secrecy_person_level");
			List<Map<String,Object>> resultTemp = this.getPersistProxy().getJdbcPersistence().findList(hql.toString(), params);
			Map<Integer, Integer> resultRow = new HashMap<Integer, Integer>();
			if( resultTemp!=null ){
				Integer rowSum = 0;
				for(Map<String,Object> objArr : resultTemp ){
					rowSum += Integer.parseInt(objArr.get("num").toString());
					resultRow.put(Integer.parseInt(objArr.get("secrecy_person_level").toString()) , Integer.parseInt(objArr.get("num").toString()));
					resultRowSum.put(Integer.parseInt(objArr.get("secrecy_person_level").toString()), resultRowSum.get(Integer.parseInt(objArr.get("secrecy_person_level").toString()))+ Integer.parseInt(objArr.get("num").toString()));
					resultRowSum.put(4, resultRowSum.get(4)+ Integer.parseInt(objArr.get("num").toString()));
				}
				resultRow.put(4, rowSum);
			}
			aAgeRange.put(nationObj.getOptionText(), resultRow);
			resultList.add(aAgeRange);
		}
		Map<String,Map<Integer, Integer>> aAgeRangeSum = new HashMap<String, Map<Integer,Integer>>();
		aAgeRangeSum.put("合计", resultRowSum);
		resultList.add(aAgeRangeSum);
		return resultList;
	}

	public List<Map<String, Map<Integer, Integer>>> statisticsByLayerOfNation(List<DictionaryOption> nationOptions, String layer, String organId, Integer hisFlag, String countScope){
		//最终结果
		List<Map<String, Map<Integer, Integer>>> resultList = new ArrayList<Map<String,Map<Integer,Integer>>>();
		//计算合计值
		Map<Integer, Integer> resultRowSum = new HashMap<Integer, Integer>();
		resultRowSum.put(1, 0);
		resultRowSum.put(2, 0);
		resultRowSum.put(3, 0);
		resultRowSum.put(4, 0);
		resultRowSum.put(1000, 0);

		for( DictionaryOption nationObj : nationOptions){
			// 一个民族的
			Map<String,Map<Integer, Integer>> aAgeRange = new HashMap<String, Map<Integer,Integer>>();

			Map<String, Object> params = new HashMap<String, Object>();
			StringBuilder hql = new StringBuilder("select secrecyper0_.secrecy_person_level, count(secrecyper0_.secrecy_person_level) as num from bm_secrecy_person secrecyper0_ cross join SYS_ORGANIZATION organ1_ cross join SYS_DISTRICT district2_ cross join SYS_USER_INFO userinfo3_ where secrecyper0_.organ_id=organ1_.ORGAN_ID and organ1_.district_code=district2_.district_code and secrecyper0_.USER_INFO_ID=userinfo3_.PERSON_ID and 1=1 ");
			hql.append(" AND userinfo3_.FOLK = :nation");
			params.put("nation", nationObj.getOptionValue());

			if( StringUtils.isNotEmpty(organId) ){
				hql.append(" AND organ1_.organ_id = :organId ");
				params.put("organId", organId);

				hql.append(" AND secrecyper0_.person_id in (SELECT DISTINCT bsp.person_id FROM `bm_key_section` bks JOIN bm_secrecy_person bsp ON  bsp.department_id=bks.DEPARTMENT_ID where bks.organ_id = :keysectionOrganId AND (bks.secrecy_status is null or bks.secrecy_status = :secrecyStatusN )) ");
				params.put("keysectionOrganId", organId);
				params.put("secrecyStatusN", 0);

			}else if( StringUtils.isNotEmpty(layer) ){
				hql.append(" AND district2_.layer like :layer ");
				params.put("layer", layer+"%");

				if( "keysection".equals(countScope) ){
					//要害部门限制条件
					hql.append(" AND secrecyper0_.person_id in (SELECT DISTINCT bsp.person_id FROM bm_key_section bks, bm_secrecy_person bsp, sys_department sd, sys_organization so, sys_district sdi WHERE bsp.department_Id = bks.department_Id AND bks.organ_id = so.organ_id AND so.DISTRICT_CODE = sdi.district_code AND sdi.layer LIKE :keysectionLayer AND( bks.secrecy_Status IS NULL OR bks.secrecy_Status = :secrecyStatusN )) ");
					params.put("keysectionLayer", layer+"%");
					params.put("secrecyStatusN", 0);
				}
				if( "keypart".equals(countScope) ){
					//要害部位限制条件
					hql.append(" AND secrecyper0_.person_id in (SELECT DISTINCT bsp.person_id FROM bm_key_part bkp,bm_key_part_person bkpp,bm_secrecy_person bsp,sys_organization so,sys_district sdi WHERE bkp.KEY_PART_ID = bkpp.KEY_PART_ID AND bkpp.PERSON_ID = bsp.person_id AND bkp.ORGAN_ID = so.ORGAN_ID AND so.DISTRICT_CODE = sdi.district_code AND sdi.layer LIKE :keypartLayer AND( bkp.secrecy_Status IS NULL OR bkp.secrecy_Status = :secrecyStatusN)) ");
					params.put("keypartLayer", layer+"%");
					params.put("secrecyStatusN", 0);
				}

			}

			if( hisFlag != null ){
				hql.append(" AND (secrecyper0_.secrecy_status is null or secrecyper0_.secrecy_status = :secrecyStatusNow) ");
				params.put("secrecyStatusNow", hisFlag);
			}

			hql.append(" AND secrecyper0_.secrecy_person_level is not null ");
			hql.append(" GROUP BY secrecyper0_.secrecy_person_level");
			List<Map<String,Object>> resultTemp = this.getPersistProxy().getJdbcPersistence().findList(hql.toString(), params);
			Map<Integer, Integer> resultRow = new HashMap<Integer, Integer>();
			if( resultTemp!=null ){
				Integer rowSum = 0;
				for(Map<String,Object> objArr : resultTemp ){
					rowSum += Integer.parseInt(objArr.get("num").toString());
					resultRow.put(Integer.parseInt(objArr.get("secrecy_person_level").toString()) , Integer.parseInt(objArr.get("num").toString()));
					resultRowSum.put(Integer.parseInt(objArr.get("secrecy_person_level").toString()), resultRowSum.get(Integer.parseInt(objArr.get("secrecy_person_level").toString()))+ Integer.parseInt(objArr.get("num").toString()));
					resultRowSum.put(4, resultRowSum.get(4)+ Integer.parseInt(objArr.get("num").toString()));
				}
				resultRow.put(4, rowSum);
			}
			aAgeRange.put(nationObj.getOptionText(), resultRow);
			resultList.add(aAgeRange);
		}
		Map<String,Map<Integer, Integer>> aAgeRangeSum = new HashMap<String, Map<Integer,Integer>>();
		aAgeRangeSum.put("合计", resultRowSum);
		resultList.add(aAgeRangeSum);
		return resultList;
	}

	public Map<String,List<Map<String, Map<Integer, Integer>>>> statisticsByNationOfAll(List<DictionaryOption> nationOptions, District district, String organId, Integer hisFlag, String countScope){
		Map<String,List<Map<String, Map<Integer, Integer>>>> resultMap = new HashMap<String,List<Map<String, Map<Integer, Integer>>>>();
		if( district!=null ){
			resultMap.put(district.getDistrictName(), this.statisticsByLayerOfNation(nationOptions, district.getLayer(), organId, hisFlag, countScope));
		}
		return resultMap;
	}

	public Map<String,List<Map<String, Map<Integer, Integer>>>> statisticsByNationOfDistrict(List<DictionaryOption> nationOptions, District district, String organId, Integer hisFlag, String countScope){
		Map<String,List<Map<String, Map<Integer, Integer>>>> returnResult = new HashMap<String,List<Map<String, Map<Integer, Integer>>>>();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append("SELECT district2_.district_name,district2_.district_code, district2_.layer FROM SYS_DISTRICT district2_ ");
		sqlStr.append("WHERE district2_.pid = :districtCode ");
		params.put("districtCode", district.getDistrictCode());
		List<Map<String,Object>> subDistrict = this.getPersistProxy().getJdbcPersistence().findList(sqlStr.toString(), params);
		if( subDistrict!=null ){
			for( Map<String,Object> aDistrict : subDistrict ){
				returnResult.put(aDistrict.get("district_name").toString(),this.statisticsByLayerOfNation(nationOptions, aDistrict.get("layer").toString(), organId, hisFlag, countScope));
			}
		}
		return returnResult;
	}
	public Map<String,List<Map<String, Map<Integer, Integer>>>> statisticsByNationOfOrgan(List<DictionaryOption> nationOptions, District district, String organId, Integer hisFlag, String countScope){
		Map<String,List<Map<String, Map<Integer, Integer>>>> returnResult = new HashMap<String,List<Map<String, Map<Integer, Integer>>>>();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT o FROM  Organ o WHERE 1=1 ");
		hql.append("AND o.district.districtCode = :districtCode ");
		params.put("districtCode", district.getDistrictCode());
		List<Organ> subOrgan = this.getPersistProxy().getOrmPersistence().findList(hql.toString(), params);
		if( subOrgan!=null ){
			for( Organ tmpOrgan : subOrgan ){
				returnResult.put(tmpOrgan.getName(),this.statisticsByNation(nationOptions, null, tmpOrgan.getOrganId(), hisFlag, countScope));
			}
		}
		return returnResult;
	}


	public List<Map<String, Map<Integer, Integer>>> statisticsByLeanLevel(List<DictionaryOption> leanLevelOptions, String districtCode, String organId, Integer hisFlag, String countScope){
		//最终结果
		List<Map<String, Map<Integer, Integer>>> resultList = new ArrayList<Map<String,Map<Integer,Integer>>>();
		//计算合计值
		Map<Integer, Integer> resultRowSum = new HashMap<Integer, Integer>();
		resultRowSum.put(1, 0);
		resultRowSum.put(2, 0);
		resultRowSum.put(3, 0);
		resultRowSum.put(4, 0);
		resultRowSum.put(1000, 0);

		for( DictionaryOption dicObj : leanLevelOptions){
			// 一个学历的
			Map<String,Map<Integer, Integer>> aAgeRange = new HashMap<String, Map<Integer,Integer>>();

			Map<String, Object> params = new HashMap<String, Object>();
			StringBuffer hql = new StringBuffer("select secrecyper0_.secrecy_person_level , count(secrecyper0_.secrecy_person_level) as num from bm_secrecy_person secrecyper0_ cross join SYS_ORGANIZATION organ1_ cross join SYS_USER_INFO userinfo2_ where secrecyper0_.organ_id=organ1_.ORGAN_ID and secrecyper0_.USER_INFO_ID=userinfo2_.PERSON_ID and 1=1 ");
			hql.append(" AND userinfo2_.DIPLOMA = :dicValue");
			params.put("dicValue", dicObj.getOptionValue());

			if( StringUtils.isNotEmpty(organId) ){
				hql.append(" AND organ1_.organ_id = :organId ");
				params.put("organId", organId);

				if( "keysection".equals(countScope) ){
					//要害部门限制条件
					hql.append(" AND secrecyper0_.person_id in (SELECT DISTINCT bsp.person_id FROM `bm_key_section` bks JOIN bm_secrecy_person bsp ON  bsp.department_id=bks.DEPARTMENT_ID where bks.organ_id = :keysectionOrganId AND (bks.secrecy_status is null or bks.secrecy_status = :secrecyStatusN )) ");
					params.put("keysectionOrganId", organId);
					params.put("secrecyStatusN", 0);
				}
				if( "keypart".equals(countScope) ){
					//要害部位限制条件
					hql.append(" AND secrecyper0_.person_id in (SELECT DISTINCT bsp.person_id FROM `bm_key_part_person` bkpp,`bm_secrecy_person` bsp,`bm_key_part` bkp WHERE bkpp.PERSON_ID = bsp.person_id AND bkp.KEY_PART_ID = bkpp.KEY_PART_ID AND bkp.ORGAN_ID =:keyPartOrganId AND( bkp.secrecy_status IS NULL OR bkp.secrecy_status = :secrecyStatusN)) ");
					params.put("keyPartOrganId", organId);
					params.put("secrecyStatusN", 0);
				}

			}else if( StringUtils.isNotEmpty(districtCode) ){
				hql.append(" AND organ1_.district_code = :districtCode ");
				params.put("districtCode", districtCode);
				if( "keysection".equals(countScope) ){
					hql.append(" AND secrecyper0_.person_id in (SELECT DISTINCT bsp.person_id FROM bm_key_section bks, bm_secrecy_person bsp, sys_department sd, sys_organization so, sys_district sdi WHERE bsp.department_Id = bks.department_Id AND bks.organ_id = so.organ_id AND so.DISTRICT_CODE = sdi.district_code AND sdi.district_code = :keysectionDistrictCode AND( bks.secrecy_Status IS NULL OR bks.secrecy_Status = :secrecyStatusN )) ");
					params.put("keysectionDistrictCode", districtCode);
					params.put("secrecyStatusN", 0);
				}

			}

			if( hisFlag != null ){
				hql.append(" AND (secrecyper0_.secrecy_status is null or secrecyper0_.secrecy_status = :secrecyStatusNow) ");
				params.put("secrecyStatusNow", hisFlag);
			}

			hql.append(" AND secrecyper0_.secrecy_person_level is not null ");
			hql.append(" GROUP BY secrecyper0_.secrecy_person_level");
			List<Map<String,Object>> resultTemp = this.getPersistProxy().getJdbcPersistence().findList(hql.toString(), params);
			Map<Integer, Integer> resultRow = new HashMap<Integer, Integer>();
			if( resultTemp!=null ){
				Integer rowSum = 0;
				for(Map<String,Object> objArr : resultTemp ){
					rowSum += Integer.parseInt(objArr.get("num").toString());
					resultRow.put(Integer.parseInt(objArr.get("secrecy_person_level").toString()) , Integer.parseInt(objArr.get("num").toString()));
					resultRowSum.put(Integer.parseInt(objArr.get("secrecy_person_level").toString()), resultRowSum.get(Integer.parseInt(objArr.get("secrecy_person_level").toString()))+ Integer.parseInt(objArr.get("num").toString()));
					resultRowSum.put(4, resultRowSum.get(4)+ Integer.parseInt(objArr.get("num").toString()));
				}
				resultRow.put(4, rowSum);
			}
			aAgeRange.put(dicObj.getOptionText(), resultRow);
			resultList.add(aAgeRange);
		}
		Map<String,Map<Integer, Integer>> aAgeRangeSum = new HashMap<String, Map<Integer,Integer>>();
		aAgeRangeSum.put("合计", resultRowSum);
		resultList.add(aAgeRangeSum);
		return resultList;
	}

	public List<Map<String, Map<Integer, Integer>>> statisticsByLayerOfLeanLevel(List<DictionaryOption> leanLevelOptions, String layer, String organId, Integer hisFlag, String countScope){
		//最终结果
		List<Map<String, Map<Integer, Integer>>> resultList = new ArrayList<Map<String,Map<Integer,Integer>>>();
		//计算合计值
		Map<Integer, Integer> resultRowSum = new HashMap<Integer, Integer>();
		resultRowSum.put(1, 0);
		resultRowSum.put(2, 0);
		resultRowSum.put(3, 0);
		resultRowSum.put(4, 0);
		resultRowSum.put(1000, 0);

		for( DictionaryOption dicObj : leanLevelOptions){
			// 一个学历的
			Map<String,Map<Integer, Integer>> aAgeRange = new HashMap<String, Map<Integer,Integer>>();

			Map<String, Object> params = new HashMap<String, Object>();
			StringBuffer hql = new StringBuffer("select secrecyper0_.secrecy_person_level, count(secrecyper0_.secrecy_person_level) as num from bm_secrecy_person secrecyper0_ cross join SYS_ORGANIZATION organ1_ cross join SYS_DISTRICT district2_ cross join SYS_USER_INFO userinfo3_ where secrecyper0_.organ_id=organ1_.ORGAN_ID and organ1_.district_code=district2_.district_code and secrecyper0_.USER_INFO_ID=userinfo3_.PERSON_ID and 1=1 ");
			hql.append(" AND userinfo3_.DIPLOMA = :dicValue");
			params.put("dicValue", dicObj.getOptionValue());

			if( StringUtils.isNotEmpty(organId) ){
				hql.append(" AND organ1_.organ_id = :organId ");
				params.put("organId", organId);

			}else if( StringUtils.isNotEmpty(layer) ){
				hql.append(" AND district2_.layer like :layer ");
				params.put("layer", layer+"%");

				if( "keysection".equals(countScope) ){
					//要害部门限制条件
					hql.append(" AND secrecyper0_.person_id in (SELECT DISTINCT bsp.person_id FROM bm_key_section bks, bm_secrecy_person bsp, sys_department sd, sys_organization so, sys_district sdi WHERE bsp.department_Id = bks.department_Id AND bks.organ_id = so.organ_id AND so.DISTRICT_CODE = sdi.district_code AND sdi.layer LIKE :keysectionLayer AND( bks.secrecy_Status IS NULL OR bks.secrecy_Status = :secrecyStatusN )) ");
					params.put("keysectionLayer", layer+"%");
					params.put("secrecyStatusN", 0);
				}
				if( "keypart".equals(countScope) ){
					//要害部位限制条件
					hql.append(" AND secrecyper0_.person_id in (SELECT DISTINCT bsp.person_id FROM bm_key_part bkp,bm_key_part_person bkpp,bm_secrecy_person bsp,sys_organization so,sys_district sdi WHERE bkp.KEY_PART_ID = bkpp.KEY_PART_ID AND bkpp.PERSON_ID = bsp.person_id AND bkp.ORGAN_ID = so.ORGAN_ID AND so.DISTRICT_CODE = sdi.district_code AND sdi.layer LIKE :keypartLayer AND( bkp.secrecy_Status IS NULL OR bkp.secrecy_Status = :secrecyStatusN)) ");
					params.put("keypartLayer", layer+"%");
					params.put("secrecyStatusN", 0);
				}

			}

			if( hisFlag != null ){
				hql.append(" AND (secrecyper0_.secrecy_status is null or secrecyper0_.secrecy_status = :secrecyStatusNow) ");
				params.put("secrecyStatusNow", hisFlag);
			}

			hql.append(" AND secrecyper0_.secrecy_person_level is not null ");
			hql.append(" GROUP BY secrecyper0_.secrecy_person_level");
			List<Map<String,Object>> resultTemp = this.getPersistProxy().getJdbcPersistence().findList(hql.toString(), params);
			Map<Integer, Integer> resultRow = new HashMap<Integer, Integer>();
			if( resultTemp!=null ){
				Integer rowSum = 0;
				for(Map<String,Object> objArr : resultTemp ){
					rowSum += Integer.parseInt(objArr.get("num").toString());
					resultRow.put(Integer.parseInt(objArr.get("secrecy_person_level").toString()) , Integer.parseInt(objArr.get("num").toString()));
					resultRowSum.put(Integer.parseInt(objArr.get("secrecy_person_level").toString()), resultRowSum.get(Integer.parseInt(objArr.get("secrecy_person_level").toString()))+ Integer.parseInt(objArr.get("num").toString()));
					resultRowSum.put(4, resultRowSum.get(4)+ Integer.parseInt(objArr.get("num").toString()));
				}
				resultRow.put(4, rowSum);
			}
			aAgeRange.put(dicObj.getOptionText(), resultRow);
			resultList.add(aAgeRange);
		}
		Map<String,Map<Integer, Integer>> aAgeRangeSum = new HashMap<String, Map<Integer,Integer>>();
		aAgeRangeSum.put("合计", resultRowSum);
		resultList.add(aAgeRangeSum);
		return resultList;
	}

	public Map<String,List<Map<String, Map<Integer, Integer>>>> statisticsByLearnLevelOfAll(List<DictionaryOption> leanLevelOptions, District district ,String organId, Integer hisFlag, String countScope){
		Map<String,List<Map<String, Map<Integer, Integer>>>> resultMap = new HashMap<String,List<Map<String, Map<Integer, Integer>>>>();
		if( district!=null ){
			resultMap.put(district.getDistrictName(), this.statisticsByLayerOfLeanLevel(leanLevelOptions, district.getLayer(), organId, hisFlag, countScope));
		}
		return resultMap;
	}
	public Map<String,List<Map<String, Map<Integer, Integer>>>> statisticsByLearnLevelOfDistrict(List<DictionaryOption> leanLevelOptions, District district ,String organId, Integer hisFlag, String countScope){
		Map<String,List<Map<String, Map<Integer, Integer>>>> returnResult = new HashMap<String,List<Map<String, Map<Integer, Integer>>>>();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append("SELECT district2_.district_name,district2_.district_code, district2_.layer FROM SYS_DISTRICT district2_ ");
		sqlStr.append("WHERE district2_.pid = :districtCode ");
		params.put("districtCode", district.getDistrictCode());
		List<Map<String,Object>> subDistrict = this.getPersistProxy().getJdbcPersistence().findList(sqlStr.toString(), params);
		if( subDistrict!=null ){
			for( Map<String,Object> aDistrict : subDistrict ){
				returnResult.put(aDistrict.get("district_name").toString(),this.statisticsByLayerOfLeanLevel(leanLevelOptions, aDistrict.get("layer").toString(), organId, hisFlag, countScope));
			}
		}
		return returnResult;
	}
	public Map<String,List<Map<String, Map<Integer, Integer>>>> statisticsByLearnLevelOfOrgan(List<DictionaryOption> leanLevelOptions, District district ,String organId, Integer hisFlag, String countScope){
		Map<String,List<Map<String, Map<Integer, Integer>>>> returnResult = new HashMap<String,List<Map<String, Map<Integer, Integer>>>>();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT o FROM  Organ o WHERE 1=1 ");
		hql.append("AND o.district.districtCode = :districtCode ");
		params.put("districtCode", district.getDistrictCode());
		List<Organ> subOrgan = this.getPersistProxy().getOrmPersistence().findList(hql.toString(), params);
		if( subOrgan!=null ){
			for( Organ tmpOrgan : subOrgan ){
				returnResult.put(tmpOrgan.getName(),this.statisticsByLeanLevel(leanLevelOptions, null, tmpOrgan.getOrganId(), hisFlag, countScope));
			}
		}
		return returnResult;
	}


	public List<Map<String, Map<Integer, Integer>>> statisticsByPolity(List<DictionaryOption> polityOptions, String districtCode, String organId, Integer hisFlag, String countScope){
		//最终结果
		List<Map<String, Map<Integer, Integer>>> resultList = new ArrayList<Map<String,Map<Integer,Integer>>>();
		//计算合计值
		Map<Integer, Integer> resultRowSum = new HashMap<Integer, Integer>();
		resultRowSum.put(1, 0);
		resultRowSum.put(2, 0);
		resultRowSum.put(3, 0);
		resultRowSum.put(4, 0);
		resultRowSum.put(1000, 0);

		for( DictionaryOption dicObj : polityOptions){
			// 一个政治面貌的
			Map<String,Map<Integer, Integer>> aAgeRange = new HashMap<String, Map<Integer,Integer>>();

			Map<String, Object> params = new HashMap<String, Object>();
			StringBuffer hql = new StringBuffer("select secrecyper0_.secrecy_person_level , count(secrecyper0_.secrecy_person_level) as num from bm_secrecy_person secrecyper0_ cross join SYS_ORGANIZATION organ1_ cross join SYS_USER_INFO userinfo2_ where secrecyper0_.organ_id=organ1_.ORGAN_ID and secrecyper0_.USER_INFO_ID=userinfo2_.PERSON_ID and 1=1 ");
			hql.append(" AND userinfo2_.polity = :dicValue");
			params.put("dicValue", dicObj.getOptionValue());

			if( StringUtils.isNotEmpty(organId) ){
				hql.append(" AND organ1_.organ_id = :organId ");
				params.put("organId", organId);

				if( "keysection".equals(countScope)){
					//要害部门限制条件
					hql.append(" AND secrecyper0_.person_id in (SELECT bsp.person_id FROM `bm_key_section` bks JOIN bm_secrecy_person bsp ON  bsp.department_id=bks.DEPARTMENT_ID where bks.organ_id = :keysectionOrganId AND (bks.secrecy_status is null or bks.secrecy_status = :secrecyStatusN )) ");
					params.put("keysectionOrganId", organId);
					params.put("secrecyStatusN", 0);
				}

				if( "keypart".equals(countScope)){
					//要害部位限制条件
					hql.append(" AND secrecyper0_.person_id in (SELECT DISTINCT bsp.person_id FROM `bm_key_part_person` bkpp,`bm_secrecy_person` bsp,`bm_key_part` bkp WHERE bkpp.PERSON_ID = bsp.person_id AND bkp.KEY_PART_ID = bkpp.KEY_PART_ID AND bkp.ORGAN_ID =:keyPartOrganId AND( bkp.secrecy_status IS NULL OR bkp.secrecy_status = :secrecyStatusN)) ");
					params.put("keyPartOrganId", organId);
					params.put("secrecyStatusN", 0);
				}

			}else if( StringUtils.isNotEmpty(districtCode) ){
				hql.append(" AND organ1_.district_code = :districtCode ");
				params.put("districtCode", districtCode);
				if( "keysection".equals(countScope)){
					hql.append(" AND secrecyper0_.person_id in (SELECT DISTINCT bsp.person_id FROM bm_key_section bks, bm_secrecy_person bsp, sys_department sd, sys_organization so, sys_district sdi WHERE bsp.department_Id = bks.department_Id AND bks.organ_id = so.organ_id AND so.DISTRICT_CODE = sdi.district_code AND sdi.district_code LIKE :keysectionDistrictCode AND( bks.secrecy_Status IS NULL OR bks.secrecy_Status = :secrecyStatusN )) ");
					params.put("keysectionDistrictCode", districtCode);
					params.put("secrecyStatusN", 0);
				}
			}

			if( hisFlag != null ){
				hql.append(" AND (secrecyper0_.secrecy_status is null or secrecyper0_.secrecy_status = :secrecyStatusNow) ");
				params.put("secrecyStatusNow", hisFlag);
			}

			hql.append(" AND secrecyper0_.secrecy_person_level is not null ");
			hql.append(" GROUP BY secrecyper0_.secrecy_person_level");
			List<Map<String,Object>> resultTemp =
					this.getPersistProxy().getJdbcPersistence().findList(hql.toString(), params);
			Map<Integer, Integer> resultRow = new HashMap<Integer, Integer>();
			if( resultTemp!=null ){
				Integer rowSum = 0;
				for(Map<String,Object> objArr : resultTemp ){
					rowSum += Integer.parseInt(objArr.get("num").toString());
					resultRow.put(Integer.parseInt(objArr.get("secrecy_person_level").toString()) , Integer.parseInt(objArr.get("num").toString()));


					resultRowSum.put(Integer.parseInt(objArr.get("secrecy_person_level").toString()), resultRowSum.get(Integer.parseInt(objArr.get("secrecy_person_level").toString()))+ Integer.parseInt(objArr.get("num").toString()));
					resultRowSum.put(4, resultRowSum.get(4)+ Integer.parseInt(objArr.get("num").toString()));
				}
				resultRow.put(4, rowSum);
			}
			aAgeRange.put(dicObj.getOptionText(), resultRow);
			resultList.add(aAgeRange);
		}
		Map<String,Map<Integer, Integer>> aAgeRangeSum = new HashMap<String, Map<Integer,Integer>>();
		aAgeRangeSum.put("合计", resultRowSum);
		resultList.add(aAgeRangeSum);
		return resultList;
	}

	public List<Map<String, Map<Integer, Integer>>> statisticsByLayerOfPolity(List<DictionaryOption> polityOptions, String layer, String organId, Integer hisFlag, String countScope){
		//最终结果
		List<Map<String, Map<Integer, Integer>>> resultList = new ArrayList<Map<String,Map<Integer,Integer>>>();
		//计算合计值
		Map<Integer, Integer> resultRowSum = new HashMap<Integer, Integer>();
		resultRowSum.put(1, 0);
		resultRowSum.put(2, 0);
		resultRowSum.put(3, 0);
		resultRowSum.put(4, 0);
		resultRowSum.put(1000, 0);

		for( DictionaryOption dicObj : polityOptions){
			// 一个政治面貌的
			Map<String,Map<Integer, Integer>> aAgeRange = new HashMap<String, Map<Integer,Integer>>();

			Map<String, Object> params = new HashMap<String, Object>();
			StringBuffer hql = new StringBuffer("select secrecyper0_.secrecy_person_level, count(secrecyper0_.secrecy_person_level) as num from bm_secrecy_person secrecyper0_ cross join SYS_ORGANIZATION organ1_ cross join SYS_DISTRICT district2_ cross join SYS_USER_INFO userinfo3_ where secrecyper0_.organ_id=organ1_.ORGAN_ID and organ1_.district_code=district2_.district_code and secrecyper0_.USER_INFO_ID=userinfo3_.PERSON_ID and 1=1 ");
			hql.append(" AND userinfo3_.polity = :dicValue");
			params.put("dicValue", dicObj.getOptionValue());

			if( StringUtils.isNotEmpty(organId) ){
				hql.append(" AND organ1_.organ_id = :organId ");
				params.put("organId", organId);
			}else if( StringUtils.isNotEmpty(layer) ){
				hql.append(" AND district2_.layer like :layer ");
				params.put("layer", layer+"%");

				if( "keysection".equals(countScope) ){
					//要害部门限制条件
					hql.append(" AND secrecyper0_.person_id in (SELECT DISTINCT bsp.person_id FROM bm_key_section bks, bm_secrecy_person bsp, sys_department sd, sys_organization so, sys_district sdi WHERE bsp.department_Id = bks.department_Id AND bks.organ_id = so.organ_id AND so.DISTRICT_CODE = sdi.district_code AND sdi.layer LIKE :keysectionLayer AND( bks.secrecy_Status IS NULL OR bks.secrecy_Status = :secrecyStatusN )) ");
					params.put("keysectionLayer", layer+"%");
					params.put("secrecyStatusN", 0);
				}

				if( "keypart".equals(countScope) ){
					//要害部位限制条件
					hql.append(" AND secrecyper0_.person_id in (SELECT DISTINCT bsp.person_id FROM bm_key_part bkp,bm_key_part_person bkpp,bm_secrecy_person bsp,sys_organization so,sys_district sdi WHERE bkp.KEY_PART_ID = bkpp.KEY_PART_ID AND bkpp.PERSON_ID = bsp.person_id AND bkp.ORGAN_ID = so.ORGAN_ID AND so.DISTRICT_CODE = sdi.district_code AND sdi.layer LIKE :keypartLayer AND( bkp.secrecy_Status IS NULL OR bkp.secrecy_Status = :secrecyStatusN)) ");
					params.put("keypartLayer", layer+"%");
					params.put("secrecyStatusN", 0);
				}

			}

			if( hisFlag != null ){
				hql.append(" AND (secrecyper0_.secrecy_status is null or secrecyper0_.secrecy_status = :secrecyStatusNow) ");
				params.put("secrecyStatusNow", hisFlag);
			}

			hql.append(" AND secrecyper0_.secrecy_person_level is not null ");
			hql.append(" GROUP BY secrecyper0_.secrecy_person_level");
			List<Map<String,Object>> resultTemp = this.getPersistProxy().getJdbcPersistence().findList(hql.toString(), params);
			Map<Integer, Integer> resultRow = new HashMap<Integer, Integer>();
			if( resultTemp!=null ){
				Integer rowSum = 0;
				for(Map<String,Object> objArr : resultTemp ){
					rowSum += Integer.parseInt(objArr.get("num").toString());
					resultRow.put(Integer.parseInt(objArr.get("secrecy_person_level").toString()) , Integer.parseInt(objArr.get("num").toString()));


					resultRowSum.put(Integer.parseInt(objArr.get("secrecy_person_level").toString()), resultRowSum.get(Integer.parseInt(objArr.get("secrecy_person_level").toString()))+ Integer.parseInt(objArr.get("num").toString()));
					resultRowSum.put(4, resultRowSum.get(4)+ Integer.parseInt(objArr.get("num").toString()));
				}
				resultRow.put(4, rowSum);
			}
			aAgeRange.put(dicObj.getOptionText(), resultRow);
			resultList.add(aAgeRange);
		}
		Map<String,Map<Integer, Integer>> aAgeRangeSum = new HashMap<String, Map<Integer,Integer>>();
		aAgeRangeSum.put("合计", resultRowSum);
		resultList.add(aAgeRangeSum);
		return resultList;
	}

	public Map<String, List<Map<String, Map<Integer, Integer>>>> statisticsByPolityOfAll(List<DictionaryOption> polityOptions, District district,  String organId, Integer hisFlag, String countScope){
		Map<String,List<Map<String, Map<Integer, Integer>>>> resultMap = new HashMap<String,List<Map<String, Map<Integer, Integer>>>>();
		if( district!=null ){
			resultMap.put(district.getDistrictName(), this.statisticsByLayerOfPolity(polityOptions, district.getLayer(), organId, hisFlag, countScope));
		}
		return resultMap;
	}
	public Map<String, List<Map<String, Map<Integer, Integer>>>> statisticsByPolityOfDistrict(List<DictionaryOption> polityOptions, District district,  String organId, Integer hisFlag, String countScope){
		Map<String,List<Map<String, Map<Integer, Integer>>>> returnResult = new HashMap<String,List<Map<String, Map<Integer, Integer>>>>();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append("SELECT district2_.district_name,district2_.district_code, district2_.layer FROM SYS_DISTRICT district2_ ");
		sqlStr.append("WHERE district2_.pid = :districtCode ");
		params.put("districtCode", district.getDistrictCode());
		List<Map<String,Object>> subDistrict = this.getPersistProxy().getJdbcPersistence().findList(sqlStr.toString(), params);
		if( subDistrict!=null ){
			for( Map<String,Object> aDistrict : subDistrict ){
				returnResult.put(aDistrict.get("district_name").toString(),this.statisticsByLayerOfPolity(polityOptions, aDistrict.get("layer").toString(), organId, hisFlag, countScope));
			}
		}
		return returnResult;

	}
	public Map<String, List<Map<String, Map<Integer, Integer>>>> statisticsByPolityOfOrgan(List<DictionaryOption> polityOptions, District district,  String organId, Integer hisFlag, String countScope){
		Map<String,List<Map<String, Map<Integer, Integer>>>> returnResult = new HashMap<String,List<Map<String, Map<Integer, Integer>>>>();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT o FROM  Organ o WHERE 1=1 ");
		hql.append("AND o.district.districtCode = :districtCode ");
		params.put("districtCode", district.getDistrictCode());
		List<Organ> subOrgan = this.getPersistProxy().getOrmPersistence().findList(hql.toString(), params);
		if( subOrgan!=null ){
			for( Organ tmpOrgan : subOrgan ){
				returnResult.put(tmpOrgan.getName(),this.statisticsByPolity(polityOptions, null, tmpOrgan.getOrganId(), hisFlag, countScope));
			}
		}
		return returnResult;
	}


	public List<Map<String, Map<Integer, Integer>>> statisticsByPersonType(List<DictionaryOption> personTypeOptions, String districtCode, String organId, Integer hisFlag, String countScope){
		//最终结果
		List<Map<String, Map<Integer, Integer>>> resultList = new ArrayList<Map<String,Map<Integer,Integer>>>();
		//计算合计值
		Map<Integer, Integer> resultRowSum = new HashMap<Integer, Integer>();
		resultRowSum.put(1, 0);
		resultRowSum.put(2, 0);
		resultRowSum.put(3, 0);
		resultRowSum.put(4, 0);
		resultRowSum.put(1000, 0);

		for( DictionaryOption dicObj : personTypeOptions){
			// 一个人员类型的
			Map<String,Map<Integer, Integer>> aAgeRange = new HashMap<String, Map<Integer,Integer>>();

			Map<String, Object> params = new HashMap<String, Object>();
			StringBuffer hql = new StringBuffer("select secrecyper0_.secrecy_person_level, count(secrecyper0_.secrecy_person_level) as num from bm_secrecy_person secrecyper0_ cross join SYS_ORGANIZATION organ1_ where secrecyper0_.organ_id=organ1_.ORGAN_ID and 1=1 ");
			hql.append(" AND secrecyper0_.person_type = :dicValue");
			params.put("dicValue", dicObj.getOptionValue());

			if( StringUtils.isNotEmpty(organId) ){
				hql.append(" AND organ1_.organ_id = :organId ");
				params.put("organId", organId);

				if("keysection".equals(countScope)){
					hql.append(" AND secrecyper0_.person_id in (SELECT bsp.person_id FROM `bm_key_section` bks JOIN bm_secrecy_person bsp ON  bsp.department_id=bks.DEPARTMENT_ID where bks.organ_id = :keysectionOrganId AND (bks.secrecy_status is null or bks.secrecy_status = :secrecyStatusN )) ");
					params.put("keysectionOrganId", organId);
					params.put("secrecyStatusN", 0);
				}

			}else if( StringUtils.isNotEmpty(districtCode) ){
				hql.append(" AND organ1_.district_code = :districtCode ");
				params.put("districtCode", districtCode);

				if("keysection".equals(countScope)){
					hql.append(" AND secrecyper0_.person_id in (SELECT DISTINCT bsp.person_id FROM bm_key_section bks, bm_secrecy_person bsp, sys_department sd, sys_organization so, sys_district sdi WHERE bsp.department_Id = bks.department_Id AND bks.organ_id = so.organ_id AND so.DISTRICT_CODE = sdi.district_code AND sdi.district_code LIKE :keysectionDistrictCode AND( bks.secrecy_Status IS NULL OR bks.secrecy_Status = :secrecyStatusN )) ");
					params.put("keysectionDistrictCode", districtCode);
					params.put("secrecyStatusN", 0);
				}
			}

			if( hisFlag != null ){
				hql.append(" AND (secrecyper0_.secrecy_status is null or secrecyper0_.secrecy_status = :secrecyStatusNow) ");
				params.put("secrecyStatusNow", hisFlag);
			}

			hql.append(" AND secrecyper0_.secrecy_person_level is not null ");
			hql.append(" GROUP BY secrecyper0_.secrecy_person_level");
			List<Map<String,Object>> resultTemp = this.getPersistProxy().getJdbcPersistence().findList(hql.toString(), params);
			Map<Integer, Integer> resultRow = new HashMap<Integer, Integer>();
			if( resultTemp!=null ){
				Integer rowSum = 0;
				for(Map<String,Object> objArr : resultTemp ){
					rowSum += Integer.parseInt(objArr.get("num").toString());
					resultRow.put(Integer.parseInt(objArr.get("secrecy_person_level").toString()) , Integer.parseInt(objArr.get("num").toString()));


					resultRowSum.put(Integer.parseInt(objArr.get("secrecy_person_level").toString()), resultRowSum.get(Integer.parseInt(objArr.get("secrecy_person_level").toString()))+ Integer.parseInt(objArr.get("num").toString()));
					resultRowSum.put(4, resultRowSum.get(4)+ Integer.parseInt(objArr.get("num").toString()));
				}
				resultRow.put(4, rowSum);
			}
			aAgeRange.put(dicObj.getOptionText(), resultRow);
			resultList.add(aAgeRange);
		}
		Map<String,Map<Integer, Integer>> aAgeRangeSum = new HashMap<String, Map<Integer,Integer>>();
		aAgeRangeSum.put("合计", resultRowSum);
		resultList.add(aAgeRangeSum);
		return resultList;
	}

	public List<Map<String, Map<Integer, Integer>>> statisticsByLayerOfPersonType(List<DictionaryOption> personTypeOptions, String layer, String organId, Integer hisFlag, String countScope){
		//最终结果
		List<Map<String, Map<Integer, Integer>>> resultList = new ArrayList<Map<String,Map<Integer,Integer>>>();
		//计算合计值
		Map<Integer, Integer> resultRowSum = new HashMap<Integer, Integer>();
		resultRowSum.put(1, 0);
		resultRowSum.put(2, 0);
		resultRowSum.put(3, 0);
		resultRowSum.put(4, 0);
		resultRowSum.put(1000, 0);

		for( DictionaryOption dicObj : personTypeOptions){
			// 一个人员类型的
			Map<String,Map<Integer, Integer>> aAgeRange = new HashMap<String, Map<Integer,Integer>>();

			Map<String, Object> params = new HashMap<String, Object>();
			StringBuffer hql = new StringBuffer("select secrecyper0_.secrecy_person_level, count(secrecyper0_.secrecy_person_level) as num from bm_secrecy_person secrecyper0_ cross join SYS_ORGANIZATION organ1_ cross join SYS_DISTRICT district2_ where secrecyper0_.organ_id=organ1_.ORGAN_ID and organ1_.district_code=district2_.district_code and 1=1 ");
			hql.append(" AND secrecyper0_.person_type = :dicValue");
			params.put("dicValue", dicObj.getOptionValue());

			if( StringUtils.isNotEmpty(organId) ){
				hql.append(" AND organ1_.organ_id = :organId ");
				params.put("organId", organId);
			}else if( StringUtils.isNotEmpty(layer) ){
				hql.append(" AND district2_.layer like :layer ");
				params.put("layer", layer+"%");

				hql.append(" AND secrecyper0_.person_id in (SELECT DISTINCT bsp.person_id FROM bm_key_section bks, bm_secrecy_person bsp, sys_department sd, sys_organization so, sys_district sdi WHERE bsp.department_Id = bks.department_Id AND bks.organ_id = so.organ_id AND so.DISTRICT_CODE = sdi.district_code AND sdi.layer LIKE :keysectionLayer AND( bks.secrecy_Status IS NULL OR bks.secrecy_Status = :secrecyStatusN )) ");
				params.put("keysectionLayer", layer+"%");
				params.put("secrecyStatusN", 0);

			}

			if( hisFlag != null ){
				hql.append(" AND (secrecyper0_.secrecy_status is null or secrecyper0_.secrecy_status = :secrecyStatusNow) ");
				params.put("secrecyStatusNow", hisFlag);
			}

			hql.append(" AND secrecyper0_.secrecy_person_level is not null ");
			hql.append(" GROUP BY secrecyper0_.secrecy_person_level");
			List<Map<String,Object>> resultTemp = this.getPersistProxy().getJdbcPersistence().findList(hql.toString(), params);
			Map<Integer, Integer> resultRow = new HashMap<Integer, Integer>();
			if( resultTemp!=null ){
				Integer rowSum = 0;
				for(Map<String,Object> objArr : resultTemp ){
					rowSum += Integer.parseInt(objArr.get("num").toString());
					resultRow.put(Integer.parseInt(objArr.get("secrecy_person_level").toString()) , Integer.parseInt(objArr.get("num").toString()));


					resultRowSum.put(Integer.parseInt(objArr.get("secrecy_person_level").toString()), resultRowSum.get(Integer.parseInt(objArr.get("secrecy_person_level").toString()))+ Integer.parseInt(objArr.get("num").toString()));
					resultRowSum.put(4, resultRowSum.get(4)+ Integer.parseInt(objArr.get("num").toString()));
				}
				resultRow.put(4, rowSum);
			}
			aAgeRange.put(dicObj.getOptionText(), resultRow);
			resultList.add(aAgeRange);
		}
		Map<String,Map<Integer, Integer>> aAgeRangeSum = new HashMap<String, Map<Integer,Integer>>();
		aAgeRangeSum.put("合计", resultRowSum);
		resultList.add(aAgeRangeSum);
		return resultList;
	}

	public Map<String,List<Map<String, Map<Integer, Integer>>>> statisticsByPersonTypeOfAll(List<DictionaryOption> personTypeOptions, District district,  String organId, Integer hisFlag, String countScope){
		Map<String,List<Map<String, Map<Integer, Integer>>>> resultMap = new HashMap<String,List<Map<String, Map<Integer, Integer>>>>();
		if( district!=null ){
			resultMap.put(district.getDistrictName(), this.statisticsByLayerOfPersonType(personTypeOptions, district.getLayer(), organId, hisFlag, countScope));
		}
		return resultMap;
	}
	public  Map<String,List<Map<String, Map<Integer, Integer>>>> statisticsByPersonTypeOfDistrict(List<DictionaryOption> personTypeOptions, District district,  String organId, Integer hisFlag, String countScope){
		Map<String,List<Map<String, Map<Integer, Integer>>>> returnResult = new HashMap<String,List<Map<String, Map<Integer, Integer>>>>();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append("SELECT district2_.district_name,district2_.district_code, district2_.layer FROM SYS_DISTRICT district2_ ");
		sqlStr.append("WHERE district2_.pid = :districtCode ");
		params.put("districtCode", district.getDistrictCode());
		List<Map<String,Object>> subDistrict = this.getPersistProxy().getJdbcPersistence().findList(sqlStr.toString(), params);
		if( subDistrict!=null ){
			for( Map<String,Object> aDistrict : subDistrict ){
				returnResult.put(aDistrict.get("district_name").toString(),this.statisticsByLayerOfPersonType(personTypeOptions, aDistrict.get("layer").toString(), organId, hisFlag, countScope));
			}
		}
		return returnResult;
	}
	public  Map<String,List<Map<String, Map<Integer, Integer>>>> statisticsByPersonTypeOfOrgan(List<DictionaryOption> personTypeOptions, District district,  String organId, Integer hisFlag, String countScope){
		Map<String,List<Map<String, Map<Integer, Integer>>>> returnResult = new HashMap<String,List<Map<String, Map<Integer, Integer>>>>();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT o FROM  Organ o WHERE 1=1 ");
		hql.append("AND o.district.districtCode = :districtCode ");
		params.put("districtCode", district.getDistrictCode());
		List<Organ> subOrgan = this.getPersistProxy().getOrmPersistence().findList(hql.toString(), params);
		if( subOrgan!=null ){
			for( Organ tmpOrgan : subOrgan ){
				returnResult.put(tmpOrgan.getName(),this.statisticsByPersonType(personTypeOptions, null, tmpOrgan.getOrganId(), hisFlag, countScope));
			}
		}
		return returnResult;
	}

	public List<Map<String, Map<Integer, Integer>>> statisticsByAdmin(List<DictionaryOption> adminLevelOptions, String districtCode, String organId, Integer hisFlag, String countScope){
		//最终结果
		List<Map<String, Map<Integer, Integer>>> resultList = new ArrayList<Map<String,Map<Integer,Integer>>>();
		//计算合计值
		Map<Integer, Integer> resultRowSum = new HashMap<Integer, Integer>();
		resultRowSum.put(1, 0);
		resultRowSum.put(2, 0);
		resultRowSum.put(3, 0);
		resultRowSum.put(4, 0);
		resultRowSum.put(1000, 0);

		for( DictionaryOption dicObj : adminLevelOptions){
			// 一个行政级别的
			Map<String,Map<Integer, Integer>> aAgeRange = new HashMap<String, Map<Integer,Integer>>();

			Map<String, Object> params = new HashMap<String, Object>();
			StringBuffer hql = new StringBuffer("select secrecyper0_.secrecy_person_level , count(secrecyper0_.secrecy_person_level) as num from bm_secrecy_person secrecyper0_ cross join SYS_ORGANIZATION organ1_ cross join SYS_USER_INFO userinfo2_ where secrecyper0_.organ_id=organ1_.ORGAN_ID and secrecyper0_.USER_INFO_ID=userinfo2_.PERSON_ID and 1=1 ");
			hql.append(" AND userinfo2_.administrative_level = :dicValue");
			params.put("dicValue", dicObj.getOptionValue());

			if( StringUtils.isNotEmpty(organId) ){
				hql.append(" AND organ1_.organ_id = :organId ");
				params.put("organId", organId);

				if( "keysection".equals(countScope) ){
					//要害部门限制条件
					hql.append(" AND secrecyper0_.person_id in (SELECT bsp.person_id FROM `bm_key_section` bks JOIN bm_secrecy_person bsp ON  bsp.department_id=bks.DEPARTMENT_ID where bks.organ_id = :keysectionOrganId AND (bks.secrecy_status is null or bks.secrecy_status = :secrecyStatusN )) ");
					params.put("keysectionOrganId", organId);
					params.put("secrecyStatusN", 0);
				}
				if( "keypart".equals(countScope) ){
					//要害部位限制条件
					hql.append(" AND secrecyper0_.person_id in (SELECT DISTINCT bsp.person_id FROM `bm_key_part_person` bkpp,`bm_secrecy_person` bsp,`bm_key_part` bkp WHERE bkpp.PERSON_ID = bsp.person_id AND bkp.KEY_PART_ID = bkpp.KEY_PART_ID AND bkp.ORGAN_ID =:keyPartOrganId AND( bkp.secrecy_status IS NULL OR bkp.secrecy_status = :secrecyStatusN)) ");
					params.put("keyPartOrganId", organId);
					params.put("secrecyStatusN", 0);
				}


			}else if( StringUtils.isNotEmpty(districtCode) ){
				hql.append(" AND organ1_.district_code = :districtCode ");
				params.put("districtCode", districtCode);
				if( "keysection".equals(countScope) ){
					hql.append(" AND secrecyper0_.person_id in (SELECT DISTINCT bsp.person_id FROM bm_key_section bks, bm_secrecy_person bsp, sys_department sd, sys_organization so, sys_district sdi WHERE bsp.department_Id = bks.department_Id AND bks.organ_id = so.organ_id AND so.DISTRICT_CODE = sdi.district_code AND sdi.district_code LIKE :keysectionDistrictCode AND( bks.secrecy_Status IS NULL OR bks.secrecy_Status = :secrecyStatusN )) ");
					params.put("keysectionDistrictCode", districtCode);
					params.put("secrecyStatusN", 0);
				}

			}

			if( hisFlag != null ){
				hql.append(" AND (secrecyper0_.secrecy_status is null or secrecyper0_.secrecy_status = :secrecyStatusNow) ");
				params.put("secrecyStatusNow", hisFlag);
			}

			hql.append(" AND secrecyper0_.secrecy_person_level is not null ");
			hql.append(" GROUP BY secrecyper0_.secrecy_person_level");
			List<Map<String,Object>> resultTemp = this.getPersistProxy().getJdbcPersistence().findList(hql.toString(), params);
			Map<Integer, Integer> resultRow = new HashMap<Integer, Integer>();
			if( resultTemp!=null ){
				Integer rowSum = 0;
				for(Map<String,Object> objArr : resultTemp ){
					rowSum += Integer.parseInt(objArr.get("num").toString());
					resultRow.put(Integer.parseInt(objArr.get("secrecy_person_level").toString()) , Integer.parseInt(objArr.get("num").toString()));


					resultRowSum.put(Integer.parseInt(objArr.get("secrecy_person_level").toString()), resultRowSum.get(Integer.parseInt(objArr.get("secrecy_person_level").toString()))+ Integer.parseInt(objArr.get("num").toString()));
					resultRowSum.put(4, resultRowSum.get(4)+ Integer.parseInt(objArr.get("num").toString()));
				}
				resultRow.put(4, rowSum);
			}
			aAgeRange.put(dicObj.getOptionText(), resultRow);
			resultList.add(aAgeRange);
		}
		Map<String,Map<Integer, Integer>> aAgeRangeSum = new HashMap<String, Map<Integer,Integer>>();
		aAgeRangeSum.put("合计", resultRowSum);
		resultList.add(aAgeRangeSum);
		return resultList;
	}

	public List<Map<String, Map<Integer, Integer>>> statisticsByLayerOfAdmin(List<DictionaryOption> adminLevelOptions, String layer, String organId, Integer hisFlag, String countScope){
		//最终结果
		List<Map<String, Map<Integer, Integer>>> resultList = new ArrayList<Map<String,Map<Integer,Integer>>>();
		//计算合计值
		Map<Integer, Integer> resultRowSum = new HashMap<Integer, Integer>();
		resultRowSum.put(1, 0);
		resultRowSum.put(2, 0);
		resultRowSum.put(3, 0);
		resultRowSum.put(4, 0);
		resultRowSum.put(1000, 0);

		for( DictionaryOption dicObj : adminLevelOptions){
			// 一个行政级别的
			Map<String,Map<Integer, Integer>> aAgeRange = new HashMap<String, Map<Integer,Integer>>();

			Map<String, Object> params = new HashMap<String, Object>();
			StringBuffer hql = new StringBuffer("select secrecyper0_.secrecy_person_level, count(secrecyper0_.secrecy_person_level) as num from bm_secrecy_person secrecyper0_ cross join SYS_ORGANIZATION organ1_ cross join SYS_DISTRICT district2_ cross join SYS_USER_INFO userinfo3_ where secrecyper0_.organ_id=organ1_.ORGAN_ID and organ1_.district_code=district2_.district_code and secrecyper0_.USER_INFO_ID=userinfo3_.PERSON_ID and 1=1 ");
			hql.append(" AND userinfo3_.administrative_level = :dicValue");
			params.put("dicValue", dicObj.getOptionValue());

			if( StringUtils.isNotEmpty(organId) ){
				hql.append(" AND organ1_.organ_id = :organId ");
				params.put("organId", organId);
			}else if( StringUtils.isNotEmpty(layer) ){
				hql.append(" AND district2_.layer like :layer ");
				params.put("layer", layer+"%");

				if( "keysection".equals(countScope) ){
					//要害部门限制条件
					hql.append(" AND secrecyper0_.person_id in (SELECT DISTINCT bsp.person_id FROM bm_key_section bks, bm_secrecy_person bsp, sys_department sd, sys_organization so, sys_district sdi WHERE bsp.department_Id = bks.department_Id AND bks.organ_id = so.organ_id AND so.DISTRICT_CODE = sdi.district_code AND sdi.layer LIKE :keysectionLayer AND( bks.secrecy_Status IS NULL OR bks.secrecy_Status = :secrecyStatusN )) ");
					params.put("keysectionLayer", layer+"%");
					params.put("secrecyStatusN", 0);
				}

				if( "keypart".equals(countScope) ){
					//要害部位限制条件
					hql.append(" AND secrecyper0_.person_id in (SELECT DISTINCT bsp.person_id FROM bm_key_part bkp,bm_key_part_person bkpp,bm_secrecy_person bsp,sys_organization so,sys_district sdi WHERE bkp.KEY_PART_ID = bkpp.KEY_PART_ID AND bkpp.PERSON_ID = bsp.person_id AND bkp.ORGAN_ID = so.ORGAN_ID AND so.DISTRICT_CODE = sdi.district_code AND sdi.layer LIKE :keypartLayer AND( bkp.secrecy_Status IS NULL OR bkp.secrecy_Status = :secrecyStatusN)) ");
					params.put("keypartLayer", layer+"%");
					params.put("secrecyStatusN", 0);
				}

			}

			if( hisFlag != null ){
				hql.append(" AND (secrecyper0_.secrecy_status is null or secrecyper0_.secrecy_status = :secrecyStatusNow) ");
				params.put("secrecyStatusNow", hisFlag);
			}

			hql.append(" AND secrecyper0_.secrecy_person_level is not null ");
			hql.append(" GROUP BY secrecyper0_.secrecy_person_level");
			List<Map<String,Object>> resultTemp = this.getPersistProxy().getJdbcPersistence().findList(hql.toString(), params);
			Map<Integer, Integer> resultRow = new HashMap<Integer, Integer>();
			if( resultTemp!=null ){
				Integer rowSum = 0;
				for(Map<String,Object> objArr : resultTemp ){
					rowSum += Integer.parseInt(objArr.get("num").toString());
					resultRow.put(Integer.parseInt(objArr.get("secrecy_person_level").toString()) , Integer.parseInt(objArr.get("num").toString()));


					resultRowSum.put(Integer.parseInt(objArr.get("secrecy_person_level").toString()), resultRowSum.get(Integer.parseInt(objArr.get("secrecy_person_level").toString()))+ Integer.parseInt(objArr.get("num").toString()));
					resultRowSum.put(4, resultRowSum.get(4)+ Integer.parseInt(objArr.get("num").toString()));
				}
				resultRow.put(4, rowSum);
			}
			aAgeRange.put(dicObj.getOptionText(), resultRow);
			resultList.add(aAgeRange);
		}
		Map<String,Map<Integer, Integer>> aAgeRangeSum = new HashMap<String, Map<Integer,Integer>>();
		aAgeRangeSum.put("合计", resultRowSum);
		resultList.add(aAgeRangeSum);
		return resultList;
	}
	public Map<String, List<Map<String, Map<Integer, Integer>>>> statisticsByAdminOfAll(List<DictionaryOption> personTypeOptions, District district,  String organId, Integer hisFlag, String countScope){
		Map<String,List<Map<String, Map<Integer, Integer>>>> resultMap = new HashMap<String,List<Map<String, Map<Integer, Integer>>>>();
		if( district!=null ){
			resultMap.put(district.getDistrictName(), this.statisticsByLayerOfAdmin(personTypeOptions, district.getLayer(), organId, hisFlag, countScope));
		}
		return resultMap;
	}
	public Map<String, List<Map<String, Map<Integer, Integer>>>> statisticsByAdminOfDistrict(List<DictionaryOption> personTypeOptions, District district,  String organId, Integer hisFlag, String countScope){
		Map<String,List<Map<String, Map<Integer, Integer>>>> returnResult = new HashMap<String,List<Map<String, Map<Integer, Integer>>>>();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append("SELECT district2_.district_name,district2_.district_code, district2_.layer FROM SYS_DISTRICT district2_ ");
		sqlStr.append("WHERE district2_.pid = :districtCode ");
		params.put("districtCode", district.getDistrictCode());
		List<Map<String,Object>> subDistrict = this.getPersistProxy().getJdbcPersistence().findList(sqlStr.toString(), params);
		if( subDistrict!=null ){
			for( Map<String,Object> aDistrict : subDistrict ){
				returnResult.put(aDistrict.get("district_name").toString(),this.statisticsByLayerOfAdmin(personTypeOptions, aDistrict.get("layer").toString(), organId, hisFlag, countScope));
			}
		}
		return returnResult;
	}
	public Map<String, List<Map<String, Map<Integer, Integer>>>> statisticsByAdminOfOrgan(List<DictionaryOption> personTypeOptions, District district,  String organId, Integer hisFlag, String countScope){
		Map<String,List<Map<String, Map<Integer, Integer>>>> returnResult = new HashMap<String,List<Map<String, Map<Integer, Integer>>>>();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT o FROM  Organ o WHERE 1=1 ");
		hql.append("AND o.district.districtCode = :districtCode ");
		params.put("districtCode", district.getDistrictCode());
		List<Organ> subOrgan = this.getPersistProxy().getOrmPersistence().findList(hql.toString(), params);
		if( subOrgan!=null ){
			for( Organ tmpOrgan : subOrgan ){
				returnResult.put(tmpOrgan.getName(),this.statisticsByAdmin(personTypeOptions, null, tmpOrgan.getOrganId(), hisFlag, countScope));
			}
		}
		return returnResult;

	}

	public List<Map<String, Map<Integer, Integer>>> statisticsByTechLevel(List<DictionaryOption> technicTitleLevelOptions, String districtCode, String organId, Integer hisFlag, String countScope){
		//最终结果
		List<Map<String, Map<Integer, Integer>>> resultList = new ArrayList<Map<String,Map<Integer,Integer>>>();
		//计算合计值
		Map<Integer, Integer> resultRowSum = new HashMap<Integer, Integer>();
		resultRowSum.put(1, 0);
		resultRowSum.put(2, 0);
		resultRowSum.put(3, 0);
		resultRowSum.put(4, 0);
		resultRowSum.put(1000, 0);

		for( DictionaryOption dicObj : technicTitleLevelOptions){
			// 一个技术职称的
			Map<String,Map<Integer, Integer>> aAgeRange = new HashMap<String, Map<Integer,Integer>>();

			Map<String, Object> params = new HashMap<String, Object>();
			StringBuffer hql = new StringBuffer("select secrecyper0_.secrecy_person_level , count(secrecyper0_.secrecy_person_level) as num from bm_secrecy_person secrecyper0_ cross join SYS_ORGANIZATION organ1_ cross join SYS_USER_INFO userinfo2_ where secrecyper0_.organ_id=organ1_.ORGAN_ID and secrecyper0_.USER_INFO_ID=userinfo2_.PERSON_ID and 1=1 ");
			hql.append(" AND userinfo2_.technic_title_level = :dicValue");
			params.put("dicValue", dicObj.getOptionValue());

			if( StringUtils.isNotEmpty(organId) ){
				hql.append(" AND organ1_.organ_id = :organId ");
				params.put("organId", organId);
				if( "keysection".equals(countScope) ){
					//要害部门限制条件
					hql.append(" AND secrecyper0_.person_id in (SELECT bsp.person_id FROM `bm_key_section` bks JOIN bm_secrecy_person bsp ON  bsp.department_id=bks.DEPARTMENT_ID where bks.organ_id = :keysectionOrganId AND (bks.secrecy_status is null or bks.secrecy_status = :secrecyStatusN )) ");
					params.put("keysectionOrganId", organId);
					params.put("secrecyStatusN", 0);
				}

				if( "keypart".equals(countScope) ){
					//要害部位限制条件
					hql.append(" AND secrecyper0_.person_id in (SELECT DISTINCT bsp.person_id FROM `bm_key_part_person` bkpp,`bm_secrecy_person` bsp,`bm_key_part` bkp WHERE bkpp.PERSON_ID = bsp.person_id AND bkp.KEY_PART_ID = bkpp.KEY_PART_ID AND bkp.ORGAN_ID =:keyPartOrganId AND( bkp.secrecy_status IS NULL OR bkp.secrecy_status = :secrecyStatusN)) ");
					params.put("keyPartOrganId", organId);
					params.put("secrecyStatusN", 0);
				}

			}else if( StringUtils.isNotEmpty(districtCode) ){
				hql.append(" AND organ1_.district_code = :districtCode ");
				params.put("districtCode", districtCode);

				if( "keysection".equals(countScope) ){
					hql.append(" AND secrecyper0_.person_id in (SELECT DISTINCT bsp.person_id FROM bm_key_section bks, bm_secrecy_person bsp, sys_department sd, sys_organization so, sys_district sdi WHERE bsp.department_Id = bks.department_Id AND bks.organ_id = so.organ_id AND so.DISTRICT_CODE = sdi.district_code AND sdi.district_code LIKE :keysectionDistrictCode AND( bks.secrecy_Status IS NULL OR bks.secrecy_Status = :secrecyStatusN )) ");
					params.put("keysectionDistrictCode", districtCode);
					params.put("secrecyStatusN", 0);
				}
			}

			if( hisFlag != null ){
				hql.append(" AND (secrecyper0_.secrecy_status is null or secrecyper0_.secrecy_status = :secrecyStatusNow) ");
				params.put("secrecyStatusNow", hisFlag);
			}

			hql.append(" AND secrecyper0_.secrecy_person_level is not null ");
			hql.append(" GROUP BY secrecyper0_.secrecy_person_level");
			List<Map<String,Object>> resultTemp = this.getPersistProxy().getJdbcPersistence().findList(hql.toString(), params);
			Map<Integer, Integer> resultRow = new HashMap<Integer, Integer>();
			if( resultTemp!=null ){
				Integer rowSum = 0;
				for(Map<String,Object> objArr : resultTemp ){
					rowSum += Integer.parseInt(objArr.get("num").toString());
					resultRow.put(Integer.parseInt(objArr.get("secrecy_person_level").toString()) , Integer.parseInt(objArr.get("num").toString()));


					resultRowSum.put(Integer.parseInt(objArr.get("secrecy_person_level").toString()), resultRowSum.get(Integer.parseInt(objArr.get("secrecy_person_level").toString()))+ Integer.parseInt(objArr.get("num").toString()));
					resultRowSum.put(4, resultRowSum.get(4)+ Integer.parseInt(objArr.get("num").toString()));
				}
				resultRow.put(4, rowSum);
			}
			aAgeRange.put(dicObj.getOptionText(), resultRow);
			resultList.add(aAgeRange);
		}
		Map<String,Map<Integer, Integer>> aAgeRangeSum = new HashMap<String, Map<Integer,Integer>>();
		aAgeRangeSum.put("合计", resultRowSum);
		resultList.add(aAgeRangeSum);
		return resultList;
	}

	public List<Map<String, Map<Integer, Integer>>> statisticsByLayerOfTechLevel(List<DictionaryOption> technicTitleLevelOptions, String layer, String organId, Integer hisFlag, String countScope){
		//最终结果
		List<Map<String, Map<Integer, Integer>>> resultList = new ArrayList<Map<String,Map<Integer,Integer>>>();
		//计算合计值
		Map<Integer, Integer> resultRowSum = new HashMap<Integer, Integer>();
		resultRowSum.put(1, 0);
		resultRowSum.put(2, 0);
		resultRowSum.put(3, 0);
		resultRowSum.put(4, 0);
		resultRowSum.put(1000, 0);

		for( DictionaryOption dicObj : technicTitleLevelOptions){
			// 一个技术职称的
			Map<String,Map<Integer, Integer>> aAgeRange = new HashMap<String, Map<Integer,Integer>>();

			Map<String, Object> params = new HashMap<String, Object>();
			StringBuffer hql = new StringBuffer("select secrecyper0_.secrecy_person_level, count(secrecyper0_.secrecy_person_level) as num from bm_secrecy_person secrecyper0_ cross join SYS_ORGANIZATION organ1_ cross join SYS_DISTRICT district2_ cross join SYS_USER_INFO userinfo3_ where secrecyper0_.organ_id=organ1_.ORGAN_ID and organ1_.district_code=district2_.district_code and secrecyper0_.USER_INFO_ID=userinfo3_.PERSON_ID and 1=1 ");
			hql.append(" AND userinfo3_.technic_title_level = :dicValue");
			params.put("dicValue", dicObj.getOptionValue());

			if( StringUtils.isNotEmpty(organId) ){
				hql.append(" AND organ1_.organ_id = :organId ");
				params.put("organId", organId);
			}else if( StringUtils.isNotEmpty(layer) ){
				hql.append(" AND district2_.layer like :layer ");
				params.put("layer", layer+"%");

				if( "keysection".equals(countScope)){
				//要害部门限制条件
					hql.append(" AND secrecyper0_.person_id in (SELECT DISTINCT bsp.person_id FROM bm_key_section bks, bm_secrecy_person bsp, sys_department sd, sys_organization so, sys_district sdi WHERE bsp.department_Id = bks.department_Id AND bks.organ_id = so.organ_id AND so.DISTRICT_CODE = sdi.district_code AND sdi.layer LIKE :keysectionLayer AND( bks.secrecy_Status IS NULL OR bks.secrecy_Status = :secrecyStatusN )) ");
					params.put("keysectionLayer", layer+"%");
					params.put("secrecyStatusN", 0);
				}
				if( "keypart".equals(countScope) ){
					//要害部位限制条件
					hql.append(" AND secrecyper0_.person_id in (SELECT DISTINCT bsp.person_id FROM bm_key_part bkp,bm_key_part_person bkpp,bm_secrecy_person bsp,sys_organization so,sys_district sdi WHERE bkp.KEY_PART_ID = bkpp.KEY_PART_ID AND bkpp.PERSON_ID = bsp.person_id AND bkp.ORGAN_ID = so.ORGAN_ID AND so.DISTRICT_CODE = sdi.district_code AND sdi.layer LIKE :keypartLayer AND( bkp.secrecy_Status IS NULL OR bkp.secrecy_Status = :secrecyStatusN)) ");
					params.put("keypartLayer", layer+"%");
					params.put("secrecyStatusN", 0);
				}

			}

			if( hisFlag != null ){
				hql.append(" AND (secrecyper0_.secrecy_status is null or secrecyper0_.secrecy_status = :secrecyStatusNow) ");
				params.put("secrecyStatusNow", hisFlag);
			}

			hql.append(" AND secrecyper0_.secrecy_person_level is not null ");
			hql.append(" GROUP BY secrecyper0_.secrecy_person_level");
			List<Map<String,Object>> resultTemp = this.getPersistProxy().getJdbcPersistence().findList(hql.toString(), params);
			Map<Integer, Integer> resultRow = new HashMap<Integer, Integer>();
			if( resultTemp!=null ){
				Integer rowSum = 0;
				for(Map<String,Object> objArr : resultTemp ){
					rowSum += Integer.parseInt(objArr.get("num").toString());
					resultRow.put(Integer.parseInt(objArr.get("secrecy_person_level").toString()) , Integer.parseInt(objArr.get("num").toString()));


					resultRowSum.put(Integer.parseInt(objArr.get("secrecy_person_level").toString()), resultRowSum.get(Integer.parseInt(objArr.get("secrecy_person_level").toString()))+ Integer.parseInt(objArr.get("num").toString()));
					resultRowSum.put(4, resultRowSum.get(4)+ Integer.parseInt(objArr.get("num").toString()));
				}
				resultRow.put(4, rowSum);
			}
			aAgeRange.put(dicObj.getOptionText(), resultRow);
			resultList.add(aAgeRange);
		}
		Map<String,Map<Integer, Integer>> aAgeRangeSum = new HashMap<String, Map<Integer,Integer>>();
		aAgeRangeSum.put("合计", resultRowSum);
		resultList.add(aAgeRangeSum);
		return resultList;
	}

	public Map<String, List<Map<String, Map<Integer, Integer>>>> statisticsByTechLevelOfAll(List<DictionaryOption> personTypeOptions, District district,  String organId, Integer hisFlag, String countScope){
		Map<String,List<Map<String, Map<Integer, Integer>>>> resultMap = new HashMap<String,List<Map<String, Map<Integer, Integer>>>>();
		if( district!=null ){
			resultMap.put(district.getDistrictName(), this.statisticsByLayerOfTechLevel(personTypeOptions, district.getLayer(), organId, hisFlag, countScope));
		}
		return resultMap;
	}
	public Map<String, List<Map<String, Map<Integer, Integer>>>> statisticsByTechLevelOfDistrict(List<DictionaryOption> personTypeOptions, District district,  String organId, Integer hisFlag, String countScope){
		Map<String,List<Map<String, Map<Integer, Integer>>>> returnResult = new HashMap<String,List<Map<String, Map<Integer, Integer>>>>();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append("SELECT district2_.district_name,district2_.district_code, district2_.layer FROM SYS_DISTRICT district2_ ");
		sqlStr.append("WHERE district2_.pid = :districtCode ");
		params.put("districtCode", district.getDistrictCode());
		List<Map<String,Object>> subDistrict = this.getPersistProxy().getJdbcPersistence().findList(sqlStr.toString(), params);
		if( subDistrict!=null ){
			for( Map<String,Object> aDistrict : subDistrict ){
				returnResult.put(aDistrict.get("district_name").toString(),this.statisticsByLayerOfTechLevel(personTypeOptions, aDistrict.get("layer").toString(), organId, hisFlag, countScope));
			}
		}
		return returnResult;
	}
	public Map<String, List<Map<String, Map<Integer, Integer>>>> statisticsByTechLevelOfOrgan(List<DictionaryOption> personTypeOptions, District district,  String organId, Integer hisFlag, String countScope){
		Map<String,List<Map<String, Map<Integer, Integer>>>> returnResult = new HashMap<String,List<Map<String, Map<Integer, Integer>>>>();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT o FROM  Organ o WHERE 1=1 ");
		hql.append("AND o.district.districtCode = :districtCode ");
		params.put("districtCode", district.getDistrictCode());
		List<Organ> subOrgan = this.getPersistProxy().getOrmPersistence().findList(hql.toString(), params);

		if( subOrgan!=null ){
			for( Organ tmpOrgan : subOrgan ){
				returnResult.put(tmpOrgan.getName(),this.statisticsByTechLevel(personTypeOptions, null, tmpOrgan.getOrganId(), hisFlag, countScope));
			}
		}
		return returnResult;
	}


	//行政区内
	public Map<Integer, Integer> countOfLayer(String layer, String organId, Integer hisFlag){
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer sqlStr = new StringBuffer("SELECT secrecyper0_.secrecy_person_level,count(secrecyper0_.secrecy_person_level)AS num FROM ");
		sqlStr.append("bm_secrecy_person secrecyper0_ ");
		sqlStr.append("CROSS JOIN SYS_ORGANIZATION organ1_ ");
		sqlStr.append("CROSS JOIN SYS_DISTRICT district2_ ");
		sqlStr.append("CROSS JOIN SYS_USER_INFO userinfo3_ ");
		sqlStr.append("WHERE ");
		sqlStr.append("secrecyper0_.organ_id = organ1_.ORGAN_ID ");
		sqlStr.append("AND organ1_.district_code = district2_.district_code ");
		sqlStr.append("AND secrecyper0_.USER_INFO_ID = userinfo3_.PERSON_ID ");
		sqlStr.append("AND 1 = 1 ");

		if( StringUtils.isNotEmpty(organId) ){
			sqlStr.append(" AND organ1_.organ_id = :organId ");
			params.put("organId", organId);
		}else if( StringUtils.isNotEmpty(layer) ){
			sqlStr.append("AND district2_.layer LIKE :layer ");
			params.put("layer", layer+"%");
		}
		sqlStr.append(" AND secrecyper0_.secrecy_person_level is not null ");
		sqlStr.append("GROUP BY ");
		sqlStr.append("secrecyper0_.secrecy_person_level ");
		List<Map<String,Object>> resultTemp = this.getPersistProxy().getJdbcPersistence().findList(sqlStr.toString(), params);
		Map<Integer, Integer> resultRow = new HashMap<Integer, Integer>();
		if( resultTemp!=null ){
			for(Map<String,Object> objArr : resultTemp ){
				resultRow.put(Integer.parseInt(objArr.get("secrecy_person_level").toString()) , Integer.parseInt(objArr.get("num").toString()));
			}
		}
		return resultRow;
	}

	//直辖
	public Map<Integer, Integer> countOfDistrnct(String layer, String organId, Integer hisFlag){
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer sqlStr = new StringBuffer("SELECT secrecyper0_.secrecy_person_level,count(secrecyper0_.secrecy_person_level)AS num FROM ");
		sqlStr.append("bm_secrecy_person secrecyper0_ ");
		sqlStr.append("CROSS JOIN SYS_ORGANIZATION organ1_ ");
		sqlStr.append("CROSS JOIN SYS_DISTRICT district2_ ");
		sqlStr.append("CROSS JOIN SYS_USER_INFO userinfo3_ ");
		sqlStr.append("WHERE ");
		sqlStr.append("secrecyper0_.organ_id = organ1_.ORGAN_ID ");
		sqlStr.append("AND organ1_.district_code = district2_.district_code ");
		sqlStr.append("AND secrecyper0_.USER_INFO_ID = userinfo3_.PERSON_ID ");
		sqlStr.append("AND 1 = 1 ");

		if( StringUtils.isNotEmpty(organId) ){
			sqlStr.append("AND organ1_.organ_id = :organId ");
			params.put("organId", organId);
		}else if( StringUtils.isNotEmpty(layer) ){
			sqlStr.append("AND district2_.layer = :layer ");
			params.put("layer", layer);
		}
		sqlStr.append(" AND secrecyper0_.secrecy_person_level is not null ");
		sqlStr.append("GROUP BY ");
		sqlStr.append("secrecyper0_.secrecy_person_level ");
		List<Map<String,Object>> resultTemp = this.getPersistProxy().getJdbcPersistence().findList(sqlStr.toString(), params);
		Map<Integer, Integer> resultRow = new HashMap<Integer, Integer>();
		if( resultTemp!=null ){
			for(Map<String,Object> objArr : resultTemp ){
				resultRow.put(Integer.parseInt(objArr.get("secrecy_person_level").toString()) , Integer.parseInt(objArr.get("num").toString()));
			}
		}
		return resultRow;
	}

	public Map<String,Map<String, Map<Integer,Integer>>> statisticsOverViewCurrent(District district, String organId, Integer hisFlag){
		Map<String,Map<String, Map<Integer,Integer>>> returnResult = new HashMap<String, Map<String,Map<Integer,Integer>>>();
		Map<String, Map<Integer,Integer>> aResutl = new HashMap<String, Map<Integer,Integer>>();
		aResutl.put("行政区内", this.countOfLayer(district.getLayer(), organId, hisFlag));
		aResutl.put("直机构", this.countOfDistrnct(district.getLayer(), organId, hisFlag));
		//
		if( organId!=null && !"".equals(organId) ){
			Organ currentOrgan = this.get(organId, Organ.class);
			if( currentOrgan!=null ){
				returnResult.put(currentOrgan.getOrganName(), aResutl);
			}else{
				returnResult.put("单位", aResutl);
			}
		}else{
			returnResult.put(district.getName(), aResutl);
		}
		return returnResult;
	}

	public Map<String,Map<String, Map<Integer,Integer>>>  statisticsOverViewDistinct(District district, String organId, Integer hisFlag){
		//查询其下行政区划
		Map<String,Map<String, Map<Integer,Integer>>> returnResult = new HashMap<String, Map<String,Map<Integer,Integer>>>();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append("SELECT district2_.district_name,district2_.district_code, district2_.layer FROM SYS_DISTRICT district2_ ");
		sqlStr.append("WHERE district2_.pid = :districtCode ");
		params.put("districtCode", district.getDistrictCode());
		List<Map<String,Object>> subDistrict = this.getPersistProxy().getJdbcPersistence().findList(sqlStr.toString(), params);
		if( subDistrict!=null ){
			for( Map<String,Object> aDistrict : subDistrict ){
				Map<String, Map<Integer,Integer>> aResutl = new HashMap<String, Map<Integer,Integer>>();
				aResutl.put("行政区内", this.countOfLayer(aDistrict.get("layer").toString(), organId, hisFlag));
				aResutl.put("直机构", this.countOfDistrnct(aDistrict.get("layer").toString(), organId, hisFlag));
				returnResult.put(aDistrict.get("district_name").toString(), aResutl);
			}
		}
		return returnResult;
	}

	public Map<String,Map<Integer,Integer>> statisticsOverViewOgran(District district, String organId, Integer hisFlag, String countScope){
		Map<String,Map<Integer,Integer>> returnResult = new HashMap<String, Map<Integer,Integer>>();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append("SELECT ");
		sqlStr.append("secrecyper0_.secrecy_person_level, ");
		sqlStr.append("organ1_.ORGAN_ID,organ1_.ORGAN_NAME, ");
		sqlStr.append("count(");
		sqlStr.append("secrecyper0_.secrecy_person_level");
		sqlStr.append(") AS num ");
		sqlStr.append("FROM ");
		sqlStr.append("bm_secrecy_person secrecyper0_ ");
		sqlStr.append("CROSS JOIN SYS_ORGANIZATION organ1_ ");
		sqlStr.append("CROSS JOIN SYS_DISTRICT district2_ ");
		sqlStr.append("WHERE ");
		sqlStr.append("secrecyper0_.organ_id = organ1_.ORGAN_ID ");
		sqlStr.append("AND organ1_.district_code = district2_.district_code ");
		sqlStr.append("AND 1 = 1 ");

		if( StringUtils.isNotEmpty(organId) ){
			sqlStr.append("AND organ1_.organ_id= :organId ");
			params.put("organId", organId);

			if( "keysection".equals(countScope) ){
				//要害部门限制条件
				sqlStr.append(" AND secrecyper0_.person_id in (SELECT bsp.person_id FROM `bm_key_section` bks JOIN bm_secrecy_person bsp ON  bsp.department_id=bks.DEPARTMENT_ID where bks.organ_id = :keysectionOrganId AND (bks.secrecy_status is null or bks.secrecy_status = :secrecyStatusN )) ");
				params.put("keysectionOrganId", organId);
				params.put("secrecyStatusN", 0);
			}

			if( "keypart".equals(countScope) ){
				//要害部位限制条件
				sqlStr.append(" AND secrecyper0_.person_id in (SELECT DISTINCT bsp.person_id FROM `bm_key_part_person` bkpp,`bm_secrecy_person` bsp,`bm_key_part` bkp WHERE bkpp.PERSON_ID = bsp.person_id AND bkp.KEY_PART_ID = bkpp.KEY_PART_ID AND bkp.ORGAN_ID =:keyPartOrganId AND( bkp.secrecy_status IS NULL OR bkp.secrecy_status = :secrecyStatusN)) ");
				params.put("keyPartOrganId", organId);
				params.put("secrecyStatusN", 0);
			}

		}else if( StringUtils.isNotEmpty(district.getDistrictCode()) ){
			sqlStr.append("AND district2_.district_code= :districtCode ");
			params.put("districtCode", district.getDistrictCode());
		}

		if( hisFlag != null ){
			sqlStr.append(" AND (secrecyper0_.secrecy_status is null or secrecyper0_.secrecy_status = :secrecyStatusNow) ");
			params.put("secrecyStatusNow", hisFlag);
		}
		sqlStr.append(" AND secrecyper0_.secrecy_person_level is not null ");
		sqlStr.append("GROUP BY ");
		sqlStr.append("secrecyper0_.secrecy_person_level, organ1_.ORGAN_NAME, organ1_.ORGAN_ID");
		List<Map<String,Object>> result = this.getPersistProxy().getJdbcPersistence().findList(sqlStr.toString(), params);
		for( Map<String,Object> aTmp : result ){
			Map<Integer,Integer> aResult = returnResult.get(aTmp.get("ORGAN_NAME").toString());
			if( aResult==null ){
				aResult = new HashMap<Integer,Integer>();
			}
			aResult.put(Integer.parseInt(aTmp.get("secrecy_person_level").toString()), Integer.parseInt(aTmp.get("num").toString()));
			returnResult.put(aTmp.get("ORGAN_NAME").toString(), aResult);
		}
		return returnResult;
	}

	public List<SecrecyPerson> queryList(PageSortModel<SecrecyPerson> psm, SecrecyPersonQo sq){
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("From SecrecyPerson sp where 1=1");
		// 按创建时间降序排列
		if (sq != null) {
			if( sq.getOrgan()!=null ){
				hql.append(" AND sp.organ.organId = :organId ");
				params.put("organId", sq.getOrgan().getOrganId());
			}
			if( sq.getSecrecyStatus()!=null ){
				hql.append(" AND sp.secrecyStatus = :secrecyStatus ");
				params.put("secrecyStatus", sq.getSecrecyStatus());
			}
			if( sq.getName()!=null && !"".equals(sq.getName()) ){
				hql.append(" AND sp.userInfo.name like :name ");
				params.put("name", "%"+sq.getName()+"%");
			}
			if( sq.getSecrecySatusOfDecryption()!=null ){
				hql.append(" AND (sp.secrecyStatus != :sSecrecySatusOfDecryption or sp.secrecyStatus is null ) ");
				params.put("sSecrecySatusOfDecryption", sq.getSecrecySatusOfDecryption());
			}
		}
		hql.append(" order by sp.createTime desc");

		if(psm == null){
			return (List<SecrecyPerson>) this.findList(hql.toString(), params);
		}else {
			return (List<SecrecyPerson>) this.findList(hql.toString(), params, psm);
		}
	}

	/* (non-Javadoc)
	 * @see com.cdthgk.bmp.secrecyperson.service.SecrecyPersonModuleService#countSecrecyPersonData(java.util.List, com.cdthgk.platform.organization.organ.domain.Organ, java.lang.String)
	 */
	@Override
	public Map<Integer, Long> countSecrecyPersonData(
			List<DictionaryOption> optionList, Organ currentOrgan,
			String countType) {
		Map<Integer, Long> returnMap = new HashMap<Integer, Long>();
		// 涉密人员统计
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("select count(sp) from SecrecyPerson sp " +
				" where 1=1 and (sp.secrecyStatus is null or sp.secrecyStatus = :statusNow) ");
		params.put("statusNow", BmpAction.SECRECY_STATUS_NOW);

		hql.append(" and sp.secrecyPersonLevel = :secrecyPersonLevel");
		if( "organ".equals(countType) ){
			hql.append(" and sp.organ.organId = :organId ");
			params.put("organId", currentOrgan.getOrganId());
		}else if( "layer".equals(countType) ){
			hql.append(" and sp.organ.district.layer  like :layer");
			params.put("layer", currentOrgan.getDistrict().getLayer() + "%");
		}
		hql.append(" and sp.secrecyPersonLevel = :secrecyPersonLevel");

		for( DictionaryOption dictionaryOption : optionList ){
			params.put("secrecyPersonLevel", dictionaryOption.getOptionValue());
			Long countNum = this.unique(hql.toString(), params, Long.class);
			returnMap.put(dictionaryOption.getOptionValue(), countNum);
		}
		return returnMap;
	}

	public Map<String, Object> getSecrecyPerson_Total_District(District district, int reflag) {
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
		sb.append(" SELECT IFNULL(SUM(CASE WHEN secrecy_person_level=1 THEN fcount END),0)AS a1,IFNULL(SUM(CASE WHEN secrecy_person_level=2 THEN fcount END),0)AS a2, ");
		sb.append(" IFNULL(SUM(CASE WHEN secrecy_person_level=3 THEN fcount END),0)AS a3 FROM ( ");
		sb.append(" SELECT secrecy_person_level, SUM(fcount)AS fcount FROM( ");
		sb.append(" SELECT secrecy_person_level, 1 AS fcount FROM bm_secrecy_person WHERE (secrecy_status is null or secrecy_status = "+ BmpAction.SECRECY_STATUS_NOW +" ) AND organ_id IN(SELECT organ_id FROM sys_organization ");
		sb.append(" WHERE district_code IN (SELECT district_code FROM sys_district WHERE "+sql+")) ");
		sb.append(" )AS A GROUP BY secrecy_person_level)AS B)AS C ");
		cmap = this.getPersistProxy().getJdbcPersistence().find(sb.toString(), params);

		return cmap;
	}

	/**(综合统计)
	 * 通过行政区划对象集合
	 * 综合统计  统计涉密人员的  个数.
	 * 包含了   行政区划和行政区内的  涉密人员 个数    都需要分密级统计出来
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
	public List<ZongHeTongJiStatDto> count_SecrecyPerson_District(List<District> districtList, boolean isGroup) {

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
			StringBuffer sql = ZongHeTongJiUtil.getDistrictSql_ZongHeTongJi_secrecyPerson(district.getLayer(),"bm_secrecy_person","organ_id");
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
	 *  通过单位对象   查询出一个单位下的涉密人员个数  都要按照密级统计出来
	 * @param organ  单位   这个的单位对象必须要包含organId信息
	 * @param district 行政区划对象
	 * @param needTotal 是否合计
	 * @return
	 *  查询出来的列 :
	 *  organName(本单位名字)
	 *  organId(本单位编码)
	 *  a1 a2 a3 (按照本单位统计出的个数信息)
	 */
	public List<ZongHeTongJiStatDto> count_SecrecyPerson_Organ(District district, Organ organ, boolean needTotal) {

		//返回值
		List<ZongHeTongJiStatDto> list = new ArrayList<ZongHeTongJiStatDto>();
		ZongHeTongJiStatDto totalMap = new ZongHeTongJiStatDto();//合计数

		//获取sql语句
		StringBuffer sb = ZongHeTongJiUtil.getOrganSql_ZongHeTongJi_secrecyPerson(district, organ, "bm_secrecy_person", "organ_id");
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
	 * 综合统计  查询 行政区划涉密人员统计  通过行政区划编码
	 * @param district  行政区划对象 , 每个行政区划必须包含  districtCode
	 * @param isGroup  是否合计
	 * @param organ   单位对象   ，这个对象可以为空，只是在模糊查询的时候我们会用到，单位的名字
	 *
	 * @return
	 */
	public List<ZongHeTongJiStatDto> count_SecrecyPerson_District(District district, boolean isGroup,Organ organ){

		//返回值
		List<ZongHeTongJiStatDto> list = new ArrayList<ZongHeTongJiStatDto>();

		if(district!=null && district.getDistrictCode()!=null) {
			ZongHeTongJiStatDto totalMap = new ZongHeTongJiStatDto();//合计数

			//获取sql语句
			StringBuffer sb = ZongHeTongJiUtil.getOrganSqlByDistrict_ZongHeTongJi_secrecyPerson(district, "bm_secrecy_person", "organ_id",organ);
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

	/* (non-Javadoc)
         * @see com.cdthgk.bmp.secrecyperson.service.SecrecyPersonService#findUserInfo(com.cdthgk.platform.organization.userinfo.domain.UserInfo)
         */
        @Override
        public List<UserInfo> findUserInfo(UserInfo u, String organId) {
                StringBuilder queryString = new StringBuilder("FROM UserInfo u WHERE u.name = :userName and u.organ.organId =:organId");
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("userName", u.getName());
                params.put("organId", organId);
                List<UserInfo> userInfoList = this.getPersistProxy().getOrmPersistence().findList(queryString.toString(), params);
                return userInfoList;
        }


	// ***********************setter and getter************************
	/**
	 * 返回msgList
	 * @return msgList
	 */
	public List<String> getMsgList() {
		return msgList;
	}

	/**
	 * 设置msgList
	 * @param msgList msgList
	 */
	public void setMsgList(List<String> msgList) {
		this.msgList = msgList;
	}

	/**
	 * 返回allLeavel
	 * @return allLeavel
	 */
	public int getAllLeavel() {
		return allLeavel;
	}

	/**
	 * 返回log
	 * @return log
	 */
	public static Log getLog() {
		return LOG;
	}



}