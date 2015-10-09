package com.cdthgk.disclosesecrecy.collector;

import java.util.ArrayList;
import java.util.List;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.stat.model.impl.SimpleTable;
import com.cdthgk.disclosesecrecy.service.DiscloseSecrecyService;
import com.cdthgk.disclosesecrecy.vo.CaseCriticalviolation;
import com.cdthgk.model.form.Parameter;
import com.cdthgk.view.web.ParameterCollector;

public class CaseCriticalviolationSimpleTableCollector implements ParameterCollector<SimpleTable>{
	private DiscloseSecrecyService discloseSecrecyService;
	@Override
	public SimpleTable getData(Parameter param) {
		SimpleTable simpleTable = new SimpleTable();
		// 获取传入参数
		District district = (District)param.getVariable("district");
		List<District> districtList = new ArrayList<District>();
		districtList.add(district);
		//通过  (行政区划)对象  查询 该行政区划下面     商业秘密事项总数
		Integer total = new Integer(0);
		total = discloseSecrecyService.getDiscloseSecrecyTotal(null, CaseCriticalviolation.class.getName(), null, district.getDistrictCode(), false);

		//设置值
		simpleTable.setTitle("严重违规案件");//下面的action是查看 商业秘密事项个数 的明细列表  包括当前行政区划 和下级行政区划
		simpleTable.setActionURL("/bmp/discloseSecrecy/zhtj_query_Detail.action?className="+CaseCriticalviolation.class.getName()+"&&district.districtCode="+ district.getDistrictCode());
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
