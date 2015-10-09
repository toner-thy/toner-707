package com.cdthgk.bmp.secrecycountryitem.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.bmp.secrecycountryitem.service.SecrecyCountryItemChangeService;
import com.cdthgk.bmp.secrecycountryitem.vo.SecrecyCountryItemChange;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;

/**
 * 商业秘密事项  密级变更  实现类
 * @author lwj 2013-07-15
 *
 */
public class SecrecyCountryItemChangeServiceImpl extends
		BmpServiceImpl<SecrecyCountryItemChange, Serializable> implements
		SecrecyCountryItemChangeService {

	/**
	 * 记录日志
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(SecrecyCountryItemChangeServiceImpl.class);

	/**
	 * 构造函数
	 */
	public SecrecyCountryItemChangeServiceImpl(){

	}

	/**
	 * 查询商业秘密事项 的密级变更list
	 *
	 * @param psm  分页对象
	 * @param organ  单位
	 * @param secrecyCountryItemChange  商业秘密事项密级变更对象
	 * @param district  行政区划
	 * @param isChildren 包含下级  1包含  0不包含
	 *
	 * @return
	 */
	public List<SecrecyCountryItemChange> queryCountryItemChangeList(PageSortModel psm, Organ organ,
			SecrecyCountryItemChange secrecyCountryItemChange,District district,int isChildren) {

		List<SecrecyCountryItemChange> list = new ArrayList<SecrecyCountryItemChange>();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("From SecrecyCountryItemChange p where 1=1 ");

		//单位不为空
		if(organ!=null && organ.getOrganId()!=null) {
			hql.append(" and p.secrecyCountryItem.createOrgan.organId= :organId ");
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

		//变更对象不为空的时候
		if(secrecyCountryItemChange!=null) {
			//商业秘密事项id
			if (secrecyCountryItemChange.getSecrecyCountryItem().getSecrecyCountryItemId()!= null
					&& !"".equals(secrecyCountryItemChange.getSecrecyCountryItem().getSecrecyCountryItemId())) {
				hql.append(" and p.secrecyCountryItem.secrecyCountryItemId = :pkid");
				params.put("pkid", secrecyCountryItemChange.getSecrecyCountryItem().getSecrecyCountryItemId());
			}
			//商业秘密事项名称
			if (secrecyCountryItemChange.getSecrecyCountryItem().getSecrecyCountryItemName()!= null
					&& !"".equals(secrecyCountryItemChange.getSecrecyCountryItem().getSecrecyCountryItemName())) {
				hql.append(" and p.secrecyCountryItem.secrecyCountryItemName like :name");
				params.put("name", "%"+secrecyCountryItemChange.getSecrecyCountryItem().getSecrecyCountryItemName()+"%");
			}
			//原密级
			if(secrecyCountryItemChange.getBeforeLevel()!=null) {
				hql.append(" and p.beforeLevel = :beforeLevel ");
				params.put("beforeLevel", secrecyCountryItemChange.getBeforeLevel());
			}
			//变更后密级
			if(secrecyCountryItemChange.getAfterLevel()!=null) {
				hql.append(" and p.afterLevel = :afterLevel ");
				params.put("afterLevel", secrecyCountryItemChange.getAfterLevel());
			}
			//变更时间
			if(secrecyCountryItemChange.getChangeDate()!=null) {
				hql.append(" and p.changeDate = :changeDate ");
				params.put("changeDate", secrecyCountryItemChange.getChangeDate());
			}
			//保密期限变更
			if(secrecyCountryItemChange.getChangeTimeState()!=null) {
				hql.append(" and p.changeTimeState = :changeTimeState ");
				params.put("changeTimeState", secrecyCountryItemChange.getChangeTimeState());
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
