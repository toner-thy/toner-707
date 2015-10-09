/**
 *
 */
package com.cdthgk.bmp.planSndSummary.annualPlan.tag;

import com.cdthgk.component.easytag.core.ScopesHashModel;
import com.cdthgk.platform.ui.tag.SelectionTagSupport;

/**
 * <p>
 * 类的说明放这里
 * </p>
 * <p>
 * 创建人 宋亚非  创建时间 2014-4-30 - 下午2:02:34
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
public class AnnualPlanSelectTag extends SelectionTagSupport {

        /**
         *
         */
        private static final long serialVersionUID = 1L;

        /* (non-Javadoc)
         * @see com.cdthgk.component.easytag.core.AbstractTemplateTagSupport#doBeforeMergeTemplate(com.cdthgk.component.easytag.core.ScopesHashModel)
         */
        @Override
        public void doBeforeMergeTemplate(ScopesHashModel model) {
                model.put("selectUri", "/bmp/annualPlanSelect/singelSelect.action");//页面
                model.put("autocompleteUri", "/bmp/annualPlanSelect/autocomplete.action");//自动补全
                model.put("title", "年度工作计划");
                model.put("autocompleteQueryName", "annualPlanQo.name");
        }

        @Override
        public int doAfterMergeTemplate() {
                return SKIP_BODY;
        }

}
