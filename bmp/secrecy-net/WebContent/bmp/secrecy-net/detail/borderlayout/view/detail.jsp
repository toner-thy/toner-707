<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/department" prefix="department" %>
<%@ taglib uri="http://www.cdthgk.com/tags/theme" prefix="theme" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE link PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">

		<title>填报情况一览</title>

		<script src="${pageContext.request.contextPath }/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
		$ENV.loader.loadStyleSheet("${pageContext.request.contextPath}/platform/theme/<theme:theme module="department"/>/skin/<theme:skin module="department"/>/css/page.css");
		$ENV.loader.loadStyleSheet("${pageContext.request.contextPath}/resources/theme/<theme:theme module="department"/>/skin/<theme:skin module="department"/>/formcheck/css/formcheck.css");
		$ENV.loader.loadStyleSheet("${ctx}/workbench/borderlayout/skin/blue/js/mootools/spinner.css");
		$ENV.loader.loadStyleSheet("${ctx}/workbench/borderlayout/skin/blue/js/mootools/spinner.gif");

		$ENV.loader.loadJavaScript("${pageContext.request.contextPath}/resources/js/mootools/mootools-core-1.4.js");
		$ENV.loader.loadJavaScript("${pageContext.request.contextPath}/resources/js/mootools/mootools-more-1.4.js");
		$ENV.loader.loadJavaScript("${pageContext.request.contextPath}/resources/js/SimpleUI/SimpleUI.js");
		$ENV.loader.loadJavaScript("${pageContext.request.contextPath}/platform/resources/js/platform.js", function(){
			$ENV.onDomReady(function(){
				$$('.div_module').each(function(item, index){
					item.set('load',{
						useSpinner: true,
						onSuccess : function(){

						}
					});
					item.reload = function() {
						item.load(item.get('url') + "?year=${year}&organ.organId=${organ.organId}&_ts="+new Date().getTime());
					};
					item.reload.delay(300 * index);
				});
			});
		});
		</script>
	</head>
	<body>
		<div class="button_bar">
			<div class="left">
				<div class="pop_button_bar">
				&nbsp;&nbsp;${organ.organName}&nbsp;-&nbsp;填报情况一览（${year}）
				</div>
			</div>
			<div class="right">
				<div class="pop_button_bar">
					<a class="pop_button pop_button_refresh" href="###"><span>刷新本页</span></a>
<%-- 					<a class="pop_button pop_button_help" href="###"><span>本页帮助</span></a> --%>
					<a class="pop_button pop_button_close" href="###"><span>退出本页</span></a>
				</div>
			</div>
		</div>
		<div class="body_content">
			<c:forEach var="modulesUri" items="${modulesUris}">
				<div class="div_module" url="${contextPath}${modulesUri}" style="margin-bottom: 10px;"></div>
			</c:forEach>
		</div>
	</body>
</html>