package com.cdthgk.secrecyFullPartTime.vo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;
import com.cdthgk.platform.permission.user.domain.User;
@SuppressWarnings("all")
/**
 *保密工作专（兼）职人员情况
 * @author Administrator
 *
 */
public class SecrecyFullPartTime implements java.io.Serializable {

	private static final long serialVersionUID = -6174487652886616089L;
	private String id;
	private UserInfo name;//姓名
	private String position;//职务
	private Integer degree;//文化程度
	private String degreeTxt;
	private Date date;//接受保密培训时间
	private String content;//接受保密培训内容;
	private String workYear;//从事保密工作年限
	private Integer isFull;//专职或者兼职 0：专职 1：兼职；
	private String isFullTxt;
	private Integer isTrain;//是否介绍保密培训 0：接受 1 ：没有
	private String isTrainTxt;

	private String description;//备注
	private Date modifyTime;//修改时间
	private Date createTime;//输入时间
	private User modifyPerson;//修改人员
	private User createPerson;//输入人员
	private Organ organId;//创建单位
	private Integer state;//状态：0：使用1：删除
        /**
         * @return 返回 id
         */
        public String getId() {
                return id;
        }
        /**
         * @return 返回 name
         */
        public UserInfo getName() {
                return name;
        }
        /**
         * @param name 设置 name
         */
        public void setName(UserInfo name) {
                this.name = name;
        }
        /**
         * @return 返回 position
         */
        public String getPosition() {
                return position;
        }
        /**
         * @param position 设置 position
         */
        public void setPosition(String position) {
                this.position = position;
        }
        /**
         * @return 返回 degree
         */
        public Integer getDegree() {
                return degree;
        }
        /**
         * @param degree 设置 degree
         */
        public void setDegree(Integer degree) {
                this.degree = degree;
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
         * @return 返回 content
         */
        public String getContent() {
                return content;
        }
        /**
         * @param content 设置 content
         */
        public void setContent(String content) {
                this.content = content;
        }
        /**
         * @return 返回 workYear
         */
        public String getWorkYear() {
                return workYear;
        }
        /**
         * @param workYear 设置 workYear
         */
        public void setWorkYear(String workYear) {
                this.workYear = workYear;
        }
        /**
         * @return 返回 isFull
         */
        public Integer getIsFull() {
                return isFull;
        }
        /**
         * @param isFull 设置 isFull
         */
        public void setIsFull(Integer isFull) {
                this.isFull = isFull;
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
         * @return 返回 degreeTxt
         */
        public String getDegreeTxt() {
                return degreeTxt;
        }
        /**
         * @param degreeTxt 设置 degreeTxt
         */
        public void setDegreeTxt(String degreeTxt) {
                this.degreeTxt = degreeTxt;
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
         * @return 返回 isFullTxt
         */
        public String getIsFullTxt() {
                return isFullTxt;
        }
        /**
         * @param isFullTxt 设置 isFullTxt
         */
        public void setIsFullTxt(String isFullTxt) {
                this.isFullTxt = isFullTxt;
        }
        /**
         * @return 返回 isTrainTxt
         */
        public String getIsTrainTxt() {
                return isTrainTxt;
        }
        /**
         * @param isTrainTxt 设置 isTrainTxt
         */
        public void setIsTrainTxt(String isTrainTxt) {
                this.isTrainTxt = isTrainTxt;
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
         * @return 返回 isTrain
         */
        public Integer getIsTrain() {
                return isTrain;
        }
        /**
         * @param isTrain 设置 isTrain
         */
        public void setIsTrain(Integer isTrain) {
                this.isTrain = isTrain;
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
         * @param id 设置 id
         */
        public void setId(String id) {
                this.id = id;
        }



}