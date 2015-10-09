/**
 *
 */
package com.cdthgk.bmp.publicityEducation.informationReview.service;

import java.io.Serializable;
import java.util.List;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.bmp.publicityEducation.informationReview.vo.InformationReview;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;

/**
 * <p>
 * 类的说明放这里
 * </p>
 * <p>
 * 创建人 宋亚非  创建时间 2014-5-5 - 上午11:13:18
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
public interface InformationReviewService extends BmpServiceTemplate<InformationReview, Serializable> {

        /**
         * <p>
         * 方法的说明
         * </p>
         * <p>
         * 创建人 宋亚非  创建时间 2014-5-6 上午10:30:03
         * </p>
         * <blockquote>
         * <h4>历史修改记录</h4>
         * <ul>
         * <li>修改人 修改时间 修改描述
         * </ul>
         * </blockquote>
         * @param psm
         * @param informationReview
         * @param organ
         * @return
         */
        List<InformationReview> getListPage(PageSortModel<InformationReview> psm, InformationReview informationReview,
                        Organ organ,District district, Boolean flag, String checkLower);

        /**
         * <p>
         * 方法的说明
         * </p>
         * <p>
         * 创建人 宋亚非  创建时间 2014-5-6 上午10:30:08
         * </p>
         * <blockquote>
         * <h4>历史修改记录</h4>
         * <ul>
         * <li>修改人 修改时间 修改描述
         * </ul>
         * </blockquote>
         * @param deleteIds
         */
        void deleteSelected(String deleteIds);

		/**
		 * <p>
		 * 方法的说明
		 * </p>
		 * <p>
		 * 创建人 陶汇源  创建时间  2014-5-12 - 下午4:29:08
		 * </p>
		 * <blockquote>
		 * <h4>历史修改记录</h4>
		 * <ul>
		 * <li>修改人 修改时间 修改描述
		 * </ul>
		 * </blockquote>
		 * @param string
		 * @param string2
		 * @param informationSources
		 * @return
		 */
		String dealOptions2Txt(String tableId, String field, String informationSources);

}
