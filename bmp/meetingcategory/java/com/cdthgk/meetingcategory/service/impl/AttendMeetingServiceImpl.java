package com.cdthgk.meetingcategory.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.meetingcategory.service.AttendMeetingService;
import com.cdthgk.meetingcategory.vo.AttendMeeting;
import com.cdthgk.meetingcategory.vo.Meeting;
import com.cdthgk.platform.attachment.service.AttachmentService;
import com.thgk.platform.syslog.service.invoke.imp.annotation.ExcuteType;
import com.thgk.platform.syslog.service.invoke.imp.annotation.SysLog;
import com.thgk.platform.syslog.service.invoke.imp.annotation.SysLogs;

import ec.common.PageSortModel;

/**
 * @description 会议资料Service实现类
 * @author huxj 2009 12 16 12:34:56
 * @modify 陈文聪 2010 8 18 07:59:47 修改注释格式
 */

@SysLogs(
		{
			@SysLog(method="update",methodParamsSize=1,description="修改办会资料{0}|attendMeetingName",excute_type=ExcuteType.UPDATE),
			@SysLog(method="deleteBatch",methodParamsSize=1,description="删除办会资料{0}|attendMeetingName",excute_type=ExcuteType.DELETE),
			@SysLog(method="save",methodParamsSize=1,description="添加办会资料{0}|attendMeetingName",excute_type=ExcuteType.ADD)

		}
)
@SuppressWarnings("all")
public class AttendMeetingServiceImpl extends BmpServiceImpl<AttendMeeting, String>
		implements AttendMeetingService {

	private AttachmentService attachementService;

	/**
	 * @description TODO
	 * @author huxj 2009 12 16 12:34:56
	 * @modify 陈文聪 2010 8 18 08:00:15 修改注释格式.
	 * @return List
	 */
	public List<Meeting> getAllMeeting() {
		return this.getPersistProxy().getOrmPersistence().findAll(Meeting.class);
	}

	/**
	 * @description TODO
	 * @author huxj 2009 12 16 12:34:56
	 * @modify 陈文聪 2010 8 18 08:00:22 修改注释格式.
	 * @return List<AttendMeeting>
	 */
	public List<AttendMeeting> getAllAttendMeeting() {
		return null;
	}

	/**
	 * @description TODO
	 * @author huxj 2009 12 16 12:34:56
	 * @modify 陈文聪 2010 8 18 08:00:40 修改注释格式.
	 * @param PageSortModel psm
	 * @param AttendMeeting attendMeeting
	 * @return List<AttendMeeting>
	 */
	@SuppressWarnings("unchecked")
	public List getList(PageSortModel psm, AttendMeeting attendMeeting) {

		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("FROM  AttendMeeting m ");
		StringBuilder conditions = new StringBuilder();

		if (attendMeeting!= null) {
			if(attendMeeting.getMeeting() != null && attendMeeting.getMeeting().getMeetingId()!=null){
				conditions.append(" m.meeting.meetingId=:meetingId");
				params.put("meetingId", attendMeeting.getMeeting().getMeetingId());
			}
			if(attendMeeting!=null && attendMeeting.getAttendMeetingName()!=null){
				conditions.append("  and m.attendMeetingName like "+"'%"+attendMeeting.getAttendMeetingName()+"%'");

			}
//			if(attendMeeting!=null && attendMeeting.getMeeting()!=null
//					&& attendMeeting.getMeeting().getMeetingName()!=null
//					&& !"".equals(attendMeeting.getMeeting().getMeetingName())){
//				conditions.append(" and m.meeting.meetingName like :meetingName");
//				params.put("meetingName", attendMeeting.getMeeting().getMeetingName());
//			}
		}

		if (conditions.length() > 0) {
			hql.append(" WHERE ").append(conditions.toString());
		}

		return findList(hql.toString(), params, psm);
	}

	// geter & seter

	public AttachmentService getAttachementService() {
		return attachementService;
	}

	public void setAttachementService(AttachmentService attachementService) {
		this.attachementService = attachementService;
	}

}
