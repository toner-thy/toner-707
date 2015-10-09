package com.cdthgk.setTheDecryption.action;

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
import com.cdthgk.platform.attachment.domain.Attachment;
import com.cdthgk.platform.dataValidate.service.DataValidateService;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.district.service.DistrictService;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.organ.service.OrganService;
import com.cdthgk.platform.permission.user.domain.User;
import com.cdthgk.setTheDecryption.service.SetTheDecryptionService;
import com.cdthgk.setTheDecryption.vo.SetTheDecryption;

import ec.common.PageSortModel;

@SuppressWarnings("all")
public class SetTheDecryptionAction extends BmpAction {
	private SetTheDecryptionService setTheDecryptionService;
	private SetTheDecryption setTheDecryption;

	private Attachment attachment;
	List<Attachment> attachmentList;
	private String showType;
	private District district;
	private DistrictService districtService;
	private OrganService organService;
	private DataValidateService dataValidateService;
	private boolean needReload = false;

	// --------销毁报废情况---------------------------------------------------------

	public String list() {
		PageSortModel psm = new PageSortModel(getRequest(), "list");
		Map<String, Object> params = new HashMap<String, Object>();
		List list = (List) setTheDecryptionService.getPageList(psm,
				setTheDecryption, params, getCurrentUser());
		this.putToRequest("list", list);
		return SUCCESS;
	}

	public String add() {
		return SUCCESS;
	}

	public String edit() {
		setTheDecryption = setTheDecryptionService
				.get(setTheDecryption.getId());
		return SUCCESS;
	}

	public boolean isNeedReload() {
		return needReload;
	}

	public void setNeedReload(boolean needReload) {
		this.needReload = needReload;
	}

	public String save() {
		setTheDecryption.setOrganId(getCurrentUser().getOrgan());
		setTheDecryption.setCreatePerson(getCurrentUser());
		setTheDecryption.setState(0);
		setTheDecryption.setCreateTime(new Date());
		setTheDecryptionService.save(setTheDecryption);
		addActionMessage("添加成功!");
		needReload = true;
		BusinessLog log=new BusinessLog();
		log.setBusinessName("定（解）密工作情况");
		log.setPrimaryKey(setTheDecryption.getId());
		BusinessLogContext.getInstance().getBusinessLogService().saveAddBusinessLogByModule(getCurrentUser(), log, setTheDecryption);
		return redirectActionResult(LIST);
	}

	public String update() {
		SetTheDecryption setTheDecryption2 = setTheDecryptionService
				.get(setTheDecryption.getId());
		SetTheDecryption beforeStd=new SetTheDecryption();
		BeanUtils.copyProperties(beforeStd, setTheDecryption2, CopyRuleEnum.ignoreCaseNull);
		BeanUtils.copyProperties(setTheDecryption2, setTheDecryption,
				CopyRuleEnum.ignoreCaseNull);
		setTheDecryption2.setDescription(setTheDecryption.getDescription());
		setTheDecryption2.setModifyPerson(getCurrentUser());
		setTheDecryption2.setModifyTime(new Date());
		setTheDecryptionService.update(setTheDecryption2);
		addActionMessage("修改成功!");
		needReload = true;
		BusinessLog log = new BusinessLog();
		log.setBusinessName("定（解）密工作情况");
		log.setPrimaryKey(setTheDecryption.getId());
		BusinessLogContext.getInstance().getBusinessLogService().saveEditBusinessLogByModule(getCurrentUser(), log, setTheDecryption2, beforeStd);
		return redirectActionResult(LIST);
	}

	public String delete() {
		List<String> strings = this.getIds();
		List<SetTheDecryption> rewardAndPenalties = new ArrayList<SetTheDecryption>();
		for (int i = 0; i < strings.size(); i++) {
			SetTheDecryption rewardAndPenalty2 = setTheDecryptionService
					.get(strings.get(i));
			rewardAndPenalty2.setState(1);
			rewardAndPenalties.add(rewardAndPenalty2);

		}
		for(String string:strings){
			SetTheDecryption sp = setTheDecryptionService
					.get(string);
			 if( sp!=null ){
	                BusinessLog log = new BusinessLog();
	                log.setBusinessName("定（解）密工作情况");
	                log.setPrimaryKey(string);
	                BusinessLogContext.getInstance().getBusinessLogService().saveDelBusinessLogByModule(getCurrentUser(), log, sp);
		        }
		}
		setTheDecryptionService.updateBatch(rewardAndPenalties);

		addActionMessage("删除成功!");
		return redirectActionResult(LIST);
	}

	public String detail() {
		setTheDecryption = setTheDecryptionService
				.get(setTheDecryption.getId());
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
		if (district != null && "".endsWith(district.getDistrictCode())) {
			district = new District();
			district = getCurrentUser().getOrgan().getDistrict();
		}
		district = setTheDecryptionService.get(district.getCode(),
				District.class);
		List list = (List) setTheDecryptionService.listForSelect(psm,
				setTheDecryption, district.getDistrictCode(), showType);
		this.putToRequest("list", list);
		return "allList";
	}

	public String organIndex() {
		// TODO 暂无考虑时间查询，时间设计到业务模块数据变动历史情况，需要先调整业务模块
		String organId = getRequest().getParameter("organId").toString();
		QueryDto queryDto = new QueryDto();
		queryDto.setYear(Integer.parseInt(getRequest().getParameter(
				"queryDto.year").toString()));
		queryDto.setMonth(Integer.parseInt(getRequest().getParameter(
				"queryDto.month").toString()));
		PageSortModel psm = new PageSortModel(getRequest(), "list");
		Organ organ = this.setTheDecryptionService.get(organId, Organ.class);
		Map<String, Object> params = new HashMap<String, Object>();
		User userTmp = new User();
		userTmp.setOrgan(organ);
		List<SetTheDecryption> dataList = this.setTheDecryptionService
				.getPageList(null, null, params, userTmp);
		putToRequest("dataList", dataList);
		return SUCCESS;
	}

	/**
	 *
	 * <p>
	 * 数据填写校验
	 * </p>
	 * <p>
	 * 创建人 陶汇源 创建时间 2014-5-26 - 上午9:53:11
	 * </p>
	 * <blockquote> <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 *
	 * @return
	 */
	public String dataValidate() {
		Map<String, Object> resultData = new HashMap<String, Object>();
		// ID值为bm_business_module中的ID
		resultData.put("id", "34");
		Map<String, Object> params = new HashMap<String, Object>();
		List<SetTheDecryption> dataList = this.setTheDecryptionService
				.getPageList(null, null, params, getCurrentUser());
		String msg = dataValidateService.validateData("定（解）密工作情况", dataList,
				"34");
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
	 * @return 返回 setTheDecryptionService
	 */
	public SetTheDecryptionService getSetTheDecryptionService() {
		return setTheDecryptionService;
	}

	/**
	 * @param setTheDecryptionService
	 *            设置 setTheDecryptionService
	 */
	public void setSetTheDecryptionService(
			SetTheDecryptionService setTheDecryptionService) {
		this.setTheDecryptionService = setTheDecryptionService;
	}

	/**
	 * @return 返回 setTheDecryption
	 */
	public SetTheDecryption getSetTheDecryption() {
		return setTheDecryption;
	}

	/**
	 * @param setTheDecryption
	 *            设置 setTheDecryption
	 */
	public void setSetTheDecryption(SetTheDecryption setTheDecryption) {
		this.setTheDecryption = setTheDecryption;
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
