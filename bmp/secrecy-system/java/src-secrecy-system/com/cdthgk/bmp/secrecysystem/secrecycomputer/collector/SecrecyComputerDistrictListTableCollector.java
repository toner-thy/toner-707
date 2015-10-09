package com.cdthgk.bmp.secrecysystem.secrecycomputer.collector;

import java.util.ArrayList;
import java.util.List;

import com.cdthgk.bmp.secrecycountryitem.qo.ZongHeTongJiStatDto;
import com.cdthgk.bmp.secrecycountryitem.service.SecrecyCountryItemService;
import com.cdthgk.bmp.secrecysystem.secrecycomputer.service.SecrecyComputerService;
import com.cdthgk.model.form.Parameter;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.stat.model.impl.DistrictListTable;
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
public class SecrecyComputerDistrictListTableCollector implements ParameterCollector<List<DistrictListTable>>{

	/***** Spring依赖注入,  服务层注入  ***/
	private SecrecyComputerService secrecyComputerService;

	/**
	 * 区县综合统计
	 */
	@Override
	public List<DistrictListTable> getData(Parameter param) {

		List<DistrictListTable> districtListTableList = new ArrayList<DistrictListTable>();
		//参数
		District district = (District)param.getVariable("district");
		////通过行政区划  统计   行政区划  和直辖单位的要害部门个数
		List<ZongHeTongJiStatDto> list = secrecyComputerService.count_SecrecyComputer_District(new ArrayList<District>(district.getChildren()), true);

		//设置表头
		DistrictListTable districtTable = new DistrictListTable();
		districtTable.setTotalText("涉密计算机");
		districtListTableList.add(districtTable);

		//设置列  包括合计列
		for (ZongHeTongJiStatDto stat : list) {
			DistrictListTable districtListTable = new DistrictListTable();
			districtListTable.setTotalText(String.valueOf(stat.getDistrictTotal()));//行政区划的总数
			districtListTableList.add(districtListTable);
		}
		return districtListTableList;
	}

	/*********************************** getter  and  setter **************************************************************************/
	/**
	 * @return the secrecyComputerService
	 */
	public SecrecyComputerService getSecrecyComputerService() {
		return secrecyComputerService;
	}

	/**
	 * @param secrecyComputerService the secrecyComputerService to set
	 */
	public void setSecrecyComputerService(
			SecrecyComputerService secrecyComputerService) {
		this.secrecyComputerService = secrecyComputerService;
	}




}
