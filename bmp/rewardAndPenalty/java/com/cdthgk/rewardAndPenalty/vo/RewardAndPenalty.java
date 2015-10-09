package com.cdthgk.rewardAndPenalty.vo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.permission.user.domain.User;
@SuppressWarnings("all")
public class RewardAndPenalty implements java.io.Serializable {

	private static final long serialVersionUID = -6174487652886616089L;
	private String id;
	private String name;//名称
	private String description;//备注
	private Date date;//时间
	private String rewardCircs;//奖励情况
	private String penaltyCircs;//惩罚情况
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
         * @return 返回 rewardCircs
         */
        public String getRewardCircs() {
                return rewardCircs;
        }
        /**
         * @param rewardCircs 设置 rewardCircs
         */
        public void setRewardCircs(String rewardCircs) {
                this.rewardCircs = rewardCircs;
        }
        /**
         * @return 返回 penaltyCircs
         */
        public String getPenaltyCircs() {
                return penaltyCircs;
        }
        /**
         * @param penaltyCircs 设置 penaltyCircs
         */
        public void setPenaltyCircs(String penaltyCircs) {
                this.penaltyCircs = penaltyCircs;
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