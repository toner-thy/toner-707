package com.cdthgk.bmp.dataClass.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cdthgk.bmp.core.action.BmpAction;
import com.cdthgk.bmp.dataClass.service.DataClassProService;
import com.cdthgk.bmp.dataClass.service.DataClassService;
import com.cdthgk.bmp.dataClass.vo.DataClass;
import com.cdthgk.bmp.dataClass.vo.DataClassOrgan;
import com.cdthgk.bmp.dataClass.vo.DataClassPro;
import com.cdthgk.common.lang.StringUtils;
import com.cdthgk.platform.attachment.context.AttachmentContext;
import com.cdthgk.platform.attachment.domain.Attachment;
import com.cdthgk.platform.organization.core.OrganizationContext;
import com.cdthgk.platform.organization.organ.domain.Organ;

import ec.common.PageSortModel;

public class DataClassAction extends BmpAction {

	private static final long serialVersionUID = 1L;
	private String deleteIds;
	private DataClass dataClass;
	private DataClassService dataClassService;
	private DataClassPro dataClassPro;
	private DataClassProService dataClassProService;
    private Attachment attachment;
    private List<Attachment> attachmentList;
    private List<String> secAttach;
	private String organIds;
	private boolean needReload = false;

	//查询本单位
	public String list(){
		PageSortModel<DataClass> psm = new PageSortModel<DataClass>(getRequest(), "dataClassList");
		List<DataClass> dataClassList = dataClassService.queryListPage(psm, dataClass, getCurrentUser().getOrgan());
		putToRequest("dataClassList", dataClassList);
		return SUCCESS;
	}

	public String add(){
		return SUCCESS;
	}
	public String adding(){
		dataClass.setOrgan(getCurrentUser().getOrgan());
		dataClass.setStatus(0);
		Set<DataClassOrgan> organs = new HashSet<DataClassOrgan>();
		if (StringUtils.isNotEmpty(organIds)) {
			String[] organsList = organIds.split(",");
			for (String organId : organsList) {
				DataClassOrgan dataClassOrgan = new DataClassOrgan();
				Organ organ = new Organ();
				organ.setOrganId(organId.trim());
				dataClassOrgan.setOrgan(organ);
				dataClassOrgan.setDataClass(dataClass);
				organs.add(dataClassOrgan);
			}
		}
		dataClass.setOrgans(organs);
		dataClassService.save(dataClass);
		needReload = true;
		addActionMessage("保存成功。");
		return SUCCESS;
	}

	public String edit(){
		dataClass = dataClassService.get(dataClass.getDataClassId());
		Iterator<DataClassOrgan> iter = dataClass.getOrgans().iterator();
		List<Organ> organs = new ArrayList<Organ>();
		while (iter.hasNext()) {
			DataClassOrgan oo = iter.next();
			organs.add(oo.getOrgan());
		}
		getRequest().setAttribute("organs", organs);
		return SUCCESS;
	}
	public String editing(){
		dataClassService.update(dataClass,organIds);
		needReload = true;
		return SUCCESS;
	}

	public String publish(){
		dataClassService.publish(dataClass.getDataClassId());
		addActionMessage("发布成功。");
		return SUCCESS;
	}


	public String delete(){
		String[] idArray = getDeleteIds().split(",");
		if(idArray!=null){
			for(String id : idArray){
				dataClassService.delete(id);
			}
		}
		addActionMessage("删除成功。");
		return SUCCESS;
	}

	public String statDataClassPro(){
		dataClass = dataClassService.get(dataClass.getDataClassId());
		//查询该分类下所有单位：填报情况
		List<Map<String, Object>> list = dataClassService.queryDataClassProById(dataClass);
		putToRequest("list", list);
		return SUCCESS;
	}
	public String statDataClassProView(){
		String organId = getRequest().getParameter("organId");
		dataClass = dataClassService.get(dataClass.getDataClassId());
		List<DataClassPro> dataClassProList = dataClassProService.queryDataClassProById(dataClass.getDataClassId(),organId,null);
		putToRequest("dataClassProList", dataClassProList);
		putToRequest("organName", OrganizationContext.getInstance().getOrganService().get(organId).getOrganName());
		return SUCCESS;
	}

/******************************************资料填报*************************************/
	//树
	public String fillMain(){
		return SUCCESS;
	}
	//树
	public String fillTree(){
		Map<String,Object> params = new HashMap<String,Object>();
        params.put("organId", getCurrentUser().getUserInfo().getOrgan().getOrganId());
        List<Map<String,Object>> districtTree = dataClassService.getTree(params);
        setResultData(districtTree);
        return "json";
	}

	//查询分类下所有资料
	public String fillList(){
		PageSortModel<DataClassPro> psm = new PageSortModel<DataClassPro>(getRequest(), "dataClassProList");
		Map<String,Object> params = new HashMap<String,Object>();
		//默认选中根节点
		if(null == dataClass){
			params.put("dataClassId", "1");
			dataClass = new DataClass();
			dataClass.setName("全部");
		}else{
			params.put("dataClassId", dataClass.getDataClassId());
			dataClass = dataClassService.get(dataClass.getDataClassId());
		}
		List<DataClassPro> dataClassProList = dataClassProService.getPageList(psm, params,dataClassPro,getCurrentUser().getUserInfo().getOrgan());
		putToRequest("dataClassProList", dataClassProList);
		return SUCCESS;
	}

	public String fillAdd(){
		return SUCCESS;
	}
	public String fillAdding(){
		dataClassPro.setOrgan(getCurrentUser().getOrgan());
		dataClassPro.setCreatePerson(getCurrentUser());
		dataClassPro.setDataClass(dataClassService.get(dataClass.getDataClassId()));
		dataClassProService.save(dataClassPro);
		AttachmentContext.getInstance().getAttachmentService().updateHostId(dataClassPro.getId(),secAttach);
		addActionMessage("保存成功。");
		return SUCCESS;
	}
	public String fillView(){
		dataClassPro = dataClassProService.get(dataClassPro.getId());
        attachmentList = AttachmentContext.getInstance().getAttachmentService().getAttachmentsByHostId(dataClassPro.getId());
		return SUCCESS;
	}

	public String getDeleteIds() {
		return deleteIds;
	}

	public void setDeleteIds(String deleteIds) {
		this.deleteIds = deleteIds;
	}

	public DataClass getDataClass() {
		return dataClass;
	}

	public void setDataClass(DataClass dataClass) {
		this.dataClass = dataClass;
	}

	public DataClassService getDataClassService() {
		return dataClassService;
	}

	public void setDataClassService(DataClassService dataClassService) {
		this.dataClassService = dataClassService;
	}

	public boolean isNeedReload() {
		return needReload;
	}

	public void setNeedReload(boolean needReload) {
		this.needReload = needReload;
	}

	public String getOrganIds() {
		return organIds;
	}

	public void setOrganIds(String organIds) {
		this.organIds = organIds;
	}

	public DataClassProService getDataClassProService() {
		return dataClassProService;
	}

	public void setDataClassProService(DataClassProService dataClassProService) {
		this.dataClassProService = dataClassProService;
	}

	public DataClassPro getDataClassPro() {
		return dataClassPro;
	}

	public void setDataClassPro(DataClassPro dataClassPro) {
		this.dataClassPro = dataClassPro;
	}

	public Attachment getAttachment() {
		return attachment;
	}

	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}

	public List<Attachment> getAttachmentList() {
		return attachmentList;
	}

	public void setAttachmentList(List<Attachment> attachmentList) {
		this.attachmentList = attachmentList;
	}

	public List<String> getSecAttach() {
		return secAttach;
	}

	public void setSecAttach(List<String> secAttach) {
		this.secAttach = secAttach;
	}

}
