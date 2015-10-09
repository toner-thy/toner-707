package com.cdthgk.bmp.equipment.equipmenttype.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cdthgk.bmp.equipment.equipmenttype.service.EquipmentTypeService;
import com.cdthgk.bmp.equipment.equipmenttype.vo.EquipmentType;
import com.cdthgk.common.lang.UUIDGenerator;
import com.cdthgk.platform.base.service.GenericServiceTemplate;
import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.permission.user.domain.User;
import com.thgk.platform.syslog.service.invoke.imp.annotation.ExcuteType;
import com.thgk.platform.syslog.service.invoke.imp.annotation.SysLog;
import com.thgk.platform.syslog.service.invoke.imp.annotation.SysLogs;

import ec.common.PageSortModel;

/**
 * @description 设备分类Service实现类.
 * @author 陈文聪 2010 3 4 12:34:56
 * @modify 陈文聪 2010 8 19 02:22:17 修改注释格式
 */

@SuppressWarnings("unchecked")
@SysLogs(
		{
			@SysLog(method="update",methodParamsSize=1,description="修改设备分类{0}|name",excute_type=ExcuteType.UPDATE),
			@SysLog(method="delete",methodParamsSize=1,description="删除设备分类{0}|name",excute_type=ExcuteType.DELETE),
			@SysLog(method="save",methodParamsSize=1,description="添加设备分类{0}|name",excute_type=ExcuteType.ADD)
		}
)
public class EquipmentTypeServiceImpl extends GenericServiceTemplate<EquipmentType, String> implements
		EquipmentTypeService {

	/**
	 * @description 获取技术设备类型
	 * @author 陈文聪 2010 3 4 12:34:56
	 * @modify 陈文聪 2010 8 19 02:25:45 修改注释格式.
	 * @param PageSortModel psm
	 * @param EquipmentType equipmentType
	 * @return List<EquipmentType>
	 */
	public List<EquipmentType> getPageList(PageSortModel psm,
			EquipmentType equipmentType) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("FROM EquipmentType et WHERE 1=1");
		if(null != equipmentType) {
			if (StringUtils.isNotBlank(String.valueOf(equipmentType.getName()))) {
				hql.append(" AND et.name like :name");
				params.put("name", "%"+equipmentType.getName()+"%");
			}
		}
//		hql.append(" and et.createPerson.userId = :userId");
//		params.put("userId", user.getUserId());
		hql.append(" ORDER BY et.modifyTime desc");

		return (List<EquipmentType>) this.findList(hql.toString(), params, psm);
	}

	@Override
	public void saveEquipmet(EquipmentType equipmentType, User user,
			Organ organ, Department department) {
		Date date = new Date();
		equipmentType.setCreatePerson(user);
		equipmentType.setCreateTime(date);
		equipmentType.setDepartment(department);
		equipmentType.setModifyPerson(user);
		equipmentType.setModifyTime(date);
		equipmentType.setOrgan(organ);
		equipmentType.setEquipmentTypeId(UUIDGenerator.generateUUID32());
		save(equipmentType);
	}

	/* (non-Javadoc)
	 * @see com.thgk.sys.base.service.ServiceReportable#convert(java.util.List, java.lang.String)
	 */
	/*@Override
	public ReportableListDto<?> convert(List<EquipmentType> domainList,
			String organId) {
		EquipmentTypeListDto listDto = new EquipmentTypeListDto();
		for (EquipmentType equipmentType : domainList) {
			EquipmentTypeWsDto equipmentTypeWsDto = new EquipmentTypeWsDto();
			equipmentTypeWsDto.setCreatePersonId(equipmentType.getCreatePerson().getUserId());
			equipmentTypeWsDto.setCreateTime(equipmentType.getCreateTime());
			equipmentTypeWsDto.setDepartmentId(equipmentType.getDepartment().getDepartmentId());
			equipmentTypeWsDto.setDescription(equipmentType.getDescription());
			equipmentTypeWsDto.setEquipmentTypeId(equipmentType.getEquipmentTypeId());
			equipmentTypeWsDto.setModifyPersonId(equipmentType.getModifyPerson().getUserId());
			equipmentTypeWsDto.setModifyTime(equipmentType.getModifyTime());
			equipmentTypeWsDto.setName(equipmentType.getName());
			equipmentTypeWsDto.setOrganId(equipmentType.getOrgan().getOrganId());
			equipmentTypeWsDto.setState(EquipmentType.PUBLISH_YES);
			listDto.getReportableList().add(equipmentTypeWsDto);
		}
		return listDto;
	}*/
	@Override
	public void saveOrUpdateBatch(List<EquipmentType> equipmentTypeList,String receiveOrganId) {
		if (equipmentTypeList == null) {
			return;
		}
		for (EquipmentType equipmentType : equipmentTypeList) {
			EquipmentType sp = this.get(equipmentType.getEquipmentTypeId());
			if (null != sp) { // null != eno 说明已经保存过，现在只需更新
			    evict(sp);
				equipmentType.setState(EquipmentType.PUBLISH_YES);
				Set<Organ> receiveOrganSet = new HashSet<Organ>();
				if (receiveOrganId != null && !"".equals(receiveOrganId)) {
					receiveOrganSet.add(this.get(receiveOrganId, Organ.class));
				}
				equipmentType.setReceiveOrgans(receiveOrganSet);
				this.update(equipmentType);
			} else { // 第一次保存
				equipmentType.setState(EquipmentType.PUBLISH_YES);
				Set<Organ> receiveOrganSet = new HashSet<Organ>();
				if (receiveOrganId != null && !"".equals(receiveOrganId)) {
					receiveOrganSet.add(this.get(receiveOrganId, Organ.class));
				}
				equipmentType.setReceiveOrgans(receiveOrganSet);
				this.save(equipmentType);
			}
		}
	}

	@Override
	public void updateBatch(String equipmentTypeIds, String organId) {
		String[] ids = equipmentTypeIds.split(",");
		this.getPersistProxy().getOrmPersistence().getHibernateTemplate().getSessionFactory().getCurrentSession().clear();
		try {
			for (String id : ids) {
				EquipmentType equipmentType = this.get(id);
				if (equipmentType != null) {
					equipmentType.setState(EquipmentType.PUBLISH_YES);
					this.update(equipmentType);
				}
			}
			LOG.debug("更新成功！");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}
	private static final Log LOG = LogFactory.getLog(EquipmentTypeServiceImpl.class);

	@Override
	public void updateBatch(EquipmentType equipmentType) {
		this.getPersistProxy().getOrmPersistence().getHibernateTemplate().getSessionFactory().getCurrentSession().clear();
		try {
				if (equipmentType != null) {
					equipmentType.setState(EquipmentType.PUBLISH_YES);
					this.update(equipmentType);
				}
			LOG.debug("更新成功！");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}

	}

	/*@Override
	public List<EquipmentTypeData> queryTransmitObject(String equipmentTypeId) {
		StringBuilder hql = new StringBuilder("From EquipmentTypeData e where e.equipmentType.equipmentTypeId =?");
		return this.getPersistProxy().getOrmPersistence().findByHQLQuery(hql.toString(), new Object[]{equipmentTypeId});
	}*/

	@Override
	public void updateBatch(List<EquipmentType> etList) {

		for(int i=0;i<etList.size();i++){
			EquipmentType et = etList.get(i);
			this.getPersistProxy().getOrmPersistence().update(et);
		}

	}
}
