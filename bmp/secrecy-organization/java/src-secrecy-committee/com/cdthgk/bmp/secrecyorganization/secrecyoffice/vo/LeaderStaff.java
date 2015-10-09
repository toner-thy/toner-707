package com.cdthgk.bmp.secrecyorganization.secrecyoffice.vo;


/**
 * BmLeaderStaff
 */
public class LeaderStaff implements java.io.Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -164537915939520407L;

	private String leaderStaffId;

	// 所属保密办（保密局）
	private SecrecyOffice secrecyOffice;

	private Integer secretary;

	private Integer deputySecretary;

	private Integer inspector;

	private Integer deputyInspector;

	private Integer deptStaff;

	private Integer other;

	/**
	 * 默认构�?函数
	 */
	public LeaderStaff() {
	}

	/**
	 * 返回leaderStaffId
	 * @return leaderStaffId
	 */
	public String getLeaderStaffId() {
		return this.leaderStaffId;
	}

	/**
	 * 设置leaderStaffId
	 * @param leaderStaffId leaderStaffId
	 */
	public void setLeaderStaffId(String leaderStaffId) {
		this.leaderStaffId = leaderStaffId;
	}

	/**
	 * 返回secretary
	 * @return secretary
	 */
	public Integer getSecretary() {
		return this.secretary;
	}

	/**
	 * 设置secretary
	 * @param secretary secretary
	 */
	public void setSecretary(Integer secretary) {
		this.secretary = secretary;
	}

	/**
	 * 返回deputySecretary
	 * @return deputySecretary
	 */
	public Integer getDeputySecretary() {
		return this.deputySecretary;
	}

	/**
	 * 设置deputySecretary
	 * @param deputySecretary deputySecretary
	 */
	public void setDeputySecretary(Integer deputySecretary) {
		this.deputySecretary = deputySecretary;
	}

	/**
	 * 返回inspector
	 * @return inspector
	 */
	public Integer getInspector() {
		return this.inspector;
	}

	/**
	 * 设置inspector
	 * @param inspector inspector
	 */
	public void setInspector(Integer inspector) {
		this.inspector = inspector;
	}

	/**
	 * 返回deputyInspector
	 * @return deputyInspector
	 */
	public Integer getDeputyInspector() {
		return this.deputyInspector;
	}

	/**
	 * 设置deputyInspector
	 * @param deputyInspector deputyInspector
	 */
	public void setDeputyInspector(Integer deputyInspector) {
		this.deputyInspector = deputyInspector;
	}

	/**
	 * 返回deptStaff
	 * @return deptStaff
	 */
	public Integer getDeptStaff() {
		return this.deptStaff;
	}

	/**
	 * 设置deptStaff
	 * @param deptStaff deptStaff
	 */
	public void setDeptStaff(Integer deptStaff) {
		this.deptStaff = deptStaff;
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
