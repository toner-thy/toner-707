/**
 *
 */
package com.cdthgk.bmp.secrecyperson.qo;

import com.cdthgk.platform.organization.organ.domain.Organ;

/**
 * <p>
 * 类的说明放这里  2013-8-1 下午3:51:38
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 *
 * @author 宋亚非
 */
public class SecrecyPersonQo {
	private String name;

	private Integer secrecyStatus;

	private Organ organ;

	Integer secrecySatusOfDecryption;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the secrecyStatus
	 */
	public Integer getSecrecyStatus() {
		return secrecyStatus;
	}

	/**
	 * @param secrecyStatus the secrecyStatus to set
	 */
	public void setSecrecyStatus(Integer secrecyStatus) {
		this.secrecyStatus = secrecyStatus;
	}

	/**
	 * @return the organ
	 */
	public Organ getOrgan() {
		return organ;
	}

	/**
	 * @param organ the organ to set
	 */
	public void setOrgan(Organ organ) {
		this.organ = organ;
	}

	/**
	 * @return the secrecySatusOfDecryption
	 */
	public Integer getSecrecySatusOfDecryption() {
		return secrecySatusOfDecryption;
	}

	/**
	 * @param secrecySatusOfDecryption the secrecySatusOfDecryption to set
	 */
	public void setSecrecySatusOfDecryption(Integer secrecySatusOfDecryption) {
		this.secrecySatusOfDecryption = secrecySatusOfDecryption;
	}

}
