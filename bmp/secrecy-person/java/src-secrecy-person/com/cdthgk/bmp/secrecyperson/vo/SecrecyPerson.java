package com.cdthgk.bmp.secrecyperson.vo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.cdthgk.platform.base.util.ReportState;
import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;
import com.cdthgk.platform.permission.user.domain.User;

/**
 * <p>
 * 机关涉密人员VO类
 * </p>
 * <p>
 * 牟远洋 2012-12-14 17:01
 * </p>
 * <blockquote>
 * <h4>历史修改记录</h4>
 * <ul>
 * <li>修改人 修改时间 修改描述
 * </ul>
 * </blockquote>
 * <p>
 * copyright tianyu 2012, all rights reserved.
 * </p>
 *
 * @author mouyuanyang
 * @since 1.0
 * @version 1.0
 */
public class SecrecyPerson extends ReportState implements java.io.Serializable{

	private static final long serialVersionUID = 5503434075904448193L;

	// 涉密人员ID
	private String secrecyPersonId;
	// 人员ID
	private UserInfo userInfo;

	private String sexTxt;
	private String learnLevelTxt;
	private String nationTxt;
	private String polityTxt;
	private String administrativeLevelTxt;
	private String personAge;

	// 行政职务
	private String officeDuty;
	// 参加工作日期
	private Date firstWorkDate;
	// 进入现涉密岗位日期
	private Date inNowPostTime;
	// 掌握外语
	private String foreignLanguage;
	// 熟悉程度
	private String familiarGrade;
	// 户籍派出所
	private String npPoliceStation;
	// 涉密程度
	private Integer secrecyPersonLevel;
	private String secrecyPersonLevelTxt;

	// 涉密工作年限
	private Integer secrecyWorkDate;
	// 接触最高涉密等级
	private Integer topSecrecyLevel;
	// 是否签订保密协议
	private Integer signSecrecyTreaty;
	// 签订保密协议日期
	private Date secrecyTreatyDate;
	// 填表日期
	private Date fillDate;
	// 脱密期年限
	private Integer outSecrecyYears;
	// 调离日期
	private Date transOutDate;
	// 政治面貌
	private String politicalStatus;
	// 政治面貌(20130428调整政治面貌为选取使用，防止老数据报错而加入的新字段)
	//20130801 userInfo表中已存在政治面貌字段，这里不应再使用
	@Deprecated
	private String politicalType;
	// 上岗证编号
	private String hasQualifCard;
	// 个人简介
	private String resume;
	// 其他需说明情况
	private String otherInfo;
	// 从事涉密工作简要情况
	private String secWorkInfo;
	// 单位审察意见
	private String organCheckOpinion;
	// 所在单位
	private Organ organ;
	// 所造部门
	private Department department;
	// 办公室电话
	private String officePhone;
	// 持证上岗培训时间
	private Date postTrainTime;
	// 是否签订离岗保密承诺
	private Integer signDimissionTreaty;
	// 岗位
	private String post;
	// 涉密状态̬
	private Integer secrecyStatus;
	// 签订保密责任书时间
	private Date secSignBookTime;
	// 取得上岗证书时间
	private Date secUppostTime;

	//人员类型
	private Integer personType;

	//是否属于要害部门工作人员
	private Integer isSecrecyDepWorker;

	//是否为定密责任人
	private Integer responsiblePerson;
	private String responsiblePersonTxt;

	// 通用字段
	private User createPerson;
	private Date createTime;
	private User modifyPerson;
	private Date modifyTime;

	public static final String IMPORT_SUCCESS_INFO = "导入成功!";

	public static final String IMPORT_FAILURE_INFO = "导入失败!";

	private Set<Organ> receiveOrgans = new HashSet<Organ>();


	// 构造器
	/** 默认构造器 */
	public SecrecyPerson() {
	}

	// ******************** Setter & Getter ********************
	/**
	 * 返回secrecyPersonId
	 * @return secrecyPersonId
	 */
	public String getSecrecyPersonId() {
		return secrecyPersonId;
	}

	/**
	 * 设置secrecyPersonId
	 * @param secrecyPersonId secrecyPersonId
	 */
	public void setSecrecyPersonId(String secrecyPersonId) {
		this.secrecyPersonId = secrecyPersonId;
	}

	/**
	 * 返回userInfo
	 * @return userInfo
	 */
	public UserInfo getUserInfo() {
		return userInfo;
	}

	/**
	 * 设置userInfo
	 * @param userInfo userInfo
	 */
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	/**
	 * 返回officeDuty
	 * @return officeDuty
	 */
	public String getOfficeDuty() {
		return officeDuty;
	}

	/**
	 * 设置officeDuty
	 * @param officeDuty officeDuty
	 */
	public void setOfficeDuty(String officeDuty) {
		this.officeDuty = officeDuty;
	}

	/**
	 * 返回firstWorkDate
	 * @return firstWorkDate
	 */
	public Date getFirstWorkDate() {
		return firstWorkDate;
	}

	/**
	 * 设置firstWorkDate
	 * @param firstWorkDate firstWorkDate
	 */
	public void setFirstWorkDate(Date firstWorkDate) {
		this.firstWorkDate = firstWorkDate;
	}

	/**
	 * 返回inNowPostTime
	 * @return inNowPostTime
	 */
	public Date getInNowPostTime() {
		return inNowPostTime;
	}

	/**
	 * 设置inNowPostTime
	 * @param inNowPostTime inNowPostTime
	 */
	public void setInNowPostTime(Date inNowPostTime) {
		this.inNowPostTime = inNowPostTime;
	}

	/**
	 * 返回foreignLanguage
	 * @return foreignLanguage
	 */
	public String getForeignLanguage() {
		return foreignLanguage;
	}

	/**
	 * 设置foreignLanguage
	 * @param foreignLanguage foreignLanguage
	 */
	public void setForeignLanguage(String foreignLanguage) {
		this.foreignLanguage = foreignLanguage;
	}

	/**
	 * 返回familiarGrade
	 * @return familiarGrade
	 */
	public String getFamiliarGrade() {
		return familiarGrade;
	}

	/**
	 * 设置familiarGrade
	 * @param familiarGrade familiarGrade
	 */
	public void setFamiliarGrade(String familiarGrade) {
		this.familiarGrade = familiarGrade;
	}

	/**
	 * 返回npPoliceStation
	 * @return npPoliceStation
	 */
	public String getNpPoliceStation() {
		return npPoliceStation;
	}

	/**
	 * 设置npPoliceStation
	 * @param npPoliceStation npPoliceStation
	 */
	public void setNpPoliceStation(String npPoliceStation) {
		this.npPoliceStation = npPoliceStation;
	}

	/**
	 * 返回secrecyPersonLevel
	 * @return secrecyPersonLevel
	 */
	public Integer getSecrecyPersonLevel() {
		return secrecyPersonLevel;
	}

	/**
	 * 设置secrecyPersonLevel
	 * @param secrecyPersonLevel secrecyPersonLevel
	 */
	public void setSecrecyPersonLevel(Integer secrecyPersonLevel) {
		this.secrecyPersonLevel = secrecyPersonLevel;
	}

	/**
	 * 返回secrecyWorkDate
	 * @return secrecyWorkDate
	 */
	public Integer getSecrecyWorkDate() {
		return secrecyWorkDate;
	}

	/**
	 * 设置secrecyWorkDate
	 * @param secrecyWorkDate secrecyWorkDate
	 */
	public void setSecrecyWorkDate(Integer secrecyWorkDate) {
		this.secrecyWorkDate = secrecyWorkDate;
	}

	/**
	 * 返回topSecrecyLevel
	 * @return topSecrecyLevel
	 */
	public Integer getTopSecrecyLevel() {
		return topSecrecyLevel;
	}

	/**
	 * 设置topSecrecyLevel
	 * @param topSecrecyLevel topSecrecyLevel
	 */
	public void setTopSecrecyLevel(Integer topSecrecyLevel) {
		this.topSecrecyLevel = topSecrecyLevel;
	}

	/**
	 * 返回signSecrecyTreaty
	 * @return signSecrecyTreaty
	 */
	public Integer getSignSecrecyTreaty() {
		return signSecrecyTreaty;
	}

	/**
	 * 设置signSecrecyTreaty
	 * @param signSecrecyTreaty signSecrecyTreaty
	 */
	public void setSignSecrecyTreaty(Integer signSecrecyTreaty) {
		this.signSecrecyTreaty = signSecrecyTreaty;
	}

	/**
	 * 返回secrecyTreatyDate
	 * @return secrecyTreatyDate
	 */
	public Date getSecrecyTreatyDate() {
		return secrecyTreatyDate;
	}

	/**
	 * 设置secrecyTreatyDate
	 * @param secrecyTreatyDate secrecyTreatyDate
	 */
	public void setSecrecyTreatyDate(Date secrecyTreatyDate) {
		this.secrecyTreatyDate = secrecyTreatyDate;
	}

	/**
	 * 返回fillDate
	 * @return fillDate
	 */
	public Date getFillDate() {
		return fillDate;
	}

	/**
	 * 设置fillDate
	 * @param fillDate fillDate
	 */
	public void setFillDate(Date fillDate) {
		this.fillDate = fillDate;
	}

	/**
	 * 返回outSecrecyYears
	 * @return outSecrecyYears
	 */
	public Integer getOutSecrecyYears() {
		return outSecrecyYears;
	}

	/**
	 * 设置outSecrecyYears
	 * @param outSecrecyYears outSecrecyYears
	 */
	public void setOutSecrecyYears(Integer outSecrecyYears) {
		this.outSecrecyYears = outSecrecyYears;
	}

	/**
	 * 返回transOutDate
	 * @return transOutDate
	 */
	public Date getTransOutDate() {
		return transOutDate;
	}

	/**
	 * 设置transOutDate
	 * @param transOutDate transOutDate
	 */
	public void setTransOutDate(Date transOutDate) {
		this.transOutDate = transOutDate;
	}

	/**
	 * 返回hasQualifCard
	 * @return hasQualifCard
	 */
	public String getHasQualifCard() {
		return hasQualifCard;
	}

	/**
	 * 设置hasQualifCard
	 * @param hasQualifCard hasQualifCard
	 */
	public void setHasQualifCard(String hasQualifCard) {
		this.hasQualifCard = hasQualifCard;
	}

	/**
	 * 返回resume
	 * @return resume
	 */
	public String getResume() {
		return resume;
	}

	/**
	 * 设置resume
	 * @param resume resume
	 */
	public void setResume(String resume) {
		this.resume = resume;
	}

	/**
	 * 返回otherInfo
	 * @return otherInfo
	 */
	public String getOtherInfo() {
		return otherInfo;
	}

	/**
	 * 设置otherInfo
	 * @param otherInfo otherInfo
	 */
	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}

	/**
	 * 返回secWorkInfo
	 * @return secWorkInfo
	 */
	public String getSecWorkInfo() {
		return secWorkInfo;
	}

	/**
	 * 设置secWorkInfo
	 * @param secWorkInfo secWorkInfo
	 */
	public void setSecWorkInfo(String secWorkInfo) {
		this.secWorkInfo = secWorkInfo;
	}

	/**
	 * 返回organCheckOpinion
	 * @return organCheckOpinion
	 */
	public String getOrganCheckOpinion() {
		return organCheckOpinion;
	}

	/**
	 * 设置organCheckOpinion
	 * @param organCheckOpinion organCheckOpinion
	 */
	public void setOrganCheckOpinion(String organCheckOpinion) {
		this.organCheckOpinion = organCheckOpinion;
	}

	/**
	 * 返回organ
	 * @return organ
	 */
	public Organ getOrgan() {
		return organ;
	}

	/**
	 * 设置organ
	 * @param organ organ
	 */
	public void setOrgan(Organ organ) {
		this.organ = organ;
	}

	/**
	 * 返回department
	 * @return department
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * 设置department
	 * @param department department
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}

	/**
	 * 返回officePhone
	 * @return officePhone
	 */
	public String getOfficePhone() {
		return officePhone;
	}

	/**
	 * 设置officePhone
	 * @param officePhone officePhone
	 */
	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	/**
	 * 返回postTrainTime
	 * @return postTrainTime
	 */
	public Date getPostTrainTime() {
		return postTrainTime;
	}

	/**
	 * 设置postTrainTime
	 * @param postTrainTime postTrainTime
	 */
	public void setPostTrainTime(Date postTrainTime) {
		this.postTrainTime = postTrainTime;
	}

	/**
	 * 返回signDimissionTreaty
	 * @return signDimissionTreaty
	 */
	public Integer getSignDimissionTreaty() {
		return signDimissionTreaty;
	}

	/**
	 * 设置signDimissionTreaty
	 * @param signDimissionTreaty signDimissionTreaty
	 */
	public void setSignDimissionTreaty(Integer signDimissionTreaty) {
		this.signDimissionTreaty = signDimissionTreaty;
	}

	/**
	 * 返回post
	 * @return post
	 */
	public String getPost() {
		return post;
	}

	/**
	 * 设置post
	 * @param post post
	 */
	public void setPost(String post) {
		this.post = post;
	}

	/**
	 * 返回secrecyStatus
	 * @return secrecyStatus
	 */
	public Integer getSecrecyStatus() {
		return secrecyStatus;
	}

	/**
	 * 设置secrecyStatus
	 * @param secrecyStatus secrecyStatus
	 */
	public void setSecrecyStatus(Integer secrecyStatus) {
		this.secrecyStatus = secrecyStatus;
	}

	/**
	 * 返回secSignBookTime
	 * @return secSignBookTime
	 */
	public Date getSecSignBookTime() {
		return secSignBookTime;
	}

	/**
	 * 设置secSignBookTime
	 * @param secSignBookTime secSignBookTime
	 */
	public void setSecSignBookTime(Date secSignBookTime) {
		this.secSignBookTime = secSignBookTime;
	}

	/**
	 * 返回secUppostTime
	 * @return secUppostTime
	 */
	public Date getSecUppostTime() {
		return secUppostTime;
	}

	/**
	 * 设置secUppostTime
	 * @param secUppostTime secUppostTime
	 */
	public void setSecUppostTime(Date secUppostTime) {
		this.secUppostTime = secUppostTime;
	}

	/**
	 * 返回createPerson
	 * @return createPerson
	 */
	public User getCreatePerson() {
		return createPerson;
	}

	/**
	 * 设置createPerson
	 * @param createPerson createPerson
	 */
	public void setCreatePerson(User createPerson) {
		this.createPerson = createPerson;
	}

	/**
	 * 返回createTime
	 * @return createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置createTime
	 * @param createTime createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 返回modifyPerson
	 * @return modifyPerson
	 */
	public User getModifyPerson() {
		return modifyPerson;
	}

	/**
	 * 设置modifyPerson
	 * @param modifyPerson modifyPerson
	 */
	public void setModifyPerson(User modifyPerson) {
		this.modifyPerson = modifyPerson;
	}

	/**
	 * 返回modifyTime
	 * @return modifyTime
	 */
	public Date getModifyTime() {
		return modifyTime;
	}

	/**
	 * 设置modifyTime
	 * @param modifyTime modifyTime
	 */
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	/**
	 * 返回receiveOrgans
	 * @return receiveOrgans
	 */
	public Set<Organ> getReceiveOrgans() {
		return receiveOrgans;
	}

	/**
	 * 设置receiveOrgans
	 * @param receiveOrgans receiveOrgans
	 */
	public void setReceiveOrgans(Set<Organ> receiveOrgans) {
		this.receiveOrgans = receiveOrgans;
	}

	/**
	 * 返回politicalStatus
	 * @return politicalStatus
	 */
	public String getPoliticalStatus() {
		return politicalStatus;
	}

	/**
	 * @return the politicalType
	 */
	public String getPoliticalType() {
		return politicalType;
	}

	/**
	 * @param politicalType the politicalType to set
	 */
	public void setPoliticalType(String politicalType) {
		this.politicalType = politicalType;
	}

	/**
	 * 设置politicalStatus
	 * @param politicalStatus politicalStatus
	 */
	public void setPoliticalStatus(String politicalStatus) {
		this.politicalStatus = politicalStatus;
	}

	/**
	 * @return the isSecrecyDepWorker
	 */
	public Integer getIsSecrecyDepWorker() {
		return isSecrecyDepWorker;
	}

	/**
	 * @param isSecrecyDepWorker the isSecrecyDepWorker to set
	 */
	public void setIsSecrecyDepWorker(Integer isSecrecyDepWorker) {
		this.isSecrecyDepWorker = isSecrecyDepWorker;
	}

	/**
	 * @return the responsiblePerson
	 */
	public Integer getResponsiblePerson() {
		return responsiblePerson;
	}

	/**
	 * @param responsiblePerson the responsiblePerson to set
	 */
	public void setResponsiblePerson(Integer responsiblePerson) {
		this.responsiblePerson = responsiblePerson;
	}

	/**
	 * @return the personType
	 */
	public Integer getPersonType() {
		return personType;
	}

	/**
	 * @param personType the personType to set
	 */
	public void setPersonType(Integer personType) {
		this.personType = personType;
	}

        /**
         * @return the sexTxt
         */
        public String getSexTxt() {
                return sexTxt;
        }

        /**
         * @param sexTxt the sexTxt to set
         */
        public void setSexTxt(String sexTxt) {
                this.sexTxt = sexTxt;
        }

        /**
         * @return the learnLevelTxt
         */
        public String getLearnLevelTxt() {
                return learnLevelTxt;
        }

        /**
         * @param learnLevelTxt the learnLevelTxt to set
         */
        public void setLearnLevelTxt(String learnLevelTxt) {
                this.learnLevelTxt = learnLevelTxt;
        }

        /**
         * @return the nationTxt
         */
        public String getNationTxt() {
                return nationTxt;
        }

        /**
         * @param nationTxt the nationTxt to set
         */
        public void setNationTxt(String nationTxt) {
                this.nationTxt = nationTxt;
        }

        /**
         * @return the polityTxt
         */
        public String getPolityTxt() {
                return polityTxt;
        }

        /**
         * @param polityTxt the polityTxt to set
         */
        public void setPolityTxt(String polityTxt) {
                this.polityTxt = polityTxt;
        }

        /**
         * @return the administrativeLevelTxt
         */
        public String getAdministrativeLevelTxt() {
                return administrativeLevelTxt;
        }

        /**
         * @param administrativeLevelTxt the administrativeLevelTxt to set
         */
        public void setAdministrativeLevelTxt(String administrativeLevelTxt) {
                this.administrativeLevelTxt = administrativeLevelTxt;
        }

        /**
         * @return the secrecyPersonLevelTxt
         */
        public String getSecrecyPersonLevelTxt() {
                return secrecyPersonLevelTxt;
        }

        /**
         * @param secrecyPersonLevelTxt the secrecyPersonLevelTxt to set
         */
        public void setSecrecyPersonLevelTxt(String secrecyPersonLevelTxt) {
                this.secrecyPersonLevelTxt = secrecyPersonLevelTxt;
        }

        /**
         * @return the responsiblePersonTxt
         */
        public String getResponsiblePersonTxt() {
                return responsiblePersonTxt;
        }

        /**
         * @param responsiblePersonTxt the responsiblePersonTxt to set
         */
        public void setResponsiblePersonTxt(String responsiblePersonTxt) {
                this.responsiblePersonTxt = responsiblePersonTxt;
        }

        /**
         * @return the personAge
         */
        public String getPersonAge() {
                return personAge;
        }

        /**
         * @param personAge the personAge to set
         */
        public void setPersonAge(String personAge) {
                this.personAge = personAge;
        }




}