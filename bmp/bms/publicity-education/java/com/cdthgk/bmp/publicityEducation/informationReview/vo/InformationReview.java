package com.cdthgk.bmp.publicityEducation.informationReview.vo;

import java.util.Date;

import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;

/**
 * BmInformationReview
 */
public class InformationReview implements java.io.Serializable {

        private String informationReviewId;

        //����.
        private String title;

        //��Ϣ����.
        private String content;

        //��Ϣ��Դ���ֵ�?��ѡ��.
        private String informationSources;
        private String informationSourcesTxt;

        //�а첿��.
//        private String undertakingDepartment;
        private Department undertakingDepartment;

        //����;��(�ֵ�?��ѡ).
        private String releaseWay;
        private String releaseWayTxt;

        //���ų������.
        private String initialOpinions;

        //רҵ�����������.
        private String professionalOpinion;

        //��λ�쵼ǩ�����.
        private String unitLeadersOpinions;

        //备注
        private String remark;

        //״̬.
        private Integer status;

        private UserInfo createPerson;

        private Date createTime;

        private UserInfo modifyPerson;

        private Date modifyTime;

        private Organ createOrgan;


        /**
         *
         */
        public InformationReview() {

        }

        /**
         * 返回informationReviewId
         * @return informationReviewId
         */
        public String getInformationReviewId() {
                return this.informationReviewId;
        }

        /**
         * 设置informationReviewId
         * @param informationReviewId informationReviewId
         */
        public void setInformationReviewId(String informationReviewId) {
                this.informationReviewId = informationReviewId;
        }

        /**
         * 返回title
         * @return title
         */
        public String getTitle() {
                return this.title;
        }

        /**
         * 设置title
         * @param title title
         */
        public void setTitle(String title) {
                this.title = title;
        }

        /**
         * 返回content
         * @return content
         */
        public String getContent() {
                return this.content;
        }

        /**
         * 设置content
         * @param content content
         */
        public void setContent(String content) {
                this.content = content;
        }

        /**
         * 返回informationSources
         * @return informationSources
         */
        public String getInformationSources() {
                return this.informationSources;
        }

        /**
         * 设置informationSources
         * @param informationSources informationSources
         */
        public void setInformationSources(String informationSources) {
                this.informationSources = informationSources;
        }

        /**
         * @return the undertakingDepartment
         */
        public Department getUndertakingDepartment() {
                return undertakingDepartment;
        }

        /**
         * @param undertakingDepartment the undertakingDepartment to set
         */
        public void setUndertakingDepartment(Department undertakingDepartment) {
                this.undertakingDepartment = undertakingDepartment;
        }

        /**
         * 返回releaseWay
         * @return releaseWay
         */
        public String getReleaseWay() {
                return this.releaseWay;
        }

        /**
         * 设置releaseWay
         * @param releaseWay releaseWay
         */
        public void setReleaseWay(String releaseWay) {
                this.releaseWay = releaseWay;
        }

        /**
         * 返回initialOpinions
         * @return initialOpinions
         */
        public String getInitialOpinions() {
                return this.initialOpinions;
        }

        /**
         * 设置initialOpinions
         * @param initialOpinions initialOpinions
         */
        public void setInitialOpinions(String initialOpinions) {
                this.initialOpinions = initialOpinions;
        }

        /**
         * 返回professionalOpinion
         * @return professionalOpinion
         */
        public String getProfessionalOpinion() {
                return this.professionalOpinion;
        }

        /**
         * 设置professionalOpinion
         * @param professionalOpinion professionalOpinion
         */
        public void setProfessionalOpinion(String professionalOpinion) {
                this.professionalOpinion = professionalOpinion;
        }

        /**
         * 返回unitLeadersOpinions
         * @return unitLeadersOpinions
         */
        public String getUnitLeadersOpinions() {
                return this.unitLeadersOpinions;
        }

        /**
         * 设置unitLeadersOpinions
         * @param unitLeadersOpinions unitLeadersOpinions
         */
        public void setUnitLeadersOpinions(String unitLeadersOpinions) {
                this.unitLeadersOpinions = unitLeadersOpinions;
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

        /**
         * @return the informationSourcesTxt
         */
        public String getInformationSourcesTxt() {
                return informationSourcesTxt;
        }

        /**
         * @param informationSourcesTxt the informationSourcesTxt to set
         */
        public void setInformationSourcesTxt(String informationSourcesTxt) {
                this.informationSourcesTxt = informationSourcesTxt;
        }

        /**
         * @return the releaseWayTxt
         */
        public String getReleaseWayTxt() {
                return releaseWayTxt;
        }

        /**
         * @param releaseWayTxt the releaseWayTxt to set
         */
        public void setReleaseWayTxt(String releaseWayTxt) {
                this.releaseWayTxt = releaseWayTxt;
        }

        /**
         * @return the remark
         */
        public String getRemark() {
                return remark;
        }

        /**
         * @param remark the remark to set
         */
        public void setRemark(String remark) {
                this.remark = remark;
        }



}
