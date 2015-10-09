package com.cdthgk.rewardAndPenalty.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.action.BmpAction;
import com.cdthgk.bmp.log.context.BusinessLogContext;
import com.cdthgk.bmp.log.vo.BusinessLog;
import com.cdthgk.common.bean.BeanUtils;
import com.cdthgk.common.bean.rule.CopyRuleEnum;
import com.cdthgk.platform.attachment.domain.Attachment;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.district.service.DistrictService;
import com.cdthgk.platform.organization.organ.service.OrganService;
import com.cdthgk.rewardAndPenalty.service.PaperPresentedService;
import com.cdthgk.rewardAndPenalty.vo.PaperPresented;
import com.cdthgk.rewardAndPenalty.vo.RewardAndPenalty;

import ec.common.PageSortModel;

@SuppressWarnings("all")
public class PaperPresentedAction extends BmpAction {
	private PaperPresentedService paperPresentedService;
	// 目标条目分类
	private PaperPresented paperPresented;
	private Attachment attachment;
	List<Attachment> attachmentList;
	private String showType;
	private District district;
	private DistrictService districtService;
	private OrganService organService;
	private boolean needReload = false;

	public String list() {
		PageSortModel psm = new PageSortModel(getRequest(), "list");
		Map<String, Object> params = new HashMap<String, Object>();
		List list = (List) paperPresentedService.getPageList(psm,
				paperPresented, params, getCurrentUser());
		this.putToRequest("list", list);
		return SUCCESS;
	}

	public String add() {
		return SUCCESS;
	}

	public String edit() {
		paperPresented = paperPresentedService.get(paperPresented.getId());
		return SUCCESS;
	}

	public boolean isNeedReload() {
		return needReload;
	}

	public void setNeedReload(boolean needReload) {
		this.needReload = needReload;
	}

	public String save() {
		paperPresented.setOrganId(getCurrentUser().getOrgan());
		paperPresented.setCreatePerson(getCurrentUser());
		paperPresented.setState(0);
		paperPresented.setCreateTime(new Date());
		paperPresentedService.save(paperPresented);
		addActionMessage("添加成功!");
		needReload = true;
		BusinessLog log = new BusinessLog();
		log.setBusinessName("论文评奖情况");
		log.setPrimaryKey(paperPresented.getId());
		BusinessLogContext
				.getInstance()
				.getBusinessLogService()
				.saveAddBusinessLogByModule(getCurrentUser(), log,
						new PaperPresented());
		return redirectActionResult(LIST);
	}

	public String update() {
		PaperPresented beforePp = new PaperPresented();
		PaperPresented paperPresented2 = paperPresentedService
				.get(paperPresented.getId());
		BeanUtils.copyProperties(beforePp, paperPresented2,
				CopyRuleEnum.ignoreCaseNull);
		paperPresented2.setDate(paperPresented.getDate());
		paperPresented2.setDescription(paperPresented.getDescription());
		paperPresented2.setModifyPerson(getCurrentUser());
		paperPresented2.setModifyTime(new Date());
		paperPresented2.setName(paperPresented.getName());
		paperPresented2.setAuthor(paperPresented.getAuthor());
		paperPresented2.setAwardName(paperPresented.getAwardName());
		paperPresented2.setAwardOrgan(paperPresented.getAwardOrgan());
		paperPresented2.setDate(paperPresented.getDate());
		paperPresented2.setPeriodical(paperPresented.getPeriodical());
		paperPresentedService.update(paperPresented2);
		addActionMessage("修改成功!");
		BusinessLog log = new BusinessLog();
		log.setBusinessName("论文评奖情况");
		log.setPrimaryKey(paperPresented2.getId());
		BusinessLogContext
				.getInstance()
				.getBusinessLogService()
				.saveEditBusinessLogByModule(getCurrentUser(), log,
						paperPresented2, beforePp);

		needReload = true;
		return redirectActionResult(LIST);
	}

	public String delete() {
		List<String> strings = this.getIds();
		List<PaperPresented> rewardAndPenalties = new ArrayList<PaperPresented>();
		for (int i = 0; i < strings.size(); i++) {
			PaperPresented rewardAndPenalty2 = paperPresentedService
					.get(strings.get(i));
			rewardAndPenalty2.setState(1);
			rewardAndPenalties.add(rewardAndPenalty2);

		}
		for (PaperPresented pp : rewardAndPenalties) {
			if (pp != null) {
				BusinessLog log = new BusinessLog();
				log.setBusinessName("论文评奖情况");
				log.setPrimaryKey(pp.getId());
				BusinessLogContext.getInstance().getBusinessLogService()
						.saveDelBusinessLogByModule(getCurrentUser(), log, pp);
			}
		}
		paperPresentedService.updateBatch(rewardAndPenalties);
		addActionMessage("删除成功!");
		return redirectActionResult(LIST);
	}

	public String detail() {
		paperPresented = paperPresentedService.get(paperPresented.getId());
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

		district = paperPresentedService
				.get(district.getCode(), District.class);
		List list = (List) paperPresentedService.listForSelect(psm,
				paperPresented, district.getDistrictCode(), showType);
		this.putToRequest("list", list);
		return "allList";
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
	 * @return 返回 paperPresentedService
	 */
	public PaperPresentedService getPaperPresentedService() {
		return paperPresentedService;
	}

	/**
	 * @param paperPresentedService
	 *            设置 paperPresentedService
	 */
	public void setPaperPresentedService(
			PaperPresentedService paperPresentedService) {
		this.paperPresentedService = paperPresentedService;
	}

	/**
	 * @return 返回 paperPresented
	 */
	public PaperPresented getPaperPresented() {
		return paperPresented;
	}

	/**
	 * @param paperPresented
	 *            设置 paperPresented
	 */
	public void setPaperPresented(PaperPresented paperPresented) {
		this.paperPresented = paperPresented;
	}
}
