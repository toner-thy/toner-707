<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
	<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/mootools/notimoo/notimoo-v1.1.js"></script>
  	<link href="${pageContext.request.contextPath}/platform/theme/default/notimoo/notimoo-v1.1.css" type="text/css" rel="stylesheet" />		
  </head>
  
  <body>
    <ap:step/>
	<div class="body_content">
		<s:actionmessage theme="messages"/>				
		<div class="edit_content">
			<div class="edit_title">
				会议列表
			</div>
			<!-- 查询 -->
			<div class="edit_query_bar">
				<s:form action="meeting_meetingList" id="meeting_release_List_form" theme="simple">
					<label for="meeting.meetingName">会议名称:</label>
					<input type="text" name="meeting.meetingName" id="meeting.meetingName" value="${meeting.meetingName}"/>
						<span class="query_button_bar">
							<span class="pop_button_bar">
								<a href="javascript:document.getElementById('meeting_release_List_form').submit();" class="pop_button"><span>查询</span></a>						
							</span>
						</span>
				</s:form>
			</div>
		</div>
	<br/>
		<!-- list -->
		<div class="edit_content">
			<div class="edit_title">
				会议发布列表
			<span class="button_bar">
				<ap:operationbutton />
			</span>
		</div>
			<s:if test="#request.allMeetingList.size>0">
				<ec:table items="allMeetingList" var="aml" tableId="allMeetingList" border="0"
					action="${pageContext.request.contextPath}/meeting_meettingList.action"
					imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
					width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
					filterable="false" autoIncludeParameters="true" sortable="false">
					<ec:row>
						<ec:column property="meetingId" alias="meetingId_checkbox" cell="checkbox" headerCell="checkbox"/>
						<ec:column property="meetingName" title="会议主题"/>
						<ec:column property="meetingTime" title="会议举行时间" parse="yyyy-MM-dd" cell="date"/>
						<ec:column property="holdOrgan.organName" title="会议发起单位"/>
						<ec:column property="holdDepartment.departmentName"  title="会议发起部门"/>
						<ec:column property="null" title="会议描述">
							<a href="javascript:view('${aml.meetingId }');">${fn:substring(aml.meetingDescription,0,20) }</a>
						</ec:column>
						<ec:column property="null" title="发布资料"><a title="发布资料" href="javascript:uploadResource('${aml.meetingId}')"><img src="${pageContext.request.contextPath}/platform/theme/default/images/frame/edit.gif"/></a></ec:column>
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
	//详情
	function view(id){
		window.showModalDialog("meeting_viewMeeting.action?meeting.meetingId="+id,window,"dialogWidth:600px;dialogHeight:400px;");
	}

	//上传
	function uploadResource(id){
		window.location.href="meeting_attendMeetingList.action?meeting.meetingId="+id;
	}
	
	//新增
	function doAdd(action){
		window.location.href=action;
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
		window.location.href=action+"?meeting.meetingId="+items[0].value	
	}
	
	//删除
	function doDel(action){
		if(window.confirm("确定删除吗？")){
			var items = EcTable.getCheckedItems();
			if(items.length==0){
				alert("请选择一项！");
				return;
			}
			var ids = "?";
			items.each(function(item){
				ids += "ids=" + item.value + "&";
			});
			window.location.href=action+ids;
		}
	}
</script>