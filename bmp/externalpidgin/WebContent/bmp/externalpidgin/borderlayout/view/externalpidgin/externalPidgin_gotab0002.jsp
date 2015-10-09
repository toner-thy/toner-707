<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html style="overflow: hidden;">
	<head>
		<title>test</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<link href="${pageContext.request.contextPath}/platform/theme/default/css/style.css" type="text/css" rel="stylesheet" />
		<link href="${pageContext.request.contextPath}/platform/theme/default/css/tab_style.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/mootools/mootools-core.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/mootools/mootools-more.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/navigationTag2.js"></script>
	</head>
	<body class="container" style="overflow: hidden">
		<%--<ap:step></ap:step>--%>
		<ap:tab2></ap:tab2>
	</body>
</html>
<script>
	var height = window.getCoordinates().height;
	$$(".container").setStyle("height",height);
	$('auditMain').setStyle("height",height-32);
</script>