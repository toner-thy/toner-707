package com.cdthgk.platform.questionAndAnswer.vo;
// default package
// Generated 2014-6-4 9:49:15 ----- 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.cdthgk.platform.permission.user.domain.User;

/**
 * SysQuestion
 */
public class SysQuestion implements java.io.Serializable {

        private String questionId;

        //�������0:δ���ࣩ.
        private Integer questionCategory;

        //�������.
        private String questionTitle;

        //��������.
        private String questionContent;

        //״̬��1�����ã�0�����ã�.
        private Integer state;

        private User createperson;
        private Date createTime;
        private User modifyPerson;
        private Date modifyTime;

        private Set<SysAnswer> sysAnswers = new HashSet<SysAnswer>(0);

        /**
         * 默认构�?函数
         */
        public SysQuestion() {
        }

        /**
         * 返回questionId
         * @return questionId
         */
        public String getQuestionId() {
                return this.questionId;
        }

        /**
         * 设置questionId
         * @param questionId questionId
         */
        public void setQuestionId(String questionId) {
                this.questionId = questionId;
        }

        /**
         * 返回questionCategory
         * @return questionCategory
         */
        public Integer getQuestionCategory() {
                return this.questionCategory;
        }

        /**
         * 设置questionCategory
         * @param questionCategory questionCategory
         */
        public void setQuestionCategory(Integer questionCategory) {
                this.questionCategory = questionCategory;
        }

        /**
         * 返回questionTitle
         * @return questionTitle
         */
        public String getQuestionTitle() {
                return this.questionTitle;
        }

        /**
         * 设置questionTitle
         * @param questionTitle questionTitle
         */
        public void setQuestionTitle(String questionTitle) {
                this.questionTitle = questionTitle;
        }

        /**
         * 返回questionContent
         * @return questionContent
         */
        public String getQuestionContent() {
                return this.questionContent;
        }

        /**
         * 设置questionContent
         * @param questionContent questionContent
         */
        public void setQuestionContent(String questionContent) {
                this.questionContent = questionContent;
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
         * 返回sysAnswers
         * @return sysAnswers
         */
        public Set<SysAnswer> getSysAnswers() {
                return this.sysAnswers;
        }

        /**
         * 设置sysAnswers
         * @param sysAnswers sysAnswers
         */
        public void setSysAnswers(Set<SysAnswer> sysAnswers) {
                this.sysAnswers = sysAnswers;
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
