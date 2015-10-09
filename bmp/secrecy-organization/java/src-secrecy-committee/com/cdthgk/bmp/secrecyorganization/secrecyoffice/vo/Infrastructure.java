package com.cdthgk.bmp.secrecyorganization.secrecyoffice.vo;
/**
 * BmInfrastructure
 */
public class Infrastructure implements java.io.Serializable {

	private static final long serialVersionUID = 8146242463506862185L;

	private String infrastructureId;

	// 所属保密办（保密局）
	private SecrecyOffice secrecyOffice;

	private String organName;

	private Integer infrastructureType;

	private Float area;

	private Float finance;

	private String address;

	private String name;

	/**
	 * 默认构�?函数
	 */
	public Infrastructure() {
	}

	/**
	 * 返回infrastructureId
	 * @return infrastructureId
	 */
	public String getInfrastructureId() {
		return this.infrastructureId;
	}

	/**
	 * 设置infrastructureId
	 * @param infrastructureId infrastructureId
	 */
	public void setInfrastructureId(String infrastructureId) {
		this.infrastructureId = infrastructureId;
	}

	/**
	 * 返回organName
	 * @return organName
	 */
	public String getOrganName() {
		return this.organName;
	}

	/**
	 * 设置organName
	 * @param organName organName
	 */
	public void setOrganName(String organName) {
		this.organName = organName;
	}

	/**
	 * 返回infrastructureType
	 * @return infrastructureType
	 */
	public Integer getInfrastructureType() {
		return this.infrastructureType;
	}

	/**
	 * 设置infrastructureType
	 * @param infrastructureType infrastructureType
	 */
	public void setInfrastructureType(Integer infrastructureType) {
		this.infrastructureType = infrastructureType;
	}

	/**
	 * 返回area
	 * @return area
	 */
	public Float getArea() {
		return this.area;
	}

	/**
	 * 设置area
	 * @param area area
	 */
	public void setArea(Float area) {
		this.area = area;
	}

	/**
	 * 返回finance
	 * @return finance
	 */
	public Float getFinance() {
		return this.finance;
	}

	/**
	 * 设置finance
	 * @param finance finance
	 */
	public void setFinance(Float finance) {
		this.finance = finance;
	}

	/**
	 * 返回address
	 * @return address
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 * 设置address
	 * @param address address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 返回name
	 * @return name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * 设置name
	 * @param name name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return 返回secrecyOffice
	 */
	public SecrecyOffice getSecrecyOffice() {
		return secrecyOffice;
	}

	/**
	 * @param secrecyOffice 设置secrecyOffice
	 */
	public void setSecrecyOffice(SecrecyOffice secrecyOffice) {
		this.secrecyOffice = secrecyOffice;
	}


}
