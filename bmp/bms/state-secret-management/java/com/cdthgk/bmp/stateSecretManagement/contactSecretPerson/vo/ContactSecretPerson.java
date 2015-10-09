package com.cdthgk.bmp.stateSecretManagement.contactSecretPerson.vo;
// default package
// Generated 2014-5-9 15:11:02 ----- 3.4.0.CR1

import java.util.Date;
import java.util.List;

import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;

/**
 * BmContactSecretPerson
 */
public class ContactSecretPerson implements java.io.Serializable {

        private String contactSecretPersonId;

        //�ļ����.
        private String secretFileName;

        private String dispatchOrgan;
        //��������.
        private Date issuedDate;


        //��������.
        private Date receiveDate;

        //����.
        private Integer fileNum;

        //��Ա����.
        private String contactPersonName;

        //�Ӵ���֪Ϥʱ��.
        private Date contactDate;

        //�Ӵ���ʽ.
        private Integer contactType;
        private Integer contactWay;
        private String contactWayTxt;

        //�а���.
        private String undertaker;
        private String undertackerNames;
        private List<UserInfo> undertackerList;

        //�����쵼.
        private String approvedLeader;
        private String approvedLeaderNames;
        private List<UserInfo> approvedLeaderList;

        //״̬.
        private Integer status;

        private UserInfo createPerson;

        private Date createTime;

        private UserInfo modifyPerson;

        private Date modifyTime;

        private Organ createOrgan;

        /**
         * 默认构�?函数
         */
        public ContactSecretPerson() {
        }

        /**
         * 返回contactSecretPersonId
         * @return contactSecretPersonId
         */
        public String getContactSecretPersonId() {
                return this.contactSecretPersonId;
        }

        /**
         * 设置contactSecretPersonId
         * @param contactSecretPersonId contactSecretPersonId
         */
        public void setContactSecretPersonId(String contactSecretPersonId) {
                this.contactSecretPersonId = contactSecretPersonId;
        }

        /**
         * 返回secretFileName
         * @return secretFileName
         */
        public String getSecretFileName() {
                return this.secretFileName;
        }

        /**
         * 设置secretFileName
         * @param secretFileName secretFileName
         */
        public void setSecretFileName(String secretFileName) {
                this.secretFileName = secretFileName;
        }

        /**
         * 返回issuedDate
         * @return issuedDate
         */
        public Date getIssuedDate() {
                return this.issuedDate;
        }

        /**
         * 设置issuedDate
         * @param issuedDate issuedDate
         */
        public void setIssuedDate(Date issuedDate) {
                this.issuedDate = issuedDate;
        }

        /**
         * 返回receiveDate
         * @return receiveDate
         */
        public Date getReceiveDate() {
                return this.receiveDate;
        }

        /**
         * 设置receiveDate
         * @param receiveDate receiveDate
         */
        public void setReceiveDate(Date receiveDate) {
                this.receiveDate = receiveDate;
        }

        /**
         * 返回fileNum
         * @return fileNum
         */
        public Integer getFileNum() {
                return this.fileNum;
        }

        /**
         * 设置fileNum
         * @param fileNum fileNum
         */
        public void setFileNum(Integer fileNum) {
                this.fileNum = fileNum;
        }

        /**
         * 返回contactPersonName
         * @return contactPersonName
         */
        public String getContactPersonName() {
                return this.contactPersonName;
        }

        /**
         * 设置contactPersonName
         * @param contactPersonName contactPersonName
         */
        public void setContactPersonName(String contactPersonName) {
                this.contactPersonName = contactPersonName;
        }

        /**
         * 返回contactDate
         * @return contactDate
         */
        public Date getContactDate() {
                return this.contactDate;
        }

        /**
         * 设置contactDate
         * @param contactDate contactDate
         */
        public void setContactDate(Date contactDate) {
                this.contactDate = contactDate;
        }

        /**
         * 返回contactType
         * @return contactType
         */
        public Integer getContactType() {
                return this.contactType;
        }

        /**
         * 设置contactType
         * @param contactType contactType
         */
        public void setContactType(Integer contactType) {
                this.contactType = contactType;
        }

        /**
         * 返回undertaker
         * @return undertaker
         */
        public String getUndertaker() {
                return this.undertaker;
        }

        /**
         * 设置undertaker
         * @param undertaker undertaker
         */
        public void setUndertaker(String undertaker) {
                this.undertaker = undertaker;
        }

        /**
         * 返回approvedLeader
         * @return approvedLeader
         */
        public String getApprovedLeader() {
                return this.approvedLeader;
        }

        /**
         * 设置approvedLeader
         * @param approvedLeader approvedLeader
         */
        public void setApprovedLeader(String approvedLeader) {
                this.approvedLeader = approvedLeader;
        }

        /**
         * 返回status
         * @return status
         */
        public Integer getStatus() {
                return this.status;
        }

        /**
         * 设置status
         * @param status status
         */
        public void setStatus(Integer status) {
                this.status = status;
        }



        /**
         * 返回createTime
         * @return createTime
         */
        public Date getCreateTime() {
                return this.createTime;
        }

        /**
         * 设置createTime
         * @param createTime createTime
         */
        public void setCreateTime(Date createTime) {
                this.createTime = createTime;
        }



        /**
         * 返回modifyTime
         * @return modifyTime
         */
        public Date getModifyTime() {
                return this.modifyTime;
        }

        /**
         * 设置modifyTime
         * @param modifyTime modifyTime
         */
        public void setModifyTime(Date modifyTime) {
                this.modifyTime = modifyTime;
        }

        /**
         * @return the createPerson
         */
        public UserInfo getCreatePerson() {
                return createPerson;
        }

        /**
         * @param createPerson the createPerson to set
         */
        public void setCreatePerson(UserInfo createPerson) {
                this.createPerson = createPerson;
        }

        /**
         * @return the modifyPerson
         */
        public UserInfo getModifyPerson() {
                return modifyPerson;
        }

        /**
         * @param modifyPerson the modifyPerson to set
         */
        public void setModifyPerson(UserInfo modifyPerson) {
                this.modifyPerson = modifyPerson;
        }

        /**
         * @return the createOrgan
         */
        public Organ getCreateOrgan() {
                return createOrgan;
        }

        /**
         * @param createOrgan the createOrgan to set
         */
        public void setCreateOrgan(Organ createOrgan) {
                this.createOrgan = createOrgan;
        }

        /**
         * @return the contactWay
         */
        public Integer getContactWay() {
                return contactWay;
        }

        /**
         * @param contactWay the contactWay to set
         */
        public void setContactWay(Integer contactWay) {
                this.contactWay = contactWay;
        }

        /**
         * @return the dispatchOrgan
         */
        public String getDispatchOrgan() {
                return dispatchOrgan;
        }

        /**
         * @param dispatchOrgan the dispatchOrgan to set
         */
        public void setDispatchOrgan(String dispatchOrgan) {
                this.dispatchOrgan = dispatchOrgan;
        }

        /**
         * @return the undertackerNames
         */
        public String getUndertackerNames() {
                return undertackerNames;
        }

        /**
         * @param undertackerNames the undertackerNames to set
         */
        public void setUndertackerNames(String undertackerNames) {
                this.undertackerNames = undertackerNames;
        }

        /**
         * @return the approvedLeaderNames
         */
        public String getApprovedLeaderNames() {
                return approvedLeaderNames;
        }

        /**
         * @param approvedLeaderNames the approvedLeaderNames to set
         */
        public void setApprovedLeaderNames(String approvedLeaderNames) {
                this.approvedLeaderNames = approvedLeaderNames;
        }

        /**
         * @return the undertackerList
         */
        public List<UserInfo> getUndertackerList() {
                return undertackerList;
        }

        /**
         * @param undertackerList the undertackerList to set
         */
        public void setUndertackerList(List<UserInfo> undertackerList) {
                this.undertackerList = undertackerList;
        }

        /**
         * @return the approvedLeaderList
         */
        public List<UserInfo> getApprovedLeaderList() {
                return approvedLeaderList;
        }

        /**
         * @param approvedLeaderList the approvedLeaderList to set
         */
        public void setApprovedLeaderList(List<UserInfo> approvedLeaderList) {
                this.approvedLeaderList = approvedLeaderList;
        }

        /**
         * @return the contactWayTxt
         */
        public String getContactWayTxt() {
                return contactWayTxt;
        }

        /**
         * @param contactWayTxt the contactWayTxt to set
         */
        public void setContactWayTxt(String contactWayTxt) {
                this.contactWayTxt = contactWayTxt;
        }




}
