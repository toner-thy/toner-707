<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>

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
    
    <script type="text/javascript">  
	   	function select(id,name){
			var arg = window.dialogArguments;
			var dc = arg.window.document;
			dc.getElementById(arg.text).value = name;
			dc.getElementById(arg.hidden).value = id;
			window.close();
		}
	</script>
  </head>
  
  <body>
   <div class="body_content">		
		<div class="edit_content">
			<div class="edit_title">
				会议列表
			</div>
			<!-- 查询 -->
			<div class="edit_query_bar">
				<s:form action="meeting_list" id="meeting_release_List_form" theme="simple">
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
		<c:if test="${allMeetingList!=null}">
			<div class="edit_content">
				<div class="edit_title">
					会议发布列表
			</div>
				<ec:table items="allMeetingList" var="aml" tableId="allMeetingList" border="0"
					action="${pageContext.request.contextPath}/meeting_openList.action"
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
						<ec:column property="null" title="选 择" sortable="false">
							<button onclick="select('${aml.meetingId}','${aml.meetingName}')">选 择</button>
						</ec:column>
					</ec:row>
				</ec:table>
			</div>
		</c:if>
		<!-- 判断没有数据时给出提示 -->
		<c:if test="${allMeetingList=='[]'}">
			<div class="edit_content">
				<center><h3><font color="red">还没有发布会议！</font></h3></center>
			</div>
		</c:if>
	</div>
  </body>
</html>