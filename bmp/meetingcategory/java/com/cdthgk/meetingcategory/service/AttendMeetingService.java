package com.cdthgk.meetingcategory.service;

import java.util.List;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.meetingcategory.vo.AttendMeeting;

import ec.common.PageSortModel;

/**
 * @description 会议资料Service
 * @author huxj 2009 12 16 12:34:56
 * @modify 陈文聪 2010 8 18 07:59:47 修改注释格式
 */
@SuppressWarnings("all")
public interface AttendMeetingService extends BmpServiceTemplate<AttendMeeting, String>{

	/**
	 * @description TODO
	 * @author null null 12:34:56
	 * @modify 陈文聪 2010 8 18 08:00:15 修改注释格式.
	 * @return List
	 */
	List getAllMeeting();

	/**
	 * @description TODO
	 * @author null null 12:34:56
	 * @modify 陈文聪 2010 8 18 08:00:22 修改注释格式.
	 * @return List<AttendMeeting>
	 */
	List<AttendMeeting> getAllAttendMeeting();

	/**
	 * @description TODO
	 * @author null null 12:34:56
	 * @modify 陈文聪 2010 8 18 08:00:40 修改注释格式.
	 * @param PageSortModel psm
	 * @param AttendMeeting attendMeeting
	 * @return List<AttendMeeting>
	 */
	List<AttendMeeting> getList(PageSortModel psm, AttendMeeting attendMeeting);
}
