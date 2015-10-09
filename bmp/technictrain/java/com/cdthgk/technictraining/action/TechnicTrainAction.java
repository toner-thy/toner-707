package com.cdthgk.technictraining.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.cdthgk.bmp.core.action.BmpAction;
import com.cdthgk.platform.attachment.context.AttachmentContext;
import com.cdthgk.platform.attachment.domain.Attachment;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.district.service.DistrictService;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.organ.service.OrganService;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;
import com.cdthgk.platform.organization.userinfo.service.UserInfoService;
import com.cdthgk.platform.permission.user.domain.User;
import com.cdthgk.technictraining.dto.TechnicTrainDto;
import com.cdthgk.technictraining.service.TechnicTrainService;
import com.cdthgk.technictraining.vo.NotPresenceOrgan;
import com.cdthgk.technictraining.vo.PresenceOrgan;
import com.cdthgk.technictraining.vo.TechnicTrain;

import ec.common.PageSortModel;
@SuppressWarnings("all")
public class TechnicTrainAction extends BmpAction {
	private static final long serialVersionUID = 7348335045777678645L;
	private TechnicTrain technicTrain;
	private TechnicTrainDto technicTrainDto;
	private TechnicTrainService technicTrainService;
	private Attachment attachment;
	private List<Attachment> attachmentList;
	private Integer technicTrainList_crd;
	private Integer technicTrainList_p;

	private OrganService organService;
	private District district;
	private DistrictService districtService;
	private String showType;

	public String list() throws ParseException {
		// 获取计算培训列表
		String tableId = "technicTrainList";
		PageSortModel pageSortModel = new PageSortModel(this
				.getRequest(), tableId);
		if (technicTrainDto == null) {
			technicTrainDto = new TechnicTrainDto();
		}
		if (technicTrainDto.getTechnicTrain() == null) {
			technicTrainDto.setTechnicTrain(new TechnicTrain());
		}
		User user = getCurrentUser();
		technicTrainDto.setOrgan(user.getUserInfo().getOrgan());
		List<TechnicTrain> technicTrainList = technicTrainService.getPageList(
				pageSortModel, technicTrainDto,getCurrentUser());
		putToRequest("technicTrainList", technicTrainList);
		return "list";
	}

	public String main(){
		PageSortModel psm = new PageSortModel(getRequest(), "technicTrainList");
		List<District> districtList = districtService.findList(psm, district);
		putToRequest("districtList", districtList);

		return "main";
	}

	public String allList() throws ParseException {

		PageSortModel pageSortModel = new PageSortModel(this.getRequest(), "technicTrainList");

		if(showType==null || "".equals(showType)){
			showType = "0";
		}

		if(district == null) {
			district = new District();
			district = getCurrentUser().getOrgan().getDistrict();
		}
		if (district != null && "".equals(district.getDistrictCode())) {
			district = new District();
			district = getCurrentUser().getOrgan().getDistrict();
		}
		district = technicTrainService.get(district.getDistrictCode(), District.class);
		List<TechnicTrain> technicTrainList = technicTrainService.getPageAllList(pageSortModel, technicTrainDto, showType, district);
		putToRequest("technicTrainList", technicTrainList);
		return "allList";
	}
	@SuppressWarnings("unchecked")
	public String addBefore() {
		return "add";
	}
	private List<String> secAttach;
        private boolean needReload=false;
        public List<String> getSecAttach() {
                return secAttach;
        }

        public void setSecAttach(List<String> secAttach) {
                this.secAttach = secAttach;
        }
        private List<String> receivePersonIds1;
        private List<String> receivePersonIds2;
        private UserInfoService userInfoService;
	public UserInfoService getUserInfoService() {
                return userInfoService;
        }

        public void setUserInfoService(UserInfoService userInfoService) {
                this.userInfoService = userInfoService;
        }

        /**
	 * @description 新增技术培训
	 * @author 刘  舜 2010 02 02 12:46:30
	 * @modify 陈文聪 2010 8 17 02:17:38 修改注释格式.
	 * @throws ParseException
	 */
	public String add() throws ParseException {
		//FIXME Action中不要出现HttpServletRequest
		HttpServletRequest request = ServletActionContext.getRequest();
		// 设置基本属性
		User user = getCurrentUser();
		Date date = new Date();
		technicTrain.setCreateTime(date);
		technicTrain.setModifyTime(date);
		technicTrain.setDepartment(user.getUserInfo().getDepartment());
		technicTrain.setOrgan(user.getOrgan());
		technicTrain.setModifyPerson(user);
		technicTrain.setCreatePerson(user);

		// technicTrain.setPersonId(technicTrain.getUserInfo().getUserInfoId());
		// technicTrainService.save(technicTrain);

		// 获取参数
		List<Organ> organs=new ArrayList<Organ>();
		List<Organ> notorgans=new ArrayList<Organ>();
		if(receivePersonIds1!=null&&receivePersonIds1.size()>0)
                {
        		for(String oString:receivePersonIds1)
        		{
        		        Organ organ=organService.get(oString);
        		        organs.add(organ);
        		}
                }
		if(receivePersonIds2!=null&&receivePersonIds2.size()>0)
		{
		        for(String oString:receivePersonIds2)
		        {
		                Organ organ=organService.get(oString);
		                notorgans.add(organ);
		        }
		}
		technicTrainService.savePresenceBatch(technicTrain, organs, notorgans);

		// FIXME 此处将不受事务管理
		//处理附件
                AttachmentContext.getInstance().getAttachmentService().updateHostId(technicTrain.getTechnicTrainingId(), secAttach);
		addActionMessage("新增培训记录成功。");
		needReload = true;
		return "list_action";
	}

	/**
	 * @description 编辑技术培训之前
	 * @author 刘  舜 2010 02 02 12:46:30
	 * @modify 陈文聪 2010 8 17 02:21:08 修改注释格式.
	 */
	public String editBefore() {
		//FIXME Action中不要出现HttpServletRequest
		HttpServletRequest request = ServletActionContext.getRequest();
		technicTrain = technicTrainService.get(technicTrain.getTechnicTrainingId());
		//FIXME technicTrain ?= null
		// 用于页面显示organsName及隐藏传值
		Iterator<PresenceOrgan> po = technicTrain.getPresenceOrgans().iterator();
		List<Organ> organs=new ArrayList<Organ>();
                List<Organ> notorgans=new ArrayList<Organ>();
		while (po.hasNext()) {
			PresenceOrgan oo = po.next();
			organs.add(oo.getOrgan());
		}
		request.setAttribute("organs", organs);

		// 用于页面显示notorganNames及隐藏传值
		Iterator<NotPresenceOrgan> npo = technicTrain.getNotPresenceOrgans().iterator();
		while (npo.hasNext()) {
			NotPresenceOrgan noo = npo.next();
			notorgans.add(noo.getOrgan());
		}

		// 获取已上传的附件列表
		attachmentList=AttachmentContext.getInstance().getAttachmentService().getAttachmentsByHostId(technicTrain.getTechnicTrainingId());
		request.setAttribute("notorgans", notorgans);
		return "edit";
	}

	/**
	 * @description 编辑技术培训
	 * @author 刘  舜 2010 02 02 12:46:30
	 * @modify 陈文聪 2010 8 17 02:26:11 修改注释格式.
	 * @throws ParseException
	 */
	public String edit() throws ParseException {
		HttpServletRequest request = ServletActionContext.getRequest();
		User user = getCurrentUser();
		TechnicTrain editTechnicTrain = technicTrainService.get(technicTrain.getTechnicTrainingId());
		editTechnicTrain.setDepartment(user.getUserInfo().getDepartment());
		editTechnicTrain.setModifyPerson(user);
		editTechnicTrain.setModifyTime(new Date());
		editTechnicTrain.setOrgan(user.getOrgan());
		editTechnicTrain.setPersonNumber(technicTrain.getPersonNumber());
		editTechnicTrain.setRemark(technicTrain.getRemark());
		editTechnicTrain.setTrainingContent(technicTrain.getTrainingContent());
		editTechnicTrain.setTrainingDate(technicTrain.getTrainingDate());
		editTechnicTrain.setTrainingPlace(technicTrain.getTrainingPlace());
		editTechnicTrain.setTrainingTarget(technicTrain.getTrainingTarget());
		editTechnicTrain.setTrainingTitle(technicTrain.getTrainingTitle());
		String organs = request.getParameter("receivePersonIds1");
		String notorgans = request.getParameter("receivePersonIds2");
		technicTrainService.updatePresenceBatch(editTechnicTrain, organs,notorgans);

		// 附件处理
	        AttachmentContext.getInstance().getAttachmentService().updateHostId(editTechnicTrain.getTechnicTrainingId(), secAttach);
		addActionMessage("编辑培训记录成功。");
		needReload = true;
		return "list_action";
	}
	/**
	 * @description 删除技术培训
	 * @author 刘  舜 2010 02 02 12:46:30
	 * @modify 陈文聪 2010 8 17 02:30:04 修改注释格式.
	 */
	public String delete() {
		technicTrainService.deleteBatchIdList(this.getIds());
		addActionMessage("删除培训记录成功。");
		return "list_action";
	}

	/**
	 * @description 显示技术培训
	 * @author 刘  舜 2010 02 02 12:46:30
	 * @modify 陈文聪 2010 8 17 02:30:16 修改注释格式.
	 */
	public String view() {
		HttpServletRequest request = ServletActionContext.getRequest();
		technicTrain = technicTrainService.get(technicTrain.getTechnicTrainingId());
		Iterator<PresenceOrgan> po = technicTrain.getPresenceOrgans().iterator();
		String organs = "";
		String organNames = "";
		while (po.hasNext()) {
			PresenceOrgan oo = po.next();
			organs = organs + oo.getOrgan().getOrganId() + ",";
			organNames += oo.getOrgan().getOrganName() + ",";
		}
		request.setAttribute("organs", organs);
		request.setAttribute("organsName", organNames);
		Iterator<NotPresenceOrgan> npo = technicTrain.getNotPresenceOrgans().iterator();
		String notorgans = "";
		String notorganNames = "";
		while (npo.hasNext()) {
			NotPresenceOrgan noo = npo.next();
			notorgans = notorgans + noo.getOrgan().getOrganId() + ",";
			notorganNames += noo.getOrgan().getOrganName() + ",";
		}
		request.setAttribute("notorgans", notorgans);
		request.setAttribute("notorgansName", notorganNames);
		attachmentList= AttachmentContext.getInstance().getAttachmentService().getAttachmentsByHostId(technicTrain.getTechnicTrainingId());
		return "view";
	}
	public TechnicTrain getTechnicTrain() {
		return technicTrain;
	}

	public void setTechnicTrain(TechnicTrain technicTrain) {
		this.technicTrain = technicTrain;
	}

	public TechnicTrainDto getTechnicTrainDto() {
		return technicTrainDto;
	}

	public void setTechnicTrainDto(TechnicTrainDto technicTrainDto) {
		this.technicTrainDto = technicTrainDto;
	}

	public TechnicTrainService getTechnicTrainService() {
		return technicTrainService;
	}

	public void setTechnicTrainService(TechnicTrainService technicTrainService) {
		this.technicTrainService = technicTrainService;
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

	public String getShowType() {
		return showType;
	}

	public void setShowType(String showType) {
		this.showType = showType;
	}

	public String tabcard(){
		return "tabcard";
	}

	public Integer getTechnicTrainList_crd() {
		return technicTrainList_crd;
	}

	public void setTechnicTrainList_crd(Integer technicTrainListCrd) {
		technicTrainList_crd = technicTrainListCrd;
	}

	public Integer getTechnicTrainList_p() {
		return technicTrainList_p;
	}

	public boolean isNeedReload() {
                return needReload;
        }

        public void setNeedReload(boolean needReload) {
                this.needReload = needReload;
        }

        public void setDistrictService(DistrictService districtService) {
                this.districtService = districtService;
        }

        public void setTechnicTrainList_p(Integer technicTrainListP) {
		technicTrainList_p = technicTrainListP;
	}

	public OrganService getOrganService() {
		return organService;
	}

	public void setOrganService(OrganService organService) {
		this.organService = organService;
	}

	/**
	 * 返回district
	 * @return district
	 */
	public District getDistrict() {
		return district;
	}

	/**
	 * 设置district
	 * @param district district
	 */
	public void setDistrict(District district) {
		this.district = district;
	}

        public List<String> getReceivePersonIds1() {
                return receivePersonIds1;
        }

        public void setReceivePersonIds1(List<String> receivePersonIds1) {
                this.receivePersonIds1 = receivePersonIds1;
        }

        public List<String> getReceivePersonIds2() {
                return receivePersonIds2;
        }

        public void setReceivePersonIds2(List<String> receivePersonIds2) {
                this.receivePersonIds2 = receivePersonIds2;
        }
}
