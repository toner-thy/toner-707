package com.cdthgk.bmp.secrecyorganization.secrecyoffice.vo;


/**
 * BmEstablishSituation
 */
public class EstablishSituation implements java.io.Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 61762870321354949L;

	private String establishSituationId;

	// 所属保密办（保密局）
	private SecrecyOffice secrecyOffice;

	private Integer civil;

	private Integer referMamager;

	private Integer fullFunds;

	private Integer selfFunds;

	/**
	 * 默认构�?函数
	 */
	public EstablishSituation() {
	}

	/**
	 * 返回establishSituationId
	 * @return establishSituationId
	 */
	public String getEstablishSituationId() {
		return this.establishSituationId;
	}

	/**
	 * 设置establishSituationId
	 * @param establishSituationId establishSituationId
	 */
	public void setEstablishSituationId(String establishSituationId) {
		this.establishSituationId = establishSituationId;
	}

	/**
	 * 返回civil
	 * @return civil
	 */
	public Integer getCivil() {
		return this.civil;
	}

	/**
	 * 设置civil
	 * @param civil civil
	 */
	public void setCivil(Integer civil) {
		this.civil = civil;
	}

	/**
	 * 返回referMamager
	 * @return referMamager
	 */
	public Integer getReferMamager() {
		return this.referMamager;
	}

	/**
	 * 设置referMamager
	 * @param referMamager referMamager
	 */
	public void setReferMamager(Integer referMamager) {
		this.referMamager = referMamager;
	}

	/**
	 * 返回fullFunds
	 * @return fullFunds
	 */
	public Integer getFullFunds() {
		return this.fullFunds;
	}

	/**
	 * 设置fullFunds
	 * @param fullFunds fullFunds
	 */
	public void setFullFunds(Integer fullFunds) {
		this.fullFunds = fullFunds;
	}

	/**
	 * 返回selfFunds
	 * @return selfFunds
	 */
	public Integer getSelfFunds() {
		return this.selfFunds;
	}

	/**
	 * 设置selfFunds
	 * @param selfFunds selfFunds
	 */
	public void setSelfFunds(Integer selfFunds) {
		this.selfFunds = selfFunds;
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
