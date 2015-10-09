package com.cdthgk.bmp.product.dto;

/**
 *
 * @author yangc 2010 3 6 12:34:56
 * @modify 陈文聪 2010 8 18 05:45:58 修改注释格式
 */

public class ProductExcel {

	private String certificateCode;
	private String productType;
	private String productName;
	private String productDesc;
	private String passTime;
	private String manufacturerName;
	private String manufacturePhone;
	private String inspectCenter;

	private String isAvailable;
	private String manufacturerAddress;
	private String inspectCenterAddress;
	private String inspectCenterPhone;

	public String getCertificateCode() {
		return certificateCode;
	}

	public void setCertificateCode(String certificateCode) {
		this.certificateCode = certificateCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public String getPassTime() {
		return passTime;
	}

	public void setPassTime(String passTime) {
		this.passTime = passTime;
	}

	public String getManufacturerName() {
		return manufacturerName;
	}

	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

	public String getManufacturePhone() {
		return manufacturePhone;
	}

	public void setManufacturePhone(String manufacturePhone) {
		this.manufacturePhone = manufacturePhone;
	}

	public String getInspectCenter() {
		return inspectCenter;
	}

	public void setInspectCenter(String inspectCenter) {
		this.inspectCenter = inspectCenter;
	}

	public String getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(String isAvailable) {
		this.isAvailable = isAvailable;
	}

	public String getManufacturerAddress() {
		return manufacturerAddress;
	}

	public void setManufacturerAddress(String manufacturerAddress) {
		this.manufacturerAddress = manufacturerAddress;
	}

	public String getInspectCenterAddress() {
		return inspectCenterAddress;
	}

	public void setInspectCenterAddress(String inspectCenterAddress) {
		this.inspectCenterAddress = inspectCenterAddress;
	}

	public String getInspectCenterPhone() {
		return inspectCenterPhone;
	}

	public void setInspectCenterPhone(String inspectCenterPhone) {
		this.inspectCenterPhone = inspectCenterPhone;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}
}
