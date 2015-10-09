/**
 *
 */
package com.cdthgk.bmp.secrecynet.secrecySupervision.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.secrecynet.secrecySupervision.service.SecrecySupervisionContentService;
import com.cdthgk.bmp.secrecynet.secrecySupervision.service.SecrecySupervisionService;
import com.cdthgk.bmp.secrecynet.secrecySupervision.vo.SecrecySupervision;
import com.cdthgk.bmp.secrecynet.secrecySupervision.vo.SecrecySupervisionContent;
import com.cdthgk.common.lang.DateUtils;
import com.cdthgk.common.lang.LangUtils;
import com.cdthgk.platform.base.action.PlatformAction;
import com.cdthgk.platform.organization.organ.domain.Organ;

/**
 * <p>
 * 类的说明放这里  2013-5-17 下午4:23:44
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 *
 * @author 宋亚非
 */
public class SecrecySupervisionAction extends PlatformAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private SecrecySupervisionService secrecySupervisionService;
	private SecrecySupervisionContentService secrecySupervisionContentService;
	private SecrecySupervision secrecySupervision;
	//子表集合
	private List<SecrecySupervisionContent> secSupConSet;

	//查询保存公用参数
	private Integer year;
	private Organ organ;

	/**
	 *
	 * <p>
	 * 方法名：add
	 * </p>
	 * <p>
	 * 宋亚非 2013-5-17 下午4:34:05
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@return
	 */
	public String add(){
		//查询当年的记录
		StringBuffer hql = new StringBuffer("from SecrecySupervision s where s.year = :year and s.reportOrgan = :reportOrgan");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("year", DateUtils.getCurrentYear());
		params.put("reportOrgan", this.getCurrentUser().getOrgan());
		List<SecrecySupervision> secrecySupervisionList = this.secrecySupervisionService.findList(hql.toString(), params);
		if( secrecySupervisionList!=null && secrecySupervisionList.size()>0){
			this.setSecrecySupervision(secrecySupervisionList.get(0));
			StringBuffer hqlSub = new StringBuffer("from SecrecySupervisionContent sc where sc.organ = :organ and sc.year BETWEEN :yearEnd AND :yearStart order by  sc.year asc");
			Map<String, Object> paramsSub = new HashMap<String, Object>();
			paramsSub.put("organ", this.getCurrentUser().getOrgan());
			paramsSub.put("yearStart", DateUtils.getCurrentYear());
			paramsSub.put("yearEnd", (DateUtils.getCurrentYear()-1));
			List<SecrecySupervisionContent> secrecySupervisionContentList = this.secrecySupervisionContentService.findList(hqlSub.toString(), paramsSub, 0,2);
			this.getSecrecySupervision().setSecrecySupervisionContentSet(new HashSet<SecrecySupervisionContent>(secrecySupervisionContentList));
		}else{
			//设置基础数据
			secrecySupervision = new SecrecySupervision();
			secrecySupervision.setYear(DateUtils.getCurrentYear());
			secrecySupervision.setReportUser(getCurrentUser());
			secrecySupervision.setReportOrgan(getCurrentUser().getOrgan());
			secrecySupervision.setReportDepartment(getCurrentUser().getUserInfo().getDepartment());
			secrecySupervision.setCreatePerson(getCurrentUser());
			secrecySupervision.setCreateTime(new Date());
			secrecySupervision.setCreateOrgan(this.getCurrentUser().getOrgan());
			secrecySupervision.setCreateDepartment(this.getCurrentUser().getUserInfo().getDepartment());

			secrecySupervision.setReportDate(new Date());
			StringBuffer hqlSub = new StringBuffer("from SecrecySupervisionContent sc where sc.organ = :organ and sc.year BETWEEN :yearEnd AND :yearStart order by  sc.year asc");
			Map<String, Object> paramsSub = new HashMap<String, Object>();
			paramsSub.put("organ", this.getCurrentUser().getOrgan());
			paramsSub.put("yearStart", DateUtils.getCurrentYear());
			paramsSub.put("yearEnd", (DateUtils.getCurrentYear()-2));
			List<SecrecySupervisionContent> secrecySupervisionContentList = this.secrecySupervisionContentService.findList(hqlSub.toString(), paramsSub, 0,2);
			SecrecySupervisionContent secrecySupervisionContentNew = new SecrecySupervisionContent();
			secrecySupervisionContentNew.setYear(DateUtils.getCurrentYear());
			if( secrecySupervisionContentList!=null && secrecySupervisionContentList.size()>0 ){
				secrecySupervisionContentList.add(secrecySupervisionContentNew);
			}else{
				secrecySupervisionContentList = new ArrayList<SecrecySupervisionContent>();
				secrecySupervisionContentList.add(secrecySupervisionContentNew);
			}
			this.getSecrecySupervision().setSecrecySupervisionContentSet( new HashSet<SecrecySupervisionContent>(secrecySupervisionContentList));
		}
		return "success";
	}

	/**
	 *
	 * <p>
	 * 方法名：save
	 * </p>
	 * <p>
	 * 宋亚非 2013-5-17 下午4:34:38
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@return
	 */
	public String save(){
		String rerurnMessage = this.secrecySupervisionService.saveOrUpdateData(secrecySupervision, secSupConSet );
		addActionMessage(rerurnMessage);
		return "success";
	}

	/**
	 *
	 * <p>
	 * 方法名：detail
	 * </p>
	 * <p>
	 * 宋亚非 2013-5-18 下午6:26:49
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@return
	 */
	public String detail(){
		if (year == null || "".equals(year)) {
			year = DateUtils.getCurrentYear();
		}
		if (organ == null) {
			organ = new Organ();
		}
		if (LangUtils.isEmpty(organ.getId())) {
			organ.setOrganId(getCurrentUser().getOrgan().getId());
		}
		StringBuffer hql = new StringBuffer("from SecrecySupervision s where s.year = :year and s.reportOrgan = :reportOrgan");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("year", year);
		params.put("reportOrgan", organ);
		List<SecrecySupervision> secrecySupervisionList = this.secrecySupervisionService.findList(hql.toString(), params);
		if( secrecySupervisionList!=null && secrecySupervisionList.size()>0){
			this.setSecrecySupervision(secrecySupervisionList.get(0));
			StringBuffer hqlSub = new StringBuffer("from SecrecySupervisionContent sc where sc.organ = :organ and sc.year BETWEEN :yearEnd AND :yearStart order by  sc.year asc");
			Map<String, Object> paramsSub = new HashMap<String, Object>();
			paramsSub.put("organ", organ);
			paramsSub.put("yearStart", year);
			paramsSub.put("yearEnd", (year-1));
			List<SecrecySupervisionContent> secrecySupervisionContentList = this.secrecySupervisionContentService.findList(hqlSub.toString(), paramsSub, 0,2);
			this.getSecrecySupervision().setSecrecySupervisionContentSet(new HashSet<SecrecySupervisionContent>(secrecySupervisionContentList));
		}else{
			//设置基础数据
			secrecySupervision = new SecrecySupervision();
			secrecySupervision.setYear(year);
		}
		return "success";
	}

	/* getter and setter */
	/**
	 * @return the secrecySupervisionService
	 */
	public SecrecySupervisionService getSecrecySupervisionService() {
		return secrecySupervisionService;
	}

	/**
	 * @param secrecySupervisionService the secrecySupervisionService to set
	 */
	public void setSecrecySupervisionService(
			SecrecySupervisionService secrecySupervisionService) {
		this.secrecySupervisionService = secrecySupervisionService;
	}

	/**
	 * @return the secrecySupervisionContentService
	 */
	public SecrecySupervisionContentService getSecrecySupervisionContentService() {
		return secrecySupervisionContentService;
	}

	/**
	 * @param secrecySupervisionContentService the secrecySupervisionContentService to set
	 */
	public void setSecrecySupervisionContentService(
			SecrecySupervisionContentService secrecySupervisionContentService) {
		this.secrecySupervisionContentService = secrecySupervisionContentService;
	}

	/**
	 * @return the secrecySupervision
	 */
	public SecrecySupervision getSecrecySupervision() {
		return secrecySupervision;
	}

	/**
	 * @param secrecySupervision the secrecySupervision to set
	 */
	public void setSecrecySupervision(SecrecySupervision secrecySupervision) {
		this.secrecySupervision = secrecySupervision;
	}

	/**
	 * @return the secSupConSet
	 */
	public List<SecrecySupervisionContent> getSecSupConSet() {
		return secSupConSet;
	}

	/**
	 * @param secSupConSet the secSupConSet to set
	 */
	public void setSecSupConSet(List<SecrecySupervisionContent> secSupConSet) {
		this.secSupConSet = secSupConSet;
	}

	/**
	 * @return the year
	 */
	public Integer getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(Integer year) {
		this.year = year;
	}

	/**
	 * @return the organ
	 */
	public Organ getOrgan() {
		return organ;
	}

	/**
	 * @param organ the organ to set
	 */
	public void setOrgan(Organ organ) {
		this.organ = organ;
	}




}
