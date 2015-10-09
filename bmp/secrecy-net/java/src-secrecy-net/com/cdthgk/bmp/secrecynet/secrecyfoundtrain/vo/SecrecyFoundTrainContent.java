package com.cdthgk.bmp.secrecynet.secrecyfoundtrain.vo;

import com.cdthgk.platform.organization.organ.domain.Organ;

public class SecrecyFoundTrainContent implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private Integer year;
	private Organ organ;
	/*private SecrecyFoundTrain secrecyFoundTrain;*/
	private Integer secrityTrainNum;
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
	 * @return the secrityTrainNum
	 */
	public Integer getSecrityTrainNum() {
		return secrityTrainNum;
	}
	/**
	 * @param secrityTrainNum the secrityTrainNum to set
	 */
	public void setSecrityTrainNum(Integer secrityTrainNum) {
		this.secrityTrainNum = secrityTrainNum;
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

}