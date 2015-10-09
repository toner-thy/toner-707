
package com.cdthgk.bmp.pucha.tectool.action;

import java.util.Date;

import com.cdthgk.bmp.core.action.BmpAction;
import com.cdthgk.bmp.pucha.nosecnet.domain.NoSecNet;
import com.cdthgk.bmp.pucha.tectool.domain.TecToolInfo;
import com.cdthgk.bmp.pucha.tectool.service.TecToolInfoService;
import com.cdthgk.common.bean.BeanUtils;
import com.cdthgk.common.bean.rule.CopyRuleEnum;
import com.cdthgk.common.lang.DateUtils;
import com.cdthgk.common.lang.LangUtils;
import com.cdthgk.platform.organization.organ.domain.Organ;

/**
 * <p>
 * TecToolInfoAction
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 *
 * @author 钟冀
 */
public class TecToolInfoAction extends BmpAction{

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
		tecToolInfo = tecToolInfoService.get(year, organ.getOrganId());
		if (tecToolInfo == null) {
			tecToolInfo = new TecToolInfo();
			tecToolInfo.setReportOrgan(getCurrentUser().getOrgan());
			tecToolInfo.setReportUser(getCurrentUser());
			tecToolInfo.setReportDate(new Date());
		}
		return SUCCESS;
	}

	/**
	 * <p>
	 * 新增或编辑
	 * </p>
	 * @return 映射
	 */
	public String saveing() {
		if (tecToolInfo != null) {
			int year = DateUtils.getCurrentYear();
			if (tecToolInfo.getYear() == null) {
				tecToolInfo.setYear(year);
			}
			if (tecToolInfo.getReportOrgan() == null) {
				tecToolInfo.setReportOrgan(getCurrentUser().getOrgan());
			}
			if (tecToolInfo.getReportUser() == null) {
				tecToolInfo.setReportUser(getCurrentUser());
			}
			Date now = new Date();
			if (LangUtils.isEmpty(tecToolInfo.getTecToolInfoId())) {
				tecToolInfo.setTecToolInfoId(null);
				tecToolInfo.setCreateTime(now);
				tecToolInfo.setReportDate(now);
				tecToolInfo.setCreatePerson(getCurrentUser());
				tecToolInfo.setModifyPerson(getCurrentUser());
				tecToolInfo.setCreateOrgan(getCurrentUser().getOrgan());
				tecToolInfo.setCreateDepartment(getCurrentUser().getUserInfo().getDepartment());
				tecToolInfoService.save(tecToolInfo);
			} else {
				TecToolInfo nsn = tecToolInfo;
				tecToolInfo = tecToolInfoService.get(tecToolInfo.getTecToolInfoId());
				tecToolInfo.setModifyPerson(getCurrentUser());
				tecToolInfo.setModifyTime(now);
				BeanUtils.copyProperties(tecToolInfo, nsn, CopyRuleEnum.ignoreCaseNull);
				tecToolInfoService.update(tecToolInfo);
			}
		}
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

	private TecToolInfo tecToolInfo;

	private TecToolInfoService tecToolInfoService;

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
	 * 返回tecToolInfo
	 * @return tecToolInfo
	 */
	public TecToolInfo getTecToolInfo() {
		return tecToolInfo;
	}

	/**
	 * 设置tecToolInfo
	 * @param tecToolInfo tecToolInfo
	 */
	public void setTecToolInfo(TecToolInfo tecToolInfo) {
		this.tecToolInfo = tecToolInfo;
	}

	/**
	 * 设置tecToolInfoService
	 * @param tecToolInfoService tecToolInfoService
	 */
	public void setTecToolInfoService(TecToolInfoService tecToolInfoService) {
		this.tecToolInfoService = tecToolInfoService;
	}
}
