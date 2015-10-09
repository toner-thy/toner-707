/**
 *
 */
package com.cdthgk.bmp.secrecyperson.action;

import java.util.Collection;
import java.util.List;

import com.cdthgk.bmp.core.action.BmpAction;
import com.cdthgk.bmp.keysection.vo.KeySection;
import com.cdthgk.bmp.secrecyperson.qo.SecrecyPersonQo;
import com.cdthgk.bmp.secrecyperson.service.SecrecyPersonModuleService;
import com.cdthgk.bmp.secrecyperson.vo.SecrecyPerson;
import com.cdthgk.common.bean.BeanUtils;
import com.cdthgk.common.lang.LangUtils;
import com.cdthgk.component.ioc.ContextUtils;
import com.cdthgk.model.structure.autocomplete.Result;
import com.cdthgk.model.structure.autocomplete.Results;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;

/**
 * <p>
 * 类的说明放这里  2013-8-1 下午3:48:25
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 *
 * @author 宋亚非
 */
public class SecrecyPersonSearchAction extends BmpAction{

	private SecrecyPersonModuleService secrecyPersonModuleService;

	private SecrecyPersonQo secrecyPersonQo;

	/**
	 *  自动补全
	 * @return
	 */
	public String autocomplete() {
		if( secrecyPersonQo==null ){
			secrecyPersonQo = new SecrecyPersonQo();
		}
		secrecyPersonQo.setOrgan(getCurrentUser().getOrgan());
		secrecyPersonQo.setSecrecySatusOfDecryption(SECRECY_STATUS_DECRYPTION);
		//查询数据
		List<SecrecyPerson> secrecyPersonlist = this.secrecyPersonModuleService.queryList(null, secrecyPersonQo);

		//构建数据
		Results results = new Results();//数据集对象
		String dataSelector = getHeader("data-selector");
		//遍历数据
		for (SecrecyPerson secrecyPerson : secrecyPersonlist) {
			Result result = new Result(secrecyPerson.getUserInfo().getName(), secrecyPerson.getUserInfo().getUserInfoId());
			if (LangUtils.isNotEmpty(dataSelector)) {
				Collection<String> selector = ContextUtils.getDefaultContext().getBean(dataSelector);
				for (String sn : selector) {
					result.addData(sn, BeanUtils.getProperty(secrecyPerson, sn));
				}
			}
			results.addResult(result);
		}
		setResultData(results);
		return AUTOCOMPLETE;
	}

	/**
	 * 页面
	 * @return
	 */
	public String singelSelect() {
		if( secrecyPersonQo==null ){
			secrecyPersonQo = new SecrecyPersonQo();
		}
		secrecyPersonQo.setOrgan(getCurrentUser().getOrgan());
		secrecyPersonQo.setSecrecySatusOfDecryption(SECRECY_STATUS_DECRYPTION);
		PageSortModel<SecrecyPerson> psm = new PageSortModel<SecrecyPerson>(getRequest(), "secrecyPersonlist");
		List<SecrecyPerson> secrecyPersonList = this.secrecyPersonModuleService.queryList(psm, secrecyPersonQo);
		this.putToRequest("secrecyPersonList", secrecyPersonList);
		return SUCCESS;
	}

 /********************************getter & setter************************************/
	/**
	 * @return the secrecyPersonModuleService
	 */
	public SecrecyPersonModuleService getSecrecyPersonModuleService() {
		return secrecyPersonModuleService;
	}

	/**
	 * @param secrecyPersonModuleService the secrecyPersonModuleService to set
	 */
	public void setSecrecyPersonModuleService(
			SecrecyPersonModuleService secrecyPersonModuleService) {
		this.secrecyPersonModuleService = secrecyPersonModuleService;
	}

	/**
	 * @return the secrecyPersonQo
	 */
	public SecrecyPersonQo getSecrecyPersonQo() {
		return secrecyPersonQo;
	}

	/**
	 * @param secrecyPersonQo the secrecyPersonQo to set
	 */
	public void setSecrecyPersonQo(SecrecyPersonQo secrecyPersonQo) {
		this.secrecyPersonQo = secrecyPersonQo;
	}




}
