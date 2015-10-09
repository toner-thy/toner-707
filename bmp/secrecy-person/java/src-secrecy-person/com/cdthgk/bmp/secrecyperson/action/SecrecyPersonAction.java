package com.cdthgk.bmp.secrecyperson.action;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.hibernate.SessionFactory;

import com.cdthgk.bmp.core.action.BmpAction;
import com.cdthgk.bmp.keyPart.service.PartPersonModuleService;
import com.cdthgk.bmp.keyPart.vo.Part;
import com.cdthgk.bmp.keyPart.vo.PartPerson;
import com.cdthgk.bmp.log.context.BusinessLogContext;
import com.cdthgk.bmp.log.vo.BusinessLog;
import com.cdthgk.bmp.secrecycountryitem.qo.ZongHeTongJiStatDto;
import com.cdthgk.bmp.secrecyperson.dto.SecrecyPersonDto;
import com.cdthgk.bmp.secrecyperson.service.SecrecyPersonDecryptionService;
import com.cdthgk.bmp.secrecyperson.service.SecrecyPersonLevelChangeService;
import com.cdthgk.bmp.secrecyperson.service.SecrecyPersonModuleService;
import com.cdthgk.bmp.secrecyperson.service.SecrecyPersonService;
import com.cdthgk.bmp.secrecyperson.transmitor.exchange.SecrecyPersonExchanger;
import com.cdthgk.bmp.secrecyperson.vo.SecrecyPerson;
import com.cdthgk.bmp.secrecyperson.vo.SecrecyPersonDecryption;
import com.cdthgk.bmp.secrecyperson.vo.SecrecyPersonLevelChange;
import com.cdthgk.bmp.statinfo.dto.QueryDto;
import com.cdthgk.code.enums.Nation;
import com.cdthgk.common.db.data.xml.XmlExportor;
import com.cdthgk.common.lang.DateUtils;
import com.cdthgk.common.lang.LangUtils;
import com.cdthgk.common.lang.StringUtils;
import com.cdthgk.component.ioc.ContextUtils;
import com.cdthgk.exchange.serverorganmapping.service.ServerReportMappingService;
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
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;
import com.cdthgk.platform.organization.userinfo.service.UserInfoService;
import com.cdthgk.platform.permission.user.domain.User;
import com.cdthgk.web.mvc.struts2.conversion.EnumConverter;
import com.cdthgk.web.upload.UploadFile;

import ec.common.PageSortModel;

/**
 * <p>
 * 机关涉密人员Action类
 * </p>
 * <p>
 * 牟远洋 2012-12-14 17:01
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
 * @author mouyuanyang
 * @since 1.0
 * @version 1.0
 */
public class SecrecyPersonAction extends BmpAction{

	private static final long serialVersionUID = 1L;

	private SecrecyPerson secrecyPerson;
	private SecrecyPersonService secrecyPersonService;
	private SecrecyPersonModuleService secrecyPersonModuleService;
	private SecrecyPersonDecryptionService secrecyPersonDecryptionService;
	private SecrecyPersonLevelChangeService secrecyPersonLevelChangeService;
	private PartPersonModuleService partPersonModuleService;
	private DataValidateService dataValidateService;
	private List<SecrecyPerson> secrecyPersonList;

	private UserInfo userInfo;
	private UserInfoService userInfoService;

	private District district;
	private DistrictService districtService;

	// 获取页面参数用
	private String actionFlag;
	private String userInfoId;
	private String departmentId;
	private String secrecyPersonIds;
	private Integer secrecyLevel;
	private Organ organ;
	private String showType = "0";

	private SecrecyPersonLevelChange secrecyPersonLevelChange;
	private SecrecyPersonDecryption secrecyPersonDecryption;

	// 设置是否重载用
	private Boolean needReload = false;
	// 查询范围用（"1":直辖单位，"2":行政区划）
	private String status = "2";

	private final String markFlag = ",";

	private DataSource dataSource;

	private SecrecyPersonExchanger secrecyPersonExchanger;

	private ServerReportMappingService serverReportMappingService;

	//导出功能按钮使用的flag
	private String expBtnFlag;


	// *********************** 方 法 ***********************

	// 构造器
	/** 默认构造器 */
	public SecrecyPersonAction() {
	}

	/**
	 * <p>
	 * 机关涉密人员列表
	 * </p>
	 * <p>
	 * 创建时间 2012-12-14 16:25
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
	 * @author mouyuanyang
	 * @version 1.0
	 * @return secrecyPersonList
	 * @throws ParseException ParseException
	 */
	public String list() throws ParseException {
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

		//查看是否是内嵌的页面
		String nestedflag = this.getRequest().getParameter("nestedflag");
		if(nestedflag!=null && nestedflag.equals("1")) {//是内嵌页面
			this.putToRequest("nestedflag", nestedflag);
		}else {
			this.putToRequest("nestedflag", "0");//不是内嵌页面
		}

		return "list";
	}

	public String list_list() throws ParseException {
		String partId = this.getRequest().getParameter("partId");
		PageSortModel<SecrecyPerson> psm = new PageSortModel<SecrecyPerson>(getRequest(), "secrecyPersonList");

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

		//要害部[门]不为空，查询该要害部[门]下的所有机关涉密人员的记录  梁文杰 2013-07-20修改
		if(departmentId!=null && !departmentId.equals("")) {
			Department depart = new Department();
			depart.setDepartmentId(departmentId);
			if(secrecyPerson==null) {
				secrecyPerson = new SecrecyPerson();
				secrecyPerson.setDepartment(depart);
			}else {
				secrecyPerson.setDepartment(depart);
			}
		}

		//综合统计使用
		String secrecyLevel = this.getRequest().getParameter("secrecyType");
		if( secrecyLevel!=null && !"".equals(secrecyLevel) ){
			if( secrecyPerson==null ){
				secrecyPerson = new SecrecyPerson();
			}
			secrecyPerson.setSecrecyPersonLevel(Integer.parseInt(secrecyLevel));
			String organId = this.getRequest().getParameter("organId");
			if( organId!=null && !"".equals(organId)){
				organ = this.secrecyPersonModuleService.get(organId, Organ.class);
			}
			this.putToRequest("secrecyType", secrecyLevel);
			this.putToRequest("organId", organId);
			this.putToRequest("organ", organ);
			this.putToRequest("zhtj", "1");
		}

		//查看是否是内嵌的页面
		String nestedflag = this.getRequest().getParameter("nestedflag");
		if(nestedflag!=null && nestedflag.equals("1")) {//是内嵌页面
			this.putToRequest("nestedflag", nestedflag);
		}else {
			this.putToRequest("nestedflag", "0");//不是内嵌页面
		}


		// 获取当前单位涉密人员列表
		List<SecrecyPerson> secrecyPersonList =
				secrecyPersonModuleService.getPageAllList(psm, secrecyPerson, organ, district, partId, checkLower, SecrecyPersonAction.SECRECY_STATUS_NOW);
		if( partId!=null && !"".equals(partId) ){
			this.putToRequest("partId", partId);
		}
		putToRequest("secrecyPersonList", secrecyPersonList);
		return "list";
	}

	/**
	 *
	 * <p>
	 * 方法名：list
	 * </p>
	 * <p>
	 * 宋亚非 2013-5-21 下午3:27:38
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
	 *@throws ParseException
	 */
	public String exportData() throws ParseException {
		Map<String, Object> params = new HashMap<String, Object>();
		// 获取当前登录单位
		Organ organ = getCurrentUser().getOrgan();
		// 获取当前单位涉密人员列表
		List<SecrecyPerson> secrecyPersonList =
				secrecyPersonModuleService.getAllList(secrecyPerson, organ);

		List<DictionaryOption> secrecyLevelList = this.secrecyPersonModuleService.getDictionaryOptionList("bmp", "secrecy_level_section");
		params.put("secrecyLevelList", secrecyLevelList);

		params.put("secrecyPersonList", secrecyPersonList);
		setResultData(params);
		return "success";
	}

	/**
	 * <p>
	 * 机关涉密人员列表(首页获取涉密人员信息)
	 * </p>
	 * <p>宋亚非
	 * 创建时间 2013-04-10 08:44
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
	 * @version 1.0
	 * @return secrecyPersonList
	 * @throws ParseException ParseException
	 */
	public String allList() throws ParseException {
		PageSortModel<SecrecyPerson> psm = new PageSortModel<SecrecyPerson>(getRequest(), "secrecyPersonList");

		// 获取当前登录单位
		Organ organ = getCurrentUser().getOrgan();
		// 获取当前单位涉密人员列表
		List<SecrecyPerson> secrecyPersonList =
				secrecyPersonModuleService.getPageAllList(psm, secrecyPerson, organ, showType);

		putToRequest("secrecyPersonList", secrecyPersonList);
		return "list";
	}

	public String layerList() throws ParseException {
		PageSortModel<SecrecyPerson> psm = new PageSortModel<SecrecyPerson>(getRequest(), "secrecyPersonList");
		showType = "1";
		// 获取当前登录单位
		Organ organ = getCurrentUser().getOrgan();
		// 获取当前单位涉密人员列表
		List<SecrecyPerson> secrecyPersonList =
				secrecyPersonModuleService.getPageAllList(psm, secrecyPerson, organ, showType);

		putToRequest("secrecyPersonList", secrecyPersonList);
		return "list";
	}


	/**
	 * <p>
	 * 机关涉密人员列表
	 * </p>
	 * <p>
	 *  牟远洋  创建时间 2012-12-14 16:25
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>宋亚非 2013-04-09  12:25 从市平台移植到省平台
	 * </ul>
	 * </blockquote>
	 * <p>
	 * copyright tianyu 2012, all rights reserved.
	 * </p>
	 *
	 */
	public String organSecPersonData(){
		Organ organ = getCurrentUser().getOrgan();
		PageSortModel<SecrecyPerson> psm = new PageSortModel<SecrecyPerson>(getRequest(),	"secrecyPersonList");
		String districtCode = organ.getDistrict().getDistrictCode();
		secrecyPersonList=secrecyPersonModuleService.getIndexPage(psm, organ, secrecyLevel, districtCode, secrecyPerson, null);
		if( secrecyPerson == null ){
		        secrecyPerson = new SecrecyPerson();
		}
		secrecyPerson.setSecrecyPersonLevel(secrecyLevel);
		this.putToRequest("secrecyPersonList", secrecyPersonList);
		this.putToApplication("secrecyLevel", secrecyLevel);
		return "demand";
	}

	public String layerSecPersonData(){
		Organ organ = getCurrentUser().getOrgan();
		PageSortModel<SecrecyPerson> psm = new PageSortModel<SecrecyPerson>(getRequest(),	"secrecyPersonList");
		String districtCode = organ.getDistrict().getDistrictCode();
		secrecyPersonList=secrecyPersonModuleService.getIndexPage(psm, organ, secrecyLevel, districtCode, secrecyPerson, "1");
                if( secrecyPerson == null ){
                        secrecyPerson = new SecrecyPerson();
                }
                secrecyPerson.setSecrecyPersonLevel(secrecyLevel);
		this.putToRequest("secrecyPersonList", secrecyPersonList);
		this.putToApplication("secrecyLevel", secrecyLevel);
		return "demand";
	}


	/**
	 * <p>
	 * 机关涉密人员新增页面
	 * </p>
	 * <p>
	 * 牟远洋 2012-12-17 10:16
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
	 * @author mouyuanyang
	 * @version 1.0
	 * @return add
	 */
	public String add() {
		Department department = new Department();

		if (LangUtils.isNotEmpty(departmentId)) {
			// 获取传入部门信息
			department = secrecyPersonModuleService.get(departmentId, Department.class);
			if(secrecyPerson==null){
				secrecyPerson = new SecrecyPerson();
				secrecyPerson.setDepartment(department);
			}
		}
		String partId = this.getRequest().getParameter("partId");
		if( partId!=null && !"".equals(partId) ){
			this.putToRequest("partId", partId);
		}

		//是否是内嵌的页面
		String nestedflag = this.getRequest().getParameter("nestedflag");
		if(nestedflag!=null && nestedflag.equals("1")) {//是内嵌的页面
			this.putToRequest("nestedflag", nestedflag);
		}else {
			this.putToRequest("nestedflag", "0");
		}

		putToRequest("department", department);
		organ = OrganizationContext.getInstance().getOrganService().get(getCurrentUser().getOrgan().getOrganId());
		return "add";
	}

	/**
	 * <p>
	 * 机关涉密人员新增操作
	 * </p>
	 * <p>
	 * 牟远洋 2012-12-17 10:23
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
	 * @author 牟远洋
	 * @version 1.0
	 * @return 重定向list
	 */
	public String adding() {
		boolean flag = false;
		User currentUser = getCurrentUser();

		// 检查当前涉密人员是否存在
		boolean checkflag = secrecyPersonModuleService.checkSecrecyPerson(
				userInfoId, departmentId, getCurrentUser().getOrgan());

		if (!checkflag) {
			//设置部门
			if (LangUtils.isNotEmpty(departmentId)) {
				// 获取要害部门传入部门信息
				secrecyPerson.setDepartment(
						secrecyPersonModuleService.get(departmentId, Department.class));
			} else {
				// 部门更改或名字改变
				if (secrecyPerson.getDepartment() != null && LangUtils.isNotEmpty(
						secrecyPerson.getDepartment().getDepartmentId())) {
					secrecyPerson.setDepartment(secrecyPersonModuleService.get(
						secrecyPerson.getDepartment().getDepartmentId(), Department.class));

				} else if (secrecyPerson.getDepartment() != null && LangUtils.isNotEmpty(
						secrecyPerson.getDepartment().getDepartmentName())) {
					Department department = secrecyPersonModuleService.saveDepartment(
							secrecyPerson.getDepartment().getDepartmentName(), currentUser);

					secrecyPerson.setDepartment(department);
				}
			}

			Department depart = secrecyPerson.getDepartment();
			putToRequest("department", depart);

			// 设置人员
			if ("update".equals(actionFlag)) {
				// 获取当前人员信息
				UserInfo userInfo = secrecyPersonModuleService.get(
						secrecyPerson.getUserInfo().getUserInfoId(), UserInfo.class);
				// 更新当前人员信息
				secrecyPersonModuleService.updateUserInfo(userInfo,
						secrecyPerson.getUserInfo(), currentUser, depart);
				secrecyPerson.setUserInfo(userInfo);
			}
			if ("".equals(userInfoId) || "add".equals(actionFlag)) {
				// 新增人员信息
				UserInfo userInfo = secrecyPersonModuleService.saveUserInfo(
						secrecyPerson.getUserInfo(), currentUser, depart);
				secrecyPerson.setUserInfo(userInfo);
			}

			// 设置涉密人员通用字段
			secrecyPerson.setOrgan(getCurrentUser().getOrgan());
			secrecyPerson.setCreateTime(new Date());
			secrecyPerson.setModifyTime(new Date());
			secrecyPerson.setCreatePerson(getCurrentUser());
			secrecyPerson.setModifyPerson(getCurrentUser());
			try {
				// 保存机关涉密人员实体
				//修改人：宋亚非   修改原因：数据库统一至一个库后，取消上报功能，填写信息后状态即为已上报   修改时间：2013-06-03
				//secrecyPerson.setReportState(ReportState.REPORT_NO);
				secrecyPerson.setReportState(ReportState.REPORT_YES);
				secrecyPersonModuleService.save(secrecyPerson);

		                BusinessLog log = new BusinessLog();
		                log.setBusinessName("涉密人员管理");
		                log.setPrimaryKey(secrecyPerson.getSecrecyPersonId());
		                BusinessLogContext.getInstance().getBusinessLogService().saveAddBusinessLogByModule(getCurrentUser(), log, secrecyPerson);
				flag = true;
			} catch (Exception e) {
				flag = false;
			}
			// 设置提示消息
			addActionMessage(flag ?
					getMessageConstant().getSaveSuccess() : getMessageConstant().getSaveFailure());
			// 设置是否需要重载
			needReload = true;

		} else {
			// 存在该涉密人员
			addActionMessage("该涉密人员已经存在!");
			// 设置是否需要重载
			needReload = false;
		}

		String partId = this.getRequest().getParameter("partId");
		if( partId!=null && !"".equals(partId) ){
			PartPerson partPerson = new PartPerson();
			Part part = this.partPersonModuleService.get(partId, Part.class);
			if( part!=null ){
				partPerson.setPart(part);
				partPerson.setSecrecyPerson(secrecyPerson);
				this.partPersonModuleService.save(partPerson);
			}
			this.putToRequest("partId", partId);
		}

		//是否是内嵌的页面
		String nestedflag = this.getRequest().getParameter("nestedflag");
		if(nestedflag!=null && nestedflag.equals("1")) {//是内嵌的页面
			this.putToRequest("nestedflag", nestedflag);
		}else {
			this.putToRequest("nestedflag", "0");
		}

		return redirectActionResult(LIST);
	}


	public String checkUserInfoName(){
	        UserInfo u = new UserInfo();
	        String userName = getRequest().getParameter("userName");
	        String userINfoId = getRequest().getParameter("userInfoId");
	        u.setName(userName);
	        List<UserInfo> uList = this.secrecyPersonModuleService.findUserInfo(u, getCurrentUser().getOrgan().getOrganId());
	        int uNum = 0;
	        if( uList!=null && uList.size()>0 ){
	               uNum = uList.size();
	        }else{
	                uNum = 0;
	        }
	        if( uList.size() == 1 ){
	               if( !userINfoId.equals(uList.get(0).getId()) ){
	                       uNum++;
	               }
	        }
	        Map<String, Object> resultData = new HashMap<String, Object>();
                resultData.put("uNum", uNum);
                setResultData(resultData);
	        return JSON;
	}

	/**
	 * <p>
	 * 机关涉密人员编辑页面
	 * </p>
	 * <p>
	 * 牟远洋 2012-12-17 10:33
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
	 * @author 牟远洋
	 * @version 1.0
	 * @return edit
	 */
	public String edit() {
		Department department = new Department();

		// 检查当前访问ID
		if (secrecyPerson == null || secrecyPerson.getSecrecyPersonId() == null
				|| LangUtils.isEmpty(secrecyPerson.getSecrecyPersonId())) {
			this.addActionMessage("您所访问的资源Id不存在，请确认后重试。");
			return this.redirectActionResult("list");
		}
		secrecyPerson = secrecyPersonModuleService.get(secrecyPerson.getSecrecyPersonId());

		if (LangUtils.isNotEmpty(departmentId)) {
			// 获取传入部门信息
			department = secrecyPersonModuleService.get(departmentId, Department.class);
		}

		String partId = this.getRequest().getParameter("partId");
		if( partId!=null && !"".equals(partId) ){
			this.putToRequest("partId", partId);
		}

		//是否是内嵌的页面
		String nestedflag = this.getRequest().getParameter("nestedflag");
		if(nestedflag!=null && nestedflag.equals("1")) {//是内嵌的页面
			this.putToRequest("nestedflag", nestedflag);
		}else {
			this.putToRequest("nestedflag", "0");
		}

		putToRequest("department", department);
		putToRequest("organ", getCurrentUser().getOrgan());
		putToRequest("loadFlag", "true");
		return "edit";
	}

	/**
	 * <p>
	 * 机关涉密人员编辑操作
	 * </p>
	 * <p>
	 * 牟远洋 2012-12-17 10:43
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
	 * @author 牟远洋
	 * @version 1.0
	 * @return 重定向list
	 */
	public String editing() {
		boolean flag = false;

		User currentUser = getCurrentUser();

		SecrecyPerson dbSecrecyPerson = secrecyPersonModuleService.get(secrecyPerson.getSecrecyPersonId());
		SessionFactory sf =  ContextUtils.getDefaultContext().getBean("sessionFactory");
		sf.getCurrentSession().evict(dbSecrecyPerson);
		BusinessLog log = new BusinessLog();
        log.setBusinessName("涉密人员管理");
        log.setPrimaryKey(dbSecrecyPerson.getSecrecyPersonId());
        BusinessLogContext.getInstance().getBusinessLogService().saveEditBusinessLogByModule(getCurrentUser(), log, secrecyPerson, dbSecrecyPerson);

		//设置部门
		if (LangUtils.isNotEmpty(departmentId)) {
			// 获取传入部门信息
			secrecyPerson.setDepartment(
					secrecyPersonModuleService.get(departmentId, Department.class));
		} else {
			if (secrecyPerson.getDepartment() != null && LangUtils.isNotEmpty(
					secrecyPerson.getDepartment().getDepartmentId())) {
				secrecyPerson.setDepartment(secrecyPersonModuleService.get(
					secrecyPerson.getDepartment().getDepartmentId(), Department.class));
			} else if (secrecyPerson.getDepartment() != null && LangUtils.isNotEmpty(
					secrecyPerson.getDepartment().getDepartmentName())) {
				Department department = secrecyPersonModuleService.saveDepartment(
						secrecyPerson.getDepartment().getDepartmentName(), currentUser);
				secrecyPerson.setDepartment(department);
			}
		}

		Department depart = secrecyPerson.getDepartment();
		putToRequest("department", depart);

		// 设置人员
		if ("resetUser".equals(actionFlag)) {

			UserInfo userInfoDB = new UserInfo();

			if ("".equals(secrecyPerson.getUserInfo().getUserInfoId())) {
				// 获取选择人员信息
				userInfoDB = secrecyPersonModuleService.get(userInfoId, UserInfo.class);
			} else {
				// 获取当前人员信息
				userInfoDB = secrecyPersonModuleService.get(
						secrecyPerson.getUserInfo().getUserInfoId(), UserInfo.class);
			}

			// 更新当前人员信息
			secrecyPersonModuleService.updateUserInfo(userInfoDB,
					secrecyPerson.getUserInfo(), currentUser, depart);
			secrecyPerson.setUserInfo(userInfoDB);
		} else {
			// 获取选择人员信息
			UserInfo userInfo = secrecyPersonModuleService.get(userInfoId, UserInfo.class);

			// 更新当前人员信息
			secrecyPersonModuleService.updateUserInfo(userInfo,
					secrecyPerson.getUserInfo(), currentUser, depart);
			secrecyPerson.setUserInfo(userInfo);
		}

		// 更新涉密人员基础信息
		secrecyPerson.setOrgan(getCurrentUser().getOrgan());
		secrecyPerson.setModifyPerson(getCurrentUser());
		secrecyPerson.setModifyTime(new Date());

		try {
			// 更新机关涉密人员实体
			//修改人：宋亚非   修改原因：数据库统一至一个库后，取消上报功能，不再存在上报后修改状态   修改时间：2013-06-03
			/*if(secrecyPerson.getReportState() > 0) {
				secrecyPerson.setReportState(ReportState.REPORT_YES_MODIFY);
			}*/
			secrecyPersonModuleService.update(secrecyPerson);
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		// 设置消息
		addActionMessage(flag ?
				getMessageConstant().getUpdateSuccess() : getMessageConstant().getUpdateFailure());
		// 设置是否需要重载
		needReload = true;
		String partId = this.getRequest().getParameter("partId");
		if( partId!=null && !"".equals(partId) ){
			this.putToRequest("partId", partId);
		}
		//是否是内嵌的页面
		String nestedflag = this.getRequest().getParameter("nestedflag");
		if(nestedflag!=null && nestedflag.equals("1")) {//是内嵌的页面
			this.putToRequest("nestedflag", nestedflag);
		}else {
			this.putToRequest("nestedflag", "0");
		}
		return redirectActionResult(LIST);
	}

	/**
	 * <p>
	 * 机关涉密人员删除操作
	 * </p>
	 * <p>
	 * 牟远洋 2012-12-17 10:43
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
	 * @author 牟远洋
	 * @version 1.0
	 * @return 重定向list
	 */
	public String delete() {
		// 检查当前访问ID
		//查看是否是内嵌的页面
		String nestedflag = this.getRequest().getParameter("nestedflag");
		if(nestedflag!=null && nestedflag.equals("1")) {//是内嵌页面
			this.putToRequest("nestedflag", nestedflag);
		}else {
			this.putToRequest("nestedflag", "0");//不是内嵌页面
		}

		if (secrecyPersonIds == null || LangUtils.isEmpty(secrecyPersonIds)
				|| secrecyPersonIds.equals(markFlag)) {
			this.addActionMessage("您所访问的资源Id不存在，请确认后重试。");
			return this.redirectActionResult("list");
		}
		// 删除
		for( String id : secrecyPersonIds.split(",") ){
	        SecrecyPerson sp = secrecyPersonModuleService.get(id);
	        if( sp!=null ){
                BusinessLog log = new BusinessLog();
                log.setBusinessName("涉密人员管理");
                log.setPrimaryKey(id);
                BusinessLogContext.getInstance().getBusinessLogService().saveDelBusinessLogByModule(getCurrentUser(), log, sp);
	        }
		}
		secrecyPersonModuleService.deleteBatchIds(secrecyPersonIds);

		// 设置信息
		this.addActionMessage(getMessageConstant().getDeleteSuccess());
		return this.redirectActionResult("list");
	}

	/**
	 * <p>
	 * 机关涉密人员详情
	 * </p>
	 * <p>
	 * 牟远洋 2012-12-17 10:36
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
	 * @author 牟远洋
	 * @version 1.0
	 * @return detail
	 */
	public String detail() {
		// 检查当前访问ID
		if (secrecyPerson == null || secrecyPerson.getSecrecyPersonId() == null
				|| LangUtils.isEmpty(secrecyPerson.getSecrecyPersonId())) {
			this.addActionMessage("您所访问的资源Id不存在，请确认后重试。");
			return this.redirectActionResult("list");
		}
		// 获取当前Id对应数据
		secrecyPerson = secrecyPersonModuleService.get(secrecyPerson.getSecrecyPersonId());

		putToRequest("organ", getCurrentUser().getOrgan());
		return "detail";
	}

	/**
	 * <p>
	 * 行政区机关涉密人员查询页
	 * </p>
	 * <p>
	 * 创建时间 2012-12-17 18:05
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
	 * @author mouyuanyang
	 * @version 1.0
	 * @return main
	 */
	public String main() {
		this.putToRequest("district", getCurrentUser().getOrgan().getDistrict());
		return "main";
	}

	/**
	 * <p>
	 * 行政区机关涉密人员查询操作
	 * </p>
	 * <p>
	 * 创建时间 2012-12-17 16:05
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
	 * @author mouyuanyang
	 * @version 1.0
	 * @return checkDistrictList
	 * @throws ParseException ParseException
	 */
	public String districtQueryList() throws ParseException {
		PageSortModel<SecrecyPerson> psm = new PageSortModel<SecrecyPerson>(getRequest(), "secrecyPersonList");

		// 获取当前登录行政区
		if (district == null) {
			district = new District();
			district.setDistrictCode(getCurrentUser().getOrgan().getDistrict().getDistrictCode());
		}
		// 获取当前登录单位
		Organ organ = getCurrentUser().getOrgan();

		List<SecrecyPerson> secrecyPersonList =
				secrecyPersonModuleService.getDistrictList(psm, district, organ, status, secrecyPerson);

		this.putToRequest("secrecyPersonList", secrecyPersonList);
		return "districtQueryList";
	}

	/**
	 * <p>
	 * 获取新增页面填充用户信息
	 * </p>
	 * <p>
	 * 创建时间 2012-12-20 17:05
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
	 * @author mouyuanyang
	 * @version 1.0
	 * @return resultMap
	 */
	public String addViewUserInfo() {
		boolean flag = false;

		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		Map<String, String> resultMap = new HashMap<String, String>();

		// 检查当前涉密人员是否存在
		flag = secrecyPersonModuleService.checkSecrecyPerson(
				userInfoId, departmentId, getCurrentUser().getOrgan());

		if (flag) {
			// 该涉密人员存在
			resultMap.put("message", "该涉密人员已经存在!");
		} else {

			// 获取人员信息
			UserInfo  userInfoDB = secrecyPersonModuleService.getUserInfo(userInfoId);

			// 设定页面显示字段
			resultMap.put("userName", userInfoDB.getName());
			resultMap.put("sex", userInfoDB.getSex());
			resultMap.put("nation", userInfoDB.getNation().ordinal() + "");
			resultMap.put("birthDate", userInfoDB.getBirthDate() == null ?
					"" : s.format(userInfoDB.getBirthDate()));
			resultMap.put("learningLevel", userInfoDB.getLearningLevel() + "");
			resultMap.put("idCard", userInfoDB.getIdentityCard());
			resultMap.put("polity", userInfoDB.getPolity());
			resultMap.put("departmentId", userInfoDB.getDepartment() == null ?
					"" : userInfoDB.getDepartment().getDepartmentId());
			resultMap.put("departmentName", userInfoDB.getDepartment() == null ?
					"" : userInfoDB.getDepartment().getDepartmentName());
			resultMap.put("staff", userInfoDB.getStaff() == null ? "" : userInfoDB.getStaff().toString());
			resultMap.put("mobile", userInfoDB.getMobile());
			resultMap.put("administrativeLevel", userInfoDB == null ? "" : userInfoDB.getAdministrativeLevel() == null ? "" : userInfoDB.getAdministrativeLevel()+"");

			resultMap.put("duty", userInfoDB == null ? "" : userInfoDB.getDuty() == null ? "" : userInfoDB.getDuty());
			resultMap.put("phone", userInfoDB == null ? "" : userInfoDB.getPhone() == null ? "" : userInfoDB.getPhone());
			resultMap.put("technicTitleLevel", userInfoDB == null ? "" : userInfoDB.getTechnicTitleLevel() == null ? "" : userInfoDB.getTechnicTitleLevel()+"");
			resultMap.put("specialtyCode", userInfoDB == null ? "" : userInfoDB.getSpecialtyCode() == null ? "" : userInfoDB.getSpecialtyCode()+"");

		}
		// 将设定值返回给页面
		this.setResultData(resultMap);
		return JSON;
	}

	/**
	 * <p>
	 * 获取编辑页面填充用户信息
	 * </p>
	 * <p>
	 * 创建时间 2012-12-26 17:05:29
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
	 * @author mouyuanyang
	 * @version 1.0
	 * @return resultMap
	 */
	public String editViewUserInfo() {
		//boolean flag = false;

		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		Map<String, String> resultMap = new HashMap<String, String>();

		// 获取人员信息
		UserInfo  userInfoDB = secrecyPersonModuleService.getUserInfo(userInfoId);

		// 设定页面显示字段
		resultMap.put("userName", userInfoDB.getName());
		resultMap.put("sex", userInfoDB.getSex());
		resultMap.put("nation", userInfoDB.getNation().ordinal() + "");
		resultMap.put("birthDate", userInfoDB.getBirthDate() == null ?
				"" : s.format(userInfoDB.getBirthDate()));
		resultMap.put("learningLevel", userInfoDB.getLearningLevel() + "");
		resultMap.put("idCard", userInfoDB.getIdentityCard());
		resultMap.put("polity", userInfoDB.getPolity());
		resultMap.put("departmentId", userInfoDB.getDepartment() == null ?
				"" : userInfoDB.getDepartment().getDepartmentId());
		resultMap.put("departmentName", userInfoDB.getDepartment() == null ?
				"" : userInfoDB.getDepartment().getDepartmentName());
		resultMap.put("staff", userInfoDB.getStaff() == null ? "" : userInfoDB.getStaff().toString());
		resultMap.put("mobile", userInfoDB.getMobile());
		resultMap.put("polity", userInfoDB.getPolity() == null ? "" : userInfoDB.getPolity().toString());
		resultMap.put("administrativeLevel", userInfoDB == null ? "" : userInfoDB.getAdministrativeLevel() == null ? "" : userInfoDB.getAdministrativeLevel()+"");


		// 将设定值返回给页面
		this.setResultData(resultMap);
		return JSON;
	}

	/**
	 * <p>
	 * 导入word
	 * </p>
	 * <p>
	 * 创建时间 2013-01-17 17:18:29
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * <p>
	 * copyright liucc 2012, all rights reserved.
	 * </p>
	 *
	 * @author liuchuncheng
	 * @version 1.0
	 * @return resultMap
	 */
	public String importWord() {
		return SUCCESS;
	}

	/**
	 * <p>
	 * 导入2007 word
	 * 进入导入执行方法
	 * </p>
	 * 刘椿成2013-01-10 15:46:13
	 * <p>
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * <p>
	 * copyright liucc 2013, all rights reserved.
	 * </p>
	 * @author liucc
	 * @since 1.0
	 * @version 1.0
	 */
	public String importWording() throws IOException {
		String msgs="";
		User currentUser = getCurrentUser();
		Map<Boolean, List<String>> msgRsMap = secrecyPersonModuleService.saveImportData(getUploadFiles("upLoadFile").get(0), currentUser);
		Set<Entry<Boolean, List<String>>> es = msgRsMap.entrySet();
		Iterator<Entry<Boolean, List<String>>> i = es.iterator();
		Entry<Boolean, List<String>> entry = i.next();
		if (entry.getKey()) {
			addActionMessage(SecrecyPerson.IMPORT_SUCCESS_INFO);
		} else if(entry.getValue()!=null) {
			Iterator<String> msgIt = entry.getValue().iterator();
			while(msgIt.hasNext()){
				String msg = msgIt.next();
				msgs =msgs+ msg + "<br>";
				putToRequest("msgs", msgs);
			}

			// 返回错误提示页面
			entry.getValue().clear();
			return SUCCESS;
		}
		return redirectActionResult(LIST);
	}

	/**
	 * createTime 2013.01.16
	 * @author wangpb
	 * @return
	 */
	public String indexView() {
		Organ organ = getCurrentUser().getOrgan();
		List<Long> secrecyPersonArgs = secrecyPersonModuleService.countSecrecyPersonData(organ);
		putToRequest("secrecyPersonArgs", secrecyPersonArgs);
		putToRequest("organ", organ);
		return SUCCESS;
	}

	/**
	 *
	 * <p>
	 * 方法名：indexViewLayer
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-16 上午11:33:06
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
	public String indexViewLayer(){
		String countType = "layer";
		Organ currentOrgan = this.getCurrentUser().getOrgan();
		List<DictionaryOption> optionList = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "secrecy_level_person");
		Map<Integer, Long> secrecyPersonCountMap = this.secrecyPersonModuleService.countSecrecyPersonData(optionList, currentOrgan, countType );
		putToRequest("secrecyPersonCountMap", secrecyPersonCountMap);
		putToRequest("countType", countType);
		putToRequest("optionList", optionList);
		return SUCCESS;
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
			if(secrecyPersonIds != null && !"".equals(secrecyPersonIds)) {
				// 查询需要上报的数据 domainList
				List<SecrecyPerson> domainList = new ArrayList<SecrecyPerson>();
				String [] secrecyPersonIdArray =  secrecyPersonIds.split(",");
				for (String sid : secrecyPersonIdArray) {
					SecrecyPerson sp = secrecyPersonModuleService.get(sid);
					sp.setReportState(ReportState.REPORT_YES);
					secrecyPersonModuleService.update(sp);
					domainList.add(sp);
				}
				// 查询上报单位
				List<String> organIdList = serverReportMappingService.getReportToOragans(getCurrentUser().getOrgan());
				Iterator<String> i = organIdList.iterator();
				while (i.hasNext()) {
					String organId = i.next();
					//组织需要上报内容
					SecrecyPersonDto secrecyPersonDto = new SecrecyPersonDto();
					List<String> querySqls = new ArrayList<String>();
					for (String secrecyPersonId : secrecyPersonIdArray) {
						querySqls.add("select * from bm_secrecy_person where person_id = '" + secrecyPersonId + "'");
						//因为接收单位并没有同步，所以需要上报。
						querySqls.add("select * from sys_organization where organ_id = '" + getCurrentUser().getOrgan().getOrganId() + "'");
						//单位下所有子部门，因为接收单位并没有同步，所以需要上报。
						querySqls.add("select * from sys_department where organ_id = '" + getCurrentUser().getOrgan().getOrganId() + "'");
						//单位下所有人员，因为要害部门负责人需要，所以需要上报。
						querySqls.add("select * from sys_user_info where organ_id = '" + getCurrentUser().getOrgan().getOrganId() + "'");
						XmlExportor exportor = new XmlExportor();
						exportor.setDataSource(dataSource);
						StringWriter writer = new StringWriter();
						exportor.exportData(writer , querySqls.toArray(new String[]{}));
						secrecyPersonDto.setReportData(writer.toString());
						secrecyPersonDto.setReceiveOrganId(organId);
						secrecyPersonDto.setSecrecyPersonId(secrecyPersonId);
						// 发送数据
						secrecyPersonExchanger.exchange(secrecyPersonDto, getCurrentUser().getOrgan()
								, OrganizationContext.getInstance().getOrganService().get(organId));
					}
				}
			}
			addActionMessage("上报成功！");
		}catch (Exception e) {
			addActionMessage("上报失败！" + e.getMessage());
		}
		return redirectActionResult(LIST);
	}

	/**
	 *
	 * <p>
	 * 涉密人员导入界面
	 * </p>
	 * <p>
	 * 创建人 宋亚非  创建时间  2013-4-18 - 下午15:12:00
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @return
	 */
	public String importSecrecyPerson(){
		return "import";
	}

	/**
	 *
	 * <p>
	 * 涉密人员导入操作
	 * </p>
	 * <p>
	 * 创建人 宋亚非  创建时间  2013-4-18 - 下午15:12:00
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @return
	 */
	public String importingSecrecyPerson(){
		needReload = true;
		UploadFile file;
		String result = "import";
		Boolean status = true;
		StringBuffer formatFaultMessage = new StringBuffer("【错误信息】<br/>");
		User currenUser = getCurrentUser();

		List<UploadFile> files = getUploadFiles("file");
		if(files!=null && files.size()>0){
			file = files.get(0);
		}else{
			return result;
		}
		try{
			List<SecrecyPerson> secrecyPersonList = new ArrayList<SecrecyPerson>();
			HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(file.getFile()));
			HSSFSheet sheet = wb.getSheetAt(0);
			Iterator<Row> rowIter= sheet.rowIterator();
			//Excel内容合法检查
			int filledColunums = 0;
			while(rowIter.hasNext()){
				Row row = rowIter.next();
				if(row.getRowNum()==0){
					filledColunums = row.getLastCellNum();
				}
				if(row.getRowNum()>0){
					//空行判断
					Cell cell = null;
					Boolean flag = true;//true 为空
					for( int i=0; i<filledColunums; i++ ){
						cell = row.getCell(i, HSSFRow.RETURN_BLANK_AS_NULL);
						if( null != cell ){
							flag = false;
							break;
						}
					}
					if( flag ){
						continue;
					}
					SecrecyPerson secrecyPerson = null;
					try {
						secrecyPerson = create(row, formatFaultMessage);
						if(secrecyPerson==null){
							status &= false;
							formatFaultMessage.append("第"+(row.getRowNum()+1)+"行 - 数据转换错误<br/>");
						}
					} catch (Exception e) {
						status &= false;
					}
					if(secrecyPerson!=null){
						//单位正确性检查 和 人员重复信息检查
						Organ organ = OrganizationContext.getInstance().getOrganService().getByName(secrecyPerson.getOrgan().getOrganName());
						if( organ==null ){
							status &= false;
							formatFaultMessage.append(" 第"+(row.getRowNum()+1)+"行- 单位【"+secrecyPerson.getOrgan().getOrganName()+"】不存在;<br/>");
						}else{
							if( !(organ.getOrganName()).equals( getCurrentUser().getOrgan().getOrganName() ) ){
								status &= false;
								formatFaultMessage.append(" 第"+(row.getRowNum()+1)+"行- 单位【"+secrecyPerson.getOrgan().getOrganName()+"】和您所在单位不匹配，请检查;<br/>");
							}else{
								secrecyPerson.setOrgan(organ);
							}
						}
						boolean checkflag = secrecyPersonModuleService.checkSecrecyPerson(secrecyPerson);
						if( checkflag ){
							status &= false;
							formatFaultMessage.append("第"+(row.getRowNum()+1)+"行 -人员【"+ secrecyPerson.getUserInfo().getName() +"】已存在;<br/>");
						}
					}
					secrecyPersonList.add(secrecyPerson);
				}
			}

			if( status ){
				Boolean resultFlag = secrecyPersonModuleService.saveSecrecyPerson( secrecyPersonList, currenUser);
				if( resultFlag ){
					addActionMessage( "导入成功" );
				}else{
					addActionMessage( "导入失败" );
					needReload = false;
				}
			}else{
				addActionMessage( "导入失败" );
				needReload = false;
			}
			this.putToRequest("errorMsg", formatFaultMessage.toString());
		}catch(Exception e){
			e.printStackTrace();
			if(org.apache.commons.lang.StringUtils.isEmpty(e.getMessage())){
				addActionMessage(ExceptionUtils.getStackTrace(e));
			}else{
				addActionMessage(e.getMessage());
			}
			return result;
		}
		return result;
	}

	/**
	 *
	 * <p>
	 * 从Excel行中获得单元格的值
	 * </p>
	 * <p>
	 * 创建人 宋亚非  创建时间  2013-4-18 - 下午17:26:00
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @return
	 */
	private String getCellValue(Cell cell){
		try{
			return cell.getRichStringCellValue().toString();
		}catch (Exception e) {
			return ((int)cell.getNumericCellValue())+"";
		}
	}

	/**
	 *
	 * <p>
	 * 从Excel行中创建对象
	 * </p>
	 * <p>
	 * 创建人 宋亚非  创建时间  2013-4-18 - 下午15:12:00
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @return
	 * @throws Exception
	 */
	private SecrecyPerson create(Row row, StringBuffer formatFaultMessage) throws Exception{
		Boolean status = true;
		StringBuffer errorRow = new StringBuffer("第"+(row.getRowNum()+1)+"行 - ");
		SecrecyPerson secrecyPerson = new SecrecyPerson();
		UserInfo userInfo = new UserInfo();
		Department department = new Department();
		EnumConverter ec = new  EnumConverter();
		DictionaryService dictionaryService = DictionaryContext.getInstance().getDictionaryService();
		Cell organCell = row.getCell(0);
		//单位（必填）
		if(organCell!=null && StringUtils.isNotEmpty(getCellValue(organCell))){
			if( getCellValue(organCell).length()<100 ){
				Organ or = new Organ();
				or.setOrganName(getCellValue(organCell).trim());
				secrecyPerson.setOrgan(or);
			}else{
				status &= false;
				errorRow.append("第一列(单位)内容过长;");
			}
		}else{
			status &= false;
			errorRow.append("第一列(单位)必须填写;");
		}
		//姓名（必填）
		Cell nameCell = row.getCell(1);
		if(nameCell!=null && StringUtils.isNotEmpty(getCellValue(nameCell))){
			if( getCellValue(nameCell).length()<50 ){
				userInfo.setName(getCellValue(nameCell).trim());
			}else{
				status &= false;
				errorRow.append("第二列(姓名)内容过长;");
			}
		}else{
			status &= false;
			errorRow.append("第二列(姓名)必须填写;");
		}
		//性别（必填）
		Cell sexCell = row.getCell(2);
		if(sexCell!=null && StringUtils.isNotEmpty(getCellValue(sexCell))){
			DictionaryOption dictionaryOption = dictionaryService.getOption("person", "sex", getCellValue(sexCell).trim());
			if( dictionaryOption == null ){
				status &= false;
				errorRow.append("第三列(性别)填写错误;");
			}else{
				userInfo.setSex(dictionaryOption.getOptionValue().toString());
			}
		}else{
			status &= false;
			errorRow.append("第三列(性别)必须填写;");
		}
		//民族（必填）
		Cell nationCell = row.getCell(3);
		if(nationCell!=null && StringUtils.isNotEmpty(getCellValue(nationCell))){
			DictionaryOption dictionaryOption = dictionaryService.getOption("person", "nation", getCellValue(nationCell).trim());
			if( dictionaryOption == null ){
				status &= false;
				errorRow.append("第四列(民族)填写错误;");
			}else{
				userInfo.setNation((Nation) LangUtils.toEnum(Nation.class, dictionaryOption.getOptionValue()));
			}
		}else{
			status &= false;
			errorRow.append("第四列(民族)必须填写;");
		}

		//出生年月（必填）
		Cell birthdayCell = row.getCell(4);
		if(birthdayCell!=null && StringUtils.isNotEmpty(getCellValue(birthdayCell))){
			try{
				userInfo.setBirthDate(birthdayCell.getDateCellValue());
			}
			catch (Exception e) {
				status &= false;
				errorRow.append("第五列(出生日期)日期格式不正确，格式：2010-1-1;");
			}
		}else{
			status &= false;
			errorRow.append("第五列(出生日期)必须填写;");
		}

		//学历（必填）
		Cell learningLevelCell = row.getCell(5);
		if(learningLevelCell!=null && StringUtils.isNotEmpty(getCellValue(learningLevelCell))){
			DictionaryOption dictionaryOption = dictionaryService.getOption("person", "learning_level", getCellValue(learningLevelCell).trim());
			if( dictionaryOption == null ){
				status &= false;
				errorRow.append("第六列(文化程度)填写错误;");
			}else{
				userInfo.setLearningLevel(dictionaryOption.getOptionValue());
			}
		}else{
			status &= false;
			errorRow.append("第六列(文化程度)必须填写;");
		}
		//身份证号
		Cell idCardCell = row.getCell(6);
		if(idCardCell!=null && StringUtils.isNotEmpty(getCellValue(idCardCell))){
			if( getCellValue(idCardCell).length()<19 ){
				userInfo.setIdentityCard(getCellValue(idCardCell).trim());
			}else{
				status &= false;
				errorRow.append("第七列(身份证号)内容过长;");
			}
		}
		//政治面貌（必填）
		Cell politicalStatusCell = row.getCell(7);
		if(politicalStatusCell!=null && StringUtils.isNotEmpty(getCellValue(politicalStatusCell)) ){
			DictionaryOption dictionaryOption = dictionaryService.getOption("person", "polity", getCellValue(politicalStatusCell).trim());
			if( dictionaryOption == null ){
				status &= false;
				errorRow.append("第八列(政治面貌)填写错误;");
			}else{
				//secrecyPerson.setPoliticalStatus(dictionaryOption.getOptionValue()+"");
				//secrecyPerson.setPoliticalType(dictionaryOption.getOptionValue()+"");
				userInfo.setPolity(dictionaryOption.getOptionValue()+"");
			}
		}else{
			status &= false;
			errorRow.append("第八列(政治面貌)必须填写;");
		}
		//参加工作时间
		Cell firstWorkDateCell = row.getCell(8);
		if(firstWorkDateCell!=null && StringUtils.isNotEmpty(getCellValue(firstWorkDateCell))){
			try{
				secrecyPerson.setFirstWorkDate(firstWorkDateCell.getDateCellValue());
			}
			catch (Exception e) {
				status &= false;
				errorRow.append("第九列(参加工作时间)日期格式不正确，格式：2010-1-1;");
			}
		}
		//部门名称（必填）
		Cell departmentCell = row.getCell(9);
		if(departmentCell!=null && StringUtils.isNotEmpty(getCellValue(departmentCell)) ){
			if(getCellValue(departmentCell).length()<50){
				department.setDepartmentName(getCellValue(departmentCell).trim());
			}else{
				status &= false;
				errorRow.append("第十列(部门名称)名称过长;");
			}
		}else{
			status &= false;
			errorRow.append("第十列(部门名称)必须填写;");
		}
		//职务
		Cell officeDutyCell = row.getCell(10);
		if(officeDutyCell!=null && StringUtils.isNotEmpty(getCellValue(officeDutyCell))){
			if( getCellValue(officeDutyCell).length()<20 ){
				secrecyPerson.setOfficeDuty(getCellValue(officeDutyCell).trim());
			}else{
				status &= false;
				errorRow.append("第十一列(职务)填写错误;");
			}
		}
		//岗位
		Cell postCell = row.getCell(11);
		if( postCell!=null && StringUtils.isNotEmpty(getCellValue(postCell)) ){
			if( getCellValue(postCell).length()<40 ){
				secrecyPerson.setPost(getCellValue(postCell));
			}else{
				status &= false;
				errorRow.append("第十二列(岗位)内容过长;");
			}
		}
		//人员类型（必填）
		Cell staffCell = row.getCell(12);
		if( staffCell!=null && StringUtils.isNotEmpty(getCellValue(staffCell)) ){
			DictionaryOption dictionaryOption = dictionaryService.getOption("bmp", "person_type", getCellValue(staffCell).trim());
			if( dictionaryOption == null ){
				status &= false;
				errorRow.append("第十三列(人员类型)填写错误;");
			}else{
				secrecyPerson.setPersonType(dictionaryOption.getOptionValue());
			}
		}else{
			status &= false;
			errorRow.append("第十三列(人员类型)必须填写;");
		}
		//行政级别（必填）
		Cell administrativeLevelCell = row.getCell(13);
		if( administrativeLevelCell!=null && StringUtils.isNotEmpty(getCellValue(administrativeLevelCell)) ){
			DictionaryOption dictionaryOption = dictionaryService.getOption("bmp", "person_admin_level", getCellValue(administrativeLevelCell).trim());
			if( dictionaryOption == null ){
				status &= false;
				errorRow.append("第十四列(行政级别)填写错误;"+getCellValue(administrativeLevelCell).trim());
			}else{
				userInfo.setAdministrativeLevel(dictionaryOption.getOptionValue());
			}
		}else{
			status &= false;
			errorRow.append("第十四列(行政级别)必须填写;");
		}

		//技术职称（必填）
		Cell technicTitleLevelCell = row.getCell(14);
		if( technicTitleLevelCell!=null && StringUtils.isNotEmpty(getCellValue(technicTitleLevelCell)) ){
			DictionaryOption dictionaryOption = dictionaryService.getOption("person", "technic_title_level", getCellValue(technicTitleLevelCell).trim());
			if( dictionaryOption == null ){
				status &= false;
				errorRow.append("第十五列(技术职称)填写错误;");
			}else{
				userInfo.setTechnicTitleLevel(dictionaryOption.getOptionValue());
			}
		}else{
			status &= false;
			errorRow.append("第十五列(技术职称)必须填写;");
		}

		//涉密等级（必填）
		Cell secrecyPersonLevelCell = row.getCell(15);
		if( secrecyPersonLevelCell!=null && StringUtils.isNotEmpty(getCellValue(secrecyPersonLevelCell)) ){
			DictionaryOption dictionaryOption = dictionaryService.getOption("bmp", "secrecy_level_person", getCellValue(secrecyPersonLevelCell).trim());
			if( dictionaryOption == null ){
				status &= false;
				errorRow.append("第十六列(涉密等级)填写错误;");
			}else{
				secrecyPerson.setSecrecyPersonLevel(dictionaryOption.getOptionValue());
			}
		}else{
			status &= false;
			errorRow.append("第十六列(涉密等级)必须填写;");
		}
		//办公室电话
		Cell officePhoneCell = row.getCell(16);
		if( officePhoneCell!=null && StringUtils.isNotEmpty(getCellValue(officePhoneCell)) ){
			if(  getCellValue(officePhoneCell).length()<50 ){
				secrecyPerson.setOfficePhone(getCellValue(officePhoneCell));
			}else{
				status &= false;
				errorRow.append("第十七列(办公室电话)填写错误;");
			}
		}
/*		//是否属于要害部门工作人员（必填）
		Cell isSecrecyDepWorkerCell = row.getCell(17);
		if( isSecrecyDepWorkerCell!=null && StringUtils.isNotEmpty(getCellValue(isSecrecyDepWorkerCell)) ){
			if( "是".equals(getCellValue(isSecrecyDepWorkerCell).trim()) || "否".equals(getCellValue(isSecrecyDepWorkerCell).trim()) ){
				if("是".equals(getCellValue(isSecrecyDepWorkerCell).trim())){
					secrecyPerson.setIsSecrecyDepWorker(1);
				}else{
					secrecyPerson.setIsSecrecyDepWorker(0);
				}
			}else{
				status &= false;
				errorRow.append("第十八列(是否属于要害部门工作人员)填写错误;");
			}
		}else{
			status &= false;
			errorRow.append("第十八列(是否属于要害部门工作人员)必须填写;");
		}
*/
		//是否为定密责任人（必填）
		Cell responsiblePersonCell = row.getCell(17);
		if( responsiblePersonCell!=null && StringUtils.isNotEmpty(getCellValue(responsiblePersonCell)) ){
			if( "是".equals(getCellValue(responsiblePersonCell).trim()) || "否".equals(getCellValue(responsiblePersonCell).trim()) ){
				if("是".equals(getCellValue(responsiblePersonCell).trim())){
					secrecyPerson.setResponsiblePerson(1);
				}else{
					secrecyPerson.setResponsiblePerson(0);
				}
			}else{
				status &= false;
				errorRow.append("第十八列(是否为定密责任人)填写错误;");
			}
		}else{
			status &= false;
			errorRow.append("第十八列(是否为定密责任人)必须填写;");
		}

		//签订保密责任书时间
		Cell secSignBookTimeCell = row.getCell(18);
		if(secSignBookTimeCell!=null && StringUtils.isNotEmpty(getCellValue(secSignBookTimeCell))){
			try{
				secrecyPerson.setSecSignBookTime(secSignBookTimeCell.getDateCellValue());
			}
			catch (Exception e) {
				status &= false;
				errorRow.append("第十九列(参加工作时间)日期格式不正确，格式：2010-1-1;");
			}
		}
		//手机
		Cell mobileCell = row.getCell(19);
		if(mobileCell!=null && StringUtils.isNotEmpty(getCellValue(mobileCell)) ){
			if( getCellValue(mobileCell).length()<50 ){
				userInfo.setMobile(getCellValue(mobileCell));
			}else{
				status &= false;
				errorRow.append("第二十列(手机)填写错误;");
			}
		}
		//取得上岗证书时间
		Cell secUppostTimeCell = row.getCell(20);
		if(secUppostTimeCell!=null && StringUtils.isNotEmpty(getCellValue(secUppostTimeCell))){
			try{
				secrecyPerson.setSecUppostTime(secUppostTimeCell.getDateCellValue());
			}
			catch (Exception e) {
				status &= false;
				errorRow.append("第二十一列(参加工作时间)日期格式不正确，格式：2010-1-1;");
			}
		}
		//个人简历
		Cell resumeCell = row.getCell(21);
		if(resumeCell!=null && StringUtils.isNotEmpty(getCellValue(resumeCell)) ){
			if (getCellValue(resumeCell).length()<2000) {
				secrecyPerson.setResume(getCellValue(resumeCell));
			}else{
				status &= false;
				errorRow.append("第二十二列(个人简历)内容过长;");
			}
		}
		//单位审查意见
		Cell organCheckOpinionCell = row.getCell(22);
		if(organCheckOpinionCell!=null && StringUtils.isNotEmpty(getCellValue(organCheckOpinionCell))){
			if (getCellValue(organCheckOpinionCell).length()<500) {
				secrecyPerson.setOrganCheckOpinion(getCellValue(organCheckOpinionCell));
			} else {
				status &= false;
				errorRow.append("第二十三列(单位审查意见)内容过长;");
			}
		}
		secrecyPerson.setUserInfo(userInfo);
		secrecyPerson.setDepartment(department);
		if( !status ){
			errorRow.append("<br/>");
			formatFaultMessage.append(errorRow);
			throw new Exception();
		}
		return secrecyPerson;
	}

	/**
	 *
	 * <p>
	 * 方法名：secrecyLevelChange 密级变更
	 * </p>
	 * <p>
	 * 宋亚非 2013-7-17 下午3:46:40
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
		String tmpIdStr = secrecyPerson.getSecrecyPersonId();
		secrecyPerson = this.secrecyPersonModuleService.get(secrecyPerson.getSecrecyPersonId());
		if( secrecyPerson==null ){
			secrecyPerson = partPersonModuleService.get(tmpIdStr).getSecrecyPerson();
		}
		return SUCCESS;
	}

	/**
	 *
	 * <p>
	 * 方法名：secrecyLevelChanging 密级变更保存
	 * </p>
	 * <p>
	 * 宋亚非 2013-7-17 下午3:47:25
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
		String secrecyPersonId = secrecyPersonLevelChange.getSecrecyPersonId().getSecrecyPersonId();
		secrecyPerson = this.secrecyPersonModuleService.get(secrecyPersonId);

		this.secrecyPersonLevelChange.setCreateDate(new Date(System.currentTimeMillis()));
		this.secrecyPersonLevelChange.setCreatePerson(getCurrentUser());
		secrecyPersonLevelChange.setSecrecyPersonId(secrecyPerson);
		this.secrecyPersonLevelChangeService.save(secrecyPersonLevelChange);
		this.addActionMessage("保存成功");
		secrecyPerson.setSecrecyPersonLevel(secrecyPersonLevelChange.getCurrentLevel());
		this.secrecyPersonModuleService.update(secrecyPerson);
		this.needReload = true;
		return SUCCESS;
	}

	/**
	 *
	 * <p>
	 * 方法名：secrecyLevelChangeHistory 密级变更历史
	 * </p>
	 * <p>
	 * 宋亚非 2013-7-17 下午3:47:38
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
		PageSortModel<SecrecyPersonLevelChange> psm = new PageSortModel<SecrecyPersonLevelChange>(getRequest(), "secrecyLevelChangeHistoryTable");

		String chageDateStartStr = getRequest().getParameter("changeDateStart");
		String chageDateEndStr = getRequest().getParameter("changeDateEnd");
		Date changeDateStart = null;
		Date changeDateEnd = null;
		if( chageDateStartStr!=null && !"".equals(chageDateStartStr) ){
			changeDateStart = DateUtils.parse(chageDateStartStr, "yyyy-MM-dd");
		}
		if( chageDateEndStr!=null && !"".equals(chageDateEndStr) ){
			changeDateEnd = DateUtils.parse(chageDateEndStr, "yyyy-MM-dd");
		}

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

		//要害部[门]不为空，查询该要害部[门]下的所有涉密人员的密级变更记录   梁文杰2013-07-20
		if(departmentId!=null && !departmentId.equals("")) {
			Department depart = new Department();
			depart.setDepartmentId(departmentId);
			if(secrecyPersonLevelChange==null) {//密级变更对象为空  初始化一个
				secrecyPersonLevelChange = new SecrecyPersonLevelChange();
				if(secrecyPerson == null) {
					secrecyPerson = new SecrecyPerson();
					secrecyPerson.setDepartment(depart);
					secrecyPersonLevelChange.setSecrecyPersonId(secrecyPerson);
				}else {
					secrecyPerson.setDepartment(depart);
					secrecyPersonLevelChange.setSecrecyPersonId(secrecyPerson);
				}
			}else {
				if(secrecyPerson == null) {
					secrecyPerson = new SecrecyPerson();
					secrecyPerson.setDepartment(depart);
					secrecyPersonLevelChange.setSecrecyPersonId(secrecyPerson);
				}else {
					secrecyPerson.setDepartment(depart);
					secrecyPersonLevelChange.setSecrecyPersonId(secrecyPerson);
				}
			}
		}


		// 获取当前单位涉密人员列表
		List<SecrecyPersonLevelChange> secrecyLevelChangeHistoryList = secrecyPersonLevelChangeService.
					getAllPageList(psm, secrecyPersonLevelChange, organ, district, checkLower, changeDateStart, changeDateEnd);
		putToRequest("secrecyLevelChangeHistoryList", secrecyLevelChangeHistoryList);
		putToRequest("changeDateStart", changeDateStart);
		putToRequest("changeDateEnd", changeDateEnd);
		return "success";
	}

	/**
	 *
	 * <p>
	 * 方法名：decryption 人员脱密
	 * </p>
	 * <p>
	 * 宋亚非 2013-7-17 下午3:48:09
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
	public String decryption(){
		String tmpIdStr = secrecyPerson.getSecrecyPersonId();
		secrecyPerson = this.secrecyPersonModuleService.get(secrecyPerson.getSecrecyPersonId());
		if( secrecyPerson==null ){
			secrecyPerson = partPersonModuleService.get(tmpIdStr).getSecrecyPerson();
		}
		return "success";
	}

	/**
	 *
	 * <p>
	 * 方法名：decryptioning 人员脱密保存
	 * </p>
	 * <p>
	 * 宋亚非 2013-7-17 下午3:48:20
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
	public String decryptioning(){
		this.secrecyPersonDecryption.setCreatePerson(getCurrentUser());
		this.secrecyPersonDecryption.setCreateDate(new Date());
		this.secrecyPersonDecryptionService.save(secrecyPersonDecryption);
		this.addActionMessage("保存成功");
		secrecyPerson = this.secrecyPersonModuleService.get(secrecyPersonDecryption.getSecrecyPersonId().getSecrecyPersonId());

		secrecyPerson.setSecrecyStatus(SecrecyPersonAction.SECRECY_STATUS_DECRYPTION);
		this.secrecyPersonModuleService.update(secrecyPerson);
		this.needReload = true;
		return "success";
	}

	/**
	 *
	 * <p>
	 * 方法名：decryptionHistory 脱密人员列表
	 * </p>
	 * <p>
	 * 宋亚非 2013-7-17 下午3:48:39
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
	public String decryptionHistory(){
		PageSortModel<SecrecyPersonDecryption> psm = new PageSortModel<SecrecyPersonDecryption>(getRequest(), "secrecyPersonDecryptionTable");

		String decryptionStartStartStr = getRequest().getParameter("decryptionStartStart");
		String decryptionStartEndStr = getRequest().getParameter("decryptionStartEnd");
		String decryptionEndStartStr = getRequest().getParameter("decryptionEndStart");
		String decryptionEndEndStr = getRequest().getParameter("decryptionEndEnd");

		Date decryptionStartStart = null;
		Date decryptionStartEnd = null;
		Date decryptionEndStart = null;
		Date decryptionEndEnd = null;

		if( StringUtils.isNotEmpty(decryptionStartStartStr) ){
			decryptionStartStart = DateUtils.parse(decryptionStartStartStr, "yyyy-MM-dd");
		}
		if( StringUtils.isNotEmpty(decryptionStartEndStr) ){
			decryptionStartEnd = DateUtils.parse(decryptionStartEndStr, "yyyy-MM-dd");
		}
		if( StringUtils.isNotEmpty(decryptionEndStartStr) ){
			decryptionEndStart = DateUtils.parse(decryptionStartStartStr, "yyyy-MM-dd");
		}
		if( StringUtils.isNotEmpty(decryptionEndEndStr) ){
			decryptionEndEnd = DateUtils.parse(decryptionStartStartStr, "yyyy-MM-dd");
		}

		Map<String,Date> dateParams = new HashMap<String, Date>();
		dateParams.put("decryptionStartStart", decryptionStartStart);
		dateParams.put("decryptionStartEnd", decryptionStartEnd);
		dateParams.put("decryptionEndStart", decryptionEndStart);
		dateParams.put("decryptionEndEnd", decryptionEndEnd);

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

		//要害部[门]不为空，查询该要害部[门]下的所有涉密人员的脱密记录  梁文杰2013-07-20修改
		if(departmentId!=null && !departmentId.equals("")) {
			Department depart = new Department();
			depart.setDepartmentId(departmentId);
			if(secrecyPersonDecryption==null) {//密级解除对象为空  初始化一个
				secrecyPersonDecryption = new SecrecyPersonDecryption();
				if(secrecyPerson == null) {
					secrecyPerson = new SecrecyPerson();
					secrecyPerson.setDepartment(depart);
					secrecyPersonDecryption.setSecrecyPersonId(secrecyPerson);
				}else {
					secrecyPerson.setDepartment(depart);
					secrecyPersonDecryption.setSecrecyPersonId(secrecyPerson);
				}
			}else {
				if(secrecyPerson == null) {
					secrecyPerson = new SecrecyPerson();
					secrecyPerson.setDepartment(depart);
					secrecyPersonDecryption.setSecrecyPersonId(secrecyPerson);
				}else {
					secrecyPerson.setDepartment(depart);
					secrecyPersonDecryption.setSecrecyPersonId(secrecyPerson);
				}
			}
		}

		// 获取当前单位涉密人员列表
		List<SecrecyPersonDecryption> secrecyPersonDecyptionList =
				secrecyPersonDecryptionService.getPageAllList(psm, secrecyPersonDecryption, organ, district, checkLower, dateParams, SecrecyPersonAction.SECRECY_STATUS_DECRYPTION);
		putToRequest("secrecyPersonDecyptionList", secrecyPersonDecyptionList);
		putToRequest("decryptionStartStart", decryptionStartStart);
		putToRequest("decryptionStartEnd", decryptionStartEnd);
		putToRequest("decryptionEndStart", decryptionEndStart);
		putToRequest("decryptionEndEnd", decryptionEndEnd);


		return SUCCESS;
	}

	/**
	 *
	 * <p>
	 * 方法名：personSecrecyLevelChangeHis 通过人员id或着密级变更历史id获取密级变更历史数据
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-15 上午9:05:08
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
	public String personSecrecyLevelChangeHis(){
		if( secrecyPerson!=null && secrecyPerson.getSecrecyPersonId()!=null && !"".equals(secrecyPerson.getSecrecyPersonId()) ){
			List<SecrecyPersonLevelChange> secrecyPersonLevelChangeList = this.secrecyPersonLevelChangeService.findPersonList(secrecyPerson.getSecrecyPersonId(),null);
			this.putToRequest("secrecyPersonLevelChangeList", secrecyPersonLevelChangeList);
		}
		if(secrecyPersonLevelChange!=null && secrecyPersonLevelChange.getSecrecyPersonLevelChangeId()!=null && !"".equals(secrecyPersonLevelChange.getSecrecyPersonLevelChangeId())){
			List<SecrecyPersonLevelChange> secrecyPersonLevelChangeList = this.secrecyPersonLevelChangeService.findPersonList(null,secrecyPersonLevelChange.getSecrecyPersonLevelChangeId());
			this.putToRequest("secrecyPersonLevelChangeList", secrecyPersonLevelChangeList);
		}
		return SUCCESS;
	}


	/**
	 *
	 * <p>
	 * 方法名：personMsg 根据人员id返回人员信息
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-15 上午11:12:32
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
	public String personMsg(){
		if( secrecyPerson!=null && secrecyPerson.getSecrecyPersonId()!=null && !"".equals(secrecyPerson.getSecrecyPersonId()) ){
			secrecyPerson = this.secrecyPersonModuleService.get(secrecyPerson.getSecrecyPersonId());
		}
		return SUCCESS;
	}

	/**
	 *
	 * <p>
	 * 方法名：personDecryptionHis 根据涉密人员id获取脱密历史数据
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-15 上午11:12:56
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
	public String personDecryptionHis(){
		if( secrecyPerson!=null && secrecyPerson.getSecrecyPersonId()!=null && !"".equals(secrecyPerson.getSecrecyPersonId()) ){
			List<SecrecyPersonDecryption> secrecyPersonDecryptionList = this.secrecyPersonDecryptionService.findPersonList(secrecyPerson.getSecrecyPersonId());
			this.putToRequest("secrecyPersonDecryptionList", secrecyPersonDecryptionList);
		}
		return SUCCESS;
	}

	/**
	 *
	 * <p>
	 * 方法名：secrecyLevelChangedetail
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-15 上午11:22:28
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
	public String secrecyLevelChangedetail(){
		secrecyPersonLevelChange = this.secrecyPersonLevelChangeService.get(secrecyPersonLevelChange.getSecrecyPersonLevelChangeId());
		return SUCCESS;
	}

	/**
	 *
	 * <p>
	 * 方法名：secrecyDecryptionDetail 涉密人员脱密信息详情
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-15 下午1:13:58
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
	public String secrecyDecryptionDetail(){
		secrecyPersonDecryption = this.secrecyPersonDecryptionService.get(secrecyPersonDecryption.getSecrecyPersonDecryptionId());
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
		PageSortModel<SecrecyPerson> psm = new PageSortModel<SecrecyPerson>(this.getRequest(), "secrecyPersonList");
		List<SecrecyPerson> secrecyPersonList = this.secrecyPersonModuleService.findPageAllList(psm, secrecyPerson, district);

		this.putToRequest("secrecyPersonList", secrecyPersonList);
		this.putToRequest("districtCode", district.getDistrictCode());
		return SUCCESS;
	}*/

	public String query_main(){
		return SUCCESS;
	}


	/********************************统计部分***********************************/

	//年龄段查询
	public String statisticsByAgeCurrent(){
		List<Map<String, Integer>> ageRange = ageRangDeal();

		String districtCode = getRequest().getParameter("districtCode");
		if (StringUtils.isEmpty(districtCode)) {
			districtCode = getCurrentUser().getOrgan().getDistrict().getDistrictCode();
		}

		String organId = getRequest().getParameter("organId");
		if( !(organId!=null && !"".equals(organId)) ){
			organId = getCurrentUser().getOrgan().getOrganId();
		}

		String countScope = getRequest().getParameter("countScope");
		if( countScope==null || "".equals(countScope) ){
			countScope = "organ";
		}

		List<Map<String, List<Map<Integer, Integer>>>> ageStatisticsList = this.secrecyPersonModuleService.statisticsByAge(ageRange, null, organId, SECRECY_STATUS_NOW, countScope);
		this.putToRequest("ageStatisticsList", ageStatisticsList);
		this.putToRequest("expBtnFlag", expBtnFlag);
		this.putToRequest("countScope", countScope);
		return "success";
	}

	public String statisticsByAge(){
		List<Map<String, Integer>> ageRange = ageRangDeal();

		String districtCode = getRequest().getParameter("districtCode");
		if (StringUtils.isEmpty(districtCode)) {
			districtCode = getCurrentUser().getOrgan().getDistrict().getDistrictCode();
		}

		String organId = getRequest().getParameter("organId");

		String countScope = getRequest().getParameter("countScope");
		if( countScope==null || "".equals(countScope) ){
			countScope = "organ";
		}

		List<Map<String, List<Map<Integer, Integer>>>> ageStatisticsList = this.secrecyPersonModuleService.statisticsByAge(ageRange, districtCode, organId,SECRECY_STATUS_NOW,countScope);
		this.putToRequest("ageStatisticsList", ageStatisticsList);
		this.putToRequest("expBtnFlag", expBtnFlag);
		return "success";
	}

	public String statisticsByLayerOfAge(){
		List<Map<String, Integer>> ageRange = ageRangDeal();

		String districtCode = getRequest().getParameter("districtCode");
		String layer = null;
		if (StringUtils.isEmpty(districtCode)) {
			layer = getCurrentUser().getOrgan().getDistrict().getLayer();
		}else{
			District district = this.secrecyPersonModuleService.get(districtCode, District.class);
			if( district!=null ){
				layer = district.getLayer();
			}
		}
		String organId = getRequest().getParameter("organId");

		String countScope = getRequest().getParameter("countScope");
		if( countScope==null || "".equals(countScope) ){
			countScope = "organ";
		}

		List<Map<String, List<Map<Integer, Integer>>>> ageStatisticsList = this.secrecyPersonModuleService.statisticsByLayerOfAge(ageRange, layer, organId,SECRECY_STATUS_NOW, countScope);
		this.putToRequest("ageStatisticsList", ageStatisticsList);
		this.putToRequest("countScope", countScope);
		return "success";
	}

	public String statisticsByAgeOfAll(){
		List<Map<String, Integer>> ageRange = ageRangDeal();

		String districtCode = getRequest().getParameter("districtCode");
		if (StringUtils.isEmpty(districtCode)) {
			districtCode = getCurrentUser().getOrgan().getDistrict().getDistrictCode();
		}else{
			District district = this.secrecyPersonModuleService.get(districtCode, District.class);
			if( district!=null ){
				districtCode = district.getDistrictCode();
			}
		}
		String organId = getRequest().getParameter("organId");

		String countScope = getRequest().getParameter("countScope");
		if( countScope==null || "".equals(countScope) ){
			countScope = "organ";
		}

		Map<String, List<Map<String, List<Map<Integer, Integer>>>>> ageStatisticsList = this.secrecyPersonModuleService.statisticsByAgeOfAll(ageRange, districtCode, organId, SECRECY_STATUS_NOW, countScope);
		this.putToRequest("ageRange", ageRange);
		this.putToRequest("ageStatisticsList", ageStatisticsList);
		this.putToRequest("countScope", countScope);
		return "success";
	}

	public String statisticsByAgeOfDistrict(){
		List<Map<String, Integer>> ageRange = ageRangDeal();

		String districtCode = getRequest().getParameter("districtCode");
		if (StringUtils.isEmpty(districtCode)) {
			districtCode = getCurrentUser().getOrgan().getDistrict().getDistrictCode();
		}
		String organId = getRequest().getParameter("organId");

		String countScope = getRequest().getParameter("countScope");
		if( countScope==null || "".equals(countScope) ){
			countScope = "organ";
		}

		Map<String, List<Map<String, List<Map<Integer, Integer>>>>> ageStatisticsList = this.secrecyPersonModuleService.statisticsByAgeOfDistrict(ageRange, districtCode, organId,SECRECY_STATUS_NOW, countScope);
		this.putToRequest("ageRange", ageRange);
		this.putToRequest("ageStatisticsList", ageStatisticsList);
		return "success";
	}

	public String statisticsByAgeOfOrgan(){
		List<Map<String, Integer>> ageRange = ageRangDeal();

		String districtCode = getRequest().getParameter("districtCode");
		if (StringUtils.isEmpty(districtCode)) {
			districtCode = getCurrentUser().getOrgan().getDistrict().getDistrictCode();
		}
		String organId = getRequest().getParameter("organId");

		String countScope = getRequest().getParameter("countScope");
		if( countScope==null || "".equals(countScope) ){
			countScope = "organ";
		}

		Map<String, List<Map<String, List<Map<Integer, Integer>>>>> ageStatisticsList = this.secrecyPersonModuleService.statisticsByAgeOfOrgan(ageRange, districtCode, organId,SECRECY_STATUS_NOW,countScope);
		this.putToRequest("ageRange", ageRange);
		this.putToRequest("ageStatisticsList", ageStatisticsList);
		this.putToRequest("countScope", countScope);
		return "success";
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	//按民族查询
	public String statisticsByNationCurrent(){
		String districtCode = getRequest().getParameter("districtCode");
		if (StringUtils.isEmpty(districtCode)) {
			districtCode = getCurrentUser().getOrgan().getDistrict().getDistrictCode();
		}
		List<DictionaryOption> nationOptions = DictionaryContext.getInstance().getDictionaryService().getOptionList("person", "nation");
		String organId = getRequest().getParameter("organId");
		if( !(organId!=null && !"".equals(organId)) ){
			organId = getCurrentUser().getOrgan().getOrganId();
		}

		String countScope = getRequest().getParameter("countScope");
		if( countScope==null || "".equals(countScope) ){
			countScope = "organ";
		}

		List<Map<String, Map<Integer, Integer>>> nationStatisticsList = this.secrecyPersonModuleService.statisticsByNation(nationOptions, districtCode, organId,SECRECY_STATUS_NOW, countScope);
		this.putToRequest("nationStatisticsList", nationStatisticsList);
		this.putToRequest("countScope", countScope);
		return "success";
	}

	public String statisticsByNation(){
		String districtCode = getRequest().getParameter("districtCode");
		if (StringUtils.isEmpty(districtCode)) {
			districtCode = getCurrentUser().getOrgan().getDistrict().getDistrictCode();
		}
		List<DictionaryOption> nationOptions = DictionaryContext.getInstance().getDictionaryService().getOptionList("person", "nation");
		String organId = getRequest().getParameter("organId");

		String countScope = getRequest().getParameter("countScope");
		if( countScope==null || "".equals(countScope) ){
			countScope = "organ";
		}

		List<Map<String, Map<Integer, Integer>>> nationStatisticsList = this.secrecyPersonModuleService.statisticsByNation(nationOptions, districtCode, organId,SECRECY_STATUS_NOW, countScope);
		this.putToRequest("nationStatisticsList", nationStatisticsList);
		this.putToRequest("countScope", countScope);
		return "success";
	}

	public String statisticsByLayerOfNation(){
		String districtCode = getRequest().getParameter("districtCode");
		String layer = null;
		if (StringUtils.isEmpty(districtCode)) {
			layer = getCurrentUser().getOrgan().getDistrict().getLayer();
		}else{
			District district = this.secrecyPersonModuleService.get(districtCode, District.class);
			if( district!=null ){
				layer = district.getLayer();
			}
		}

		String countScope = getRequest().getParameter("countScope");
		if( countScope==null || "".equals(countScope) ){
			countScope = "organ";
		}

		List<DictionaryOption> nationOptions = DictionaryContext.getInstance().getDictionaryService().getOptionList("person", "nation");
		String organId = getRequest().getParameter("organId");
		List<Map<String, Map<Integer, Integer>>> nationStatisticsList = this.secrecyPersonModuleService.statisticsByLayerOfNation(nationOptions, layer, organId,SECRECY_STATUS_NOW, countScope);
		this.putToRequest("nationStatisticsList", nationStatisticsList);
		this.putToRequest("countScope", countScope);
		return "success";
	}

	public String statisticsByNationOfAll(){
		String districtCode = getRequest().getParameter("districtCode");
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		}else{
			district = this.secrecyPersonModuleService.get(districtCode, District.class);
		}

		String countScope = getRequest().getParameter("countScope");
		if( countScope==null || "".equals(countScope) ){
			countScope = "organ";
		}

		List<DictionaryOption> nationOptions = DictionaryContext.getInstance().getDictionaryService().getOptionList("person", "nation");
		String organId = getRequest().getParameter("organId");
		Map<String, List<Map<String, Map<Integer, Integer>>>> nationStatisticsList = this.secrecyPersonModuleService.statisticsByNationOfAll(nationOptions, district, organId,SECRECY_STATUS_NOW, countScope);
		this.putToRequest("nationOptions", nationOptions);
		this.putToRequest("nationStatisticsList", nationStatisticsList);
		this.putToRequest("countScope", countScope);
		return "success";
	}

	public String statisticsByNationOfDistrict(){
		String districtCode = getRequest().getParameter("districtCode");
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		}else{
			district = this.secrecyPersonModuleService.get(districtCode, District.class);
		}

		String countScope = getRequest().getParameter("countScope");
		if( countScope==null || "".equals(countScope) ){
			countScope = "organ";
		}

		List<DictionaryOption> nationOptions = DictionaryContext.getInstance().getDictionaryService().getOptionList("person", "nation");
		String organId = getRequest().getParameter("organId");
		Map<String, List<Map<String, Map<Integer, Integer>>>> nationStatisticsList = this.secrecyPersonModuleService.statisticsByNationOfDistrict(nationOptions, district, organId,SECRECY_STATUS_NOW, countScope);
		this.putToRequest("nationOptions", nationOptions);
		this.putToRequest("nationStatisticsList", nationStatisticsList);
		return "success";
	}

	public String statisticsByNationOfOrgan(){
		String districtCode = getRequest().getParameter("districtCode");
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		}else{
			district = this.secrecyPersonModuleService.get(districtCode, District.class);
		}
		List<DictionaryOption> nationOptions = DictionaryContext.getInstance().getDictionaryService().getOptionList("person", "nation");
		String organId = getRequest().getParameter("organId");

		String countScope = getRequest().getParameter("countScope");
		if( countScope==null || "".equals(countScope) ){
			countScope = "organ";
		}

		Map<String, List<Map<String, Map<Integer, Integer>>>> nationStatisticsList = this.secrecyPersonModuleService.statisticsByNationOfOrgan(nationOptions, district, organId,SECRECY_STATUS_NOW, countScope);
		this.putToRequest("nationOptions", nationOptions);
		this.putToRequest("nationStatisticsList", nationStatisticsList);
		this.putToRequest("countScope", countScope);
		return "success";
	}

	/////////////////////////////////////////////////////////////////////////////////
	//按学历查询
	public String statisticsByLeanLevelCurrent(){
		String districtCode = getRequest().getParameter("districtCode");
		if (StringUtils.isEmpty(districtCode)) {
			districtCode = getCurrentUser().getOrgan().getDistrict().getDistrictCode();
		}
		List<DictionaryOption> leaningLevelOptions = DictionaryContext.getInstance().getDictionaryService().getOptionList("person", "learning_level");
		String organId = getRequest().getParameter("organId");
		if( !(organId!=null && !"".equals(organId)) ){
			organId = getCurrentUser().getOrgan().getOrganId();
		}

		String countScope = getRequest().getParameter("countScope");
		if( countScope==null || "".equals(countScope) ){
			countScope = "organ";
		}

		List<Map<String, Map<Integer, Integer>>> statisticsList = this.secrecyPersonModuleService.statisticsByLeanLevel(leaningLevelOptions, districtCode, organId,SECRECY_STATUS_NOW, countScope);
		this.putToRequest("statisticsList", statisticsList);
		this.putToRequest("countScope", countScope);
		return "success";
	}

	public String statisticsByLeanLevel(){
		String districtCode = getRequest().getParameter("districtCode");
		if (StringUtils.isEmpty(districtCode)) {
			districtCode = getCurrentUser().getOrgan().getDistrict().getDistrictCode();
		}
		List<DictionaryOption> leaningLevelOptions = DictionaryContext.getInstance().getDictionaryService().getOptionList("person", "learning_level");
		String organId = getRequest().getParameter("organId");

		String countScope = getRequest().getParameter("countScope");
		if( countScope==null || "".equals(countScope) ){
			countScope = "organ";
		}

		List<Map<String, Map<Integer, Integer>>> statisticsList = this.secrecyPersonModuleService.statisticsByLeanLevel(leaningLevelOptions, districtCode, organId,SECRECY_STATUS_NOW, countScope);
		this.putToRequest("statisticsList", statisticsList);
		this.putToRequest("countScope", countScope);
		return "success";
	}

	public String statisticsByLayerOfLeanLevel(){
		String districtCode = getRequest().getParameter("districtCode");
		String layer = null;
		if (StringUtils.isEmpty(districtCode)) {
			layer = getCurrentUser().getOrgan().getDistrict().getLayer();
		}else{
			District district = this.secrecyPersonModuleService.get(districtCode, District.class);
			if( district!=null ){
				layer = district.getLayer();
			}
		}

		String countScope = getRequest().getParameter("countScope");
		if( countScope==null || "".equals(countScope) ){
			countScope = "organ";
		}

		List<DictionaryOption> leaningLevelOptions = DictionaryContext.getInstance().getDictionaryService().getOptionList("person", "learning_level");
		String organId = getRequest().getParameter("organId");
		List<Map<String, Map<Integer, Integer>>> statisticsList = this.secrecyPersonModuleService.statisticsByLayerOfLeanLevel(leaningLevelOptions, layer, organId,SECRECY_STATUS_NOW, countScope);
		this.putToRequest("statisticsList", statisticsList);
		this.putToRequest("countScope", countScope);
		return "success";
	}

	public String statisticsByLearnLevelOfAll(){
		String districtCode = getRequest().getParameter("districtCode");
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		}else{
			district = this.secrecyPersonModuleService.get(districtCode, District.class);
		}

		String countScope = getRequest().getParameter("countScope");
		if( countScope==null || "".equals(countScope) ){
			countScope = "organ";
		}

		List<DictionaryOption> leaningLevelOptions = DictionaryContext.getInstance().getDictionaryService().getOptionList("person", "learning_level");
		String organId = getRequest().getParameter("organId");
		Map<String, List<Map<String, Map<Integer, Integer>>>> statisticsList = this.secrecyPersonModuleService.statisticsByLearnLevelOfAll(leaningLevelOptions, district, organId,SECRECY_STATUS_NOW, countScope);
		this.putToRequest("leaningLevelOptions", leaningLevelOptions);
		this.putToRequest("statisticsList", statisticsList);
		this.putToRequest("countScope", countScope);
		return "success";
	}

	public String statisticsByLearnLevelOfDistrict(){
		String districtCode = getRequest().getParameter("districtCode");
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		}else{
			district = this.secrecyPersonModuleService.get(districtCode, District.class);
		}

		String countScope = getRequest().getParameter("countScope");
		if( countScope==null || "".equals(countScope) ){
			countScope = "organ";
		}

		List<DictionaryOption> leaningLevelOptions = DictionaryContext.getInstance().getDictionaryService().getOptionList("person", "learning_level");
		String organId = getRequest().getParameter("organId");
		Map<String, List<Map<String, Map<Integer, Integer>>>> statisticsList = this.secrecyPersonModuleService.statisticsByLearnLevelOfDistrict(leaningLevelOptions, district, organId,SECRECY_STATUS_NOW, countScope);
		this.putToRequest("leaningLevelOptions", leaningLevelOptions);
		this.putToRequest("statisticsList", statisticsList);
		return "success";
	}

	public String statisticsByLearnLevelOfOrgan(){
		String districtCode = getRequest().getParameter("districtCode");
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		}else{
			district = this.secrecyPersonModuleService.get(districtCode, District.class);
		}
		List<DictionaryOption> leaningLevelOptions = DictionaryContext.getInstance().getDictionaryService().getOptionList("person", "learning_level");
		String organId = getRequest().getParameter("organId");

		String countScope = getRequest().getParameter("countScope");
		if( countScope==null || "".equals(countScope) ){
			countScope = "organ";
		}

		Map<String, List<Map<String, Map<Integer, Integer>>>> statisticsList = this.secrecyPersonModuleService.statisticsByLearnLevelOfOrgan(leaningLevelOptions, district, organId,SECRECY_STATUS_NOW, countScope);
		this.putToRequest("leaningLevelOptions", leaningLevelOptions);
		this.putToRequest("statisticsList", statisticsList);
		this.putToRequest("countScope", countScope);
		return "success";
	}

	///////////////////////////////////////////////////////////////////////////////////////
	//按政治面貌查询
	public String statisticsByPolityCurrent(){
		String districtCode = getRequest().getParameter("districtCode");
		if (StringUtils.isEmpty(districtCode)) {
			districtCode = getCurrentUser().getOrgan().getDistrict().getDistrictCode();
		}
		List<DictionaryOption> polityOptions = polityOptionsDeal();
		String organId = getRequest().getParameter("organId");
		if( !(organId!=null && !"".equals(organId)) ){
			organId = getCurrentUser().getOrgan().getOrganId();
		}

		String countScope = getRequest().getParameter("countScope");
		if( countScope==null || "".equals(countScope) ){
			countScope = "organ";
		}

		List<Map<String, Map<Integer, Integer>>> statisticsList = this.secrecyPersonModuleService.statisticsByPolity(polityOptions, districtCode, organId,SECRECY_STATUS_NOW, countScope);
		this.putToRequest("statisticsList", statisticsList);
		this.putToRequest("countScope", countScope);
		return "success";
	}

	public String statisticsByPolity(){
		String districtCode = getRequest().getParameter("districtCode");
		if (StringUtils.isEmpty(districtCode)) {
			districtCode = getCurrentUser().getOrgan().getDistrict().getDistrictCode();
		}
		List<DictionaryOption> polityOptions = polityOptionsDeal();
		String organId = getRequest().getParameter("organId");
		String countScope = getRequest().getParameter("countScope");
		if( countScope==null || "".equals(countScope) ){
			countScope = "organ";
		}

		List<Map<String, Map<Integer, Integer>>> statisticsList = this.secrecyPersonModuleService.statisticsByPolity(polityOptions, districtCode, organId,SECRECY_STATUS_NOW,countScope);
		this.putToRequest("statisticsList", statisticsList);
		this.putToRequest("countScope", countScope);
		return "success";
	}

	public String statisticsByLayerOfPolity(){
		String districtCode = getRequest().getParameter("districtCode");
		String layer = null;
		if (StringUtils.isEmpty(districtCode)) {
			layer = getCurrentUser().getOrgan().getDistrict().getLayer();
		}else{
			District district = this.secrecyPersonModuleService.get(districtCode, District.class);
			if( district!=null ){
				layer = district.getLayer();
			}
		}

		String countScope = getRequest().getParameter("countScope");
		if( countScope==null || "".equals(countScope) ){
			countScope = "organ";
		}

		List<DictionaryOption> polityOptions = polityOptionsDeal();
		String organId = getRequest().getParameter("organId");
		List<Map<String, Map<Integer, Integer>>> statisticsList = this.secrecyPersonModuleService.statisticsByLayerOfPolity(polityOptions, layer, organId, SECRECY_STATUS_NOW, countScope);
		this.putToRequest("statisticsList", statisticsList);
		this.putToRequest("countScope", countScope);
		return "success";
	}

	public String statisticsByPolityOfAll(){
		String districtCode = getRequest().getParameter("districtCode");
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		}else{
			district = this.secrecyPersonModuleService.get(districtCode, District.class);
		}

		String countScope = getRequest().getParameter("countScope");
		if( countScope==null || "".equals(countScope) ){
			countScope = "organ";
		}

		List<DictionaryOption> polityOptions = polityOptionsDeal();
		String organId = getRequest().getParameter("organId");
		Map<String, List<Map<String, Map<Integer, Integer>>>> statisticsList = this.secrecyPersonModuleService.statisticsByPolityOfAll(polityOptions, district, organId,SECRECY_STATUS_NOW, countScope);
		this.putToRequest("options", polityOptions);
		this.putToRequest("statisticsList", statisticsList);
		this.putToRequest("countScope", countScope);
		return "success";
	}

	public String statisticsByPolityOfDistrict(){
		String districtCode = getRequest().getParameter("districtCode");
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		}else{
			district = this.secrecyPersonModuleService.get(districtCode, District.class);
		}

		String countScope = getRequest().getParameter("countScope");
		if( countScope==null || "".equals(countScope) ){
			countScope = "organ";
		}

		List<DictionaryOption> polityOptions = polityOptionsDeal();
		String organId = getRequest().getParameter("organId");
		Map<String, List<Map<String, Map<Integer, Integer>>>> statisticsList = this.secrecyPersonModuleService.statisticsByPolityOfDistrict(polityOptions, district, organId,SECRECY_STATUS_NOW, countScope);
		this.putToRequest("options", polityOptions);
		this.putToRequest("statisticsList", statisticsList);
		return "success";
	}

	public String statisticsByPolityOfOrgan(){
		String districtCode = getRequest().getParameter("districtCode");
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		}else{
			district = this.secrecyPersonModuleService.get(districtCode, District.class);
		}
		List<DictionaryOption> polityOptions = polityOptionsDeal();
		String organId = getRequest().getParameter("organId");
		String countScope = getRequest().getParameter("countScope");
		if( countScope==null || "".equals(countScope) ){
			countScope = "organ";
		}

		Map<String, List<Map<String, Map<Integer, Integer>>>> statisticsList = this.secrecyPersonModuleService.statisticsByPolityOfOrgan(polityOptions, district, organId,SECRECY_STATUS_NOW, countScope);
		this.putToRequest("options", polityOptions);
		this.putToRequest("statisticsList", statisticsList);
		this.putToRequest("countScope", countScope);
		return "success";
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////
	//按职级查询
	public String statisticsByAdminTechLevelCurrent(){
		String districtCode = getRequest().getParameter("districtCode");
		if (StringUtils.isEmpty(districtCode)) {
			districtCode = getCurrentUser().getOrgan().getDistrict().getDistrictCode();
		}
		String organId = getRequest().getParameter("organId");
		if( !(organId!=null && !"".equals(organId)) ){
			organId = getCurrentUser().getOrgan().getOrganId();
		}

		String countScope = getRequest().getParameter("countScope");
		if( countScope==null || "".equals(countScope) ){
			countScope = "organ";
		}

		//行政级别
		List<DictionaryOption> adminLevelOptions = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "person_admin_level");
		//技术职称
		List<DictionaryOption> technicTitleLevelOptions = DictionaryContext.getInstance().getDictionaryService().getOptionList("person", "technic_title_level");
		List<Map<String, Map<Integer, Integer>>> statisticsList_admin = this.secrecyPersonModuleService.statisticsByAdmin(adminLevelOptions, districtCode, organId,SECRECY_STATUS_NOW, countScope);
		List<Map<String, Map<Integer, Integer>>> statisticsList_tech = this.secrecyPersonModuleService.statisticsByTechLevel(technicTitleLevelOptions, districtCode, organId,SECRECY_STATUS_NOW, countScope);
		this.putToRequest("statisticsList_admin", statisticsList_admin);
		this.putToRequest("statisticsList_tech", statisticsList_tech);
		this.putToRequest("countScope", countScope);
		return "success";
	}

	public String statisticsByAdminTechLevel(){
		String districtCode = getRequest().getParameter("districtCode");
		if (StringUtils.isEmpty(districtCode)) {
			districtCode = getCurrentUser().getOrgan().getDistrict().getDistrictCode();
		}
		String countScope = getRequest().getParameter("countScope");
		if( countScope==null || "".equals(countScope) ){
			countScope = "organ";
		}
		String organId = getRequest().getParameter("organId");
		//行政级别
		List<DictionaryOption> adminLevelOptions = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "person_admin_level");
		//技术职称
		List<DictionaryOption> technicTitleLevelOptions = DictionaryContext.getInstance().getDictionaryService().getOptionList("person", "technic_title_level");
		List<Map<String, Map<Integer, Integer>>> statisticsList_admin = this.secrecyPersonModuleService.statisticsByAdmin(adminLevelOptions, districtCode, organId,SECRECY_STATUS_NOW,countScope);
		List<Map<String, Map<Integer, Integer>>> statisticsList_tech = this.secrecyPersonModuleService.statisticsByTechLevel(technicTitleLevelOptions, districtCode, organId,SECRECY_STATUS_NOW,countScope);
		this.putToRequest("statisticsList_admin", statisticsList_admin);
		this.putToRequest("statisticsList_tech", statisticsList_tech);
		return "success";
	}

	public String statisticsByLayerOfAdminTechLevel(){
		String districtCode = getRequest().getParameter("districtCode");
		String layer = null;
		if (StringUtils.isEmpty(districtCode)) {
			layer = getCurrentUser().getOrgan().getDistrict().getLayer();
		}else{
			District district = this.secrecyPersonModuleService.get(districtCode, District.class);
			if( district!=null ){
				layer = district.getLayer();
			}
		}

		String countScope = getRequest().getParameter("countScope");
		if( countScope==null || "".equals(countScope) ){
			countScope = "organ";
		}

		//行政级别
		List<DictionaryOption> adminLevelOptions = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "person_admin_level");
		//技术职称
		List<DictionaryOption> technicTitleLevelOptions = DictionaryContext.getInstance().getDictionaryService().getOptionList("person", "technic_title_level");
		String organId = getRequest().getParameter("organId");
		List<Map<String, Map<Integer, Integer>>> statisticsList_admin = this.secrecyPersonModuleService.statisticsByLayerOfAdmin(adminLevelOptions, layer, organId,SECRECY_STATUS_NOW, countScope);
		List<Map<String, Map<Integer, Integer>>> statisticsList_tech = this.secrecyPersonModuleService.statisticsByLayerOfTechLevel(technicTitleLevelOptions, layer, organId,SECRECY_STATUS_NOW, countScope);
		this.putToRequest("statisticsList_admin", statisticsList_admin);
		this.putToRequest("statisticsList_tech", statisticsList_tech);
		return "success";
	}

	public String statisticsByAdminTechLevelOfAll(){
		String districtCode = getRequest().getParameter("districtCode");
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		}else{
			district = this.secrecyPersonModuleService.get(districtCode, District.class);
		}

		String countScope = getRequest().getParameter("countScope");
		if( countScope==null || "".equals(countScope) ){
			countScope = "organ";
		}

		//行政级别
		List<DictionaryOption> adminLevelOptions = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "person_admin_level");
		//技术职称
		List<DictionaryOption> technicTitleLevelOptions = DictionaryContext.getInstance().getDictionaryService().getOptionList("person", "technic_title_level");
		String organId = getRequest().getParameter("organId");
		Map<String, List<Map<String, Map<Integer, Integer>>>> statisticsList_admin = this.secrecyPersonModuleService.statisticsByAdminOfAll(adminLevelOptions, district, organId,SECRECY_STATUS_NOW, countScope);
		Map<String, List<Map<String, Map<Integer, Integer>>>> statisticsList_tech = this.secrecyPersonModuleService.statisticsByTechLevelOfAll(technicTitleLevelOptions, district, organId,SECRECY_STATUS_NOW, countScope);

		this.putToRequest("adminLevelOptions", adminLevelOptions);
		this.putToRequest("technicTitleLevelOptions", technicTitleLevelOptions);
		this.putToRequest("statisticsList_admin", statisticsList_admin);
		this.putToRequest("statisticsList_tech", statisticsList_tech);
		this.putToRequest("countScope", countScope);
		return "success";
	}

	public String statisticsByAdminTechLevelOfDistrict(){
		String districtCode = getRequest().getParameter("districtCode");
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		}else{
			district = this.secrecyPersonModuleService.get(districtCode, District.class);
		}

		String countScope = getRequest().getParameter("countScope");
		if( countScope==null || "".equals(countScope) ){
			countScope = "organ";
		}

		//行政级别
		List<DictionaryOption> adminLevelOptions = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "person_admin_level");
		//技术职称
		List<DictionaryOption> technicTitleLevelOptions = DictionaryContext.getInstance().getDictionaryService().getOptionList("person", "technic_title_level");
		String organId = getRequest().getParameter("organId");
		Map<String, List<Map<String, Map<Integer, Integer>>>> statisticsList_admin = this.secrecyPersonModuleService.statisticsByAdminOfDistrict(adminLevelOptions, district, organId,SECRECY_STATUS_NOW, countScope);
		Map<String, List<Map<String, Map<Integer, Integer>>>> statisticsList_tech = this.secrecyPersonModuleService.statisticsByTechLevelOfDistrict(technicTitleLevelOptions, district, organId,SECRECY_STATUS_NOW, countScope);
		this.putToRequest("adminLevelOptions", adminLevelOptions);
		this.putToRequest("technicTitleLevelOptions", technicTitleLevelOptions);
		this.putToRequest("statisticsList_admin", statisticsList_admin);
		this.putToRequest("statisticsList_tech", statisticsList_tech);
		return "success";
	}

	public String statisticsByAdminTechLevelOfOrgan(){
		String districtCode = getRequest().getParameter("districtCode");
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		}else{
			district = this.secrecyPersonModuleService.get(districtCode, District.class);
		}
		String countScope = getRequest().getParameter("countScope");
		if( countScope==null || "".equals(countScope) ){
			countScope = "organ";
		}
		//行政级别
		List<DictionaryOption> adminLevelOptions = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "person_admin_level");
		//技术职称
		List<DictionaryOption> technicTitleLevelOptions = DictionaryContext.getInstance().getDictionaryService().getOptionList("person", "technic_title_level");
		String organId = getRequest().getParameter("organId");
		Map<String, List<Map<String, Map<Integer, Integer>>>> statisticsList_admin = this.secrecyPersonModuleService.statisticsByAdminOfOrgan(adminLevelOptions, district, organId,SECRECY_STATUS_NOW, countScope);
		Map<String, List<Map<String, Map<Integer, Integer>>>> statisticsList_tech = this.secrecyPersonModuleService.statisticsByTechLevelOfOrgan(technicTitleLevelOptions, district, organId,SECRECY_STATUS_NOW, countScope);
		this.putToRequest("adminLevelOptions", adminLevelOptions);
		this.putToRequest("technicTitleLevelOptions", technicTitleLevelOptions);
		this.putToRequest("statisticsList_admin", statisticsList_admin);
		this.putToRequest("statisticsList_tech", statisticsList_tech);
		this.putToRequest("countScope", countScope);
		return "success";
	}


	///////////////////////////////////////////////////////////////////////////////////////////////////////
	//按人员类型查询
	public String statisticsByPersonTypeCurrent(){
		String districtCode = getRequest().getParameter("districtCode");
		if (StringUtils.isEmpty(districtCode)) {
			districtCode = getCurrentUser().getOrgan().getDistrict().getDistrictCode();
		}
		List<DictionaryOption> personTypeOptions = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "person_type");
		String organId = getRequest().getParameter("organId");
		if( !(organId!=null && !"".equals(organId)) ){
			organId = getCurrentUser().getOrgan().getOrganId();
		}

		String countScope = getRequest().getParameter("countScope");
		if( countScope==null || "".equals(countScope) ){
			countScope = "organ";
		}

		List<Map<String, Map<Integer, Integer>>> statisticsList = this.secrecyPersonModuleService.statisticsByPersonType(personTypeOptions, districtCode, organId,SECRECY_STATUS_NOW, countScope);
		this.putToRequest("statisticsList", statisticsList);
		this.putToRequest("countScope", countScope);
		return "success";
	}

	public String statisticsByPersonType(){
		String districtCode = getRequest().getParameter("districtCode");
		if (StringUtils.isEmpty(districtCode)) {
			districtCode = getCurrentUser().getOrgan().getDistrict().getDistrictCode();
		}
		List<DictionaryOption> personTypeOptions = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "person_type");
		String organId = getRequest().getParameter("organId");

		String countScope = getRequest().getParameter("countScope");
		if( countScope==null || "".equals(countScope) ){
			countScope = "organ";
		}

		List<Map<String, Map<Integer, Integer>>> statisticsList = this.secrecyPersonModuleService.statisticsByPersonType(personTypeOptions, districtCode, organId,SECRECY_STATUS_NOW, countScope);
		this.putToRequest("statisticsList", statisticsList);
		this.putToRequest("countScope", countScope);
		return "success";
	}

	public String statisticsByLayerOfPersonType(){
		String districtCode = getRequest().getParameter("districtCode");
		String layer = null;
		if (StringUtils.isEmpty(districtCode)) {
			layer = getCurrentUser().getOrgan().getDistrict().getLayer();
		}else{
			District district = this.secrecyPersonModuleService.get(districtCode, District.class);
			if( district!=null ){
				layer = district.getLayer();
			}
		}

		String countScope = getRequest().getParameter("countScope");
		if( countScope==null || "".equals(countScope) ){
			countScope = "organ";
		}

		List<DictionaryOption> personTypeOptions = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "person_type");
		String organId = getRequest().getParameter("organId");
		List<Map<String, Map<Integer, Integer>>> statisticsList = this.secrecyPersonModuleService.statisticsByLayerOfPersonType(personTypeOptions, layer, organId,SECRECY_STATUS_NOW, countScope);
		this.putToRequest("statisticsList", statisticsList);
		this.putToRequest("countScope", countScope);
		return "success";
	}

	public String statisticsByPersonTypeOfAll(){
		String districtCode = getRequest().getParameter("districtCode");
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		}else{
			district = this.secrecyPersonModuleService.get(districtCode, District.class);
		}

		String countScope = getRequest().getParameter("countScope");
		if( countScope==null || "".equals(countScope) ){
			countScope = "organ";
		}

		List<DictionaryOption> personTypeOptions = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "person_type");
		String organId = getRequest().getParameter("organId");
		Map<String, List<Map<String, Map<Integer, Integer>>>> statisticsList = this.secrecyPersonModuleService.statisticsByPersonTypeOfAll(personTypeOptions, district, organId,SECRECY_STATUS_NOW, countScope);
		this.putToRequest("options", personTypeOptions);
		this.putToRequest("statisticsList", statisticsList);
		this.putToRequest("countScope", countScope);
		return "success";
	}

	public String statisticsByPersonTypeOfDistrict(){
		String districtCode = getRequest().getParameter("districtCode");
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		}else{
			district = this.secrecyPersonModuleService.get(districtCode, District.class);
		}
		List<DictionaryOption> personTypeOptions = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "person_type");
		String organId = getRequest().getParameter("organId");

		String countScope = getRequest().getParameter("countScope");
		if( countScope==null || "".equals(countScope) ){
			countScope = "organ";
		}

		Map<String, List<Map<String, Map<Integer, Integer>>>> statisticsList = this.secrecyPersonModuleService.statisticsByPersonTypeOfDistrict(personTypeOptions, district, organId,SECRECY_STATUS_NOW, countScope);
		this.putToRequest("options", personTypeOptions);
		this.putToRequest("statisticsList", statisticsList);
		return "success";
	}

	public String statisticsByPersonTypeOfOrgan(){
		String districtCode = getRequest().getParameter("districtCode");
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		}else{
			district = this.secrecyPersonModuleService.get(districtCode, District.class);
		}

		String countScope = getRequest().getParameter("countScope");
		if( countScope==null || "".equals(countScope) ){
			countScope = "organ";
		}

		List<DictionaryOption> personTypeOptions = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "person_type");
		String organId = getRequest().getParameter("organId");
		Map<String, List<Map<String, Map<Integer, Integer>>>> statisticsList = this.secrecyPersonModuleService.statisticsByPersonTypeOfOrgan(personTypeOptions, district, organId,SECRECY_STATUS_NOW, countScope);
		this.putToRequest("options", personTypeOptions);
		this.putToRequest("statisticsList", statisticsList);
		this.putToRequest("countScope", countScope);
		return "success";
	}



	///////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//概览
	public String statisticsOverView(){
		String districtCode = getRequest().getParameter("districtCode");
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		}else{
			district = this.secrecyPersonModuleService.get(districtCode, District.class);
		}
		String organId = getRequest().getParameter("organId");
		Map<String,Map<String, Map<Integer,Integer>>> currentView = this.secrecyPersonModuleService.statisticsOverViewCurrent(district, organId,SECRECY_STATUS_NOW);
		this.putToRequest("currentView", currentView);
		Map<String,Map<String, Map<Integer,Integer>>> distinctView = this.secrecyPersonModuleService.statisticsOverViewDistinct(district, organId,SECRECY_STATUS_NOW);
		this.putToRequest("distinctView", distinctView);
		return "success";
	}

	//概览
	public String statisticsOverViewOgran(){
		String districtCode = getRequest().getParameter("districtCode");
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		}else{
			district = this.secrecyPersonModuleService.get(districtCode, District.class);
		}
		String countScope = getRequest().getParameter("countScope");
		if( countScope==null || "".equals(countScope) ){
			countScope = "organ";
		}
		String organId = getRequest().getParameter("organId");
		Map<String,Map<Integer,Integer>> districtView = this.secrecyPersonModuleService.statisticsOverViewOgran(district, organId,SECRECY_STATUS_NOW, countScope);
		this.putToRequest("districtView", districtView);
		this.putToRequest("countScope", countScope);
		return "success";
	}




	/***************************************导出部分*************************************************/

	//概览导出
	public String exportOverviewOrganCurrent(){
		Map<String, Object> result = new HashMap<String, Object>();
		//String districtCode = getRequest().getParameter("districtCode");
		District district = null;
		/*if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		}else{
			district = this.secrecyPersonModuleService.get(districtCode, District.class);
		}*/
		String organId = getRequest().getParameter("organId");
		if( !(organId!=null && !"".equals(organId)) ){
			organId = getCurrentUser().getOrgan().getOrganId();
		}

		String countScope = getRequest().getParameter("countScope");
		if( countScope==null || "".equals(countScope) ){
			countScope = "organ";
		}

		List<DictionaryOption> secrecyLevelOptions = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "secrecy_level_person");
		//Map<String,Map<String, Map<Integer,Integer>>> currentView = this.secrecyPersonModuleService.statisticsOverViewCurrent(district, organId,SECRECY_STATUS_NOW);
		//Map<String,Map<String, Map<Integer,Integer>>> currentView = this.secrecyPersonModuleService.statisticsOverViewCurrent(district, organId,SECRECY_STATUS_NOW);
		Map<String,Map<Integer,Integer>> districtView = this.secrecyPersonModuleService.statisticsOverViewOgran(district, organId,SECRECY_STATUS_NOW, countScope);

		//单位  类型   涉密等级
		List<LinkedList<String>> currentViewResult = new ArrayList<LinkedList<String>>();
		LinkedList<String> titleRow = new LinkedList<String>();
		titleRow.add("名称");
		for( DictionaryOption SLDO : secrecyLevelOptions ){
			titleRow.add(SLDO.getOptionText());
		}
		titleRow.add("合计");

		currentViewResult.add(titleRow);

		for( String rowName : districtView.keySet() ){
			LinkedList<String> rowResult = new LinkedList<String>();
			rowResult.add(rowName);
			for( String halfRowKey : districtView.keySet() ){
				Integer halfRowTotal = 0;
				Map<Integer, Integer> secrecyLevelResult = districtView.get(halfRowKey);
				for( DictionaryOption SLDO : secrecyLevelOptions ){
					Integer aCell = secrecyLevelResult.get(SLDO.getOptionValue()) == null ? 0 :secrecyLevelResult.get(SLDO.getOptionValue());
					rowResult.add(aCell+"");
					halfRowTotal += aCell;
				}
				rowResult.add(halfRowTotal+"");
			}
			currentViewResult.add(rowResult);
		}
		result.put("currentViewResult", currentViewResult);
		setResultData(result);
		return SUCCESS;
	}


	/**
	 *
	 * <p>
	 * 方法名：collationOfData  公共数据处理方法
	 * </p>
	 * <p>
	 * 宋亚非 2013-7-27 上午11:49:23
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@param resultdata
	 *@param options
	 *@return
	 */
	public List<LinkedList<String>>  collationOfData( Map<String, List<Map<String, Map<Integer, Integer>>>> resultdata, List<DictionaryOption> options, String firstCellName ){
		List<DictionaryOption> secrecyLevelOptions = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "secrecy_level_person");
		List<LinkedList<String>> returnResult = new ArrayList<LinkedList<String>>();
		LinkedList<String> header = new LinkedList<String>();
		header.add("名称");
		for( int i=0; i<options.size(); i++ ){
			for( DictionaryOption slo : secrecyLevelOptions ){
				header.add(slo.getOptionText());
			}
		}
		for( DictionaryOption slo : secrecyLevelOptions ){
			header.add(slo.getOptionText());
		}
		returnResult.add(header);
		for(String rowName : resultdata.keySet() ){
			LinkedList<String> aRow = new LinkedList<String>();
			aRow.add(rowName);
			Integer rowTotal = 0;
			List<Map<String, Map<Integer, Integer>>> tmpRow = resultdata.get(rowName);
			for( Map<String, Map<Integer, Integer>> tmpMap : tmpRow ){
				for( String key : tmpMap.keySet() ){
					if( "合计".equals(key) ){
						for(DictionaryOption slo : secrecyLevelOptions){
							Map<Integer, Integer> tmpMaptmp = tmpMap.get(key);
							for( Integer key2 : tmpMaptmp.keySet() ){
								if( slo.getOptionValue() == key2 ){
									rowTotal = rowTotal+tmpMap.get(key).get(key2);
								}
							}
						}
					}
				}
			}

			for( DictionaryOption d : options ){
				for( DictionaryOption slo : secrecyLevelOptions ){
					Integer dividend = 0;
					for( Map<String, Map<Integer, Integer>> dataMap : resultdata.get(rowName) ){
						Map<Integer,Integer> tmp = dataMap.get(d.getOptionText());
						if(tmp!=null){
							dividend = tmp.get(slo.getOptionValue());
						}
					}
					if( rowTotal!=null && rowTotal!=0 && dividend!=null && dividend!=0 ){
						aRow.add(dividend + "(" + Math.round(dividend*10000.00/rowTotal)/100.00 + "%)");
					}else{
						aRow.add(0 + "(0.00%)");
					}
				}
			}
			for( Map<String, Map<Integer, Integer>> tmpMap : tmpRow ){
				for( String key : tmpMap.keySet() ){
					if( "合计".equals(key) ){
						for(DictionaryOption slo : secrecyLevelOptions){
							Map<Integer, Integer> tmpMaptmp = tmpMap.get(key);
							int flag = 1;
							int cellValue = 0;
							for( Integer key2 : tmpMaptmp.keySet() ){
								if( slo.getOptionValue() == key2 ){
									cellValue = tmpMap.get(key).get(key2);
									flag = 0;
								}
							}
							if( flag == 1 ){
								aRow.add(0 + "(0.00%)");
							}else{
								if(rowTotal!=null && rowTotal!=0 && cellValue!=0){
									aRow.add(cellValue + "(" + Math.round(cellValue*10000.00/rowTotal)/100.00 + "%)");
								}else{
									aRow.add(0 + "(0.00%)");
								}
							}
						}
					}
				}
			}

			returnResult.add(aRow);
		}

		return returnResult;
	}

	/**
	 *
	 * <p>
	 * 方法名：collationOfDataDetail 公共数据处理方法（概览和本单位导出使用）
	 * </p>
	 * <p>
	 * 宋亚非 2013-7-27 上午11:52:21
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@param resultdata
	 *@param options
	 *@return
	 */
	public List<LinkedList<String>>  collationOfDataDetail( List<Map<String, Map<Integer, Integer>>> resultdata, List<DictionaryOption> options, String fistCellName ){
		List<DictionaryOption> secrecyLevelOptions = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "secrecy_level_person");
		List<LinkedList<String>> returnResult = new ArrayList<LinkedList<String>>();

		LinkedList<String> headerRow = new LinkedList<String>();
		headerRow.add(fistCellName);
		for(DictionaryOption sld : secrecyLevelOptions){
			headerRow.add(sld.getOptionText());
		}
		headerRow.add("合计");
		returnResult.add(headerRow);
		for( Map<String, Map<Integer, Integer>> layer1 : resultdata ){
			for( String ageRangeName : layer1.keySet() ){
				LinkedList<String> aRow = new LinkedList<String>();
				aRow.add(ageRangeName);
				Map<Integer, Integer> layer1ContentMap = layer1.get(ageRangeName);
				Integer rowTotal = 0;
				//计算合计
				for( Integer secrecyLevelValue : layer1ContentMap.keySet() ){
					for(DictionaryOption sld : secrecyLevelOptions){
						if( sld.getOptionValue() == secrecyLevelValue ){
							rowTotal = rowTotal + layer1ContentMap.get(secrecyLevelValue);
						}
					}
				}
				for(DictionaryOption sld : secrecyLevelOptions){
					int dividend = 0;
					for( Integer secrecyLevelValue : layer1ContentMap.keySet() ){
						if( sld.getOptionValue() == secrecyLevelValue ){
							dividend = layer1ContentMap.get(secrecyLevelValue);
						}
					}
					if( rowTotal!=null && rowTotal!=0 && dividend != 0 ){
						aRow.add(dividend + "(" + Math.round(dividend*10000.00/rowTotal)/100.00 +"%)");
					}else{
						aRow.add(0 + "(0.00%)");
					}
				}
				aRow.add(rowTotal+"");
				returnResult.add(aRow);
			}
		}
		return returnResult;
	}

	/**
	 * <p>
	 * 方法名称  ageRangDeal
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-28 上午9:43:14  统计部分的人员年龄阶段统一设置
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
	private List<Map<String, Integer>> ageRangDeal() {
		List<Map<String,Integer>> ageRange = new ArrayList<Map<String,Integer>>();
		Map<String ,Integer> ageRange1 = new HashMap<String, Integer>();
		ageRange1.put("start", 0);
		ageRange1.put("end", 35);
		ageRange.add(ageRange1);

		Map<String ,Integer> ageRange2 = new HashMap<String, Integer>();
		ageRange2.put("start", 36);
		ageRange2.put("end", 45);
		ageRange.add(ageRange2);

		Map<String ,Integer> ageRange3 = new HashMap<String, Integer>();
		ageRange3.put("start", 46);
		ageRange3.put("end", 55);
		ageRange.add(ageRange3);

		Map<String ,Integer> ageRange4 = new HashMap<String, Integer>();
		ageRange4.put("start", 56);
		ageRange4.put("end", null);
		ageRange.add(ageRange4);
		return ageRange;
	}

	/**
	 * <p>
	 * 方法名称  polityOptionsDeal 关于政治面貌统计部分的处理，剔除options中“非中共党员”的统计项
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-28 上午9:36:30
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
	private List<DictionaryOption> polityOptionsDeal() {
		List<DictionaryOption> polityOptions = DictionaryContext.getInstance().getDictionaryService().getOptionList("person", "polity");
		for( DictionaryOption polityOption : polityOptions ){
			if( "非中共党员".equals(polityOption.getOptionText()) ){
				polityOptions.remove(polityOption);
			}
		}
		return polityOptions;
	}

	/**
	 *
	 * <p>
	 * 方法名：exportOverView  概览导出
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-1 上午11:03:08
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
	public String exportOverView(){
		Map<String, Object> result = new HashMap<String, Object>();
		String districtCode = getRequest().getParameter("districtCode");
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		}else{
			district = this.secrecyPersonModuleService.get(districtCode, District.class);
		}
		String organId = getRequest().getParameter("organId");
		List<DictionaryOption> secrecyLevelOptions = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "secrecy_level_person");
		Map<String,Map<String, Map<Integer,Integer>>> currentView = this.secrecyPersonModuleService.statisticsOverViewCurrent(district, organId,SECRECY_STATUS_NOW);
		Map<String,Map<String, Map<Integer,Integer>>> distinctView = this.secrecyPersonModuleService.statisticsOverViewDistinct(district, organId,SECRECY_STATUS_NOW);
		//单位  类型   涉密等级
		List<LinkedList<String>> currentViewResult = new ArrayList<LinkedList<String>>();
		List<LinkedList<String>> distinctViewResult = new ArrayList<LinkedList<String>>();
		LinkedList<String> titleRow = new LinkedList<String>();
		titleRow.add("名称");
		for( int i=0;i<2;i++){
			for( DictionaryOption SLDO : secrecyLevelOptions ){
				titleRow.add(SLDO.getOptionText());
			}
			titleRow.add("合计");
		}

		currentViewResult.add(titleRow);
		distinctViewResult.add(titleRow);

		for( String rowName : currentView.keySet() ){
			LinkedList<String> rowResult = new LinkedList<String>();
			Map<String,Map<Integer,Integer>> row = currentView.get(rowName);
			rowResult.add(rowName);
			for( String halfRowKey : row.keySet() ){
				Integer halfRowTotal = 0;
				if( "直机构".equals(halfRowKey) ){
					Map<Integer, Integer> secrecyLevelResult = row.get(halfRowKey);
					for( DictionaryOption SLDO : secrecyLevelOptions ){
						Integer aCell = secrecyLevelResult.get(SLDO.getOptionValue()) == null ? 0 :secrecyLevelResult.get(SLDO.getOptionValue());
						rowResult.add(aCell+"");
						halfRowTotal += aCell;
					}
					rowResult.add(halfRowTotal+"");
				}
			}
			for( String halfRowKey : row.keySet() ){
				Integer halfRowTotal = 0;
				if( "行政区内".equals(halfRowKey) ){
					Map<Integer, Integer> secrecyLevelResult = row.get(halfRowKey);
					for( DictionaryOption SLDO : secrecyLevelOptions ){
						Integer aCell = secrecyLevelResult.get(SLDO.getOptionValue()) == null ? 0 :secrecyLevelResult.get(SLDO.getOptionValue());
						rowResult.add(aCell+"");
						halfRowTotal += aCell;
					}
					rowResult.add(halfRowTotal+"");
				}
			}
			currentViewResult.add(rowResult);
		}
		result.put("currentViewResult", currentViewResult);

		for( String rowName : distinctView.keySet() ){
			LinkedList<String> rowResult = new LinkedList<String>();
			Map<String,Map<Integer,Integer>> row = distinctView.get(rowName);
			rowResult.add(rowName);
			for( String halfRowKey : row.keySet() ){
				Integer halfRowTotal = 0;
				if( "直机构".equals(halfRowKey) ){
					Map<Integer, Integer> secrecyLevelResult = row.get(halfRowKey);
					for( DictionaryOption SLDO : secrecyLevelOptions ){
						Integer aCell = secrecyLevelResult.get(SLDO.getOptionValue()) == null ? 0 :secrecyLevelResult.get(SLDO.getOptionValue());
						rowResult.add(aCell+"");
						halfRowTotal += aCell;
					}
					rowResult.add(halfRowTotal+"");
				}
			}
			for( String halfRowKey : row.keySet() ){
				Integer halfRowTotal = 0;
				if( "行政区内".equals(halfRowKey) ){
					Map<Integer, Integer> secrecyLevelResult = row.get(halfRowKey);
					for( DictionaryOption SLDO : secrecyLevelOptions ){
						Integer aCell = secrecyLevelResult.get(SLDO.getOptionValue()) == null ? 0 :secrecyLevelResult.get(SLDO.getOptionValue());
						rowResult.add(aCell+"");
						halfRowTotal += aCell;
					}
					rowResult.add(halfRowTotal+"");
				}
			}
			distinctViewResult.add(rowResult);
		}
		result.put("distinctViewResult", distinctViewResult);

		setResultData(result);
		return SUCCESS;
	}

	/**
	 *
	 * <p>
	 * 方法名：exportOverviewDistrict 概览导出（按layer合计）
	 * </p>
	 * <p>
	 * 宋亚非 2013-7-27 下午2:55:20
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
	public String exportOverviewDistrict(){
		Map<String, Object> result = new HashMap<String, Object>();

		String districtCode = getRequest().getParameter("districtCode");
		String layer = null;
		if (StringUtils.isEmpty(districtCode)) {
			layer = getCurrentUser().getOrgan().getDistrict().getLayer();
		}else{
			District district = this.secrecyPersonModuleService.get(districtCode, District.class);
			if( district!=null ){
				layer = district.getLayer();
			}
		}
		String organId = getRequest().getParameter("organId");

		String countScope = getRequest().getParameter("countScope");
		if( countScope==null || "".equals(countScope) ){
			countScope = "organ";
		}

		//年龄阶段
		List<Map<String, Integer>> ageRange = ageRangDeal();

		List<DictionaryOption> ageRangeDic = new ArrayList<DictionaryOption>();
		int dicNum = 0;
		for( Map<String, Integer> aRange : ageRange ){
			DictionaryOption dicOption = new DictionaryOption();
			Integer startNum = aRange.get("start");
			Integer endNum = aRange.get("end");
			if( startNum==0 || startNum==null ){
				dicOption.setOptionId(dicNum+"");
				dicOption.setOptionText("小于"+endNum+"岁");
				dicOption.setOptionValue(dicNum);
			}
			if( startNum!=null && startNum>0 && endNum!=null && endNum >0 ){
				dicOption.setOptionId(dicNum+"");
				dicOption.setOptionText(startNum+"岁至"+endNum+"岁");
				dicOption.setOptionValue(dicNum);
			}
			if( startNum!=null && endNum==null ){
				dicOption.setOptionId(dicNum+"");
				dicOption.setOptionText("大于"+startNum+"岁");
				dicOption.setOptionValue(dicNum);
			}
			ageRangeDic.add(dicOption);
			dicNum++;
		}
		List<Map<String, List<Map<Integer, Integer>>>> ageStatisticsList = this.secrecyPersonModuleService.statisticsByLayerOfAge(ageRange, layer, organId,SECRECY_STATUS_NOW, countScope);

		if( ageStatisticsList!=null ){
			List<Map<String, Map<Integer, Integer>>> dataResult = new ArrayList<Map<String,Map<Integer,Integer>>>();
			for( Map<String, List<Map<Integer, Integer>>> layer1 : ageStatisticsList ){
				for( String layer1Key : layer1.keySet() ){
					Map<String,Map<Integer,Integer>> tmp = new HashMap<String, Map<Integer,Integer>>();
					if(layer1.get(layer1Key)!=null && layer1.get(layer1Key).size()>0){
						tmp.put(layer1Key, layer1.get(layer1Key).get(0));
					}else{
						tmp.put(layer1Key, null);
					}
					dataResult.add(tmp);
				}
			}
			result.put("ageResult", this.collationOfDataDetail(dataResult, ageRangeDic,"年龄分段"));
		}else{
			result.put("ageResult", null);
		}


		//民族
		List<DictionaryOption> nationOptions = DictionaryContext.getInstance().getDictionaryService().getOptionList("person", "nation");
		List<Map<String, Map<Integer, Integer>>> nationStatisticsList = this.secrecyPersonModuleService.statisticsByLayerOfNation(nationOptions, layer, organId,SECRECY_STATUS_NOW, countScope);
		result.put("nationResult", this.collationOfDataDetail(nationStatisticsList, nationOptions,"民族"));

		//学历
		List<DictionaryOption> leaningLevelOptions = DictionaryContext.getInstance().getDictionaryService().getOptionList("person", "learning_level");
		List<Map<String, Map<Integer, Integer>>> leaningLevelstatisticsList = this.secrecyPersonModuleService.statisticsByLayerOfLeanLevel(leaningLevelOptions, layer, organId,SECRECY_STATUS_NOW, countScope);
		result.put("learnLeverResult", this.collationOfDataDetail(leaningLevelstatisticsList, leaningLevelOptions,"学历"));

		List<DictionaryOption> polityOptions = polityOptionsDeal();
		List<Map<String, Map<Integer, Integer>>> statisticsListPolity = this.secrecyPersonModuleService.statisticsByLayerOfPolity(polityOptions, layer, organId,SECRECY_STATUS_NOW, countScope);
		result.put("polityResult", this.collationOfDataDetail(statisticsListPolity, polityOptions,"政治面貌"));

		//行政级别
		List<DictionaryOption> adminLevelOptions = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "person_admin_level");
		//技术职称
		List<DictionaryOption> technicTitleLevelOptions = DictionaryContext.getInstance().getDictionaryService().getOptionList("person", "technic_title_level");
		List<Map<String, Map<Integer, Integer>>> statisticsList_admin = this.secrecyPersonModuleService.statisticsByLayerOfAdmin(adminLevelOptions, layer, organId,SECRECY_STATUS_NOW, countScope);
		List<Map<String, Map<Integer, Integer>>> statisticsList_tech = this.secrecyPersonModuleService.statisticsByLayerOfTechLevel(technicTitleLevelOptions, layer, organId,SECRECY_STATUS_NOW, countScope);
		result.put("adminResult", this.collationOfDataDetail(statisticsList_admin, adminLevelOptions,"行政级别"));
		result.put("techResult", this.collationOfDataDetail(statisticsList_tech, technicTitleLevelOptions,"技术职称"));

		//人员类型
		List<DictionaryOption> personTypeOptions = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "person_type");
		List<Map<String, Map<Integer, Integer>>> statisticsListPersonType = this.secrecyPersonModuleService.statisticsByLayerOfPersonType(personTypeOptions, layer, organId,SECRECY_STATUS_NOW, countScope);
		result.put("personTypeResult", this.collationOfDataDetail(statisticsListPersonType, personTypeOptions,"人员类型"));

		setResultData(result);
		return "success";
	}

	/**
	 *
	 * <p>
	 * 方法名：exportOverviewOrgan 概览导出（直辖单位合计）
	 * </p>
	 * <p>
	 * 宋亚非 2013-7-27 下午2:56:08
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
	public String exportOverviewOrgan(){
		Map<String, Object> result = new HashMap<String, Object>();

		String districtCode = getRequest().getParameter("districtCode");
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		}else{
			district = this.secrecyPersonModuleService.get(districtCode, District.class);
		}
		String organId = getRequest().getParameter("organId");

		List<Map<String, Integer>> ageRange = ageRangDeal();

		List<DictionaryOption> ageRangeDic = new ArrayList<DictionaryOption>();
		int dicNum = 0;
		for( Map<String, Integer> aRange : ageRange ){
			DictionaryOption dicOption = new DictionaryOption();
			Integer startNum = aRange.get("start");
			Integer endNum = aRange.get("end");
			if( startNum==0 || startNum==null ){
				dicOption.setOptionId(dicNum+"");
				dicOption.setOptionText("小于"+endNum+"岁");
				dicOption.setOptionValue(dicNum);
			}
			if( startNum!=null && startNum>0 && endNum!=null && endNum >0 ){
				dicOption.setOptionId(dicNum+"");
				dicOption.setOptionText(startNum+"岁至"+endNum+"岁");
				dicOption.setOptionValue(dicNum);
			}
			if( startNum!=null && endNum==null ){
				dicOption.setOptionId(dicNum+"");
				dicOption.setOptionText("大于"+startNum+"岁");
				dicOption.setOptionValue(dicNum);
			}
			ageRangeDic.add(dicOption);
			dicNum++;
		}

		String countScope = getRequest().getParameter("countScope");
		if( countScope==null || "".equals(countScope) ){
			countScope = "organ";
		}

		List<Map<String, List<Map<Integer, Integer>>>> ageStatisticsList = this.secrecyPersonModuleService.statisticsByAge(ageRange, district.getDistrictCode(), organId,SECRECY_STATUS_NOW, countScope);

		if( ageStatisticsList!=null ){
			List<Map<String, Map<Integer, Integer>>> dataResult = new ArrayList<Map<String,Map<Integer,Integer>>>();
			for( Map<String, List<Map<Integer, Integer>>> layer1 : ageStatisticsList ){
				for( String layer1Key : layer1.keySet() ){
					Map<String,Map<Integer,Integer>> tmp = new HashMap<String, Map<Integer,Integer>>();
					if(layer1.get(layer1Key)!=null && layer1.get(layer1Key).size()>0){
						tmp.put(layer1Key, layer1.get(layer1Key).get(0));
					}else{
						tmp.put(layer1Key, null);
					}
					dataResult.add(tmp);
				}
			}
			result.put("ageResult", this.collationOfDataDetail(dataResult, ageRangeDic,"年龄分段"));
		}else{
			result.put("ageResult", new ArrayList<String>());
		}


		//民族
		List<DictionaryOption> nationOptions = DictionaryContext.getInstance().getDictionaryService().getOptionList("person", "nation");
		List<Map<String, Map<Integer, Integer>>> nationStatisticsList = this.secrecyPersonModuleService.statisticsByNation(nationOptions, district.getDistrictCode(), organId,SECRECY_STATUS_NOW, countScope);
		result.put("nationResult", this.collationOfDataDetail(nationStatisticsList, nationOptions,"民族"));

		//学历
		List<DictionaryOption> leaningLevelOptions = DictionaryContext.getInstance().getDictionaryService().getOptionList("person", "learning_level");
		List<Map<String, Map<Integer, Integer>>> leaningLevelstatisticsList = this.secrecyPersonModuleService.statisticsByLeanLevel(leaningLevelOptions, district.getDistrictCode(), organId,SECRECY_STATUS_NOW, countScope);
		result.put("learnLeverResult", this.collationOfDataDetail(leaningLevelstatisticsList, leaningLevelOptions,"学历"));

		List<DictionaryOption> polityOptions = polityOptionsDeal();
		List<Map<String, Map<Integer, Integer>>> statisticsListPolity = this.secrecyPersonModuleService.statisticsByPolity(polityOptions, district.getDistrictCode(), organId,SECRECY_STATUS_NOW,countScope);
		result.put("polityResult", this.collationOfDataDetail(statisticsListPolity, polityOptions,"政治面貌"));

		//行政级别
		List<DictionaryOption> adminLevelOptions = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "person_admin_level");
		//技术职称
		List<DictionaryOption> technicTitleLevelOptions = DictionaryContext.getInstance().getDictionaryService().getOptionList("person", "technic_title_level");
		List<Map<String, Map<Integer, Integer>>> statisticsList_admin = this.secrecyPersonModuleService.statisticsByAdmin(adminLevelOptions, district.getDistrictCode(), organId,SECRECY_STATUS_NOW,countScope);
		List<Map<String, Map<Integer, Integer>>> statisticsList_tech = this.secrecyPersonModuleService.statisticsByTechLevel(technicTitleLevelOptions, district.getDistrictCode(), organId,SECRECY_STATUS_NOW,countScope);
		result.put("adminResult", this.collationOfDataDetail(statisticsList_admin, adminLevelOptions,"行政级别"));
		result.put("techResult", this.collationOfDataDetail(statisticsList_tech, technicTitleLevelOptions,"技术职称"));

		//人员类型
		List<DictionaryOption> personTypeOptions = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "person_type");
		List<Map<String, Map<Integer, Integer>>> statisticsListPersonType = this.secrecyPersonModuleService.statisticsByPersonType(personTypeOptions, district.getDistrictCode(), organId,SECRECY_STATUS_NOW, countScope);
		result.put("personTypeResult", this.collationOfDataDetail(statisticsListPersonType, personTypeOptions,"人员类型"));

		setResultData(result);
		return "success";
	}

	/**
	 *
	 * <p>
	 * 方法名：exportAgeDistrict 年龄结构统计导出（行政区划统计）
	 * </p>
	 * <p>
	 * 宋亚非 2013-7-27 下午3:10:38
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
	public String exportAgeDistrict(){
		Map<String, Object> result = new HashMap<String, Object>();

		List<Map<String, Integer>> ageRange = ageRangDeal();

		String districtCode = getRequest().getParameter("districtCode");
		if (StringUtils.isEmpty(districtCode)) {
			districtCode = getCurrentUser().getOrgan().getDistrict().getDistrictCode();
		}else{
			District district = this.secrecyPersonModuleService.get(districtCode, District.class);
			if( district!=null ){
				districtCode = district.getDistrictCode();
			}
		}
		String organId = getRequest().getParameter("organId");

		String countScope = getRequest().getParameter("countScope");
		if( countScope==null || "".equals(countScope) ){
			countScope = "organ";
		}

		List<DictionaryOption> ageRangeDic = new ArrayList<DictionaryOption>();
		int dicNum = 0;
		for( Map<String, Integer> aRange : ageRange ){
			DictionaryOption dicOption = new DictionaryOption();
			Integer startNum = aRange.get("start");
			Integer endNum = aRange.get("end");
			if( startNum==0 || startNum==null ){
				dicOption.setOptionId(dicNum+"");
				dicOption.setOptionText("小于"+endNum+"岁");
				dicOption.setOptionValue(dicNum);
			}
			if( startNum!=null && startNum>0 && endNum!=null && endNum >0 ){
				dicOption.setOptionId(dicNum+"");
				dicOption.setOptionText(startNum+"岁至"+endNum+"岁");
				dicOption.setOptionValue(dicNum);
			}
			if( startNum!=null && endNum==null ){
				dicOption.setOptionId(dicNum+"");
				dicOption.setOptionText("大于"+startNum+"岁");
				dicOption.setOptionValue(dicNum);
			}
			ageRangeDic.add(dicOption);
			dicNum++;
		}


		Map<String, List<Map<String, List<Map<Integer, Integer>>>>> ageStatisticsListDistrict = this.secrecyPersonModuleService.statisticsByAgeOfAll(ageRange, districtCode, organId,SECRECY_STATUS_NOW, countScope);
		if( ageStatisticsListDistrict!=null ){
			Map<String, List<Map<String, Map<Integer, Integer>>>> dataResultMap = new HashMap<String, List<Map<String,Map<Integer,Integer>>>>();
			for( String mainKey : ageStatisticsListDistrict.keySet() ){
				List<Map<String, Map<Integer, Integer>>> dataResult = new ArrayList<Map<String,Map<Integer,Integer>>>();
				List<Map<String, List<Map<Integer, Integer>>>> ageStatisticsList = ageStatisticsListDistrict.get(mainKey);
				for( Map<String, List<Map<Integer, Integer>>> layer1 : ageStatisticsList ){
					for( String layer1Key : layer1.keySet() ){
						Map<String,Map<Integer,Integer>> tmp = new HashMap<String, Map<Integer,Integer>>();
						if(layer1.get(layer1Key)!=null && layer1.get(layer1Key).size()>0){
							tmp.put(layer1Key, layer1.get(layer1Key).get(0));
						}else{
							tmp.put(layer1Key, null);
						}
						dataResult.add(tmp);
					}
				}
				dataResultMap.put(mainKey, dataResult);
			}
			result.put("result1", this.collationOfData(dataResultMap, ageRangeDic,"年龄分段"));
		}else{
			result.put("result1", new ArrayList<String>());
		}

		Map<String, List<Map<String, List<Map<Integer, Integer>>>>> ageStatisticsListOrgan = this.secrecyPersonModuleService.statisticsByAgeOfDistrict(ageRange, districtCode, organId,SECRECY_STATUS_NOW, countScope);
		if( ageStatisticsListOrgan!=null ){
			Map<String, List<Map<String, Map<Integer, Integer>>>> dataResultMap = new HashMap<String, List<Map<String,Map<Integer,Integer>>>>();
			for( String mainKey : ageStatisticsListOrgan.keySet() ){
				List<Map<String, Map<Integer, Integer>>> dataResult = new ArrayList<Map<String,Map<Integer,Integer>>>();
				List<Map<String, List<Map<Integer, Integer>>>> ageStatisticsList = ageStatisticsListOrgan.get(mainKey);
				for( Map<String, List<Map<Integer, Integer>>> layer1 : ageStatisticsList ){
					for( String layer1Key : layer1.keySet() ){
						Map<String,Map<Integer,Integer>> tmp = new HashMap<String, Map<Integer,Integer>>();
						if(layer1.get(layer1Key)!=null && layer1.get(layer1Key).size()>0){
							tmp.put(layer1Key, layer1.get(layer1Key).get(0));
						}else{
							tmp.put(layer1Key, null);
						}
						dataResult.add(tmp);
					}
				}
				dataResultMap.put(mainKey, dataResult);
			}
			result.put("result2", this.collationOfData(dataResultMap, ageRangeDic,"年龄分段"));
		}else{
			result.put("result2", new ArrayList<String>());
		}

		DictionaryOption dicOption = new DictionaryOption();
		dicOption.setOptionId(dicNum+"");
		dicOption.setOptionText("合计");
		dicOption.setOptionValue(dicNum);
		ageRangeDic.add(dicOption);
		List<String> subRow = new ArrayList<String>();
		for(DictionaryOption o : ageRangeDic ){
			subRow.add(o.getOptionText());
		}
		result.put("subRow", subRow);
		setResultData(result);

		return SUCCESS;
	}


	/**
	 *
	 * <p>
	 * 方法名：exportAgeOrgan  年龄结构统计导出（直辖单位）
	 * </p>
	 * <p>
	 * 宋亚非 2013-7-27 下午5:24:30
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
	public String exportAgeOrgan(){
		Map<String, Object> result = new HashMap<String, Object>();

		List<Map<String, Integer>> ageRange = ageRangDeal();

		String districtCode = getRequest().getParameter("districtCode");
		if (StringUtils.isEmpty(districtCode)) {
			districtCode = getCurrentUser().getOrgan().getDistrict().getDistrictCode();
		}else{
			District district = this.secrecyPersonModuleService.get(districtCode, District.class);
			if( district!=null ){
				districtCode = district.getDistrictCode();
			}
		}
		String organId = getRequest().getParameter("organId");

		List<DictionaryOption> ageRangeDic = new ArrayList<DictionaryOption>();
		int dicNum = 0;
		for( Map<String, Integer> aRange : ageRange ){
			DictionaryOption dicOption = new DictionaryOption();
			Integer startNum = aRange.get("start");
			Integer endNum = aRange.get("end");
			if( startNum==0 || startNum==null ){
				dicOption.setOptionId(dicNum+"");
				dicOption.setOptionText("小于"+endNum+"岁");
				dicOption.setOptionValue(dicNum);
			}
			if( startNum!=null && startNum>0 && endNum!=null && endNum >0 ){
				dicOption.setOptionId(dicNum+"");
				dicOption.setOptionText(startNum+"岁至"+endNum+"岁");
				dicOption.setOptionValue(dicNum);
			}
			if( startNum!=null && endNum==null ){
				dicOption.setOptionId(dicNum+"");
				dicOption.setOptionText("大于"+startNum+"岁");
				dicOption.setOptionValue(dicNum);
			}
			ageRangeDic.add(dicOption);
			dicNum++;
		}

		String countScope = getRequest().getParameter("countScope");
		if( countScope==null || "".equals(countScope) ){
			countScope = "organ";
		}

		Map<String, List<Map<String, List<Map<Integer, Integer>>>>> ageStatisticsListDistrict = this.secrecyPersonModuleService.statisticsByAgeOfOrgan(ageRange, districtCode, organId,SECRECY_STATUS_NOW, countScope);
		if( ageStatisticsListDistrict!=null ){
			Map<String, List<Map<String, Map<Integer, Integer>>>> dataResultMap = new HashMap<String, List<Map<String,Map<Integer,Integer>>>>();
			for( String mainKey : ageStatisticsListDistrict.keySet() ){
				List<Map<String, Map<Integer, Integer>>> dataResult = new ArrayList<Map<String,Map<Integer,Integer>>>();
				List<Map<String, List<Map<Integer, Integer>>>> ageStatisticsList = ageStatisticsListDistrict.get(mainKey);
				for( Map<String, List<Map<Integer, Integer>>> layer1 : ageStatisticsList ){
					for( String layer1Key : layer1.keySet() ){
						Map<String,Map<Integer,Integer>> tmp = new HashMap<String, Map<Integer,Integer>>();
						if(layer1.get(layer1Key)!=null && layer1.get(layer1Key).size()>0){
							tmp.put(layer1Key, layer1.get(layer1Key).get(0));
						}else{
							tmp.put(layer1Key, null);
						}
						dataResult.add(tmp);
					}
				}
				dataResultMap.put(mainKey, dataResult);
			}
			result.put("result1", this.collationOfData(dataResultMap, ageRangeDic,"年龄分段"));
		}else{
			result.put("result1", new ArrayList<String>());
		}

		DictionaryOption dicOption = new DictionaryOption();
		dicOption.setOptionId(dicNum+"");
		dicOption.setOptionText("合计");
		dicOption.setOptionValue(dicNum);
		ageRangeDic.add(dicOption);
		List<String> subRow = new ArrayList<String>();
		for(DictionaryOption o : ageRangeDic ){
			subRow.add(o.getOptionText());
		}
		result.put("subRow", subRow);
		setResultData(result);

		return SUCCESS;
	}

	public String exportNationDistrict(){
		Map<String, Object> result = new HashMap<String, Object>();

		String districtCode = getRequest().getParameter("districtCode");
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		}else{
			district = this.secrecyPersonModuleService.get(districtCode, District.class);
		}
		String organId = getRequest().getParameter("organId");

		String countScope = getRequest().getParameter("countScope");
		if( countScope==null || "".equals(countScope) ){
			countScope = "organ";
		}

		//民族
		List<DictionaryOption> nationOptions = DictionaryContext.getInstance().getDictionaryService().getOptionList("person", "nation");

		Map<String, List<Map<String, Map<Integer, Integer>>>> nationStatisticsList = this.secrecyPersonModuleService.statisticsByNationOfAll(nationOptions, district, organId,SECRECY_STATUS_NOW, countScope);
		result.put("result1", this.collationOfData(nationStatisticsList, nationOptions,"民族"));

		Map<String, List<Map<String, Map<Integer, Integer>>>> nationStatisticsList1 = this.secrecyPersonModuleService.statisticsByNationOfDistrict(nationOptions, district, organId,SECRECY_STATUS_NOW, countScope);
		result.put("result2", this.collationOfData(nationStatisticsList1, nationOptions,"民族"));

		DictionaryOption dicOption = new DictionaryOption();
		dicOption.setOptionId("");
		dicOption.setOptionText("合计");
		dicOption.setOptionValue(100000);
		nationOptions.add(dicOption);
		List<String> subRow = new ArrayList<String>();
		for(DictionaryOption o : nationOptions ){
			subRow.add(o.getOptionText());
		}
		result.put("subRow", subRow);
		setResultData(result);
		return SUCCESS;
	}

	public String exportNationOrgan(){
		Map<String, Object> result = new HashMap<String, Object>();

		String districtCode = getRequest().getParameter("districtCode");
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		}else{
			district = this.secrecyPersonModuleService.get(districtCode, District.class);
		}
		String organId = getRequest().getParameter("organId");

		String countScope = getRequest().getParameter("countScope");
		if( countScope==null || "".equals(countScope) ){
			countScope = "organ";
		}

		//民族
		List<DictionaryOption> nationOptions = DictionaryContext.getInstance().getDictionaryService().getOptionList("person", "nation");

		Map<String, List<Map<String, Map<Integer, Integer>>>> nationStatisticsList = this.secrecyPersonModuleService.statisticsByNationOfOrgan(nationOptions, district, organId,SECRECY_STATUS_NOW, countScope);
		result.put("result1", this.collationOfData(nationStatisticsList, nationOptions,"民族"));

		DictionaryOption dicOption = new DictionaryOption();
		dicOption.setOptionId("");
		dicOption.setOptionText("合计");
		dicOption.setOptionValue(100000);
		nationOptions.add(dicOption);
		List<String> subRow = new ArrayList<String>();
		for(DictionaryOption o : nationOptions ){
			subRow.add(o.getOptionText());
		}
		result.put("subRow", subRow);
		setResultData(result);
		return SUCCESS;
	}

	public String exportLearnLevelDistrict(){
		Map<String, Object> result = new HashMap<String, Object>();

		String districtCode = getRequest().getParameter("districtCode");
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		}else{
			district = this.secrecyPersonModuleService.get(districtCode, District.class);
		}
		String organId = getRequest().getParameter("organId");

		String countScope = getRequest().getParameter("countScope");
		if( countScope==null || "".equals(countScope) ){
			countScope = "organ";
		}

		//学历
		List<DictionaryOption> leaningLevelOptions = DictionaryContext.getInstance().getDictionaryService().getOptionList("person", "learning_level");
		Map<String, List<Map<String, Map<Integer, Integer>>>> leaningLevelstatisticsList = this.secrecyPersonModuleService.statisticsByLearnLevelOfAll(leaningLevelOptions, district, organId,SECRECY_STATUS_NOW, countScope);
		result.put("result1", this.collationOfData(leaningLevelstatisticsList, leaningLevelOptions,"学历"));

		Map<String, List<Map<String, Map<Integer, Integer>>>> leaningLevelstatisticsList1 = this.secrecyPersonModuleService.statisticsByLearnLevelOfDistrict(leaningLevelOptions, district, organId,SECRECY_STATUS_NOW, countScope);
		result.put("result2", this.collationOfData(leaningLevelstatisticsList1, leaningLevelOptions,"学历"));

		DictionaryOption dicOption = new DictionaryOption();
		dicOption.setOptionId("");
		dicOption.setOptionText("合计");
		dicOption.setOptionValue(100000);
		leaningLevelOptions.add(dicOption);
		List<String> subRow = new ArrayList<String>();
		for(DictionaryOption o : leaningLevelOptions ){
			subRow.add(o.getOptionText());
		}
		result.put("subRow", subRow);
		setResultData(result);
		return SUCCESS;
	}

	public String exportLearnLevelOrgan(){
		Map<String, Object> result = new HashMap<String, Object>();

		String districtCode = getRequest().getParameter("districtCode");
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		}else{
			district = this.secrecyPersonModuleService.get(districtCode, District.class);
		}
		String organId = getRequest().getParameter("organId");

		String countScope = getRequest().getParameter("countScope");
		if( countScope==null || "".equals(countScope) ){
			countScope = "organ";
		}

		//学历
		List<DictionaryOption> leaningLevelOptions = DictionaryContext.getInstance().getDictionaryService().getOptionList("person", "learning_level");
		Map<String, List<Map<String, Map<Integer, Integer>>>> leaningLevelstatisticsList = this.secrecyPersonModuleService.statisticsByLearnLevelOfOrgan(leaningLevelOptions, district, organId,SECRECY_STATUS_NOW, countScope);
		result.put("result1", this.collationOfData(leaningLevelstatisticsList, leaningLevelOptions,"学历"));

		DictionaryOption dicOption = new DictionaryOption();
		dicOption.setOptionId("");
		dicOption.setOptionText("合计");
		dicOption.setOptionValue(100000);
		leaningLevelOptions.add(dicOption);
		List<String> subRow = new ArrayList<String>();
		for(DictionaryOption o : leaningLevelOptions ){
			subRow.add(o.getOptionText());
		}
		result.put("subRow", subRow);
		setResultData(result);
		return SUCCESS;
	}

	public String exportPolityDistrict(){
		Map<String, Object> result = new HashMap<String, Object>();

		String districtCode = getRequest().getParameter("districtCode");
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		}else{
			district = this.secrecyPersonModuleService.get(districtCode, District.class);
		}
		String organId = getRequest().getParameter("organId");

		String countScope = getRequest().getParameter("countScope");
		if( countScope==null || "".equals(countScope) ){
			countScope = "organ";
		}

		List<DictionaryOption> polityOptions = polityOptionsDeal();
		Map<String, List<Map<String, Map<Integer, Integer>>>> statisticsListPolity = this.secrecyPersonModuleService.statisticsByPolityOfAll(polityOptions, district, organId,SECRECY_STATUS_NOW, countScope);
		result.put("result1", this.collationOfData(statisticsListPolity, polityOptions,"政治面貌"));

		Map<String, List<Map<String, Map<Integer, Integer>>>> statisticsListPolity1 = this.secrecyPersonModuleService.statisticsByPolityOfDistrict(polityOptions, district, organId,SECRECY_STATUS_NOW, countScope);
		result.put("result2", this.collationOfData(statisticsListPolity1, polityOptions,"政治面貌"));

		DictionaryOption dicOption = new DictionaryOption();
		dicOption.setOptionId("");
		dicOption.setOptionText("合计");
		dicOption.setOptionValue(100000);
		polityOptions.add(dicOption);
		List<String> subRow = new ArrayList<String>();
		for(DictionaryOption o : polityOptions ){
			subRow.add(o.getOptionText());
		}
		result.put("subRow", subRow);
		setResultData(result);
		return SUCCESS;
	}

	public String exportPolityOrgan(){
		Map<String, Object> result = new HashMap<String, Object>();

		String districtCode = getRequest().getParameter("districtCode");
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		}else{
			district = this.secrecyPersonModuleService.get(districtCode, District.class);
		}
		String organId = getRequest().getParameter("organId");

		List<DictionaryOption> polityOptions = polityOptionsDeal();
		String countScope = getRequest().getParameter("countScope");
		if( countScope==null || "".equals(countScope) ){
			countScope = "organ";
		}

		Map<String, List<Map<String, Map<Integer, Integer>>>> statisticsListPolity = this.secrecyPersonModuleService.statisticsByPolityOfOrgan(polityOptions, district, organId,SECRECY_STATUS_NOW, countScope);
		result.put("result1", this.collationOfData(statisticsListPolity, polityOptions,"政治面貌"));

		DictionaryOption dicOption = new DictionaryOption();
		dicOption.setOptionId("");
		dicOption.setOptionText("合计");
		dicOption.setOptionValue(100000);
		polityOptions.add(dicOption);
		List<String> subRow = new ArrayList<String>();
		for(DictionaryOption o : polityOptions ){
			subRow.add(o.getOptionText());
		}
		result.put("subRow", subRow);
		setResultData(result);
		return SUCCESS;
	}

	public String exportAdminTechDistrict(){
		Map<String, Object> result = new HashMap<String, Object>();

		String districtCode = getRequest().getParameter("districtCode");
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		}else{
			district = this.secrecyPersonModuleService.get(districtCode, District.class);
		}
		String organId = getRequest().getParameter("organId");

		String countScope = getRequest().getParameter("countScope");
		if( countScope==null || "".equals(countScope) ){
			countScope = "organ";
		}

		//行政级别
		List<DictionaryOption> adminLevelOptions = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "person_admin_level");
		//技术职称
		List<DictionaryOption> technicTitleLevelOptions = DictionaryContext.getInstance().getDictionaryService().getOptionList("person", "technic_title_level");

		Map<String, List<Map<String, Map<Integer, Integer>>>> statisticsList_admin = this.secrecyPersonModuleService.statisticsByAdminOfAll(adminLevelOptions, district, organId,SECRECY_STATUS_NOW, countScope);
		result.put("result1", this.collationOfData(statisticsList_admin, adminLevelOptions,"行政级别"));

		Map<String, List<Map<String, Map<Integer, Integer>>>> statisticsList_admin1 = this.secrecyPersonModuleService.statisticsByAdminOfDistrict(adminLevelOptions, district, organId,SECRECY_STATUS_NOW, countScope);
		result.put("result11", this.collationOfData(statisticsList_admin1, adminLevelOptions,"行政级别"));

		Map<String, List<Map<String, Map<Integer, Integer>>>> statisticsList_tech = this.secrecyPersonModuleService.statisticsByTechLevelOfAll(technicTitleLevelOptions, district, organId,SECRECY_STATUS_NOW, countScope);
		result.put("result2", this.collationOfData(statisticsList_tech, technicTitleLevelOptions,"技术职称"));

		Map<String, List<Map<String, Map<Integer, Integer>>>> statisticsList_tech1 = this.secrecyPersonModuleService.statisticsByTechLevelOfDistrict(technicTitleLevelOptions, district, organId,SECRECY_STATUS_NOW, countScope);
		result.put("result21", this.collationOfData(statisticsList_tech1, technicTitleLevelOptions,"技术职称"));

		DictionaryOption dicOption = new DictionaryOption();
		dicOption.setOptionId("");
		dicOption.setOptionText("合计");
		dicOption.setOptionValue(100000);
		adminLevelOptions.add(dicOption);
		List<String> subRow1 = new ArrayList<String>();
		for(DictionaryOption o : adminLevelOptions ){
			subRow1.add(o.getOptionText());
		}
		result.put("subRow1", subRow1);

		DictionaryOption dicOption2 = new DictionaryOption();
		dicOption2.setOptionId("");
		dicOption2.setOptionText("合计");
		dicOption2.setOptionValue(100000);
		technicTitleLevelOptions.add(dicOption2);
		List<String> subRow2 = new ArrayList<String>();
		for(DictionaryOption o : technicTitleLevelOptions ){
			subRow2.add(o.getOptionText());
		}
		result.put("subRow2", subRow2);
		setResultData(result);
		return SUCCESS;
	}

	public String exportAdminTechOrgan(){
		Map<String, Object> result = new HashMap<String, Object>();

		String districtCode = getRequest().getParameter("districtCode");
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		}else{
			district = this.secrecyPersonModuleService.get(districtCode, District.class);
		}
		String organId = getRequest().getParameter("organId");

		String countScope = getRequest().getParameter("countScope");
		if( countScope==null || "".equals(countScope) ){
			countScope = "organ";
		}

		//行政级别
		List<DictionaryOption> adminLevelOptions = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "person_admin_level");
		//技术职称
		List<DictionaryOption> technicTitleLevelOptions = DictionaryContext.getInstance().getDictionaryService().getOptionList("person", "technic_title_level");

		Map<String, List<Map<String, Map<Integer, Integer>>>> statisticsList_admin = this.secrecyPersonModuleService.statisticsByAdminOfOrgan(adminLevelOptions, district, organId,SECRECY_STATUS_NOW, countScope);
		result.put("result1", this.collationOfData(statisticsList_admin, adminLevelOptions,"行政级别"));

		Map<String, List<Map<String, Map<Integer, Integer>>>> statisticsList_tech = this.secrecyPersonModuleService.statisticsByTechLevelOfOrgan(technicTitleLevelOptions, district, organId,SECRECY_STATUS_NOW, countScope);
		result.put("result2", this.collationOfData(statisticsList_tech, technicTitleLevelOptions,"技术职称"));

		DictionaryOption dicOption = new DictionaryOption();
		dicOption.setOptionId("");
		dicOption.setOptionText("合计");
		dicOption.setOptionValue(100000);
		adminLevelOptions.add(dicOption);
		List<String> subRow1 = new ArrayList<String>();
		for(DictionaryOption o : adminLevelOptions ){
			subRow1.add(o.getOptionText());
		}
		result.put("subRow1", subRow1);

		DictionaryOption dicOption2 = new DictionaryOption();
		dicOption2.setOptionId("");
		dicOption2.setOptionText("合计");
		dicOption2.setOptionValue(100000);
		technicTitleLevelOptions.add(dicOption2);
		List<String> subRow2 = new ArrayList<String>();
		for(DictionaryOption o : technicTitleLevelOptions ){
			subRow2.add(o.getOptionText());
		}
		result.put("subRow2", subRow2);
		setResultData(result);
		return SUCCESS;	}

	public String exportPersonTypeDistrict(){
		Map<String, Object> result = new HashMap<String, Object>();

		String districtCode = getRequest().getParameter("districtCode");
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		}else{
			district = this.secrecyPersonModuleService.get(districtCode, District.class);
		}
		String organId = getRequest().getParameter("organId");

		String countScope = getRequest().getParameter("countScope");
		if( countScope==null || "".equals(countScope) ){
			countScope = "organ";
		}

		//人员类型
		List<DictionaryOption> personTypeOptions = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "person_type");
		Map<String, List<Map<String, Map<Integer, Integer>>>> statisticsListPersonType = this.secrecyPersonModuleService.statisticsByPersonTypeOfAll(personTypeOptions, district, organId,SECRECY_STATUS_NOW, countScope);
		result.put("result1", this.collationOfData(statisticsListPersonType, personTypeOptions,"人员类型"));

		Map<String, List<Map<String, Map<Integer, Integer>>>> statisticsListPersonType1 = this.secrecyPersonModuleService.statisticsByPersonTypeOfDistrict(personTypeOptions, district, organId,SECRECY_STATUS_NOW, countScope);
		result.put("result2", this.collationOfData(statisticsListPersonType1, personTypeOptions,"人员类型"));

		DictionaryOption dicOption = new DictionaryOption();
		dicOption.setOptionId("");
		dicOption.setOptionText("合计");
		dicOption.setOptionValue(100000);
		personTypeOptions.add(dicOption);
		List<String> subRow = new ArrayList<String>();
		for(DictionaryOption o : personTypeOptions ){
			subRow.add(o.getOptionText());
		}
		result.put("subRow", subRow);
		setResultData(result);
		return SUCCESS;
	}

	public String exportAPersonTypeOrgan(){
		Map<String, Object> result = new HashMap<String, Object>();

		String districtCode = getRequest().getParameter("districtCode");
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		}else{
			district = this.secrecyPersonModuleService.get(districtCode, District.class);
		}
		String organId = getRequest().getParameter("organId");

		String countScope = getRequest().getParameter("countScope");
		if( countScope==null || "".equals(countScope) ){
			countScope = "organ";
		}

		//人员类型
		List<DictionaryOption> personTypeOptions = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "person_type");
		Map<String, List<Map<String, Map<Integer, Integer>>>> statisticsListPersonType = this.secrecyPersonModuleService.statisticsByPersonTypeOfOrgan(personTypeOptions, district, organId,SECRECY_STATUS_NOW, countScope);
		result.put("result1", this.collationOfData(statisticsListPersonType, personTypeOptions,"人员类型"));

		DictionaryOption dicOption = new DictionaryOption();
		dicOption.setOptionId("");
		dicOption.setOptionText("合计");
		dicOption.setOptionValue(100000);
		personTypeOptions.add(dicOption);
		List<String> subRow = new ArrayList<String>();
		for(DictionaryOption o : personTypeOptions ){
			subRow.add(o.getOptionText());
		}
		result.put("subRow", subRow);
		setResultData(result);
		return SUCCESS;
	}

	/**
	 *
	 * <p>
	 * 方法名：exportCurrent 本单位导出
	 * </p>
	 * <p>
	 * 宋亚非 2013-7-29 下午2:26:45
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
	public String exportCurrent(){
		Map<String, Object> result = new HashMap<String, Object>();

		String districtCode = getRequest().getParameter("districtCode");
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		}else{
			district = this.secrecyPersonModuleService.get(districtCode, District.class);
		}
		//单位统计明细导出
		String organId = getRequest().getParameter("organId");
		if( !(organId!=null && !"".equals(organId)) ){
			//本单位统计明细导出
			organId = getCurrentUser().getOrgan().getOrganId();
		}

		List<Map<String, Integer>> ageRange = ageRangDeal();

		List<DictionaryOption> ageRangeDic = new ArrayList<DictionaryOption>();
		int dicNum = 0;
		for( Map<String, Integer> aRange : ageRange ){
			DictionaryOption dicOption = new DictionaryOption();
			Integer startNum = aRange.get("start");
			Integer endNum = aRange.get("end");
			if( startNum==0 || startNum==null ){
				dicOption.setOptionId(dicNum+"");
				dicOption.setOptionText("小于"+endNum+"岁");
				dicOption.setOptionValue(dicNum);
			}
			if( startNum!=null && startNum>0 && endNum!=null && endNum >0 ){
				dicOption.setOptionId(dicNum+"");
				dicOption.setOptionText(startNum+"岁至"+endNum+"岁");
				dicOption.setOptionValue(dicNum);
			}
			if( startNum!=null && endNum==null ){
				dicOption.setOptionId(dicNum+"");
				dicOption.setOptionText("大于"+startNum+"岁");
				dicOption.setOptionValue(dicNum);
			}
			ageRangeDic.add(dicOption);
			dicNum++;
		}

		String countScope = getRequest().getParameter("countScope");
		if( countScope==null || "".equals(countScope) ){
			countScope = "organ";
		}

		List<Map<String, List<Map<Integer, Integer>>>> ageStatisticsList = this.secrecyPersonModuleService.statisticsByAge(ageRange, null, organId,SECRECY_STATUS_NOW, countScope);

		if( ageStatisticsList!=null ){
			List<Map<String, Map<Integer, Integer>>> dataResult = new ArrayList<Map<String,Map<Integer,Integer>>>();
			for( Map<String, List<Map<Integer, Integer>>> layer1 : ageStatisticsList ){
				for( String layer1Key : layer1.keySet() ){
					Map<String,Map<Integer,Integer>> tmp = new HashMap<String, Map<Integer,Integer>>();
					if(layer1.get(layer1Key)!=null && layer1.get(layer1Key).size()>0){
						tmp.put(layer1Key, layer1.get(layer1Key).get(0));
					}else{
						tmp.put(layer1Key, null);
					}
					dataResult.add(tmp);
				}
			}
			result.put("ageResult", this.collationOfDataDetail(dataResult, ageRangeDic,"年龄分段"));
		}else{
			result.put("ageResult", new ArrayList<String>());
		}


		//民族
		List<DictionaryOption> nationOptions = DictionaryContext.getInstance().getDictionaryService().getOptionList("person", "nation");
		List<Map<String, Map<Integer, Integer>>> nationStatisticsList = this.secrecyPersonModuleService.statisticsByNation(nationOptions, null, organId,SECRECY_STATUS_NOW, countScope);
		result.put("nationResult", this.collationOfDataDetail(nationStatisticsList, nationOptions,"民族"));

		//学历
		List<DictionaryOption> leaningLevelOptions = DictionaryContext.getInstance().getDictionaryService().getOptionList("person", "learning_level");
		List<Map<String, Map<Integer, Integer>>> leaningLevelstatisticsList = this.secrecyPersonModuleService.statisticsByLeanLevel(leaningLevelOptions, null, organId,SECRECY_STATUS_NOW, countScope);
		result.put("learnLeverResult", this.collationOfDataDetail(leaningLevelstatisticsList, leaningLevelOptions,"学历"));

		List<DictionaryOption> polityOptions = polityOptionsDeal();
		List<Map<String, Map<Integer, Integer>>> statisticsListPolity = this.secrecyPersonModuleService.statisticsByPolity(polityOptions, null, organId,SECRECY_STATUS_NOW, countScope);
		result.put("polityResult", this.collationOfDataDetail(statisticsListPolity, polityOptions,"政治面貌"));

		//行政级别
		List<DictionaryOption> adminLevelOptions = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "person_admin_level");
		//技术职称
		List<DictionaryOption> technicTitleLevelOptions = DictionaryContext.getInstance().getDictionaryService().getOptionList("person", "technic_title_level");
		List<Map<String, Map<Integer, Integer>>> statisticsList_admin = this.secrecyPersonModuleService.statisticsByAdmin(adminLevelOptions, null, organId,SECRECY_STATUS_NOW, countScope);
		List<Map<String, Map<Integer, Integer>>> statisticsList_tech = this.secrecyPersonModuleService.statisticsByTechLevel(technicTitleLevelOptions, null, organId,SECRECY_STATUS_NOW, countScope);
		result.put("adminResult", this.collationOfDataDetail(statisticsList_admin, adminLevelOptions,"行政级别"));
		result.put("techResult", this.collationOfDataDetail(statisticsList_tech, technicTitleLevelOptions,"技术职称"));

		//人员类型
		List<DictionaryOption> personTypeOptions = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "person_type");
		List<Map<String, Map<Integer, Integer>>> statisticsListPersonType = this.secrecyPersonModuleService.statisticsByPersonType(personTypeOptions, null, organId,SECRECY_STATUS_NOW, countScope);
		result.put("personTypeResult", this.collationOfDataDetail(statisticsListPersonType, personTypeOptions,"人员类型"));

		setResultData(result);
		return SUCCESS;
	}

	/**
	 * 涉密人员列表的导出
	 * @return
	 */
	public String exportExcel_List() {
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
			this.putToRequest("fromQuery", fromQuery);
			this.putToRequest("checkLower", checkLower);
		}else{
			//查询登录用户与创建单位相同的信息
			organ = getCurrentUser().getOrgan();
		}

		//要害部[门]不为空，查询该要害部[门]下的所有机关涉密人员的记录  梁文杰 2013-07-20修改
		if(departmentId!=null && !departmentId.equals("")) {
			Department depart = new Department();
			depart.setDepartmentId(departmentId);
			if(secrecyPerson==null) {
				secrecyPerson = new SecrecyPerson();
				secrecyPerson.setDepartment(depart);
			}else {
				secrecyPerson.setDepartment(depart);
			}
		}

		// 获取当前单位涉密人员列表
		List<SecrecyPerson> secrecyPersonList = new ArrayList<SecrecyPerson>();
		try {
			secrecyPersonList =	secrecyPersonModuleService.getPageAllList(null, secrecyPerson, organ, district,null, checkLower, SecrecyPersonAction.SECRECY_STATUS_NOW);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Map<String, Object> params = new HashMap<String, Object>();
		//被导出的数据
		params.put("secrecyPersonList", secrecyPersonList);
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
	 * <p>
	 * 方法名称  zhtj_query_method
	 * </p>
	 * <p>
	 * 宋亚非 2013-9-6 下午3:42:50
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
		List<ZongHeTongJiStatDto> secrecyCountryItemStatDtoList = secrecyPersonModuleService.count_SecrecyPerson_District(districtList, false);
		//查询  子行政区划的明细
		List<ZongHeTongJiStatDto> childrenStatDtoList = secrecyPersonModuleService.count_SecrecyPerson_District(childrenDistrictList, true);

		putToRequest("secrecyCountryItemStatDtoList", secrecyCountryItemStatDtoList);
		putToRequest("childrenStatDtoList", childrenStatDtoList);
		putToRequest("districtCode", district.getDistrictCode());
		putToRequest("district", district);
		putToRequest("topDistrict", this.getCurrentUser().getOrgan().getDistrict());
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

		List<ZongHeTongJiStatDto> secrecyCountryItemStatDtoList = secrecyPersonModuleService.count_SecrecyPerson_District(district, true,organ);
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

		List<SecrecyPerson> secrecyPersonList = new ArrayList<SecrecyPerson>();
		PageSortModel<SecrecyPerson> psm = new PageSortModel<SecrecyPerson>(this.getRequest(), "secrecyPersonList");

		//设置密级
		String secrecy_level = getRequest().getParameter("secrecyLevel");//密级
		if(secrecyPerson==null) {
			secrecyPerson = new SecrecyPerson();
		}
		if(secrecy_level!=null && !"".equals(secrecy_level)) {
			secrecyPerson.setSecrecyPersonLevel(Integer.parseInt(secrecy_level));
		}

		String organId =  getRequest().getParameter("organId"); //单位id
		if(organId!=null && !"".equals(organId)) {//如果有单位，就用单位对象
			Organ organ = new Organ();
			organ.setOrganId(organId);
			//secrecyPersonList =  secrecyPersonModuleService.queryCountryItemList(psm, organ, secrecyPerson);
			try {
				secrecyPersonList =  secrecyPersonModuleService.getPageAllList(psm, secrecyPerson, organ, null, null, null, BmpAction.SECRECY_STATUS_NOW);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}else {//如果没有单位对象   就需要使用 行政区划对象   也说明点到合计了
			district = districtService.get(district.getDistrictCode());
			//secrecyPersonList =  secrecyPersonModuleService.queryCountryItemList(psm, district, secrecyPerson);
			try {
				secrecyPersonList =  secrecyPersonModuleService.getPageAllList(psm, secrecyPerson, null, district, null, null, BmpAction.SECRECY_STATUS_NOW);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		putToRequest("secrecyPersonList", secrecyPersonList);
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
		List<ZongHeTongJiStatDto> dataStatDtoList = secrecyPersonModuleService.count_SecrecyPerson_District(districtList, false);

		//导出设置
  		Map<String, Object> params = new HashMap<String, Object>();
  		//被导出的数据
  		params.put("dataStatDtoList", dataStatDtoList);
		params.put("district", district);
		params.put("title", "涉密人员");
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
		List<ZongHeTongJiStatDto> childrenStatDtoList = secrecyPersonModuleService.count_SecrecyPerson_District(childrenDistrictList, true);

		//导出设置
  		Map<String, Object> params = new HashMap<String, Object>();
  		//被导出的数据
  		params.put("childrenStatDtoList", childrenStatDtoList);
		params.put("district", district);
		params.put("title", "涉密人员");
		this.putToRequest("district", district);
  		setResultData(params);
		return SUCCESS;
	}

	/*********************************************************************************/

	//综合统计  行政区划显示
	public String zhtj_query(){
		district = districtService.get(this.getCurrentUser().getOrgan().getDistrict().getDistrictCode());
		zhtj_query_method();
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
		try {
			secrecyPersonList =  secrecyPersonModuleService.getPageAllList(null, secrecyPerson, secrecyPersonModuleService.get(organId, Organ.class), null, null, null, BmpAction.SECRECY_STATUS_NOW);
			// 计算年龄（用personAge作为年龄存放使用）
			for (SecrecyPerson secrecyPerson : secrecyPersonList) {
			        if( secrecyPerson.getUserInfo().getBirthDate()!=null ){
			                secrecyPerson.setPersonAge(DateUtils.getYearNumber(secrecyPerson.getUserInfo().getBirthDate(), new Date()) + "");
			        }
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
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
		resultData.put("id", "4");
		try {
			secrecyPersonList =  secrecyPersonModuleService.getPageAllList(null, secrecyPerson, getCurrentUser().getOrgan(), null, null, null, BmpAction.SECRECY_STATUS_NOW);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String msg = dataValidateService.validateData("涉密人员", secrecyPersonList, "4");
		resultData.put("msg", msg);
		setResultData(resultData);
		return JSON;
	}
	// ******************** Setter & Getter ********************

	/**
	 * 返回secrecyPerson
	 * @return secrecyPerson
	 */
	public SecrecyPerson getSecrecyPerson() {
		return secrecyPerson;
	}

	/**
	 * 设置secrecyPerson
	 * @param secrecyPerson secrecyPerson
	 */
	public void setSecrecyPerson(SecrecyPerson secrecyPerson) {
		this.secrecyPerson = secrecyPerson;
	}

	/**
	 * 返回secrecyPersonService
	 * @return secrecyPersonService
	 */
	public SecrecyPersonService getSecrecyPersonService() {
		return secrecyPersonService;
	}

	/**
	 * 设置secrecyPersonService
	 * @param secrecyPersonService secrecyPersonService
	 */
	public void setSecrecyPersonService(SecrecyPersonService secrecyPersonService) {
		this.secrecyPersonService = secrecyPersonService;
	}

	/**
	 * 返回secrecyPersonModuleService
	 * @return secrecyPersonModuleService
	 */
	public SecrecyPersonModuleService getSecrecyPersonModuleService() {
		return secrecyPersonModuleService;
	}

	/**
	 * 设置secrecyPersonModuleService
	 * @param secrecyPersonModuleService secrecyPersonModuleService
	 */
	public void setSecrecyPersonModuleService(
			SecrecyPersonModuleService secrecyPersonModuleService) {
		this.secrecyPersonModuleService = secrecyPersonModuleService;
	}

	/**
	 * 返回secrecyPersonList
	 * @return secrecyPersonList
	 */
	public List<SecrecyPerson> getSecrecyPersonList() {
		return secrecyPersonList;
	}

	/**
	 * 设置secrecyPersonList
	 * @param secrecyPersonList secrecyPersonList
	 */
	public void setSecrecyPersonList(List<SecrecyPerson> secrecyPersonList) {
		this.secrecyPersonList = secrecyPersonList;
	}

	/**
	 * 返回userInfo
	 * @return userInfo
	 */
	public UserInfo getUserInfo() {
		return userInfo;
	}

	/**
	 * 设置userInfo
	 * @param userInfo userInfo
	 */
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	/**
	 * 返回userInfoService
	 * @return userInfoService
	 */
	public UserInfoService getUserInfoService() {
		return userInfoService;
	}


	/**
	 * 设置userInfoService
	 * @param userInfoService userInfoService
	 */
	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}


	/**
	 * 返回secrecyPersonIds
	 * @return secrecyPersonIds
	 */
	public String getSecrecyPersonIds() {
		return secrecyPersonIds;
	}


	/**
	 * 设置secrecyPersonIds
	 * @param secrecyPersonIds secrecyPersonIds
	 */
	public void setSecrecyPersonIds(String secrecyPersonIds) {
		this.secrecyPersonIds = secrecyPersonIds;
	}

	/**
	 * 返回needReload
	 * @return needReload
	 */
	public Boolean getNeedReload() {
		return needReload;
	}

	/**
	 * 设置needReload
	 * @param needReload needReload
	 */
	public void setNeedReload(Boolean needReload) {
		this.needReload = needReload;
	}

	/**
	 * 返回district
	 * @return district
	 */
	public District getDistrict() {
		return district;
	}

	/**
	 * 设置district
	 * @param district district
	 */
	public void setDistrict(District district) {
		this.district = district;
	}

	/**
	 * 返回districtService
	 * @return districtService
	 */
	public DistrictService getDistrictService() {
		return districtService;
	}

	/**
	 * 设置districtService
	 * @param districtService districtService
	 */
	public void setDistrictService(DistrictService districtService) {
		this.districtService = districtService;
	}

	/**
	 * 返回status
	 * @return status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 设置status
	 * @param status status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 返回userInfoId
	 * @return userInfoId
	 */
	public String getUserInfoId() {
		return userInfoId;
	}

	/**
	 * 设置userInfoId
	 * @param userInfoId userInfoId
	 */
	public void setUserInfoId(String userInfoId) {
		this.userInfoId = userInfoId;
	}

	/**
	 * 返回actionFlag
	 * @return actionFlag
	 */
	public String getActionFlag() {
		return actionFlag;
	}

	/**
	 * 设置actionFlag
	 * @param actionFlag actionFlag
	 */
	public void setActionFlag(String actionFlag) {
		this.actionFlag = actionFlag;
	}

	/**
	 * 返回departmentId
	 * @return departmentId
	 */
	public String getDepartmentId() {
		return departmentId;
	}

	/**
	 * 设置departmentId
	 * @param departmentId departmentId
	 */
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	/**
	 * @param secrecyPersonExchanger 设置secrecyPersonExchanger
	 */
	public void setSecrecyPersonExchanger(
			SecrecyPersonExchanger secrecyPersonExchanger) {
		this.secrecyPersonExchanger = secrecyPersonExchanger;
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
	 * @return the secrecyPersonDecryptionService
	 */
	public SecrecyPersonDecryptionService getSecrecyPersonDecryptionService() {
		return secrecyPersonDecryptionService;
	}

	/**
	 * @param secrecyPersonDecryptionService the secrecyPersonDecryptionService to set
	 */
	public void setSecrecyPersonDecryptionService(
			SecrecyPersonDecryptionService secrecyPersonDecryptionService) {
		this.secrecyPersonDecryptionService = secrecyPersonDecryptionService;
	}

	/**
	 * @return the secrecyPersonLevelChangeService
	 */
	public SecrecyPersonLevelChangeService getSecrecyPersonLevelChangeService() {
		return secrecyPersonLevelChangeService;
	}

	/**
	 * @param secrecyPersonLevelChangeService the secrecyPersonLevelChangeService to set
	 */
	public void setSecrecyPersonLevelChangeService(
			SecrecyPersonLevelChangeService secrecyPersonLevelChangeService) {
		this.secrecyPersonLevelChangeService = secrecyPersonLevelChangeService;
	}

	/**
	 * @return the secrecyPersonLevelChange
	 */
	public SecrecyPersonLevelChange getSecrecyPersonLevelChange() {
		return secrecyPersonLevelChange;
	}

	/**
	 * @param secrecyPersonLevelChange the secrecyPersonLevelChange to set
	 */
	public void setSecrecyPersonLevelChange(
			SecrecyPersonLevelChange secrecyPersonLevelChange) {
		this.secrecyPersonLevelChange = secrecyPersonLevelChange;
	}

	/**
	 * @return the secrecyPersonDecryption
	 */
	public SecrecyPersonDecryption getSecrecyPersonDecryption() {
		return secrecyPersonDecryption;
	}

	/**
	 * @param secrecyPersonDecryption the secrecyPersonDecryption to set
	 */
	public void setSecrecyPersonDecryption(
			SecrecyPersonDecryption secrecyPersonDecryption) {
		this.secrecyPersonDecryption = secrecyPersonDecryption;
	}

	/**
	 * @return the expBtnFlag
	 */
	public String getExpBtnFlag() {
		return expBtnFlag;
	}

	/**
	 * @param expBtnFlag the expBtnFlag to set
	 */
	public void setExpBtnFlag(String expBtnFlag) {
		this.expBtnFlag = expBtnFlag;
	}

	/**
	 * @return the partPersonModuleService
	 */
	public PartPersonModuleService getPartPersonModuleService() {
		return partPersonModuleService;
	}

	/**
	 * @param partPersonModuleService the partPersonModuleService to set
	 */
	public void setPartPersonModuleService(
			PartPersonModuleService partPersonModuleService) {
		this.partPersonModuleService = partPersonModuleService;
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