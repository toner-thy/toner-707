
package com.cdthgk.bmp.pucha.detail.action;

import java.util.ArrayList;
import java.util.List;

import com.cdthgk.bmp.pucha.detail.constants.DetailConstants;
import com.cdthgk.common.lang.DateUtils;
import com.cdthgk.common.lang.LangUtils;
import com.cdthgk.component.constant.ConstantPool;
import com.cdthgk.platform.base.action.PlatformAction;
import com.cdthgk.platform.organization.organ.domain.Organ;

/**
 * <p>
 * DetailAction
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 *
 * @author 钟冀
 */
public class DetailAction extends PlatformAction{

	private static final long serialVersionUID = 1180797464523067610L;

	/**
	 * <p>
	 * 详情查询页面
	 * </p>
	 * @return 映射
	 */
	public String query() {
		List<Integer> years = new ArrayList<Integer>();
		for (int i = DateUtils.getCurrentYear(); i > 2010; i--) {
			years.add(i);
		}
		putToRequest("years", years);
		return SUCCESS;
	}

	/**
	 * <p>
	 * 上报详细情况
	 * </p>
	 * @return 映射
	 */
	public String detail() {
		if (year == null) {
			year = DateUtils.getCurrentYear();
		}
		if (LangUtils.isEmpty(organ)) {
			organ = getCurrentUser().getOrgan();
		}
		if (LangUtils.isEmpty(organ.getId())) {
			organ = getCurrentUser().getOrgan();
		}
		putToRequest("modulesUris", ConstantPool.getConstant(DetailConstants.class).getModuleUris());
		return SUCCESS;
	}

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
}
