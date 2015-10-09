package com.cdthgk.bmp.secrecyresearchproject.vo;
// default package
// Generated 2013-7-15 14:28:32 ----- 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;
import com.cdthgk.platform.permission.user.domain.User;

/**
 * SecrecyResearchProject  涉密项目
 */
public class SecrecyResearchProject implements java.io.Serializable {

	private static final long serialVersionUID = 2075745516344874521L;

	private String secrecyResearchProjectId; //涉密科研项目id
	private String secrecyResearchProjectName; //涉密科研项目名称
	private UserInfo formulateSecrecyPerson; //定密负责人
	private Integer secrecyLevel; //密级
	private String secrecyLevelTxt;
	private Integer secrecyLimit; //项目保密期限
	private Integer limitType; //期限单位
	private String limitTypeTxt;
	private Date secrecyLimitBeginDate; //保密期限起
	private Date secrecyLimitEndDate; //保密期限止
	private UserInfo projectPerson; //项目负责人
	private Integer projectState; //项目状态
	private String projectStateTxt;
	private String content; //内容
	private Department departId; //部门
	private Integer secrecyStatus; //解密状态

	private User createPerson;
	private Date createTime;
	private Organ createOrgan;
	private User modifyPerson;
	private Date modifyTime;
	private Organ modifyOrgan;
	private Integer dataState;

    //涉密项目密级解除
	private Set<SecrecyResearchProjectClear> secrecyResearchProjectClears = new HashSet<SecrecyResearchProjectClear>(0);
	//涉密项目密级变更
	private Set<SecrecyResearchProjectChange> secrecyResearchProjectChanges = new HashSet<SecrecyResearchProjectChange>(0);

	/**
	 * 默认构函数
	 */
	public SecrecyResearchProject() {
	}

	public String getSecrecyResearchProjectId() {
		return secrecyResearchProjectId;
	}

	public void setSecrecyResearchProjectId(String secrecyResearchProjectId) {
		this.secrecyResearchProjectId = secrecyResearchProjectId;
	}

	public String getSecrecyResearchProjectName() {
		return secrecyResearchProjectName;
	}

	public void setSecrecyResearchProjectName(String secrecyResearchProjectName) {
		this.secrecyResearchProjectName = secrecyResearchProjectName;
	}

	public UserInfo getFormulateSecrecyPerson() {
		return formulateSecrecyPerson;
	}

	public void setFormulateSecrecyPerson(UserInfo formulateSecrecyPerson) {
		this.formulateSecrecyPerson = formulateSecrecyPerson;
	}

	public Integer getSecrecyLevel() {
		return secrecyLevel;
	}

	public void setSecrecyLevel(Integer secrecyLevel) {
		this.secrecyLevel = secrecyLevel;
	}

	public Integer getSecrecyLimit() {
		return secrecyLimit;
	}

	public void setSecrecyLimit(Integer secrecyLimit) {
		this.secrecyLimit = secrecyLimit;
	}

	public Integer getLimitType() {
		return limitType;
	}

	public void setLimitType(Integer limitType) {
		this.limitType = limitType;
	}

	public Date getSecrecyLimitBeginDate() {
		return secrecyLimitBeginDate;
	}

	public void setSecrecyLimitBeginDate(Date secrecyLimitBeginDate) {
		this.secrecyLimitBeginDate = secrecyLimitBeginDate;
	}

	public Date getSecrecyLimitEndDate() {
		return secrecyLimitEndDate;
	}

	public void setSecrecyLimitEndDate(Date secrecyLimitEndDate) {
		this.secrecyLimitEndDate = secrecyLimitEndDate;
	}

	public UserInfo getProjectPerson() {
		return projectPerson;
	}

	public void setProjectPerson(UserInfo projectPerson) {
		this.projectPerson = projectPerson;
	}

	public Integer getProjectState() {
		return projectState;
	}

	public void setProjectState(Integer projectState) {
		this.projectState = projectState;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Department getDepartId() {
		return departId;
	}

	public void setDepartId(Department departId) {
		this.departId = departId;
	}

	public Integer getSecrecyStatus() {
		return secrecyStatus;
	}

	public void setSecrecyStatus(Integer secrecyStatus) {
		this.secrecyStatus = secrecyStatus;
	}

	public User getCreatePerson() {
		return createPerson;
	}

	public void setCreatePerson(User createPerson) {
		this.createPerson = createPerson;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Organ getCreateOrgan() {
		return createOrgan;
	}

	public void setCreateOrgan(Organ createOrgan) {
		this.createOrgan = createOrgan;
	}

	public User getModifyPerson() {
		return modifyPerson;
	}

	public void setModifyPerson(User modifyPerson) {
		this.modifyPerson = modifyPerson;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Organ getModifyOrgan() {
		return modifyOrgan;
	}

	public void setModifyOrgan(Organ modifyOrgan) {
		this.modifyOrgan = modifyOrgan;
	}

	public Integer getDataState() {
		return dataState;
	}

	public void setDataState(Integer dataState) {
		this.dataState = dataState;
	}

	public Set<SecrecyResearchProjectClear> getSecrecyResearchProjectClears() {
		return secrecyResearchProjectClears;
	}

	public void setSecrecyResearchProjectClears(
			Set<SecrecyResearchProjectClear> secrecyResearchProjectClears) {
		this.secrecyResearchProjectClears = secrecyResearchProjectClears;
	}

	public Set<SecrecyResearchProjectChange> getSecrecyResearchProjectChanges() {
		return secrecyResearchProjectChanges;
	}

	public void setSecrecyResearchProjectChanges(
			Set<SecrecyResearchProjectChange> secrecyResearchProjectChanges) {
		this.secrecyResearchProjectChanges = secrecyResearchProjectChanges;
	}

        /**
         * @return the secrecyLevelTxt
         */
        public String getSecrecyLevelTxt() {
                return secrecyLevelTxt;
        }

        /**
         * @param secrecyLevelTxt the secrecyLevelTxt to set
         */
        public void setSecrecyLevelTxt(String secrecyLevelTxt) {
                this.secrecyLevelTxt = secrecyLevelTxt;
        }

        /**
         * @return the limitTypeTxt
         */
        public String getLimitTypeTxt() {
                return limitTypeTxt;
        }

        /**
         * @param limitTypeTxt the limitTypeTxt to set
         */
        public void setLimitTypeTxt(String limitTypeTxt) {
                this.limitTypeTxt = limitTypeTxt;
        }

        /**
         * @return the projectStateTxt
         */
        public String getProjectStateTxt() {
                return projectStateTxt;
        }

        /**
         * @param projectStateTxt the projectStateTxt to set
         */
        public void setProjectStateTxt(String projectStateTxt) {
                this.projectStateTxt = projectStateTxt;
        }



}
