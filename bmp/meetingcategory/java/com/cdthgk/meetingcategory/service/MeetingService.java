package com.cdthgk.meetingcategory.service;

import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.meetingcategory.vo.AttendMeeting;
import com.cdthgk.meetingcategory.vo.Meeting;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;

/**
 * @description 会议管理Service
 * @author huxj 2009 12 16 12:34:56
 * @modify 陈文聪 2010 8 18 07:57:50 修改注释格式
 */
@SuppressWarnings("all")
public interface MeetingService extends BmpServiceTemplate<Meeting, String>{

	/**
	 * @description TODO
	 * @author huxj 2009 12 16 12:34:56
	 * @modify 陈文聪 2010 8 18 07:58:09 修改注释格式.
	 * @return List
	 */
	List getAllMeeting();

	/**
	 * @description TODO
	 * @author huxj 2009 12 16 12:34:56
	 * @modify 陈文聪 2010 8 18 07:58:19 修改注释格式.
	 * @return List<AttendMeeting>
	 */
	List<AttendMeeting> getAllAttendMeeting();

	/**
	 * @description TODO
	 * @author huxj 2009 12 16 12:34:56
	 * @modify 陈文聪 2010 8 18 07:58:27 修改注释格式.
	 * @param PageSortModel psm
	 * @param TechnologyDefence meeting
	 * @return List
	 */
	List getList(PageSortModel psm, Meeting meeting);

	/**
	 * @description TODO
	 * @author huxj 2009 12 16 12:34:56
	 * @modify 陈文聪 2010 8 18 07:58:48 修改注释格式.
	 * @param PageSortModel psm
	 * @param TechnologyDefence meeting
	 * @param Map<String, Object> params
	 * @return List<Meeting>
	 */
	public List<Meeting> getPageList(PageSortModel psm, Meeting meeting, Map<String, Object> params,Organ organ);

        public List<Meeting>  listForSelect(PageSortModel<Meeting> psm, Meeting meeting, String districtCode,
                        String includeChild);
}
