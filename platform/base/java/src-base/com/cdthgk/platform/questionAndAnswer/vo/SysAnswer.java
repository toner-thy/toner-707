package com.cdthgk.platform.questionAndAnswer.vo;
// default package
// Generated 2014-6-4 9:49:15 ----- 3.4.0.CR1

import java.util.Date;

import com.cdthgk.platform.permission.user.domain.User;

/**
 * SysAnswer
 */
public class SysAnswer implements java.io.Serializable {

        private String answerId;

        //����������.
        private SysQuestion sysQuestion;

        //������.
        private String answerContent;

        //״̬��1�����ã�0�����ã�.
        private Integer state;

        private User createperson;
        private Date createTime;
        private User modifyPerson;
        private Date modifyTime;

        /**
         * 默认构�?函数
         */
        public SysAnswer() {
        }

        /**
         * 返回answerId
         * @return answerId
         */
        public String getAnswerId() {
                return this.answerId;
        }

        /**
         * 设置answerId
         * @param answerId answerId
         */
        public void setAnswerId(String answerId) {
                this.answerId = answerId;
        }

        /**
         * 返回sysQuestion
         * @return sysQuestion
         */
        public SysQuestion getSysQuestion() {
                return this.sysQuestion;
        }

        /**
         * 设置sysQuestion
         * @param sysQuestion sysQuestion
         */
        public void setSysQuestion(SysQuestion sysQuestion) {
                this.sysQuestion = sysQuestion;
        }

        /**
         * 返回answerContent
         * @return answerContent
         */
        public String getAnswerContent() {
                return this.answerContent;
        }

        /**
         * 设置answerContent
         * @param answerContent answerContent
         */
        public void setAnswerContent(String answerContent) {
                this.answerContent = answerContent;
        }

        /**
         * 返回state
         * @return state
         */
        public Integer getState() {
                return this.state;
        }

        /**
         * 设置state
         * @param state state
         */
        public void setState(Integer state) {
                this.state = state;
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
         * @return the createperson
         */
        public User getCreateperson() {
                return createperson;
        }

        /**
         * @param createperson the createperson to set
         */
        public void setCreateperson(User createperson) {
                this.createperson = createperson;
        }

        /**
         * @return the modifyPerson
         */
        public User getModifyPerson() {
                return modifyPerson;
        }

        /**
         * @param modifyPerson the modifyPerson to set
         */
        public void setModifyPerson(User modifyPerson) {
                this.modifyPerson = modifyPerson;
        }


}
