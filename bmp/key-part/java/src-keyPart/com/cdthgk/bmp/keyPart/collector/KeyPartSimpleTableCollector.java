package com.cdthgk.bmp.keyPart.collector;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.stat.model.impl.SimpleTable;
import com.cdthgk.bmp.keyPart.service.PartService;
import com.cdthgk.bmp.secrecycountryitem.service.SecrecyCountryItemService;
import com.cdthgk.model.form.Parameter;
import com.cdthgk.view.web.ParameterCollector;

/**
 * <p>
 * 数据收集器，通过行政区划对象，统计出该行政区划下面，要害部位的总数
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 *
 * @author 梁文杰 2013-08-27
 */
public class KeyPartSimpleTableCollector implements ParameterCollector<SimpleTable>{

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
	 * 通过  (行政区划)对象  查询该行政区划下面     要害部位的总数
	 * 这个方法  直接算出   要害部位的总数
	 */
	public SimpleTable getData(Parameter param) {

		SimpleTable simpleTable = new SimpleTable();
		// 获取传入参数
		District district = (District)param.getVariable("district");
		List<District> districtList = new ArrayList<District>();
		districtList.add(district);

		//通过  (行政区划)对象  查询 该行政区划下面     要害部位总数
		Integer total = partService.getBasicPartTotal(district, 1);;

		//设置值
		simpleTable.setTitle("要害部位");//下面的action是查看 要害部位个数 的明细列表  包括当前行政区划 和下级行政区划
		simpleTable.setActionURL("/bmp/part/zhtj_query_Detail.action?district.DistrictCode="+ district.getDistrictCode());
		simpleTable.setTotalText(total);

		return simpleTable;
	}

}
