package com.cdthgk.bmp.keyPart.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.bmp.keyPart.service.KeyPartChangeService;
import com.cdthgk.bmp.keyPart.vo.KeyPartChange;
import com.cdthgk.common.lang.LangUtils;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;

public class KeyPartChangeServiceImpl extends BmpServiceImpl<KeyPartChange, String> implements KeyPartChangeService{

	private static final Logger LOGGER = LoggerFactory
			.getLogger(KeyPartChangeServiceImpl.class);

	public KeyPartChangeServiceImpl(){

	}

	/**
	 * 要害部位密级变更记录  查询
	 *
	 * @param psm   分页对象
	 * @param keyPartChange   要害部位变更
	 * @param district    行政区划
	 * @param isChildren  是否查询下级
	 *
	 * @return
	 */
	public List<KeyPartChange> queryKeyPartChangeList(PageSortModel psm, KeyPartChange keyPartChange,Organ organ,District district, int isChildren){
		List<KeyPartChange> list = new ArrayList<KeyPartChange>();//如果 分页对象为空

		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("from KeyPartChange kpc where 1=1 ");
		//单位不为空
		if(organ!=null && organ.getOrganId()!=null) {
			hql.append("  and kpc.keyPartId.organ.organId=:organId  ");
			params.put("organId", organ.getOrganId());
		}
		//行政区划不为空
		if(district!=null && district.getLayer()!=null) {
			if(isChildren==1) {
				hql.append("  and kpc.keyPartId.organ.district.layer like :layer  ");
				params.put("layer", district.getLayer()+"%");
			}else {
				hql.append("  and kpc.keyPartId.organ.district.layer = :layer  ");
				params.put("layer", district.getLayer());
			}
		}

		if(keyPartChange!=null) {
			//保密要害部位过滤
			if(keyPartChange.getKeyPartId()!=null ) {
				//保密要害部位名称过滤
				if(keyPartChange.getKeyPartId().getPartName()!=null) {
					hql.append(" and kpc.keyPartId.partName like :partName ");
					params.put("partName", "%" + keyPartChange.getKeyPartId().getPartName() + "%");
				}
				//要害部门的  过滤，如果传入的参数中有  保密要害部门的信息，那么就只查询出该保密要害部门下的   保密要害部位
				if (keyPartChange.getKeyPartId().getDepartment()!= null && LangUtils.isNotEmpty(keyPartChange.getKeyPartId().getDepartment().getDepartmentId())) {
					hql.append(" and kpc.keyPartId.department.departmentId = :departmentId");
					params.put("departmentId", keyPartChange.getKeyPartId().getDepartment().getDepartmentId());
				}
				//要害部位  id
				if(keyPartChange.getKeyPartId().getPartId()!=null) {
					hql.append(" and kpc.keyPartId.partId = :pkid ");
					params.put("pkid", keyPartChange.getKeyPartId().getPartId());
				}
			}
			//原密级
			if(keyPartChange.getBeforeLevel()!=null) {
				hql.append(" and kpc.beforeLevel = :beforeLevel ");
				params.put("beforeLevel", keyPartChange.getBeforeLevel());
			}
			//变更后密级
			if(keyPartChange.getAfterLevel()!=null) {
				hql.append(" and kpc.afterLevel = :afterLevel ");
				params.put("afterLevel", keyPartChange.getAfterLevel());
			}
			//变更时间
			if(keyPartChange.getChangeDate()!=null) {
				hql.append(" and kpc.changeDate = :changeDate ");
				params.put("changeDate", keyPartChange.getChangeDate());
			}
			//保密期限变更
			if(keyPartChange.getChangeTimeState()!=null) {
				hql.append(" and kpc.changeTimeState = :changeTimeState ");
				params.put("changeTimeState", keyPartChange.getChangeTimeState());
			}
		}
		hql.append(" order by kpc.createDate desc");
		if(psm==null) {
			list = this.findList(hql.toString(), params);
		}else {
			list = this.findList(hql.toString(), params, psm);
		}


		return list;
	}
}
