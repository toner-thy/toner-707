/**
 *
 */
package com.cdthgk.bmp.secrecysystem.statistics.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.action.BmpAction;
import com.cdthgk.bmp.secrecyorganization.secrecyoffice.dto.ObjectNumberFomart;
import com.cdthgk.bmp.secrecysystem.statistics.service.SecrecySystemStatisticsService;
import com.cdthgk.common.lang.StringUtils;
import com.cdthgk.platform.dictionary.context.DictionaryContext;
import com.cdthgk.platform.dictionary.domain.DictionaryOption;
import com.cdthgk.platform.dictionary.spi.DictionaryService;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.district.service.DistrictService;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.organ.service.OrganService;

/**
 * <p>
 * 类的说明放这里  2013-8-9 上午11:24:58
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 *
 * @author 宋亚非
 */
public class SecrecySystemStatisticsAction extends BmpAction {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private SecrecySystemStatisticsService secrecySystemService;
	private DistrictService districtService;
	private OrganService organService;


	/**
	 *
	 * <p>
	 * 涉密计算机总数
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-8-9 - 下午4:01:53
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @return
	 */
	public String secrecyComputerCount() {
		//处理单位id信息
		Organ organ = new Organ();
		if(getRequest().getParameter("organId")!=null && !"".equals(getRequest().getParameter("organId"))) {
			String organId = getRequest().getParameter("organId");
			organ = organService.get(organId);
		}else {
			organ = getCurrentUser().getOrgan();
		}

		putToRequest("computerMap", secrecySystemService.getComputerCount(null, organ, Boolean.FALSE));
		putToRequest("threeMap", secrecySystemService.getComputerThreeCount(null, organ, Boolean.FALSE));
		putToRequest("wailianMap", secrecySystemService.getComputerWaiLianCount(null, organ, Boolean.FALSE));
		return SUCCESS;
	}

	public String export_secrecyComputerCount(){
		Organ organ = new Organ();
		if(getRequest().getParameter("organId")!=null && !"".equals(getRequest().getParameter("organId"))) {
			String organId = getRequest().getParameter("organId");
			organ = organService.get(organId);
		}else {
			organ = getCurrentUser().getOrgan();
		}

		//导出设置
  		Map<String, Object> params = new HashMap<String, Object>();
  		//被导出的数据
  		params.put("computerMap", secrecySystemService.getComputerCount(null, organ, Boolean.FALSE));
  		params.put("threeMap", secrecySystemService.getComputerThreeCount(null, organ, Boolean.FALSE));
  		params.put("wailianMap", secrecySystemService.getComputerWaiLianCount(null, organ, Boolean.FALSE));
  		//时间格式化器
  		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
  		params.put("dateFormat", dateFormat);
  		//数据字典工具
  		DictionaryService dictionary = DictionaryContext.getInstance().getDictionaryService();
  		params.put("dictionary", dictionary);
  		//数据类型转换
  		params.put("Integer", Integer.class);
  		ObjectNumberFomart onf = new ObjectNumberFomart();
		params.put("onf", onf);

  		setResultData(params);
  		return SUCCESS;
	}

	/**行政区划       涉密计算机总数
	 * 行政区划的统计
	 * @return
	 */
	public String secrecyComputerCount_District(){
		//处理行政区划
		String districtCode = this.getRequest().getParameter("districtCode");//行政区划编码
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = districtService.get(districtCode);
		}

		putToRequest("computerMap", secrecySystemService.getComputerCount(district, null, Boolean.TRUE));
		putToRequest("threeMap", secrecySystemService.getComputerThreeCount(district, null, Boolean.TRUE));
		putToRequest("wailianMap", secrecySystemService.getComputerWaiLianCount(district, null, Boolean.TRUE));
		return SUCCESS;
	}

	public String export_secrecyComputerCount_District(){
		String districtCode = this.getRequest().getParameter("districtCode");//行政区划编码
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = districtService.get(districtCode);
		}

		//导出设置
  		Map<String, Object> params = new HashMap<String, Object>();
  		//被导出的数据
  		params.put("computerMap", secrecySystemService.getComputerCount(district, null, Boolean.TRUE));
  		params.put("threeMap", secrecySystemService.getComputerThreeCount(district, null, Boolean.TRUE));
  		params.put("wailianMap", secrecySystemService.getComputerWaiLianCount(district, null, Boolean.TRUE));
  		//时间格式化器
  		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
  		params.put("dateFormat", dateFormat);
  		//数据字典工具
  		DictionaryService dictionary = DictionaryContext.getInstance().getDictionaryService();
  		params.put("dictionary", dictionary);
  		//数据类型转换
  		params.put("Integer", Integer.class);
  		ObjectNumberFomart onf = new ObjectNumberFomart();
		params.put("onf", onf);

  		setResultData(params);
  		return SUCCESS;
	}

	/** 涉密信息系统
	 *  单位明细统计
	 * @return
	 */
	public String secrecyComputerCount_OrganDetail() {
		//处理行政区划
		String districtCode = this.getRequest().getParameter("districtCode");//行政区划编码
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = districtService.get(districtCode);
		}

		List<Map<String,Object>> secrecyComputerList = secrecySystemService.getSecrecyComputer_OrganDetail(district);
		this.putToRequest("secrecyComputerList", secrecyComputerList);
		return SUCCESS;
	}

	/************************************************************************** 移动存储介质 ********************************************************************/
	/**本单位
	 * 本单位 移动存储介质统计
	 * @return
	 */
	public String secrecyMediaCount_CurrentOrgan() {
		//处理单位id信息
		Organ organ = new Organ();
		if(getRequest().getParameter("organId")!=null && !"".equals(getRequest().getParameter("organId"))) {
			String organId = getRequest().getParameter("organId");
			organ.setOrganId(organId);
		}else {
			organ = getCurrentUser().getOrgan();
		}

		List<Map<String,Object>> mobileStorageMediaList = secrecySystemService.getMobileStorageMedia_CurrentOrgan(organ);
		this.putToRequest("mobileStorageMediaList", mobileStorageMediaList);
		return SUCCESS;
	}

	/**行政区划
	 * 行政区划 移动存储介质统计
	 * @return
	 */
	public String secrecyMediaCount_District() {
		//处理行政区划
		String districtCode = this.getRequest().getParameter("districtCode");//行政区划编码
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = districtService.get(districtCode);
		}

		List<Map<String,Object>> mobileStorageMediaList = secrecySystemService.getMobileStorageMedia_District(district);
		this.putToRequest("mobileStorageMediaList", mobileStorageMediaList);
		return SUCCESS;
	}

	/**直辖单位
	 * 直辖单位移动存储介质统计
	 * @return
	 */
	public String secrecyMediaCount_Layer() {
		//处理行政区划
		String districtCode = this.getRequest().getParameter("districtCode");//行政区划编码
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = districtService.get(districtCode);
		}

		List<Map<String,Object>> mobileStorageMediaList = secrecySystemService.getMobileStorageMedia_Layer(district);
		this.putToRequest("mobileStorageMediaList", mobileStorageMediaList);
		return SUCCESS;
	}

	/**本单位    导出
	 * 本单位 移动存储介质 导出
	 * @return
	 */
	public String export_MobileStorageMediaCurrentOrgan() {
		//处理单位id信息
		Organ organ = new Organ();
		if(getRequest().getParameter("organId")!=null && !"".equals(getRequest().getParameter("organId"))) {
			String organId = getRequest().getParameter("organId");
			organ.setOrganId(organId);
		}else {
			organ = getCurrentUser().getOrgan();
		}
		List<Map<String,Object>> mobileStorageMediaList = secrecySystemService.getMobileStorageMedia_CurrentOrgan(organ);

		//导出设置
  		Map<String, Object> params = new HashMap<String, Object>();
  		//被导出的数据
  		params.put("mobileStorageMediaList", mobileStorageMediaList);
  		//时间格式化器
  		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
  		params.put("dateFormat", dateFormat);
  		//数据字典工具
  		DictionaryService dictionary = DictionaryContext.getInstance().getDictionaryService();
  		params.put("dictionary", dictionary);
  		//数据类型转换
  		params.put("Integer", Integer.class);

  		setResultData(params);
		return SUCCESS;
	}

	/**行政区划    导出
	 * 行政区划 移动存储介质 导出
	 * @return
	 */
	public String export_MobileStorageMediaDistrict() {
		//处理行政区划
		String districtCode = this.getRequest().getParameter("districtCode");//行政区划编码
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = districtService.get(districtCode);
		}

		List<Map<String,Object>> mobileStorageMediaList = secrecySystemService.getMobileStorageMedia_District(district);

		//导出设置
  		Map<String, Object> params = new HashMap<String, Object>();
  		//被导出的数据
  		params.put("mobileStorageMediaList", mobileStorageMediaList);
  		//时间格式化器
  		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
  		params.put("dateFormat", dateFormat);
  		//数据字典工具
  		DictionaryService dictionary = DictionaryContext.getInstance().getDictionaryService();
  		params.put("dictionary", dictionary);
  		//数据类型转换
  		params.put("Integer", Integer.class);

  		setResultData(params);
		return SUCCESS;
	}

	/**直辖单位  导出
	 * 直辖单位移动存储介质 导出
	 * @return
	 */
	public String export_MobileStorageMediaLayer() {
		//处理行政区划
		String districtCode = this.getRequest().getParameter("districtCode");//行政区划编码
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = districtService.get(districtCode);
		}

		List<Map<String,Object>> mobileStorageMediaList = secrecySystemService.getMobileStorageMedia_Layer(district);

		//导出设置
  		Map<String, Object> params = new HashMap<String, Object>();
  		//被导出的数据
  		params.put("mobileStorageMediaList", mobileStorageMediaList);
  		//时间格式化器
  		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
  		params.put("dateFormat", dateFormat);
  		//数据字典工具
  		DictionaryService dictionary = DictionaryContext.getInstance().getDictionaryService();
  		params.put("dictionary", dictionary);
  		//数据类型转换
  		params.put("Integer", Integer.class);

  		setResultData(params);
		return SUCCESS;
	}

	/************************************************************************** 移动存储介质 ********************************************************************/


	/************************************************************************** 其他涉密设备********************************************************************/
	/**本单位
	 * 本单位 其他涉密设备统计
	 * @return
	 */
	public String secrecyOthersCount_CurrentOrgan() {
		//处理单位id信息
		Organ organ = new Organ();
		if(getRequest().getParameter("organId")!=null && !"".equals(getRequest().getParameter("organId"))) {
			String organId = getRequest().getParameter("organId");
			organ.setOrganId(organId);
		}else {
			organ = getCurrentUser().getOrgan();
		}

		List<Map<String,Object>> othersList = secrecySystemService.getOthers_CurrentOrgan(organ);
		this.putToRequest("othersList", othersList);
		return SUCCESS;
	}

	/**行政区划
	 * 行政区划 其他涉密设备统计
	 * @return
	 */
	public String secrecyOthersCount_District() {
		//处理行政区划
		String districtCode = this.getRequest().getParameter("districtCode");//行政区划编码
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = districtService.get(districtCode);
		}

		List<Map<String,Object>> othersList = secrecySystemService.getOthers_District(district);
		this.putToRequest("othersList", othersList);
		return SUCCESS;
	}

	/**直辖单位
	 * 直辖单位  其他涉密设备统计
	 * @return
	 */
	public String secrecyOthersCount_Layer() {
		//处理行政区划
		String districtCode = this.getRequest().getParameter("districtCode");//行政区划编码
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = districtService.get(districtCode);
		}

		List<Map<String,Object>> othersList = secrecySystemService.getOthers_Layer(district);
		this.putToRequest("othersList", othersList);
		return SUCCESS;
	}

	/**本单位         导出
	 * 本单位 其他涉密设备   导出
	 * @return
	 */
	public String export_OthersCurrentOrgan() {
		//处理单位id信息
		Organ organ = new Organ();
		if(getRequest().getParameter("organId")!=null && !"".equals(getRequest().getParameter("organId"))) {
			String organId = getRequest().getParameter("organId");
			organ.setOrganId(organId);
		}else {
			organ = getCurrentUser().getOrgan();
		}
		List<Map<String,Object>> othersList = secrecySystemService.getOthers_CurrentOrgan(organ);

		//导出设置
  		Map<String, Object> params = new HashMap<String, Object>();
  		//被导出的数据
  		params.put("othersList", othersList);
  		//时间格式化器
  		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
  		params.put("dateFormat", dateFormat);
  		//数据字典工具
  		DictionaryService dictionary = DictionaryContext.getInstance().getDictionaryService();
  		params.put("dictionary", dictionary);
  		//数据类型转换
  		params.put("Integer", Integer.class);

  		setResultData(params);
		return SUCCESS;
	}

	/**行政区划        导出
	 * 行政区划 其他涉密设备   导出
	 * @return
	 */
	public String export_OthersDistrict() {
		//处理行政区划
		String districtCode = this.getRequest().getParameter("districtCode");//行政区划编码
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = districtService.get(districtCode);
		}

		List<Map<String,Object>> othersList = secrecySystemService.getOthers_District(district);

		//导出设置
  		Map<String, Object> params = new HashMap<String, Object>();
  		//被导出的数据
  		params.put("othersList", othersList);
  		//时间格式化器
  		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
  		params.put("dateFormat", dateFormat);
  		//数据字典工具
  		DictionaryService dictionary = DictionaryContext.getInstance().getDictionaryService();
  		params.put("dictionary", dictionary);
  		//数据类型转换
  		params.put("Integer", Integer.class);

  		setResultData(params);
		return SUCCESS;
	}

	/**直辖单位        导出
	 * 直辖单位  其他涉密设备   导出
	 * @return
	 */
	public String export_OthersLayer() {
		//处理行政区划
		String districtCode = this.getRequest().getParameter("districtCode");//行政区划编码
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = districtService.get(districtCode);
		}

		List<Map<String,Object>> othersList = secrecySystemService.getOthers_Layer(district);

		//导出设置
  		Map<String, Object> params = new HashMap<String, Object>();
  		//被导出的数据
  		params.put("othersList", othersList);
  		//时间格式化器
  		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
  		params.put("dateFormat", dateFormat);
  		//数据字典工具
  		DictionaryService dictionary = DictionaryContext.getInstance().getDictionaryService();
  		params.put("dictionary", dictionary);
  		//数据类型转换
  		params.put("Integer", Integer.class);

  		setResultData(params);
		return SUCCESS;
	}
	/************************************************************************** 其他涉密设备********************************************************************/


	/**
	 *
	 * <p>
	 * 方法名：collationOfDataDetail 公共处理方法
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-12 下午3:40:42
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@param resultdata
	 *@param options
	 *@param fistCellName
	 *@return
	 */
	public List<LinkedList<String>>  collationOfDataDetail( List<Map<String, Map<Integer, Integer>>> resultdata, List<DictionaryOption> options, String fistCellName ){
		List<DictionaryOption> secrecyLevelOptions = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "secrecy_level_person");
		List<LinkedList<String>> returnResult = new ArrayList<LinkedList<String>>();

		LinkedList<String> headerRow = new LinkedList<String>();
		headerRow.add(fistCellName);
		for(DictionaryOption sld : secrecyLevelOptions){
			headerRow.add(sld.getOptionText());
		}
		headerRow.add("合计");
		returnResult.add(headerRow);
		for( Map<String, Map<Integer, Integer>> layer1 : resultdata ){
			for( String ageRangeName : layer1.keySet() ){
				LinkedList<String> aRow = new LinkedList<String>();
				aRow.add(ageRangeName);
				Map<Integer, Integer> layer1ContentMap = layer1.get(ageRangeName);
				Integer rowTotal = 0;
				//计算合计
				if( layer1ContentMap !=null ){
					for( Integer secrecyLevelValue : layer1ContentMap.keySet() ){
						for(DictionaryOption sld : secrecyLevelOptions){
							if( sld.getOptionValue() == secrecyLevelValue ){
								rowTotal = rowTotal + layer1ContentMap.get(secrecyLevelValue);
							}
						}
					}
				}else{
					rowTotal = 0;
				}
				for(DictionaryOption sld : secrecyLevelOptions){
					int dividend = 0;
					if( layer1ContentMap!=null ){
						for( Integer secrecyLevelValue : layer1ContentMap.keySet() ){
							if( sld.getOptionValue() == secrecyLevelValue ){
								dividend = layer1ContentMap.get(secrecyLevelValue);
							}
						}
					}
					if( rowTotal!=null && rowTotal!=0 && dividend != 0 ){
						aRow.add(dividend + "(" + Math.round(dividend*10000.00/rowTotal)/100.00 +"%)");
					}else{
						aRow.add(0 + "(0.00%)");
					}
				}
				aRow.add(rowTotal+"");
				returnResult.add(aRow);
			}
		}
		return returnResult;
	}

	/**
	 *
	 * <p>
	 * 方法名：secrecyNetwork_CurrentOrgan 本单位  涉密网络  统计
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-12 下午2:01:45
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@return
	 */
	public String secrecyNetwork_CurrentOrgan(){
		Organ organ = this.getCurrentUser().getOrgan();
		List<DictionaryOption> optionsList = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "network_type");
		List<Map<String, Map<Integer,Integer>>> resultList = this.secrecySystemService.getNetwork_CurrentOrgan(optionsList, organ, SECRECY_STATUS_NOW);
		//处理数据传递页面
		List<LinkedList<String>> networkCountList = this.collationOfDataDetail(resultList, optionsList, "网络类型");
		this.putToRequest("networkCountList",  networkCountList);
		return SUCCESS;
	}

	/**
	 *
	 * <p>
	 * 方法名：secrecyNetwork_CurrentOrganExport 导出
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-12 下午5:24:11
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@return
	 */
	public String secrecyNetwork_CurrentOrganExport(){
		Organ organ = this.getCurrentUser().getOrgan();
		List<DictionaryOption> optionsList = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "network_type");
		List<Map<String, Map<Integer,Integer>>> resultList = this.secrecySystemService.getNetwork_CurrentOrgan(optionsList, organ, SECRECY_STATUS_NOW);
		//处理数据传递
		List<LinkedList<String>> networkCountList = this.collationOfDataDetail(resultList, optionsList, "网络类型");
		//导出
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("networkCountList", networkCountList);
		params.put("title", "涉密网络类型统计");
		setResultData(params);
		return "success";
	}

	/**
	 *
	 * <p>
	 * 方法名：secrecyNetwork_District 行政区划 涉密网络  统计
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-12 下午2:02:18
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@return
	 */
	public String secrecyNetwork_District(){
		//处理行政区划
		String districtCode = this.getRequest().getParameter("districtCode");//行政区划编码
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = districtService.get(districtCode);
		}
		List<DictionaryOption> optionsList = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "network_type");
		List<Map<String, Map<Integer,Integer>>> resultList = this.secrecySystemService.getNetwork_Layer(optionsList, district, SECRECY_STATUS_NOW);
		//处理数据传递页面
		List<LinkedList<String>> networkCountList = this.collationOfDataDetail(resultList, optionsList, "网络类型");
		this.putToRequest("networkCountList",  networkCountList);
		return SUCCESS;
	}

	/**
	 *
	 * <p>
	 * 方法名：secrecyNetwork_DistrictExport
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-12 下午5:30:05
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@return
	 */
	public String secrecyNetwork_DistrictExport(){
		//处理行政区划
		String districtCode = this.getRequest().getParameter("districtCode");//行政区划编码
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = districtService.get(districtCode);
		}
		List<DictionaryOption> optionsList = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "network_type");
		List<Map<String, Map<Integer,Integer>>> resultList = this.secrecySystemService.getNetwork_Layer(optionsList, district, SECRECY_STATUS_NOW);
		//处理数据传递页面
		List<LinkedList<String>> networkCountList = this.collationOfDataDetail(resultList, optionsList, "网络类型");
		//导出
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("networkCountList", networkCountList);
		params.put("title", "涉密网络类型统计");
		setResultData(params);
		return "success";
	}

	/**
	 *
	 * <p>
	 * 方法名：secrecyNetwork_Layer 直辖单位 涉密网络 统计
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-12 下午2:02:55
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@return
	 */
	public String secrecyNetwork_Layer(){
		//处理行政区划
		String districtCode = this.getRequest().getParameter("districtCode");//行政区划编码
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = districtService.get(districtCode);
		}
		List<DictionaryOption> optionsList = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "network_type");
		List<Map<String, Map<Integer,Integer>>> resultList = this.secrecySystemService.getNetwork_DistrictOrgan(optionsList, district, SECRECY_STATUS_NOW);
		//处理数据传递页面
		List<LinkedList<String>> networkCountList = this.collationOfDataDetail(resultList, optionsList, "网络类型");
		this.putToRequest("networkCountList",  networkCountList);
		return SUCCESS;
	}

	/**
	 *
	 * <p>
	 * 方法名：secrecyNetwork_LayerExport
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-12 下午5:30:23
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 *@return
	 */
	public String secrecyNetwork_LayerExport(){
		//处理行政区划
		String districtCode = this.getRequest().getParameter("districtCode");//行政区划编码
		District district = null;
		if (StringUtils.isEmpty(districtCode)) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = districtService.get(districtCode);
		}
		List<DictionaryOption> optionsList = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "network_type");
		List<Map<String, Map<Integer,Integer>>> resultList = this.secrecySystemService.getNetwork_DistrictOrgan(optionsList, district, SECRECY_STATUS_NOW);
		//处理数据传递页面
		List<LinkedList<String>> networkCountList = this.collationOfDataDetail(resultList, optionsList, "网络类型");
		//导出
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("networkCountList", networkCountList);
		params.put("title", "涉密网络类型统计");
		setResultData(params);
		return "success";
	}




	/**
	 * @param secrecySystemService 设置secrecySystemService
	 */
	public void setSecrecySystemService(
			SecrecySystemStatisticsService secrecySystemService) {
		this.secrecySystemService = secrecySystemService;
	}

	public DistrictService getDistrictService() {
		return districtService;
	}

	public void setDistrictService(DistrictService districtService) {
		this.districtService = districtService;
	}

	public OrganService getOrganService() {
		return organService;
	}

	public void setOrganService(OrganService organService) {
		this.organService = organService;
	}

}
