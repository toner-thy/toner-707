package com.cdthgk.bmp.secrecyorganization.secrecyoffice.vo;


/**
 * BmEmployPerson
 */
public class EmployPerson implements java.io.Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -6798600647225071861L;

	private String employPersonId;

	// 所属保密办（保密局）
	private SecrecyOffice secrecyOffice;

	private Integer managerStaff;

	private Integer specialtyStaff;

	private Integer workStaff;

	private Integer other;

	/**
	 * 默认构�?函数
	 */
	public EmployPerson() {
	}

	/**
	 * 返回employPersonId
	 * @return employPersonId
	 */
	public String getEmployPersonId() {
		return this.employPersonId;
	}

	/**
	 * 设置employPersonId
	 * @param employPersonId employPersonId
	 */
	public void setEmployPersonId(String employPersonId) {
		this.employPersonId = employPersonId;
	}

	/**
	 * 返回managerStaff
	 * @return managerStaff
	 */
	public Integer getManagerStaff() {
		return this.managerStaff;
	}

	/**
	 * 设置managerStaff
	 * @param managerStaff managerStaff
	 */
	public void setManagerStaff(Integer managerStaff) {
		this.managerStaff = managerStaff;
	}

	/**
	 * 返回specialtyStaff
	 * @return specialtyStaff
	 */
	public Integer getSpecialtyStaff() {
		return this.specialtyStaff;
	}

	/**
	 * 设置specialtyStaff
	 * @param specialtyStaff specialtyStaff
	 */
	public void setSpecialtyStaff(Integer specialtyStaff) {
		this.specialtyStaff = specialtyStaff;
	}

	/**
	 * 返回workStaff
	 * @return workStaff
	 */
	public Integer getWorkStaff() {
		return this.workStaff;
	}

	/**
	 * 设置workStaff
	 * @param workStaff workStaff
	 */
	public void setWorkStaff(Integer workStaff) {
		this.workStaff = workStaff;
	}

	/**
	 * 返回other
	 * @return other
	 */
	public Integer getOther() {
		return this.other;
	}

	/**
	 * 设置other
	 * @param other other
	 */
	public void setOther(Integer other) {
		this.other = other;
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
