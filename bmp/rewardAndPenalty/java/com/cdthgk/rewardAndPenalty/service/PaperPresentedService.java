package com.cdthgk.rewardAndPenalty.service;

import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.platform.permission.user.domain.User;
import com.cdthgk.rewardAndPenalty.vo.PaperPresented;

import ec.common.PageSortModel;

/**
 * @description 会议管理Service
 * @author huxj 2009 12 16 12:34:56
 * @modify 陈文聪 2010 8 18 07:57:50 修改注释格式
 */
@SuppressWarnings("all")
public interface PaperPresentedService extends BmpServiceTemplate<PaperPresented, String>{

	List getAllPaperPresented();

	List getList(PageSortModel psm, PaperPresented paperPresented);

	public List<PaperPresented> getPageList(PageSortModel psm, PaperPresented paperPresented, Map<String, Object> params,User user);

        public List<PaperPresented>  listForSelect(PageSortModel<PaperPresented> psm, PaperPresented paperPresented, String districtCode,
                        String includeChild);
}
