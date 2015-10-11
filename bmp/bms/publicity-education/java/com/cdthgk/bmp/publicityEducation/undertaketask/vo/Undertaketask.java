package com.cdthgk.bmp.publicityEducation.undertaketask.vo;


import java.util.Date;
import java.util.List;

import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;

/**
 * BmUndertaketask
 */
public class Undertaketask implements java.io.Serializable {

	private static final long serialVersionUID = 4795343661337448160L;

		//id.
        private String undertaketaskId;

        //���⣨�Ե㣩���.
        private String taskName;

        //�´ﵥλ.
        private Organ releaseUnit;

        //�а���.
        private String undertaker;

        //���ʱ��.
        private Date completeTime;

        //�����Ч.
        private String specificResults;

        private Integer status;

        private UserInfo createPerson;

        private Date createTime;

        private UserInfo modifyPerson;

        private Date modifyTime;

        private Organ createOrgan;

        private String awards;
        private UserInfo projectLeader;
        private Organ undertakeOrgan;

        private List<UserInfo> undertakerList;
        private String undertakerUserTxt;

        /**
         * 默认构�?函数
         */
        public Undertaketask() {
        }



        /**
         * 返回undertaketaskId
         * @return undertaketaskId
         */
        public String getUndertaketaskId() {
                return this.undertaketaskId;
        }

        /**
         * 设置undertaketaskId
         * @param undertaketaskId undertaketaskId
         */
        public void setUndertaketaskId(String undertaketaskId) {
                this.undertaketaskId = undertaketaskId;
        }

        /**
         * 返回taskName
         * @return taskName
         */
        public String getTaskName() {
                return this.taskName;
        }

        /**
         * 设置taskName
         * @param taskName taskName
         */
        public void setTaskName(String taskName) {
                this.taskName = taskName;
        }


        /**
         * @return the releaseUnit
         */
        public Organ getReleaseUnit() {
                return releaseUnit;
        }

        /**
         * @param releaseUnit the releaseUnit to set
         */
        public void setReleaseUnit(Organ releaseUnit) {
                this.releaseUnit = releaseUnit;
        }

        /**
         * 返回undertaker
         * @return undertaker
         */
        public String getUndertaker() {
                return this.undertaker;
        }

        /**
         * 设置undertaker
         * @param undertaker undertaker
         */
        public void setUndertaker(String undertaker) {
                this.undertaker = undertaker;
        }

        /**
         * 返回completeTime
         * @return completeTime
         */
        public Date getCompleteTime() {
                return this.completeTime;
        }

        /**
         * 设置completeTime
         * @param completeTime completeTime
         */
        public void setCompleteTime(Date completeTime) {
                this.completeTime = completeTime;
        }

        /**
         * 返回specificResults
         * @return specificResults
         */
        public String getSpecificResults() {
                return this.specificResults;
        }

        /**
         * 设置specificResults
         * @param specificResults specificResults
         */
        public void setSpecificResults(String specificResults) {
                this.specificResults = specificResults;
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
         * @return the modiryPerson
         */
        public UserInfo getModifyPerson() {
                return modifyPerson;
        }

        /**
         * @param modiryPerson the modiryPerson to set
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
         * @return the undertakerList
         */
        public List<UserInfo> getUndertakerList() {
                return undertakerList;
        }

        /**
         * @param undertakerList the undertakerList to set
         */
        public void setUndertakerList(List<UserInfo> undertakerList) {
                this.undertakerList = undertakerList;
        }



        /**
         * @return the undertakerUserTxt
         */
        public String getUndertakerUserTxt() {
                return undertakerUserTxt;
        }



        /**
         * @param undertakerUserTxt the undertakerUserTxt to set
         */
        public void setUndertakerUserTxt(String undertakerUserTxt) {
                this.undertakerUserTxt = undertakerUserTxt;
        }



		public String getAwards() {
			return awards;
		}



		public void setAwards(String awards) {
			this.awards = awards;
		}



		public UserInfo getProjectLeader() {
			return projectLeader;
		}



		public void setProjectLeader(UserInfo projectLeader) {
			this.projectLeader = projectLeader;
		}



		public Organ getUndertakeOrgan() {
			return undertakeOrgan;
		}



		public void setUndertakeOrgan(Organ undertakeOrgan) {
			this.undertakeOrgan = undertakeOrgan;
		}



}
