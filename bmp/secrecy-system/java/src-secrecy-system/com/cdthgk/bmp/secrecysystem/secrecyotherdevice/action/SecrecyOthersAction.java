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
import com.cdthgk.bmp.secrecysystem.secrecyotherdevice.service.SecrecyOthersChangeService;
import com.cdthgk.bmp.secrecysystem.secrecyotherdevice.service.SecrecyOthersClearService;
import com.cdthgk.bmp.secrecysystem.secrecyotherdevice.service.SecrecyOthersService;
import com.cdthgk.bmp.secrecysystem.secrecyotherdevice.vo.SecrecyMobilestoragemedia;
import com.cdthgk.bmp.secrecysystem.secrecyotherdevice.vo.SecrecyOthers;
import com.cdthgk.bmp.secrecysystem.secrecyotherdevice.vo.SecrecyOthersChange;
import com.cdthgk.bmp.secrecysystem.secrecyotherdevice.vo.SecrecyOthersClear;
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
 * 类的说明放这里  2013-7-31 下午1:54:03
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 *
 * @author 宋亚非
 */
public class SecrecyOthersAction extends BmpAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private SecrecyOthersService secrecyOthersService;
	private SecrecyOthersChangeService secrecyOthersChangeService;
	private SecrecyOthersClearService secrecyOthersClearService;
	private DataValidateService dataValidateService;

	private SecrecyOthers secrecyOthers;
	private SecrecyOthersChange secrecyOthersChange;
	private SecrecyOthersClear secrecyOthersClear;

	private DistrictService districtService;
	private District district;

	private Boolean needReload = false;

	/**
	 *
	 * <p>
	 * 方法名：main 访问主页面
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-6 下午3:49:29
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
	 * 宋亚非 2013-8-6 下午3:49:09
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
			if( secrecyOthers==null ){
				secrecyOthers = new SecrecyOthers();
				secrecyOthers.setSecrecyLevel(Integer.parseInt(secrecyType));
				this.putToRequest("secrecyType", secrecyType);
			}
		}
		if( organId!=null ){
			organ = this.secrecyOthersService.get(organId, Organ.class);
			this.putToRequest("organId", organId);
			this.putToRequest("organ", organ);
                        this.putToRequest("zhtj", "1");
		}

		PageSortModel<SecrecyOthers> psm = new PageSortModel<SecrecyOthers>(getRequest(), "secrecyOthersListTable");
		List<SecrecyOthers> secrecyOthersList = this.secrecyOthersService.findPageAllList(psm, secrecyOthers, organ, district, checkLower, SECRECY_STATUS_NOW);
		this.putToRequest("secrecyOthersList", secrecyOthersList);
		return LIST;
	}

	/**
	 *
	 * <p>
	 * 方法名：add 增加
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-6 下午3:49:49
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
	 * 方法名：adding 增加使用的保存方法
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-6 下午3:50:03
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

		secrecyOthers.setCreatePerson(getCurrentUser());
		secrecyOthers.setCreateTime(new Date(System.currentTimeMillis()));
		secrecyOthers.setCreateOrgan(getCurrentUser().getOrgan());
		secrecyOthers.setSecrecyStatus(SECRECY_STATUS_NOW);

		Department department = null;
		// 设置部门ID
		if(secrecyOthers.getIsbelongKeydepartment() == 1) {
			if (StringUtils.isNotEmpty(secrecyOthers.getKeyPart().getPartId())) {
				department = secrecyOthersService.get(secrecyOthers.getKeyPart().getPartId(), Part.class).getDepartment();
			}
			if (StringUtils.isNotEmpty(secrecyOthers.getKeySection().getKeySectionId())) {
				department = secrecyOthersService.get(secrecyOthers.getKeySection().getKeySectionId(), KeySection.class).getDepartment();
			}
		} else {
			if( secrecyOthers.getDepartment().getDepartmentId()!=null && !"".equals(secrecyOthers.getDepartment().getDepartmentId()) ){
				department = secrecyOthersService.get(secrecyOthers.getDepartment().getDepartmentId(), Department.class);
			}else{
				department = secrecyOthersService.addDepartment(secrecyOthers.getDepartment().getDepartmentName(), getCurrentUser());
			}
		}

		//处理责任人
		UserInfo dutyPerson = null;
		if( secrecyOthers.getDutyPerson()!=null ){
			if(secrecyOthers.getDutyPerson().getUserInfoId()!=null && !"".equals(secrecyOthers.getDutyPerson().getUserInfoId())){
				dutyPerson = this.secrecyOthersService.get(secrecyOthers.getDutyPerson().getUserInfoId(), UserInfo.class);
			}else{
				//新增人员
				dutyPerson = this.secrecyOthersService.addUserinfo(secrecyOthers.getDutyPerson().getName(), getCurrentUser());
			}
		}
		secrecyOthers.setDutyPerson(dutyPerson);
		//Department department = secrecyOthersService.get(secrecyOthers.getDepartment().getDepartmentId(), Department.class);
		secrecyOthers.setDepartment(department);
		this.secrecyOthersService.save(secrecyOthers);
		this.setNeedReload(true);
		addActionMessage(getMessageConstant().getSaveSuccess());
		BusinessLog log = new BusinessLog();
		log.setBusinessName("其他涉密设备");
		log.setPrimaryKey(secrecyOthers.getSecrecyothersId());
		BusinessLogContext
				.getInstance()
				.getBusinessLogService()
				.saveAddBusinessLogByModule(getCurrentUser(), log,
						new SecrecyOthers());
		return redirectActionResult(LIST);
	}

	/**
	 *
	 * <p>
	 * 方法名：edit 编辑方法
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-6 下午3:50:22
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
		secrecyOthers = this.secrecyOthersService.get(secrecyOthers.getSecrecyothersId());
		return EDIT;
	}

	/**
	 *
	 * <p>
	 * 方法名：editing 编辑使用的保存方法
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-6 下午3:50:36
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
		SecrecyOthers beforeSo=new SecrecyOthers();
		SecrecyOthers secrecyOthersDb = secrecyOthersService.get(secrecyOthers.getSecrecyothersId());
		BeanUtils.copyProperties(beforeSo, secrecyOthersDb, CopyRuleEnum.ignoreCaseNull);
		BeanUtils.copyProperties(secrecyOthersDb, secrecyOthers, CopyRuleEnum.ignoreCaseEmpty);
		Department department = null;
		// 设置部门ID
		if(secrecyOthersDb.getIsbelongKeydepartment() == 1) {
			if (StringUtils.isNotEmpty(secrecyOthersDb.getKeyPart().getPartId())) {
				department = secrecyOthersService.get(secrecyOthersDb.getKeyPart().getPartId(), Part.class).getDepartment();
			}
			if (StringUtils.isNotEmpty(secrecyOthersDb.getKeySection().getKeySectionId())) {
				department = secrecyOthersService.get(secrecyOthersDb.getKeySection().getKeySectionId(), KeySection.class).getDepartment();
			}
		} else {
			if( secrecyOthers.getDepartment().getDepartmentId()!=null && !"".equals(secrecyOthers.getDepartment().getDepartmentId()) ){
				department = secrecyOthersService.get(secrecyOthers.getDepartment().getDepartmentId(), Department.class);
			}else{
				department = secrecyOthersService.addDepartment(secrecyOthers.getDepartment().getDepartmentName(), getCurrentUser());
			}
		}
		//Department department = secrecyOthersService.get(secrecyOthersDb.getDepartment().getDepartmentId(), Department.class);

		//处理责任人
		UserInfo dutyPerson = null;
		if( secrecyOthers.getDutyPerson()!=null ){
			if(secrecyOthers.getDutyPerson().getUserInfoId()!=null && !"".equals(secrecyOthers.getDutyPerson().getUserInfoId())){
				dutyPerson = this.secrecyOthersService.get(secrecyOthers.getDutyPerson().getUserInfoId(), UserInfo.class);
			}else{
				//新增人员
				dutyPerson = this.secrecyOthersService.addUserinfo(secrecyOthers.getDutyPerson().getName(), getCurrentUser());
			}
		}
		secrecyOthersDb.setDutyPerson(dutyPerson);

		secrecyOthersDb.setDepartment(department);
		// 设置通用字段
		secrecyOthersDb.setModifyTime(new Date());
		secrecyOthersDb.setModifyPerson(getCurrentUser());
		secrecyOthersDb.setModifyOrgan(getCurrentUser().getUserInfo().getOrgan());
		secrecyOthersService.update(secrecyOthersDb);
		addActionMessage(getMessageConstant().getUpdateSuccess());
		this.putToRequest("secrecyOthers", secrecyOthersDb);
		this.setNeedReload(true);
		 BusinessLog log = new BusinessLog();
	 		log.setBusinessName("其他涉密设备");
	 		log.setPrimaryKey(secrecyOthersDb.getSecrecyothersId());
	 		BusinessLogContext
	 				.getInstance()
	 				.getBusinessLogService()
	 				.saveEditBusinessLogByModule(getCurrentUser(), log,
	 						secrecyOthersDb, beforeSo);
		return redirectActionResult(LIST);
	}

	/**
	 *
	 * <p>
	 * 方法名：delete 删除方法
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-6 下午3:50:48
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
		String deleteIds = getRequest().getParameter("secrecyothersIds");
		String [] idsArray = deleteIds.split(",");
		String message = "";
		for (String id : idsArray) {
			SecrecyOthers secrecyOthersDb = secrecyOthersService.get(id);
			if(secrecyOthersDb != null){
				if(CollectionUtils.isEmpty(secrecyOthersDb.getSecrecyOthersChanges())
						&& CollectionUtils.isEmpty(secrecyOthersDb.getSecrecyOthersClears())){
					secrecyOthersService.delete(secrecyOthersDb);
					BusinessLog log = new BusinessLog();
         			log.setBusinessName("其他涉密设备");
         			log.setPrimaryKey(secrecyOthersDb.getSecrecyothersId());
         			BusinessLogContext
         					.getInstance()
         					.getBusinessLogService()
         					.saveDelBusinessLogByModule(getCurrentUser(), log,
         							secrecyOthersDb);
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
	 * 宋亚非 2013-8-6 下午3:50:59
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
		secrecyOthers = this.secrecyOthersService.get(secrecyOthers.getSecrecyothersId());
		return DETAIL;
	}

	/**
	 *
	 * <p>
	 * 方法名：secrecyLevelChangeHistory 涉密等级变更历史列表
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-6 下午3:51:18
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

		PageSortModel<SecrecyOthersChange> psm = new PageSortModel<SecrecyOthersChange>(getRequest(), "secrecyOthersChangeListTable");
		List<SecrecyOthersChange> secrecyOthersChangeList = this.secrecyOthersChangeService.findPageAllList(psm, secrecyOthersChange, organ, district, checkLower);
		this.putToRequest("secrecyOthersChangeList", secrecyOthersChangeList);
		return SUCCESS;
	}

	/**
	 *
	 * <p>
	 * 方法名：secrecyLevelChange 密级变更
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-6 下午3:51:41
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
		secrecyOthers = this.secrecyOthersService.get(secrecyOthers.getSecrecyothersId());
		return SUCCESS;
	}

	/**
	 *
	 * <p>
	 * 方法名：secrecyLevelChanging 密级变更使用的保存方法
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-6 下午3:51:58
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
		secrecyOthers = this.secrecyOthersService.get(secrecyOthers.getSecrecyothersId());
		if( secrecyOthers!=null ){
			secrecyOthersChange.setSecrecyOthers(secrecyOthers);
		}else{
			this.addActionMessage("您所访问的资源Id不存在，请确认后重试。");
			return this.redirectActionResult("list");
		}
		secrecyOthersChange.setCreatePerson(getCurrentUser());
		secrecyOthersChange.setCreateDate(new Date(System.currentTimeMillis()));
		this.secrecyOthersChangeService.save(secrecyOthersChange);
		secrecyOthers.setSecrecyLevel(secrecyOthersChange.getAfterLevel());
		this.secrecyOthersService.update(secrecyOthers);
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
		secrecyOthers = this.secrecyOthersService.get(secrecyOthers.getSecrecyothersId());
		return SUCCESS;
	}

	/**
	 *
	 * <p>
	 * 方法名：declassifieding 解除密级使用的保存方法
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-6 下午3:52:15
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
		secrecyOthers = this.secrecyOthersService.get(secrecyOthersClear.getSecrecyOthers().getSecrecyothersId());
		secrecyOthersClear.setCreatePerson(getCurrentUser());
		secrecyOthersClear.setCreateDate(new Date(System.currentTimeMillis()));
		this.secrecyOthersClearService.save(secrecyOthersClear);
		secrecyOthers.setSecrecyStatus(PERSON_CHANGE_HISTORY);
		this.secrecyOthersService.update(secrecyOthers);
		addActionMessage(getMessageConstant().getSaveSuccess());
		this.setNeedReload(true);
		return SUCCESS;
	}

	/**
	 *
	 * <p>
	 * 方法名：declassifiedHistory 解密历史信息列表
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-6 下午3:52:38
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

		PageSortModel<SecrecyOthersClear> psm = new PageSortModel<SecrecyOthersClear>(getRequest(), "secrecyOthersClearListTable");
		List<SecrecyOthersClear> secrecyOthersClearList = this.secrecyOthersClearService.findPageAllList(psm, secrecyOthersClear, organ, district, checkLower);
		this.putToRequest("secrecyOthersClearList", secrecyOthersClearList);
		return SUCCESS;
	}

	/**
	 *
	 * <p>
	 * 方法名：export 列表导出
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-6 下午3:52:55
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
		List<SecrecyOthers> secrecyOthersList = this.secrecyOthersService.findPageAllList(null, secrecyOthers, organ, district, checkLower, SECRECY_STATUS_NOW);

		Map<String, Object> params = new HashMap<String, Object>();
		//被导出的数据
		params.put("dataList", secrecyOthersList);
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
		secrecyOthersChange = this.secrecyOthersChangeService.get(secrecyOthersChange.getSecrecyothersChangeId() );
		return SUCCESS;
	}

	public String secrecyDeclassifiedDetail(){
		secrecyOthersClear = this.secrecyOthersClearService.get(secrecyOthersClear.getSecrecyothersClearId());
		return SUCCESS;
	}

	public String baseDetail(){
		secrecyOthers = this.secrecyOthersService.get(secrecyOthers.getSecrecyothersId());
		return SUCCESS;
	}

	public String levalChangeDetail(){
		if( secrecyOthers!=null && secrecyOthers.getSecrecyothersId() !=null && !"".equals(secrecyOthers.getSecrecyothersId()) ){
			List<SecrecyOthersChange> secrecyOthersChangeList
				= this.secrecyOthersChangeService.findDataList(secrecyOthers.getSecrecyothersId(),
							null);
			this.putToRequest("secrecyOthersChangeList", secrecyOthersChangeList);
		}
		if( secrecyOthersChange!=null && secrecyOthersChange.getSecrecyothersChangeId()!=null
					&& !"".equals(secrecyOthersChange.getSecrecyothersChangeId()) ){
			List<SecrecyOthersChange> secrecyOthersChangeList
				= this.secrecyOthersChangeService.findDataList( null,
						secrecyOthersChange.getSecrecyothersChangeId());
			this.putToRequest("secrecyOthersChangeList", secrecyOthersChangeList);
		}
		return SUCCESS;
	}

	public String declassifiedDetail(){
		if( secrecyOthers!=null && secrecyOthers.getSecrecyothersId() !=null && !"".equals(secrecyOthers.getSecrecyothersId()) ){
			 List<SecrecyOthersClear> secrecyOthersClearList = this.secrecyOthersClearService.findDataList(secrecyOthers.getSecrecyothersId() );
			this.putToRequest("secrecyOthersClearList", secrecyOthersClearList);
		}
		return SUCCESS;
	}

	public String indexView(){
		String countType = getRequest().getParameter("countType");
		String returnStr = "";
		List<DictionaryOption> optionList = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "secrecy_level_thing");
		List<DictionaryOption> typeList = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "other_media_type");
		Organ currentOrgan = this.getCurrentUser().getOrgan();
		//密级  类型
		Map<Integer,Map<Integer, Long>> countMap = this.secrecyOthersService.countOthersData(optionList, typeList, currentOrgan, countType );
		putToRequest("countMap", countMap);
		putToRequest("countType", countType);
		putToRequest("optionList", optionList);
		putToRequest("typeList", typeList);
		putToRequest("countType", countType);
		//本单位
		if( countType!=null && "organ".equals(countType) ){
			returnStr = "organView";
		}
		//保密局
		if( countType!=null && "layer".equals(countType) ){
			returnStr = "layerView";
		}
		return returnStr;
	}

	public String countData(){
		String countType = getRequest().getParameter("countType");
		//本单位
		if( countType!=null && "organ".equals(countType) ){
			Organ currentOrgan = this.getCurrentUser().getOrgan();
			PageSortModel<SecrecyOthers> psm = new PageSortModel<SecrecyOthers>(getRequest(), "secrecyOthersListTable");
			List<SecrecyOthers> secrecyOthersList = this.secrecyOthersService.findDataList(psm, currentOrgan, countType, secrecyOthers);
			this.putToRequest("secrecyOthersList", secrecyOthersList);
		}
		//保密局
		if( countType!=null && "layer".equals(countType) ){
			Organ currentOrgan = this.getCurrentUser().getOrgan();
			PageSortModel<SecrecyOthers> psm = new PageSortModel<SecrecyOthers>(getRequest(), "secrecyOthersListTable");
			List<SecrecyOthers> secrecyOthersList = this.secrecyOthersService.findDataList(psm, currentOrgan, countType, secrecyOthers);
			this.putToRequest("secrecyOthersList", secrecyOthersList);

		}
		putToRequest("countType", countType);
		return SUCCESS;
	}

	public String countSubData(){
		String countType = getRequest().getParameter("countType");
		//本单位
		if( countType!=null && "organ".equals(countType) ){
			Organ currentOrgan = this.getCurrentUser().getOrgan();
			PageSortModel<SecrecyOthers> psm = new PageSortModel<SecrecyOthers>(getRequest(), "secrecyOthersListTable");
			List<SecrecyOthers> secrecyOthersList = this.secrecyOthersService.findDataList(psm, currentOrgan, countType, secrecyOthers);
			this.putToRequest("secrecyOthersList", secrecyOthersList);
		}
		//保密局
		if( countType!=null && "layer".equals(countType) ){
			Organ currentOrgan = this.getCurrentUser().getOrgan();
			PageSortModel<SecrecyOthers> psm = new PageSortModel<SecrecyOthers>(getRequest(), "secrecyOthersListTable");
			List<SecrecyOthers> secrecyOthersList = this.secrecyOthersService.findDataList(psm, currentOrgan, countType, secrecyOthers);
			this.putToRequest("secrecyOthersList", secrecyOthersList);

		}
		putToRequest("countType", countType);
		return SUCCESS;
	}


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
		PageSortModel<SecrecyOthers> psm = new PageSortModel<SecrecyOthers>(this.getRequest(), "secrecyOthersListTable");
		List<SecrecyOthers> secrecyOthersList = this.secrecyOthersService.findPageAllList(psm, secrecyOthers, district);

		this.putToRequest("secrecyOthersList", secrecyOthersList);
		this.putToRequest("districtCode", district.getDistrictCode());
		return SUCCESS;
	}*/

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

		List<ZongHeTongJiStatDto> secrecyCountryItemStatDtoList = secrecyOthersService.count_SecrecyOthers_District(district, true,organ);
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

		List<SecrecyOthers> secrecyOthersList = new ArrayList<SecrecyOthers>();
		PageSortModel<SecrecyOthers> psm = new PageSortModel<SecrecyOthers>(this.getRequest(), "secrecyComputerList");

		//设置密级
		String secrecy_level = getRequest().getParameter("secrecyLevel");//密级
		if(secrecyOthers==null) {
			secrecyOthers = new SecrecyOthers();
		}
		if(secrecy_level!=null && !"".equals(secrecy_level)) {
			secrecyOthers.setSecrecyLevel(Integer.parseInt(secrecy_level));
		}

		String organId =  getRequest().getParameter("organId"); //单位id
		if(organId!=null && !"".equals(organId)) {//如果有单位，就用单位对象
			Organ organ = new Organ();
			organ.setOrganId(organId);
			//secrecyPersonList =  secrecyPersonModuleService.queryCountryItemList(psm, organ, secrecyPerson);
			secrecyOthersList =  secrecyOthersService.findPageAllList(psm, secrecyOthers, organ, null, null, BmpAction.SECRECY_STATUS_NOW);

		}else {//如果没有单位对象   就需要使用 行政区划对象   也说明点到合计了
			district = districtService.get(district.getDistrictCode());
			//secrecyPersonList =  secrecyPersonModuleService.queryCountryItemList(psm, district, secrecyPerson);
			secrecyOthersList =  secrecyOthersService.findPageAllList(psm, secrecyOthers, null, district, null, BmpAction.SECRECY_STATUS_NOW);
		}

		putToRequest("secrecyOthersList", secrecyOthersList);
		putToRequest("secrecy_level", secrecy_level);
		return SUCCESS;
	}

	/************************************************************************************************/

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
		List<ZongHeTongJiStatDto> dataStatDtoList = secrecyOthersService.count_SecrecyOthers_District(districtList, false);

		//导出设置
  		Map<String, Object> params = new HashMap<String, Object>();
  		//被导出的数据
  		params.put("dataStatDtoList", dataStatDtoList);
		params.put("district", district);
		params.put("title", "其他涉密设备");
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
		List<ZongHeTongJiStatDto> childrenStatDtoList = secrecyOthersService.count_SecrecyOthers_District(childrenDistrictList, true);

		//导出设置
  		Map<String, Object> params = new HashMap<String, Object>();
  		//被导出的数据
  		params.put("childrenStatDtoList", childrenStatDtoList);
		params.put("district", district);
		params.put("title", "其他涉密设备");
		this.putToRequest("district", district);
  		setResultData(params);
		return SUCCESS;
	}

	/*********************************************************************************/

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
	 * 宋亚非 2013-9-6 下午3:59:02
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
		List<ZongHeTongJiStatDto> secrecyCountryItemStatDtoList = secrecyOthersService.count_SecrecyOthers_District(districtList, false);
		//查询  子行政区划的明细
		List<ZongHeTongJiStatDto> childrenStatDtoList = secrecyOthersService.count_SecrecyOthers_District(childrenDistrictList, true);

		putToRequest("secrecyCountryItemStatDtoList", secrecyCountryItemStatDtoList);
		putToRequest("childrenStatDtoList", childrenStatDtoList);
		putToRequest("districtCode", district.getDistrictCode());
		putToRequest("district", district);
		putToRequest("topDistrict", this.getCurrentUser().getOrgan().getDistrict());
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
		district = secrecyOthersService.get(organId, Organ.class).getDistrict();
		putToRequest("secrecyOthersList", secrecyOthersService.findPageAllList(null, secrecyOthers, secrecyOthersService.get(organId, Organ.class), district, "", SECRECY_STATUS_NOW));
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
		resultData.put("id", "12");
		List<SecrecyOthers> secrecyOthersList = secrecyOthersService.findPageAllList(null, null, getCurrentUser().getOrgan(), getCurrentUser().getOrgan().getDistrict(), "", SECRECY_STATUS_NOW);
		String msg = dataValidateService.validateData("其他设备", secrecyOthersList, "12");
		resultData.put("msg", msg);
		setResultData(resultData);
		return JSON;
	}
	/****************************getters & setters***********************************/

	/**
	 * @return the secrecyOthersService
	 */
	public SecrecyOthersService getSecrecyOthersService() {
		return secrecyOthersService;
	}


	/**
	 * @param secrecyOthersService the secrecyOthersService to set
	 */
	public void setSecrecyOthersService(SecrecyOthersService secrecyOthersService) {
		this.secrecyOthersService = secrecyOthersService;
	}


	/**
	 * @return the secrecyOthersChangeService
	 */
	public SecrecyOthersChangeService getSecrecyOthersChangeService() {
		return secrecyOthersChangeService;
	}


	/**
	 * @param secrecyOthersChangeService the secrecyOthersChangeService to set
	 */
	public void setSecrecyOthersChangeService(
			SecrecyOthersChangeService secrecyOthersChangeService) {
		this.secrecyOthersChangeService = secrecyOthersChangeService;
	}


	/**
	 * @return the secrecyOthersClearService
	 */
	public SecrecyOthersClearService getSecrecyOthersClearService() {
		return secrecyOthersClearService;
	}


	/**
	 * @param secrecyOthersClearService the secrecyOthersClearService to set
	 */
	public void setSecrecyOthersClearService(
			SecrecyOthersClearService secrecyOthersClearService) {
		this.secrecyOthersClearService = secrecyOthersClearService;
	}


	/**
	 * @return the secrecyOthers
	 */
	public SecrecyOthers getSecrecyOthers() {
		return secrecyOthers;
	}


	/**
	 * @param secrecyOthers the secrecyOthers to set
	 */
	public void setSecrecyOthers(SecrecyOthers secrecyOthers) {
		this.secrecyOthers = secrecyOthers;
	}


	/**
	 * @return the secrecyOthersChange
	 */
	public SecrecyOthersChange getSecrecyOthersChange() {
		return secrecyOthersChange;
	}


	/**
	 * @param secrecyOthersChange the secrecyOthersChange to set
	 */
	public void setSecrecyOthersChange(SecrecyOthersChange secrecyOthersChange) {
		this.secrecyOthersChange = secrecyOthersChange;
	}


	/**
	 * @return the secrecyOthersClear
	 */
	public SecrecyOthersClear getSecrecyOthersClear() {
		return secrecyOthersClear;
	}


	/**
	 * @param secrecyOthersClear the secrecyOthersClear to set
	 */
	public void setSecrecyOthersClear(SecrecyOthersClear secrecyOthersClear) {
		this.secrecyOthersClear = secrecyOthersClear;
	}

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
