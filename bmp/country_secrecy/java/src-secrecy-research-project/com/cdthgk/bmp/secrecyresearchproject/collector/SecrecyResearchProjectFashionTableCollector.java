package com.cdthgk.bmp.secrecyresearchproject.collector;

import java.util.Iterator;
import java.util.List;

import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.stat.model.impl.FashionTable;
import com.cdthgk.bmp.secrecycountryitem.qo.ZongHeTongJiStatDto;
import com.cdthgk.bmp.secrecyresearchproject.service.SecrecyResearchProjectService;
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
public class SecrecyResearchProjectFashionTableCollector implements ParameterCollector<FashionTable>{

	/***** Spring依赖注入,  服务层注入  ***/
	private SecrecyResearchProjectService secrecyResearchProjectService; //涉密科研项目的service

	/**
	 * //通过（单位）对象   查询该单位的   涉密科研项目的统计情况    这里查询只返回的一条记录而已
	 */
	@Override
	public FashionTable getData(Parameter param) {

		FashionTable fashionTable = new FashionTable();
		// 获取传入organ参数
		Organ organ = (Organ)param.getVariable("organ");

		//通过（单位）对象   查询该单位的   涉密科研项目的密级情况    这里查询只返回的一条记录而已
		List<ZongHeTongJiStatDto> objectList = secrecyResearchProjectService.count_ResearchProject_Organ(null,organ, false);

		if(objectList!=null) {
			Iterator<ZongHeTongJiStatDto> it = objectList.iterator();
			while(it.hasNext()){
				ZongHeTongJiStatDto item = it.next();

				fashionTable.setStyleType("1");
				fashionTable.setOrganId(organ.getOrganId());
				fashionTable.setTitle("涉密科研项目统计");
				fashionTable.setImgUrl("/bmp/statfk/borderlayout/resource/img/secrecyResearchProject.gif");
				fashionTable.setListAction("bmp/secrecyResearchProject/zhtj_OrganDetailList.action");
				fashionTable.setSecrecyLevel1("绝 密");
				fashionTable.setSecrecyNum1(item.getOrganSecrecyLevel1());
				fashionTable.setSecrecyLevel2("机 密");
				fashionTable.setSecrecyNum2(item.getOrganSecrecyLevel2());
				fashionTable.setSecrecyLevel3("秘 密");
				fashionTable.setSecrecyNum3(item.getOrganSecrecyLevel3());
				fashionTable.setTotalText("合 计");
				fashionTable.setTotalNum(item.getOrganSecrecyLevel1()
						+ item.getOrganSecrecyLevel2()
						+ item.getOrganSecrecyLevel3());
			}
		}


		return fashionTable;
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
