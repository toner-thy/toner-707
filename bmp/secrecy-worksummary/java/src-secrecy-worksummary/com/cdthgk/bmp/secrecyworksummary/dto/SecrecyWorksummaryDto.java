
package com.cdthgk.bmp.secrecyworksummary.dto;

/**
 * <p>
 * 保密工作概况DTO
 * </p>
 * <p>
 * 创建时间 2013-1-13 - 下午2:52:58
 * </p>
 * <blockquote>
 * <h4>历史修改记录</h4>
 * <ul>
 * <li>修改人 修改时间 修改描述
 * </ul>
 * </blockquote>
 * <p>
 * copyright cdthgk 2010-2011, all rights reserved.
 * </p>
 *
 * @author 陶汇源
 * @author cdthgk r&d
 * @since 1.0
 * @version 1.0
 */
public class SecrecyWorksummaryDto {

	/**
	 * 单位ID
	 */
	private String organId;
	/**
	 * 单位名称
	 */
	private String organName;
	/**
	 * 保密工作机构成员
	 */
	private Integer numPersonGroupMember;
	/**
	 * 保密办成员
	 */
	private Integer numSecrecyWorkOrganMember;
	/**
	 * 要害部门
	 */
	private Integer numKeysection;
	/**
	 * 要害部位
	 */
	private Integer numKeyPart;
	/**
	 * 机关涉密人员
	 */
	private Integer numSecrecyPerson;
	/**
	 * @return 返回organId
	 */
	public String getOrganId() {
		return organId;
	}
	/**
	 * @param organId 设置organId
	 */
	public void setOrganId(String organId) {
		this.organId = organId;
	}
	/**
	 * @return 返回organName
	 */
	public String getOrganName() {
		return organName;
	}
	/**
	 * @param organName 设置organName
	 */
	public void setOrganName(String organName) {
		this.organName = organName;
	}
	/**
	 * @return 返回numPersonGroupMember
	 */
	public Integer getNumPersonGroupMember() {
		return numPersonGroupMember;
	}
	/**
	 * @param numPersonGroupMember 设置numPersonGroupMember
	 */
	public void setNumPersonGroupMember(Integer numPersonGroupMember) {
		this.numPersonGroupMember = numPersonGroupMember;
	}
	/**
	 * @return 返回numSecrecyWorkOrganMember
	 */
	public Integer getNumSecrecyWorkOrganMember() {
		return numSecrecyWorkOrganMember;
	}
	/**
	 * @param numSecrecyWorkOrganMember 设置numSecrecyWorkOrganMember
	 */
	public void setNumSecrecyWorkOrganMember(Integer numSecrecyWorkOrganMember) {
		this.numSecrecyWorkOrganMember = numSecrecyWorkOrganMember;
	}
	/**
	 * @return 返回numKeysection
	 */
	public Integer getNumKeysection() {
		return numKeysection;
	}
	/**
	 * @param numKeysection 设置numKeysection
	 */
	public void setNumKeysection(Integer numKeysection) {
		this.numKeysection = numKeysection;
	}
	/**
	 * @return 返回numKeyPart
	 */
	public Integer getNumKeyPart() {
		return numKeyPart;
	}
	/**
	 * @param numKeyPart 设置numKeyPart
	 */
	public void setNumKeyPart(Integer numKeyPart) {
		this.numKeyPart = numKeyPart;
	}
	/**
	 * @return 返回numSecrecyPerson
	 */
	public Integer getNumSecrecyPerson() {
		return numSecrecyPerson;
	}
	/**
	 * @param numSecrecyPerson 设置numSecrecyPerson
	 */
	public void setNumSecrecyPerson(Integer numSecrecyPerson) {
		this.numSecrecyPerson = numSecrecyPerson;
	}
}
