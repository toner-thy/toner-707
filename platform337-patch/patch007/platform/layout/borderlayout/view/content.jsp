<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="configuration" uri="http://www.cdthgk.com/tags/configuration"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>

		<title></title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">

	    <script src="${pageContext.request.contextPath}/resources/js/environment/environment.js" type="text/javascript"></script>

	<c:choose>
		<c:when test="${useTab}">
		<script type="text/javascript">
		$ENV.loader.loadStyleSheet("${pageContext.request.contextPath}/resources/theme/borderlayout/skin/blue/mifmenu/css/menu_default.css");
		$ENV.loader.loadStyleSheet("${pageContext.request.contextPath}/resources/theme/borderlayout/skin/blue/SimpleUI/css/TabPanel.css");
		$ENV.loader.loadJavaScript("${pageContext.request.contextPath}/resources/js/mootools/mootools-core-1.4.js");
		$ENV.loader.loadJavaScript("${pageContext.request.contextPath}/resources/js/mootools/mootools-more-1.4.js");
		$ENV.loader.loadJavaScript("${pageContext.request.contextPath}/resources/js/mifmenu/1.3/mif.menu.js");

		$ENV.loader.loadJavaScript("${pageContext.request.contextPath}/resources/js/SimpleUI/TabPanel.js", function(){
			$ENV.onDomReady(function(){
				//var url = "${index}";
				//var title = "首页";
				// 单位类型：UNKNOW SECRECY_OFFICE ORGAN_MANAGER
				//if('${_.login.actor.user.organ.organType}' == 'ORGAN_MANAGER'){
					//BMS
				//}
				var url = "${pageContext.request.contextPath}/bmp/newbieGuide/newbieGuide_index.action";
				var	title = "新手引导";
				tp = new TabPanel(document.body, {
					title: title,
 					max : '${_.config.borderlayout.tabMax}',
 					maxPolicy : '${_.config.borderlayout.tabMaxPolicy}',
					url : url
					<%--
					url : "${pageContext.request.contextPath}/workbench/workbench/index.action"
					--%>
				});
			});
		});

		var tp;
		function load(p) {
			if (p) {
				tp.add({
					title : p.title,
					url : p.url,
					close : true
				});
			}
		}
		</script>
		</c:when>
		<c:otherwise>
		<script type="text/javascript">
		function load(p) {
			if (p) {
				document.getElementById('content').src = p.url;
			}
		}
		</script>
		</c:otherwise>
	</c:choose>

		<style type="text/css">
		* { padding:0; margin:0;}
		html,body {
			overflow:hidden;
		}
		</style>
		<style type="text/css">
		html,body {
			margin: 0;padding: 0;overflow:hidden;
		}
		#content {
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
		<c:if test="${!useTab}">
		<iframe id="content" name="content" frameborder="0" scrolling="no" src="${index}"></iframe>
		</c:if>
	</body>
</html>
