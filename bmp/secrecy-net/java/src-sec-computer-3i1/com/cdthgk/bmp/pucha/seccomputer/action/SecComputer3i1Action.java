
package com.cdthgk.bmp.pucha.seccomputer.action;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.cdthgk.bmp.core.action.BmpAction;
import com.cdthgk.bmp.pucha.seccomputer.domain.SecComputer3i1;
import com.cdthgk.bmp.pucha.seccomputer.service.SecComputer3i1Service;
import com.cdthgk.common.lang.DateUtils;
import com.cdthgk.common.lang.LangUtils;
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
public class SecComputer3i1Action extends BmpAction{

	/**
	 *
	 */
	private static final long serialVersionUID = 595939338740093095L;


	/**
	 * <p>
	 * 进入新增或者编辑
	 * </p>
	 * @return 映射
	 */
	public String save() {
		if (year == null) {
			year = DateUtils.getCurrentYear();
		}
		if (organ == null) {
			organ = new Organ();
		}
		if (LangUtils.isEmpty(organ.getId())) {
			organ.setOrganId(getCurrentUser().getOrgan().getId());
		}
		putToRequest("date", DateUtils.formartDate(new Date()));
		List<SecComputer3i1> list = secComputer3i1Service.getList(year, organ.getOrganId());
		if (LangUtils.isNotEmpty(list)) {
			putToRequest("sc", list.get(0));
		}
		putToRequest("list", list);
		return SUCCESS;
	}

	/**
	 * <p>
	 * 新增或编辑
	 * </p>
	 * @return 映射
	 */
	public String saveing() {
		int year = DateUtils.getCurrentYear();
		if (secComputer3i1List != null) {
			Iterator<SecComputer3i1> it = secComputer3i1List.iterator();
			int sort = 1;
			while(it.hasNext()) {
				SecComputer3i1 secComputer3i1 = it.next();
				// 忽略空
				if (secComputer3i1 == null) {
					it.remove();
				} else {
					if (secComputer3i1.getYear() == null) {
						secComputer3i1.setYear(year);
					}
					if (secComputer3i1.getReportOrgan() == null) {
						secComputer3i1.setReportOrgan(getCurrentUser().getOrgan());
					}
					if (secComputer3i1.getReportUser() == null) {
						secComputer3i1.setReportUser(getCurrentUser());
					}
					if (LangUtils.isEmpty(secComputer3i1.getId())) {
						secComputer3i1.setId(null);
					}
					secComputer3i1.setSort(sort);
					secComputer3i1.setReportDate(new Date());
					secComputer3i1.setCreatePerson(getCurrentUser());
					secComputer3i1.setModifyPerson(getCurrentUser());
					secComputer3i1.setCreateOrgan(getCurrentUser().getOrgan());
					secComputer3i1.setCreateDepartment(getCurrentUser().getUserInfo().getDepartment());
					sort++;
				}
			}
		}
		secComputer3i1Service.save(secComputer3i1List , year, getCurrentUser().getOrgan().getId());
		addActionMessage("保存成功");
		return SUCCESS;
	}

	/**
	 * <p>
	 * 详情页面
	 * </p>
	 * @return 映射
	 */
	public String detail() {
		return save();
	}

	// ********************************************************************
	//	property
	// ********************************************************************

	private Integer year;

	private Organ organ;

	private List<SecComputer3i1> secComputer3i1List;

	private SecComputer3i1Service secComputer3i1Service;

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
	 * 设置secComputer3i1Service
	 * @param secComputer3i1Service secComputer3i1Service
	 */
	public void setSecComputer3i1Service(SecComputer3i1Service secComputer3i1Service) {
		this.secComputer3i1Service = secComputer3i1Service;
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
	 * 返回secComputer3i1List
	 * @return secComputer3i1List
	 */
	public List<SecComputer3i1> getSecComputer3i1List() {
		return secComputer3i1List;
	}

	/**
	 * 设置secComputer3i1List
	 * @param secComputer3i1List secComputer3i1List
	 */
	public void setSecComputer3i1List(List<SecComputer3i1> secComputer3i1List) {
		this.secComputer3i1List = secComputer3i1List;
	}
}
