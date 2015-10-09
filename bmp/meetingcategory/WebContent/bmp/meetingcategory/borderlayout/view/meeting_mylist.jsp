<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<%@ taglib tagdir="/WEB-INF/tags/dictionary" prefix="dictionary" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
  <head>
    <title>会议列表</title> 

	 <!-- css -->
    <link href="${pageContext.request.contextPath}/platform/theme/default/css/ec.css" type="text/css" rel="stylesheet" />
	<link href="${pageContext.request.contextPath}/platform/theme/default/css/style.css" type="text/css" rel="stylesheet" />
	<link href="${pageContext.request.contextPath}/platform/theme/default/pop_button/pop_button.css" type="text/css" rel="stylesheet" />
	
	<!-- js -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/pub.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/utils.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/mootools/mootools-core.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/mootools/ectable/EcTable.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/mootools/formcheck/formcheck.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/FCKeditor/fckeditor.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/utils.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/mootools/notimoo/notimoo-v1.1.js"></script>
  	<link href="${pageContext.request.contextPath}/platform/theme/default/notimoo/notimoo-v1.1.css" type="text/css" rel="stylesheet" />
   
    <script type="text/javascript">
    	window.addEvent('domready', function(){
    		//弹出框选择部门
    		Util.selectDepartment("meeting.holdDepartment.departmentName","meeting.holdDepartment.departmentName","meeting.holdDepartment.departmentId");
    	});
	</script>
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
					<s:form action="meeting_myList" id="meeting_release_myList_form" theme="simple">
						<div>
							<table width="100%" border="0" class="edit_table" id="edit-0">
								<tr height="36px;">
									<td align="right" width="10%">会议主题：</td>
									<td colspan="4" width="85%"><input type="text" name="meeting.meetingName" id="meeting.meetingName" value="${meeting.meetingName}" size="65"/></td>
								</tr>	
								
								<tr height="36px;">
									<td align="right" width="10%">会议举行时间：</td>
									<td width="25%">
											<input type="text" id="meeting.meetingTime" name="meeting.meetingTime" value="<fmt:formatDate value='${meeting.meetingTime }' pattern='yyyy-MM-dd'/>"title="举行时间" class="Wdate " onFocus="WdatePicker()"readonly="readonly" />
									</td>
											<input type="hidden" name="meeting.meetingCategory.meetingCategoryId" value="${meeting.meetingCategory.meetingCategoryId}">
									<td align="right" width="10%">会议发起部门：</td>
									<td width="55%" colspan="2">
										<input id="meeting.holdDepartment.departmentName" type="text"  class="validate['required']" readonly="readonly" value="${meeting.holdDepartment.departmentName}"/>
										<input name="meeting.holdDepartment.departmentId" id="meeting.holdDepartment.departmentId" type="hidden" />
									</td>
								</tr>
								
								<tr height="36px;">
								 	<td colspan="5">
									    <div align="center">
											<span class="query_button_bar">
												<span class="pop_button_bar">
													<a href="javascript:document.getElementById('meeting_release_myList_form').submit();" class="pop_button"><span>查询</span></a>
												</span>
											</span>
										</div>
								 	</td>
								</tr>
							</table>
						</div>			
					</s:form>
				</div>
				<!-- list -->
				<div class="edit_content" style="margin-top: 10px;">
					<div class="edit_title">
						会议发布列表
						<span class="button_bar">
							<ap:operationbutton />
						</span>
					</div>
					<s:if test="#request.allMeetingList.size>0">
						<ec:table items="allMeetingList" var="aml" tableId="allMeetingList" border="0"
							action="${pageContext.request.contextPath}/meeting_list.action"
							imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
							width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
							filterable="false" autoIncludeParameters="true" sortable="false">
							<ec:row>
								<ec:column property="meetingId" alias="meetingId_checkbox" cell="checkbox" headerCell="checkbox" width="5%"/>
								<ec:column property="meetingName" title="会议主题" width="18%" style="line-height:20px;padding:5px;">
									<div title="${aml.meetingName}">${fn:substring(aml.meetingName,0,15)}<c:if test="${fn:length(aml.meetingName)>15}">...</c:if></div>
								</ec:column>
								<ec:column property="meetingTime" title="会议举行时间" parse="yyyy-MM-dd" cell="date" width="13%"/>
								<ec:column property="holdOrgan.organName" title="会议发起单位" width="15%"/>
								<ec:column property="holdDepartment.departmentName"  title="会议发起部门" width="15%"/>
								
								<ec:column property="meetingCategory.categoryName"  title="所属分类" width="8%">
									${fn:substring(aml.meetingCategory.categoryName,0,8) }<c:if test="${fn:length(aml.meetingName)>8}">...</c:if>
								</ec:column>
								<ec:column property="status" title="发布状态" width="7%">
									<dictionary:text fieldCode="isuse" tableCode="meeting" optionValue="${aml.status}"></dictionary:text>
								</ec:column>
								<ec:column property="null" title="办会资料管理" width="12%">
									<a href="###" onclick="attendMeeting('${aml.meetingId}')"><img src="${pageContext.request.contextPath}/platform/theme/default/images/main/menu_xiaobio_7.gif" title="办会资料管理"/></a>
								</ec:column>
								<ec:column property="null" title="详情" width="1%">
									<a href="javascript:view('${aml.meetingId}')"><img src="${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif"/></a>
								</ec:column>
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
		var date = new Date();
		window.showModalDialog("meeting_view.action?meeting.meetingId="+id+"&date="+date.getTime(),window,"dialogWidth:600px;dialogHeight:320px;status:no;help:no");
	}

	//上传
	function uploadResource(id){
		window.location.href="meeting_attendMeetingList.action?meeting.meetingId="+id;
	}
	
	//新增
	function doAdd(action){
		window.location.href=action+"?meetingCategoryId=${meeting.meetingCategory.meetingCategoryId}"+"&myListAdd=1";
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
		var items = EcTable.getCheckedItems();
		if(items.length==0){
			alert("请选择一项！");
			return;
		}
		if(window.confirm("确定删除吗？")){
			var ids = "?";
			items.each(function(item){
				ids += "ids=" + item.value + "&";
			});
			window.location.href=action+ids;
		}
	}
	
	//发布
	function doIssue(action){
		var items = EcTable.getCheckedItems();
		if(items.length==0){
			alert("请选择要发布的项！");
			return;
		}
		if(window.confirm("确定要发布吗？")){
			var ids = "?";
			items.each(function(item){
				ids += "ids=" + item.value + "&";
			});
			window.parent.location.href=action+ids;
		}
  	}
	
	function attendMeeting(id){
		window.location.href="attendMeeting_myList.action?attendMeeting.meeting.meetingId="+id;
	}
</script>