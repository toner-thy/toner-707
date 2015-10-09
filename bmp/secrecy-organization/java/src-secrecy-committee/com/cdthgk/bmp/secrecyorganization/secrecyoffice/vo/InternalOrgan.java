package com.cdthgk.bmp.secrecyorganization.secrecyoffice.vo;


/**
 * BmInternalOrgan
 */
public class InternalOrgan implements java.io.Serializable {

	private static final long serialVersionUID = 4955664428050804294L;

	private String internalOrganId;

	private SecrecyOffice secrecyOffice;

	private String internalDeptname;

	private Integer internalWorkNum;

	private Integer internalRealNum;

	private Integer internalAdminLevel;

	private Integer internalType;

	private Integer sort;

	//内设机构设置情况
	public static final Integer INTERNAL_TYPE_1 = 1;
	//参照管理事业单位机构设置情况
	public static final Integer INTERNAL_TYPE_2 = 2;
	//事业单位设置情况
	public static final Integer INTERNAL_TYPE_3 = 3;
	/**
	 * 默认构�?函数
	 */
	public InternalOrgan() {
	}

	/**
	 * 返回internalOrganId
	 * @return internalOrganId
	 */
	public String getInternalOrganId() {
		return this.internalOrganId;
	}

	/**
	 * 设置internalOrganId
	 * @param internalOrganId internalOrganId
	 */
	public void setInternalOrganId(String internalOrganId) {
		this.internalOrganId = internalOrganId;
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

	/**
	 * 返回internalDeptname
	 * @return internalDeptname
	 */
	public String getInternalDeptname() {
		return this.internalDeptname;
	}

	/**
	 * 设置internalDeptname
	 * @param internalDeptname internalDeptname
	 */
	public void setInternalDeptname(String internalDeptname) {
		this.internalDeptname = internalDeptname;
	}

	/**
	 * 返回internalWorkNum
	 * @return internalWorkNum
	 */
	public Integer getInternalWorkNum() {
		return this.internalWorkNum;
	}

	/**
	 * 设置internalWorkNum
	 * @param internalWorkNum internalWorkNum
	 */
	public void setInternalWorkNum(Integer internalWorkNum) {
		this.internalWorkNum = internalWorkNum;
	}

	/**
	 * 返回internalRealNum
	 * @return internalRealNum
	 */
	public Integer getInternalRealNum() {
		return this.internalRealNum;
	}

	/**
	 * 设置internalRealNum
	 * @param internalRealNum internalRealNum
	 */
	public void setInternalRealNum(Integer internalRealNum) {
		this.internalRealNum = internalRealNum;
	}

	/**
	 * 返回internalAdminLevel
	 * @return internalAdminLevel
	 */
	public Integer getInternalAdminLevel() {
		return this.internalAdminLevel;
	}

	/**
	 * 设置internalAdminLevel
	 * @param internalAdminLevel internalAdminLevel
	 */
	public void setInternalAdminLevel(Integer internalAdminLevel) {
		this.internalAdminLevel = internalAdminLevel;
	}

	/**
	 * 返回internalType
	 * @return internalType
	 */
	public Integer getInternalType() {
		return this.internalType;
	}

	/**
	 * 设置internalType
	 * @param internalType internalType
	 */
	public void setInternalType(Integer internalType) {
		this.internalType = internalType;
	}

	/**
	 * @return 返回sort
	 */
	public Integer getSort() {
		return sort;
	}

	/**
	 * @param sort 设置sort
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}

}
