<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<%@ taglib tagdir="/WEB-INF/tags/dictionary" prefix="dictionary" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>杂志征订</title>

	<!-- css -->
	<link href="${ctx}/platform/theme/default/formcheck/formcheck.css" type="text/css" rel="stylesheet" />
	<link href="${ctx}/platform/theme/default/notimoo/notimoo-v1.1.css" type="text/css" rel="stylesheet" />
	<link href="${ctx}/platform/theme/borderlayout/skin/blue/css/page.css" type="text/css" rel="stylesheet" />
	<link href="${ctx}/platform/theme/borderlayout/skin/blue/css/ecTable.css" type="text/css" rel="stylesheet" />
	
	<!-- js -->
	<script type="text/javascript" src="${ctx}/platform/theme/public_js/pub.js"></script>
	<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
	<script src="${ctx}/platform/theme/public_js/mootools/mootools-core.js" type="text/javascript"></script>
	<script src="${ctx}/platform/theme/public_js/mootools/mootools-more.js" type="text/javascript"></script>
	<script src="${ctx}/platform/theme/public_js/mootools/formcheck/formcheck.js" type="text/javascript"></script>
	<script src="${ctx}/resources/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	<script src="${ctx}/platform/theme/public_js/utils.js" type="text/javascript"></script>
	<script src="${ctx}/platform/theme/public_js/mootools/notimoo/notimoo-v1.1.js" type="text/javascript"></script>
	<script src="${ctx}/platform/theme/public_js/TabUtils.js" type="text/javascript"></script>
	<script src="${ctx}/platform/theme/public_js/mootools/ectable/EcTable.js"></script>
	<script src="${ctx}/resources/js/SimpleUI/SimpleUI.js" type="text/javascript"></script>
	<script type="text/javascript" src="${ctx}/FCKeditor/fckeditor.js"></script>
	
	<s:actionmessage theme="messages" />
  </head> 

  <body>
  	<!-- 公共头部 -->
    	<div class="but_bar">
			<div class="left">
				<span class="pop_button_bar">
					<a href="###" onclick="doAdd('${pageContext.request.contextPath}/meeting_add.action');this.blur();" class="pop_button"><span>新增</span></a>
					<a href="###" onclick="doEdit('${pageContext.request.contextPath}/meeting_edit.action');this.blur();" class="pop_button"><span>编辑</span></a>
					<a href="###" onclick="doDel('${pageContext.request.contextPath}/meeting_delete.action');this.blur();" class="pop_button"><span>删除</span></a>
				</span>
			</div>
			<div >
				<a href="###" onclick="doBack();" class="pop_button" style="margin-left: 155px;"><span>返 回</span></a>
			</div>
			<div class="right">
				<div class="pop_button_bar">
					<a class="pop_button pop_button_refresh" href="###" onclick="javascript:window.refresh();"><span>刷新本页</span></a>
					<a class="pop_button pop_button_close" href="###" onclick="javascript:TabUtil.closeTab();"><span>退出本页</span></a>
				</div>
			</div>
		</div>

		<div id="body_content" class="body_content">
			<!-- 查询panel开始 -->
    		<div class="panel">
				<div class="panel_header">
					<div class="panel_title panel_titleSearchIco">
						上传资料查询
					</div>
					<div class="panel_titleBtnBar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content">
					<!-- 查询内容开始 -->
					<form id="list_form_1" name="list_form_1" action="attendMeeting_list.action" method="post">
						<table class="panel_content_search_form">
							<tr>
								<td style="text-align:right;"> 资料名称：</td>
								<td style="text-align:left;">
									<input type="text" name="attendMeeting.attendMeetingName" id="attendMeeting.attendMeetingName" value="${attendMeeting.attendMeetingName}"/>
									<input type="hidden" name="attendMeeting.meeting.meetingId" value="${attendMeeting.meeting.meetingId }"/>
								</td>
								<td>
									<div class="btn_query_bar pop_button_bar">
										<a class="pop_button" href="javascript:document.getElementById('list_form_1').submit();">
											<span>查 询</span>
										</a>
									</div>
								</td>
							</tr>
						</table>
					</form>
					<!-- 查询内容结束 -->
				</div>
			</div>
    		<!-- 查询panel结束 -->

			<!-- 内容panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						【${fn:substring(attendMeeting.meeting.meetingName,0,9)}】<c:if test="${fn:length(attendMeeting.meeting.meetingName)>9 }">...</c:if>&nbsp;&nbsp;&nbsp;&nbsp;下的资料列表
						<span class="button_bar">
							<c:if test="${not empty myListFlag}">
								<ap:operationbutton />
							</c:if>
						</span>
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content">
					<s:if test="#request.allattendMeetingList.size>0">
						<ec:table items="allattendMeetingList" var="sc" tableId="allattendMeetingList" border="0"
							action="${pageContext.request.contextPath}/attendMeeting_myList.action"
							imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
							width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
							filterable="false" autoIncludeParameters="true" sortable="false">
							<ec:row>
								<ec:column property="checkbox" alias="attendMeetingId_checkbox" headerCell="checkbox">
									<input type="checkbox" value="${sc.attendMeetingId}" name="id" class="row_checkbox">
								</ec:column>
								<ec:column property="attendMeetingName" title="资料名称"/>
								<ec:column property="meeting.meetingName" title="会议名称" width="25%">
									<div title="${sc.meeting.meetingName}">${fn:substring(sc.meeting.meetingName,0,20)}<c:if test="${fn:length(sc.meeting.meetingName)>20}">...</c:if></div>
								</ec:column>
								<ec:column property="organ.organName" title="资料上传单位"/>
								<ec:column property="department.departmentName" title="资料上传部门"/>
								<ec:column property="userInfo.name"  title="资料上传用户"/>
								<ec:column property="commitInfoTime" title="资料上传时间" format="yyyy-MM-dd HH:mm" cell="date"/>
								<ec:column property="null" title="显示详情"><a title="详细信息" href="javascript:view('${sc.attendMeetingId}')"><img src="${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif" border="0" title="详细信息"/></a></ec:column>
							</ec:row>
						</ec:table>
					</s:if>
					<s:else>
						<styles:nolist/>
					</s:else>
				</div>
			</div>
			<!-- 内容panel结束 -->
		</div>
  </body>
</html>
<!-- js -->
<script language="javascript">
	//新增
	function doAdd(action){
		window.location.href="attendMeeting_add.action"+"?attendMeeting.meeting.meetingId=${attendMeeting.meeting.meetingId}";
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
		window.location.href="attendMeeting_edit.action?attendMeeting.attendMeetingId="+items[0].value;
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
			window.location.href="attendMeeting_delete.action?"+ids+"&attendMeeting.meeting.meetingId=${attendMeeting.meeting.meetingId}";
		}
	}
	
	//返回
	function doBack(){
		window.location.href="meeting_list.action";
	}
	
	//详情
	function view(id){
		//window.location.href="attendMeeting_view.action?attendMeeting.attendMeetingId="+id+"&myListFlag=${myListFlag}";
		environment.dialog.open({
			url : "attendMeeting_view.action?attendMeeting.attendMeetingId="+id+"&myListFlag=${myListFlag}",
			width : 800,
			height : 670,
			icon : '${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif',
			title : '会议资料详情'
		});
	}
</script>