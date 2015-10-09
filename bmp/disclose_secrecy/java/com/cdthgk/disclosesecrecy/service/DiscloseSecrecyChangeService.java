package com.cdthgk.disclosesecrecy.service;

import java.util.List;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.disclosesecrecy.vo.DiscloseSecrecy;
import com.cdthgk.disclosesecrecy.vo.DiscloseSecrecyChange;
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
public interface DiscloseSecrecyChangeService
extends BmpServiceTemplate<DiscloseSecrecyChange, String>{
    /**
     * 本单位密级变更列表+查询
     * @param psm
     * @param discloseSecrecyChange
     * @param user
     * @return
     */
	List<DiscloseSecrecyChange> querydiscloseSecrecyChangelistList(
			PageSortModel<DiscloseSecrecyChange> psm, DiscloseSecrecyChange discloseSecrecyChange,User user);
	 /**
     * 保密局密级变更列表+查询
     * @param psm
     * @param discloseSecrecyChange
     * @param user
     * @return
     */
	List<DiscloseSecrecyChange> querydiscloseSecrecyChangelistList(
			PageSortModel<DiscloseSecrecyChange> psm, DiscloseSecrecyChange discloseSecrecyChange,User user,String districtCode,String includeChrild);
	/**
	 * 某个泄密事件密级变更记录
	 *
	 * @param psm   分页对象
	 * @param discloseSecrecy   泄密事件变更
	 * @return
	 */
	public List<DiscloseSecrecyChange> querydiscloseSecrecyChangeList(PageSortModel<DiscloseSecrecyChange> psm,
			DiscloseSecrecy discloseSecrecy,User user) ;


}
