package com.cdthgk.secrecyCarrier.vo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;
import com.cdthgk.platform.permission.user.domain.User;
@SuppressWarnings("all")
public class SecrecyCopy implements java.io.Serializable {

	private static final long serialVersionUID = -6174487652886616089L;
	private String id;
	private Date date;//复印时间
	private String name;//文件（资料）名称
	private String docNumber;//文号
	private Integer secrecyLevel;//密级
	private String secrecyLevelTxt;
	private String number;//份数
	private String pageNo;//每份页数
	private Department draftingDep;//申请部门
	private UserInfo  approver;//批准人
	private UserInfo  applicant;//申请人
	private UserInfo undertaker;//承办人
	private Organ didOrgan;//文件制发单位
	private String usePlace;//用途
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
         * @return 返回 applicant
         */
        public UserInfo getApplicant() {
                return applicant;
        }

        /**
         * @param applicant 设置 applicant
         */
        public void setApplicant(UserInfo applicant) {
                this.applicant = applicant;
        }

        /**
         * @return 返回 undertaker
         */
        public UserInfo getUndertaker() {
                return undertaker;
        }

        /**
         * @param undertaker 设置 undertaker
         */
        public void setUndertaker(UserInfo undertaker) {
                this.undertaker = undertaker;
        }

        /**
         * @return 返回 didOrgan
         */
        public Organ getDidOrgan() {
                return didOrgan;
        }

        /**
         * @param didOrgan 设置 didOrgan
         */
        public void setDidOrgan(Organ didOrgan) {
                this.didOrgan = didOrgan;
        }

        /**
         * @return 返回 usePlace
         */
        public String getUsePlace() {
                return usePlace;
        }

        /**
         * @param usePlace 设置 usePlace
         */
        public void setUsePlace(String usePlace) {
                this.usePlace = usePlace;
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
         * @return 返回 pageNo
         */
        public String getPageNo() {
                return pageNo;
        }
        /**
         * @param pageNo 设置 pageNo
         */
        public void setPageNo(String pageNo) {
                this.pageNo = pageNo;
        }
        /**
         * @return 返回 draftingDep
         */
        public Department getDraftingDep() {
                return draftingDep;
        }
        /**
         * @param draftingDep 设置 draftingDep
         */
        public void setDraftingDep(Department draftingDep) {
                this.draftingDep = draftingDep;
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