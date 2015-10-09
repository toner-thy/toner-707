package com.cdthgk.bmp.keyPart.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.bmp.keyPart.service.KeyPartSecrecyClearService;
import com.cdthgk.bmp.keyPart.vo.KeyPartSecrecyClear;
import com.cdthgk.common.lang.LangUtils;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;

public class KeyPartSecrecyClearServiceImpl extends BmpServiceImpl<KeyPartSecrecyClear, String> implements KeyPartSecrecyClearService{

	private static final Logger LOGGER = LoggerFactory
			.getLogger(KeyPartSecrecyClearServiceImpl.class);

	public KeyPartSecrecyClearServiceImpl(){

	}

	/**
	 * 查询 保密要害部位的密级解除记录
	 * @param psm  分页对象
	 * @param keyPartSecrecyClear 密级解除对象
	 * @param district   行政区划
	 * @param isChildren 是否包含下级
	 * @return
	 */
	public List<KeyPartSecrecyClear> queryKeyPartClearList(PageSortModel psm, KeyPartSecrecyClear keyPartSecrecyClear,Organ organ, District district, int isChildren){

		List<KeyPartSecrecyClear> list = new ArrayList<KeyPartSecrecyClear>();
		if (psm == null) { // 如果分页对象为空，返回list
			return list;
		}

		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("FROM KeyPartSecrecyClear kpsc where 1=1 ");
		//单位不为空
		if(organ!=null && organ.getOrganId()!=null) {
			hql.append(" and kpsc.keyPartId.organ.organId=:organId ");
			params.put("organId", organ.getOrganId());
		}
		//行政区划不为空
		if(district!=null && district.getLayer()!=null) {
			if(isChildren==1) {
				hql.append("  and kpsc.keyPartId.organ.district.layer like :layer  ");
				params.put("layer", district.getLayer()+"%");
			}else {
				hql.append("  and kpsc.keyPartId.organ.district.layer = :layer  ");
				params.put("layer", district.getLayer());
			}
		}

		if(keyPartSecrecyClear!=null) {
			//保密要害部位
			if(keyPartSecrecyClear.getKeyPartId()!=null ) {
				//保密要害部位 名称过滤
				if(keyPartSecrecyClear.getKeyPartId().getPartName() != null) {
					hql.append(" and kpsc.keyPartId.partName like :partName");
					params.put("partName", "%" + keyPartSecrecyClear.getKeyPartId().getPartName() + "%");
				}
				//要害部门的  过滤，如果传入的参数中有  保密要害部门的信息，那么就只查询出该保密要害部门下的   保密要害部位
				if (keyPartSecrecyClear.getKeyPartId().getDepartment()!= null && LangUtils.isNotEmpty(keyPartSecrecyClear.getKeyPartId().getDepartment().getDepartmentId())) {
					hql.append(" and kpsc.keyPartId.department.departmentId = :departmentId");
					params.put("departmentId", keyPartSecrecyClear.getKeyPartId().getDepartment().getDepartmentId());
				}
			}
			//解除前密级
			if(keyPartSecrecyClear.getKeyPartId().getSecrecyLevel()!=null) {
				hql.append(" and kpsc.keyPartId.secrecyLevel = :secrecyLevel");
				params.put("secrecyLevel", keyPartSecrecyClear.getKeyPartId().getSecrecyLevel());
			}
			//解密类型
			if(keyPartSecrecyClear.getClearType()!=null) {
				hql.append(" and kpsc.clearType = :clearType");
				params.put("clearType", keyPartSecrecyClear.getClearType());
			}
			//解密时间
			if(keyPartSecrecyClear.getClearTime()!=null) {
				hql.append(" and kpsc.clearTime = :clearTime");
				params.put("clearTime", keyPartSecrecyClear.getClearTime());
			}
		}

		hql.append(" order by kpsc.createDate desc");//降序排列
		list = (List<KeyPartSecrecyClear>) this.findList(hql.toString(), params, psm);


		return list;
	}
}
