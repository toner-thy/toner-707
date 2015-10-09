/**
 *
 */
package com.cdthgk.platform.dataValidate.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.platform.base.service.BaseServiceImpl;
import com.cdthgk.platform.dataValidate.service.DataValidateLogService;
import com.cdthgk.platform.dataValidate.vo.DataValidateLog;
import com.cdthgk.platform.permission.user.domain.User;

/**
 * <p>
 * 类的说明放这里
 * </p>
 * <p>
 * 创建人 宋亚非  创建时间 2014-5-30 - 下午2:54:25
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
public class DataValidateLogServiceImpl extends BaseServiceImpl<DataValidateLog, String> implements DataValidateLogService {

        /* (non-Javadoc)
         * @see com.cdthgk.platform.dataValidate.service.DataValidateLogService#getLastCheckRecord()
         */
        @Override
        public DataValidateLog getLastCheckRecord(User currentUser) {
                Map<String, Object> params = new HashMap<String, Object>();
                StringBuilder sb = new StringBuilder();
                sb.append("FROM DataValidateLog dvl where dvl.createOrgan = :createOrgan AND dvl.createTime = (Select max(d.createTime) from DataValidateLog d where d.createOrgan = :createOrgan2 )");
                params.put("createOrgan", currentUser.getOrgan());
                params.put("createOrgan2", currentUser.getOrgan());
                List<DataValidateLog> datavalidateLogList = this.findList(sb.toString(), params);
                if( datavalidateLogList!=null && datavalidateLogList.size()>0){
                        return datavalidateLogList.get(0);
                }
                return null;
        }


}
