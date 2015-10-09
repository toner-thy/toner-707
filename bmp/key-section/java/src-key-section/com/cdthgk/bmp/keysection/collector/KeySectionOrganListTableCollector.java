
package com.cdthgk.bmp.keysection.collector;

import java.util.ArrayList;
import java.util.List;

import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.stat.model.impl.OrganListTable;
import com.cdthgk.bmp.keysection.dto.KeySectionStatDto;
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
public class KeySectionOrganListTableCollector implements ParameterCollector<List<OrganListTable>>{

	private KeySectionService keySectionService;

	public KeySectionService getKeySectionService() {
		return keySectionService;
	}

	public void setKeySectionService(KeySectionService keySectionService) {
		this.keySectionService = keySectionService;
	}

	/**
	 * //按照单位  统计要害部门  分密级显示
	 */
	@Override
	public List<OrganListTable> getData(Parameter param) {

		List<OrganListTable> organListTableList = new ArrayList<OrganListTable>();
		// 获取传入参数
		Organ organ = (Organ)param.getVariable("organ");
		District district = (District)param.getVariable("district");
		String orgName = (String)param.getVariable("organName");
		organ.setOrganName(orgName);

		List<KeySectionStatDto> keySectionStatDtoList = keySectionService.queryOrganByDistrict(district, false, organ);

		OrganListTable organTable = new OrganListTable();
		// 设置头部
		organTable.setOrganName("要害部门");
		organTable.setSecrecyLevel1("核 心");
		organTable.setSecrecyLevel2("重 要");
		organTable.setSecrecyLevel3("一 般");
		organListTableList.add(organTable);

		//设置值
		for (KeySectionStatDto keySection : keySectionStatDtoList) {
			OrganListTable organListTable = new OrganListTable();

			organListTable.setOrganName(keySection.getName());
			organListTable.setSecrecyLevel1(keySection.getNum1().toString());
			organListTable.setSecrecyLevel2(keySection.getNum2().toString());
			organListTable.setSecrecyLevel3(keySection.getNum3().toString());

			organListTableList.add(organListTable);
		}

		return organListTableList;
	}



}
