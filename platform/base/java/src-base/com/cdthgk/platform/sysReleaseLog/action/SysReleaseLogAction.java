/**
 *
 */
package com.cdthgk.platform.sysReleaseLog.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.platform.base.action.PlatformAction;
import com.cdthgk.platform.sysReleaseLog.service.SysReleaseLogService;
import com.cdthgk.platform.sysReleaseLog.vo.SysReleaseLog;

/**
 * <p>
 * 类的说明放这里
 * </p>
 * <p>
 * 创建人 宋亚非  创建时间 2014-5-26 - 下午5:00:03
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
public class SysReleaseLogAction extends PlatformAction {
    /**
	 *
	 */
	private static final long serialVersionUID = 1L;
		private SysReleaseLogService sysReleaseLogService;

        public String detail(){
               List<SysReleaseLog> sysReleaseLogList = this.sysReleaseLogService.listAll();
               Map<Integer,List<SysReleaseLog>> sysReleaseLogMap = new LinkedHashMap<Integer, List<SysReleaseLog>>();
               if( sysReleaseLogList!=null && sysReleaseLogList.size()>0 ){
                       for( SysReleaseLog srl : sysReleaseLogList ){
                               Calendar calender = Calendar.getInstance();
                               calender.setTime(srl.getReleaseDate());
                               Integer year = calender.get(Calendar.YEAR);
                               List<SysReleaseLog> tmpSrlList = sysReleaseLogMap.get(year);
                               if( tmpSrlList!=null && tmpSrlList.size()>0 ){
                                       tmpSrlList.add(srl);
                               }else{
                                       tmpSrlList = new ArrayList<SysReleaseLog>();
                                       tmpSrlList.add(srl);
                                       sysReleaseLogMap.put(year, tmpSrlList);
                               }
                       }
               }else{
                       Calendar calender = Calendar.getInstance();
                       sysReleaseLogMap.put(calender.get(Calendar.YEAR), null);
               }

               this.putToRequest("sysReleaseLogMap", sysReleaseLogMap);
               return SUCCESS;
        }


















/**************************************** getter && setter ******************************************************/
        /**
         * @return the sysReleaseLogService
         */
        public SysReleaseLogService getSysReleaseLogService() {
                return sysReleaseLogService;
        }




        /**
         * @param sysReleaseLogService the sysReleaseLogService to set
         */
        public void setSysReleaseLogService(SysReleaseLogService sysReleaseLogService) {
                this.sysReleaseLogService = sysReleaseLogService;
        }

}
