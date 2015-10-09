package com.cdthgk.meetingcategory.vo;

import java.util.Date;
import java.util.Set;

import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;
import com.cdthgk.platform.permission.user.domain.User;

/**
 * @description TODO
 * @author 杨  成 2009 12 16 12:34:56
 * @modify 陈文聪 2010 8 18 07:28:55 修改注释格式
 */

public class Meeting implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// 会议id
	private String meetingId;
	// 会议名称/议题/涉密会议（活动名称）
	private String meetingName;
	// 开会时间
	private Date meetingTime;
	// 会议举办单位
	private String holdOrgans;
	// 会议举办部门
	private String holdDepartment;
	// 出息人员
	private String attendUserInfos;
	private String attendUserInfoNames;
	private UserInfo presenter;//主持人
	private UserInfo recorder;//会议记录人
	private Organ organ;

	private User createPerson;//创建人

	private Date createTime;//创建时间

	private User modifyPerson;//修改人

	private Date modifyTime;//修改时间

	private String place;//会议/活动举行地点
	private String content;//会议/活动主要内容
	private String scope;//涉密会议知悉范围
	private String situation;//保密参与情况
	private String measure;//主要管理措施

	private Integer secrecyLevel;//涉密等级
	private String secrecyLevelStr;//涉密等级


	/*新增字段*/
	private Integer status;//删除时候0:删除了；1：显示
	private MeetingCategory meetingCategory;//会议类型


        /**
         * @return 返回 meetingId
         */
        public String getMeetingId() {
                return meetingId;
        }
        /**
         * @param meetingId 设置 meetingId
         */
        public void setMeetingId(String meetingId) {
                this.meetingId = meetingId;
        }
        /**
         * @return 返回 meetingName
         */
        public String getMeetingName() {
                return meetingName;
        }
        /**
         * @param meetingName 设置 meetingName
         */
        public void setMeetingName(String meetingName) {
                this.meetingName = meetingName;
        }
        /**
         * @return 返回 meetingTime
         */
        public Date getMeetingTime() {
                return meetingTime;
        }
        /**
         * @param meetingTime 设置 meetingTime
         */
        public void setMeetingTime(Date meetingTime) {
                this.meetingTime = meetingTime;
        }

        /**
         * @return 返回 attendUserInfos
         */
        public String getAttendUserInfos() {
                return attendUserInfos;
        }
        /**
         * @param attendUserInfos 设置 attendUserInfos
         */
        public void setAttendUserInfos(String attendUserInfos) {
                this.attendUserInfos = attendUserInfos;
        }
        /**
         * @return 返回 presenter
         */
        public UserInfo getPresenter() {
                return presenter;
        }
        /**
         * @return 返回 holdOrgans
         */
        public String getHoldOrgans() {
                return holdOrgans;
        }
        /**
         * @param holdOrgans 设置 holdOrgans
         */
        public void setHoldOrgans(String holdOrgans) {
                this.holdOrgans = holdOrgans;
        }
        /**
         * @return 返回 holdDepartment
         */
        public String getHoldDepartment() {
                return holdDepartment;
        }
        /**
         * @param holdDepartment 设置 holdDepartment
         */
        public void setHoldDepartment(String holdDepartment) {
                this.holdDepartment = holdDepartment;
        }
        /**
         * @return 返回 attendUserInfoNames
         */
        public String getAttendUserInfoNames() {
                return attendUserInfoNames;
        }
        /**
         * @param attendUserInfoNames 设置 attendUserInfoNames
         */
        public void setAttendUserInfoNames(String attendUserInfoNames) {
                this.attendUserInfoNames = attendUserInfoNames;
        }
        /**
         * @param presenter 设置 presenter
         */
        public void setPresenter(UserInfo presenter) {
                this.presenter = presenter;
        }
        /**
         * @return 返回 recorder
         */
        public UserInfo getRecorder() {
                return recorder;
        }
        /**
         * @param recorder 设置 recorder
         */
        public void setRecorder(UserInfo recorder) {
                this.recorder = recorder;
        }
        /**
         * @return 返回 organ
         */
        public Organ getOrgan() {
                return organ;
        }
        /**
         * @param organ 设置 organ
         */
        public void setOrgan(Organ organ) {
                this.organ = organ;
        }
        /**
         * @return 返回 createPerson
         */
        public User getCreatePerson() {
                return createPerson;
        }
        /**
         * @param createPerson 设置 createPerson
         */
        public void setCreatePerson(User createPerson) {
                this.createPerson = createPerson;
        }
        /**
         * @return 返回 createTime
         */
        public Date getCreateTime() {
                return createTime;
        }
        /**
         * @param createTime 设置 createTime
         */
        public void setCreateTime(Date createTime) {
                this.createTime = createTime;
        }
        /**
         * @return 返回 modifyPerson
         */
        public User getModifyPerson() {
                return modifyPerson;
        }
        /**
         * @param modifyPerson 设置 modifyPerson
         */
        public void setModifyPerson(User modifyPerson) {
                this.modifyPerson = modifyPerson;
        }
        /**
         * @return 返回 modifyTime
         */
        public Date getModifyTime() {
                return modifyTime;
        }
        /**
         * @param modifyTime 设置 modifyTime
         */
        public void setModifyTime(Date modifyTime) {
                this.modifyTime = modifyTime;
        }
        /**
         * @return 返回 place
         */
        public String getPlace() {
                return place;
        }
        /**
         * @param place 设置 place
         */
        public void setPlace(String place) {
                this.place = place;
        }
        /**
         * @return 返回 content
         */
        public String getContent() {
                return content;
        }
        /**
         * @param content 设置 content
         */
        public void setContent(String content) {
                this.content = content;
        }
        /**
         * @return 返回 scope
         */
        public String getScope() {
                return scope;
        }
        /**
         * @param scope 设置 scope
         */
        public void setScope(String scope) {
                this.scope = scope;
        }
        /**
         * @return 返回 situation
         */
        public String getSituation() {
                return situation;
        }
        /**
         * @param situation 设置 situation
         */
        public void setSituation(String situation) {
                this.situation = situation;
        }
        /**
         * @return 返回 measure
         */
        public String getMeasure() {
                return measure;
        }
        /**
         * @param measure 设置 measure
         */
        public void setMeasure(String measure) {
                this.measure = measure;
        }
        /**
         * @return 返回 secrecyLevel
         */
        public Integer getSecrecyLevel() {
                return secrecyLevel;
        }
        /**
         * @param secrecyLevel 设置 secrecyLevel
         */
        public void setSecrecyLevel(Integer secrecyLevel) {
                this.secrecyLevel = secrecyLevel;
        }
        /**
         * @return 返回 status
         */
        public Integer getStatus() {
                return status;
        }
        /**
         * @param status 设置 status
         */
        public void setStatus(Integer status) {
                this.status = status;
        }
        /**
         * @return 返回 meetingCategory
         */
        public MeetingCategory getMeetingCategory() {
                return meetingCategory;
        }
        /**
         * @param meetingCategory 设置 meetingCategory
         */
        public void setMeetingCategory(MeetingCategory meetingCategory) {
                this.meetingCategory = meetingCategory;
        }
		/**
		 * @return 返回secrecyLevelStr
		 */
		public String getSecrecyLevelStr() {
			return secrecyLevelStr;
		}
		/**
		 * @param secrecyLevelStr 设置secrecyLevelStr
		 */
		public void setSecrecyLevelStr(String secrecyLevelStr) {
			this.secrecyLevelStr = secrecyLevelStr;
		}
}