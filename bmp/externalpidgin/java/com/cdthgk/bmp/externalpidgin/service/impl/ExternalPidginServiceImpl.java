package com.cdthgk.bmp.externalpidgin.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

//import com.thgk.bmp.externalpidgin.dto.ExternalPidginDto;
//import com.thgk.bmp.externalpidgin.dto.ExternalPidginListDto;
//import com.thgk.bmp.externalpidgin.service.ExternalPidginService;
//import com.thgk.bmp.externalpidgin.vo.ExternalPidgin;
//import com.thgk.bmp.keysection.vo.Section;
//import com.thgk.bmp.secrecyperson.service.impl.SecrecyPersonServiceImpl;
//import com.thgk.bmp.secrecyperson.vo.SecrecyPerson;
//import com.thgk.platform.core.GlobalSysInfo;
//import com.thgk.platform.district.vo.District;
//import com.thgk.platform.organ.vo.Organ;
import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.bmp.externalpidgin.dto.ExternalPidginDto;
import com.cdthgk.bmp.externalpidgin.dto.ExternalPidginListDto;
import com.cdthgk.bmp.externalpidgin.service.ExternalPidginService;
import com.cdthgk.bmp.externalpidgin.vo.ExternalPidgin;
import com.cdthgk.bmp.secrecyperson.service.impl.SecrecyPersonServiceImpl;
import com.cdthgk.bmp.secrecyperson.vo.SecrecyPerson;
import com.cdthgk.exchange.vo.ReportableListDto;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.permission.user.domain.User;
import com.thgk.platform.syslog.service.invoke.imp.annotation.ExcuteType;
import com.thgk.platform.syslog.service.invoke.imp.annotation.SysLog;
import com.thgk.platform.syslog.service.invoke.imp.annotation.SysLogs;
//import com.thgk.platform.user.vo.User;
//import com.thgk.sys.base.service.ServiceReportableImpl;
//import com.thgk.sys.base.vo.ReportableListDto;

import ec.common.PageSortModel;

/**
 * @description 涉密涉外活动Service实现类.
 * @author 王欢 2010 1 7 12:34:56
 * @modify 陈文聪 2010 8 19 02:10:40 修改注释格式
 */

@SuppressWarnings("all")
@SysLogs({
		@SysLog(method = "update", methodParamsSize = 1, description = "修改涉密涉外活动管理编号{0}|externalPidginNo", excute_type = ExcuteType.UPDATE),
		@SysLog(method = "delete", methodParamsSize = 1, description = "删除涉密涉外活动管理编号{0}|externalPidginNo", excute_type = ExcuteType.DELETE),
		@SysLog(method = "save", methodParamsSize = 1, description = "添加涉密涉外活动管理编号{0}|externalPidginNo", excute_type = ExcuteType.ADD) })
public class ExternalPidginServiceImpl extends BmpServiceImpl<ExternalPidgin, String> implements ExternalPidginService {

	private static final Log LOG = LogFactory
			.getLog(ExternalPidginServiceImpl.class);

	/**
	 * @description 查询列表
	 * @author 王欢 2010 1 7 12:34:56
	 * @modify 陈文聪 2010 8 19 02:15:02 修改注释格式.
	 * @param PageSortModel
	 *            psm
	 * @param ExternalPidginDto
	 *            externalPidginDto
	 * @throws ParseException
	 * @return List<ExternalPidgin>
	 */
	public List<ExternalPidgin> listForEc(PageSortModel psm,
			ExternalPidginDto externalPidginDto, User currentUser) throws ParseException {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("FROM ExternalPidgin where 1=1 ");
		String organId = currentUser.getUserInfo().getOrgan().getOrganId();
		hql.append(" and organ.organId = :organId ");
		params.put("organId", organId);
		if (externalPidginDto != null) {
			if (StringUtils
					.isNotBlank(externalPidginDto.getEternalPidginName())) {
				hql.append(" and eternalPidginName like :eternalPidginName");
				params.put("eternalPidginName",
						"%" + externalPidginDto.getEternalPidginName() + "%");
			}

			if (externalPidginDto.getSecrecyLevel() != null) {
				hql.append(" and secrecyLevel =:secrecyLevel");
				params.put("secrecyLevel", externalPidginDto.getSecrecyLevel());
			}

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if (externalPidginDto.getStartTime() != null
					&& !externalPidginDto.getStartTime().equals("")) {
				Date startTimes = sdf.parse(externalPidginDto.getStartTime());
				hql.append(" and startDate >= :startDate");
				params.put("startDate", startTimes);
			}

			if (externalPidginDto.getEndTime() != null
					&& !externalPidginDto.getEndTime().equals("")) {
				Date end = sdf.parse(externalPidginDto.getEndTime());
				Date endTimes = new Date(end.getTime() + 24 * 60 * 60 * 1000);
				hql.append(" and startDate < :endTimes");
				params.put("endTimes", endTimes);
			}
		}
		return (List<ExternalPidgin>) findList(hql.toString(), params, psm);
	}

	/**
	 *
	 * @author 王欢 2010 1 7 12:34:56
	 * @modify 陈文聪 2010 8 19 02:15:27 修改注释格式.
	 * @param PageSortModel
	 *            pageSortModel
	 * @param ExternalPidgin
	 *            externalPidgin
	 * @return List
	 */
	public List getPageList(PageSortModel pageSortModel,
			ExternalPidgin externalPidgin, String showType) {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(ExternalPidgin.class);
		String layer = null;// GlobalSysInfo.getCurrentUser().getOrgan().getLayer();
		if (externalPidgin != null) {
			if (externalPidgin.getEternalPidginName() != null
					&& !"".equals(externalPidgin.getEternalPidginName())) {
				detachedCriteria.add(Restrictions.like("eternalPidginName", "%"
						+ externalPidgin.getEternalPidginName() + "%"));
			}
			if (externalPidgin.getOrgan() != null
					&& externalPidgin.getOrgan().getOrganId() != null
					&& !"".equals(externalPidgin.getOrgan().getOrganId())) {
				Organ o = this.get(externalPidgin.getOrgan().getOrganId(),
						Organ.class);
				layer = o.getLayer();
			}
		}

		if (showType == null || "0".equals(showType)) {
			layer += "%";
		}

		detachedCriteria.createCriteria("organ").add(
				Restrictions.like("layer", layer));

		return null;//this.findPageByCriteria(detachedCriteria, pageSortModel);

	}

	/**
	 *
	 * @author 王欢 2010 1 7 12:34:56
	 * @modify 陈文聪 2010 8 19 02:15:27 修改注释格式.
	 * @param PageSortModel
	 *            pageSortModel
	 * @param ExternalPidginDto
	 *            externalPidginDto
	 * @return List
	 */
	public List getPageList(PageSortModel pageSortModel,
			ExternalPidginDto externalPidginDto) {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(ExternalPidgin.class);
		District ds = null;//this.get(GlobalSysInfo.getCurrentUser().getOrgan().getDistrict().getDistrictCode(), District.class);
		if (externalPidginDto != null
				&& externalPidginDto.getEternalPidginName() != null
				&& !"".equals(externalPidginDto.getEternalPidginName())) {
			detachedCriteria.add(Restrictions.like("eternalPidginName", "%"
					+ externalPidginDto.getEternalPidginName() + "%"));
		}
		detachedCriteria.createCriteria("organ").add(
				Restrictions.like("district.layer", ds.getLayer() + "%"));
		return null;// this.findPageByCriteria(detachedCriteria, pageSortModel);
	}

	public List<Object[]> countExternalPidgin(String countType,
			String startTime, String endTime, String organName) {
		String sqlbyorgan = "SELECT o.ORGAN_ID,o.ORGAN_NAME,"
				+ "(SELECT count(1) FROM bm_external_pidgin ep "
				+ "where ep.ORGAN_ID=o.ORGAN_ID "
				+ "and ep.SECRECY_LEVEL=1 and ep.START_DATE between ? and ?),"
				+ "(SELECT count(1) FROM bm_external_pidgin ep "
				+ "where ep.ORGAN_ID=o.ORGAN_ID and ep.SECRECY_LEVEL=2 "
				+ "and ep.START_DATE between ? and ?),"
				+ "(SELECT count(1) FROM bm_external_pidgin ep "
				+ "where ep.ORGAN_ID=o.ORGAN_ID and ep.SECRECY_LEVEL=3 "
				+ "and ep.START_DATE between ? and ?),"
				+ "(SELECT count(1) FROM bm_external_pidgin ep "
				+ "where ep.ORGAN_ID=o.ORGAN_ID and ep.SECRECY_LEVEL=4 "
				+ "and ep.START_DATE between ? and ?),"
				+ "(SELECT count(1) FROM bm_external_pidgin ep "
				+ "where ep.ORGAN_ID=o.ORGAN_ID and ep.SECRECY_LEVEL=5 "
				+ "and ep.START_DATE between ? and ?),(SELECT count(1) "
				+ "FROM bm_external_pidgin ep where ep.ORGAN_ID=o.ORGAN_ID "
				+ "and ep.SECRECY_LEVEL=6 and ep.START_DATE between ? and ?)"
				+ "FROM sys_organization o INNER JOIN sys_district dis "
				+ "ON o.district_code=dis.district_code "
				+ "WHERE o.layer like ? ";

		String sqldistrict = "SELECT dis.district_code,dis.district_name,"
				+ "(SELECT count(1) FROM bm_external_pidgin ep "
				+ "inner join sys_organization o on ep.ORGAN_ID=o.ORGAN_ID "
				+ "where o.district_code=dis.district_code and ep.SECRECY_LEVEL=1 "
				+ "and ep.START_DATE between ? and ?),(SELECT count(1) "
				+ "FROM bm_external_pidgin ep inner join sys_organization o on "
				+ "ep.ORGAN_ID=o.ORGAN_ID where o.district_code=dis.district_code and "
				+ "ep.SECRECY_LEVEL=2 and ep.START_DATE between ? and ?),(SELECT count(1) "
				+ "FROM bm_external_pidgin ep inner join sys_organization o "
				+ "on ep.ORGAN_ID=o.ORGAN_ID where o.district_code=dis.district_code "
				+ "and ep.SECRECY_LEVEL=3 and ep.START_DATE between ? and ?),"
				+ "(SELECT count(1) FROM bm_external_pidgin ep inner join "
				+ "sys_organization o on ep.ORGAN_ID=o.ORGAN_ID "
				+ "where o.district_code=dis.district_code and ep.SECRECY_LEVEL=4 "
				+ "and ep.START_DATE between ? and ?),(SELECT count(1) FROM "
				+ "bm_external_pidgin ep inner join sys_organization o "
				+ "on ep.ORGAN_ID=o.ORGAN_ID where o.district_code=dis.district_code "
				+ "and ep.SECRECY_LEVEL=5 and ep.START_DATE between ? and ?),"
				+ "(SELECT count(1) FROM bm_external_pidgin ep inner "
				+ "join sys_organization o on ep.ORGAN_ID=o.ORGAN_ID where "
				+ "o.district_code=dis.district_code and ep.SECRECY_LEVEL=6 and "
				+ "ep.START_DATE between ? and ?)FROM sys_district dis "
				+ "WHERE  dis.layer like ?";
		List params = new ArrayList();
		SimpleDateFormat sdf = new SimpleDateFormat();
		Date startDate = new Date();
		Date endDate = new Date();
		try {
			startDate = sdf.parse(startTime);
			endDate = sdf.parse(startTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < 6; i++) {
			params.add(startDate);
			params.add(endDate);
		}
		List<Object[]> list = null;

		if (countType.equals("1")) {
			params.add("%");//GlobalSysInfo.getCurrentUser().getOrgan().getLayer()+ "%");
			if (organName != null) {
				sqlbyorgan += " and o.ORGAN_NAME like ?";
				params.add("%" + organName + "%");
			}
			list = this.getPersistProxy().getOrmPersistence()
					.findByNativeQuery(sqlbyorgan, params.toArray());
		} else {
			params.add(null);// GlobalSysInfo.getCurrentUser().getOrgan().getDistrict().getLayer()+ "%");
			list = this.getPersistProxy().getOrmPersistence()
					.findByNativeQuery(sqldistrict, params.toArray());
		}
		return list;

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Object[]> countAllOrgan(String countType, String startTime,
			String endTime, String organName) {
		String sqlbyorgan = "SELECT * FROM (SELECT o.ORGAN_ID,o.ORGAN_NAME,"
				+ "(SELECT count(1) FROM bm_external_pidgin ep "
				+ "where ep.ORGAN_ID=o.ORGAN_ID "
				+ "and ep.SECRECY_LEVEL=1 and ep.START_DATE between ? and ?),"
				+ "(SELECT count(1) FROM bm_external_pidgin ep "
				+ "where ep.ORGAN_ID=o.ORGAN_ID and ep.SECRECY_LEVEL=2 "
				+ "and ep.START_DATE between ? and ?),"
				+ "(SELECT count(1) FROM bm_external_pidgin ep "
				+ "where ep.ORGAN_ID=o.ORGAN_ID and ep.SECRECY_LEVEL=3 "
				+ "and ep.START_DATE between ? and ?),"
				+ "(SELECT count(1) FROM bm_external_pidgin ep "
				+ "where ep.ORGAN_ID=o.ORGAN_ID and ep.SECRECY_LEVEL=4 "
				+ "and ep.START_DATE between ? and ?),"
				+ "(SELECT count(1) FROM bm_external_pidgin ep "
				+ "where ep.ORGAN_ID=o.ORGAN_ID and ep.SECRECY_LEVEL=5 "
				+ "and ep.START_DATE between ? and ?),(SELECT count(1) "
				+ "FROM bm_external_pidgin ep where ep.ORGAN_ID=o.ORGAN_ID "
				+ "and ep.SECRECY_LEVEL=6 and ep.START_DATE between ? and ?)"
				+ "FROM sys_organization o INNER JOIN sys_district dis "
				+ "ON o.district_code=dis.district_code "
				+ "WHERE o.layer like ? or o.district_code=dis.district_code and o.ORGAN_ID != 1) t";

		String sqldistrict = "SELECT dis.district_code,dis.district_name,"
				+ "(SELECT count(1) FROM bm_external_pidgin ep "
				+ "inner join sys_organization o on ep.ORGAN_ID=o.ORGAN_ID "
				+ "where o.district_code=dis.district_code and ep.SECRECY_LEVEL=1 "
				+ "and ep.START_DATE between ? and ?),(SELECT count(1) "
				+ "FROM bm_external_pidgin ep inner join sys_organization o on "
				+ "ep.ORGAN_ID=o.ORGAN_ID where o.district_code=dis.district_code and "
				+ "ep.SECRECY_LEVEL=2 and ep.START_DATE between ? and ?),(SELECT count(1) "
				+ "FROM bm_external_pidgin ep inner join sys_organization o "
				+ "on ep.ORGAN_ID=o.ORGAN_ID where o.district_code=dis.district_code "
				+ "and ep.SECRECY_LEVEL=3 and ep.START_DATE between ? and ?),"
				+ "(SELECT count(1) FROM bm_external_pidgin ep inner join "
				+ "sys_organization o on ep.ORGAN_ID=o.ORGAN_ID "
				+ "where o.district_code=dis.district_code and ep.SECRECY_LEVEL=4 "
				+ "and ep.START_DATE between ? and ?),(SELECT count(1) FROM "
				+ "bm_external_pidgin ep inner join sys_organization o "
				+ "on ep.ORGAN_ID=o.ORGAN_ID where o.district_code=dis.district_code "
				+ "and ep.SECRECY_LEVEL=5 and ep.START_DATE between ? and ?),"
				+ "(SELECT count(1) FROM bm_external_pidgin ep inner "
				+ "join sys_organization o on ep.ORGAN_ID=o.ORGAN_ID where "
				+ "o.district_code=dis.district_code and ep.SECRECY_LEVEL=6 and "
				+ "ep.START_DATE between ? and ?)FROM sys_district dis "
				+ "WHERE  dis.layer like ?";
		List params = new ArrayList();
		SimpleDateFormat sdf = new SimpleDateFormat();
		Date startDate = new Date();
		Date endDate = new Date();
		try {
			startDate = sdf.parse(startTime);
			endDate = sdf.parse(startTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < 6; i++) {
			params.add(startDate);
			params.add(endDate);
		}
		List<Object[]> list = null;

		// 1 按单位统计
		if (countType.equals("1")) {
			params.add(null);//GlobalSysInfo.getCurrentUser().getOrgan().getLayer()+ "%");
			if (organName != null) {
				sqlbyorgan += " where t.ORGAN_NAME like ?";
				params.add("%" + organName + "%");
			}
			list = this.getPersistProxy().getOrmPersistence()
					.findByNativeQuery(sqlbyorgan, params.toArray());
		} else {
			params.add(null);//GlobalSysInfo.getCurrentUser().getOrgan().getDistrict().getLayer()+ "%");
			list = this.getPersistProxy().getOrmPersistence()
					.findByNativeQuery(sqldistrict, params.toArray());
		}
		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List getAllPageList(PageSortModel pageSortModel,
			ExternalPidgin externalPidgin, String showType, Organ organ) {
		// StringBuilder hql = new StringBuilder(
		// "from ExternalPidgin ep where 1=1");
		Map<String, Object> params = new HashMap<String, Object>();

		StringBuilder hql = new StringBuilder(
				"from ExternalPidgin ep left join ep.receiveOrgans ro where 1=1 and (ro.organId = :currentOrganId or ep.organ.organId = :cOrganId)");
		params.put("currentOrganId", organ.getOrganId());
		params.put("cOrganId", organ.getOrganId());

		User user = null;//GlobalSysInfo.getCurrentUser();
		// 查询
		if (externalPidgin != null
				&& externalPidgin.getEternalPidginName() != null
				&& !"".equals(externalPidgin.getEternalPidginName())) {
			hql.append(" and (ep.eternalPidginName like :eternalPidginName)");
			params.put("eternalPidginName",
					"%" + externalPidgin.getEternalPidginName() + "%");
		}

		hql.append(" and ( ep.organ.district.districtCode = :districtCode");
		params.put("districtCode", user.getOrgan().getDistrict()
				.getDistrictCode());
		hql.append(" or ep.organ.layer like :layer)");
		params.put("layer", user.getOrgan().getLayer() + "%");
		hql.append(" order by ep.createTime desc");
		return this.findList(hql.toString(), params, pageSortModel);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List getDataAllPageList(String organNames,
			PageSortModel pageSortModel, ExternalPidgin externalPidgin,
			ExternalPidginDto externalPidginDto, String showType,
			String countType, Organ organ) {
		// StringBuilder hql = new StringBuilder(
		// "from ExternalPidgin ep where 1=1");

		Map<String, Object> params = new HashMap<String, Object>();

		StringBuilder hql = new StringBuilder(
				"from ExternalPidgin ep left join ep.receiveOrgans ro where 1=1 and (ro.organId = :currentOrganId or ep.organ.organId = :cOrganId)");
		params.put("currentOrganId", organ.getOrganId());
		params.put("cOrganId", organ.getOrganId());

		User user = null;// getCurrentUser();
		// 查询
		if (externalPidgin != null
				&& externalPidgin.getEternalPidginName() != null
				&& !"".equals(externalPidgin.getEternalPidginName())) {
			hql.append(" and (ep.eternalPidginName like :eternalPidginName)");
			params.put("eternalPidginName",
					"%" + externalPidgin.getEternalPidginName() + "%");
		}

		if (organNames != null && !"".equals(organNames)) {
			hql.append(" and ep.organ.organName like :organNames");
			params.put("organNames", "%" + organNames + "%");
		}

		if (externalPidginDto != null) {
			// 获取对应单位
			if (externalPidginDto.getSecrecyLevel() != null) {
				if (externalPidginDto.getSecrecyLevel() == 1000) {
					hql.append(" and ep.secrecyLevel != :secrecyLevel");
					params.put("secrecyLevel",
							externalPidginDto.getSecrecyLevel());
					if (countType == null || "".equals(countType)
							|| countType.equals("1")) {
						hql.append(" and ( ep.organ.district.districtCode = :districtCode");
						params.put("districtCode", user.getOrgan()
								.getDistrict().getDistrictCode());
						hql.append(" or ep.organ.layer like :layer)");
						params.put("layer", user.getOrgan().getLayer() + "%");
					}
				} else {
					if (externalPidginDto.getSecrecyLevel() == 100) {
						hql.append(" and ep.secrecyLevel != :secrecyLevel");
						params.put("secrecyLevel",
								externalPidginDto.getSecrecyLevel());

						if (countType != null && !"".equals(countType)
								&& countType.equals("2")) {
							hql.append(" and ep.organ.district.districtName = :districtName");
							params.put("districtName",
									externalPidginDto.getOrganName());
						} else {
							if (externalPidginDto.getOrganName() != null
									&& !"".equals(externalPidginDto
											.getOrganName())) {
								hql.append(" and ep.organ.organName = :organName");
								params.put("organName",
										externalPidginDto.getOrganName());
							} else {
								hql.append(" and ( ep.organ.district.districtCode = :districtCode");
								params.put("districtCode", user.getOrgan()
										.getDistrict().getDistrictCode());
								hql.append(" or ep.organ.layer like :layer)");
								params.put("layer", user.getOrgan().getLayer()
										+ "%");
							}
						}
					} else {
						hql.append(" and ep.secrecyLevel = :secrecyLevel");
						params.put("secrecyLevel",
								externalPidginDto.getSecrecyLevel());

						if (countType != null && !"".equals(countType)
								&& countType.equals("2")) {
							if (externalPidginDto.getOrganName() != null
									&& !"".equals(externalPidginDto
											.getOrganName())) {
								hql.append(" and ep.organ.district.districtName = :districtName");
								params.put("districtName",
										externalPidginDto.getOrganName());
							}
						} else {
							if (externalPidginDto.getOrganName() != null
									&& !"".equals(externalPidginDto
											.getOrganName())) {
								hql.append(" and ep.organ.organName = :organName");
								params.put("organName",
										externalPidginDto.getOrganName());
							} else {
								hql.append(" and ( ep.organ.district.districtCode = :districtCode");
								params.put("districtCode", user.getOrgan()
										.getDistrict().getDistrictCode());
								hql.append(" or ep.organ.layer like :layer)");
								params.put("layer", user.getOrgan().getLayer()
										+ "%");
							}
						}
					}
				}
			}
		} else {
			hql.append(" and ( ep.organ.district.districtCode = :districtCode");
			params.put("districtCode", user.getOrgan().getDistrict()
					.getDistrictCode());
			hql.append(" or ep.organ.layer like :layer)");
			params.put("layer", user.getOrgan().getLayer() + "%");
		}

		hql.append(" order by ep.createTime desc");
		hql.append(" group by ep.organ.organName");
		return this.findList(hql.toString(), params, pageSortModel);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List getDataPageList(String organNames, PageSortModel pageSortModel,
			ExternalPidgin externalPidgin, ExternalPidginDto externalPidginDto,
			String showType, String countType) {
		StringBuilder hql = new StringBuilder(
				"from ExternalPidgin ep where 1=1");
		Map<String, Object> params = new HashMap<String, Object>();
		User user = null;// CurrentUser();
		// 查询
		if (externalPidgin != null
				&& externalPidgin.getEternalPidginName() != null
				&& !"".equals(externalPidgin.getEternalPidginName())) {
			hql.append(" and (ep.eternalPidginName like :eternalPidginName)");
			params.put("eternalPidginName",
					"%" + externalPidgin.getEternalPidginName() + "%");
		}

		if (organNames != null && !"".equals(organNames)) {
			hql.append(" and ep.organ.organName like :organNames");
			params.put("organNames", "%" + organNames + "%");
		}

		if (externalPidginDto != null) {
			// 获取对应单位
			if (externalPidginDto.getSecrecyLevel() != null) {
				if (externalPidginDto.getSecrecyLevel() == 1000) {
					hql.append(" and ep.secrecyLevel != :secrecyLevel");
					params.put("secrecyLevel",
							externalPidginDto.getSecrecyLevel());
					if (countType == null || "".equals(countType)
							|| countType.equals("1")) {
						hql.append(" and ep.organ.layer like :layer");
						params.put("layer", user.getOrgan().getLayer() + "%");
					}
				} else {
					if (externalPidginDto.getSecrecyLevel() == 100) {
						hql.append(" and ep.secrecyLevel != :secrecyLevel");
						params.put("secrecyLevel",
								externalPidginDto.getSecrecyLevel());

						if (countType != null && !"".equals(countType)
								&& countType.equals("2")) {
							hql.append(" and ep.organ.district.districtName = :districtName");
							params.put("districtName",
									externalPidginDto.getOrganName());
						} else {
							if (externalPidginDto.getOrganName() != null
									&& !"".equals(externalPidginDto
											.getOrganName())) {
								hql.append(" and ep.organ.organName = :organName");
								params.put("organName",
										externalPidginDto.getOrganName());
							} else {
								hql.append(" and ep.organ.layer like :layer");
								params.put("layer", user.getOrgan().getLayer()
										+ "%");
							}
						}
					} else {
						hql.append(" and ep.secrecyLevel = :secrecyLevel");
						params.put("secrecyLevel",
								externalPidginDto.getSecrecyLevel());

						if (countType != null && !"".equals(countType)
								&& countType.equals("2")) {
							if (externalPidginDto.getOrganName() != null
									&& !"".equals(externalPidginDto
											.getOrganName())) {
								hql.append(" and ep.organ.district.districtName = :districtName");
								params.put("districtName",
										externalPidginDto.getOrganName());
							}
						} else {
							if (externalPidginDto.getOrganName() != null
									&& !"".equals(externalPidginDto
											.getOrganName())) {
								hql.append(" and ep.organ.organName = :organName");
								params.put("organName",
										externalPidginDto.getOrganName());
							} else {
								hql.append(" and ep.organ.layer like :layer");
								params.put("layer", user.getOrgan().getLayer()
										+ "%");
							}
						}
					}
				}
			}
		} else {
			hql.append(" and ep.organ.layer like :layer");
			params.put("layer", user.getOrgan().getLayer() + "%");
		}

		hql.append(" order by ep.createTime desc");
		return this.findList(hql.toString(), params, pageSortModel);
	}
	@Override
	public List<ExternalPidgin> getDistrictExternalPidginList(
			PageSortModel pageSortModel, District district, String showType, Organ currentOrgan) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("select s from ExternalPidgin s where 1=1 ");
		if (showType != null && district != null
				&& district.getDistrictCode() != null) {
			if ("1".equals(showType)) {
				// 这里查看下级
				District d = this.get(district.getDistrictCode(),
						District.class);
				hql.append(" and s.organ.district.layer like :layer ");
				params.put("layer", d.getLayer() + "%");
			} else if ("0".equals(showType)) {
				// 这里查看本级
				hql.append(" and s.organ.district.districtCode = :districtCode ");
				params.put("districtCode", district.getDistrictCode());
			}
		}
		hql.append(" order by s.createTime desc");
		return (List<ExternalPidgin>) findList(hql.toString(),params, pageSortModel);
	}

	// Transmit Data Method

	/*@Override
	public ReportableListDto<?> convert(List<ExternalPidgin> domainList,String organId) {

		ExternalPidginListDto externalPidginListDto = new ExternalPidginListDto();

		for (ExternalPidgin externalPidgin : domainList) {

			ExternalPidginDto keySectionDto = new ExternalPidginDto();

			keySectionDto.setExternalPidginId(externalPidgin.getExternalPidginId());
			keySectionDto.setExternalPidginNo(externalPidgin.getExternalPidginNo());
			keySectionDto.setExternalPidginType(externalPidgin.getExternalPidginType());
			keySectionDto.setSecrecyLevel(externalPidgin.getSecrecyLevel());
			keySectionDto.setEternalPidginName(externalPidgin.getEternalPidginName());
			keySectionDto.setStartDate(externalPidgin.getStartDate());
			keySectionDto.setEndDate(externalPidgin.getEndDate());
			keySectionDto.setPlace(externalPidgin.getPlace());
			keySectionDto.setMainOrgan(externalPidgin.getMainOrgan());
			keySectionDto.setAidanceOrgan(externalPidgin.getAidanceOrgan());
			keySectionDto.setKeyWord(externalPidgin.getKeyWord());
			keySectionDto.setUndertaker(externalPidgin.getUndertaker());
			keySectionDto.setSecrecyDutier(externalPidgin.getSecrecyDutier());
			keySectionDto.setDutierHeadship(externalPidgin.getDutierHeadship());
			keySectionDto.setContent(externalPidgin.getContent());
			//keySectionDto.setDepartment(externalPidgin.getDepartment().getDepartmentId());
			keySectionDto.setOrgan(externalPidgin.getOrgan().getOrganId());
			keySectionDto.setCreateTime(externalPidgin.getCreateTime());
			keySectionDto.setModifyTime(externalPidgin.getModifyTime());
			keySectionDto.setReportOrgan(organId);

			externalPidginListDto.getReportableList().add(keySectionDto);
		}

		return externalPidginListDto;
	}
*/

	public boolean saveOrUpdateBatch(List<ExternalPidgin> externalPidginList, String receiveOrganId) {
		boolean flag = false;
		try{
			for (ExternalPidgin externalPidgin : externalPidginList) {
				ExternalPidgin e = this.get(externalPidgin.getExternalPidginId());
				if (null != e) { // null != eno 说明已经保存过，现在只需更新
					evict(e);
					//e.setInceptTime(new Date());
					//e.setReportState(ExternalPidgin.REPORT_YET);
					//e.setReportOrganState(ExternalPidgin.REPORT_YET);
					e.setReportOrgan(this.get(receiveOrganId, Organ.class));
					e.getReceiveOrgans().add(this.get(receiveOrganId, Organ.class));
					this.update(e);

				} else { // 第一次保存
					//externalPidgin.setInceptTime(new Date());
					//externalPidgin.setReportState(ExternalPidgin.REPORT_YET);
					//externalPidgin.setReportOrganState(ExternalPidgin.REPORT_YET);
					externalPidgin.setReportOrgan(this.get(receiveOrganId, Organ.class));
					Set<Organ> receiveOrganSet = new HashSet<Organ>();
					if (receiveOrganId != null && !"".equals(receiveOrganId)) {
						receiveOrganSet.add(this.get(receiveOrganId, Organ.class));
					}
					externalPidgin.setReceiveOrgans(receiveOrganSet);
					this.save(externalPidgin);
				}
				flag = true;
				LOG.debug("上报数据成功！");
				}
			}catch (Exception e) {
				flag = false;
				LOG.error(e.getMessage(), e);
			}
				return flag;
		}

	@Override
	public boolean updateLocalData(String externalPidginIds, String organId) {
		boolean flag = false;
		String[] ids = externalPidginIds.split(",");
		this.getPersistProxy().getOrmPersistence().getHibernateTemplate()
			.getSessionFactory().getCurrentSession().clear();
		try {
			for (String id : ids) {
				ExternalPidgin externalPidgin = this.get(id);
				if (externalPidgin != null) {
					externalPidgin.setReportOrganTime(new Date());
					externalPidgin.setReportOrgan(this.get(organId, Organ.class));
					//externalPidgin.setReportState(Section.REPORT_ALREADY);
					this.update(externalPidgin);
				}
			}
			flag = true;
			LOG.debug("更新成功！");
		} catch (Exception e) {
			flag = false;
			LOG.error(e.getMessage(), e);
		}
		return flag;
	}

	@Override
	public void updateLocalData(List<ExternalPidginDto> reportableList,
			List<String> successOrganIds, List<String> organIds) {
		this.getPersistProxy().getOrmPersistence().getHibernateTemplate()
		.getSessionFactory().getCurrentSession().clear();
		Iterator<ExternalPidginDto> i = reportableList.iterator();
		while (i.hasNext()) {
			ExternalPidginDto externalPidginDto = i.next();
			ExternalPidgin externalPidgin = this.get(externalPidginDto.getExternalPidginId());
			if (successOrganIds.size() == organIds.size()) {
				// 全部成功
				//externalPidgin.setTransmitState(Section.SEND_SUCCESS);
			} else {
				// 部分成功
				//externalPidgin.setTransmitState(Section.SEND_FAILURE);
			}
			externalPidgin.setReportTime(new Date());
			externalPidgin.setReportOrganTime(new Date());
			//externalPidgin.setReportState(Section.REPORT_ALREADY);
			this.update(externalPidgin);
		}

	}

	@Override
	public void updateBatch(ExternalPidgin externalPidgin) {
		this.getPersistProxy().getOrmPersistence().getHibernateTemplate()
		.getSessionFactory().getCurrentSession().clear();
		try {
			if (externalPidgin != null) {
				externalPidgin.setReportTime(new Date());
				//externalPidgin.setReportState(SecrecyPerson.REPORT_ALREADY);
				this.update(externalPidgin);
			}
			LOG.debug("更新成功！");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			}
		}
	/**
	 *
	 * @description 统计分析数据撰取详情列表
	 * @author yangc 2010 1 12 null 12:34:56
	 * @modify 陈文聪 2010 8 18 09:17:58 修改注释格式.
	 * @param PageSortModel psm
	 * @param Organ organ
	 * @param Integer secrecyLevel
	 * @return List<ExternalPidgin>
	 */
	public List<ExternalPidgin> getForPage(PageSortModel psm,
			Organ organ,Integer secrecyLevel,String districtCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder(
				"FROM ExternalPidgin e where 1=1 and e.reportState = 2 ");
		if(organ !=null){
			hql.append("and e.organ.organId = :organId ");
			params.put("organId", organ.getOrganId());
		}
		hql.append("and e.secrecyLevel = :secrecyLevel ");
		params.put("secrecyLevel", secrecyLevel);
		hql.append(" and e.organ.district.districtCode = :districtCode");
		params.put("districtCode", districtCode);
		return (List<ExternalPidgin>) findList(hql.toString(),params, psm);
	}
	/**
	 *
	 * @description 首页统计数据撰取
	 * @author 刘椿成 2012 08 29 null 10:34:56
	 * @modify
	 * @param PageSortModel psm
	 * @param Organ organ
	 * * @param Integer secrecyLevel
	 * * @param String districtCode
	 * @return List<ExternalPidgin>
	 */
	public List<ExternalPidgin> getIndexPage(PageSortModel psm,
			Organ organ,Integer secrecyLevel,String districtCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder(
				"FROM ExternalPidgin e where 1=1");
		if(organ !=null){
			hql.append("and e.organ.organId = :organId ");
			params.put("organId", organ.getOrganId());
		}
		hql.append("and e.secrecyLevel = :secrecyLevel ");
		params.put("secrecyLevel", secrecyLevel);
		hql.append(" and e.organ.district.districtCode = :districtCode");
		params.put("districtCode", districtCode);
		return (List<ExternalPidgin>) findList(hql.toString(),params, psm);
	}
	/**
	 *
	 * @description 首页涉密涉外活动详情
	 * @author 刘椿成 2012 08 29 null 10:34:56
	 * @modify
	 * @param PageSortModel psm
	 * @param Organ organ
	 * * @param Integer secrecyLevel
	 * * @param String districtCode
	 * @return List<ExternalPidgin>
	 */
	public List<ExternalPidgin> getIndexPage(PageSortModel psm,ExternalPidgin externalPidgin,
			Organ organ,String districtCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder(
		"FROM ExternalPidgin e where 1=1");
		if(organ !=null){
			hql.append("and e.organ.organId = :organId ");
			params.put("organId", organ.getOrganId());
		}
		if (externalPidgin !=null && externalPidgin.getEternalPidginName()!=null && !"".equals(externalPidgin.getEternalPidginName())) {
			hql.append("and e.eternalPidginName like :eternalPidginName ");
			params.put("eternalPidginName", "%"+externalPidgin.getEternalPidginName()+"%");
		}
		hql.append(" and e.organ.district.districtCode = :districtCode");
		params.put("districtCode", districtCode);
		return (List<ExternalPidgin>) findList(hql.toString(),params, psm);
	}
}
