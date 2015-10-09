package com.cdthgk.bmp.secrecyproducts.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.action.BmpAction;
import com.cdthgk.bmp.log.context.BusinessLogContext;
import com.cdthgk.bmp.log.vo.BusinessLog;
import com.cdthgk.bmp.secrecycountryitem.qo.ZongHeTongJiStatDto;
import com.cdthgk.bmp.secrecycountryitem.vo.SecrecyCountryItem;
import com.cdthgk.bmp.secrecyproducts.service.SecrecyProductsChangeService;
import com.cdthgk.bmp.secrecyproducts.service.SecrecyProductsClearService;
import com.cdthgk.bmp.secrecyproducts.service.SecrecyProductsService;
import com.cdthgk.bmp.secrecyproducts.vo.SecrecyProducts;
import com.cdthgk.bmp.secrecyproducts.vo.SecrecyProductsChange;
import com.cdthgk.bmp.secrecyproducts.vo.SecrecyProductsClear;
import com.cdthgk.bmp.statinfo.dto.QueryDto;
import com.cdthgk.common.bean.BeanUtils;
import com.cdthgk.common.bean.rule.CopyRuleEnum;
import com.cdthgk.common.lang.CollectionUtils;
import com.cdthgk.common.lang.StringUtils;
import com.cdthgk.platform.dataValidate.service.DataValidateService;
import com.cdthgk.platform.dictionary.context.DictionaryContext;
import com.cdthgk.platform.dictionary.spi.DictionaryService;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.district.service.DistrictService;
import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.organ.service.OrganService;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

public class SecrecyProductsAction extends BmpAction{

	private static final long serialVersionUID = -571127430489328683L;

	/***Spring注入***/
	private SecrecyProductsService secrecyProductsService;
	private SecrecyProductsChangeService secrecyProductsChangeService;
	private SecrecyProductsClearService secrecyProductsClearService;
	private DistrictService districtService;//行政区划
	private OrganService organService;
	private DataValidateService dataValidateService;




	/***struts2**/
	private SecrecyProductsClear secrecyProductsClear;
	private SecrecyProductsChange secrecyProductsChange;
	private SecrecyProducts secrecyProducts;
	private District district;




	/**
	 * 进入密品的   主列表
	 * @return
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
		District district = null;
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

		return SUCCESS;
	}

	/**
	 * 进入密品的   列表页面
	 * @return
	 */
	public String list_list() {

		List<SecrecyProducts> secrecyProductsList = new ArrayList<SecrecyProducts>();
		PageSortModel psm = new PageSortModel(this.getRequest(), "secrecyProductsList");

		//标志的传递  业务标志  1表示查询   0表示普通业务模块
		String ywFlag = this.getRequest().getParameter("ywFlag");
		if(ywFlag==null) {
			ywFlag="0";
		}
		this.putToRequest("ywFlag", ywFlag);//标志的传递  业务标志  1表示查询   0表示普通业务模块

		if(ywFlag.equals("1")) {//查询页面 按照行政区划   来查
			//行政区划编码
			String districtCode = this.getRequest().getParameter("districtCode");//行政区划编码
			District district = null;
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

			secrecyProductsList = secrecyProductsService.querySecrecyProductsList(psm, district, isChildren, secrecyProducts);
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

			secrecyProductsList = secrecyProductsService.querySecrecyProductsList(psm, organ, secrecyProducts);
		}

		this.putToRequest("secrecyProductsList", secrecyProductsList);
		this.putToRequest("secrecyProducts", secrecyProducts);
		return SUCCESS;
	}

	/**
	 * 密品 密级变更的列表
	 * @return
	 */
	public String change_list(){

		List<SecrecyProductsChange> secrecyProductsChangeList = new ArrayList<SecrecyProductsChange>();
		PageSortModel psm = new PageSortModel(this.getRequest(), "secrecyProductsChangeList");

		//标志的传递  业务标志  1表示查询   0表示普通业务模块
		String ywFlag = this.getRequest().getParameter("ywFlag");
		if(ywFlag==null) {
			ywFlag="0";
		}
		this.putToRequest("ywFlag", ywFlag);//标志的传递  业务标志  1表示查询   0表示普通业务模块

		if(ywFlag.equals("1")) {//查询页面 按照行政区划   来查
			//行政区划编码
			String districtCode = this.getRequest().getParameter("districtCode");//行政区划编码
			District district = null;
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

			secrecyProductsChangeList = secrecyProductsChangeService.queryChangeList(psm, secrecyProductsChange, null,district,isChildren);
		}else if(ywFlag.equals("0")){ //普通页面按照  单位来查询
			Organ organ = this.getCurrentUser().getOrgan();
			secrecyProductsChangeList = secrecyProductsChangeService.queryChangeList(psm, secrecyProductsChange, organ, null, 0);
		}

		this.putToRequest("secrecyProductsChangeList", secrecyProductsChangeList);
		this.putToRequest("secrecyProductsChange", secrecyProductsChange);
		return SUCCESS;
	}

	/**
	 * 密品  密级解除列表
	 * @return
	 */
	public String clear_list() {

		List<SecrecyProductsClear> secrecyProductsClearList = new ArrayList<SecrecyProductsClear>();
		PageSortModel psm = new PageSortModel(this.getRequest(), "secrecyProductsClearList");

		//标志的传递  业务标志  1表示查询   0表示普通业务模块
		String ywFlag = this.getRequest().getParameter("ywFlag");
		if(ywFlag==null) {
			ywFlag="0";
		}
		this.putToRequest("ywFlag", ywFlag);//标志的传递  业务标志  1表示查询   0表示普通业务模块

		if(ywFlag.equals("1")) {//查询页面 按照行政区划   来查
			//行政区划编码
			String districtCode = this.getRequest().getParameter("districtCode");//行政区划编码
			District district = null;
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

			secrecyProductsClearList = secrecyProductsClearService.queryClearList(psm, null,secrecyProductsClear,district,isChildren);
		}else if(ywFlag.equals("0")){ //普通页面按照  单位来查询
			Organ organ = this.getCurrentUser().getOrgan();
			secrecyProductsClearList = secrecyProductsClearService.queryClearList(psm, organ,secrecyProductsClear,null,0);
		}

		this.putToRequest("secrecyProductsClearList", secrecyProductsClearList);
		this.putToRequest("secrecyProductsClear", secrecyProductsClear);
		return SUCCESS;
	}

	/**
	 * 进入密品的   新增页面
	 * @return
	 */
	public String add(){

		//设置密品初始化信息
		this.putToRequest("needReload", Boolean.FALSE);
		return SUCCESS;
	}

	/**
	 * 密品的  新增保存
	 * @return
	 */
	public String adding(){

		//设置创建人信息
		User u = this.getCurrentUser();
		secrecyProducts.setCreatePerson(u);
		secrecyProducts.setCreateTime(new Date());
		secrecyProducts.setCreateOrgan(u.getOrgan());

		//处理定密负责人
		String userid = secrecyProducts.getFormulatesecrecyPerson().getUserInfoId();
		if(userid==null || "".equals(userid)) {//系统中不存在该定密负责人
			 UserInfo secrecyPerson = secrecyProductsService.saveSecrecyPerson(secrecyProducts.getFormulatesecrecyPerson(), u);
			 secrecyProducts.setFormulatesecrecyPerson(secrecyPerson);
		}

		//处理部门
		String departmentId = secrecyProducts.getDepartId().getDepartmentId();
		if(departmentId==null || "".equals(departmentId)) {//是在系统中不存在  的部门
			String departmentName = this.getRequest().getParameter("departmentName");//获取部门的名称
			Department department = secrecyProductsService.saveDepartmentByName(departmentName, u);//保存部门
			secrecyProducts.setDepartId(department);
		}

		secrecyProducts = secrecyProductsService.save(secrecyProducts);//保存
		if(secrecyProducts!=null) {
			this.putToRequest("needReload", Boolean.TRUE);
		}else {
			this.putToRequest("needReload", Boolean.FALSE);
		}
		this.putToRequest("secrecyProducts", secrecyProducts);
		BusinessLog log=new BusinessLog();
		log.setBusinessName("密品");
		log.setPrimaryKey(secrecyProducts.getSecrecyproductsId());
		BusinessLogContext.getInstance().getBusinessLogService().saveAddBusinessLogByModule(getCurrentUser(), log, secrecyProducts);
		return SUCCESS;
	}

	/**
	 * 进入密品的   编辑页面
	 * @return
	 */
	public String edit(){

		String pkId = secrecyProducts.getSecrecyproductsId();//获取id
		secrecyProducts = secrecyProductsService.get(pkId);//通过id查询出对象

		this.putToRequest("secrecyProducts", secrecyProducts);
		this.putToRequest("needReload", Boolean.FALSE);
		return SUCCESS;
	}

	/**
	 * 密品的   编辑保存
	 * @return
	 */
	public String editing(){

		//设置修改人
		SecrecyProducts oldSp=secrecyProductsService.get(secrecyProducts.getSecrecyproductsId());
		User u = this.getCurrentUser();
		secrecyProducts.setModifyPerson(u);
		secrecyProducts.setModifyOrgan(u.getOrgan());
		secrecyProducts.setModifyTime(new Date());

		//处理定密负责人
		String userid = secrecyProducts.getFormulatesecrecyPerson().getUserInfoId();
		if(userid==null || "".equals(userid)) {//系统中不存在该定密负责人
			 UserInfo secrecyPerson = secrecyProductsService.saveSecrecyPerson(secrecyProducts.getFormulatesecrecyPerson(), u);
			 secrecyProducts.setFormulatesecrecyPerson(secrecyPerson);
		}

		//处理部门
		String departmentId = secrecyProducts.getDepartId().getDepartmentId();
		if(departmentId==null || "".equals(departmentId)) {//是在系统中不存在  的部门
			String departmentName = this.getRequest().getParameter("departmentName");//获取部门的名称
			Department department = secrecyProductsService.saveDepartmentByName(departmentName, u);//保存部门
			secrecyProducts.setDepartId(department);
		}

		secrecyProducts = secrecyProductsService.saveOrUpdate(secrecyProducts);
		if(secrecyProducts!=null) {
			this.putToRequest("needReload", Boolean.TRUE);
		}else {
			this.putToRequest("needReload", Boolean.FALSE);
		}
		this.putToRequest("secrecyProducts", secrecyProducts);
		BusinessLog log = new BusinessLog();
		log.setBusinessName("密品");
		log.setPrimaryKey(secrecyProducts.getSecrecyproductsId());
		BusinessLogContext.getInstance().getBusinessLogService().saveEditBusinessLogByModule(getCurrentUser(), log, secrecyProducts, oldSp);
		return SUCCESS;
	}

	/**
	 * 密品的  删除
	 * @return
	 */
	public String del(){

		String secrecyProductsIds = this.getRequest().getParameter("secrecyProductsIds");
		if(secrecyProductsIds!=null && !"".equals(secrecyProductsIds)) {
			secrecyProductsService.deleteBatchIds(secrecyProductsIds);
		}

		this.addActionMessage(getMessageConstant().getDeleteSuccess());
		for( String id : secrecyProductsIds.split(",") ){
                BusinessLog log = new BusinessLog();
                log.setBusinessName("密品");
                log.setPrimaryKey(id);
                BusinessLogContext.getInstance().getBusinessLogService().saveDelBusinessLogByModule(getCurrentUser(), log, new SecrecyProducts());
		}
		return LIST;
	}

	/**
	 * 进入  密级变更
	 * @return
	 */
	public String change() {

		String pkId = secrecyProducts.getSecrecyproductsId();//获取id
		secrecyProducts = secrecyProductsService.get(pkId);//通过id查询出对象

		//构建密级变更对象
		SecrecyProductsChange obj = new SecrecyProductsChange();
		obj.setSecrecyProducts(secrecyProducts);
		obj.setBeforeLevel(secrecyProducts.getSecrecyLevel());
		obj.setChangeDate(new Date());

		this.putToRequest("secrecyProductsChange", obj);
		return SUCCESS;
	}

	/**
	 * 密级变更
	 * @return
	 */
	public String changeing() {
		//设置创建信息
		secrecyProductsChange.setCreatePerson(getCurrentUser());
		secrecyProductsChange.setCreateDate(new Date());
		SecrecyProducts beforeSp=null;
		SecrecyProducts fk=null;
		try{
			//保存 密级变更对象
			SecrecyProductsChange obj = secrecyProductsChangeService.save(secrecyProductsChange);

			//变更密品的密级
			fk = secrecyProductsService.get(obj.getSecrecyProducts().getSecrecyproductsId());
			beforeSp=new SecrecyProducts();
			BeanUtils.copyProperties(beforeSp, fk, CopyRuleEnum.ignoreCaseNull);
			fk.setSecrecyLevel(obj.getAfterLevel());
			secrecyProductsService.saveOrUpdate(fk);

		}catch(Exception e) {
			e.printStackTrace();
			addActionMessage("密品密级变更失败！");
			this.putToRequest("needReload", Boolean.FALSE);
		}

		addActionMessage("密品密级变更成功！");
		this.putToRequest("needReload", Boolean.TRUE);
		BusinessLog log = new BusinessLog();
		log.setBusinessName("密品");
		log.setPrimaryKey(fk.getSecrecyproductsId());
		BusinessLogContext.getInstance().getBusinessLogService().saveEditBusinessLogByModule(getCurrentUser(), log, fk, beforeSp);
		return SUCCESS;
	}

	/**
	 * 进入  密级解除
	 * @return
	 */
	public String clear() {
		String pkId = secrecyProducts.getSecrecyproductsId();//获取id
		secrecyProducts = secrecyProductsService.get(pkId);//通过id查询出对象

		//构建
		SecrecyProductsClear obj = new SecrecyProductsClear();
		obj.setSecrecyProducts(secrecyProducts);
		obj.setClearTime(new Date());

		this.putToRequest("secrecyProductsClear", obj);
		return SUCCESS;
	}

	/**
	 * 密级解除
	 * @return
	 */
	public String clearing() {

		//设置创建信息
		secrecyProductsClear.setCreatePerson(getCurrentUser());
		secrecyProductsClear.setCreateDate(new Date());
		SecrecyProducts beforeSp=null;
		SecrecyProducts fk=null;
		try{
			//保存解密
			SecrecyProductsClear obj = secrecyProductsClearService.save(secrecyProductsClear);

			//密品解密
			fk = secrecyProductsService.get(obj.getSecrecyProducts().getSecrecyproductsId());
			beforeSp=new SecrecyProducts();
			BeanUtils.copyProperties(beforeSp, fk, CopyRuleEnum.ignoreCaseNull);
			fk.setSecrecyStatus(1);
			secrecyProductsService.saveOrUpdate(fk);

		}catch(Exception e) {
			e.printStackTrace();
			addActionMessage("密品密级解除失败！");
			this.putToRequest("needReload", Boolean.FALSE);
		}

		addActionMessage("密品密级解除成功！");
		this.putToRequest("needReload", Boolean.TRUE);
		BusinessLog log = new BusinessLog();
		log.setBusinessName("密品");
		log.setPrimaryKey(fk.getSecrecyproductsId());
		BusinessLogContext.getInstance().getBusinessLogService().saveEditBusinessLogByModule(getCurrentUser(), log, fk, beforeSp);
		return SUCCESS;
	}

	/**
	 * 变更明细
	 * @return
	 */
	public String changeDetial() {
		//获取变更对象
		secrecyProductsChange = secrecyProductsChangeService.get(secrecyProductsChange.getChangeId());
		//密品
		secrecyProducts = secrecyProductsChange.getSecrecyProducts();

		this.putToRequest("secrecyProductsChange", secrecyProductsChange);
		this.putToRequest("secrecyProducts", secrecyProducts);

		return SUCCESS;
	}

	/**
	 * 解密明细
	 * @return
	 */
	public String clearDetial() {
		//获取解除对象
		secrecyProductsClear = secrecyProductsClearService.get(secrecyProductsClear.getClearId());
		//密品
		secrecyProducts = secrecyProductsClear.getSecrecyProducts();
		//查询 密品 变更
		if(secrecyProductsChange==null) {
			secrecyProductsChange = new SecrecyProductsChange();
		}
		secrecyProductsChange.setSecrecyProducts(secrecyProducts);
		List<SecrecyProductsChange> secrecyProductsChangeList = new ArrayList<SecrecyProductsChange>();
		//业务标志 1查询模块  0普通业务模块
		String ywFlag = this.getRequest().getParameter("ywFlag");
		Organ organ = this.getCurrentUser().getOrgan();
		if(ywFlag!=null && ywFlag.equals("1")){
			organ = secrecyProducts.getCreateOrgan();
		}
		secrecyProductsChangeList = secrecyProductsChangeService.queryChangeList(null, secrecyProductsChange,organ,null,0);


		this.putToRequest("secrecyProductsClear", secrecyProductsClear);
		this.putToRequest("secrecyProducts", secrecyProducts);
		this.putToRequest("secrecyProductsChangeList", secrecyProductsChangeList);
		this.putToRequest("secrecyProductsChange", secrecyProductsChange);

		return SUCCESS;
	}

	/**
	 * 密品明细
	 * @return
	 */
	public String detail() {
		Organ organ = this.getCurrentUser().getOrgan();
		District district = organ.getDistrict();
		//获取密品对象
		secrecyProducts = secrecyProductsService.get(secrecyProducts.getSecrecyproductsId());

		//查询 密品 变更
		if(secrecyProductsChange==null) {
			secrecyProductsChange = new SecrecyProductsChange();
		}
		secrecyProductsChange.setSecrecyProducts(secrecyProducts);

		//查询
		List<SecrecyProductsChange> secrecyProductsChangeList = null;
		if(!organ.getOrganId().equals(secrecyProducts.getCreateOrgan().getOrganId())) {
			secrecyProductsChangeList = secrecyProductsChangeService.queryChangeList(null,secrecyProductsChange,null,district,1);
		}else {
			secrecyProductsChangeList = secrecyProductsChangeService.queryChangeList(null,secrecyProductsChange,organ,null,0);
		}

		this.putToRequest("secrecyProductsChangeList", secrecyProductsChangeList);
		this.putToRequest("secrecyProductsChange", secrecyProductsChange);
		this.putToRequest("secrecyProducts", secrecyProducts);

		return SUCCESS;
	}

	/**
	 * 通过ajax查询 密品表  中的数据被哪些表所引用
	 * 传递参数  secrecyProductsId -> 密品id
	 * @return
	 */
	public String ajax_relationship() {

		String ids = this.getRequest().getParameter("secrecyProductsId");
		Integer iValue = 0;
		String message = "";

		if(ids!=null) {
			String[] args =  ids.split(",");
			if(args!=null && args.length>0) {
				for(int i=0;i<args.length;i++) {
					String id = args[i];

					iValue = secrecyProductsService.getRelationshipForTable(id);//获取到引用的信息
					if(iValue==1){
						message = "密级变更";
					}else if(iValue == 2) {
						message = "密级解除";
					}

					if(iValue!=0) {
						break;
					}
				}
			}
		}

		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("flag", String.valueOf(iValue));
		resultMap.put("message", message);

		this.setResultData(resultMap);
		return JSON;
	}

	/**
	 * 导出数据
	 * @return
	 */
	public String export() {

		List<SecrecyProducts> secrecyProductsList = new ArrayList<SecrecyProducts>();

		//业务标志  1： 查询页面     0：普通业务模块
		String ywFlag = this.getRequest().getParameter("ywFlag");
		if(ywFlag.equals("1")) {
			String districtCode = this.getRequest().getParameter("districtCode");//行政区划编码
			String isChildren = this.getRequest().getParameter("isChildren");//是否包含下级
			//处理是否包含下级的标志
			isChildren = isChildren==null || isChildren.equals("")? "0": isChildren;
			//处理行政区划对象
			District district = null;
			if(districtCode==null) {
				district = this.getCurrentUser().getOrgan().getDistrict();
			}else {
				district = districtService.get(districtCode);
			}
			secrecyProductsList = secrecyProductsService.querySecrecyProductsList(null, district, Integer.parseInt(isChildren), secrecyProducts);

		}else {
			Organ organ = this.getCurrentUser().getOrgan();
			secrecyProductsList = secrecyProductsService.querySecrecyProductsList(null, organ, secrecyProducts);//查询 密品
		}
		this.putToRequest("secrecyProductsList", secrecyProductsList);

		Map<String, Object> params = new HashMap<String, Object>();
		//被导出的数据
		params.put("secrecyProductsList", secrecyProductsList);
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


	/**(本单位)
	 * 首页统计密品
	 * @return
	 */
	public String indexView_SecrecyProducts() {
		//本单位
		Organ organ = getCurrentUser().getOrgan();
		//总数查询
		Map<String, Object> cmap = secrecyProductsService.getSecrecyProducts_Total_CurrentOrgan(organ);

		putToRequest("cmap", cmap);
		putToRequest("organ", organ);
		return SUCCESS;
	}

	/**(本单位)
	 * 密品  明细 列表
	 * @return
	 */
	public String indexView_DetailList() {
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
		if(secrecyProducts==null) {
			secrecyProducts = new SecrecyProducts();
		}
		if(secrecy_level!=null && !"".equals(secrecy_level)) {
			if(!secrecy_level.equals("-1")) {
				secrecyProducts.setSecrecyLevel(Integer.parseInt(secrecy_level));
				if(secrecy_level.equals("1")) {
					putToRequest("secrecy_level_name", "秘密");
				}else if(secrecy_level.equals("2")){
					putToRequest("secrecy_level_name", "机密");
				}else if(secrecy_level.equals("3")){
					putToRequest("secrecy_level_name", "绝密");
				}
			}else {
				putToRequest("secrecy_level_name", "全部");
			}
		}

		//查询记录
		PageSortModel psm = new PageSortModel(this.getRequest(), "secrecyProductsList");
		List<SecrecyProducts> secrecyProductsList =  secrecyProductsService.querySecrecyProductsList(psm, organ, secrecyProducts);

		putToRequest("secrecyProductsList", secrecyProductsList);
		putToRequest("secrecy_level", secrecy_level);
		return SUCCESS;
	}


	/**(行政区)
	 * 首页统计 密品
	 * @return
	 */
	public String indexView_SecrecyProducts_District() {
		//行政区
		District district =  getCurrentUser().getOrgan().getDistrict();
		//总数查询
		Map<String, Object> cmap = secrecyProductsService.getSecrecyProducts_Total_District(district, 1);

		putToRequest("cmap", cmap);
		putToRequest("district", district);
		return SUCCESS;
	}

	/**(行政区)
	 * 密品  明细 列表
	 * @return
	 */
	public String indexView_DetailList_District() {
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
		if(secrecyProducts==null) {
			secrecyProducts = new SecrecyProducts();
		}
		if(secrecy_level!=null && !"".equals(secrecy_level)) {
			if(!secrecy_level.equals("-1")) {
				secrecyProducts.setSecrecyLevel(Integer.parseInt(secrecy_level));
				if(secrecy_level.equals("1")) {
					putToRequest("secrecy_level_name", "秘密");
				}else if(secrecy_level.equals("2")){
					putToRequest("secrecy_level_name", "机密");
				}else if(secrecy_level.equals("3")){
					putToRequest("secrecy_level_name", "绝密");
				}
			}else {
				putToRequest("secrecy_level_name", "全部");
			}
		}

		PageSortModel psm = new PageSortModel(this.getRequest(), "secrecyProductsList");
		List<SecrecyProducts> secrecyProductsList =  secrecyProductsService.querySecrecyProductsList(psm, district,1, secrecyProducts);

		putToRequest("secrecyProductsList", secrecyProductsList);
		putToRequest("secrecy_level", secrecy_level);
		return SUCCESS;
	}

	/**
	 * 涉密科研项目查询  左树页面
	 * @return
	 */
	public String query_main(){
		return SUCCESS;
	}

	/***************************************综合统计*******************************************/
	/**
	 * 综合统计  通过行政区划    查询密品个数 的明细列表
	 * 包括当前行政区划 和下级行政区划 的密品的明细
	 * @return
	 */
	public String zhtj_query_Detail(){

		district = districtService.get(district.getDistrictCode());

		//处理行政区划
		List<District> districtList = new ArrayList<District>();
		List<District> childrenDistrictList =  new ArrayList<District>(district.getChildren());
		districtList.add(district);

		//查询  当前行政区划的 明细
		List<ZongHeTongJiStatDto> secrecyProductsStatDtoList = secrecyProductsService.count_SecrecyProducts_District(districtList, false);
		//查询  子行政区划的明细
		List<ZongHeTongJiStatDto> childrenStatDtoList = secrecyProductsService.count_SecrecyProducts_District(childrenDistrictList, true);

		putToRequest("secrecyProductsStatDtoList", secrecyProductsStatDtoList);
		putToRequest("childrenStatDtoList", childrenStatDtoList);
		putToRequest("districtCode", district.getDistrictCode());
		putToRequest("topDistrict", this.getCurrentUser().getOrgan().getDistrict());

		return SUCCESS;
	}

	/**
	 * 综合统计  通过行政区划编码  统计该行政区划密品的统计
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

		List<ZongHeTongJiStatDto> secrecyProductsStatDtoList = secrecyProductsService.count_SecrecyProducts_District(district, true,organ);
		putToRequest("secrecyProductsStatDtoList", secrecyProductsStatDtoList);
		putToRequest("district", district);
		putToRequest("topDistrict", this.getCurrentUser().getOrgan().getDistrict());

		return SUCCESS;
	}

	/**
	 * 通过密级，行政区划对象或者单位对象，查询密品对应的列表
	 * 密级是必须字段，如果点到合计上面了，这里的单位是空的，所以需要行政区划对象
	 *                如果没有点到合计，那么会使用单位的对象去查询
	 * @return
	 */
	public String zhtj_DetailList() {

		List<SecrecyProducts> secrecyProductsList = new ArrayList<SecrecyProducts>();
		PageSortModel psm = new PageSortModel(this.getRequest(), "secrecyProductsList");

		//设置密级
		String secrecy_level = getRequest().getParameter("secrecyLevel");//密级
		if(secrecyProducts==null) {
			secrecyProducts = new SecrecyProducts();
		}
		if(secrecy_level!=null && !"".equals(secrecy_level)) {
			secrecyProducts.setSecrecyLevel(Integer.parseInt(secrecy_level));
		}

		String organId =  getRequest().getParameter("organId"); //单位id
		if(organId!=null && !"".equals(organId)) {//如果有单位，就用单位对象
			Organ organ = new Organ();
			organ.setOrganId(organId);
			secrecyProductsList =  secrecyProductsService.querySecrecyProductsList(psm, organ, secrecyProducts);
		}else {//如果没有单位对象   就需要使用 行政区划对象   也说明:鼠标点到合计了
			district = districtService.get(district.getDistrictCode());
			secrecyProductsList =  secrecyProductsService.querySecrecyProductsList(psm, district, secrecyProducts);
		}

		putToRequest("secrecyProductsList", secrecyProductsList);
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
		if (secrecyProducts == null) {
			secrecyProducts = new SecrecyProducts();
		}
		if(secrecy_level==null || secrecy_level.equals("")) {
			secrecy_level="0";
		}
		secrecyProducts.setSecrecyLevel(Integer.parseInt(secrecy_level));

		String organId =  getRequest().getParameter("organId"); //单位id
		Organ organ = new Organ();
		if(organId!=null) {
			organ = organService.get(organId);
		}

		//查询
		List<SecrecyProducts> secrecyProductsList = new ArrayList<SecrecyProducts>();
		PageSortModel psm = new PageSortModel(this.getRequest(), "secrecyProductsList");
		secrecyProductsList = secrecyProductsService.querySecrecyProductsList(psm, organ, secrecyProducts);//查询 密品

		putToRequest("secrecyProductsList", secrecyProductsList);
		putToRequest("secrecy_level", secrecy_level);
		putToRequest("organ", organ);
		putToRequest("dataGetFlag", "1");
		putToRequest("ywFlag", "0");
		return SUCCESS;
	}

	/**
	 * 综合统计  通过行政区划    查询密品个数 的明细列表
	 * 包括当前行政区划 和下级行政区划 的密品的明细
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
		List<ZongHeTongJiStatDto> secrecyProductsStatDtoList = secrecyProductsService.count_SecrecyProducts_District(districtList, false);
		//查询  子行政区划的明细
		List<ZongHeTongJiStatDto> childrenStatDtoList = secrecyProductsService.count_SecrecyProducts_District(childrenDistrictList, true);

		putToRequest("secrecyProductsStatDtoList", secrecyProductsStatDtoList);
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
		List<ZongHeTongJiStatDto> secrecyProductsStatDtoList = secrecyProductsService.count_SecrecyProducts_District(districtList, false);

		//导出设置
  		Map<String, Object> params = new HashMap<String, Object>();
  		//被导出的数据
  		params.put("secrecyProductsStatDtoList", secrecyProductsStatDtoList);
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
		List<ZongHeTongJiStatDto> childrenStatDtoList = secrecyProductsService.count_SecrecyProducts_District(childrenDistrictList, true);

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
		List<SecrecyProducts> secrecyProductsList = secrecyProductsService.querySecrecyProductsList(null, secrecyProductsService.get(organId, Organ.class), secrecyProducts);
		putToRequest("secrecyProductsList", secrecyProductsList);
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
		resultData.put("id", "6");
		List<SecrecyProducts> secrecyProductsList = secrecyProductsService.querySecrecyProductsList(null, getCurrentUser().getOrgan(), null);
		String msg = dataValidateService.validateData("密品", secrecyProductsList, "6");
		resultData.put("msg", msg);
		setResultData(resultData);
		return JSON;
	}


    /**********************************************************************************************************************************************/
	public SecrecyProductsService getSecrecyProductsService() {
		return secrecyProductsService;
	}
	public void setSecrecyProductsService(
			SecrecyProductsService secrecyProductsService) {
		this.secrecyProductsService = secrecyProductsService;
	}
	public SecrecyProductsChangeService getSecrecyProductsChangeService() {
		return secrecyProductsChangeService;
	}
	public void setSecrecyProductsChangeService(
			SecrecyProductsChangeService secrecyProductsChangeService) {
		this.secrecyProductsChangeService = secrecyProductsChangeService;
	}
	public SecrecyProductsClearService getSecrecyProductsClearService() {
		return secrecyProductsClearService;
	}
	public void setSecrecyProductsClearService(
			SecrecyProductsClearService secrecyProductsClearService) {
		this.secrecyProductsClearService = secrecyProductsClearService;
	}
	public SecrecyProductsClear getSecrecyProductsClear() {
		return secrecyProductsClear;
	}
	public void setSecrecyProductsClear(SecrecyProductsClear secrecyProductsClear) {
		this.secrecyProductsClear = secrecyProductsClear;
	}
	public SecrecyProductsChange getSecrecyProductsChange() {
		return secrecyProductsChange;
	}
	public void setSecrecyProductsChange(SecrecyProductsChange secrecyProductsChange) {
		this.secrecyProductsChange = secrecyProductsChange;
	}
	public SecrecyProducts getSecrecyProducts() {
		return secrecyProducts;
	}
	public void setSecrecyProducts(SecrecyProducts secrecyProducts) {
		this.secrecyProducts = secrecyProducts;
	}
	public DistrictService getDistrictService() {
		return districtService;
	}
	public void setDistrictService(DistrictService districtService) {
		this.districtService = districtService;
	}
	public District getDistrict() {
		return district;
	}
	public void setDistrict(District district) {
		this.district = district;
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
