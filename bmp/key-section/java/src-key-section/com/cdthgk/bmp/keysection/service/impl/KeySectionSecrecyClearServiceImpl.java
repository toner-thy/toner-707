package com.cdthgk.bmp.keysection.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.bmp.keysection.service.KeySectionSecrecyClearService;
import com.cdthgk.bmp.keysection.vo.KeySectionSecrecyClear;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;

public class KeySectionSecrecyClearServiceImpl extends BmpServiceImpl<KeySectionSecrecyClear, Serializable>
implements KeySectionSecrecyClearService{


	/**
	 * 记录日志
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(KeySectionSecrecyClearServiceImpl.class);

	/**
	 * 构造函数
	 */
	public KeySectionSecrecyClearServiceImpl(){

	}

	/**
	 * 查询 保密要害部门的密级解除记录
	 * @param psm  分页对象
	 * @param keySectionSecrecyClear 密级解除对象
	 * @param organ  单位对象
	 * @param district  行政区划
	 * @param isChildren  是否包含下级  针对行政区划对象   1表示包含   0表示不包含
	 *
	 * @return
	 */
	public List<KeySectionSecrecyClear> queryKeySectionClearList(PageSortModel psm, KeySectionSecrecyClear keySectionSecrecyClear, Organ organ, District district, Integer isChildren){

		List<KeySectionSecrecyClear> list = new ArrayList<KeySectionSecrecyClear>();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("select kssc ");
		hql.append(" FROM KeySectionSecrecyClear kssc LEFT JOIN kssc.keySectionId ks LEFT JOIN ks.department depart where 1=1 ");

		//单位不为空
		if(organ!=null && organ.getOrganId()!=null) {
			hql.append(" and kssc.keySectionId.organ.organId=:organId ");
			params.put("organId", organ.getOrganId());
		}
		//行政区划不为空
		if(district!=null && district.getLayer()!=null) {
			if(isChildren==1) {
				hql.append(" and kssc.keySectionId.organ.district.layer like :layer ");
				params.put("layer", district.getLayer()+"%");
			}else {
				hql.append(" and kssc.keySectionId.organ.district.layer = :layer ");
				params.put("layer", district.getLayer());
			}
		}

		//密级解除 对象不为空
		if(keySectionSecrecyClear!=null) {
			//保密要害部门
			if(keySectionSecrecyClear.getKeySectionId()!=null && keySectionSecrecyClear.getKeySectionId().getKeySectionId()!=null) {
				hql.append(" and kssc.keySectionId.department.departmentName like :departmentName");
				params.put("departmentName", "%" + keySectionSecrecyClear.getKeySectionId().getDepartment().getDepartmentName() + "%");
			}
			//解除前密级
			if(keySectionSecrecyClear.getKeySectionId().getSecrecyLevel()!=null) {
				hql.append(" and kssc.keySectionId.secrecyLevel = :secrecyLevel");
				params.put("secrecyLevel", keySectionSecrecyClear.getKeySectionId().getSecrecyLevel());
			}
			//解密类型
			if(keySectionSecrecyClear.getClearType()!=null) {
				hql.append(" and kssc.clearType = :clearType");
				params.put("clearType", keySectionSecrecyClear.getClearType());
			}
			//解密时间
			if(keySectionSecrecyClear.getClearTime()!=null) {
				hql.append(" and kssc.clearTime = :clearTime");
				params.put("clearTime", keySectionSecrecyClear.getClearTime());
			}
		}

		hql.append(" order by kssc.createDate desc");//降序排列
		if (psm == null) {//分页对象 为空   不分页
			list = (List<KeySectionSecrecyClear>) this.findList(hql.toString(), params);
		}else {
			list = (List<KeySectionSecrecyClear>) this.findList(hql.toString(), params, psm);
		}
		return list;
	}
}
