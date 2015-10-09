package com.cdthgk.disclosesecrecy.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.disclosesecrecy.service.DiscloseSecrecyChangeService;
import com.cdthgk.disclosesecrecy.vo.DiscloseSecrecy;
import com.cdthgk.disclosesecrecy.vo.DiscloseSecrecyChange;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

/**
 * <p>
 * 保密范围Serviceimpl接口实现类
 * </p>
 * <p>
 * 王欢 2009-10-28 12:34:56
 * </p>
 * <blockquote>
 * <h4>历史修改记录</h4>
 * <ul>
 * <li>
 * 修改人wangpb 2013 1 7 12:00:02 修改注释格式
 * </ul>
 * </blockquote>
 *
 * @author 王欢
 * @since 1.0
 * @version 1.0
 */
public class DiscloseSecrecyChangeServiceImpl extends BmpServiceImpl<DiscloseSecrecyChange, String> implements
DiscloseSecrecyChangeService {


	@Override
	public List<DiscloseSecrecyChange> querydiscloseSecrecyChangelistList(
			PageSortModel<DiscloseSecrecyChange> psm,
			DiscloseSecrecyChange discloseSecrecyChange,User user) {
		List<DiscloseSecrecyChange> list = new ArrayList<DiscloseSecrecyChange>();//如果 分页对象为空
		if (psm == null) {
			return list;
		}
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("from DiscloseSecrecyChange dsc where 1=1 ");
		hql.append(" and dsc.createPerson.organ.organId = :organId");
		params.put("organId", user.getOrgan().getOrganId());
		if(discloseSecrecyChange!=null) {

			if(discloseSecrecyChange.getDisclosesecrecycaseId()!=null ) {

				if(discloseSecrecyChange.getDisclosesecrecycaseId().getName()!=null) {
					hql.append(" and dsc.disclosesecrecycaseId.name like :name ");
					params.put("name", "%" + discloseSecrecyChange.getDisclosesecrecycaseId().getName()+ "%");
				}
			}
			//原密级
			if(discloseSecrecyChange.getBeforeLevel()!=null) {
				hql.append(" and dsc.beforeLevel = :beforeLevel ");
				params.put("beforeLevel", discloseSecrecyChange.getBeforeLevel());
			}
			//变更后密级
			if(discloseSecrecyChange.getAfterLevel()!=null) {
				hql.append(" and dsc.afterLevel = :afterLevel ");
				params.put("afterLevel", discloseSecrecyChange.getAfterLevel());
			}
			//变更时间
			if(discloseSecrecyChange.getChangeDate()!=null) {
				hql.append(" and dsc.changeDate = :changeDate ");
				params.put("changeDate", discloseSecrecyChange.getChangeDate());
			}
			//保密期限变更
			if(discloseSecrecyChange.getChangeTimeState()!=null) {
				hql.append(" and dsc.changeTimeState = :changeTimeState ");
				params.put("changeTimeState", discloseSecrecyChange.getChangeTimeState());
			}
		}
		hql.append(" order by dsc.createDate desc");
		list = this.findList(hql.toString(), params);
		return list;
	}
	/**
	 * 某个泄密事件密级变更记录
	 *
	 * @param psm   分页对象
	 * @param discloseSecrecy   泄密事件变更
	 * @return
	 */
	public List<DiscloseSecrecyChange> querydiscloseSecrecyChangeList(
			PageSortModel<DiscloseSecrecyChange> psm,
			DiscloseSecrecy discloseSecrecy,User user) {
			Map<String, Object> params = new HashMap<String, Object>();
			StringBuilder hql = new StringBuilder("from DiscloseSecrecyChange dsc where 1=1 ");
			hql.append(" and dsc.disclosesecrecycaseId.disclosesecrecycaseId = :disclosesecrecycaseId");
			hql.append(" and dsc.createPerson.organ.organId = :organId");
			params.put("disclosesecrecycaseId", discloseSecrecy.getDisclosesecrecycaseId());
			params.put("organId", user.getOrgan().getOrganId());
			hql.append(" order by dsc.createDate desc");
			List<DiscloseSecrecyChange> list = this.findList(hql.toString(), params);
			return list;
	}
	@Override
	public List<DiscloseSecrecyChange> querydiscloseSecrecyChangelistList(
			PageSortModel<DiscloseSecrecyChange> psm,
			DiscloseSecrecyChange discloseSecrecyChange, User user,
			String districtCode,String includeChild) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("from DiscloseSecrecyChange dsc where 1=1 ");
		hql.append(" and dsc.createPerson.organ.organId in (");
		if (includeChild.equals("1")) {
		int layer=this.getPersistProxy().getJdbcPersistence().findForInt(
				"select layer from sys_district where district_code ='"+districtCode+"'", params);
		hql.append("select o.organId from Organ as o where o.district.districtCode in ");
		hql.append("(select district.districtCode from District as district where district.layer like :layer ))");
		params.put("layer",  layer+"%");
		}
		if (includeChild.equals("0")) {
			hql.append("select o.organId from Organ as o where o.district.districtCode=:districtCode )");
			params.put("districtCode",  districtCode);
		}
		if(discloseSecrecyChange!=null) {

			if(discloseSecrecyChange.getDisclosesecrecycaseId()!=null ) {

				if(discloseSecrecyChange.getDisclosesecrecycaseId().getName()!=null) {
					hql.append(" and dsc.disclosesecrecycaseId.name like :name ");
					params.put("name", "%" + discloseSecrecyChange.getDisclosesecrecycaseId().getName()+ "%");
				}
			}
			//原密级
			if(discloseSecrecyChange.getBeforeLevel()!=null) {
				hql.append(" and dsc.beforeLevel = :beforeLevel ");
				params.put("beforeLevel", discloseSecrecyChange.getBeforeLevel());
			}
			//变更后密级
			if(discloseSecrecyChange.getAfterLevel()!=null) {
				hql.append(" and dsc.afterLevel = :afterLevel ");
				params.put("afterLevel", discloseSecrecyChange.getAfterLevel());
			}
			//变更时间
			if(discloseSecrecyChange.getChangeDate()!=null) {
				hql.append(" and dsc.changeDate = :changeDate ");
				params.put("changeDate", discloseSecrecyChange.getChangeDate());
			}
			//保密期限变更
			if(discloseSecrecyChange.getChangeTimeState()!=null) {
				hql.append(" and dsc.changeTimeState = :changeTimeState ");
				params.put("changeTimeState", discloseSecrecyChange.getChangeTimeState());
			}
		}
		hql.append(" order by dsc.createDate desc");
		List<DiscloseSecrecyChange> list= this.findList(hql.toString(), params);
		return list;
	}



}