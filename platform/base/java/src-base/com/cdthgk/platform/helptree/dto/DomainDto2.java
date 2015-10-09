package com.cdthgk.platform.helptree.dto;

import java.util.List;

import com.cdthgk.platform.permission.domain.domain.Domain;

public class DomainDto2 {
        String domainName;
       List<Domain> list;
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
public List<Domain> getList() {
        return list;
}
/**
 * @param list 设置 list
 */
public void setList(List<Domain> list) {
        this.list = list;
}
}
