package com.cdthgk.disclosesecrecy.service;

import java.util.List;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.disclosesecrecy.vo.DiscloseSecrecyClear;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

/**
 * <p>
 * 法律法规Service接口
 * </p>
 * <p>
 * 王欢 2009-10-28  12:34:56
 * </p>
 * <blockquote>
 * <h4>历史修改记录</h4>
 * <ul>
 * <li>
 * 修改人chwencong 2010 8 18 02:40:02 修改注释格式
 * </ul>
 * <li>
 * 修改人wangpb 2013 1 7 11:40:02 修改注释格式
 * </ul>
 * </blockquote>
 * <p>
 * copyright tianyu 2012, all rights reserved.
 * </p>
 *
 * @author 王欢
 * @since 1.0
 * @version 1.0
 */
public interface DiscloseSecrecyClearService extends BmpServiceTemplate<DiscloseSecrecyClear, String>{

	List<DiscloseSecrecyClear> queryDiscloseSecrecyClearList(PageSortModel<DiscloseSecrecyClear> psm,
			DiscloseSecrecyClear discloseSecrecyClear,User user);

	List<DiscloseSecrecyClear> queryDiscloseSecrecyClearList(
			PageSortModel<DiscloseSecrecyClear> psm,
			DiscloseSecrecyClear discloseSecrecyClear, User currentUser,
			String districtCode,String includeChild);

}
