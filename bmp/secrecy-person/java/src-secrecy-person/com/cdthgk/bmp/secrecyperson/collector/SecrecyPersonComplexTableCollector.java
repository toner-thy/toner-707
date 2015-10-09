package com.cdthgk.bmp.secrecyperson.collector;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.cdthgk.bmp.secrecycountryitem.qo.ZongHeTongJiStatDto;
import com.cdthgk.bmp.secrecycountryitem.service.SecrecyCountryItemService;
import com.cdthgk.bmp.secrecyperson.service.SecrecyPersonModuleService;
import com.cdthgk.model.form.Parameter;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.stat.model.impl.ComplexTable;
import com.cdthgk.view.web.ParameterCollector;

/**
 * <p>
 * 通过行政区划  统计   行政区划和直辖单位的 涉密人员个数   这里不需要合计行
 *  这里的查询出来的数据只有一排哦  亲
 *
 *  //查询出来的列
 *  district_name(行政区划名字)
 *  district_code(行政区划编码)
 *  a1 a2 a3 (按照行政区划统计出的个数信息)
 *  b1 b2 b3 (按照直辖单位统计出的个数信息)
 *
 * 行政区划:按照密级划分 ,密级字段的值=1,  那么查询出来的列所对应的列名就是a1
 * 直辖单位:按照密级划分 ,密级字段的值=1,  那么查询出来的列所对应的列名就是b1
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 *
 * @author
 */
public class SecrecyPersonComplexTableCollector implements ParameterCollector<ComplexTable>{

	/*** Spring依赖注入 服务层注入 ***/
	private SecrecyPersonModuleService secrecyPersonModuleService;

	/**
	 * 通过行政区划  统计   行政区划和直辖单位的商业秘密事项个数
	 * 不需要合计行
	 */
	@Override
	public ComplexTable getData(Parameter param) {

		ComplexTable complexTable = new ComplexTable();
		// 获取传入参数
		District district = (District)param.getVariable("district");

		List<District> districtList = new ArrayList<District>();
		districtList.add(district);

		//通过行政区划  统计   行政区划和直辖单位的 涉密人员个数   这里不需要合计
		List<ZongHeTongJiStatDto> list = secrecyPersonModuleService.count_SecrecyPerson_District(districtList, false);
		Iterator<ZongHeTongJiStatDto> it = list.iterator();

		while(it.hasNext()){
			ZongHeTongJiStatDto item = it.next();
			//表头
			complexTable.setTitle("涉密人员");
			complexTable.setDistrictName(item.getName());
			complexTable.setSecrecyText("密 级");
			complexTable.setNumberText("数 量");
			//密级=1
			complexTable.setSecrecyLevel1("核心");
			complexTable.setSecrecyNum1(item.getLayerSecrecyLevel1());// 直辖数量
			complexTable.setSecrecyNum2(item.getDistrictSecrecyLevel1());	// 行政区数量
			//密级=2
			complexTable.setSecrecyLevel2("重要");
			complexTable.setSecrecyNum3(item.getLayerSecrecyLevel2());// 直辖数量
			complexTable.setSecrecyNum4(item.getDistrictSecrecyLevel2());	// 行政区数量
			//密级=3
			complexTable.setSecrecyLevel3("一般");
			complexTable.setSecrecyNum5(item.getLayerSecrecyLevel3());// 直辖数量
			complexTable.setSecrecyNum6(item.getDistrictSecrecyLevel3());// 行政区数量
			//合计
			complexTable.setTotalText("合 计");
			complexTable.setTotalNum1(item.getLayerTotal());	// 直辖合计
			complexTable.setTotalNum2(item.getDistrictTotal());	// 行政区合计
		}

		return complexTable;
	}

	/*************************************** getter and setter ***************************************************************/
	/**
	 * @return the secrecyPersonModuleService
	 */
	public SecrecyPersonModuleService getSecrecyPersonModuleService() {
		return secrecyPersonModuleService;
	}

	/**
	 * @param secrecyPersonModuleService the secrecyPersonModuleService to set
	 */
	public void setSecrecyPersonModuleService(
			SecrecyPersonModuleService secrecyPersonModuleService) {
		this.secrecyPersonModuleService = secrecyPersonModuleService;
	}

}
