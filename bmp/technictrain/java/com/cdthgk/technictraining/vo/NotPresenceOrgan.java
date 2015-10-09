package com.cdthgk.technictraining.vo;
import com.cdthgk.platform.organization.organ.domain.Organ;
public class NotPresenceOrgan implements java.io.Serializable {

	// Fields

	/**
	 *
	 */
	private static final long serialVersionUID = 3702569007870708838L;
	private String notPresenceOrganId;
	private TechnicTrain technicTraining;
	private Organ organ;

	public String getNotPresenceOrganId() {
		return notPresenceOrganId;
	}

	public void setNotPresenceOrganId(String notPresenceOrganId) {
		this.notPresenceOrganId = notPresenceOrganId;
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