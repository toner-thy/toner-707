package com.cdthgk.disclosesecrecy.collector;

import java.util.ArrayList;
import java.util.List;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.stat.model.impl.SimpleTable;
import com.cdthgk.disclosesecrecy.service.DiscloseSecrecyService;
import com.cdthgk.disclosesecrecy.vo.DiscloseSecrecy;
import com.cdthgk.model.form.Parameter;
import com.cdthgk.view.web.ParameterCollector;

/**
 * <p>
 * 数据收集器，通过行政区划对象，统计出该行政区划下面，泄密事件的总数
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 *
 * @author 刘兵兵 2013-08-27
 */
public class DiscloseSecrecySimpleTableCollector implements ParameterCollector<SimpleTable>{

	private DiscloseSecrecyService discloseSecrecyService;
	/**
	 * 通过  (行政区划)对象  查询该行政区划下面     国家秘密事项的总数
	 * 这个方法  直接算出   国家秘密事项的总数
	 */
	@Override
	public SimpleTable getData(Parameter param) {

		SimpleTable simpleTable = new SimpleTable();
		// 获取传入参数
		District district = (District)param.getVariable("district");
		List<District> districtList = new ArrayList<District>();
		districtList.add(district);

		//通过  (行政区划)对象  查询 该行政区划下面     国家秘密事项总数
		Integer total = new Integer(0);
		/*Map<String, Object> cmap = secrecyCountryItemService.getSecrecyCountryItim_Total_District(district, 1);
		if(cmap!=null) {
			Object oc = cmap.get("total");
			if(oc!=null) {
				total = Integer.parseInt(oc.toString());
			}
		}*/
		total = discloseSecrecyService.getDiscloseSecrecyTotal(null, DiscloseSecrecy.class.getName(), null, district.getDistrictCode(), false);

		//设置值
		simpleTable.setTitle("泄密案件");//下面的action是查看 国家秘密事项个数 的明细列表  包括当前行政区划 和下级行政区划
		simpleTable.setActionURL("/bmp/discloseSecrecy/zhtj_query_Detail.action?className="+DiscloseSecrecy.class.getName()+"&&district.districtCode="+ district.getDistrictCode());
		simpleTable.setTotalText(total);

		return simpleTable;
	}
	public DiscloseSecrecyService getDiscloseSecrecyService() {
		return discloseSecrecyService;
	}
	public void setDiscloseSecrecyService(
			DiscloseSecrecyService discloseSecrecyService) {
		this.discloseSecrecyService = discloseSecrecyService;
	}





}
