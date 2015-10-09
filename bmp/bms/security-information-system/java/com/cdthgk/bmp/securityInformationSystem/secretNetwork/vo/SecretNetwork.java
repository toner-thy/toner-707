package com.cdthgk.bmp.securityInformationSystem.secretNetwork.vo;
// default package
// Generated 2014-5-9 15:11:02 ----- 3.4.0.CR1

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;

/**
 * BmSecretNetwork
 */
public class SecretNetwork implements java.io.Serializable {

        private String secretNetworkId;

        //����ʱ��.
        private Date buildTime;

        //�н���λ.
        private String constructionUnit;

        //������.
        private String approvalDepartment;

        //����ʱ��.
        private Date approvalTime;

        //�ĺ�.
        private String docNum;

        //״̬.
        private Integer status;

        private UserInfo createPerson;

        private Date createTime;

        private UserInfo modifyPerson;

        private Date modifyTime;

        private Organ createOrgan;

        private List<SecretNetworkManagers> secretNetworkManagerses = new ArrayList<SecretNetworkManagers>(0);

        /**
         * 默认构�?函数
         */
        public SecretNetwork() {
        }

        /**
         * 返回secretNetworkId
         * @return secretNetworkId
         */
        public String getSecretNetworkId() {
                return this.secretNetworkId;
        }

        /**
         * 设置secretNetworkId
         * @param secretNetworkId secretNetworkId
         */
        public void setSecretNetworkId(String secretNetworkId) {
                this.secretNetworkId = secretNetworkId;
        }

        /**
         * 返回buildTime
         * @return buildTime
         */
        public Date getBuildTime() {
                return this.buildTime;
        }

        /**
         * 设置buildTime
         * @param buildTime buildTime
         */
        public void setBuildTime(Date buildTime) {
                this.buildTime = buildTime;
        }

        /**
         * 返回constructionUnit
         * @return constructionUnit
         */
        public String getConstructionUnit() {
                return this.constructionUnit;
        }

        /**
         * 设置constructionUnit
         * @param constructionUnit constructionUnit
         */
        public void setConstructionUnit(String constructionUnit) {
                this.constructionUnit = constructionUnit;
        }

        /**
         * 返回approvalDepartment
         * @return approvalDepartment
         */
        public String getApprovalDepartment() {
                return this.approvalDepartment;
        }

        /**
         * 设置approvalDepartment
         * @param approvalDepartment approvalDepartment
         */
        public void setApprovalDepartment(String approvalDepartment) {
                this.approvalDepartment = approvalDepartment;
        }

        /**
         * 返回approvalTime
         * @return approvalTime
         */
        public Date getApprovalTime() {
                return this.approvalTime;
        }

        /**
         * 设置approvalTime
         * @param approvalTime approvalTime
         */
        public void setApprovalTime(Date approvalTime) {
                this.approvalTime = approvalTime;
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
         * @return the secretNetworkManagerses
         */
        public List<SecretNetworkManagers> getSecretNetworkManagerses() {
                return secretNetworkManagerses;
        }

        /**
         * @param secretNetworkManagerses the secretNetworkManagerses to set
         */
        public void setSecretNetworkManagerses(List<SecretNetworkManagers> secretNetworkManagerses) {
                this.secretNetworkManagerses = secretNetworkManagerses;
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

}
