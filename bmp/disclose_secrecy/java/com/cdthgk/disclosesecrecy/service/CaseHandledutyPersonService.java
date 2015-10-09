package com.cdthgk.disclosesecrecy.service;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.service.BmpServiceTemplate;
import com.cdthgk.disclosesecrecy.vo.CaseCriticalviolation;
import com.cdthgk.disclosesecrecy.vo.CaseHandledutyPerson;
import com.cdthgk.disclosesecrecy.vo.DiscloseSecrecy;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;
public interface CaseHandledutyPersonService
extends BmpServiceTemplate<CaseHandledutyPerson, String>{

	List<CaseHandledutyPerson> queryCaseHandledutyPersonList(
			PageSortModel<CaseHandledutyPerson> psm,DiscloseSecrecy discloseSecrecy,CaseCriticalviolation caseCriticalviolation);

	/**
	 *统计处理责任人数量
	 * @param organId 部门id
	 * @param handle_type 处理刑事
	 * @param distrust_code 区域编码
	 * @param groupBy 分组字段（manage_level 行政级别，political_landscape 政治面貌）
	 * @return List<Map<String, Object> 统计结果（Map（key=分组字段值,value=数量））
	 */
	public List<Map<String, Object>> countCaseHandledutyPerson(String organId,
			String handle_type, String groupBy,String className,String distrust_code,boolean zhixia) ;
	/**
	 * 获取处理形式字典值
	 * @return
	 */
	public List<Map<String, Object>> getHandleType();
	public CaseHandledutyPerson saveCaseHandledutyPerson(CaseHandledutyPerson e,User currentUser);
	public CaseHandledutyPerson updateCaseHandledutyPerson(CaseHandledutyPerson e,User currentUser);
	/**
	 * 获取政治面貌字典值
	 * @return
	 */
	List<Map<String, Object>> getPolitical();
}
