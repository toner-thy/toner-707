package com.cdthgk.checkevent.vo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.permission.user.domain.User;
@SuppressWarnings("all")
public class SecrecyCheckEvent implements java.io.Serializable {

	private static final long serialVersionUID = -6174487652886616089L;
	private String secrecyCheckEventId;
	private String eventName;//检查内容/名字
	private String eventDescription;//检查描述/备注
	private Date eventDate;//检查时间
	private String disposeIdea;//处理意见
	private String checkCircs;//检查情况
	private String checkMeans;//检查手段
	private Organ checkedOrgan;//受检单位（外键）
	private String checkedDepartment;//受检部门（外键）
	private String checkedDepartmentNames;//显示和导出使用字段
	private Date modifyTime;//修改时间
	private Date createTime;//输入时间
	private User modifyPerson;//修改人员
	private User createPerson;//输入人员
	private Organ organId;//单位ID
	private String checkPosition;//检查位置
	private String checkPerson;//检查人员

        private Department departmentId;
        private Integer type;
        //用于查询
        private String startTime;
        private String endTime;
        //新增字段
        private Integer joinNumber;//参加人数
        private String rectification ; //整改情况
	/**
         * @return 返回 rectification
         */
        public String getRectification() {
                return rectification;
        }

        /**
         * @param rectification 设置 rectification
         */
        public void setRectification(String rectification) {
                this.rectification = rectification;
        }

        /**
         * @return 返回 checkedDepartment
         */
        public String getCheckedDepartment() {
                return checkedDepartment;
        }

        /**
         * @param checkedDepartment 设置 checkedDepartment
         */
        public void setCheckedDepartment(String checkedDepartment) {
                this.checkedDepartment = checkedDepartment;
        }

	/**
         * @return 返回 joinNumber
         */
        public Integer getJoinNumber() {
                return joinNumber;
        }

        /**
         * @param joinNumber 设置 joinNumber
         */
        public void setJoinNumber(Integer joinNumber) {
                this.joinNumber = joinNumber;
        }



	public String getSecrecyCheckEventId() {
		return secrecyCheckEventId;
	}

	public void setSecrecyCheckEventId(String secrecyCheckEventId) {
		this.secrecyCheckEventId = secrecyCheckEventId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public String getDisposeIdea() {
		return disposeIdea;
	}

	public void setDisposeIdea(String disposeIdea) {
		this.disposeIdea = disposeIdea;
	}

	public String getCheckCircs() {
		return checkCircs;
	}

	public void setCheckCircs(String checkCircs) {
		this.checkCircs = checkCircs;
	}

	public String getCheckMeans() {
		return checkMeans;
	}

	public void setCheckMeans(String checkMeans) {
		this.checkMeans = checkMeans;
	}

	/**
	 * @return the checkedOrgan
	 */
	public Organ getCheckedOrgan() {
		return checkedOrgan;
	}

	/**
	 * @param checkedOrgan the checkedOrgan to set
	 */
	public void setCheckedOrgan(Organ checkedOrgan) {
		this.checkedOrgan = checkedOrgan;
	}


	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public User getModifyPerson() {
		return modifyPerson;
	}

	/**
         * @return 返回 departmentId
         */
        public Department getDepartmentId() {
                return departmentId;
        }

        /**
         * @param departmentId 设置 departmentId
         */
        public void setDepartmentId(Department departmentId) {
                this.departmentId = departmentId;
        }

        /**
         * @return 返回 type
         */
        public Integer getType() {
                return type;
        }

        /**
         * @param type 设置 type
         */
        public void setType(Integer type) {
                this.type = type;
        }

        public void setModifyPerson(User modifyPerson) {
		this.modifyPerson = modifyPerson;
	}

	public User getCreatePerson() {
		return createPerson;
	}

	public void setCreatePerson(User createPerson) {
		this.createPerson = createPerson;
	}

	public Organ getOrganId() {
		return organId;
	}

	public void setOrganId(Organ organId) {
		this.organId = organId;
	}
	public String getCheckPosition() {
		return checkPosition;
	}

	public void setCheckPosition(String checkPosition) {
		this.checkPosition = checkPosition;
	}

	public String getCheckPerson() {
		return checkPerson;
	}

	public void setCheckPerson(String checkPerson) {
		this.checkPerson = checkPerson;
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
         * @return the checkedDepartmentNames
         */
        public String getCheckedDepartmentNames() {
                return checkedDepartmentNames;
        }

        /**
         * @param checkedDepartmentNames the checkedDepartmentNames to set
         */
        public void setCheckedDepartmentNames(String checkedDepartmentNames) {
                this.checkedDepartmentNames = checkedDepartmentNames;
        }


}