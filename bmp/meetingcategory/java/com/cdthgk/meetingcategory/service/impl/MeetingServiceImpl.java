package com.cdthgk.meetingcategory.service.impl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.meetingcategory.service.MeetingService;
import com.cdthgk.meetingcategory.vo.AttendMeeting;
import com.cdthgk.meetingcategory.vo.Meeting;
import com.cdthgk.platform.attachment.service.AttachmentService;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.thgk.platform.syslog.service.invoke.imp.annotation.ExcuteType;
import com.thgk.platform.syslog.service.invoke.imp.annotation.SysLog;
import com.thgk.platform.syslog.service.invoke.imp.annotation.SysLogs;

import ec.common.PageSortModel;

/**
 * @description 会议管理Service
 * @author huxj 2009 12 16 12:34:56
 * @modify 陈文聪 2010 8 18 07:57:50 修改注释格式
 */

@SysLogs(
		{
			@SysLog(method="update",methodParamsSize=1,description="修改会议信息{0}|meetingName",excute_type=ExcuteType.UPDATE),
			@SysLog(method="deleteBatchWithId",methodParamsSize=1,description="删除会议信息",excute_type=ExcuteType.DELETE),
			@SysLog(method="save",methodParamsSize=1,description="添加会议信息{0}|meetingName",excute_type=ExcuteType.ADD)

		}
)
@SuppressWarnings("all")
public class MeetingServiceImpl extends BmpServiceImpl<Meeting, String> implements
		MeetingService {

	private AttachmentService attachementService;

	/**
	 * @description TODO
	 * @author huxj 2009 12 16 12:34:56
	 * @modify 陈文聪 2010 8 18 07:58:09 修改注释格式.
	 * @return List
	 */
	public List getAllMeeting() {
		return this.getPersistProxy().getOrmPersistence()
				.findAll(Meeting.class);
	}

	/**
	 * @description TODO
	 * @author huxj 2009 12 16 12:34:56
	 * @modify 陈文聪 2010 8 18 07:58:19 修改注释格式.
	 * @return List<AttendMeeting>
	 */
	public List<AttendMeeting> getAllAttendMeeting() {
		// FIXME 有什么用处？
		return null;
	}

	/**
	 * @description TODO
	 * @author huxj 2009 12 16 12:34:56
	 * @modify 陈文聪 2010 8 18 07:58:27 修改注释格式.
	 * @param PageSortModel psm
	 * @param TechnologyDefence meeting
	 * @return List
	 */
	public List getList(PageSortModel psm, Meeting meeting) {
		String hql = "FROM Meeting m WHERE 1=1 ";
		Map<String, Object> params = new HashMap<String, Object>();
		if (meeting != null && !"".equals(meeting.getMeetingName())) {
			hql += " and m.meetingName like :meetingName";
			params.put("meetingName", "%" + meeting.getMeetingName() + "%");
		}
		return findList(hql.toString(), params, psm);
	}

	/**
	 * @description TODO
	 * @author huxj 2009 12 16 12:34:56
	 * @modify 陈文聪 2010 8 18 07:58:48 修改注释格式.
	 * @param PageSortModel psm
	 * @param TechnologyDefence meeting
	 * @param Map<String, Object> params
	 * @return List<Meeting>
	 */
	public List<Meeting> getPageList(PageSortModel psm, Meeting meeting, Map<String, Object> params,Organ organ) {
		// 通过举办单位过滤单位数据
		String hql = "FROM Meeting m WHERE 1=1 and m.organ.organId=:organId";
		params.put("organId", organ.getOrganId());

		// 自己发布的会议
		hql += " and m.status=1 ";

		// 会议主题查询
		if (meeting != null && !"".equals(meeting.getMeetingName()) && null != meeting.getMeetingName()) {
			hql += " and m.meetingName like :meetingName";
			params.put("meetingName", "%" + meeting.getMeetingName() + "%");
		}

		// 会议分类查询
		if (meeting != null
				&& null != meeting.getMeetingCategory()
				&& !"".equals(meeting.getMeetingCategory().getCategoryName())
				&& null != meeting.getMeetingCategory().getCategoryName()) {
			hql += " and m.meetingCategory.categoryName like :categoryName";
			params.put("categoryName", "%" +meeting.getMeetingCategory().getCategoryName()+ "%");
		}


		// 时间查询
		if (meeting != null
				&& !"".equals(meeting.getMeetingTime())
				&& null != meeting.getMeetingTime()) {

			hql += " and DATE_FORMAT( m.meetingTime,'%Y-%m-%d') =:meetingTime";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String meetingTime = sdf.format(meeting.getMeetingTime());
			params.put("meetingTime", meetingTime);
		}
		hql += " order by m.meetingTime desc";
		if (psm != null) {
			return (List<Meeting>)findList(hql.toString(), params, psm);
		}
		return (List<Meeting>)findList(hql.toString(), params);
	}

	// geter & seter

	public AttachmentService getAttachementService() {
		return attachementService;
	}

	public void setAttachementService(AttachmentService attachementService) {
		this.attachementService = attachementService;
	}

        @Override
        public List<Meeting> listForSelect(PageSortModel<Meeting> psm, Meeting meeting, String districtCode,
                        String includeChild) {
                Map<String, Object> params = new HashMap<String, Object>();
                String hql = "FROM Meeting m WHERE 1=1 ";

                // 自己发布的会议
                hql += " and m.status=1 ";

                // 会议主题查询
                if (meeting != null && !"".equals(meeting.getMeetingName()) && null != meeting.getMeetingName()) {
                        hql += " and m.meetingName like :meetingName";
                        params.put("meetingName", "%" + meeting.getMeetingName() + "%");
                }

                // 会议分类查询
                if (meeting != null
                                && null != meeting.getMeetingCategory()
                                && !"".equals(meeting.getMeetingCategory().getCategoryName())
                                && null != meeting.getMeetingCategory().getCategoryName()) {
                        hql += " and m.meetingCategory.categoryName like :categoryName";
                        params.put("categoryName", "%" +meeting.getMeetingCategory().getCategoryName()+ "%");
                }


                // 时间查询
                if (meeting != null
                                && !"".equals(meeting.getMeetingTime())
                                && null != meeting.getMeetingTime()) {

                        hql += " and DATE_FORMAT( m.meetingTime,'%Y-%m-%d') =:meetingTime";
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        String meetingTime = sdf.format(meeting.getMeetingTime());
                        params.put("meetingTime", meetingTime);
                }
                if (includeChild.equals("1")) {
                        //hql语句--保密局
                        int layer=this.getPersistProxy().getJdbcPersistence().findForInt(
                                        "select layer from sys_district where district_code ='"+districtCode+"'", params);
                        hql +=" and m.organ.organId in (";
                         hql +="select o.organId from Organ as o where o.district.districtCode in ";
                         hql +="(select district.districtCode from District as district where district.layer like :layer ))";
                        params.put("layer",  layer+"%");
                }
                if (includeChild.equals("0")) {
                        //hql语句--直辖单位
                         hql +=" and m.organ.organId in (";
                         hql +="select o.organId from Organ as o where o.district.districtCode=:districtCode)";
                        params.put("districtCode",  districtCode);
                }
                hql += " order by m.meetingTime desc";
                return findList(hql.toString(), params, psm);
        }


}
