<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap" %>
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="ui" %>
<%@ taglib uri="http://www.cdthgk.com/tags/permission/role" prefix="role" %>
<%@ taglib uri="http://www.cdthgk.com/tags/permission/user" prefix="user" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>角色分配用户</title>

		<script src="${pageContext.request.contextPath}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
		$ENV.loader.loadStyleSheet("${pageContext.request.contextPath}/platform/template/borderlayout/skin/blue/css/page.css");
		$ENV.loader.loadJavaScript("${pageContext.request.contextPath}/resources/js/mootools/mootools-core-1.4.js");
		$ENV.loader.loadJavaScript("${pageContext.request.contextPath}/resources/js/mootools/mootools-more-1.4.js");
		$ENV.loader.loadJavaScript("${pageContext.request.contextPath}/platform/resources/js/platform.js");
		$ENV.loader.loadJavaScript("${pageContext.request.contextPath}/resources/js/SimpleUI/SimpleUI.js",function() {
			$ENV.onDomReady(function(){
				$('btnSave').addEvent('click', function(){
					var form = $('role_assign_form');
					new Request.JSON({
					    url: form.action,
					    onComplete: function(result, text){
					    	window.alert(result.message);
					    }
					}).post(form.toQueryString());
				});
			});
		});
		</script>

		<s:actionmessage theme="messages"/>
	</head>
	<body>
		<div class="button_bar">
			<div class="left">
				<div class="pop_button_bar">
					<a id="btnSave" class="pop_button" href="###"><span>保 存</span></a>
				</div>
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
						角色分配用户
					</div>
					<%-- 右侧按钮 --%>
					<div class="panel_titleBtnBar">
					</div>
				</div>
				<%-- 内容 --%>
				<div class="panel_content panel_content_table">
					<s:form action="roleAssigning" namespace="/platform/permission/role" name="role_assign_form" id="role_assign_form" theme="simple">
							<input type="hidden" id="type" name="type" value="${type}"/>
					<table class="content_table">
						<tr>
							<td align="right" style="width: 120px">
								角色：
							</td>
							<td>
								<role:multySelectByOrgan textEl="roleNames" valueEl="roleIds" required="true" onlyFromValue="true"
									value="${roles}" textProperty="roleChineseName" valueProperty="roleId" includeShare="true" organId="${organ.organId}"/>
							</td>
						</tr>
						<tr>
							<td colspan="2" style="padding: 8px;">
							</td>
						</tr>
						<tr>
							<td align="right">
								用户：
							</td>
							<td>
								<c:choose>
								<c:when test="${type eq 'organ'}">
									<user:multySelectByOrgan textEl="userNames" valueEl="userIds" buttonEl="btnSelectUser"
										required="true" onlyFromValue="true" value="${users}" textProperty="userInfo.name" valueProperty="userId" organId="${organ.organId }"/>
								</c:when>
								<c:otherwise>
									<user:multySelectByDistrict textEl="userNames" valueEl="userIds" buttonEl="btnSelectUser"
										required="true" onlyFromValue="true" value="${users}" textProperty="userInfo.name" valueProperty="userId"/>
								</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>
							<td colspan="2" style="padding: 8px;">
							</td>
						</tr>
					</table>
					</s:form>
				</div>
			</div>
		</div>
	</body>
</html>