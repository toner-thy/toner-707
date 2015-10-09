/**
 *
 */
package com.cdthgk.platform.newbieGuide.service;

import java.util.List;

import com.cdthgk.platform.base.service.BaseServiceTemplate;
import com.cdthgk.platform.newbieGuide.vo.SysNewbieGuide;
import com.cdthgk.platform.permission.user.domain.User;

/**
 * <p>
 * 类的说明放这里
 * </p>
 * <p>
 * 创建人 宋亚非  创建时间 2014-6-12 - 上午10:45:28
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
public interface NewbieGuideService extends BaseServiceTemplate<SysNewbieGuide, String> {

        /**
         * <p>
         * 方法的说明
         * </p>
         * <p>
         * 创建人 宋亚非  创建时间 2014-6-12 上午11:14:30
         * </p>
         * <blockquote>
         * <h4>历史修改记录</h4>
         * <ul>
         * <li>修改人 修改时间 修改描述
         * </ul>
         * </blockquote>
         * @return
         */
        List<SysNewbieGuide> listAll(User currentUser);

}
