package com.cdthgk.bmp.formatFile.action;

import java.util.List;

import com.cdthgk.bmp.core.action.BmpAction;
import com.cdthgk.bmp.formatFile.service.FormatFileService;
import com.cdthgk.bmp.formatFile.vo.FormatFile;
import com.cdthgk.common.lang.UUIDGenerator;
import com.cdthgk.platform.attachment.domain.Attachment;
import com.cdthgk.platform.attachment.service.AttachmentService;
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
	//查询本单位公文接收
	public String acceptList(){
		PageSortModel<FormatFile> psm = new PageSortModel<FormatFile>(getRequest(), "formatFileList");
		List<FormatFile> formatFileList = formatFileService.queryAcceptListPage(psm, formatFile, getCurrentUser().getUserInfo().getUserInfoId());
		putToRequest("formatFileList", formatFileList);
		return SUCCESS;
	}

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

		// TODO 注入一个bmp.fileStorage；通过该对象来获取存储路径。设置在formatFile对象中;附件名称乱码未处理；


		ProjectModuleDateDirStorage p = new ProjectModuleDateDirStorage();
		formatFileService.save(formatFile);
		return SUCCESS;
	}

	public String edit(){
		formatFile = formatFileService.get(formatFile.getId());
		return SUCCESS;
	}
	public String editing(){
		formatFileService.save(formatFile);
		return SUCCESS;
	}
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

	public String detail(){
		formatFile = formatFileService.get(formatFile.getId());
		return SUCCESS;
	}

	public String download(){
		attachment = attachmentService.getAttachmentsByHostId(formatFile.getId()).get(0);
		return "download";
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
}
