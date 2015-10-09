/**
 *
 */
package com.cdthgk.bmp.stateSecretManagement.contactSecretPerson.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.action.BmpAction;
import com.cdthgk.bmp.log.context.BusinessLogContext;
import com.cdthgk.bmp.log.vo.BusinessLog;
import com.cdthgk.bmp.secrecyresearchproject.vo.SecrecyResearchProject;
import com.cdthgk.bmp.stateSecretManagement.contactSecretPerson.service.ContactSecretPersonService;
import com.cdthgk.bmp.stateSecretManagement.contactSecretPerson.vo.ContactSecretPerson;
import com.cdthgk.bmp.statinfo.dto.QueryDto;
import com.cdthgk.common.bean.BeanUtils;
import com.cdthgk.common.bean.rule.CopyRuleEnum;
import com.cdthgk.common.lang.CollectionUtils;
import com.cdthgk.platform.dataValidate.service.DataValidateService;
import com.cdthgk.platform.dictionary.context.DictionaryContext;
import com.cdthgk.platform.dictionary.spi.DictionaryService;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;

/**
 * <p>
 * 类的说明放这里
 * </p>
 * <p>
 * 创建人 宋亚非 创建时间 2014-5-9 - 下午4:06:45
 * </p>
 * <blockquote> <h4>历史修改记录</h4>
 * <ul>
 * <li>修改人 修改时间 修改描述
 * </ul>
 * </blockquote>
 * <p>
 * copyright cdthgk 2010-2011, all rights reserved.
 * </p>
 *
 * @author 钟冀
 * @author cdthgk r&d
 * @since 1.0
 * @version 1.0
 */
public class ContactSecretPersonAction extends BmpAction {

	private ContactSecretPersonService contactSecretPersonService;

	private ContactSecretPerson contactSecretPerson;

	private DataValidateService dataValidateService;

	private String deleteIds;

	private District district;
	private String fromQuery;
	private String checkLower;

	/**
	 *
	 * <p>
	 * 方法的说明
	 * </p>
	 * <p>
	 * 创建人 宋亚非 创建时间 2014-5-20 下午2:51:24
	 * </p>
	 * <blockquote> <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 *
	 * @return
	 */
	public String main() {
		district = getCurrentUser().getOrgan().getDistrict();
		return SUCCESS;
	}

	public String list() {
		// 统计中判断行政区代码
		if (district == null) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = contactSecretPersonService.get(
					district.getDistrictCode(), District.class);
		}
		boolean flag = false;
		// 判断查询列表过来的1查看保密局，0不是查询列表过来的查看本单位
		if ("1".equals(fromQuery)) {
			flag = true;
		}
		List<ContactSecretPerson> dataList = new ArrayList<ContactSecretPerson>();
		PageSortModel<ContactSecretPerson> psm = new PageSortModel<ContactSecretPerson>(
				getRequest(), "dataList");
		dataList = this.contactSecretPersonService.getListPage(psm,
				contactSecretPerson, this.getCurrentUser().getUserInfo()
						.getOrgan(), district, flag, checkLower);
		if (dataList != null && dataList.size() > 0) {
			for (ContactSecretPerson csp : dataList) {
				csp.setUndertackerNames(this.contactSecretPersonService
						.userInfoIds2Txt(csp.getUndertaker()));
				csp.setApprovedLeaderNames(this.contactSecretPersonService
						.userInfoIds2Txt(csp.getApprovedLeader()));
			}
		}
		this.putToRequest("dataList", dataList);
		return SUCCESS;
	}

	public String add() {
		return SUCCESS;
	}

	public String adding() {
		contactSecretPerson.setStatus(1);
		contactSecretPerson.setCreateOrgan(this.getCurrentUser().getUserInfo()
				.getOrgan());
		contactSecretPerson
				.setCreatePerson(this.getCurrentUser().getUserInfo());
		contactSecretPerson.setCreateTime(new Date());
		this.contactSecretPersonService.save(contactSecretPerson);
		this.addActionMessage("添加成功");
		BusinessLog log=new BusinessLog();
		log.setBusinessName("接触和知悉人情况");
		log.setPrimaryKey(contactSecretPerson.getContactSecretPersonId());
		BusinessLogContext.getInstance().getBusinessLogService().saveAddBusinessLogByModule(getCurrentUser(), log, contactSecretPerson);
		return redirectActionResult(LIST);
	}

	public String edit() {
		if (contactSecretPerson != null
				&& contactSecretPerson.getContactSecretPersonId() != null
				&& !"".equals(contactSecretPerson.getContactSecretPersonId())) {
			contactSecretPerson = this.contactSecretPersonService
					.get(contactSecretPerson.getContactSecretPersonId());
			if (contactSecretPerson != null) {
				contactSecretPerson
						.setUndertackerList(this.contactSecretPersonService
								.userInfoIds2ListObj(contactSecretPerson
										.getUndertaker()));
				contactSecretPerson
						.setApprovedLeaderList(this.contactSecretPersonService
								.userInfoIds2ListObj(contactSecretPerson
										.getApprovedLeader()));
				this.putToRequest("contactSecretPerson", contactSecretPerson);
			} else {
				this.addActionMessage("查找数据失败");
				return redirectActionResult(LIST);
			}
		} else {
			this.addActionMessage("查找数据失败");
			return redirectActionResult(LIST);
		}
		return SUCCESS;
	}

	public String editing() {
		ContactSecretPerson beforeCsp=new ContactSecretPerson();
		if (contactSecretPerson != null
				&& contactSecretPerson.getContactSecretPersonId() != null
				&& !"".equals(contactSecretPerson.getContactSecretPersonId())) {
			ContactSecretPerson cspDB = this.contactSecretPersonService
					.get(contactSecretPerson.getContactSecretPersonId());
			BeanUtils.copyProperties(beforeCsp, cspDB, CopyRuleEnum.ignoreCaseNull);
			if (cspDB != null) {
				cspDB.setSecretFileName(contactSecretPerson.getSecretFileName());
				cspDB.setDispatchOrgan(contactSecretPerson.getDispatchOrgan());
				cspDB.setIssuedDate(contactSecretPerson.getIssuedDate());
				cspDB.setReceiveDate(contactSecretPerson.getReceiveDate());
				cspDB.setFileNum(contactSecretPerson.getFileNum());
				cspDB.setContactPersonName(contactSecretPerson
						.getContactPersonName());
				cspDB.setContactDate(contactSecretPerson.getContactDate());
				cspDB.setContactType(contactSecretPerson.getContactType());
				cspDB.setContactWay(contactSecretPerson.getContactWay());
				cspDB.setUndertaker(contactSecretPerson.getUndertaker());
				cspDB.setApprovedLeader(contactSecretPerson.getApprovedLeader());
				cspDB.setModifyPerson(this.getCurrentUser().getUserInfo());
				cspDB.setModifyTime(new Date());
				this.contactSecretPersonService.update(cspDB);
				this.addActionMessage("修改成功");
				BusinessLog log = new BusinessLog();
				log.setBusinessName("接触和知悉人情况");
				log.setPrimaryKey(contactSecretPerson.getContactSecretPersonId());
				BusinessLogContext.getInstance().getBusinessLogService().saveEditBusinessLogByModule(getCurrentUser(), log, contactSecretPerson, beforeCsp);
			} else {
				this.addActionMessage("查询相关记录失败");
			}
		} else {
			this.addActionMessage("获取数据失败");
		}
		return redirectActionResult(LIST);
	}

	public String delete() {
		this.contactSecretPersonService.deleteSelected(deleteIds);
		this.addActionMessage("删除成功");
		for( String id : deleteIds.split(",") ){
                BusinessLog log = new BusinessLog();
                log.setBusinessName("接触和知悉人情况");
                log.setPrimaryKey(id);
                BusinessLogContext.getInstance().getBusinessLogService().saveDelBusinessLogByModule(getCurrentUser(), log, new ContactSecretPerson());
		}
		return redirectActionResult(LIST);
	}

	public String detail() {
		if (contactSecretPerson != null
				&& contactSecretPerson.getContactSecretPersonId() != null
				&& !"".equals(contactSecretPerson.getContactSecretPersonId())) {
			contactSecretPerson = this.contactSecretPersonService
					.get(contactSecretPerson.getContactSecretPersonId());
			if (contactSecretPerson != null) {
				contactSecretPerson
						.setUndertackerNames(this.contactSecretPersonService
								.userInfoIds2Txt(contactSecretPerson
										.getUndertaker()));
				contactSecretPerson
						.setApprovedLeaderNames(this.contactSecretPersonService
								.userInfoIds2Txt(contactSecretPerson
										.getApprovedLeader()));
				this.putToRequest("contactSecretPerson", contactSecretPerson);
			}
		}
		return SUCCESS;
	}

	public String export() {
		List<ContactSecretPerson> dataList = new ArrayList<ContactSecretPerson>();
		dataList = this.contactSecretPersonService.getListPage(null,
				contactSecretPerson, getCurrentUser().getOrgan(), null, false,
				null);
		if (dataList != null && dataList.size() > 0) {
			for (ContactSecretPerson csp : dataList) {
				csp.setUndertackerNames(this.contactSecretPersonService
						.userInfoIds2Txt(csp.getUndertaker()));
				csp.setApprovedLeaderNames(this.contactSecretPersonService
						.userInfoIds2Txt(csp.getApprovedLeader()));
			}
		}
		Map<String, Object> params = new HashMap<String, Object>();
		// 被导出的数据
		params.put("dataList", dataList);
		// 时间格式化器
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		params.put("dateFormat", dateFormat);
		// 数据字典工具
		DictionaryService dictionary = DictionaryContext.getInstance()
				.getDictionaryService();
		params.put("dictionary", dictionary);
		// 数据类型转换
		params.put("Integer", Integer.class);

		setResultData(params);
		return SUCCESS;
	}

	/**
	 *
	 * <p>
	 * 保密工作信息总览获取数据
	 * </p>
	 * <p>
	 * 创建人 陶汇源 创建时间 2014-5-7 - 下午2:28:35
	 * </p>
	 * <blockquote> <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * <li>宋亚非 2014-05-15 创建
	 * </ul>
	 * </blockquote>
	 *
	 * @return
	 */
	public String organIndex() {
		// TODO 暂无考虑时间查询，时间设计到业务模块数据变动历史情况，需要先调整业务模块
		String organId = getRequest().getParameter("organId").toString();
		QueryDto queryDto = new QueryDto();
		queryDto.setYear(Integer.parseInt(getRequest().getParameter(
				"queryDto.year").toString()));
		queryDto.setMonth(Integer.parseInt(getRequest().getParameter(
				"queryDto.month").toString()));
		Organ organ = this.contactSecretPersonService.get(organId, Organ.class);
		List<ContactSecretPerson> contactSecretPersonList = this.contactSecretPersonService
				.getListPage(null, null, organ, null, false, null);
		DictionaryService dictionaryService = DictionaryContext.getInstance()
				.getDictionaryService();
		if (contactSecretPersonList != null
				&& contactSecretPersonList.size() > 0) {
			for (ContactSecretPerson csp : contactSecretPersonList) {
				csp.setUndertackerNames(this.contactSecretPersonService
						.userInfoIds2Txt(csp.getUndertaker()));
				csp.setApprovedLeaderNames(this.contactSecretPersonService
						.userInfoIds2Txt(csp.getApprovedLeader()));
				csp.setContactWayTxt(dictionaryService.getOption("bmp",
						"contactWay", csp.getContactWay()).getOptionText());
			}
		}
		putToRequest("contactSecretPersonList", contactSecretPersonList);
		return SUCCESS;
	}

	public String dataValidate() {
		Map<String, Object> resultData = new HashMap<String, Object>();
		// ID值为bm_business_module中的ID
		resultData.put("id", "26");
		List<ContactSecretPerson> contactSecretPersonList = this.contactSecretPersonService
				.getListPage(null, null, getCurrentUser().getOrgan(), null,
						false, null);
		String msg = dataValidateService.validateData("接触和知悉绝密级商业秘密文件人员情况",
				contactSecretPersonList, "26");
		resultData.put("msg", msg);
		setResultData(resultData);
		return JSON;
	}

	/********************************************** getter && setter ************************************************************************/

	/**
	 * @return the deleteIds
	 */
	public String getDeleteIds() {
		return deleteIds;
	}

	/**
	 * @return the contactSecretPersonService
	 */
	public ContactSecretPersonService getContactSecretPersonService() {
		return contactSecretPersonService;
	}

	/**
	 * @param contactSecretPersonService
	 *            the contactSecretPersonService to set
	 */
	public void setContactSecretPersonService(
			ContactSecretPersonService contactSecretPersonService) {
		this.contactSecretPersonService = contactSecretPersonService;
	}

	/**
	 * @return the contactSecretPerson
	 */
	public ContactSecretPerson getContactSecretPerson() {
		return contactSecretPerson;
	}

	/**
	 * @param contactSecretPerson
	 *            the contactSecretPerson to set
	 */
	public void setContactSecretPerson(ContactSecretPerson contactSecretPerson) {
		this.contactSecretPerson = contactSecretPerson;
	}

	/**
	 * @param deleteIds
	 *            the deleteIds to set
	 */
	public void setDeleteIds(String deleteIds) {
		this.deleteIds = deleteIds;
	}

	/**
	 * @return the district
	 */
	public District getDistrict() {
		return district;
	}

	/**
	 * @param district
	 *            the district to set
	 */
	public void setDistrict(District district) {
		this.district = district;
	}

	/**
	 * @return the fromQuery
	 */
	public String getFromQuery() {
		return fromQuery;
	}

	/**
	 * @param fromQuery
	 *            the fromQuery to set
	 */
	public void setFromQuery(String fromQuery) {
		this.fromQuery = fromQuery;
	}

	/**
	 * @return the checkLower
	 */
	public String getCheckLower() {
		return checkLower;
	}

	/**
	 * @param checkLower
	 *            the checkLower to set
	 */
	public void setCheckLower(String checkLower) {
		this.checkLower = checkLower;
	}

	/**
	 * @return the dataValidateService
	 */
	public DataValidateService getDataValidateService() {
		return dataValidateService;
	}

	/**
	 * @param dataValidateService
	 *            the dataValidateService to set
	 */
	public void setDataValidateService(DataValidateService dataValidateService) {
		this.dataValidateService = dataValidateService;
	}

}
