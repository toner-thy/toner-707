package com.cdthgk.checkevent.service;

import java.util.List;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.checkevent.vo.SecrecyCheckEvent;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

/**
 * @ 类用途
 * @ 创建人 hxj
 * @ 创建时间 Oct 12, 2009 - 9:38:28 AM
 * @ 修改人
 * @ 修改时间
 * @ 修改描述
 * @ 公司名称
 * @ 当前系统主版本号
 */
@SuppressWarnings("all")
public interface SecrecyCheckEventService extends BmpServiceTemplate<SecrecyCheckEvent, String>{

	List listForEc(PageSortModel psm, SecrecyCheckEvent secrecyCheckEvent, Organ organ);
        public void updates(SecrecyCheckEvent secrecyCheckEvent,User user);
	void updateEvent(SecrecyCheckEvent secrecyCheckEvent, String tableIds,User user);

	/**
	 * @param showType
	 * @description 所有保密检查事件
	 * @author 杨  成 2009-10-12 10:12
	 * @param PageSortModel psm
	 * @param RewardAndPenalty secrecyCheckEvent
	 * @return list
	 */
	List allListForEc(PageSortModel psm, SecrecyCheckEvent secrecyCheckEvent,
			String showType, District district);

}
