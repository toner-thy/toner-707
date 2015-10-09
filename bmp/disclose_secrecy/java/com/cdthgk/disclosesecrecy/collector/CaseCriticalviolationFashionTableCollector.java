
package com.cdthgk.disclosesecrecy.collector;

import java.util.List;

import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.stat.model.impl.FashionTable;
import com.cdthgk.disclosesecrecy.service.DiscloseSecrecyService;
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
public class CaseCriticalviolationFashionTableCollector implements ParameterCollector<FashionTable>{

	private DiscloseSecrecyService discloseSecrecyService;

	public DiscloseSecrecyService getDiscloseSecrecyService() {
		return discloseSecrecyService;
	}

	public void setDiscloseSecrecyService(
			DiscloseSecrecyService discloseSecrecyService) {
		this.discloseSecrecyService = discloseSecrecyService;
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
		List<Object[]> objectList = discloseSecrecyService.getOrganKeySection(organ,"bm_case_criticalviolation");

		for (Object[] object : objectList) {
			fashionTable.setStyleType("1");
			fashionTable.setOrganId(organ.getOrganId());
			fashionTable.setTitle("严重违规案件统计");
			fashionTable.setImgUrl("/bmp/statfk/borderlayout/resource/img/criticalviolation.gif");
			fashionTable.setListAction("bmp/caseCriticalviolation/caseCriticalviolation_selectListing2.action" +
					"?retrun=true&&caseCriticalviolation.secrecyLevel=3&&caseCriticalviolation.createOrgan.organId="+organ.getOrganId()
					+"&&caseCriticalviolation.createOrgan.organName="+organ.getOrganName()
					+"&&includeChild=0&&districtCode="+organ.getDistrict().getDistrictCode());
			fashionTable.setSecrecyLevel1("绝 密");//秘 密 	机 密 	绝 密
			fashionTable.setSecrecyNum1(object[2] == null ? 0 : Integer.parseInt(object[2].toString()));
			fashionTable.setSecrecyLevel2("机 密");
			fashionTable.setSecrecyNum2(object[3] == null ? 0 : Integer.parseInt(object[3].toString()));
			fashionTable.setSecrecyLevel3("秘 密");
			fashionTable.setSecrecyNum3(object[4] == null ? 0 : Integer.parseInt(object[4].toString()));
			fashionTable.setTotalText("合 计");
			fashionTable.setTotalNum(Integer.parseInt(object[2].toString())
					+ Integer.parseInt(object[3].toString())
					+ Integer.parseInt(object[4].toString()));
		}

		return fashionTable;
	}

}
