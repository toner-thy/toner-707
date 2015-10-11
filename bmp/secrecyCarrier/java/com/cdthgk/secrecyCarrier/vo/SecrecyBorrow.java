package com.cdthgk.secrecyCarrier.vo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;
import com.cdthgk.platform.permission.user.domain.User;
@SuppressWarnings("all")
public class SecrecyBorrow implements java.io.Serializable {

	private static final long serialVersionUID = -6174487652886616089L;
	private String id;
	private Date date;//借阅时间
	private String name;//文件名称
	private Integer secrecyLevel;//密级
	private Integer pageNumber;
	private String secrecyLevelTxt;
	private String docNumber;//文号
	private String number;//份数
	private UserInfo borrowUserInfo;//借阅人
	private UserInfo approver;//审批人
	private Date returnDate;//归还时间
	private String description;//备注
	private Date createTime;//输入时间
	private User modifyPerson;//修改人员
	private User createPerson;//输入人员
	private Organ organId;//创建单位
	private Integer state;//状态：0：使用1：删除
	private Date modifyTime;//修改时间
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
         * @return 返回 name
         */
        public String getName() {
                return name;
        }
        /**
         * @param name 设置 name
         */
        public void setName(String name) {
                this.name = name;
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
         * @return 返回 docNumber
         */
        public String getDocNumber() {
                return docNumber;
        }
        /**
         * @param docNumber 设置 docNumber
         */
        public void setDocNumber(String docNumber) {
                this.docNumber = docNumber;
        }
        /**
         * @return 返回 number
         */
        public String getNumber() {
                return number;
        }
        /**
         * @param number 设置 number
         */
        public void setNumber(String number) {
                this.number = number;
        }
        /**
         * @return 返回 borrowUserInfo
         */
        public UserInfo getBorrowUserInfo() {
                return borrowUserInfo;
        }
        /**
         * @param borrowUserInfo 设置 borrowUserInfo
         */
        public void setBorrowUserInfo(UserInfo borrowUserInfo) {
                this.borrowUserInfo = borrowUserInfo;
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
         * @return 返回 returnDate
         */
        public Date getReturnDate() {
                return returnDate;
        }
        /**
         * @param returnDate 设置 returnDate
         */
        public void setReturnDate(Date returnDate) {
                this.returnDate = returnDate;
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
         * @param state 设置 state
         */
        public void setState(Integer state) {
                this.state = state;
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
		public Integer getPageNumber() {
			return pageNumber;
		}
		public void setPageNumber(Integer pageNumber) {
			this.pageNumber = pageNumber;
		}

}