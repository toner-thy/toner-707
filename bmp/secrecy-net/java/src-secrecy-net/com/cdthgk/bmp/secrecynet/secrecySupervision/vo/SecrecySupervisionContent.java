package com.cdthgk.bmp.secrecynet.secrecySupervision.vo;

import com.cdthgk.platform.organization.organ.domain.Organ;

public class SecrecySupervisionContent implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private Integer year;
	private Organ organ;
	private Double budget;
	private Double actualUse;
	private Integer buyCheckTools;
	private Integer platformConstruction;
	private Integer businessTrain;
	private Integer networkEvaluation;
	private String other;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the year
	 */
	public Integer getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(Integer year) {
		this.year = year;
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
	 * @return the budget
	 */
	public Double getBudget() {
		return budget;
	}
	/**
	 * @param budget the budget to set
	 */
	public void setBudget(Double budget) {
		this.budget = budget;
	}
	/**
	 * @return the actualUse
	 */
	public Double getActualUse() {
		return actualUse;
	}
	/**
	 * @param actualUse the actualUse to set
	 */
	public void setActualUse(Double actualUse) {
		this.actualUse = actualUse;
	}
	/**
	 * @return the buyCheckTools
	 */
	public Integer getBuyCheckTools() {
		return buyCheckTools;
	}
	/**
	 * @param buyCheckTools the buyCheckTools to set
	 */
	public void setBuyCheckTools(Integer buyCheckTools) {
		this.buyCheckTools = buyCheckTools;
	}
	/**
	 * @return the platformConstruction
	 */
	public Integer getPlatformConstruction() {
		return platformConstruction;
	}
	/**
	 * @param platformConstruction the platformConstruction to set
	 */
	public void setPlatformConstruction(Integer platformConstruction) {
		this.platformConstruction = platformConstruction;
	}
	/**
	 * @return the businessTrain
	 */
	public Integer getBusinessTrain() {
		return businessTrain;
	}
	/**
	 * @param businessTrain the businessTrain to set
	 */
	public void setBusinessTrain(Integer businessTrain) {
		this.businessTrain = businessTrain;
	}
	/**
	 * @return the networkEvaluation
	 */
	public Integer getNetworkEvaluation() {
		return networkEvaluation;
	}
	/**
	 * @param networkEvaluation the networkEvaluation to set
	 */
	public void setNetworkEvaluation(Integer networkEvaluation) {
		this.networkEvaluation = networkEvaluation;
	}
	/**
	 * @return the other
	 */
	public String getOther() {
		return other;
	}
	/**
	 * @param other the other to set
	 */
	public void setOther(String other) {
		this.other = other;
	}




}