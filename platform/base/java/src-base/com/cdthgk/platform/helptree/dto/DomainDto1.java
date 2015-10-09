package com.cdthgk.platform.helptree.dto;

import java.util.List;


public class DomainDto1 {

        String domainName;
        List<DomainDto2> list;
        /**
         * @return 返回 domainName
         */
        public String getDomainName() {
                return domainName;
        }
        /**
         * @param domainName 设置 domainName
         */
        public void setDomainName(String domainName) {
                this.domainName = domainName;
        }
        /**
         * @return 返回 list
         */
        public List<DomainDto2> getList() {
                return list;
        }
        /**
         * @param list 设置 list
         */
        public void setList(List<DomainDto2> list) {
                this.list = list;
        }


}
