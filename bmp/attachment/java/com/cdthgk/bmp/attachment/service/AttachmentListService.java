package com.cdthgk.bmp.attachment.service;

import java.util.List;

import com.cdthgk.bmp.attachment.vo.AttachmentList;
import com.cdthgk.platform.base.service.GenericBasicService;

import ec.common.PageSortModel;

/**
 * @简介 附件接口
 * @创建人 杨成 2009-12-1 10:59
 * @修改人 彭 维 2010-08-17 18:43 整理注释
 */

@SuppressWarnings("unchecked")
public interface AttachmentListService extends GenericBasicService<AttachmentList, String>{

	public List<AttachmentList> queryAttachmentListPageList(PageSortModel psm, AttachmentList attachmentList);

	public void saveAttachementList( AttachmentList  attachmentList);

	public List<AttachmentList> indexList(PageSortModel<AttachmentList> psm);

	public List<AttachmentList> indexAll(AttachmentList attachmentList ,PageSortModel<AttachmentList> psm);

}
