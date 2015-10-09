package com.cdthgk.bmp.keyPart.action;

import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;

import com.cdthgk.agenda.vo.Agenda;
import com.cdthgk.bmp.core.action.BmpAction;
import com.cdthgk.bmp.keyPart.dto.KeyPartDto;
import com.cdthgk.bmp.keyPart.service.KeyPartChangeService;
import com.cdthgk.bmp.keyPart.service.KeyPartSecrecyClearService;
import com.cdthgk.bmp.keyPart.service.PartModuleService;
import com.cdthgk.bmp.keyPart.service.PartPersonModuleService;
import com.cdthgk.bmp.keyPart.service.PartService;
import com.cdthgk.bmp.keyPart.transmitor.exchange.KeyPartExchanger;
import com.cdthgk.bmp.keyPart.vo.KeyPartChange;
import com.cdthgk.bmp.keyPart.vo.KeyPartSecrecyClear;
import com.cdthgk.bmp.keyPart.vo.Part;
import com.cdthgk.bmp.keyPart.vo.PartPerson;
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
import com.cdthgk.component.ioc.ContextUtils;
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

import ec.common.PageSortModel;

/**
 * <p>
 * PartAction.java 要害部位控制类
 * </p>
 * <blockquote>
 * <h4>历史修改记录</h4>
 * <ul>
 * * 刘椿成 2012-12-14 13:26:59 加入注释
 * </ul>
 * </blockquote>
 * <p>
 * copyright liucc 2012, all rights reserved.
 * </p>
 * @author 刘椿成
 * @since 1.0
 * @version 1.0
 */
public class PartAction extends BmpAction {

	// Fields
	private static final long serialVersionUID = 1L;
	private Part part;
	private Integer secrecyLevel;
	private List<Part> partList;
	private PartModuleService partModuleService;
	private DataValidateService dataValidateService;
	private String partPersonIds;
	private String partIds;
	private Organ organ;
	private District district;
	private String listIds;
	private Boolean needReload = false;
	private UserInfo userInfo;

	// 涉密人员使用字段
	private PartPerson partPerson;
	private List<PartPerson> partPersonList;
	private PartPersonModuleService partPersonModuleService;
	private SecrecyPerson secrecyPerson;
	private SecrecyPersonModuleService secrecyPersonModuleService;
	// 附件使用字段
	private AttachmentService attachmentService;
	private List<String> keyPartAttachs;
	private DataSource dataSource;

	//部门变更或者部门改名的标志：1：部门改名；2部门变更
	private String deptFlag;
	private String departmentId;
	private String showType = "0";

	private KeyPartExchanger keyPartExchanger;
	private ServerReportMappingService serverReportMappingService;

	/**
	 * spring注入
	 */
	//要害部位密级变更
	private KeyPartChangeService keyPartChangeService;
	//要害部位密级解除
	private KeyPartSecrecyClearService keyPartSecrecyClearService;
	//要害部位  服务
	private PartService partService;
	//行政区划 服务层
	private DistrictService districtService;
	private OrganService organService;

	/**
	 * STRUTS2 domainMoudle注入
	 */
	//要害部位密级变更
	private KeyPartChange keyPartChange;
	//要害部位密级解除
	private KeyPartSecrecyClear keyPartSecrecyClear;

	/**
	 * <p>
	 * 列表页面
	 * </p>
	 * <p>
	 * 刘椿成 2012-12-14 10:43:03
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * <p>
	 * copyright 刘椿成  2012, all rights reserved.
	 * </p>
	 *
	 * @author 刘椿成
	 * @since 1.0
	 * @version 1.0
	 */
	public String list(){

		//标志的传递  业务标志  1表示查询   0表示普通业务模块
		String ywFlag = this.getRequest().getParameter("ywFlag");
		if(ywFlag==null) {
			ywFlag="0";
		}
		this.putToRequest("ywFlag", ywFlag);//标志的传递  业务标志  1表示查询   0表示普通业务模块

		//行政区划编码
		String districtCode = this.getRequest().getParameter("districtCode");
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

		return "list";
	}


	public String list_list(){
		// 获取list
		@SuppressWarnings("rawtypes")
		PageSortModel psm = new PageSortModel(getRequest(), "partList");
		if (organ == null) {
			organ = getCurrentUser().getOrgan(); // 返回当前登录单位
		}else {
			organ = partModuleService.get(getRequest().getParameter("organ.organId").toString(), Organ.class); // 得到指定的单位
		}

		//要害部[门]不为空，查询该要害部[门]下的所有要害部位的记录
		if(departmentId!=null && !departmentId.equals("")) {
			Department depart = new Department();
			depart.setDepartmentId(departmentId);
			if(part==null) {
				part = new Part();
				part.setDepartment(depart);
			}else {
				part.setDepartment(depart);
			}
		}

		//查看是否是内嵌的页面
		String nestedflag = this.getRequest().getParameter("nestedflag");
		if(nestedflag!=null && nestedflag.equals("1")) {//是内嵌页面
			this.putToRequest("nestedflag", nestedflag);
		}else {
			this.putToRequest("nestedflag", "0");//不是内嵌页面
		}

		//标志的传递  业务标志  1表示查询   0表示普通业务模块
		String ywFlag = this.getRequest().getParameter("ywFlag");
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
			partList = partModuleService.getListPage(psm, part, district, isChildren);
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
			partList = partModuleService.getListPage(psm, part, organ);
		}

		putToRequest("partList", partList);
		return "list";
	}

	/**
	 * 密级变更列表
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String list_change(){
		// 获取list
		List<KeyPartChange> keyPart_changelist = new ArrayList<KeyPartChange>();
		PageSortModel psm = new PageSortModel(getRequest(), "keyPart_changelist");

		//要害部[门]不为空，查询该要害部[门]下的所有要害部位的密级变更记录
		if(departmentId!=null && !departmentId.equals("")) {
			Department depart = new Department();
			depart.setDepartmentId(departmentId);
			if(keyPartChange==null) {//密级变更对象为空  初始化一个
				keyPartChange = new KeyPartChange();
				if(part == null) {
					part = new Part();
					part.setDepartment(depart);
					keyPartChange.setKeyPartId(part);
				}else {
					part.setDepartment(depart);
					keyPartChange.setKeyPartId(part);
				}
			}else {
				if(part == null) {
					part = new Part();
					part.setDepartment(depart);
					keyPartChange.setKeyPartId(part);
				}else {
					part.setDepartment(depart);
					keyPartChange.setKeyPartId(part);
				}
			}
		}

		//标志的传递  业务标志  1表示查询   0表示普通业务模块
		String ywFlag = this.getRequest().getParameter("ywFlag");
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
			keyPart_changelist = keyPartChangeService.queryKeyPartChangeList(psm, keyPartChange, null, district,isChildren);
		}else if(ywFlag.equals("0")){ //普通页面按照  单位来查询
			Organ organ = this.getCurrentUser().getOrgan();
			keyPart_changelist = keyPartChangeService.queryKeyPartChangeList(psm, keyPartChange, organ, null, 0);
		}

		putToRequest("keyPart_changelist", keyPart_changelist);
		return "list";
	}

	/**
	 * 密级解除列表
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String list_clear(){
		// 获取list
		List<KeyPartSecrecyClear> keyPart_clearlist = new ArrayList<KeyPartSecrecyClear>();
		PageSortModel psm = new PageSortModel(getRequest(), "keyPart_clearlist");

		//要害部[门]不为空，查询该要害部[门]下的所有要害部位的密级解除记录
		if(departmentId!=null && !departmentId.equals("")) {
			Department depart = new Department();
			depart.setDepartmentId(departmentId);
			if(keyPartSecrecyClear==null) {//密级解除对象为空  初始化一个
				keyPartSecrecyClear = new KeyPartSecrecyClear();
				if(part == null) {
					part = new Part();
					part.setDepartment(depart);
					keyPartSecrecyClear.setKeyPartId(part);
				}else {
					part.setDepartment(depart);
					keyPartSecrecyClear.setKeyPartId(part);
				}
			}else {
				if(part == null) {
					part = new Part();
					part.setDepartment(depart);
					keyPartSecrecyClear.setKeyPartId(part);
				}else {
					part.setDepartment(depart);
					keyPartSecrecyClear.setKeyPartId(part);
				}
			}
		}


		//标志的传递  业务标志  1表示查询   0表示普通业务模块
		String ywFlag = this.getRequest().getParameter("ywFlag");
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
			keyPart_clearlist = keyPartSecrecyClearService.queryKeyPartClearList(psm, keyPartSecrecyClear,null,district,isChildren);
		}else if(ywFlag.equals("0")){ //普通页面按照  单位来查询
			Organ organ = this.getCurrentUser().getOrgan();
			keyPart_clearlist = keyPartSecrecyClearService.queryKeyPartClearList(psm, keyPartSecrecyClear,organ, null,0);
		}

		putToRequest("keyPart_clearlist", keyPart_clearlist);
		return "list";
	}

	/**
	 *
	 * <p>
	 * 方法名：exportData 导出列表信息
	 * </p>
	 * <p>
	 * 宋亚非 2013-5-21 下午2:57:56
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
	public String exportData(){
		Map<String, Object> params = new HashMap<String, Object>();
		if (organ == null) {
			organ = getCurrentUser().getOrgan(); // 返回当前登录单位
		}else {
			organ = partModuleService.get(getRequest().getParameter("organ.organId").toString(), Organ.class); // 得到指定的单位
		}
		List<DictionaryOption> secrecyLevelList = this.partModuleService.getDictionaryOptionList("bmp", "secrecy_level_section");
		params.put("secrecyLevelList", secrecyLevelList);
		params.put("partList", partModuleService.getListPage(null, part, organ));
		setResultData(params);
		return "success";
	}

	/**
	 * <p>
	 * 列表页面
	 * </p>
	 * <p>
	 * 宋亚非  2013-04-10 09:06:03
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * <p>
	 * copyright 刘椿成  2012, all rights reserved.
	 * </p>
	 *
	 * @author 刘椿成
	 * @since 1.0
	 * @version 1.0
	 */
	@SuppressWarnings("rawtypes")
	public String allList(){
		// 获取list
		PageSortModel psm = new PageSortModel(getRequest(), "partList");
		if (organ == null) {
			organ = getCurrentUser().getOrgan(); // 返回当前登录单位
		}else {
			organ = partModuleService.get(getRequest().getParameter("organ.organId").toString(), Organ.class); // 得到指定的单位
		}
		putToRequest("partList", partModuleService.getListPage(psm, part, organ, showType));
		return "list";
	}





	/**
	 * <p>
	 * 到新增页面
	 * </p>
	 * <p>
	 * 刘椿成 2012-12-14 10:43:03
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
	 * @author 刘椿成
	 * @since 1.0
	 * @version 1.0
	 */
	public String add() {
		if(departmentId!=null) {//如果部门id不为空的情况
			Department depart = partPersonModuleService.get(departmentId,Department.class);
			if(part==null) {
				part = new Part();
				part.setDepartment(depart);
			}
		}

		String nestedflag = this.getRequest().getParameter("nestedflag");
		if(nestedflag!=null && !nestedflag.equals("")) {//证明是内嵌页面
			putToRequest("nestedflag", nestedflag);
		}else {
			putToRequest("nestedflag", "0");
		}

		putToRequest("organ", getCurrentUser().getOrgan());
		putToRequest("part", part);
		return "add";
	}

	/**
	 * <p>
	 * 新增操作
	 * </p>
	 * <p>
	 * 刘椿成 2012-12-14 16:46:11
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * <p>
	 * </p>
	 * @author 刘椿成
	 * @since 1.0
	 * @version 1.0
	 */
	public String adding() {
		// 传入的字段
		part = partModuleService.saveSystemDataByPart(part, getCurrentUser(), null);
		part.setCreateperson(getCurrentUser());
		part.setCreateTime(new Date());
		part.setOrgan(getCurrentUser().getOrgan());
		//修改人：宋亚非   修改原因：数据库统一至一个库后，取消上报功能，填写信息后状态即为已上报   修改时间：2013-06-03
		//part.setReportState(ReportState.REPORT_NO);
		part.setReportState(ReportState.REPORT_YES);
		//设置解密状态默认为0
		part.setSecrecyStatus(0);

		partModuleService.save(part);
		part = partModuleService.get(part.getPartId());
		putToRequest("organ", getCurrentUser().getOrgan());

		// 上传附件
		attachmentService.updateHostId(part.getPartId(),keyPartAttachs);

		// 获取指定部位下涉密人员列表及计算涉密人员数量
		PageSortModel psm = new PageSortModel(getRequest(), "partPersonList");
		if (organ == null) {
			organ = getCurrentUser().getOrgan(); // 返回当前登录单位
		}else {
			organ = partModuleService.get(getRequest().getParameter("organ.organId").toString(), Organ.class); // 得到指定的单位
		}
		partPersonList = partPersonModuleService.getPersonListPage(psm, part, organ, SECRECY_STATUS_NOW);
		putToRequest("partPersonSize", partPersonList == null ? 0 :partPersonList.size());

		// 得到要害部位附件
		putToRequest("attachments", attachmentService.getAttachmentsByHostId(part.getPartId()));
		// 设置信息
		addActionMessage(getMessageConstant().getSaveSuccess()+ "请继续添加该要害部位下的涉密人员信息！");

                BusinessLog log = new BusinessLog();
                log.setBusinessName("保密要害部位 ");
                log.setPrimaryKey(part.getPartId());
                BusinessLogContext.getInstance().getBusinessLogService().saveAddBusinessLogByModule(getCurrentUser(), log, part);

                this.needReload = true;

		//是否是内嵌页面的标志   1表示内嵌页面    0表示否
		String nestedflag = this.getRequest().getParameter("nestedflag");
		putToRequest("nestedflag", nestedflag);

		return "edit";
	}

	/**
	 * <p>
	 * 到编辑页面
	 * </p>
	 * <p>
	 * 刘椿成 2012-12-15 10:43:03
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * <p>
	 * copyright 刘椿成 2012, all rights reserved.
	 * </p>
	 *
	 * @author 刘椿成
	 * @since 1.0
	 * @version 1.0
	 */
	public String edit() {
		// 检查
		if(part==null || part.getPartId()==null || part.getPartId().equals("")){
			this.addActionMessage("您所访问的资源Id不存在，请确认后重试。");
			return this.redirectActionResult("list");
		}

		//要害部门对象
		part = partModuleService.get(part.getPartId());
		putToRequest("organ", getCurrentUser().getOrgan());

		// 获取指定部位下涉密人员列表及计算涉密人员数量
		PageSortModel psm = new PageSortModel(getRequest(), "partList");
		if (organ == null) {
			organ = getCurrentUser().getOrgan(); // 返回当前登录单位
		}else {
			organ = partModuleService.get(getRequest().getParameter("organ.organId").toString(), Organ.class); // 得到指定的单位
		}
		if(departmentId!=null) {//如果部门id不为空的情况
			Department depart = partPersonModuleService.get(departmentId,Department.class);
			part.setDepartment(depart);
		}
		partPersonList = partPersonModuleService.getPersonListPage(psm, part, organ, SECRECY_STATUS_NOW);
		putToRequest("partPersonSize", partPersonList == null ? 0 :partPersonList.size());

		//是否是内嵌页面
		String nestedflag = this.getRequest().getParameter("nestedflag");
		if(nestedflag!=null && !nestedflag.equals("")) {//证明是内嵌页面
			putToRequest("nestedflag", nestedflag);
		}else {
			putToRequest("nestedflag", "0");
		}

		//要害部位附件
		putToRequest("attachments", attachmentService.getAttachmentsByHostId(part.getPartId()));

		return "edit";
	}

	/**
	 * <p>
	 * 编辑操作
	 * </p>
	 * <p>
	 * 刘椿成 2012-12-14 16:46:11
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * <p>
	 * </p>
	 * @author 刘椿成
	 * @since 1.0
	 * @version 1.0
	 */
	public String editing() {

	        Part beforePart = partModuleService.get(part.getPartId());
	        SessionFactory sf =  ContextUtils.getDefaultContext().getBean("sessionFactory");
                sf.getCurrentSession().evict(beforePart);


		part = partModuleService.saveSystemDataByPart(part, getCurrentUser(), deptFlag);
		part.setModifyPerson(getCurrentUser());
		part.setModifyTime(new Date());
		part.setOrgan(getCurrentUser().getOrgan());
		//修改人：宋亚非   修改原因：数据库统一至一个库后，取消上报功能，不再存在上报后修改状态   修改时间：2013-06-03
		/*if(part.getReportState() > 0) {
			part.setReportState(ReportState.REPORT_YES_MODIFY);
		}*/

		partModuleService.update(part);
		attachmentService.updateHostId(part.getPartId(), keyPartAttachs);

                BusinessLog log = new BusinessLog();
                log.setBusinessName("要害部位");
                log.setPrimaryKey(part.getPartId());
                BusinessLogContext.getInstance().getBusinessLogService().saveEditBusinessLogByModule(getCurrentUser(), log, part, beforePart);


		// 设置信息
		this.addActionMessage(getMessageConstant().getUpdateSuccess());

		String nestedflag = this.getRequest().getParameter("nestedflag");//查看是否是内嵌页面
		needReload = true;
		if(nestedflag!=null && nestedflag.equals("1")) {//内嵌的页面
			putToRequest("nestedflag", nestedflag);
			return this.redirectActionResult("success");
		}else { //不是内嵌的页面
			return this.redirectActionResult(LIST);
		}
	}

	/**
	 * <p>
	 * 删除操作
	 * </p>
	 * <p>
	 * 刘椿成 2012-12-15 10:43:03
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * <p>
	 * copyright 刘椿成 2012, all rights reserved.
	 * </p>
	 * @author 刘椿成
	 * @since 1.0
	 * @version 1.0
	 */
	public String delete() {
		// 检查
		if(partIds==null || partIds.equals("") || partIds.equals(",")){
			this.addActionMessage("您所访问的资源Id不存在，请确认后重试。");
			return this.redirectActionResult("list");
		}

		for( String id : partIds.split(",") ){
		      Part p =   partModuleService.get(id);
		      if( p!=null ){
	                        BusinessLog log = new BusinessLog();
	                        log.setBusinessName("要害部位");
	                        log.setPrimaryKey(id);
	                        BusinessLogContext.getInstance().getBusinessLogService().saveDelBusinessLogByModule(getCurrentUser(), log, p);
		      }
		}

		partModuleService.deleteBatchIds(partIds);
		// 设置信息
		this.addActionMessage(getMessageConstant().getDeleteSuccess());
		return this.redirectActionResult(LIST);
	}

	/**
	 * <p>
	 * 详情
	 * </p>
	 * <p>
	 * 刘椿成 2012-12-15 10:43:03
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * <p>
	 * copyright 刘椿成 2012, all rights reserved.
	 * </p>
	 * @author 刘椿成
	 * @since 1.0
	 * @version 1.0
	 */
	public String detail() {
		// 检查
		if(part==null || part.getPartId()==null || part.getPartId().equals("")){
			this.addActionMessage("您所访问的资源Id不存在，请确认后重试。");
			return this.redirectActionResult("list");
		}

		//部门
		part = partModuleService.get(part.getPartId());
		partPersonList = partPersonModuleService.getPersonListPage(null, part, null, SECRECY_STATUS_NOW);
		putToRequest("part", part);
		putToRequest("attachments", attachmentService.getAttachmentsByHostId(part.getPartId()));
		putToRequest("organ", getCurrentUser().getOrgan());
		putToRequest("partPersonSize", partPersonList == null ? 0 :partPersonList.size());


		//变更记录
		if(keyPartChange==null) {
			keyPartChange = new KeyPartChange();
		}
		keyPartChange.setKeyPartId(part);
		List<KeyPartChange> keyPart_changelist = keyPartChangeService.queryKeyPartChangeList(null, keyPartChange,this.getCurrentUser().getOrgan(),null,0);
		this.putToRequest("keyPart_changelist", keyPart_changelist);

		return "detail";
	}

	/**
	 * <p>
	 * 到部位涉密人员列表页面
	 * </p>
	 * <p>
	 * 刘椿成 2012-12-14 10:43:03
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
	 * @author liucc
	 * @since 1.0
	 * @version 1.0
	 */
	public String secPersonList() {
		// 通过部位ID得到要害部位信息
		part = partModuleService.get(part.getPartId());
		// 获取指定部位下涉密人员列表
		PageSortModel psm = new PageSortModel(getRequest(), "partList");
		if (organ == null) {
			organ = getCurrentUser().getOrgan(); // 返回当前登录单位
		}else {
			organ = partModuleService.get(getRequest().getParameter("organ.organId").toString(), Organ.class); // 得到指定的单位
		}
		putToRequest("partPersonList", partPersonModuleService.getPersonListPage(psm, part, organ, SECRECY_STATUS_NOW));
		return this.redirectActionResult(LIST);
	}

	/**
	 * <p>
	 * 到新增部位涉密人员页面
	 * </p>
	 * <p>
	 * 刘椿成 2012-12-14 10:43:03
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
	 * @author liucc
	 * @since 1.0
	 * @version 1.0
	 */
	public String addPerson() {
		putToRequest("organ", getCurrentUser().getOrgan());
		part = partModuleService.get(part.getPartId());
		putToRequest("part", partModuleService.get(part.getPartId()));
		putToRequest("department", part.getDepartment());
		return "addPerson";
	}

	/**
	 * <p>
	 * 到新增部位涉密人员操作
	 * </p>
	 * <p>
	 * liucc 2012-12-14 10:43:03
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
	 * @author liucc
	 * @since 1.0
	 * @version 1.0
	 */
	public String addPersoning() {
		// 传入的字段
		PartPerson partPersonTemp = new PartPerson();
		part = partModuleService.get(part.getPartId());
		partPersonTemp.setPart(part);

		//设置涉密人员(根据涉密人员ID是否存在判断保存方式：存在直接保存涉密人员ID否则新增该人员后再保存)
		if (secrecyPerson != null && LangUtils.isNotEmpty(secrecyPerson.getSecrecyPersonId())) {
			partPersonTemp.setId(UUID.randomUUID().toString());
			secrecyPerson.setDepartment(part.getDepartment());
			SecrecyPerson sp = partPersonModuleService.get(
					secrecyPerson.getSecrecyPersonId(), SecrecyPerson.class);

			//修改人：宋亚非   修改原因：数据库统一至一个库后，取消上报功能，填写信息后状态即为已上报   修改时间：2013-06-03
			//sp.setReportState(ReportState.REPORT_NO);
			sp.setReportState(ReportState.REPORT_YES);
			secrecyPersonModuleService.update(sp);
			partPersonTemp.setSecrecyPerson(sp);
		}else if (secrecyPerson != null && LangUtils.isNotEmpty(
			secrecyPerson.getUserInfo().getName())||LangUtils.isEmpty(secrecyPerson.getSecrecyPersonId())) {
			partPersonTemp.setId(UUID.randomUUID().toString());
			UserInfo userInfo = secrecyPersonModuleService.saveUserInfo(secrecyPerson.getUserInfo(), getCurrentUser(),part.getDepartment());
			secrecyPerson.setUserInfo(userInfo);
			secrecyPerson = partPersonModuleService.saveSecrecyPerson(secrecyPerson.getUserInfo().getName(), secrecyPerson, getCurrentUser());
			// 设置部门
			secrecyPerson.setDepartment(part.getDepartment());
			SecrecyPerson sp = partPersonModuleService.get(
					secrecyPerson.getSecrecyPersonId(), SecrecyPerson.class);
			//修改人：宋亚非   修改原因：数据库统一至一个库后，取消上报功能，填写信息后状态即为已上报   修改时间：2013-06-03
			//sp.setReportState(ReportState.REPORT_NO);
			sp.setReportState(ReportState.REPORT_YES);
			partPersonTemp.setSecrecyPerson(sp);
		}
		partPersonModuleService.save(partPersonTemp);
		// 设置信息
		this.addActionMessage(getMessageConstant().getSaveSuccess());
		needReload = true;
		return this.redirectActionResult(LIST);
	}

	/**
	 * <p>
	 * 到编辑部位涉密人员页面
	 * </p>
	 * <p>
	 * 刘椿成 2012-12-14 10:43:03
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
	 * @author 刘椿成
	 * @since 1.0
	 * @version 1.0
	 */
	public String editPerson() {
		partPerson = partPersonModuleService.get(partPerson.getId());
		putToRequest("secrecyPerson", partPerson.getSecrecyPerson());

		part = partModuleService.get(part.getPartId());
		putToRequest("part", partPerson.getPart());
		putToRequest("organ", getCurrentUser().getOrgan());
		return "editPerson";
	}

	/**
	 * <p>
	 * 到编辑部位涉密人员操作
	 * </p>
	 * <p>
	 * 刘椿成 2012-12-14 10:43:03
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
	 * @author 刘椿成
	 * @since 1.0
	 * @version 1.0
	 */
	public String editPersoning() {
		// 得到数据库原始bean
		PartPerson partPersonFormDb = partPersonModuleService.get(partPerson.getId());
		part = partModuleService.get(part.getPartId());

		// 更新业务信息
		partPersonFormDb.setPart(part);

		//设置涉密人员(根据涉密人员ID是否存在判断保存方式：存在直接保存涉密人员ID否则新增该人员后再保存)
		if (secrecyPerson != null && LangUtils.isNotEmpty(secrecyPerson.getSecrecyPersonId())) {
			// 更新涉密人员基础信息
			secrecyPerson.setOrgan(getCurrentUser().getOrgan());
			secrecyPerson.setModifyPerson(getCurrentUser());
			secrecyPerson.setModifyTime(new Date());

			// 更新用户信息
			userInfo = secrecyPersonModuleService.get(secrecyPerson.getUserInfo().getUserInfoId(), UserInfo.class);
			secrecyPersonModuleService.updateUserInfo(userInfo, secrecyPerson.getUserInfo(), getCurrentUser(),part.getDepartment());

			//修改人：宋亚非   修改原因：数据库统一至一个库后，取消上报功能，不再存在上报后修改状态   修改时间：2013-06-03
			/*if(secrecyPerson.getReportState() > 0) {
				secrecyPerson.setReportState(ReportState.REPORT_YES_MODIFY);
			}*/


			// 更新机关涉密人员实体
			secrecyPersonModuleService.update(secrecyPerson);
			// 设置部门
			secrecyPerson.setDepartment(part.getDepartment());
			partPersonFormDb.setSecrecyPerson(secrecyPerson);
		} else if (secrecyPerson != null && LangUtils.isNotEmpty(
			secrecyPerson.getUserInfo().getName())|| LangUtils.isEmpty(secrecyPerson.getSecrecyPersonId())) {
			partPersonFormDb.setId(UUID.randomUUID().toString());
			secrecyPerson.setDepartment(part.getDepartment());
			secrecyPerson = partPersonModuleService.saveSecrecyPerson(secrecyPerson.getUserInfo().getName(), secrecyPerson, getCurrentUser());
			SecrecyPerson sp = partPersonModuleService.get(
					secrecyPerson.getSecrecyPersonId(), SecrecyPerson.class);
			//修改人：宋亚非   修改原因：数据库统一至一个库后，取消上报功能，不再存在上报后修改状态   修改时间：2013-06-03
			/*if(sp.getReportState() > 0) {
				sp.setReportState(ReportState.REPORT_YES_MODIFY);
			}*/
			secrecyPersonModuleService.update(sp);
			partPersonFormDb.setSecrecyPerson(sp);
		}
		partPersonModuleService.update(partPersonFormDb);
		// 设置信息
		this.addActionMessage(getMessageConstant().getUpdateSuccess());
		needReload = true;
		return this.redirectActionResult(LIST);
	}

	/**
	 * <p>
	 * 删除部位涉密人员操作
	 * </p>
	 * <p>
	 * 刘椿成 2012-12-15 10:43:03
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * <p>
	 * copyright 刘椿成 2012, all rights reserved.
	 * </p>
	 * @author 刘椿成
	 * @since 1.0
	 * @version 1.0
	 */
	public String deletePerson() {
		// 检查
		if(partPersonIds==null || partPersonIds.equals("") || partPersonIds.equals(",")){
			this.addActionMessage("您所访问的资源Id不存在，请确认后重试。");
			return this.redirectActionResult("list");
		}
		// 删除
		partPersonModuleService.deleteBatchIds(partPersonIds);
		part = partModuleService.get(part.getPartId());
		// 设置信息
		this.addActionMessage(getMessageConstant().getDeleteSuccess());

		return this.redirectActionResult(LIST);
	}

	/**
	 * <p>
	 * 部位涉密人员详情
	 * </p>
	 * <p>
	 * 刘椿成 2012-12-15 10:43:03
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * <p>
	 * copyright 刘椿成 2012, all rights reserved.
	 * </p>
	 * @author 刘椿成
	 * @since 1.0
	 * @version 1.0
	 */
	public String detailPerson() {
		// 检查
		if(partPerson==null || partPerson.getSecrecyPerson()==null || partPerson.getSecrecyPerson().getSecrecyPersonId().equals("")){
			this.addActionMessage("您所访问的资源Id不存在，请确认后重试。");
			return this.redirectActionResult("list");
		}
		secrecyPerson = partPersonModuleService.get(partPerson.getSecrecyPerson().getSecrecyPersonId(),SecrecyPerson.class);
		part = partPersonModuleService.get(part.getPartId(), Part.class);
		putToRequest("organ", getCurrentUser().getOrgan());

		return "detailPerson";
	}

	/**(本单位)
	 * 主页要害部位统计
	 *
	 * @return 物理视图
	 */
	public String indexView() {
		Organ organ = getCurrentUser().getOrgan();

		List<Long> keyPartArgs = partPersonModuleService.countSectionData(organ);
		putToRequest("keyPartArgs", keyPartArgs);
		this.putToRequest("organ", organ);

		return SUCCESS;
	}

	/**(本单位)
	 * 首页单位统计详情  数据撰取
	 * @author 刘椿成
	 * *</p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>宋亚非  2013-04-09 13:07  从市平台移植到省平台
	 * </ul>
	 * 2012 8 29 10:45:54 修改注释格式.
	 */
	public String organPartData(){

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
		if(part==null) {
			part = new Part();
		}
		if(secrecy_level!=null && !"".equals(secrecy_level)) {
			if(!secrecy_level.equals("-1")) {
				part.setSecrecyLevel(Integer.parseInt(secrecy_level));
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

		PageSortModel psm = new PageSortModel(getRequest(), "partList");
		List<Part> partList = partModuleService.getListPage(psm, part, organ);
		this.putToRequest("partList", partList);

		return "demand";
	}

	/**(保密局)
	 * 首页统计  保密局查看
	 * @return
	 */
	public String indexView_District() {
		District district = getCurrentUser().getOrgan().getDistrict();

		List<Long> keyPartArgs = partPersonModuleService.countPartData(district);
		putToRequest("keyPartArgs", keyPartArgs);
		putToRequest("district", district);

		return SUCCESS;
	}

	/**
	 * 保密局
	 * 首页  统计要害部门的  数据撰取功能
	 * @return
	 */
	public String organPartData_District(){

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
		if(part==null) {
			part = new Part();
		}
		if(secrecy_level!=null && !"".equals(secrecy_level)) {
			if(!secrecy_level.equals("-1")) {
				part.setSecrecyLevel(Integer.parseInt(secrecy_level));
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

		PageSortModel psm = new PageSortModel(getRequest(), "partList");
		List<Part> partList = partModuleService.getListPage(psm, part, district,1);//查询 包含下级的行政区划
		this.putToRequest("partList", partList);

		return "demand";
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
			if(partIds != null && !"".equals(partIds)) {
				// 查询需要上报的数据 domainList
				List<Part> domainList = new ArrayList<Part>();
				String [] partIdArray =  partIds.split(",");
				for (String sid : partIdArray) {
					Part p = partModuleService.get(sid);
					p.setAttachmentList(attachmentService.getAttachmentsByHostId(p.getPartId()));
					p.setReportState(ReportState.REPORT_YES);
					partModuleService.update(p);
					domainList.add(p);

					//要害部位下的涉密人员状态更改
					List<SecrecyPerson> secrecyPersonList = secrecyPersonModuleService.getSecrecyPersonByDepartment(
							p.getDepartment().getDepartmentId()
							, p.getOrgan());
					for (SecrecyPerson secrecyPerson : secrecyPersonList) {
						secrecyPerson.setReportState(ReportState.REPORT_YES);
						secrecyPersonModuleService.update(secrecyPerson);
					}

				}
				// 查询上报单位
				List<String> organIdList = serverReportMappingService.getReportToOragans(getCurrentUser().getOrgan());
				Iterator<String> i = organIdList.iterator();
				while (i.hasNext()) {
					String organId = i.next();
					//组织需要上报内容
					KeyPartDto keyPartDto = new KeyPartDto();
					List<String> querySqls = new ArrayList<String>();
					for (String partId : partIdArray) {
						Part keyPart = partModuleService.get(partId);
						String departmentId = keyPart.getDepartment().getDepartmentId();
						String oId = keyPart.getOrgan().getOrganId();
						querySqls.add("select * from bm_key_part where KEY_PART_ID = '" + partId + "'");
						//因为接收单位并没有同步，所以需要上报。
						querySqls.add("select * from sys_organization where organ_id = '" + oId + "'");
						//单位下所有子部门，因为接收单位并没有同步，所以需要上报。
						querySqls.add("select * from sys_department where organ_id = '" + oId + "'");
						//单位下所有人员，因为要害部门负责人需要，所以需要上报。
						querySqls.add("select * from sys_user_info where organ_id = '" + oId + "'");
						//要害部位下的涉密人员。
						querySqls.add("select * from bm_secrecy_person pt WHERE pt.organ_id = '"
								+ oId + "'" + "and pt.department_id = '" + departmentId + "'");
						//要害部位的人员中间表。
						querySqls.add("select * from bm_key_part_person where KEY_PART_ID='" + partId + "'");
						//部位附件需要，所以需要上报。
						querySqls.add("select * from base_attachment where host_id = '" + partId + "'");
						XmlExportor exportor = new XmlExportor();
						exportor.setDataSource(dataSource);
						StringWriter writer = new StringWriter();
						exportor.exportData(writer , querySqls.toArray(new String[]{}));
						keyPartDto.setReportData(writer.toString());
						keyPartDto.setReceiveOrganId(organId);
						keyPartDto.setPartId(partId);
						// 发送数据
						keyPartExchanger.exchange(keyPartDto, getCurrentUser().getOrgan()
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
	 * 要害部位  密级变更
	 * 梁文杰  2013-07-15
	 * @return
	 */
	public String change() {

		String id = part.getPartId();//得到要害部位的id
		Part obj = partModuleService.get(id);

		//构建密级变更对象
		KeyPartChange keyPartChange = new KeyPartChange();
		keyPartChange.setKeyPartId(obj);//要害部位对象
		keyPartChange.setBeforeLevel(obj.getSecrecyLevel());//原密级

		putToRequest("keyPartChange", keyPartChange);
		return SUCCESS;
	}

	/**
	 * 要害部位  密级变更  保存
	 * 梁文杰  2013-07-15
	 * @return
	 */
	public String changeing() {

		keyPartChange.setCreatePerson(getCurrentUser());
		keyPartChange.setCreateDate(new Date());

		try{
			KeyPartChange obj = keyPartChangeService.save(keyPartChange);//保存密级变更信息

			Part part_fk = partModuleService.get(obj.getKeyPartId().getPartId());//得到要害部位
			part_fk.setSecrecyLevel(obj.getAfterLevel());//设置变更后的密级
			partModuleService.saveOrUpdate(part_fk);//更新要害部位对象
		}catch(Exception e) {
			e.printStackTrace();
			addActionMessage("要害部位密级变更失败！");
			return SUCCESS;
		}

		addActionMessage("要害部位密级变更成功！");
		putToRequest("reflag", 1);//成功的标志
		needReload = true;
		return SUCCESS;
	}

	/**
	 * 要害部位  密级解除
	 * 梁文杰  2013-07-15
	 * @return
	 */
	public String secrecyClear() {

		String id = part.getPartId();//得到要害部位的id
		Part obj = partModuleService.get(id);

		//构建解密对象
		KeyPartSecrecyClear keyPartSecrecyClear = new KeyPartSecrecyClear();
		keyPartSecrecyClear.setKeyPartId(obj);//要害部位对象

		putToRequest("keyPartSecrecyClear", keyPartSecrecyClear);
		return SUCCESS;
	}

	/**
	 * 要害部位  密级解除  保存
	 * 梁文杰  2013-07-15
	 * @return
	 */
	public String secrecyClearing() {

		keyPartSecrecyClear.setCreatePerson(getCurrentUser());
		keyPartSecrecyClear.setCreateDate(new Date());

		try{
			KeyPartSecrecyClear obj = keyPartSecrecyClearService.save(keyPartSecrecyClear);//保存密级解除信息

			Part part_fk = partModuleService.get(obj.getKeyPartId().getPartId());//得到要害部位
			//设置  解密后的密级
			//设置  要害部位表的解密状态的标志
			part_fk.setSecrecyStatus(1);

			partModuleService.saveOrUpdate(part_fk);//解除密级以后    更新要害部位对象

		}catch(Exception e) {
			e.printStackTrace();
			addActionMessage("要害部位密级解除失败！");
			return SUCCESS;
		}

		addActionMessage("要害部位密级解除成功！");
		putToRequest("reflag", 1);//成功的标志
		needReload = true;
		return SUCCESS;
	}


	/**
	 * 保密要害部位密级变更详情
	 * @return
	 */
	public String changeDetail() {
		//变更
		String id = keyPartChange.getKeyPartSecrecyChangeId();
		keyPartChange = keyPartChangeService.get(id);
		this.putToRequest("keyPartChange", keyPartChange);

		//部位
		part = keyPartChange.getKeyPartId();
		partPersonList = partPersonModuleService.getPersonListPage(null, part, null, SECRECY_STATUS_NOW);
		this.putToRequest("part", part);
		putToRequest("attachments", attachmentService.getAttachmentsByHostId(part.getPartId()));
		putToRequest("organ", getCurrentUser().getOrgan());
		putToRequest("partPersonSize", partPersonList == null ? 0 :partPersonList.size());

		return SUCCESS;
	}

	/**
	 * 保密要害部位的  密级解除详情
	 * @return
	 */
	public String clearDetail() {
		//密级解除记录
		String id = keyPartSecrecyClear.getKeyPartSecrecyClearId();
		keyPartSecrecyClear = keyPartSecrecyClearService.get(id);
		this.putToRequest("keyPartSecrecyClear", keyPartSecrecyClear);

		//部门记录
		part = keyPartSecrecyClear.getKeyPartId();
		partPersonList = partPersonModuleService.getPersonListPage(null, part, null, SECRECY_STATUS_NOW);
		putToRequest("part", part);
		putToRequest("attachments", attachmentService.getAttachmentsByHostId(part.getPartId()));
		putToRequest("organ", getCurrentUser().getOrgan());
		putToRequest("partPersonSize", partPersonList == null ? 0 :partPersonList.size());

		//变更记录
		if(keyPartChange==null) {
			keyPartChange = new KeyPartChange();
		}
		keyPartChange.setKeyPartId(part);
		List<KeyPartChange> keyPart_changelist = new ArrayList<KeyPartChange>();
		//业务标志 1查询模块  0普通业务模块
		String ywFlag = this.getRequest().getParameter("ywFlag");
		Organ organ = this.getCurrentUser().getOrgan();
		if(ywFlag!=null && ywFlag.equals("1")){
			organ = part.getOrgan();
		}
		keyPart_changelist = keyPartChangeService.queryKeyPartChangeList(null, keyPartChange,organ,null,0);


		this.putToRequest("keyPart_changelist", keyPart_changelist);
		return SUCCESS;
	}


	/**(本单位)
	 * 统计本单位的保密要害部位信息
	 * @return
	 */
	public String org_CountKeyPartBySecrecyLevel() {

		String organId = "";

		HttpServletRequest r = this.getRequest();
		if(r.getParameter("organId")==null || r.getParameter("organId").equals("")){
			organId = this.getCurrentUser().getOrgan().getOrganId();//得到本单位的 组织id
		}else {
			organId = r.getParameter("organId");//得到传入的参数   组织id
		}

		//查询本单位的要害部门的统计数据
		List<Map<String, Object>> countPartList = partService.count_KeyPart_By_Secrecy_Level(organId);

		this.putToRequest("countPartList", countPartList);
		return SUCCESS;
	}

	/**(行政区划)
	 * 保密局  统计要害部位  行政区划
	 * @return
	 */
	public String org_CountKeyPartBySecrecyLevel_District() {
		//处理行政区划
		String districtCode = this.getRequest().getParameter("districtCode");//行政区划编码
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = districtService.get(districtCode);
		}

		//查询保密局 要害部位  统计数据     行政区划
		List<Map<String, Object>> countPartList = partService.count_KeyPart_By_Secrecy_Level_District(district);
		this.putToRequest("countPartList", countPartList);
		this.putToRequest("districtCode", district.getDistrictCode());

		return SUCCESS;
	}

	/**(  直辖单位)
	 * 保密局  统计要害部位  直辖单位
	 * @return
	 */
	public String org_CountKeyPartBySecrecyLevel_Layer() {

		//处理行政区划
		String districtCode = this.getRequest().getParameter("districtCode");//行政区划编码
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = districtService.get(districtCode);
		}

		//查询保密局 要害部位  统计数据     直辖单位
		List<Map<String, Object>> countPartList = partService.count_KeyPart_By_Secrecy_Level_Layer(district);
		this.putToRequest("countPartList", countPartList);
		this.putToRequest("districtCode", district.getDistrictCode());

		return SUCCESS;
	}

	/**(单位明细)
	 * 保密局  统计要害部位 各个单位明细
	 * @return
	 */
	public String org_CountKeyPartBySecrecyLevel_OrganDetail() {

		//处理行政区划
		String districtCode = this.getRequest().getParameter("districtCode");//行政区划编码
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = districtService.get(districtCode);
		}

		//查询保密局 要害部位  统计数据     各个单位明细
		List<Map<String, Object>> countPartList = partService.count_KeyPart_By_Secrecy_Level_OrganDetail(district);
		this.putToRequest("countPartList", countPartList);
		this.putToRequest("districtCode", district.getDistrictCode());

		return SUCCESS;
	}


	/**(本单位)
	 * 统计导出
	 * @return
	 */
	public String exportExcel_PartCount(){

		String organId = "";

		HttpServletRequest r = this.getRequest();
		if(r.getParameter("organId")==null || r.getParameter("organId").equals("")){
			organId = this.getCurrentUser().getOrgan().getOrganId();//得到本单位的 组织id
		}else {
			organId = r.getParameter("organId");//得到传入的参数   组织id
		}

		//查询本单位的要害部门的统计数据
		List<Map<String, Object>> countPartList = partService.count_KeyPart_By_Secrecy_Level(organId);
		Map<String, Object> params = new HashMap<String, Object>();
		//被导出的数据
		params.put("countPartList", countPartList);

		//数据类型转换
		params.put("Integer", Integer.class);
		setResultData(params);

		return SUCCESS;
	}


	/**(行政区划)
	 * 统计导出
	 * @return
	 */
	public String exportExcel_PartCount_District(){

		//处理行政区划
		String districtCode = this.getRequest().getParameter("districtCode");//行政区划编码
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = districtService.get(districtCode);
		}

		//查询保密局 要害部位  统计数据     行政区划
		List<Map<String, Object>> countPartList = partService.count_KeyPart_By_Secrecy_Level_District(district);
		Map<String, Object> params = new HashMap<String, Object>();
		//被导出的数据
		params.put("countPartList", countPartList);

		//数据类型转换
		params.put("Integer", Integer.class);
		setResultData(params);

		return SUCCESS;
	}

	/**(直辖单位)
	 * 统计导出
	 * @return
	 */
	public String exportExcel_PartCount_Layer(){

		//处理行政区划
		String districtCode = this.getRequest().getParameter("districtCode");//行政区划编码
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = districtService.get(districtCode);
		}

		//查询保密局 要害部位  统计数据     直辖单位
		List<Map<String, Object>> countPartList = partService.count_KeyPart_By_Secrecy_Level_Layer(district);
		Map<String, Object> params = new HashMap<String, Object>();
		//被导出的数据
		params.put("countPartList", countPartList);

		//数据类型转换
		params.put("Integer", Integer.class);
		setResultData(params);
		return SUCCESS;
	}

	/**(单位明细)
	 * 统计导出
	 * @return
	 */
	public String exportExcel_PartCount_OrganDetail(){

		//处理行政区划
		String districtCode = this.getRequest().getParameter("districtCode");//行政区划编码
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = districtService.get(districtCode);
		}

		//查询保密局 要害部位  统计数据     各个单位明细
		List<Map<String, Object>> countPartList = partService.count_KeyPart_By_Secrecy_Level_OrganDetail(district);
		Map<String, Object> params = new HashMap<String, Object>();
		//被导出的数据
		params.put("countPartList", countPartList);

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
			partList = partModuleService.getListPage(null, part, district, Integer.parseInt(isChildren));

		}else {
			Organ organ = getCurrentUser().getOrgan();
			partList = partModuleService.getListPage(null, part, organ);
		}

		Map<String, Object> params = new HashMap<String, Object>();
		//被导出的数据
		params.put("countPartList", partList);
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
	 * ajax获取保密要害部位  是否有关联
	 * @return
	 */
	public String ajax_keyPart() {
		//查看要害部位是否 和其他的表有关联    true有  false没有
		int flag = partService.getKeyPartRelationship(this.getRequest().getParameter("partId"));
		String message = "";
		if(flag==1){
			message = "要害部位密级变更";
		}else if(flag == 2) {
			message = "要害部位密级解除";
		}

		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("flag", String.valueOf(flag));
		resultMap.put("message", message);

		this.setResultData(resultMap);
		return JSON;
	}


	public String ajax_departmentOfkeyPart(){
		Part tmpKeyPart = this.partModuleService.get(part.getPartId());

		Map<String, String> resultMap = new HashMap<String, String>();
		if( tmpKeyPart!=null && tmpKeyPart.getDepartment()!=null ){
			resultMap.put("departmentName", tmpKeyPart.getDepartment().getDepartmentName());
			resultMap.put("departmentId", tmpKeyPart.getDepartment().getDepartmentId());
		}
		this.setResultData(resultMap);
		return JSON;
	}



	/**
	 * 保密要害部位查询  左树页面
	 * @return
	 */
	public String query_main(){
		return SUCCESS;
	}



	/***************************************综合统计*******************************************/
	/**
	 * 综合统计  通过行政区划    查询要害部位个数 的明细列表
	 * 包括当前行政区划 和下级行政区划 的要害部位的明细
	 * @return
	 */
	public String zhtj_query_Detail(){

		district = districtService.get(district.getDistrictCode());

		//处理行政区划
		List<District> districtList = new ArrayList<District>();
		List<District> childrenDistrictList =  new ArrayList<District>(district.getChildren());
		districtList.add(district);

		//查询  当前行政区划的 明细
		List<ZongHeTongJiStatDto> partStatDtoList = partService.count_District(districtList, false);
		//查询  子行政区划的明细
		List<ZongHeTongJiStatDto> childrenStatDtoList = partService.count_District(childrenDistrictList, true);

		putToRequest("partStatDtoList", partStatDtoList);
		putToRequest("childrenStatDtoList", childrenStatDtoList);
		putToRequest("districtCode", district.getDistrictCode());
		putToRequest("topDistrict", this.getCurrentUser().getOrgan().getDistrict());

		return SUCCESS;
	}

	/**
	 * 综合统计  通过行政区划编码  统计该行政区划要害部位的统计
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

		List<ZongHeTongJiStatDto> partStatDtoList = partService.count_District(district, true,organ);
		putToRequest("partStatDtoList", partStatDtoList);
		putToRequest("district", district);
		putToRequest("topDistrict", this.getCurrentUser().getOrgan().getDistrict());

		return SUCCESS;
	}

	/**
	 * 通过密级，行政区划对象或者单位对象，查询要害部位对应的列表
	 * 密级是必须字段，如果点到合计上面了，这里的单位是空的，所以需要行政区划对象
	 *                如果没有点到合计，那么会使用单位的对象去查询
	 * @return
	 */
	public String zhtj_DetailList() {

		List<Part> partList = new ArrayList<Part>();
		PageSortModel psm = new PageSortModel(this.getRequest(), "partList");

		//设置密级
		String secrecy_level = getRequest().getParameter("secrecyLevel");//密级
		if(part==null) {
			part = new Part();
		}
		if(secrecy_level!=null && !"".equals(secrecy_level)) {
			part.setSecrecyLevel(Integer.parseInt(secrecy_level));
		}

		String organId =  getRequest().getParameter("organId"); //单位id
		if(organId!=null && !"".equals(organId)) {//如果有单位，就用单位对象
			Organ organ = new Organ();
			organ.setOrganId(organId);
			partList =  partModuleService.getListPage(psm, part, organ);
		}else {//如果没有单位对象   就需要使用 行政区划对象   也说明点到合计了
			district = districtService.get(district.getDistrictCode());
			partList =  partService.getListPage(psm, part, district);
		}

		putToRequest("partList", partList);
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
		if (part == null) {
			part = new Part();
		}
		if(secrecy_level==null || secrecy_level.equals("")) {
			secrecy_level="0";
		}
		part.setSecrecyLevel(Integer.parseInt(secrecy_level));

		String organId =  getRequest().getParameter("organId"); //单位id
		Organ organ = new Organ();
		if(organId!=null) {
			organ = organService.get(organId);
		}

		//查询
		List<Part> partList = new ArrayList<Part>();
		PageSortModel psm = new PageSortModel(this.getRequest(), "partList");
		partList = partModuleService.getListPage(psm, part, organ);//查询 保密要害部位

		putToRequest("partList", partList);
		putToRequest("secrecy_level", secrecy_level);
		putToRequest("organ", organ);
		putToRequest("dataGetFlag", "1");//综合统计撰取数据的标志
		putToRequest("ywFlag", "0");
		return SUCCESS;
	}

	/**
	 * 综合统计  通过行政区划    查询要害部位个数 的明细列表
	 * 包括当前行政区划 和下级行政区划 的要害部位的明细
	 * @return
	 */
	public String zhtj_query(){

		district = this.getCurrentUser().getOrgan().getDistrict();
		district = districtService.get(district.getDistrictCode());

		//处理行政区划
		List<District> districtList = new ArrayList<District>();
		List<District> childrenDistrictList =  new ArrayList<District>(district.getChildren());
		districtList.add(district);

		//查询  当前行政区划的 明细
		List<ZongHeTongJiStatDto> partStatDtoList = partService.count_District(districtList, false);
		//查询  子行政区划的明细
		List<ZongHeTongJiStatDto> childrenStatDtoList = partService.count_District(childrenDistrictList, true);

		putToRequest("partStatDtoList", partStatDtoList);
		putToRequest("childrenStatDtoList", childrenStatDtoList);
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

		//查询  当前行政区划的 明细
		List<ZongHeTongJiStatDto> partStatDtoList = partService.count_District(districtList, false);

		//导出设置
  		Map<String, Object> params = new HashMap<String, Object>();
  		//被导出的数据
  		params.put("partStatDtoList", partStatDtoList);
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

		//处理行政区划
		List<District> childrenDistrictList =  new ArrayList<District>(district.getChildren());

		//查询  子行政区划的明细
		List<ZongHeTongJiStatDto> childrenStatDtoList = partService.count_District(childrenDistrictList, true);

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
		partList = partModuleService.getListPage(null, part, partModuleService.get(organId, Organ.class));
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
		resultData.put("id", "3");
		partList = partModuleService.getListPage(null, part, getCurrentUser().getOrgan());
                String msg = dataValidateService.validateData("要害部位", partList, "3");
		resultData.put("msg", msg);
		setResultData(resultData);
		return JSON;
	}
	/************************************************************************************************/




	// ******************** Setter & Getter ********************
	/**
	 * 返回part
	 * @return part
	 */
	public Part getPart() {
		return part;
	}

	/**
	 * 设置part
	 * @param part part
	 */
	public void setPart(Part part) {
		this.part = part;
	}

	/**
	 * 返回secrecyLevel
	 * @return secrecyLevel
	 */
	public Integer getSecrecyLevel() {
		return secrecyLevel;
	}

	/**
	 * 设置secrecyLevel
	 * @param secrecyLevel secrecyLevel
	 */
	public void setSecrecyLevel(Integer secrecyLevel) {
		this.secrecyLevel = secrecyLevel;
	}

	/**
	 * 返回partList
	 * @return partList
	 */
	public List<Part> getPartList() {
		return partList;
	}

	/**
	 * 设置partList
	 * @param partList partList
	 */
	public void setPartList(List<Part> partList) {
		this.partList = partList;
	}

	/**
	 * 返回partModuleService
	 * @return partModuleService
	 */
	public PartModuleService getPartModuleService() {
		return partModuleService;
	}

	/**
	 * 设置partModuleService
	 * @param partModuleService partModuleService
	 */
	public void setPartModuleService(PartModuleService partModuleService) {
		this.partModuleService = partModuleService;
	}

	/**
	 * 返回partPersonIds
	 * @return partPersonIds
	 */
	public String getPartPersonIds() {
		return partPersonIds;
	}

	/**
	 * 设置partPersonIds
	 * @param partPersonIds partPersonIds
	 */
	public void setPartPersonIds(String partPersonIds) {
		this.partPersonIds = partPersonIds;
	}

	/**
	 * 返回partIds
	 * @return partIds
	 */
	public String getPartIds() {
		return partIds;
	}

	/**
	 * 设置partIds
	 * @param partIds partIds
	 */
	public void setPartIds(String partIds) {
		this.partIds = partIds;
	}

	/**
	 * 返回organ
	 * @return organ
	 */
	public Organ getOrgan() {
		return organ;
	}

	/**
	 * 设置organ
	 * @param organ organ
	 */
	public void setOrgan(Organ organ) {
		this.organ = organ;
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
	 * 返回listIds
	 * @return listIds
	 */
	public String getListIds() {
		return listIds;
	}

	/**
	 * 设置listIds
	 * @param listIds listIds
	 */
	public void setListIds(String listIds) {
		this.listIds = listIds;
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
	 * 返回attachmentService
	 * @return attachmentService
	 */
	public AttachmentService getAttachmentService() {
		return attachmentService;
	}
	/**
	 * 设置attachmentService
	 * @param attachmentService attachmentService
	 */
	public void setAttachmentService(AttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}

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
	 * 返回partPerson
	 * @return partPerson
	 */
	public PartPerson getPartPerson() {
		return partPerson;
	}
	/**
	 * 设置partPerson
	 * @param partPerson partPerson
	 */
	public void setPartPerson(PartPerson partPerson) {
		this.partPerson = partPerson;
	}
	/**
	 * 返回partPersonModuleService
	 * @return partPersonModuleService
	 */
	public PartPersonModuleService getPartPersonModuleService() {
		return partPersonModuleService;
	}
	/**
	 * 设置partPersonModuleService
	 * @param partPersonModuleService partPersonModuleService
	 */
	public void setPartPersonModuleService(
			PartPersonModuleService partPersonModuleService) {
		this.partPersonModuleService = partPersonModuleService;
	}
	/**
	 * 返回keyPartAttachs
	 * @return keyPartAttachs
	 */
	public List<String> getKeyPartAttachs() {
		return keyPartAttachs;
	}
	/**
	 * 设置keyPartAttachs
	 * @param keyPartAttachs keyPartAttachs
	 */
	public void setKeyPartAttachs(List<String> keyPartAttachs) {
		this.keyPartAttachs = keyPartAttachs;
	}
	/**
	 * 返回partPersonList
	 * @return partPersonList
	 */
	public List<PartPerson> getPartPersonList() {
		return partPersonList;
	}
	/**
	 * 设置partPersonList
	 * @param partPersonList partPersonList
	 */
	public void setPartPersonList(List<PartPerson> partPersonList) {
		this.partPersonList = partPersonList;
	}
	/**
	 * @return the departmentId
	 */
	public String getDepartmentId() {
		return departmentId;
	}
	/**
	 * @param departmentId the departmentId to set
	 */
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	/**
	 * 返回deptFlag
	 * @return deptFlag
	 */
	public String getDeptFlag() {
		return deptFlag;
	}
	/**
	 * 设置deptFlag
	 * @param deptFlag deptFlag
	 */
	public void setDeptFlag(String deptFlag) {
		this.deptFlag = deptFlag;
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
	 * @param keyPartExchanger 设置keyPartExchanger
	 */
	public void setKeyPartExchanger(KeyPartExchanger keyPartExchanger) {
		this.keyPartExchanger = keyPartExchanger;
	}

	/**
	 * @param serverReportMappingService 设置serverReportMappingService
	 */
	public void setServerReportMappingService(
			ServerReportMappingService serverReportMappingService) {
		this.serverReportMappingService = serverReportMappingService;
	}

	/**
	 * @param dataSource 设置dataSource
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public KeyPartChangeService getKeyPartChangeService() {
		return keyPartChangeService;
	}

	public void setKeyPartChangeService(KeyPartChangeService keyPartChangeService) {
		this.keyPartChangeService = keyPartChangeService;
	}

	public KeyPartSecrecyClearService getKeyPartSecrecyClearService() {
		return keyPartSecrecyClearService;
	}

	public void setKeyPartSecrecyClearService(
			KeyPartSecrecyClearService keyPartSecrecyClearService) {
		this.keyPartSecrecyClearService = keyPartSecrecyClearService;
	}

	public KeyPartChange getKeyPartChange() {
		return keyPartChange;
	}

	public void setKeyPartChange(KeyPartChange keyPartChange) {
		this.keyPartChange = keyPartChange;
	}

	public KeyPartSecrecyClear getKeyPartSecrecyClear() {
		return keyPartSecrecyClear;
	}

	public void setKeyPartSecrecyClear(KeyPartSecrecyClear keyPartSecrecyClear) {
		this.keyPartSecrecyClear = keyPartSecrecyClear;
	}

	public PartService getPartService() {
		return partService;
	}

	public void setPartService(PartService partService) {
		this.partService = partService;
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
