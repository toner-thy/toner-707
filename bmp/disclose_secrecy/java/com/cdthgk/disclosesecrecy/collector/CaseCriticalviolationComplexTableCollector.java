package com.cdthgk.disclosesecrecy.collector;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.stat.model.impl.ComplexTable;
import com.cdthgk.disclosesecrecy.service.DiscloseSecrecyService;
import com.cdthgk.disclosesecrecy.vo.CaseCriticalviolation;
import com.cdthgk.model.form.Parameter;
import com.cdthgk.view.web.ParameterCollector;


public class CaseCriticalviolationComplexTableCollector implements ParameterCollector<ComplexTable>{
	private DiscloseSecrecyService discloseSecrecyService;
	@Override
	public ComplexTable getData(Parameter param) {

		ComplexTable complexTable = new ComplexTable();
		// 获取传入参数
		District district = (District)param.getVariable("district");
		List<District> districtList = new ArrayList<District>();
		districtList.add(district);
		//通过行政区划  统计   行政区划和直辖单位的  国家秘密事项个数   这里不需要合计
		List<Map<String, Object>> listSecrecy =discloseSecrecyService.countDiscloseSecrecy(null, null, "secrecy_level",
				CaseCriticalviolation.class.getName(), district.getDistrictCode(), false);//保密局
		List<Map<String, Object>> listZhixiaSecrecy =discloseSecrecyService.countDiscloseSecrecy(null, null, "secrecy_level",
				CaseCriticalviolation.class.getName(), district.getDistrictCode(), true);//直辖单位
		for (int i = 0; i < listSecrecy.size(); i++) {
			Map<String, Object> map = listSecrecy.get(i);
			Map<String, Object> mapZhixia = listZhixiaSecrecy.get(i);
			complexTable.setTitle("严重违规案件");
			complexTable.setDistrictName(district.getName());
			complexTable.setSecrecyText("密 级");
			complexTable.setNumberText("数 量");
			if (i==0) {
				//密级=1
				complexTable.setSecrecyLevel1(map.get("option_text").toString());
				complexTable.setSecrecyNum1(Integer.parseInt(mapZhixia.get("fcount").toString()));// 直辖数量
				complexTable.setSecrecyNum2(Integer.parseInt(map.get("fcount").toString()));	// 行政区数量
			}
			if (i==1) {
				//密级=2
				complexTable.setSecrecyLevel2(map.get("option_text").toString());
				complexTable.setSecrecyNum3(Integer.parseInt(mapZhixia.get("fcount").toString()));// 直辖数量
				complexTable.setSecrecyNum4(Integer.parseInt(map.get("fcount").toString()));	// 行政区数量
			}
			if (i==2) {
				//密级=3
				complexTable.setSecrecyLevel3(map.get("option_text").toString());
				complexTable.setSecrecyNum5(Integer.parseInt(mapZhixia.get("fcount").toString()));// 直辖数量
				complexTable.setSecrecyNum6(Integer.parseInt(map.get("fcount").toString()));// 行政区数量
			}
			if (i==3) {
				//合计
				complexTable.setTotalText("合 计");
			}
		}
		return complexTable;
	}

	/*************************************** getter and setter ***************************************************************/
	public DiscloseSecrecyService getDiscloseSecrecyService() {
		return discloseSecrecyService;
	}

	public void setDiscloseSecrecyService(
			DiscloseSecrecyService discloseSecrecyService) {
		this.discloseSecrecyService = discloseSecrecyService;
	}


}
