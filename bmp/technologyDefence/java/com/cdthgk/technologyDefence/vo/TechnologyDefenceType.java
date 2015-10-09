package com.cdthgk.technologyDefence.vo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.cdthgk.platform.permission.user.domain.User;

/**
 * @description TODO
 * @author 杨  成 2009 12 16 12:34:56
 * @modify 陈文聪 2010 8 18 07:28:55 修改注释格式
 */

public class TechnologyDefenceType implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private Integer type;//技术防范类型
	private String name;//技术类型名字
	private TechnologyDefenceType parent;//上级类型
	private Set<TechnologyDefenceType> childs = new HashSet<TechnologyDefenceType>();

	private User createPerson;//创建人

	private Date createTime;//创建时间

	private User modifyPerson;//修改人

	private Date modifyTime;//修改时间

	/*新增字段*/
	private Integer status;//0:删除；1：禁用；2：启用

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
         * @return 返回 type
         */
        public Integer getType() {
                return type;
        }

        /**
         * @param type 设置 type
         */
        public void setType(Integer type) {
                this.type = type;
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
         * @return 返回 parent
         */
        public TechnologyDefenceType getParent() {
                return parent;
        }

        /**
         * @param parent 设置 parent
         */
        public void setParent(TechnologyDefenceType parent) {
                this.parent = parent;
        }

        /**
         * @return 返回 childs
         */
        public Set<TechnologyDefenceType> getChilds() {
                return childs;
        }

        /**
         * @param childs 设置 childs
         */
        public void setChilds(Set<TechnologyDefenceType> childs) {
                this.childs = childs;
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
         * @return 返回 status
         */
        public Integer getStatus() {
                return status;
        }

        /**
         * @param status 设置 status
         */
        public void setStatus(Integer status) {
                this.status = status;
        }

}