package com.cdthgk.disclosesecrecy.service;

import java.util.List;

import com.cdthgk.disclosesecrecy.vo.CaseCriticalviolation;
import com.cdthgk.platform.base.service.GenericBasicService;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

public interface CaseCriticalviolationService extends
GenericBasicService<CaseCriticalviolation, String> {

	/**
	 *导出时
	 * @param psm
	 * @param discloseSecrecy
	 * @param user
	 * @return
	 */
	public List<CaseCriticalviolation> listForAll(CaseCriticalviolation caseCriticalviolation,User user,String districtCode,String includeChild);
	/**
	 * 本单位或者是保密局 严重违规案件 列表
	 * @param psm 分页
	 * @param discloseSecrecy 查询时传入
	 * @param user 登陆用户
	 * @param baomijuObendanwei false查询本单位的，true 查询保密局的
	 * @return
	 */
	public List<CaseCriticalviolation> listForEc(PageSortModel<CaseCriticalviolation> psm,
			CaseCriticalviolation caseCriticalviolation, User user,boolean baomijuOrbendanwei);
	public int getCaseCriticalviolationIdRelationship(String caseCriticalviolationId);
	public List<CaseCriticalviolation> listForSelect(PageSortModel<CaseCriticalviolation> psm,
			CaseCriticalviolation caseCriticalviolation, String districtCode,String includeChild);

}
