package com.cdthgk.devicemgr.action;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.cdthgk.devicemgr.service.DeviceMgrService;
import com.cdthgk.devicemgr.service.SecrecyEquipmentCategoryService;
import com.cdthgk.devicemgr.vo.EquipmentLoan;
import com.cdthgk.devicemgr.vo.EquipmentTrash;
import com.cdthgk.devicemgr.vo.SecrecyEquipment;
import com.cdthgk.devicemgr.vo.SecrecyEquipmentCategory;
import com.cdthgk.platform.base.action.PlatformAction;
import com.cdthgk.platform.organization.core.OrganizationContext;
import com.cdthgk.platform.organization.organ.configuration.OrganConfiguration;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;
import com.cdthgk.platform.organization.userinfo.service.UserInfoService;
import com.cdthgk.platform.permission.user.domain.User;
import com.cdthgk.platform.permission.user.service.UserService;

import ec.common.PageSortModel;
/**
 * @description 保密技术设备基本操作
 * @author 熊  超 2009-10-12 9:30
 * @modify 彭  维 2010-08-19 9:00 整理注释
 */
@SuppressWarnings("all")
public class DeviceMgrAction extends PlatformAction {
	private SecrecyEquipment secrecyEquipment;
	private List<SecrecyEquipment> secrecyEquipmentList;
	private EquipmentTrash equipmentTrash;
	private DeviceMgrService deviceMgrService;
	private EquipmentLoan equipmentLoan;
	private String secrecyEquipmentIds;
	private SecrecyEquipmentCategory secrecyEquipmentCategory;
	private List<SecrecyEquipmentCategory> secrecyEquipmentCategoryList;
	private String secrecyEquipmentCategoryId;
	private String secrecyEquipmentCategoryIds;
	private SecrecyEquipmentCategoryService secrecyEquipmentCategoryService;
	private boolean needReload=false;
	private String personId;
	private UserInfoService userInfoService;

	public String main(){
		return "main";
	}
	public String list(){

		Organ organ = getCurrentUser().getUserInfo().getOrgan();
		// 得到当前类型
		if(secrecyEquipmentCategory==null){
			secrecyEquipmentCategory = new SecrecyEquipmentCategory();
			secrecyEquipmentCategory.setSecrecyEquipmentCategoryId("1");
		}else if(secrecyEquipmentCategory.getSecrecyEquipmentCategoryId()==null || secrecyEquipmentCategory.getSecrecyEquipmentCategoryId().equals("")){
			secrecyEquipmentCategory.setSecrecyEquipmentCategoryId("1");
		}
		secrecyEquipmentCategory = secrecyEquipmentCategoryService.read(secrecyEquipmentCategory.getSecrecyEquipmentCategoryId());

		// 得到当前类型的下的所有设备信息
		PageSortModel psm = new PageSortModel(getRequest(), "secrecyEquipmentList");
		secrecyEquipmentList = deviceMgrService.getSecrecyEquipmentList(psm, secrecyEquipmentCategory, secrecyEquipment, organ);

		return "list";

	}

	public boolean isNeedReload() {
                return needReload;
        }

        public void setNeedReload(boolean needReload) {
                this.needReload = needReload;
        }

	public String add(){
		// 得到指定类型
		if(secrecyEquipmentCategory==null){
			secrecyEquipmentCategory = new SecrecyEquipmentCategory();
			secrecyEquipmentCategory.setSecrecyEquipmentCategoryId("1");
		}else if(secrecyEquipmentCategory.getSecrecyEquipmentCategoryId()==null || secrecyEquipmentCategory.getSecrecyEquipmentCategoryId().equals("")){
			secrecyEquipmentCategory.setSecrecyEquipmentCategoryId("1");
		}
		secrecyEquipmentCategory = secrecyEquipmentCategoryService.read(secrecyEquipmentCategory.getSecrecyEquipmentCategoryId());

		// 得到类型下的设备信息
		PageSortModel psm = new PageSortModel(getRequest(), "secrecyEquipmentList");
		String hql = "from SecrecyEquipment se where se.secrecyEquipmentCategory.secrecyEquipmentCategoryId='" + secrecyEquipmentCategory.getSecrecyEquipmentCategoryId() + "'";
		secrecyEquipmentList = (List<SecrecyEquipment>) deviceMgrService.listById(hql, psm);
		putToRequest("secrecyEquipmentList", secrecyEquipmentList);
		putToRequest("secrecyEquipmentCategory", secrecyEquipmentCategory);
		return "add";

	}

	public String edit(){
		// 检查设备信息ID是否正常传入
		if(secrecyEquipment == null || secrecyEquipment.getSecrecyEquipmentId()==null || secrecyEquipment.getSecrecyEquipmentId().equals("")){
			this.addActionMessage("未找到所属的设备信息ID，请确认后重试。");
			// 设备信息ID丢失，默认跳转到根类型去
			secrecyEquipmentCategory = secrecyEquipmentCategoryService.read("1");
			return this.redirectActionResult(LIST);
		}

		// 设备信息是否存在数据库中
		secrecyEquipment = deviceMgrService.read(secrecyEquipment.getSecrecyEquipmentId());
		if(secrecyEquipment==null){
			this.addActionMessage("未找到所属的设备信息，请确认后重试。");

			// 设备信息不在数据库，默认跳转到根类型去
			secrecyEquipmentCategory = secrecyEquipmentCategoryService.read("1");
			return this.redirectActionResult(LIST);
		}

		// 得到编辑的设备信息的类型
		secrecyEquipmentCategory = secrecyEquipment.getSecrecyEquipmentCategory();

		// 获取设备信息
		secrecyEquipment = deviceMgrService.get(secrecyEquipment.getSecrecyEquipmentId());
		return "edit";

	}

	public String delete(){
		// 检查
        if(secrecyEquipmentIds==null || secrecyEquipmentIds.equals("") || secrecyEquipmentIds.equals(",")){
            return this.redirectActionResult("list");
        }
        secrecyEquipmentCategory = secrecyEquipmentCategoryService.read(secrecyEquipmentCategory.getSecrecyEquipmentCategoryId());
        // 删除
        String[] ids = secrecyEquipmentIds.split(",");
        for (String id : ids) {
        	//通过设备状态判断是否可以删除
        	deviceMgrService.delete(id);
        }

        // 设置信息
        this.addActionMessage("删除设备信息成功。");
        return this.redirectActionResult("list");
	}

	public String save(){
		// 检查类型ID是否正常传入
		if(secrecyEquipment.getSecrecyEquipmentCategory() == null || secrecyEquipment.getSecrecyEquipmentCategory().getSecrecyEquipmentCategoryId()==null || secrecyEquipment.getSecrecyEquipmentCategory().getSecrecyEquipmentCategoryId().equals("")){
			this.addActionMessage("未找到所属的类型ID，请确认后重试。");

			// 类型ID丢失，默认跳转到根类型去
			secrecyEquipmentCategory = secrecyEquipmentCategoryService.read("1");
			return this.redirectActionResult(LIST);
		}
		// 检查类型是否存在数据库中
		secrecyEquipmentCategory = secrecyEquipmentCategoryService.read(secrecyEquipment.getSecrecyEquipmentCategory().getSecrecyEquipmentCategoryId());
		if(secrecyEquipmentCategory==null){
			this.addActionMessage("未找到所属的类型，请确认后重试。");
			// 类型不在数据库，默认跳转到根类型去
			secrecyEquipmentCategory = secrecyEquipmentCategoryService.read("1");
			return this.redirectActionResult(LIST);
		}

		secrecyEquipment.setStatus(1);
		secrecyEquipment.setCreatePerson(getCurrentUser());
        secrecyEquipment.setCreateTime(new Date());
        secrecyEquipment.setDepartment(getCurrentUser().getUserInfo().getDepartment());
        secrecyEquipment.setOrgan(getCurrentUser().getUserInfo().getOrgan());
		deviceMgrService.save(secrecyEquipment);
		this.addActionMessage("新增【"+ secrecyEquipment.getName() +"】成功。");
		return redirectActionResult("list");
	}

	public String detail(){
		// 检查设备信息ID是否正常传入
		if(secrecyEquipment == null || secrecyEquipment.getSecrecyEquipmentId() == null || secrecyEquipment.getSecrecyEquipmentId().equals("")){
			this.addActionMessage("未找到相关的设备信息ID，请确认后重试。");
			// 相关设备信息ID丢失，默认跳转到根类型去
			secrecyEquipmentCategory = secrecyEquipmentCategoryService.read("1");
			return this.redirectActionResult(LIST);
		}

		// 检查设备信息是否存在数据库中
		secrecyEquipment = deviceMgrService.read(secrecyEquipment.getSecrecyEquipmentId());
		if(secrecyEquipment == null){
			this.addActionMessage("未找到相关的设备信息，请确认后重试。");
			// 相关的设备信息不在数据库，默认跳转到根类型去
			secrecyEquipmentCategory = secrecyEquipmentCategoryService.read("1");
			return this.redirectActionResult(LIST);
		}

		// 相关设备信息的类型
		secrecyEquipmentCategory = secrecyEquipment.getSecrecyEquipmentCategory();
		secrecyEquipment = deviceMgrService.get(secrecyEquipment.getSecrecyEquipmentId());
        return "detail";

	}

	public String update(){
		// 检查设备信息ID是否正常传入
		if(secrecyEquipment == null || secrecyEquipment.getSecrecyEquipmentId() == null || secrecyEquipment.getSecrecyEquipmentId().equals("")){
			this.addActionMessage("未找到需要编辑的设备信息ID，请确认后重试。");

			// 需要编辑的设备信息ID丢失，默认跳转到根类型去
			secrecyEquipmentCategory = secrecyEquipmentCategoryService.read("1");
			return this.redirectActionResult(LIST);
		}

		// 检查设备信息是否存在数据库中
		if(secrecyEquipment == null){
			this.addActionMessage("未找到需要编辑的设备信息，请确认后重试。");
			// 需要编辑的设备信息不在数据库，默认跳转到根类型去
			secrecyEquipmentCategory = secrecyEquipmentCategoryService.read("1");
			return this.redirectActionResult(LIST);
		}
		// 更新来自于页面的数据
		SecrecyEquipment secrecyEquipmentDb=deviceMgrService.get(secrecyEquipment.getSecrecyEquipmentId());
		secrecyEquipmentDb.setStatus(1);
//                UserInfo userInfo=userInfoService.get(personId);
//                User user =userInfoService.get(personId).getUser();
                secrecyEquipmentDb.setDutyPerson(secrecyEquipment.getDutyPerson());
                secrecyEquipmentDb.setAtPlace(secrecyEquipment.getAtPlace());
                secrecyEquipmentDb.setBuyTime(secrecyEquipment.getBuyTime());
                secrecyEquipmentDb.setName(secrecyEquipment.getName());
                secrecyEquipmentDb.setNumber(secrecyEquipment.getNumber());
                secrecyEquipmentDb.setTypeCode(secrecyEquipment.getTypeCode());
                secrecyEquipmentDb.setUnitPrice(secrecyEquipment.getUnitPrice());
                secrecyEquipmentDb.setPrice(secrecyEquipment.getPrice());
		deviceMgrService.update(secrecyEquipmentDb);
		// 得到编辑的设备信息的类型（用于编辑后返回到该类型的编辑页面）
		secrecyEquipmentCategory = secrecyEquipmentCategoryService.read(secrecyEquipment.getSecrecyEquipmentCategory().getSecrecyEquipmentCategoryId());
		this.addActionMessage("编辑【"+ secrecyEquipment.getName() +"】成功。");

		return redirectActionResult("list");

	}

	public String top(){
		PageSortModel psm = new PageSortModel(getRequest(),"list");
		putToRequest("list",deviceMgrService.listForEc(psm, secrecyEquipment,getCurrentUser()));
		return "top";
	}

	/**
	 * @description 申请过期列表
	 * @author 熊  超 2009-10-12 9:30
	 * @param null
	 */
	public String trashList(){
		PageSortModel psm = new PageSortModel(getRequest(),"list");
		putToRequest("list",deviceMgrService.listForEc(psm, equipmentTrash));
		return "trashList";
	}

	/**
	 * @description 借用过期列表
	 * @author 熊  超 2009-10-12 9:30
	 * @param null
	 */
	public String trashPassList(){
		PageSortModel psm = new PageSortModel(getRequest(),"list");
		putToRequest("list",deviceMgrService.listForEcPass(psm, equipmentLoan));
		return "trashPassList";
	}

	public String addTrash(){
		return "addTrash";
	}

	public String saveTrash(){
		User user=getCurrentUser();
		equipmentTrash.setApplyPerson(user.getUserInfo());
		equipmentTrash.setCreatePerson(user.getUserInfo());
		equipmentTrash.setCreateTime(new Date());
		equipmentTrash.setDepartmentId(user.getUserInfo().getDepartment());
		equipmentTrash.setOrganId(user.getOrgan());
		equipmentTrash.setModifyPerson(user.getUserInfo());
		equipmentTrash.setModifyTime(new Date());
		equipmentTrash.setStatus(1);
		deviceMgrService.saveTrash(equipmentTrash);
		/*SecrecyEquipment se=deviceMgrService.get(equipmentTrash.getSecrecyEquipment().getSecrecyEquipmentId(), SecrecyEquipment.class);
		se.setStatus(100);
		deviceMgrService.update(se);*/
		putToRequest("Flag", "succ");
		addActionMessage("新增报废设备成功。");
		return "addTrash";
		//return redirectActionResult("trashList");
	}

	public String editTrash(){
		equipmentTrash=deviceMgrService.get(equipmentTrash.getEquipmentTrashId(), EquipmentTrash.class);
		return "editTrash";
	}

	public String updateTrash(){
		User user=getCurrentUser();
		equipmentTrash.setApplyPerson(user.getUserInfo());
		equipmentTrash.setCreatePerson(user.getUserInfo());
		equipmentTrash.setCreateTime(new Date());
		equipmentTrash.setDepartmentId(user.getUserInfo().getDepartment());
		equipmentTrash.setOrganId(user.getOrgan());
		equipmentTrash.setModifyPerson(user.getUserInfo());
		equipmentTrash.setModifyTime(new Date());
		deviceMgrService.updateTrash(equipmentTrash);
		addActionMessage("编辑报废设备成功。");
		return redirectActionResult("trashList");
	}

	public String deleteTrash(){
		deviceMgrService.deleteBatchTrash(this.getIds());
		addActionMessage("删除报废设备成功。");
		return redirectActionResult("trashList");
	}

	public EquipmentTrash getEquipmentTrash() {
		return equipmentTrash;
	}

	public void setEquipmentTrash(EquipmentTrash equipmentTrash) {
		this.equipmentTrash = equipmentTrash;
	}

	/**
	 * @description 借出列表
	 * @author 熊  超 2009-10-12 9:30
	 * @param null
	 */
	public String loanList(){
		PageSortModel psm = new PageSortModel(getRequest(),"list");
		putToRequest("list",deviceMgrService.listForEcAuditPass(psm, equipmentLoan));
		return "loanList";
	}

	public String addLoan(){
		return "addLoan";
	}

	/**
	 * @description 状态：1表示本处室处长审批
	 * @author 熊  超 2009-10-12 9:30
	 * @param null
	 */
	public String saveLoan(){
		User user=getCurrentUser();
		Date date=new Date();
		equipmentLoan.setCreatePerson(user);
		equipmentLoan.setModifyPerson(user);
		equipmentLoan.setCreateTime(date);
		equipmentLoan.setDepartment(user.getUserInfo().getDepartment());
		equipmentLoan.setOrgan(user.getOrgan());
		equipmentLoan.setModifyTime(date);
		equipmentLoan.setLoanPerson(user.getUserInfo());
		equipmentLoan.setStatus(1);
		deviceMgrService.saveLoan(equipmentLoan);
		putToRequest("Flag", "succ");
		addActionMessage("新增设备借用信息成功。");
		return "saveLoan";
	}

	public String editLoan(){
		equipmentLoan=deviceMgrService.get(equipmentLoan.getEquipmentLoanId(), EquipmentLoan.class);
		return "editLoan";
	}

	public String updateLoan(){
		User user=getCurrentUser();
		equipmentLoan.setModifyPerson(user);
		equipmentLoan.setModifyTime(new Date());
		equipmentLoan.setOrgan(user.getOrgan());
		deviceMgrService.updateLoan(equipmentLoan);
		addActionMessage("编辑设备借用信息成功。");
		return redirectActionResult("loanList");
	}
	public String loan(){
		SecrecyEquipment se=deviceMgrService.get(equipmentLoan.getSecrecyEquipment().getSecrecyEquipmentId(), SecrecyEquipment.class);
		se.setStatus(9);
		deviceMgrService.update(se);
		addActionMessage("借出成功。");
		return redirectActionResult("loanList");
	}

	/**
	 * @description 归还借用设备
	 * @author 熊  超 2009-10-12 9:30
	 * @param null
	 */
	public String restituteLoan(){
		User user=getCurrentUser();
		equipmentLoan=deviceMgrService.get(equipmentLoan.getEquipmentLoanId(), EquipmentLoan.class);
		equipmentLoan.setModifyPerson(user);
		equipmentLoan.setModifyTime(new Date());
		equipmentLoan.setOrgan(user.getOrgan());
		equipmentLoan.setStatus(1);
		SecrecyEquipment se=equipmentLoan.getSecrecyEquipment();
		se.setStatus(0);
		deviceMgrService.updateLoan(equipmentLoan);
		addActionMessage("归还借用设备成功。");
		return redirectActionResult("loanList");
	}

	public String deleteLoan(){
		deviceMgrService.deleteBatchLoan(this.getIds());
		addActionMessage("删除借用设备信息成功。");
		return redirectActionResult("loanList");
	}

	public EquipmentLoan getEquipmentLoan() {
		return equipmentLoan;
	}

	public void setEquipmentLoan(EquipmentLoan equipmentLoan) {
		this.equipmentLoan = equipmentLoan;
	}

	public String loanOut(){
		EquipmentLoan el=deviceMgrService.get(equipmentLoan.getEquipmentLoanId(), EquipmentLoan.class);
		el.getSecrecyEquipment().setStatus(2);
		el.setStatus(9);
		deviceMgrService.updateLoan(el);
		addActionMessage("借出成功");
		return redirectActionResult("loanList");
	}

	public String loanIn(){
		EquipmentLoan el=deviceMgrService.get(equipmentLoan.getEquipmentLoanId(), EquipmentLoan.class);
		el.getSecrecyEquipment().setStatus(1);
		el.setStatus(75);
		el.getSecrecyEquipment().setStatus(1);
		deviceMgrService.updateLoan(el);
		addActionMessage("归还成功");
		return redirectActionResult("loanList");
	}

	public String auditLoan(){
		PageSortModel psm = new PageSortModel(getRequest(),"list");
		putToRequest("list",deviceMgrService.listForEcAudit(psm, equipmentLoan));
		return "auditLoan";
	}

	public String auditLoan1(){
		PageSortModel psm = new PageSortModel(getRequest(),"list");
		putToRequest("list",deviceMgrService.listForEcAudit1(psm, equipmentLoan));
		return "auditLoan1";
	}

	public String saveAuditLoan(){
		EquipmentLoan et=deviceMgrService.get(equipmentLoan.getEquipmentLoanId(), EquipmentLoan.class);
		et.setStatus(equipmentLoan.getStatus());
		deviceMgrService.updateLoan(et);
		addActionMessage("保密技术设备借用申请审批成功。");
		return redirectActionResult("auditLoan");
	}

	public UserInfoService getUserInfoService() {
                return userInfoService;
        }

        public void setUserInfoService(UserInfoService userInfoService) {
                this.userInfoService = userInfoService;
        }

        public String addAuditLoan(){
		equipmentLoan=deviceMgrService.get(equipmentLoan.getEquipmentLoanId(), EquipmentLoan.class);
		return "addAuditLoan";
	}

	public String getPersonId() {
                return personId;
        }

        public void setPersonId(String personId) {
                this.personId = personId;
        }

        public String auditTrash(){
		PageSortModel psm = new PageSortModel(getRequest(),"list");
		putToRequest("list",deviceMgrService.listForEcAudit(psm, equipmentTrash));
		return "auditTrash";
	}

	public String addAuditTrash(){
		equipmentTrash=deviceMgrService.get(equipmentTrash.getEquipmentTrashId(), EquipmentTrash.class);
		return "addAuditTrash";
	}

	public String saveAuditTrash(){
		EquipmentTrash et=deviceMgrService.get(equipmentTrash.getEquipmentTrashId(), EquipmentTrash.class);
		et.setStatus(equipmentTrash.getStatus());
		deviceMgrService.updateTrash(et);
		addActionMessage("保密技术设备报废申请审批成功。");
		return redirectActionResult("auditTrash");
	}

	/**
	 * @description 应该放在同一个事务中
	 * @author 熊  超 2009-10-12 9:30
	 * @param null
	 */
	public String saveAuditLoan1(){
		EquipmentLoan et=deviceMgrService.get(equipmentLoan.getEquipmentLoanId(), EquipmentLoan.class);
		et.setStatus(equipmentLoan.getStatus());
		if(equipmentLoan.getStatus()==11){
			SecrecyEquipment se=et.getSecrecyEquipment();
			se.setStatus(equipmentLoan.getStatus());
		}
		deviceMgrService.updateLoan(et);
		addActionMessage("保密技术设备借用申请审批成功。");
		return redirectActionResult("auditLoan1");
	}

	public String addAuditLoan1(){
		equipmentLoan=deviceMgrService.get(equipmentLoan.getEquipmentLoanId(), EquipmentLoan.class);
		return "addAuditLoan1";
	}

	public String auditTrash1(){
		PageSortModel psm = new PageSortModel(getRequest(),"list");
		putToRequest("list",deviceMgrService.listForEcAudit1(psm, equipmentTrash));
		return "auditTrash1";
	}

	public String addAuditTrash1(){
		equipmentTrash=deviceMgrService.get(equipmentTrash.getEquipmentTrashId(), EquipmentTrash.class);
		return "addAuditTrash1";
	}

	public String saveAuditTrash1(){
		EquipmentTrash et=deviceMgrService.get(equipmentTrash.getEquipmentTrashId(), EquipmentTrash.class);
		et.setStatus(equipmentTrash.getStatus());
		deviceMgrService.updateTrash(et);
		addActionMessage("保密技术设备借用申请审批成功。");
		return redirectActionResult("auditLoan");
	}

	public String trashOut(){
		EquipmentTrash et=deviceMgrService.get(equipmentTrash.getEquipmentTrashId(), EquipmentTrash.class);
		et.setStatus(100);
		et.getSecrecyEquipment().setStatus(100);
		deviceMgrService.updateTrash(et);
		addActionMessage("报废成功");
		return redirectActionResult("trashList");
	}

	public DeviceMgrService getDeviceMgrService() {
		return deviceMgrService;
	}

	public void setDeviceMgrService(DeviceMgrService deviceMgrService) {
		this.deviceMgrService = deviceMgrService;
	}

	public SecrecyEquipment getSecrecyEquipment() {
		return secrecyEquipment;
	}

	public void setSecrecyEquipment(SecrecyEquipment secrecyEquipment) {
		this.secrecyEquipment = secrecyEquipment;
	}
	public String warning() {
		HttpServletRequest request=this.getRequest();
		this.putToRequest("url", request.getParameter("url"));
		return "success";
	}


	/**
	 * 返回secrecyEquipmentList
	 * @return secrecyEquipmentList
	 */
	public List<SecrecyEquipment> getSecrecyEquipmentList() {
		return secrecyEquipmentList;
	}

	/**
	 * 设置secrecyEquipmentList
	 * @param secrecyEquipmentList secrecyEquipmentList
	 */
	public void setSecrecyEquipmentList(List<SecrecyEquipment> secrecyEquipmentList) {
		this.secrecyEquipmentList = secrecyEquipmentList;
	}

	/**
	 * 返回secrecyEquipmentIds
	 * @return secrecyEquipmentIds
	 */
	public String getSecrecyEquipmentIds() {
		return secrecyEquipmentIds;
	}

	/**
	 * 设置secrecyEquipmentIds
	 * @param secrecyEquipmentIds secrecyEquipmentIds
	 */
	public void setSecrecyEquipmentIds(String secrecyEquipmentIds) {
		this.secrecyEquipmentIds = secrecyEquipmentIds;
	}

	/**
	 * 返回secrecyEquipmentCategoryList
	 * @return secrecyEquipmentCategoryList
	 */
	public List<SecrecyEquipmentCategory> getSecrecyEquipmentCategoryList() {
		return secrecyEquipmentCategoryList;
	}

	/**
	 * 设置secrecyEquipmentCategoryList
	 * @param secrecyEquipmentCategoryList secrecyEquipmentCategoryList
	 */
	public void setSecrecyEquipmentCategoryList(
			List<SecrecyEquipmentCategory> secrecyEquipmentCategoryList) {
		this.secrecyEquipmentCategoryList = secrecyEquipmentCategoryList;
	}

	/**
	 * 返回secrecyEquipmentCategoryId
	 * @return secrecyEquipmentCategoryId
	 */
	public String getSecrecyEquipmentCategoryId() {
		return secrecyEquipmentCategoryId;
	}

	/**
	 * 设置secrecyEquipmentCategoryId
	 * @param secrecyEquipmentCategoryId secrecyEquipmentCategoryId
	 */
	public void setSecrecyEquipmentCategoryId(String secrecyEquipmentCategoryId) {
		this.secrecyEquipmentCategoryId = secrecyEquipmentCategoryId;
	}

	/**
	 * 返回secrecyEquipmentCategoryIds
	 * @return secrecyEquipmentCategoryIds
	 */
	public String getSecrecyEquipmentCategoryIds() {
		return secrecyEquipmentCategoryIds;
	}

	/**
	 * 设置secrecyEquipmentCategoryIds
	 * @param secrecyEquipmentCategoryIds secrecyEquipmentCategoryIds
	 */
	public void setSecrecyEquipmentCategoryIds(String secrecyEquipmentCategoryIds) {
		this.secrecyEquipmentCategoryIds = secrecyEquipmentCategoryIds;
	}

	/**
	 * 返回secrecyEquipmentCategoryService
	 * @return secrecyEquipmentCategoryService
	 */
	public SecrecyEquipmentCategoryService getSecrecyEquipmentCategoryService() {
		return secrecyEquipmentCategoryService;
	}

	/**
	 * 设置secrecyEquipmentCategoryService
	 * @param secrecyEquipmentCategoryService secrecyEquipmentCategoryService
	 */
	public void setSecrecyEquipmentCategoryService(
			SecrecyEquipmentCategoryService secrecyEquipmentCategoryService) {
		this.secrecyEquipmentCategoryService = secrecyEquipmentCategoryService;
	}

	/**
	 * 返回secrecyEquipmentCategory
	 * @return secrecyEquipmentCategory
	 */
	public SecrecyEquipmentCategory getSecrecyEquipmentCategory() {
		return secrecyEquipmentCategory;
	}

	/**
	 * 设置secrecyEquipmentCategory
	 * @param secrecyEquipmentCategory secrecyEquipmentCategory
	 */
	public void setSecrecyEquipmentCategory(
			SecrecyEquipmentCategory secrecyEquipmentCategory) {
		this.secrecyEquipmentCategory = secrecyEquipmentCategory;
	}

}