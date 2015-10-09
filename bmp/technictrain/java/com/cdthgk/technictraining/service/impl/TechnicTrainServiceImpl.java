package com.cdthgk.technictraining.service.impl;

import java.text.ParseException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.permission.user.domain.User;
import com.cdthgk.technictraining.dto.TechnicTrainDto;
import com.cdthgk.technictraining.service.TechnicTrainService;
import com.cdthgk.technictraining.vo.NotPresenceOrgan;
import com.cdthgk.technictraining.vo.PresenceOrgan;
import com.cdthgk.technictraining.vo.TechnicTrain;
import com.thgk.platform.syslog.service.invoke.imp.annotation.ExcuteType;
import com.thgk.platform.syslog.service.invoke.imp.annotation.SysLog;
import com.thgk.platform.syslog.service.invoke.imp.annotation.SysLogs;

import ec.common.PageSortModel;

/**
 * @description 保密技术培训Service实现类
 * @author 刘  舜 2010 02 02 12:46:30
 * @modify 陈文聪 2010 8 17 02:36:55
 */

@SuppressWarnings("all")
@SysLogs(
		{
			@SysLog(method="update",methodParamsSize=1,description="修改技术培训信息{0}|trainingTitle",excute_type=ExcuteType.UPDATE),
			@SysLog(method="delete",methodParamsSize=1,description="删除技术培训信息{0}|trainingTitle",excute_type=ExcuteType.DELETE),
			@SysLog(method="save",methodParamsSize=1,description="添加技术培训信息{0}|trainingTitle",excute_type=ExcuteType.ADD),
		}
)
public class TechnicTrainServiceImpl extends BmpServiceImpl<TechnicTrain, String>
		implements TechnicTrainService {

	/**
	 * @description 列表分页
	 * @author 刘  舜 2010 02 02 12:46:30
	 * @modify 陈文聪 2010 8 17 02:32:22 修改注释格式.
	 * @param PageSortModel pageSortModel
	 * @param TechnicTrainDto technicTrainDto
	 * @throws ParseException
	 * @return List<TechnicTrain>
	 */
	@SuppressWarnings("unchecked")
	public List<TechnicTrain> getPageList(PageSortModel pageSortModel,
			TechnicTrainDto technicTrainDto ,User user) throws ParseException {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("from TechnicTrain t where t.organ.organId=:organId");
		params.put("organId", user.getOrgan().getOrganId());
		if (technicTrainDto != null
				&& technicTrainDto.getTechnicTrain() != null
				&& technicTrainDto.getTechnicTrain().getTrainingTitle()!=null
				&& !"".equals(technicTrainDto.getTechnicTrain()
						.getTrainingTitle())) {
			hql.append(" and t.trainingTitle like:trainingTitle");
			params.put("trainingTitle", "%"+technicTrainDto.getTechnicTrain()
						.getTrainingTitle()+"%");
		}
		hql.append(" order by trainingDate desc");
		List<TechnicTrain> list = (List<TechnicTrain>) this.findList(hql.toString(), params, pageSortModel);
		return list;

	}

	/**
	 * @description 列表分页(所有技术培训)
	 * @author 刘  舜 2010 02 02 12:46:30
	 * @modify 陈文聪 2010 8 17 02:32:55 修改注释格式.
	 * @param PageSortModel pageSortModel
	 * @param TechnicTrainDto technicTrainDto
	 * @throws ParseException
	 * @return List<TechnicTrain>
	 */
	public List<TechnicTrain> getPageAllList(PageSortModel pageSortModel,
			TechnicTrainDto technicTrainDto,String showType,District district) throws ParseException {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = null;
		hql = new StringBuffer("from TechnicTrain t where 1=1 ");
		if (technicTrainDto != null){
			if(technicTrainDto.getTechnicTrain() != null
					&& technicTrainDto.getTechnicTrain().getTrainingTitle()!=null
					&& !"".equals(technicTrainDto.getTechnicTrain().getTrainingTitle())) {
				hql.append(" and t.trainingTitle like :trainingTitle");
				params.put("trainingTitle", "%"+technicTrainDto.getTechnicTrain()
							.getTrainingTitle()+"%");

			}
			if(technicTrainDto.getOrgan() != null && technicTrainDto.getOrgan().getOrganName()!=null
					&& !"".equals(technicTrainDto.getOrgan().getOrganName())) {
				hql.append(" and t.organ.organName like :organName");
				params.put("organName", "%"+technicTrainDto.getOrgan().getOrganName()+"%");

			}
		}

		if (showType != null && district != null
				&& district.getDistrictCode() != null) {
			if ("0".equals(showType)) {
				// 这里查看下级
				District d = this.get(district.getDistrictCode(),
						District.class);
				hql.append(" and t.organ.district.layer like :layer ");
				params.put("layer", d.getLayer() + "%");
			} else if ("1".equals(showType)) {
				// 这里查看本级
				hql.append(" and t.organ.district.districtCode = :districtCode ");
				params.put("districtCode", district.getDistrictCode());
			}
		}

		hql.append(" order by t.trainingDate desc");
		List<TechnicTrain> list = (List<TechnicTrain>) this.findList(hql.toString(), params, pageSortModel);
		return list;

	}
        /**
	 * @description 保存保密技术培训
	 * @author 刘  舜 2010 02 02 12:46:30
	 * @modify 陈文聪 2010 8 17 02:33:20 修改注释格式.
	 * @param TechnicTrain technicTrain
	 * @param String organs
	 * @param String notorgans
	 * @return void
	 */
	public void savePresenceBatch(TechnicTrain technicTrain,List<Organ> organs ,List<Organ>  notorgans) {
		Set<PresenceOrgan> organSet = new HashSet<PresenceOrgan>();

		// 保存单位
		if (organs != null ) {
			for (Organ organ : organs) {
				PresenceOrgan presenceOrgan = new PresenceOrgan();
				presenceOrgan.setOrgan(organ);
				presenceOrgan.setTechnicTraining(technicTrain);
				organSet.add(presenceOrgan);
			}
			technicTrain.setPresenceOrgans(organSet);
		}

		Set<NotPresenceOrgan> notorganSet = new HashSet<NotPresenceOrgan>();

		// 保存单位
		if (notorgans != null) {
			for (Organ organ :  notorgans) {
				NotPresenceOrgan notPresenceOrgan = new NotPresenceOrgan();
				notPresenceOrgan.setOrgan(organ);
				notPresenceOrgan.setTechnicTraining(technicTrain);
				notorganSet.add(notPresenceOrgan);
			}
			technicTrain.setNotPresenceOrgans(notorganSet);
		}

		this.save(technicTrain);
	}

	/**
	 * @description 更新保密技术培训信息
	 * @author 刘  舜 2010 02 02 12:46:30
	 * @modify 陈文聪 2010 8 17 02:33:50 修改注释格式.
	 * @param TechnicTrain editTechnicTrain
	 * @param String organs
	 * @param String notorgans
	 * @return void
	 */
	public TechnicTrain updatePresenceBatch(TechnicTrain editTechnicTrain,
			String organs, String notorgans) {
		TechnicTrain tt = get(editTechnicTrain.getTechnicTrainingId());
		tt.setPresenceOrgans(null);
		tt.setNotPresenceOrgans(null);
		getPersistProxy().getOrmPersistence().update(tt);

		Set<PresenceOrgan> organSet = new HashSet<PresenceOrgan>();
		// 保存单位
		if (organs != null && !organs.equals("")) {
			String[] organsList = organs.split(",");
			for (String organId : organsList) {
				PresenceOrgan presenceOrgan = new PresenceOrgan();
				Organ organ = new Organ();
				organ.setOrganId(organId);
				presenceOrgan.setOrgan(organ);
				presenceOrgan.setTechnicTraining(tt);
				organSet.add(presenceOrgan);
			}
		}


		Set<NotPresenceOrgan> notorganSet = new HashSet<NotPresenceOrgan>();
		// 保存单位
		if (notorgans != null && !notorgans.equals("")) {
			String[] organsList = notorgans.split(",");
			for (String organId : organsList) {
				NotPresenceOrgan notPresenceOrgan = new NotPresenceOrgan();
				Organ organ = new Organ();
				organ.setOrganId(organId);
				notPresenceOrgan.setOrgan(organ);
				notPresenceOrgan.setTechnicTraining(tt);
				notorganSet.add(notPresenceOrgan);
			}
		}
		tt.setPresenceOrgans(organSet);
		tt.setNotPresenceOrgans(notorganSet);
		getPersistProxy().getOrmPersistence().update(tt);
		return tt;
	}
}
