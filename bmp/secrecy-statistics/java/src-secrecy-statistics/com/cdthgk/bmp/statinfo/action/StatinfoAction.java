package com.cdthgk.bmp.statinfo.action;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.cdthgk.bmp.core.action.BmpAction;
import com.cdthgk.bmp.statinfo.dto.QueryDto;
import com.cdthgk.bmp.statinfo.service.StatinfoService;
import com.cdthgk.common.lang.CollectionUtils;
import com.cdthgk.common.lang.StringUtils;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.core.OrganizationContext;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.organ.service.OrganService;

import ec.common.PageSortModel;

/**
 *
 * <p>
 * 保密工作信息总览
 * </p>
 * <p>
 * 创建时间 2014-5-5 - 下午3:54:53
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
 * @author 陶汇源
 * @author cdthgk r&d
 * @since 1.0
 * @version 1.0
 */
public class StatinfoAction extends BmpAction{

	private static final long serialVersionUID = 1L;
	private Organ organ;
	private QueryDto queryDto;
	private StatinfoService statinfoService;
	private List<String> selectedModuleValueList;
	private OrganService organService;

	// *********************** 方 法 ***********************

	// 构造器
	/** 默认构造器 */
	public StatinfoAction() {
	}

	/**
	 *
	 * <p>
	 * 保密工作台账主页
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2014-5-9 - 上午9:04:26
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @return
	 */
	public String index(){
		if(queryDto == null){
			queryDto = new QueryDto();
			queryDto.setYear(Calendar.getInstance().get(Calendar.YEAR));
			queryDto.setMonth(Calendar.getInstance().get(Calendar.MONTH));
		}
		// 获取所有业务模块
		List<Map<String, Object>> list = statinfoService.getAllBusinessModule();
		putToRequest("list", list);
		// 获取选中模块
		List<Map<String, Object>> selectedModuleList = getSelectedModuleList(list);
		putToRequest("selectedModuleList", selectedModuleList);
		// 动态获取标题
		String title = queryDto.getYear() + "年" + queryDto.getMonth() + "月";
		putToRequest("title", title);
		if (organ == null) {
			organ = getCurrentUser().getOrgan();
		} else {
			organ = organService.get(organ.getOrganId());
		}
		// 是否显示“返回”按钮
		String flag = getRequest().getParameter("flag");
		if (StringUtils.isNotEmpty(flag) && flag.equals("1")) {
			putToRequest("flag", "1");
		} else{
			putToRequest("flag", "0");
		}
		return SUCCESS;
	}

	/**
	 *
	 * <p>
	 * 导出保密工作台账
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2014-5-9 - 上午9:05:43
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @return
	 */
	public String xmlExport(){
		List<Map<String, Object>> list = statinfoService.getAllBusinessModule();
		List<Map<String, Object>> selectedModuleList = getSelectedModuleList(list);
		organ = organService.get(organ.getOrganId());
		Map<String, Object> dataMap = statinfoService.buildData(selectedModuleList, organ);
		//设置DOC表头
		ServletActionContext.getResponse().setContentType("application/msword");
		String fileName = "【" + organ.getOrganName() + "】保密工作台账";
		try {
			fileName = new String(fileName.getBytes("gb2312"), "ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		ServletActionContext.getResponse().addHeader("Content-disposition", "attachement;filename=" + fileName + ".doc");
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		putToRequest("dataMap", dataMap);
		return "xmlExport";
	}

	/**
	 *
	 * <p>
	 * 选择导出保密工作台账（不含表头）
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2014-5-9 - 上午9:05:43
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @return
	 */
	public String xmlExportSelected(){
		List<Map<String, Object>> list = statinfoService.getAllBusinessModule();
		List<Map<String, Object>> selectedModuleList = getSelectedModuleList(list);
		organ = organService.get(organ.getOrganId());
		Map<String, Object> dataMap = statinfoService.buildData(selectedModuleList, organ);
		//设置DOC表头
		ServletActionContext.getResponse().setContentType("application/msword");
		String fileName = "【" + organ.getOrganName() + "】保密业务概览";
		try {
			fileName = new String(fileName.getBytes("gb2312"), "ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		ServletActionContext.getResponse().addHeader("Content-disposition", "attachement;filename=" + fileName + ".doc");
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		dataMap.put("selectedModuleList", selectedModuleList);
		putToRequest("dataMap", dataMap);
		return "xmlExport";
	}

	private List<Map<String, Object>> getSelectedModuleList(List<Map<String, Object>> list){
		List<Map<String, Object>> selectedModuleList = new ArrayList<Map<String,Object>>();
		for (Map<String, Object> map : list) {
			if(CollectionUtils.isNotEmpty(selectedModuleValueList)){
				for (String selected : selectedModuleValueList) {
					if(selected != null && selected.equals(map.get("id"))){
						selectedModuleList.add(map);
					}
				}
			}
		}
		if(CollectionUtils.isEmpty(selectedModuleList)){
			selectedModuleList = list;
		}
		return selectedModuleList;
	}

	public String main(){
		return SUCCESS;
	}

	public String mainList(){
		if(organ == null) {
			organ = new Organ();
			organ.setDistrict(getCurrentUser().getOrgan().getDistrict());
		} else {
			organ.setDistrict(organService.get(organ.getDistrict().getDistrictCode(), District.class));
		}
		PageSortModel<Organ> psm = new PageSortModel<Organ>(getRequest(), getListId());
		List<Organ> list =  organService.findList(organ, psm);
		putToRequest("list", list);
		return SUCCESS;
	}

	/**
	 * @return 返回organ
	 */
	public Organ getOrgan() {
		return organ;
	}

	/**
	 * @param organ 设置organ
	 */
	public void setOrgan(Organ organ) {
		this.organ = organ;
	}

	/**
	 * @return 返回queryDto
	 */
	public QueryDto getQueryDto() {
		return queryDto;
	}

	/**
	 * @param queryDto 设置queryDto
	 */
	public void setQueryDto(QueryDto queryDto) {
		this.queryDto = queryDto;
	}
	/**
	 * @return 返回selectedModuleValueList
	 */
	public List<String> getSelectedModuleValueList() {
		return selectedModuleValueList;
	}

	/**
	 * @param selectedModuleValueList 设置selectedModuleValueList
	 */
	public void setSelectedModuleValueList(List<String> selectedModuleValueList) {
		this.selectedModuleValueList = selectedModuleValueList;
	}

	/**
	 * @param statinfoService 设置statinfoService
	 */
	public void setStatinfoService(StatinfoService statinfoService) {
		this.statinfoService = statinfoService;
	}

	/**
	 * @param organService 设置organService
	 */
	public void setOrganService(OrganService organService) {
		this.organService = organService;
	}
}