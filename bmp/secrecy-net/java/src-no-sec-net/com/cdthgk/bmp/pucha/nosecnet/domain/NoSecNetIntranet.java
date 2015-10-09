package com.cdthgk.bmp.pucha.nosecnet.domain;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;


/**
 * The persistent class for the bm_no_sec_net_intranet database table.
 *
 */
@Entity
@Table(name="bm_no_sec_net_intranet")
public class NoSecNetIntranet implements Serializable {
	private static final long serialVersionUID = 1L;
	private String intranetId;
	private String name;
	private String purpose;
	private String useRange;
	private Integer userNum;
	private NoSecNet noSecNet;

	private Integer sort = 1;

    public NoSecNetIntranet() {
    }


    @Id
   	@GeneratedValue(generator="assignedUUIDGenerator")
   	@GenericGenerator(name="assignedUUIDGenerator", strategy="com.cdthgk.persistece.hibernate.idgen.AssignedUUIDGenerator")
	@Column(name="INTRANET_ID")
	public String getIntranetId() {
		return this.intranetId;
	}

	public void setIntranetId(String intranetId) {
		this.intranetId = intranetId;
	}


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getPurpose() {
		return this.purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}


	@Column(name="USE_RANGE")
	public String getUseRange() {
		return this.useRange;
	}

	public void setUseRange(String useRange) {
		this.useRange = useRange;
	}


	@Column(name="USER_NUM")
	public Integer getUserNum() {
		return this.userNum;
	}

	public void setUserNum(Integer userNum) {
		this.userNum = userNum;
	}


	//bi-directional many-to-one association to NoSecNet
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="NO_SEC_NET_ID")
	public NoSecNet getNoSecNet() {
		return this.noSecNet;
	}

	public void setNoSecNet(NoSecNet noSecNet) {
		this.noSecNet = noSecNet;
	}


	/**
	 * 返回sort
	 * @return sort
	 */
	@Column(name="SORT")
	public Integer getSort() {
		return sort;
	}


	/**
	 * 设置sort
	 * @param sort sort
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}



}