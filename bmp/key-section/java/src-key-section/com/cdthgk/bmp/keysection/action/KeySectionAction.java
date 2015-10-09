package com.cdthgk.bmp.keysection.action;

import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import com.cdthgk.agenda.vo.Agenda;
import com.cdthgk.bmp.core.action.BmpAction;
import com.cdthgk.bmp.keyPart.service.PartModuleService;
import com.cdthgk.bmp.keyPart.service.PartService;
import com.cdthgk.bmp.keyPart.vo.Part;
import com.cdthgk.bmp.keysection.dto.KeySectionDto;
import com.cdthgk.bmp.keysection.dto.KeySectionStatDto;
import com.cdthgk.bmp.keysection.qo.KeySectionQo;
import com.cdthgk.bmp.keysection.service.KeySectionChangeService;
import com.cdthgk.bmp.keysection.service.KeySectionModuleService;
import com.cdthgk.bmp.keysection.service.KeySectionSecrecyClearService;
import com.cdthgk.bmp.keysection.service.KeySectionService;
import com.cdthgk.bmp.keysection.transmitor.exchange.KeySectionExchanger;
import com.cdthgk.bmp.keysection.vo.KeySection;
import com.cdthgk.bmp.keysection.vo.KeySectionChange;
import com.cdthgk.bmp.keysection.vo.KeySectionSecrecyClear;
import com.cdthgk.bmp.log.context.BusinessLogContext;
import com.cdthgk.bmp.log.vo.BusinessLog;
import com.cdthgk.bmp.secrecycountryitem.qo.ZongHeTongJiStatDto;
import com.cdthgk.bmp.secrecyperson.service.SecrecyPersonModuleService;
import com.cdthgk.bmp.secrecyperson.vo.SecrecyPerson;
import com.cdthgk.bmp.statinfo.dto.QueryDto;
import com.cdthgk.common.bean.BeanUtils;
import com.cdthgk.common.bean.rule.CopyRuleEnum;
import com.cdthgk.common.db.data.xml.XmlExportor;
import com.cdthgk.common.lang.CollectionUtils;
import com.cdthgk.common.lang.LangUtils;
import com.cdthgk.common.lang.StringUtils;
import com.cdthgk.exchange.serverorganmapping.service.ServerReportMappingService;
import com.cdthgk.platform.attachment.service.AttachmentService;
import com.cdthgk.platform.base.util.ReportState;
import com.cdthgk.platform.dataValidate.service.DataValidateService;
import com.cdthgk.platform.dictionary.context.DictionaryContext;
import com.cdthgk.platform.dictionary.domain.DictionaryOption;
import com.cdthgk.platform.dictionary.spi.DictionaryService;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.district.service.DistrictService;
import com.cdthgk.platform.organization.core.OrganizationContext;
import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.organ.service.OrganService;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

/**
 * <p>
 * 要害部门Action类
 * </p>
 * <p>
 * 王彭波 2012-12-12 17:01
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
 * @author wangpb
 * @since 1.0
 * @version 1.0
 */
public class KeySectionAction extends BmpAction{

	private static final long serialVersionUID = -5567519755267324406L;

	private KeySection keySection;
	private KeySectionQo keySectionQo;
	private List<KeySection> keySectionlist;
	private KeySectionModuleService keySectionModuleService;
	private PartModuleService partModuleService;
	private String keySectionIds;
	private Boolean needReload = false;
	private AttachmentService attachmentService;
	private List<String> secAttach;
	private SecrecyPersonModuleService secrecyPersonModuleService;
	private DataValidateService dataValidateService;
	private String departmentName;
	private String addOrEditSearch;
	private String showType = "0";

	private KeySectionExchanger keySectionExchanger;
	private ServerReportMappingService serverReportMappingService;

	private DataSource dataSource;

	private Integer secrecyLevel;

	/**
	 * struts2的DomainModel注入
	 */
	//要害部门密级变更对象
	KeySectionChange keySectionChange;
	//要害部门解密对象
	KeySectionSecrecyClear keySectionSecrecyClear;
	//行政区划
	private District district;
	private OrganService organService;



	/**
	 *   spring注入
	 */
	//保密要害部门密级变更    服务层对象
	KeySectionChangeService keySectionChangeService;
	//要害部门解密    服务层对象
	KeySectionSecrecyClearService keySectionSecrecyClearService;
	//要害部门外部服务
	KeySectionService keySectionService;
	//要害部位的服务层
	private PartService partService;
	//行政区划服务层
	private DistrictService districtService;

	// *********************** 方 法 ***********************


	// 构造器
	/** 默认构造器 */
	public KeySectionAction() {
	}

	/**
	 * <p>
	 * 要害部门列表
	 * </p>
	 * <p>
	 * 王彭波 创建时间 2012-12-14 17:25
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
	 * @author wangpb
	 * @version 1.0
	 * @throws ParseException parseException
	 * @return success
	 */
	@SuppressWarnings("rawtypes")
	public String list() throws ParseException {

		List<KeySection> keySectionlist = new ArrayList<KeySection>();
		PageSortModel psm = new PageSortModel(getRequest(), "keySectionlist");
		String ywFlag = this.getRequest().getParameter("ywFlag");//标志的传递  业务标志  1表示查询   0表示普通业务模块
		if(ywFlag==null) {
			ywFlag="0";
		}
		this.putToRequest("ywFlag", ywFlag);//标志的传递  业务标志  1表示查询   0表示普通业务模块

		if(ywFlag.equals("1")) {//查询页面 按照行政区划   来查
			//行政区划编码
			String districtCode = this.getRequest().getParameter("districtCode");//行政区划编码
			if (StringUtils.isEmpty(districtCode)) {
				district = getCurrentUser().getOrgan().getDistrict();
			} else {
				district = districtService.get(districtCode);
			}
			this.putToRequest("district", district);

			//是否包含下级
			String strChildren = this.getRequest().getParameter("isChildren");//行政区划编码
			int isChildren = 0;
			if(strChildren!=null && !"".equals(strChildren)) {
				isChildren = Integer.parseInt(strChildren);
			}
			this.putToRequest("isChildren", isChildren);
			//查询数据
			keySectionlist = keySectionModuleService.queryList(psm, district,isChildren, keySectionQo);
		}else if(ywFlag.equals("0")){ //普通页面按照  单位来查询
			Organ organ = this.getCurrentUser().getOrgan();
			keySectionlist = keySectionModuleService.queryList(psm, organ, keySectionQo);
		}
		this.putToRequest("keySectionlist", keySectionlist);

		return SUCCESS;
	}

	/**
	 * 单独   的保密要害部门列表
	 * @return
	 */
	public String list_list() {

		List<KeySection> keySectionlist = new ArrayList<KeySection>();
		PageSortModel psm = new PageSortModel(getRequest(), "keySectionlist");
		String ywFlag = this.getRequest().getParameter("ywFlag");//标志的传递  业务标志  1表示查询   0表示普通业务模块
		if(ywFlag==null) {
			ywFlag="0";
		}
		this.putToRequest("ywFlag", ywFlag);//标志的传递  业务标志  1表示查询   0表示普通业务模块

		if(ywFlag.equals("1")) {//查询页面 按照行政区划   来查
			//行政区划编码
			String districtCode = this.getRequest().getParameter("districtCode");//行政区划编码
			if (StringUtils.isEmpty(districtCode)) {
				district = getCurrentUser().getOrgan().getDistrict();
			} else {
				district = districtService.get(districtCode);
			}
			this.putToRequest("district", district);

			//是否包含下级
			String strChildren = this.getRequest().getParameter("isChildren");//行政区划编码
			int isChildren = 0;
			if(strChildren!=null && !"".equals(strChildren)) {
				isChildren = Integer.parseInt(strChildren);
			}
			this.putToRequest("isChildren", isChildren);

			//查询
			keySectionlist = keySectionModuleService.queryList(psm, district,isChildren, keySectionQo);
		}else if(ywFlag.equals("0")){ //普通页面按照  单位来查询
			String organId = getRequest().getParameter("organ.organId");
			Organ organ = new Organ();
			if(organId==null) {
				organ = this.getCurrentUser().getOrgan();
			}else{
				organ = organService.get(organId);
			}
			putToRequest("dataGetFlag", this.getRequest().getParameter("dataGetFlag"));//数据撰取标志 用于综合统计
			putToRequest("organ", organ);
			keySectionlist = keySectionModuleService.queryList(psm, organ, keySectionQo);
		}
		this.putToRequest("keySectionlist", keySectionlist);

		return SUCCESS;
	}


	/**
	 * 密级变更列表
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String list_change() {

		List<KeySectionChange> keySection_changelist = new ArrayList<KeySectionChange>();
		PageSortModel psm = new PageSortModel(getRequest(), "keySection_changelist");
		String ywFlag = this.getRequest().getParameter("ywFlag");//标志的传递  业务标志  1表示查询   0表示普通业务模块
		if(ywFlag==null) {
			ywFlag="0";
		}
		this.putToRequest("ywFlag", ywFlag);

		if(ywFlag.equals("1")) {//查询页面 按照行政区划   来查
			//行政区划编码
			String districtCode = this.getRequest().getParameter("districtCode");//行政区划编码
			if (StringUtils.isEmpty(districtCode)) {
				district = getCurrentUser().getOrgan().getDistrict();
			} else {
				district = districtService.get(districtCode);
			}
			this.putToRequest("district", district);
			//是否包含下级
			String strChildren = this.getRequest().getParameter("isChildren");//行政区划编码
			int isChildren = 0;
			if(strChildren!=null && !"".equals(strChildren)) {
				isChildren = Integer.parseInt(strChildren);
			}
			this.putToRequest("isChildren", isChildren);
			//查询
			keySection_changelist = keySectionChangeService.queryKeySectionChangeList(psm, keySectionChange, null, district,isChildren);//变更的历史记录
		}else if(ywFlag.equals("0")){ //普通页面按照  单位来查询
			Organ organ = this.getCurrentUser().getOrgan();
			keySection_changelist = keySectionChangeService.queryKeySectionChangeList(psm, keySectionChange, organ, null,null);//变更的历史记录
		}

		putToRequest("keySection_changelist", keySection_changelist);
		return SUCCESS;
	}

	/**
	 * 密级解除列表
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String list_clear() {

		List<KeySectionSecrecyClear> keySection_clearlist = new ArrayList<KeySectionSecrecyClear>();
		PageSortModel psm = new PageSortModel(getRequest(), "keySection_clearlist");
		String ywFlag = this.getRequest().getParameter("ywFlag");//标志的传递  业务标志  1表示查询   0表示普通业务模块
		if(ywFlag==null) {
			ywFlag="0";
		}
		this.putToRequest("ywFlag", ywFlag);

		if(ywFlag.equals("1")) {//查询页面  按照行政区划  查询
			//行政区划编码
			String districtCode = this.getRequest().getParameter("districtCode");//行政区划编码
			if (StringUtils.isEmpty(districtCode)) {
				district = getCurrentUser().getOrgan().getDistrict();
			} else {
				district = districtService.get(districtCode);
			}
			this.putToRequest("district", district);
			//是否包含下级
			String strChildren = this.getRequest().getParameter("isChildren");//行政区划编码
			int isChildren = 0;
			if(strChildren!=null && !"".equals(strChildren)) {
				isChildren = Integer.parseInt(strChildren);
			}
			this.putToRequest("isChildren", isChildren);
			//查询
			keySection_clearlist = keySectionSecrecyClearService.queryKeySectionClearList(psm, keySectionSecrecyClear, null, district,isChildren);//解除记录
		}else if(ywFlag.equals("0")) {//普通业务模块  按照  单位查询
			Organ organ = this.getCurrentUser().getOrgan();
			keySection_clearlist = keySectionSecrecyClearService.queryKeySectionClearList(psm, keySectionSecrecyClear, organ,null,null);//解除记录
		}

		putToRequest("keySection_clearlist", keySection_clearlist);
		return SUCCESS;
	}


	public String exportData() throws ParseException {
		Map<String, Object> params = new HashMap<String, Object>();
		Organ organ = getCurrentUser().getOrgan();
		keySectionlist = keySectionModuleService.queryList(organ, keySectionQo);

		List<DictionaryOption> secrecyLevelList = this.keySectionModuleService.getDictionaryOptionList("bmp", "secrecy_level_section");
		params.put("secrecyLevelList", secrecyLevelList);
		params.put("keySectionlist", keySectionlist);
		setResultData(params);
		return SUCCESS;
	}
	/**
	 * <p>
	 * 要害部门列表（首页获取要害部门列表）
	 * </p>
	 * <p>
	 * 宋亚非 创建时间 2012-12-14 17:25
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
	 * @author wangpb
	 * @version 1.0
	 * @throws ParseException parseException
	 * @return success
	 */
	public String allList() throws ParseException {
		@SuppressWarnings("rawtypes")
		PageSortModel psm = new PageSortModel(getRequest(), "secrecyPersonList");

		Organ organ = getCurrentUser().getOrgan();

		keySectionlist = keySectionModuleService.queryList(psm, organ, keySectionQo, showType);

		putToRequest("keySectionList", keySectionlist);

		return SUCCESS;
	}


	/**
	 * 主页要害部门统计
	 *
	 * @return 物理视图
	 */
	public String indexView() {
		Organ organ = getCurrentUser().getOrgan();
		List<Long> keySectionArgs =  keySectionModuleService.countSectionData(organ);
		putToRequest("keySectionArgs", keySectionArgs);
		putToRequest("organ", organ);
		return SUCCESS;
	}

	/**
	 * <p>
	 * 首页要害部门单位统计撰取
	 * </p>
	 * <p>刘椿成
	 * 创建时间 ?
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>宋亚非  2013-04-09 从市平台移植到省平台上
	 * </ul>
	 * </blockquote>
	 * <p>
	 * copyright tianyu 2012, all rights reserved.
	 * </p>
	 *
	 * @author wangpb
	 * @version 1.0
	 * @return success
	 */
	public String organSectionData(){

		String organId =  getRequest().getParameter("organId"); //单位id
		String secrecy_level = getRequest().getParameter("secrecyLevel");//密级

		//设置单位
		Organ organ = null;
		if(organId==null || organId.equals("")) {
			organ = getCurrentUser().getOrgan();
		}else {
			organ = new Organ();
			organ.setOrganId(organId);
		}

		//设置密级
		if(keySectionQo==null) {
			keySectionQo = new KeySectionQo();
		}
		if(secrecy_level!=null && !"".equals(secrecy_level)) {
			if(!secrecy_level.equals("-1")) {
				keySectionQo.setSecrecyLevel(Integer.parseInt(secrecy_level));
				if(secrecy_level.equals("1")) {
					putToRequest("secrecy_level_name", "核心");
				}else if(secrecy_level.equals("2")){
					putToRequest("secrecy_level_name", "重要");
				}else if(secrecy_level.equals("3")){
					putToRequest("secrecy_level_name", "一般");
				}
			}else {
				putToRequest("secrecy_level_name", "全部");
			}
		}

		PageSortModel psm = new PageSortModel(getRequest(), "keySectionlist");
		keySectionlist = keySectionModuleService.queryList(psm, organ, keySectionQo);
		this.putToRequest("keySectionlist", keySectionlist);

		return "organSectionData";
	}

	/**
	 * 主页要害部门统计  保密局
	 *
	 * @return 物理视图
	 */
	public String indexView_District() {
		//行政区
		District district =  getCurrentUser().getOrgan().getDistrict();

		List<Long> keySectionArgs =  keySectionModuleService.countSectionData(district);
		putToRequest("keySectionArgs", keySectionArgs);
		putToRequest("district", district);
		return SUCCESS;
	}

	/**
	 * 首页  统计    保密局明细查看
	 * @return
	 */
	public String indexView_SectionData_District(){

		String layer =  getRequest().getParameter("layer"); //单位id
		String secrecy_level = getRequest().getParameter("secrecyLevel");//密级

		//设置行政区划
		District district = null;
		if(layer==null || layer.equals("")) {
			district = getCurrentUser().getOrgan().getDistrict();
		}else {
			district = new District();
			district.setLayer(layer);
		}

		//设置密级
		if(keySectionQo==null) {
			keySectionQo = new KeySectionQo();
		}
		if(secrecy_level!=null && !"".equals(secrecy_level)) {
			if(!secrecy_level.equals("-1")) {
				keySectionQo.setSecrecyLevel(Integer.parseInt(secrecy_level));
				if(secrecy_level.equals("1")) {
					putToRequest("secrecy_level_name", "核心");
				}else if(secrecy_level.equals("2")){
					putToRequest("secrecy_level_name", "重要");
				}else if(secrecy_level.equals("3")){
					putToRequest("secrecy_level_name", "一般");
				}
			}else {
				putToRequest("secrecy_level_name", "全部");
			}
		}

		PageSortModel psm = new PageSortModel(getRequest(), "keySectionlist");
		keySectionlist = keySectionModuleService.queryList(psm, district,1, keySectionQo);//包含下级的  行政区划的查询
		this.putToRequest("keySectionlist", keySectionlist);

		return "organSectionData";
	}


	/**
	 * <p>
	 * 要害部门新增页面
	 * </p>
	 * <p>
	 * 创建时间 2012-12-15 17:25
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
	 * @author wangpb
	 * @version 1.0
	 * @return success
	 */
	public String add() {
		putToRequest("organ", getCurrentUser().getOrgan());
		return SUCCESS;

	}

	/**
	 * <p>
	 * 要害部门新增操作
	 * </p>
	 * <p>
	 * 创建时间 2012-12-15 17:25
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
	 * @author wangpb
	 * @version 1.0
	 * @return success
	 */
	public String adding() {

		User user = getCurrentUser();
		Organ organ = keySectionModuleService.get(keySection.getOrgan().getOrganId(), Organ.class);
		keySection.setCreatePerson(getCurrentUser());
		keySection.setCreateTime(new Date());
		keySection.setOrgan(organ);

		//设置部门
		if (keySection.getDepartment() != null && LangUtils.isNotEmpty(
				keySection.getDepartment().getDepartmentId())) {
			keySection.setDepartment(keySectionModuleService.get(
					keySection.getDepartment().getDepartmentId(), Department.class));
		} else if (keySection.getDepartment() != null && LangUtils.isNotEmpty(
				keySection.getDepartment().getDepartmentName())) {
			Department department = keySectionModuleService.saveDepartment(
					keySection.getDepartment().getDepartmentName(), user);
			keySection.setDepartment(department);
		}

		//设置负责人
		if (keySection.getPrincipal() != null && LangUtils.isNotEmpty(
				keySection.getPrincipal().getUserInfoId())) {
			keySection.setPrincipal(keySectionModuleService.get(
					keySection.getPrincipal().getUserInfoId(), UserInfo.class));
		} else if (keySection.getPrincipal() != null && LangUtils.isNotEmpty(
				keySection.getPrincipal().getName())) {
			keySection.getPrincipal().setDepartment(keySection.getDepartment());
			UserInfo userInfo = keySectionModuleService.saveUserInfo(
					keySection.getPrincipal(), user);
			keySection.setPrincipal(userInfo);

		}
		//设置解密状态默认为0
		keySection.setSecrecyStatus(0);


		//修改人：宋亚非   修改原因：数据库统一至一个库后，取消上报功能，填写信息后状态即为已上报   修改时间：2013-06-03
		//keySection.setReportState(ReportState.REPORT_NO);
		keySection.setReportState(ReportState.REPORT_YES);
		keySectionModuleService.save(keySection);

		attachmentService.updateHostId(keySection.getKeySectionId(), secAttach);

                BusinessLog log = new BusinessLog();
                log.setBusinessName("保密要害部门 ");
                log.setPrimaryKey(keySection.getKeySectionId());
                BusinessLogContext.getInstance().getBusinessLogService().saveAddBusinessLogByModule(getCurrentUser(), log, keySection);

		// 设置信息
		addActionMessage(getMessageConstant().getSaveSuccess()+ "请继续添加该要害部门下的要害部位和涉密人员信息！");
		needReload = true;
		//return "success";
		return redirectActionResult("add");
	}

	/**
	 * <p>
	 * 要害部门编辑页面
	 * </p>
	 * <p>
	 * 创建时间 2012-12-15 17:35
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
	 * @author wangpb
	 * @version 1.0
	 * @throws ParseException parseException
	 * @return success
	 */
	public String edit() throws ParseException {
		if (keySection != null && LangUtils.isNotEmpty(keySection.getKeySectionId())) {

			keySection = keySectionModuleService.get(keySection.getKeySectionId());
			putToRequest("attachments", attachmentService.getAttachmentsByHostId(
					keySection.getKeySectionId()));
			putToRequest("secPersonNum", secrecyPersonModuleService.getSecrecyPersonByDepartment(
					keySection.getDepartment().getDepartmentId(), keySection.getOrgan()).size());

			return SUCCESS;
		} else {
			this.addActionMessage("请选择保密要害部门！");
			return "list";
		}
	}

	/**
	 * <p>
	 * 要害部门编辑操作
	 * </p>
	 * <p>
	 * 创建时间 2012-12-15 17:38
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
	 * @author wangpb
	 * @version 1.0
	 * @return success
	 */
	public String editing() {
		User user = getCurrentUser();
		KeySection k = keySectionModuleService.get(keySection.getKeySectionId());

		KeySection ksBefore = new KeySection();
	        BeanUtils.copyProperties(ksBefore, k, CopyRuleEnum.ignoreCaseNull);

		//设置部门
		if (keySection.getDepartment() != null && LangUtils.isNotEmpty(
				keySection.getDepartment().getDepartmentId())) {
			k.setDepartment(keySectionModuleService.get(
					keySection.getDepartment().getDepartmentId(), Department.class));
		} else if (keySection.getDepartment() != null && LangUtils.isNotEmpty(
				keySection.getDepartment().getDepartmentName())) {
			Department department = keySectionModuleService.saveDepartment(
					keySection.getDepartment().getDepartmentName(), user);
			k.setDepartment(department);
		}

		//设置负责人
		if (keySection.getPrincipal() != null && LangUtils.isNotEmpty(
				keySection.getPrincipal().getUserInfoId())) {
			k.setPrincipal(keySectionModuleService.get(
					keySection.getPrincipal().getUserInfoId(), UserInfo.class));
		} else if (keySection.getPrincipal() != null && LangUtils.isNotEmpty(
				keySection.getPrincipal().getName())) {
			keySection.getPrincipal().setDepartment(k.getDepartment());
			UserInfo userInfo = keySectionModuleService.saveUserInfo(
					keySection.getPrincipal(), user);
			k.setPrincipal(userInfo);
		}
		k.setPhone(keySection.getPhone());
		k.setSecScope(keySection.getSecScope());
		k.setSecrecyLevel(keySection.getSecrecyLevel());
		k.setPersonNum(keySection.getPersonNum());
		k.setModifyTime(new Date());
		k.setModifyPerson(getCurrentUser());
		//修改人：宋亚非   修改原因：数据库统一至一个库后，取消上报功能，不再存在上报后修改状态   修改时间：2013-06-03
		/*if(k.getReportState() > 0) {
			k.setReportState(ReportState.REPORT_YES_MODIFY);
		}*/
		keySectionModuleService.update(k);
		attachmentService.updateHostId(keySection.getKeySectionId(), secAttach);
		addActionMessage(getMessageConstant().getUpdateSuccess());

                BusinessLog log = new BusinessLog();
                log.setBusinessName("保密要害部门");
                log.setPrimaryKey(k.getKeySectionId());
                BusinessLogContext.getInstance().getBusinessLogService().saveEditBusinessLogByModule(getCurrentUser(), log, k, ksBefore);

		keySection = k;
		needReload = true;
		return "list";
	}

	/**
	 * <p>
	 * 要害部门删除操作
	 * </p>
	 * <p>
	 * 创建时间 2012-12-15 17:58
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
	 * @author wangpb
	 * @version 1.0
	 * @return success
	 */
	public String delete() {
		if (keySectionIds != null && !"".equals(keySectionIds)) {
		        for( String  id : keySectionIds.split(",") ){
		                KeySection ks = keySectionModuleService.get(id);
		                if( ks!=null ){
		                        BusinessLog log = new BusinessLog();
		                        log.setBusinessName("保密要害部门");
		                        log.setPrimaryKey(id);
		                        BusinessLogContext.getInstance().getBusinessLogService().saveDelBusinessLogByModule(getCurrentUser(), log, ks);
		                }
		        }
			keySectionModuleService.deleteBatchIds(keySectionIds);
		}
		addActionMessage(getMessageConstant().getDeleteSuccess());
		return "list";
	}

	/**
	 * <p>
	 * 要害部门查询操作
	 * </p>
	 * <p>
	 * 创建时间 2012-12-15 17:58
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
	 * @author wangpb
	 * @version 1.0
	 * @return success
	 */
	public String query() {
		@SuppressWarnings("rawtypes")
		PageSortModel psm = new PageSortModel(getRequest(), "secrecyPersonList");

		Organ organ = getCurrentUser().getOrgan();

		keySectionlist = keySectionModuleService.queryList(psm, organ, keySectionQo);
		putToRequest("keySectionList", keySectionlist);

		return "list";
	}

	/**
	 * <p>
	 * 要害部门详情
	 * </p>
	 * <p>
	 * 创建时间 2012-12-15 17:38
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
	 * @author wangpb
	 * @version 1.0
	 * @return success
	 */
	public String detail() {
		putToRequest("attachments", attachmentService.getAttachmentsByHostId(keySection.getKeySectionId()));
		keySection = keySectionModuleService.get(keySection.getKeySectionId());
		//保密要害部门变更
		if(keySectionChange==null) {
			keySectionChange = new KeySectionChange();
		}
		keySectionChange.setKeySectionId(keySection);
		List<KeySectionChange> keySection_changelist = keySectionChangeService.queryKeySectionChangeList(null, keySectionChange, this.getCurrentUser().getOrgan(),null,null);//变更的历史记录


		putToRequest("keySection_changelist", keySection_changelist);
		putToRequest("keySection", keySection);
		putToRequest("secPersonNum", secrecyPersonModuleService.getSecrecyPersonByDepartment(
				keySection.getDepartment().getDepartmentId(), keySection.getOrgan()).size());
		return SUCCESS;
	}

	/**
	 * <p>
	 * 查询部门
	 * </p>
	 * <p>
	 * 创建时间 2012-12-16 09:38
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
	 * @author wangpb
	 * @version 1.0
	 * @return success
	 */
	public String searchDepartment() {
		// 检查
		Organ organ = getCurrentUser().getOrgan();
		int count = keySectionModuleService.searchDepartment(departmentName, organ);
		// 删除
		Map<String, String> resultMap = new HashMap<String, String>();
		if (count > 0) {
			if (addOrEditSearch.equals("add")) {
				resultMap.put("message", "当前【" + departmentName + "】部门已经是保密要害部门" +
						"，请重新选择，如需修改，请返回列表对该保密要害部门进行编辑");
			} else {
				resultMap.put("message", "所要更换【" + departmentName + "】保密要害部门信息已存在" +
						"，请重新选择，如需修改，请返回列表对该保密要害部门进行编辑");
			}
			resultMap.put("success", null);
		} else {
			resultMap.put("message", null);
			resultMap.put("success", "true");
		}
		// 将设定值返回给页面
		this.setResultData(resultMap);
		return JSON;
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
			if(keySectionIds != null && !"".equals(keySectionIds)) {
				// 查询需要上报的数据
				String [] keySectionIdArray =  keySectionIds.split(",");
				for (String sid : keySectionIdArray) {
					KeySection section = keySectionModuleService.get(sid);
					section.setReportState(ReportState.REPORT_YES);
					//要害部门下部位状态更改
					List<Part> partList = (List<Part>) partModuleService.getListBySection(getCurrentUser(), section);
					for (Part part : partList) {
						part.setReportState(ReportState.REPORT_YES);
						partModuleService.update(part);
					}
					//要害部门下的涉密人员状态更改
					List<SecrecyPerson> secrecyPersonList = secrecyPersonModuleService.getSecrecyPersonByDepartment(
							section.getDepartment().getDepartmentId()
							, section.getOrgan());
					for (SecrecyPerson secrecyPerson : secrecyPersonList) {
						secrecyPerson.setReportState(ReportState.REPORT_YES);
						secrecyPersonModuleService.update(secrecyPerson);
					}
					//要害部门状态更改
					keySectionModuleService.update(section);
				}
				// 查询上报单位
				List<String> organIdList = serverReportMappingService.getReportToOragans(getCurrentUser().getOrgan());
				Iterator<String> i = organIdList.iterator();
				while (i.hasNext()) {
					String organId = i.next();
					//组织需要上报内容
					KeySectionDto keySectionDto = new KeySectionDto();
					List<String> querySqls = new ArrayList<String>();
					for (String keySectionId : keySectionIdArray) {
						KeySection section = keySectionModuleService.get(keySectionId);
						String departmentId = section.getDepartment().getDepartmentId();
						String oId = section.getOrgan().getOrganId();
						querySqls.add("select * from bm_key_section where key_section_id = '" + keySectionId + "'");
						//因为接收单位并没有同步，所以需要上报。
						querySqls.add("select * from sys_organization where organ_id = '" + oId + "'");
						//单位下所有子部门，因为接收单位并没有同步，所以需要上报。
						querySqls.add("select * from sys_department where organ_id = '" + oId + "'");
						//单位下所有人员，因为要害部门负责人需要，所以需要上报。
						querySqls.add("select * from sys_user_info where organ_id = '" + oId + "'");
						//要害部门附件需要，所以需要上报。
						querySqls.add("select * from base_attachment where host_id = '" + section.getKeySectionId() + "'");
						//要害部门下的部位。
						querySqls.add("select * from bm_key_part where department_id='" + departmentId + "'");
						//要害部门下的涉密人员。
						querySqls.add("select * from bm_secrecy_person where department_id='" + departmentId + "'");

						//部位附件列表
						List<String> partListId = new ArrayList<String>();
						List<Part> partList = (List<Part>) partModuleService.getListBySection(getCurrentUser(), section);
						if (CollectionUtils.isNotEmpty(partList)) {
							for (Part part : partList) {
								//部位附件需要，所以需要上报。
								querySqls.add("select * from base_attachment where host_id = '" + part.getPartId() + "'");
								//要害部门下的部位的人员中间表。。
								querySqls.add("select * from bm_key_part_person where KEY_PART_ID='" + part.getPartId() + "'");
								partListId.add(part.getPartId());
							}
						}
						XmlExportor exportor = new XmlExportor();
						exportor.setDataSource(dataSource);
						StringWriter writer = new StringWriter();
						exportor.exportData(writer , querySqls.toArray(new String[]{}));
						keySectionDto.setReportData(writer.toString());
						keySectionDto.setReceiveOrganId(organId);
						keySectionDto.setSectionId(keySectionId);
						keySectionDto.setPartListId(partListId);
						// 发送数据
						keySectionExchanger.exchange(keySectionDto, getCurrentUser().getOrgan()
								, OrganizationContext.getInstance().getOrganService().get(organId));
					}
				}
			}
			addActionMessage("上报成功！");
		}catch (Exception e) {
			e.printStackTrace();
			addActionMessage("上报失败！" + e.getMessage());
		}

		return redirectActionResult(LIST);
	}


	/**
	 * 要害部门的  密级变更
	 * 梁文杰  2013-07-13新增
	 * @return
	 */
	public String secrecyChange() {
		String keySectionId = keySection.getKeySectionId();//得到要害部门id

		//通过要害部门id 得到该要害部门信息
		KeySection obj  = keySectionModuleService.get(keySectionId);

		//构建 密级变更对象
		KeySectionChange keySectionChange = new KeySectionChange();
		keySectionChange.setBeforeLevel(obj.getSecrecyLevel());
		keySectionChange.setKeySectionId(obj);

		putToRequest("keySectionChange", keySectionChange);
		return SUCCESS;
	}

	/**
	 * 要害部门的  密级变更 保存
	 * 梁文杰  2013-07-13新增
	 * @return
	 */
	public String secrecyChangeing() {

		//设置创建人和创建时间
		keySectionChange.setCreatePerson(getCurrentUser());
		keySectionChange.setCreateDate(new Date());

		try{
			//保存要害部门密级变更对象
			KeySectionChange obj = keySectionChangeService.save(keySectionChange);

			//变更密级以后  将要害部门的密级同时变动
			KeySection fk_obj = keySectionModuleService.get(obj.getKeySectionId().getKeySectionId());//获取要害部门
			fk_obj.setSecrecyLevel(obj.getAfterLevel());//设置变更后的密级
			keySectionModuleService.saveOrUpdate(fk_obj);

		}catch(Exception e) {
			e.printStackTrace();
			addActionMessage("要害部门密级变更失败！");
			return SUCCESS;
		}

		addActionMessage("要害部门密级变更成功！");
		putToRequest("reflag", 1);
		needReload = true;
		return SUCCESS;
	}

	/**
	 * 要害部门的 解密
	 * 梁文杰  2013-07-13新增
	 * @return
	 */
	public String secrecyClear() {
		String keySectionId = keySection.getKeySectionId();//得到要害部门id
		//通过要害部门id 得到该要害部门信息
		KeySection obj  = keySectionModuleService.get(keySectionId);

		//构建 解密对象
		KeySectionSecrecyClear keySectionSecrecyClear = new KeySectionSecrecyClear();
		keySectionSecrecyClear.setKeySectionId(obj);//原密级
		putToRequest("keySectionSecrecyClear", keySectionSecrecyClear);
		return SUCCESS;
	}

	/**
	 * 要害部门的 解密  保存
	 * 梁文杰  2013-07-13新增
	 * @return
	 */
	public String secrecyClearing() {

		keySectionSecrecyClear.setCreatePerson(getCurrentUser());
		keySectionSecrecyClear.setCreateDate(new Date());

		try{
			//保存要害部门保存  解密信息
			KeySectionSecrecyClear obj = keySectionSecrecyClearService.save(keySectionSecrecyClear);

			//解除密级以后  将要害部门的密级同时变动
			KeySection fk_obj = keySectionModuleService.get(obj.getKeySectionId().getKeySectionId());//获取要害部门
			//设置解密后的密级 不用设置  暂时保留
			//设置  要害部门表的解密状态的标志
			fk_obj.setSecrecyStatus(1);  //设置成解密

			keySectionModuleService.saveOrUpdate(fk_obj);//更新解密以后的要害部门的信息

		}catch(Exception e) {
			e.printStackTrace();
			addActionMessage("要害部门密级解除失败！");
			return SUCCESS;
		}
		needReload = true;
		addActionMessage("要害部门密级解除成功！");
		return SUCCESS;
	}


	/**
	 * 查询 保密要害部门的密级变更详情
	 * @return
	 */
	public String changeDetail() {
		String id = keySectionChange.getSectionChangeId();
		keySectionChange = keySectionChangeService.get(id);//通过id查询对象
		//要害部门
		keySection = keySectionChange.getKeySectionId();

		this.putToRequest("keySectionChange", keySectionChange);
		this.putToRequest("keySection", keySection);
		putToRequest("attachments", attachmentService.getAttachmentsByHostId(keySection.getKeySectionId()));
		putToRequest("secPersonNum", secrecyPersonModuleService.getSecrecyPersonByDepartment(
				keySection.getDepartment().getDepartmentId(), keySection.getOrgan()).size());
		return SUCCESS;
	}

	/**
	 * 查询 保密要害部门的 密级解除情况   的详情
	 * @return
	 */
	public String clearDetail() {

		String id = keySectionSecrecyClear.getSectionSecrecyClearId();
		keySectionSecrecyClear = keySectionSecrecyClearService.get(id);//通过id查询对象
		//要害部门
		keySection = keySectionSecrecyClear.getKeySectionId();
		//变更记录
		if(keySectionChange==null) {
			keySectionChange = new KeySectionChange();
		}
		keySectionChange.setKeySectionId(keySection);
		List<KeySectionChange>  keySectionChangeList = new ArrayList<KeySectionChange>();
		//业务标志 1查询模块  0普通业务模块
		String ywFlag = this.getRequest().getParameter("ywFlag");
		Organ organ = this.getCurrentUser().getOrgan();
		if(ywFlag!=null && ywFlag.equals("1")){
			organ = keySection.getOrgan();
		}
		keySectionChangeList = keySectionChangeService.queryKeySectionChangeList(null, keySectionChange, organ,null,null);



		putToRequest("attachments", attachmentService.getAttachmentsByHostId(keySection.getKeySectionId()));
		putToRequest("secPersonNum", secrecyPersonModuleService.getSecrecyPersonByDepartment(
				keySection.getDepartment().getDepartmentId(), keySection.getOrgan()).size());
		this.putToRequest("keySectionSecrecyClear", keySectionSecrecyClear);
		this.putToRequest("keySection", keySection);
		this.putToRequest("keySection_changelist", keySectionChangeList);
		return SUCCESS;
	}

	/**
	 * (本单位)
	 * 统计本单位的要害部门的信息
	 * @return
	 */
	public String org_CountKeySectionBySecrecyLevel() {

		String organId = "";

		HttpServletRequest r = this.getRequest();
		if(r.getParameter("organId")==null || r.getParameter("organId").equals("")){
			organId = this.getCurrentUser().getOrgan().getOrganId();//得到本单位的 组织id
		}else {
			organId = r.getParameter("organId");//得到传入的参数   组织id
		}

		//查询本单位的要害部门的统计数据
		List<Map<String, Object>> countSectionList = keySectionService.count_KeySection_By_Secrecy_Level(organId);
		//查询本单位的要害部位的统计数据
		List<Map<String, Object>> countPartList = partService.count_KeyPart_By_Secrecy_Level(organId);

		this.putToRequest("countSectionList", countSectionList);
		this.putToRequest("countPartList", countPartList);
		return SUCCESS;
	}

	/**
	 * (行政区划)
	 * 保密要害部门  统计 保密局  统计(行政区划)的数据
	 * @return
	 */
	public String org_CountKeySectionBySecrecyLevel_District() {
		//处理行政区划
		String districtCode = this.getRequest().getParameter("districtCode");//行政区划编码
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = districtService.get(districtCode);
		}

		//查询保密局  要害部门  统计数据    行政区划
		List<Map<String, Object>> countSectionList = keySectionService.count_KeySection_By_Secrecy_Level_District(district);
		//查询保密局 要害部位  统计数据     行政区划
		List<Map<String, Object>> countPartList = partService.count_KeyPart_By_Secrecy_Level_District(district);

		this.putToRequest("countSectionList", countSectionList);
		this.putToRequest("countPartList", countPartList);
		this.putToRequest("districtCode", district.getDistrictCode());

		return SUCCESS;
	}

	/**
	 * (直辖单位)
	 * 保密局统计 行政区内  直辖单位的    要害部门的统计
	 * @return
	 */
	public String org_CountKeySectionBySecrecyLevel_Layer() {
		//处理行政区划
		String districtCode = this.getRequest().getParameter("districtCode");//行政区划编码
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = districtService.get(districtCode);
		}

		//查询保密局  要害部门  统计数据   行政区内
		List<Map<String, Object>> countSectionList = keySectionService.count_KeySection_By_Secrecy_Level_Layer(district);

		//查询保密局 要害部位  统计数据     行政区内
	    List<Map<String, Object>> countPartList = partService.count_KeyPart_By_Secrecy_Level_Layer(district);

		this.putToRequest("countSectionList", countSectionList);
		this.putToRequest("countPartList", countPartList);
		this.putToRequest("districtCode", district.getDistrictCode());

		return SUCCESS;
	}

	/**
	 * (单位明细)
	 * 保密局统计 行政区内  各个单位的    要害部门的  明细统计
	 * @return
	 */
	public String org_CountKeySectionBySecrecyLevel_OrganDetail() {
		//处理行政区划
		String districtCode = this.getRequest().getParameter("districtCode");//行政区划编码
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = districtService.get(districtCode);
		}

		//查询保密局  要害部门  统计数据   行政区内   各个单位的
		List<Map<String, Object>> countSectionList = keySectionService.count_KeySection_By_Secrecy_Level_OrganDetail(district);
		this.putToRequest("countSectionList", countSectionList);
		this.putToRequest("districtCode", district.getDistrictCode());

		return SUCCESS;
	}


	/**（本单位出）
	 * 导出excel  要害部门的统计
	 * @return
	 */
	public String exportExcel_KeySectionCount() {

		String organId = "";

		HttpServletRequest r = this.getRequest();
		if(r.getParameter("organId")==null || r.getParameter("organId").equals("")){
			organId = this.getCurrentUser().getOrgan().getOrganId();//得到本单位的 组织id
		}else {
			organId = r.getParameter("organId");//得到传入的参数   组织id
		}

		//查询本单位的要害部门的统计数据
		List<Map<String, Object>> countSectionList = keySectionService.count_KeySection_By_Secrecy_Level(organId);
		//查询本单位的要害部位的统计数据
		List<Map<String, Object>> countPartList = partService.count_KeyPart_By_Secrecy_Level(organId);

		Map<String, Object> params = new HashMap<String, Object>();
		//被导出的数据
		params.put("countSectionList", countSectionList);
		params.put("countPartList", countPartList);

		//数据类型转换
		params.put("Integer", Integer.class);
		setResultData(params);

		return SUCCESS;
	}


	/**（行政区划导出）
	 * 导出excel  要害部门的统计   行政区划导出
	 * @return
	 */
	public String exportExcel_KeySectionCount_District() {

		//处理行政区划
		String districtCode = this.getRequest().getParameter("districtCode");//行政区划编码
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = districtService.get(districtCode);
		}

		//查询保密局  要害部门  统计数据    行政区划
		List<Map<String, Object>> countSectionList = keySectionService.count_KeySection_By_Secrecy_Level_District(district);
		//查询保密局 要害部位  统计数据     行政区划
		List<Map<String, Object>> countPartList = partService.count_KeyPart_By_Secrecy_Level_District(district);


		Map<String, Object> params = new HashMap<String, Object>();
		//被导出的数据
		params.put("countSectionList", countSectionList);
		params.put("countPartList", countPartList);

		//数据类型转换
		params.put("Integer", Integer.class);
		setResultData(params);

		return SUCCESS;
	}

	/**（直辖单位导出）
	 * 导出excel  要害部门的统计   行政区划导出
	 * @return
	 */
	public String exportExcel_KeySectionCount_Layer() {

		//处理行政区划
		String districtCode = this.getRequest().getParameter("districtCode");//行政区划编码
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = districtService.get(districtCode);
		}

		//查询保密局  要害部门  统计数据    直辖单位
		List<Map<String, Object>> countSectionList = keySectionService.count_KeySection_By_Secrecy_Level_Layer(district);
		//查询保密局 要害部位  统计数据     行政区内
	    List<Map<String, Object>> countPartList = partService.count_KeyPart_By_Secrecy_Level_Layer(district);

		Map<String, Object> params = new HashMap<String, Object>();
		//被导出的数据
		params.put("countSectionList", countSectionList);
		params.put("countPartList", countPartList);

		//数据类型转换
		params.put("Integer", Integer.class);
		setResultData(params);

		return SUCCESS;
	}


	/**（单位明细导出）
	 * 导出excel  要害部门的统计   行政区划导出
	 * @return
	 */
	public String exportExcel_KeySectionCount_OrganDetail() {

		//处理行政区划
		String districtCode = this.getRequest().getParameter("districtCode");//行政区划编码
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = districtService.get(districtCode);
		}

		//查询保密局  要害部门  统计数据    单位明细
		List<Map<String, Object>> countSectionList = keySectionService.count_KeySection_By_Secrecy_Level_OrganDetail(district);

		Map<String, Object> params = new HashMap<String, Object>();
		//被导出的数据
		params.put("countSectionList", countSectionList);
		//数据类型转换
		params.put("Integer", Integer.class);
		setResultData(params);

		return SUCCESS;
	}

	/**
	 * 导出   list页面的信息
	 * @return
	 * @throws ParseException
	 */
	public String exportExcel_List() throws ParseException {

		//业务标志  1： 查询页面     0：普通业务模块
		String ywFlag = this.getRequest().getParameter("ywFlag");
		if(ywFlag.equals("1")) {
			String districtCode = this.getRequest().getParameter("districtCode");//行政区划编码
			String isChildren = this.getRequest().getParameter("isChildren");//是否包含下级
			//处理是否包含下级的标志
			isChildren = isChildren==null || isChildren.equals("")? "0": isChildren;
			//处理行政区划对象
			if(districtCode==null) {
				district = this.getCurrentUser().getOrgan().getDistrict();
			}else {
				district = districtService.get(districtCode);
			}
			keySectionlist = keySectionModuleService.queryList(null, district, Integer.parseInt(isChildren), keySectionQo);

		}else {
			Organ organ = getCurrentUser().getOrgan();
			keySectionlist = keySectionModuleService.queryList(null, organ, keySectionQo);
		}

		Map<String, Object> params = new HashMap<String, Object>();
		//被导出的数据
		params.put("countSectionList", keySectionlist);
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

	/**
	 * ajax获取保密要害部门  是否有关联
	 * @return
	 */
	public String ajax_keySection() {
		//查看要害部门是否 和其他的表有关联    true有  false没有
		int flag = keySectionService.getKeySectionRelationship(this.getRequest().getParameter("keySectionId"));
		String message = "";
		if(flag==1){
			message = "要害部位";
		}else if(flag == 2) {
			message = "要害部门密级变更";
		}else if(flag == 3) {
			message = "要害部门密级解除";
		}else if(flag == 4) {
			message = "涉密人员";
		}

		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("flag", String.valueOf(flag));
		resultMap.put("message", message);

		this.setResultData(resultMap);
		return JSON;
	}



	/**
	 * 保密要害部门查询  左树页面
	 * @return
	 */
	public String query_main(){
		return SUCCESS;
	}



	/***************************************综合统计*******************************************/
	/**
	 * 综合统计  通过行政区划    查询要害部门个数 的明细列表
	 * 包括当前行政区划 和下级行政区划 的要害部门的明细
	 * @return
	 */
	public String zhtj_query_Detail(){

		district = districtService.get(district.getDistrictCode());

		//处理行政区划
		List<District> districtList = new ArrayList<District>();
		List<District> childrenDistrictList =  new ArrayList<District>(district.getChildren());
		districtList.add(district);

		//处理单位
		Organ organ = this.getCurrentUser().getOrgan();

		//查询  当前行政区划的 明细
		List<KeySectionStatDto> keySectionStatDtoList = keySectionService.queryKeySectionByDistrict(districtList, false, organ);
		//查询  子行政区划的明细
		List<KeySectionStatDto> childrenkeySectionStatDtoList = keySectionService.queryKeySectionByDistrict(childrenDistrictList, true, organ);

		putToRequest("keySectionStatDtoList", keySectionStatDtoList);
		putToRequest("distictList", childrenkeySectionStatDtoList);
		putToRequest("districtCode", district.getDistrictCode());
		putToRequest("topDistrict", this.getCurrentUser().getOrgan().getDistrict());

		return SUCCESS;
	}

	/**
	 * 综合统计  通过行政区划编码  统计该行政区划要害部门的统计
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

		List<ZongHeTongJiStatDto> keySectionStatDtoList = keySectionService.count_KeySection_District(district,true,organ);
		putToRequest("keySectionStatDtoList", keySectionStatDtoList);
		putToRequest("district", district);
		putToRequest("topDistrict", this.getCurrentUser().getOrgan().getDistrict());

		return SUCCESS;
	}

	/**
	 * 通过密级，行政区划对象或者单位对象，查询要害部门对应的列表
	 * 密级是必须字段，如果点到合计上面了，这里的单位是空的，所以需要行政区划对象
	 *                如果没有点到合计，那么会使用单位的对象去查询
	 * @return
	 */
	public String zhtj_DetailList() {

		List<KeySection> keySectionList = new ArrayList<KeySection>();
		PageSortModel psm = new PageSortModel(this.getRequest(), "keySectionList");

		//设置密级
		String secrecy_level = getRequest().getParameter("secrecyLevel");//密级
		if(keySection==null) {
			keySection = new KeySection();
		}
		if(secrecy_level!=null && !"".equals(secrecy_level)) {
			keySection.setSecrecyLevel(Integer.parseInt(secrecy_level));
		}

		String organId =  getRequest().getParameter("organId"); //单位id
		if(organId!=null && !"".equals(organId)) {//如果有单位，就用单位对象
			Organ organ = new Organ();
			organ.setOrganId(organId);
			keySectionList =  keySectionService.queryList(psm, organ, keySection);
		}else {//如果没有单位对象   就需要使用 行政区划对象   也说明点到合计了
			district = districtService.get(district.getDistrictCode());
			keySectionList =  keySectionService.queryList(psm, district, keySection);
		}

		putToRequest("keySectionList", keySectionList);
		putToRequest("secrecy_level", secrecy_level);
		return SUCCESS;
	}

	/**
	 * 一个单位的   数据篆取
	 * @return
	 */
	public String zhtj_OrganDetailList() {

		//设置密级
		String secrecy_level = getRequest().getParameter("secrecyType");//密级
		if (keySectionQo == null) {
			keySectionQo = new KeySectionQo();
			if(keySectionQo.getSecrecyLevel()==null) {
				if(secrecy_level==null || secrecy_level.equals("")) {
					secrecy_level="0";
				}
				keySectionQo.setSecrecyLevel(Integer.parseInt(secrecy_level));
			}
		}

		String organId =  getRequest().getParameter("organId"); //单位id
		Organ organ = new Organ();
		if(organId!=null) {
			organ = organService.get(organId);
		}

		//查询
		List<KeySection> keySectionList = new ArrayList<KeySection>();
		PageSortModel psm = new PageSortModel(this.getRequest(), "keySectionList");
		keySectionList = keySectionModuleService.queryList(psm, organ, keySectionQo);//查询 保密要害部位

		putToRequest("keySectionList", keySectionList);
		putToRequest("keySectionQo", keySectionQo);
		putToRequest("secrecy_level", secrecy_level);
		putToRequest("organ", organ);
		putToRequest("dataGetFlag", "1");//综合统计撰取数据的标志
		putToRequest("ywFlag", "0");
		return SUCCESS;
	}

	/**
	 * 综合统计  通过行政区划    查询要害部门个数 的明细列表
	 * 包括当前行政区划 和下级行政区划 的要害部门的明细
	 * @return
	 */
	public String zhtj_query(){
		district = this.getCurrentUser().getOrgan().getDistrict();
		district = districtService.get(district.getDistrictCode());

		//处理行政区划
		List<District> districtList = new ArrayList<District>();
		List<District> childrenDistrictList =  new ArrayList<District>(district.getChildren());
		districtList.add(district);

		//处理单位
		Organ organ = this.getCurrentUser().getOrgan();

		//查询  当前行政区划的 明细
		List<KeySectionStatDto> keySectionStatDtoList = keySectionService.queryKeySectionByDistrict(districtList, false, organ);
		//查询  子行政区划的明细
		List<KeySectionStatDto> childrenkeySectionStatDtoList = keySectionService.queryKeySectionByDistrict(childrenDistrictList, true, organ);

		putToRequest("keySectionStatDtoList", keySectionStatDtoList);
		putToRequest("distictList", childrenkeySectionStatDtoList);
		putToRequest("districtCode", district.getDistrictCode());
		putToRequest("topDistrict", this.getCurrentUser().getOrgan().getDistrict());

		return SUCCESS;
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
		//处理单位
		Organ organ = this.getCurrentUser().getOrgan();

		//查询  当前行政区划的 明细
		List<KeySectionStatDto> keySectionStatDtoList = keySectionService.queryKeySectionByDistrict(districtList, false, organ);

		//导出设置
  		Map<String, Object> params = new HashMap<String, Object>();
  		//被导出的数据
  		params.put("keySectionStatDtoList", keySectionStatDtoList);
		params.put("district", district);
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
		//处理单位
		Organ organ = this.getCurrentUser().getOrgan();
		//处理行政区划
		List<District> childrenDistrictList =  new ArrayList<District>(district.getChildren());

		//查询  子行政区划的明细
		List<KeySectionStatDto> childrenStatDtoList = keySectionService.queryKeySectionByDistrict(childrenDistrictList, true, organ);

		//导出设置
  		Map<String, Object> params = new HashMap<String, Object>();
  		//被导出的数据
  		params.put("childrenStatDtoList", childrenStatDtoList);
		params.put("district", district);
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
		keySectionlist = keySectionModuleService.queryList(null, keySectionModuleService.get(organId, Organ.class), keySectionQo);
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
		resultData.put("id", "2");
		keySectionlist = keySectionModuleService.queryList(null, getCurrentUser().getOrgan(), null);
                String msg = dataValidateService.validateData("要害部门", keySectionlist, "2");
		resultData.put("msg", msg);
		setResultData(resultData);
		return JSON;
	}

	// ******************** Setter & Getter ********************
	public KeySection getKeySection() {
		return keySection;
	}

	public void setKeySection(KeySection keySection) {
		this.keySection = keySection;
	}

	public KeySectionQo getKeySectionQo() {
		return keySectionQo;
	}

	public void setKeySectionQo(KeySectionQo keySectionQo) {
		this.keySectionQo = keySectionQo;
	}

	public List<KeySection> getKeySectionlist() {
		return keySectionlist;
	}

	public void setKeySectionlist(List<KeySection> keySectionlist) {
		this.keySectionlist = keySectionlist;
	}

	public KeySectionModuleService getKeySectionModuleService() {
		return keySectionModuleService;
	}

	public void setKeySectionModuleService(
			KeySectionModuleService keySectionModuleService) {
		this.keySectionModuleService = keySectionModuleService;
	}

	public String getKeySectionIds() {
		return keySectionIds;
	}

	public void setKeySectionIds(String keySectionIds) {
		this.keySectionIds = keySectionIds;
	}

	public Boolean getNeedReload() {
		return needReload;
	}

	public void setNeedReload(Boolean needReload) {
		this.needReload = needReload;
	}

	public AttachmentService getAttachmentService() {
		return attachmentService;
	}

	public void setAttachmentService(AttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}

	public List<String> getSecAttach() {
		return secAttach;
	}

	public void setSecAttach(List<String> secAttach) {
		this.secAttach = secAttach;
	}

	public SecrecyPersonModuleService getSecrecyPersonModuleService() {
		return secrecyPersonModuleService;
	}

	public void setSecrecyPersonModuleService(
			SecrecyPersonModuleService secrecyPersonModuleService) {
		this.secrecyPersonModuleService = secrecyPersonModuleService;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getAddOrEditSearch() {
		return addOrEditSearch;
	}

	public void setAddOrEditSearch(String addOrEditSearch) {
		this.addOrEditSearch = addOrEditSearch;
	}

	/**
	 * @param keySectionExchanger 设置keySectionExchanger
	 */
	public void setKeySectionExchanger(KeySectionExchanger keySectionExchanger) {
		this.keySectionExchanger = keySectionExchanger;
	}

	/**
	 * @param serverReportMappingService 设置serverReportMappingService
	 */
	public void setServerReportMappingService(
			ServerReportMappingService serverReportMappingService) {
		this.serverReportMappingService = serverReportMappingService;
	}

	/**
	 * @return the secrecyLevel
	 */
	public Integer getSecrecyLevel() {
		return secrecyLevel;
	}

	/**
	 * @param secrecyLevel the secrecyLevel to set
	 */
	public void setSecrecyLevel(Integer secrecyLevel) {
		this.secrecyLevel = secrecyLevel;
	}

	/**
	 * @return the partModuleService
	 */
	public PartModuleService getPartModuleService() {
		return partModuleService;
	}

	/**
	 * @param partModuleService the partModuleService to set
	 */
	public void setPartModuleService(PartModuleService partModuleService) {
		this.partModuleService = partModuleService;
	}

	/**
	 * @return the showType
	 */
	public String getShowType() {
		return showType;
	}

	/**
	 * @param showType the showType to set
	 */
	public void setShowType(String showType) {
		this.showType = showType;
	}

	/**
	 * @param dataSource 设置dataSource
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * 要害部门密级变更对象   struts2的DomainModel注入
	 */
	public KeySectionChange getKeySectionChange() {
		return keySectionChange;
	}
	/**
	 * 要害部门密级变更对象   struts2的DomainModel注入
	 */
	public void setKeySectionChange(KeySectionChange keySectionChange) {
		this.keySectionChange = keySectionChange;
	}

	public KeySectionSecrecyClear getKeySectionSecrecyClear() {
		return keySectionSecrecyClear;
	}

	public void setKeySectionSecrecyClear(
			KeySectionSecrecyClear keySectionSecrecyClear) {
		this.keySectionSecrecyClear = keySectionSecrecyClear;
	}

	/**
	 * //保密要害部门密级变更服务层  对象  spring注入
	 * @return
	 */
	public KeySectionChangeService getKeySectionChangeService() {
		return keySectionChangeService;
	}

	/**
	 * //保密要害部门密级变更服务层  对象  spring注入
	 * @param keySectionChangeService
	 */
	public void setKeySectionChangeService(
			KeySectionChangeService keySectionChangeService) {
		this.keySectionChangeService = keySectionChangeService;
	}

	public KeySectionSecrecyClearService getKeySectionSecrecyClearService() {
		return keySectionSecrecyClearService;
	}

	public void setKeySectionSecrecyClearService(
			KeySectionSecrecyClearService keySectionSecrecyClearService) {
		this.keySectionSecrecyClearService = keySectionSecrecyClearService;
	}
	public KeySectionService getKeySectionService() {
		return keySectionService;
	}

	public void setKeySectionService(KeySectionService keySectionService) {
		this.keySectionService = keySectionService;
	}


	public PartService getPartService() {
		return partService;
	}

	public void setPartService(PartService partService) {
		this.partService = partService;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public DistrictService getDistrictService() {
		return districtService;
	}

	public void setDistrictService(DistrictService districtService) {
		this.districtService = districtService;
	}

	public OrganService getOrganService() {
		return organService;
	}

	public void setOrganService(OrganService organService) {
		this.organService = organService;
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