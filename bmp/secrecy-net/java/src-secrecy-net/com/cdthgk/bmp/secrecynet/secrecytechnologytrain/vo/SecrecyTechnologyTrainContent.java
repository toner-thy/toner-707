package com.cdthgk.bmp.secrecynet.secrecytechnologytrain.vo;

import com.cdthgk.platform.organization.organ.domain.Organ;

public class SecrecyTechnologyTrainContent implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private Integer year;
	private Organ organ;
	/*private SecrecyTechnologyTrain secrecyTechnologyTrain;*/
	private Integer secrityTechTrainNum;
	private Integer trainNum;
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
	 * @return the secrityTechTrainNum
	 */
	public Integer getSecrityTechTrainNum() {
		return secrityTechTrainNum;
	}
	/**
	 * @param secrityTechTrainNum the secrityTechTrainNum to set
	 */
	public void setSecrityTechTrainNum(Integer secrityTechTrainNum) {
		this.secrityTechTrainNum = secrityTechTrainNum;
	}
	/**
	 * @return the trainNum
	 */
	public Integer getTrainNum() {
		return trainNum;
	}
	/**
	 * @param trainNum the trainNum to set
	 */
	public void setTrainNum(Integer trainNum) {
		this.trainNum = trainNum;
	}

}