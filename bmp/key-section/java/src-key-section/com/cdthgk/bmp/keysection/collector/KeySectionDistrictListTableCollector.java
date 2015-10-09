
package com.cdthgk.bmp.keysection.collector;

import java.util.ArrayList;
import java.util.List;

import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.stat.model.impl.DistrictListTable;
import com.cdthgk.bmp.keysection.dto.KeySectionStatDto;
import com.cdthgk.bmp.keysection.service.KeySectionService;
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
public class KeySectionDistrictListTableCollector implements ParameterCollector<List<DistrictListTable>>{

	private KeySectionService keySectionService;


	public KeySectionService getKeySectionService() {
		return keySectionService;
	}


	public void setKeySectionService(KeySectionService keySectionService) {
		this.keySectionService = keySectionService;
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
		List<KeySectionStatDto> keySectionStatDtoList = keySectionService.queryKeySectionByDistrict(new ArrayList<District>(district.getChildren()), true, organ);

		DistrictListTable districtTable = new DistrictListTable();
		districtTable.setTotalText("要害部门");
		districtListTableList.add(districtTable);

		for (KeySectionStatDto keySection : keySectionStatDtoList) {
			DistrictListTable districtListTable = new DistrictListTable();
			districtListTable.setTotalText(keySection.getAllTotal().toString());
			districtListTableList.add(districtListTable);
		}
		return districtListTableList;
	}




}
