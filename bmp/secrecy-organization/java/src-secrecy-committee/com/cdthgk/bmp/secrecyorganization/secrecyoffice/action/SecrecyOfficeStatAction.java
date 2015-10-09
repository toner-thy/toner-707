package com.cdthgk.bmp.secrecyorganization.secrecyoffice.action;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.action.BmpAction;
import com.cdthgk.bmp.secrecyorganization.secrecyoffice.dto.ObjectNumberFomart;
import com.cdthgk.bmp.secrecyorganization.secrecyoffice.service.SecrecyOfficeModuleService;
import com.cdthgk.bmp.secrecyorganization.secrecyoffice.vo.EmployPerson;
import com.cdthgk.bmp.secrecyorganization.secrecyoffice.vo.EstablishSituation;
import com.cdthgk.bmp.secrecyorganization.secrecyoffice.vo.LeaderStaff;
import com.cdthgk.bmp.secrecyorganization.secrecyoffice.vo.SecrecyOffice;
import com.cdthgk.common.lang.StringUtils;
import com.cdthgk.platform.district.domain.District;

/**
 * <p>
 * 保密办（保密局） Action 类
 * </p>
 * <p>
 * 陶汇源 2012-12-24 17:43:03
 * </p>
 * <blockquote>
 * <h4>历史修改记录</h4>
 * <ul>
 * <li>修改人 修改时间 修改描述
 * </ul>
 * </blockquote>
 * <p>
 * copyright taohy 2012, all rights reserved.
 * </p>
 *
 * @author FastCodeingTools
 * @author taohy
 * @since 1.0
 * @version 1.0
 * @param <E>
 */
@SuppressWarnings("unchecked")
public class SecrecyOfficeStatAction<E> extends BmpAction {

	private static final long serialVersionUID = -2561934305983981072L;

	// 通用字段
	private SecrecyOfficeModuleService secrecyOfficeModuleService;
	private District district;
	private String districtCode;
	// 行政区 和 本单位
	boolean flag = false;


	// *********************** 方 法 ***********************

	// 构造器
	/** 默认构造器 */
	public SecrecyOfficeStatAction() {

	}

	/**
	 *
	 * <p>
	 * 保密行政管理部门人员编制情况统计
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-7-20 - 下午3:51:16
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @return
	 */
	public String statEstablishSituation(){
		districtCode = getRequest().getParameter("districtCode");
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = secrecyOfficeModuleService.get(districtCode, District.class);
			flag = true;
		}
		if(getRequest().getParameter("flag") != null){
			if(getRequest().getParameter("flag").equals("false")){
				flag = false;
			}
		}
		List<EmployPerson> employPersonList = (List<EmployPerson>) secrecyOfficeModuleService.queryEstablishSituation("EmployPerson", district, flag);
		List<EstablishSituation> establishSituationList = (List<EstablishSituation>) secrecyOfficeModuleService.queryEstablishSituation("EstablishSituation", district, flag);
		List<LeaderStaff> leaderStaffList = (List<LeaderStaff>) secrecyOfficeModuleService.queryEstablishSituation("LeaderStaff", district, flag);
		// 如果集合为空，需要初始化一次，把集合中的值设为0
		SecrecyOffice secrecyOffice = new SecrecyOffice();
		secrecyOffice.setEmployPersonSet(new HashSet<EmployPerson>(employPersonList));
		secrecyOffice.setEstablishSituationSet(new HashSet<EstablishSituation>(establishSituationList));
		secrecyOffice.setLeaderStaffSet(new HashSet<LeaderStaff>(leaderStaffList));
		secrecyOfficeModuleService.initEstablishSituation(secrecyOffice);

		putToRequest("employPersonList", secrecyOffice.getEmployPersonSet());
		putToRequest("establishSituationList", secrecyOffice.getEstablishSituationSet());
		putToRequest("leaderStaffList", secrecyOffice.getLeaderStaffSet());
		putToRequest("flag", flag);
		return SUCCESS;
	}

	/**
	 *
	 * <p>
	 * 行政区下所有单位保密行政管理部门人员编制情况
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-7-23 - 下午1:36:39
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @return
	 */
	public String statEstablishSituationOrgan() {
		districtCode = getRequest().getParameter("districtCode");
		if (districtCode == null) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = secrecyOfficeModuleService.get(districtCode, District.class);
		}
		putToRequest("organList", secrecyOfficeModuleService.getOrganListByDistrict(district));
		return SUCCESS;
	}

	/**
	 *
	 * <p>
	 * 保密行政管理部门人员在编人员情况
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-7-22 - 上午11:31:16
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @return
	 */
	public String statOnlineSituation() {
		districtCode = getRequest().getParameter("districtCode");
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = secrecyOfficeModuleService.get(districtCode, District.class);
			flag = true;
		}
		if(getRequest().getParameter("flag") != null){
			if(getRequest().getParameter("flag").equals("false")){
				flag = false;
			}
		}
		putToRequest("yearMap", secrecyOfficeModuleService.queryYearMap(district, flag));
		putToRequest("diplomaMap", secrecyOfficeModuleService.queryDiplomaMap(district, flag));
		putToRequest("specialMap", secrecyOfficeModuleService.querySpecialMap(district, flag));
		putToRequest("postTypeMap", secrecyOfficeModuleService.queryPostTypeMap(district, flag));
		putToRequest("flag", flag);
		return SUCCESS;
	}

	/**
	 *
	 * <p>
	 * 行政区下所有单位保密行政管理部门人员在编人员情况
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-7-23 - 下午1:42:20
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @return
	 */
	public String statOnlineSituationOrgan() {
		districtCode = getRequest().getParameter("districtCode");
		if (districtCode == null) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = secrecyOfficeModuleService.get(districtCode, District.class);
		}
		putToRequest("organList", secrecyOfficeModuleService.getOrganListByDistrict(district));
		return SUCCESS;
	}

	/**
	 *
	 * <p>
	 * 保密局基础设施统计
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-7-23 - 上午10:03:16
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @return
	 */
	public String statInfrastructure() {
		districtCode = getRequest().getParameter("districtCode");
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = secrecyOfficeModuleService.get(districtCode, District.class);
			flag = true;
		}
		if(getRequest().getParameter("flag") != null){
			if(getRequest().getParameter("flag").equals("false")){
				flag = false;
			}
		}
		Map<String, Integer> infrastructureMap = secrecyOfficeModuleService.queryInfrastructure(district, flag);
		putToRequest("infrastructureMap", infrastructureMap);
		putToRequest("flag", flag);
		return SUCCESS;
	}
	/**
	 *
	 * <p>
	 * 行政区下所有单位基础设施统计
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-7-23 - 下午1:37:44
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @return
	 */
	public String statInfrastructureOrgan() {
		districtCode = getRequest().getParameter("districtCode");
		if (districtCode == null) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = secrecyOfficeModuleService.get(districtCode, District.class);
		}
		putToRequest("organList", secrecyOfficeModuleService.getOrganListByDistrict(district));
		return SUCCESS;
	}

	/**
	 *
	 * <p>
	 * 导出人员编制情况
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-7-25 - 上午10:03:28
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @return
	 */
	public String exportEstablishSituation() {
		Map<String, Object> params = new HashMap<String, Object>();
		districtCode = getRequest().getParameter("districtCode");
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = secrecyOfficeModuleService.get(districtCode, District.class);
			flag = true;
		}
		if(getRequest().getParameter("flag") != null){
			if(getRequest().getParameter("flag").equals("false")){
				flag = false;
			}
		}
		List<EmployPerson> employPersonList = (List<EmployPerson>) secrecyOfficeModuleService.queryEstablishSituation("EmployPerson", district, flag);
		List<EstablishSituation> establishSituationList = (List<EstablishSituation>) secrecyOfficeModuleService.queryEstablishSituation("EstablishSituation", district, flag);
		List<LeaderStaff> leaderStaffList = (List<LeaderStaff>) secrecyOfficeModuleService.queryEstablishSituation("LeaderStaff", district, flag);
		// 如果集合为空，需要初始化一次，把集合中的值设为0
		SecrecyOffice secrecyOffice = new SecrecyOffice();
		secrecyOffice.setEmployPersonSet(new HashSet<EmployPerson>(employPersonList));
		secrecyOffice.setEstablishSituationSet(new HashSet<EstablishSituation>(establishSituationList));
		secrecyOffice.setLeaderStaffSet(new HashSet<LeaderStaff>(leaderStaffList));
		secrecyOfficeModuleService.initEstablishSituation(secrecyOffice);
		params.put("employPersonList", secrecyOffice.getEmployPersonSet());
		params.put("establishSituationList", secrecyOffice.getEstablishSituationSet());
		params.put("leaderStaffList", secrecyOffice.getLeaderStaffSet());
		params.put("name", district.getDistrictName());
		ObjectNumberFomart onf = new ObjectNumberFomart();
		params.put("onf", onf);
		setResultData(params);
		return SUCCESS;
	}

	/**
	 *
	 * <p>
	 * 导出在编人员情况
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-7-25 - 上午10:03:28
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @return
	 */
	public String exportOnlineSituation() {
		Map<String, Object> params = new HashMap<String, Object>();
		districtCode = getRequest().getParameter("districtCode");
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = secrecyOfficeModuleService.get(districtCode, District.class);
			flag = true;
		}
		if(getRequest().getParameter("flag") != null){
			if(getRequest().getParameter("flag").equals("false")){
				flag = false;
			}
		}
		params.put("yearMap", secrecyOfficeModuleService.queryYearMap(district, flag));
		params.put("diplomaMap", secrecyOfficeModuleService.queryDiplomaMap(district, flag));
		params.put("specialMap", secrecyOfficeModuleService.querySpecialMap(district, flag));
		params.put("postTypeMap", secrecyOfficeModuleService.queryPostTypeMap(district, flag));
		params.put("name", district.getDistrictName());
		ObjectNumberFomart onf = new ObjectNumberFomart();
		params.put("onf", onf);
		setResultData(params);
		return SUCCESS;
	}

	/**
	 *
	 * <p>
	 * 导出基础设施
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-7-25 - 下午1:32:58
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @return
	 */
	public String exportInfrastructure() {
		Map<String, Object> params = new HashMap<String, Object>();
		districtCode = getRequest().getParameter("districtCode");
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = secrecyOfficeModuleService.get(districtCode, District.class);
			flag = true;
		}
		if(getRequest().getParameter("flag") != null){
			if(getRequest().getParameter("flag").equals("false")){
				flag = false;
			}
		}
		params.put("infrastructureMap", secrecyOfficeModuleService.queryInfrastructure(district, flag));
		params.put("name", district.getDistrictName());
		ObjectNumberFomart onf = new ObjectNumberFomart();
		params.put("onf", onf);
		setResultData(params);
		return SUCCESS;
	}
	/**
	 * @param secrecyOfficeModuleService 设置secrecyOfficeModuleService
	 */
	public void setSecrecyOfficeModuleService(
			SecrecyOfficeModuleService secrecyOfficeModuleService) {
		this.secrecyOfficeModuleService = secrecyOfficeModuleService;
	}

	/**
	 * @return 返回district
	 */
	public District getDistrict() {
		return district;
	}

	/**
	 * @param district 设置district
	 */
	public void setDistrict(District district) {
		this.district = district;
	}

	/**
	 * @return 返回districtCode
	 */
	public String getDistrictCode() {
		return districtCode;
	}

	/**
	 * @param districtCode 设置districtCode
	 */
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	/**
	 * @return 返回flag
	 */
	public boolean isFlag() {
		return flag;
	}

	/**
	 * @param flag 设置flag
	 */
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
}