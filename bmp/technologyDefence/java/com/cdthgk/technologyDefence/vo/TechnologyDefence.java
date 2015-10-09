package com.cdthgk.technologyDefence.vo;

import java.util.Date;

import com.cdthgk.platform.permission.user.domain.User;

/**
 * @description TODO
 * @author 杨  成 2009 12 16 12:34:56
 * @modify 陈文聪 2010 8 18 07:28:55 修改注释格式
 */

public class TechnologyDefence implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// 会议id
	private String id;
	private TechnologyDefenceType technologyDefenceType;//技术防范类型
	private String question;//问题
	private String answer;//答案
	private String reminder;//温馨提示；

	private User createPerson;//创建人

	private User modifyPerson;//修改人

	private Date createTime;//创建时间


	private Date modifyTime;//修改时间

	/*新增字段*/
	private Integer status;//0:删除；1：禁用；2：启用

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
         * @return 返回 technologyDefenceType
         */
        public TechnologyDefenceType getTechnologyDefenceType() {
                return technologyDefenceType;
        }

        /**
         * @param technologyDefenceType 设置 technologyDefenceType
         */
        public void setTechnologyDefenceType(TechnologyDefenceType technologyDefenceType) {
                this.technologyDefenceType = technologyDefenceType;
        }

        /**
         * @return 返回 question
         */
        public String getQuestion() {
                return question;
        }

        /**
         * @param question 设置 question
         */
        public void setQuestion(String question) {
                this.question = question;
        }

        /**
         * @return 返回 answer
         */
        public String getAnswer() {
                return answer;
        }

        /**
         * @param answer 设置 answer
         */
        public void setAnswer(String answer) {
                this.answer = answer;
        }

        /**
         * @return 返回 reminder
         */
        public String getReminder() {
                return reminder;
        }

        /**
         * @param reminder 设置 reminder
         */
        public void setReminder(String reminder) {
                this.reminder = reminder;
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
         * @return 返回 status
         */
        public Integer getStatus() {
                return status;
        }

        /**
         * @param status 设置 status
         */
        public void setStatus(Integer status) {
                this.status = status;
        }



}