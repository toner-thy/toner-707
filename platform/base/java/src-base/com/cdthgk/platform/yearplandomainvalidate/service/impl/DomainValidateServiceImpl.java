/**
 *
 */
package com.cdthgk.platform.yearplandomainvalidate.service.impl;

import java.util.List;

import com.cdthgk.platform.base.service.BaseServiceImpl;
import com.cdthgk.platform.permission.domain.domain.Domain;
import com.cdthgk.platform.yearplandomainvalidate.service.DomainValidateService;

/**
 * <p>
 * 类的说明放这里
 * </p>
 * <p>
 * 创建人 宋亚非  创建时间 2014-7-10 - 上午10:12:33
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
public class DomainValidateServiceImpl extends BaseServiceImpl<Object, String> implements DomainValidateService {

        /* (non-Javadoc)
         * @see com.cdthgk.platform.yearplandomainvalidate.service.DomainValidateService#validateDomain(java.util.List)
         */
        @Override
        public Boolean validateDomain(List<Domain> domainList) {
//                ca82caf647245d670147246078e00000   制定计划任务
//                ca82cacb46d71e6a0146d73293550006   计划任务书查询
//                ca82cacb46d71e6a0146d73293550116   计划完成进度查询
                Boolean flag1 = false;
                Boolean flag2 = false;
                Boolean flag3 = false;

                if( domainList!=null && domainList.size()>0 ){
                        for( Domain domain : domainList ){
                               if( domain.getDomainId().equals("ca82caf647245d670147246078e00000") ){
                                       flag1 = true;
                                       continue;
                               }
                               if( domain.getDomainId().equals("ca82cacb46d71e6a0146d73293550006") ){
                                       flag2 = true;
                                       continue;
                               }
                               if( domain.getDomainId().equals("ca82cacb46d71e6a0146d73293550116") ){
                                       flag3 = true;
                                       continue;
                               }
                        }
                }
                return flag1 && flag2 && flag3;
        }


}
