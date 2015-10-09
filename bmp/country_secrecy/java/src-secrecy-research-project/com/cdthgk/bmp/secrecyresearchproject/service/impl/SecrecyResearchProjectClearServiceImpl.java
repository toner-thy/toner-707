package com.cdthgk.bmp.secrecyresearchproject.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.bmp.secrecyresearchproject.service.SecrecyResearchProjectClearService;
import com.cdthgk.bmp.secrecyresearchproject.vo.SecrecyResearchProjectClear;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;

public class SecrecyResearchProjectClearServiceImpl  extends BmpServiceImpl<SecrecyResearchProjectClear, Serializable>
implements SecrecyResearchProjectClearService {

	/**
	 * 查询涉密科研项目 的密级解除list
	 *
	 * @param psm  分页对象
	 * @param secrecyResearchProjectClear  涉密科研项目密级解除对象
	 * @param organ 单位
	 * @param district  行政区划
	 * @param isChildren 包含下级  1包含  0不包含
	 *
	 * @return
	 */
	public List<SecrecyResearchProjectClear> queryClearList(PageSortModel psm,
			SecrecyResearchProjectClear secrecyResearchProjectClear, Organ organ,District district,int isChildren){

		List<SecrecyResearchProjectClear> list = new ArrayList<SecrecyResearchProjectClear>();

		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("from SecrecyResearchProjectClear p where 1=1 ");

		//单位不为空
		if(organ!=null && organ.getOrganId()!=null) {
			hql.append("  and p.secrecyResearchProject.createOrgan.organId =:organId ");
			params.put("organId", organ.getOrganId());
		}
		//行政区划不为空
		if(district!=null && district.getLayer()!=null) {
			if(isChildren==1){
				hql.append(" and p.secrecyResearchProject.createOrgan.district.layer like :layer ");
				params.put("layer", district.getLayer()+"%");
			}else{
				hql.append(" and p.secrecyResearchProject.createOrgan.district.layer = :layer ");
				params.put("layer", district.getLayer());
			}
		}

		if(secrecyResearchProjectClear!=null) {
			//名称
			if (secrecyResearchProjectClear.getSecrecyResearchProject().getSecrecyResearchProjectName()!= null
					&& !"".equals(secrecyResearchProjectClear.getSecrecyResearchProject().getSecrecyResearchProjectName())) {
				hql.append(" and p.secrecyResearchProject.secrecyResearchProjectName like :name");
				params.put("name", "%"+secrecyResearchProjectClear.getSecrecyResearchProject().getSecrecyResearchProjectName()+"%");
			}
			//解除前密级
			if(secrecyResearchProjectClear.getSecrecyResearchProject().getSecrecyLevel()!=null) {
				hql.append(" and p.secrecyResearchProject.secrecyLevel = :secrecyLevel");
				params.put("secrecyLevel", secrecyResearchProjectClear.getSecrecyResearchProject().getSecrecyLevel());
			}
			//解密类型
			if(secrecyResearchProjectClear.getClearType()!=null) {
				hql.append(" and p.clearType = :clearType");
				params.put("clearType", secrecyResearchProjectClear.getClearType());
			}
			//解密时间
			if(secrecyResearchProjectClear.getClearTime()!=null) {
				hql.append(" and p.clearTime = :clearTime");
				params.put("clearTime", secrecyResearchProjectClear.getClearTime());
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
