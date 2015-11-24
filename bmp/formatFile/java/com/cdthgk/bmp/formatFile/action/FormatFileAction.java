package com.cdthgk.bmp.formatFile.action;

import java.util.List;

import com.cdthgk.bmp.core.action.BmpAction;
import com.cdthgk.bmp.formatFile.service.FormatFileService;
import com.cdthgk.bmp.formatFile.vo.FormatFile;
import com.cdthgk.platform.attachment.domain.Attachment;
import com.cdthgk.platform.attachment.service.AttachmentService;

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
		if(attachments!=null && attachments.size()>0){
			formatFile.setOrgan(getCurrentUser().getOrgan());
			formatFileService.save(formatFile);
			// 上传附件
			attachmentService.updateHostId(formatFile.getId(), attachments);
			addActionMessage("上传成功。");
			return SUCCESS;
		}else{
			addActionMessage("上传失败，请重新上传。");
			return SUCCESS;
		}
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
