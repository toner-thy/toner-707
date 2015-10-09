package com.cdthgk.bmp.attachment.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.attachment.service.AttachmentListService;
import com.cdthgk.bmp.attachment.vo.AttachmentList;
import com.cdthgk.platform.attachment.domain.Attachment;
import com.cdthgk.platform.base.service.GenericServiceTemplate;

import ec.common.PageSortModel;

/**
 * @简介 附件接口实现
 * @创建人 杨成 2009-12-1 10:59
 * @修改人 彭 维 2010-08-17 18:43 整理注释
 */

@SuppressWarnings("unchecked")
public class AttachmentListServiceImpl extends GenericServiceTemplate<AttachmentList, String> implements AttachmentListService {

	@Override
	public List<AttachmentList> queryAttachmentListPageList(PageSortModel psm, AttachmentList attachmentList) {
		StringBuffer hql = new StringBuffer("from AttachmentList as al where 1=1 ");
		Map<String, Object> params = new HashMap<String, Object>();
		if( attachmentList!=null ){
			if( attachmentList.getCreatePerson()!=null ){
				hql.append(" and al.createPerson.userId = :userId  ");
				params.put("userId", attachmentList.getCreatePerson().getUserId());
			}
			if( attachmentList.getAttachmentListName()!=null && !"".equals(attachmentList.getAttachmentListName()) ){
				hql.append(" and al.attachmentListName like :attachmentList ");
				params.put("attachmentList", "%"+attachmentList.getAttachmentListName()+"%");
			}
		}
		List<AttachmentList> attachmentListAll = this.findList(hql.toString(), params, psm);
		return attachmentListAll;
	}

	@Override
	public void saveAttachementList(AttachmentList attachmentList) {
		// TODO Auto-generated method stub
		super.save(attachmentList);
	}

	/* (non-Javadoc)
	 * @see com.cdthgk.bmp.attachment.service.AttachmentListService#indexList(java.lang.String, ec.common.PageSortModel)
	 */
	@Override
	public List<AttachmentList> indexList(PageSortModel<AttachmentList> psm) {
		// TODO Auto-generated method stub
		String hql = "FROM AttachmentList al ORDER BY al.createTime desc";
		List<AttachmentList> attList = this.getList(hql, psm, null);

		return attList;
	}

	/* (non-Javadoc)
	 * @see com.cdthgk.bmp.attachment.service.AttachmentListService#indexAll(ec.common.PageSortModel)
	 */
	@Override
	public List<AttachmentList> indexAll(AttachmentList attachmentList, PageSortModel<AttachmentList> psm) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM AttachmentList al where 1=1 ");
		if( attachmentList!=null && attachmentList.getAttachmentListName()!=null && !"".equals(attachmentList.getAttachmentListName()) ){
			hql.append(" AND al.attachmentListName like :attachmentListName ");
			params.put("attachmentListName", "%"+attachmentList.getAttachmentListName()+"%");
		}
		hql.append("ORDER BY al.createTime desc");
		List<AttachmentList> attList = this.findList(hql.toString(), params, psm);

		return attList;
	}



}
