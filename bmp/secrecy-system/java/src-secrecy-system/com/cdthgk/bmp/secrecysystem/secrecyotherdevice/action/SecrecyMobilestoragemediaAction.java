/**
 *
 */
package com.cdthgk.bmp.secrecysystem.secrecyotherdevice.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.action.BmpAction;
import com.cdthgk.bmp.keyPart.vo.Part;
import com.cdthgk.bmp.keysection.vo.KeySection;
import com.cdthgk.bmp.log.context.BusinessLogContext;
import com.cdthgk.bmp.log.vo.BusinessLog;
import com.cdthgk.bmp.secrecycountryitem.qo.ZongHeTongJiStatDto;
import com.cdthgk.bmp.secrecysystem.secrecynetwork.vo.SecrecyNetwork;
import com.cdthgk.bmp.secrecysystem.secrecyotherdevice.service.SecrecyMobilestoragemediaChangeService;
import com.cdthgk.bmp.secrecysystem.secrecyotherdevice.service.SecrecyMobilestoragemediaClearService;
import com.cdthgk.bmp.secrecysystem.secrecyotherdevice.service.SecrecyMobilestoragemediaService;
import com.cdthgk.bmp.secrecysystem.secrecyotherdevice.vo.SecrecyMobilestoragemedia;
import com.cdthgk.bmp.secrecysystem.secrecyotherdevice.vo.SecrecyMobilestoragemediaChange;
import com.cdthgk.bmp.secrecysystem.secrecyotherdevice.vo.SecrecyMobilestoragemediaClear;
import com.cdthgk.bmp.statinfo.dto.QueryDto;
import com.cdthgk.common.bean.BeanUtils;
import com.cdthgk.common.bean.rule.CopyRuleEnum;
import com.cdthgk.common.lang.CollectionUtils;
import com.cdthgk.common.lang.StringUtils;
import com.cdthgk.platform.dataValidate.service.DataValidateService;
import com.cdthgk.platform.dictionary.context.DictionaryContext;
import com.cdthgk.platform.dictionary.domain.DictionaryOption;
import com.cdthgk.platform.dictionary.spi.DictionaryService;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.district.service.DistrictService;
import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;

import ec.common.PageSortModel;

/**
 * <p>
 * 类的说明放这里  2013-7-31 下午1:53:42
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 *
 * @author 宋亚非
 */
public class SecrecyMobilestoragemediaAction extends BmpAction {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private SecrecyMobilestoragemediaService secrecyMobilestoragemediaService;
	private SecrecyMobilestoragemediaChangeService secrecyMobilestoragemediaChangeService;
	private SecrecyMobilestoragemediaClearService secrecyMobilestoragemediaClearService;
	private SecrecyMobilestoragemedia secrecyMobilestoragemedia;
	private SecrecyMobilestoragemediaChange secrecyMobilestoragemediaChange;
	private SecrecyMobilestoragemediaClear secrecyMobilestoragemediaClear;
	private DistrictService districtService;
	private DataValidateService dataValidateService;
	private Boolean needReload = false;
	private District district;

	/**
	 *
	 * <p>
	 * 方法名：main 访问主页面
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-6 下午3:53:12
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
	public String main(){
		String districtCode = this.getRequest().getParameter("districtCode");
		if( districtCode!=null && !"".equals(districtCode) ){
			this.putToRequest("districtCode", districtCode);
		}
		String fromQuery = this.getRequest().getParameter("fromQuery");// 表示从mainQuery过来的
		if (StringUtils.isEmpty(fromQuery)) {
			fromQuery = "0";
		}
		if( "1".equals(fromQuery) && (districtCode==null || "".equals(districtCode)) ){
			this.putToRequest("districtCode", getCurrentUser().getOrgan().getDistrict().getDistrictCode());
		}
		String checkLower = this.getRequest().getParameter("checkLower");// 表示从mainQuery过来的
		if (StringUtils.isEmpty(checkLower)) {
			checkLower = "0";
		}
		this.putToRequest("fromQuery", fromQuery);
		this.putToRequest("checkLower", checkLower);
		return "main";
	}

	/**
	 *
	 * <p>
	 * 方法名：list 列表页
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-6 下午3:53:23
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
	public String list(){
		String districtCode = this.getRequest().getParameter("districtCode");
		District district = null;
		Organ organ = null;
		String fromQuery = this.getRequest().getParameter("fromQuery");// 表示从mainQuery过来的
		if (StringUtils.isEmpty(fromQuery)) {
			fromQuery = "0";
		}
		String checkLower = this.getRequest().getParameter("checkLower");// 表示从mainQuery过来的
		if (StringUtils.isEmpty(checkLower)) {
			checkLower = "0";
		}
		if( districtCode!=null && !"".equals(districtCode) && "1".equals(fromQuery) ){
			this.putToRequest("districtCode", districtCode);
			district = this.districtService.get(districtCode);
			putToRequest("district", district);
			this.putToRequest("fromQuery", fromQuery);
			this.putToRequest("checkLower", checkLower);
		}else{
			//查询登录用户与创建单位相同的信息
			organ = getCurrentUser().getOrgan();
		}

		//综合统计使用
		String secrecyType = this.getRequest().getParameter("secrecyType");
		String organId = this.getRequest().getParameter("organId");
		if( secrecyType!=null ){
			if( secrecyMobilestoragemedia==null ){
				secrecyMobilestoragemedia = new SecrecyMobilestoragemedia();
				secrecyMobilestoragemedia.setSecrecyLevel(Integer.parseInt(secrecyType));
				this.putToRequest("secrecyType", secrecyType);
			}
		}
		if( organId!=null ){
			organ = this.secrecyMobilestoragemediaService.get(organId, Organ.class);
			this.putToRequest("organId", organId);
			this.putToRequest("organ", organ);
                        this.putToRequest("zhtj", "1");
		}

		PageSortModel<SecrecyMobilestoragemedia> psm = new PageSortModel<SecrecyMobilestoragemedia>(getRequest(), "secrecymobilestoragemediaListTable");
		List<SecrecyMobilestoragemedia> secrecyMobilestoragemediaList = this.secrecyMobilestoragemediaService.findPageAllList(psm, secrecyMobilestoragemedia, organ, district,checkLower, SECRECY_STATUS_NOW);
		this.putToRequest("secrecyMobilestoragemediaList", secrecyMobilestoragemediaList);
		return "list";
	}

	/**
	 *
	 * <p>
	 * 方法名：add 新增
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-6 下午3:53:40
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
		List<DictionaryOption> isbelongKeydepartmentOptions = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "is_section_part");
		this.putToRequest("isbelongKeydepartmentOptions", isbelongKeydepartmentOptions);
		return SUCCESS;
	}

	/**
	 *
	 * <p>
	 * 方法名：adding 新增保存
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-6 下午3:53:48
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
	public String adding(){
		secrecyMobilestoragemedia.setCreatePerson(getCurrentUser());
		secrecyMobilestoragemedia.setCreateTime(new Date(System.currentTimeMillis()));
		secrecyMobilestoragemedia.setCreateOrgan(getCurrentUser().getOrgan());
		secrecyMobilestoragemedia.setSecrecyStatus(SECRECY_STATUS_NOW);

		Department department = null;
		// 设置部门ID
		if(secrecyMobilestoragemedia.getIsbelongKeydepartment() == 1) {
			if (StringUtils.isNotEmpty(secrecyMobilestoragemedia.getKeyPart().getPartId())) {
				department = secrecyMobilestoragemediaService.get(secrecyMobilestoragemedia.getKeyPart().getPartId(), Part.class).getDepartment();
			}
			if (StringUtils.isNotEmpty(secrecyMobilestoragemedia.getKeySection().getKeySectionId())) {
				department = secrecyMobilestoragemediaService.get(secrecyMobilestoragemedia.getKeySection().getKeySectionId(), KeySection.class).getDepartment();
			}
		} else {
			if( secrecyMobilestoragemedia.getDepartment().getDepartmentId()!=null && !"".equals(secrecyMobilestoragemedia.getDepartment().getDepartmentId()) ){
				department = secrecyMobilestoragemediaService.get(secrecyMobilestoragemedia.getDepartment().getDepartmentId(), Department.class);
			}else{
				department = secrecyMobilestoragemediaService.addDepartment(secrecyMobilestoragemedia.getDepartment().getDepartmentName(), getCurrentUser());
			}
		}

		//处理责任人
		UserInfo dutyPerson = null;
		if( secrecyMobilestoragemedia.getDutyPerson()!=null ){
			if(secrecyMobilestoragemedia.getDutyPerson().getUserInfoId()!=null && !"".equals(secrecyMobilestoragemedia.getDutyPerson().getUserInfoId())){
				dutyPerson = this.secrecyMobilestoragemediaService.get(secrecyMobilestoragemedia.getDutyPerson().getUserInfoId(), UserInfo.class);
			}else{
				//新增人员
				dutyPerson = this.secrecyMobilestoragemediaService.addUserinfo(secrecyMobilestoragemedia.getDutyPerson().getName(), getCurrentUser());
			}
		}

		secrecyMobilestoragemedia.setDutyPerson(dutyPerson);
		//Department department = secrecyMobilestoragemediaService.get(secrecyMobilestoragemedia.getDepartment().getDepartmentId(), Department.class);
		secrecyMobilestoragemedia.setDepartment(department);
		this.secrecyMobilestoragemediaService.save(secrecyMobilestoragemedia);
		this.setNeedReload(true);
		addActionMessage(getMessageConstant().getSaveSuccess());
		BusinessLog log = new BusinessLog();
		log.setBusinessName("涉密移动存储介质");
		log.setPrimaryKey(secrecyMobilestoragemedia.getSecrecymobilestoragemediaId());
		BusinessLogContext
				.getInstance()
				.getBusinessLogService()
				.saveAddBusinessLogByModule(getCurrentUser(), log,
						new SecrecyMobilestoragemedia());
		return redirectActionResult(LIST);
	}

	/**
	 *
	 * <p>
	 * 方法名：edit 编辑
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-6 下午3:53:59
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
	public String edit(){
		List<DictionaryOption> isbelongKeydepartmentOptions = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "is_section_part");
		this.putToRequest("isbelongKeydepartmentOptions", isbelongKeydepartmentOptions);
		secrecyMobilestoragemedia = this.secrecyMobilestoragemediaService.get(secrecyMobilestoragemedia.getSecrecymobilestoragemediaId());
		return EDIT;
	}

	/**
	 *
	 * <p>
	 * 方法名：editing 编辑保存
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-6 下午3:54:07
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
	public String editing(){
		SecrecyMobilestoragemedia beforeSm=new SecrecyMobilestoragemedia();
		SecrecyMobilestoragemedia secrecyMobilestoragemediaDb = secrecyMobilestoragemediaService.get(secrecyMobilestoragemedia.getSecrecymobilestoragemediaId());
		BeanUtils.copyProperties(beforeSm, secrecyMobilestoragemediaDb, CopyRuleEnum.ignoreCaseNull);
		BeanUtils.copyProperties(secrecyMobilestoragemediaDb, secrecyMobilestoragemedia, CopyRuleEnum.ignoreCaseEmpty);
		Department department = null;
		// 设置部门ID
		if(secrecyMobilestoragemediaDb.getIsbelongKeydepartment() == 1) {
			if (StringUtils.isNotEmpty(secrecyMobilestoragemediaDb.getKeyPart().getPartId())) {
				department = secrecyMobilestoragemediaService.get(secrecyMobilestoragemediaDb.getKeyPart().getPartId(), Part.class).getDepartment();
			}
			if (StringUtils.isNotEmpty(secrecyMobilestoragemediaDb.getKeySection().getKeySectionId())) {
				department = secrecyMobilestoragemediaService.get(secrecyMobilestoragemediaDb.getKeySection().getKeySectionId(), KeySection.class).getDepartment();
			}
		} else {
			if(secrecyMobilestoragemedia.getDepartment().getDepartmentId()!=null && !"".equals(secrecyMobilestoragemedia.getDepartment().getDepartmentId())){
				department = secrecyMobilestoragemediaService.get(secrecyMobilestoragemedia.getDepartment().getDepartmentId(), Department.class);
			}else{
				department = secrecyMobilestoragemediaService.addDepartment(secrecyMobilestoragemedia.getDepartment().getDepartmentName(), getCurrentUser());
			}
		}

		//处理责任人
		UserInfo dutyPerson = null;
		if( secrecyMobilestoragemedia.getDutyPerson()!=null ){
			if(secrecyMobilestoragemedia.getDutyPerson().getUserInfoId()!=null && !"".equals(secrecyMobilestoragemedia.getDutyPerson().getUserInfoId())){
				dutyPerson = this.secrecyMobilestoragemediaService.get(secrecyMobilestoragemedia.getDutyPerson().getUserInfoId(), UserInfo.class);
			}else{
				//新增人员
				dutyPerson = this.secrecyMobilestoragemediaService.addUserinfo(secrecyMobilestoragemedia.getDutyPerson().getName(), getCurrentUser());
			}
		}

		secrecyMobilestoragemediaDb.setDutyPerson(dutyPerson);

		//Department department = secrecyMobilestoragemediaService.get(secrecyMobilestoragemediaDb.getDepartment().getDepartmentId(), Department.class);
		secrecyMobilestoragemediaDb.setDepartment(department);
		// 设置通用字段
		secrecyMobilestoragemediaDb.setModifyTime(new Date());
		secrecyMobilestoragemediaDb.setModifyPerson(getCurrentUser());
		secrecyMobilestoragemediaDb.setModifyOrgan(getCurrentUser().getUserInfo().getOrgan());
		secrecyMobilestoragemediaService.update(secrecyMobilestoragemediaDb);
		putToRequest("secrecyMobilestoragemedia", secrecyMobilestoragemediaDb);
		this.setNeedReload(true);
		addActionMessage(getMessageConstant().getUpdateSuccess());
		this.setNeedReload(true);
		 BusinessLog log = new BusinessLog();
	 		log.setBusinessName("涉密移动存储介质");
	 		log.setPrimaryKey(secrecyMobilestoragemediaDb.getSecrecymobilestoragemediaId());
	 		BusinessLogContext
	 				.getInstance()
	 				.getBusinessLogService()
	 				.saveEditBusinessLogByModule(getCurrentUser(), log,
	 						secrecyMobilestoragemediaDb, beforeSm);
		return redirectActionResult(LIST);
	}

	/**
	 *
	 * <p>
	 * 方法名：delete 删除
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-6 下午3:54:20
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
	public String delete(){
		String deleteIds = getRequest().getParameter("secrecymobilestoragemediaIds");
		String [] idsArray = deleteIds.split(",");
		String message = "";
		for (String id : idsArray) {
			SecrecyMobilestoragemedia secrecyMobilestoragemediaDb = secrecyMobilestoragemediaService.get(id);
			if(secrecyMobilestoragemediaDb != null){
				if(CollectionUtils.isEmpty(secrecyMobilestoragemediaDb.getSecrecyMobilestoragemediaChanges())
						&& CollectionUtils.isEmpty(secrecyMobilestoragemediaDb.getSecrecyMobilestoragemediaClears())){
					secrecyMobilestoragemediaService.delete(secrecyMobilestoragemediaDb);
					BusinessLog log = new BusinessLog();
         			log.setBusinessName("涉密计算机");
         			log.setPrimaryKey(secrecyMobilestoragemediaDb.getSecrecymobilestoragemediaId());
         			BusinessLogContext
         					.getInstance()
         					.getBusinessLogService()
         					.saveDelBusinessLogByModule(getCurrentUser(), log,
         							secrecyMobilestoragemediaDb);
					message = getMessageConstant().getDeleteSuccess();
				} else {
					message = getMessageConstant().getDeleteFailure();
				}
				addActionMessage(message);
			}
		}
		return this.redirectActionResult("list");
	}

	/**
	 *
	 * <p>
	 * 方法名：detail 显示明细
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-6 下午3:54:33
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
		List<DictionaryOption> isbelongKeydepartmentOptions = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "is_section_part");
		this.putToRequest("isbelongKeydepartmentOptions", isbelongKeydepartmentOptions);
		secrecyMobilestoragemedia = this.secrecyMobilestoragemediaService.get(secrecyMobilestoragemedia.getSecrecymobilestoragemediaId());
		return DETAIL;
	}

	/**
	 *
	 * <p>
	 * 方法名：secrecyLevelChangeHistory 密级变更历史列表界面
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-6 下午3:55:00
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
	public String secrecyLevelChangeHistory(){
		String districtCode = this.getRequest().getParameter("districtCode");
		District district = null;
		Organ organ = null;
		String fromQuery = this.getRequest().getParameter("fromQuery");// 表示从mainQuery过来的
		if (StringUtils.isEmpty(fromQuery)) {
			fromQuery = "0";
		}
		String checkLower = this.getRequest().getParameter("checkLower");// 表示从mainQuery过来的
		if (StringUtils.isEmpty(checkLower)) {
			checkLower = "0";
		}
		if( districtCode!=null && !"".equals(districtCode) && "1".equals(fromQuery) ){
			this.putToRequest("districtCode", districtCode);
			district = this.districtService.get(districtCode);
			putToRequest("district", district);
			this.putToRequest("fromQuery", fromQuery);
			this.putToRequest("checkLower", checkLower);
		}else{
			//查询登录用户与创建单位相同的信息
			organ = getCurrentUser().getOrgan();
		}
		PageSortModel<SecrecyMobilestoragemediaChange> psm = new PageSortModel<SecrecyMobilestoragemediaChange>(getRequest(), "secrecymobilestoragemediaChangeListTable");
		List<SecrecyMobilestoragemediaChange> secrecyMobilestoragemediaChangeList = this.secrecyMobilestoragemediaChangeService.findPageAllList(psm, secrecyMobilestoragemediaChange, organ, district, checkLower);
		this.putToRequest("secrecyMobilestoragemediaChangeList", secrecyMobilestoragemediaChangeList);
		return SUCCESS;
	}

	/**
	 *
	 * <p>
	 * 方法名：secrecyLevelChange 密级变更
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-6 下午3:55:18
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
	public String secrecyLevelChange(){
		secrecyMobilestoragemedia = this.secrecyMobilestoragemediaService.get(secrecyMobilestoragemedia.getSecrecymobilestoragemediaId());
		return SUCCESS;
	}

	/**
	 *
	 * <p>
	 * 方法名：secrecyLevelChanging 密级变更保存
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-6 下午3:55:33
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
	public String secrecyLevelChanging(){
		secrecyMobilestoragemedia = this.secrecyMobilestoragemediaService.get(secrecyMobilestoragemedia.getSecrecymobilestoragemediaId());
		if( secrecyMobilestoragemedia!=null ){
			secrecyMobilestoragemediaChange.setSecrecyMobilestoragemedia(secrecyMobilestoragemedia);
		}else{
			this.addActionMessage("您所访问的资源Id不存在，请确认后重试。");
			return this.redirectActionResult("list");
		}
		secrecyMobilestoragemediaChange.setCreatePerson(getCurrentUser());
		secrecyMobilestoragemediaChange.setCreateDate(new Date(System.currentTimeMillis()));
		this.secrecyMobilestoragemediaChangeService.save(secrecyMobilestoragemediaChange);
		secrecyMobilestoragemedia.setSecrecyLevel(secrecyMobilestoragemediaChange.getAfterLevel());
		this.secrecyMobilestoragemediaService.update(secrecyMobilestoragemedia);
		addActionMessage(getMessageConstant().getSaveSuccess());
		this.setNeedReload(true);
		return SUCCESS;
	}

	/**
	 *
	 * <p>
	 * 方法名：declassified  解除
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-5 下午4:09:34
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
	public String declassified(){
		secrecyMobilestoragemedia = this.secrecyMobilestoragemediaService.get(secrecyMobilestoragemedia.getSecrecymobilestoragemediaId());
		return SUCCESS;
	}

	/**
	 *
	 * <p>
	 * 方法名：declassifieding 解除密级保存方法
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-6 下午3:55:49
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
	public String declassifieding(){
		secrecyMobilestoragemedia = this.secrecyMobilestoragemediaService.get(secrecyMobilestoragemediaClear.getSecrecyMobilestoragemedia().getSecrecymobilestoragemediaId());
		secrecyMobilestoragemediaClear.setCreatePerson(getCurrentUser());
		secrecyMobilestoragemediaClear.setCreateDate(new Date(System.currentTimeMillis()));
		this.secrecyMobilestoragemediaClearService.save(secrecyMobilestoragemediaClear);
		secrecyMobilestoragemedia.setSecrecyStatus(PERSON_CHANGE_HISTORY);
		this.secrecyMobilestoragemediaService.update(secrecyMobilestoragemedia);
		addActionMessage(getMessageConstant().getSaveSuccess());
		this.setNeedReload(true);
		return SUCCESS;
	}

	/**
	 *
	 * <p>
	 * 方法名：declassifiedHistory 解密历史
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-6 下午3:56:12
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
	public String declassifiedHistory(){
		String districtCode = this.getRequest().getParameter("districtCode");
		District district = null;
		Organ organ = null;
		String fromQuery = this.getRequest().getParameter("fromQuery");// 表示从mainQuery过来的
		if (StringUtils.isEmpty(fromQuery)) {
			fromQuery = "0";
		}
		String checkLower = this.getRequest().getParameter("checkLower");// 表示从mainQuery过来的
		if (StringUtils.isEmpty(checkLower)) {
			checkLower = "0";
		}
		if( districtCode!=null && !"".equals(districtCode) && "1".equals(fromQuery) ){
			this.putToRequest("districtCode", districtCode);
			district = this.districtService.get(districtCode);
			putToRequest("district", district);
			this.putToRequest("fromQuery", fromQuery);
			this.putToRequest("checkLower", checkLower);
		}else{
			//查询登录用户与创建单位相同的信息
			organ = getCurrentUser().getOrgan();
		}
		PageSortModel<SecrecyMobilestoragemediaClear> psm = new PageSortModel<SecrecyMobilestoragemediaClear>(getRequest(), "secrecymobilestoragemediaClearListTable");
		List<SecrecyMobilestoragemediaClear> secrecyMobilestoragemediaClearList = this.secrecyMobilestoragemediaClearService.findPageAllList(psm, secrecyMobilestoragemediaClear, organ, district, checkLower);
		this.putToRequest("secrecyMobilestoragemediaClearList", secrecyMobilestoragemediaClearList);
		return SUCCESS;
	}

	/**
	 *
	 * <p>
	 * 方法名：export 导出
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-6 下午3:56:25
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
	public String export(){
		String districtCode = this.getRequest().getParameter("districtCode");
		District district = null;
		Organ organ = null;
		String fromQuery = this.getRequest().getParameter("fromQuery");// 表示从mainQuery过来的
		if (StringUtils.isEmpty(fromQuery)) {
			fromQuery = "0";
		}
		String checkLower = this.getRequest().getParameter("checkLower");// 表示从mainQuery过来的
		if (StringUtils.isEmpty(checkLower)) {
			checkLower = "0";
		}
		if( districtCode!=null && !"".equals(districtCode) && "1".equals(fromQuery) ){
			this.putToRequest("districtCode", districtCode);
			district = this.districtService.get(districtCode);
			putToRequest("district", district);
			this.putToRequest("fromQuery", fromQuery);
			this.putToRequest("checkLower", checkLower);
		}else{
			//查询登录用户与创建单位相同的信息
			organ = getCurrentUser().getOrgan();
		}
		List<SecrecyMobilestoragemedia> secrecyMobilestoragemediaList = this.secrecyMobilestoragemediaService.findPageAllList(null, secrecyMobilestoragemedia, organ, district, checkLower, SECRECY_STATUS_NOW);

		Map<String, Object> params = new HashMap<String, Object>();
		//被导出的数据
		params.put("dataList", secrecyMobilestoragemediaList);
		//时间格式化器
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		params.put("dateFormat", dateFormat);
		//数据字典工具
		DictionaryService dictionary = DictionaryContext.getInstance().getDictionaryService();
		params.put("dictionary", dictionary);
		//数据类型转换
		params.put("Integer", Integer.class);
		setResultData(params);
		return SUCCESS;
	}

	public String secrecyLevelChangeDetail(){
		secrecyMobilestoragemediaChange = this.secrecyMobilestoragemediaChangeService.get(secrecyMobilestoragemediaChange.getMobilestoragemediaChangeId());
		return SUCCESS;
	}

	public String secrecyDeclassifiedDetail(){
		secrecyMobilestoragemediaClear = this.secrecyMobilestoragemediaClearService.get(secrecyMobilestoragemediaClear.getMobilestoragemediaClearId());
		return SUCCESS;
	}

	public String baseDetail(){
		secrecyMobilestoragemedia = this.secrecyMobilestoragemediaService.get(secrecyMobilestoragemedia.getSecrecymobilestoragemediaId());
		return SUCCESS;
	}

	public String levalChangeDetail(){
		if( secrecyMobilestoragemedia!=null && secrecyMobilestoragemedia.getSecrecymobilestoragemediaId()!=null && !"".equals(secrecyMobilestoragemedia.getSecrecymobilestoragemediaId()) ){
			List<SecrecyMobilestoragemediaChange> secrecyMobilestoragemediaChangeList
				= this.secrecyMobilestoragemediaChangeService.findDataList(secrecyMobilestoragemedia.getSecrecymobilestoragemediaId(),
							null);
			this.putToRequest("secrecyMobilestoragemediaChangeList", secrecyMobilestoragemediaChangeList);
		}
		if( secrecyMobilestoragemediaChange!=null && secrecyMobilestoragemediaChange.getMobilestoragemediaChangeId()!=null
					&& !"".equals(secrecyMobilestoragemediaChange.getMobilestoragemediaChangeId()) ){
			List<SecrecyMobilestoragemediaChange> secrecyMobilestoragemediaChangeList
				= this.secrecyMobilestoragemediaChangeService.findDataList( null,
						secrecyMobilestoragemediaChange.getMobilestoragemediaChangeId());
			this.putToRequest("secrecyMobilestoragemediaChangeList", secrecyMobilestoragemediaChangeList);
		}
		return SUCCESS;
	}

	public String declassifiedDetail(){
		if( secrecyMobilestoragemedia!=null && secrecyMobilestoragemedia.getSecrecymobilestoragemediaId()!=null && !"".equals(secrecyMobilestoragemedia.getSecrecymobilestoragemediaId()) ){
			 List<SecrecyMobilestoragemediaClear> secrecyMobilestoragemediaClearList = this.secrecyMobilestoragemediaClearService.findDataList(secrecyMobilestoragemedia.getSecrecymobilestoragemediaId() );
			this.putToRequest("secrecyMobilestoragemediaClearList", secrecyMobilestoragemediaClearList);
		}
		return SUCCESS;
	}


	public String indexView(){
		String countType = getRequest().getParameter("countType");
		String returnStr = "";
		//本单位
		if( countType!=null && "organ".equals(countType) ){
			Organ currentOrgan = this.getCurrentUser().getOrgan();
			List<DictionaryOption> optionList = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "secrecy_level_thing");
			List<DictionaryOption> typeList = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "media_type");
			//密级  类型
			Map<Integer,Map<Integer, Long>> countMap = this.secrecyMobilestoragemediaService.countSecrecyMobilestoragemediaData(optionList, typeList, currentOrgan, countType );
			putToRequest("countMap", countMap);
			putToRequest("countType", countType);
			putToRequest("optionList", optionList);
			putToRequest("typeList", typeList);
			returnStr = "organView";
		}
		//保密局
		if( countType!=null && "layer".equals(countType) ){
			Organ currentOrgan = this.getCurrentUser().getOrgan();
			List<DictionaryOption> optionList = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "secrecy_level_thing");
			List<DictionaryOption> typeList = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "media_type");
			//密级  类型
			Map<Integer,Map<Integer, Long>> countMap = this.secrecyMobilestoragemediaService.countSecrecyMobilestoragemediaData(optionList, typeList, currentOrgan, countType );
			putToRequest("countMap", countMap);
			putToRequest("countType", countType);
			putToRequest("optionList", optionList);
			putToRequest("typeList", typeList);
			returnStr = "layerView";
		}
		putToRequest("countType", countType);
		return returnStr;
	}

	public String countData(){
		String countType = getRequest().getParameter("countType");
		//本单位
		if( countType!=null && "organ".equals(countType) ){
			Organ currentOrgan = this.getCurrentUser().getOrgan();
			PageSortModel<SecrecyMobilestoragemedia> psm = new PageSortModel<SecrecyMobilestoragemedia>(getRequest(), "secrecymobilestoragemediaListTable");
			List<SecrecyMobilestoragemedia> secrecyMobilestoragemediaList = this.secrecyMobilestoragemediaService.findDataList(psm, currentOrgan, countType, secrecyMobilestoragemedia);
			this.putToRequest("secrecyMobilestoragemediaList", secrecyMobilestoragemediaList);
		}
		//保密局
		if( countType!=null && "layer".equals(countType) ){
			Organ currentOrgan = this.getCurrentUser().getOrgan();
			PageSortModel<SecrecyMobilestoragemedia> psm = new PageSortModel<SecrecyMobilestoragemedia>(getRequest(), "secrecymobilestoragemediaListTable");
			List<SecrecyMobilestoragemedia> secrecyMobilestoragemediaList = this.secrecyMobilestoragemediaService.findDataList(psm, currentOrgan, countType, secrecyMobilestoragemedia);
			this.putToRequest("secrecyMobilestoragemediaList", secrecyMobilestoragemediaList);

		}
		putToRequest("countType", countType);
		return SUCCESS;
	}

	public String countSubData(){
		String countType = getRequest().getParameter("countType");
		//本单位
		if( countType!=null && "organ".equals(countType) ){
			Organ currentOrgan = this.getCurrentUser().getOrgan();
			PageSortModel<SecrecyMobilestoragemedia> psm = new PageSortModel<SecrecyMobilestoragemedia>(getRequest(), "secrecymobilestoragemediaListTable");
			List<SecrecyMobilestoragemedia> secrecyMobilestoragemediaList = this.secrecyMobilestoragemediaService.findDataList(psm, currentOrgan, countType, secrecyMobilestoragemedia);
			this.putToRequest("secrecyMobilestoragemediaList", secrecyMobilestoragemediaList);
		}
		//保密局
		if( countType!=null && "layer".equals(countType) ){
			Organ currentOrgan = this.getCurrentUser().getOrgan();
			PageSortModel<SecrecyMobilestoragemedia> psm = new PageSortModel<SecrecyMobilestoragemedia>(getRequest(), "secrecymobilestoragemediaListTable");
			List<SecrecyMobilestoragemedia> secrecyMobilestoragemediaList = this.secrecyMobilestoragemediaService.findDataList(psm, currentOrgan, countType, secrecyMobilestoragemedia);
			this.putToRequest("secrecyMobilestoragemediaList", secrecyMobilestoragemediaList);

		}
		putToRequest("countType", countType);
		return SUCCESS;
	}

	/**
	 *
	 * <p>
	 * 方法名称  query_list 查询方法列表页
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-22 下午4:00:16
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
	/*public String query_list(){
		//处理行政区划  从树节点获取
		String districtCode = this.getRequest().getParameter("districtCode");//行政区划编码
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = districtService.get(districtCode);
		}

		//查询
		PageSortModel<SecrecyMobilestoragemedia> psm = new PageSortModel<SecrecyMobilestoragemedia>(this.getRequest(), "secrecymobilestoragemediaListTable");
		List<SecrecyMobilestoragemedia> secrecyMobilestoragemediaList = this.secrecyMobilestoragemediaService.findPageAllList(psm, secrecyMobilestoragemedia, district);

		this.putToRequest("secrecyMobilestoragemediaList", secrecyMobilestoragemediaList);
		this.putToRequest("districtCode", district.getDistrictCode());
		return SUCCESS;
	}*/

	/**
	 *
	 * <p>
	 * 方法名称  query_main 查询方法行政区划树
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-22 下午4:00:33
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
	public String query_main(){
		return SUCCESS;
	}

	/***************************************综合统计*******************************************/
	/**
	 * 综合统计  通过行政区划    查询涉密人员个数 的明细列表
	 * 包括当前行政区划 和下级行政区划 的涉密人员的明细
	 * @return
	 */
	public String zhtj_query_Detail(){

		district = districtService.get(district.getDistrictCode());

		zhtj_query_method();

		return SUCCESS;
	}

	/**
	 * 综合统计  通过行政区划编码  统计该行政区划涉密人员的统计
	 * 一个单位一排数据，同时这个action提供了通过单位的名称模糊查询的功能。
	 *
	 * @return
	 */
	public String zhtj_queryByDistrict() {
		//这里只会得到  行政区划编码，没有单位的信息
		district = districtService.get(district.getDistrictCode());

		String organName = this.getRequest().getParameter("organ.organName");
		Organ organ = null;
		if(organName!=null && !"".equals(organName)) {
			organ = new Organ();
			organ.setOrganName(organName);
		}

		List<ZongHeTongJiStatDto> secrecyCountryItemStatDtoList = secrecyMobilestoragemediaService.count_SecrecyMobilestoragemedia_District(district, true,organ);
		putToRequest("secrecyCountryItemStatDtoList", secrecyCountryItemStatDtoList);
		putToRequest("district", district);
		putToRequest("topDistrict", this.getCurrentUser().getOrgan().getDistrict());
		return SUCCESS;
	}

	/**
	 * 通过密级，行政区划对象或者单位对象，查询涉密人员对应的列表
	 * 密级是必须字段，如果点到合计上面了，这里的单位是空的，所以需要行政区划对象
	 *                如果没有点到合计，那么会使用单位的对象去查询
	 * @return
	 */
	public String zhtj_DetailList() {
		List<SecrecyMobilestoragemedia> secrecyMobilestoragemediaList = new ArrayList<SecrecyMobilestoragemedia>();
		PageSortModel<SecrecyMobilestoragemedia> psm = new PageSortModel<SecrecyMobilestoragemedia>(this.getRequest(), "secrecymobilestoragemediaListTable");

		//设置密级
		String secrecy_level = getRequest().getParameter("secrecyLevel");//密级
		if(secrecyMobilestoragemedia==null) {
			secrecyMobilestoragemedia = new SecrecyMobilestoragemedia();
		}
		if(secrecy_level!=null && !"".equals(secrecy_level)) {
			secrecyMobilestoragemedia.setSecrecyLevel(Integer.parseInt(secrecy_level));
		}

		String organId =  getRequest().getParameter("organId"); //单位id
		if(organId!=null && !"".equals(organId)) {//如果有单位，就用单位对象
			Organ organ = new Organ();
			organ.setOrganId(organId);
			//secrecyPersonList =  secrecyPersonModuleService.queryCountryItemList(psm, organ, secrecyPerson);
			secrecyMobilestoragemediaList =  secrecyMobilestoragemediaService.findPageAllList(psm, secrecyMobilestoragemedia, organ, null, null, BmpAction.SECRECY_STATUS_NOW);

		}else {//如果没有单位对象   就需要使用 行政区划对象   也说明点到合计了
			district = districtService.get(district.getDistrictCode());
			//secrecyPersonList =  secrecyPersonModuleService.queryCountryItemList(psm, district, secrecyPerson);
			secrecyMobilestoragemediaList =  secrecyMobilestoragemediaService.findPageAllList(psm, secrecyMobilestoragemedia, null, district, null, BmpAction.SECRECY_STATUS_NOW);
		}

		putToRequest("secrecyMobilestoragemediaList", secrecyMobilestoragemediaList);
		putToRequest("secrecy_level", secrecy_level);
		return SUCCESS;
	}

	/************************************************************************************************/

	public String zhtj_query(){

		district = districtService.get(this.getCurrentUser().getOrgan().getDistrict().getDistrictCode());

		zhtj_query_method();

		return SUCCESS;
	}

	/**
	 * <p>
	 * 方法名称  zhtj_query_method
	 * </p>
	 * <p>
	 * 宋亚非 2013-9-6 下午3:56:47
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 */
	private void zhtj_query_method() {
		//处理行政区划
		List<District> districtList = new ArrayList<District>();
		List<District> childrenDistrictList =  new ArrayList<District>(district.getChildren());
		districtList.add(district);

		//查询  当前行政区划的 明细
		List<ZongHeTongJiStatDto> secrecyCountryItemStatDtoList = secrecyMobilestoragemediaService.count_SecrecyMobilestoragemedia_District(districtList, false);
		//查询  子行政区划的明细
		List<ZongHeTongJiStatDto> childrenStatDtoList = secrecyMobilestoragemediaService.count_SecrecyMobilestoragemedia_District(childrenDistrictList, true);

		putToRequest("secrecyCountryItemStatDtoList", secrecyCountryItemStatDtoList);
		putToRequest("childrenStatDtoList", childrenStatDtoList);
		putToRequest("districtCode", district.getDistrictCode());
		putToRequest("district", district);
		putToRequest("topDistrict", this.getCurrentUser().getOrgan().getDistrict());
	}


	/************************************综合统计导出************************************************************/
	/**
	 * 行政区划导出
	 * @return
	 */
	public String stat_exportDistrict() {
		district = districtService.get(district.getDistrictCode());

		//处理行政区划
		List<District> districtList = new ArrayList<District>();
		districtList.add(district);

		//查询  当前行政区划的 明细
		List<ZongHeTongJiStatDto> dataStatDtoList = secrecyMobilestoragemediaService.count_SecrecyMobilestoragemedia_District(districtList, false);

		//导出设置
  		Map<String, Object> params = new HashMap<String, Object>();
  		//被导出的数据
  		params.put("dataStatDtoList", dataStatDtoList);
		params.put("district", district);
		params.put("title", "涉密移动存储介质");
		this.putToRequest("district", district);
  		setResultData(params);
		return SUCCESS;
	}

	/**
	 * 行政区内导出
	 * @return
	 */
	public String stat_exportLayer(){
		district = districtService.get(district.getDistrictCode());

		//处理行政区划
		List<District> childrenDistrictList =  new ArrayList<District>(district.getChildren());

		//查询  子行政区划的明细
		List<ZongHeTongJiStatDto> childrenStatDtoList = secrecyMobilestoragemediaService.count_SecrecyMobilestoragemedia_District(childrenDistrictList, true);

		//导出设置
  		Map<String, Object> params = new HashMap<String, Object>();
  		//被导出的数据
  		params.put("childrenStatDtoList", childrenStatDtoList);
		params.put("district", district);
		params.put("title", "涉密移动存储介质");
		this.putToRequest("district", district);
  		setResultData(params);
		return SUCCESS;
	}

	/**
	 *
	 * <p>
	 * 保密工作信息总览获取数据
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2014-5-7 - 下午2:28:35
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @return
	 */
	public String organIndex(){
		// TODO 暂无考虑时间查询，时间设计到业务模块数据变动历史情况，需要先调整业务模块
		String organId = getRequest().getParameter("organId").toString();
		QueryDto queryDto = new QueryDto();
		queryDto.setYear(Integer.parseInt(getRequest().getParameter("queryDto.year").toString()));
		queryDto.setMonth(Integer.parseInt(getRequest().getParameter("queryDto.month").toString()));
		district = secrecyMobilestoragemediaService.get(organId, Organ.class).getDistrict();
		putToRequest("secrecyMobilestoragemediaList", secrecyMobilestoragemediaService.findPageAllList(null, secrecyMobilestoragemedia, secrecyMobilestoragemediaService.get(organId, Organ.class), district, "0", SECRECY_STATUS_NOW));
		return SUCCESS;
	}

	/**
	 *
	 * <p>
	 * 数据填写校验
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2014-5-26 - 上午9:53:11
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @return
	 */
	public String dataValidate(){
		Map<String, Object> resultData = new HashMap<String, Object>();
		// ID值为bm_business_module中的ID
		resultData.put("id", "11");
		List<SecrecyMobilestoragemedia> secrecyMobilestoragemediaList = secrecyMobilestoragemediaService.findPageAllList(null, null, getCurrentUser().getOrgan(), getCurrentUser().getOrgan().getDistrict(), "0", SECRECY_STATUS_NOW);
		String msg = dataValidateService.validateData("涉密移动存储介质", secrecyMobilestoragemediaList, "11");
		resultData.put("msg", msg);
		setResultData(resultData);
		return JSON;
	}
	/*********************************************************************************/

	/**********************************getter & setter****************************************/

	/**
	 * @return the needReload
	 */
	public Boolean getNeedReload() {
		return needReload;
	}

	/**
	 * @param needReload the needReload to set
	 */
	public void setNeedReload(Boolean needReload) {
		this.needReload = needReload;
	}

	/**
	 * @return the secrecyMobilestoragemedia
	 */
	public SecrecyMobilestoragemedia getSecrecyMobilestoragemedia() {
		return secrecyMobilestoragemedia;
	}

	/**
	 * @param secrecyMobilestoragemedia the secrecyMobilestoragemedia to set
	 */
	public void setSecrecyMobilestoragemedia(
			SecrecyMobilestoragemedia secrecyMobilestoragemedia) {
		this.secrecyMobilestoragemedia = secrecyMobilestoragemedia;
	}

	/**
	 * @return the secrecyMobilestoragemediaChange
	 */
	public SecrecyMobilestoragemediaChange getSecrecyMobilestoragemediaChange() {
		return secrecyMobilestoragemediaChange;
	}

	/**
	 * @param secrecyMobilestoragemediaChange the secrecyMobilestoragemediaChange to set
	 */
	public void setSecrecyMobilestoragemediaChange(
			SecrecyMobilestoragemediaChange secrecyMobilestoragemediaChange) {
		this.secrecyMobilestoragemediaChange = secrecyMobilestoragemediaChange;
	}

	/**
	 * @return the secrecyMobilestoragemediaClear
	 */
	public SecrecyMobilestoragemediaClear getSecrecyMobilestoragemediaClear() {
		return secrecyMobilestoragemediaClear;
	}

	/**
	 * @param secrecyMobilestoragemediaClear the secrecyMobilestoragemediaClear to set
	 */
	public void setSecrecyMobilestoragemediaClear(
			SecrecyMobilestoragemediaClear secrecyMobilestoragemediaClear) {
		this.secrecyMobilestoragemediaClear = secrecyMobilestoragemediaClear;
	}

	/**
	 * @return the secrecyMobilestoragemediaService
	 */
	public SecrecyMobilestoragemediaService getSecrecyMobilestoragemediaService() {
		return secrecyMobilestoragemediaService;
	}

	/**
	 * @param secrecyMobilestoragemediaService the secrecyMobilestoragemediaService to set
	 */
	public void setSecrecyMobilestoragemediaService(
			SecrecyMobilestoragemediaService secrecyMobilestoragemediaService) {
		this.secrecyMobilestoragemediaService = secrecyMobilestoragemediaService;
	}

	/**
	 * @return the secrecyMobilestoragemediaChangeService
	 */
	public SecrecyMobilestoragemediaChangeService getSecrecyMobilestoragemediaChangeService() {
		return secrecyMobilestoragemediaChangeService;
	}

	/**
	 * @param secrecyMobilestoragemediaChangeService the secrecyMobilestoragemediaChangeService to set
	 */
	public void setSecrecyMobilestoragemediaChangeService(
			SecrecyMobilestoragemediaChangeService secrecyMobilestoragemediaChangeService) {
		this.secrecyMobilestoragemediaChangeService = secrecyMobilestoragemediaChangeService;
	}

	/**
	 * @return the secrecyMobilestoragemediaClearService
	 */
	public SecrecyMobilestoragemediaClearService getSecrecyMobilestoragemediaClearService() {
		return secrecyMobilestoragemediaClearService;
	}

	/**
	 * @param secrecyMobilestoragemediaClearService the secrecyMobilestoragemediaClearService to set
	 */
	public void setSecrecyMobilestoragemediaClearService(
			SecrecyMobilestoragemediaClearService secrecyMobilestoragemediaClearService) {
		this.secrecyMobilestoragemediaClearService = secrecyMobilestoragemediaClearService;
	}

	/**
	 * @return the districtService
	 */
	public DistrictService getDistrictService() {
		return districtService;
	}

	/**
	 * @param districtService the districtService to set
	 */
	public void setDistrictService(DistrictService districtService) {
		this.districtService = districtService;
	}

	/**
	 * @return the district
	 */
	public District getDistrict() {
		return district;
	}

	/**
	 * @param district the district to set
	 */
	public void setDistrict(District district) {
		this.district = district;
	}

        /**
         * @return the dataValidateService
         */
        public DataValidateService getDataValidateService() {
                return dataValidateService;
        }

        /**
         * @param dataValidateService the dataValidateService to set
         */
        public void setDataValidateService(DataValidateService dataValidateService) {
                this.dataValidateService = dataValidateService;
        }

}
