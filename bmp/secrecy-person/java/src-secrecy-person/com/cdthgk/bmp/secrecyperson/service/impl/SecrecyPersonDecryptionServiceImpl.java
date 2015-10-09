/**
 *
 */
package com.cdthgk.bmp.secrecyperson.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.bmp.secrecyperson.service.SecrecyPersonDecryptionService;
import com.cdthgk.bmp.secrecyperson.vo.SecrecyPersonDecryption;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;

/**
 * <p>
 * 类的说明放这里 2013-7-17 下午3:20:30
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 *
 * @author 宋亚非
 */
public class SecrecyPersonDecryptionServiceImpl extends
		BmpServiceImpl<SecrecyPersonDecryption, String> implements
		SecrecyPersonDecryptionService {

	/* (non-Javadoc)
	 * @see com.cdthgk.bmp.secrecyperson.service.SecrecyPersonDecryptionService#getPageAllList(ec.common.PageSortModel, com.cdthgk.bmp.secrecyperson.vo.SecrecyPersonDecryption, com.cdthgk.platform.organization.organ.domain.Organ, java.lang.Integer[])
	 */
	@Override
	public List<SecrecyPersonDecryption> getPageAllList(
			PageSortModel<SecrecyPersonDecryption> psm,
			SecrecyPersonDecryption secrecyPersonDecryption, Organ organ, District district, String checkLower,
			Map<String, Date> dateParams, Integer historyFlag) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("from SecrecyPersonDecryption s where 1=1 ");

		if( organ!=null ){
			// 当前单位涉密人员
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
		if (secrecyPersonDecryption != null) {
			// 姓 名
			if (secrecyPersonDecryption.getSecrecyPersonId()!=null && secrecyPersonDecryption.getSecrecyPersonId().getUserInfo()!=null
					&& secrecyPersonDecryption.getSecrecyPersonId().getUserInfo().getName() != null
					&& !"".equals(secrecyPersonDecryption.getSecrecyPersonId().getUserInfo().getName())) {
				hql.append(" and s.secrecyPersonId.userInfo.name like :name");
				params.put("name", "%" + secrecyPersonDecryption.getSecrecyPersonId().getUserInfo().getName() + "%");
			}
			//部门id过滤  尤其是保密要害部门的id 梁文杰20130-07-20修改
			if(secrecyPersonDecryption.getSecrecyPersonId()!=null && secrecyPersonDecryption.getSecrecyPersonId().getDepartment()!=null &&
					secrecyPersonDecryption.getSecrecyPersonId().getDepartment().getDepartmentId()!=null) {
				hql.append(" and s.secrecyPersonId.department.departmentId = :departId");
				params.put("departId", secrecyPersonDecryption.getSecrecyPersonId().getDepartment().getDepartmentId());
			}

			// 脱密类型
			if (secrecyPersonDecryption.getDecryptionType() != null ) {
				hql.append(" and s.decryptionType = :decryptionType");
				params.put("decryptionType", secrecyPersonDecryption.getDecryptionType());
			}
			//脱密时间起  范围
			if( dateParams.get("decryptionStartStart")!=null ){
				hql.append(" and s.decryptionStart >= :decryptionStartStart");
				params.put("decryptionStartStart", dateParams.get("decryptionStartStart"));
			}
			if( dateParams.get("decryptionStartEnd")!=null ){
				hql.append(" and s.decryptionStart <= :decryptionStartEnd");
				params.put("decryptionStartEnd", dateParams.get("decryptionStartEnd"));
			}
			//脱密时间止  范围
			if( dateParams.get("decryptionEndStart")!=null ){
				hql.append(" and s.decryptionEnd >= :decryptionEndStart");
				params.put("decryptionEndStart", dateParams.get("decryptionEndStart"));
			}
			if( dateParams.get("decryptionEndEnd")!=null ){
				hql.append(" and s.decryptionEnd <= :decryptionEndEnd");
				params.put("decryptionEndEnd", dateParams.get("decryptionEndEnd"));
			}
		}
		if( historyFlag!=null ){
			hql.append(" and s.secrecyPersonId.secrecyStatus = :historyFlag");
			params.put("historyFlag", historyFlag);
		}

		// 按创建时间降序排列
		hql.append(" order by s.createDate desc");

//		return (List<SecrecyPersonDecryption>) this.getList(hql.toString(), psm, params);
		return (List<SecrecyPersonDecryption>) this.findList(hql.toString(), params, psm);
	}

	/* (non-Javadoc)
	 * @see com.cdthgk.bmp.secrecyperson.service.SecrecyPersonDecryptionService#findPersonList(java.lang.String)
	 */
	@Override
	public List<SecrecyPersonDecryption> findPersonList(String secrecyPersonId) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("from SecrecyPersonDecryption s where 1=1 ");

		// 拼接查询条件
		if (secrecyPersonId != null && !"".equals(secrecyPersonId)) {
			hql.append(" and s.secrecyPersonId.secrecyPersonId = :secrecyPersonId ");
			params.put("secrecyPersonId", secrecyPersonId);
		}
		// 按创建时间降序排列
		hql.append(" order by s.createDate desc");

		return (List<SecrecyPersonDecryption>) this.findList(hql.toString(), params);
	}

}
