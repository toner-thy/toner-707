package com.cdthgk.technictraining.vo;

// default package

import com.cdthgk.platform.organization.organ.domain.Organ;

/**
 * PresenceOrgan entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class PresenceOrgan implements java.io.Serializable {

	// Fields

	/**
	 *
	 */
	private static final long serialVersionUID = 7847716362274897250L;
	private String presenceOrganId;
	private TechnicTrain technicTraining;
	private Organ organ;
	public String getPresenceOrganId() {
		return presenceOrganId;
	}
	public void setPresenceOrganId(String presenceOrganId) {
		this.presenceOrganId = presenceOrganId;
	}
	public TechnicTrain getTechnicTraining() {
		return technicTraining;
	}
	public void setTechnicTraining(TechnicTrain technicTraining) {
		this.technicTraining = technicTraining;
	}
	public Organ getOrgan() {
		return organ;
	}
	public void setOrgan(Organ organ) {
		this.organ = organ;
	}


}