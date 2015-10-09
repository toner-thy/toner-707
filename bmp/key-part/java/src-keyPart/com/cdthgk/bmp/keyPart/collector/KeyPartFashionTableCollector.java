package com.cdthgk.bmp.keyPart.collector;

import java.util.Iterator;
import java.util.List;

import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.stat.model.impl.FashionTable;
import com.cdthgk.bmp.keyPart.service.PartService;
import com.cdthgk.bmp.secrecycountryitem.qo.ZongHeTongJiStatDto;
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
public class KeyPartFashionTableCollector implements ParameterCollector<FashionTable>{

	/*** Spring依赖注入 服务层注入 ***/
	private PartService partService;
	/*************************************** getter and setter ***************************************************************/
	public PartService getPartService() {
		return partService;
	}

	public void setPartService(PartService partService) {
		this.partService = partService;
	}
	/*************************************** getter and setter ***************************************************************/


	/**
	 * //通过（单位）对象   查询该单位的   要害部位的统计情况    这里查询只返回的一条记录而已
	 */
	@Override
	public FashionTable getData(Parameter param) {

		FashionTable fashionTable = new FashionTable();
		// 获取传入organ参数
		Organ organ = (Organ)param.getVariable("organ");

		//通过（单位）对象   查询该单位的   要害部位的密级情况    这里查询只返回的一条记录而已
		List<ZongHeTongJiStatDto> objectList = partService.count_Organ(null,organ, false);

		if(objectList!=null) {
			Iterator<ZongHeTongJiStatDto> it = objectList.iterator();
			while(it.hasNext()){
				ZongHeTongJiStatDto item = it.next();

				fashionTable.setStyleType("1");
				fashionTable.setOrganId(organ.getOrganId());
				fashionTable.setTitle("要害部位统计");
				fashionTable.setImgUrl("/bmp/statfk/borderlayout/resource/img/keyPart.gif");
				fashionTable.setListAction("bmp/part/zhtj_OrganDetailList.action");
				fashionTable.setSecrecyLevel1("核 心");
				fashionTable.setSecrecyNum1(item.getOrganSecrecyLevel1());
				fashionTable.setSecrecyLevel2("重 要");
				fashionTable.setSecrecyNum2(item.getOrganSecrecyLevel2());
				fashionTable.setSecrecyLevel3("一 般");
				fashionTable.setSecrecyNum3(item.getOrganSecrecyLevel3());
				fashionTable.setTotalText("合 计");
				fashionTable.setTotalNum(item.getOrganSecrecyLevel1()
						+ item.getOrganSecrecyLevel2()
						+ item.getOrganSecrecyLevel3());
			}
		}


		return fashionTable;
	}


}
