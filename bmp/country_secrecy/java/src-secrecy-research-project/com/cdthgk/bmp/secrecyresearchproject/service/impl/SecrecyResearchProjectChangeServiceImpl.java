package com.cdthgk.bmp.secrecyresearchproject.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.bmp.secrecyresearchproject.service.SecrecyResearchProjectChangeService;
import com.cdthgk.bmp.secrecyresearchproject.vo.SecrecyResearchProjectChange;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;


public class SecrecyResearchProjectChangeServiceImpl extends BmpServiceImpl<SecrecyResearchProjectChange, Serializable>
implements SecrecyResearchProjectChangeService {

	/**
	 * 查询涉密科研项目变更 列表
	 * @param psm   分页对象
	 * @param secrecyResearchProjectChange  变更对象
	 * @param organ 单位
	 * @param district  行政区划
	 * @param isChildren 包含下级  1包含  0不包含
	 *
	 * @return
	 */
	public List<SecrecyResearchProjectChange> queryChangeList(PageSortModel psm, SecrecyResearchProjectChange secrecyResearchProjectChange , Organ organ,District district,int isChildren){

		List<SecrecyResearchProjectChange> list = new ArrayList<SecrecyResearchProjectChange>();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("From SecrecyResearchProjectChange p where 1=1 ");

		//单位不为空
		if(organ!=null && organ.getOrganId()!=null) {
			hql.append(" and p.secrecyResearchProject.createOrgan.organId =:organId ");
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

		//变更对象不为空的时候
		if(secrecyResearchProjectChange!=null) {
			//涉密科研项目id
			if (secrecyResearchProjectChange.getSecrecyResearchProject().getSecrecyResearchProjectId()!= null
					&& !"".equals(secrecyResearchProjectChange.getSecrecyResearchProject().getSecrecyResearchProjectId())) {
				hql.append(" and p.secrecyResearchProject.secrecyResearchProjectId = :pkid");
				params.put("pkid", secrecyResearchProjectChange.getSecrecyResearchProject().getSecrecyResearchProjectId());
			}
			//涉密科研项目名称
			if (secrecyResearchProjectChange.getSecrecyResearchProject().getSecrecyResearchProjectName()!= null
					&& !"".equals(secrecyResearchProjectChange.getSecrecyResearchProject().getSecrecyResearchProjectName())) {
				hql.append(" and p.secrecyResearchProject.secrecyResearchProjectName like :name");
				params.put("name", "%"+secrecyResearchProjectChange.getSecrecyResearchProject().getSecrecyResearchProjectName()+"%");
			}
			//原密级
			if(secrecyResearchProjectChange.getBeforeLevel()!=null) {
				hql.append(" and p.beforeLevel = :beforeLevel ");
				params.put("beforeLevel", secrecyResearchProjectChange.getBeforeLevel());
			}
			//变更后密级
			if(secrecyResearchProjectChange.getAfterLevel()!=null) {
				hql.append(" and p.afterLevel = :afterLevel ");
				params.put("afterLevel", secrecyResearchProjectChange.getAfterLevel());
			}
			//变更时间
			if(secrecyResearchProjectChange.getChangeDate()!=null) {
				hql.append(" and p.changeDate = :changeDate ");
				params.put("changeDate", secrecyResearchProjectChange.getChangeDate());
			}
			//保密期限变更
			if(secrecyResearchProjectChange.getChangeTimeState()!=null) {
				hql.append(" and p.changeTimeState = :changeTimeState ");
				params.put("changeTimeState", secrecyResearchProjectChange.getChangeTimeState());
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
