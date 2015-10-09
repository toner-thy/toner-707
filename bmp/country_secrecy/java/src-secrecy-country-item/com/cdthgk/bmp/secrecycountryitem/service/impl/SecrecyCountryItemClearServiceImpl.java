package com.cdthgk.bmp.secrecycountryitem.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.bmp.secrecycountryitem.service.SecrecyCountryItemClearService;
import com.cdthgk.bmp.secrecycountryitem.vo.SecrecyCountryItemClear;
import com.cdthgk.common.lang.DateUtils;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;

/**
 * 商业秘密事项  密级解除   实现类
 * @author lwj 2013-07-15
 *
 */
public class SecrecyCountryItemClearServiceImpl extends
		BmpServiceImpl<SecrecyCountryItemClear, Serializable> implements
		SecrecyCountryItemClearService {

	/**
	 * 记录日志
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(SecrecyCountryItemClearServiceImpl.class);

	/**
	 * 构造函数
	 */
	public SecrecyCountryItemClearServiceImpl(){

	}

	/**
	 * 查询商业秘密事项 的密级解除list
	 *
	 * @param psm  分页对象
	 * @param secrecyCountryItemChange  商业秘密事项密级解除对象
	 * @param organ 单位
	 * @param district  行政区划
	 * @param isChildren 包含下级  1包含  0不包含
	 *
	 * @return
	 */
	public List<SecrecyCountryItemClear> queryCountryItemChangeList(PageSortModel psm,  Organ organ,
			SecrecyCountryItemClear secrecyCountryItemClear,District district,int isChildren){

		List<SecrecyCountryItemClear> list = new ArrayList<SecrecyCountryItemClear>();

		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("from SecrecyCountryItemClear p where 1=1 ");

		//单位不为空
		if(organ!=null && organ.getOrganId()!=null) {
			hql.append(" and p.secrecyCountryItem.createOrgan.organId= :organId  ");
			params.put("organId", organ.getOrganId());
		}
		//行政区划不为空
		if(district!=null && district.getLayer()!=null) {
			if(isChildren==1){
				hql.append(" and p.secrecyCountryItem.createOrgan.district.layer like :layer ");
				params.put("layer", district.getLayer()+"%");
			}else{
				hql.append(" and p.secrecyCountryItem.createOrgan.district.layer = :layer ");
				params.put("layer", district.getLayer());
			}
		}

		if(secrecyCountryItemClear!=null) {
			//商业秘密事项名称
			if (secrecyCountryItemClear.getSecrecyCountryItem().getSecrecyCountryItemName()!= null
					&& !"".equals(secrecyCountryItemClear.getSecrecyCountryItem().getSecrecyCountryItemName())) {
				hql.append(" and p.secrecyCountryItem.secrecyCountryItemName like :name");
				params.put("name", "%"+secrecyCountryItemClear.getSecrecyCountryItem().getSecrecyCountryItemName()+"%");
			}
			//解除前密级
			if(secrecyCountryItemClear.getSecrecyCountryItem().getSecrecyLevel()!=null) {
				hql.append(" and p.secrecyCountryItem.secrecyLevel = :secrecyLevel");
				params.put("secrecyLevel", secrecyCountryItemClear.getSecrecyCountryItem().getSecrecyLevel());
			}
			//解密类型
			if(secrecyCountryItemClear.getClearType()!=null) {
				hql.append(" and p.clearType = :clearType");
				params.put("clearType", secrecyCountryItemClear.getClearType());
			}
			//解密时间
			if(secrecyCountryItemClear.getClearTime()!=null) {
				hql.append(" and p.clearTime = :clearTime");
				params.put("clearTime", secrecyCountryItemClear.getClearTime());
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
