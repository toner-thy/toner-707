package com.cdthgk.bmp.secrecyproducts.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.bmp.secrecyproducts.service.SecrecyProductsChangeService;
import com.cdthgk.bmp.secrecyproducts.vo.SecrecyProductsChange;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;

public class SecrecyProductsChangeServiceImpl extends BmpServiceImpl<SecrecyProductsChange, Serializable>
implements SecrecyProductsChangeService {

	/**
	 * 查询密品变更 列表
	 * @param psm   分页对象
	 * @param secrecyProductsChange  变更对象
	 * @param organ 单位
	 * @param district  行政区划
	 * @param isChildren 包含下级  1包含  0不包含
	 * @return
	 */
	public List<SecrecyProductsChange> queryChangeList(PageSortModel psm, SecrecyProductsChange secrecyProductsChange,Organ organ,District district,int isChildren){

		List<SecrecyProductsChange> list = new ArrayList<SecrecyProductsChange>();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("From SecrecyProductsChange p where 1=1 ");

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

		//变更对象不为空的时候
		if(secrecyProductsChange!=null) {
			//密品id
			if (secrecyProductsChange.getSecrecyProducts().getSecrecyproductsId()!= null
					&& !"".equals(secrecyProductsChange.getSecrecyProducts().getSecrecyproductsId())) {
				hql.append(" and p.secrecyProducts.secrecyproductsId = :pid");
				params.put("pid", secrecyProductsChange.getSecrecyProducts().getSecrecyproductsId());
			}
			//密品名称
			if (secrecyProductsChange.getSecrecyProducts().getSecrecyproductsName()!= null
					&& !"".equals(secrecyProductsChange.getSecrecyProducts().getSecrecyproductsName())) {
				hql.append(" and p.secrecyProducts.secrecyproductsName like :name");
				params.put("name", "%"+secrecyProductsChange.getSecrecyProducts().getSecrecyproductsName()+"%");
			}
			//原密级
			if(secrecyProductsChange.getBeforeLevel()!=null) {
				hql.append(" and p.beforeLevel = :beforeLevel ");
				params.put("beforeLevel", secrecyProductsChange.getBeforeLevel());
			}
			//变更后密级
			if(secrecyProductsChange.getAfterLevel()!=null) {
				hql.append(" and p.afterLevel = :afterLevel ");
				params.put("afterLevel", secrecyProductsChange.getAfterLevel());
			}
			//变更时间
			if(secrecyProductsChange.getChangeDate()!=null) {
				hql.append(" and p.changeDate = :changeDate ");
				params.put("changeDate", secrecyProductsChange.getChangeDate());
			}
			//保密期限变更
			if(secrecyProductsChange.getChangeTimeState()!=null) {
				hql.append(" and p.changeTimeState = :changeTimeState ");
				params.put("changeTimeState", secrecyProductsChange.getChangeTimeState());
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
