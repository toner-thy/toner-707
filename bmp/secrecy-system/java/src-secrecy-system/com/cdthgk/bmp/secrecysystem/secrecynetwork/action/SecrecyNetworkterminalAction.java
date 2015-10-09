package com.cdthgk.bmp.secrecysystem.secrecynetwork.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cdthgk.bmp.core.action.BmpAction;
import com.cdthgk.bmp.secrecycountryitem.qo.ZongHeTongJiStatDto;
import com.cdthgk.bmp.secrecysystem.secrecycomputer.service.SecrecyComputerService;
import com.cdthgk.bmp.secrecysystem.secrecycomputer.vo.SecrecyComputer;
import com.cdthgk.bmp.secrecysystem.secrecynetwork.service.SecrecyNetworkService;
import com.cdthgk.bmp.secrecysystem.secrecynetwork.service.SecrecyNetworkterminalService;
import com.cdthgk.bmp.secrecysystem.secrecynetwork.vo.SecrecyNetwork;
import com.cdthgk.bmp.secrecysystem.secrecynetwork.vo.SecrecyNetworkterminal;
import com.cdthgk.common.lang.StringUtils;
import com.cdthgk.platform.dictionary.context.DictionaryContext;
import com.cdthgk.platform.dictionary.domain.DictionaryOption;
import com.cdthgk.platform.dictionary.spi.DictionaryService;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.district.service.DistrictService;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

public class SecrecyNetworkterminalAction extends BmpAction {

	private static final long serialVersionUID = 1L;

	private SecrecyNetworkterminal secrecyNetworkterminal;
	private SecrecyNetwork secrecyNetwork;
	private SecrecyComputer secrecyComputer;
	private SecrecyComputerService secrecyComputerService;
	private SecrecyNetworkService secrecyNetworkService;
	private SecrecyNetworkterminalService secrecyNetworkterminalService;
	private List<SecrecyNetworkterminal> secrecyNetworkterminalArray;
	private DistrictService districtService;
	private Boolean needReload = false;
	private District district;
	private String fromQuery;
	private String checkLower;

	/**
	 *
	 * <p>
	 * 涉密网络终端查询
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-8-22 - 下午3:14:16
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @return
	 */
	public String mainQuery() {
		district = getCurrentUser().getOrgan().getDistrict();
		return SUCCESS;
	}

	/**
	 *
	 * <p>
	 * 保存涉密网络终端
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-8-7 - 下午1:59:51
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @return
	 */
	public String save() {
		secrecyComputer = secrecyComputerService.get(secrecyComputer.getSecrecycomputerId());
		secrecyNetwork = secrecyNetworkService.get(secrecyNetwork.getSecrecyNetworkId());
		secrecyNetworkterminal.setSecrecyComputer(secrecyComputer);
		secrecyNetworkterminal.setSecrecyNetwork(secrecyNetwork);

		secrecyNetworkterminal.setDutyPerson(secrecyComputer.getDutyPerson());
		secrecyNetworkterminal.setSecrecyLevel(secrecyComputer.getSecrecyLevel());
		secrecyNetworkterminal.setComputerType(secrecyComputer.getComputerType());
		secrecyNetworkterminal.setDutyPerson(secrecyComputer.getDutyPerson());
		secrecyNetworkterminal.setIsbelongKeydepartment(secrecyComputer.getIsbelongKeydepartment());
		secrecyNetworkterminal.setIsFanghu(secrecyComputer.getIsFanghu());
		secrecyNetworkterminal.setIsWailian(secrecyComputer.getIsWailian());
		secrecyNetworkterminal.setDepartment(secrecyComputer.getDepartment());

		secrecyNetworkterminal.setCreatePerson(getCurrentUser());
		secrecyNetworkterminal.setCreateTime(new Date(System.currentTimeMillis()));
		secrecyNetworkterminal.setDataState(1);
		secrecyNetworkterminal.setCreateOrgan(getCurrentUser().getOrgan());
		secrecyNetworkterminal.setCreatePerson(getCurrentUser());
		secrecyNetworkterminal.setCreateTime(new Date(System.currentTimeMillis()));
		secrecyNetworkterminal.setSecrecyStatus(BmpAction.SECRECY_STATUS_NOW);
		this.secrecyNetworkterminalService.save(secrecyNetworkterminal);

		secrecyComputer.setIsNetTerminal(1);
		this.secrecyComputerService.update(secrecyComputer);
		this.addActionMessage(getMessageConstant().getSaveSuccess());
		this.setNeedReload(true);
		return redirectActionResult(ADD);
	}

	/**
	 *
	 * <p>
	 * 方法名：remove 移除终端
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-8 下午2:06:56
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
	public String remove(){
		secrecyComputer = secrecyComputerService.get(secrecyComputer.getSecrecycomputerId());
		Set<SecrecyNetworkterminal> netTerminalSet = secrecyComputer.getSecrecyNetworkterminals();
		SecrecyNetworkterminal secrecyNetworkterminalDB = null;
		for( SecrecyNetworkterminal snt : netTerminalSet ){
			if( snt.getDataState()!=null && snt.getDataState() == 1 ){
				secrecyNetworkterminalDB =snt;
				break;
			}
		}
		secrecyNetworkterminalDB.setRemoveNetworkDate(secrecyNetworkterminal.getRemoveNetworkDate());
		secrecyNetworkterminalDB.setRemoveNetworkReason(secrecyNetworkterminal.getRemoveNetworkReason());
		secrecyNetworkterminalDB.setModifyOrgan(getCurrentUser().getOrgan());
		secrecyNetworkterminalDB.setModifyPerson(getCurrentUser());
		secrecyNetworkterminalDB.setModifyTime(new Date(System.currentTimeMillis()));
		secrecyNetworkterminalDB.setDataState(0);
		this.secrecyNetworkterminalService.update(secrecyNetworkterminalDB);

		secrecyComputer.setIsNetTerminal(0);
		this.secrecyComputerService.update(secrecyComputer);

		this.addActionMessage(getMessageConstant().getSaveSuccess());
		this.setNeedReload(true);
		return redirectActionResult("remove");
	}

	/**
	 *
	 * <p>
	 * 方法名：list 列表
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-7 下午3:38:07
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
	public String list() {
		if (district == null) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = secrecyNetworkService.get(district.getDistrictCode(), District.class);
		}
		boolean flag = true;
		// 判断查询列表过来的1查看保密局，0不是查询列表过来的查看本单位
		if(StringUtils.isEmpty(fromQuery) || "0".equals(fromQuery)){
			flag = false;
		}
		PageSortModel<SecrecyNetworkterminal> psm = new PageSortModel<SecrecyNetworkterminal>(getRequest(), "secrecyNetworkterminalTable");
		Organ organ = getCurrentUser().getOrgan();
		User currentUserTmp = this.getCurrentUser();
		//综合统计使用
		String secrecyType = this.getRequest().getParameter("secrecyType");
		String organId = this.getRequest().getParameter("organId");
		if( secrecyType!=null ){
			if( secrecyNetworkterminal==null ){
				secrecyNetworkterminal = new SecrecyNetworkterminal();
				secrecyNetworkterminal.setSecrecyLevel(Integer.parseInt(secrecyType));
				this.putToRequest("secrecyType", secrecyType);
			}
		}
		if( organId!=null ){
			organ = this.secrecyNetworkterminalService.get(organId, Organ.class);
			currentUserTmp = new User();
			currentUserTmp.setOrgan(organ);
			this.putToRequest("organId", organId);
			this.putToRequest("organ", organ);
                        this.putToRequest("zhtj", "1");
		}

		List<SecrecyNetworkterminal> secrecyNetworktreminalList = this.secrecyNetworkterminalService.findListPage(psm, secrecyNetworkterminal, organ
				, district, flag, currentUserTmp, checkLower);

		this.putToRequest("secrecyNetworkterminalList", secrecyNetworktreminalList);
		return SUCCESS;
	}

	/**
	 *
	 * <p>
	 * 方法名：export 导出
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-8 上午9:38:49
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
	public String export(){
		if (district == null) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = secrecyNetworkService.get(district.getDistrictCode(), District.class);
		}
		boolean flag = true;
		// 判断查询列表过来的1查看保密局，0不是查询列表过来的查看本单位
		if(StringUtils.isEmpty(fromQuery) || "0".equals(fromQuery)){
			flag = false;
		}
		//查询登录用户与创建单位相同的信息
		Organ organ = getCurrentUser().getOrgan();
		List<SecrecyNetworkterminal> secrecyNetworktreminalList = this.secrecyNetworkterminalService.findListPage(null, secrecyNetworkterminal, organ
				, district, flag, getCurrentUser(), checkLower);

		Map<String, Object> params = new HashMap<String, Object>();
		//被导出的数据
		params.put("dataList", secrecyNetworktreminalList);
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

	/**
	 *
	 * <p>
	 * 方法名：saveTerminals 保存涉密网络的接入终端
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-8 下午2:06:42
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
	public String saveTerminals(){
		secrecyNetwork = this.secrecyNetworkService.get(secrecyNetwork.getSecrecyNetworkId());
		for( SecrecyNetworkterminal snt : secrecyNetworkterminalArray ){
			SecrecyComputer sc = null;
			//this.secrecyComputerService.get(snt.getSecrecyComputer().getSecrecycomputerId());
			//处理涉密计算机信息
			sc = this.secrecyComputerService.addSecrecyComputer(snt.getSecrecyComputer(),getCurrentUser());

			snt.setSecrecyNetwork(secrecyNetwork);

			if( sc != null ){
				snt.setSecrecyComputer(sc);
				snt.setDutyPerson(sc.getDutyPerson());
				snt.setSecrecyLevel(sc.getSecrecyLevel());
				snt.setComputerType(sc.getComputerType());
				snt.setDutyPerson(sc.getDutyPerson());
				snt.setIsbelongKeydepartment(sc.getIsbelongKeydepartment());
				snt.setIsFanghu(sc.getIsFanghu());
				snt.setIsWailian(sc.getIsWailian());
				snt.setDepartment(sc.getDepartment());
			}

			snt.setSecrecyNetwork(secrecyNetwork);
			snt.setCreatePerson(getCurrentUser());
			snt.setCreateTime(new Date(System.currentTimeMillis()));
			snt.setDataState(1);
			snt.setCreateOrgan(getCurrentUser().getOrgan());
			snt.setCreatePerson(getCurrentUser());
			snt.setCreateTime(new Date(System.currentTimeMillis()));
			snt.setSecrecyStatus(BmpAction.SECRECY_STATUS_NOW);

			this.secrecyNetworkterminalService.save(snt);

			sc.setIsNetTerminal(1);
			this.secrecyComputerService.update(sc);
		}
		this.addActionMessage(getMessageConstant().getSaveSuccess());
		this.setNeedReload(true);
		return redirectActionResult(ADD);
	}

	/**
	 *
	 * <p>
	 * 方法名：removeTerminals 保存移除涉密网络的终端
	 * </p>
	 * <p>
	 * 宋亚非 2013-8-8 下午2:08:19
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
	public String removeTerminals(){
		SecrecyNetworkterminal secrecyNetworkterminalDB = this.getSecrecyNetworkterminalService().get(secrecyNetworkterminal.getSecrecynetworkterminalId());
		SecrecyComputer sc = secrecyNetworkterminalDB.getSecrecyComputer();

		secrecyNetworkterminalDB.setRemoveNetworkDate(secrecyNetworkterminal.getRemoveNetworkDate());
		secrecyNetworkterminalDB.setRemoveNetworkReason(secrecyNetworkterminal.getRemoveNetworkReason());
		secrecyNetworkterminalDB.setModifyOrgan(getCurrentUser().getOrgan());
		secrecyNetworkterminalDB.setModifyPerson(getCurrentUser());
		secrecyNetworkterminalDB.setModifyTime(new Date(System.currentTimeMillis()));
		secrecyNetworkterminalDB.setDataState(0);
		this.secrecyNetworkterminalService.update(secrecyNetworkterminalDB);
		this.setSecrecyNetworkterminal(secrecyNetworkterminalDB);

		sc.setIsNetTerminal(0);
		this.secrecyComputerService.update(sc);

		this.addActionMessage(getMessageConstant().getSaveSuccess());
		this.setNeedReload(true);
		return redirectActionResult("remove");
	}

	public String indexView(){
		String countType = getRequest().getParameter("countType");
		String returnStr = "";
		List<DictionaryOption> optionList = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "secrecy_level_thing");
		List<DictionaryOption> typeList = DictionaryContext.getInstance().getDictionaryService().getOptionList("bmp", "computer_type");
		Organ currentOrgan = this.getCurrentUser().getOrgan();
		//密级  类型
		Map<Integer,Map<Integer, Long>> countMap = this.secrecyNetworkterminalService.countData(optionList, typeList, currentOrgan, countType );
		putToRequest("countMap", countMap);
		putToRequest("countType", countType);
		putToRequest("optionList", optionList);
		putToRequest("typeList", typeList);
		putToRequest("countType", countType);
		//本单位
		if( countType!=null && "organ".equals(countType) ){
			returnStr = "organView";
		}
		//保密局
		if( countType!=null && "layer".equals(countType) ){
			returnStr = "layerView";
		}
		return returnStr;
	}

	public String countData(){
		String countType = getRequest().getParameter("countType");
		//本单位
		if( countType!=null && "organ".equals(countType) ){
			Organ currentOrgan = this.getCurrentUser().getOrgan();
			PageSortModel<SecrecyNetworkterminal> psm = new PageSortModel<SecrecyNetworkterminal>(getRequest(), "secrecyNetworkterminalTable");
			List<SecrecyNetworkterminal> secrecyNetworkterminalList = this.secrecyNetworkterminalService.findDataList(psm, currentOrgan, countType, secrecyNetworkterminal);
			this.putToRequest("secrecyNetworkterminalList", secrecyNetworkterminalList);
		}
		//保密局
		if( countType!=null && "layer".equals(countType) ){
			Organ currentOrgan = this.getCurrentUser().getOrgan();
			PageSortModel<SecrecyNetworkterminal> psm = new PageSortModel<SecrecyNetworkterminal>(getRequest(), "secrecyNetworkterminalTable");
			List<SecrecyNetworkterminal> secrecyNetworkterminalList = this.secrecyNetworkterminalService.findDataList(psm, currentOrgan, countType, secrecyNetworkterminal);
			this.putToRequest("secrecyNetworkterminalList", secrecyNetworkterminalList);

		}
		putToRequest("countType", countType);
		return SUCCESS;
	}

	public String countSubData(){
		String countType = getRequest().getParameter("countType");
		//本单位
		if( countType!=null && "organ".equals(countType) ){
			Organ currentOrgan = this.getCurrentUser().getOrgan();
			PageSortModel<SecrecyNetworkterminal> psm = new PageSortModel<SecrecyNetworkterminal>(getRequest(), "secrecyNetworkterminalTable");
			List<SecrecyNetworkterminal> secrecyNetworkterminalList = this.secrecyNetworkterminalService.findDataList(psm, currentOrgan, countType, secrecyNetworkterminal);
			this.putToRequest("secrecyNetworkterminalList", secrecyNetworkterminalList);
		}
		//保密局
		if( countType!=null && "layer".equals(countType) ){
			Organ currentOrgan = this.getCurrentUser().getOrgan();
			PageSortModel<SecrecyNetworkterminal> psm = new PageSortModel<SecrecyNetworkterminal>(getRequest(), "secrecyNetworkterminalTable");
			List<SecrecyNetworkterminal> secrecyNetworkterminalList = this.secrecyNetworkterminalService.findDataList(psm, currentOrgan, countType, secrecyNetworkterminal);
			this.putToRequest("secrecyNetworkterminalList", secrecyNetworkterminalList);

		}
		putToRequest("countType", countType);
		return SUCCESS;
	}


	/***************************************综合统计*******************************************/
	/**
	 * 综合统计  通过行政区划    查询涉密人员个数 的明细列表
	 * 包括当前行政区划 和下级行政区划 的涉密人员的明细
	 * @return
	 */
	public String zhtj_query_Detail(){

		district = districtService.get(district.getDistrictCode());

		zhtj_query_method();

		return SUCCESS;
	}

	/**
	 * 综合统计  通过行政区划编码  统计该行政区划涉密人员的统计
	 * 一个单位一排数据，同时这个action提供了通过单位的名称模糊查询的功能。
	 *
	 * @return
	 */
	public String zhtj_queryByDistrict() {
		//这里只会得到  行政区划编码，没有单位的信息
		district = districtService.get(district.getDistrictCode());

		String organName = this.getRequest().getParameter("organ.organName");
		Organ organ = null;
		if(organName!=null && !"".equals(organName)) {
			organ = new Organ();
			organ.setOrganName(organName);
		}

		List<ZongHeTongJiStatDto> secrecyCountryItemStatDtoList = secrecyNetworkterminalService.count_SecrecyNetworkterminal_District(district, true,organ);
		putToRequest("secrecyCountryItemStatDtoList", secrecyCountryItemStatDtoList);
		putToRequest("district", district);
		putToRequest("topDistrict", this.getCurrentUser().getOrgan().getDistrict());
		return SUCCESS;
	}

	/**
	 * 通过密级，行政区划对象或者单位对象，查询涉密人员对应的列表
	 * 密级是必须字段，如果点到合计上面了，这里的单位是空的，所以需要行政区划对象
	 *                如果没有点到合计，那么会使用单位的对象去查询
	 * @return
	 */
	public String zhtj_DetailList() {

		List<SecrecyNetworkterminal> secrecyNetworkterminalList = new ArrayList<SecrecyNetworkterminal>();
		PageSortModel<SecrecyNetworkterminal> psm = new PageSortModel<SecrecyNetworkterminal>(this.getRequest(), "secrecyNetworkterminalList");

		//设置密级
		String secrecy_level = getRequest().getParameter("secrecyLevel");//密级
		if(secrecyNetworkterminal==null) {
			secrecyNetworkterminal = new SecrecyNetworkterminal();
		}
		if(secrecy_level!=null && !"".equals(secrecy_level)) {
			secrecyNetworkterminal.setSecrecyLevel(Integer.parseInt(secrecy_level));
		}

		String organId =  getRequest().getParameter("organId"); //单位id
		if(organId!=null && !"".equals(organId)) {//如果有单位，就用单位对象
			Organ organ = new Organ();
			organ.setOrganId(organId);
			//secrecyPersonList =  secrecyPersonModuleService.queryCountryItemList(psm, organ, secrecyPerson);
			User currentUserTmp = new User();
			currentUserTmp.setOrgan(organ);
			secrecyNetworkterminalList =  secrecyNetworkterminalService.findListPage(psm, secrecyNetworkterminal, organ, null, false, currentUserTmp, null);

		}else {//如果没有单位对象   就需要使用 行政区划对象   也说明点到合计了
			district = districtService.get(district.getDistrictCode());
			//secrecyPersonList =  secrecyPersonModuleService.queryCountryItemList(psm, district, secrecyPerson);
			secrecyNetworkterminalList =  secrecyNetworkterminalService.findListPage(psm, secrecyNetworkterminal, null, district, true, null, null);
		}
		putToRequest("secrecyNetworkterminalList", secrecyNetworkterminalList);
		putToRequest("secrecy_level", secrecy_level);
		return SUCCESS;
	}

	/************************************************************************************************/

	public String zhtj_query(){

		district = districtService.get(this.getCurrentUser().getOrgan().getDistrict().getDistrictCode());

		zhtj_query_method();

		return SUCCESS;
	}

	/**
	 * <p>
	 * 方法名称  zhtj_query_method
	 * </p>
	 * <p>
	 * 宋亚非 2013-9-6 下午3:55:15
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @since 1.0
	 * @version 1.0
	 */
	private void zhtj_query_method() {
		//处理行政区划
		List<District> districtList = new ArrayList<District>();
		List<District> childrenDistrictList =  new ArrayList<District>(district.getChildren());
		districtList.add(district);

		//查询  当前行政区划的 明细
		List<ZongHeTongJiStatDto> secrecyCountryItemStatDtoList = secrecyNetworkterminalService.count_SecrecyNetworkterminal_District(districtList, false);
		//查询  子行政区划的明细
		List<ZongHeTongJiStatDto> childrenStatDtoList = secrecyNetworkterminalService.count_SecrecyNetworkterminal_District(childrenDistrictList, true);

		putToRequest("secrecyCountryItemStatDtoList", secrecyCountryItemStatDtoList);
		putToRequest("childrenStatDtoList", childrenStatDtoList);
		putToRequest("districtCode", district.getDistrictCode());
		putToRequest("district", district);
		putToRequest("topDistrict", this.getCurrentUser().getOrgan().getDistrict());
	}


	/************************************综合统计导出************************************************************/
	/**
	 * 行政区划导出
	 * @return
	 */
	public String stat_exportDistrict() {
		district = districtService.get(district.getDistrictCode());

		//处理行政区划
		List<District> districtList = new ArrayList<District>();
		districtList.add(district);

		//查询  当前行政区划的 明细
		List<ZongHeTongJiStatDto> dataStatDtoList = secrecyNetworkterminalService.count_SecrecyNetworkterminal_District(districtList, false);

		//导出设置
  		Map<String, Object> params = new HashMap<String, Object>();
  		//被导出的数据
  		params.put("dataStatDtoList", dataStatDtoList);
		params.put("district", district);
		params.put("title", "涉密网络终端");
		this.putToRequest("district", district);
  		setResultData(params);
		return SUCCESS;
	}

	/**
	 * 行政区内导出
	 * @return
	 */
	public String stat_exportLayer(){
		district = districtService.get(district.getDistrictCode());

		//处理行政区划
		List<District> childrenDistrictList =  new ArrayList<District>(district.getChildren());

		//查询  子行政区划的明细
		List<ZongHeTongJiStatDto> childrenStatDtoList = secrecyNetworkterminalService.count_SecrecyNetworkterminal_District(childrenDistrictList, true);

		//导出设置
  		Map<String, Object> params = new HashMap<String, Object>();
  		//被导出的数据
  		params.put("childrenStatDtoList", childrenStatDtoList);
		params.put("district", district);
		params.put("title", "涉密网络终端");
		this.putToRequest("district", district);
  		setResultData(params);
		return SUCCESS;
	}

	/*********************************************************************************/

	/********************************************getter && setter****************************************************/
	/**
	 * @return the secrecyNetworkterminal
	 */
	public SecrecyNetworkterminal getSecrecyNetworkterminal() {
		return secrecyNetworkterminal;
	}

	/**
	 * @param secrecyNetworkterminal the secrecyNetworkterminal to set
	 */
	public void setSecrecyNetworkterminal(
			SecrecyNetworkterminal secrecyNetworkterminal) {
		this.secrecyNetworkterminal = secrecyNetworkterminal;
	}

	/**
	 * @return the secrecyNetwork
	 */
	public SecrecyNetwork getSecrecyNetwork() {
		return secrecyNetwork;
	}

	/**
	 * @param secrecyNetwork the secrecyNetwork to set
	 */
	public void setSecrecyNetwork(SecrecyNetwork secrecyNetwork) {
		this.secrecyNetwork = secrecyNetwork;
	}

	/**
	 * @return the secrecyComputer
	 */
	public SecrecyComputer getSecrecyComputer() {
		return secrecyComputer;
	}

	/**
	 * @param secrecyComputer the secrecyComputer to set
	 */
	public void setSecrecyComputer(SecrecyComputer secrecyComputer) {
		this.secrecyComputer = secrecyComputer;
	}

	/**
	 * @return the secrecyComputerService
	 */
	public SecrecyComputerService getSecrecyComputerService() {
		return secrecyComputerService;
	}

	/**
	 * @param secrecyComputerService the secrecyComputerService to set
	 */
	public void setSecrecyComputerService(
			SecrecyComputerService secrecyComputerService) {
		this.secrecyComputerService = secrecyComputerService;
	}

	/**
	 * @return the secrecyNetworkService
	 */
	public SecrecyNetworkService getSecrecyNetworkService() {
		return secrecyNetworkService;
	}

	/**
	 * @param secrecyNetworkService the secrecyNetworkService to set
	 */
	public void setSecrecyNetworkService(SecrecyNetworkService secrecyNetworkService) {
		this.secrecyNetworkService = secrecyNetworkService;
	}

	/**
	 * @return the secrecyNetworkterminalService
	 */
	public SecrecyNetworkterminalService getSecrecyNetworkterminalService() {
		return secrecyNetworkterminalService;
	}

	/**
	 * @param secrecyNetworkterminalService the secrecyNetworkterminalService to set
	 */
	public void setSecrecyNetworkterminalService(
			SecrecyNetworkterminalService secrecyNetworkterminalService) {
		this.secrecyNetworkterminalService = secrecyNetworkterminalService;
	}

	/**
	 * @return the needReload
	 */
	public Boolean getNeedReload() {
		return needReload;
	}

	/**
	 * @param needReload the needReload to set
	 */
	public void setNeedReload(Boolean needReload) {
		this.needReload = needReload;
	}

	/**
	 * @return the secrecyNetworkterminalArray
	 */
	public List<SecrecyNetworkterminal> getSecrecyNetworkterminalArray() {
		return secrecyNetworkterminalArray;
	}

	/**
	 * @param secrecyNetworkterminalArray the secrecyNetworkterminalArray to set
	 */
	public void setSecrecyNetworkterminalArray(
			List<SecrecyNetworkterminal> secrecyNetworkterminalArray) {
		this.secrecyNetworkterminalArray = secrecyNetworkterminalArray;
	}

	/**
	 * @return 返回district
	 */
	public District getDistrict() {
		return district;
	}

	/**
	 * @param district 设置district
	 */
	public void setDistrict(District district) {
		this.district = district;
	}

	/**
	 * @return 返回fromQuery
	 */
	public String getFromQuery() {
		return fromQuery;
	}

	/**
	 * @param fromQuery 设置fromQuery
	 */
	public void setFromQuery(String fromQuery) {
		this.fromQuery = fromQuery;
	}

	/**
	 * @return 返回checkLower
	 */
	public String getCheckLower() {
		return checkLower;
	}

	/**
	 * @param checkLower 设置checkLower
	 */
	public void setCheckLower(String checkLower) {
		this.checkLower = checkLower;
	}

	/**
	 * @return the districtService
	 */
	public DistrictService getDistrictService() {
		return districtService;
	}

	/**
	 * @param districtService the districtService to set
	 */
	public void setDistrictService(DistrictService districtService) {
		this.districtService = districtService;
	}

}