
package com.cdthgk.bmp.keysection.collector;

import java.util.List;

import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.stat.model.impl.FashionTable;
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
public class KeySectionFashionTableCollector implements ParameterCollector<FashionTable>{

	private KeySectionService keySectionService;

	public KeySectionService getKeySectionService() {
		return keySectionService;
	}

	public void setKeySectionService(KeySectionService keySectionService) {
		this.keySectionService = keySectionService;
	}

	/**
	 * //通过（单位）对象   查询该单位的   要害部门的密级情况    这里查询只返回的一条记录而已
	 */
	@Override
	public FashionTable getData(Parameter param) {

		FashionTable fashionTable = new FashionTable();
		// 获取传入organ参数
		Organ organ = (Organ)param.getVariable("organ");

		//通过（单位）对象   查询该单位的   要害部门的密级情况    这里查询只返回的一条记录而已
		List<Object[]> objectList = keySectionService.getOrganKeySection(organ);

		for (Object[] object : objectList) {
			fashionTable.setStyleType("1");
			fashionTable.setOrganId(organ.getOrganId());
			fashionTable.setTitle("要害部门统计");
			fashionTable.setImgUrl("/bmp/statfk/borderlayout/resource/img/keySection.gif");
			fashionTable.setListAction("bmp/keySection/zhtj_OrganDetailList.action");
			fashionTable.setSecrecyLevel1("核 心");
			fashionTable.setSecrecyNum1(object[2] == null ? 0 : Integer.parseInt(object[2].toString()));
			fashionTable.setSecrecyLevel2("重 要");
			fashionTable.setSecrecyNum2(object[3] == null ? 0 : Integer.parseInt(object[3].toString()));
			fashionTable.setSecrecyLevel3("一 般");
			fashionTable.setSecrecyNum3(object[4] == null ? 0 : Integer.parseInt(object[4].toString()));
			fashionTable.setTotalText("合 计");
			fashionTable.setTotalNum(Integer.parseInt(object[2].toString())
					+ Integer.parseInt(object[3].toString())
					+ Integer.parseInt(object[4].toString()));
		}

		return fashionTable;
	}

}
