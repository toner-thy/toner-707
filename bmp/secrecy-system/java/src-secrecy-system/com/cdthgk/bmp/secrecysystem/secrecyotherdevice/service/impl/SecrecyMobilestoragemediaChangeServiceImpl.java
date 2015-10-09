/**
 *
 */
package com.cdthgk.bmp.secrecysystem.secrecyotherdevice.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.bmp.secrecysystem.secrecyotherdevice.service.SecrecyMobilestoragemediaChangeService;
import com.cdthgk.bmp.secrecysystem.secrecyotherdevice.vo.SecrecyMobilestoragemediaChange;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;

/**
 * <p>
 * 类的说明放这里 2013-8-5 下午2:03:32
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 *
 * @author 宋亚非
 */
public class SecrecyMobilestoragemediaChangeServiceImpl extends
		BmpServiceImpl<SecrecyMobilestoragemediaChange, String> implements
		SecrecyMobilestoragemediaChangeService {

	/* (non-Javadoc)
	 * @see com.cdthgk.bmp.secrecysystem.secrecyotherdevice.service.SecrecyMobilestoragemediaChangeService#findPageAllList(ec.common.PageSortModel, com.cdthgk.bmp.secrecysystem.secrecyotherdevice.vo.SecrecyMobilestoragemediaChange, com.cdthgk.platform.organization.organ.domain.Organ, java.lang.Integer)
	 */
	@Override
	public List<SecrecyMobilestoragemediaChange> findPageAllList(
			PageSortModel<SecrecyMobilestoragemediaChange> psm,
			SecrecyMobilestoragemediaChange secrecyMobilestoragemediaChange,
			Organ organ, District district, String checkLower) {
		Map<String,Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM SecrecyMobilestoragemediaChange smc where 1=1");
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
		if(secrecyMobilestoragemediaChange!=null ){
			if(secrecyMobilestoragemediaChange.getSecrecyMobilestoragemedia()!=null){
				if( secrecyMobilestoragemediaChange.getSecrecyMobilestoragemedia().getMediaType()!=null ){
					hql.append(" AND smc.secrecyMobilestoragemedia.mediaType = :mediaType ");
					params.put("mediaType", secrecyMobilestoragemediaChange.getSecrecyMobilestoragemedia().getMediaType());
				}
			}
			if( secrecyMobilestoragemediaChange.getChangeTimeState()!=null ){
				hql.append(" AND smc.changeTimeState = :changeTimeState ");
				params.put("changeTimeState", secrecyMobilestoragemediaChange.getChangeTimeState());
			}
			if( secrecyMobilestoragemediaChange.getBeforeLevel()!=null ){
				hql.append(" AND smc.beforeLevel = :beforeLevel ");
				params.put("beforeLevel", secrecyMobilestoragemediaChange.getBeforeLevel());
			}
			if( secrecyMobilestoragemediaChange.getAfterLevel()!=null ){
				hql.append(" AND smc.afterLevel = :afterLevel ");
				params.put("afterLevel", secrecyMobilestoragemediaChange.getAfterLevel());
			}
		}
		List<SecrecyMobilestoragemediaChange> secrecyMobilestoragemediaChangeList = this.findList(hql.toString(), params, psm);
		return secrecyMobilestoragemediaChangeList;
	}

	/* (non-Javadoc)
	 * @see com.cdthgk.bmp.secrecysystem.secrecyotherdevice.service.SecrecyMobilestoragemediaChangeService#findDataList(java.lang.String, java.lang.String)
	 */
	@Override
	public List<SecrecyMobilestoragemediaChange> findDataList(
			String secrecyMobilestoragemediaId,
			String secrecyMobilestoragemediaChangeId) {
		Map<String,Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM SecrecyMobilestoragemediaChange smc where 1=1");

		//查询条件
			if(secrecyMobilestoragemediaId!=null && !"".equals(secrecyMobilestoragemediaId)){
				hql.append(" AND smc.secrecyMobilestoragemedia.secrecymobilestoragemediaId = :secrecyMobilestoragemediaId ");
				params.put("secrecyMobilestoragemediaId", secrecyMobilestoragemediaId);
			}
			if( secrecyMobilestoragemediaChangeId!=null && !"".equals(secrecyMobilestoragemediaChangeId)){
				hql.append(" AND smc.mobilestoragemediaChangeId = :mobilestoragemediaChangeId ");
				params.put("mobilestoragemediaChangeId", secrecyMobilestoragemediaChangeId);
			}
		List<SecrecyMobilestoragemediaChange> secrecyMobilestoragemediaChangeList = this.findList(hql.toString(), params);
		return secrecyMobilestoragemediaChangeList;
	}

}
