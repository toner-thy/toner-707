package com.cdthgk.disclosesecrecy.action;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.cdthgk.bmp.log.context.BusinessLogContext;
import com.cdthgk.bmp.log.vo.BusinessLog;
import com.cdthgk.common.bean.BeanUtils;
import com.cdthgk.common.bean.rule.CopyRuleEnum;
import com.cdthgk.common.lang.CollectionUtils;
import com.cdthgk.common.lang.StringUtils;
import com.cdthgk.disclosesecrecy.service.CaseCriticalviolationChangeService;
import com.cdthgk.disclosesecrecy.service.CaseCriticalviolationClearService;
import com.cdthgk.disclosesecrecy.service.CaseCriticalviolationService;
import com.cdthgk.disclosesecrecy.service.CaseHandledutyPersonService;
import com.cdthgk.disclosesecrecy.vo.CaseCriticalviolation;
import com.cdthgk.disclosesecrecy.vo.CaseCriticalviolationChange;
import com.cdthgk.disclosesecrecy.vo.CaseCriticalviolationClear;
import com.cdthgk.disclosesecrecy.vo.CaseHandledutyPerson;
import com.cdthgk.disclosesecrecy.vo.DiscloseSecrecy;
import com.cdthgk.platform.base.action.PlatformAction;
import com.cdthgk.platform.dictionary.context.DictionaryContext;
import com.cdthgk.platform.dictionary.spi.DictionaryService;
import com.cdthgk.platform.district.domain.District;

import ec.common.PageSortModel;

public class CaseCriticalviolationAction extends PlatformAction {

	private static final long serialVersionUID = 9064155163585641297L;
	//严重违规事件
	private CaseCriticalviolation caseCriticalviolation;
	private CaseCriticalviolationService caseCriticalviolationService;
	private Boolean needReload = false;
	private String caseCriticalviolationIds;
	private List<CaseCriticalviolation> caseCriticalviolations;
	//密级变更
	private CaseCriticalviolationChange caseCriticalviolationChange;
	private CaseCriticalviolationClear caseCriticalviolationClear;
	private CaseCriticalviolationChangeService caseCriticalviolationChangeService;
	private CaseCriticalviolationClearService caseCriticalviolationClearService;

	//严重违规处理人
	private CaseHandledutyPersonService caseHandledutyPersonService;
	private CaseHandledutyPerson caseHandledutyPerson;
	private static List<Integer> filterValues = new ArrayList<Integer>();
	static{
		for (int i = 1; i < 18; i++) {
			filterValues.add(i);
		}
	}

	//添加严重违规案件页面
	public String add() {

		return SUCCESS;
	}
	//添加严重违规案件负责人页面
	public String add_CaseHandledutyPerson() {

		return SUCCESS;
	}

	//添加严重违规案件
	public String adding_CaseHandledutyPerson() {

		boolean flag = false;
		Date date = new Date();
		caseHandledutyPerson.setCreateTime(date);
		caseHandledutyPerson.setDataState(0);//状态
		if (null!=caseCriticalviolation&&null!=caseCriticalviolation.getCaseCriticalviolationId()) {
			caseHandledutyPerson.setCaseCriticalviolation(caseCriticalviolation);
			caseHandledutyPerson.setDiscloseCaseType(CaseHandledutyPerson.discloseCaseType_CaseCcv);
		}
		try {
			// 保存实体
			caseHandledutyPersonService.saveCaseHandledutyPerson(caseHandledutyPerson, getCurrentUser());
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		addActionMessage(flag ? "新增严重违规案件案件处理人员成功" : "新增严重违规案件案件处理人员失败");
		needReload = true;
		BusinessLog log = new BusinessLog();
		log.setBusinessName("严重违规案件");
		log.setPrimaryKey(caseHandledutyPerson.getCaseHandledutyPersonId());
		BusinessLogContext.getInstance().getBusinessLogService().saveAddBusinessLogByModule(getCurrentUser(), log, new DiscloseSecrecy());
		return redirectActionResult(LIST);
	}
	//添加严重违规案件
	public String adding() {
		boolean flag = false;
		Date date = new Date();
		// 设置实体属性值
		caseCriticalviolation.setCreateOrgan(getCurrentUser().getUserInfo().getOrgan());
		caseCriticalviolation.setCreatePerson(getCurrentUser().getUserInfo());
		caseCriticalviolation.setCreateTime(date);
		caseCriticalviolation.setStatus(CaseCriticalviolation.PUBLISH_NO);
		caseCriticalviolation.setState(CaseCriticalviolation.PUBLISH_NO);
		try {
			// 保存实体
			caseCriticalviolationService.save(caseCriticalviolation);
			flag = true;
		} catch (Exception e) {
			flag = false;
		}

		addActionMessage(flag ? "新增严重违规案件成功" : "新增严重违规案件失败");
		needReload = true;
		BusinessLog log = new BusinessLog();
		log.setBusinessName("严重违规案件");
		log.setPrimaryKey(caseCriticalviolation.getCaseCriticalviolationId());
		BusinessLogContext.getInstance().getBusinessLogService().saveAddBusinessLogByModule(getCurrentUser(), log, new CaseCriticalviolation());
		return redirectActionResult(LIST);
	}

	public String exportExcel_List() throws ParseException {
		String includeChild="";
		String districtCode="";
		HttpServletRequest r = this.getRequest();
		if (null!=getRequest().getParameter("districtCode")&&!getRequest().getParameter("districtCode").equals("")) {
			districtCode = r.getParameter("districtCode");
		}

		if (null!=getRequest().getParameter("includeChild")&&!getRequest().getParameter("includeChild").equals("")) {
			includeChild = r.getParameter("includeChild");
		}
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = caseCriticalviolationService.get(districtCode, District.class);
		}
		caseCriticalviolations = caseCriticalviolationService.listForAll(caseCriticalviolation,getCurrentUser(),districtCode,includeChild);
		Map<String, Object> params = new HashMap<String, Object>();
		//被导出的数据
		params.put("caseCriticalviolations", caseCriticalviolations);
		//数据类型转换
		params.put("Integer", Integer.class);
		//时间格式化器
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		params.put("dateFormat", dateFormat);
		//数据字典工具
		DictionaryService dictionary = DictionaryContext.getInstance().getDictionaryService();
		params.put("dictionary", dictionary);

		setResultData(params);
		return SUCCESS;
	}
	//严重违规案件密级变更
	public String change() {

		CaseCriticalviolation ds = caseCriticalviolationService.get(caseCriticalviolation
				.getCaseCriticalviolationId());
		CaseCriticalviolationChange caseCriticalviolationChange = new CaseCriticalviolationChange();
		caseCriticalviolationChange.setCaseCriticalviolation(ds);
		caseCriticalviolationChange.setBeforeLevel(ds.getSecrecyLevel());
		putToRequest("caseCriticalviolationChange", caseCriticalviolationChange);
		return SUCCESS;

	}

	//严重违规案件详情
	public String detail() {
		String id = caseCriticalviolation.getCaseCriticalviolationId();
		caseCriticalviolationChange = caseCriticalviolationChangeService.get(id);
		caseCriticalviolation = caseCriticalviolationService.get(id);
		List<CaseCriticalviolationChange> caseCriticalviolation_changelist = new ArrayList<CaseCriticalviolationChange>(caseCriticalviolation.getCaseCriticalviolationChangeSet());
		putToRequest("caseCriticalviolation_changelist", caseCriticalviolation_changelist);
		this.putToRequest("caseCriticalviolationChange", caseCriticalviolationChange);
		return SUCCESS;
	}
	//严重违规案件密级变更详情
	public String changeDetail() {
		String id = caseCriticalviolationChange.getCaseCriticalviolationChangeId();
		caseCriticalviolationChange = caseCriticalviolationChangeService.get(id);
		List<CaseCriticalviolation> caseCriticalviolation_list = new ArrayList<CaseCriticalviolation>();
		caseCriticalviolation_list.add(caseCriticalviolationChange.getCaseCriticalviolation());
		putToRequest("caseCriticalviolation_list", caseCriticalviolation_list);
		this.putToRequest("caseCriticalviolationChange", caseCriticalviolationChange);
		return SUCCESS;
	}
	//修改严重违规案件密级
	public String changeing() {

		caseCriticalviolationChange.setCreatePerson(getCurrentUser());
		caseCriticalviolationChange.setCreateDate(new Date());
		CaseCriticalviolation beforeCc=new CaseCriticalviolation();
		CaseCriticalviolation ds=null;
		try{
			CaseCriticalviolationChange obj = caseCriticalviolationChangeService.save(caseCriticalviolationChange);

			ds = caseCriticalviolationService.get(obj
					.getCaseCriticalviolation().getCaseCriticalviolationId());
			BeanUtils.copyProperties(beforeCc, ds, CopyRuleEnum.ignoreCaseNull);
			ds.setSecrecyLevel(obj.getAfterLevel());
			caseCriticalviolationService.saveOrUpdate(ds);
			BusinessLog log = new BusinessLog();
    		log.setBusinessName("严重违规案件");
    		log.setPrimaryKey(ds.getCaseCriticalviolationId());
    		BusinessLogContext
    				.getInstance()
    				.getBusinessLogService()
    				.saveEditBusinessLogByModule(getCurrentUser(), log, ds,
    						beforeCc);
		}catch(Exception e) {
			e.printStackTrace();
			addActionMessage("严重违规案件密级变更失败！");
			return SUCCESS;
		}

		addActionMessage("严重违规案件密级变更成功！");
		putToRequest("reflag", 1);//成功的标志
		needReload = true;
		return SUCCESS;

	}
	//严重违规案件密级解除
	public String clear() {

		CaseCriticalviolation ds = caseCriticalviolationService.get(caseCriticalviolation
				.getCaseCriticalviolationId());
		CaseCriticalviolationClear caseCriticalviolationClear = new CaseCriticalviolationClear();
		caseCriticalviolationClear.setCaseCriticalviolation(ds);
		putToRequest("caseCriticalviolationClear", caseCriticalviolationClear);
		return SUCCESS;

	}
	//严重违规案件密级解除详情
	public String clearDetail() {

		String id =caseCriticalviolationClear.getCaseCriticalviolationClearId();
		caseCriticalviolationClear = caseCriticalviolationClearService.get(id);
		caseCriticalviolation=caseCriticalviolationClear.getCaseCriticalviolation();
		this.putToRequest("caseCriticalviolationClear", caseCriticalviolationClear);
		return detail();
	}
	//编辑严重违规案件密级解除
	public String clearing() {

		caseCriticalviolationClear.setCreatePerson(getCurrentUser());
		caseCriticalviolationClear.setCreateDate(new Date());
		CaseCriticalviolation beforeCc=new CaseCriticalviolation();
		CaseCriticalviolation ds=null;
		try{
			CaseCriticalviolationClear obj = caseCriticalviolationClearService.save(caseCriticalviolationClear);

			ds = caseCriticalviolationService.get(obj.getCaseCriticalviolation().getCaseCriticalviolationId());
			BeanUtils.copyProperties(beforeCc, ds, CopyRuleEnum.ignoreCaseNull);
			ds.setStatus(1);
			caseCriticalviolationService.saveOrUpdate(ds);
			BusinessLog log = new BusinessLog();
    		log.setBusinessName("严重违规案件");
    		log.setPrimaryKey(ds.getCaseCriticalviolationId());
    		BusinessLogContext
    				.getInstance()
    				.getBusinessLogService()
    				.saveEditBusinessLogByModule(getCurrentUser(), log, ds,
    						beforeCc);
		}catch(Exception e) {
			e.printStackTrace();
			addActionMessage("严重违规案件密级解除失败！");
			return SUCCESS;
		}

		addActionMessage("严重违规案件密级解除成功！");
		putToRequest("reflag", 1);//成功的标志
		needReload = true;
		return SUCCESS;
	}

	//删除严重违规案件
	public String del() {
		String message = "";
		for (String id : getIds()) {
			caseCriticalviolation = caseCriticalviolationService.get(id);
			if(caseCriticalviolation != null){
				if(CollectionUtils.isEmpty(caseCriticalviolation.getCaseCriticalviolationChangeSet())
						&& CollectionUtils.isEmpty(caseCriticalviolation.getCaseCriticalviolationClearSet())){
					CaseCriticalviolation cadel= caseCriticalviolationService.delete(caseCriticalviolation);
					BusinessLog log = new BusinessLog();
        			log.setBusinessName("严重违规案件");
        			log.setPrimaryKey(id);
        			BusinessLogContext
        					.getInstance()
        					.getBusinessLogService()
        					.saveDelBusinessLogByModule(getCurrentUser(), log,
        							cadel);
					message = getMessageConstant().getDeleteSuccess();
				} else {
					message = getMessageConstant().getDeleteFailure();
				}
				addActionMessage(message);
			}
		}
		return SUCCESS;
	}

	/**
	 * ajax获取严重违规案件  是否有关联
	 * @return
	 */
	public String ajax_discloseSecrecy() {
		//查看要害部位是否 和其他的表有关联    true有  false没有
		int flag = caseCriticalviolationService.getCaseCriticalviolationIdRelationship(this.getRequest().getParameter("caseCriticalviolationId"));
		String message = "";
		if(flag==1){
			message = "泄密案件密级变更";
		}else if(flag == 2) {
			message = "泄密案件密级解除";
		}

		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("flag", String.valueOf(flag));
		resultMap.put("message", message);

		this.setResultData(resultMap);
		return JSON;
	}

	//删除严重违规案件负责人
	public String del_CaseHandledutyPerson() {
		boolean flag = false;
		try {
			caseHandledutyPersonService.deleteBatchIdList(getIds());
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		addActionMessage(flag ? "删除严重违规案件负责人成功" : "删除严重违规案件负责人失败");
		return SUCCESS;
	}
	//编辑严重违规案件负责人编辑页面
	public String edit_CaseHandledutyPerson() {

		caseHandledutyPerson = caseHandledutyPersonService.get(caseHandledutyPerson.getCaseHandledutyPersonId());
		if (caseHandledutyPerson.getDiscloseCaseType()==CaseHandledutyPerson.discloseCaseType_CaseCcv) {

			caseCriticalviolation.setCaseCriticalviolationId(caseHandledutyPerson.getCaseCriticalviolation().getCaseCriticalviolationId());
		}
		return SUCCESS;

	}

	//严重违规案件人员详情
	public String detail_CaseHandledutyPerson() {
		String id = caseHandledutyPerson.getCaseHandledutyPersonId();
		caseHandledutyPerson = caseHandledutyPersonService.get(id);

		this.putToRequest("caseHandledutyPerson", caseHandledutyPerson);
		return SUCCESS;
	}
	//编辑严重违规案件页面
	public String edit() {

		PageSortModel<CaseHandledutyPerson> psm = new PageSortModel<CaseHandledutyPerson>(getRequest(), "caseHandledutyPersonList");
		if (null !=caseCriticalviolation&&null!=caseCriticalviolation.getCaseCriticalviolationId()) {

			caseCriticalviolation = caseCriticalviolationService.get(caseCriticalviolation.getCaseCriticalviolationId());
		}
		else {
			caseCriticalviolation = caseCriticalviolationService.get(getId());

		}
		//编辑涉密事件时获得严重违规案件负责人列表
		putToRequest("caseHandledutyPersonList", caseHandledutyPersonService.queryCaseHandledutyPersonList(psm,null,caseCriticalviolation));
		return SUCCESS;

	}
	//编辑严重违规案件负责人
	public String editing_CaseHandledutyPerson() {
		boolean flag = false;
		CaseHandledutyPerson chdp= caseHandledutyPersonService.get(caseHandledutyPerson.getCaseHandledutyPersonId());
		chdp.setUserInfo(caseHandledutyPerson.getUserInfo());
		chdp.setManageLevel(caseHandledutyPerson.getManageLevel());
		chdp.setHandleType(caseHandledutyPerson.getHandleType());
		chdp.setPoliticalLandscape(caseHandledutyPerson.getPoliticalLandscape());
		chdp.setDepartment(caseHandledutyPerson.getDepartment());
		if (caseHandledutyPerson.getDiscloseCaseType()==CaseHandledutyPerson.discloseCaseType_CaseCcv) {

			caseCriticalviolation.setCaseCriticalviolationId(caseHandledutyPerson.getCaseCriticalviolation().getCaseCriticalviolationId());
		}
		if (caseHandledutyPerson.getDiscloseCaseType()==CaseHandledutyPerson.discloseCaseType_DC) {
		}
		// 更新实体
		try {
			caseHandledutyPersonService.updateCaseHandledutyPerson(chdp,getCurrentUser());
			this.putToRequest("caseHandledutyPerson", chdp);
			flag = true;
		} catch (Exception e) {
			flag = false;
		}

		addActionMessage(flag ? "编辑严重违规案件负责人成功" : "编辑严重违规案件负责人失败");
		needReload = true;
		return redirectActionResult(LIST);
	}

	//编辑严重违规案件
	public String editing() {
		boolean flag = false;
		// 创建更新实体DiscloseSecrecy discloseSecrecy
		CaseCriticalviolation beforeCc=new CaseCriticalviolation();
		CaseCriticalviolation ds = caseCriticalviolationService.get(caseCriticalviolation
				.getCaseCriticalviolationId());
		BeanUtils.copyProperties(beforeCc, ds, CopyRuleEnum.ignoreCaseNull);

		ds.setModifyPerson(getCurrentUser().getUserInfo());
		ds.setModifyTime(new Date());
		ds.setSecrecyLevel(caseCriticalviolation.getSecrecyLevel());
		ds.setStatus(caseCriticalviolation.getStatus());
		ds.setName(caseCriticalviolation.getName());
		ds.setCaseType(caseCriticalviolation.getCaseType());
		ds.setDealResult(caseCriticalviolation.getDealResult());
		ds.setDutyOrganKind(caseCriticalviolation.getDutyOrganKind());
		ds.setDepartment(caseCriticalviolation.getDepartment());
		ds.setStatus(DiscloseSecrecy.PUBLISH_NO);
		// 更新实体
		try {
			caseCriticalviolationService.update(ds);
			this.putToRequest("caseCriticalviolation", ds);
			flag = true;
		} catch (Exception e) {
			flag = false;
		}

		addActionMessage(flag ? "编辑严重违规案件成功" : "编辑严重违规案件失败");
		needReload = true;
		BusinessLog log = new BusinessLog();
		log.setBusinessName("严重违规案件");
		log.setPrimaryKey(ds.getCaseCriticalviolationId());
		BusinessLogContext.getInstance().getBusinessLogService().saveEditBusinessLogByModule(getCurrentUser(), log, ds, beforeCc);
		return redirectActionResult(LIST);
	}
	private  String baomiju;
	private District district;
	//分页显示严重违规案件
	public String  list()
	{
		HttpServletRequest r = this.getRequest();
		boolean baomijuOrbendanwei = false;
		if((r.getParameter("baomijuOrbendanwei")!=null &&!r.getParameter("baomijuOrbendanwei").equals(""))
				||(baomiju!=null&&!baomiju.equals(""))){
			baomijuOrbendanwei =true;
			putToRequest("baomijuOrbendanwei", "baomiju");
			System.out.println("保密局");
		}

		PageSortModel<CaseCriticalviolation> psm = new PageSortModel<CaseCriticalviolation>(getRequest(), "caseCriticalviolationList");
		putToRequest("caseCriticalviolationList", caseCriticalviolationService.listForEc(psm,
				caseCriticalviolation, getCurrentUser(),baomijuOrbendanwei));
		return SUCCESS;
	}
	//分页显示泄密事件--保密局查询首次跳转页面
	public String  selectList()
	{
		return SUCCESS;
	}
	//分页显示泄密事件--保密局查询
	public String  selectListing()
	{

		String includeChild="";
		String districtCode="";
		HttpServletRequest r = this.getRequest();
		if (null!=getRequest().getParameter("districtCode")&&!getRequest().getParameter("districtCode").equals("")) {
			districtCode = r.getParameter("districtCode");
			if (districtCode.indexOf("?")>=0) {
				districtCode=districtCode.substring(0 ,districtCode.indexOf("?"));
			}
			district = caseCriticalviolationClearService.get(districtCode, District.class);
		}else{
			district = getCurrentUser().getOrgan().getDistrict();
			districtCode=getCurrentUser().getOrgan().getDistrict().getCode();
		}

		if (null!=getRequest().getParameter("secrecyType")&&!getRequest().getParameter("secrecyType").equals("")) {
			caseCriticalviolation.setSecrecyLevel(Integer.parseInt(getRequest().getParameter("secrecyType")));
		}

		 if (null!=getRequest().getParameter("retrun")&&getRequest().getParameter("retrun").equals("true")) {
	                        putToRequest("retrun","true");
	                }

		if (null!=getRequest().getParameter("includeChild")&&!getRequest().getParameter("includeChild").equals("")) {
			includeChild = r.getParameter("includeChild");
		}else{
			//包含
			includeChild="1";
		}
		putToRequest("includeChild",includeChild);
		putToRequest("districtCode",districtCode);

		PageSortModel<CaseCriticalviolation> psm = new PageSortModel<CaseCriticalviolation>(getRequest(), "caseCriticalviolationList");
		putToRequest("caseCriticalviolationList", caseCriticalviolationService.listForSelect(psm,caseCriticalviolation, districtCode,includeChild));
		return SUCCESS;
	}


	//分页显示严重违规案件密级变更
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String list_change(){
		// 获取list
		PageSortModel psm = new PageSortModel(getRequest(), "caseCriticalviolation_changelist");

		List<CaseCriticalviolationChange> caseCriticalviolation_changelist = caseCriticalviolationChangeService.queryCaseCriticalviolationChangelist(psm, caseCriticalviolationChange,getCurrentUser());

		putToRequest("caseCriticalviolation_changelist", caseCriticalviolation_changelist);
		return "list";
	}
	//分页显示严重违规案件密级变更--保密局查询
	@SuppressWarnings({ "rawtypes" })
	public String select_change(){
		String includeChild="";
		String districtCode="";
		HttpServletRequest r = this.getRequest();
		if (null!=getRequest().getParameter("districtCode")&&!getRequest().getParameter("districtCode").equals("")) {
			districtCode = r.getParameter("districtCode");
			district = caseCriticalviolationClearService.get(districtCode, District.class);
		}else{
			district = getCurrentUser().getOrgan().getDistrict();
			districtCode=getCurrentUser().getOrgan().getDistrict().getCode();
		}
		if (null!=getRequest().getParameter("includeChild")&&!getRequest().getParameter("includeChild").equals("")) {
			includeChild = r.getParameter("includeChild");
		}else{
			//包含
			includeChild="1";
		}
		putToRequest("includeChild",includeChild);
		putToRequest("districtCode",districtCode);
		// 获取list
		PageSortModel psm = new PageSortModel(getRequest(), "caseCriticalviolation_changelist");

		List<CaseCriticalviolationChange> caseCriticalviolation_changelist = caseCriticalviolationChangeService.queryCaseCriticalviolationChangelist(psm, caseCriticalviolationChange,getCurrentUser(),districtCode,includeChild);

		putToRequest("caseCriticalviolation_changelist", caseCriticalviolation_changelist);
		return "list";
	}

	//分页显示严重违规案件密级解除
	public String list_clear(){
		// 获取list
		PageSortModel<CaseCriticalviolationClear> psm = new PageSortModel<CaseCriticalviolationClear>(getRequest(), "caseCriticalviolationClear_clearlist");
		List<CaseCriticalviolationClear> caseCriticalviolation_clearlist = caseCriticalviolationClearService.queryCaseCriticalviolationClearList(psm, caseCriticalviolationClear,getCurrentUser());
		putToRequest("caseCriticalviolation_clearlist", caseCriticalviolation_clearlist);
		return "list";
	}
	//分页显示严重违规案件密级解除--保密局查询
	public String select_clear(){
		String includeChild="";
		String districtCode="";
		HttpServletRequest r = this.getRequest();
		if (null!=getRequest().getParameter("districtCode")&&!getRequest().getParameter("districtCode").equals("")) {
			districtCode = r.getParameter("districtCode");
			district = caseCriticalviolationClearService.get(districtCode, District.class);
		}else{
			district = getCurrentUser().getOrgan().getDistrict();
			districtCode=getCurrentUser().getOrgan().getDistrict().getCode();
		}
		if (null!=getRequest().getParameter("includeChild")&&!getRequest().getParameter("includeChild").equals("")) {
			includeChild = r.getParameter("includeChild");
		}else{
			//包含
			includeChild="1";
		}
		putToRequest("includeChild",includeChild);
		putToRequest("districtCode",districtCode);
		PageSortModel<CaseCriticalviolationClear> psm = new PageSortModel<CaseCriticalviolationClear>(getRequest(), "caseCriticalviolationClear_clearlist");
		List<CaseCriticalviolationClear> caseCriticalviolation_clearlist = caseCriticalviolationClearService.queryCaseCriticalviolationClearList(psm, caseCriticalviolationClear,getCurrentUser(),districtCode,includeChild);
		putToRequest("caseCriticalviolation_clearlist", caseCriticalviolation_clearlist);
		return "list";
	}

	public String listMain() {
		return SUCCESS;
	}

	/**
	 * 查询列表主页
	 * @return
	 */
	public String queryMain() {
		String districtCode="";
		String includeChild="";
		HttpServletRequest r = this.getRequest();
		if (null!=getRequest().getParameter("districtCode")) {
			districtCode = r.getParameter("districtCode");
		}else{
			districtCode=getCurrentUser().getOrgan().getDistrict().getCode();
		}
		putToRequest("districtCode",districtCode);
		if (null!=getRequest().getParameter("includeChild")) {
			includeChild = r.getParameter("includeChild");
		}else{
			//包含
			includeChild="1";
		}
		putToRequest("includeChild",includeChild);
		return SUCCESS;
	}

	public CaseCriticalviolation getCaseCriticalviolation() {
		return caseCriticalviolation;
	}
	public void setCaseCriticalviolation(CaseCriticalviolation caseCriticalviolation) {
		this.caseCriticalviolation = caseCriticalviolation;
	}
	public CaseCriticalviolationService getCaseCriticalviolationService() {
		return caseCriticalviolationService;
	}
	public void setCaseCriticalviolationService(
			CaseCriticalviolationService caseCriticalviolationService) {
		this.caseCriticalviolationService = caseCriticalviolationService;
	}
	public Boolean getNeedReload() {
		return needReload;
	}
	public void setNeedReload(Boolean needReload) {
		this.needReload = needReload;
	}
	public String getCaseCriticalviolationIds() {
		return caseCriticalviolationIds;
	}
	public void setCaseCriticalviolationIds(String caseCriticalviolationIds) {
		this.caseCriticalviolationIds = caseCriticalviolationIds;
	}
	public List<CaseCriticalviolation> getCaseCriticalviolations() {
		return caseCriticalviolations;
	}
	public void setCaseCriticalviolations(
			List<CaseCriticalviolation> caseCriticalviolations) {
		this.caseCriticalviolations = caseCriticalviolations;
	}
	public CaseCriticalviolationChange getCaseCriticalviolationChange() {
		return caseCriticalviolationChange;
	}
	public void setCaseCriticalviolationChange(
			CaseCriticalviolationChange caseCriticalviolationChange) {
		this.caseCriticalviolationChange = caseCriticalviolationChange;
	}
	public CaseCriticalviolationClear getCaseCriticalviolationClear() {
		return caseCriticalviolationClear;
	}
	public void setCaseCriticalviolationClear(
			CaseCriticalviolationClear caseCriticalviolationClear) {
		this.caseCriticalviolationClear = caseCriticalviolationClear;
	}
	public CaseCriticalviolationChangeService getCaseCriticalviolationChangeService() {
		return caseCriticalviolationChangeService;
	}
	public void setCaseCriticalviolationChangeService(
			CaseCriticalviolationChangeService caseCriticalviolationChangeService) {
		this.caseCriticalviolationChangeService = caseCriticalviolationChangeService;
	}
	public CaseCriticalviolationClearService getCaseCriticalviolationClearService() {
		return caseCriticalviolationClearService;
	}
	public void setCaseCriticalviolationClearService(
			CaseCriticalviolationClearService caseCriticalviolationClearService) {
		this.caseCriticalviolationClearService = caseCriticalviolationClearService;
	}
	public CaseHandledutyPersonService getCaseHandledutyPersonService() {
		return caseHandledutyPersonService;
	}
	public void setCaseHandledutyPersonService(
			CaseHandledutyPersonService caseHandledutyPersonService) {
		this.caseHandledutyPersonService = caseHandledutyPersonService;
	}
	public CaseHandledutyPerson getCaseHandledutyPerson() {
		return caseHandledutyPerson;
	}
	public void setCaseHandledutyPerson(CaseHandledutyPerson caseHandledutyPerson) {
		this.caseHandledutyPerson = caseHandledutyPerson;
	}
	public  List<Integer> getFilterValues() {
		return filterValues;
	}
	public  void setFilterValues(List<Integer> filterValues) {
		CaseCriticalviolationAction.filterValues = filterValues;
	}
	public String getBaomiju() {
		return baomiju;
	}
	public void setBaomiju(String baomiju) {
		this.baomiju = baomiju;
	}
	public District getDistrict() {
		return district;
	}
	public void setDistrict(District district) {
		this.district = district;
	}

}
