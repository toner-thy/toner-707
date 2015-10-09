package com.cdthgk.bmp.secrecyStatistics.action;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.action.BmpAction;
import com.cdthgk.bmp.keyPart.service.PartModuleService;
import com.cdthgk.bmp.keyPart.vo.Part;
import com.cdthgk.bmp.keysection.qo.KeySectionQo;
import com.cdthgk.bmp.keysection.service.KeySectionModuleService;
import com.cdthgk.bmp.secrecyStatistics.dto.SecrecyStatisticsDto;
import com.cdthgk.bmp.secrecyStatistics.service.SecrecyStatisticsModuleService;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.service.SecrecyWorkOrganMemberUnitModuleService;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.service.SecrecyWorkOrganModuleService;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.service.SecrecyWorkOrganRelationMemberModuleService;
import com.cdthgk.bmp.secrecyorganization.secrecyworkorganunit.vo.SecrecyWorkOrgan;
import com.cdthgk.bmp.secrecyperson.service.SecrecyPersonModuleService;
import com.cdthgk.bmp.secrecyperson.vo.SecrecyPerson;
import com.cdthgk.bmp.secrecyworksummary.service.SecrecyWorksummaryModuleService;
import com.cdthgk.platform.attachment.service.AttachmentService;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;

/**
 * <p>
 * 保密工作概况Action类
 * </p>
 * <p>
 * 陶汇源 2013-01-13 11:41
 * </p>
 * <blockquote>
 * <h4>历史修改记录</h4>
 * <ul>
 * <li>修改人 修改时间 修改描述
 * </ul>
 * </blockquote>
 * <p>
 * copyright tianyu 2012, all rights reserved.
 * </p>
 *
 * @author taohy
 * @since 1.0
 * @version 1.0
 */
public class SecrecyStatisticsAction extends BmpAction{

	private static final long serialVersionUID = 1L;
	private Organ organ;
	private District district;
	private String flag;
	private SecrecyStatisticsModuleService secrecyStatisticsModuleService;
	private SecrecyWorkOrganModuleService secrecyWorkOrganModuleService;
	private SecrecyWorkOrganMemberUnitModuleService secrecyWorkOrganMemberUnitModuleService;
	private SecrecyWorkOrganRelationMemberModuleService secrecyWorkOrganRelationMemberModuleService;
	private AttachmentService attachmentService;
	private KeySectionModuleService keySectionModuleService;
	private PartModuleService partModuleService;
	private SecrecyPersonModuleService secrecyPersonModuleService;
	private SecrecyPerson secrecyPerson;
	private Part part;
	private KeySectionQo keySectionQo;

	// *********************** 方 法 ***********************

	// 构造器
	/** 默认构造器 */
	public SecrecyStatisticsAction() {
	}

	/**
	 *
	 * <p>
	 * 行政区划树
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-1-13 - 上午11:50:12
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @return
	 */
	public String main(){
		return MAIN;
	}

	/**
	 *
	 * <p>
	 * 保密数据概况列表
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-1-13 - 下午11:45:05
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @return
	 */
	public String list(){
		if(district == null) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = secrecyStatisticsModuleService.get(district.getDistrictCode(), District.class);
		}
		List<SecrecyStatisticsDto> list = secrecyStatisticsModuleService.getSecrecyStatisticsPageList(district, organ);
		putToRequest("list", list);
		return LIST;
	}

	/**
	 *
	 * <p>
	 * 打印方法
	 * </p>
	 * <p>
	 * 宋亚非 2013-4-26 下午12:03:25
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 *
	 *@return
	 */
	public String print(){
		district = getCurrentUser().getOrgan().getDistrict();
		List<SecrecyStatisticsDto> list = secrecyStatisticsModuleService.getSecrecyStatisticsPageList(district, organ);
		putToRequest("list", list);
		return "success";
	}

	@SuppressWarnings("rawtypes")
	public String detail(){
		String result = "";
		SecrecyWorkOrgan secrecyWorkOrgan = null;
		organ = secrecyWorkOrganMemberUnitModuleService.get(organ.getOrganId(), Organ.class);
		//if("personGroupMember".equals(flag) || "secrecyWorkOrganMember".equals(flag)){
			//保密工作机构成员  和  保密办成员  相同属性
			secrecyWorkOrgan = secrecyWorkOrganModuleService.getSecrecyWorkOrganByOrgan(organ);
			putToRequest("attachments", attachmentService.getAttachmentsByHostId(secrecyWorkOrgan.getSecrecyWorkOrganId()));
			putToRequest("secrecyWorkOrgan", secrecyWorkOrgan);
		//}
		if("personGroupMember".equals(flag)){
			//保密工作机构成员
			putToRequest("secrecyWorkOrganRelationMemberList", secrecyWorkOrganRelationMemberModuleService.getPageList(secrecyWorkOrgan, organ, PERSON_CHANGE_HISTORY));
			result = "personGroupMemberDetail";
		} else if("secrecyWorkOrganMember".equals(flag)){
			//保密办成员
			putToRequest("secrecyWorkOrganMemberUnitList", secrecyWorkOrganMemberUnitModuleService.getPageList(secrecyWorkOrgan,organ,PERSON_CHANGE_NOW));
			result = "secrecyWorkOrganMemberDetail";
		} else if("keysection".equals(flag)){
			//保密要害部门
			PageSortModel psm = new PageSortModel(getRequest(), "secrecyPersonList");
			putToRequest("keySectionlist", keySectionModuleService.queryList(psm, organ, keySectionQo));
			result = "keysectionDetail";
		} else if("keyPart".equals(flag)){
			PageSortModel psm = new PageSortModel(getRequest(), "partList");
			putToRequest("partList", partModuleService.getListPage(psm, part, organ));
			result = "keyPartDetail";
		} else if("secrecyPerson".equals(flag)){
			PageSortModel psm = new PageSortModel(getRequest(), "secrecyPersonList");
			try {
				putToRequest("secrecyPersonList", secrecyPersonModuleService.getPageAllList(psm, secrecyPerson, organ,null,null,null,SECRECY_STATUS_NOW));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			result = "secrecyPersonDetail";
		}
		return result;
	}

	/**
	 *
	 * <p>
	 * 导出EXCEL
	 * </p>
	 * <p>
	 * 创建人 陶汇源  创建时间  2013-4-26 - 上午10:02:21
	 * </p>
	 * <blockquote>
	 * <h4>历史修改记录</h4>
	 * <ul>
	 * <li>修改人 修改时间 修改描述
	 * </ul>
	 * </blockquote>
	 * @return
	 */
	public String exportData(){
		if(district == null) {
			district = getCurrentUser().getOrgan().getDistrict();
		} else {
			district = secrecyStatisticsModuleService.get(district.getDistrictCode(), District.class);
		}
		List<SecrecyStatisticsDto> list = secrecyStatisticsModuleService.getSecrecyStatisticsPageList(district, organ);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("list", list);
		params.put("districtName", district.getDistrictName());
		setResultData(params);
		return SUCCESS;
	}

	/**
	 * @param secrecyWorksummaryModuleService 设置secrecyWorksummaryModuleService
	 */
	public void setSecrecyWorksummaryModuleService(
			SecrecyWorksummaryModuleService secrecyWorksummaryModuleService) {
		//this.secrecyWorksummaryModuleService = secrecyWorksummaryModuleService;
	}

	/**
	 * @param secrecyWorkOrganModuleService 设置secrecyWorkOrganModuleService
	 */
	public void setSecrecyWorkOrganModuleService(
			SecrecyWorkOrganModuleService secrecyWorkOrganModuleService) {
		this.secrecyWorkOrganModuleService = secrecyWorkOrganModuleService;
	}

	/**
	 * @param secrecyWorkOrganMemberUnitModuleService 设置secrecyWorkOrganMemberUnitModuleService
	 */
	public void setSecrecyWorkOrganMemberUnitModuleService(
			SecrecyWorkOrganMemberUnitModuleService secrecyWorkOrganMemberUnitModuleService) {
		this.secrecyWorkOrganMemberUnitModuleService = secrecyWorkOrganMemberUnitModuleService;
	}

	/**
	 * @param secrecyWorkOrganRelationMemberModuleService 设置secrecyWorkOrganRelationMemberModuleService
	 */
	public void setSecrecyWorkOrganRelationMemberModuleService(
			SecrecyWorkOrganRelationMemberModuleService secrecyWorkOrganRelationMemberModuleService) {
		this.secrecyWorkOrganRelationMemberModuleService = secrecyWorkOrganRelationMemberModuleService;
	}

	/**
	 * @param attachmentService 设置attachmentService
	 */
	public void setAttachmentService(AttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}

	/**
	 * @param keySectionModuleService 设置keySectionModuleService
	 */
	public void setKeySectionModuleService(
			KeySectionModuleService keySectionModuleService) {
		this.keySectionModuleService = keySectionModuleService;
	}

	/**
	 * @param partModuleService 设置partModuleService
	 */
	public void setPartModuleService(PartModuleService partModuleService) {
		this.partModuleService = partModuleService;
	}

	/**
	 * @param secrecyPersonModuleService 设置secrecyPersonModuleService
	 */
	public void setSecrecyPersonModuleService(
			SecrecyPersonModuleService secrecyPersonModuleService) {
		this.secrecyPersonModuleService = secrecyPersonModuleService;
	}

	/**
	 * @param secrecyStatisticsModuleService the secrecyStatisticsModuleService to set
	 */
	public void setSecrecyStatisticsModuleService(
			SecrecyStatisticsModuleService secrecyStatisticsModuleService) {
		this.secrecyStatisticsModuleService = secrecyStatisticsModuleService;
	}

	/**
	 * 返回secrecyPerson
	 * @return secrecyPerson
	 */
	public SecrecyPerson getSecrecyPerson() {
		return secrecyPerson;
	}

	/**
	 * 设置secrecyPerson
	 * @param secrecyPerson secrecyPerson
	 */
	public void setSecrecyPerson(SecrecyPerson secrecyPerson) {
		this.secrecyPerson = secrecyPerson;
	}

	/**
	 * 返回part
	 * @return part
	 */
	public Part getPart() {
		return part;
	}

	/**
	 * 设置part
	 * @param part part
	 */
	public void setPart(Part part) {
		this.part = part;
	}

	/**
	 * 返回keySectionQo
	 * @return keySectionQo
	 */
	public KeySectionQo getKeySectionQo() {
		return keySectionQo;
	}

	/**
	 * 设置keySectionQo
	 * @param keySectionQo keySectionQo
	 */
	public void setKeySectionQo(KeySectionQo keySectionQo) {
		this.keySectionQo = keySectionQo;
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
	 * @return 返回flag
	 */
	public String getFlag() {
		return flag;
	}

	/**
	 * @param flag 设置flag
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}
}