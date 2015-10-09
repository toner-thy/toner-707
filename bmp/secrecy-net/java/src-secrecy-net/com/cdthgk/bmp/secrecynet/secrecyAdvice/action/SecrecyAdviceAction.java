
package com.cdthgk.bmp.secrecynet.secrecyAdvice.action;

import java.util.Date;
import java.util.List;

import com.cdthgk.bmp.core.action.BmpAction;
import com.cdthgk.bmp.secrecynet.secrecyAdvice.service.SecrecyAdviceService;
import com.cdthgk.bmp.secrecynet.secrecyAdvice.vo.SecrecyAdvice;
import com.cdthgk.common.lang.DateUtils;
import com.cdthgk.common.lang.LangUtils;
import com.cdthgk.common.lang.UUIDGenerator;
import com.cdthgk.platform.organization.organ.domain.Organ;

/**
 * <p>
 * 类的说明放这里
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 *
 * @author 钟冀
 */
public class SecrecyAdviceAction extends BmpAction{

	/**
	 *
	 */
	private static final long serialVersionUID = 5967748472001815560L;

	private SecrecyAdviceService secrecyAdviceService;
	private SecrecyAdvice secrecyAdvice;
	private List<SecrecyAdvice> secrecyAdviceList;
	private String secrecyAdviceIds;


	public String list(){
		return "list";
	}

	public String add(){
		if (year == null) {
			year = DateUtils.getCurrentYear();
		}
		if (organ == null) {
			organ = new Organ();
		}
		if (LangUtils.isEmpty(organ.getId())) {
			organ.setOrganId(getCurrentUser().getOrgan().getId());
		}
		secrecyAdvice = secrecyAdviceService.getSecrecyAdviceByYear(year, organ);
		if (secrecyAdvice == null) {
			secrecyAdvice = new SecrecyAdvice();
			secrecyAdvice.setOrgan(getCurrentUser().getOrgan());
			secrecyAdvice.setReportUser(getCurrentUser());
			secrecyAdvice.setReportTime(new Date());
		}
		return "add";
	}

	public String adding(){
		if(secrecyAdvice.getId() != null && !"".equals(secrecyAdvice.getId())){
			secrecyAdvice.setModifyPerson(getCurrentUser());
			secrecyAdvice.setModifyTime(new Date());
			secrecyAdviceService.update(secrecyAdvice);
		}else{
			secrecyAdvice.setYear(DateUtils.getCurrentYear());
			secrecyAdvice.setId(UUIDGenerator.generatUUID());
			secrecyAdvice.setCreatePerson(getCurrentUser());
			secrecyAdvice.setCreateOrgan(getCurrentUser().getOrgan());
			secrecyAdvice.setCreateDepartment(getCurrentUser().getOrgan().getDepartment());
			secrecyAdvice.setModifyPerson(getCurrentUser());
			secrecyAdvice.setCreateTime(new Date());
			secrecyAdviceService.save(secrecyAdvice);
		}

		return redirectActionResult(LIST);
	}

	public String edit(){
		return "edit";
	}

	public String editing(){
		return redirectActionResult(LIST);
	}

	public String detail() {
		return add();
	}

	// ********************************************************************
	//	property
	// ********************************************************************

	private Integer year;

	private Organ organ;

	/**
	 * 返回year
	 * @return year
	 */
	public Integer getYear() {
		return year;
	}

	/**
	 * 设置year
	 * @param year year
	 */
	public void setYear(Integer year) {
		this.year = year;
	}

	/**
	 * 返回organ
	 * @return organ
	 */
	public Organ getOrgan() {
		return organ;
	}

	/**
	 * 设置organ
	 * @param organ organ
	 */
	public void setOrgan(Organ organ) {
		this.organ = organ;
	}
	/**
	 * 设置secrecyAdviceService
	 * @param secrecyAdviceService secrecyAdviceService
	 */
	public void setSecrecyAdviceService(SecrecyAdviceService secrecyAdviceService) {
		this.secrecyAdviceService = secrecyAdviceService;
	}
	/**
	 * 返回secrecyAdvice
	 * @return secrecyAdvice
	 */
	public SecrecyAdvice getSecrecyAdvice() {
		return secrecyAdvice;
	}
	/**
	 * 设置secrecyAdvice
	 * @param secrecyAdvice secrecyAdvice
	 */
	public void setSecrecyAdvice(SecrecyAdvice secrecyAdvice) {
		this.secrecyAdvice = secrecyAdvice;
	}
	/**
	 * 返回secrecyAdviceList
	 * @return secrecyAdviceList
	 */
	public List<SecrecyAdvice> getSecrecyAdviceList() {
		return secrecyAdviceList;
	}
	/**
	 * 设置secrecyAdviceList
	 * @param secrecyAdviceList secrecyAdviceList
	 */
	public void setSecrecyAdviceList(List<SecrecyAdvice> secrecyAdviceList) {
		this.secrecyAdviceList = secrecyAdviceList;
	}
	/**
	 * 返回secrecyAdviceIds
	 * @return secrecyAdviceIds
	 */
	public String getSecrecyAdviceIds() {
		return secrecyAdviceIds;
	}
	/**
	 * 设置secrecyAdviceIds
	 * @param secrecyAdviceIds secrecyAdviceIds
	 */
	public void setSecrecyAdviceIds(String secrecyAdviceIds) {
		this.secrecyAdviceIds = secrecyAdviceIds;
	}

}
