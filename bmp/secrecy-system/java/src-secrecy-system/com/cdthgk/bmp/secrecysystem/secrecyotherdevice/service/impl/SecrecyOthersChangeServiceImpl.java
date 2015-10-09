/**
 *
 */
package com.cdthgk.bmp.secrecysystem.secrecyotherdevice.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.bmp.secrecysystem.secrecyotherdevice.service.SecrecyOthersChangeService;
import com.cdthgk.bmp.secrecysystem.secrecyotherdevice.vo.SecrecyOthersChange;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;

/**
 * <p>
 * 类的说明放这里 2013-8-5 下午2:10:23
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 *
 * @author 宋亚非
 */
public class SecrecyOthersChangeServiceImpl extends
		BmpServiceImpl<SecrecyOthersChange, String> implements
		SecrecyOthersChangeService {

	/* (non-Javadoc)
	 * @see com.cdthgk.bmp.secrecysystem.secrecyotherdevice.service.SecrecyOthersChangeService#findPageAllList(ec.common.PageSortModel, com.cdthgk.bmp.secrecysystem.secrecyotherdevice.vo.SecrecyOthersChange, com.cdthgk.platform.organization.organ.domain.Organ)
	 */
	@Override
	public List<SecrecyOthersChange> findPageAllList(
			PageSortModel<SecrecyOthersChange> psm,
			SecrecyOthersChange secrecyOthersChange, Organ organ, District district, String checkLower) {
		Map<String,Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM SecrecyOthersChange soc where 1=1");

		if( organ!=null ){
			hql.append(" AND soc.secrecyOthers.createOrgan.organId = :organId ");
			params.put("organId", organ.getOrganId());
		}

		if( district!=null ){
			if( checkLower!=null && "1".equals(checkLower) ){
				hql.append(" AND soc.secrecyOthers.createOrgan.district.layer like :layer ");
				params.put("layer", district.getLayer()+"%");
			}else{
				hql.append(" AND soc.secrecyOthers.createOrgan.district.layer = :layer ");
				params.put("layer", district.getLayer());
			}
		}

		//查询条件
		if(secrecyOthersChange!=null ){
			if(secrecyOthersChange.getSecrecyOthers()!=null){
				if( secrecyOthersChange.getSecrecyOthers().getSecrecyothersType()!=null ){
					hql.append(" AND soc.secrecyOthers.secrecyothersType = :secrecyothersType ");
					params.put("secrecyothersType", secrecyOthersChange.getSecrecyOthers().getSecrecyothersType());
				}
			}
			if( secrecyOthersChange.getChangeTimeState()!=null ){
				hql.append(" AND soc.changeTimeState = :changeTimeState ");
				params.put("changeTimeState", secrecyOthersChange.getChangeTimeState());
			}
			if( secrecyOthersChange.getBeforeLevel()!=null ){
				hql.append(" AND soc.beforeLevel = :beforeLevel ");
				params.put("beforeLevel", secrecyOthersChange.getBeforeLevel());
			}
			if( secrecyOthersChange.getAfterLevel()!=null ){
				hql.append(" AND soc.afterLevel = :afterLevel ");
				params.put("afterLevel", secrecyOthersChange.getAfterLevel());
			}
		}
		List<SecrecyOthersChange> secrecyOthersChangeList = this.findList(hql.toString(), params, psm);
		return secrecyOthersChangeList;
	}

	/* (non-Javadoc)
	 * @see com.cdthgk.bmp.secrecysystem.secrecyotherdevice.service.SecrecyOthersChangeService#findDataList(java.lang.String, java.lang.String)
	 */
	@Override
	public List<SecrecyOthersChange> findDataList(String secrecyOthersId,
			String secrecyOthersChangeId) {
		Map<String,Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM SecrecyOthersChange soc where 1=1");

		//查询条件
		if( secrecyOthersId!=null && !"".equals(secrecyOthersId) ){
			hql.append(" AND soc.secrecyOthers.secrecyothersId = :secrecyothersId ");
			params.put("secrecyothersId", secrecyOthersId);
		}

		if( secrecyOthersChangeId!=null && !"".equals(secrecyOthersChangeId) ){
			hql.append(" AND soc.secrecyothersChangeId = :secrecyothersChangeId ");
			params.put("secrecyothersChangeId", secrecyOthersChangeId);
		}
		List<SecrecyOthersChange> secrecyOthersChangeList = this.findList(hql.toString(), params);
		return secrecyOthersChangeList;
	}



}
