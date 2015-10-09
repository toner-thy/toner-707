package com.cdthgk.bmp.secrecyperson.collector;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.secrecycountryitem.service.SecrecyCountryItemService;
import com.cdthgk.bmp.secrecyperson.service.SecrecyPersonModuleService;
import com.cdthgk.model.form.Parameter;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.stat.model.impl.SimpleTable;
import com.cdthgk.view.web.ParameterCollector;

/**
 * <p>
 * 数据收集器，通过行政区划对象，统计出该行政区划下面，涉密人员的总数
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 *
 * @author 梁文杰 2013-08-27
 */
public class SecrecyPersonSimpleTableCollector implements ParameterCollector<SimpleTable>{

	/***** Spring依赖注入,  服务层注入  ***/
	private SecrecyPersonModuleService secrecyPersonModuleService; //涉密人员的service

	/**
	 * 通过  (行政区划)对象  查询该行政区划下面     涉密人员的总数
	 * 这个方法  直接算出   涉密人员的总数
	 */
	public SimpleTable getData(Parameter param) {

		SimpleTable simpleTable = new SimpleTable();
		// 获取传入参数
		District district = (District)param.getVariable("district");
		List<District> districtList = new ArrayList<District>();
		districtList.add(district);

		//通过  (行政区划)对象  查询 该行政区划下面     涉密人员总数
		Integer total = new Integer(0);
		Map<String, Object> cmap = secrecyPersonModuleService.getSecrecyPerson_Total_District(district, 1);
		if(cmap!=null) {
			Object oc = cmap.get("total");
			if(oc!=null) {
				total = Integer.parseInt(oc.toString());
			}
		}

		//设置值
		simpleTable.setTitle("涉密人员");//下面的action是查看 涉密人员个数 的明细列表  包括当前行政区划 和下级行政区划
		simpleTable.setActionURL("/bmp/secrecyperson/zhtj_query_Detail.action?district.DistrictCode="+ district.getDistrictCode());
		simpleTable.setTotalText(total);

		return simpleTable;
	}

	/*****************************getter and setter*******************************************/
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
