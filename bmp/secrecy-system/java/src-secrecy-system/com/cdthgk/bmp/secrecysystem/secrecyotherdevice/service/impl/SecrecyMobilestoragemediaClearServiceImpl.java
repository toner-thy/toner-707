/**
 *
 */
package com.cdthgk.bmp.secrecysystem.secrecyotherdevice.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.bmp.secrecysystem.secrecyotherdevice.service.SecrecyMobilestoragemediaClearService;
import com.cdthgk.bmp.secrecysystem.secrecyotherdevice.vo.SecrecyMobilestoragemediaClear;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;

/**
 * <p>
 * 类的说明放这里 2013-8-5 下午2:06:17
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 *
 * @author 宋亚非
 */
public class SecrecyMobilestoragemediaClearServiceImpl extends
		BmpServiceImpl<SecrecyMobilestoragemediaClear, String> implements
		SecrecyMobilestoragemediaClearService {

	/* (non-Javadoc)
	 * @see com.cdthgk.bmp.secrecysystem.secrecyotherdevice.service.SecrecyMobilestoragemediaClearService#findPageAllList(ec.common.PageSortModel, com.cdthgk.bmp.secrecysystem.secrecyotherdevice.vo.SecrecyMobilestoragemediaChange, com.cdthgk.platform.organization.organ.domain.Organ, java.lang.Integer)
	 */
	@Override
	public List<SecrecyMobilestoragemediaClear> findPageAllList(
			PageSortModel<SecrecyMobilestoragemediaClear> psm,
			SecrecyMobilestoragemediaClear secrecyMobilestoragemediaClear,
			Organ organ, District district, String checkLower) {
		Map<String,Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM SecrecyMobilestoragemediaClear smc where 1=1");
		if( organ!=null ){
			hql.append(" AND smc.secrecyMobilestoragemedia.createOrgan.organId = :organId ");
			params.put("organId", organ.getOrganId());
		}

		if( district!=null ){
			if( checkLower!=null && "1".equals(checkLower) ){
				hql.append(" AND smc.secrecyMobilestoragemedia.createOrgan.district.layer like :layer ");
				params.put("layer", district.getLayer()+"%");
			}else{
				hql.append(" AND smc.secrecyMobilestoragemedia.createOrgan.district.layer = :layer ");
				params.put("layer", district.getLayer());
			}
		}

		//查询条件
		if(secrecyMobilestoragemediaClear!=null ){
			if( secrecyMobilestoragemediaClear!=null && secrecyMobilestoragemediaClear.getSecrecyMobilestoragemedia()!=null ){
				if( secrecyMobilestoragemediaClear.getSecrecyMobilestoragemedia().getMediaType()!=null ){
					hql.append(" AND smc.secrecyMobilestoragemedia.mediaType = :mediaType ");
					params.put("mediaType", secrecyMobilestoragemediaClear.getSecrecyMobilestoragemedia().getMediaType());
				}
				if( secrecyMobilestoragemediaClear.getSecrecyMobilestoragemedia().getSecrecyLevel()!=null ){
					hql.append(" AND smc.secrecyMobilestoragemedia.secrecyLevel = :secrecyLevel ");
					params.put("secrecyLevel", secrecyMobilestoragemediaClear.getSecrecyMobilestoragemedia().getSecrecyLevel());
				}
			}

			if( secrecyMobilestoragemediaClear.getClearType()!=null ){
				hql.append(" AND smc.clearType = :clearType ");
				params.put("clearType", secrecyMobilestoragemediaClear.getClearType());
			}

			if( secrecyMobilestoragemediaClear.getClearTime()!=null ){
				hql.append(" AND smc.clearTime = :clearTime ");
				params.put("clearTime", secrecyMobilestoragemediaClear.getClearTime());
			}
		}
		List<SecrecyMobilestoragemediaClear> secrecyMobilestoragemediaClearList = this.findList(hql.toString(), params, psm);
		return secrecyMobilestoragemediaClearList;
	}

	/* (non-Javadoc)
	 * @see com.cdthgk.bmp.secrecysystem.secrecyotherdevice.service.SecrecyMobilestoragemediaClearService#findDataList(java.lang.String)
	 */
	@Override
	public List<SecrecyMobilestoragemediaClear> findDataList(
			String secrecyMobilestoragemediaId) {
		Map<String,Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM SecrecyMobilestoragemediaClear smc where 1=1");

		//查询条件

			if( secrecyMobilestoragemediaId!=null && !"".equals(secrecyMobilestoragemediaId) ){
				hql.append(" AND smc.secrecyMobilestoragemedia.secrecymobilestoragemediaId = :secrecymobilestoragemediaId ");
				params.put("secrecymobilestoragemediaId", secrecyMobilestoragemediaId);
			}

		List<SecrecyMobilestoragemediaClear> secrecyMobilestoragemediaClearList = this.findList(hql.toString(), params);
		return secrecyMobilestoragemediaClearList;

	}

}
