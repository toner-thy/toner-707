<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
  <head>
    <title>杂志征订</title>

	<!-- css -->
    <link href="${pageContext.request.contextPath}/platform/theme/default/css/ec.css" type="text/css" rel="stylesheet" />
	<link href="${pageContext.request.contextPath}/platform/theme/default/css/style.css" type="text/css" rel="stylesheet" />
	<link href="${pageContext.request.contextPath}/platform/theme/default/pop_button/pop_button.css" type="text/css" rel="stylesheet" />
	
	<!-- js -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/pub.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/utils.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/mootools/mootools-core.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/mootools/ectable/EcTable.js"></script>		
    <script type="text/javascript"></script>
  </head>
 
  <body>
	  <ap:step/>
		<div class="body_content">		
			<div class="edit_content">
				<div class="edit_title"> 
					已经上传资料列表
				</div>
				<div class="edit_query_bar">
					<s:form action="meeting_attendMeetingList" id="meeting_attendMeetingList_form" theme="simple">
						<label for="attendMeeting.attendMeetingName">资料名称:</label>
						<input type="text" name="attendMeeting.attendMeetingName" id="attendMeeting.attendMeetingName" value="${attendMeeting.attendMeetingName}"/>
							<span class="query_button_bar">
								<span class="pop_button_bar">
									<a href="javascript:document.getElementById('meeting_attendMeetingList_form').submit();" class="pop_button"><span>查询</span></a>						
								</span>
							</span>
						<input type="hidden" name="meeting.meetingId" value="${meeting.meetingId }"/>
					</s:form>
				</div>
			</div>
			<br/>
			
			<div class="edit_content">
				<div class="edit_title">
					 已添加资料列表
					<span class="button_bar">
						<span class="pop_button_bar">
							<a href="###" onclick="doAdd();" class="pop_button"><span>添加</span></a>
							<a href="###" onclick="doEdit();" class="pop_button"><span>修改</span></a>
							<a href="###" onclick="doDel();" class="pop_button"><span>删除</span></a>
							<a href="###" onclick="doBack();" class="pop_button"><span>返回</span></a>
						</span>
					</span>
			</div>
				<!-- list -->
				<s:if test="#request.allattendMeetingList.size>0">
					<ec:table items="allattendMeetingList" var="sc" tableId="allattendMeetingList" border="0"
						action="${pageContext.request.contextPath}/meeting_attendMeetingList.action"
						imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
						width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
						filterable="false" autoIncludeParameters="true" sortable="false">
						<ec:row>
							<ec:column property="checkbox" alias="attendMeetingId_checkbox" headerCell="checkbox">
								<input type="checkbox" value="${sc.attendMeetingId}" name="id" class="row_checkbox">
							</ec:column>
							<ec:column property="attendMeetingName" title="资料名称"/>
							<ec:column property="organ.organName" title="资料上传单位"/>
							<ec:column property="department.departmentName" title="资料上传部门"/>
							<ec:column property="userInfo.name"  title="资料上传用户"/>
							<ec:column property="commitInfoTime" title="资料上传时间"/>
							<ec:column property="null" title="显示详情"><a title="详细信息" href="javascript:view('${sc.attendMeetingId}')"><img src="${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif"/></a></ec:column>
						</ec:row>
					</ec:table>
				</s:if>
				<!-- 没有数据时给出提示 -->
				<s:else>
					<styles:nolist/>
				</s:else>
			</div>
	   </div>
  </body>
</html>
	<!-- js -->
	<script language="javascript">
		//新增
		function doAdd(action){
			window.location.href="meeting_showAddAttendMeeting.action?meeting.meetingId=${meeting.meetingId}";
		}
		
		//编辑
		function doEdit(action){
			var items = EcTable.getCheckedItems();
			if(items.length==0){
				alert("请选择一项！");
				return;
			}else if(items.length>1){
				alert("请选择一项！");
				return;
			}
			window.location.href="meeting_showEditAttendMeeting.action?meeting.meetingId=${meeting.meetingId}&attendMeeting.attendMeetingId="+items[0].value;
		}
		
		//删除
		function doDel(action){
			if(window.confirm("确定删除吗？")){
				var items = EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择一项！");
					return;
				}
				var ids = "";
				items.each(function(item){
					ids += "ids=" + item.value + "&";
				});
				window.location.href="meeting_deleteAttendMeeting.action?meeting.meetingId=${meeting.meetingId}&"+ids;
			}
		}
		
		//返回
		function doBack(){
			window.location.href="meeting_meetingList.action";
		}
	</script>