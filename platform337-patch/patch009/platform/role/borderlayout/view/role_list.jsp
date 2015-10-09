<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap" %>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="ui" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dic" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>角色列表</title>

		<script src="${pageContext.request.contextPath}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
		$ENV.loader.loadStyleSheet("${pageContext.request.contextPath}/platform/template/borderlayout/skin/blue/css/page.css");
		$ENV.loader.loadStyleSheet("${pageContext.request.contextPath}/platform/template/borderlayout/skin/blue/css/ecTable.css");
		$ENV.loader.loadStyleSheet("${pageContext.request.contextPath}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css");
		$ENV.loader.loadJavaScript("${pageContext.request.contextPath}/resources/js/mootools/mootools-core-1.4.js");
		$ENV.loader.loadJavaScript("${pageContext.request.contextPath}/resources/js/mootools/mootools-more-1.4.js");
		$ENV.loader.loadJavaScript("${pageContext.request.contextPath}/platform/resources/js/platform.js");
		$ENV.loader.loadJavaScript("${pageContext.request.contextPath}/resources/js/SimpleUI/SimpleUI.js",function() {
			$ENV.onDomReady(function(){

			});
		});
		$ENV.loader.loadJavaScript("${pageContext.request.contextPath}/platform/template/borderlayout/resources/js/ectable/EcTable.js");
		$ENV.loader.loadJavaScript("${pageContext.request.contextPath}/resources/js/notimoo/notimoo-1.2.1.js");

		function doRoleAssign(action, id) {
			var items = EcTable.getCheckedItems();
			if(items.length ==0){
				alert("请选择一项！");
				return;
			}
			var ids = "?";
			items.each(function(item){
				ids += "ids=" + item.value + "&";
			});
			ids += '&_ts=' + new Date().getTime()<c:if test="${not empty param['organ.organId']}">
			+ "&organ.organId=${param['organ.organId']}"</c:if>;
			$ENV.dialog.open({
				title : '',
				url : action + ids,
				width : 0.8,
				height : 0.9
			});
		}
		function doRoleAssignAdmin(action, id) {
			var items = EcTable.getCheckedItems();
			if(items.length ==0){
				alert("请选择一项！");
				return;
			}
			var msg = "";
			items.each(function(item){
				var id = item.value;
				if ($(id + "_shareRole").value) {
					msg += invaliteMsg($(id + "_name").value) + "\n";
				}
			});
			if (msg) {
				alert(msg);
				return;
			}
			var ids = "?";
			items.each(function(item){
				ids += "ids=" + item.value + "&";
			});
			ids += '&_ts=' + new Date().getTime()<c:if test="${not empty param['organ.organId']}">
			+ "&organ.organId=${param['organ.organId']}"</c:if>;
			$ENV.dialog.open({
				title : '角色分配用户（管理）',
				url : action + ids,
				width : 0.8,
				height : 0.9
			});
		}

		function doRoleAuth(action) {
			var items = EcTable.getCheckedItems();
			if(items.length==0){
				alert("请选择一项！");
				return;
			}else if(items.length>1){
				alert("请最多选择一项！");
				return;
			}
			var id = items[0].value;
			if ($(id + "_shareRole").value) {
				alert(invaliteMsg($(id + "_name").value));
				return;
			}
		 	//window.showModalDialog(action+'?_ts=' + new Date().getTime() + '&id='+items[0].value,window,'dialogWidth=750px;dialogHeight=800px;status=no;directories=no;menubar=no;resizable=yes;scrollbars=auto');
			$ENV.dialog.open({
				title : '分配角色权限： ' + $(id + "_name").value,
				url : action+'?_ts=' + new Date().getTime() + '&id='+id<c:if test="${not empty param['organ.organId']}">
										+ "&organ.organId=${param['organ.organId']}"</c:if>,
				width : 400,
				height : 0.9
			});
		}

		function doAdd(action) {
			window.location.href=action+"?_ts="+$time()<c:if test="${not empty param['organ.organId']}">
			+ "&organ.organId=${param['organ.organId']}"</c:if>;
		}

		function doEdit(action) {
			var items = EcTable.getCheckedItems();
			if(items.length==0){
				alert("请选择一项！");
				return;
			}else if(items.length>1){
				alert("请最多选择一项！");
				return;
			}
			var id = items[0].value;
			if ($(id + "_shareRole").value) {
				alert(invaliteMsg($(id + "_name").value));
				return;
			}
			window.location.href=action+"?id="+id<c:if test="${not empty param['organ.organId']}">
			+ "&organ.organId=${param['organ.organId']}"</c:if>;
		}

		function invaliteMsg(roleName) {
			return "角色[" + roleName + "]是共享角色，只能分配用户";
		}

		function doDel(action) {
			var items = EcTable.getCheckedItems();
			if(items.length ==0){
				alert("请选择一项！");
				return;
			}
			var msg = "";
			items.each(function(item){
				var id = item.value;
				if ($(id + "_shareRole").value) {
					msg += invaliteMsg($(id + "_name").value) + "\n";
				}
			});
			if (msg) {
				alert(msg);
				return;
			}
			var ids = "?";
			items.each(function(item){
				ids += "ids=" + item.value + "&";
			});
			if(window.confirm("确定删除吗？")){
				window.location.href=action+ids<c:if test="${not empty param['organ.organId']}">
				+ "&organ.organId=${param['organ.organId']}"</c:if>;
			}
		}
		function doShare(action) {
			 var items = EcTable.getCheckedItems();
				if(items.length ==0){
					alert("请选择一项！");
					return;
				}
				var msg = "";
				items.each(function(item){
					var id = item.value;
					if ($(id + "_shareRole").value) {
						msg += invaliteMsg($(id + "_name").value) + "\n";
					}
				});
				if (msg) {
					alert(msg);
					return;
				}
				var ids = "?";
				items.each(function(item){
					ids += "ids=" + item.value + "&";
				});
				ids += '&_ts=' + new Date().getTime()<c:if test="${not empty param['organ.organId']}">
				+ "&organ.organId=${param['organ.organId']}"</c:if>;
				$ENV.dialog.open({
					title : '',
					url : action + ids,
					width : 0.8,
					height : 0.9
				});
		}
		</script>

		<s:actionmessage theme="messages"/>
	</head>
	<body>
		<div class="button_bar">
			<div class="left">
				<ap:operationbutton />
			</div>
			<div class="right">
				<div class="pop_button_bar">
					<a class="pop_button pop_button_refresh" href="javascript:refresh();"><span>刷新本页</span></a>
					<a class="pop_button pop_button_max" href="###"><span>最大化</span></a>
					<a class="pop_button pop_button_help" href="###"><span>本页帮助</span></a>
					<a class="pop_button pop_button_close" href="###"><span>退出本页</span></a>
				</div>
			</div>
		</div>
		<div id="body_content" class="body_content">
			<div class="panel">
				<%-- 头部 --%>
				<div class="panel_header">
					<%-- 标题 --%>
					<div class="panel_title panel_search_ico">
						快速查询
					</div>
					<%-- 右侧按钮 --%>
					<div class="panel_titleBtnBar">
					</div>
				</div>
				<%-- 内容 --%>
				<div class="panel_content">
					<s:form action="role_list" namespace="/platform/role" name="role_list_form" id="role_list_form" theme="simple">
					<input type="hidden" name="organ.organId" value="${organ.organId}"/>
					<table class="panel_content_search_form">
						<tr>
							<td align="right">
								角色中文名称：
							</td>
							<td>
								<input type="text" name="role.roleChineseName" id="role.roleChineseName" value="${role.roleChineseName}" />
							</td>
							<td align="right">
								角色英文名称：
							</td>
							<td>
								<input type="text" name="role.roleEnglishName" id="role.roleEnglishName" value="${role.roleEnglishName}"/>
							</td>
							<td>
								<div class="btn_query_bar pop_button_bar">
									<a class="pop_button" href="javascript:document.getElementById('role_list_form').submit();"><span>查询</span></a>
								</div>
							</td>
						</tr>
					</table>
					</s:form>
				</div>
			</div>
			<br/>
			<div class="panel">
				<%-- 头部 --%>
				<div class="panel_header">
					<%-- 标题 --%>
					<div class="panel_title panel_list_ico">
					角色列表
					</div>
					<%-- 右侧按钮 --%>
					<div class="panel_btn_bar pop_button_bar">

					</div>
				</div>
				<div class="panel_content">
				<s:if test="#request.list.size>0">
					<ec:table items="list" var="role" tableId="${listId }" border="0"
						action="${pageContext.request.contextPath}/platform/role/role_list.action"
						imagePath="${pageContext.request.contextPath}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
						width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
						filterable="false" autoIncludeParameters="true" sortable="true">
						<ec:row>
							<ec:column property="roleId" alias="roleId_checkbox" cell="checkbox" headerCell="checkbox"/>
							<ec:column property="roleChineseName" title="角色中文名称"/>
							<ec:column property="roleEnglishName" title="角色英文名称"/>

							<ec:column property="null" title="是否自动指派" sortable="false">
								 <dic:text fieldCode="yes_no" optionValue="${role.autoAssign}"/>
							</ec:column>
							<ec:column property="null" title="是否共享角色" sortable="false">
								<c:if test="${organ.id == role.organ.id}">否<input id="${role.roleId}_shareRole" type="hidden" value=""></c:if>
								<c:if test="${organ.id != role.organ.id}">是（共享自：${role.organ.name}）<input id="${role.roleId}_shareRole" type="hidden" value="true"></c:if>
							</ec:column>
							<ec:column property="null" title="描述" sortable="false">
									<input type="hidden" id="${role.roleId}_name" value="${role.roleChineseName}"/>
									<div title="${fn:substring(role.description,0,40)}">${fn:substring(role.description,0,20) }
									<c:if test="${fn:length(role.description)>20 }">.....</c:if></div>
							</ec:column>
							<%--
							<ec:column property="roleId" title="操作" sortable="false"><ap:operationlink value="${role.roleId}" />&nbsp;</ec:column>
							 --%>
						</ec:row>
					</ec:table>
				</s:if>
				<s:else>
					<ui:noData/>
				</s:else>
				</div>
			</div>
		</div>
	</body>
</html>