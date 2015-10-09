<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="ui" uri="http://www.cdthgk.com/tags/ui"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

		<title>${name}</title>

		<script src="${contextPath}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
		$ENV.onDomReady(function(){
			$ENV.setContext('${contextPath}');
			$ENV.setContainer(window['home_container']);

			window.status = '用户 ：${_.login.actor.user.userInfo.name}  单位：${_.login.actor.user.organ.name}';
		});

		$ENV.loader.loadStyleSheet("${contextPath}/platform/template/borderlayout/skin/blue/css/pageNoButtonBar.css");
		$ENV.loader.loadStyleSheet("${contextPath}/resources/theme/borderlayout/skin/blue/SimpleUI/css/SimpleUI.css");
		$ENV.loader.loadStyleSheet("${contextPath}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css");
		$ENV.loader.loadStyleSheet("${contextPath}/resources/theme/borderlayout/skin/blue/AscribeDialog/main.css");

		$ENV.loader.loadJavaScript("${contextPath}/resources/js/mootools/mootools-core-1.4.js");
		$ENV.loader.loadJavaScript("${contextPath}/resources/js/mootools/mootools-more-1.4.js");
		$ENV.loader.loadJavaScript("${contextPath}/resources/js/SimpleUI/SimpleUI.js");
		$ENV.loader.loadJavaScript("${pageContext.request.contextPath}/platform/resources/js/platform.js");
		$ENV.loader.loadJavaScript("${contextPath}/resources/js/notimoo/notimoo-1.2.1.js", function(){
			$ENV.onDomReady(function(){

			});
		});

		$ENV.loader.loadJavaScript("${pageContext.request.contextPath}/resources/js/AscribeDialog/dialog.js", function(){
		});

		</script>

		<style type="text/css">
		html,body {
			margin: 0;padding: 0;overflow:hidden;
		}
		#home_container {
			position:absolute;
			_position:static;
			top:0;
			bottom:0;
			left:0;
			right:0;
			overflow: hidden;
			width: 100%;
			height: 100%;
		}
		</style>
	</head>
	<body>
		<iframe id="home_container" name="home_container" frameborder="0" scrolling="no" src="${ctx}/bmp/desktopMap/desktopMap_menu1.action?domainId=${domainId}"></iframe>
	</body>
</html>