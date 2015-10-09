package com.cdthgk.bmp.equipment.equipment.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.cdthgk.bmp.equipment.equipment.service.EquipmentService;
import com.cdthgk.bmp.equipment.equipment.vo.EquipmentNumber;
import com.cdthgk.bmp.equipment.equipmenttype.service.EquipmentTypeService;
import com.cdthgk.bmp.equipment.equipmenttype.vo.EquipmentType;
import com.cdthgk.platform.base.action.PlatformAction;
import com.cdthgk.platform.base.transmitor.vo.TransmitMessage;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.init.service.InitParamsService;
import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

/**
 * @description 技术设备配备Action.
 * @author 陈文聪 2010 3 4 12:34:56
 * @modify 陈文聪 2010 8 19 02:28:28 修改注释格式
 */
@SuppressWarnings("unchecked")
public class EquipmentAction extends PlatformAction {

	// Field
	private static final long serialVersionUID = 1L;
	private EquipmentNumber equipment;
	private EquipmentType equipmentType;
	private EquipmentService equipmentService;
	private EquipmentTypeService equipmentTypeService;
	/*private OrganService organService;*/
	Map<String, String> numberMap = new HashMap<String, String>();
	private String parentId;
	private String organName;
	//private ReportDto reportDto;
	private List equipmentReportOrganList;
	//private TransmitWsClientTemplate equipmentTransmitWsClient;
	private InitParamsService initParamsService;
	//private IDistrictService districtService;
	private District district;
	private String status = "2";
	private String organIds;
	/**
	 * @description 查看各单位的技术设备入口
	 * @author 陈文聪 2010 3 4 12:34:56
	 * @modify 陈文聪 2010 8 19 02:29:15 修改注释格式.
	 */
	public String main() {
		this.putToRequest("saveFlag", "saveFlag");
		return SUCCESS;
	}

	public String mainList(){
		Organ organ = getCurrentUser().getOrgan();
		this.putToRequest("equipmentList", equipmentService.getEquipmentList(equipmentType, equipment,organ));
		if (null != equipment && null != equipment.getOrgan()) {
			this.putToRequest("saveFlag", equipment);
			this.putToRequest("organId", equipment.getOrgan().getOrganId());
			if(StringUtils.isEmpty(equipment.getOrgan().getOrganId())){
				this.putToRequest("Organ", getCurrentUser().getOrgan());
			}else{
				//this.putToRequest("Organ", organService.get(equipment.getOrgan().getOrganId(), Organ.class));
				this.putToRequest("Organ", equipmentService.get(equipment.getOrgan().getOrganId(), Organ.class));
			}
		}else{
			this.putToRequest("Organ", getCurrentUser().getOrgan());
		}

		// 最左栏
		if (null != this.getRequest().getParameter("saveFlag") && !"".equals(this.getRequest().getParameter("saveFlag"))) {
			this.putToRequest("saveFlag", "F2T");
		}
		return SUCCESS;
	}

	/**
	 * @description 获取技术设备配备列表
	 * @author 陈文聪 2010 3 4 12:34:56
	 * @modify 陈文聪 2010 8 19 02:29:29 修改注释格式.
	 */
	public String list() {
		Organ organ = getCurrentUser().getOrgan();
		this.putToRequest("equipmentList", equipmentService.getEquipmentList(equipmentType, equipment,organ));
		if (null != equipment && null != equipment.getOrgan()) {
			this.putToRequest("saveFlag", equipment);
			this.putToRequest("organId", equipment.getOrgan().getOrganId());
			if(StringUtils.isEmpty(equipment.getOrgan().getOrganId())){
				this.putToRequest("Organ", getCurrentUser().getOrgan());
			}else{
				//this.putToRequest("Organ", organService.get(equipment.getOrgan().getOrganId(), Organ.class));
				this.putToRequest("Organ", equipmentService.get(equipment.getOrgan().getOrganId(), Organ.class));
			}
		}else{
			this.putToRequest("Organ", getCurrentUser().getOrgan());
		}

		// 最左栏
		if (null != this.getRequest().getParameter("saveFlag") && !"".equals(this.getRequest().getParameter("saveFlag"))) {
			this.putToRequest("saveFlag", "F2T");
		}
		return SUCCESS;
	}

	/**
	 * @description 保存技术设备配备
	 * @author 陈文聪 2010 3 4 12:34:56
	 * @modify 陈文聪 2010 8 19 02:29:40 修改注释格式.
	 */
	public String save() {
		Organ organ = getCurrentUser().getOrgan();
		Department department = getCurrentUser().getUserInfo().getDepartment();
		User user = getCurrentUser();
		equipmentService.saveButch(numberMap, equipmentType,user,organ,department);
		addActionMessage("保存成功");
		return "list_action";
	}

	/**
	 * @description 上报技术设备配备
	 * @author xiangy 2011 9 2 16:23:22
	 *
	 */
	public String report() {
		Organ organ = getCurrentUser().getOrgan();
		TransmitMessage transmitMessage = null;
		String eqNumberIds ="";
		List<String> eqList = new ArrayList<String>();
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("organId", organ.getOrganId());
		List<String> idList = null;//(List<String>) equipmentService.listOnly("select e.equipmentId from EquipmentNumber e where 1=1 and e.organ.organId = :organId", params);

		for (String eid : idList) {
				eqNumberIds += eid + ",";
		}
		if(eqNumberIds != null && !"".equals(eqNumberIds)) {
//			List<String> organIdList = initParamsService.getReportToOragans();
			/*List<String> organIdList = initParamsService.getReportToOragans(getCurrentUser().getOrgan());
			Iterator<String> i = organIdList.iterator();
			while (i.hasNext()) {
				String organId = i.next();
				transmitMessage = equipmentService.reportToOrgan(Arrays.asList(eqNumberIds.split(","))
						,  getCurrentUser().getOrgan(), organId);
			}*/
		}
		return "list_action";
	}

	/**
	 *
	 * <p>
	 * 批量上报
	 * 向 钰 创建时间  2011 9 9 - 10:30:15
	 * @return
	 */
	public String reports(){
		TransmitMessage transmitMessage = null;
		List<String> ids = this.getIds();
		String eqNumberIds ="";
		List<String> eqList = new ArrayList<String>();
		Map<String,Object> params = new HashMap<String,Object>();
		for (String id : ids) {
			params.put("organId", id);
			List<String> idList = null;//(List<String>) equipmentService.listOnly("select e.equipmentId from EquipmentNumber e where 1=1 and e.organ.organId = :organId", params);
			for (String eid : idList) {
				eqNumberIds += eid + ",";
			}
		}
		if(eqNumberIds != null && !"".equals(eqNumberIds)) {
//			List<String> organIdList = initParamsService.getReportToOragans();
			List<String> organIdList = null;//initParamsService.getReportToOragans(getCurrentUser().getOrgan());
			Iterator<String> i = organIdList.iterator();
			while (i.hasNext()) {
				String organId = i.next();
				/*transmitMessage = equipmentService.reportToOrgan(Arrays.asList(eqNumberIds.split(","))
						,  getCurrentUser().getOrgan(), organId);*/
			}
		}
		return "checkDistrictList";
	}


	/**
	 * @description 显示详情
	 * @author 陈文聪 2010 3 4 12:34:56
	 * @modify 陈文聪 2010 8 19 02:29:51 修改注释格式.
	 */
	public String view() {
		Organ organ = getCurrentUser().getOrgan();
		equipment = equipmentService.getEquipmentNumber(this.getId(),organ);
		if (null != equipment && null != equipment.getOrgan() && !"".equals(equipment.getOrgan().getOrganId())) {
			equipment = equipmentService.getEquipmentNumber(this.getId(), equipment.getOrgan().getOrganId());
		} else {
			equipment = equipmentService.getEquipmentNumber(this.getId(),organ);
		}
		return SUCCESS;
	}

	/**
	 * @description 当前用户行政区划下的所有单位树
	 * @author 陈文聪 2010 3 4 12:34:56
	 * @modify 陈文聪 2010 8 19 02:30:05 修改注释格式.
	 */
	public String tree() {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("parentId", parentId);
		params.put("layer", getCurrentUser().getOrgan().getLayer()+"%");
		Organ organ = getCurrentUser().getOrgan();
		Organ x = organ.getParent();
		List<Map<String,Object>> districtTree = equipmentService.getTree(params,organ);
		setResultData(districtTree);
		return "json";
	}

	/**
	 * 统计设备数量
	 * @author liuyf
	 * @return String
	 */
	public String stat() {
		Organ organ = getCurrentUser().getOrgan();
		List<Object[]> list = equipmentService.countStatOrgan(organName,organ);
		this.putToRequest("list", list);
		this.putToRequest("organNames", organName);
		return SUCCESS;
	}
	/**
	 * 历史统计设备数量
	 * @author zhaohy
	 * @return String
	 */
	public String showHistoryStat() {
		Organ organ = getCurrentUser().getOrgan();
		/*if(reportDto == null){
			DateFormat dateFormat = new SimpleDateFormat("yyyy");
			reportDto = new ReportDto();
			reportDto.setYears(dateFormat.format(new Date()));
			reportDto.setQuarter(12);
		}else {
			equipmentReportOrganList = equipmentService.getEquipmentReportOrganList(reportDto,organ);
		}*/
		return SUCCESS;
	}
	/**
	 * 撰取统计数据
	 * @author liuyf
	 * @return String
	 */
	public String dataStat() {
		List<Object[]> list = equipmentService.countDataStat(equipment);
		putToRequest("dataList", list);
		return SUCCESS;
	}

	/**
	 *
	 * <p>
	 * 设备管理查询
	 * 向 钰 创建时间  2011 9 7 - 18:54:47
	 * @return
	 */
	public String checkmain(){
		String tableId = "districtList";
		PageSortModel psm = new PageSortModel(getRequest(),tableId);
		List<District> districtList = (List<District>) this.equipmentService.get(tableId, District.class); //districtService.findPageByCriteria(psm, district, null);
		putToRequest("districtList", districtList);
		return SUCCESS;
	}
	public String checkDistrictList(){
		PageSortModel psm = new PageSortModel(getRequest(),"equipmentList");
		String districtCode = getRequest().getParameter("district.districtCode");//getRequestValue().getParameter("district.districtCode");
		if(districtCode == null ) {
			district = new District();
			district.setDistrictCode(getCurrentUser().getOrgan().getDistrict().getDistrictCode());
			district = equipmentService.get(district.getDistrictCode(),District.class);
		}else{
			 district = equipmentService.get(districtCode,District.class);
		}
		List<Organ> equipmentList = equipmentService.getDistrictList(psm,district,getCurrentUser().getOrgan(),status,equipment);
		this.putToRequest("equipmentList", equipmentList);
		this.putToRequest("district", district);
		return SUCCESS;
	}

	public String listView(){
		PageSortModel psm = new PageSortModel(getRequest(),"equipmentList");
		Organ organ = equipment.getOrgan();
		this.putToRequest("organId", equipment.getOrgan().getOrganId());
		this.putToRequest("equipmentList", equipmentService.getEquipmentList(psm, organ));
		return SUCCESS;
	}

	// geter & seter

	/**
	 * @return the organName
	 */
	public String getOrganName() {
		return organName;
	}

	/**
	 * @return the equipment
	 */
	public EquipmentNumber getEquipment() {
		return equipment;
	}

	/**
	 * @param equipment the equipment to set
	 */
	public void setEquipment(EquipmentNumber equipment) {
		this.equipment = equipment;
	}

	/**
	 * @param organName the organName to set
	 */
	public void setOrganName(String organName) {
		this.organName = organName;
	}

	public EquipmentService getEquipmentService() {
		return equipmentService;
	}

	public void setEquipmentService(EquipmentService equipmentService) {
		this.equipmentService = equipmentService;
	}

	public EquipmentTypeService getEquipmentTypeService() {
		return equipmentTypeService;
	}

	public void setEquipmentTypeService(EquipmentTypeService equipmentTypeService) {
		this.equipmentTypeService = equipmentTypeService;
	}

	public EquipmentType getEquipmentType() {
		return equipmentType;
	}

	public void setEquipmentType(EquipmentType equipmentType) {
		this.equipmentType = equipmentType;
	}

	public Map<String, String> getNumberMap() {
		return numberMap;
	}

	public void setNumberMap(Map<String, String> numberMap) {
		this.numberMap = numberMap;
	}

	/*public OrganService getOrganService() {
		return organService;
	}

	public void setOrganService(OrganService organService) {
		this.organService = organService;
	}*/

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return the equipmentReportOrganList
	 */
	public List getEquipmentReportOrganList() {
		return equipmentReportOrganList;
	}

	/**
	 * @param equipmentReportOrganList the equipmentReportOrganList to set
	 */
	public void setEquipmentReportOrganList(List equipmentReportOrganList) {
		this.equipmentReportOrganList = equipmentReportOrganList;
	}


	/**
	 * @return the reportDto
	 */
	/*public ReportDto getReportDto() {
		return reportDto;
	}*/

	/**
	 * @param reportDto the reportDto to set
	 */
	/*public void setReportDto(ReportDto reportDto) {
		this.reportDto = reportDto;
	}*/

	/*public void setEquipmentTransmitWsClient(
			TransmitWsClientTemplate equipmentTransmitWsClient) {
		this.equipmentTransmitWsClient = equipmentTransmitWsClient;
	}*/

	public InitParamsService getInitParamsService() {
		return initParamsService;
	}

	public void setInitParamsService(InitParamsService initParamsService) {
		this.initParamsService = initParamsService;
	}

	/**
	 * @return the districtService
	 */
	/*public IDistrictService getDistrictService() {
		return districtService;
	}*/

	/**
	 * @param districtService the districtService to set
	 */
	/*public void setDistrictService(IDistrictService districtService) {
		this.districtService = districtService;
	}*/

	/**
	 * @return the district
	 */
	public District getDistrict() {
		return district;
	}

	/**
	 * @param district the district to set
	 */
	public void setDistrict(District district) {
		this.district = district;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the organIds
	 */
	public String getOrganIds() {
		return organIds;
	}

	/**
	 * @param organIds the organIds to set
	 */
	public void setOrganIds(String organIds) {
		this.organIds = organIds;
	}

	/**
	 * @return the equipmentTransmitWsClient
	 */
	/*public TransmitWsClientTemplate getEquipmentTransmitWsClient() {
		return equipmentTransmitWsClient;
	}*/



}
