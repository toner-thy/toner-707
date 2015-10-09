package com.cdthgk.platform.newbieGuide.vo;
// default package
// Generated 2014-6-12 10:23:08 ----- 3.4.0.CR1

/**
 * SysNewbieGuide
 */
public class SysNewbieGuide implements java.io.Serializable {

        private String newbieguideId;

        //���.
        private String name;

        //������Ҫ�����ݵı���.
        private String tableName;

        //�ñ�ʹ�õ�״̬�ֶ�������û�У����ÿ�.
        private String statusColumnStr;

        //��������.
        private String addUrl;

        //һ������½����ҳ��.
        private String listUrl;

        //״̬��1��ʹ�ã�0�����ã�.
        private Integer status;

        private Long dataNum;

        private Integer orderNum;

        /**
         * 默认构�?函数
         */
        public SysNewbieGuide() {
        }

        /**
         * 返回newbieguideId
         * @return newbieguideId
         */
        public String getNewbieguideId() {
                return this.newbieguideId;
        }

        /**
         * 设置newbieguideId
         * @param newbieguideId newbieguideId
         */
        public void setNewbieguideId(String newbieguideId) {
                this.newbieguideId = newbieguideId;
        }

        /**
         * 返回name
         * @return name
         */
        public String getName() {
                return this.name;
        }

        /**
         * 设置name
         * @param name name
         */
        public void setName(String name) {
                this.name = name;
        }

        /**
         * 返回tableName
         * @return tableName
         */
        public String getTableName() {
                return this.tableName;
        }

        /**
         * 设置tableName
         * @param tableName tableName
         */
        public void setTableName(String tableName) {
                this.tableName = tableName;
        }

        /**
         * @return the statusColumnStr
         */
        public String getStatusColumnStr() {
                return statusColumnStr;
        }

        /**
         * @param statusColumnStr the statusColumnStr to set
         */
        public void setStatusColumnStr(String statusColumnStr) {
                this.statusColumnStr = statusColumnStr;
        }

        /**
         * 返回addUrl
         * @return addUrl
         */
        public String getAddUrl() {
                return this.addUrl;
        }

        /**
         * 设置addUrl
         * @param addUrl addUrl
         */
        public void setAddUrl(String addUrl) {
                this.addUrl = addUrl;
        }

        /**
         * 返回listUrl
         * @return listUrl
         */
        public String getListUrl() {
                return this.listUrl;
        }

        /**
         * 设置listUrl
         * @param listUrl listUrl
         */
        public void setListUrl(String listUrl) {
                this.listUrl = listUrl;
        }

        /**
         * 返回status
         * @return status
         */
        public Integer getStatus() {
                return this.status;
        }

        /**
         * 设置status
         * @param status status
         */
        public void setStatus(Integer status) {
                this.status = status;
        }

        /**
         * @return the dataNum
         */
        public Long getDataNum() {
                return dataNum;
        }

        /**
         * @param dataNum the dataNum to set
         */
        public void setDataNum(Long dataNum) {
                this.dataNum = dataNum;
        }

        /**
         * @return the orderNum
         */
        public Integer getOrderNum() {
                return orderNum;
        }

        /**
         * @param orderNum the orderNum to set
         */
        public void setOrderNum(Integer orderNum) {
                this.orderNum = orderNum;
        }


}
