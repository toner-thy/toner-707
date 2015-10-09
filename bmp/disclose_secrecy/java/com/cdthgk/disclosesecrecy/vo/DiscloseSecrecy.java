package com.cdthgk.disclosesecrecy.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;

public class DiscloseSecrecy implements Serializable {
	private static final long serialVersionUID = 1L;
	private String disclosesecrecycaseId;
	private String name;
	private Integer dealResult;//查处结果
	private String dealResultTxt;
	private Integer secrecyLevel;
	private String secrecyLevelTxt;
	private Integer casekind;//案件性质
	private String casekindTxt;
	private Integer caseType;//发案形式
	private String caseTypeTxt;

	private Integer dutyOrganKind;//责任单位性质
	private String dutyOrganKindTxt;
	private Department department;

	private Integer status;

	private Organ createOrgan;
	private UserInfo createPerson;
	private Date createTime;
	private UserInfo modifyPerson;

	private Date modifyTime;
	private Integer state;
	private Set<DiscloseSecrecyChange> discloseSecrecyChanges = new HashSet<DiscloseSecrecyChange>();
	private Set<DiscloseSecrecyClear> discloseSecrecyClears = new HashSet<DiscloseSecrecyClear>();
	/**
	 * 未发布
	 */
	public static final Integer PUBLISH_NO = 0;
	/**
	 * 已发布
	 */
	public static final Integer PUBLISH_YES = 1;

	// 构造器
	/** 默认构造器 */
	public DiscloseSecrecy() {

	}

	//********************************* getter and setter ********************************




	public void setState(Integer state) {
		this.state = state;
	}



	public String getDisclosesecrecycaseId() {
		return disclosesecrecycaseId;
	}

	public void setDisclosesecrecycaseId(String disclosesecrecycaseId) {
		this.disclosesecrecycaseId = disclosesecrecycaseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getDealResult() {
		return dealResult;
	}

	public void setDealResult(Integer dealResult) {
		this.dealResult = dealResult;
	}

	public Integer getCasekind() {
		return casekind;
	}

	public void setCasekind(Integer casekind) {
		this.casekind = casekind;
	}

	public Integer getCaseType() {
		return caseType;
	}

	public void setCaseType(Integer caseType) {
		this.caseType = caseType;
	}

	public Integer getDutyOrganKind() {
		return dutyOrganKind;
	}

	public void setDutyOrganKind(Integer dutyOrganKind) {
		this.dutyOrganKind = dutyOrganKind;
	}

	public Integer getState() {
		return state;
	}

	public Integer getSecrecyLevel() {
		return this.secrecyLevel;
	}

	public void setSecrecyLevel(Integer secrecyLevel) {
		this.secrecyLevel = secrecyLevel;
	}



	/**
         * @return 返回 dealResultTxt
         */
        public String getDealResultTxt() {
                return dealResultTxt;
        }

        /**
         * @param dealResultTxt 设置 dealResultTxt
         */
        public void setDealResultTxt(String dealResultTxt) {
                this.dealResultTxt = dealResultTxt;
        }

        /**
         * @return 返回 secrecyLevelTxt
         */
        public String getSecrecyLevelTxt() {
                return secrecyLevelTxt;
        }

        /**
         * @param secrecyLevelTxt 设置 secrecyLevelTxt
         */
        public void setSecrecyLevelTxt(String secrecyLevelTxt) {
                this.secrecyLevelTxt = secrecyLevelTxt;
        }

        /**
         * @return 返回 casekindTxt
         */
        public String getCasekindTxt() {
                return casekindTxt;
        }

        /**
         * @param casekindTxt 设置 casekindTxt
         */
        public void setCasekindTxt(String casekindTxt) {
                this.casekindTxt = casekindTxt;
        }

        /**
         * @return 返回 caseTypeTxt
         */
        public String getCaseTypeTxt() {
                return caseTypeTxt;
        }

        /**
         * @param caseTypeTxt 设置 caseTypeTxt
         */
        public void setCaseTypeTxt(String caseTypeTxt) {
                this.caseTypeTxt = caseTypeTxt;
        }

        /**
         * @return 返回 dutyOrganKindTxt
         */
        public String getDutyOrganKindTxt() {
                return dutyOrganKindTxt;
        }

        /**
         * @param dutyOrganKindTxt 设置 dutyOrganKindTxt
         */
        public void setDutyOrganKindTxt(String dutyOrganKindTxt) {
                this.dutyOrganKindTxt = dutyOrganKindTxt;
        }

        public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}



	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}


	public Organ getCreateOrgan() {
		return createOrgan;
	}

	public void setCreateOrgan(Organ createOrgan) {
		this.createOrgan = createOrgan;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Set<DiscloseSecrecyChange> getDiscloseSecrecyChanges() {
		return discloseSecrecyChanges;
	}

	public void setDiscloseSecrecyChanges(
			Set<DiscloseSecrecyChange> discloseSecrecyChanges) {
		this.discloseSecrecyChanges = discloseSecrecyChanges;
	}

	public Set<DiscloseSecrecyClear> getDiscloseSecrecyClears() {
		return discloseSecrecyClears;
	}

	public void setDiscloseSecrecyClears(
			Set<DiscloseSecrecyClear> discloseSecrecyClears) {
		this.discloseSecrecyClears = discloseSecrecyClears;
	}

	public UserInfo getCreatePerson() {
		return createPerson;
	}

	public void setCreatePerson(UserInfo createPerson) {
		this.createPerson = createPerson;
	}

	public UserInfo getModifyPerson() {
		return modifyPerson;
	}

	public void setModifyPerson(UserInfo modifyPerson) {
		this.modifyPerson = modifyPerson;
	}
}