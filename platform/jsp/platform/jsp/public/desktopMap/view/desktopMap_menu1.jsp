<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

		<title></title>

		<script src="${pageContext.request.contextPath}/resources/js/environment/environment.js" type="text/javascript"></script>
		<style type="text/css">
		html,body {
			margin: 0;padding: 0;overflow:hidden;
		}
		iframe {
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
		<iframe id="virtual_desktop_code" name="virtual_desktop_code" frameborder="0" scrolling="no" src="${pageContext.request.contextPath}/bmp/desktopMap/desktopMap_menu2.action?domainId=${domainId}"></iframe>
	</body>
</html>