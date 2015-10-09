package com.cdthgk.bmp.secrecyorganization.secrecyoffice.action;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.cdthgk.bmp.core.action.BmpAction;
import com.cdthgk.bmp.secrecyorganization.secrecycommittee.service.SecrecyCommitteeModuleService;
import com.cdthgk.bmp.secrecyorganization.secrecycommittee.vo.SecrecyCommittee;
import com.cdthgk.bmp.secrecyorganization.secrecyoffice.dto.SecrecyOfficeDto;
import com.cdthgk.bmp.secrecyorganization.secrecyoffice.service.SecrecyOfficeMemberModuleService;
import com.cdthgk.bmp.secrecyorganization.secrecyoffice.service.SecrecyOfficeModuleService;
import com.cdthgk.bmp.secrecyorganization.secrecyoffice.transmitor.exchange.SecrecyOfficeExchanger;
import com.cdthgk.bmp.secrecyorganization.secrecyoffice.vo.EmployPerson;
import com.cdthgk.bmp.secrecyorganization.secrecyoffice.vo.EstablishSituation;
import com.cdthgk.bmp.secrecyorganization.secrecyoffice.vo.Infrastructure;
import com.cdthgk.bmp.secrecyorganization.secrecyoffice.vo.InternalOrgan;
import com.cdthgk.bmp.secrecyorganization.secrecyoffice.vo.LeaderStaff;
import com.cdthgk.bmp.secrecyorganization.secrecyoffice.vo.SecrecyOffice;
import com.cdthgk.bmp.secrecyorganization.secrecyoffice.vo.SecrecyOfficeMember;
import com.cdthgk.common.bean.BeanUtils;
import com.cdthgk.common.bean.rule.CopyRuleEnum;
import com.cdthgk.common.db.data.xml.XmlExportor;
import com.cdthgk.common.lang.CollectionUtils;
import com.cdthgk.exchange.serverorganmapping.service.ServerReportMappingService;
import com.cdthgk.platform.base.util.ReportState;
import com.cdthgk.platform.dictionary.context.DictionaryContext;
import com.cdthgk.platform.dictionary.domain.DictionaryOption;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.core.OrganizationContext;
import com.cdthgk.platform.organization.organ.domain.Organ;

/**
 * <p>
 * 保密办（保密局） Action 类
 * </p>
 * <p>
 * 陶汇源 2012-12-24 17:43:03
 * </p>
 * <blockquote>
 * <h4>历史修改记录</h4>
 * <ul>
 * <li>修改人 修改时间 修改描述
 * </ul>
 * </blockquote>
 * <p>
 * copyright taohy 2012, all rights reserved.
 * </p>
 *
 * @author FastCodeingTools
 * @author taohy
 * @since 1.0
 * @version 1.0
 * @param <E>
 */
public class SecrecyOfficeAction<E> extends BmpAction {

	private static final long serialVersionUID = -2561934305983981072L;

	// 通用字段
	private SecrecyOfficeModuleService secrecyOfficeModuleService;
	private SecrecyCommitteeModuleService secrecyCommitteeModuleService;
	private SecrecyOfficeMemberModuleService secrecyOfficeMemberModuleService;

	private String secrecyOfficeIds;
	private SecrecyOffice secrecyOffice;
	private List<SecrecyOffice> secrecyOfficeList;
	private List<SecrecyOfficeMember> secrecyOfficeMemberList;

	private District district;
	private Organ organ;

	private EmployPerson employPerson;
	private EstablishSituation establishSituation;
	private LeaderStaff leaderStaff;
	private InternalOrgan internalOrgan;
	private List<InternalOrgan> internalOrganList = new ArrayList<InternalOrgan>();
	private List<Infrastructure> infrastructureList = new ArrayList<Infrastructure>();

	//是否显示上报字段
	private boolean isShow = true;
	private DataSource dataSource;
	private SecrecyOfficeExchanger secrecyOfficeExchanger;
	private ServerReportMappingService serverReportMappingService;
	// *********************** 方 法 ***********************

	// 构造器
	/** 默认构造器 */
	public SecrecyOfficeAction() {
	}

	/**
	 * <p>
	 * 详情
	 * </p>
	 * <p>
	 * 陶汇源 2012-12-29 14:43:41
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人：宋亚非  修改时间 ：2013-05-13 修改描述：屏蔽查询功能结果中的操作按钮
	 * </ul>
	 * </blockquote>
	 * <p>
	 * copyright tianyu 2012, all rights reserved.
	 * </p>
	 *
	 * @author FastCodeingTools
	 * @author 陶汇源
	 * @since 1.0
	 * @version 1.0
	 */
	public String detail() {
		// 详情页面为isDetail=1，编辑页面为isDetail=0；
		putToRequest("isDetail", "1");
		if(district == null){
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district =  secrecyCommitteeModuleService.get(district.getDistrictCode(), District.class);
		}
		SecrecyCommittee secrecyCommittee = secrecyCommitteeModuleService.getByDistrict(district);

		if (secrecyCommittee == null) {
			// 不存在保密委
			addActionMessage("当前尚未填写保密委员会，请在【保密委员会】中添加。");
			putToRequest("message", "当前尚未填写保密委员会，请在【保密委员会】中添加。");
			putToRequest("secrecyCommittee", "0");
			return DETAIL;
		} else {
			putToRequest("secrecyCommittee", "1");
		}
		//增加了从页面传递过来的参数editable，目的是让查询的结果中不显示操作按钮。
		String editable = this.getRequest().getParameter("editable");
		if( editable!=null && "0".equals(editable)){
			putToRequest("secrecyCommittee", "0");
		}

		// 行政区划不是当前登录用户的行政区划到详情页面，否则到添加页面。
		if(district.getDistrictCode().equals(getCurrentUser().getOrgan().getDistrict().getDistrictCode())){
			secrecyOffice = secrecyOfficeModuleService.getSecrecyOfficeByOrgan(getCurrentUser().getOrgan());
			if (secrecyOffice == null) {
				// 不存在保密办（保密局）
				//如果是查询功能，则不应转到增加界面
				if( !"0".equals(editable) ){
					return redirectActionResult(ADD);
				}
			} else {
				// 初始化人员编制构成情况
				secrecyOffice = secrecyOfficeModuleService.initEstablishSituation(secrecyOffice);
				// 初始化内设机构基本情况
				List<DictionaryOption> dictionaryOptionList = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "internalOrgan");
				putToRequest("dictionaryOptionList", dictionaryOptionList);
				List<Map<String, Object>> list = secrecyOfficeModuleService.initInternalOrgan(secrecyOffice, dictionaryOptionList);
				putToRequest("list", list);
				// 初始化基础设施建设基本情况 （集合在secrecyOffice.infrastructureSet里）
			}
			putToRequest("viewCondition", "1");
		} else {
			//通过行政区的保密委查询保密办
			secrecyOffice = secrecyOfficeModuleService.getSecrecyOfficeBySecrecyCommittee(secrecyCommittee);
			if (secrecyOffice == null) {
				secrecyOffice = new SecrecyOffice();
			}
			// 初始化人员编制构成情况
			secrecyOffice = secrecyOfficeModuleService.initEstablishSituation(secrecyOffice);
			// 初始化内设机构基本情况
			List<DictionaryOption> dictionaryOptionList = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "internalOrgan");
			putToRequest("dictionaryOptionList", dictionaryOptionList);
			List<Map<String, Object>> list = secrecyOfficeModuleService.initInternalOrgan(secrecyOffice, dictionaryOptionList);
			putToRequest("list", list);
			putToRequest("viewCondition", "0");
		}

		//是否显示上报(列表页和带行政区的查询列表页)
		if(getRequest().getParameter("va") != null){
			isShow = false;
		}
		putToRequest("isShow", isShow);

		return DETAIL;
	}

	/**
	 *
	 * <p>
	 * 方法名：exportData 导出列表数据
	 * </p>
	 * <p>
	 * 宋亚非 2013-5-21 上午9:33:14
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
	public String exportData() {
		Map<String, Object> params = new HashMap<String, Object>();
		if(district == null){
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district =  secrecyCommitteeModuleService.get(district.getDistrictCode(), District.class);
		}
		params.put("district", district);
		SecrecyCommittee secrecyCommittee = secrecyCommitteeModuleService.getByDistrict(district);

		if (secrecyCommittee == null) {
			return "success";
		}
		// 行政区划不是当前登录用户的行政区划到详情页面，否则到添加页面。
		if(district.getDistrictCode().equals(getCurrentUser().getOrgan().getDistrict().getDistrictCode())){
			secrecyOffice = secrecyOfficeModuleService.getSecrecyOfficeByOrgan(getCurrentUser().getOrgan());
			if (secrecyOffice == null) {
				// 不存在保密办（保密局）
			} else {
				params.put("secrecyOffice", secrecyOffice);
				// 得到成员列表
				secrecyOfficeMemberList = secrecyOfficeMemberModuleService.getAllList(secrecyOffice);
				params.put("secrecyOfficeMemberList", secrecyOfficeMemberList);
			}
		} else {
			//通过行政区的保密委查询保密办
			secrecyOffice = secrecyOfficeModuleService.getSecrecyOfficeBySecrecyCommittee(secrecyCommittee);
			if(secrecyOffice != null){
				params.put("secrecyOffice", secrecyOffice);
				secrecyOfficeMemberList = secrecyOfficeMemberModuleService.getAllList(secrecyOffice);
				params.put("secrecyOfficeMemberList", secrecyOfficeMemberList);
			}
		}
		// 组装编制情况导出的集合为MAP
		List<EmployPerson> epList = new ArrayList<EmployPerson>(secrecyOffice.getEmployPersonSet());
		List<EstablishSituation> esList = new ArrayList<EstablishSituation>(secrecyOffice.getEstablishSituationSet());
		List<LeaderStaff> lsList = new ArrayList<LeaderStaff>(secrecyOffice.getLeaderStaffSet());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("civil", (esList == null || esList.size()==0) ? 0 : esList.get(0).getCivil());
		map.put("referMamager",(esList == null || esList.size()==0) ? 0 : esList.get(0).getReferMamager());
		map.put("fullFunds", (esList == null || esList.size()==0) ? 0 : esList.get(0).getFullFunds());
		map.put("selfFunds", (esList == null || esList.size()==0) ? 0 : esList.get(0).getSelfFunds());
		map.put("secretary", (lsList == null || lsList.size()==0) ? 0 : lsList.get(0).getSecretary());
		map.put("deputySecretary", (lsList == null || lsList.size()==0) ? 0 : lsList.get(0).getDeputySecretary());
		map.put("inspector", (lsList == null || lsList.size()==0) ? 0 : lsList.get(0).getInspector());
		map.put("deputyInspector", (lsList == null || lsList.size()==0) ? 0 : lsList.get(0).getDeputyInspector());
		map.put("deptStaff", (lsList == null || lsList.size()==0) ? 0 : lsList.get(0).getDeptStaff());
		map.put("managerStaff", (epList == null || epList.size()==0) ? 0 : epList.get(0).getManagerStaff());
		map.put("specialtyStaff", (epList == null || epList.size()==0) ? 0 : epList.get(0).getSpecialtyStaff());
		map.put("workStaff", (epList == null || epList.size()==0) ? 0 : epList.get(0).getWorkStaff());
		map.put("other", (epList == null || epList.size()==0)? 0 : epList.get(0).getOther());
		params.put("map", map);

		// 初始化内设机构基本情况
		List<DictionaryOption> dictionaryOptionList = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "internalOrgan");
		params.put("dictionaryOptionList", dictionaryOptionList);
		List<Map<String, Object>> list = secrecyOfficeModuleService.initInternalOrgan(secrecyOffice, dictionaryOptionList);
		List<Map<String, Object>> innerList = new ArrayList<Map<String,Object>>();
		for (Map<String, Object> l : list) {
			Map<String, Object> innerMap = new HashMap<String, Object>();
			for (int i = 1; i <= 3; i++) {
				innerMap.put("deptName"+i, l.get("deptName" + i) == null ? "" : l.get("deptName" + i));
				innerMap.put("workNum"+i, l.get("workNum" + i) == null ? "" : l.get("workNum" + i));
				innerMap.put("realNum"+i, l.get("realNum" + i) == null ? "" : l.get("realNum" + i));
				innerMap.put("adminLevel"+i, l.get("adminLevel" + i) == null ? "" : l.get("adminLevel" + i));
			}
			innerList.add(innerMap);
		}
		params.put("innerList", innerList);
		params.put("dictionaryService", DictionaryContext.getInstance().getDictionaryService());
		setResultData(params);
		return SUCCESS;
	}



	/**
	 * <p>
	 * 新增
	 * </p>
	 * <p>
	 * 陶汇源 2012-12-29 14:44:25
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * <p>
	 * copyright tianyu 2012, all rights reserved.
	 * </p>
	 *
	 * @author FastCodeingTools
	 * @author 陶汇源
	 * @since 1.0
	 * @version 1.0
	 */
	public String add() {
		secrecyOffice = new SecrecyOffice();
		secrecyOffice.setMainOrgan(getCurrentUser().getOrgan());
		putToRequest("currentOrgan", getCurrentUser().getOrgan());
		return SUCCESS;
	}

	/**
	 * <p>
	 * 保存
	 * </p>
	 * <p>
	 * 陶汇源 2012-12-29 14:44:39
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * <p>
	 * copyright tianyu 2012, all rights reserved.
	 * </p>
	 *
	 * @author FastCodeingTools
	 * @author 陶汇源
	 * @since 1.0
	 * @version 1.0
	 */
	public String adding() {
		secrecyOffice = secrecyOfficeModuleService
				.saveSystemDataBySecrecyOffice(secrecyOffice, getCurrentUser());
		secrecyOffice.setSecrecyCommittee(secrecyCommitteeModuleService.getByDistrict(getCurrentUser().getOrgan().getDistrict()));
		// 设置通用字段
		secrecyOffice.setCreateTime(new Date());
		secrecyOffice.setCreateUser(getCurrentUser());
		secrecyOffice.setCreateOrgan(getCurrentUser().getUserInfo().getOrgan());
		secrecyOffice.setEnable(1);

		//修改人：宋亚非   修改原因：数据库统一至一个库后，取消上报功能，填写信息后状态即为已上报   修改时间：2013-06-03
		//secrecyOffice.setReportState(ReportState.REPORT_NO);
		secrecyOffice.setReportState(ReportState.REPORT_YES);

		secrecyOfficeModuleService.save(secrecyOffice);
		addActionMessage(getMessageConstant().getSaveSuccess()+ "请继续添加该保密办（保密局）下的成员信息！");
		return redirectActionResult(EDIT);
	}

	/**
	 * <p>
	 * 编辑
	 * </p>
	 * <p>
	 * 陶汇源 2012-12-29 14:45:19
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * <p>
	 * copyright tianyu 2012, all rights reserved.
	 * </p>
	 *
	 * @author FastCodeingTools
	 * @author 陶汇源
	 * @since 1.0
	 * @version 1.0
	 */
	public String edit() {
		if(district == null){
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district =  secrecyCommitteeModuleService.get(district.getDistrictCode(), District.class);
		}
		secrecyOffice = secrecyOfficeModuleService.get(secrecyOffice.getSecrecyOfficeId());
		// 初始化人员编制构成情况
		secrecyOffice = secrecyOfficeModuleService.initEstablishSituation(secrecyOffice);
		// 初始化内设机构基本情况
		List<DictionaryOption> dictionaryOptionList = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "internalOrgan");
		putToRequest("dictionaryOptionList", dictionaryOptionList);
		List<Map<String, Object>> list = secrecyOfficeModuleService.initInternalOrgan(secrecyOffice, dictionaryOptionList);
		putToRequest("list", list);
		// 初始化基础设施建设基本情况 （集合在secrecyOffice.infrastructureSet里）

		// 详情页面为isDetail=1，编辑页面为isDetail=0；
		putToRequest("isDetail", "0");
		secrecyOffice.setMainOrgan(getCurrentUser().getOrgan());
		return SUCCESS;
	}


	/**
	 * <p>
	 * 更新
	 * </p>
	 * <p>
	 * 陶汇源 2012-12-29 14:45:34
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * <p>
	 * copyright tianyu 2012, all rights reserved.
	 * </p>
	 *
	 * @author FastCodeingTools
	 * @author 陶汇源
	 * @since 1.0
	 * @version 1.0
	 */
	public String editing() {
		SecrecyOffice dbSecrecyOffice = secrecyOfficeModuleService.get(secrecyOffice.getSecrecyOfficeId());
		secrecyOffice = secrecyOfficeModuleService
				.saveSystemDataBySecrecyOffice(secrecyOffice, getCurrentUser());
		BeanUtils.copyProperties(dbSecrecyOffice, secrecyOffice, CopyRuleEnum.ignoreCaseEmpty);
		// 设置通用字段
		dbSecrecyOffice.setModifyTime(new Date());
		dbSecrecyOffice.setModifyUser(getCurrentUser());
		dbSecrecyOffice.setModifyOrgan(getCurrentUser().getUserInfo().getOrgan());
		dbSecrecyOffice.setEnable(1);
		//修改人：宋亚非   修改原因：数据库统一至一个库后，取消上报功能，不再存在上报后修改状态   修改时间：2013-06-03
		/*if(dbSecrecyOffice.getReportState() > 0) {
			dbSecrecyOffice.setReportState(ReportState.REPORT_YES_MODIFY);
		}*/
		secrecyOfficeModuleService.update(dbSecrecyOffice);
		addActionMessage(getMessageConstant().getUpdateSuccess());
		return redirectActionResult(DETAIL);
	}

	public String list(){
		if(district == null) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = secrecyCommitteeModuleService.get(district.getDistrictCode(), District.class);
		}
		return redirectActionResult("detail");
	}

	public String main(){
		return SUCCESS;
	}


	/**
	 *
	 * <p>
	 * 新增、编辑人员编制构成
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-7-16 - 上午10:51:25
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @return
	 */
	public String editEstablishSituation() {
		secrecyOffice = secrecyOfficeModuleService.get(secrecyOffice.getSecrecyOfficeId());
		// 初始化人员编制构成情况
		if(CollectionUtils.isNotEmpty(secrecyOffice.getLeaderStaffSet())){
			Iterator<LeaderStaff> iterLeaderStaff = secrecyOffice.getLeaderStaffSet().iterator();
			while (iterLeaderStaff.hasNext()) {
				leaderStaff = iterLeaderStaff.next();
			}
		}
		if(CollectionUtils.isNotEmpty(secrecyOffice.getEstablishSituationSet())){
			Iterator<EstablishSituation> establishSituationStaff = secrecyOffice.getEstablishSituationSet().iterator();
			while (establishSituationStaff.hasNext()) {
				establishSituation = establishSituationStaff.next();
			}
		}
		if(CollectionUtils.isNotEmpty(secrecyOffice.getEmployPersonSet())){
			Iterator<EmployPerson> employPersonStaff = secrecyOffice.getEmployPersonSet().iterator();
			while (employPersonStaff.hasNext()) {
				employPerson = employPersonStaff.next();
			}
		}
		return SUCCESS;
	}

	/**
	 *
	 * <p>
	 * 新增、编辑人员编制构成
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-7-16 - 上午10:51:25
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @return
	 */
	public String editEstablishSituationing() {
		secrecyOfficeModuleService.saveEstablishSituation(secrecyOffice, establishSituation
				, leaderStaff, employPerson);
		addActionMessage(getMessageConstant().getSaveSuccess());
		return redirectActionResult(EDIT);
	}
	/**
	 *
	 * <p>
	 * 新增、编辑内设机构
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-7-16 - 上午10:51:25
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @return
	 */
	public String editInternalOrgan() {
		secrecyOffice = secrecyOfficeModuleService.get(secrecyOffice.getSecrecyOfficeId());
		List<InternalOrgan> sortInternalOrganList = new ArrayList<InternalOrgan>(secrecyOffice.getInternalOrganSet());
		Collections.sort(sortInternalOrganList, new Comparator<InternalOrgan>() {
			@Override
			public int compare(InternalOrgan o1, InternalOrgan o2) {
				if (o1.getSort() > o2.getSort()) {
					return 1;
				} else {
					return -1;
				}
			}
		});
		putToRequest("sortInternalOrganList", sortInternalOrganList);
		List<DictionaryOption> dictionaryOptionList = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "internalOrgan");
		putToRequest("dictionaryOptionList", dictionaryOptionList);
		List<DictionaryOption> adminLevelList = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "organ_admin_level");
		putToRequest("adminLevelList", adminLevelList);
		return SUCCESS;
	}

	/**
	 *
	 * <p>
	 * 新增、编辑内设机构
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-7-16 - 上午10:51:25
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @return
	 */
	public String editInternalOrganing() {
		secrecyOfficeModuleService.saveInternalOrgan(secrecyOffice, internalOrganList);
		addActionMessage(getMessageConstant().getSaveSuccess());
		return redirectActionResult(EDIT);
	}

	/**
	 *
	 * <p>
	 * 新增、编辑基础设施建设
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-7-16 - 上午10:51:25
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @return
	 */
	public String editInfrastructure() {
		secrecyOffice = secrecyOfficeModuleService.get(secrecyOffice.getSecrecyOfficeId());
		List<DictionaryOption> infrastructureTypeList = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "infrastructure");
		putToRequest("infrastructureTypeList", infrastructureTypeList);
		return SUCCESS;
	}

	/**
	 *
	 * <p>
	 * 新增、编辑基础设施建设
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-7-16 - 上午10:51:25
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @return
	 */
	public String editInfrastructureing() {
		secrecyOfficeModuleService.saveInfrastructure(secrecyOffice, infrastructureList);
		addActionMessage(getMessageConstant().getSaveSuccess());
		return redirectActionResult(EDIT);
	}


	/**
	 *
	 * <p>
	 * 上报
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-3-19 - 下午5:16:28
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @return
	 */
	public String report(){
		try{
			if(secrecyOfficeIds != null && !"".equals(secrecyOfficeIds)) {
				// 查询需要上报的数据 domainList
				List<SecrecyOffice> domainList = new ArrayList<SecrecyOffice>();
				String [] partIdArray =  secrecyOfficeIds.split(",");
				for (String sid : partIdArray) {
					SecrecyOffice so = secrecyOfficeModuleService.get(sid);
					so.setReportState(ReportState.REPORT_YES);
					secrecyOfficeModuleService.update(so);
					domainList.add(so);
				}
				// 查询上报单位
				List<String> organIdList = serverReportMappingService.getReportToOragans(getCurrentUser().getOrgan());
				Iterator<String> i = organIdList.iterator();
				while (i.hasNext()) {
					String organId = i.next();
					//组织需要上报内容
					for (String secrecyOfficeId : partIdArray) {
						SecrecyOfficeDto secrecyOfficeDto = new SecrecyOfficeDto();
						List<String> querySqls = new ArrayList<String>();
						querySqls.add("select * from bm_secrecy_office where SECRECY_OFFICE_ID = '" + secrecyOfficeId + "'");
						//因为接收单位并没有同步，所以需要上报。
						querySqls.add("select * from sys_organization where organ_id = '" + getCurrentUser().getOrgan().getOrganId() + "'");
						//单位下所有子部门，因为接收单位并没有同步，所以需要上报。
						querySqls.add("select * from sys_department where organ_id = '" + getCurrentUser().getOrgan().getOrganId() + "'");
						//单位下所有人员，因为要害部门负责人需要，所以需要上报。
						querySqls.add("select * from sys_user_info where organ_id = '" + getCurrentUser().getOrgan().getOrganId() + "'");
						//保密办成员列表
						querySqls.add("select * from bm_secrecy_office_members where SECRECY_OFFICE_ID = '" + secrecyOfficeId + "'");
						XmlExportor exportor = new XmlExportor();
						exportor.setDataSource(dataSource);
						StringWriter writer = new StringWriter();
						exportor.exportData(writer , querySqls.toArray(new String[]{}));
						secrecyOfficeDto.setReportData(writer.toString());
						secrecyOfficeDto.setReceiveOrganId(organId);
						secrecyOfficeDto.setSecrecyOfficeId(secrecyOfficeId);
						// 发送数据
						secrecyOfficeExchanger.exchange(secrecyOfficeDto, getCurrentUser().getOrgan()
								, OrganizationContext.getInstance().getOrganService().get(organId));
					}
				}
			}
			addActionMessage("上报成功！");
		}catch (Exception e) {
			addActionMessage("上报失败！" + e.getMessage());
		}
		return redirectActionResult(DETAIL);
	}

	// ******************** Setter & Getter ********************
	public SecrecyOfficeModuleService getSecrecyOfficeModuleService() {
		return secrecyOfficeModuleService;
	}

	public void setSecrecyOfficeModuleService(
			SecrecyOfficeModuleService secrecyOfficeModuleService) {
		this.secrecyOfficeModuleService = secrecyOfficeModuleService;
	}

	public SecrecyCommitteeModuleService getSecrecyCommitteeModuleService() {
		return secrecyCommitteeModuleService;
	}

	public void setSecrecyCommitteeModuleService(
			SecrecyCommitteeModuleService secrecyCommitteeModuleService) {
		this.secrecyCommitteeModuleService = secrecyCommitteeModuleService;
	}

	public SecrecyOfficeMemberModuleService getSecrecyOfficeMemberModuleService() {
		return secrecyOfficeMemberModuleService;
	}

	public void setSecrecyOfficeMemberModuleService(
			SecrecyOfficeMemberModuleService secrecyOfficeMemberModuleService) {
		this.secrecyOfficeMemberModuleService = secrecyOfficeMemberModuleService;
	}

	public SecrecyOffice getSecrecyOffice() {
		return secrecyOffice;
	}

	public void setSecrecyOffice(SecrecyOffice secrecyOffice) {
		this.secrecyOffice = secrecyOffice;
	}

	public List<SecrecyOffice> getSecrecyOfficeList() {
		return secrecyOfficeList;
	}

	public void setSecrecyOfficeList(List<SecrecyOffice> secrecyOfficeList) {
		this.secrecyOfficeList = secrecyOfficeList;
	}

	public List<SecrecyOfficeMember> getSecrecyOfficeMemberList() {
		return secrecyOfficeMemberList;
	}

	public void setSecrecyOfficeMemberList(
			List<SecrecyOfficeMember> secrecyOfficeMemberList) {
		this.secrecyOfficeMemberList = secrecyOfficeMemberList;
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
	 * @return 返回organ
	 */
	public Organ getOrgan() {
		return organ;
	}

	/**
	 * @param organ 设置organ
	 */
	public void setOrgan(Organ organ) {
		this.organ = organ;
	}

	/**
	 * @return 返回secrecyOfficeIds
	 */
	public String getSecrecyOfficeIds() {
		return secrecyOfficeIds;
	}

	/**
	 * @param secrecyOfficeIds 设置secrecyOfficeIds
	 */
	public void setSecrecyOfficeIds(String secrecyOfficeIds) {
		this.secrecyOfficeIds = secrecyOfficeIds;
	}

	/**
	 * @param secrecyOfficeExchanger 设置secrecyOfficeExchanger
	 */
	public void setSecrecyOfficeExchanger(
			SecrecyOfficeExchanger secrecyOfficeExchanger) {
		this.secrecyOfficeExchanger = secrecyOfficeExchanger;
	}

	/**
	 * @param serverReportMappingService 设置serverReportMappingService
	 */
	public void setServerReportMappingService(
			ServerReportMappingService serverReportMappingService) {
		this.serverReportMappingService = serverReportMappingService;
	}

	/**
	 * @return 返回isShow
	 */
	public boolean isShow() {
		return isShow;
	}

	/**
	 * @param isShow 设置isShow
	 */
	public void setShow(boolean isShow) {
		this.isShow = isShow;
	}

	/**
	 * @param dataSource 设置dataSource
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * @return 返回employPerson
	 */
	public EmployPerson getEmployPerson() {
		return employPerson;
	}

	/**
	 * @param employPerson 设置employPerson
	 */
	public void setEmployPerson(EmployPerson employPerson) {
		this.employPerson = employPerson;
	}

	/**
	 * @return 返回establishSituation
	 */
	public EstablishSituation getEstablishSituation() {
		return establishSituation;
	}

	/**
	 * @param establishSituation 设置establishSituation
	 */
	public void setEstablishSituation(EstablishSituation establishSituation) {
		this.establishSituation = establishSituation;
	}

	/**
	 * @return 返回leaderStaff
	 */
	public LeaderStaff getLeaderStaff() {
		return leaderStaff;
	}

	/**
	 * @param leaderStaff 设置leaderStaff
	 */
	public void setLeaderStaff(LeaderStaff leaderStaff) {
		this.leaderStaff = leaderStaff;
	}

	/**
	 * @return 返回internalOrgan
	 */
	public InternalOrgan getInternalOrgan() {
		return internalOrgan;
	}

	/**
	 * @param internalOrgan 设置internalOrgan
	 */
	public void setInternalOrgan(InternalOrgan internalOrgan) {
		this.internalOrgan = internalOrgan;
	}

	/**
	 * @return 返回internalOrganList
	 */
	public List<InternalOrgan> getInternalOrganList() {
		return internalOrganList;
	}

	/**
	 * @param internalOrganList 设置internalOrganList
	 */
	public void setInternalOrganList(List<InternalOrgan> internalOrganList) {
		this.internalOrganList = internalOrganList;
	}

	/**
	 * @return 返回infrastructureList
	 */
	public List<Infrastructure> getInfrastructureList() {
		return infrastructureList;
	}

	/**
	 * @param infrastructureList 设置infrastructureList
	 */
	public void setInfrastructureList(List<Infrastructure> infrastructureList) {
		this.infrastructureList = infrastructureList;
	}

}