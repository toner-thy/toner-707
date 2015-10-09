package com.cdthgk.bmp.secrecyresearchproject.collector;

import java.util.ArrayList;
import java.util.List;

import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.stat.model.impl.OrganListTable;
import com.cdthgk.bmp.secrecycountryitem.qo.ZongHeTongJiStatDto;
import com.cdthgk.bmp.secrecyresearchproject.service.SecrecyResearchProjectService;
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
public class SecrecyResearchProjectOrganListTableCollector implements ParameterCollector<List<OrganListTable>>{

	/***** Spring依赖注入,  服务层注入  ***/
	private SecrecyResearchProjectService secrecyResearchProjectService; //涉密科研项目的service

	/**
	 * //按照单位  统计国家秘密  分密级显示
	 */
	@Override
	public List<OrganListTable> getData(Parameter param) {

		List<OrganListTable> organListTableList = new ArrayList<OrganListTable>();
		// 获取传入参数
		Organ organ = (Organ)param.getVariable("organ");
		District district = (District)param.getVariable("district");
		String orgName = (String)param.getVariable("organName");
		organ.setOrganName(orgName);

		//查询  统计出吧单位下 涉密科研项目 的数量  按照密级统计
		List<ZongHeTongJiStatDto> statList = secrecyResearchProjectService.count_ResearchProject_Organ(district, organ, false);

		// 设置头部  表格的第一排
		OrganListTable organTable = new OrganListTable();
		organTable.setOrganName("涉密科研项目");
		organTable.setSecrecyLevel1("绝 密");
		organTable.setSecrecyLevel2("机 密");
		organTable.setSecrecyLevel3("秘 密");
		organListTableList.add(organTable);

		if(statList!=null) {
			//设置值
			for (ZongHeTongJiStatDto stat : statList) {
				OrganListTable organListTable = new OrganListTable();

				organListTable.setOrganName(stat.getName());//单位的名称
				organListTable.setSecrecyLevel1(String.valueOf(stat.getOrganSecrecyLevel1()));
				organListTable.setSecrecyLevel2(String.valueOf(stat.getOrganSecrecyLevel2()));
				organListTable.setSecrecyLevel3(String.valueOf(stat.getOrganSecrecyLevel3()));
				organListTableList.add(organListTable);
			}
		}


		return organListTableList;
	}


	/*********************************** getter  and  setter **************************************************************************/
	public SecrecyResearchProjectService getSecrecyResearchProjectService() {
		return secrecyResearchProjectService;
	}

	public void setSecrecyResearchProjectService(
			SecrecyResearchProjectService secrecyResearchProjectService) {
		this.secrecyResearchProjectService = secrecyResearchProjectService;
	}

}
