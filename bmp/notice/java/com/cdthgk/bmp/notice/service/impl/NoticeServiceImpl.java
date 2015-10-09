package com.cdthgk.bmp.notice.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.notice.service.NoticeService;
import com.cdthgk.bmp.notice.vo.BmSystemNotice;
import com.cdthgk.platform.base.service.GenericServiceTemplate;

import ec.common.PageSortModel;

public class NoticeServiceImpl extends GenericServiceTemplate<BmSystemNotice, String> implements
		NoticeService {

	public List<BmSystemNotice> getListPage(PageSortModel<BmSystemNotice> psm, BmSystemNotice notice, String startTime, String endTime){
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("FROM BmSystemNotice n WHERE 1 = 1");
		//按标题查询
		if(notice!=null && notice.getNoticeName()!=null && !"".equals(notice.getNoticeName())){
			hql.append(" and n.noticeName like :noticeName ");
			params.put("noticeName", "%" + notice.getNoticeName() + "%");
		}
		//按发布时间查询
		if( startTime!=null && !"".equals(startTime) ){
			hql.append(" and n.noticePublishDate >= :startDate ");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date d = sdf.parse(startTime);
				params.put("startDate", d);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if( startTime!=null && !"".equals(startTime) ){
			hql.append(" and n.noticePublishDate <= :endDate ");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date d = sdf.parse(endTime);
				params.put("endDate", d);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// 按创建时间降序排列
		hql.append(" order by n.noticePublishDate desc");
		//List<SystemNotice> list =(List<SystemNotice>) this.getList(hql.toString(), psm, params);
		List<BmSystemNotice> list =(List<BmSystemNotice>) this.findList(hql.toString(), params, psm);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.cdthgk.bmp.notice.service.NoticeService#indexList(ec.common.PageSortModel)
	 */
	@Override
	public List<BmSystemNotice> indexList(PageSortModel<BmSystemNotice> psm) {
		// TODO Auto-generated method stub
		String hql = "FROM BmSystemNotice sn ORDER BY sn.noticePublishDate desc";
        List<BmSystemNotice> noticeList = this.getList(hql, psm, null);
		return noticeList;
	}

	/* (non-Javadoc)
	 * @see com.cdthgk.bmp.notice.service.NoticeService#indexAll(ec.common.PageSortModel)
	 */
	@Override
	public List<BmSystemNotice> indexAll(PageSortModel<BmSystemNotice> psm) {
		// TODO Auto-generated method stub
		String hql = "FROM BmSystemNotice sn ORDER BY sn.noticePublishDate desc";
		List<BmSystemNotice> noticeList = this.getList(hql, psm, null);;
		return noticeList;
	}

}
