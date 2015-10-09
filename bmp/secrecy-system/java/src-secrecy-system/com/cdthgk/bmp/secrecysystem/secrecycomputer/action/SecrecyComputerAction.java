package com.cdthgk.bmp.secrecysystem.secrecycomputer.action;

import java.util.ArrayList;
import java.util.Collection;
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
import com.cdthgk.bmp.secrecysystem.secrecycomputer.service.SecrecyComputerChangeService;
import com.cdthgk.bmp.secrecysystem.secrecycomputer.service.SecrecyComputerClearService;
import com.cdthgk.bmp.secrecysystem.secrecycomputer.service.SecrecyComputerService;
import com.cdthgk.bmp.secrecysystem.secrecycomputer.vo.SecrecyComputer;
import com.cdthgk.bmp.secrecysystem.secrecycomputer.vo.SecrecyComputerChange;
import com.cdthgk.bmp.secrecysystem.secrecycomputer.vo.SecrecyComputerClear;
import com.cdthgk.bmp.secrecysystem.statistics.service.SecrecySystemStatisticsService;
import com.cdthgk.bmp.statinfo.dto.QueryDto;
import com.cdthgk.common.bean.BeanUtils;
import com.cdthgk.common.bean.rule.CopyRuleEnum;
import com.cdthgk.common.lang.CollectionUtils;
import com.cdthgk.common.lang.LangUtils;
import com.cdthgk.common.lang.StringUtils;
import com.cdthgk.component.ioc.ContextUtils;
import com.cdthgk.model.structure.autocomplete.Result;
import com.cdthgk.model.structure.autocomplete.Results;
import com.cdthgk.platform.dataValidate.service.DataValidateService;
import com.cdthgk.platform.dictionary.context.DictionaryContext;
import com.cdthgk.platform.dictionary.domain.DictionaryOption;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.district.service.DistrictService;
import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;
import com.cdthgk.platform.permission.user.domain.User;
import com.cdthgk.secrecyCarrier.vo.SecrecyTechnologyPrevention;

import ec.common.PageSortModel;

public class SecrecyComputerAction extends BmpAction {

	private static final long serialVersionUID = 1L;

	private String secrecyComputerIds;
	private SecrecyComputer secrecyComputer;
	private SecrecyComputerChange secrecyComputerChange;
	private SecrecyComputerClear secrecyComputerClear;
	private SecrecyComputerService secrecyComputerService;
	private SecrecyComputerChangeService secrecyComputerChangeService;
	private SecrecyComputerClearService secrecyComputerClearService;
	private DataValidateService dataValidateService;
//	private SecrecyNetworkService secrecyNetworkService;
	private SecrecySystemStatisticsService secrecySystemService;
	private DistrictService districtService;
	private Boolean needReload = false;
	private District district;
	private String fromQuery;
	private String checkLower;

	public String main() {
		// 从查询菜单中获取行政区代码，处理行政区划  从树节点获取
		String districtCode = this.getRequest().getParameter("districtCode");//行政区划编码
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = secrecyComputerService.get(districtCode, District.class);
		}
		fromQuery = this.getRequest().getParameter("fromQuery");// 表示从mainQuery过来的
		if (StringUtils.isEmpty(fromQuery)) {
			fromQuery = "0";
		}
		checkLower = this.getRequest().getParameter("checkLower");// 表示从mainQuery过来的
		if (StringUtils.isEmpty(checkLower)) {
			checkLower = "0";
		}
		return SUCCESS;
	}

	/**
	 *
	 * <p>
	 * 涉密计算机保密局查询
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-8-22 - 下午2:32:55
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @return
	 */
	public String mainQuery() {
		district = getCurrentUser().getOrgan().getDistrict();
		return SUCCESS;
	}

	public String list(){
		PageSortModel<SecrecyComputer> psm = new PageSortModel<SecrecyComputer>(getRequest(), "secrecyComputerList");
		// 统计中判断行政区代码
		if(district == null){
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = secrecyComputerService.get(district.getDistrictCode(), District.class);
		}
		boolean flag = true;
		// 判断查询列表过来的1查看保密局，0不是查询列表过来的查看本单位
		if("0".equals(fromQuery)){
			flag = false;
		}

		User currentUserTmp = this.getCurrentUser();
		//综合统计使用
		String secrecyType = this.getRequest().getParameter("secrecyType");
		String organId = this.getRequest().getParameter("organId");
		if( secrecyType!=null ){
			if( secrecyComputer==null ){
				secrecyComputer = new SecrecyComputer();
				secrecyComputer.setSecrecyLevel(Integer.parseInt(secrecyType));
				this.putToRequest("secrecyType", secrecyType);
			}
		}
		if( organId!=null ){
			Organ organ = this.secrecyComputerService.get(organId, Organ.class);
			currentUserTmp = new User();
			currentUserTmp.setOrgan(organ);
			this.putToRequest("organId", organId);
			this.putToRequest("organ", organ);
			this.putToRequest("zhtj", "1");
		}

		putToRequest("secrecyComputerList", secrecyComputerService.getListPage(psm,
				secrecyComputer, district, flag, currentUserTmp, checkLower));
		return SUCCESS;
	}

	public String add(){
		putToRequest("sectionPartList", DictionaryContext.getInstance()
				.getDictionaryService().getOptionList("bmp", "is_section_part"));
		return SUCCESS;
	}

	//验证硬盘序列号（全局唯一）
	public String chkDiskSeq(){
		Map<String, String> resultMap = new HashMap<String, String>();
		Boolean returnValue = false;
		String checkType = getRequest().getParameter("checkType");
		if( secrecyComputer!=null && secrecyComputer.getDiskSeq()!=null && !"".equals(secrecyComputer.getDiskSeq()) && checkType!=null && !"".equals(checkType) ){
			returnValue = this.secrecyComputerService.checkDiskSeq(secrecyComputer.getDiskSeq(), checkType, secrecyComputer.getSecrecycomputerId());
		}
		resultMap.put("chkDiskSeqFlag", returnValue.toString());
		this.setResultData(resultMap);
		return JSON;
	}

	//验证计算机编号（单位内唯一）
	public String chkComputerNo(){
		Map<String, String> resultMap = new HashMap<String, String>();
		Boolean returnValue = false;
		String checkType = getRequest().getParameter("checkType");
		if( secrecyComputer!=null && secrecyComputer.getComputerNo()!=null && !"".equals(secrecyComputer.getComputerNo()) && checkType!=null && !"".equals(checkType) ){
			returnValue = this.secrecyComputerService.checkComputerNo(getCurrentUser().getOrgan(), secrecyComputer.getComputerNo(), checkType, secrecyComputer.getSecrecycomputerId());
		}
		resultMap.put("chkComputerNoFlag", returnValue.toString());
		this.setResultData(resultMap);
		return JSON;
	}

	public String adding(){
		// 设置通用字段
		secrecyComputer.setCreateTime(new Date());
		secrecyComputer.setCreatePerson(getCurrentUser());
		Department department = null;

		if(secrecyComputer.getKeySection()!=null && "".equals(secrecyComputer.getKeySection().getKeySectionId()) ){
			secrecyComputer.setKeySection(null);
		}

		if( secrecyComputer.getKeyPart()!=null && "".equals(secrecyComputer.getKeyPart().getPartId()) ){
			secrecyComputer.setKeyPart(null);
		}

		// 设置部门ID
		if(secrecyComputer.getIsbelongKeydepartment() == 1) {
			if (secrecyComputer.getKeyPart()!=null && StringUtils.isNotEmpty(secrecyComputer.getKeyPart().getPartId())) {
				department = secrecyComputerService.get(secrecyComputer.getKeyPart().getPartId(), Part.class).getDepartment();
			}
			if (secrecyComputer.getKeySection()!=null && StringUtils.isNotEmpty(secrecyComputer.getKeySection().getKeySectionId())) {
				department = secrecyComputerService.get(secrecyComputer.getKeySection().getKeySectionId(), KeySection.class).getDepartment();
			}
		} else {
			//处理要害部门，当要害部门不存在时，新增
			if( secrecyComputer.getDepartment().getDepartmentId()!=null && !"".equals(secrecyComputer.getDepartment().getDepartmentId()) ){
				department = secrecyComputerService.get(secrecyComputer.getDepartment().getDepartmentId(), Department.class);
			}else{
				//新增要害部门
				department = this.secrecyComputerService.addDepartment(secrecyComputer.getDepartment().getDepartmentName(), getCurrentUser());
			}
		}
		//处理责任人
		UserInfo dutyPerson = null;
		if( secrecyComputer.getDutyPerson()!=null ){
			if(secrecyComputer.getDutyPerson().getUserInfoId()!=null && !"".equals(secrecyComputer.getDutyPerson().getUserInfoId())){
				dutyPerson = this.secrecyComputerService.get(secrecyComputer.getDutyPerson().getUserInfoId(), UserInfo.class);
			}else{
				//新增人员
				dutyPerson = this.secrecyComputerService.addUserinfo(secrecyComputer.getDutyPerson().getName(), getCurrentUser());
			}
		}

		secrecyComputer.setDutyPerson(dutyPerson);
		secrecyComputer.setDepartment(department);
		secrecyComputer.setSecrecyStatus(SECRECY_STATUS_NOW);
		//默认保存时为未接入网络
		secrecyComputer.setIsNetTerminal(0);
		secrecyComputerService.save(secrecyComputer);
		addActionMessage(getMessageConstant().getSaveSuccess());
		needReload = true;
		BusinessLog log = new BusinessLog();
		log.setBusinessName("涉密计算机");
		log.setPrimaryKey(secrecyComputer.getSecrecycomputerId());
		BusinessLogContext
				.getInstance()
				.getBusinessLogService()
				.saveAddBusinessLogByModule(getCurrentUser(), log,
						new SecrecyComputer());
		return redirectActionResult(ADD);
	}

	public String edit(){
		secrecyComputer = secrecyComputerService.get(secrecyComputer.getSecrecycomputerId());
		putToRequest("sectionPartList", DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "is_section_part"));
		return SUCCESS;
	}

	public String editing(){
		SecrecyComputer beforeSc=new SecrecyComputer();
		SecrecyComputer secrecyComputerDb = secrecyComputerService.get(secrecyComputer.getSecrecycomputerId());
		BeanUtils.copyProperties(beforeSc, secrecyComputerDb, CopyRuleEnum.ignoreCaseNull);
		BeanUtils.copyProperties(secrecyComputerDb, secrecyComputer, CopyRuleEnum.ignoreCaseEmpty);
		Department department = null;

		if(secrecyComputer.getKeySection()!=null && "".equals(secrecyComputer.getKeySection().getKeySectionId()) ){
			secrecyComputerDb.setKeySection(null);
		}

		if( secrecyComputer.getKeyPart()!=null && "".equals(secrecyComputer.getKeyPart().getPartId()) ){
			secrecyComputerDb.setKeyPart(null);
		}

		// 设置部门ID
		if(secrecyComputer.getIsbelongKeydepartment() == 1) {
			if (secrecyComputer.getKeyPart()!=null && StringUtils.isNotEmpty(secrecyComputer.getKeyPart().getPartId())) {
				department = secrecyComputerService.get(secrecyComputer.getKeyPart().getPartId(), Part.class).getDepartment();
			}
			if (secrecyComputer.getKeySection()!=null && StringUtils.isNotEmpty(secrecyComputer.getKeySection().getKeySectionId())) {
				department = secrecyComputerService.get(secrecyComputer.getKeySection().getKeySectionId(), KeySection.class).getDepartment();
			}
		} else {
			if( secrecyComputer.getDepartment().getDepartmentId()!=null && !"".equals(secrecyComputer.getDepartment().getDepartmentId()) ){
				department = secrecyComputerService.get(secrecyComputer.getDepartment().getDepartmentId(), Department.class);
			}else{
				//新增要害部门
				department = this.secrecyComputerService.addDepartment(secrecyComputer.getDepartment().getDepartmentName(), getCurrentUser());
			}
		}

		//处理责任人
		UserInfo dutyPerson = null;
		if( secrecyComputer.getDutyPerson()!=null ){
			if(secrecyComputer.getDutyPerson().getUserInfoId()!=null && !"".equals(secrecyComputer.getDutyPerson().getUserInfoId())){
				dutyPerson = this.secrecyComputerService.get(secrecyComputer.getDutyPerson().getUserInfoId(), UserInfo.class);
			}else{
				//新增人员
				dutyPerson = this.secrecyComputerService.addUserinfo(secrecyComputer.getDutyPerson().getName(), getCurrentUser());
			}
		}

		secrecyComputerDb.setDutyPerson(dutyPerson);
		secrecyComputerDb.setDepartment(department);
		// 设置通用字段
		secrecyComputerDb.setModifyTime(new Date());
		secrecyComputerDb.setModifyPerson(getCurrentUser());
		secrecyComputerDb.setModifyOrgan(getCurrentUser().getUserInfo().getOrgan());
		secrecyComputerService.update(secrecyComputerDb);
		putToRequest("secrecyComputer", secrecyComputerDb);
		needReload = true;
		addActionMessage(getMessageConstant().getUpdateSuccess());
		 BusinessLog log = new BusinessLog();
 		log.setBusinessName("涉密计算机");
 		log.setPrimaryKey(secrecyComputerDb.getSecrecycomputerId());
 		BusinessLogContext
 				.getInstance()
 				.getBusinessLogService()
 				.saveEditBusinessLogByModule(getCurrentUser(), log,
 						secrecyComputerDb, beforeSc);
		return SUCCESS;
	}

	public String detail(){
		secrecyComputer = secrecyComputerService.get(secrecyComputer.getSecrecycomputerId());
		return SUCCESS;
	}

	public String detailChange() {
		secrecyComputerChange = secrecyComputerChangeService.get(secrecyComputerChange.getComputerChangeId());
		return SUCCESS;
	}

	public String delete(){
		String [] idsArray = secrecyComputerIds.split(",");
		String message = "";
		for (String id : idsArray) {
			secrecyComputer = secrecyComputerService.get(id);
			if(secrecyComputer != null){
				if(CollectionUtils.isEmpty(secrecyComputer.getSecrecyComputerChanges())
						&& CollectionUtils.isEmpty(secrecyComputer.getSecrecyComputerClears())){
					secrecyComputerService.delete(secrecyComputer);
					 BusinessLog log = new BusinessLog();
         			log.setBusinessName("涉密计算机");
         			log.setPrimaryKey(secrecyComputer.getSecrecycomputerId());
         			BusinessLogContext
         					.getInstance()
         					.getBusinessLogService()
         					.saveDelBusinessLogByModule(getCurrentUser(), log,
         							secrecyComputer);
					message = getMessageConstant().getDeleteSuccess();
				} else {
					message = getMessageConstant().getDeleteFailure();
				}
				addActionMessage(message);
			}
		}
		return redirectActionResult(MAIN);
	}

	public String exportData() {
		// 统计中判断行政区代码
		if(district == null){
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = secrecyComputerService.get(district.getDistrictCode(), District.class);
		}
		boolean flag = true;
		// 判断查询列表过来的1查看保密局，0不是查询列表过来的查看本单位
		if("0".equals(fromQuery)){
			flag = false;
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("secrecyComputerList", secrecyComputerService.getListPage(
				new PageSortModel<SecrecyComputer>(getRequest(), "secrecyComputerList")
				, secrecyComputer, district, flag, getCurrentUser(), checkLower));
		setResultData(params);
		return SUCCESS;
	}

	/**
	 *
	 * <p>
	 * 得到密级变更成员列表
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-7-19 - 上午9:48:40
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @return
	 */
	public String secrecyChangeList() {
		// 查询判断行政区代码
		if(district == null){
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = secrecyComputerService.get(district.getDistrictCode(), District.class);
		}
		boolean flag = true;
		// 判断查询列表过来的1查看保密局，0不是查询列表过来的查看本单位
		if("0".equals(fromQuery)){
			flag = false;
		}
		PageSortModel<SecrecyComputerChange> secrecyComputerChangePsm
			= new PageSortModel<SecrecyComputerChange>(getRequest(), "secrecyComputerChangeList");
		putToRequest("secrecyComputerChangeList", secrecyComputerChangeService
				.getSecrecyComputerChangePageList(secrecyComputerChangePsm, secrecyComputerChange, getCurrentUser(), flag, district, checkLower));
		return SUCCESS;
	}
	/**
	 *
	 * <p>
	 * 得到解除成员列表
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-7-19 - 上午9:48:50
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @return
	 */
	public String decryptionList() {
		// 查询判断行政区代码
		if(district == null){
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = secrecyComputerService.get(district.getDistrictCode(), District.class);
		}
		boolean flag = true;
		// 判断查询列表过来的1查看保密局，0不是查询列表过来的查看本单位
		if("0".equals(fromQuery)){
			flag = false;
		}
		PageSortModel<SecrecyComputerClear> secrecyComputerClearPsm
		= new PageSortModel<SecrecyComputerClear>(getRequest(), "secrecyComputerClearList");
		putToRequest("secrecyComputerClearList", secrecyComputerClearService
				.getSecrecyComputerClearPageList(secrecyComputerClearPsm, secrecyComputerClear, getCurrentUser(), flag, district, checkLower));
		return SUCCESS;
	}

	/**
	 *
	 * <p>
	 * 密级变更
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-7-18 - 下午4:38:51
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @return
	 */
	public String secrecyChange() {
		secrecyComputer = secrecyComputerService.get(secrecyComputer.getSecrecycomputerId());
		return SUCCESS;
	}
	/**
	 *
	 * <p>
	 * 密级变更ing
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-7-18 - 下午4:40:23
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @return
	 */
	public String secrecyChanging() {
		secrecyComputerChangeService.saveSecrecyComputerChange(secrecyComputerChange, getCurrentUser());
		//更改原保密办成员涉密状态
		secrecyComputer = secrecyComputerService.get(secrecyComputerChange.getSecrecyComputer().getSecrecycomputerId());
		secrecyComputer.setSecrecyStatus(SECRECY_STATUS_CHANGE);
		secrecyComputer.setSecrecyLevel(secrecyComputerChange.getAfterLevel());
		secrecyComputerService.update(secrecyComputer);
		addActionMessage(getMessageConstant().getSaveSuccess());
		//是否关闭密级变更页面 0 关闭
		putToRequest("reflag", "0");
		return SUCCESS;
	}
	/**
	 *
	 * <p>
	 * 解除
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-7-18 - 下午4:38:51
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @return
	 */
	public String decryption() {
		secrecyComputer = secrecyComputerService.get(secrecyComputer.getSecrecycomputerId());
		return SUCCESS;
	}
	/**
	 *
	 * <p>
	 * 脱密ing
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-7-18 - 下午4:40:23
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @return
	 */
	public String decryptioning() {
		secrecyComputerClearService.saveSecrecyComputerClear(secrecyComputerClear, getCurrentUser());
		//更改原保密办成员涉密状态
		secrecyComputer = secrecyComputerService.get(secrecyComputerClear.getSecrecyComputer().getSecrecycomputerId());
		secrecyComputer.setSecrecyStatus(SECRECY_STATUS_DECRYPTION);
		secrecyComputerService.update(secrecyComputer);
		addActionMessage(getMessageConstant().getSaveSuccess());
		putToRequest("reflag", "0");
		return SUCCESS;
	}

	/**
	 *
	 * <p>
	 * 涉密计算机接入网络
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-8-6 - 下午4:07:59
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @return
	 */
	public String addNet() {
		secrecyComputer = secrecyComputerService.get(secrecyComputer.getSecrecycomputerId());
		return SUCCESS;
	}

	public String removeNet() {
		secrecyComputer = secrecyComputerService.get(secrecyComputer.getSecrecycomputerId());
		return SUCCESS;
	}

	/**
	 *
	 * <p>
	 * 方法名：removeNeting 移除网络
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-7 下午5:49:21
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
	public String removeNeting(){
		secrecyComputer = secrecyComputerService.get(secrecyComputer.getSecrecycomputerId());
		return SUCCESS;
	}


	public String ajax_isTerminalOfkeyPart(){
		SecrecyComputer tmpSecrecyComputer = this.secrecyComputerService.get(secrecyComputer.getSecrecycomputerId());

		Map<String, String> resultMap = new HashMap<String, String>();
		if( tmpSecrecyComputer!=null && tmpSecrecyComputer.getIsNetTerminal()!=null ){
			resultMap.put("isNetTerminal", tmpSecrecyComputer.getIsNetTerminal()+"");
			resultMap.put("userInfoId", tmpSecrecyComputer.getDutyPerson().getUserInfoId());
			resultMap.put("userInfoName", tmpSecrecyComputer.getDutyPerson().getName());
			resultMap.put("secrecyLevel", tmpSecrecyComputer.getSecrecyLevel()+"");
			resultMap.put("computerType", tmpSecrecyComputer.getComputerType()+"");
			resultMap.put("computerNo", tmpSecrecyComputer.getComputerNo());
			resultMap.put("diskSeq", tmpSecrecyComputer.getDiskSeq());
			resultMap.put("isbelongKeydepartment", tmpSecrecyComputer.getIsbelongKeydepartment()+"");
			resultMap.put("isFanghu", tmpSecrecyComputer.getIsFanghu()+"");
			resultMap.put("isWailian", tmpSecrecyComputer.getIsWailian()+"");
			resultMap.put("departmentId", tmpSecrecyComputer.getDepartment().getDepartmentId());
			resultMap.put("departmentName", tmpSecrecyComputer.getDepartment().getDepartmentName());

			if( tmpSecrecyComputer.getKeySection()!=null ){
				resultMap.put("keySectionId", tmpSecrecyComputer.getKeySection().getKeySectionId());
				resultMap.put("keySectionName", tmpSecrecyComputer.getKeySection().getDepartment().getDepartmentName());
			}

			if( tmpSecrecyComputer.getKeyPart()!=null ){
				resultMap.put("partId", tmpSecrecyComputer.getKeyPart().getPartId());
				resultMap.put("keyPartName", tmpSecrecyComputer.getKeyPart().getPartName());
			}
		}
		this.setResultData(resultMap);
		return JSON;
	}



	public String autocomplete() {
		Organ organ = getCurrentUser().getOrgan();
		//查询数据
		List<SecrecyComputer> secrecyComputerList = secrecyComputerService.getListPage(null, secrecyComputer,
				organ.getDistrict(), false, getCurrentUser(), "");
		//构建数据
		Results results = new Results();//数据集对象
		String dataSelector = getHeader("data-selector");
		//遍历数据
		for (SecrecyComputer secrecyComputer : secrecyComputerList) {
			Result result = new Result(secrecyComputer.getComputerNo(), secrecyComputer.getSecrecycomputerId());
			if (LangUtils.isNotEmpty(dataSelector)) {
				Collection<String> selector = ContextUtils.getDefaultContext().getBean(dataSelector);
				for (String sn : selector) {
					result.addData(sn, BeanUtils.getProperty(secrecyComputer, sn));
				}
			}
			results.addResult(result);
		}
		setResultData(results);
		return AUTOCOMPLETE;
	}

	/**
	 * 页面
	 * @return
	 */
	public String singelSelect() {
		Organ organ = getCurrentUser().getOrgan();
		PageSortModel<SecrecyComputer> psm = new PageSortModel<SecrecyComputer>(getRequest(), "secrecyComputerList");
		putToRequest("secrecyComputerList", secrecyComputerService.getListPage(psm, secrecyComputer,
				organ.getDistrict(), false, getCurrentUser(), ""));
		return SUCCESS;
	}

	/**
	 *
	 * <p>
	 * 首页面板展示
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-8-14 - 上午11:17:15
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @return
	 */
	public String indexView() {
		Organ organ = getCurrentUser().getOrgan();
		List<Map<String, String>> computerList = new ArrayList<Map<String,String>>();
		// 数据库中取出flag值false=本单位；true=保密局
		computerList = secrecySystemService.getComputerCount(organ.getDistrict(), organ, Boolean.FALSE);
		putToRequest("flag", "false");
		List<DictionaryOption> optionList = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "secrecy_level_thing");
		List<DictionaryOption> computerTypeList = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "computer_type");

		List<Map<String, Object>> results = new ArrayList<Map<String,Object>>();
		// 密级
		for (DictionaryOption secrecyOption : optionList) {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("secrecyLevel", secrecyOption.getOptionText());
			result.put("secrecyLevelValue", secrecyOption.getOptionValue());
			for (Map<String, String> coumputerMap : computerList) {
				result.put(coumputerMap.get("type"), coumputerMap.get("level" + secrecyOption.getOptionValue()));
			}
			results.add(result);
		}

		putToRequest("computerTypeList", computerTypeList);
		putToRequest("results", results);
		putToRequest("organ", organ);
		return SUCCESS;
	}

	/**
	 *
	 * <p>
	 * 保密局的
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-9-10 - 下午3:13:26
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @return
	 */
	public String indexViewDistrict() {
		Organ organ = getCurrentUser().getOrgan();
		List<Map<String, String>> computerList = new ArrayList<Map<String,String>>();
		// 数据库中取出flag值false=本单位；true=保密局
		computerList = secrecySystemService.getComputerCount(organ.getDistrict(), organ, Boolean.TRUE);
		putToRequest("flag", "true");

		List<DictionaryOption> optionList = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "secrecy_level_thing");
		List<DictionaryOption> computerTypeList = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "computer_type");

		List<Map<String, Object>> results = new ArrayList<Map<String,Object>>();
		// 密级
		for (DictionaryOption secrecyOption : optionList) {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("secrecyLevel", secrecyOption.getOptionText());
			result.put("secrecyLevelValue", secrecyOption.getOptionValue());
			for (Map<String, String> coumputerMap : computerList) {
				result.put(coumputerMap.get("type"), coumputerMap.get("level" + secrecyOption.getOptionValue()));
			}
			results.add(result);
		}

		putToRequest("computerTypeList", computerTypeList);
		putToRequest("results", results);
		putToRequest("organ", organ);
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

		List<ZongHeTongJiStatDto> secrecyCountryItemStatDtoList = secrecyComputerService.count_SecrecyComputer_District(district, true,organ);
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

		List<SecrecyComputer> secrecyComputerList = new ArrayList<SecrecyComputer>();
		PageSortModel<SecrecyComputer> psm = new PageSortModel<SecrecyComputer>(this.getRequest(), "secrecyComputerList");

		//设置密级
		String secrecy_level = getRequest().getParameter("secrecyLevel");//密级
		if(secrecyComputer==null) {
			secrecyComputer = new SecrecyComputer();
		}
		if(secrecy_level!=null && !"".equals(secrecy_level)) {
			secrecyComputer.setSecrecyLevel(Integer.parseInt(secrecy_level));
		}

		String organId =  getRequest().getParameter("organId"); //单位id
		if(organId!=null && !"".equals(organId)) {//如果有单位，就用单位对象
			Organ organ = new Organ();
			organ.setOrganId(organId);
			//secrecyPersonList =  secrecyPersonModuleService.queryCountryItemList(psm, organ, secrecyPerson);
			User currentUserTmp = new User();
			currentUserTmp.setOrgan(organ);
			secrecyComputerList =  secrecyComputerService.getListPage(psm, secrecyComputer, null, false, currentUserTmp, null);

		}else {//如果没有单位对象   就需要使用 行政区划对象   也说明点到合计了
			district = districtService.get(district.getDistrictCode());
			//secrecyPersonList =  secrecyPersonModuleService.queryCountryItemList(psm, district, secrecyPerson);
			secrecyComputerList =  secrecyComputerService.getListPage(psm, secrecyComputer, district, true, null, null);
		}

		putToRequest("secrecyComputerList", secrecyComputerList);
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
		List<ZongHeTongJiStatDto> dataStatDtoList = secrecyComputerService.count_SecrecyComputer_District(districtList, false);

		//导出设置
  		Map<String, Object> params = new HashMap<String, Object>();
  		//被导出的数据
  		params.put("dataStatDtoList", dataStatDtoList);
		params.put("district", district);
		params.put("title", "涉密计算机");
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
		List<ZongHeTongJiStatDto> childrenStatDtoList = secrecyComputerService.count_SecrecyComputer_District(childrenDistrictList, true);

		//导出设置
  		Map<String, Object> params = new HashMap<String, Object>();
  		//被导出的数据
  		params.put("childrenStatDtoList", childrenStatDtoList);
		params.put("district", district);
		params.put("title", "涉密计算机");
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
	 * 宋亚非 2013-9-6 下午3:51:30
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
		List<ZongHeTongJiStatDto> secrecyCountryItemStatDtoList = secrecyComputerService.count_SecrecyComputer_District(districtList, false);
		//查询  子行政区划的明细
		List<ZongHeTongJiStatDto> childrenStatDtoList = secrecyComputerService.count_SecrecyComputer_District(childrenDistrictList, true);

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
		User currentUserTmp = new User();
		currentUserTmp.setOrgan(secrecyComputerService.get(organId, Organ.class));
		district = secrecyComputerService.get(organId, Organ.class).getDistrict();
		List<SecrecyComputer> secrecyComputerList = secrecyComputerService.getListPage(null,secrecyComputer, district, false, currentUserTmp, "0");
		putToRequest("secrecyComputerList", secrecyComputerList);
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
		resultData.put("id", "8");
		List<SecrecyComputer> secrecyComputerList = secrecyComputerService.getListPage(null,secrecyComputer, getCurrentUser().getOrgan().getDistrict(), false, getCurrentUser(), "0");
		String msg = dataValidateService.validateData("涉密计算机", secrecyComputerList, "8");
		resultData.put("msg", msg);
		setResultData(resultData);
		return JSON;
	}

	/**
	 * @return 返回secrecyComputerIds
	 */
	public String getSecrecyComputerIds() {
		return secrecyComputerIds;
	}

	/**
	 * @param secrecyComputerIds 设置secrecyComputerIds
	 */
	public void setSecrecyComputerIds(String secrecyComputerIds) {
		this.secrecyComputerIds = secrecyComputerIds;
	}

	/**
	 * @return 返回secrecyComputer
	 */
	public SecrecyComputer getSecrecyComputer() {
		return secrecyComputer;
	}

	/**
	 * @param secrecyComputer 设置secrecyComputer
	 */
	public void setSecrecyComputer(SecrecyComputer secrecyComputer) {
		this.secrecyComputer = secrecyComputer;
	}

	/**
	 * @param secrecyComputerService 设置secrecyComputerService
	 */
	public void setSecrecyComputerService(
			SecrecyComputerService secrecyComputerService) {
		this.secrecyComputerService = secrecyComputerService;
	}

	/**
	 * @return 返回needReload
	 */
	public Boolean getNeedReload() {
		return needReload;
	}

	/**
	 * @param needReload 设置needReload
	 */
	public void setNeedReload(Boolean needReload) {
		this.needReload = needReload;
	}

	/**
	 * @return 返回secrecyComputerChange
	 */
	public SecrecyComputerChange getSecrecyComputerChange() {
		return secrecyComputerChange;
	}

	/**
	 * @param secrecyComputerChange 设置secrecyComputerChange
	 */
	public void setSecrecyComputerChange(SecrecyComputerChange secrecyComputerChange) {
		this.secrecyComputerChange = secrecyComputerChange;
	}

	/**
	 * @return 返回secrecyComputerClear
	 */
	public SecrecyComputerClear getSecrecyComputerClear() {
		return secrecyComputerClear;
	}

	/**
	 * @param secrecyComputerClear 设置secrecyComputerClear
	 */
	public void setSecrecyComputerClear(SecrecyComputerClear secrecyComputerClear) {
		this.secrecyComputerClear = secrecyComputerClear;
	}

	/**
	 * @param secrecyComputerChangeService 设置secrecyComputerChangeService
	 */
	public void setSecrecyComputerChangeService(
			SecrecyComputerChangeService secrecyComputerChangeService) {
		this.secrecyComputerChangeService = secrecyComputerChangeService;
	}

	/**
	 * @param secrecyComputerClearService 设置secrecyComputerClearService
	 */
	public void setSecrecyComputerClearService(
			SecrecyComputerClearService secrecyComputerClearService) {
		this.secrecyComputerClearService = secrecyComputerClearService;
	}

	/**
	 * @param secrecySystemService 设置secrecySystemService
	 */
	public void setSecrecySystemService(
			SecrecySystemStatisticsService secrecySystemService) {
		this.secrecySystemService = secrecySystemService;
	}
	/**
	 * @return 返回district
	 */
	public District getDistrict() {
		return district;
	}

	/**
	 * @param district 设置district
	 */
	public void setDistrict(District district) {
		this.district = district;
	}

	/**
	 * @return 返回fromQuery
	 */
	public String getFromQuery() {
		return fromQuery;
	}

	/**
	 * @param fromQuery 设置fromQuery
	 */
	public void setFromQuery(String fromQuery) {
		this.fromQuery = fromQuery;
	}

	/**
	 * @return 返回checkLower
	 */
	public String getCheckLower() {
		return checkLower;
	}

	/**
	 * @param checkLower 设置checkLower
	 */
	public void setCheckLower(String checkLower) {
		this.checkLower = checkLower;
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