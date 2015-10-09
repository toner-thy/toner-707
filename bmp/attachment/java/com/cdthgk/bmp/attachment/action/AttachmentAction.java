package com.cdthgk.bmp.attachment.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.cdthgk.bmp.attachment.dto.AttachmentDto;
import com.cdthgk.bmp.attachment.dto.AttachmentQueryDto;
import com.cdthgk.bmp.attachment.service.AttachmentListService;
import com.cdthgk.bmp.attachment.service.AttachmentQueryService;
import com.cdthgk.bmp.attachment.vo.AttachmentList;
import com.cdthgk.platform.attachment.domain.Attachment;
import com.cdthgk.platform.attachment.service.AttachmentService;
import com.cdthgk.platform.base.action.PlatformAction;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;

public class AttachmentAction extends PlatformAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private AttachmentQueryDto attachmentQueryDto;
	private AttachmentList attachmentList;

	// 上传
	private Attachment attachment;
	//上传附件
	private List<String> attachments;

	//private UpLoadedFiles upLoadFiles;
	private AttachmentService attachmentService;

	private AttachmentListService attachmentListService;

	private AttachmentQueryService attachmentQueryService;

	private String timeType;

	private String deleteIds;

	public Attachment getAttachment() {
		return attachment;
	}

	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}

	/*public UpLoadedFiles getUpLoadFiles() {
		return upLoadFiles;
	}

	public void setUpLoadFiles(UpLoadedFiles upLoadFiles) {
		this.upLoadFiles = upLoadFiles;
	}*/

	public AttachmentService getAttachmentService() {
		return attachmentService;
	}

	public void setAttachmentService(AttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}

	public String getTimeType() {
		return timeType;
	}

	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}

	/**
	 * 获取首页显示的附件列表
	 * @return
	 */
	public String indexList(){
		PageSortModel<AttachmentList> psm = new PageSortModel<AttachmentList>(getRequest(), "attList");
		psm.setPageSize(3);
		psm.setPageNumber(1);
		List<AttachmentList> attList = attachmentListService.indexList(psm);
		if( attList!=null && attList.size()>0 ){
			for( AttachmentList attal : attList ){
				List<Attachment> attaList = attachmentService.getAttachmentsByHostId(attal.getAttachmentListId());
				attal.setAttachmentCollect(attaList);
			}
		}
		putToRequest("attList", attList);
		return "success";
	}

	/**
	 * 首页显示用的所有附件列表
	 * @return
	 */
	public String indexAll(){
		PageSortModel<AttachmentList> psm = new PageSortModel<AttachmentList>(getRequest(), "attList");
		List<AttachmentList> attList = attachmentListService.indexAll(attachmentList, psm);
		if( attList!=null && attList.size()>0 ){
			for( AttachmentList attal : attList ){
				List<Attachment> attaList = attachmentService.getAttachmentsByHostId(attal.getAttachmentListId());
				attal.setAttachmentCollect(attaList);
			}
		}
		putToRequest("attList", attList);
		return "success";
	}


	public String myList(){

		PageSortModel<AttachmentList> psm = new PageSortModel<AttachmentList>(getRequest(), "attachmentList");
		if( attachmentList!=null ){
			attachmentList.setCreatePerson(getCurrentUser());
		}else{
			attachmentList = new AttachmentList();
			attachmentList.setCreatePerson(getCurrentUser());
		}

		List<AttachmentList> attList = attachmentListService.queryAttachmentListPageList(psm, attachmentList);
		putToRequest("attList", attList);
		return SUCCESS;
	}

	public String organList(){

		PageSortModel psm = new PageSortModel(getRequest(), "attachmentList");

		if(attachmentQueryDto == null){
			attachmentQueryDto = new AttachmentQueryDto();
		}

		Organ organ = getCurrentUser().getOrgan();
		attachmentQueryDto.setOrgan(organ);

		/*List<Attachment> attList = attachementService.queryAttachmentPageList(psm, attachmentQueryDto);
		this.attachmentList = getAttachmentDtoList(attList);*/

		return SUCCESS;
	}

	public String districtList(){

		PageSortModel psm = new PageSortModel(getRequest(), "attachmentList");

		if(attachmentQueryDto == null){
			attachmentQueryDto = new AttachmentQueryDto();
		}

		District district = getCurrentUser().getOrgan().getDistrict();
		attachmentQueryDto.setDistrict(district);

		/*List<Attachment> attList = attachementService.queryAttachmentPageList(psm, attachmentQueryDto);
		this.attachmentList = getAttachmentDtoList(attList);*/

		return SUCCESS;
	}

	public String allList(){

		PageSortModel psm = new PageSortModel(getRequest(), "attachment");

		List<Attachment> attList = attachmentQueryService.getPageList(psm, attachmentQueryDto, attachmentList);


		putToRequest("attachmentList1", attList);

		return SUCCESS;
	}

	public List<AttachmentDto> getAttachmentDtoList(List<Attachment> attList){

		List<AttachmentDto> attDtoList = new ArrayList<AttachmentDto>();

		if(attList != null){
			for(Attachment att : attList){

				AttachmentDto attDto = new AttachmentDto();

				try {
					BeanUtils.copyProperties(attDto, att);
					/*User u = attachementService.get(att.getUserId(), User.class);
					Organ o = attachementService.get(att.getOrganId(), Organ.class);*/
					//District d = attachementService.get(att.getDistrictId(), District.class);

					/*attDto.setUser(u);
					attDto.setOrgan(o);*/
					//attDto.setDistrict(d);
				} catch (Exception e) {
					e.printStackTrace();
				}

				attDtoList.add(attDto);
			}
		}

		return attDtoList;

	}

	public String add(){

		return SUCCESS;
	}

	public String save(){
		if(attachments!=null && attachments.size()>0 && attachmentList!=null){
			attachmentList.setCreatePerson(getCurrentUser());
			attachmentList.setCreateTime(new Date());
			attachmentListService.save(attachmentList);
			// 上传附件
			attachmentService.updateHostId(attachmentList.getAttachmentListId(),attachments);
			addActionMessage("附件上传成功。");
			return SUCCESS;
		}else{
			addActionMessage("上传附件失败，请重新上传。");
			return "failure";
		}
	}

	/**
	 *
	 * <p>
	 * edit
	 * </p>
	 * <p>
	 * 宋亚非 2013-5-9 上午9:47:59
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
	public String edit(){
		String id = getRequest().getParameter("id");
		attachmentList = this.attachmentListService.get(id);
		return SUCCESS;
	}

	/**
	 *
	 * <p>
	 * 方法名：editing
	 * </p>
	 * <p>
	 * 宋亚非 2013-5-9 上午9:53:21
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
	public String editing(){
		if(attachments!=null && attachments.size()>0 && attachmentList!=null){
			attachmentList.setCreatePerson(getCurrentUser());
			attachmentList.setCreateTime(new Date());
			attachmentListService.update(attachmentList);
			// 上传附件
			attachmentService.updateHostId(attachmentList.getAttachmentListId(),attachments);
			addActionMessage("附件编辑成功。");
			return SUCCESS;
		}else{
			addActionMessage("附件编辑失败，请重新编辑。");
			return "failure";
		}
	}

	public String update(){

		return SUCCESS;
	}

	public String detail(){



		return SUCCESS;
	}

	public String download(){
		attachment=attachmentService.get(attachment.getAttachId());
		/*UploadFile sa = new UploadFile();
		sa.setBeanConfig("attachment");
		sa.setSavePath(attachment.getAttachUrl());
		sa.setFileName(attachment.getAttachName());

		this.setOutData(sa);
		putToRequest("attachment", attachment);*/
		return "download";
	}

	public void validateDownload(){

		if(attachment == null
				|| attachment.getAttachId() == null
				|| "".equals(attachment.getAttachId())){
			this.addActionError("对不起，你需要下载的附件ID丢失，请重新选择下载!");
		}

	}

	public String delete(){
		String[] idArray = this.getDeleteIds().split(",");
		if( idArray!=null ){
			for( String id : idArray ){
				attachmentListService.delete(id);
			}
		}
		this.addActionMessage("删除附件成功。");
		return SUCCESS;
	}

	public void validateDelete(){

		/*if (this.getDeleteIds().getDeleteId() == null || this.getDeleteIds().getDeleteId().isEmpty()) {
			addActionMessage("请勾选需要删除的发起学习");
		}*/

	}

	/**
	 * @return the attachments
	 */
	public List<String> getAttachments() {
		return attachments;
	}

	/**
	 * @param attachments the attachments to set
	 */
	public void setAttachments(List<String> attachments) {
		this.attachments = attachments;
	}

	/**
	 * @return the attachmentListService
	 */
	public AttachmentListService getAttachmentListService() {
		return attachmentListService;
	}

	/**
	 * @param attachmentListService the attachmentListService to set
	 */
	public void setAttachmentListService(AttachmentListService attachmentListService) {
		this.attachmentListService = attachmentListService;
	}

	/**
	 * @return the attachmentList
	 */
	public AttachmentList getAttachmentList() {
		return attachmentList;
	}

	/**
	 * @param attachmentList the attachmentList to set
	 */
	public void setAttachmentList(AttachmentList attachmentList) {
		this.attachmentList = attachmentList;
	}

	/**
	 * @return the deleteIds
	 */
	public String getDeleteIds() {
		return deleteIds;
	}

	/**
	 * @param deleteIds the deleteIds to set
	 */
	public void setDeleteIds(String deleteIds) {
		this.deleteIds = deleteIds;
	}

	/**
	 * @return the attachmentQueryService
	 */
	public AttachmentQueryService getAttachmentQueryService() {
		return attachmentQueryService;
	}

	/**
	 * @param attachmentQueryService the attachmentQueryService to set
	 */
	public void setAttachmentQueryService(
			AttachmentQueryService attachmentQueryService) {
		this.attachmentQueryService = attachmentQueryService;
	}

	/**
	 * @return the attachmentQueryDto
	 */
	public AttachmentQueryDto getAttachmentQueryDto() {
		return attachmentQueryDto;
	}

	/**
	 * @param attachmentQueryDto the attachmentQueryDto to set
	 */
	public void setAttachmentQueryDto(AttachmentQueryDto attachmentQueryDto) {
		this.attachmentQueryDto = attachmentQueryDto;
	}



}
