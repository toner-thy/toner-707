package com.cdthgk.rewardAndPenalty.service;

import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.platform.permission.user.domain.User;
import com.cdthgk.rewardAndPenalty.vo.RewardAndPenalty;

import ec.common.PageSortModel;

/**
 * @description 会议管理Service
 * @author huxj 2009 12 16 12:34:56
 * @modify 陈文聪 2010 8 18 07:57:50 修改注释格式
 */
@SuppressWarnings("all")
public interface RewardAndPenaltyService extends BmpServiceTemplate<RewardAndPenalty, String>{

	List getAllRewardAndPenalty();

	List getList(PageSortModel psm, RewardAndPenalty rewardAndPenalty);

	public List<RewardAndPenalty> getPageList(PageSortModel psm, RewardAndPenalty rewardAndPenalty, Map<String, Object> params,User user);

        public List<RewardAndPenalty>  listForSelect(PageSortModel<RewardAndPenalty> psm, RewardAndPenalty rewardAndPenalty, String districtCode,
                        String includeChild);
}
