package com.cdthgk.bmp.publicityEducation.publicityEducation.vo;

import java.util.Date;

import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;

/**
 * BmPublicityeducation
 */
public class Publicityeducation implements java.io.Serializable {

        //id.
        private String publicityeducationId;

        //��ѵʱ��.
        private Date trainTime;

        //��ʽ.
        private String form;

        //��Χ.
        private String trainRange;

        //�μ�����.
        private Integer participantsNum;

        //��Ҫ����.
        private String content;

        private Integer status;

        private UserInfo createPerson;

        private Date createTime;

        private UserInfo modifyPerson;

        private Date modifyTime;

        private Organ createOrgan;

        /**
         * 默认构�?函数
         */
        public Publicityeducation() {
        }

        /**
         * 返回publicityeducationId
         * @return publicityeducationId
         */
        public String getPublicityeducationId() {
                return this.publicityeducationId;
        }

        /**
         * 设置publicityeducationId
         * @param publicityeducationId publicityeducationId
         */
        public void setPublicityeducationId(String publicityeducationId) {
                this.publicityeducationId = publicityeducationId;
        }

        /**
         * 返回trainTime
         * @return trainTime
         */
        public Date getTrainTime() {
                return this.trainTime;
        }

        /**
         * 设置trainTime
         * @param trainTime trainTime
         */
        public void setTrainTime(Date trainTime) {
                this.trainTime = trainTime;
        }

        /**
         * 返回form
         * @return form
         */
        public String getForm() {
                return this.form;
        }

        /**
         * 设置form
         * @param form form
         */
        public void setForm(String form) {
                this.form = form;
        }

        /**
         * 返回trainRange
         * @return trainRange
         */
        public String getTrainRange() {
                return this.trainRange;
        }

        /**
         * 设置trainRange
         * @param trainRange trainRange
         */
        public void setTrainRange(String trainRange) {
                this.trainRange = trainRange;
        }

        /**
         * 返回participantsNum
         * @return participantsNum
         */
        public Integer getParticipantsNum() {
                return this.participantsNum;
        }

        /**
         * 设置participantsNum
         * @param participantsNum participantsNum
         */
        public void setParticipantsNum(Integer participantsNum) {
                this.participantsNum = participantsNum;
        }

        /**
         * 返回content
         * @return content
         */
        public String getContent() {
                return this.content;
        }

        /**
         * 设置content
         * @param content content
         */
        public void setContent(String content) {
                this.content = content;
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
         * @return the createTime
         */
        public Date getCreateTime() {
                return createTime;
        }

        /**
         * @param createTime the createTime to set
         */
        public void setCreateTime(Date createTime) {
                this.createTime = createTime;
        }

        /**
         * @return the modiryPerson
         */
        public UserInfo getModifyPerson() {
                return modifyPerson;
        }

        /**
         * @param modiryPerson the modiryPerson to set
         */
        public void setModifyPerson(UserInfo modifyPerson) {
                this.modifyPerson = modifyPerson;
        }

        /**
         * @return the modifyTime
         */
        public Date getModifyTime() {
                return modifyTime;
        }

        /**
         * @param modifyTime the modifyTime to set
         */
        public void setModifyTime(Date modifyTime) {
                this.modifyTime = modifyTime;
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
