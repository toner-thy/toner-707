
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
public class DiscloseSecrecyFashionTableCollector implements ParameterCollector<FashionTable>{

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
		List<Object[]> objectList = discloseSecrecyService.getOrganKeySection(organ,"bm_case_disclosesecrecy");

		for (Object[] object : objectList) {
			fashionTable.setStyleType("1");
			fashionTable.setOrganId(organ.getOrganId());
			fashionTable.setTitle("泄密案件统计");
			fashionTable.setImgUrl("/bmp/statfk/borderlayout/resource/img/discloseSecrecy.gif");

			fashionTable.setListAction("bmp/discloseSecrecy/discloseSecrecy_selectListing2.action" +
					"?retrun=true&&discloseSecrecy.secrecyLevel=3&&discloseSecrecy.createOrgan.organId="+organ.getOrganId()
					+"&&discloseSecrecy.createOrgan.organName="+organ.getOrganName()
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
