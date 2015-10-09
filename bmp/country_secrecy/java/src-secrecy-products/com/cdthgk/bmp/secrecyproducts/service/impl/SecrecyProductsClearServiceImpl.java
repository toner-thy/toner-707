package com.cdthgk.bmp.secrecyproducts.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.bmp.secrecyproducts.service.SecrecyProductsClearService;
import com.cdthgk.bmp.secrecyproducts.vo.SecrecyProductsClear;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;

public class SecrecyProductsClearServiceImpl extends
BmpServiceImpl<SecrecyProductsClear, Serializable> implements
SecrecyProductsClearService {


	/**
	 * 查询密品 的密级解除list
	 *
	 * @param psm  分页对象
	 * @param secrecyProductsClear  密品密级解除对象
	 * @param organ 单位
	 * @param district  行政区划
	 * @param isChildren 包含下级  1包含  0不包含
	 * @return
	 */
	public List<SecrecyProductsClear> queryClearList(PageSortModel psm,Organ organ,
			SecrecyProductsClear secrecyProductsClear,District district,int isChildren){

		List<SecrecyProductsClear> list = new ArrayList<SecrecyProductsClear>();

		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("from SecrecyProductsClear p where 1=1 ");

		//单位不为空
		if(organ!=null && organ.getOrganId()!=null) {
			hql.append(" and p.secrecyProducts.createOrgan.organId= :organId ");
			params.put("organId", organ.getOrganId());
		}
		//行政区划不为空
		if(district!=null && district.getLayer()!=null) {
			if(isChildren==1){
				hql.append(" and p.secrecyProducts.createOrgan.district.layer like :layer ");
				params.put("layer", district.getLayer()+"%");
			}else{
				hql.append(" and p.secrecyProducts.createOrgan.district.layer = :layer ");
				params.put("layer", district.getLayer());
			}
		}

		if(secrecyProductsClear!=null) {
			//名称
			if (secrecyProductsClear.getSecrecyProducts().getSecrecyproductsName()!= null
					&& !"".equals(secrecyProductsClear.getSecrecyProducts().getSecrecyproductsName())) {
				hql.append(" and p.secrecyProducts.secrecyproductsName like :name");
				params.put("name", "%"+secrecyProductsClear.getSecrecyProducts().getSecrecyproductsName()+"%");
			}
			//解除前密级
			if(secrecyProductsClear.getSecrecyProducts().getSecrecyLevel()!=null) {
				hql.append(" and p.secrecyProducts.secrecyLevel = :secrecyLevel");
				params.put("secrecyLevel", secrecyProductsClear.getSecrecyProducts().getSecrecyLevel());
			}
			//解密类型
			if(secrecyProductsClear.getClearType()!=null) {
				hql.append(" and p.clearType = :clearType");
				params.put("clearType", secrecyProductsClear.getClearType());
			}
			//解密时间
			if(secrecyProductsClear.getClearTime()!=null) {
				hql.append(" and p.clearTime = :clearTime");
				params.put("clearTime", secrecyProductsClear.getClearTime());
			}
		}
		hql.append(" order by p.createDate desc");

		if(psm!=null) {
			list = this.findList(hql.toString(), params, psm);
		}else {
			list = this.findList(hql.toString(), params);
		}
		return list;
	}
}
