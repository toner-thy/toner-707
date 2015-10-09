
package com.cdthgk.disclosesecrecy.collector;

import java.util.ArrayList;
import java.util.List;

import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.stat.model.impl.DistrictListTable;
import com.cdthgk.disclosesecrecy.dto.DiscloseSecrecyStatDto;
import com.cdthgk.disclosesecrecy.service.DiscloseSecrecyService;
import com.cdthgk.disclosesecrecy.vo.CaseCriticalviolation;
import com.cdthgk.model.form.Parameter;
import com.cdthgk.view.web.ParameterCollector;

/**
 * <p>
 * 区县综合统计
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 *
 * @author
 */
public class CaseCriticalviolationDistrictListTableCollector implements ParameterCollector<List<DistrictListTable>>{

	private DiscloseSecrecyService discloseSecrecyService;
	public DiscloseSecrecyService getDiscloseSecrecyService() {
		return discloseSecrecyService;
	}
	public void setDiscloseSecrecyService(
			DiscloseSecrecyService discloseSecrecyService) {
		this.discloseSecrecyService = discloseSecrecyService;
	}
	/**
	 * 区县综合统计
	 */
	@Override
	public List<DistrictListTable> getData(Parameter param) {
		List<DistrictListTable> districtListTableList = new ArrayList<DistrictListTable>();
		//参数
		Organ organ = (Organ)param.getVariable("organ");
		District district = (District)param.getVariable("district");
		////通过行政区划  统计   行政区划  和直辖单位的要害部门个数
		List<DiscloseSecrecyStatDto> keySectionStatDtoList = discloseSecrecyService.queryDiscloseSecrecyByDistrict(new ArrayList<District>(district.getChildren()), true, organ,CaseCriticalviolation.class.getName());
		DistrictListTable districtTable = new DistrictListTable();
		districtTable.setTotalText("严重违规案件");
		districtListTableList.add(districtTable);
		for (DiscloseSecrecyStatDto keySection : keySectionStatDtoList) {
			DistrictListTable districtListTable = new DistrictListTable();
			districtListTable.setTotalText(keySection.getAllTotal().toString());
			districtListTableList.add(districtListTable);
		}
		return districtListTableList;
	}




}
