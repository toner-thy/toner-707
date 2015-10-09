package com.cdthgk.rewardAndPenalty.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.action.BmpAction;
import com.cdthgk.bmp.log.context.BusinessLogContext;
import com.cdthgk.bmp.log.vo.BusinessLog;
import com.cdthgk.bmp.publicityEducation.departmentWebsite.vo.Departmentwebsite;
import com.cdthgk.bmp.secrecyperson.vo.SecrecyPerson;
import com.cdthgk.bmp.statinfo.dto.QueryDto;
import com.cdthgk.common.bean.BeanUtils;
import com.cdthgk.common.bean.rule.CopyRuleEnum;
import com.cdthgk.common.lang.CollectionUtils;
import com.cdthgk.platform.attachment.domain.Attachment;
import com.cdthgk.platform.dataValidate.service.DataValidateService;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.district.service.DistrictService;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.organ.service.OrganService;
import com.cdthgk.platform.permission.user.domain.User;
import com.cdthgk.rewardAndPenalty.service.RewardAndPenaltyService;
import com.cdthgk.rewardAndPenalty.vo.RewardAndPenalty;

import ec.common.PageSortModel;

@SuppressWarnings("all")
public class RewardAndPenaltyAction extends BmpAction {
	private RewardAndPenaltyService rewardAndPenaltyService;
	private RewardAndPenalty rewardAndPenalty;
	private Attachment attachment;
	List<Attachment> attachmentList;
	private String showType;
	private District district;
	private DistrictService districtService;
	private OrganService organService;
	private DataValidateService dataValidateService;
	private boolean needReload = false;

	public String list() {
		PageSortModel psm = new PageSortModel(getRequest(), "list");
		Map<String, Object> params = new HashMap<String, Object>();
		List list = (List) rewardAndPenaltyService.getPageList(psm,
				rewardAndPenalty, params, getCurrentUser());
		this.putToRequest("list", list);
		return SUCCESS;
	}

	public String add() {
		return SUCCESS;
	}

	public String edit() {
		rewardAndPenalty = rewardAndPenaltyService
				.get(rewardAndPenalty.getId());
		return SUCCESS;
	}

	public boolean isNeedReload() {
		return needReload;
	}

	public void setNeedReload(boolean needReload) {
		this.needReload = needReload;
	}

	public String save() {
		rewardAndPenalty.setOrganId(getCurrentUser().getOrgan());
		rewardAndPenalty.setCreatePerson(getCurrentUser());
		rewardAndPenalty.setState(0);
		rewardAndPenalty.setCreateTime(new Date());
		rewardAndPenaltyService.save(rewardAndPenalty);
		addActionMessage("添加成功!");
		needReload = true;
		BusinessLog log = new BusinessLog();
		log.setBusinessName("实施奖惩情况");
		log.setPrimaryKey(rewardAndPenalty.getId());
		BusinessLogContext
				.getInstance()
				.getBusinessLogService()
				.saveAddBusinessLogByModule(getCurrentUser(), log,
						new RewardAndPenalty());
		return redirectActionResult(LIST);
	}

	public String update() {
		RewardAndPenalty beforeRap = new RewardAndPenalty();
		RewardAndPenalty rewardAndPenalty2 = rewardAndPenaltyService
				.get(rewardAndPenalty.getId());
		BeanUtils.copyProperties(beforeRap, rewardAndPenalty2,
				CopyRuleEnum.ignoreCaseNull);
		rewardAndPenalty2.setDate(rewardAndPenalty.getDate());
		rewardAndPenalty2.setDescription(rewardAndPenalty.getDescription());
		rewardAndPenalty2.setModifyPerson(getCurrentUser());
		rewardAndPenalty2.setModifyTime(new Date());
		rewardAndPenalty2.setName(rewardAndPenalty.getName());
		rewardAndPenalty2.setPenaltyCircs(rewardAndPenalty.getPenaltyCircs());
		rewardAndPenalty2.setRewardCircs(rewardAndPenalty.getRewardCircs());
		rewardAndPenaltyService.update(rewardAndPenalty2);
		addActionMessage("修改成功!");
		BusinessLog log = new BusinessLog();
		log.setBusinessName("实施奖惩情况");
		log.setPrimaryKey(rewardAndPenalty2.getId());
		BusinessLogContext
				.getInstance()
				.getBusinessLogService()
				.saveEditBusinessLogByModule(getCurrentUser(), log,
						rewardAndPenalty2, beforeRap);
		needReload = true;
		return redirectActionResult(LIST);
	}

	public String delete() {
		List<String> strings = this.getIds();
		List<RewardAndPenalty> rewardAndPenalties = new ArrayList<RewardAndPenalty>();
		for (int i = 0; i < strings.size(); i++) {
			RewardAndPenalty rewardAndPenalty2 = rewardAndPenaltyService
					.get(strings.get(i));
			rewardAndPenalty2.setState(1);
			rewardAndPenalties.add(rewardAndPenalty2);

		}
		for (RewardAndPenalty rap : rewardAndPenalties) {
			if (rap != null) {
				BusinessLog log = new BusinessLog();
				log.setBusinessName("实施奖惩情况");
				log.setPrimaryKey(rap.getId());
				BusinessLogContext.getInstance().getBusinessLogService()
						.saveDelBusinessLogByModule(getCurrentUser(), log, rap);
			}
		}
		rewardAndPenaltyService.updateBatch(rewardAndPenalties);
		addActionMessage("删除成功!");
		return redirectActionResult(LIST);
	}

	public String detail() {
		rewardAndPenalty = rewardAndPenaltyService
				.get(rewardAndPenalty.getId());
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
			district = getCurrentUser().getOrgan().getDistrict();
		}
		if (district != null && "".equals(district.getDistrictCode())) {
			district = new District();
			district = getCurrentUser().getOrgan().getDistrict();
		}
		district = rewardAndPenaltyService.get(district.getCode(),
				District.class);
		List list = (List) rewardAndPenaltyService.listForSelect(psm,
				rewardAndPenalty, district.getDistrictCode(), showType);
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
		Organ organ = this.rewardAndPenaltyService.get(organId, Organ.class);
		Map<String, Object> params = new HashMap<String, Object>();
		User tmpUser = new User();
		Organ tmpOrgan = this.rewardAndPenaltyService.get(organId, Organ.class);
		tmpUser.setOrgan(tmpOrgan);
		List<RewardAndPenalty> rewardAndPenaltylist = rewardAndPenaltyService
				.getPageList(null, null, params, tmpUser);
		putToRequest("rewardAndPenaltylist", rewardAndPenaltylist);
		return SUCCESS;
	}

	public String dataValidate() {
		Map<String, Object> resultData = new HashMap<String, Object>();
		// ID值为bm_business_module中的ID
		resultData.put("id", "24");
		Map<String, Object> params = new HashMap<String, Object>();
		List<RewardAndPenalty> rewardAndPenaltylist = rewardAndPenaltyService
				.getPageList(null, null, params, getCurrentUser());
		String msg = dataValidateService.validateData("保密工作奖惩情况",
				rewardAndPenaltylist, "24");
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
	 * @return 返回 rewardAndPenaltyService
	 */
	public RewardAndPenaltyService getRewardAndPenaltyService() {
		return rewardAndPenaltyService;
	}

	/**
	 * @param rewardAndPenaltyService
	 *            设置 rewardAndPenaltyService
	 */
	public void setRewardAndPenaltyService(
			RewardAndPenaltyService rewardAndPenaltyService) {
		this.rewardAndPenaltyService = rewardAndPenaltyService;
	}

	/**
	 * @return 返回 rewardAndPenalty
	 */
	public RewardAndPenalty getRewardAndPenalty() {
		return rewardAndPenalty;
	}

	/**
	 * @param rewardAndPenalty
	 *            设置 rewardAndPenalty
	 */
	public void setRewardAndPenalty(RewardAndPenalty rewardAndPenalty) {
		this.rewardAndPenalty = rewardAndPenalty;
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
