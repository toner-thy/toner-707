package com.cdthgk.bmp.product.vo;


import java.util.Date;

import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;


/**
 * NetSignUp entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class Product implements java.io.Serializable {

        private static final long serialVersionUID = -2291761937448642410L;
        private String productId;
        private String certificateCode;
        private String productName;
        private String productDesc;
        private Date passTime;
        private String manufacturerName;
        private String manufacturePhone;
        private String inspectCenter;
        private Date createTime;
        private UserInfo createPerson;

        private Date modifyTime;
        private UserInfo modifyPerson;
        private Department department;
        private Organ organ;
        private Integer isAvailable;
        private String manufacturerAddress;
        private String inspectCenterAddress;
        private String inspectCenterPhone;
        private Integer productType;

        public Integer getProductType() {
                return productType;
        }
        public void setProductType(Integer productType) {
                this.productType = productType;
        }

        public String getProductId() {
                return productId;
        }
        public void setProductId(String productId) {
                this.productId = productId;
        }
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
        public Date getPassTime() {
                return passTime;
        }
        public void setPassTime(Date passTime) {
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
        public Integer getIsAvailable() {
                return isAvailable;
        }
        public void setIsAvailable(Integer isAvailable) {
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
        public Date getCreateTime() {
                return createTime;
        }
        public void setCreateTime(Date createTime) {
                this.createTime = createTime;
        }
        public UserInfo getCreatePerson() {
                return createPerson;
        }
        public void setCreatePerson(UserInfo createPerson) {
                this.createPerson = createPerson;
        }
        public Date getModifyTime() {
                return modifyTime;
        }
        public void setModifyTime(Date modifyTime) {
                this.modifyTime = modifyTime;
        }
        public UserInfo getModifyPerson() {
                return modifyPerson;
        }
        public void setModifyPerson(UserInfo modifyPerson) {
                this.modifyPerson = modifyPerson;
        }
        public Department getDepartment() {
                return department;
        }
        public void setDepartment(Department department) {
                this.department = department;
        }
        public Organ getOrgan() {
                return organ;
        }
        public void setOrgan(Organ organ) {
                this.organ = organ;
        }


}