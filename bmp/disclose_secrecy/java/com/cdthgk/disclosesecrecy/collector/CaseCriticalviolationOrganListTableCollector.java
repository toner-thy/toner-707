package com.cdthgk.disclosesecrecy.collector;

import java.util.ArrayList;
import java.util.List;

import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.stat.model.impl.OrganListTable;
import com.cdthgk.disclosesecrecy.dto.DiscloseSecrecyStatDto;
import com.cdthgk.disclosesecrecy.service.DiscloseSecrecyService;
import com.cdthgk.disclosesecrecy.vo.CaseCriticalviolation;
import com.cdthgk.model.form.Parameter;
import com.cdthgk.view.web.ParameterCollector;


/**
 * <p>
 * 按照单位  统计国家秘密  分密级显示
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 *
 * @author
 */
public class CaseCriticalviolationOrganListTableCollector implements ParameterCollector<List<OrganListTable>>{

	/*** Spring依赖注入 服务层注入 ***/
	private DiscloseSecrecyService discloseSecrecyService;

	/**
	 * //按照单位  统计国家秘密  分密级显示
	 */
	@Override
	public List<OrganListTable> getData(Parameter param) {

		List<OrganListTable> organListTableList = new ArrayList<OrganListTable>();
		// 获取传入参数
		String organName = (String)param.getVariable("organName");
		Organ organ=null;
        if (organName!=null&&!organName.equals("")) {
        	organ=new Organ();
        	organ.setOrganName(organName);
		}
		District district = (District)param.getVariable("district");
		List<DiscloseSecrecyStatDto> keySectionStatDtoList = discloseSecrecyService.queryDiscloseSecrecyByOrgan(district, false, organ, CaseCriticalviolation.class.getName().toString());

		OrganListTable organTable = new OrganListTable();
		// 设置头部
		organTable.setOrganName("严重违规案件");
		organTable.setSecrecyLevel1("秘 密");
		organTable.setSecrecyLevel2("机 密");
		organTable.setSecrecyLevel3("绝 密");
		organListTableList.add(organTable);

		//设置值
		for (DiscloseSecrecyStatDto keySection : keySectionStatDtoList) {
			OrganListTable organListTable = new OrganListTable();
			organListTable.setOrganName(keySection.getName());
			organListTable.setSecrecyLevel1(keySection.getNum1().toString());
			organListTable.setSecrecyLevel2(keySection.getNum2().toString());
			organListTable.setSecrecyLevel3(keySection.getNum3().toString());
			organListTableList.add(organListTable);
		}
		return organListTableList;
	}


	/*********************************** getter  and  setter **************************************************************************/
	public DiscloseSecrecyService getDiscloseSecrecyService() {
		return discloseSecrecyService;
	}

	public void setDiscloseSecrecyService(
			DiscloseSecrecyService discloseSecrecyService) {
		this.discloseSecrecyService = discloseSecrecyService;
	}


}
