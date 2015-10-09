package com.cdthgk.devicemgr.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.devicemgr.service.DeviceMgrService;
import com.cdthgk.devicemgr.vo.EquipmentLoan;
import com.cdthgk.devicemgr.vo.EquipmentTrash;
import com.cdthgk.devicemgr.vo.SecrecyEquipment;
import com.cdthgk.devicemgr.vo.SecrecyEquipmentCategory;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.permission.user.domain.User;
import com.thgk.platform.syslog.service.invoke.imp.annotation.ExcuteType;
import com.thgk.platform.syslog.service.invoke.imp.annotation.SysLog;
import com.thgk.platform.syslog.service.invoke.imp.annotation.SysLogs;

import ec.common.PageSortModel;

/**
 * @description 保密技术设备管理接口实现
 * @author 熊  超 2009-10-12 9:30
 * @modify 彭  维 2010-08-19 9:00 整理注释
 */
@SysLogs(
	{
		@SysLog(method="update",methodParamsSize=1,description="修改保密技术设备{0}|name",excute_type=ExcuteType.UPDATE),
		@SysLog(method="deleteBatchWithId",methodParamsSize=1,description="删除保密设备",excute_type=ExcuteType.DELETE),
		@SysLog(method="save",methodParamsSize=1,description="添加保密技术设备{0}|name",excute_type=ExcuteType.ADD),
		@SysLog(method="saveTrash",methodParamsSize=1,description="申请保密技术设备{0}报废|secrecyEquipment.name",excute_type=ExcuteType.ADD),
		@SysLog(method="updateTrash",methodParamsSize=1,description="修改保密技术设备{0}报废申请|secrecyEquipment.name",excute_type=ExcuteType.UPDATE),
		@SysLog(method="deleteBatchTrash",methodParamsSize=1,description="删除保密技术设备{0}报废申请|secrecyEquipment.name",excute_type=ExcuteType.DELETE),
		@SysLog(method="saveLoan",methodParamsSize=1,description="申请保密技术设备{0}借用|secrecyEquipment.name",excute_type=ExcuteType.ADD),
		@SysLog(method="updateLoan",methodParamsSize=1,description="修改保密技术设备{0}借用申请|secrecyEquipment.name",excute_type=ExcuteType.UPDATE),
		@SysLog(method="deleteBatchLoan",methodParamsSize=1,description="删除保密技术设备{0}借用申请|secrecyEquipment.name",excute_type=ExcuteType.DELETE),

	}
)

@SuppressWarnings("all")
public class DeviceMgrServiceImpl extends BmpServiceImpl<SecrecyEquipment, String>implements DeviceMgrService {

	/**
	 * @description 查询列表
	 * @author 牟远洋 2013 04 08 11:01:56
	 * @param PageSortModel psm
	 * @param MessageIssue messageIssue
	 * @return List<MessageIssue>
	 */
	public List<SecrecyEquipment> getSecrecyEquipmentList(PageSortModel psm, SecrecyEquipmentCategory secrecyEquipmentCategory, SecrecyEquipment secrecyEquipment, Organ organ){

		StringBuilder hql = new StringBuilder("FROM SecrecyEquipment se where 1=1 ");
		        if (secrecyEquipmentCategory.getParentSecrecyEquipmentCategory()!=null) {
		                hql.append("and  se.secrecyEquipmentCategory.secrecyEquipmentCategoryId='" + secrecyEquipmentCategory.getSecrecyEquipmentCategoryId() + "'");
                        }
		Map<String, Object> params = new HashMap<String, Object>();

		// 判断当前登录人员单位是否是保密局
		if(organ.getOrganName().indexOf("国家保密局") == -1){
			hql.append(" and se.organ.organId = :organId");
			params.put("organId", organ.getOrganId());
		}
		// 查询条件
		if(secrecyEquipment != null){
			if (secrecyEquipment.getName() != null && !"".equals(secrecyEquipment.getName())) {
				hql.append(" and se.name like :name");
				params.put("name", "%" + secrecyEquipment.getName() + "%");
			}
			if (secrecyEquipment.getTypeCode() != null && !"".equals(secrecyEquipment.getTypeCode())) {
				hql.append(" and se.typeCode like :typeCode");
				params.put("typeCode", "%" + secrecyEquipment.getTypeCode() + "%");
			}
			if (secrecyEquipment.getOrgan() != null && !"".equals(secrecyEquipment.getOrgan().getOrganName())) {
				hql.append(" and se.organ.organName like :organName");
				params.put("organName", "%" + secrecyEquipment.getOrgan().getOrganName() + "%");
			}
		}

		hql.append(" order by se.createTime desc");

		return (List<SecrecyEquipment>)findList(hql.toString(), params, psm);
	}


	public List<SecrecyEquipment> listForEc(PageSortModel psm,SecrecyEquipment secrecyEquipment,User user) {
		StringBuilder hql = new StringBuilder("FROM SecrecyEquipment s ");
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder conditions = new StringBuilder();
		conditions.append(" WHERE s.organ =:organ ");
		params.put("organ", user.getUserInfo().getOrgan());
		if (secrecyEquipment != null) {
			if(secrecyEquipment.getSecrecyEquipmentCategory()!=null){
				if(!secrecyEquipment.getSecrecyEquipmentCategory().getSecrecyEquipmentCategoryId().equals("1")){
					conditions.append(" and s.secrecyEquipmentCategory.secrecyEquipmentCategoryId = '"+secrecyEquipment.getSecrecyEquipmentCategory().getSecrecyEquipmentCategoryId()+ "'");
				}
			}
			if (secrecyEquipment.getName() != null
					&& !"".equals(secrecyEquipment.getName())) {
				conditions.append(" AND name LIKE :name ");
				params.put("name", "%" + secrecyEquipment.getName() + "%");
			}
			if (secrecyEquipment.getStatus() != null
					&& !"".equals(secrecyEquipment.getStatus())) {
				conditions.append(" AND status =:status ");
				params.put("status", secrecyEquipment.getStatus());
			}
			if(secrecyEquipment.getSecrecyEquipmentId()!=null&&!"".equals(secrecyEquipment.getSecrecyEquipmentId())){
				conditions.append(" AND secrecyEquipmentId =:secrecyEquipmentId ");
				params.put("secrecyEquipmentId", secrecyEquipment.getSecrecyEquipmentId());
			}
		}
		hql.append(conditions.toString());
		hql.append(" AND asset.assetId is not null ");
		hql.append(" ORDER BY ");

		String prop = psm.getProp();
		String sort = psm.getOrder();
		if (prop != null) {
			prop = prop.replace("__", ".");
			hql.append(prop);
		} else {
			hql.append(" s.createTime ");
		}
		if ("asc".equalsIgnoreCase(sort)) {
			hql.append(" asc");
		} else {
			hql.append(" desc");
		}
		return (List<SecrecyEquipment>) findList(hql.toString(), params, psm);
	}

	/*@Override
	public SecrecyEquipment save(SecrecyEquipment secrecyEquipment) {
		secrecyEquipment.setCreatePerson(getCurrentUser());
		secrecyEquipment.setCreateTime(new Date());
		secrecyEquipment.setDepartment(getCurrentUser().getUserInfo().getDepartment());
		secrecyEquipment.setOrgan(getCurrentUser().getUserInfo().getOrgan());
		super.save(secrecyEquipment);
	}*/

	/*@Override
	public SecrecyEquipment update(SecrecyEquipment secrecyEquipment) {
		SecrecyEquipment updateSecrecyEquipment = this.get(secrecyEquipment
				.getSecrecyEquipmentId());
		updateSecrecyEquipment.setName(secrecyEquipment.getName());
		updateSecrecyEquipment.setTypeCode(secrecyEquipment.getTypeCode());
		updateSecrecyEquipment.setCategoryCode(secrecyEquipment
				.getCategoryCode());
		updateSecrecyEquipment.setPrice(secrecyEquipment.getPrice());
		updateSecrecyEquipment.setBuyTime(secrecyEquipment.getBuyTime());
		updateSecrecyEquipment.setAtPlace(secrecyEquipment.getAtPlace());
		super.update(updateSecrecyEquipment);
	}*/

	public List<EquipmentTrash> listForEc(PageSortModel psm,
			EquipmentTrash equipmentTrash) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("FROM  EquipmentTrash pg ");
		StringBuilder conditions = new StringBuilder();

		if (equipmentTrash != null) {
			if (equipmentTrash.getSecrecyEquipment() != null
					&& StringUtils.isNotBlank(equipmentTrash.getSecrecyEquipment().getName())) {
				conditions.append("pg.secrecyEquipment.name like :name ");
				params.put("name", "%" + equipmentTrash.getSecrecyEquipment().getName() + "%");
			}
			if (equipmentTrash.getSecrecyEquipment() != null
					&& StringUtils.isNotBlank(equipmentTrash.getSecrecyEquipment().getSecrecyEquipmentId())) {
				conditions.append("pg.secrecyEquipment.secrecyEquipmentId =:secrecyEquipmentId ");
				params.put("secrecyEquipmentId", "%" + equipmentTrash.getSecrecyEquipment().getSecrecyEquipmentId() + "%");
			}
			if(equipmentTrash.getEquipmentTrashId()!=null&&!"".equals(equipmentTrash.getEquipmentTrashId())){
				conditions.append("pg.equipmentTrashId =:equipmentTrashId ");
				params.put("equipmentTrashId", equipmentTrash.getEquipmentTrashId());

			}
		}
		if (conditions.length() > 0) {
			hql.append(" WHERE ").append(conditions.toString());
		}
		hql.append("order by pg.createTime desc");
		return this.getPersistProxy().getOrmPersistence().findList(hql.toString(), params, psm);
	}

	public void saveTrash(EquipmentTrash equipmentTrash) {
		this.getPersistProxy().getOrmPersistence().persist(equipmentTrash);
	}

	public void updateTrash(EquipmentTrash equipmentTrash) {
		this.getPersistProxy().getOrmPersistence().merge(equipmentTrash);
	}

	public void deleteBatchTrash(List<String> ids) {
		List trashList=new ArrayList();
		List secrecyEquipmentList=new ArrayList();
		if(ids!=null && ids.size()>0){
			for(int i=0;i<ids.size();i++){
				EquipmentTrash et=this.get(ids.get(i), EquipmentTrash.class);
				SecrecyEquipment se=this.get(et.getSecrecyEquipment().getSecrecyEquipmentId(),SecrecyEquipment.class);
				se.setStatus(1);
				secrecyEquipmentList.add(se);
				trashList.add(et);
			}
		}
		getPersistProxy().getOrmPersistence().saveOrUpdateBatch(secrecyEquipmentList);
                getPersistProxy().getOrmPersistence().deleteBatch(trashList);
	}

	public List<EquipmentLoan> listForEc(PageSortModel psm,
			EquipmentLoan equipmentLoan) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("FROM  EquipmentLoan pg ");
		StringBuilder conditions = new StringBuilder();

		if (equipmentLoan != null) {
			if (equipmentLoan.getSecrecyEquipment() != null
					&& StringUtils.isNotBlank(equipmentLoan.getSecrecyEquipment().getName())) {
				conditions.append("pg.secrecyEquipment.name like :name ");
				params.put("name", "%" + equipmentLoan.getSecrecyEquipment().getName() + "%");
			}
			if (equipmentLoan.getSecrecyEquipment() != null
					&& StringUtils.isNotBlank(equipmentLoan.getSecrecyEquipment().getSecrecyEquipmentId())) {
				if(conditions.length()>0){
					conditions.append(" and ");
				}
				conditions.append("pg.secrecyEquipment.secrecyEquipmentId =:secrecyEquipmentId ");
				params.put("secrecyEquipmentId", equipmentLoan.getSecrecyEquipment().getSecrecyEquipmentId());
			}
		}
		if (conditions.length() > 0) {
			hql.append(" WHERE ").append(conditions.toString());
		}
		hql.append("order by pg.createTime desc");
		return this.getPersistProxy().getOrmPersistence().findList(hql.toString(), params, psm);
	}

	public void saveLoan(EquipmentLoan equipmentLoan) {
		this.getPersistProxy().getOrmPersistence().persist(equipmentLoan);
	}

	public void updateLoan(EquipmentLoan equipmentLoan) {
		this.getPersistProxy().getOrmPersistence().merge(equipmentLoan);
	}

	public void deleteBatchLoan(List<String> ids) {
		List loanList=new ArrayList();
		List secrecyEquipmentList=new ArrayList();
		if(ids!=null && ids.size()>0){
			for(int i=0;i<ids.size();i++){
				EquipmentLoan et=this.get(ids.get(i), EquipmentLoan.class);
				SecrecyEquipment se=this.get(et.getSecrecyEquipment().getSecrecyEquipmentId(),SecrecyEquipment.class);
				se.setStatus(0);
				secrecyEquipmentList.add(se);
				loanList.add(et);
			}
		}
//		getPersistProxy().getOrmPersistence().batchMerge(secrecyEquipmentList);

		getPersistProxy().getOrmPersistence().saveOrUpdateBatch(secrecyEquipmentList);
		getPersistProxy().getOrmPersistence().deleteBatch(loanList);
	}

	public List listForEcPass(PageSortModel psm, EquipmentLoan equipmentLoan) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("FROM  EquipmentLoan pg ");
		StringBuilder conditions = new StringBuilder();

		if (equipmentLoan != null) {
			if (equipmentLoan.getSecrecyEquipment() != null
					&& StringUtils.isNotBlank(equipmentLoan.getSecrecyEquipment().getName())) {
				conditions.append("pg.secrecyEquipment.name like :name ");
				params.put("name", "%" + equipmentLoan.getSecrecyEquipment().getName() + "%");
			}

			if (equipmentLoan.getSecrecyEquipment() != null
					&& StringUtils.isNotBlank(equipmentLoan.getSecrecyEquipment().getSecrecyEquipmentId())) {
				if(conditions.length()>0){
					conditions.append(" and ");
				}
				conditions.append("pg.secrecyEquipment.secrecyEquipmentId =:secrecyEquipmentId ");
				params.put("secrecyEquipmentId", equipmentLoan.getSecrecyEquipment().getSecrecyEquipmentId());
			}

		}
		if(conditions.length()>0){
			conditions.append(" and ");
		}
		conditions.append("pg.loanEndTime <=:currentDate and pg.status=0");
		Date currentDate = new Date(System.currentTimeMillis() + 24 * 60* 60 * 1000);
		params.put("currentDate", currentDate);

		if (conditions.length() > 0) {
			hql.append(" WHERE ").append(conditions.toString());
		}
		hql.append("order by pg.createTime desc");
		return (List<EquipmentLoan>)this.getPersistProxy().getOrmPersistence().findList(hql.toString(), params, psm);
	}

	public List listForEcAuditPass(PageSortModel psm,
			EquipmentLoan equipmentLoan) {
		Map<String, Object> params = new HashMap<String, Object>();
		//状态为借出或者归还
		StringBuilder hql = new StringBuilder("FROM  EquipmentLoan pg where pg.status =11 or pg.status=75");

		if (equipmentLoan != null) {
			if (equipmentLoan.getSecrecyEquipment() != null
					&& StringUtils.isNotBlank(equipmentLoan.getSecrecyEquipment().getName())) {
				hql.append(" and pg.secrecyEquipment.name like :name ");
				params.put("name", "%" + equipmentLoan.getSecrecyEquipment().getName() + "%");
			}

			if (equipmentLoan.getSecrecyEquipment() != null
					&& StringUtils.isNotBlank(equipmentLoan.getSecrecyEquipment().getSecrecyEquipmentId())) {

				hql.append(" and pg.secrecyEquipment.secrecyEquipmentId =:secrecyEquipmentId ");
				params.put("secrecyEquipmentId", equipmentLoan.getSecrecyEquipment().getSecrecyEquipmentId());
			}

		}

		hql.append("order by pg.createTime desc");
		return (List<EquipmentLoan>) this.getPersistProxy().getOrmPersistence().findList(hql.toString(), params, psm);
	}

	public List listForEcAudit(PageSortModel psm, EquipmentLoan equipmentLoan) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("FROM  EquipmentLoan pg ");
		StringBuilder conditions = new StringBuilder();

		if (equipmentLoan != null) {
			if (equipmentLoan.getSecrecyEquipment() != null
					&& StringUtils.isNotBlank(equipmentLoan.getSecrecyEquipment().getName())) {
				conditions.append("pg.secrecyEquipment.name like :name ");
				params.put("name", "%" + equipmentLoan.getSecrecyEquipment().getName() + "%");
			}

			if (equipmentLoan.getSecrecyEquipment() != null
					&& StringUtils.isNotBlank(equipmentLoan.getSecrecyEquipment().getSecrecyEquipmentId())) {
				if(conditions.length()>0){
					conditions.append(" and ");
				}
				conditions.append("pg.secrecyEquipment.secrecyEquipmentId =:secrecyEquipmentId ");
				params.put("secrecyEquipmentId", equipmentLoan.getSecrecyEquipment().getSecrecyEquipmentId());
			}

		}

		if(conditions.length()>0){
			conditions.append(" and ");
		}
		conditions.append(" pg.status=1 ");

		if (conditions.length() > 0) {
			hql.append(" WHERE ").append(conditions.toString());
		}
		hql.append("order by pg.createTime desc");
		return (List<EquipmentLoan>) this.getPersistProxy().getOrmPersistence().findList(hql.toString(), params, psm);
	}

	public List listForEcAudit1(PageSortModel psm, EquipmentLoan equipmentLoan) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("FROM  EquipmentLoan pg ");
		StringBuilder conditions = new StringBuilder();

		if (equipmentLoan != null) {
			if (equipmentLoan.getSecrecyEquipment() != null
					&& StringUtils.isNotBlank(equipmentLoan.getSecrecyEquipment().getName())) {
				conditions.append("pg.secrecyEquipment.name like :name ");
				params.put("name", "%" + equipmentLoan.getSecrecyEquipment().getName() + "%");
			}

			if (equipmentLoan.getSecrecyEquipment() != null
					&& StringUtils.isNotBlank(equipmentLoan.getSecrecyEquipment().getSecrecyEquipmentId())) {
				if(conditions.length()>0){
					conditions.append(" and ");
				}
				conditions.append("pg.secrecyEquipment.secrecyEquipmentId =:secrecyEquipmentId ");
				params.put("secrecyEquipmentId", equipmentLoan.getSecrecyEquipment().getSecrecyEquipmentId());
			}

		}

		if(conditions.length()>0){
			conditions.append(" and ");
		}
		conditions.append(" pg.status=15 ");

		if (conditions.length() > 0) {
			hql.append(" WHERE ").append(conditions.toString());
		}
		hql.append("order by pg.createTime desc");
		return (List<EquipmentLoan>)this.getPersistProxy().getOrmPersistence().findList(hql.toString(), params, psm);
	}

	public List listForEcAudit(PageSortModel psm, EquipmentTrash equipmentTrash) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("FROM  EquipmentTrash pg WHERE pg.status=1 ");
		StringBuilder conditions = new StringBuilder();

		if (equipmentTrash != null) {
			if (equipmentTrash.getSecrecyEquipment() != null
					&& StringUtils.isNotBlank(equipmentTrash.getSecrecyEquipment().getName())) {
				conditions.append(" and pg.secrecyEquipment.name like :name ");
				params.put("name", "%" + equipmentTrash.getSecrecyEquipment().getName() + "%");
			}
			if (equipmentTrash.getSecrecyEquipment() != null
					&& StringUtils.isNotBlank(equipmentTrash.getSecrecyEquipment().getSecrecyEquipmentId())) {
				conditions.append(" and pg.secrecyEquipment.secrecyEquipmentId =:secrecyEquipmentId ");
				params.put("secrecyEquipmentId", "%" + equipmentTrash.getSecrecyEquipment().getSecrecyEquipmentId() + "%");
			}
		}
		hql.append("order by pg.createTime desc");
		return (List<EquipmentTrash>) this.getPersistProxy().getOrmPersistence().findList(hql.toString(), params, psm);
	}

	public List listForEcAudit1(PageSortModel psm, EquipmentTrash equipmentTrash) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("FROM  EquipmentTrash pg WHERE pg.status=15 ");
		StringBuilder conditions = new StringBuilder();

		if (equipmentTrash != null) {
			if (equipmentTrash.getSecrecyEquipment() != null
					&& StringUtils.isNotBlank(equipmentTrash.getSecrecyEquipment().getName())) {
				conditions.append(" and pg.secrecyEquipment.name like :name ");
				params.put("name", "%" + equipmentTrash.getSecrecyEquipment().getName() + "%");
			}
			if (equipmentTrash.getSecrecyEquipment() != null
					&& StringUtils.isNotBlank(equipmentTrash.getSecrecyEquipment().getSecrecyEquipmentId())) {
				conditions.append(" and pg.secrecyEquipment.secrecyEquipmentId =:secrecyEquipmentId ");
				params.put("secrecyEquipmentId", "%" + equipmentTrash.getSecrecyEquipment().getSecrecyEquipmentId() + "%");
			}
		}
		hql.append("order by pg.createTime desc");
		return (List<EquipmentTrash>)this.getPersistProxy().getOrmPersistence().findList(hql.toString(), params, psm);
	}

	/**
	 * @description 得到设备列表
	 * @author 熊  超 2009-10-12 9:30
	 * @param null
	 * @return List<Map<String,String>>
	 */
	public List<Map<String,String>> getSecrecyEquipmentList(User user){
		List<Map<String,String>> returnList = new ArrayList<Map<String,String>>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("organ", user.getUserInfo().getOrgan());
		//只查出状态正常的设备
		String hql = "from SecrecyEquipment s where s.organ=:organ and s.status=1";

		List<SecrecyEquipment> list =  (List<SecrecyEquipment>)this.findList(hql, params);
		for(SecrecyEquipment s :list){
			Map<String, String> map = new HashMap<String, String>();
			map.put("id", s.getSecrecyEquipmentId());
			map.put("name", s.getName());
			returnList.add(map);
		}
		return returnList;
	}

	/**
	 *
	 * <p>
	 * 删除技术设备信息
	 * </p>
	 * <p>
	 * 创建人 梁富胜 创建时间 Oct 12, 2010 - 3:24:33 PM
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @param ids
	 * @return
	 */
	public String deleteBatchs(List<String> ids){
		//删除技术设备信息
		//this.deleteBatchWithId(ids);
		this.deleteBatchIdList(ids);
		/*for (String id:ids) {
			this.warningService.deleteBatchWithId(this.warningService.findWarningById(id));
		}*/
		return "删除设备成功";
	}


        @Override
        public List<SecrecyEquipment> listById(String hql, PageSortModel psm) {
                //String hql = "from SecrecyEquipment se where se.secrecyEquipmentCategory.secrecyEquipmentCategoryId='" + secrecyEquipmentCategory.getSecrecyEquipmentCategoryId() + "'";
                Map<String, Object> params = new HashMap<String, Object>();
                return (List<SecrecyEquipment>)this.getPersistProxy().getOrmPersistence().findList(hql.toString(), params, psm);
        }

	/*private WarningService warningService;//依赖注入

	public WarningService getWarningService() {
		return warningService;
	}

	public void setWarningService(WarningService warningService) {
		this.warningService = warningService;
	}*/

}