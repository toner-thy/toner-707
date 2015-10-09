package com.cdthgk.secrecyCarrier.service;

import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.platform.permission.user.domain.User;
import com.cdthgk.secrecyCarrier.vo.SecrecyPrint;

import ec.common.PageSortModel;

@SuppressWarnings("all")
public interface SecrecyPrintService extends BmpServiceTemplate<SecrecyPrint, String>{

	List getAllSecrecyPrint();

	List getList(PageSortModel psm, SecrecyPrint secrecyPrint);

	public List<SecrecyPrint> getPageList(PageSortModel psm, SecrecyPrint secrecyPrint, Map<String, Object> params,User user);

        public List<SecrecyPrint>  listForSelect(PageSortModel<SecrecyPrint> psm, SecrecyPrint secrecyPrint, String districtCode,
                        String includeChild);

        /**
         * <p>
         * 方法的说明
         * </p>
         * <p>
         * 创建人 宋亚非  创建时间 2014-5-16 下午2:26:19
         * </p>
         * <blockquote>
         * <h4>历史修改记录</h4>
         * <ul>
         * <li>修改人 修改时间 修改描述
         * </ul>
         * </blockquote>
         * @param userInfoIds
         * @return
         */
        String userInfoIds2Names(String userInfoIds);
}
