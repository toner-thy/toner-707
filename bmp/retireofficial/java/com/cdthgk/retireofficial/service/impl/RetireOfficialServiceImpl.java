package com.cdthgk.retireofficial.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.permission.user.domain.User;
import com.cdthgk.retireofficial.service.RetireOfficialService;
import com.cdthgk.retireofficial.vo.RetireOfficial;
import com.thgk.platform.syslog.service.invoke.imp.annotation.ExcuteType;
import com.thgk.platform.syslog.service.invoke.imp.annotation.SysLog;
import com.thgk.platform.syslog.service.invoke.imp.annotation.SysLogs;

import ec.common.PageSortModel;

/**
 * @description 离退休Service.
 * @author 王欢 2009 10 21 12:34:56
 * @modify 陈文聪 2010 8 18 02:58:17 修改注释格式
 */
@SuppressWarnings("all")
@SysLogs(
		{
			@SysLog(method="update",methodParamsSize=1,description="修改{0}的离退休档案|name",excute_type=ExcuteType.UPDATE),
			@SysLog(method="deleteBatchWithId",methodParamsSize=1,description="删除{0}的离退休档案|String",excute_type=ExcuteType.DELETE),
			@SysLog(method="save",methodParamsSize=1,description="添加{0}的离退休档案|name",excute_type=ExcuteType.ADD)
		}
)
public class RetireOfficialServiceImpl extends BmpServiceImpl<RetireOfficial, String> implements RetireOfficialService{

	/**
	 *
	 * @author 王欢 2009 10 21 12:34:56
	 * @modify 陈文聪 2010 8 18 03:02:23 修改注释格式.
	 * @param PageSortModel psm
	 * @param RetireOfficial retireOfficial
	 * @return List<RetireOfficial>
	 */
	public List<RetireOfficial> listForEc(PageSortModel psm, RetireOfficial retireOfficial, Date startTime, Date endTime,User user) {

		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("FROM RetireOfficial where 1=1 ");
		Organ organ = user.getUserInfo().getOrgan();
		hql.append(" and organ.organId = :organId ");
		params.put("organId", organ.getOrganId());
		if(retireOfficial!=null){
			if(StringUtils.isNotBlank(retireOfficial.getName())){
				hql.append(" and name like :name");
				params.put("name", "%"+retireOfficial.getName()+"%");
			}
			if(StringUtils.isNotBlank(retireOfficial.getNativePlace())){
				hql.append(" and nativePlace like :nativePlace");
				params.put("nativePlace", "%"+retireOfficial.getNativePlace()+"%");
			}
		}

		// 时间段 开始时间
		if (startTime != null) {
			hql.append(" and retireTime >= :startTime");
			params.put("startTime", startTime);
		}
		// 时间段 结束时间
		if (endTime != null) {
			hql.append(" and retireTime < :endTime");
			params.put("endTime", new Date(endTime.getTime() + 24 * 60 * 60 * 1000));
		}

		if (psm != null) {
			if (psm.getProp() != null && !psm.getProp().equals("null")) {
				hql.append(" order by ");
				hql.append(psm.getProp());
				hql.append(" ");
				hql.append(psm.getOrder());
			}else{
				hql.append(" order by createTime desc");
			}
		}
		return (List<RetireOfficial>)findList(hql.toString(), params, psm);
	}
}

