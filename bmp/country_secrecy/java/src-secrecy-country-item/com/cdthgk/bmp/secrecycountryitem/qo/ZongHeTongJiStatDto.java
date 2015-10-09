package com.cdthgk.bmp.secrecycountryitem.qo;

public class ZongHeTongJiStatDto {

	//名字，编码
	private String name;
	private String code;

	//行政区划
	private int districtSecrecyLevel1;
	private int districtSecrecyLevel2;
	private int districtSecrecyLevel3;
	private int districtTotal;//行政区划总数

	//直辖单位
	private int layerSecrecyLevel1;
	private int layerSecrecyLevel2;
	private int layerSecrecyLevel3;
	private int layerTotal;//直辖单位总数


	//名字，编码
	private String organName;
	private String organCode;

	//本单位
	private int organSecrecyLevel1;
	private int organSecrecyLevel2;
	private int organSecrecyLevel3;
	private int organTotal;//本单位总数


	public int getOrganSecrecyLevel1() {
		return organSecrecyLevel1;
	}
	public void setOrganSecrecyLevel1(int organSecrecyLevel1) {
		this.organSecrecyLevel1 = organSecrecyLevel1;
	}
	public int getOrganSecrecyLevel2() {
		return organSecrecyLevel2;
	}
	public void setOrganSecrecyLevel2(int organSecrecyLevel2) {
		this.organSecrecyLevel2 = organSecrecyLevel2;
	}
	public int getOrganSecrecyLevel3() {
		return organSecrecyLevel3;
	}
	public void setOrganSecrecyLevel3(int organSecrecyLevel3) {
		this.organSecrecyLevel3 = organSecrecyLevel3;
	}
	public int getOrganTotal() {
		return organTotal;
	}
	public void setOrganTotal(int organTotal) {
		this.organTotal = organTotal;
	}

	public String getOrganName() {
		return organName;
	}
	public void setOrganName(String organName) {
		this.organName = organName;
	}
	public String getOrganCode() {
		return organCode;
	}
	public void setOrganCode(String organCode) {
		this.organCode = organCode;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getDistrictSecrecyLevel1() {
		return districtSecrecyLevel1;
	}
	public void setDistrictSecrecyLevel1(int districtSecrecyLevel1) {
		this.districtSecrecyLevel1 = districtSecrecyLevel1;
	}
	public int getDistrictSecrecyLevel2() {
		return districtSecrecyLevel2;
	}
	public void setDistrictSecrecyLevel2(int districtSecrecyLevel2) {
		this.districtSecrecyLevel2 = districtSecrecyLevel2;
	}
	public int getDistrictSecrecyLevel3() {
		return districtSecrecyLevel3;
	}
	public void setDistrictSecrecyLevel3(int districtSecrecyLevel3) {
		this.districtSecrecyLevel3 = districtSecrecyLevel3;
	}
	public int getDistrictTotal() {
		return districtTotal;
	}
	public void setDistrictTotal(int districtTotal) {
		this.districtTotal = districtTotal;
	}
	public int getLayerSecrecyLevel1() {
		return layerSecrecyLevel1;
	}
	public void setLayerSecrecyLevel1(int layerSecrecyLevel1) {
		this.layerSecrecyLevel1 = layerSecrecyLevel1;
	}
	public int getLayerSecrecyLevel2() {
		return layerSecrecyLevel2;
	}
	public void setLayerSecrecyLevel2(int layerSecrecyLevel2) {
		this.layerSecrecyLevel2 = layerSecrecyLevel2;
	}
	public int getLayerSecrecyLevel3() {
		return layerSecrecyLevel3;
	}
	public void setLayerSecrecyLevel3(int layerSecrecyLevel3) {
		this.layerSecrecyLevel3 = layerSecrecyLevel3;
	}
	public int getLayerTotal() {
		return layerTotal;
	}
	public void setLayerTotal(int layerTotal) {
		this.layerTotal = layerTotal;
	}



}
