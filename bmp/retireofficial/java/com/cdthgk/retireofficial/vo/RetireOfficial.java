package com.cdthgk.retireofficial.vo;

import java.util.Date;

import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;

public class RetireOfficial  implements java.io.Serializable {
	private static final long serialVersionUID = -692438744439524139L;
	private String retireOfficialId;
     private String name;
     private Integer sex;
     private Date birthDate;
     private Integer nation;
     private String nativePlace;
     private Date joinPartyTime;
     private Date startWorkTime;
     private Date retireTime;
     private String retireCode;
     private Integer retireLevel;
     private String retireOrganDuty;
     private Float monthEarning;
     private Float basicRetireMoney;
     private Float lifeSubsidy;
     private Float subsidy2;
     private Float foodSubsidy;
     private Integer health;
     private String idCard;
     private String address;
     private Integer addressSize;
     private String phone;
     private String mobile;
     private String spouseName;
     private Date spouseBirthDate;
     private Integer spouseHealth;
     private String spouseOrganDuty;
     private String spouseInfo;
     private String personalRecord;
     private Department department;
     private Organ organ;
     private UserInfo createPerson;
     private Date createTime;
     private UserInfo modifyPerson;
     private Date modifyTime;


    // Constructors

    /** default constructor */
    public RetireOfficial() {
    }

    // Property accessors

    public String getRetireOfficialId() {
        return this.retireOfficialId;
    }

    public void setRetireOfficialId(String retireOfficialId) {
        this.retireOfficialId = retireOfficialId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return this.sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getNation() {
        return this.nation;
    }

    public void setNation(Integer nation) {
        this.nation = nation;
    }

    public String getNativePlace() {
        return this.nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public Date getJoinPartyTime() {
        return this.joinPartyTime;
    }

    public void setJoinPartyTime(Date joinPartyTime) {
        this.joinPartyTime = joinPartyTime;
    }

    public Date getStartWorkTime() {
        return this.startWorkTime;
    }

    public void setStartWorkTime(Date startWorkTime) {
        this.startWorkTime = startWorkTime;
    }

    public Date getRetireTime() {
        return this.retireTime;
    }

    public void setRetireTime(Date retireTime) {
        this.retireTime = retireTime;
    }

    public String getRetireCode() {
        return this.retireCode;
    }

    public void setRetireCode(String retireCode) {
        this.retireCode = retireCode;
    }

    public Integer getRetireLevel() {
        return this.retireLevel;
    }

    public void setRetireLevel(Integer retireLevel) {
        this.retireLevel = retireLevel;
    }

    public String getRetireOrganDuty() {
        return this.retireOrganDuty;
    }

    public void setRetireOrganDuty(String retireOrganDuty) {
        this.retireOrganDuty = retireOrganDuty;
    }

    public Float getMonthEarning() {
        return this.monthEarning;
    }

    public void setMonthEarning(Float monthEarning) {
        this.monthEarning = monthEarning;
    }

    public Float getBasicRetireMoney() {
        return this.basicRetireMoney;
    }

    public void setBasicRetireMoney(Float basicRetireMoney) {
        this.basicRetireMoney = basicRetireMoney;
    }

    public Float getLifeSubsidy() {
        return this.lifeSubsidy;
    }

    public void setLifeSubsidy(Float lifeSubsidy) {
        this.lifeSubsidy = lifeSubsidy;
    }

    public Float getSubsidy2() {
        return this.subsidy2;
    }

    public void setSubsidy2(Float subsidy2) {
        this.subsidy2 = subsidy2;
    }

    public Float getFoodSubsidy() {
        return this.foodSubsidy;
    }

    public void setFoodSubsidy(Float foodSubsidy) {
        this.foodSubsidy = foodSubsidy;
    }

    public Integer getHealth() {
        return this.health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public String getIdCard() {
        return this.idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAddressSize() {
        return this.addressSize;
    }

    public void setAddressSize(Integer addressSize) {
        this.addressSize = addressSize;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSpouseName() {
        return this.spouseName;
    }

    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }

    public Date getSpouseBirthDate() {
        return this.spouseBirthDate;
    }

    public void setSpouseBirthDate(Date spouseBirthDate) {
        this.spouseBirthDate = spouseBirthDate;
    }

    public Integer getSpouseHealth() {
        return this.spouseHealth;
    }

    public void setSpouseHealth(Integer spouseHealth) {
        this.spouseHealth = spouseHealth;
    }

    public String getSpouseOrganDuty() {
        return this.spouseOrganDuty;
    }

    public void setSpouseOrganDuty(String spouseOrganDuty) {
        this.spouseOrganDuty = spouseOrganDuty;
    }

    public String getSpouseInfo() {
        return this.spouseInfo;
    }

    public void setSpouseInfo(String spouseInfo) {
        this.spouseInfo = spouseInfo;
    }

    public String getPersonalRecord() {
        return this.personalRecord;
    }

    public void setPersonalRecord(String personalRecord) {
        this.personalRecord = personalRecord;
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

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Organ getOrgan() {
		return organ;
	}

	public void setOrgan(Organ organ) {
		this.organ = organ;
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