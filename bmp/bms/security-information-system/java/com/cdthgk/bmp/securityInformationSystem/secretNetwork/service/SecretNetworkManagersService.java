/**
 *
 */
package com.cdthgk.bmp.securityInformationSystem.secretNetwork.service;

import java.io.Serializable;
import java.util.List;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
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
 * 创建人 宋亚非  创建时间 2014-5-9 - 下午4:07:47
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
public interface SecretNetworkManagersService extends BmpServiceTemplate<SecretNetworkManagers, Serializable> {

        /**
         * <p>
         * 方法的说明
         * </p>
         * <p>
         * 创建人 宋亚非  创建时间 2014-5-14 下午2:34:57
         * </p>
         * <blockquote>
         * <h4>历史修改记录</h4>
         * <ul>
         * <li>修改人 修改时间 修改描述
         * </ul>
         * </blockquote>
         * @param secretNetwork
         * @return
         */
        List<SecretNetworkManagers> getManagersByNetwork(SecretNetwork secretNetwork);



}
