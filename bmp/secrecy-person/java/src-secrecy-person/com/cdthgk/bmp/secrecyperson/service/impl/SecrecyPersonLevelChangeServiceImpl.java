/**
 *
 */
package com.cdthgk.bmp.secrecyperson.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.bmp.secrecyperson.service.SecrecyPersonLevelChangeService;
import com.cdthgk.bmp.secrecyperson.vo.SecrecyPersonLevelChange;
import com.cdthgk.common.lang.StringUtils;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;

/**
 * <p>
 * 类的说明放这里 2013-7-17 下午3:18:13
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 *
 * @author 宋亚非
 */
public class SecrecyPersonLevelChangeServiceImpl extends
		BmpServiceImpl<SecrecyPersonLevelChange, String> implements
		SecrecyPersonLevelChangeService {

	/* (non-Javadoc)
	 * @see com.cdthgk.bmp.secrecyperson.service.SecrecyPersonLevelChangeService#getAllPageList(ec.common.PageSortModel, com.cdthgk.bmp.secrecyperson.vo.SecrecyPersonLevelChange, com.cdthgk.platform.organization.organ.domain.Organ)
	 */
	@Override
	public List<SecrecyPersonLevelChange> getAllPageList(
			PageSortModel<SecrecyPersonLevelChange> psm,
			SecrecyPersonLevelChange secrecyPersonLevelChange, Organ organ, District district, String checkLower, Date chageDateStart, Date chageDateEnd) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("from SecrecyPersonLevelChange s where 1=1 ");

		// 当前单位涉密人员
		if( organ!=null ){
			hql.append(" and s.secrecyPersonId.organ.organId = :organId");
			params.put("organId", organ.getOrganId());
		}

		if( district!=null ){
			if( checkLower!=null && "1".equals(checkLower) ){
				hql.append(" and s.secrecyPersonId.organ.district.layer like :layer");
				params.put("layer", district.getLayer()+"%");
			}else{
				hql.append(" and s.secrecyPersonId.organ.district.layer = :layer");
				params.put("layer", district.getLayer());
			}
		}

		// 拼接查询条件
		if (secrecyPersonLevelChange != null) {
			if(secrecyPersonLevelChange.getSecrecyPersonId()!=null && secrecyPersonLevelChange.getSecrecyPersonId().getUserInfo()!=null
					&& StringUtils.isNotEmpty(secrecyPersonLevelChange.getSecrecyPersonId().getUserInfo().getName()) ){
				hql.append(" and s.secrecyPersonId.userInfo.name like :secrecyPersonName ");
				params.put("secrecyPersonName", "%"+secrecyPersonLevelChange.getSecrecyPersonId().getUserInfo().getName()+"%");
			}
			if(secrecyPersonLevelChange.getReviewPerson()!=null && StringUtils.isNotEmpty(secrecyPersonLevelChange.getReviewPerson().getName()) ){
				hql.append(" and s.reviewPerson.name like :reviewPersonName ");
				params.put("reviewPersonName", "%"+secrecyPersonLevelChange.getReviewPerson().getName()+"%");
			}
			//部门id过滤  尤其是保密要害部门的id  梁文杰20130-07-20修改
			if(secrecyPersonLevelChange.getSecrecyPersonId()!=null && secrecyPersonLevelChange.getSecrecyPersonId().getDepartment()!=null &&
					secrecyPersonLevelChange.getSecrecyPersonId().getDepartment().getDepartmentId()!=null) {
				hql.append(" and s.secrecyPersonId.department.departmentId = :departId");
				params.put("departId", secrecyPersonLevelChange.getSecrecyPersonId().getDepartment().getDepartmentId());
			}
		}
		if( chageDateStart!=null ){
			hql.append(" and s.changeDate >= :chageDateStart ");
			params.put("chageDateStart", chageDateStart);
		}
		if( chageDateEnd!=null ){
			hql.append(" and s.changeDate <= :chageDateEnd ");
			params.put("chageDateEnd", chageDateEnd);
		}

		// 按创建时间降序排列
		hql.append(" order by s.createDate desc");

//		return (List<SecrecyPersonLevelChange>) this.getList(hql.toString(), psm, params);
		return (List<SecrecyPersonLevelChange>) this.findList(hql.toString(), params, psm);

	}

	/* (non-Javadoc)
	 * @see com.cdthgk.bmp.secrecyperson.service.SecrecyPersonLevelChangeService#findPersonList(java.lang.String)
	 */
	@Override
	public List<SecrecyPersonLevelChange> findPersonList(String secrecyPersonId, String secrecyPersonLevelChangeId) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("from SecrecyPersonLevelChange s where 1=1 ");

		// 拼接查询条件
		if (secrecyPersonId != null && !"".equals(secrecyPersonId)) {
			hql.append(" and s.secrecyPersonId.secrecyPersonId = :secrecyPersonId");
			params.put("secrecyPersonId", secrecyPersonId);
		}
		if(secrecyPersonLevelChangeId!=null && !"".equals(secrecyPersonLevelChangeId)){
			hql.append(" and s.secrecyPersonLevelChangeId = :secrecyPersonLevelChangeId");
			params.put("secrecyPersonLevelChangeId", secrecyPersonLevelChangeId);
		}
		// 按创建时间降序排列
		hql.append(" order by s.createDate desc");
		return (List<SecrecyPersonLevelChange>) this.findList(hql.toString(), params);
	}

}
