package com.cdthgk.bmp.secrecyproducts.collector;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.stat.model.impl.SimpleTable;
import com.cdthgk.bmp.secrecyproducts.service.SecrecyProductsService;
import com.cdthgk.model.form.Parameter;
import com.cdthgk.view.web.ParameterCollector;

/**
 * <p>
 * 数据收集器，通过行政区划对象，统计出该行政区划下面，密品的总数
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 *
 * @author 梁文杰 2013-08-27
 */
public class SecrecyProductsSimpleTableCollector implements ParameterCollector<SimpleTable>{

	/***** Spring依赖注入,  服务层注入  ***/
	private SecrecyProductsService secrecyProductsService; //密品的service


	/**
	 * 通过  (行政区划)对象  查询该行政区划下面     密品的总数
	 * 这个方法  直接算出   密品的总数
	 */
	public SimpleTable getData(Parameter param) {

		SimpleTable simpleTable = new SimpleTable();
		// 获取传入参数
		District district = (District)param.getVariable("district");
		List<District> districtList = new ArrayList<District>();
		districtList.add(district);

		//通过  (行政区划)对象  查询 该行政区划下面     密品总数
		Integer total = new Integer(0);
		Map<String, Object> cmap = secrecyProductsService.getSecrecyProducts_Total_District(district, 1);
		if(cmap!=null) {
			Object oc = cmap.get("total");
			if(oc!=null) {
				total = Integer.parseInt(oc.toString());
			}
		}

		//设置值
		simpleTable.setTitle("密品");//下面的action是查看 密品个数 的明细列表  包括当前行政区划 和下级行政区划
		simpleTable.setActionURL("/bmp/secrecyProducts/zhtj_query_Detail.action?district.DistrictCode="+ district.getDistrictCode());
		simpleTable.setTotalText(total);

		return simpleTable;
	}



	/*****************************getter and setter*******************************************/
	public SecrecyProductsService getSecrecyProductsService() {
		return secrecyProductsService;
	}

	public void setSecrecyProductsService(
			SecrecyProductsService secrecyProductsService) {
		this.secrecyProductsService = secrecyProductsService;
	}

}
