package com.cdthgk.meetingcategory.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.cdthgk.bmp.core.action.BmpAction;
import com.cdthgk.bmp.statinfo.dto.QueryDto;
import com.cdthgk.common.lang.CollectionUtils;
import com.cdthgk.common.lang.StringUtils;
import com.cdthgk.meetingcategory.service.MeetingCategoryService;
import com.cdthgk.meetingcategory.service.MeetingService;
import com.cdthgk.meetingcategory.vo.AttendMeeting;
import com.cdthgk.meetingcategory.vo.Meeting;
import com.cdthgk.meetingcategory.vo.MeetingCategory;
import com.cdthgk.platform.attachment.context.AttachmentContext;
import com.cdthgk.platform.attachment.domain.Attachment;
import com.cdthgk.platform.dataValidate.service.DataValidateService;
import com.cdthgk.platform.dictionary.context.DictionaryContext;
import com.cdthgk.platform.dictionary.spi.DictionaryService;
import com.cdthgk.platform.district.domain.District;
import com.cdthgk.platform.organization.department.domain.Department;
import com.cdthgk.platform.organization.department.service.DepartmentService;
import com.cdthgk.platform.organization.organ.domain.Organ;
import com.cdthgk.platform.organization.userinfo.domain.UserInfo;
import com.cdthgk.platform.permission.user.domain.User;
import com.opensymphony.xwork2.ActionContext;

import ec.common.PageSortModel;

/**
 * @description TODO
 * @author huxj 2009 12 16 12:34:56
 * @modify 陈文聪 2010 8 18 07:30:43 修改注释格式
 */
@SuppressWarnings("all")
public class MeetingAction extends BmpAction {

        private static final long serialVersionUID = 1L;
        private MeetingService meetingService;
        private Meeting meeting;
        private AttendMeeting attendMeeting;
        private List<Meeting> allMeetingList;
        private MeetingCategoryService meetingCategoryService;
        private DepartmentService departmentService;
        private DataValidateService dataValidateService;
        private Attachment attachment;
        private List<Attachment> attachmentList;
        private String meetingTime;
        private Integer allMeetingList_crd;
        private Integer allMeetingList_p;
        private Boolean needReload = false;
        private List<String> readPersonIds=new ArrayList<String>();
        public Boolean getNeedReload() {
                return needReload;
        }

        public void setNeedReload(Boolean needReload) {
                this.needReload = needReload;
        }
        public String main() {
                return "main";
        }
        public String list() {
                String tableId = "allMeetingList";
                Map<String, Object> params = new HashMap<String, Object>();
                PageSortModel psm = new PageSortModel(getRequest(), tableId);
                List<MeetingCategory> meetingCategoryList = meetingCategoryService.getMeetingCategoryList();
                if (null!= meeting) {
                        if (meeting.getMeetingCategory().getMeetingCategoryId()!=null && !"".equals(meeting.getMeetingCategory().getMeetingCategoryId())) {
                                meeting.setMeetingCategory(meetingCategoryService.get(meeting.getMeetingCategory().getMeetingCategoryId()));
                        }
                }
                else{
                        meeting=new Meeting();
                        meeting.setMeetingCategory(meetingCategoryList.get(0));
                }

                this.putToRequest("allMeetingList", meetingService.getPageList(psm, meeting, params,getCurrentUser().getOrgan()));



                this.putToRequest("meetingCategoryList", meetingCategoryList);
                if (meeting.getMeetingCategory().getMeetingCategoryId().equals("ca82caeb45d9c78f0145d9ce75990002")) {
                        //会议（活动）记录2
                        return "list2";
                }else if(meeting.getMeetingCategory().getMeetingCategoryId().equals("ca82caeb45d9c78f0145d9cdc2280001"))
                {
                        //保密会议（活动）1
                        return "list1";
                }else if(meeting.getMeetingCategory().getMeetingCategoryId().equals("ca82caeb45d9c78f0145d9cd1cd00000"))
                {
                        //涉密会议（活动）
                        return "list";

                }else {
                        //其它
                        return "list0";
                }

        }
        public String export() {
                PageSortModel<MeetingCategory> psm = new PageSortModel<MeetingCategory>(
                                getRequest(), "allMeetingList");
                psm.setAll(true);
                // 查询
                Map<String, Object> params2 = new HashMap<String, Object>();
                List<MeetingCategory> meetingCategoryList = meetingCategoryService.getMeetingCategoryList();
                if (null!= meeting) {
                        if (meeting.getMeetingCategory().getMeetingCategoryId()!=null && !"".equals(meeting.getMeetingCategory().getMeetingCategoryId())) {
                                meeting.setMeetingCategory(meetingCategoryService.get(meeting.getMeetingCategory().getMeetingCategoryId()));
                        }
                }
                else{
                        meeting=new Meeting();
                        meeting.setMeetingCategory(meetingCategoryList.get(0));
                }

                Map<String, Object> params = new HashMap<String, Object>();

                //保密局--导出
                if(StringUtils.isNotEmpty(getRequest().getParameter("baomiju")))
                {

                        PageSortModel<Meeting> psm2 = new PageSortModel<Meeting>(getRequest(), "allMeetingList");
                        psm2.setAll(true);
                        District district;
                        String districtCode="";
                        String includeChild="";
                        HttpServletRequest r = this.getRequest();
                        if (null!=getRequest().getParameter("districtCode")&&!getRequest().getParameter("districtCode").equals("")) {
                                districtCode = r.getParameter("districtCode");
                                if (districtCode.indexOf("?")>=0) {
                                        districtCode=districtCode.substring(0 ,districtCode.indexOf("?"));
                                }
                        }else{
                                districtCode=getCurrentUser().getOrgan().getDistrict().getCode();
                        }
                        if (null!=getRequest().getParameter("includeChild")) {
                                includeChild = r.getParameter("includeChild");
                        }else{
                                //包含
                                includeChild="1";
                        }
                        if (StringUtils.isEmpty(districtCode)) {
                                district = getCurrentUser().getOrgan().getDistrict();
                        } else {
                                district = meetingService.get(districtCode, District.class);
                        }

                        params.put("allMeetingList", meetingService.listForSelect(psm2,meeting, districtCode,includeChild));
                }else{
                        //被导出的数据--本单位
                        params.put("allMeetingList", meetingService.getPageList(psm, meeting, params2,getCurrentUser().getOrgan()));
                }
                setResultData(params);
                addActionMessage("导出成功");
                //时间格式化器
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                params.put("dateFormat", dateFormat);
                if (meeting.getMeetingCategory().getMeetingCategoryId().equals("ca82caeb45d9c78f0145d9ce75990002")) {
                        //会议（活动）记录2
                        return "export2";
                }else if(meeting.getMeetingCategory().getMeetingCategoryId().equals("ca82caeb45d9c78f0145d9cdc2280001"))
                {

                        //保密会议（活动）1
                        return "export1";
                }else if(meeting.getMeetingCategory().getMeetingCategoryId().equals("ca82caeb45d9c78f0145d9cd1cd00000"))
                {
                        //数据字典工具
                        DictionaryService dictionary = DictionaryContext.getInstance().getDictionaryService();
                        params.put("dictionary", dictionary);
                        //涉密会议（活动）
                        return "export";

                }else {
                        //其它
                        return "export0";
                }
        }
        //分页显示泄密事件--保密局查询
        public String  selectListing()
        {
                District district;
                String districtCode="";
                String includeChild="";
                HttpServletRequest r = this.getRequest();
                if (null!=getRequest().getParameter("districtCode")&&!getRequest().getParameter("districtCode").equals("")) {
                        districtCode = r.getParameter("districtCode");
                        if (districtCode.indexOf("?")>=0) {
                                districtCode=districtCode.substring(0 ,districtCode.indexOf("?"));
                        }
                }else{
                        districtCode=getCurrentUser().getOrgan().getDistrict().getCode();
                }
                if (null!=getRequest().getParameter("includeChild")) {
                        includeChild = r.getParameter("includeChild");
                }else{
                        //包含
                        includeChild="1";
                }
                if (StringUtils.isEmpty(districtCode)) {
                        district = getCurrentUser().getOrgan().getDistrict();
                } else {
                        district = meetingService.get(districtCode, District.class);
                }
                PageSortModel<Meeting> psm = new PageSortModel<Meeting>(getRequest(), "allMeetingList");
                putToRequest("includeChild",includeChild);
                putToRequest("districtCode",districtCode);
                List<MeetingCategory> meetingCategoryList = meetingCategoryService.getMeetingCategoryList();
                if (null!= meeting) {
                        if (meeting.getMeetingCategory().getMeetingCategoryId()!=null && !"".equals(meeting.getMeetingCategory().getMeetingCategoryId())) {
                                meeting.setMeetingCategory(meetingCategoryService.get(meeting.getMeetingCategory().getMeetingCategoryId()));
                        }
                }
                else{
                        meeting=new Meeting();
                        meeting.setMeetingCategory(meetingCategoryList.get(0));
                }
                putToRequest("allMeetingList", meetingService.listForSelect(psm,meeting, districtCode,includeChild));
                this.putToRequest("meetingCategoryList", meetingCategoryList);
                this.putToRequest("district", district);
                if (meeting.getMeetingCategory().getMeetingCategoryId().equals("ca82caeb45d9c78f0145d9ce75990002")) {
                        //会议（活动）记录2
                        return "selectListing2";
                }else if(meeting.getMeetingCategory().getMeetingCategoryId().equals("ca82caeb45d9c78f0145d9cdc2280001"))
                {
                        //保密会议（活动）1
                        return "selectListing1";
                }else if(meeting.getMeetingCategory().getMeetingCategoryId().equals("ca82caeb45d9c78f0145d9cd1cd00000"))
                {
                        //涉密会议（活动）
                        return "selectListing";

                }else {
                        //其它
                        return "selectListing0";
                }
        }

        /**
         * @description 我发布的会议列表
         * @author 陈文聪 2010 2 25 12:34:56
         * @modify 陈文聪 2010 8 18 07:32:38 修改注释格式.
         */
        public String myList() {
                User user = getCurrentUser();
                String tableId = "allMeetingList";
                Map<String, Object> params = new HashMap<String, Object>();
                PageSortModel psm = new PageSortModel(getRequest(), tableId);
                String meetingCategoryId = this.getRequest().getParameter("meeting.meetingCategory.meetingCategoryId");

                if (null == meetingCategoryId || "".equals(meetingCategoryId)) {
                        params.put("meetingCategoryId", "1");
                } else {
                        params.put("meetingCategoryId", meetingCategoryId);
                }
                params.put("myList", user.getUserId());
                this.putToRequest("myList", user.getUserId());
                this.putToRequest("allMeetingList", meetingService.getPageList(psm, meeting, params,getCurrentUser().getOrgan()));

              /*  if (null != meeting && null != meeting.getHoldDepartment()
                                && null != meeting.getHoldDepartment().getDepartmentId()
                                && !"".equals(meeting.getHoldDepartment().getDepartmentId())) {
                        Department depart = departmentService.get(meeting.getHoldDepartment().getDepartmentId());
                        meeting.setHoldDepartment(depart);
                }*/
                return "mylist";
        }

        /**
         * @description 添加前
         * @author huxj 2009 12 16 12:34:56
         * @modify 陈文聪 2010 8 18 07:33:27 修改注释格式.
         */
        public String add() {
                List<MeetingCategory> meetingCategoryList = meetingCategoryService.getMeetingCategoryList();
                if(meetingCategoryList.size()==0){
                        this.putToRequest("msg", "msg");
                }
                this.putToRequest("meetingCategoryList", meetingCategoryList);
                this.putToRequest("myListFlag", this.getRequest().getParameter("myListAdd"));
                return ADD;
        }

        /**
         * @description 添加前
         * @author huxj 2009 12 16 12:34:56
         * @modify 陈文聪 2010 8 18 07:33:27 修改注释格式.
         */
        public String addbefore() {
                MeetingCategory meetingCategory = meetingCategoryService.get(this.getRequest().getParameter("meetingCategoryId"));
                if(meetingCategory==null){
                        this.putToRequest("msg", "msg");
                }
                this.putToRequest("meetingCategory", meetingCategory);

                if (meetingCategory!=null&&meetingCategory.getMeetingCategoryId().equals("ca82caeb45d9c78f0145d9ce75990002")) {
                        //会议（活动）记录2
                        return "addbefore2";
                }else if(meetingCategory!=null&&meetingCategory.getMeetingCategoryId().equals("ca82caeb45d9c78f0145d9cdc2280001"))
                {
                        //保密会议（活动）1
                        return "addbefore1";
                }else if(meetingCategory!=null&&meetingCategory.getMeetingCategoryId().equals("ca82caeb45d9c78f0145d9cd1cd00000"))
                {
                        //涉密会议（活动）
                        return "addbefore";

                }else {
                        //其它
                        return "addbefore0";
                }
        }

        /**
         * @description 添加
         * @author huxj 2009 12 16 12:34:56
         * @modify 陈文聪 2010 8 18 07:34:52 修改注释格式.
         */
        public String save() {
                User user = getCurrentUser();
                Organ organ = user.getOrgan();
                Department dept = user.getUserInfo().getDepartment();
                Date date = new Date();
                MeetingCategory mc = meetingCategoryService.get(meeting.getMeetingCategory().getMeetingCategoryId());

                meeting.setCreatePerson(user);
                meeting.setCreateTime(date);
                meeting.setOrgan(organ);
                meeting.setStatus(1);
                meeting.setMeetingCategory(mc);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                try {
                        meeting.setMeetingTime(sdf.parse(meetingTime));
                } catch (ParseException e) {
                        meeting.setMeetingTime(new Date());
                }

                meetingService.save(meeting);
                // 新增时添加附件 chenwc by 20100423
                AttachmentContext.getInstance().getAttachmentService().updateHostId(meeting.getMeetingId(),secAttach);
                String meetingCategoryId = mc.getMeetingCategoryId();
                this.putToRequest("meetingCategoryId", meetingCategoryId);
                this.putToRequest("meeting", meeting);
                addActionMessage("新增会议信息成功。");
                List<MeetingCategory> meetingCategoryList = meetingCategoryService.getMeetingCategoryList();
                this.putToRequest("meetingCategoryList", meetingCategoryList);

                if (null != this.getRequest().getParameter("myListFlag")
                                && !"".equals(this.getRequest().getParameter("myListFlag"))) {
                        // this.putToRequest("myListFlag", "myListFlag");
                        return "mylist_action";
                }
                return "mylist_action";
        }

        private List<String> secAttach;
        public List<String> getSecAttach() {
                return secAttach;
        }

        public void setSecAttach(List<String> secAttach) {
                this.secAttach = secAttach;
        }
        /**
         * @description TODO
         * @author huxj 2009 12 16 12:34:56
         * @modify 陈文聪 2010 8 18 07:35:08 修改注释格式.
         */
        public String edit() {
                meeting=meetingService.get(meeting.getMeetingId());
                attachmentList = AttachmentContext.getInstance().getAttachmentService().getAttachmentsByHostId(meeting.getMeetingId());
                if (StringUtils.isNotEmpty(meeting.getAttendUserInfos())){
                        List<UserInfo> userInfos = new ArrayList<UserInfo>();
                        String[]  strings=meeting.getAttendUserInfos().split(",");
                        for (int i = 0; i < strings.length; i++) {
                                String userInfoIdString=strings[i].trim();
                                UserInfo userInfo = meetingService.get(userInfoIdString, UserInfo.class);
                                userInfos.add(userInfo);
                        }
                        this.putToRequest("userInfos",userInfos);
                }
                if (meeting.getMeetingCategory().getMeetingCategoryId().equals("ca82caeb45d9c78f0145d9ce75990002")) {
                        //会议（活动）记录2
                        return "edit2";
                }else if(meeting.getMeetingCategory().getMeetingCategoryId().equals("ca82caeb45d9c78f0145d9cdc2280001"))
                {
                        //保密会议（活动）1
                        return "edit1";
                }else if(meeting.getMeetingCategory().getMeetingCategoryId().equals("ca82caeb45d9c78f0145d9cd1cd00000"))
                {
                        //涉密会议（活动）
                        return "edit";

                }else {
                        //其它
                        return "edit0";
                }
        }

        /**
         * @description 更新
         * @author huxj 2009 12 16 12:34:56
         * @modify 陈文聪 2010 8 18 07:35:30 修改注释格式.
         */
        public String update() {
                User user = getCurrentUser();
                Organ organ = user.getOrgan();
                Meeting m = meetingService.get(meeting.getMeetingId(), Meeting.class);
                m.setCreatePerson(user);
                m.setModifyTime(new Date());
                m.setModifyPerson(user);
                m.setOrgan(organ);
                m.setStatus(1);

                m.setMeetingName(meeting.getMeetingName());
                m.setMeetingTime(meeting.getMeetingTime());
                m.setAttendUserInfos(meeting.getAttendUserInfos());
                m.setContent(meeting.getContent());
                m.setMeasure(meeting.getMeasure());
                m.setMeetingName(meeting.getMeetingName());
                m.setPlace(meeting.getPlace());
                m.setPresenter(m.getPresenter());
                m.setRecorder(meeting.getRecorder());
                m.setSituation(meeting.getSituation());
                m.setSecrecyLevel(meeting.getSecrecyLevel());
                m.setScope(meeting.getScope());
                m.setMeetingCategory(meetingCategoryService.get(meeting.getMeetingCategory().getMeetingCategoryId()));
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                try {
                        m.setMeetingTime(sdf.parse(meetingTime));
                } catch (ParseException e) {
                        m.setMeetingTime(new Date());
                }

                meetingService.update(m);
                meeting=m;
                putToRequest("meeting", meeting);
                AttachmentContext.getInstance().getAttachmentService().updateHostId(meeting.getMeetingId(),secAttach);
                addActionMessage("编辑会议信息成功。");
                return "mylist_action";
        }

        /**
         * @description TODO
         * @author huxj 2009 12 16 12:34:56
         * @modify 陈文聪 2010 8 18 07:35:53 修改注释格式.
         */
        public String delete() {
                List<Meeting> entityList = new ArrayList<Meeting>();
                for (int i = 0; i < this.getIds().size(); i++) {
                        Meeting meeting2= meetingService.get(this.getIds().get(i));
                        meeting2.setStatus(0);
                        entityList.add(meeting2);
                }
                meetingService.updateBatch(entityList);
                addActionMessage("删除会议信息成功。");
                putToRequest("meeting", meeting);
                return "mylist_action";
        }

        /**
         * @description TODO
         * @author huxj 2009 12 16 12:34:56
         * @modify 陈文聪 2010 8 18 07:36:02 修改注释格式.
         */
        public String issue() {
                List<String> ids = this.getIds();
                for (String id : ids) {
                        System.out.println(id + "   ");
                        Meeting m = meetingService.get(id, Meeting.class);
                        m.setStatus(1);
                        meetingService.update(m);
                }
                return "mylist_action";
        }
        /**
         * @description TODO
         * @author huxj 2009 12 16 12:34:56
         * @modify 陈文聪 2010 8 18 07:36:46 修改注释格式.
         */
        public String view() {
                // 检查
                meeting=meetingService.get(meeting.getMeetingId());
                if(meeting==null || meeting.getMeetingId()==null || meeting.getMeetingId().equals("")){
                        this.addActionMessage("您所访问的资源Id不存在，请确认后重试。");
                        return "view0";
                }
                attachmentList = AttachmentContext.getInstance().getAttachmentService().getAttachmentsByHostId(meeting.getMeetingId());
                if (StringUtils.isNotEmpty(meeting.getAttendUserInfos())){
                        String[]  strings=meeting.getAttendUserInfos().split(",");
                        String userInfos ="";
                        for (int i = 0; i < strings.length; i++) {
                                String userInfoIdString=strings[i].trim();
                                UserInfo userInfo = meetingService.get(userInfoIdString, UserInfo.class);
                                if (i==strings.length-1) {
                                       userInfos+=userInfo.getName()+"。";
                                }else {
                                        userInfos+=userInfo.getName()+",";
                                }
                        }
                        meeting.setAttendUserInfoNames(userInfos);
                }
                if (meeting.getMeetingCategory().getMeetingCategoryId().equals("ca82caeb45d9c78f0145d9ce75990002")) {
                        //会议（活动）记录2
                        return "view2";
                }else if(meeting.getMeetingCategory().getMeetingCategoryId().equals("ca82caeb45d9c78f0145d9cdc2280001"))
                {
                        //保密会议（活动）1
                        return "view1";
                }else if(meeting.getMeetingCategory().getMeetingCategoryId().equals("ca82caeb45d9c78f0145d9cd1cd00000"))
                {
                        //涉密会议（活动）
                        return "view";

                }else {
                        //其它
                        return "view0";
                }
        }

        /**
         * @description TODO
         * @author huxj 2009 12 16 12:34:56
         * @modify 陈文聪 2010 8 18 07:36:57 修改注释格式.
         */
        public String openDialog() {
                return "openDialog";
        }

        /**
         * @description TODO
         * @author huxj 2009 12 16 12:34:56
         * @modify 陈文聪 2010 8 18 07:37:03 修改注释格式.
         */
        public String openList() {
                String id = "allMeetingList";
                PageSortModel psm = new PageSortModel(getRequest(), id);
                ActionContext.getContext().put("allMeetingList",
                                meetingService.getList(psm, meeting));
                return "openList";
        }

        /**
	   	 *
	   	 * <p>
	   	 * 保密工作信息总览获取数据
	   	 * </p>
	   	 * <p>
	   	 * 创建人 陶汇源  创建时间  2014-5-7 - 下午2:28:35
	   	 * </p>
	   	 * <blockquote>
	   	 * <h4>历史修改记录</h4>
	   	 * <ul>
	   	 * <li>修改人 修改时间 修改描述
	   	 * </ul>
	   	 * </blockquote>
	   	 * @return
	   	 */
	   	public String organIndex(){
	   		// TODO 暂无考虑时间查询，时间设计到业务模块数据变动历史情况，需要先调整业务模块
	   		String organId = getRequest().getParameter("organId").toString();
	   		QueryDto queryDto = new QueryDto();
	   		queryDto.setYear(Integer.parseInt(getRequest().getParameter("queryDto.year").toString()));
	   		queryDto.setMonth(Integer.parseInt(getRequest().getParameter("queryDto.month").toString()));
	   		Map<String, Object> params = new HashMap<String, Object>();
	   		String meetingCategoryId = getRequest().getParameter("meetingCategoryId").toString();
	   		meeting = new Meeting();
		    if (meetingCategoryId.equals("ca82caeb45d9c78f0145d9ce75990002")) {
	            //会议（活动）记录 保密工作领导小组会议记录
		    	meeting.setMeetingCategory(meetingService.get("ca82caeb45d9c78f0145d9ce75990002", MeetingCategory.class));
		    	List<Meeting> meetingList1 = meetingService.getPageList(null, meeting, params, meetingService.get(organId, Organ.class));
		    	for (Meeting meeting : meetingList1) {
		    		String attendUserInfoName = "";
		    		String[] attendUserInfoIds = meeting.getAttendUserInfos().split(",");
                    for (int i = 0; i < attendUserInfoIds.length; i++) {
                            String userInfoId = attendUserInfoIds[i].trim();
                            UserInfo userInfo = meetingService.get(userInfoId, UserInfo.class);
                            attendUserInfoName += userInfo.getName() + ",";
                    }
		    		//保密参与情况字段用来存放出席人员名称
		    		meeting.setAttendUserInfoNames(attendUserInfoName);
				}
		    	putToRequest("meetingList1", meetingList1);
	            return "organIndex1";
		    } else if(meetingCategoryId.equals("ca82caeb45d9c78f0145d9cdc2280001")){
	            //保密会议（活动）
		    	meeting.setMeetingCategory(meetingService.get("ca82caeb45d9c78f0145d9cdc2280001", MeetingCategory.class));
		    	List<Meeting> meetingList2 = meetingService.getPageList(null, meeting, params, meetingService.get(organId, Organ.class));
		    	for (Meeting meeting : meetingList2) {
		    		String attendUserInfoName = "";
		    		String[] attendUserInfoIds = meeting.getAttendUserInfos().split(",");
                    for (int i = 0; i < attendUserInfoIds.length; i++) {
                            String userInfoId = attendUserInfoIds[i].trim();
                            UserInfo userInfo = meetingService.get(userInfoId, UserInfo.class);
                            attendUserInfoName += userInfo.getName() + ",";
                    }
		    		//保密参与情况字段用来存放出席人员名称
		    		meeting.setAttendUserInfoNames(attendUserInfoName);
				}
		    	putToRequest("meetingList2", meetingList2);
	            return "organIndex2";
		    }else if(meetingCategoryId.equals("ca82caeb45d9c78f0145d9cd1cd00000")){
	            //涉密会议（活动）
		    	meeting.setMeetingCategory(meetingService.get("ca82caeb45d9c78f0145d9cd1cd00000", MeetingCategory.class));
		   		putToRequest("meetingList3", meetingService.getPageList(null, meeting, params, meetingService.get(organId, Organ.class)));
		        return "organIndex3";
		    }else {
	            //其它
	            return "list0";
		    }
	   	}
	   	public String dataValidate(){
	                Map<String, Object> resultData = new HashMap<String, Object>();
	                // ID值为bm_business_module中的ID

	                String msg = "<span style='color:green'>已填写</span>";
	                String meetingCategoryId = getRequest().getParameter("meetingCategoryId").toString();
	                meeting = new Meeting();
	                Map<String, Object> params = new HashMap<String, Object>();
	                if (meetingCategoryId.equals("ca82caeb45d9c78f0145d9ce75990002")) {
	                     resultData.put("id", "20");
                            //会议（活动）记录 保密工作领导小组会议记录
                            meeting.setMeetingCategory(meetingService.get("ca82caeb45d9c78f0145d9ce75990002", MeetingCategory.class));
                            List<Meeting> meetingList1 = meetingService.getPageList(null, meeting, params,getCurrentUser().getOrgan());
                            msg = dataValidateService.validateData("会议（活动）记录", meetingList1, "20");
                            resultData.put("msg", msg);
                            setResultData(resultData);
                            return JSON;
                        } else if(meetingCategoryId.equals("ca82caeb45d9c78f0145d9cdc2280001")){
                             resultData.put("id", "21");
                            //保密会议（活动）
                            meeting.setMeetingCategory(meetingService.get("ca82caeb45d9c78f0145d9cdc2280001", MeetingCategory.class));
                            List<Meeting> meetingList2 = meetingService.getPageList(null, meeting, params,getCurrentUser().getOrgan());
                            msg = dataValidateService.validateData("保密会议（活动）", meetingList2, "21");
                            resultData.put("msg", msg);
                            setResultData(resultData);
                            return JSON;
                        }else if(meetingCategoryId.equals("ca82caeb45d9c78f0145d9cd1cd00000")){
                                resultData.put("id", "22");
                        //涉密会议（活动）
                            meeting.setMeetingCategory(meetingService.get("ca82caeb45d9c78f0145d9cd1cd00000", MeetingCategory.class));
                            List<Meeting> meetingList3 = meetingService.getPageList(null, meeting, params,getCurrentUser().getOrgan());
                            msg = dataValidateService.validateData("涉密会议（活动）", meetingList3, "22");
                            resultData.put("msg", msg);
                            setResultData(resultData);
                            return JSON;
                        }else {
                                //其它
                                msg = "<span style='color:red'>【ID错误！】</span>";
                                resultData.put("msg", msg);
                                setResultData(resultData);
                                return JSON;
                        }



	        }

        // geter & seter

        public List<Meeting> getAllMeetingList() {
                return allMeetingList;
        }

        public void setAllMeetingList(List<Meeting> allMeetingList) {
                this.allMeetingList = allMeetingList;
        }

        public MeetingService getMeetingService() {
                return meetingService;
        }

        public void setMeetingService(MeetingService meetingService) {
                this.meetingService = meetingService;
        }

        public Meeting getMeeting() {
                return meeting;
        }

        public void setMeeting(Meeting meeting) {
                this.meeting = meeting;
        }

        public AttendMeeting getAttendMeeting() {
                return attendMeeting;
        }

        public void setAttendMeeting(AttendMeeting attendMeeting) {
                this.attendMeeting = attendMeeting;
        }

        public MeetingCategoryService getMeetingCategoryService() {
                return meetingCategoryService;
        }

        public void setMeetingCategoryService(
                        MeetingCategoryService meetingCategoryService) {
                this.meetingCategoryService = meetingCategoryService;
        }

        public DepartmentService getDepartmentService() {
                return departmentService;
        }

        /**
         * @return 返回 readPersonIds
         */
        public List<String> getReadPersonIds() {
                return readPersonIds;
        }

        /**
         * @param readPersonIds 设置 readPersonIds
         */
        public void setReadPersonIds(List<String> readPersonIds) {
                this.readPersonIds = readPersonIds;
        }

        public void setDepartmentService(DepartmentService departmentService) {
                this.departmentService = departmentService;
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

        /**
         * @return 返回meetingTime
         */
        public String getMeetingTime() {
                return meetingTime;
        }

        /**
         * @param meetingTime 设置meetingTime
         */
        public void setMeetingTime(String meetingTime) {
                this.meetingTime = meetingTime;
        }

        public Integer getAllMeetingList_crd() {
                return allMeetingList_crd;
        }

        public void setAllMeetingList_crd(Integer allMeetingListCrd) {
                allMeetingList_crd = allMeetingListCrd;
        }

        public Integer getAllMeetingList_p() {
                return allMeetingList_p;
        }

        public void setAllMeetingList_p(Integer allMeetingListP) {
                allMeetingList_p = allMeetingListP;
        }

        /**
         * @return the dataValidateService
         */
        public DataValidateService getDataValidateService() {
                return dataValidateService;
        }

        /**
         * @param dataValidateService the dataValidateService to set
         */
        public void setDataValidateService(DataValidateService dataValidateService) {
                this.dataValidateService = dataValidateService;
        }


}
