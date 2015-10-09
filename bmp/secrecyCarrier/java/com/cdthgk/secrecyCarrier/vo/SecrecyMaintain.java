package com.cdthgk.secrecyCarrier.vo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;
import com.cdthgk.platform.permission.user.domain.User;
@SuppressWarnings("all")
public class SecrecyMaintain implements java.io.Serializable {

	private static final long serialVersionUID = -6174487652886616089L;
	private String id;
	private String type;//设备类型(品牌型号)
	private Date date;//送修时间
	private Integer secrecyLevel;//密级
	private String secrecyLevelTxt;
	private Organ maintainOrgan;//维修单位
	private Department useDepartment;//使用部门
	private UserInfo seeUserInfo;//监修人
	private String reason;//维修原因
	private String leaderIdea;//领导审批意见
	private String depIdea;//部门意见
	private String description;//备注
	private Date modifyTime;//修改时间
	private Date createTime;//输入时间
	private User modifyPerson;//修改人员
	private User createPerson;//输入人员
	private Organ organId;//创建单位
	private Integer state;//状态：0：使用1：删除
	//用于查询
        private String startTime;
        private String endTime;
        /**
         * @return 返回 id
         */
        public String getId() {
                return id;
        }
        /**
         * @param id 设置 id
         */
        public void setId(String id) {
                this.id = id;
        }
        /**
         * @return 返回 type
         */
        public String getType() {
                return type;
        }
        /**
         * @param type 设置 type
         */
        public void setType(String type) {
                this.type = type;
        }
        /**
         * @return 返回 date
         */
        public Date getDate() {
                return date;
        }
        /**
         * @param date 设置 date
         */
        public void setDate(Date date) {
                this.date = date;
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
         * @return 返回 maintainOrgan
         */
        public Organ getMaintainOrgan() {
                return maintainOrgan;
        }
        /**
         * @param maintainOrgan 设置 maintainOrgan
         */
        public void setMaintainOrgan(Organ maintainOrgan) {
                this.maintainOrgan = maintainOrgan;
        }
        /**
         * @return 返回 useDepartment
         */
        public Department getUseDepartment() {
                return useDepartment;
        }
        /**
         * @param useDepartment 设置 useDepartment
         */
        public void setUseDepartment(Department useDepartment) {
                this.useDepartment = useDepartment;
        }
        /**
         * @return 返回 seeUserInfo
         */
        public UserInfo getSeeUserInfo() {
                return seeUserInfo;
        }
        /**
         * @param seeUserInfo 设置 seeUserInfo
         */
        public void setSeeUserInfo(UserInfo seeUserInfo) {
                this.seeUserInfo = seeUserInfo;
        }
        /**
         * @return 返回 reason
         */
        public String getReason() {
                return reason;
        }
        /**
         * @param reason 设置 reason
         */
        public void setReason(String reason) {
                this.reason = reason;
        }
        /**
         * @return 返回 leaderIdea
         */
        public String getLeaderIdea() {
                return leaderIdea;
        }
        /**
         * @param leaderIdea 设置 leaderIdea
         */
        public void setLeaderIdea(String leaderIdea) {
                this.leaderIdea = leaderIdea;
        }
        /**
         * @return 返回 depIdea
         */
        public String getDepIdea() {
                return depIdea;
        }
        /**
         * @param depIdea 设置 depIdea
         */
        public void setDepIdea(String depIdea) {
                this.depIdea = depIdea;
        }
        /**
         * @return 返回 description
         */
        public String getDescription() {
                return description;
        }
        /**
         * @param description 设置 description
         */
        public void setDescription(String description) {
                this.description = description;
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
         * @return 返回 organId
         */
        public Organ getOrganId() {
                return organId;
        }
        /**
         * @param organId 设置 organId
         */
        public void setOrganId(Organ organId) {
                this.organId = organId;
        }
        /**
         * @return 返回 state
         */
        public Integer getState() {
                return state;
        }
        /**
         * @param state 设置 state
         */
        public void setState(Integer state) {
                this.state = state;
        }
        /**
         * @return 返回 startTime
         */
        public String getStartTime() {
                return startTime;
        }
        /**
         * @param startTime 设置 startTime
         */
        public void setStartTime(String startTime) {
                this.startTime = startTime;
        }
        /**
         * @return 返回 endTime
         */
        public String getEndTime() {
                return endTime;
        }
        /**
         * @param endTime 设置 endTime
         */
        public void setEndTime(String endTime) {
                this.endTime = endTime;
        }
        /**
         * @return the secrecyLevelTxt
         */
        public String getSecrecyLevelTxt() {
                return secrecyLevelTxt;
        }
        /**
         * @param secrecyLevelTxt the secrecyLevelTxt to set
         */
        public void setSecrecyLevelTxt(String secrecyLevelTxt) {
                this.secrecyLevelTxt = secrecyLevelTxt;
        }


}