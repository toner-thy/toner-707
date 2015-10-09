
package com.cdthgk.bmp.keysection.collector;

import java.util.ArrayList;
import java.util.List;

import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.stat.model.impl.ComplexTable;
import com.cdthgk.bmp.keysection.dto.KeySectionStatDto;
import com.cdthgk.bmp.keysection.service.KeySectionService;
import com.cdthgk.model.form.Parameter;
import com.cdthgk.view.web.ParameterCollector;

/**
 * <p>
 * 类的说明放这里
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 *
 * @author
 */
public class KeySectionComplexTableCollector implements ParameterCollector<ComplexTable>{

	private KeySectionService keySectionService;

	public KeySectionService getKeySectionService() {
		return keySectionService;
	}

	public void setKeySectionService(KeySectionService keySectionService) {
		this.keySectionService = keySectionService;
	}

	/**
	 * //通过行政区划  统计   行政区划  和直辖单位的要害部门个数
	 */
	@Override
	public ComplexTable getData(Parameter param) {

		ComplexTable complexTable = new ComplexTable();
		// 获取传入参数
		Organ organ = (Organ)param.getVariable("organ");
		District district = (District)param.getVariable("district");

		List<District> districtList = new ArrayList<District>();
		districtList.add(district);

		//通过行政区划  统计   行政区划  和直辖单位的要害部门个数
		List<KeySectionStatDto> keySectionStatDtoList = keySectionService.queryKeySectionByDistrict(districtList, false, organ);

		for (KeySectionStatDto keySection : keySectionStatDtoList) {
			complexTable.setTitle("要害部门");
			complexTable.setDistrictName(keySection.getName());
			complexTable.setSecrecyText("密 级");
			complexTable.setNumberText("数 量");
			complexTable.setSecrecyLevel1("核 心");
			// 直辖数量
			complexTable.setSecrecyNum1(keySection.getNum1());
			// 行政区数量
			complexTable.setSecrecyNum2(keySection.getNum4());
			complexTable.setSecrecyLevel2("重 要");
			// 直辖数量
			complexTable.setSecrecyNum3(keySection.getNum2());
			// 行政区数量
			complexTable.setSecrecyNum4(keySection.getNum5());
			complexTable.setSecrecyLevel3("一 般");
			// 直辖数量
			complexTable.setSecrecyNum5(keySection.getNum3());
			// 行政区数量
			complexTable.setSecrecyNum6(keySection.getNum6());
			complexTable.setTotalText("合 计");
			// 直辖合计
			complexTable.setTotalNum1(keySection.getSelfTotal());
			// 行政区合计
			complexTable.setTotalNum2(keySection.getAllTotal());
		}

		return complexTable;
	}


}
