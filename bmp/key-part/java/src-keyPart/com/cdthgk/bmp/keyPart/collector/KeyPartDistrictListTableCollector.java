package com.cdthgk.bmp.keyPart.collector;

import java.util.ArrayList;
import java.util.List;

import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.stat.model.impl.DistrictListTable;
import com.cdthgk.bmp.keyPart.service.PartService;
import com.cdthgk.bmp.secrecycountryitem.qo.ZongHeTongJiStatDto;
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
public class KeyPartDistrictListTableCollector implements ParameterCollector<List<DistrictListTable>>{

	/*** Spring依赖注入 服务层注入 ***/
	private PartService partService;
	/*************************************** getter and setter ***************************************************************/
	public PartService getPartService() {
		return partService;
	}

	public void setPartService(PartService partService) {
		this.partService = partService;
	}
	/*************************************** getter and setter ***************************************************************/


	/**
	 * 区县综合统计
	 */
	@Override
	public List<DistrictListTable> getData(Parameter param) {

		List<DistrictListTable> districtListTableList = new ArrayList<DistrictListTable>();
		//参数
		District district = (District)param.getVariable("district");
		////通过行政区划  统计   行政区划  和直辖单位的要害部门个数
		List<ZongHeTongJiStatDto> list = partService.count_District(new ArrayList<District>(district.getChildren()), true);

		//设置表头
		DistrictListTable districtTable = new DistrictListTable();
		districtTable.setTotalText("要害部位");
		districtListTableList.add(districtTable);

		//设置列  包括合计列
		for (ZongHeTongJiStatDto stat : list) {
			DistrictListTable districtListTable = new DistrictListTable();
			districtListTable.setTotalText(String.valueOf(stat.getDistrictTotal()));//行政区划的总数
			districtListTableList.add(districtListTable);
		}
		return districtListTableList;
	}


}
