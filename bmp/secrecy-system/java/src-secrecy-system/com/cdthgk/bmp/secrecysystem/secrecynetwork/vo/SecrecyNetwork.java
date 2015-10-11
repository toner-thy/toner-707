package com.cdthgk.bmp.secrecysystem.secrecynetwork.vo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.permission.user.domain.User;

/**
 * SecrecyNetwork
 */
public class SecrecyNetwork implements java.io.Serializable {

	private static final long serialVersionUID = 7320222160360341388L;

	private String secrecyNetworkId;

	private String name;

	private Integer secrecyLevel;

	private String secrecyLevelTxt;

	private Integer networkType;

	private String networkTypeTxt;

	private Integer networkNum;

	private Integer isReview;

	private String isReviewTxt;

	private Date reviewTime;

	private String reviewOrgan;

	private Integer isApproval;

	private String isApprovalTxt;

	private Date approvalTime;

	private String approvalNo;

	private Date startUseDate;

	private Department department;

	private User createPerson;

	private Date createTime;

	private User modifyPerson;

	private Date modifyTime;

	private Integer dataState;

	private Organ createOrgan;

	private Organ modifyOrgan;

	private Integer secrecyStatus;

	private Set<SecrecyNetworkChange> secrecyNetworkChanges = new HashSet<SecrecyNetworkChange>(
			0);

	private Set<SecrecyNetworkterminal> secrecyNetworkterminals = new HashSet<SecrecyNetworkterminal>(
			0);

	private Set<SecrecyNetworkClear> secrecyNetworkClears = new HashSet<SecrecyNetworkClear>(
			0);

	/**
	 * 默认构�?函数
	 */
	public SecrecyNetwork() {
	}

	/**
	 * @return 返回secrecyNetworkId
	 */
	public String getSecrecyNetworkId() {
		return secrecyNetworkId;
	}


	/**
	 * @param secrecyNetworkId 设置secrecyNetworkId
	 */
	public void setSecrecyNetworkId(String secrecyNetworkId) {
		this.secrecyNetworkId = secrecyNetworkId;
	}


	/**
	 * @return 返回secrecyLevel
	 */
	public Integer getSecrecyLevel() {
		return secrecyLevel;
	}

	/**
	 * @param secrecyLevel 设置secrecyLevel
	 */
	public void setSecrecyLevel(Integer secrecyLevel) {
		this.secrecyLevel = secrecyLevel;
	}

	/**
	 * @return 返回networkType
	 */
	public Integer getNetworkType() {
		return networkType;
	}

	/**
	 * @param networkType 设置networkType
	 */
	public void setNetworkType(Integer networkType) {
		this.networkType = networkType;
	}

	/**
	 * @return 返回networkNum
	 */
	public Integer getNetworkNum() {
		return networkNum;
	}

	/**
	 * @param networkNum 设置networkNum
	 */
	public void setNetworkNum(Integer networkNum) {
		this.networkNum = networkNum;
	}

	/**
	 * @return 返回isReview
	 */
	public Integer getIsReview() {
		return isReview;
	}

	/**
	 * @param isReview 设置isReview
	 */
	public void setIsReview(Integer isReview) {
		this.isReview = isReview;
	}

	/**
	 * @return 返回isApproval
	 */
	public Integer getIsApproval() {
		return isApproval;
	}

	/**
	 * @param isApproval 设置isApproval
	 */
	public void setIsApproval(Integer isApproval) {
		this.isApproval = isApproval;
	}

	/**
	 * @return 返回startUseDate
	 */
	public Date getStartUseDate() {
		return startUseDate;
	}

	/**
	 * @param startUseDate 设置startUseDate
	 */
	public void setStartUseDate(Date startUseDate) {
		this.startUseDate = startUseDate;
	}

	/**
	 * @return 返回department
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * @param department 设置department
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}


	/**
	 * @return 返回createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime 设置createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return 返回createPerson
	 */
	public User getCreatePerson() {
		return createPerson;
	}

	/**
	 * @param createPerson 设置createPerson
	 */
	public void setCreatePerson(User createPerson) {
		this.createPerson = createPerson;
	}

	/**
	 * @return 返回modifyPerson
	 */
	public User getModifyPerson() {
		return modifyPerson;
	}

	/**
	 * @param modifyPerson 设置modifyPerson
	 */
	public void setModifyPerson(User modifyPerson) {
		this.modifyPerson = modifyPerson;
	}

	/**
	 * @return 返回modifyTime
	 */
	public Date getModifyTime() {
		return modifyTime;
	}

	/**
	 * @param modifyTime 设置modifyTime
	 */
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	/**
	 * @return 返回dataState
	 */
	public Integer getDataState() {
		return dataState;
	}

	/**
	 * @param dataState 设置dataState
	 */
	public void setDataState(Integer dataState) {
		this.dataState = dataState;
	}

	/**
	 * @return 返回createOrgan
	 */
	public Organ getCreateOrgan() {
		return createOrgan;
	}

	/**
	 * @param createOrgan 设置createOrgan
	 */
	public void setCreateOrgan(Organ createOrgan) {
		this.createOrgan = createOrgan;
	}

	/**
	 * @return 返回modifyOrgan
	 */
	public Organ getModifyOrgan() {
		return modifyOrgan;
	}

	/**
	 * @param modifyOrgan 设置modifyOrgan
	 */
	public void setModifyOrgan(Organ modifyOrgan) {
		this.modifyOrgan = modifyOrgan;
	}

	/**
	 * @return 返回secrecyStatus
	 */
	public Integer getSecrecyStatus() {
		return secrecyStatus;
	}

	/**
	 * @param secrecyStatus 设置secrecyStatus
	 */
	public void setSecrecyStatus(Integer secrecyStatus) {
		this.secrecyStatus = secrecyStatus;
	}

	/**
	 * @return 返回secrecyNetworkChanges
	 */
	public Set<SecrecyNetworkChange> getSecrecyNetworkChanges() {
		return secrecyNetworkChanges;
	}

	/**
	 * @param secrecyNetworkChanges 设置secrecyNetworkChanges
	 */
	public void setSecrecyNetworkChanges(
			Set<SecrecyNetworkChange> secrecyNetworkChanges) {
		this.secrecyNetworkChanges = secrecyNetworkChanges;
	}

	/**
	 * @return 返回secrecyNetworkterminals
	 */
	public Set<SecrecyNetworkterminal> getSecrecyNetworkterminals() {
		return secrecyNetworkterminals;
	}

	/**
	 * @param secrecyNetworkterminals 设置secrecyNetworkterminals
	 */
	public void setSecrecyNetworkterminals(
			Set<SecrecyNetworkterminal> secrecyNetworkterminals) {
		this.secrecyNetworkterminals = secrecyNetworkterminals;
	}

	/**
	 * @return 返回secrecyNetworkClears
	 */
	public Set<SecrecyNetworkClear> getSecrecyNetworkClears() {
		return secrecyNetworkClears;
	}

	/**
	 * @param secrecyNetworkClears 设置secrecyNetworkClears
	 */
	public void setSecrecyNetworkClears(
			Set<SecrecyNetworkClear> secrecyNetworkClears) {
		this.secrecyNetworkClears = secrecyNetworkClears;
	}

	/**
	 * @return 返回name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name 设置name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return 返回reviewTime
	 */
	public Date getReviewTime() {
		return reviewTime;
	}

	/**
	 * @param reviewTime 设置reviewTime
	 */
	public void setReviewTime(Date reviewTime) {
		this.reviewTime = reviewTime;
	}


	public String getReviewOrgan() {
		return reviewOrgan;
	}

	public void setReviewOrgan(String reviewOrgan) {
		this.reviewOrgan = reviewOrgan;
	}

	/**
	 * @return 返回approvalTime
	 */
	public Date getApprovalTime() {
		return approvalTime;
	}

	/**
	 * @param approvalTime 设置approvalTime
	 */
	public void setApprovalTime(Date approvalTime) {
		this.approvalTime = approvalTime;
	}

	/**
	 * @return 返回approvalNo
	 */
	public String getApprovalNo() {
		return approvalNo;
	}

	/**
	 * @param approvalNo 设置approvalNo
	 */
	public void setApprovalNo(String approvalNo) {
		this.approvalNo = approvalNo;
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
         * @return the networkTypeTxt
         */
        public String getNetworkTypeTxt() {
                return networkTypeTxt;
        }

        /**
         * @param networkTypeTxt the networkTypeTxt to set
         */
        public void setNetworkTypeTxt(String networkTypeTxt) {
                this.networkTypeTxt = networkTypeTxt;
        }

        /**
         * @return the isReviewTxt
         */
        public String getIsReviewTxt() {
                return isReviewTxt;
        }

        /**
         * @param isReviewTxt the isReviewTxt to set
         */
        public void setIsReviewTxt(String isReviewTxt) {
                this.isReviewTxt = isReviewTxt;
        }

        /**
         * @return the isApprovalTxt
         */
        public String getIsApprovalTxt() {
                return isApprovalTxt;
        }

        /**
         * @param isApprovalTxt the isApprovalTxt to set
         */
        public void setIsApprovalTxt(String isApprovalTxt) {
                this.isApprovalTxt = isApprovalTxt;
        }


}
