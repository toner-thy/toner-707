package com.cdthgk.bmp.feedbackInfo.action;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.cdthgk.bmp.feedbackInfo.service.FeedbackInfoService;
import com.cdthgk.bmp.feedbackInfo.vo.FeedbackInfo;
import com.cdthgk.platform.attachment.domain.Attachment;
import com.cdthgk.platform.attachment.service.AttachmentService;
import com.cdthgk.platform.base.action.PlatformAction;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

/**
 * @description 意见反馈
 * @author 牟远洋 2013 05 078 10:14:56
 */
public class FeedbackInfoAction extends PlatformAction {

	private static final long serialVersionUID = 1L;
	private FeedbackInfo feedbackInfo;
	private List<FeedbackInfo> feedbackInfoList;
	private FeedbackInfoService feedbackInfoService;
	// 上传
	private Attachment attachment;
	private List<Attachment> attachmentList;
	//private UpLoadedFiles upLoadFiles;
	private AttachmentService attachementService;

	private List<String> attachmentIds;

	private String feedbackIds;

	private Boolean needReload = false;

	/**
	 * @description 列表
	 * @author 牟远洋 2013 05 078 10:14:56
	 */
	public String list(){
		PageSortModel<FeedbackInfo> psm = new PageSortModel<FeedbackInfo>(getRequest(),"feedbackInfoList");
		// 返回信息列表
		feedbackInfoList = feedbackInfoService.getFeedbackInfoList(psm, feedbackInfo, null);

		return "list";
	}

	/**
	 * @description 列表
	 * @author 牟远洋 2013 05 078 10:14:56
	 */
	public String userList(){
		PageSortModel<FeedbackInfo> psm = new PageSortModel<FeedbackInfo>(getRequest(),"feedbackInfoList");
		User user = getCurrentUser();
		// 返回信息列表
		feedbackInfoList = feedbackInfoService.getFeedbackInfoList(psm, feedbackInfo, user);

		return "list";
	}

	/**
	 * @description 新增之前
	 * @author 牟远洋 2013 05 07 10:30:56
	 */
	public String add() {
		return "add";
	}

	/**
	 * @description 新增
	 * @author 牟远洋 2013 05 07 10:31:56
	 */
	public String save() {
		boolean flag = false;
		FeedbackInfo feedback = new FeedbackInfo();
		// 设置属性字段
		feedback.setFeedbackTitle(feedbackInfo.getFeedbackTitle());
		feedback.setFeedbackType(feedbackInfo.getFeedbackType());
		feedback.setStatus(0);
		feedback.setContent(feedbackInfo.getContent());
		feedback.setCreatePerson(getCurrentUser());
		feedback.setOrgan(getCurrentUser().getOrgan());
		try {
			// 保存实体
			feedbackInfoService.save(feedback);
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		attachementService.updateHostId(feedback.getFeedbackInfoId(), this.getAttachmentIds());
		// 编辑时上传附件
		/*if (upLoadFiles != null && flag == true) {
			List<UploadFile> uploadFileList = upLoadFiles.getUpLoadFiles();

			try {
				// 保存附件
				attachementService.saveUploadFileList(uploadFileList,feedback.getFeedbackInfoId()
						, new FTPFileConfig(GlobalSysInfo.getCurrentUser().getOrgan().getOrganId())
						, GlobalSysInfo.getCurrentUser());
			} catch (Exception e) {
				flag = false;
			}
		}*/

		addActionMessage(flag ? "新增反馈信息成功!": "新增反馈信息失败!");
		needReload = true;

		return redirectActionResult(LIST);
	}

	/**
	 * @description 下载附件
	 * @author 牟远洋 2013 5 07 10:34:56
	 * @throws ParseException
	 */
	public String download() throws ParseException {
		this.attachment = (Attachment) feedbackInfoService.get(
				attachment.getAttachId(), Attachment.class);
		putToRequest("attachment", attachment);
		return "ftpDown";
	}

	/**
	 * @description 编辑之前
	 * @author 牟远洋 2013 05 07 10:30:56
	 */
	public String edit() {
		// 检查
		if(feedbackInfo == null || feedbackInfo.getFeedbackInfoId() == null || feedbackInfo.getFeedbackInfoId().equals("")){
			this.addActionMessage("您所访问的资源Id不存在，请确认后重试。");
			return this.redirectActionResult("list");
		}
		feedbackInfo = feedbackInfoService.get(feedbackInfo.getFeedbackInfoId());
		return "edit";
	}

	/**
	 * @description 编辑
	 * @author 牟远洋 2013 05 078 10:30:56
	 */
	public String update() {
		boolean flag = false;
		// 创建更新实体
		FeedbackInfo feedback = feedbackInfoService.get(feedbackInfo.getFeedbackInfoId());

		// 设置属性字段
		feedback.setFeedbackTitle(feedbackInfo.getFeedbackTitle());
		feedback.setStatus(feedbackInfo.getStatus());
		feedback.setFeedbackType(feedbackInfo.getFeedbackType());
		feedback.setContent(feedbackInfo.getContent());
		feedback.setModifyPerson(getCurrentUser());
		feedback.setModifyTime(new Date());

		// 更新实体
		try {
			feedbackInfoService.update(feedback);
			this.putToRequest("feedbackInfo", feedback);
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		/*// 若附件存在,则保存附件
		if (upLoadFiles != null) {
			List<UploadFile> uploadFileList = upLoadFiles.getUpLoadFiles();
			try {
				attachementService.saveUploadFileList(uploadFileList,feedbackInfo.getFeedbackInfoId()
						, new FTPFileConfig(GlobalSysInfo.getCurrentUser().getOrgan().getOrganId())
						,GlobalSysInfo.getCurrentUser());
				flag = true;
			} catch (Exception e) {
				flag = false;
			}
		}*/
		// 设置信息
		this.addActionMessage(flag ? "编辑反馈信息成功": "编辑反馈信息失败");
		needReload = true;

		return redirectActionResult(LIST);
	}

	/**
	 * 删除
	 * @author 牟远洋 2012 09 18 16:30:56
	 */
	public String delete() {
		feedbackInfoService.deleteBatchIds(feedbackIds);
		this.addActionMessage("删除反馈信息成功");
		return SUCCESS;
	}

	/**
	 * 详情页面
	 * @author 牟远洋 2012 09 18 18:30:56
	 */
	public String detail() {
		// 检查
		if(feedbackInfo == null || feedbackInfo.getFeedbackInfoId() == null || feedbackInfo.getFeedbackInfoId().equals("")){
			this.addActionMessage("您所访问的资源Id不存在，请确认后重试。");
			return this.redirectActionResult("list");
		}
		feedbackInfo = feedbackInfoService.get(feedbackInfo.getFeedbackInfoId());
		return "detail";
	}

	/**
	 * 返回feedbackInfo
	 * @return feedbackInfo
	 */
	public FeedbackInfo getFeedbackInfo() {
		return feedbackInfo;
	}

	/**
	 * 设置feedbackInfo
	 * @param feedbackInfo feedbackInfo
	 */
	public void setFeedbackInfo(FeedbackInfo feedbackInfo) {
		this.feedbackInfo = feedbackInfo;
	}

	/**
	 * 返回feedbackInfoList
	 * @return feedbackInfoList
	 */
	public List<FeedbackInfo> getFeedbackInfoList() {
		return feedbackInfoList;
	}

	/**
	 * 设置feedbackInfoList
	 * @param feedbackInfoList feedbackInfoList
	 */
	public void setFeedbackInfoList(List<FeedbackInfo> feedbackInfoList) {
		this.feedbackInfoList = feedbackInfoList;
	}

	/**
	 * 返回feedbackInfoService
	 * @return feedbackInfoService
	 */
	public FeedbackInfoService getFeedbackInfoService() {
		return feedbackInfoService;
	}

	/**
	 * 设置feedbackInfoService
	 * @param feedbackInfoService feedbackInfoService
	 */
	public void setFeedbackInfoService(FeedbackInfoService feedbackInfoService) {
		this.feedbackInfoService = feedbackInfoService;
	}

	/**
	 * 返回attachment
	 * @return attachment
	 */
	public Attachment getAttachment() {
		return attachment;
	}

	/**
	 * 设置attachment
	 * @param attachment attachment
	 */
	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}

	/**
	 * 返回attachmentList
	 * @return attachmentList
	 */
	public List<Attachment> getAttachmentList() {
		return attachmentList;
	}

	/**
	 * 设置attachmentList
	 * @param attachmentList attachmentList
	 */
	public void setAttachmentList(List<Attachment> attachmentList) {
		this.attachmentList = attachmentList;
	}

	/**
	 * 返回attachementService
	 * @return attachementService
	 */
	public AttachmentService getAttachementService() {
		return attachementService;
	}

	/**
	 * 设置attachementService
	 * @param attachementService attachementService
	 */
	public void setAttachementService(AttachmentService attachementService) {
		this.attachementService = attachementService;
	}

	/**
	 * 返回feedbackIds
	 * @return feedbackIds
	 */
	public String getFeedbackIds() {
		return feedbackIds;
	}

	/**
	 * 设置feedbackIds
	 * @param feedbackIds feedbackIds
	 */
	public void setFeedbackIds(String feedbackIds) {
		this.feedbackIds = feedbackIds;
	}

	/**
	 * @return the needReload
	 */
	public Boolean getNeedReload() {
		return needReload;
	}

	/**
	 * @param needReload the needReload to set
	 */
	public void setNeedReload(Boolean needReload) {
		this.needReload = needReload;
	}

	/**
	 * @return the attachmentIds
	 */
	public List<String> getAttachmentIds() {
		return attachmentIds;
	}

	/**
	 * @param attachmentIds the attachmentIds to set
	 */
	public void setAttachmentIds(List<String> attachmentIds) {
		this.attachmentIds = attachmentIds;
	}


}
