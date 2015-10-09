package com.cdthgk.secrecyCarrier.vo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import com.cdthgk.bmp.keyPart.vo.Part;
import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.permission.user.domain.User;
@SuppressWarnings("all")
public class SecrecyTechnologyPrevention implements java.io.Serializable {

	private static final long serialVersionUID = -6174487652886616089L;
	private String id;
	private String name;//设备名称
	private String number;//数量
	private Date date;//时间
	private Department equipDep;//装备部门
	private String purpose;//用途;
	private Part keyPart;//要害部位
	private String description;//备注
	private Date modifyTime;//修改时间
	private Date createTime;//输入时间
	private User modifyPerson;//修改人员
	private User createPerson;//输入人员
	private Organ organId;//创建单位
	private Integer state;//状态：0：使用1：删除
        /**
         * @return 返回 id
         */
        public String getId() {
                return id;
        }
        /**
         * @param id 设置 id
         */
        public void setId(String id) {
                this.id = id;
        }
        /**
         * @return 返回 name
         */
        public String getName() {
                return name;
        }
        /**
         * @param name 设置 name
         */
        public void setName(String name) {
                this.name = name;
        }
        /**
         * @return 返回 number
         */
        public String getNumber() {
                return number;
        }
        /**
         * @param number 设置 number
         */
        public void setNumber(String number) {
                this.number = number;
        }
        /**
         * @return 返回 date
         */
        public Date getDate() {
                return date;
        }
        /**
         * @param date 设置 date
         */
        public void setDate(Date date) {
                this.date = date;
        }
        /**
         * @return 返回 equipDep
         */
        public Department getEquipDep() {
                return equipDep;
        }
        /**
         * @param equipDep 设置 equipDep
         */
        public void setEquipDep(Department equipDep) {
                this.equipDep = equipDep;
        }
        /**
         * @return 返回 purpose
         */
        public String getPurpose() {
                return purpose;
        }
        /**
         * @param purpose 设置 purpose
         */
        public void setPurpose(String purpose) {
                this.purpose = purpose;
        }
        /**
         * @return 返回 keyPart
         */
        public Part getKeyPart() {
                return keyPart;
        }
        /**
         * @param keyPart 设置 keyPart
         */
        public void setKeyPart(Part keyPart) {
                this.keyPart = keyPart;
        }
        /**
         * @return 返回 description
         */
        public String getDescription() {
                return description;
        }
        /**
         * @param description 设置 description
         */
        public void setDescription(String description) {
                this.description = description;
        }
        /**
         * @return 返回 modifyTime
         */
        public Date getModifyTime() {
                return modifyTime;
        }
        /**
         * @param modifyTime 设置 modifyTime
         */
        public void setModifyTime(Date modifyTime) {
                this.modifyTime = modifyTime;
        }
        /**
         * @return 返回 createTime
         */
        public Date getCreateTime() {
                return createTime;
        }
        /**
         * @param createTime 设置 createTime
         */
        public void setCreateTime(Date createTime) {
                this.createTime = createTime;
        }
        /**
         * @return 返回 modifyPerson
         */
        public User getModifyPerson() {
                return modifyPerson;
        }
        /**
         * @param modifyPerson 设置 modifyPerson
         */
        public void setModifyPerson(User modifyPerson) {
                this.modifyPerson = modifyPerson;
        }
        /**
         * @return 返回 createPerson
         */
        public User getCreatePerson() {
                return createPerson;
        }
        /**
         * @param createPerson 设置 createPerson
         */
        public void setCreatePerson(User createPerson) {
                this.createPerson = createPerson;
        }
        /**
         * @return 返回 organId
         */
        public Organ getOrganId() {
                return organId;
        }
        /**
         * @param organId 设置 organId
         */
        public void setOrganId(Organ organId) {
                this.organId = organId;
        }
        /**
         * @return 返回 state
         */
        public Integer getState() {
                return state;
        }
        /**
         * @param state 设置 state
         */
        public void setState(Integer state) {
                this.state = state;
        }

}