package com.cdthgk.bmp.attachment.service;

import java.util.Date;
import java.util.List;

import com.cdthgk.bmp.attachment.dto.AttachmentQueryDto;
import com.cdthgk.bmp.attachment.vo.AttachmentList;
import com.cdthgk.platform.attachment.domain.Attachment;
import com.cdthgk.platform.base.service.GenericBasicService;

import ec.common.PageSortModel;

public interface AttachmentQueryService  extends GenericBasicService<Attachment, String> {
	public List<Attachment> getPageList(PageSortModel psm, AttachmentQueryDto attachmentQueryDto, AttachmentList attachmentList);
}
