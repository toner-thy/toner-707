package com.cdthgk.bmp.stateSecretManagement.receiveConfidential.vo;
// default package
// Generated 2014-5-9 15:11:02 ----- 3.4.0.CR1

import java.util.Date;

import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;

/**
 * BmReceiveConfidential
 */
public class ReceiveConfidential implements java.io.Serializable {

        private String receiveConfidentialId;

        //�ܼ����.
        private String confidentialName;

        //�ܼ�����.
        private String confidentialContent;

        //�ܼ�.
        private int securityLevel;
        private String securityLevelTxt;

        //ʱ��.
        private Date receiverTime;

        //�ĺ�.
        private String docNum;

        //������ʽ.
        private int carrierFormat;
        private String carrierFormatTxt;

        //����.
        private int fileNum;

        //״̬.
        private int status;

        //������.
        private UserInfo createPerson;

        //����ʱ��.
        private Date createTime;

        //�޸���.
        private UserInfo modifyPerson;

        //�޸�ʱ��.
        private Date modifyTime;

        //������λ.
        private Organ createOrgan;

        /**
         * 默认构�?函数
         */
        public ReceiveConfidential() {
        }

        /**
         * 返回receiveConfidentialId
         * @return receiveConfidentialId
         */
        public String getReceiveConfidentialId() {
                return this.receiveConfidentialId;
        }

        /**
         * 设置receiveConfidentialId
         * @param receiveConfidentialId receiveConfidentialId
         */
        public void setReceiveConfidentialId(String receiveConfidentialId) {
                this.receiveConfidentialId = receiveConfidentialId;
        }

        /**
         * 返回confidentialName
         * @return confidentialName
         */
        public String getConfidentialName() {
                return this.confidentialName;
        }

        /**
         * 设置confidentialName
         * @param confidentialName confidentialName
         */
        public void setConfidentialName(String confidentialName) {
                this.confidentialName = confidentialName;
        }

        /**
         * 返回confidentialContent
         * @return confidentialContent
         */
        public String getConfidentialContent() {
                return this.confidentialContent;
        }

        /**
         * 设置confidentialContent
         * @param confidentialContent confidentialContent
         */
        public void setConfidentialContent(String confidentialContent) {
                this.confidentialContent = confidentialContent;
        }

        /**
         * 返回securityLevel
         * @return securityLevel
         */
        public int getSecurityLevel() {
                return this.securityLevel;
        }

        /**
         * 设置securityLevel
         * @param securityLevel securityLevel
         */
        public void setSecurityLevel(int securityLevel) {
                this.securityLevel = securityLevel;
        }

        /**
         * 返回receiverTime
         * @return receiverTime
         */
        public Date getReceiverTime() {
                return this.receiverTime;
        }

        /**
         * 设置receiverTime
         * @param receiverTime receiverTime
         */
        public void setReceiverTime(Date receiverTime) {
                this.receiverTime = receiverTime;
        }

        /**
         * 返回docNum
         * @return docNum
         */
        public String getDocNum() {
                return this.docNum;
        }

        /**
         * 设置docNum
         * @param docNum docNum
         */
        public void setDocNum(String docNum) {
                this.docNum = docNum;
        }

        /**
         * 返回carrierFormat
         * @return carrierFormat
         */
        public int getCarrierFormat() {
                return this.carrierFormat;
        }

        /**
         * 设置carrierFormat
         * @param carrierFormat carrierFormat
         */
        public void setCarrierFormat(int carrierFormat) {
                this.carrierFormat = carrierFormat;
        }

        /**
         * 返回fileNum
         * @return fileNum
         */
        public int getFileNum() {
                return this.fileNum;
        }

        /**
         * 设置fileNum
         * @param fileNum fileNum
         */
        public void setFileNum(int fileNum) {
                this.fileNum = fileNum;
        }

        /**
         * 返回status
         * @return status
         */
        public int getStatus() {
                return this.status;
        }

        /**
         * 设置status
         * @param status status
         */
        public void setStatus(int status) {
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
         * @return the securityLevelTxt
         */
        public String getSecurityLevelTxt() {
                return securityLevelTxt;
        }

        /**
         * @param securityLevelTxt the securityLevelTxt to set
         */
        public void setSecurityLevelTxt(String securityLevelTxt) {
                this.securityLevelTxt = securityLevelTxt;
        }

        /**
         * @return the carrierFormatTxt
         */
        public String getCarrierFormatTxt() {
                return carrierFormatTxt;
        }

        /**
         * @param carrierFormatTxt the carrierFormatTxt to set
         */
        public void setCarrierFormatTxt(String carrierFormatTxt) {
                this.carrierFormatTxt = carrierFormatTxt;
        }


}
