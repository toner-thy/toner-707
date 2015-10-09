package com.cdthgk.bmp.notice.action;

import java.util.Date;
import java.util.List;

import com.cdthgk.bmp.notice.service.NoticeService;
import com.cdthgk.bmp.notice.vo.BmSystemNotice;
import com.cdthgk.platform.base.action.PlatformAction;

import ec.common.PageSortModel;

public class NoticeAction extends PlatformAction {

	private static final long serialVersionUID = 1L;

	private NoticeService noticeService;

	private BmSystemNotice notice;

	private Boolean needReload = false;

	private String startTime;
	private String endTime;


//	private ImsService imsService;

	public String list(){
		// 获取list
		PageSortModel<BmSystemNotice> psm = new PageSortModel<BmSystemNotice>(getRequest(), "noticeList");
		putToRequest("noticeList", noticeService.getListPage(psm, notice, startTime, endTime));
		return "success";
	}

	public String indexList(){
		PageSortModel<BmSystemNotice> psm = new PageSortModel<BmSystemNotice>(getRequest(), "noticeList");
		psm.setPageSize(7);
		psm.setPageNumber(1);
        List<BmSystemNotice> noticeList = noticeService.indexList(psm);
		putToRequest("noticeList", noticeList);
		return "success";
	}

	public String indexAll(){
		PageSortModel<BmSystemNotice> psm = new PageSortModel<BmSystemNotice>(getRequest(), "noticeList");
		putToRequest("noticeList", noticeService.indexAll(psm));
		return "success";
	}

	public String detail(){
		String id = getRequest().getParameter("id");
		notice = noticeService.get(id);
		return "success";
	}

	public String add(){
		return "success";
	}

	public String adding(){
		notice.setCreatePerson(getCurrentUser());
		notice.setCreateTime(new Date());
		this.noticeService.save(notice);
		this.setNeedReload(true);
//IMS发送消息测试
//		ImsMessageDto imsDto = new ImsMessageDto();
//		imsDto.setTitle("测试TITLE");
//		imsDto.setContent("测试CONTENT");
//		List<User> userList = new ArrayList<User>();
//		userList.add(getCurrentUser());
//		imsDto.setUserList(userList);
//		imsDto.setMsgPushTime(new Date());
//		imsDto.setPublisher(getCurrentUser().getUserInfo().getName());
//		imsDto.setValidity(new Date());
//		ImsMessageUtil.sendImsMessage(imsDto, getRequest());
		return "success";
	}

	public String edit(){
		String id = getRequest().getParameter("id");
		if( id!=null && !"".equals(id) ){
			notice = noticeService.get(id);
		}else{
			addActionMessage("未找到相关记录");
			return "failure";
		}
		return "success";
	}

	public String editing(){
		if( notice!=null && notice.getNoticeId()!=null && !"".equals(notice.getNoticeId()) ){
			notice.setModifyPerson(getCurrentUser());
			notice.setModifyTime(new Date());
			noticeService.update(notice);
			addActionMessage("修改成功");
			this.setNeedReload(true);
			return "success";
		}else{
			addActionMessage("修改失败");
			return "edit";
		}
	}

	public String delete(){
		String deleteIds = getRequest().getParameter("deleteIds");
		this.noticeService.deleteBatchIds(deleteIds);
		addActionMessage("删除成功");
		return "success";
	}







	/*------------------get & set----------------*/
	/**
	 * @return the noticeService
	 */
	public NoticeService getNoticeService() {
		return noticeService;
	}

	/**
	 * @param noticeService the noticeService to set
	 */
	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}


	/**
	 * @return the notice
	 */
	public BmSystemNotice getNotice() {
		return notice;
	}


	/**
	 * @param notice the notice to set
	 */
	public void setNotice(BmSystemNotice notice) {
		this.notice = notice;
	}


	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}


	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}


	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}


	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
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
}
