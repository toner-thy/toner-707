/**
 *
 */
package com.cdthgk.bmp.secrecynet.secrecytechnologytrain.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.secrecynet.secrecytechnologytrain.service.SecrecyTechnologyTrainContentService;
import com.cdthgk.bmp.secrecynet.secrecytechnologytrain.service.SecrecyTechnologyTrainService;
import com.cdthgk.bmp.secrecynet.secrecytechnologytrain.vo.SecrecyTechnologyTrain;
import com.cdthgk.bmp.secrecynet.secrecytechnologytrain.vo.SecrecyTechnologyTrainContent;
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
public class SecrecyTechnologyTrainAction extends PlatformAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private SecrecyTechnologyTrainService secrecyTechnologyTrainService;
	private SecrecyTechnologyTrainContentService secrecyTechnologyTrainContentService;
	private SecrecyTechnologyTrain secrecyTechnologyTrain;
	//子表集合
	private List<SecrecyTechnologyTrainContent> secTechTrainConSet;

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
		StringBuffer hql = new StringBuffer("from SecrecyTechnologyTrain s where s.year = :year and s.reportOrgan = :reportOrgan");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("year", DateUtils.getCurrentYear());
		params.put("reportOrgan", this.getCurrentUser().getOrgan());
		List<SecrecyTechnologyTrain> secrecyTechnologyTrainList = this.secrecyTechnologyTrainService.findList(hql.toString(), params);
		if( secrecyTechnologyTrainList!=null && secrecyTechnologyTrainList.size()>0){
			this.setSecrecyTechnologyTrain(secrecyTechnologyTrainList.get(0));
			StringBuffer hqlSub = new StringBuffer("from SecrecyTechnologyTrainContent sc where sc.organ = :organ and sc.year BETWEEN :yearEnd AND :yearStart order by  sc.year desc");
			Map<String, Object> paramsSub = new HashMap<String, Object>();
			paramsSub.put("organ", this.getCurrentUser().getOrgan());
			paramsSub.put("yearStart", DateUtils.getCurrentYear());
			paramsSub.put("yearEnd", (DateUtils.getCurrentYear()-2));
			List<SecrecyTechnologyTrainContent> secrecyTechnologyTrainContentList = this.secrecyTechnologyTrainContentService.findList(hqlSub.toString(), paramsSub, 0,3);
			this.getSecrecyTechnologyTrain().setSecrecyTechnologyTrainContentSet(new HashSet<SecrecyTechnologyTrainContent>(secrecyTechnologyTrainContentList));
		}else{
			//设置基础数据
			secrecyTechnologyTrain = new SecrecyTechnologyTrain();
			secrecyTechnologyTrain.setYear(DateUtils.getCurrentYear());
			secrecyTechnologyTrain.setReportUser(getCurrentUser());
			secrecyTechnologyTrain.setReportOrgan(getCurrentUser().getOrgan());
			secrecyTechnologyTrain.setReportDepartment(getCurrentUser().getUserInfo().getDepartment());
			secrecyTechnologyTrain.setCreatePerson(getCurrentUser());
			secrecyTechnologyTrain.setCreateTime(new Date());
			secrecyTechnologyTrain.setCreateOrgan(this.getCurrentUser().getOrgan());
			secrecyTechnologyTrain.setCreateDepartment(this.getCurrentUser().getUserInfo().getDepartment());

			secrecyTechnologyTrain.setReportDate(new Date());
			StringBuffer hqlSub = new StringBuffer("from SecrecyTechnologyTrainContent sc where sc.organ = :organ and sc.year BETWEEN :yearEnd AND :yearStart order by  sc.year desc");
			Map<String, Object> paramsSub = new HashMap<String, Object>();
			paramsSub.put("organ", this.getCurrentUser().getOrgan());
			paramsSub.put("yearStart", DateUtils.getCurrentYear());
			paramsSub.put("yearEnd", (DateUtils.getCurrentYear()-2));
			List<SecrecyTechnologyTrainContent> secrecyTechnologyTrainContentList = this.secrecyTechnologyTrainContentService.findList(hqlSub.toString(), paramsSub, 0, 3);
			SecrecyTechnologyTrainContent secrecyTechnologyTrainContentNew = new SecrecyTechnologyTrainContent();
			secrecyTechnologyTrainContentNew.setYear(DateUtils.getCurrentYear());
			if( secrecyTechnologyTrainContentList!=null && secrecyTechnologyTrainContentList.size()>0 ){
				secrecyTechnologyTrainContentList.add(secrecyTechnologyTrainContentNew);
			}else{
				secrecyTechnologyTrainContentList = new ArrayList<SecrecyTechnologyTrainContent>();
				secrecyTechnologyTrainContentList.add(secrecyTechnologyTrainContentNew);
			}
			this.getSecrecyTechnologyTrain().setSecrecyTechnologyTrainContentSet(new HashSet<SecrecyTechnologyTrainContent>(secrecyTechnologyTrainContentList));
		}
		return "success";
	}

	/**
	 *
	 * <p>
	 * 方法名：detail
	 * </p>
	 * <p>
	 * 宋亚非 2013-5-18 下午6:16:48
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
		if (year == null) {
			year = DateUtils.getCurrentYear();
		}
		if (organ == null) {
			organ = new Organ();
		}
		if (LangUtils.isEmpty(organ.getId())) {
			organ.setOrganId(getCurrentUser().getOrgan().getId());
		}
		StringBuffer hql = new StringBuffer("from SecrecyTechnologyTrain s where s.year = :year and s.reportOrgan = :reportOrgan");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("year", year);
		params.put("reportOrgan", organ);
		List<SecrecyTechnologyTrain> secrecyTechnologyTrainList = this.secrecyTechnologyTrainService.findList(hql.toString(), params);
		if( secrecyTechnologyTrainList!=null && secrecyTechnologyTrainList.size()>0){
			this.setSecrecyTechnologyTrain(secrecyTechnologyTrainList.get(0));
			StringBuffer hqlSub = new StringBuffer("from SecrecyTechnologyTrainContent sc where sc.organ = :organ and sc.year BETWEEN :yearEnd AND :yearStart order by  sc.year desc");
			Map<String, Object> paramsSub = new HashMap<String, Object>();
			paramsSub.put("organ", organ);
			paramsSub.put("yearStart", year);
			paramsSub.put("yearEnd", (year-2));
			List<SecrecyTechnologyTrainContent> secrecyTechnologyTrainContentList = this.secrecyTechnologyTrainContentService.findList(hqlSub.toString(), paramsSub, 0,3);
			this.getSecrecyTechnologyTrain().setSecrecyTechnologyTrainContentSet(new HashSet<SecrecyTechnologyTrainContent>(secrecyTechnologyTrainContentList));
		}else{
			secrecyTechnologyTrain = new SecrecyTechnologyTrain();
			secrecyTechnologyTrain.setYear(DateUtils.getCurrentYear());
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
		String rerurnMessage = this.secrecyTechnologyTrainService.saveOrUpdateData(secrecyTechnologyTrain, secTechTrainConSet );
		addActionMessage(rerurnMessage);
		return "success";
	}

	/* getter and setter */
	/**
	 * @return the secrecyTechnologyTrainService
	 */
	public SecrecyTechnologyTrainService getSecrecyTechnologyTrainService() {
		return secrecyTechnologyTrainService;
	}

	/**
	 * @param secrecyTechnologyTrainService the secrecyTechnologyTrainService to set
	 */
	public void setSecrecyTechnologyTrainService(
			SecrecyTechnologyTrainService secrecyTechnologyTrainService) {
		this.secrecyTechnologyTrainService = secrecyTechnologyTrainService;
	}

	/**
	 * @return the secrecyTechnologyTrainContentService
	 */
	public SecrecyTechnologyTrainContentService getSecrecyTechnologyTrainContentService() {
		return secrecyTechnologyTrainContentService;
	}

	/**
	 * @param secrecyTechnologyTrainContentService the secrecyTechnologyTrainContentService to set
	 */
	public void setSecrecyTechnologyTrainContentService(
			SecrecyTechnologyTrainContentService secrecyTechnologyTrainContentService) {
		this.secrecyTechnologyTrainContentService = secrecyTechnologyTrainContentService;
	}

	/**
	 * @return the secrecyTechnologyTrain
	 */
	public SecrecyTechnologyTrain getSecrecyTechnologyTrain() {
		return secrecyTechnologyTrain;
	}

	/**
	 * @param secrecyTechnologyTrain the secrecyTechnologyTrain to set
	 */
	public void setSecrecyTechnologyTrain(
			SecrecyTechnologyTrain secrecyTechnologyTrain) {
		this.secrecyTechnologyTrain = secrecyTechnologyTrain;
	}

	/**
	 * @return the secTechTrainConSet
	 */
	public List<SecrecyTechnologyTrainContent> getSecTechTrainConSet() {
		return secTechTrainConSet;
	}

	/**
	 * @param secTechTrainConSet the secTechTrainConSet to set
	 */
	public void setSecTechTrainConSet(
			List<SecrecyTechnologyTrainContent> secTechTrainConSet) {
		this.secTechTrainConSet = secTechTrainConSet;
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
