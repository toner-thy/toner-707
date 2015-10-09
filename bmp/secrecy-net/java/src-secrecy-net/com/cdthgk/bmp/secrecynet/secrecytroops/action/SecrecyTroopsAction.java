package com.cdthgk.bmp.secrecynet.secrecytroops.action;

import java.util.Date;

import com.cdthgk.bmp.secrecynet.secrecytroops.service.SecrecyTroopsService;
import com.cdthgk.bmp.secrecynet.secrecytroops.vo.SecrecyTroops;
import com.cdthgk.common.lang.DateUtils;
import com.cdthgk.common.lang.LangUtils;
import com.cdthgk.platform.base.action.PlatformAction;
import com.cdthgk.platform.organization.organ.domain.Organ;

public class SecrecyTroopsAction extends PlatformAction {

	private static final long serialVersionUID = 1L;

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
		SecrecyTroops st = secrecyTroopsService.getSecrecyTroopsByYear(year, organ);
		if(st != null){
			secrecyTroops = st;
			putToRequest("flag", "edit");
			return SUCCESS;
		}
		if(secrecyTroops == null){
			secrecyTroops = new SecrecyTroops();
			secrecyTroops.setReportOrgan(getCurrentUser().getOrgan());
			secrecyTroops.setReportUser(getCurrentUser());
			secrecyTroops.setReportDate(new Date());
			putToRequest("flag", "add");
		} else {
			putToRequest("flag", "edit");
			secrecyTroops = secrecyTroopsService.get(secrecyTroops.getId());
		}
		return SUCCESS;
	}

	public String adding(){
		secrecyTroops.setReportOrgan(getCurrentUser().getOrgan());
		secrecyTroops.setReportUser(getCurrentUser());
		secrecyTroops.setReportDate(new Date());
		secrecyTroops.setCreateOrgan(getCurrentUser().getOrgan());
		secrecyTroops.setCreateDepartment(getCurrentUser().getUserInfo().getDepartment());
		secrecyTroops.setYear(DateUtils.getCurrentYear());
		secrecyTroops.setCreatePerson(getCurrentUser());
		secrecyTroops.setModifyPerson(getCurrentUser());
		secrecyTroops.setCreateTime(secrecyTroops.getReportDate());

		secrecyTroopsService.save(secrecyTroops);
		addActionMessage("保存成功！");
		return redirectActionResult(ADD);
	}

	public String editing(){
		SecrecyTroops dbSecrecyTroops = secrecyTroopsService.get(secrecyTroops.getId());
		dbSecrecyTroops.setModifyPerson(getCurrentUser());
		dbSecrecyTroops.setModifyTime(new Date());
		dbSecrecyTroops.setAgeFortyfiveDown(secrecyTroops.getAgeFortyfiveDown());
		dbSecrecyTroops.setAgeFortyfiveUp(secrecyTroops.getAgeFortyfiveUp());
		dbSecrecyTroops.setDegreeBenNum(secrecyTroops.getDegreeBenNum());
		dbSecrecyTroops.setDegreeBoNum(secrecyTroops.getDegreeBoNum());
		dbSecrecyTroops.setDegreeDazNum(secrecyTroops.getDegreeDazNum());
		dbSecrecyTroops.setDegreeSuoNum(secrecyTroops.getDegreeSuoNum());
		dbSecrecyTroops.setJuExistNum(secrecyTroops.getJuExistNum());
		dbSecrecyTroops.setJupPlaitNum(secrecyTroops.getJupPlaitNum());
		dbSecrecyTroops.setSmallOrganNum(secrecyTroops.getSmallOrganNum());
		dbSecrecyTroops.setSpecialyComputerNum(secrecyTroops.getSpecialyComputerNum());
		dbSecrecyTroops.setSpecialyOtherNum(secrecyTroops.getSpecialyOtherNum());
		dbSecrecyTroops.setZxExistNum(secrecyTroops.getZxExistNum());
		dbSecrecyTroops.setZxPlaitNum(secrecyTroops.getZxPlaitNum());
		secrecyTroopsService.update(dbSecrecyTroops);
		addActionMessage("更新成功！");
		return redirectActionResult(ADD);
	}

	public String detail() {
		return add();
	}

	// ********************************************************************
	//	property
	// ********************************************************************

	private Integer year;

	private Organ organ;

	private SecrecyTroops secrecyTroops;

	private SecrecyTroopsService secrecyTroopsService;

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

	public void setSecrecyTroopsService(SecrecyTroopsService secrecyTroopsService) {
		this.secrecyTroopsService = secrecyTroopsService;
	}

	public SecrecyTroops getSecrecyTroops() {
		return secrecyTroops;
	}

	public void setSecrecyTroops(SecrecyTroops secrecyTroops) {
		this.secrecyTroops = secrecyTroops;
	}

}