package com.cdthgk.technictraining.dto;

import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.technictraining.vo.TechnicTrain;

/**
 * @description 保密技术培训Dto
 * @author 刘  舜 2010 02 02 12:46:30
 * @modify 陈文聪 2010 8 17 02:31:37
 */

public class TechnicTrainDto {
	private TechnicTrain technicTrain;
	private Organ organ;
	private String startTime;
	private String endTime;


	public TechnicTrain getTechnicTrain() {
		return technicTrain;
	}
	public void setTechnicTrain(TechnicTrain technicTrain) {
		this.technicTrain = technicTrain;
	}
	public Organ getOrgan() {
		return organ;
	}
	public void setOrgan(Organ organ) {
		this.organ = organ;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}



}
