package com.cdthgk.bmp.secrecysystem.secrecynetwork.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.action.BmpAction;
import com.cdthgk.bmp.secrecycountryitem.qo.ZongHeTongJiStatDto;
import com.cdthgk.bmp.secrecycountryitem.qo.ZongHeTongJiUtil;
import com.cdthgk.bmp.secrecysystem.secrecynetwork.service.SecrecyNetworkService;
import com.cdthgk.bmp.secrecysystem.secrecynetwork.vo.SecrecyNetwork;
import com.cdthgk.common.lang.StringUtils;
import com.cdthgk.platform.base.service.GenericServiceTemplate;
import com.cdthgk.platform.dictionary.domain.DictionaryOption;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.core.OrganizationContext;
import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

public class SecrecyNetworkServiceImpl extends GenericServiceTemplate<SecrecyNetwork, String> implements
	SecrecyNetworkService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<SecrecyNetwork> getListPage(
			PageSortModel<SecrecyNetwork> psm, SecrecyNetwork secrecyNetwork, District district, boolean flag, User currentUser, String checkLower) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("FROM SecrecyNetwork s WHERE 1 = 1" +
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
		if (secrecyNetwork != null) {
			if (StringUtils.isNotEmpty(secrecyNetwork.getName())) {
				hql.append(" and s.name like :name");
				params.put("name", "%" + secrecyNetwork.getName() + "%");
			}
			if (secrecyNetwork.getNetworkType() != null && !"".equals(secrecyNetwork.getNetworkType())) {
				hql.append(" and s.networkType = :networkType");
				params.put("networkType", secrecyNetwork.getNetworkType());
			}
			if (secrecyNetwork.getSecrecyLevel() != null && !"".equals(secrecyNetwork.getSecrecyLevel())) {
				hql.append(" and s.secrecyLevel = :secrecyLevel");
				params.put("secrecyLevel", secrecyNetwork.getSecrecyLevel());
			}
			if (secrecyNetwork.getIsApproval() != null && !"".equals(secrecyNetwork.getIsApproval())) {
				hql.append(" and s.isApproval = :isApproval");
				params.put("isApproval", secrecyNetwork.getIsApproval());
			}
			if (secrecyNetwork.getIsReview() != null && !"".equals(secrecyNetwork.getIsReview())) {
				hql.append(" and s.isReview = :isReview");
				params.put("isReview", secrecyNetwork.getIsReview());
			}
		}
		hql.append(" order by s.createTime desc");
		List<SecrecyNetwork> returnList = new ArrayList<SecrecyNetwork>();
		if (psm == null) {
			returnList = this.findList(hql.toString(), params);
		}else{
			returnList = this.findList(hql.toString(), params, psm);
		}
		if( returnList!=null && returnList.size()>0 ){
			for( SecrecyNetwork sn : returnList ){
				Map<String, Object> subParams = new HashMap<String, Object>();
				StringBuilder subHql = new StringBuilder();
				subHql.append("SELECT new map(count(snt) as num ) FROM SecrecyNetworkterminal snt WHERE snt.secrecyNetwork.secrecyNetworkId = :secrecyNetworkId ");
				subParams.put("secrecyNetworkId", sn.getSecrecyNetworkId());
				subHql.append(" AND snt.dataState = :dataState");
				subParams.put("dataState", 1);
				List<Map<String,Object>> subResult = this.getPersistProxy().getOrmPersistence().findList(subHql.toString(), subParams);
				if( subResult!=null && subResult.size()>0 ){
					sn.setNetworkNum(Integer.parseInt(subResult.get(0).get("num").toString()));
				}else{
					sn.setNetworkNum(0);
				}
			}
		}


		return returnList;
	}

	/* (non-Javadoc)
	 * @see com.cdthgk.bmp.secrecysystem.secrecynetwork.service.SecrecyNetworkService#countOthersData(java.util.List, java.util.List, com.cdthgk.platform.organization.organ.domain.Organ, java.lang.String)
	 */
	@Override
	public Map<Integer, Map<Integer, Long>> countData(
			List<DictionaryOption> optionList, List<DictionaryOption> typeList,
			Organ currentOrgan, String countType) {
		Map<Integer, Map<Integer, Long>> returnMap = new HashMap<Integer, Map<Integer,Long>>();
		// 统计
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("select new map(sn.networkType as networkType, count(sn) as num) from SecrecyNetwork sn " +
				" where 1=1 and (sn.secrecyStatus is null or sn.secrecyStatus = :statusNow) ");
		params.put("statusNow", BmpAction.SECRECY_STATUS_NOW);

		if( "organ".equals(countType) ){
			hql.append(" and sn.department.organ.organId = :organId ");
			params.put("organId", currentOrgan.getOrganId());
		}else if( "layer".equals(countType) ){
			hql.append(" and sn.department.organ.district.layer like :layer");
			params.put("layer", currentOrgan.getDistrict().getLayer() + "%");
		}
		hql.append(" and sn.secrecyLevel = :secrecyLevel");

		hql.append(" group by sn.networkType ");

		for( DictionaryOption dictionaryOption : optionList ){
			params.put("secrecyLevel", dictionaryOption.getOptionValue());
			List<Map<String, Object>> aResultList = this.getPersistProxy().getOrmPersistence().findList(hql.toString(), params);
			Map<Integer,Long> tmpResult = new HashMap<Integer, Long>();
			if( aResultList!=null && aResultList.size()>0 ){
				for( Map<String,Object> tmpDbResult : aResultList ){
					tmpResult.put(Integer.parseInt(tmpDbResult.get("networkType").toString()), Long.parseLong(tmpDbResult.get("num").toString()));
				}
			}
			Map<Integer, Long> aReturnResult = new HashMap<Integer, Long>();

			for( DictionaryOption typeOption : typeList ){
				if( tmpResult!=null && tmpResult.size()>0 ){
					Long tmpValue = tmpResult.get(typeOption.getOptionValue());
					if( tmpValue!=null ){
						aReturnResult.put(typeOption.getOptionValue(), tmpValue);
					}else{
						aReturnResult.put(typeOption.getOptionValue(), new Long(0));
					}
				}else{
					aReturnResult.put(typeOption.getOptionValue(), new Long(0));
				}
			}

			returnMap.put(dictionaryOption.getOptionValue(), aReturnResult);
		}
		return returnMap;
	}

	/* (non-Javadoc)
	 * @see com.cdthgk.bmp.secrecysystem.secrecynetwork.service.SecrecyNetworkService#findDataList(ec.common.PageSortModel, com.cdthgk.platform.organization.organ.domain.Organ, java.lang.String, com.cdthgk.bmp.secrecysystem.secrecyotherdevice.vo.SecrecyOthers)
	 */
	@Override
	public List<SecrecyNetwork> findDataList(PageSortModel<SecrecyNetwork> psm,
			Organ currentOrgan, String countType, SecrecyNetwork secrecyNetwork) {
		Map<String,Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM SecrecyNetwork s where 1=1");

		if( "organ".equals(countType) ){
			hql.append(" and s.department.organ.organId = :organId ");
			params.put("organId", currentOrgan.getOrganId());
		}else if( "layer".equals(countType) ){
			hql.append(" and s.department.organ.district.layer like :layer");
			params.put("layer", currentOrgan.getDistrict().getLayer() + "%");
		}
		hql.append(" AND s.secrecyStatus = :secrecyStatusFlag");
		params.put("secrecyStatusFlag", BmpAction.SECRECY_STATUS_NOW);

		//查询条件
		if (secrecyNetwork != null) {
			if (StringUtils.isNotEmpty(secrecyNetwork.getName())) {
				hql.append(" and s.name like :name");
				params.put("name", "%" + secrecyNetwork.getName() + "%");
			}
			if (secrecyNetwork.getNetworkType() != null && !"".equals(secrecyNetwork.getNetworkType())) {
				hql.append(" and s.networkType = :networkType");
				params.put("networkType", secrecyNetwork.getNetworkType());
			}
			if (secrecyNetwork.getSecrecyLevel() != null && !"".equals(secrecyNetwork.getSecrecyLevel())) {
				hql.append(" and s.secrecyLevel = :secrecyLevel");
				params.put("secrecyLevel", secrecyNetwork.getSecrecyLevel());
			}
			if (secrecyNetwork.getIsApproval() != null && !"".equals(secrecyNetwork.getIsApproval())) {
				hql.append(" and s.isApproval = :isApproval");
				params.put("isApproval", secrecyNetwork.getIsApproval());
			}
			if (secrecyNetwork.getIsReview() != null && !"".equals(secrecyNetwork.getIsReview())) {
				hql.append(" and s.isReview = :isReview");
				params.put("isReview", secrecyNetwork.getIsReview());
			}
		}
		List<SecrecyNetwork> secrecyNetworkList = new ArrayList<SecrecyNetwork>();
		if( psm!=null ){
			secrecyNetworkList = this.findList(hql.toString(), params, psm);
		}else{
			secrecyNetworkList = this.findList(hql.toString(), params);
		}
              if( secrecyNetworkList!=null && secrecyNetworkList.size()>0 ){
                        for( SecrecyNetwork sn : secrecyNetworkList ){
                                Map<String, Object> subParams = new HashMap<String, Object>();
                                StringBuilder subHql = new StringBuilder();
                                subHql.append("SELECT new map(count(snt) as num ) FROM SecrecyNetworkterminal snt WHERE snt.secrecyNetwork.secrecyNetworkId = :secrecyNetworkId ");
                                subParams.put("secrecyNetworkId", sn.getSecrecyNetworkId());
                                subHql.append(" AND snt.dataState = :dataState");
                                subParams.put("dataState", 1);
                                List<Map<String,Object>> subResult = this.getPersistProxy().getOrmPersistence().findList(subHql.toString(), subParams);
                                if( subResult!=null && subResult.size()>0 ){
                                        sn.setNetworkNum(Integer.parseInt(subResult.get(0).get("num").toString()));
                                }else{
                                        sn.setNetworkNum(0);
                                }
                        }
                }

		return secrecyNetworkList;
	}

	/**
	 * 综合统计 总数
	 */
	public Map<String, Object> getSecrecyNetwork_Total_District(District district, int reflag) {
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
		sb.append(" SELECT secrecy_level, 1 AS fcount FROM bm_secrecy_network WHERE (secrecy_status is null or secrecy_status = "+ BmpAction.SECRECY_STATUS_NOW +" ) AND create_organ IN(SELECT organ_id FROM sys_organization  ");
		sb.append(" WHERE district_code IN (SELECT district_code FROM sys_district WHERE "+sql+")) ");
		sb.append(" )AS A GROUP BY secrecy_level)AS B)AS C ");
		cmap = this.getPersistProxy().getJdbcPersistence().find(sb.toString(), params);

		return cmap;
	}

	public List<ZongHeTongJiStatDto> count_SecrecyNetwork_District(List<District> districtList, boolean isGroup) {

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
			StringBuffer sql = ZongHeTongJiUtil.getDistrictSql_ZongHeTongJi_secrecySystem(district.getLayer(),"bm_secrecy_network","create_organ");
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
	 *
	 * <p>
	 * 方法名称  count_SecrecyComputer_Organ 综合统计
	 * </p>
	 * <p>
	 * 宋亚非 2013-9-4 下午4:56:13
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
	 *@param organ
	 *@param needTotal
	 *@return
	 */
	public List<ZongHeTongJiStatDto> count_SecrecyNetwork_Organ(District district, Organ organ, boolean needTotal) {

		//返回值
		List<ZongHeTongJiStatDto> list = new ArrayList<ZongHeTongJiStatDto>();
		ZongHeTongJiStatDto totalMap = new ZongHeTongJiStatDto();//合计数

		//获取sql语句
		StringBuffer sb = ZongHeTongJiUtil.getOrganSql_ZongHeTongJi_secrecySystem(district, organ, "bm_secrecy_network", "create_organ");
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
	public List<ZongHeTongJiStatDto> count_SecrecyNetwork_District(District district, boolean isGroup,Organ organ){

		//返回值
		List<ZongHeTongJiStatDto> list = new ArrayList<ZongHeTongJiStatDto>();

		if(district!=null && district.getDistrictCode()!=null) {
			ZongHeTongJiStatDto totalMap = new ZongHeTongJiStatDto();//合计数

			//获取sql语句
			StringBuffer sb = ZongHeTongJiUtil.getOrganSqlByDistrict_ZongHeTongJi_secrecySystem(district, "bm_secrecy_network", "create_organ",organ);
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
         * @see com.cdthgk.bmp.secrecysystem.secrecynetwork.service.SecrecyNetworkService#addDepartment(java.lang.String, com.cdthgk.platform.permission.user.domain.User)
         */
        @Override
        public Department addDepartment(String departmentName, User currentUser) {
                // TODO Auto-generated method stub
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

}
