/**
 *
 */
package com.cdthgk.platform.questionAndAnswer.action;

import com.cdthgk.platform.base.action.BaseAction;
import com.cdthgk.platform.questionAndAnswer.service.SysAnswerService;

/**
 * <p>
 * 类的说明放这里
 * </p>
 * <p>
 * 创建人 宋亚非  创建时间 2014-6-4 - 上午11:12:08
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
public class SysAnswerAction extends BaseAction {

        private SysAnswerService sysAnswerService;

        /**
         * @return the sysAnswerService
         */
        public SysAnswerService getSysAnswerService() {
                return sysAnswerService;
        }

        /**
         * @param sysAnswerService the sysAnswerService to set
         */
        public void setSysAnswerService(SysAnswerService sysAnswerService) {
                this.sysAnswerService = sysAnswerService;
        }



}
