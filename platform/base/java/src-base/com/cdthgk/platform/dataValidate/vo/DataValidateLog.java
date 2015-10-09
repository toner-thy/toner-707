package com.cdthgk.platform.dataValidate.vo;
// default package
// Generated 2014-5-30 14:43:33 ----- 3.4.0.CR1

import java.util.Date;

import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;

/**
 * SysDataValidateLog
 */
public class DataValidateLog implements java.io.Serializable {

        //id.
        private String dataValidateLogId;

        private Date createTime;

        private Organ createOrgan;

        private UserInfo createPerson;

        /**
         * 默认构函数
         */
        public DataValidateLog() {
        }

        /**
         * @return the dataValidateLogId
         */
        public String getDataValidateLogId() {
                return dataValidateLogId;
        }

        /**
         * @param dataValidateLogId the dataValidateLogId to set
         */
        public void setDataValidateLogId(String dataValidateLogId) {
                this.dataValidateLogId = dataValidateLogId;
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


}
