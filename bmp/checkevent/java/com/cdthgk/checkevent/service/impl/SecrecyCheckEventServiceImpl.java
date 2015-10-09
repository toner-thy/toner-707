package com.cdthgk.checkevent.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.checkevent.service.SecrecyCheckEventService;
import com.cdthgk.checkevent.vo.SecrecyCheckEvent;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.permission.user.domain.User;
import com.thgk.platform.syslog.service.invoke.imp.annotation.ExcuteType;
import com.thgk.platform.syslog.service.invoke.imp.annotation.SysLog;
import com.thgk.platform.syslog.service.invoke.imp.annotation.SysLogs;

import ec.common.PageSortModel;

/**
 * @ 类用途 @ 创建人 hxj @ 创建时间 Oct 12, 2009 - 9:36:48 AM @ 修改人 @ 修改时间 @ 修改描述 @ 公司名称 @ 当前系统主版本号
 */
@SuppressWarnings("all")
@SysLogs(
		{
			@SysLog(method="update",methodParamsSize=1,description="更新保密检查事件{0}|eventName",excute_type=ExcuteType.UPDATE),
			@SysLog(method="deleteBatchWithId",methodParamsSize=1,description="删除保密检查事件",excute_type=ExcuteType.DELETE),
			@SysLog(method="save",methodParamsSize=1,description="添加保密检查事件{0}|eventName",excute_type=ExcuteType.ADD)
		}
)
public class SecrecyCheckEventServiceImpl extends
BmpServiceImpl<SecrecyCheckEvent, String> implements
		SecrecyCheckEventService {

	@SuppressWarnings("unchecked")
	public List listForEc(PageSortModel psm,SecrecyCheckEvent secrecyCheckEvent, Organ organ) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("FROM SecrecyCheckEvent WHERE 1=1");
		StringBuilder conditions = new StringBuilder();
		// 查看当前登录单位数据
		hql.append(" and organId.organId = :organId");
		params.put("organId", organ.getOrganId());

		if (secrecyCheckEvent != null) {
			if (secrecyCheckEvent.getEventName() != null && !"".equals(secrecyCheckEvent.getEventName())) {
				hql.append(" and eventName like :eventName");
				params.put("eventName", "%" + secrecyCheckEvent.getEventName() + "%");
			}
			DateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			if( secrecyCheckEvent.getStartTime()!=null && !"".equals(secrecyCheckEvent.getStartTime()) ){
			        try {
                                        params.put("startTime", sdf.parse(secrecyCheckEvent.getStartTime()));
                                        hql.append(" and eventDate >= :startTime ");
                                } catch (ParseException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                }
			}
			if( secrecyCheckEvent.getEndTime()!=null && !"".equals(secrecyCheckEvent.getEndTime()) ){
                                try {
                                        params.put("endTime", sdf.parse(secrecyCheckEvent.getEndTime()));
                                        hql.append(" and eventDate <= :endTime ");
                                } catch (ParseException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                }
			}
		}

		List<SecrecyCheckEvent> returnList = new ArrayList<SecrecyCheckEvent>();

		if( psm==null ){
		        returnList = findList(hql.toString(), params);
		        dealDepartmentNames(returnList);
		}else{
		        returnList = findList(hql.toString(), params, psm);
		}
		return returnList;
	}

	public void dealDepartmentNames( List<SecrecyCheckEvent> dataList ){
	        if( dataList!=null && dataList.size()>0 ){
	                for( SecrecyCheckEvent sce : dataList ){
	                        StringBuilder departmentNames = new StringBuilder();
	                        if( sce!=null && sce.getCheckedDepartment()!=null ){
	                                int flag = 0;
	                                for( String id : sce.getCheckedDepartment().split(",") ){
	                                        flag ++;
	                                        Department department = this.get(id.trim(), Department.class);
	                                        if(department!=null){
	                                             departmentNames.append(department.getDepartmentName());
	                                             if( flag < sce.getCheckedDepartment().split(",").length ){
	                                                     departmentNames.append(",");
	                                             }
	                                        }
	                                }
	                        }
	                        sce.setCheckedDepartmentNames(departmentNames.toString());
	                }
	        }
	}

	public void updates(SecrecyCheckEvent secrecyCheckEvent,User user) {

		SecrecyCheckEvent tec = this.get(secrecyCheckEvent.getSecrecyCheckEventId());
		tec.setModifyPerson(user);
		tec.setModifyTime(new Date());
		tec.setOrganId(user.getOrgan());
		tec.setEventName(secrecyCheckEvent.getEventName());
		tec.setEventDescription(secrecyCheckEvent.getEventDescription());
		tec.setEventDate(secrecyCheckEvent.getEventDate());
		tec.setDisposeIdea(secrecyCheckEvent.getDisposeIdea());
		tec.setCheckCircs(secrecyCheckEvent.getCheckCircs());
		tec.setCheckMeans(secrecyCheckEvent.getCheckMeans());
		tec.setCheckedOrgan(secrecyCheckEvent.getCheckedOrgan());
		tec.setCheckedDepartment(secrecyCheckEvent.getCheckedDepartment());
		tec.setCheckedOrgan(secrecyCheckEvent.getCheckedOrgan());
		//tec.setCheckEventTables(secrecyCheckEvent.getCheckEventTables());
		// 调用父类的更新方法
		super.update(tec);
	}

	public SecrecyCheckEvent saves(SecrecyCheckEvent secrecyCheckEvent,User user) {
		secrecyCheckEvent.setCreatePerson(user);
		secrecyCheckEvent.setCreateTime(new Date());
		secrecyCheckEvent.setModifyPerson(user);
		secrecyCheckEvent.setModifyTime(new Date());
		secrecyCheckEvent.setOrganId(user
				.getOrgan());
		// 调用父类的更新方法
		return super.save(secrecyCheckEvent);
	}

	public void updateEvent(SecrecyCheckEvent secrecyCheckEvent, String tableIds,User user) {
		SecrecyCheckEvent sce=this.get(secrecyCheckEvent.getSecrecyCheckEventId());
		sce.setModifyPerson(user);
		sce.setModifyTime(new Date());
		sce.setEventName(secrecyCheckEvent.getEventName());
		sce.setEventDate(secrecyCheckEvent.getEventDate());
		sce.setCheckedOrgan(secrecyCheckEvent.getCheckedOrgan());
		sce.setCheckedDepartment(secrecyCheckEvent.getCheckedDepartment());
		sce.setEventDescription(secrecyCheckEvent.getEventDescription());
		sce.setDisposeIdea(secrecyCheckEvent.getDisposeIdea());
		sce.setCheckCircs(secrecyCheckEvent.getCheckCircs());
		sce.setCheckMeans(secrecyCheckEvent.getCheckMeans());
//		sce.setCheckCircs(sce.getCheckCircs());

		/*for(Object cet: sce.getCheckEventTables()){

			this.getPersistProxy().getOrmPersistence().delete(cet);
		}
		if(tableIds!=null){
		Set<CheckEventTable> tableSet=new HashSet<CheckEventTable>();

		String[] ids=tableIds.split(",");
		for(String id:ids){
			if(!id.equals("")){
				CheckTable checkTable=this.get(id, CheckTable.class);
				CheckEventTable checkEventTable=new CheckEventTable();
				checkEventTable.setSecrecyCheckEvent(sce);
				checkEventTable.setOrgan(this.get(sce.getCheckedOrgan(), Organ.class));
				checkEventTable.setCheckTable(checkTable);
				tableSet.add(checkEventTable);
			}
		}

		sce.setCheckEventTables(tableSet);
		}*/

		this.update(sce);
	}

	/**
	 * @description 查询所有保密检查事件
	 * @author 杨  成 2009-10-12 10:12
	 * @param PageSortModel psm
	 * @param RewardAndPenalty secrecyCheckEvent
	 * @return list
	 */
	@SuppressWarnings("unchecked")
	public List allListForEc(PageSortModel psm,SecrecyCheckEvent secrecyCheckEvent, String showType ,District district) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = null;

		hql = new StringBuffer("from SecrecyCheckEvent s where 1=1 ");
		if (secrecyCheckEvent != null) {
			if (secrecyCheckEvent.getEventName() != null
					&& !"".equals(secrecyCheckEvent.getEventName())) {
				hql.append(" and eventName like :eventName");
				params.put("eventName", "%" + secrecyCheckEvent.getEventName().trim() + "%");
			}
			if (secrecyCheckEvent.getCheckedOrgan() != null
					&& !"".equals(secrecyCheckEvent.getCheckedOrgan().getOrganName())) {
				hql.append(" and checkedOrgan.organName like :organName");
				params.put("organName", "%" + secrecyCheckEvent.getCheckedOrgan().getOrganName().trim() + "%");
			}
			if (secrecyCheckEvent.getStartTime() != null && !"".equals(secrecyCheckEvent.getStartTime())
					&& secrecyCheckEvent.getEndTime() != null && !"".equals(secrecyCheckEvent.getEndTime())) {
				try {
					SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
					Date dateStart = sf.parse(secrecyCheckEvent.getStartTime());
					Date dateEnd = sf.parse(secrecyCheckEvent.getEndTime());
					hql.append(" and  s.eventDate >= :startTime and s.eventDate <= :endTime");
					params.put("startTime", dateStart);
					params.put("endTime", dateEnd);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (secrecyCheckEvent.getStartTime() != null && !"".equals(secrecyCheckEvent.getStartTime())) {
				try {
					SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
					Date dateStart = sf.parse(secrecyCheckEvent.getStartTime());
					hql.append(" and  s.eventDate >= :startTime ");
					params.put("startTime", dateStart);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (secrecyCheckEvent.getEndTime() != null && !"".equals(secrecyCheckEvent.getEndTime())) {


				try {
					SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
					Date dateEnd = sf.parse(secrecyCheckEvent.getEndTime());
					hql.append(" and  s.eventDate <= :endTime ");
					params.put("endTime", dateEnd);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		if (showType != null && district != null
				&& district.getDistrictCode() != null) {
			if ("0".equals(showType)) {
				// 这里查看下级
				District d = this.get(district.getDistrictCode(),
						District.class);
				hql.append(" and s.organId.district.layer like :layer ");
				params.put("layer", d.getLayer() + "%");
			} else if ("1".equals(showType)) {
				// 这里查看本级
				hql.append(" and s.organId.district.districtCode = :districtCode ");
				params.put("districtCode", district.getDistrictCode());
			}
		}

		if (psm != null) {
			if (psm.getProp() != null && !psm.getProp().equals("null")) {
				hql.append(" order by ");
				hql.append(psm.getProp());
				hql.append(" ");
				hql.append(psm.getOrder());
			}else{
				hql.append(" order by eventDate desc ");
			}
		}
		return (List<SecrecyCheckEvent>) findList(hql.toString(),params, psm);
	}
}
