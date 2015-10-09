/**
 *
 */
package com.cdthgk.platform.questionAndAnswer.service;

import java.util.List;

import com.cdthgk.platform.base.service.BaseServiceTemplate;
import com.cdthgk.platform.permission.user.domain.User;
import com.cdthgk.platform.questionAndAnswer.vo.SysQuestion;
import com.sun.xml.internal.bind.v2.model.core.ID;

import ec.common.PageSortModel;

/**
 * <p>
 * 类的说明放这里
 * </p>
 * <p>
 * 创建人 宋亚非  创建时间 2014-6-4 - 上午10:53:12
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
public interface SysQuestionService extends BaseServiceTemplate<SysQuestion, String> {

        /**
         * <p>
         * 方法的说明
         * </p>
         * <p>
         * 创建人 宋亚非  创建时间 2014-6-4 上午11:37:23
         * </p>
         * <blockquote>
         * <h4>历史修改记录</h4>
         * <ul>
         * <li>修改人 修改时间 修改描述
         * </ul>
         * </blockquote>
         * @param psm
         * @param sysQuestion
         * @param currentUser
         * @return
         */
        List<SysQuestion> getListPage(PageSortModel<SysQuestion> psm, SysQuestion sysQuestion, User currentUser);

        /**
         * <p>
         * 方法的说明
         * </p>
         * <p>
         * 创建人 宋亚非  创建时间 2014-6-4 下午3:00:37
         * </p>
         * <blockquote>
         * <h4>历史修改记录</h4>
         * <ul>
         * <li>修改人 修改时间 修改描述
         * </ul>
         * </blockquote>
         * @param deleteIds
         */
        void deleteSelectedId(String deleteIds);

}
