package com.cdthgk.technologyDefence.vo;

import java.util.Date;

import com.cdthgk.platform.permission.user.domain.User;

/**
 * @description TODO
 * @author 杨  成 2009 12 16 12:34:56
 * @modify 陈文聪 2010 8 18 07:28:55 修改注释格式
 */

public class TechnologyDefenceUser implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private TechnologyDefence technologyDefence;
	private User user;
	private Date createTime;//创建时间
	private Date modifyTime;//修改时间
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
         * @return 返回 technologyDefence
         */
        public TechnologyDefence getTechnologyDefence() {
                return technologyDefence;
        }
        /**
         * @param technologyDefence 设置 technologyDefence
         */
        public void setTechnologyDefence(TechnologyDefence technologyDefence) {
                this.technologyDefence = technologyDefence;
        }
        /**
         * @return 返回 user
         */
        public User getUser() {
                return user;
        }
        /**
         * @param user 设置 user
         */
        public void setUser(User user) {
                this.user = user;
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


}