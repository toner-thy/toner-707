package com.cdthgk.bmp.attachment.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.attachment.dto.AttachmentQueryDto;
import com.cdthgk.bmp.attachment.service.AttachmentQueryService;
import com.cdthgk.bmp.attachment.vo.AttachmentList;
import com.cdthgk.platform.attachment.domain.Attachment;
import com.cdthgk.platform.base.service.GenericServiceTemplate;

import ec.common.PageSortModel;

public class AttachmentQueryServiceImpl extends GenericServiceTemplate<Attachment, String> implements AttachmentQueryService {

	@Override
	public List<Attachment> getPageList(PageSortModel psm, AttachmentQueryDto attachmentQueryDto, AttachmentList attachmentList ) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("select a from Attachment as a, AttachmentList as al, Organ as o, User as u ");
		hql.append("where a.status = '1' and a.hostId = al.attachmentListId and a.userId = u.userId and a.organId = o.organId ");
		Map<String, Object> params = new HashMap<String, Object>();
		if( attachmentList!=null && attachmentList.getAttachmentListId()!=null && !"".equals(attachmentList.getAttachmentListId()) ){
			hql.append(" and al.attachmentListId = :attachmentListId ");
			params.put("attachmentListId", attachmentList.getAttachmentListId());
		}

		if(attachmentQueryDto!=null ){
			if( attachmentQueryDto.getAttachmentName()!=null && !"".equals(attachmentQueryDto.getAttachmentName()) ){
				hql.append(" and a.attachName like :attachName ");
				params.put("attachName", "%"+ attachmentQueryDto.getAttachmentName() +"%");
			}

			if( attachmentQueryDto.getOrgan()!=null && attachmentQueryDto.getOrgan().getOrganName()!=null && !"".equals(attachmentQueryDto.getOrgan().getOrganName()) ){
				//Organ organ = OrganizationContext.getInstance().getOrganService().getByName(attachmentQueryDto.getOrgan().getOrganName());
				hql.append(" and o.organName like :organName ");
				params.put("organName", "%"+ attachmentQueryDto.getOrgan().getOrganName() +"%");
			}

			if( attachmentQueryDto.getUser()!=null && attachmentQueryDto.getUser().getUserName()!=null && !"".equals(attachmentQueryDto.getUser().getUserName()) ){
				hql.append(" and u.userName like :userName ");
				params.put("userName", "%"+attachmentQueryDto.getUser().getUserName()+"%");
			}

			if( attachmentQueryDto.getStartTime()!=null && !"".equals(attachmentQueryDto.getStartTime()) ){
				hql.append(" and a.createTime >= :startDate ");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					Date d = sdf.parse(attachmentQueryDto.getStartTime());
					params.put("startDate", d);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if( attachmentQueryDto.getEndTime()!=null && !"".equals(attachmentQueryDto.getEndTime()) ){
				hql.append(" and a.createTime <= :endDate ");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					Date d = sdf.parse(attachmentQueryDto.getEndTime());
					params.put("endDate", d);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		List<Attachment> aList = this.findList(hql.toString(), params, psm);
		return aList;
	}

}
