/**
 *
 */
package com.cdthgk.platform.sysReleaseLog.service;

import java.util.List;

import com.cdthgk.platform.base.service.GenericBasicService;
import com.cdthgk.platform.sysReleaseLog.vo.SysReleaseLog;

/**
 * <p>
 * 类的说明放这里
 * </p>
 * <p>
 * 创建人 宋亚非  创建时间 2014-5-26 - 下午4:55:47
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
public interface SysReleaseLogService extends GenericBasicService<SysReleaseLog, String> {

        /**
         * <p>
         * 方法的说明
         * </p>
         * <p>
         * 创建人 宋亚非  创建时间 2014-5-26 下午5:16:19
         * </p>
         * <blockquote>
         * <h4>历史修改记录</h4>
         * <ul>
         * <li>修改人 修改时间 修改描述
         * </ul>
         * </blockquote>
         * @return
         */
        List<SysReleaseLog> listAll();

}
