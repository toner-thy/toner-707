package com.cdthgk.bmp.secrecysystem.secrecynetwork.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.action.BmpAction;
import com.cdthgk.bmp.log.context.BusinessLogContext;
import com.cdthgk.bmp.log.vo.BusinessLog;
import com.cdthgk.bmp.secrecycountryitem.qo.ZongHeTongJiStatDto;
import com.cdthgk.bmp.secrecysystem.secrecycomputer.vo.SecrecyComputer;
import com.cdthgk.bmp.secrecysystem.secrecynetwork.service.SecrecyNetworkChangeService;
import com.cdthgk.bmp.secrecysystem.secrecynetwork.service.SecrecyNetworkClearService;
import com.cdthgk.bmp.secrecysystem.secrecynetwork.service.SecrecyNetworkService;
import com.cdthgk.bmp.secrecysystem.secrecynetwork.service.SecrecyNetworkterminalService;
import com.cdthgk.bmp.secrecysystem.secrecynetwork.vo.SecrecyNetwork;
import com.cdthgk.bmp.secrecysystem.secrecynetwork.vo.SecrecyNetworkChange;
import com.cdthgk.bmp.secrecysystem.secrecynetwork.vo.SecrecyNetworkClear;
import com.cdthgk.bmp.secrecysystem.secrecynetwork.vo.SecrecyNetworkterminal;
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
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

public class SecrecyNetworkAction extends BmpAction {

	private static final long serialVersionUID = 1L;

	private String secrecyNetworkIds;
	private SecrecyNetwork secrecyNetwork;
	private SecrecyNetworkChange secrecyNetworkChange;
	private SecrecyNetworkClear secrecyNetworkClear;
	private SecrecyNetworkService secrecyNetworkService;
	private SecrecyNetworkChangeService secrecyNetworkChangeService;
	private SecrecyNetworkClearService secrecyNetworkClearService;
	private SecrecyNetworkterminalService secrecyNetworkterminalService;
	private SecrecyNetworkterminal secrecyNetworkterminal;
	private DistrictService districtService;
	private DataValidateService dataValidateService;
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
			district = secrecyNetworkService.get(districtCode, District.class);
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

	public String list(){
		PageSortModel<SecrecyNetwork> psm = new PageSortModel<SecrecyNetwork>(getRequest(), "secrecyNetworkList");
		if (district == null) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = secrecyNetworkService.get(district.getDistrictCode(), District.class);
		}
		boolean flag = true;
		// 判断查询列表过来的1查看保密局，0不是查询列表过来的查看本单位
		if("0".equals(fromQuery)){
			flag = false;
		}

		User currentUserTemp = this.getCurrentUser();
		//综合统计使用
		String secrecyType = this.getRequest().getParameter("secrecyType");
		String organId = this.getRequest().getParameter("organId");
		if( secrecyType!=null ){
			if( secrecyNetwork==null ){
				secrecyNetwork = new SecrecyNetwork();
				secrecyNetwork.setSecrecyLevel(Integer.parseInt(secrecyType));
				this.putToRequest("secrecyType", secrecyType);
			}
		}
		if( organId!=null ){
			Organ organ = this.secrecyNetworkService.get(organId, Organ.class);
			currentUserTemp = new User();
			currentUserTemp.setOrgan(organ);
			this.putToRequest("organId", organId);
			this.putToRequest("organ", organ);
                        this.putToRequest("zhtj", "1");
		}

		putToRequest("secrecyNetworkList", secrecyNetworkService.getListPage(psm, secrecyNetwork, district, flag, currentUserTemp, checkLower));
		return SUCCESS;
	}

	/**
	 *
	 * <p>
	 * 涉密网络查询
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-8-22 - 下午3:14:16
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

	public String add(){
		return SUCCESS;
	}

	public String adding(){
		// 设置通用字段
		secrecyNetwork.setCreateTime(new Date());
		secrecyNetwork.setCreatePerson(getCurrentUser());
		secrecyNetwork.setCreateOrgan(getCurrentUser().getOrgan());

		Department department = null;
                // 设置部门ID
                if( secrecyNetwork.getDepartment().getDepartmentId()!=null && !"".equals(secrecyNetwork.getDepartment().getDepartmentId()) ){
                        department = secrecyNetworkService.get(secrecyNetwork.getDepartment().getDepartmentId(), Department.class);
                }else{
                        department = secrecyNetworkService.addDepartment(secrecyNetwork.getDepartment().getDepartmentName(), getCurrentUser());
                }

                secrecyNetwork.setDepartment(department);
		secrecyNetwork.setSecrecyStatus(SECRECY_STATUS_NOW);
		secrecyNetworkService.save(secrecyNetwork);
		addActionMessage(getMessageConstant().getSaveSuccess());
		needReload = true;
		BusinessLog log = new BusinessLog();
		log.setBusinessName("涉密网络");
		log.setPrimaryKey(secrecyNetwork.getSecrecyNetworkId());
		BusinessLogContext
				.getInstance()
				.getBusinessLogService()
				.saveAddBusinessLogByModule(getCurrentUser(), log,
						new SecrecyNetwork());
		return redirectActionResult(ADD);
	}

	public String edit(){
		secrecyNetwork = secrecyNetworkService.get(secrecyNetwork.getSecrecyNetworkId());
		return SUCCESS;
	}

	public String editing(){
		SecrecyNetwork beforeSn=new SecrecyNetwork();
		SecrecyNetwork secrecyNetworkDb = secrecyNetworkService.get(secrecyNetwork.getSecrecyNetworkId());
		BeanUtils.copyProperties(beforeSn, secrecyNetworkDb, CopyRuleEnum.ignoreCaseNull);
		BeanUtils.copyProperties(secrecyNetworkDb, secrecyNetwork, CopyRuleEnum.ignoreCaseEmpty);
		if(secrecyNetworkDb.getIsReview() == 0){
			secrecyNetworkDb.setReviewOrgan(null);
			secrecyNetworkDb.setReviewTime(null);
		}
		if(secrecyNetworkDb.getIsApproval() == 0){
			secrecyNetworkDb.setApprovalNo(null);
			secrecyNetworkDb.setApprovalTime(null);
		}
		Department department = null;
                // 设置部门ID
                if( secrecyNetwork.getDepartment().getDepartmentId()!=null && !"".equals(secrecyNetwork.getDepartment().getDepartmentId()) ){
                        department = secrecyNetworkService.get(secrecyNetwork.getDepartment().getDepartmentId(), Department.class);
                }else{
                        department = secrecyNetworkService.addDepartment(secrecyNetwork.getDepartment().getDepartmentName(), getCurrentUser());
                }
                secrecyNetworkDb.setDepartment(department);
		// 设置通用字段
		secrecyNetworkDb.setModifyTime(new Date());
		secrecyNetworkDb.setModifyPerson(getCurrentUser());
		secrecyNetworkDb.setModifyOrgan(getCurrentUser().getUserInfo().getOrgan());
		secrecyNetworkService.update(secrecyNetworkDb);
		putToRequest("secrecyNetwork", secrecyNetworkDb);
		needReload = true;
		addActionMessage(getMessageConstant().getUpdateSuccess());
		 BusinessLog log = new BusinessLog();
	 		log.setBusinessName("涉密网络");
	 		log.setPrimaryKey(secrecyNetworkDb.getSecrecyNetworkId());
	 		BusinessLogContext
	 				.getInstance()
	 				.getBusinessLogService()
	 				.saveEditBusinessLogByModule(getCurrentUser(), log,
	 						secrecyNetworkDb, beforeSn);
		return SUCCESS;
	}

	public String detail(){
		secrecyNetwork = secrecyNetworkService.get(secrecyNetwork.getSecrecyNetworkId());
		return SUCCESS;
	}

	public String detailChange(){
		secrecyNetworkChange = secrecyNetworkChangeService.get(secrecyNetworkChange.getNetworkChangeId());
		return SUCCESS;
	}

	public String delete(){
		String [] idsArray = secrecyNetworkIds.split(",");
		String message = "";
		for (String id : idsArray) {
			secrecyNetwork = secrecyNetworkService.get(id);
			if(secrecyNetwork != null){
				if(CollectionUtils.isEmpty(secrecyNetwork.getSecrecyNetworkChanges())
						&& CollectionUtils.isEmpty(secrecyNetwork.getSecrecyNetworkClears())
						&& CollectionUtils.isEmpty(secrecyNetwork.getSecrecyNetworkterminals() )){
					secrecyNetworkService.delete(secrecyNetwork);
					BusinessLog log = new BusinessLog();
         			log.setBusinessName("涉密计算机");
         			log.setPrimaryKey(secrecyNetwork.getSecrecyNetworkId());
         			BusinessLogContext
         					.getInstance()
         					.getBusinessLogService()
         					.saveDelBusinessLogByModule(getCurrentUser(), log,
         							secrecyNetwork);
					message = getMessageConstant().getDeleteSuccess();
				} else {
					//message = getMessageConstant().getDeleteFailure();
					message = "删除失败：该数据有密级变动等历史信息，不能删除";
				}
				addActionMessage(message);
			}
		}
		return redirectActionResult(MAIN);
	}

	public String exportData() {
		String districtCode = this.getRequest().getParameter("districtCode");//行政区划编码
		District district;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = secrecyNetworkService.get(districtCode, District.class);
		}
		boolean flag = true;
		// 判断查询列表过来的1查看保密局，0不是查询列表过来的查看本单位
		if("0".equals(fromQuery)){
			flag = false;
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("secrecyNetworkList", secrecyNetworkService.getListPage(
				new PageSortModel<SecrecyNetwork>(getRequest(), "secrecyNetworkList")
				, secrecyNetwork, district, flag, getCurrentUser(), checkLower));
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		params.put("dateFormat", dateFormat);
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
			district = secrecyNetworkChangeService.get(district.getDistrictCode(), District.class);
		}
		boolean flag = true;
		// 判断查询列表过来的1查看保密局，0不是查询列表过来的查看本单位
		if("0".equals(fromQuery)){
			flag = false;
		}
		PageSortModel<SecrecyNetworkChange> secrecyNetworkChangePsm
			= new PageSortModel<SecrecyNetworkChange>(getRequest(), "secrecyNetworkChangeList");
		putToRequest("secrecyNetworkChangeList", secrecyNetworkChangeService
				.getSecrecyNetworkChangePageList(secrecyNetworkChangePsm, secrecyNetworkChange, getCurrentUser(), flag, district, checkLower));
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
			district = secrecyNetworkClearService.get(district.getDistrictCode(), District.class);
		}
		boolean flag = true;
		// 判断查询列表过来的1查看保密局，0不是查询列表过来的查看本单位
		if("0".equals(fromQuery)){
			flag = false;
		}
		PageSortModel<SecrecyNetworkClear> secrecyNetworkClearPsm
			= new PageSortModel<SecrecyNetworkClear>(getRequest(), "secrecyNetworkClearList");
		putToRequest("secrecyNetworkClearList", secrecyNetworkClearService
				.getSecrecyNetworkClearPageList(secrecyNetworkClearPsm, secrecyNetworkClear, getCurrentUser(), flag, district, checkLower));
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
		secrecyNetwork = secrecyNetworkService.get(secrecyNetwork.getSecrecyNetworkId());
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
		secrecyNetworkChangeService.saveSecrecyNetworkChange(secrecyNetworkChange, getCurrentUser());
		//更改原保密办成员涉密状态
		secrecyNetwork = secrecyNetworkService.get(secrecyNetworkChange.getSecrecyNetwork().getSecrecyNetworkId());
		secrecyNetwork.setSecrecyStatus(SECRECY_STATUS_CHANGE);
		secrecyNetwork.setSecrecyLevel(secrecyNetworkChange.getAfterLevel());
		secrecyNetworkService.update(secrecyNetwork);
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
		secrecyNetwork = secrecyNetworkService.get(secrecyNetwork.getSecrecyNetworkId());
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
		secrecyNetworkClearService.saveSecrecyNetworkClear(secrecyNetworkClear, getCurrentUser());
		//更改原保密办成员涉密状态
		secrecyNetwork = secrecyNetworkService.get(secrecyNetworkClear.getSecrecyNetwork().getSecrecyNetworkId());
		secrecyNetwork.setSecrecyStatus(SECRECY_STATUS_DECRYPTION);
		secrecyNetworkService.update(secrecyNetwork);
		addActionMessage(getMessageConstant().getSaveSuccess());
		putToRequest("reflag", "0");
		return SUCCESS;
	}

	public String autocomplete() {
		//查询数据
		List<SecrecyNetwork> secrecyNetworkList = secrecyNetworkService.getListPage(null, secrecyNetwork, getCurrentUser().getOrgan().getDistrict(), false, getCurrentUser(), "");
		//构建数据
		Results results = new Results();//数据集对象
		String dataSelector = getHeader("data-selector");
		//遍历数据
		for (SecrecyNetwork secrecyNetwork : secrecyNetworkList) {
			Result result = new Result(secrecyNetwork.getName(), secrecyNetwork.getSecrecyNetworkId());
			if (LangUtils.isNotEmpty(dataSelector)) {
				Collection<String> selector = ContextUtils.getDefaultContext().getBean(dataSelector);
				for (String sn : selector) {
					result.addData(sn, BeanUtils.getProperty(secrecyNetwork, sn));
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
		PageSortModel<SecrecyNetwork> psm = new PageSortModel<SecrecyNetwork>(getRequest(), "secrecyNetworkList");
		putToRequest("secrecyNetworkList", secrecyNetworkService.getListPage(psm, secrecyNetwork, getCurrentUser().getOrgan().getDistrict(), false, getCurrentUser(), ""));
		return SUCCESS;
	}

	/**
	 *
	 * <p>
	 * 方法名：addComputer 接入计算机
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-8 下午1:52:08
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
	public String addComputer(){
		putToRequest("sectionPartList", DictionaryContext.getInstance()
				.getDictionaryService().getOptionList("bmp", "is_section_part"));
		secrecyNetwork = this.secrecyNetworkService.get(secrecyNetwork.getSecrecyNetworkId());
		return SUCCESS;
	}

	/**
	 *
	 * <p>
	 * 方法名：removeComputer 查询 某网络的计算机列表
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-8 下午1:52:12
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
	public String removeComputer(){
		secrecyNetwork = this.secrecyNetworkService.get(secrecyNetwork.getSecrecyNetworkId());
		PageSortModel<SecrecyNetworkterminal> psm = new PageSortModel<SecrecyNetworkterminal>(getRequest(), "secrecyNetworkterminalListTab");
		List<SecrecyNetworkterminal> secrecyNetworkterminalList = this.secrecyNetworkterminalService.findTerminalListByNetwork(psm, secrecyNetwork, secrecyNetworkterminal, SECRECY_STATUS_NOW);
		this.putToRequest("secrecyNetworkterminalList", secrecyNetworkterminalList);
		return SUCCESS;
	}

	/**
	 *
	 * <p>
	 * 方法名称 移除计算机
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-21 上午11:08:19
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
	public String rmcomputer(){
		secrecyNetworkterminal = this.secrecyNetworkterminalService.get(secrecyNetworkterminal.getSecrecynetworkterminalId());
		return SUCCESS;
	}


	public String indexView(){
		String countType = getRequest().getParameter("countType");
		String returnStr = "";
		List<DictionaryOption> optionList = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "secrecy_level_thing");
		List<DictionaryOption> typeList = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "network_type");
		Organ currentOrgan = this.getCurrentUser().getOrgan();
		//密级  类型
		Map<Integer,Map<Integer, Long>> countMap = this.secrecyNetworkService.countData(optionList, typeList, currentOrgan, countType );
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
			PageSortModel<SecrecyNetwork> psm = new PageSortModel<SecrecyNetwork>(getRequest(), "secrecyNetworkList");
			List<SecrecyNetwork> secrecyNetworkList = this.secrecyNetworkService.findDataList(psm, currentOrgan, countType, secrecyNetwork);
			this.putToRequest("secrecyNetworkList", secrecyNetworkList);
		}
		//保密局
		if( countType!=null && "layer".equals(countType) ){
			Organ currentOrgan = this.getCurrentUser().getOrgan();
			PageSortModel<SecrecyNetwork> psm = new PageSortModel<SecrecyNetwork>(getRequest(), "secrecyNetworkList");
			List<SecrecyNetwork> secrecyNetworkList = this.secrecyNetworkService.findDataList(psm, currentOrgan, countType, secrecyNetwork);
			this.putToRequest("secrecyNetworkList", secrecyNetworkList);

		}
		putToRequest("countType", countType);
		return SUCCESS;
	}

	public String countSubData(){
		String countType = getRequest().getParameter("countType");
		//本单位
		if( countType!=null && "organ".equals(countType) ){
			Organ currentOrgan = this.getCurrentUser().getOrgan();
			PageSortModel<SecrecyNetwork> psm = new PageSortModel<SecrecyNetwork>(getRequest(), "secrecyNetworkList");
			List<SecrecyNetwork> secrecyNetworkList = this.secrecyNetworkService.findDataList(psm, currentOrgan, countType, secrecyNetwork);
			this.putToRequest("secrecyNetworkList", secrecyNetworkList);
		}
		//保密局
		if( countType!=null && "layer".equals(countType) ){
			Organ currentOrgan = this.getCurrentUser().getOrgan();
			PageSortModel<SecrecyNetwork> psm = new PageSortModel<SecrecyNetwork>(getRequest(), "secrecyNetworkList");
			List<SecrecyNetwork> secrecyNetworkList = this.secrecyNetworkService.findDataList(psm, currentOrgan, countType, secrecyNetwork);
			this.putToRequest("secrecyNetworkList", secrecyNetworkList);

		}
		putToRequest("countType", countType);
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

		List<ZongHeTongJiStatDto> secrecyCountryItemStatDtoList = secrecyNetworkService.count_SecrecyNetwork_District(district, true,organ);
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

		List<SecrecyNetwork> secrecyNetworkList = new ArrayList<SecrecyNetwork>();
		PageSortModel<SecrecyNetwork> psm = new PageSortModel<SecrecyNetwork>(this.getRequest(), "secrecyNetworkList");

		//设置密级
		String secrecy_level = getRequest().getParameter("secrecyLevel");//密级
		if(secrecyNetwork==null) {
			secrecyNetwork = new SecrecyNetwork();
		}
		if(secrecy_level!=null && !"".equals(secrecy_level)) {
			secrecyNetwork.setSecrecyLevel(Integer.parseInt(secrecy_level));
		}

		String organId =  getRequest().getParameter("organId"); //单位id
		if(organId!=null && !"".equals(organId)) {//如果有单位，就用单位对象
			Organ organ = new Organ();
			organ.setOrganId(organId);
			//secrecyPersonList =  secrecyPersonModuleService.queryCountryItemList(psm, organ, secrecyPerson);
			User currentUserTmp = new User();
			currentUserTmp.setOrgan(organ);
			secrecyNetworkList =  secrecyNetworkService.getListPage(psm, secrecyNetwork, null, false, currentUserTmp, null);

		}else {//如果没有单位对象   就需要使用 行政区划对象   也说明点到合计了
			district = districtService.get(district.getDistrictCode());
			//secrecyPersonList =  secrecyPersonModuleService.queryCountryItemList(psm, district, secrecyPerson);
			secrecyNetworkList =  secrecyNetworkService.getListPage(psm, secrecyNetwork, district, true, null, null);
		}

		putToRequest("secrecyNetworkList", secrecyNetworkList);
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
		List<ZongHeTongJiStatDto> dataStatDtoList = secrecyNetworkService.count_SecrecyNetwork_District(districtList, false);

		//导出设置
  		Map<String, Object> params = new HashMap<String, Object>();
  		//被导出的数据
  		params.put("dataStatDtoList", dataStatDtoList);
		params.put("district", district);
		params.put("title", "涉密网络");
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
		List<ZongHeTongJiStatDto> childrenStatDtoList = secrecyNetworkService.count_SecrecyNetwork_District(childrenDistrictList, true);

		//导出设置
  		Map<String, Object> params = new HashMap<String, Object>();
  		//被导出的数据
  		params.put("childrenStatDtoList", childrenStatDtoList);
		params.put("district", district);
		params.put("title", "涉密网络");
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
	 * 宋亚非 2013-9-6 下午3:53:38
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
		List<ZongHeTongJiStatDto> secrecyCountryItemStatDtoList = secrecyNetworkService.count_SecrecyNetwork_District(districtList, false);
		//查询  子行政区划的明细
		List<ZongHeTongJiStatDto> childrenStatDtoList = secrecyNetworkService.count_SecrecyNetwork_District(childrenDistrictList, true);

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
		User currentUserTemp = new User();
		currentUserTemp.setOrgan(secrecyNetworkService.get(organId, Organ.class));
		district = secrecyNetworkService.get(organId, Organ.class).getDistrict();
		putToRequest("secrecyNetworkList", secrecyNetworkService.getListPage(null, secrecyNetwork, district, false, currentUserTemp, "0"));
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
		resultData.put("id", "9");
		List<SecrecyNetwork> secrecyNetworkList = secrecyNetworkService.getListPage(null, secrecyNetwork, getCurrentUser().getOrgan().getDistrict(), false, getCurrentUser(), "0");
		String msg = dataValidateService.validateData("涉密网络", secrecyNetworkList, "9");
		resultData.put("msg", msg);
		setResultData(resultData);
		return JSON;
	}

	/******************************************* setters && getters **************************************************/
	/**
	 * @return 返回secrecyNetworkIds
	 */
	public String getSecrecyNetworkIds() {
		return secrecyNetworkIds;
	}

	/**
	 * @param secrecyNetworkIds 设置secrecyNetworkIds
	 */
	public void setSecrecyNetworkIds(String secrecyNetworkIds) {
		this.secrecyNetworkIds = secrecyNetworkIds;
	}

	/**
	 * @return 返回secrecyNetwork
	 */
	public SecrecyNetwork getSecrecyNetwork() {
		return secrecyNetwork;
	}

	/**
	 * @param secrecyNetwork 设置secrecyNetwork
	 */
	public void setSecrecyNetwork(SecrecyNetwork secrecyNetwork) {
		this.secrecyNetwork = secrecyNetwork;
	}

	/**
	 * @return 返回secrecyNetworkChange
	 */
	public SecrecyNetworkChange getSecrecyNetworkChange() {
		return secrecyNetworkChange;
	}

	/**
	 * @param secrecyNetworkChange 设置secrecyNetworkChange
	 */
	public void setSecrecyNetworkChange(SecrecyNetworkChange secrecyNetworkChange) {
		this.secrecyNetworkChange = secrecyNetworkChange;
	}

	/**
	 * @return 返回secrecyNetworkClear
	 */
	public SecrecyNetworkClear getSecrecyNetworkClear() {
		return secrecyNetworkClear;
	}

	/**
	 * @param secrecyNetworkClear 设置secrecyNetworkClear
	 */
	public void setSecrecyNetworkClear(SecrecyNetworkClear secrecyNetworkClear) {
		this.secrecyNetworkClear = secrecyNetworkClear;
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
	 * @param secrecyNetworkService 设置secrecyNetworkService
	 */
	public void setSecrecyNetworkService(SecrecyNetworkService secrecyNetworkService) {
		this.secrecyNetworkService = secrecyNetworkService;
	}

	/**
	 * @param secrecyNetworkChangeService 设置secrecyNetworkChangeService
	 */
	public void setSecrecyNetworkChangeService(
			SecrecyNetworkChangeService secrecyNetworkChangeService) {
		this.secrecyNetworkChangeService = secrecyNetworkChangeService;
	}

	/**
	 * @param secrecyNetworkClearService 设置secrecyNetworkClearService
	 */
	public void setSecrecyNetworkClearService(
			SecrecyNetworkClearService secrecyNetworkClearService) {
		this.secrecyNetworkClearService = secrecyNetworkClearService;
	}

	/**
	 * @return the secrecyNetworkterminalService
	 */
	public SecrecyNetworkterminalService getSecrecyNetworkterminalService() {
		return secrecyNetworkterminalService;
	}

	/**
	 * @param secrecyNetworkterminalService the secrecyNetworkterminalService to set
	 */
	public void setSecrecyNetworkterminalService(
			SecrecyNetworkterminalService secrecyNetworkterminalService) {
		this.secrecyNetworkterminalService = secrecyNetworkterminalService;
	}

	/**
	 * @return the secrecyNetworkterminal
	 */
	public SecrecyNetworkterminal getSecrecyNetworkterminal() {
		return secrecyNetworkterminal;
	}

	/**
	 * @param secrecyNetworkterminal the secrecyNetworkterminal to set
	 */
	public void setSecrecyNetworkterminal(
			SecrecyNetworkterminal secrecyNetworkterminal) {
		this.secrecyNetworkterminal = secrecyNetworkterminal;
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