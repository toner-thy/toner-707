package com.cdthgk.bmp.planSndSummary.annualPlan.vo;
// default package
// Generated 2014-4-29 10:31:38 ----- 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.cdthgk.bmp.planSndSummary.annualSummary.vo.AnnualSummary;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;

/**
 * BmAnnualPlan
 */
public class AnnualPlan implements java.io.Serializable {

        //��ȹ����ƻ� ID.
        private String annualPlanId;

        //��ȹ����ƻ�����.
        private String annualPlanTitle;

        //���.
        private Integer annualPlanYear;

        //��ȹ����ƻ�����.
        private String annualPlanContent;

        private String annualPlanContentPre;

        //״̬.
        private Integer status;

        //������.
        private UserInfo createPerson;

        //����ʱ��.
        private Date createTime;

        //�޸���.
        private UserInfo modifyPerson;

        //�޸�ʱ��.
        private Date modifyTime;

        //������λ.
        private Organ createOrgan;

        private Set<AnnualSummary> bmAnnualSummaries = new HashSet<AnnualSummary>(0);

        /**
         * 默认构�?函数
         */
        public AnnualPlan() {
        }

        /**
         * 返回annualPlanId
         * @return annualPlanId
         */
        public String getAnnualPlanId() {
                return this.annualPlanId;
        }

        /**
         * 设置annualPlanId
         * @param annualPlanId annualPlanId
         */
        public void setAnnualPlanId(String annualPlanId) {
                this.annualPlanId = annualPlanId;
        }

        /**
         * 返回annualPlanTitle
         * @return annualPlanTitle
         */
        public String getAnnualPlanTitle() {
                return this.annualPlanTitle;
        }

        /**
         * 设置annualPlanTitle
         * @param annualPlanTitle annualPlanTitle
         */
        public void setAnnualPlanTitle(String annualPlanTitle) {
                this.annualPlanTitle = annualPlanTitle;
        }

        /**
         * 返回annualPlanYear
         * @return annualPlanYear
         */
        public Integer getAnnualPlanYear() {
                return this.annualPlanYear;
        }

        /**
         * 设置annualPlanYear
         * @param annualPlanYear annualPlanYear
         */
        public void setAnnualPlanYear(Integer annualPlanYear) {
                this.annualPlanYear = annualPlanYear;
        }

        /**
         * 返回annualPlanContent
         * @return annualPlanContent
         */
        public String getAnnualPlanContent() {
                return this.annualPlanContent;
        }

        /**
         * 设置annualPlanContent
         * @param annualPlanContent annualPlanContent
         */
        public void setAnnualPlanContent(String annualPlanContent) {
                this.annualPlanContent = annualPlanContent;
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
         * 返回bmAnnualSummaries
         * @return bmAnnualSummaries
         */
        public Set<AnnualSummary> getBmAnnualSummaries() {
                return this.bmAnnualSummaries;
        }

        /**
         * 设置bmAnnualSummaries
         * @param bmAnnualSummaries bmAnnualSummaries
         */
        public void setBmAnnualSummaries(Set<AnnualSummary> bmAnnualSummaries) {
                this.bmAnnualSummaries = bmAnnualSummaries;
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
		 * @return 返回annualPlanContentPre
		 */
		public String getAnnualPlanContentPre() {
			return annualPlanContentPre;
		}

		/**
		 * @param annualPlanContentPre 设置annualPlanContentPre
		 */
		public void setAnnualPlanContentPre(String annualPlanContentPre) {
			this.annualPlanContentPre = annualPlanContentPre;
		}
}
