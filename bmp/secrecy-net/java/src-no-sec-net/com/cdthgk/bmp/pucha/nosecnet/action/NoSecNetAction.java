
package com.cdthgk.bmp.pucha.nosecnet.action;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import com.cdthgk.bmp.core.action.BmpAction;
import com.cdthgk.bmp.pucha.nosecnet.domain.NoSecNet;
import com.cdthgk.bmp.pucha.nosecnet.domain.NoSecNetIntranet;
import com.cdthgk.bmp.pucha.nosecnet.service.NoSecNetService;
import com.cdthgk.common.bean.BeanUtils;
import com.cdthgk.common.bean.rule.CopyRuleEnum;
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
public class NoSecNetAction extends BmpAction{

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
		noSecNet = noSecNetService.get(year, organ.getOrganId());
		if (noSecNet == null) {
			noSecNet = new NoSecNet();
			noSecNet.setReportOrgan(getCurrentUser().getOrgan());
			noSecNet.setReportUser(getCurrentUser());
			noSecNet.setReportDate(new Date());
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
		if (noSecNet != null) {
			int year = DateUtils.getCurrentYear();
			if (noSecNet.getYear() == null) {
				noSecNet.setYear(year);
			}
			if (noSecNet.getReportOrgan() == null) {
				noSecNet.setReportOrgan(getCurrentUser().getOrgan());
			}
			if (noSecNet.getReportUser() == null) {
				noSecNet.setReportUser(getCurrentUser());
			}
			if (LangUtils.isNotEmpty(noSecNetIntranetList)) {
				Iterator<NoSecNetIntranet> it = noSecNetIntranetList.iterator();
				int index = 1;
				while (it.hasNext()) {
					NoSecNetIntranet noSecNetIntranet = it.next();
					if (noSecNetIntranet == null) {
						it.remove();
					} else {
						if (LangUtils.isEmpty(noSecNetIntranet.getIntranetId())) {
							noSecNetIntranet.setIntranetId(null);
						}
						noSecNetIntranet.setNoSecNet(noSecNet);
						noSecNetIntranet.setSort(index);
						index++;
					}
				}
			}
			Date now = new Date();
			if (LangUtils.isEmpty(noSecNet.getNoSecNetId())) {
				noSecNet.setNoSecNetId(null);
				noSecNet.setCreateTime(now);
				noSecNet.setReportDate(now);
				noSecNet.setCreatePerson(getCurrentUser());
				noSecNet.setModifyPerson(getCurrentUser());
				noSecNet.setCreateOrgan(getCurrentUser().getOrgan());
				noSecNet.setCreateDepartment(getCurrentUser().getUserInfo().getDepartment());
				if(noSecNetIntranetList!=null && LangUtils.isNotEmpty(noSecNetIntranetList)){
					noSecNet.setNoSecNetIntranets(new HashSet<NoSecNetIntranet>(noSecNetIntranetList));
				}
				noSecNetService.save(noSecNet);
				noSecNetService.update(noSecNet, noSecNetIntranetList);
			} else {
				NoSecNet nsn = noSecNet;
				noSecNet = noSecNetService.get(noSecNet.getNoSecNetId());
				noSecNet.setModifyPerson(getCurrentUser());
				noSecNet.setModifyTime(now);
				BeanUtils.copyProperties(noSecNet, nsn, CopyRuleEnum.ignoreCaseNull);
				noSecNetService.update(noSecNet, noSecNetIntranetList);
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

	private NoSecNet noSecNet;

	private NoSecNetService noSecNetService;

	private List<NoSecNetIntranet> noSecNetIntranetList;


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
	 * 设置noSecNetService
	 * @param noSecNetService noSecNetService
	 */
	public void setNoSecNetService(NoSecNetService noSecNetService) {
		this.noSecNetService = noSecNetService;
	}

	/**
	 * 返回noSecNet
	 * @return noSecNet
	 */
	public NoSecNet getNoSecNet() {
		return noSecNet;
	}

	/**
	 * 设置noSecNet
	 * @param noSecNet noSecNet
	 */
	public void setNoSecNet(NoSecNet noSecNet) {
		this.noSecNet = noSecNet;
	}

	/**
	 * 返回noSecNetIntranetList
	 * @return noSecNetIntranetList
	 */
	public List<NoSecNetIntranet> getNoSecNetIntranetList() {
		return noSecNetIntranetList;
	}

	/**
	 * 设置noSecNetIntranetList
	 * @param noSecNetIntranetList noSecNetIntranetList
	 */
	public void setNoSecNetIntranetList(List<NoSecNetIntranet> noSecNetIntranetList) {
		this.noSecNetIntranetList = noSecNetIntranetList;
	}
}
