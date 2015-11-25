package com.cdthgk.bmp.formatFile.action;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.cdthgk.bmp.core.action.BmpAction;
import com.cdthgk.bmp.formatFile.service.FormatFileService;
import com.cdthgk.bmp.formatFile.vo.FormatFile;
import com.cdthgk.bmp.formatFile.vo.FormatFileUserInfo;
import com.cdthgk.common.lang.UUIDGenerator;
import com.cdthgk.platform.attachment.domain.Attachment;
import com.cdthgk.platform.attachment.service.AttachmentService;
import com.cdthgk.platform.organization.core.OrganizationContext;
import com.cdthgk.standard.file.storage.ProjectModuleDateDirStorage;
import com.cdthgk.web.upload.UploadFile;

import ec.common.PageSortModel;

public class FormatFileAction extends BmpAction {

	private static final long serialVersionUID = 1L;
	private String deleteIds;
	private FormatFile formatFile;
	private FormatFileService formatFileService;
	private AttachmentService attachmentService;
	// 上传
	private Attachment attachment;
	//上传附件
	private List<String> attachments;
	//上传规则
	private ProjectModuleDateDirStorage projectModuleDateDirStorage;
	//人员
	private String userInfoIds;


	//查询本单位公文发送
	public String list(){
		PageSortModel<FormatFile> psm = new PageSortModel<FormatFile>(getRequest(), "formatFileList");
		List<FormatFile> formatFileList = formatFileService.queryListPage(psm, formatFile, getCurrentUser().getOrgan());
		putToRequest("formatFileList", formatFileList);
		if(getRequest().getParameter("msg") != null){
			addActionMessage("保存成功");
		}
		return SUCCESS;
	}

	//上传
	public String add(){
		return SUCCESS;
	}
	public String adding(){
		String formatFileId = UUIDGenerator.generateUUID32();
		UploadFile uploadFile = this.getUploadFiles().get(0);
		Attachment attach = new Attachment(uploadFile);
		attach.setHostId(formatFileId);
		attachmentService.save(attach);
		formatFile.setId(formatFileId);
		formatFile.setOrgan(getCurrentUser().getOrgan());
		String path = projectModuleDateDirStorage.getBaseDir() + projectModuleDateDirStorage.getRelativeDir() + attach.getAttachUrl();
		formatFile.setFormatFileUrl(path.replaceAll("\\\\", "/"));
		formatFile.setStatus(0);
		formatFileService.save(formatFile);
		return SUCCESS;
	}

	//进入发送页面，选择人员
	public String send(){
		formatFile = formatFileService.get(formatFile.getId());
		return SUCCESS;
	}
	public String sending(){
		Set<FormatFileUserInfo> userInfoSet = new HashSet<FormatFileUserInfo>();
		String [] userInfoIdStr = userInfoIds.split(",");
		for (String userInfoId : userInfoIdStr) {
			FormatFileUserInfo formatFileUserInfo = new FormatFileUserInfo();
			formatFileUserInfo.setFormatFile(formatFile);
			formatFileUserInfo.setStatus(0);
			formatFileUserInfo.setUserInfo(OrganizationContext.getInstance().getMemberService().get(userInfoId));
			userInfoSet.add(formatFileUserInfo);
		}
		formatFile.setUserInfoSet(userInfoSet);
		formatFile.setStatus(1);
		formatFileService.update(formatFile);
		return SUCCESS;
	}

	//删除
	public String delete(){
		String[] idArray = getDeleteIds().split(",");
		if(idArray!=null){
			for(String id : idArray){
				formatFileService.delete(id);
			}
		}
		addActionMessage("删除成功。");
		return SUCCESS;
	}

	//详情
	public String detail(){
		formatFile = formatFileService.get(formatFile.getId());
		return SUCCESS;
	}

	//查看发送人员
	public String viewUserInfo(){
		formatFile = formatFileService.get(formatFile.getId());
		Set<FormatFileUserInfo> formatFileUserInfoSet = formatFile.getUserInfoSet();
		putToRequest("formatFile", formatFile);
		putToRequest("formatFileUserInfoSet", formatFileUserInfoSet);
		return SUCCESS;
	}

	/************************       接收          ************************************/
	//查询本单位公文接收
	public String acceptList(){
		PageSortModel<FormatFile> psm = new PageSortModel<FormatFile>(getRequest(), "formatFileList");
		List<FormatFile> formatFileList = formatFileService.queryAcceptListPage(psm, formatFile, getCurrentUser().getUserInfo().getUserInfoId());
		putToRequest("formatFileList", formatFileList);
		return SUCCESS;
	}
	//查看
	public String acceptView(){
		//页面传入 两张表ID;formatFileId  userInfoId 表示唯一
		formatFile = formatFileService.get(formatFile.getId());
		String userInfoId = getRequest().getParameter("userInfoId").toString();
		formatFileService.updateFormatFileUserInfo(formatFile, userInfoId);
		putToRequest("formatFile", formatFile);
		return SUCCESS;
	}

	public FormatFile getFormatFile() {
		return formatFile;
	}

	public void setFormatFile(FormatFile formatFile) {
		this.formatFile = formatFile;
	}

	public void setFormatFileService(FormatFileService formatFileService) {
		this.formatFileService = formatFileService;
	}

	public String getDeleteIds() {
		return deleteIds;
	}

	public void setDeleteIds(String deleteIds) {
		this.deleteIds = deleteIds;
	}

	public Attachment getAttachment() {
		return attachment;
	}

	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}

	public List<String> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<String> attachments) {
		this.attachments = attachments;
	}

	public void setAttachmentService(AttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}
	public void setProjectModuleDateDirStorage(
			ProjectModuleDateDirStorage projectModuleDateDirStorage) {
		this.projectModuleDateDirStorage = projectModuleDateDirStorage;
	}
	public String getUserInfoIds() {
		return userInfoIds;
	}
	public void setUserInfoIds(String userInfoIds) {
		this.userInfoIds = userInfoIds;
	}
}
