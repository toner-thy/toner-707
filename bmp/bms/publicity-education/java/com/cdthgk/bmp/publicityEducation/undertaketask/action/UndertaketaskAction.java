/**
 *
 */
package com.cdthgk.bmp.publicityEducation.undertaketask.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.agenda.vo.Agenda;
import com.cdthgk.bmp.core.action.BmpAction;
import com.cdthgk.bmp.log.context.BusinessLogContext;
import com.cdthgk.bmp.log.vo.BusinessLog;
import com.cdthgk.bmp.publicityEducation.undertaketask.service.UndertaketaskService;
import com.cdthgk.bmp.publicityEducation.undertaketask.vo.Undertaketask;
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
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;
import com.cdthgk.rewardAndPenalty.vo.RewardAndPenalty;

import ec.common.PageSortModel;

/**
 * <p>
 * 承担课题情况
 * </p>
 * <p>
 * 创建人 宋亚非 创建时间 2014-5-5 - 上午11:29:07
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
public class UndertaketaskAction extends BmpAction {
	private UndertaketaskService undertaketaskService;

	private DataValidateService dataValidateService;

	private Undertaketask undertaketask;

	private String deleteIds;

	private District district;
	private String fromQuery;
	private String checkLower;

    private List<String> secAttach;
    private List<Attachment> attachmentList;
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
			district = undertaketaskService.get(district.getDistrictCode(),
					District.class);
		}
		boolean flag = false;
		// 判断查询列表过来的1查看保密局，0不是查询列表过来的查看本单位
		if ("1".equals(fromQuery)) {
			flag = true;
		}

		List<Undertaketask> dataList = new ArrayList<Undertaketask>();
		PageSortModel<Undertaketask> psm = new PageSortModel<Undertaketask>(
				getRequest(), "dataList");

		dataList = this.undertaketaskService.getListPage(psm, undertaketask,
				getCurrentUser().getOrgan(), district, flag, checkLower);
		this.dealUserNameList(dataList);
		this.putToRequest("dataList", dataList);
		return SUCCESS;
	}

	public void dealUserNameList(List<Undertaketask> dataList) {
		if (dataList != null && dataList.size() > 0) {
			for (Undertaketask utt : dataList) {
				if (utt != null && utt.getUndertaker() != null
						&& !"".equals(utt.getUndertaker())) {
					List<UserInfo> uiList = new ArrayList<UserInfo>();
					for (String uiId : utt.getUndertaker().split(",")) {
						UserInfo ui = this.undertaketaskService.get(
								uiId.trim(), UserInfo.class);
						if (ui != null) {
							uiList.add(ui);
						}
					}
					utt.setUndertakerList(uiList);
				}
			}
		}
	}

	public void dealUserName(Undertaketask data) {
		if (data != null && data.getUndertaker() != null
				&& !"".equals(data.getUndertaker())) {
			List<UserInfo> uiList = new ArrayList<UserInfo>();
			for (String uiId : data.getUndertaker().split(",")) {
				UserInfo ui = this.undertaketaskService.get(uiId.trim(),
						UserInfo.class);
				if (ui != null) {
					uiList.add(ui);
				}
			}
			data.setUndertakerList(uiList);
		}
	}

	public String add() {
		System.out.println(undertaketask);
		return SUCCESS;
	}

	public String adding() {
		undertaketask.setCreatePerson(this.getCurrentUser().getUserInfo());
		undertaketask.setCreateTime(new Date(System.currentTimeMillis()));
		undertaketask.setCreateOrgan(this.getCurrentUser().getUserInfo()
				.getOrgan());
		undertaketask.setStatus(1);
		this.undertaketaskService.save(undertaketask);

		 // 新增时添加附件
        AttachmentContext.getInstance().getAttachmentService().updateHostId(undertaketask.getUndertaketaskId(),secAttach);

		this.addActionMessage("添加成功");
		BusinessLog log = new BusinessLog();
		log.setBusinessName("承担课题情况");
		log.setPrimaryKey(undertaketask.getUndertaketaskId());
		BusinessLogContext
				.getInstance()
				.getBusinessLogService()
				.saveAddBusinessLogByModule(getCurrentUser(), log,
						new RewardAndPenalty());
		return redirectActionResult(LIST);
	}

	public String edit() {
		if (undertaketask != null && undertaketask.getUndertaketaskId() != null
				&& !"".equals(undertaketask.getUndertaketaskId())) {
			undertaketask = this.undertaketaskService.get(undertaketask
					.getUndertaketaskId());
			attachmentList = AttachmentContext.getInstance().getAttachmentService().getAttachmentsByHostId(undertaketask.getUndertaketaskId());

			if (undertaketask != null) {
				this.dealUserName(undertaketask);
				this.putToRequest("undertaketask", undertaketask);
				return SUCCESS;
			} else {
				this.addActionMessage("获取信息失败，请重试");
				return redirectActionResult(LIST);
			}
		} else {
			this.addActionMessage("获取信息失败，请重试");
			return redirectActionResult(LIST);
		}
	}

	public String editing() {
		Undertaketask beforeU = new Undertaketask();
		if (undertaketask != null && undertaketask.getUndertaketaskId() != null
				&& !"".equals(undertaketask.getUndertaketaskId())) {
			Undertaketask uttDB = undertaketaskService.get(undertaketask
					.getUndertaketaskId());
			BeanUtils.copyProperties(beforeU, uttDB,
					CopyRuleEnum.ignoreCaseNull);
			uttDB.setTaskName(undertaketask.getTaskName());
			uttDB.setReleaseUnit(undertaketask.getReleaseUnit());
			uttDB.setUndertaker(undertaketask.getUndertaker());
			uttDB.setCompleteTime(undertaketask.getCompleteTime());
			uttDB.setSpecificResults(undertaketask.getSpecificResults());
			uttDB.setModifyPerson(this.getCurrentUser().getUserInfo());
			uttDB.setModifyTime(new Date());
			this.undertaketaskService.update(uttDB);
			this.addActionError("修改成功");

			AttachmentContext.getInstance().getAttachmentService().updateHostId(uttDB.getUndertaketaskId(),secAttach);

			BusinessLog log = new BusinessLog();
			log.setBusinessName("承担课题情况");
			log.setPrimaryKey(uttDB.getUndertaketaskId());
			BusinessLogContext
					.getInstance()
					.getBusinessLogService()
					.saveEditBusinessLogByModule(getCurrentUser(), log, uttDB,
							beforeU);
		} else {
			this.addActionError("修改失败");
		}
		return redirectActionResult(LIST);
	}

	public String delete() {
		if (deleteIds != null && !"".equals(deleteIds)) {
			this.undertaketaskService.deleteSelected(deleteIds);
			this.addActionMessage("删除成功");
		} else {
			this.addActionMessage("删除失败");
		}
		for (String id : deleteIds.split(",")) {
			BusinessLog log = new BusinessLog();
			log.setBusinessName("承担课题情况");
			log.setPrimaryKey(id);
			BusinessLogContext
					.getInstance()
					.getBusinessLogService()
					.saveDelBusinessLogByModule(getCurrentUser(), log,
							new Undertaketask());
		}
		return redirectActionResult(LIST);
	}

	public String detail() {
		if (undertaketask != null && undertaketask.getUndertaketaskId() != null
				&& !"".equals(undertaketask.getUndertaketaskId())) {
			undertaketask = this.undertaketaskService.get(undertaketask
					.getUndertaketaskId());
			attachmentList = AttachmentContext.getInstance().getAttachmentService().getAttachmentsByHostId(undertaketask.getUndertaketaskId());
			if (undertaketask != null) {
				this.dealUserName(undertaketask);
				this.putToApplication("undertaketask", undertaketask);
			}
		}
		return SUCCESS;
	}

	public void dealUserList2Str(Undertaketask data) {
		if (data != null && data.getUndertaker() != null
				&& !"".equals(data.getUndertaker())) {
			StringBuilder userUndertabker = new StringBuilder("");
			int flag = 0;
			for (String uiId : data.getUndertaker().split(",")) {
				flag++;
				UserInfo ui = this.undertaketaskService.get(uiId.trim(),
						UserInfo.class);
				if (ui != null) {
					userUndertabker.append(ui.getName());
					if (flag < data.getUndertaker().split(",").length) {
						userUndertabker.append(",");
					}
				}
			}
			data.setUndertakerUserTxt(userUndertabker.toString());
		}
	}

	public String export() {
		List<Undertaketask> dataList = new ArrayList<Undertaketask>();
		dataList = this.undertaketaskService.getListPage(null, undertaketask,
				getCurrentUser().getOrgan(), null, false, null);
		if (dataList != null && dataList.size() > 0) {
			for (Undertaketask utt : dataList) {
				dealUserList2Str(utt);
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
		List<Undertaketask> undertaketaskList = undertaketaskService
				.getListPage(null, undertaketask,
						undertaketaskService.get(organId, Organ.class), null,
						false, null);
		putToRequest("undertaketaskList", undertaketaskList);
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
		resultData.put("id", "16");
		List<Undertaketask> undertaketaskList = undertaketaskService
				.getListPage(null, undertaketask, getCurrentUser().getOrgan(),
						null, false, null);
		String msg = dataValidateService.validateData("承担课题情况",
				undertaketaskList, "16");
		resultData.put("msg", msg);
		setResultData(resultData);
		return JSON;
	}

	/************************************************* getter && setter *********************************************************************/

	/**
	 * @return the undertaketaskService
	 */
	public UndertaketaskService getUndertaketaskService() {
		return undertaketaskService;
	}

	/**
	 * @param undertaketaskService
	 *            the undertaketaskService to set
	 */
	public void setUndertaketaskService(
			UndertaketaskService undertaketaskService) {
		this.undertaketaskService = undertaketaskService;
	}

	/**
	 * @return the undertaketask
	 */
	public Undertaketask getUndertaketask() {
		return undertaketask;
	}

	/**
	 * @param undertaketask
	 *            the undertaketask to set
	 */
	public void setUndertaketask(Undertaketask undertaketask) {
		this.undertaketask = undertaketask;
	}

	/**
	 * @return the deleteIds
	 */
	public String getDeleteIds() {
		return deleteIds;
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
	public List<String> getSecAttach() {
        return secAttach;
	}

	public void setSecAttach(List<String> secAttach) {
	        this.secAttach = secAttach;
	}

	public List<Attachment> getAttachmentList() {
		return attachmentList;
	}

	public void setAttachmentList(List<Attachment> attachmentList) {
		this.attachmentList = attachmentList;
	}

}
