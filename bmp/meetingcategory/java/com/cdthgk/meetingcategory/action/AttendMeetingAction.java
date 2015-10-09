package com.cdthgk.meetingcategory.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.cdthgk.meetingcategory.service.AttendMeetingService;
import com.cdthgk.meetingcategory.vo.AttendMeeting;
import com.cdthgk.meetingcategory.vo.Meeting;
import com.cdthgk.platform.attachment.context.AttachmentContext;
import com.cdthgk.platform.attachment.domain.Attachment;
import com.cdthgk.platform.base.action.PlatformAction;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

/**
 * @description 会议资料Action
 * @author null null 12:34:56
 * @modify 陈文聪 2010 8 18 07:38:11 修改注释格式
 */
@SuppressWarnings("all")
public class AttendMeetingAction extends PlatformAction {
	private static final long serialVersionUID = 1L;
	private AttendMeetingService attendMeetingService;
	private Attachment attachment;
	private AttendMeeting attendMeeting;
	List<Attachment> attachmentList;
	private Meeting meeting;
	// methods

	/**
	 * @description 添加前
	 * @author null null 12:34:56
	 * @modify 陈文聪 2010 8 18 07:48:38 修改注释格式.
	 */
	public String add() {
		return ADD;
	}

	/**
	 * @description 添加
	 * @author null null 12:34:56
	 * @modify 陈文聪 2010 8 18 07:48:54 修改注释格式.
	 */
	private List<String> secAttach;
        public List<String> getSecAttach() {
                return secAttach;
        }

        public void setSecAttach(List<String> secAttach) {
                this.secAttach = secAttach;
        }
	public String save() {
		attendMeeting.setCommitInfoTime(new Date());
		User user = getCurrentUser();
		attendMeeting.setDepartment(user.getUserInfo().getDepartment());
		attendMeeting.setOrgan(user.getUserInfo().getDepartment().getOrgan());
		attendMeeting.setUserInfo(user.getUserInfo());
		attendMeetingService.save(attendMeeting);
		AttachmentContext.getInstance().getAttachmentService().updateHostId(attendMeeting.getAttendMeetingId(),secAttach);
		addActionMessage("新增会议资料成功。");
		return "mylist_action";
	}

	/**
	 * @description TODO
	 * @author null null 12:34:56
	 * @modify 陈文聪 2010 8 18 07:49:18 修改注释格式.
	 */
	@SuppressWarnings("unchecked")
	public String edit() {
		setAttendMeeting(attendMeetingService.get(attendMeeting.getAttendMeetingId(), AttendMeeting.class));
		attachmentList =AttachmentContext.getInstance().getAttachmentService().getAttachmentsByHostId(attendMeeting.getAttendMeetingId());
		return EDIT;
	}
	/**
	 * @description TODO
	 * @author null null 12:34:56
	 * @modify 陈文聪 2010 8 18 07:49:56 修改注释格式.
	 */
	public String update() {
		AttendMeeting am = attendMeetingService.get(attendMeeting.getAttendMeetingId(), AttendMeeting.class);
		am.setCommitInfoTime(new Date());
		am.setDepartment(getCurrentUser().getUserInfo().getDepartment());
		am.setUserInfo(getCurrentUser().getUserInfo());
		am.setOrgan(getCurrentUser().getUserInfo().getDepartment().getOrgan());
		am.setAttendMeetingContent(attendMeeting.getAttendMeetingContent());
		am.setAttendMeetingName(attendMeeting.getAttendMeetingName());

		attendMeetingService.update(am);
		AttachmentContext.getInstance().getAttachmentService().updateHostId(attendMeeting.getAttendMeetingId(),secAttach);
		addActionMessage("编辑会议资料成功。");
		return "mylist_action";
	}
	/**
	 * @description TODO
	 * @author null null 12:34:56
	 * @modify 陈文聪 2010 8 18 07:50:07 修改注释格式.
	 */
	@SuppressWarnings("unchecked")
	public String delete() {
		List snList = new ArrayList();
		for (int i = 0; i < this.getIds().size(); i++) {
			AttendMeeting am = attendMeetingService.get(this.getIds().get(i), AttendMeeting.class);
			snList.add(am);
		}
		attendMeetingService.deleteBatch(snList);
		addActionMessage("删除会议资料成功。");
		return "mylist_action";
	}

	/**
	 * @description TODO
	 * @author null null 12:34:56
	 * @modify 陈文聪 2010 8 18 07:51:12 修改注释格式.
	 */
	public String list() {
		String id = "allattendMeetingList";
		PageSortModel psm = new PageSortModel(getRequest(), id);
		Meeting mm = attendMeetingService.get(attendMeeting.getMeeting().getMeetingId(), Meeting.class);
		attendMeeting.setMeeting(mm);
		ServletActionContext.getContext().put("allattendMeetingList", attendMeetingService.getList(psm, attendMeeting));
		if (null != this.getRequest().getParameter("myListFlag") && !"".equals(this.getRequest().getParameter("myListFlag"))) {
			this.putToRequest("myListFlag", "myListFlag");
			return "mylist";
		}
		return LIST;
	}

	/**
	 * @description TODO
	 * @author null null 12:34:56
	 * @modify 陈文聪 2010 8 18 07:51:30 修改注释格式.
	 */
	public String myList() {
		String id = "allattendMeetingList";
		PageSortModel psm = new PageSortModel(getRequest(), id);
		Meeting mm = null;
		if(attendMeeting!=null){
			if(attendMeeting.getMeeting()!=null){
				if(!attendMeeting.getMeeting().getMeetingId().equals("")){
					mm = attendMeetingService.get(attendMeeting.getMeeting().getMeetingId(), Meeting.class);
				}
			}
		}
		attendMeeting.setMeeting(mm);
		ServletActionContext.getContext().put("allattendMeetingList", attendMeetingService.getList(psm, attendMeeting));
		this.putToRequest("myListFlag", "myListFlag");
		return "mylist";
	}

	/**
	 * @description 详情
	 * @author null null 12:34:56
	 * @modify 陈文聪 2010 8 18 07:51:46 修改注释格式.
	 */
	public String view() {
		setAttendMeeting(attendMeetingService.get(attendMeeting .getAttendMeetingId(), AttendMeeting.class));
		attachmentList = AttachmentContext.getInstance().getAttachmentService().getAttachmentsByHostId(attendMeeting.getAttendMeetingId());
		this.putToRequest("meetingId", attendMeeting.getMeeting().getMeetingId());
		this.putToRequest("myListFlag", this.getRequest().getParameter("myListFlag"));
		return "view";
	}

	// geter & seter

	public Attachment getAttachment() {
		return attachment;
	}

	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}
	public AttendMeeting getAttendMeeting() {
		return attendMeeting;
	}

	public void setAttendMeeting(AttendMeeting attendMeeting) {
		this.attendMeeting = attendMeeting;
	}

	public List<Attachment> getAttachmentList() {
		return attachmentList;
	}

	public void setAttachmentList(List<Attachment> attachmentList) {
		this.attachmentList = attachmentList;
	}
	public AttendMeetingService getAttendMeetingService() {
		return attendMeetingService;
	}

	public void setAttendMeetingService(
			AttendMeetingService attendMeetingService) {
		this.attendMeetingService = attendMeetingService;
	}

	public Meeting getMeeting() {
		return meeting;
	}

	public void setMeeting(Meeting meeting) {
		this.meeting = meeting;
	}
}
