package com.cdthgk.platform.dataValidate.vo;
// default package
// Generated 2014-5-28 11:02:30 ----- 3.4.0.CR1



/**
 * BmBusinessRule
 */
public class BusinessRule  implements java.io.Serializable {


 	//id
	private String businessRuleId;


	private String businessModuleId;

	private String name;


	private Integer isWrite;


	private Integer minRecordNum;


	private Integer isRecentlyChanged;


	private Integer verificationValue;


	private String moduleUrl;


    public BusinessRule() {
    }

	 /**
	 * 返回businessRuleId
	 * @return businessRuleId
	 */
	public String getBusinessRuleId() {
		return this.businessRuleId;
	}

	/**
	 * 设置businessRuleId
	 * @param businessRuleId businessRuleId
	 */
	public void setBusinessRuleId(String businessRuleId) {
		this.businessRuleId = businessRuleId;
	}
	 /**
	 * 返回businessModuleId
	 * @return businessModuleId
	 */
	public String getBusinessModuleId() {
		return this.businessModuleId;
	}

	/**
	 * 设置businessModuleId
	 * @param businessModuleId businessModuleId
	 */
	public void setBusinessModuleId(String businessModuleId) {
		this.businessModuleId = businessModuleId;
	}
	 /**
	 * 返回isWrite
	 * @return isWrite
	 */
	public Integer getIsWrite() {
		return this.isWrite;
	}

	/**
	 * 设置isWrite
	 * @param isWrite isWrite
	 */
	public void setIsWrite(Integer isWrite) {
		this.isWrite = isWrite;
	}
	 /**
	 * 返回minRecordNum
	 * @return minRecordNum
	 */
	public Integer getMinRecordNum() {
		return this.minRecordNum;
	}

	/**
	 * 设置minRecordNum
	 * @param minRecordNum minRecordNum
	 */
	public void setMinRecordNum(Integer minRecordNum) {
		this.minRecordNum = minRecordNum;
	}
	 /**
	 * 返回isRecentlyChanged
	 * @return isRecentlyChanged
	 */
	public Integer getIsRecentlyChanged() {
		return this.isRecentlyChanged;
	}

	/**
	 * 设置isRecentlyChanged
	 * @param isRecentlyChanged isRecentlyChanged
	 */
	public void setIsRecentlyChanged(Integer isRecentlyChanged) {
		this.isRecentlyChanged = isRecentlyChanged;
	}
	 /**
	 * 返回verificationValue
	 * @return verificationValue
	 */
	public Integer getVerificationValue() {
		return this.verificationValue;
	}

	/**
	 * 设置verificationValue
	 * @param verificationValue verificationValue
	 */
	public void setVerificationValue(Integer verificationValue) {
		this.verificationValue = verificationValue;
	}
	 /**
	 * 返回moduleUrl
	 * @return moduleUrl
	 */
	public String getModuleUrl() {
		return this.moduleUrl;
	}

	/**
	 * 设置moduleUrl
	 * @param moduleUrl moduleUrl
	 */
	public void setModuleUrl(String moduleUrl) {
		this.moduleUrl = moduleUrl;
	}

        /**
         * @return 返回 name
         */
        public String getName() {
                return name;
        }

        /**
         * @param name 设置 name
         */
        public void setName(String name) {
                this.name = name;
        }




}


