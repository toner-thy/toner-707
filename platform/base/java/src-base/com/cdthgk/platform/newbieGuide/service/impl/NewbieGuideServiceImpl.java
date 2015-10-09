/**
 *
 */
package com.cdthgk.platform.newbieGuide.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.common.lang.StringUtils;
import com.cdthgk.platform.base.service.BaseServiceImpl;
import com.cdthgk.platform.newbieGuide.service.NewbieGuideService;
import com.cdthgk.platform.newbieGuide.vo.SysNewbieGuide;
import com.cdthgk.platform.permission.user.domain.User;

/**
 * <p>
 * 类的说明放这里
 * </p>
 * <p>
 * 创建人 宋亚非  创建时间 2014-6-12 - 上午10:48:31
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
public class NewbieGuideServiceImpl extends BaseServiceImpl<SysNewbieGuide, String> implements NewbieGuideService {

        /* (non-Javadoc)
         * @see com.cdthgk.platform.newbieGuide.service.NewbieGuideService#listAll()
         */
        @Override
        public List<SysNewbieGuide> listAll(User currentUser) {
                StringBuilder sb = new StringBuilder();
                Map<String , Object> params = new HashMap<String, Object>();
                sb.append("From SysNewbieGuide s where s.status = :status ");
                params.put("status", 1);
                sb.append(" order by s.orderNum asc");
                List<SysNewbieGuide> returnList = this.findList(sb.toString(), params);
                if( returnList!=null && returnList.size()>0 ){
                        for( SysNewbieGuide sng : returnList ){
                                Long dataNum = ObjectDataNum(sng.getTableName(), sng.getStatusColumnStr(), currentUser);
                                sng.setDataNum(dataNum);
                        }
                }
                return returnList;
        }

        private Long ObjectDataNum(String tableObjectName, String statusColumnStr, User currentUser){
                Long dataNum = 0L;
                if( tableObjectName!=null && !"".equals(tableObjectName) ){
                        StringBuilder sb = new StringBuilder();
                        Map<String, Object> params = new HashMap<String, Object>();
                        sb.append("select count(*) from ");
                        sb.append(tableObjectName);
                        sb.append(" where 1=1 ");
                        if( StringUtils.isNotEmpty(statusColumnStr)){
                                sb.append(statusColumnStr);
                        }
                        params.put("organ", currentUser.getOrgan());
                        dataNum = this.count(sb.toString(), params);
                }

                return dataNum;
        }
}
