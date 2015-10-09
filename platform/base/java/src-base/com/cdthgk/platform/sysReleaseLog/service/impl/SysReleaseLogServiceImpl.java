/**
 *
 */
package com.cdthgk.platform.sysReleaseLog.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.platform.base.service.GenericServiceTemplate;
import com.cdthgk.platform.sysReleaseLog.service.SysReleaseLogService;
import com.cdthgk.platform.sysReleaseLog.vo.SysReleaseLog;

/**
 * <p>
 * 类的说明放这里
 * </p>
 * <p>
 * 创建人 宋亚非  创建时间 2014-5-26 - 下午4:57:53
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
public class SysReleaseLogServiceImpl extends GenericServiceTemplate<SysReleaseLog, String> implements SysReleaseLogService {

        /* (non-Javadoc)
         * @see com.cdthgk.platform.sysReleaseLog.service.SysReleaseLogService#listAll()
         */
        @Override
        public List<SysReleaseLog> listAll() {
                Map<String,Object> params = new HashMap<String, Object>();
                StringBuilder sb = new StringBuilder("FROM SysReleaseLog s ");
                sb.append("ORDER BY s.releaseDate desc");
                return this.findList(sb.toString(), params);
        }


}
