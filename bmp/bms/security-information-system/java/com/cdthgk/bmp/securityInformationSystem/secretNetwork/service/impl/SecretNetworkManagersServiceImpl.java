/**
 *
 */
package com.cdthgk.bmp.securityInformationSystem.secretNetwork.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceImpl;
import com.cdthgk.bmp.securityInformationSystem.secretNetwork.service.SecretNetworkManagersService;
import com.cdthgk.bmp.securityInformationSystem.secretNetwork.service.SecretNetworkService;
import com.cdthgk.bmp.securityInformationSystem.secretNetwork.vo.SecretNetwork;
import com.cdthgk.bmp.securityInformationSystem.secretNetwork.vo.SecretNetworkManagers;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

/**
 * <p>
 * 类的说明放这里
 * </p>
 * <p>
 * 创建人 宋亚非  创建时间 2014-5-9 - 下午4:09:26
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
public class SecretNetworkManagersServiceImpl extends BmpServiceImpl<SecretNetworkManagers, Serializable> implements SecretNetworkManagersService {

        /* (non-Javadoc)
         * @see com.cdthgk.bmp.securityInformationSystem.secretNetwork.service.SecretNetworkManagersService#getManagersByNetwork(com.cdthgk.bmp.securityInformationSystem.secretNetwork.vo.SecretNetwork)
         */
        @Override
        public List<SecretNetworkManagers> getManagersByNetwork(SecretNetwork secretNetwork) {
                List<SecretNetworkManagers> returnList = new ArrayList<SecretNetworkManagers>();
                if(secretNetwork!=null){
                        Map<String, Object> params = new HashMap<String, Object>();
                        StringBuilder hql = new StringBuilder("FROM SecretNetworkManagers a WHERE bmSecretNetwork = :sn");
                        params.put("sn", secretNetwork);
                        returnList = this.findList(hql.toString(), params);
                }
                return returnList;
        }



}
