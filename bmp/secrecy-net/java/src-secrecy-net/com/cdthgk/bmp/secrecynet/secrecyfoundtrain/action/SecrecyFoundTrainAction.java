/**
 *
 */
package com.cdthgk.bmp.secrecynet.secrecyfoundtrain.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.secrecynet.secrecyfoundtrain.service.SecrecyFoundTrainContentService;
import com.cdthgk.bmp.secrecynet.secrecyfoundtrain.service.SecrecyFoundTrainService;
import com.cdthgk.bmp.secrecynet.secrecyfoundtrain.vo.SecrecyFoundTrain;
import com.cdthgk.bmp.secrecynet.secrecyfoundtrain.vo.SecrecyFoundTrainContent;
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
public class SecrecyFoundTrainAction extends PlatformAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private SecrecyFoundTrainService secrecyFoundTrainService;
	private SecrecyFoundTrainContentService secrecyFoundTrainContentService;
	private SecrecyFoundTrain secrecyFoundTrain;
	//子表集合
	private List<SecrecyFoundTrainContent> secFounTrainConSet;

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
		if (year == null) {
			year = DateUtils.getCurrentYear();
		}
		if (organ == null) {
			organ = new Organ();
		}
		if (LangUtils.isEmpty(organ.getId())) {
			organ.setOrganId(getCurrentUser().getOrgan().getId());
		}

		//查询当年的记录
		StringBuffer hql = new StringBuffer("from SecrecyFoundTrain s where s.year = :year and s.reportOrgan = :reportOrgan");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("year", year);
		params.put("reportOrgan", organ);
		List<SecrecyFoundTrain> secrecyFoundTrainList = this.secrecyFoundTrainService.findList(hql.toString(), params);
		if( secrecyFoundTrainList!=null && secrecyFoundTrainList.size()>0){
			this.setSecrecyFoundTrain(secrecyFoundTrainList.get(0));
			StringBuffer hqlSub = new StringBuffer("from SecrecyFoundTrainContent sc where sc.organ = :organ and sc.year BETWEEN :yearEnd AND :yearStart order by  sc.year asc");
			Map<String, Object> paramsSub = new HashMap<String, Object>();
			paramsSub.put("organ", organ);
			paramsSub.put("yearStart", year);
			paramsSub.put("yearEnd", (year - 2));
			List<SecrecyFoundTrainContent> secrecyFoundTrainContentList = this.secrecyFoundTrainContentService.findList(hqlSub.toString(), paramsSub, 0,3);
			this.getSecrecyFoundTrain().setSecrecyFoundTrainContentSet(new HashSet<SecrecyFoundTrainContent>(secrecyFoundTrainContentList));
		}else{
			//设置基础数据
			secrecyFoundTrain = new SecrecyFoundTrain();
			secrecyFoundTrain.setYear(DateUtils.getCurrentYear());
			secrecyFoundTrain.setReportUser(getCurrentUser());
			secrecyFoundTrain.setReportOrgan(getCurrentUser().getOrgan());
			secrecyFoundTrain.setReportDepartment(getCurrentUser().getUserInfo().getDepartment());
			secrecyFoundTrain.setCreatePerson(getCurrentUser());
			secrecyFoundTrain.setCreateTime(new Date());
			secrecyFoundTrain.setCreateOrgan(this.getCurrentUser().getOrgan());
			secrecyFoundTrain.setCreateDepartment(this.getCurrentUser().getUserInfo().getDepartment());

			secrecyFoundTrain.setReportDate(new Date());
			StringBuffer hqlSub = new StringBuffer("from SecrecyFoundTrainContent sc where sc.organ = :organ and sc.year BETWEEN :yearEnd AND :yearStart order by  sc.year asc");
			Map<String, Object> paramsSub = new HashMap<String, Object>();
			paramsSub.put("organ", organ);
			paramsSub.put("yearStart", year);
			paramsSub.put("yearEnd", (year-2));
			List<SecrecyFoundTrainContent> secrecyFoundTrainContentList = this.secrecyFoundTrainContentService.findList(hqlSub.toString(), paramsSub, 0,3);
			SecrecyFoundTrainContent secrecyFoundTrainContentNew = new SecrecyFoundTrainContent();
			secrecyFoundTrainContentNew.setYear(DateUtils.getCurrentYear());
			if( secrecyFoundTrainContentList!=null && secrecyFoundTrainContentList.size()>0 ){
				secrecyFoundTrainContentList.add(secrecyFoundTrainContentNew);
			}else{
				secrecyFoundTrainContentList = new ArrayList<SecrecyFoundTrainContent>();
				secrecyFoundTrainContentList.add(secrecyFoundTrainContentNew);
			}
			this.getSecrecyFoundTrain().setSecrecyFoundTrainContentSet( new HashSet<SecrecyFoundTrainContent>(secrecyFoundTrainContentList));
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
		String rerurnMessage = this.secrecyFoundTrainService.saveOrUpdateData(secrecyFoundTrain, secFounTrainConSet );
		addActionMessage(rerurnMessage);
		return "success";
	}

	/**
	 *
	 * <p>
	 * 方法名：detail
	 * </p>
	 * <p>
	 * 宋亚非 2013-5-18 下午6:03:11
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

		//查询当年的记录
		StringBuffer hql = new StringBuffer("from SecrecyFoundTrain s where s.year = :year and s.reportOrgan = :reportOrgan");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("year", year);
		params.put("reportOrgan", organ);
		List<SecrecyFoundTrain> secrecyFoundTrainList = this.secrecyFoundTrainService.findList(hql.toString(), params);
		if( secrecyFoundTrainList!=null && secrecyFoundTrainList.size()>0){
			this.setSecrecyFoundTrain(secrecyFoundTrainList.get(0));
			StringBuffer hqlSub = new StringBuffer("from SecrecyFoundTrainContent sc where sc.organ = :organ and sc.year BETWEEN :yearEnd AND :yearStart order by  sc.year asc");
			Map<String, Object> paramsSub = new HashMap<String, Object>();
			paramsSub.put("organ", organ);
			paramsSub.put("yearStart", year);
			paramsSub.put("yearEnd", (year - 2));
			List<SecrecyFoundTrainContent> secrecyFoundTrainContentList = this.secrecyFoundTrainContentService.findList(hqlSub.toString(), paramsSub, 0,3);
			this.getSecrecyFoundTrain().setSecrecyFoundTrainContentSet(new HashSet<SecrecyFoundTrainContent>(secrecyFoundTrainContentList));
		}else{
			secrecyFoundTrain = new SecrecyFoundTrain();
			secrecyFoundTrain.setYear(DateUtils.getCurrentYear());
		}
		return "success";
	}

	/* getter and setter */

	/**
	 * @return the secrecyFoundTrain
	 */
	public SecrecyFoundTrain getSecrecyFoundTrain() {
		return secrecyFoundTrain;
	}

	/**
	 * @param secrecyFoundTrain the secrecyFoundTrain to set
	 */
	public void setSecrecyFoundTrain(SecrecyFoundTrain secrecyFoundTrain) {
		this.secrecyFoundTrain = secrecyFoundTrain;
	}

	/**
	 * @return the secrecyFoundTrainService
	 */
	public SecrecyFoundTrainService getSecrecyFoundTrainService() {
		return secrecyFoundTrainService;
	}

	/**
	 * @param secrecyFoundTrainService the secrecyFoundTrainService to set
	 */
	public void setSecrecyFoundTrainService(
			SecrecyFoundTrainService secrecyFoundTrainService) {
		this.secrecyFoundTrainService = secrecyFoundTrainService;
	}

	/**
	 * @return the secrecyFoundTrainContentService
	 */
	public SecrecyFoundTrainContentService getSecrecyFoundTrainContentService() {
		return secrecyFoundTrainContentService;
	}

	/**
	 * @param secrecyFoundTrainContentService the secrecyFoundTrainContentService to set
	 */
	public void setSecrecyFoundTrainContentService(
			SecrecyFoundTrainContentService secrecyFoundTrainContentService) {
		this.secrecyFoundTrainContentService = secrecyFoundTrainContentService;
	}

	/**
	 * @return the secFounTrainConSet
	 */
	public List<SecrecyFoundTrainContent> getSecFounTrainConSet() {
		return secFounTrainConSet;
	}

	/**
	 * @param secFounTrainConSet the secFounTrainConSet to set
	 */
	public void setSecFounTrainConSet(
			List<SecrecyFoundTrainContent> secFounTrainConSet) {
		this.secFounTrainConSet = secFounTrainConSet;
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
