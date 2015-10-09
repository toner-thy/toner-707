package com.cdthgk.meetingcategory.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdthgk.bmp.core.action.BmpAction;
import com.cdthgk.meetingcategory.service.MeetingCategoryService;
import com.cdthgk.meetingcategory.service.MeetingService;
import com.cdthgk.meetingcategory.vo.Meeting;
import com.cdthgk.meetingcategory.vo.MeetingCategory;
import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.permission.user.domain.User;

import ec.common.PageSortModel;

/**
 * @description 会议类别管理
 * @author 陈文聪 2010 2 25 12:34:56
 * @modify 陈文聪 2010 8 18 07:17:50 修改注释格式
 */
@SuppressWarnings("all")
public class MeetingCategoryAction extends BmpAction {

	// Fields

	private static final long serialVersionUID = 1L;
	private MeetingService meetingService;
	private MeetingCategory meetingCategory;
	private MeetingCategoryService meetingCategoryService;
	private Integer meetingCategoryList_crd;
	private Integer meetingCategoryList_p;
	private List<Meeting> meetingList;
	// methods
        private boolean needReload=false;

	public boolean isNeedReload() {
                return needReload;
        }

        public void setNeedReload(boolean needReload) {
                this.needReload = needReload;
        }

        /**
	 * @description 会议分类主入口
	 * @author 陈文聪 2010 2 25 12:34:56陈文聪 2010 2 25 12:34:56
	 * @modify 陈文聪 2010 8 18 07:18:15 修改注释格式.
	 */
	public String main(){
		return "main";
	}

	/**
	 * @description 会议分类列表
	 * @author 陈文聪 2010 2 25 12:34:56陈文聪 2010 2 25 12:34:56
	 * @modify 陈文聪 2010 8 18 07:18:24 修改注释格式.
	 */
	public String list(){
		String tableId = "templetTypeList";
		PageSortModel psm = new PageSortModel(getRequest(),tableId);
		Map<String,Object> params = new HashMap<String,Object>();
		//默认选中根节点
		if(null == meetingCategory){
			params.put("meetingCategoryId", "1");
		}else{
			params.put("meetingCategoryId", meetingCategory.getMeetingCategoryId());
		}
		meetingCategory = meetingCategoryService.get((String)params.get("meetingCategoryId"));
		putToRequest("parentMeetingCategory", meetingCategory);
		List<MeetingCategory> meetingCategoryList = meetingCategoryService.getPageList(psm, params);
		putToRequest("meetingCategoryList", meetingCategoryList);
		return "list";
	}

	/**
	 * @description TODO
	 * @author 陈文聪 2010 2 25 12:34:56陈文聪 2010 2 25 12:34:56
	 * @modify 陈文聪 2010 8 18 07:19:29 修改注释格式.
	 */
	/*public String tree(){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("organId", getCurrentUser().getUserInfo().getOrgan().getOrganId());
		List<Map<String,Object>> districtTree = meetingCategoryService.getTree(params);
		setOutData(districtTree);
		return "json";
	}
	*/
	  public String tree(){
	                Map<String,Object> params = new HashMap<String,Object>();
	                params.put("organId", getCurrentUser().getUserInfo().getOrgan().getOrganId());
	                List<Map<String,Object>> districtTree = meetingCategoryService.getTree(params);
	                setResultData(districtTree);
	                return "json";
	        }

	/**
	 * @description 新增之前
	 * @author 陈文聪 2010 2 25 12:34:56陈文聪 2010 2 25 12:34:56
	 * @modify 陈文聪 2010 8 18 07:19:40 修改注释格式.
	 */
	@SuppressWarnings("unchecked")
	public String addBefore(){
		return "add";
	}

	/**
	 * @description 新增
	 * @author 陈文聪 2010 2 25 12:34:56陈文聪 2010 2 25 12:34:56
	 * @modify 陈文聪 2010 8 18 07:21:39 修改注释格式.
	 */
	public String add(){
		User user = getCurrentUser();
		//得到当前用户的单位
		Organ organ = user.getOrgan();
		Department dept = user.getUserInfo().getDepartment();
		Date date  = new Date();

		meetingCategory.setDepartment(dept);
		meetingCategory.setOrgan(organ);
		meetingCategory.setCreatePerson(user);
		meetingCategory.setCreateTime(date);

		meetingCategoryService.save(meetingCategory);
		needReload = true;
		addActionMessage("新增会议分类成功。");
		return "list_action";
	}

	/**
	 * @description 编辑之前
	 * @author 陈文聪 2010 2 25 12:34:56陈文聪 2010 2 25 12:34:56
	 * @modify 陈文聪 2010 8 18 07:21:50 修改注释格式.
	 */
	@SuppressWarnings("unchecked")
	public String editBefore(){
		meetingCategory = meetingCategoryService.get(meetingCategory.getMeetingCategoryId());

		return "edit";
	}

	/**
	 * @description 编辑
	 * @author 陈文聪 2010 2 25 12:34:56陈文聪 2010 2 25 12:34:56
	 * @modify 陈文聪 2010 8 18 07:22:00 修改注释格式.
	 */
	public String edit(){
		MeetingCategory mc = meetingCategoryService.get(meetingCategory.getMeetingCategoryId());
		User user = getCurrentUser();
		//得到当前用户的单位
		Date date  = new Date();
		mc.setModifyPerson(user);
		mc.setModifyTime(date);
		mc.setCategoryName(meetingCategory.getCategoryName());
		mc.setCategoryDesc(meetingCategory.getCategoryDesc());
		mc.setOrderNo(meetingCategory.getOrderNo());

		meetingCategoryService.update(mc);
		needReload = true;
		addActionMessage("编辑会议分类成功。");
		return "list_action";
	}

	/**
	 * @description TODO
	 * @author 陈文聪 2010 2 25 12:34:56陈文聪 2010 2 25 12:34:56
	 * @modify 陈文聪 2010 8 18 07:22:15 修改注释格式.
	 */
	public String delete(){
		// 检查当前会议分类下面是否有会议信息
		List<String> ids = this.getIds();
		for (String id : ids) {
			meetingCategory = meetingCategoryService.get(id);
			meetingList = (List<Meeting>) meetingService.getList("from Meeting m where m.meetingCategory.meetingCategoryId = '" + meetingCategory.getMeetingCategoryId() + "'", 0, 10000, null);
			if(meetingList!=null && meetingList.size()>0){
				this.addActionMessage("当前选择会议分类中"+ meetingCategory.getCategoryName() +"下有" + meetingList.size() + "条会议信息，请先删除相关会议信息后重试。");

				return redirectActionResult("list");
			}
			//meetingCategoryService.delete(id);
		}
               meetingCategoryService.deleteBatchIdList(ids);

                addActionMessage("删除会议分类成功。");
		return "list_action";

	}

	/**
	 * @description TODO
	 * @author 陈文聪 2010 2 25 12:34:56陈文聪 2010 2 25 12:34:56
	 * @modify 陈文聪 2010 8 18 07:22:29 修改注释格式.
	 */
	public String view(){
		meetingCategory = meetingCategoryService.get(meetingCategory.getMeetingCategoryId());
		return "view";
	}

	// geter & seter

	public MeetingService getMeetingService() {
		return meetingService;
	}

	public void setMeetingService(MeetingService meetingService) {
		this.meetingService = meetingService;
	}

	public MeetingCategory getMeetingCategory() {
		return meetingCategory;
	}

	public void setMeetingCategory(MeetingCategory meetingCategory) {
		this.meetingCategory = meetingCategory;
	}

	public MeetingCategoryService getMeetingCategoryService() {
		return meetingCategoryService;
	}

	public void setMeetingCategoryService(
			MeetingCategoryService meetingCategoryService) {
		this.meetingCategoryService = meetingCategoryService;
	}

	public Integer getMeetingCategoryList_crd() {
		return meetingCategoryList_crd;
	}

	public void setMeetingCategoryList_crd(Integer meetingCategoryListCrd) {
		meetingCategoryList_crd = meetingCategoryListCrd;
	}

	public Integer getMeetingCategoryList_p() {
		return meetingCategoryList_p;
	}

	public void setMeetingCategoryList_p(Integer meetingCategoryListP) {
		meetingCategoryList_p = meetingCategoryListP;
	}

}
