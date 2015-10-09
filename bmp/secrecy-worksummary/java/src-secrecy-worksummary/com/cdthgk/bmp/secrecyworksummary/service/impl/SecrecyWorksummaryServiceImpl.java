package com.cdthgk.bmp.secrecyworksummary.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.action.BmpAction;
import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.bmp.secrecyworksummary.dto.SecrecyWorksummaryDto;
import com.cdthgk.bmp.secrecyworksummary.service.SecrecyWorksummaryModuleService;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;

/**
 *
 * <p>
 * 保密工作概况Service类
 * </p>
 * <p>
 * 创建时间 2013-1-13 - 上午11:48:13
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
@SuppressWarnings("rawtypes")
public class SecrecyWorksummaryServiceImpl extends BmpServiceImpl<Object, String>
			implements SecrecyWorksummaryModuleService{

	private String secrecyWorksummarySql;

	// 构造器
	/** 默认构造器 */
	public SecrecyWorksummaryServiceImpl() {
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SecrecyWorksummaryDto> getSecrecyWorksummaryPageList(PageSortModel psm,
			District district, Organ queryOrgan) {
		List<SecrecyWorksummaryDto> list = new ArrayList<SecrecyWorksummaryDto>();
		//查询单位列表
		/*OrganHqlQuery qurey = OrganizationContext.getInstance().getOrganService().createQuery();
		List<Organ> organList = qurey.eqDistrict(district.getDistrictCode())
			.coName(queryOrgan == null ? "" : queryOrgan.getOrganName()).eqStatus(DataStatus.USE).eqLogoutStatus(Useable.ENABLE)
			.setPagination(psm)
			.list();*/
		Map<String, Object> paramsOrg = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("SELECT o FROM Organ o WHERE o.district.districtCode = :districtCode and o.logoutStatus = 1 and o.status = 1");
		paramsOrg.put("districtCode", district.getDistrictCode());
		if( queryOrgan != null && queryOrgan.getOrganName()!=null && !"".equals(queryOrgan.getOrganName()) ){
			hql.append("and o.organName like :organName ");
			paramsOrg.put("organName", "%"+ queryOrgan.getOrganName() +"%");
		}
		//查询保密业务数据
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("districtCode", district.getDistrictCode());
		List<Object> objectList = this.getPersistProxy().getOrmPersistence().findList(hql.toString(), paramsOrg, psm);
		List<Organ> organList = new ArrayList<Organ>();
		if( objectList!=null && objectList.size()>0 ){
			for( Object object : objectList ){
				Organ org = (Organ) object;
				organList.add(org);
			}
		}
		for (Organ organ : organList) {
			params.put("organId", organ.getOrganId());
			params.put("secrecyStatus", BmpAction.SECRECY_STATUS_NOW);
			params.put("keypartSecrecyStatus", 0);
			params.put("keysectionSecrecyStatus", 0);
			params.put("unitSecrecyStatus", BmpAction.SECRECY_STATUS_NOW);
			params.put("relationSecrecyStatus", BmpAction.SECRECY_STATUS_NOW);

			Map<String, Object> map =  this.getPersistProxy().getJdbcPersistence()
					.find(secrecyWorksummarySql, params);
			SecrecyWorksummaryDto secrecyWorksummaryDto = new SecrecyWorksummaryDto();
			secrecyWorksummaryDto.setOrganId(organ.getOrganId());
			secrecyWorksummaryDto.setOrganName(organ.getOrganName());
			secrecyWorksummaryDto.setNumKeyPart(Integer.parseInt(map.get("num_keyPart").toString()));
			secrecyWorksummaryDto.setNumKeysection(Integer.parseInt(map.get("num_keysection").toString()));
			secrecyWorksummaryDto.setNumPersonGroupMember(Integer.parseInt(map.get("num_personGroupMember").toString()));
			secrecyWorksummaryDto.setNumSecrecyPerson(Integer.parseInt(map.get("num_secrecyPerson").toString()));
			secrecyWorksummaryDto.setNumSecrecyWorkOrganMember(Integer.parseInt(map.get("num_secrecyWorkOrganMember").toString()));
			list.add(secrecyWorksummaryDto);
		}
		return list;
	}

	/**
	 *报表使用，不分页
	 */
	public List<SecrecyWorksummaryDto> getSecrecyWorksummaryAllList(District district, Organ queryOrgan) {
		List<SecrecyWorksummaryDto> list = new ArrayList<SecrecyWorksummaryDto>();
		//查询单位列表
		/*OrganHqlQuery qurey = OrganizationContext.getInstance().getOrganService().createQuery();
		List<Organ> organList = qurey.eqDistrict(district.getDistrictCode())
			.coName(queryOrgan == null ? "" : queryOrgan.getOrganName()).eqStatus(DataStatus.USE).eqLogoutStatus(Useable.ENABLE)
			.setPagination(psm)
			.list();*/
		Map<String, Object> paramsOrg = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("SELECT o FROM Organ o, District d WHERE o.district.districtCode = d.districtCode and d.districtCode = :districtCode and o.logoutStatus = '1' and o.status = '1'");
		paramsOrg.put("districtCode", district.getDistrictCode());
		if( queryOrgan != null && queryOrgan.getOrganName()!=null && !"".equals(queryOrgan.getOrganName()) ){
			hql.append("and o.organName like :organName ");
			paramsOrg.put("organName", "%"+ queryOrgan.getOrganName() +"%");
		}
		//查询保密业务数据
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("districtCode", district.getDistrictCode());
		List<Object> objectList = this.getPersistProxy().getOrmPersistence().findList(hql.toString(), paramsOrg);
		List<Organ> organList = new ArrayList<Organ>();
		if( objectList!=null && objectList.size()>0 ){
			for( Object object : objectList ){
				Organ org = (Organ) object;
				organList.add(org);
			}
		}
		for (Organ organ : organList) {
			params.put("organId", organ.getOrganId());
			params.put("secrecyStatus", BmpAction.SECRECY_STATUS_NOW);
			params.put("keypartSecrecyStatus", 0);
			params.put("keysectionSecrecyStatus", 0);
			params.put("unitSecrecyStatus", BmpAction.SECRECY_STATUS_NOW);
			params.put("relationSecrecyStatus", BmpAction.SECRECY_STATUS_NOW);
			Map<String, Object> map =  this.getPersistProxy().getJdbcPersistence()
					.find(secrecyWorksummarySql, params);
			SecrecyWorksummaryDto secrecyWorksummaryDto = new SecrecyWorksummaryDto();
			secrecyWorksummaryDto.setOrganId(organ.getOrganId());
			secrecyWorksummaryDto.setOrganName(organ.getOrganName());
			secrecyWorksummaryDto.setNumKeyPart(Integer.parseInt(map.get("num_keyPart").toString()));
			secrecyWorksummaryDto.setNumKeysection(Integer.parseInt(map.get("num_keysection").toString()));
			secrecyWorksummaryDto.setNumPersonGroupMember(Integer.parseInt(map.get("num_personGroupMember").toString()));
			secrecyWorksummaryDto.setNumSecrecyPerson(Integer.parseInt(map.get("num_secrecyPerson").toString()));
			secrecyWorksummaryDto.setNumSecrecyWorkOrganMember(Integer.parseInt(map.get("num_secrecyWorkOrganMember").toString()));
			list.add(secrecyWorksummaryDto);
		}
		return list;
	}

	/**
	 * @param secrecyWorksummarySql 设置secrecyWorksummarySql
	 */
	public void setSecrecyWorksummarySql(String secrecyWorksummarySql) {
		this.secrecyWorksummarySql = secrecyWorksummarySql;
	}
}