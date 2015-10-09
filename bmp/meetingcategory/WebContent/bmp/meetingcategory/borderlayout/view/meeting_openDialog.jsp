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
	<iframe name="meetingOpenList" id="meetingOpenList" src="<s:url action="meeting_openList"/>" height="99%" width="99%" scrolling="no" frameborder="0"></iframe>
  </body>
</html>