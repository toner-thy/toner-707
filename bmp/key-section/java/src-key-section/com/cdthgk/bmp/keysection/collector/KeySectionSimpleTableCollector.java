
package com.cdthgk.bmp.keysection.collector;

import java.util.ArrayList;
import java.util.List;

import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.stat.model.impl.SimpleTable;
import com.cdthgk.bmp.keysection.service.KeySectionService;
import com.cdthgk.model.form.Parameter;
import com.cdthgk.view.web.ParameterCollector;

/**
 * <p>
 * 类的说明放这里
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 *
 * @author
 */
public class KeySectionSimpleTableCollector implements ParameterCollector<SimpleTable>{

	private KeySectionService keySectionService;

	public KeySectionService getKeySectionService() {
		return keySectionService;
	}

	public void setKeySectionService(KeySectionService keySectionService) {
		this.keySectionService = keySectionService;
	}



	/**
	 * 通过  (行政区划)对象  查询该行政区划下面      要害部门总数
	 * 这个方法  直接算出   要害部门的总数
	 */
	public SimpleTable getData(Parameter param) {

		SimpleTable simpleTable = new SimpleTable();
		// 获取传入参数
		District district = (District)param.getVariable("district");
		List<District> districtList = new ArrayList<District>();
		districtList.add(district);

		//通过  (行政区划)对象  查询 该行政区划下面      要害部门总数
		Integer total = keySectionService.getSectionTotal_basic(district, 1);

		//设置值
		simpleTable.setTitle("要害部门");//下面的action是查看 要害部门个数 的明细列表  包括当前行政区划 和下级行政区划
		simpleTable.setActionURL("/bmp/keySection/zhtj_query_Detail.action?district.DistrictCode="+ district.getDistrictCode());
		simpleTable.setTotalText(total);

		return simpleTable;
	}

}
