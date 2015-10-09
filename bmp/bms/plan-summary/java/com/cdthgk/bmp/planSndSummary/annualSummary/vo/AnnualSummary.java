package com.cdthgk.bmp.planSndSummary.annualSummary.vo;
// default package
// Generated 2014-4-29 10:31:38 ----- 3.4.0.CR1

import java.util.Date;

import com.cdthgk.bmp.planSndSummary.annualPlan.vo.AnnualPlan;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;

/**
 * BmAnnualSummary
 */
public class AnnualSummary implements java.io.Serializable {

        //��ȹ����ܽ�id.
        private String annualSummaryId;

        //��ȹ����ƻ� ID.
        private AnnualPlan bmAnnualPlan;

        //��ȹ����ܽ����.
        private String annualSummaryTitle;

        //���.
        private Integer annualSummaryYear;

        //��ȹ����ܽ�����.
        private String annualSummaryContent;

        private String annualSummaryContentPre;

        //״̬.
        private Integer status;

        private UserInfo createPerson;

        private Date createTime;

        //39.
        private UserInfo modifyPerson;

        private Date modifyTime;

        private Organ createOrgan;

        /**
         * 默认构�?函数
         */
        public AnnualSummary() {
        }

        /**
         * 返回annualSummaryId
         * @return annualSummaryId
         */
        public String getAnnualSummaryId() {
                return this.annualSummaryId;
        }

        /**
         * 设置annualSummaryId
         * @param annualSummaryId annualSummaryId
         */
        public void setAnnualSummaryId(String annualSummaryId) {
                this.annualSummaryId = annualSummaryId;
        }

        /**
         * 返回bmAnnualPlan
         * @return bmAnnualPlan
         */
        public AnnualPlan getBmAnnualPlan() {
                return this.bmAnnualPlan;
        }

        /**
         * 设置bmAnnualPlan
         * @param bmAnnualPlan bmAnnualPlan
         */
        public void setBmAnnualPlan(AnnualPlan bmAnnualPlan) {
                this.bmAnnualPlan = bmAnnualPlan;
        }

        /**
         * 返回annualSummaryTitle
         * @return annualSummaryTitle
         */
        public String getAnnualSummaryTitle() {
                return this.annualSummaryTitle;
        }

        /**
         * 设置annualSummaryTitle
         * @param annualSummaryTitle annualSummaryTitle
         */
        public void setAnnualSummaryTitle(String annualSummaryTitle) {
                this.annualSummaryTitle = annualSummaryTitle;
        }

        /**
         * 返回annualSummaryYear
         * @return annualSummaryYear
         */
        public Integer getAnnualSummaryYear() {
                return this.annualSummaryYear;
        }

        /**
         * 设置annualSummaryYear
         * @param annualSummaryYear annualSummaryYear
         */
        public void setAnnualSummaryYear(Integer annualSummaryYear) {
                this.annualSummaryYear = annualSummaryYear;
        }

        /**
         * 返回annualSummaryContent
         * @return annualSummaryContent
         */
        public String getAnnualSummaryContent() {
                return this.annualSummaryContent;
        }

        /**
         * 设置annualSummaryContent
         * @param annualSummaryContent annualSummaryContent
         */
        public void setAnnualSummaryContent(String annualSummaryContent) {
                this.annualSummaryContent = annualSummaryContent;
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
         * 返回modiryTime
         * @return modiryTime
         */
        public Date getModifyTime() {
                return this.modifyTime;
        }

        /**
         * 设置modiryTime
         * @param modiryTime modiryTime
         */
        public void setModifyTime(Date modiryTime) {
                this.modifyTime = modiryTime;
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
		 * @return 返回annualSummaryContentPre
		 */
		public String getAnnualSummaryContentPre() {
			return annualSummaryContentPre;
		}

		/**
		 * @param annualSummaryContentPre 设置annualSummaryContentPre
		 */
		public void setAnnualSummaryContentPre(String annualSummaryContentPre) {
			this.annualSummaryContentPre = annualSummaryContentPre;
		}
}
