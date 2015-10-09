package com.cdthgk.bmp.secrecysystem.secrecycomputer.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.xhtmlrenderer.protocols.data.DataURLConnection;

import com.cdthgk.bmp.core.action.BmpAction;
import com.cdthgk.bmp.keyPart.vo.Part;
import com.cdthgk.bmp.keysection.vo.KeySection;
import com.cdthgk.bmp.secrecycountryitem.qo.ZongHeTongJiStatDto;
import com.cdthgk.bmp.secrecycountryitem.qo.ZongHeTongJiUtil;
import com.cdthgk.bmp.secrecysystem.secrecycomputer.service.SecrecyComputerService;
import com.cdthgk.bmp.secrecysystem.secrecycomputer.vo.SecrecyComputer;
import com.cdthgk.common.bean.BeanUtils;
import com.cdthgk.common.bean.rule.CopyRuleEnum;
import com.cdthgk.common.lang.LangUtils;
import com.cdthgk.common.lang.StringUtils;
import com.cdthgk.platform.base.service.GenericBasicService;
import com.cdthgk.platform.base.service.GenericServiceTemplate;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.core.OrganizationContext;
import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

public class SecrecyComputerServiceImpl extends GenericServiceTemplate<SecrecyComputer, String> implements
	SecrecyComputerService {


	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<SecrecyComputer> getListPage(
			PageSortModel<SecrecyComputer> psm, SecrecyComputer secrecyComputer, District district,
			boolean flag, User currentUser, String checkLower) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("FROM SecrecyComputer s WHERE 1 = 1" +
				" and ( s.secrecyStatus = " + BmpAction.SECRECY_STATUS_NOW + " or  s.secrecyStatus = " + BmpAction.SECRECY_STATUS_CHANGE + ")");
		// false ： 查看本单位； true ：查看行政区
		if (flag) {
			// checkLower 是否查看下级 1：查看下级； 0：不查看
			if ("1".equals(checkLower)) {
				// 查看整个行政区
				hql.append(" and s.createOrgan.district.layer like :layer");
				params.put("layer", district.getLayer() + "%");
			} else {
				// 查看直辖机关单位
				hql.append(" and s.createOrgan.district.districtCode = :districtCode");
				params.put("districtCode", district.getDistrictCode());
			}
		} else {
			hql.append(" and s.createOrgan.organId = :organId");
			params.put("organId", currentUser.getOrgan().getOrganId());
		}

		//限定状态
		//hql.append(" and s.secrecyStatus = :secrecyStatus");
		//params.put("secrecyStatus", BmpAction.SECRECY_STATUS_NOW);

		if (secrecyComputer != null) {
			if (secrecyComputer.getDutyPerson() != null && StringUtils.isNotEmpty(secrecyComputer.getDutyPerson().getName())) {
				hql.append(" and s.dutyPerson.name like :name");
				params.put("name", "%" + secrecyComputer.getDutyPerson().getName() + "%");
			}
			if (secrecyComputer.getComputerType() != null && !"".equals(secrecyComputer.getComputerType())) {
				hql.append(" and s.computerType = :computerType");
				params.put("computerType", secrecyComputer.getComputerType());
			}
			if (secrecyComputer.getSecrecyLevel() != null && !"".equals(secrecyComputer.getSecrecyLevel())) {
				hql.append(" and s.secrecyLevel = :secrecyLevel");
				params.put("secrecyLevel", secrecyComputer.getSecrecyLevel());
			}
			if (secrecyComputer.getIsbelongKeydepartment() != null && !"".equals(secrecyComputer.getIsbelongKeydepartment())) {
				hql.append(" and s.isbelongKeydepartment = :isbelongKeydepartment");
				params.put("isbelongKeydepartment", secrecyComputer.getIsbelongKeydepartment());
			}
			if (StringUtils.isNotEmpty(secrecyComputer.getComputerNo())) {
				hql.append(" and s.computerNo like :computerNo");
				params.put("computerNo", "%" + secrecyComputer.getComputerNo() + "%");
			}
		}
		hql.append(" order by s.createTime desc");
		if (psm == null) {
			return this.findList(hql.toString(), params);
		}
		return this.findList(hql.toString(), params, psm);
	}

	/* (non-Javadoc)
	 * @see com.cdthgk.bmp.secrecysystem.secrecycomputer.service.SecrecyComputerService#checkDiskSeq(java.lang.String)
	 */
	@Override
	public Boolean checkDiskSeq(String diskSeq, String checkType, String secrecyComputerId) {
		// TODO Auto-generated method stub
		Boolean returnValue = false;
		List<Map<String, Object>> resultMap = new ArrayList<Map<String,Object>>();
		StringBuilder hql = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();
		hql.append("SELECT new map(sc.secrecycomputerId as id) FROM SecrecyComputer sc WHERE sc.diskSeq = :diskSeq ");
		params.put("diskSeq", diskSeq);
		resultMap = this.getPersistProxy().getOrmPersistence().findList(hql.toString(), params);
		if( "add".equals(checkType) && resultMap!=null && resultMap.size()<=0 ){
			returnValue = true;
		}
		if( "edit".equals(checkType) && resultMap!=null && resultMap.size()<=0 ){
			returnValue = true;
		}
		if( "edit".equals(checkType) && resultMap!=null && resultMap.size()>1 && secrecyComputerId!=null && !"".equals(secrecyComputerId) ){
			returnValue = false;
		}
		if( "edit".equals(checkType) && resultMap!=null && resultMap.size()==1 && secrecyComputerId!=null && !"".equals(secrecyComputerId) ){
			if( resultMap.get(0).get("id").equals(secrecyComputerId) ){
				returnValue = true;
			}else{
				returnValue = false;
			}
		}
		return returnValue;
	}

	/* (non-Javadoc)
	 * @see com.cdthgk.bmp.secrecysystem.secrecycomputer.service.SecrecyComputerService#checkComputerNo(com.cdthgk.platform.organization.organ.domain.Organ, java.lang.String)
	 */
	@Override
	public Boolean checkComputerNo(Organ organ, String computerNo,  String checkType, String secrecyComputerId) {
		// TODO Auto-generated method stub
		Boolean returnValue = false;
		List<Map<String, Object>> resultMap = new ArrayList<Map<String,Object>>();
		StringBuilder hql = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();
		hql.append("SELECT new map(sc.secrecycomputerId as id) FROM SecrecyComputer sc WHERE sc.computerNo = :computerNo ");
		params.put("computerNo", computerNo);
		hql.append("AND sc.createOrgan.organId = :organId ");
		params.put("organId", organ.getOrganId());
		resultMap = this.getPersistProxy().getOrmPersistence().findList(hql.toString(), params);
		if( "add".equals(checkType) && resultMap!=null && resultMap.size()<=0 ){
			returnValue = true;
		}
		if( "edit".equals(checkType) && resultMap!=null && resultMap.size()<=0 ){
			returnValue = true;
		}
		if( "edit".equals(checkType) && resultMap!=null && resultMap.size()>1 && secrecyComputerId!=null && !"".equals(secrecyComputerId) ){
			returnValue = false;
		}
		if( "edit".equals(checkType) && resultMap!=null && resultMap.size()==1 && secrecyComputerId!=null && !"".equals(secrecyComputerId) ){
			if( resultMap.get(0).get("id").equals(secrecyComputerId) ){
				returnValue = true;
			}else{
				returnValue = false;
			}
		}
		return returnValue;
	}

	/* (non-Javadoc)
	 * @see com.cdthgk.bmp.secrecysystem.secrecycomputer.service.SecrecyComputerService#addDepartment(java.lang.String, com.cdthgk.platform.permission.user.domain.User)
	 */
	@Override
	public Department addDepartment(String departmentName, User currentUser) {
		Department dept = new Department();
		//当前单位不存在该部门，新增系统部门
		Department existDepartment  = OrganizationContext.getInstance().getDepartmentService()
				.getByName(departmentName, currentUser.getOrgan());
		if(existDepartment == null) {
			dept.setDepartmentName(departmentName);
			dept.setOrgan(currentUser.getOrgan());
			dept.setParent(currentUser.getOrgan().getDepartment());
			dept.setCreatePerson(currentUser);
			dept.setModifyPerson(currentUser);
			dept.save();
		} else {
			dept = existDepartment;
		}
		return dept;
	}

	/* (non-Javadoc)
	 * @see com.cdthgk.bmp.secrecysystem.secrecycomputer.service.SecrecyComputerService#addUserinfo(java.lang.String, com.cdthgk.platform.permission.user.domain.User)
	 */
	@Override
	public UserInfo addUserinfo(String userName, User currentUser) {
		UserInfo userInfo = new UserInfo();
		//当前单位不存在该人员，新增系统人员
		List<UserInfo> userInfoList = (List<UserInfo>) OrganizationContext.getInstance().getMemberService()
				.getByName(userName);
		if(userInfoList != null && userInfoList.size() > 0) {
			userInfo = userInfoList.get(0);
		} else {
			userInfo.setName(userName);
			userInfo.setOrgan(currentUser.getOrgan());
			userInfo.setDepartment(currentUser.getOrgan().getDepartment());
			userInfo.setCreatePerson(currentUser);
			userInfo.setModifyPerson(currentUser);
			userInfo.save();
		}
		return userInfo;
	}

	/* (non-Javadoc)
	 * @see com.cdthgk.bmp.secrecysystem.secrecycomputer.service.SecrecyComputerService#dealSecrecyComputer(com.cdthgk.bmp.secrecysystem.secrecycomputer.vo.SecrecyComputer, com.cdthgk.platform.permission.user.domain.User)
	 */
	@Override
	public SecrecyComputer addSecrecyComputer(SecrecyComputer secrecyComputer,
			User user) {
		SecrecyComputer returnSecrecyComputer = null;
		if( secrecyComputer!=null){
			SecrecyComputer secrecyComputerDb = null;
			if( secrecyComputer.getSecrecycomputerId()!=null && !"".equals(secrecyComputer.getSecrecycomputerId()) ){
				secrecyComputerDb = this.get(secrecyComputer.getSecrecycomputerId());
			}else{
				secrecyComputer.setSecrecycomputerId(null);
			}

			//处理部门
			Department department = null;

			if(secrecyComputer.getKeySection()!=null && "".equals(secrecyComputer.getKeySection().getKeySectionId()) ){
				secrecyComputer.setKeySection(null);
			}

			if( secrecyComputer.getKeyPart()!=null && "".equals(secrecyComputer.getKeyPart().getPartId()) ){
				secrecyComputer.setKeyPart(null);
			}

			// 设置部门ID
			if(secrecyComputer.getIsbelongKeydepartment() == 1) {
				if (secrecyComputer.getKeyPart()!=null && StringUtils.isNotEmpty(secrecyComputer.getKeyPart().getPartId())) {
					department = this.get(secrecyComputer.getKeyPart().getPartId(), Part.class).getDepartment();
				}
				if (secrecyComputer.getKeySection()!=null && StringUtils.isNotEmpty(secrecyComputer.getKeySection().getKeySectionId())) {
					department = this.get(secrecyComputer.getKeySection().getKeySectionId(), KeySection.class).getDepartment();
				}
			} else {
				//处理要害部门，当要害部门不存在时，新增
				if( secrecyComputer.getDepartment().getDepartmentId()!=null && !"".equals(secrecyComputer.getDepartment().getDepartmentId()) ){
					department = this.get(secrecyComputer.getDepartment().getDepartmentId(), Department.class);
				}else{
					//新增要害部门
					department = this.addDepartment(secrecyComputer.getDepartment().getDepartmentName(), user);
				}
			}
			//处理责任人
			UserInfo dutyPerson = null;
			if( secrecyComputer.getDutyPerson()!=null ){
				if(secrecyComputer.getDutyPerson().getUserInfoId()!=null && !"".equals(secrecyComputer.getDutyPerson().getUserInfoId())){
					dutyPerson = this.get(secrecyComputer.getDutyPerson().getUserInfoId(), UserInfo.class);
				}else{
					//新增人员
					dutyPerson = this.addUserinfo(secrecyComputer.getDutyPerson().getName(), user);
				}
			}

			secrecyComputer.setDutyPerson(dutyPerson);
			secrecyComputer.setDepartment(department);
			secrecyComputer.setSecrecyStatus(BmpAction.SECRECY_STATUS_NOW);

			if( secrecyComputerDb!=null ){
				BeanUtils.copyProperties(secrecyComputerDb, secrecyComputer, CopyRuleEnum.ignoreCaseEmpty);
				secrecyComputerDb.setModifyOrgan(user.getOrgan());
				secrecyComputerDb.setModifyPerson(user);
				secrecyComputerDb.setModifyTime(new Date(System.currentTimeMillis()));
				secrecyComputerDb.setIsNetTerminal(1);
				this.update(secrecyComputerDb);
				returnSecrecyComputer = secrecyComputerDb;
			}else{
				secrecyComputer.setCreateOrgan(user.getOrgan());
				secrecyComputer.setCreatePerson(user);
				secrecyComputer.setCreateTime(new Date(System.currentTimeMillis()));
				secrecyComputer.setIsNetTerminal(1);
				this.save(secrecyComputer);
				returnSecrecyComputer = secrecyComputer;
			}
		}
		return  returnSecrecyComputer;
	}

	/**
	 *
	 * <p>
	 * 方法名称  getSecrecyCountryItim_Total_District 涉密计算机综合统计  总数
	 * </p>
	 * <p>
	 * 宋亚非 2013-9-4 下午1:59:40
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@param district
	 *@param reflag
	 *@return
	 */
	public Map<String, Object> getSecrecyComputer_Total_District(District district, int reflag) {
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
		sb.append(" SELECT secrecy_level, 1 AS fcount FROM bm_secrecy_computer WHERE (secrecy_status is null or secrecy_status = "+ BmpAction.SECRECY_STATUS_NOW +" ) AND create_organ IN(SELECT organ_id FROM sys_organization  ");
		sb.append(" WHERE district_code IN (SELECT district_code FROM sys_district WHERE "+sql+")) ");
		sb.append(" )AS A GROUP BY secrecy_level)AS B)AS C ");
		cmap = this.getPersistProxy().getJdbcPersistence().find(sb.toString(), params);

		return cmap;
	}

	/**
	 * 涉密计算机综合统计
	 */
	public List<ZongHeTongJiStatDto> count_SecrecyComputer_District(List<District> districtList, boolean isGroup) {

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
			StringBuffer sql = ZongHeTongJiUtil.getDistrictSql_ZongHeTongJi_secrecySystem(district.getLayer(),"bm_secrecy_computer","create_organ");
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
	 * 涉密计算机综合统计
	 */
	public List<ZongHeTongJiStatDto> count_SecrecyComputer_Organ(District district, Organ organ, boolean needTotal) {

		//返回值
		List<ZongHeTongJiStatDto> list = new ArrayList<ZongHeTongJiStatDto>();
		ZongHeTongJiStatDto totalMap = new ZongHeTongJiStatDto();//合计数

		//获取sql语句
		StringBuffer sb = ZongHeTongJiUtil.getOrganSql_ZongHeTongJi_secrecySystem(district, organ, "bm_secrecy_computer", "create_organ");
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
	public List<ZongHeTongJiStatDto> count_SecrecyComputer_District(District district, boolean isGroup,Organ organ){

		//返回值
		List<ZongHeTongJiStatDto> list = new ArrayList<ZongHeTongJiStatDto>();

		if(district!=null && district.getDistrictCode()!=null) {
			ZongHeTongJiStatDto totalMap = new ZongHeTongJiStatDto();//合计数

			//获取sql语句
			StringBuffer sb = ZongHeTongJiUtil.getOrganSqlByDistrict_ZongHeTongJi_secrecySystem(district, "bm_secrecy_computer", "create_organ",organ);
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

}
