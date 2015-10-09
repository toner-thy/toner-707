package com.cdthgk.meetingcategory.vo;

import java.util.Date;

import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;

/**
 * @description 会议资料
 * @author 杨  成 2009 12 16 12:34:56
 * @modify 陈文聪 2010 8 18 07:29:38 修改注释格式
 */

public class AttendMeeting {
	private String attendMeetingId;
	private String attendMeetingName;
	private String attendMeetingContent;
	private Meeting meeting;
	private Organ organ;
	private Department department;
	private UserInfo userInfo;
	private Date commitInfoTime;

	public Meeting getMeeting() {
		return meeting;
	}

	public void setMeeting(Meeting meeting) {
		this.meeting = meeting;
	}

	public String getAttendMeetingId() {
		return attendMeetingId;
	}

	public void setAttendMeetingId(String attendMeetingId) {
		this.attendMeetingId = attendMeetingId;
	}

	public String getAttendMeetingName() {
		return attendMeetingName;
	}

	public void setAttendMeetingName(String attendMeetingName) {
		this.attendMeetingName = attendMeetingName;
	}

	public String getAttendMeetingContent() {
		return attendMeetingContent;
	}

	public void setAttendMeetingContent(String attendMeetingContent) {
		this.attendMeetingContent = attendMeetingContent;
	}

	public Organ getOrgan() {
		return organ;
	}

	public void setOrgan(Organ organ) {
		this.organ = organ;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public Date getCommitInfoTime() {
		return commitInfoTime;
	}

	public void setCommitInfoTime(Date commitInfoTime) {
		this.commitInfoTime = commitInfoTime;
	}

}
