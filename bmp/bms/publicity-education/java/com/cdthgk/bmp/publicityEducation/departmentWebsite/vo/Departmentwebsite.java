package com.cdthgk.bmp.publicityEducation.departmentWebsite.vo;
// default package
// Generated 2014-5-5 10:29:06 ----- 3.4.0.CR1

import java.util.Date;

import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;

/**
 * BmDepartmentwebsite
 */
public class Departmentwebsite implements java.io.Serializable {

        private String departmentwebsiteId;

        //��վ���.
        private String websiteName;

        //����Ա������ˣ�.
        private String adminName;

        //����Աְ��.
        private String adminPost;

        //��վ��������.
        private Department department;

        private Integer status;

        private UserInfo createPerson;

        private Date createTime;

        private UserInfo modifyPerson;

        private Date modifyTime;

        private Organ createOrgan;

        /**
         * 默认构�?函数
         */
        public Departmentwebsite() {
        }

        /**
         * 返回departmentwebsiteId
         * @return departmentwebsiteId
         */
        public String getDepartmentwebsiteId() {
                return this.departmentwebsiteId;
        }

        /**
         * 设置departmentwebsiteId
         * @param departmentwebsiteId departmentwebsiteId
         */
        public void setDepartmentwebsiteId(String departmentwebsiteId) {
                this.departmentwebsiteId = departmentwebsiteId;
        }

        /**
         * 返回websiteName
         * @return websiteName
         */
        public String getWebsiteName() {
                return this.websiteName;
        }

        /**
         * 设置websiteName
         * @param websiteName websiteName
         */
        public void setWebsiteName(String websiteName) {
                this.websiteName = websiteName;
        }

        /**
         * 返回adminName
         * @return adminName
         */
        public String getAdminName() {
                return this.adminName;
        }

        /**
         * 设置adminName
         * @param adminName adminName
         */
        public void setAdminName(String adminName) {
                this.adminName = adminName;
        }

        /**
         * 返回adminPost
         * @return adminPost
         */
        public String getAdminPost() {
                return this.adminPost;
        }

        /**
         * 设置adminPost
         * @param adminPost adminPost
         */
        public void setAdminPost(String adminPost) {
                this.adminPost = adminPost;
        }

        /**
         * @return the department
         */
        public Department getDepartment() {
                return department;
        }

        /**
         * @param department the department to set
         */
        public void setDepartment(Department department) {
                this.department = department;
        }

        /**
         * @return the status
         */
        public Integer getStatus() {
                return status;
        }

        /**
         * @param status the status to set
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
