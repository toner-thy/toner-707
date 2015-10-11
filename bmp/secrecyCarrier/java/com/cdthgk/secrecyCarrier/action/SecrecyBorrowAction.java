package com.cdthgk.secrecyCarrier.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.action.BmpAction;
import com.cdthgk.bmp.log.context.BusinessLogContext;
import com.cdthgk.bmp.log.vo.BusinessLog;
import com.cdthgk.bmp.statinfo.dto.QueryDto;
import com.cdthgk.common.bean.BeanUtils;
import com.cdthgk.common.bean.rule.CopyRuleEnum;
import com.cdthgk.common.lang.CollectionUtils;
import com.cdthgk.platform.attachment.context.AttachmentContext;
import com.cdthgk.platform.attachment.domain.Attachment;
import com.cdthgk.platform.dataValidate.service.DataValidateService;
import com.cdthgk.platform.dictionary.context.DictionaryContext;
import com.cdthgk.platform.dictionary.spi.DictionaryService;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.district.service.DistrictService;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.organ.service.OrganService;
import com.cdthgk.platform.permission.user.domain.User;
import com.cdthgk.secrecyCarrier.service.SecrecyBorrowService;
import com.cdthgk.secrecyCarrier.vo.DestructionScrap;
import com.cdthgk.secrecyCarrier.vo.SecrecyBorrow;

import ec.common.PageSortModel;

@SuppressWarnings("all")
public class SecrecyBorrowAction extends BmpAction {
	private SecrecyBorrowService secrecyBorrowService;
	// 目标条目分类
	private SecrecyBorrow secrecyBorrow;
	private DataValidateService dataValidateService;

	private Attachment attachment;
	List<Attachment> attachmentList;
	private List<String> secAttach;
	private String showType;
	private District district;
	private DistrictService districtService;
	private OrganService organService;
	private boolean needReload = false;

	// --------载体借阅情况---------------------------------------------------------

	public String list() {
		PageSortModel psm = new PageSortModel(getRequest(), "list");
		Map<String, Object> params = new HashMap<String, Object>();
		List list = (List) secrecyBorrowService.getPageList(psm, secrecyBorrow,
				params, getCurrentUser());
		this.putToRequest("list", list);
		return SUCCESS;
	}

	public String add() {
		return SUCCESS;
	}

	public String edit() {
		secrecyBorrow = secrecyBorrowService.get(secrecyBorrow.getId());
		attachmentList = AttachmentContext.getInstance().getAttachmentService().getAttachmentsByHostId(secrecyBorrow.getId());
		return SUCCESS;
	}

	public boolean isNeedReload() {
		return needReload;
	}

	public void setNeedReload(boolean needReload) {
		this.needReload = needReload;
	}

	public String save() {
		secrecyBorrow.setOrganId(getCurrentUser().getOrgan());
		secrecyBorrow.setCreatePerson(getCurrentUser());
		secrecyBorrow.setState(0);
		secrecyBorrow.setCreateTime(new Date());
		secrecyBorrowService.save(secrecyBorrow);
		AttachmentContext.getInstance().getAttachmentService().updateHostId(secrecyBorrow.getId(),secAttach);
		addActionMessage("添加成功!");
		needReload = true;
		BusinessLog log = new BusinessLog();
		log.setBusinessName("涉密载体借阅情况");
		log.setPrimaryKey(secrecyBorrow.getId());
		BusinessLogContext
				.getInstance()
				.getBusinessLogService()
				.saveAddBusinessLogByModule(getCurrentUser(), log,
						new SecrecyBorrow());
		return redirectActionResult(LIST);
	}

	public String update() {
		SecrecyBorrow beforeSb = new SecrecyBorrow();
		SecrecyBorrow secrecyBorrow2 = secrecyBorrowService.get(secrecyBorrow
				.getId());
		BeanUtils.copyProperties(beforeSb, secrecyBorrow2,
				CopyRuleEnum.ignoreCaseNull);
		secrecyBorrow2.setModifyPerson(getCurrentUser());
		BeanUtils.copyProperties(secrecyBorrow2, secrecyBorrow,
				CopyRuleEnum.ignoreCaseNull);
		secrecyBorrow2.setModifyTime(new Date());
		secrecyBorrowService.update(secrecyBorrow2);
		AttachmentContext.getInstance().getAttachmentService().updateHostId(secrecyBorrow2.getId(),secAttach);
		addActionMessage("修改成功!");
		needReload = true;
		BusinessLog log = new BusinessLog();
		log.setBusinessName("涉密载体借阅情况");
		log.setPrimaryKey(secrecyBorrow2.getId());
		BusinessLogContext
				.getInstance()
				.getBusinessLogService()
				.saveEditBusinessLogByModule(getCurrentUser(), log,
						secrecyBorrow2, beforeSb);
		return redirectActionResult(LIST);
	}

	public String delete() {
		List<String> strings = this.getIds();
		List<SecrecyBorrow> rewardAndPenalties = new ArrayList<SecrecyBorrow>();
		for (int i = 0; i < strings.size(); i++) {
			SecrecyBorrow rewardAndPenalty2 = secrecyBorrowService.get(strings
					.get(i));
			BusinessLog log = new BusinessLog();
			log.setBusinessName("涉密载体借阅情况");
			log.setPrimaryKey(strings.get(i));
			BusinessLogContext
					.getInstance()
					.getBusinessLogService()
					.saveDelBusinessLogByModule(getCurrentUser(), log,
							rewardAndPenalty2);
			rewardAndPenalty2.setState(1);
			rewardAndPenalties.add(rewardAndPenalty2);

		}
		secrecyBorrowService.updateBatch(rewardAndPenalties);
		addActionMessage("删除成功!");
		return redirectActionResult(LIST);
	}

	public String detail() {
		secrecyBorrow = secrecyBorrowService.get(secrecyBorrow.getId());
		attachmentList = AttachmentContext.getInstance().getAttachmentService().getAttachmentsByHostId(secrecyBorrow.getId());
		return SUCCESS;
	}

	public String main() {
		return SUCCESS;
	}

	public String allList() {
		PageSortModel psm = new PageSortModel(getRequest(), "list");
		if (showType == null || "".equals(showType)) {
			showType = "0";
		}

		if (district == null) {
			district = new District();
			district.setDistrictCode(getCurrentUser().getOrgan().getDistrict()
					.getDistrictCode());
		}
		if (district != null && "".equals(district.getDistrictCode())) {
			district = new District();
			district.setDistrictCode(getCurrentUser().getOrgan().getDistrict()
					.getDistrictCode());
		}
		district = secrecyBorrowService.get(district.getDistrictCode(),
				District.class);
		List list = (List) secrecyBorrowService.listForSelect(psm,
				secrecyBorrow, district.getDistrictCode(), showType);
		this.putToRequest("list", list);
		return "allList";
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
		Organ organ = this.secrecyBorrowService.get(organId, Organ.class);
		User userTmp = new User();
		userTmp.setOrgan(organ);
		Map<String, Object> params = new HashMap<String, Object>();
		List<SecrecyBorrow> secrecyBorrowList = (List<SecrecyBorrow>) secrecyBorrowService
				.getPageList(null, null, params, userTmp);
		DictionaryService dictionaryService = DictionaryContext.getInstance()
				.getDictionaryService();
		if (secrecyBorrowList != null && secrecyBorrowList.size() > 0) {
			for (SecrecyBorrow sb : secrecyBorrowList) {
				sb.setSecrecyLevelTxt(dictionaryService.getOption("bmp",
						"secrecy_level_thing", sb.getSecrecyLevel())
						.getOptionText());
			}
		}
		putToRequest("secrecyBorrowList", secrecyBorrowList);
		return SUCCESS;
	}

	public String dataValidate() {
		Map<String, Object> resultData = new HashMap<String, Object>();
		// ID值为bm_business_module中的ID
		resultData.put("id", "28");
		Map<String, Object> params = new HashMap<String, Object>();
		List<SecrecyBorrow> secrecyBorrowList = (List<SecrecyBorrow>) secrecyBorrowService
				.getPageList(null, null, params, getCurrentUser());
		String msg = dataValidateService.validateData("涉密载体借阅情况",
				secrecyBorrowList, "28");
		resultData.put("msg", msg);
		setResultData(resultData);
		return JSON;
	}

	public Attachment getAttachment() {
		return attachment;
	}

	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}

	public List<Attachment> getAttachmentList() {
		return attachmentList;
	}

	public void setAttachmentList(List<Attachment> attachmentList) {
		this.attachmentList = attachmentList;
	}

	public String getShowType() {
		return showType;
	}

	public void setShowType(String showType) {
		this.showType = showType;
	}

	public OrganService getOrganService() {
		return organService;
	}

	public void setOrganService(OrganService organService) {
		this.organService = organService;
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

	/**
	 * @return 返回 secrecyBorrowService
	 */
	public SecrecyBorrowService getSecrecyBorrowService() {
		return secrecyBorrowService;
	}

	/**
	 * @param secrecyBorrowService
	 *            设置 secrecyBorrowService
	 */
	public void setSecrecyBorrowService(
			SecrecyBorrowService secrecyBorrowService) {
		this.secrecyBorrowService = secrecyBorrowService;
	}

	/**
	 * @return 返回 secrecyBorrow
	 */
	public SecrecyBorrow getSecrecyBorrow() {
		return secrecyBorrow;
	}

	/**
	 * @param secrecyBorrow
	 *            设置 secrecyBorrow
	 */
	public void setSecrecyBorrow(SecrecyBorrow secrecyBorrow) {
		this.secrecyBorrow = secrecyBorrow;
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

	public List<String> getSecAttach() {
		return secAttach;
	}

	public void setSecAttach(List<String> secAttach) {
		this.secAttach = secAttach;
	}


}
