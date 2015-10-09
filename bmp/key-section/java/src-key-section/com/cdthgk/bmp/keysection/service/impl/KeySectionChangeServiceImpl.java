package com.cdthgk.bmp.keysection.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdthgk.bmp.core.service.BmpServiceImpl;

import com.cdthgk.bmp.keysection.service.KeySectionChangeService;
import com.cdthgk.bmp.keysection.vo.KeySectionChange;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;



/**
 * 要害部门密级变更 实现类
 * @author 梁文杰  2013-07-13
 *
 */
public class KeySectionChangeServiceImpl extends BmpServiceImpl<KeySectionChange, Serializable>
implements KeySectionChangeService{

	/**
	 * 记录日志
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(KeySectionChangeServiceImpl.class);

	/**
	 * 构造函数
	 */
	public KeySectionChangeServiceImpl(){

	}

	/**
	 * 查询保密要害部门的变更记录
	 * @param psm   页面对象
	 * @param keySectionChange  要害部门密级变更对象(查询对象)
	 * @param organ     单位对象
	 * @param district  行政区对象
	 * @param isChildren  是否包含下级  针对行政区划对象   1表示包含   0表示不包含
	 *
	 * @return
	 */
	public List<KeySectionChange> queryKeySectionChangeList(PageSortModel psm, KeySectionChange keySectionChange, Organ organ, District district, Integer isChildren){

		List<KeySectionChange> list = new ArrayList<KeySectionChange>();//返回的list
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("select ksc ");
		hql.append(" FROM KeySectionChange ksc LEFT JOIN ksc.keySectionId ks LEFT JOIN ksc.keySectionId.department depart where 1=1 ");

		//如果单位对象不为空的时候
		if(organ!=null && organ.getOrganId()!=null) {
			hql.append(" and ks.keySectionId.organ.organId=:organId ");
			params.put("organId", organ.getOrganId());
		}
		//如果行政区划 不为空的
		if(district!=null && district.getLayer()!=null) {
			if(isChildren==1) {
				hql.append(" and ks.keySectionId.organ.district.layer like :layer ");
				params.put("layer", district.getLayer()+"%");
			}else {
				hql.append(" and ks.keySectionId.organ.district.layer = :layer ");
				params.put("layer", district.getLayer());
			}
		}

		//查询条件为空的情况
		if(keySectionChange!=null) {
			//保密要害部门id
			if(keySectionChange.getKeySectionId()!=null && keySectionChange.getKeySectionId().getKeySectionId()!=null) {
				hql.append(" and ksc.keySectionId.department.departmentId = :pkid");
				params.put("pkid",keySectionChange.getKeySectionId().getDepartment().getDepartmentId());
			}
			//保密要害部门name
			if(keySectionChange.getKeySectionId()!=null && keySectionChange.getKeySectionId().getDepartment()!=null && keySectionChange.getKeySectionId().getDepartment().getDepartmentName()!=null) {
				hql.append(" and ksc.keySectionId.department.departmentName like :departmentName");
				params.put("departmentName", "%" + keySectionChange.getKeySectionId().getDepartment().getDepartmentName() + "%");
			}
			//原密级
			if(keySectionChange.getBeforeLevel()!=null) {
				hql.append(" and ksc.beforeLevel = :beforeLevel");
				params.put("beforeLevel", keySectionChange.getBeforeLevel());
			}
			//变更后密级
			if(keySectionChange.getAfterLevel()!=null) {
				hql.append(" and ksc.afterLevel = :afterLevel");
				params.put("afterLevel", keySectionChange.getAfterLevel());
			}
			//变更时间
			if(keySectionChange.getChangeDate()!=null) {
				hql.append(" and ksc.changeDate = :changeDate");
				params.put("changeDate", keySectionChange.getChangeDate());
			}
			//保密期限变更
			if(keySectionChange.getChangeTimeState()!=null) {
				hql.append(" and ksc.changeTimeState = :changeTimeState");
				params.put("changeTimeState", keySectionChange.getChangeTimeState());
			}
		}
		hql.append(" order by ksc.createDate desc");//降序排列

		if(psm==null) {
			list = (List<KeySectionChange>) this.findList(hql.toString(), params);
		}else {
			list = (List<KeySectionChange>) this.findList(hql.toString(), params, psm);
		}

		return list;
	}


}
