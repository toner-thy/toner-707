package com.cdthgk.bmp.secrecynet.secrecybasic.action;

import java.util.Date;

import com.cdthgk.bmp.secrecynet.secrecybasic.service.SecrecyBasicService;
import com.cdthgk.bmp.secrecynet.secrecybasic.vo.SecrecyBasic;
import com.cdthgk.common.lang.DateUtils;
import com.cdthgk.common.lang.LangUtils;
import com.cdthgk.platform.base.action.PlatformAction;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;

public class SecrecyBasicAction extends PlatformAction {

	private static final long serialVersionUID = 1L;

	public String list(){
		PageSortModel<SecrecyBasic> psm = new PageSortModel<SecrecyBasic>(getRequest(), "secrecyBasicList");
		putToRequest("secrecyBasicList", secrecyBasicService.getListPage(psm, secrecyBasic, getCurrentUser()));
		return "success";
	}

	public String add(){
		secrecyBasic = new SecrecyBasic();
		secrecyBasic.setReportOrgan(getCurrentUser().getOrgan());
		secrecyBasic.setReportUser(getCurrentUser());
		secrecyBasic.setReportDate(new Date());
		secrecyBasic.setTitle("市直机关");
		putToRequest("flag", "add");
		return SUCCESS;
	}

	public String add2(){
		secrecyBasic = new SecrecyBasic();
		secrecyBasic.setReportOrgan(getCurrentUser().getOrgan());
		secrecyBasic.setReportUser(getCurrentUser());
		secrecyBasic.setReportDate(new Date());
		putToRequest("flag", "add");
		secrecyBasic.setTitle("区、县（市）");
		return SUCCESS;
	}

	public String adding(){
		secrecyBasic.setReportOrgan(getCurrentUser().getOrgan());
		secrecyBasic.setReportUser(getCurrentUser());
		secrecyBasic.setReportDate(new Date());
		secrecyBasic.setCreateOrgan(getCurrentUser().getOrgan());
		secrecyBasic.setCreateDepartment(getCurrentUser().getUserInfo().getDepartment());
		secrecyBasic.setYear(DateUtils.getCurrentYear());
		secrecyBasic.setCreatePerson(getCurrentUser());
		secrecyBasic.setModifyPerson(getCurrentUser());
		secrecyBasic.setCreateTime(secrecyBasic.getReportDate());

		secrecyBasicService.save(secrecyBasic);
		addActionMessage("保存成功！");
		return redirectActionResult(LIST);
	}

	public String edit(){
		secrecyBasic = secrecyBasicService.get(secrecyBasic.getId());
		putToRequest("flag", "edit");
		return SUCCESS;
	}

	public String editing(){
		secrecyBasic.setModifyPerson(getCurrentUser());
		secrecyBasic.setModifyTime(new Date());
		secrecyBasic.setReportDate(new Date());
		secrecyBasicService.update(secrecyBasic);
		addActionMessage("更新成功！");
		return redirectActionResult(LIST);
	}

	public String del(){
		secrecyBasicService.deleteBatchIds(deleteIds);
		addActionMessage("删除成功");
		return redirectActionResult(LIST);
	}

	public String detail() {
		if (year == null) {
			year = DateUtils.getCurrentYear();
		}
		if (organ == null) {
			organ = new Organ();
		}
		if (LangUtils.isEmpty(organ.getId())) {
			organ.setOrganId(getCurrentUser().getOrgan().getId());
		}
		putToRequest("secrecyBasicList", secrecyBasicService.getSecrecyBasicList(year, organ.getOrganId()));
		return SUCCESS;
	}

	// ********************************************************************
	//	property
	// ********************************************************************

	private Integer year;

	private Organ organ;

	private SecrecyBasic secrecyBasic;
	private SecrecyBasicService secrecyBasicService;
	private String deleteIds;

	public SecrecyBasic getSecrecyBasic() {
		return secrecyBasic;
	}

	public void setSecrecyBasic(SecrecyBasic secrecyBasic) {
		this.secrecyBasic = secrecyBasic;
	}

	public void setSecrecyBasicService(SecrecyBasicService secrecyBasicService) {
		this.secrecyBasicService = secrecyBasicService;
	}

	public String getDeleteIds() {
		return deleteIds;
	}

	public void setDeleteIds(String deleteIds) {
		this.deleteIds = deleteIds;
	}

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
}