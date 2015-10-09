
package com.cdthgk.bmp.log.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.log.service.BusinessLogService;
import com.cdthgk.bmp.log.util.HibernateConfigurationHelper;
import com.cdthgk.bmp.log.vo.BusinessLog;
import com.cdthgk.bmp.log.vo.BusinessLogContent;
import com.cdthgk.common.lang.StringUtils;
import com.cdthgk.platform.base.service.GenericServiceTemplate;
import com.cdthgk.platform.permission.user.domain.User;
import com.cdthgk.standard.asserts.AssertStandardApp;

import ec.common.PageSortModel;

/**
 * <p>
 * 类的说明放这里
 * </p>
 * <p>
 * 创建时间 2014-8-12 - 下午1:00:04
 * </p>
 * <blockquote>
 * <h4>历史修改记录</h4>
 * <ul>
 * <li>修改人 修改时间 修改描述
 * </ul>
 * </blockquote>
 * <p>
 * copyright cdthgk 2010-2011, all rights reserved.
 * </p>
 *
 * @author 陶汇源
 * @author cdthgk r&d
 * @since 1.0
 * @version 1.0
 */
public class BusinessLogServiceImpl extends GenericServiceTemplate<BusinessLog, String> implements BusinessLogService {


	/**
	 * {@inheritDoc}
	 */
	@Override
	public BusinessLog save(BusinessLog log) {
		AssertStandardApp.isNotEmpty(log.getBusinessName(), "业务表名称[business_name]不能为空");
		AssertStandardApp.isNotEmpty(log.getTableName(), "业务表[table_name]不能为空");
		AssertStandardApp.isNotEmpty(log.getPrimaryKey(), "业务Id[primary_key]不能为空");
		AssertStandardApp.isNotEmpty(log.getOperateType(), "操作类型[operate_type]不能为空");
		AssertStandardApp.isNotEmpty(log.getOperateUser(), "操作人员[operate_user_id]不能为空");
		AssertStandardApp.isNotEmpty(log.getOperateOrgan(), "操作单位[operate_organ_id]不能为空");
		AssertStandardApp.isNotEmpty(log.getOperateTime(), "操作时间[operate_time]不能为空");
		getPersistProxy().getOrmPersistence().getHibernateTemplate().save(log);
		return log;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void saveAddBusinessLogByModule(User currentUser, BusinessLog log, Object object){
		log.setOperateType(BusinessLog.OPERATE_TPYE_ADD);
		log.setOperateOrgan(currentUser.getOrgan());
		log.setOperateTime(new Date());
		log.setOperateUser(currentUser);
		log.setTableName(HibernateConfigurationHelper.getTableName(object));
		save(log);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void saveEditBusinessLogByModule(User currentUser, BusinessLog log, Object objLater, Object objBefore){
		log.setOperateType(BusinessLog.OPERATE_TPYE_EDIT);
		log.setOperateOrgan(currentUser.getOrgan());
		log.setOperateTime(new Date());
		log.setOperateUser(currentUser);
		log.setTableName(HibernateConfigurationHelper.getTableName(objLater));
		List<BusinessLogContent> logContentList = HibernateConfigurationHelper.getBusinessLogContent(objLater, objBefore, log);
		log.setLogContentSet(new HashSet<BusinessLogContent>(logContentList));
		save(log);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void saveDelBusinessLogByModule(User currentUser, BusinessLog log, Object object){
		log.setOperateType(BusinessLog.OPERATE_TPYE_DELETE);
		log.setOperateOrgan(currentUser.getOrgan());
		log.setOperateTime(new Date());
		log.setOperateUser(currentUser);
		log.setTableName(HibernateConfigurationHelper.getTableName(object));
		save(log);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<BusinessLog> getPageList(PageSortModel<BusinessLog> psm, BusinessLog businessLog, User currentUser,
			String startTime, String endTime, String userName) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("from BusinessLog log where 1=1 ");
		// 菜单区分
		// ADMIN查看所有
		if (!currentUser.getUserId().equals("1")){
			if(currentUser.getOrgan().getOrganType().ordinal() == 1) {
				// 保密局1
				hql.append(" and log.operateOrgan.district.districtCode = :districtCode ");
				params.put("districtCode", currentUser.getOrgan().getDistrict().getDistrictCode());
			} else {
				// 机关单位2 未设置0
				hql.append(" and log.operateOrgan.organId = :organId ");
				params.put("organId", currentUser.getOrgan().getOrganId());
			}
		}


		// 查询
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if (StringUtils.isNotEmpty(startTime) && StringUtils.isNotEmpty(endTime)) {
				hql.append(" and (log.operateTime >= :startTime and log.operateTime <= :endTime )");
				params.put("startTime", sdf.parse(startTime));
				//有结束时间时对结束时间进行限定（XX日23时59分59秒）
				long x = 24*60*60*1000-1;
				params.put("endTime", new Date(sdf.parse(endTime).getTime() + x));
			} else if (StringUtils.isNotEmpty(startTime)) {
				// 查询条件只有开始时间，表示日程时间段内有这个时间点的就行
				hql.append(" and (log.operateTime >= :startTime)");
				params.put("startTime", sdf.parse(startTime));
			} else if (StringUtils.isNotEmpty(endTime)) {
				// 查询条件只有结束时间，表示日程时间段内有这个时间点的就行
				hql.append(" and (log.operateTime <= :endTime)");
				//有结束时间时对结束时间进行限定（XX日23时59分59秒）
				long x = 24*60*60*1000-1;
				params.put("endTime", new Date(sdf.parse(endTime).getTime() + x));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if(businessLog != null){
			if (businessLog.getOperateType() != null) {
				hql.append(" and log.operateType = :operateType");
				params.put("operateType", businessLog.getOperateType());
			}
			if (StringUtils.isNotEmpty(businessLog.getOperateUser().getUserInfo().getName())) {
				hql.append(" and log.operateUser.userInfo.name like :userInfoName");
				params.put("userInfoName", "%" + businessLog.getOperateUser().getUserInfo().getName() + "%");
			}
			if (StringUtils.isNotEmpty(businessLog.getOperateOrgan().getOrganName())) {
				hql.append(" and log.operateOrgan.organName like :organName");
				params.put("organName", "%" + businessLog.getOperateOrgan().getOrganName() + "%");
			}
		}
		hql.append(" order by log.operateTime desc");
		return findList(hql.toString(), params, psm);
	}
}
