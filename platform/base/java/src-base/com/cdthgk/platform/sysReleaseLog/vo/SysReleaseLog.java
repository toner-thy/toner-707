package com.cdthgk.platform.sysReleaseLog.vo;
// default package
// Generated 2014-5-26 16:45:00 ----- 3.4.0.CR1

import java.util.Date;

/**
 * SysReleaseLog
 */
public class SysReleaseLog implements java.io.Serializable {

        /**
         *
         */
        private static final long serialVersionUID = 1L;

        //id.
        private String logId;

        //������.
        private String releasePerson;

        //����ʱ��.
        private Date releaseDate;

        //�����汾��.
        private String releaseVersion;

        //�����Ĵ���汾��.
        private String releaseCodeVersion;

        //��������.
        private String releaseContent;

        //ע������.
        private String releaseAttention;

        //��ע.
        private String remark;

        /**
         * 默认构�?函数
         */
        public SysReleaseLog() {
        }

        /**
         * 返回logId
         * @return logId
         */
        public String getLogId() {
                return this.logId;
        }

        /**
         * 设置logId
         * @param logId logId
         */
        public void setLogId(String logId) {
                this.logId = logId;
        }

        /**
         * 返回releasePerson
         * @return releasePerson
         */
        public String getReleasePerson() {
                return this.releasePerson;
        }

        /**
         * 设置releasePerson
         * @param releasePerson releasePerson
         */
        public void setReleasePerson(String releasePerson) {
                this.releasePerson = releasePerson;
        }

        /**
         * 返回releaseDate
         * @return releaseDate
         */
        public Date getReleaseDate() {
                return this.releaseDate;
        }

        /**
         * 设置releaseDate
         * @param releaseDate releaseDate
         */
        public void setReleaseDate(Date releaseDate) {
                this.releaseDate = releaseDate;
        }

        /**
         * 返回releaseVersion
         * @return releaseVersion
         */
        public String getReleaseVersion() {
                return this.releaseVersion;
        }

        /**
         * 设置releaseVersion
         * @param releaseVersion releaseVersion
         */
        public void setReleaseVersion(String releaseVersion) {
                this.releaseVersion = releaseVersion;
        }

        /**
         * 返回releaseCodeVersion
         * @return releaseCodeVersion
         */
        public String getReleaseCodeVersion() {
                return this.releaseCodeVersion;
        }

        /**
         * 设置releaseCodeVersion
         * @param releaseCodeVersion releaseCodeVersion
         */
        public void setReleaseCodeVersion(String releaseCodeVersion) {
                this.releaseCodeVersion = releaseCodeVersion;
        }

        /**
         * 返回releaseContent
         * @return releaseContent
         */
        public String getReleaseContent() {
                return this.releaseContent;
        }

        /**
         * 设置releaseContent
         * @param releaseContent releaseContent
         */
        public void setReleaseContent(String releaseContent) {
                this.releaseContent = releaseContent;
        }

        /**
         * 返回releaseAttention
         * @return releaseAttention
         */
        public String getReleaseAttention() {
                return this.releaseAttention;
        }

        /**
         * 设置releaseAttention
         * @param releaseAttention releaseAttention
         */
        public void setReleaseAttention(String releaseAttention) {
                this.releaseAttention = releaseAttention;
        }

        /**
         * 返回remark
         * @return remark
         */
        public String getRemark() {
                return this.remark;
        }

        /**
         * 设置remark
         * @param remark remark
         */
        public void setRemark(String remark) {
                this.remark = remark;
        }

}
