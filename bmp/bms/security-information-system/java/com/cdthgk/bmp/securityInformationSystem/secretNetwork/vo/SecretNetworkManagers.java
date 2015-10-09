package com.cdthgk.bmp.securityInformationSystem.secretNetwork.vo;
// default package
// Generated 2014-5-9 15:11:02 ----- 3.4.0.CR1

import java.util.Date;

import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;

/**
 * BmSecretNetworkManagers
 */
public class SecretNetworkManagers implements java.io.Serializable {

        private String secretNetworkManagersId;

        private SecretNetwork bmSecretNetwork;

        private Integer managerType;

        //����Ա����.
        private String managerName;

        //ְ��.
        private String managerPost;

        //ѧ��.
        private String learning;

        //��ҵѧУ.
        private String graduateSchool;

        //�Ƿ�μӹ�������ҵ����ѵ���ǣ�1����0����.
        private Integer isParticipatedSecre;

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
        public SecretNetworkManagers() {
        }

        /**
         * 返回secretNetworkManagersId
         * @return secretNetworkManagersId
         */
        public String getSecretNetworkManagersId() {
                return this.secretNetworkManagersId;
        }

        /**
         * 设置secretNetworkManagersId
         * @param secretNetworkManagersId secretNetworkManagersId
         */
        public void setSecretNetworkManagersId(String secretNetworkManagersId) {
                this.secretNetworkManagersId = secretNetworkManagersId;
        }

        /**
         * 返回bmSecretNetwork
         * @return bmSecretNetwork
         */
        public SecretNetwork getBmSecretNetwork() {
                return this.bmSecretNetwork;
        }

        /**
         * 设置bmSecretNetwork
         * @param bmSecretNetwork bmSecretNetwork
         */
        public void setBmSecretNetwork(SecretNetwork bmSecretNetwork) {
                this.bmSecretNetwork = bmSecretNetwork;
        }

        /**
         * 返回managerName
         * @return managerName
         */
        public String getManagerName() {
                return this.managerName;
        }

        /**
         * 设置managerName
         * @param managerName managerName
         */
        public void setManagerName(String managerName) {
                this.managerName = managerName;
        }

        /**
         * 返回managerPost
         * @return managerPost
         */
        public String getManagerPost() {
                return this.managerPost;
        }

        /**
         * 设置managerPost
         * @param managerPost managerPost
         */
        public void setManagerPost(String managerPost) {
                this.managerPost = managerPost;
        }

        /**
         * 返回learning
         * @return learning
         */
        public String getLearning() {
                return this.learning;
        }

        /**
         * 设置learning
         * @param learning learning
         */
        public void setLearning(String learning) {
                this.learning = learning;
        }

        /**
         * 返回graduateSchool
         * @return graduateSchool
         */
        public String getGraduateSchool() {
                return this.graduateSchool;
        }

        /**
         * 设置graduateSchool
         * @param graduateSchool graduateSchool
         */
        public void setGraduateSchool(String graduateSchool) {
                this.graduateSchool = graduateSchool;
        }

        /**
         * 返回isParticipatedSecre
         * @return isParticipatedSecre
         */
        public Integer getIsParticipatedSecre() {
                return this.isParticipatedSecre;
        }

        /**
         * 设置isParticipatedSecre
         * @param isParticipatedSecre isParticipatedSecre
         */
        public void setIsParticipatedSecre(Integer isParticipatedSecre) {
                this.isParticipatedSecre = isParticipatedSecre;
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
         * @return the managerType
         */
        public Integer getManagerType() {
                return managerType;
        }

        /**
         * @param managerType the managerType to set
         */
        public void setManagerType(Integer managerType) {
                this.managerType = managerType;
        }



}
