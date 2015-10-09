/**
 *
 */
package com.cdthgk.bmp.secrecysystem.secrecyotherdevice.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.bmp.secrecysystem.secrecyotherdevice.service.SecrecyOthersClearService;
import com.cdthgk.bmp.secrecysystem.secrecyotherdevice.vo.SecrecyOthersClear;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;

/**
 * <p>
 * 类的说明放这里 2013-8-5 下午2:14:26
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 *
 * @author 宋亚非
 */
public class SecrecyOthersClearServiceImpl extends
		BmpServiceImpl<SecrecyOthersClear, String> implements
		SecrecyOthersClearService {

	/* (non-Javadoc)
	 * @see com.cdthgk.bmp.secrecysystem.secrecyotherdevice.service.SecrecyOthersClearService#findPageAllList(ec.common.PageSortModel, com.cdthgk.bmp.secrecysystem.secrecyotherdevice.vo.SecrecyOthersClear, com.cdthgk.platform.organization.organ.domain.Organ)
	 */
	@Override
	public List<SecrecyOthersClear> findPageAllList(
			PageSortModel<SecrecyOthersClear> psm,
			SecrecyOthersClear secrecyOthersClear, Organ organ, District district, String checkLower) {
		Map<String,Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM SecrecyOthersClear soc where 1=1");
		//当前登录单位
		if( organ!=null ){
			hql.append(" AND soc.secrecyOthers.createOrgan.organId = :organId ");
			params.put("organId", organ.getOrganId());
		}
		//行政区划树查询
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
		if(secrecyOthersClear!=null ){
			if( secrecyOthersClear!=null && secrecyOthersClear.getSecrecyOthers()!=null ){
				if( secrecyOthersClear.getSecrecyOthers().getSecrecyothersType()!=null ){
					hql.append(" AND soc.secrecyOthers.secrecyothersType = :secrecyothersType ");
					params.put("secrecyothersType", secrecyOthersClear.getSecrecyOthers().getSecrecyothersType());
				}
				if( secrecyOthersClear.getSecrecyOthers().getSecrecyLevel()!=null ){
					hql.append(" AND soc.secrecyOthers.secrecyLevel = :secrecyLevel ");
					params.put("secrecyLevel", secrecyOthersClear.getSecrecyOthers().getSecrecyLevel());
				}
			}

			if( secrecyOthersClear.getClearType()!=null ){
				hql.append(" AND soc.clearType = :clearType ");
				params.put("clearType", secrecyOthersClear.getClearType());
			}

			if( secrecyOthersClear.getClearTime()!=null ){
				hql.append(" AND soc.clearTime = :clearTime ");
				params.put("clearTime", secrecyOthersClear.getClearTime());
			}
		}
		List<SecrecyOthersClear> secrecyOthersClearList = this.findList(hql.toString(), params, psm);
		return secrecyOthersClearList;
	}

	/* (non-Javadoc)
	 * @see com.cdthgk.bmp.secrecysystem.secrecyotherdevice.service.SecrecyOthersClearService#findDataList(java.lang.String)
	 */
	@Override
	public List<SecrecyOthersClear> findDataList(String secrecyOthersId) {
		Map<String,Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM SecrecyOthersClear soc where 1=1");

		//查询条件
		if( secrecyOthersId!=null && !"".equals(secrecyOthersId) ){
			hql.append(" AND soc.secrecyOthers.secrecyothersId = :secrecyOthersId ");
			params.put("secrecyOthersId", secrecyOthersId);
		}

		List<SecrecyOthersClear> secrecyOthersClearList = this.findList(hql.toString(), params);
		return secrecyOthersClearList;
	}

}
