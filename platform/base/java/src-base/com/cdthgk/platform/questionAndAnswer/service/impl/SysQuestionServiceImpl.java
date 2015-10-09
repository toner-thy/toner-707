/**
 *
 */
package com.cdthgk.platform.questionAndAnswer.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.platform.base.service.BaseServiceImpl;
import com.cdthgk.platform.permission.user.domain.User;
import com.cdthgk.platform.questionAndAnswer.service.SysQuestionService;
import com.cdthgk.platform.questionAndAnswer.vo.SysQuestion;

import ec.common.PageSortModel;

/**
 * <p>
 * 类的说明放这里
 * </p>
 * <p>
 * 创建人 宋亚非  创建时间 2014-6-4 - 上午11:00:31
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
public class SysQuestionServiceImpl extends BaseServiceImpl<SysQuestion, String> implements SysQuestionService {

        /* (non-Javadoc)
         * @see com.cdthgk.platform.questionAndAnswer.service.SysQuestionService#getListPage(ec.common.PageSortModel, com.cdthgk.platform.questionAndAnswer.vo.SysQuestion, com.cdthgk.platform.permission.user.domain.User)
         */
        @Override
        public List<SysQuestion> getListPage(PageSortModel<SysQuestion> psm, SysQuestion sysQuestion, User currentUser) {

                List<SysQuestion> returnList = null;
                StringBuilder hql = new StringBuilder();
                Map<String, Object> params = new HashMap<String, Object>();
                hql.append("FROM SysQuestion s where s.state = :state");
                params.put("state", 1);
                if( sysQuestion!=null ){
                        if( sysQuestion.getQuestionTitle()!=null && !"".equals(sysQuestion.getQuestionTitle()) ){
                                hql.append(" and s.questionTitle like :questionTitle ");
                                params.put("questionTitle", "%"+ sysQuestion.getQuestionTitle() +"%");
                        }
                        if( sysQuestion.getQuestionContent()!=null && !"".equals(sysQuestion.getQuestionContent()) ){
                                hql.append(" and s.questionContent like :questionContent ");
                                params.put("questionContent", "%"+ sysQuestion.getQuestionContent() +"%");
                        }
                        if( sysQuestion.getQuestionCategory()!=null ){
                                hql.append(" and s.questionCategory = :questionCategory ");
                                params.put("questionContent", sysQuestion.getQuestionCategory());
                        }
                }

                if( psm!=null ){
                        returnList = this.findList(hql.toString(), params, psm);
                }else{
                        returnList = this.findList(hql.toString(), params);
                }
                return returnList;
        }

        /* (non-Javadoc)
         * @see com.cdthgk.platform.questionAndAnswer.service.SysQuestionService#deleteSelectedId(java.lang.String)
         */
        @Override
        public void deleteSelectedId(String deleteIds) {
                if( deleteIds!=null && !"".equals(deleteIds) ){
                        String[] ids = deleteIds.split(",");
                        if( ids!=null && ids.length>0 ){
                                for( String id : ids ){
                                        SysQuestion sqDb = this.get(id);
                                        if( sqDb!=null ){
                                                sqDb.setState(0);
                                                this.update(sqDb);
                                        }
                                }
                        }
                }
        }



}
