package com.cdthgk.bmp.secrecysystem.secrecyotherdevice.collector;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.secrecysystem.secrecycomputer.service.SecrecyComputerService;
import com.cdthgk.bmp.secrecysystem.secrecyotherdevice.service.SecrecyOthersService;
import com.cdthgk.model.form.Parameter;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.stat.model.impl.SimpleTable;
import com.cdthgk.view.web.ParameterCollector;

/**
 * <p>
 * 数据收集器，通过行政区划对象，统计出该行政区划下面，其他涉密设备的总数
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 *
 * @author 梁文杰 2013-08-27
 */
public class SecrecyOthersSimpleTableCollector implements ParameterCollector<SimpleTable>{

	/***** Spring依赖注入,  服务层注入  ***/
	private SecrecyOthersService secrecyOthersService; //其他涉密设备的service

	/**
	 * 通过  (行政区划)对象  查询该行政区划下面    其他涉密设备的总数
	 * 这个方法  直接算出  其他涉密设备的总数
	 */
	public SimpleTable getData(Parameter param) {

		SimpleTable simpleTable = new SimpleTable();
		// 获取传入参数
		District district = (District)param.getVariable("district");
		List<District> districtList = new ArrayList<District>();
		districtList.add(district);

		//通过  (行政区划)对象  查询 该行政区划下面    其他涉密设备总数
		Integer total = new Integer(0);
		Map<String, Object> cmap = secrecyOthersService.getSecrecyOthers_Total_District(district, 1);
		if(cmap!=null) {
			Object oc = cmap.get("total");
			if(oc!=null) {
				total = Integer.parseInt(oc.toString());
			}
		}

		//设置值
		simpleTable.setTitle("其他涉密设备");//下面的action是查看其他涉密设备个数 的明细列表  包括当前行政区划 和下级行政区划
		simpleTable.setActionURL("/secrecysystem/secrecyothers/zhtj_query_Detail.action?district.DistrictCode="+ district.getDistrictCode());
		simpleTable.setTotalText(total);

		return simpleTable;
	}

	/*****************************getter and setter*******************************************/
	/**
	 * @return the secrecyOthersService
	 */
	public SecrecyOthersService getSecrecyOthersService() {
		return secrecyOthersService;
	}

	/**
	 * @param secrecyOthersService the secrecyOthersService to set
	 */
	public void setSecrecyOthersService(SecrecyOthersService secrecyOthersService) {
		this.secrecyOthersService = secrecyOthersService;
	}



}
