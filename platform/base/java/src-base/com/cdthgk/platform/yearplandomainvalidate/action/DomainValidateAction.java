/**
 *
 */
package com.cdthgk.platform.yearplandomainvalidate.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.platform.base.action.BaseAction;
import com.cdthgk.platform.helptree.service.HelpTreeService;
import com.cdthgk.platform.permission.domain.domain.Domain;
import com.cdthgk.platform.yearplandomainvalidate.service.DomainValidateService;

/**
 * <p>
 * 类的说明放这里
 * </p>
 * <p>
 * 创建人 宋亚非  创建时间 2014-7-10 - 上午9:58:08
 * </p>
 * <blockquote>
 * <h4>历史修改记录</h4>
 * <ul>
 * <li>修改人 修改时间 修改描述
 * </ul>
 * </blockquote>
 * <p>
 * copyright cdthgk 2010-2011, all rights reserved.
 * </p>
 *
 * @author 钟冀
 * @author cdthgk r&d
 * @since 1.0
 * @version 1.0
 */
public class DomainValidateAction extends BaseAction{

        private HelpTreeService helpTreeService;

        private DomainValidateService domainValidateService;

        public String checkDomain(){
                List<Domain> domainList = helpTreeService.queryDomainByUser(getCurrentUser());
                Boolean hasAuthority = domainValidateService.validateDomain(domainList);
                Map<String, String> resultMap = new HashMap<String, String>();
                resultMap.put("flag", hasAuthority.toString());
                this.setResultData(resultMap);
                return JSON;
        }

        /**
         * @return the helpTreeService
         */
        public HelpTreeService getHelpTreeService() {
                return helpTreeService;
        }

        /**
         * @param helpTreeService the helpTreeService to set
         */
        public void setHelpTreeService(HelpTreeService helpTreeService) {
                this.helpTreeService = helpTreeService;
        }

        /**
         * @return the domainValidateService
         */
        public DomainValidateService getDomainValidateService() {
                return domainValidateService;
        }

        /**
         * @param domainValidateService the domainValidateService to set
         */
        public void setDomainValidateService(DomainValidateService domainValidateService) {
                this.domainValidateService = domainValidateService;
        }

}
