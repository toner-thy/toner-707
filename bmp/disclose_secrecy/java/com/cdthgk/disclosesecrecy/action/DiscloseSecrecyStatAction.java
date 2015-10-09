package com.cdthgk.disclosesecrecy.action;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.disclosesecrecy.dto.DiscloseSecrecyStatDto;
import com.cdthgk.disclosesecrecy.service.DiscloseSecrecyService;
import com.cdthgk.disclosesecrecy.vo.DiscloseSecrecy;
import com.cdthgk.platform.base.action.PlatformAction;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.district.service.DistrictService;
import com.cdthgk.platform.organization.organ.domain.Organ;
/**
 * 泄密案件统计Action
 * @author 刘兵兵 2013-8-29
 *
 */
public class DiscloseSecrecyStatAction extends PlatformAction {
	private static final long serialVersionUID = 1L;
	private District district;
	private DiscloseSecrecyService discloseSecrecyService;
	//行政区划服务层
	private DistrictService districtService;
	private String className;
	private String zhiquyu;
	/***************************************综合统计*******************************************/
	/**
	 * 泄密事件统计详情（展示该行政区域和子区域）--行政区划和行政区内
	 * @return
	 */
	public String zhtj_query_Detail(){
		deal_result(true,true);
		return SUCCESS;
	}

	//查询界面
	public String zhtj_query(){
		district = this.getCurrentUser().getOrgan().getDistrict();
		deal_result(true,true);
		return SUCCESS;
	}

	/**
	 * 综合统计  通过行政区划编码  统计该行政区划泄密事件的统计
	 * 一个单位一排数据，同时这个action提供了通过单位的名称模糊查询的功能。
	 *
	 * @return
	 */
	public String zhtj_queryByDistrict() {
		//这里只会得到  行政区划编码，没有单位的信息
		district = districtService.get(district.getDistrictCode());
		String organName = this.getRequest().getParameter("organ.organName");
		Organ organ = null;
		if(organName!=null && !"".equals(organName)) {
			organ = new Organ();
			organ.setOrganName(organName);
		}
		List<DiscloseSecrecyStatDto> secrecyCountryItemStatDtoList = discloseSecrecyService.queryDiscloseSecrecyByOrgan(district,true,organ, className);
		putToRequest("secrecyCountryItemStatDtoList", secrecyCountryItemStatDtoList);
		putToRequest("district", district);
		putToRequest("topDistrict", this.getCurrentUser().getOrgan().getDistrict());
		//putToRequest("className", className);
		return SUCCESS;
	}

	/***************************************综合统计导出*******************************************/
	public String export(){
		if (zhiquyu.equals("true")) {

			deal_result(false,true);
		}else{
			deal_result(false,false);

		}
		if (className.equals(DiscloseSecrecy.class.getName())) {
			return "discloseSecrecy";

		}else {

			return "case";
		}
	}
	public void deal_result(boolean showOrExport,boolean zhiquyu)
	{
		district = districtService.get(district.getDistrictCode());
		//处理行政区划
		List<District> districtList = new ArrayList<District>();
		List<District> childrenDistrictList =  new ArrayList<District>(district.getChildren());//所选择的下一级区域
		districtList.add(district);
		Organ organ = this.getCurrentUser().getOrgan();
		if (showOrExport) {
			//查询  当前行政区划的 明细
			List<DiscloseSecrecyStatDto> keySectionStatDtoList = discloseSecrecyService.queryDiscloseSecrecyByDistrict(districtList, false, organ,className);
			//查询  子行政区划的明细
			List<DiscloseSecrecyStatDto> childrenkeySectionStatDtoList = discloseSecrecyService.queryDiscloseSecrecyByDistrict(childrenDistrictList, true, organ,className);
			putToRequest("keySectionStatDtoList", keySectionStatDtoList);
			putToRequest("distictList", childrenkeySectionStatDtoList);
			putToRequest("districtCode", district.getDistrictCode());
			putToRequest("className", className);
			putToRequest("topDistrict", this.getCurrentUser().getOrgan().getDistrict());

		}else {
			Map<String, Object> params = new HashMap<String, Object>();
			StringBuffer nameString=new StringBuffer("【"+district.getName()+"】");
			if (className.equals("1")) {
				className=DiscloseSecrecy.class.getName();
				nameString.append(" 泄密案件统计");
			}
			else {
				className=CaseCriticalviolationAction.class.getName();
				nameString.append(" 严重违规案件统计");
			}
			if (zhiquyu) {
				//被导出的数据
				List<DiscloseSecrecyStatDto> childrenkeySectionStatDtoList = discloseSecrecyService.queryDiscloseSecrecyByDistrict(childrenDistrictList, true, organ,className);
				params.put("childrenkeySectionStatDtoList", childrenkeySectionStatDtoList);
				nameString.append("(行政区内)");
			}else{
				List<DiscloseSecrecyStatDto> keySectionStatDtoList = discloseSecrecyService.queryDiscloseSecrecyByDistrict(districtList, false, organ,className);
				params.put("childrenkeySectionStatDtoList", keySectionStatDtoList);
				nameString.append("(行政区划)");
			}
			params.put("name", nameString);
			setResultData(params);
		}
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public DistrictService getDistrictService() {
		return districtService;
	}

	public void setDistrictService(DistrictService districtService) {
		this.districtService = districtService;
	}

	public DiscloseSecrecyService getDiscloseSecrecyService() {
		return discloseSecrecyService;
	}

	public void setDiscloseSecrecyService(
			DiscloseSecrecyService discloseSecrecyService) {
		this.discloseSecrecyService = discloseSecrecyService;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	public String getZhiquyu() {
		return zhiquyu;
	}
	public void setZhiquyu(String zhiquyu) {
		this.zhiquyu = zhiquyu;
	}


}
