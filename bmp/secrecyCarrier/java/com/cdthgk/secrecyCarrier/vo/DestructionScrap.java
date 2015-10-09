package com.cdthgk.secrecyCarrier.vo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;
import com.cdthgk.platform.permission.user.domain.User;
@SuppressWarnings("all")
public class DestructionScrap implements java.io.Serializable {

	private static final long serialVersionUID = -6174487652886616089L;
	private String id;
	private Integer type;//涉密载体形式（含报废涉密设备）：
	private String typeTxt;
	private String content;//涉密载体内容
	private Integer number;//数量
	private Integer secrecyLevel;//密级
	private String secrecyLevelTxt;
	private String toPlace ;//销毁、报废流向
	private UserInfo responsibleUser ;//经办人
	private UserInfo approver ;//审批人
	private String description;//备注
	private Date modifyTime;//修改时间
	private Date createTime;//输入时间
	private User modifyPerson;//修改人员
	private User createPerson;//输入人员
	private Organ organId;//创建单位
	private Integer state;//状态：0：使用1：删除
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
        public Integer getType() {
                return type;
        }
        /**
         * @param type 设置 type
         */
        public void setType(Integer type) {
                this.type = type;
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
         * @return 返回 number
         */
        public Integer getNumber() {
                return number;
        }
        /**
         * @param number 设置 number
         */
        public void setNumber(Integer number) {
                this.number = number;
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
         * @return 返回 toPlace
         */
        public String getToPlace() {
                return toPlace;
        }
        /**
         * @param toPlace 设置 toPlace
         */
        public void setToPlace(String toPlace) {
                this.toPlace = toPlace;
        }
        /**
         * @return 返回 responsibleUser
         */
        public UserInfo getResponsibleUser() {
                return responsibleUser;
        }
        /**
         * @param responsibleUser 设置 responsibleUser
         */
        public void setResponsibleUser(UserInfo responsibleUser) {
                this.responsibleUser = responsibleUser;
        }
        /**
         * @return 返回 approver
         */
        public UserInfo getApprover() {
                return approver;
        }
        /**
         * @param approver 设置 approver
         */
        public void setApprover(UserInfo approver) {
                this.approver = approver;
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
         * @return the typeTxt
         */
        public String getTypeTxt() {
                return typeTxt;
        }
        /**
         * @param typeTxt the typeTxt to set
         */
        public void setTypeTxt(String typeTxt) {
                this.typeTxt = typeTxt;
        }
        /**
         * @return the serialversionuid
         */
        public static long getSerialversionuid() {
                return serialVersionUID;
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