package com.cdthgk.bmp.attachment.vo;

import java.util.Date;
import java.util.List;

import com.cdthgk.platform.attachment.domain.Attachment;
import com.cdthgk.platform.permission.user.domain.User;
import com.thgk.platform.core.BaseDomain;


/**
 * @简介 附件实体
 * @创建人 杨成 2009-12-1 10:59
 * @修改人 彭 维 2010-08-17 18:43 整理注释
 */

public class AttachmentList extends BaseDomain {

	private static final long serialVersionUID = 1L;

	private String attachmentListId;

	private String attachmentListName;

	private Integer attachmentListStatus;

	//仅供登录界面下载列表使用
	private List<Attachment> attachmentCollect;

	/**
	 * @return the attachmentListId
	 */
	public String getAttachmentListId() {
		return attachmentListId;
	}

	/**
	 * @param attachmentListId the attachmentListId to set
	 */
	public void setAttachmentListId(String attachmentListId) {
		this.attachmentListId = attachmentListId;
	}

	/**
	 * @return the attachmentListName
	 */
	public String getAttachmentListName() {
		return attachmentListName;
	}

	/**
	 * @param attachmentListName the attachmentListName to set
	 */
	public void setAttachmentListName(String attachmentListName) {
		this.attachmentListName = attachmentListName;
	}

	/**
	 * @return the attachmentListStatus
	 */
	public Integer getAttachmentListStatus() {
		return attachmentListStatus;
	}

	/**
	 * @param attachmentListStatus the attachmentListStatus to set
	 */
	public void setAttachmentListStatus(Integer attachmentListStatus) {
		this.attachmentListStatus = attachmentListStatus;
	}

	/**
	 * @return the attachmentCollect
	 */
	public List<Attachment> getAttachmentCollect() {
		return attachmentCollect;
	}

	/**
	 * @param attachmentCollect the attachmentCollect to set
	 */
	public void setAttachmentCollect(List<Attachment> attachmentCollect) {
		this.attachmentCollect = attachmentCollect;
	}

}