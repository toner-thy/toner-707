/**
 *
 */
package com.cdthgk.bmp.stateSecretManagement.receiveConfidential.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.action.BmpAction;
import com.cdthgk.bmp.log.context.BusinessLogContext;
import com.cdthgk.bmp.log.vo.BusinessLog;
import com.cdthgk.bmp.stateSecretManagement.contactSecretPerson.vo.ContactSecretPerson;
import com.cdthgk.bmp.stateSecretManagement.receiveConfidential.service.ReceiveConfidentialService;
import com.cdthgk.bmp.stateSecretManagement.receiveConfidential.vo.ReceiveConfidential;
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
public class ReceiveConfidentialAction extends BmpAction {

	private ReceiveConfidentialService receiveConfidentialService;

	private DataValidateService dataValidateService;

	private ReceiveConfidential receiveConfidential;

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
			district = receiveConfidentialService.get(
					district.getDistrictCode(), District.class);
		}
		boolean flag = false;
		// 判断查询列表过来的1查看保密局，0不是查询列表过来的查看本单位
		if ("1".equals(fromQuery)) {
			flag = true;
		}

		List<ReceiveConfidential> dataList = new ArrayList<ReceiveConfidential>();
		PageSortModel<ReceiveConfidential> psm = new PageSortModel<ReceiveConfidential>(
				this.getRequest(), "dataList");
		dataList = this.receiveConfidentialService.getListPage(psm,
				receiveConfidential, this.getCurrentUser().getUserInfo()
						.getOrgan(), district, flag, checkLower);
		this.putToRequest("dataList", dataList);
		return SUCCESS;
	}

	public String add() {

		return SUCCESS;
	}

	public String adding() {
		receiveConfidential.setStatus(1);
		receiveConfidential
				.setCreatePerson(this.getCurrentUser().getUserInfo());
		receiveConfidential.setCreateTime(new Date());
		receiveConfidential.setCreateOrgan(this.getCurrentUser().getUserInfo()
				.getOrgan());
		this.receiveConfidentialService.save(receiveConfidential);
		this.addActionMessage("添加成功");
		BusinessLog log = new BusinessLog();
		log.setBusinessName("收到密件情况");
		log.setPrimaryKey(receiveConfidential.getReceiveConfidentialId());
		BusinessLogContext
				.getInstance()
				.getBusinessLogService()
				.saveAddBusinessLogByModule(getCurrentUser(), log,
						receiveConfidential);
		return redirectActionResult(LIST);
	}

	public String edit() {
		if (receiveConfidential != null
				&& receiveConfidential.getReceiveConfidentialId() != null
				&& !"".equals(receiveConfidential.getReceiveConfidentialId())) {
			receiveConfidential = this.receiveConfidentialService
					.get(receiveConfidential.getReceiveConfidentialId());
			if (receiveConfidential != null) {
				this.putToRequest("receiveConfidential", receiveConfidential);
				return SUCCESS;
			} else {
				this.addActionMessage("数据获取失败");
				return redirectActionResult(LIST);
			}
		} else {
			this.addActionMessage("查找失败");
			return redirectActionResult(LIST);
		}
	}

	public String editing() {
		ReceiveConfidential beforeRcf=new ReceiveConfidential();
		if (receiveConfidential != null
				&& receiveConfidential.getReceiveConfidentialId() != null
				&& !"".equals(receiveConfidential.getReceiveConfidentialId())) {
			ReceiveConfidential rcDB = this.receiveConfidentialService
					.get(receiveConfidential.getReceiveConfidentialId());
			BeanUtils.copyProperties(beforeRcf, rcDB, CopyRuleEnum.ignoreCaseNull);
			if (rcDB != null) {
				rcDB.setModifyPerson(this.getCurrentUser().getUserInfo());
				rcDB.setModifyTime(new Date());
				rcDB.setConfidentialName(receiveConfidential
						.getConfidentialName());
				rcDB.setConfidentialContent(receiveConfidential
						.getConfidentialContent());
				rcDB.setSecurityLevel(receiveConfidential.getSecurityLevel());
				rcDB.setReceiverTime(receiveConfidential.getReceiverTime());
				rcDB.setDocNum(receiveConfidential.getDocNum());
				rcDB.setCarrierFormat(receiveConfidential.getCarrierFormat());
				rcDB.setFileNum(receiveConfidential.getFileNum());
				this.receiveConfidentialService.update(rcDB);
				this.addActionMessage("修改成功");
				BusinessLog log = new BusinessLog();
				log.setBusinessName("收到密件情况");
				log.setPrimaryKey(receiveConfidential.getReceiveConfidentialId());
				BusinessLogContext.getInstance().getBusinessLogService().saveEditBusinessLogByModule(getCurrentUser(), log, receiveConfidential, beforeRcf);
			} else {
				this.addActionMessage("获取相关数据失败");
			}
		} else {
			this.addActionMessage("修改失败");
		}
		return redirectActionResult(LIST);
	}

	public String delete() {
		this.receiveConfidentialService.deleteSelected(deleteIds);
		for( String id : deleteIds.split(",") ){
                BusinessLog log = new BusinessLog();
                log.setBusinessName("收到密件情况");
                log.setPrimaryKey(id);
                BusinessLogContext.getInstance().getBusinessLogService().saveDelBusinessLogByModule(getCurrentUser(), log, new ReceiveConfidential());
		}
		this.addActionMessage("删除成功");
		return redirectActionResult(LIST);
	}

	public String detail() {
		if (receiveConfidential != null
				&& receiveConfidential.getReceiveConfidentialId() != null
				&& !"".equals(receiveConfidential.getReceiveConfidentialId())) {
			receiveConfidential = this.receiveConfidentialService
					.get(receiveConfidential.getReceiveConfidentialId());
			if (receiveConfidential != null) {
				this.putToRequest("receiveConfidential", receiveConfidential);
			}
		}
		return SUCCESS;
	}

	public String export() {
		List<ReceiveConfidential> dataList = new ArrayList<ReceiveConfidential>();
		dataList = this.receiveConfidentialService.getListPage(null,
				receiveConfidential, this.getCurrentUser().getUserInfo()
						.getOrgan(), null, false, null);
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
		Organ organ = this.receiveConfidentialService.get(organId, Organ.class);
		List<ReceiveConfidential> receiveConfidentialList = this.receiveConfidentialService
				.getListPage(null, null, organ, null, false, null);
		DictionaryService dictionAryService = DictionaryContext.getInstance()
				.getDictionaryService();
		for (ReceiveConfidential rc : receiveConfidentialList) {
			rc.setSecurityLevelTxt(dictionAryService.getOption("bmp",
					"secrecy_level_thing", rc.getSecurityLevel())
					.getOptionText());
			rc.setCarrierFormatTxt(dictionAryService.getOption("bmp",
					"carrierFormat", rc.getCarrierFormat()).getOptionText());
		}
		putToRequest("receiveConfidentialList", receiveConfidentialList);
		return SUCCESS;
	}

	public String dataValidate() {
		Map<String, Object> resultData = new HashMap<String, Object>();
		// ID值为bm_business_module中的ID
		resultData.put("id", "25");
		List<ReceiveConfidential> receiveConfidentialList = this.receiveConfidentialService
				.getListPage(null, null, getCurrentUser().getOrgan(), null,
						false, null);
		String msg = dataValidateService.validateData("收到密件情况",
				receiveConfidentialList, "25");
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
	 * @return the receiveConfidentialService
	 */
	public ReceiveConfidentialService getReceiveConfidentialService() {
		return receiveConfidentialService;
	}

	/**
	 * @param receiveConfidentialService
	 *            the receiveConfidentialService to set
	 */
	public void setReceiveConfidentialService(
			ReceiveConfidentialService receiveConfidentialService) {
		this.receiveConfidentialService = receiveConfidentialService;
	}

	/**
	 * @return the receiveConfidential
	 */
	public ReceiveConfidential getReceiveConfidential() {
		return receiveConfidential;
	}

	/**
	 * @param receiveConfidential
	 *            the receiveConfidential to set
	 */
	public void setReceiveConfidential(ReceiveConfidential receiveConfidential) {
		this.receiveConfidential = receiveConfidential;
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
