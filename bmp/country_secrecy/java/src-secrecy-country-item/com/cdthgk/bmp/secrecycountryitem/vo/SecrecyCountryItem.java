package com.cdthgk.bmp.secrecycountryitem.vo;
// default package
// Generated 2013-7-15 14:28:32 ----- 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.cdthgk.bmp.keysection.vo.KeySection;
import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;
import com.cdthgk.platform.permission.user.domain.User;

/**
 * SecrecyCountryItem  商业秘密事项
 */
public class SecrecyCountryItem implements java.io.Serializable {

	private static final long serialVersionUID = -7366253845572345631L;

	private String secrecyCountryItemId; //商业秘密事项id
	private String secrecyCountryItemName; //商业秘密事项名称
	private UserInfo formulateSecrecyPerson; //定密负责人
	private Integer secrecyLevel; //密级(3秘密，2机密，1绝密)
	private String secrecyLevelTxt;
	private Integer secrecyLimitType; //保密期限类型(时限区域，解密时间，解密条件，其他(长期))
	private String secrecyLimitTypeTxt;
	private Integer secrecyLimit;  //保密期限
	private Integer limitType;  //期限单位（年，月，日）
	private String limitTypeTxt;
	private Date secrecyLimitBeginDate; //保密期限起
	private Date secrecyLimitEndDate; //保密期限止
	private Date removeSecrecyDate;  //解密时间
	private String removeSecrecyCondition; //解密条件
	private Integer isfromKeyDepartment;  //是否由保密要害部门产生(是,否)
	private Department departId;  //部门名称
	private String content;   //内容
	private Integer secrecyStatus;  //解密状态
	private KeySection keySectionId;   //要害部门  外键

	private User createPerson;  //创建人员
	private Date createTime;  //创建时间
	private Organ createOrgan;  //创建单位
	private User modifyPerson;  //修改人员
	private Date modifyTime;  //修改时间
	private Organ modifyOrgan; //修改单位
	private Integer dataState;


    //密级变更
	private Set<SecrecyCountryItemChange> secrecyCountryItemChanges = new HashSet<SecrecyCountryItemChange>(0);
	//密级解除
	private Set<SecrecyCountryItemClear> secrecyCountryItemClears = new HashSet<SecrecyCountryItemClear>(0);


	/**
	 * 默认构函数
	 */
	public SecrecyCountryItem() {
	}
	public String getSecrecyCountryItemId() {
		return secrecyCountryItemId;
	}

	public void setSecrecyCountryItemId(String secrecyCountryItemId) {
		this.secrecyCountryItemId = secrecyCountryItemId;
	}

	public String getSecrecyCountryItemName() {
		return secrecyCountryItemName;
	}

	public void setSecrecyCountryItemName(String secrecyCountryItemName) {
		this.secrecyCountryItemName = secrecyCountryItemName;
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

	public Integer getSecrecyLimitType() {
		return secrecyLimitType;
	}

	public void setSecrecyLimitType(Integer secrecyLimitType) {
		this.secrecyLimitType = secrecyLimitType;
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

	public Date getRemoveSecrecyDate() {
		return removeSecrecyDate;
	}

	public void setRemoveSecrecyDate(Date removeSecrecyDate) {
		this.removeSecrecyDate = removeSecrecyDate;
	}

	public String getRemoveSecrecyCondition() {
		return removeSecrecyCondition;
	}

	public void setRemoveSecrecyCondition(String removeSecrecyCondition) {
		this.removeSecrecyCondition = removeSecrecyCondition;
	}

	public Integer getIsfromKeyDepartment() {
		return isfromKeyDepartment;
	}

	public void setIsfromKeyDepartment(Integer isfromKeyDepartment) {
		this.isfromKeyDepartment = isfromKeyDepartment;
	}

	public Department getDepartId() {
		return departId;
	}

	public void setDepartId(Department departId) {
		this.departId = departId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getSecrecyStatus() {
		return secrecyStatus;
	}

	public void setSecrecyStatus(Integer secrecyStatus) {
		this.secrecyStatus = secrecyStatus;
	}

	public KeySection getKeySectionId() {
		return keySectionId;
	}

	public void setKeySectionId(KeySection keySectionId) {
		this.keySectionId = keySectionId;
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
	public Set<SecrecyCountryItemChange> getSecrecyCountryItemChanges() {
		return secrecyCountryItemChanges;
	}
	public void setSecrecyCountryItemChanges(
			Set<SecrecyCountryItemChange> secrecyCountryItemChanges) {
		this.secrecyCountryItemChanges = secrecyCountryItemChanges;
	}
	public Set<SecrecyCountryItemClear> getSecrecyCountryItemClears() {
		return secrecyCountryItemClears;
	}
	public void setSecrecyCountryItemClears(
			Set<SecrecyCountryItemClear> secrecyCountryItemClears) {
		this.secrecyCountryItemClears = secrecyCountryItemClears;
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
         * @return the secrecyLimitTypeTxt
         */
        public String getSecrecyLimitTypeTxt() {
                return secrecyLimitTypeTxt;
        }
        /**
         * @param secrecyLimitTypeTxt the secrecyLimitTypeTxt to set
         */
        public void setSecrecyLimitTypeTxt(String secrecyLimitTypeTxt) {
                this.secrecyLimitTypeTxt = secrecyLimitTypeTxt;
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
