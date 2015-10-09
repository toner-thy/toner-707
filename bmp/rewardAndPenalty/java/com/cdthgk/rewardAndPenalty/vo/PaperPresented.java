package com.cdthgk.rewardAndPenalty.vo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.permission.user.domain.User;
@SuppressWarnings("all")
public class PaperPresented implements java.io.Serializable {

	private static final long serialVersionUID = -6174487652886616089L;
	private String id;
	private String author;//作者
	private String name;//篇名
	private String periodical;//刊物
	private Date date;//期号
	private String awardName ;//奖项名称
	private String awardOrgan ;//评奖单位
	private String description;//备注
	private Date modifyTime;//修改时间
	private Date createTime;//输入时间
	private User modifyPerson;//修改人员
	private User createPerson;//输入人员
	private Organ organId;//创建单位
	private Integer state;//状态：0：使用1：删除
	//用于查询
        private String startTime;
        private String endTime;
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
         * @return 返回 author
         */
        public String getAuthor() {
                return author;
        }
        /**
         * @param author 设置 author
         */
        public void setAuthor(String author) {
                this.author = author;
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
         * @return 返回 periodical
         */
        public String getPeriodical() {
                return periodical;
        }
        /**
         * @param periodical 设置 periodical
         */
        public void setPeriodical(String periodical) {
                this.periodical = periodical;
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
         * @return 返回 awardName
         */
        public String getAwardName() {
                return awardName;
        }
        /**
         * @param awardName 设置 awardName
         */
        public void setAwardName(String awardName) {
                this.awardName = awardName;
        }
        /**
         * @return 返回 awardOrgan
         */
        public String getAwardOrgan() {
                return awardOrgan;
        }
        /**
         * @param awardOrgan 设置 awardOrgan
         */
        public void setAwardOrgan(String awardOrgan) {
                this.awardOrgan = awardOrgan;
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
         * @return 返回 startTime
         */
        public String getStartTime() {
                return startTime;
        }
        /**
         * @param startTime 设置 startTime
         */
        public void setStartTime(String startTime) {
                this.startTime = startTime;
        }
        /**
         * @return 返回 endTime
         */
        public String getEndTime() {
                return endTime;
        }
        /**
         * @param endTime 设置 endTime
         */
        public void setEndTime(String endTime) {
                this.endTime = endTime;
        }


}