package com.cdthgk.bmp.secrecyproducts.vo;
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
 * SecrecyProducts   密品
 */
public class SecrecyProducts implements java.io.Serializable {

	private static final long serialVersionUID = -3381954569444994910L;

	private String secrecyproductsId;   //密品id
	private String secrecyproductsName;  //密品名称
	private UserInfo formulatesecrecyPerson;  //定密负责人

	private Integer secrecyLevel;  //密级(3秘密，2机密，1绝密)
	private String secrecyLevelTxt;
	private Integer secrecyLimit;  //保密期限
	private Integer limitType;  //期限单位（1年，2月，3日）
	private String limitTypeTxt;
	private Date secrecyLimitBegindate;  //保密期限起
	private Date secrecyLimitEnddate; //保密期限止
	private String fileNo;   //文号
	private Integer vectorform; //载体形式
	private Integer vectorformNum;  //数量
	private String content;   //内容
	private Department departId;  //部门名称
	private Integer secrecyStatus;  //解密状态

	private User createPerson;
	private Date createTime;
	private Organ createOrgan;
	private User modifyPerson;
	private Date modifyTime;
	private Organ modifyOrgan;
	private Integer dataState;


	//密级变更
	private Set<SecrecyProductsChange> secrecyProductsChanges = new HashSet<SecrecyProductsChange>(0);
	//密级解除
	private Set<SecrecyProductsClear> secrecyProductsClears = new HashSet<SecrecyProductsClear>(0);

	/**
	 * 默认构函数
	 */
	public SecrecyProducts() {
	}

	/**
	 * 返回secrecyproductsId
	 * @return secrecyproductsId
	 */
	public String getSecrecyproductsId() {
		return this.secrecyproductsId;
	}

	/**
	 * 设置secrecyproductsId
	 * @param secrecyproductsId secrecyproductsId
	 */
	public void setSecrecyproductsId(String secrecyproductsId) {
		this.secrecyproductsId = secrecyproductsId;
	}

	/**
	 * 返回secrecyproductsName
	 * @return secrecyproductsName
	 */
	public String getSecrecyproductsName() {
		return this.secrecyproductsName;
	}

	/**
	 * 设置secrecyproductsName
	 * @param secrecyproductsName secrecyproductsName
	 */
	public void setSecrecyproductsName(String secrecyproductsName) {
		this.secrecyproductsName = secrecyproductsName;
	}


	/**
	 * 返回secrecyLevel
	 * @return secrecyLevel
	 */
	public Integer getSecrecyLevel() {
		return this.secrecyLevel;
	}

	/**
	 * 设置secrecyLevel
	 * @param secrecyLevel secrecyLevel
	 */
	public void setSecrecyLevel(Integer secrecyLevel) {
		this.secrecyLevel = secrecyLevel;
	}

	/**
	 * 返回secrecyLimit
	 * @return secrecyLimit
	 */
	public Integer getSecrecyLimit() {
		return this.secrecyLimit;
	}

	/**
	 * 设置secrecyLimit
	 * @param secrecyLimit secrecyLimit
	 */
	public void setSecrecyLimit(Integer secrecyLimit) {
		this.secrecyLimit = secrecyLimit;
	}

	/**
	 * 返回limitType
	 * @return limitType
	 */
	public Integer getLimitType() {
		return this.limitType;
	}

	/**
	 * 设置limitType
	 * @param limitType limitType
	 */
	public void setLimitType(Integer limitType) {
		this.limitType = limitType;
	}

	/**
	 * 返回secrecyLimitBegindate
	 * @return secrecyLimitBegindate
	 */
	public Date getSecrecyLimitBegindate() {
		return this.secrecyLimitBegindate;
	}

	/**
	 * 设置secrecyLimitBegindate
	 * @param secrecyLimitBegindate secrecyLimitBegindate
	 */
	public void setSecrecyLimitBegindate(Date secrecyLimitBegindate) {
		this.secrecyLimitBegindate = secrecyLimitBegindate;
	}

	/**
	 * 返回secrecyLimitEnddate
	 * @return secrecyLimitEnddate
	 */
	public Date getSecrecyLimitEnddate() {
		return this.secrecyLimitEnddate;
	}

	/**
	 * 设置secrecyLimitEnddate
	 * @param secrecyLimitEnddate secrecyLimitEnddate
	 */
	public void setSecrecyLimitEnddate(Date secrecyLimitEnddate) {
		this.secrecyLimitEnddate = secrecyLimitEnddate;
	}

	/**
	 * 返回fileNo
	 * @return fileNo
	 */
	public String getFileNo() {
		return this.fileNo;
	}

	/**
	 * 设置fileNo
	 * @param fileNo fileNo
	 */
	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}

	/**
	 * 返回vectorform
	 * @return vectorform
	 */
	public Integer getVectorform() {
		return this.vectorform;
	}

	/**
	 * 设置vectorform
	 * @param vectorform vectorform
	 */
	public void setVectorform(Integer vectorform) {
		this.vectorform = vectorform;
	}

	/**
	 * 返回vectorformNum
	 * @return vectorformNum
	 */
	public Integer getVectorformNum() {
		return this.vectorformNum;
	}

	/**
	 * 设置vectorformNum
	 * @param vectorformNum vectorformNum
	 */
	public void setVectorformNum(Integer vectorformNum) {
		this.vectorformNum = vectorformNum;
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
	 * 返回departId
	 * @return departId
	 */
	public Department getDepartId() {
		return this.departId;
	}

	/**
	 * 设置departId
	 * @param departId departId
	 */
	public void setDepartId(Department departId) {
		this.departId = departId;
	}

	/**
	 * 返回createPerson
	 * @return createPerson
	 */
	public User getCreatePerson() {
		return this.createPerson;
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
	 * 返回modifyPerson
	 * @return modifyPerson
	 */
	public User getModifyPerson() {
		return this.modifyPerson;
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
	 * 返回dataState
	 * @return dataState
	 */
	public Integer getDataState() {
		return this.dataState;
	}

	/**
	 * 设置dataState
	 * @param dataState dataState
	 */
	public void setDataState(Integer dataState) {
		this.dataState = dataState;
	}

	/**
	 * 返回secrecyStatus
	 * @return secrecyStatus
	 */
	public Integer getSecrecyStatus() {
		return this.secrecyStatus;
	}

	/**
	 * 设置secrecyStatus
	 * @param secrecyStatus secrecyStatus
	 */
	public void setSecrecyStatus(Integer secrecyStatus) {
		this.secrecyStatus = secrecyStatus;
	}

	/**
	 * 返回createOrgan
	 * @return createOrgan
	 */
	public Organ getCreateOrgan() {
		return this.createOrgan;
	}

	/**
	 * 设置createOrgan
	 * @param createOrgan createOrgan
	 */
	public void setCreateOrgan(Organ createOrgan) {
		this.createOrgan = createOrgan;
	}

	/**
	 * 返回modifyOrgan
	 * @return modifyOrgan
	 */
	public Organ getModifyOrgan() {
		return this.modifyOrgan;
	}

	/**
	 * 设置modifyOrgan
	 * @param modifyOrgan modifyOrgan
	 */
	public void setModifyOrgan(Organ modifyOrgan) {
		this.modifyOrgan = modifyOrgan;
	}

	public Set<SecrecyProductsChange> getSecrecyProductsChanges() {
		return secrecyProductsChanges;
	}

	public void setSecrecyProductsChanges(
			Set<SecrecyProductsChange> secrecyProductsChanges) {
		this.secrecyProductsChanges = secrecyProductsChanges;
	}

	public Set<SecrecyProductsClear> getSecrecyProductsClears() {
		return secrecyProductsClears;
	}

	public void setSecrecyProductsClears(
			Set<SecrecyProductsClear> secrecyProductsClears) {
		this.secrecyProductsClears = secrecyProductsClears;
	}

	public UserInfo getFormulatesecrecyPerson() {
		return formulatesecrecyPerson;
	}

	public void setFormulatesecrecyPerson(UserInfo formulatesecrecyPerson) {
		this.formulatesecrecyPerson = formulatesecrecyPerson;
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



}
